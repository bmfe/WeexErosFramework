package com.eros.framework.extend.adapter.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Synthetic;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class DefaultImageViewTarget extends DrawableImageViewTarget {

    private static final String TAG = "DynamicImageViewTarget";
    private static boolean isOriginal = true;
    private DynamicSizeDeterminer dynamicSizeDeterminer;
    private ImageLoadListener mLoadListener;

    public interface ImageLoadListener {
        void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super
                Drawable> transition);

        void onLoadFailed(@Nullable Drawable errorDrawable);
    }

    public void setImageLoadListener(ImageLoadListener listener) {
        this.mLoadListener = listener;
    }

    public DefaultImageViewTarget(ImageView view) {
        super(view);
        dynamicSizeDeterminer = new DynamicSizeDeterminer(view);
    }


    public ViewTarget waitForLayoutSub() {
        dynamicSizeDeterminer.waitForLayout = true;
        return this;
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void getSize(@NonNull SizeReadyCallback cb) {
        dynamicSizeDeterminer.getSize(cb);
    }


    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super
            Drawable> transition) {
        super.onResourceReady(resource, transition);
        if (mLoadListener != null) {
            mLoadListener.onResourceReady(resource, transition);
        }
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        if (mLoadListener != null) {
            mLoadListener.onLoadFailed(errorDrawable);
        }
    }


    @Override
    public void removeCallback(@NonNull SizeReadyCallback cb) {
        super.removeCallback(cb);
        dynamicSizeDeterminer.removeCallback(cb);
    }


    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {
        super.onLoadCleared(placeholder);
        dynamicSizeDeterminer.clearCallbacksAndListener();
    }

    static final class DynamicSizeDeterminer {
        // Some negative sizes (Target.SIZE_ORIGINAL) are valid, 0 is never valid.
        private static final int PENDING_SIZE = 0;
        @VisibleForTesting
        @Nullable
        static Integer maxDisplayLength;
        private final View view;
        private final List<SizeReadyCallback> cbs = new ArrayList<>();
        @Synthetic
        boolean waitForLayout;

        @Nullable
        private DefaultImageViewTarget.DynamicSizeDeterminer.SizeDeterminerLayoutListener
                layoutListener;

        DynamicSizeDeterminer(@NonNull View view) {
            this.view = view;
        }

        // Use the maximum to avoid depending on the device's current orientation.
        private static int getMaxDisplayLength(@NonNull Context context) {
            if (maxDisplayLength == null) {
                WindowManager windowManager =
                        (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = Preconditions.checkNotNull(windowManager).getDefaultDisplay();
                Point displayDimensions = new Point();
                display.getSize(displayDimensions);
                maxDisplayLength = Math.max(displayDimensions.x, displayDimensions.y);
            }
            return maxDisplayLength;
        }

        private void notifyCbs(int width, int height) {
            // One or more callbacks may trigger the removal of one or more additional callbacks,
            // so we
            // need a copy of the list to avoid a concurrent modification exception. One place this
            // happens is when a full request completes from the in memory cache while its
            // thumbnail is
            // still being loaded asynchronously. See #2237.
            for (SizeReadyCallback cb : new ArrayList<>(cbs)) {
                cb.onSizeReady(width, height);
            }
        }

        @Synthetic
        void checkCurrentDimens() {
            if (cbs.isEmpty()) {
                return;
            }

            int currentWidth = getTargetWidth();
            int currentHeight = getTargetHeight();
            if (!isViewStateAndSizeValid(currentWidth, currentHeight)) {
                return;
            }

            notifyCbs(currentWidth, currentHeight);
            clearCallbacksAndListener();
        }

        void getSize(@NonNull SizeReadyCallback cb) {
            int currentWidth = getTargetWidth();
            int currentHeight = getTargetHeight();
            if (isViewStateAndSizeValid(currentWidth, currentHeight)) {
                cb.onSizeReady(currentWidth, currentHeight);
                return;
            }

            // We want to notify callbacks in the order they were added and we only expect one or
            // two
            // callbacks to be added a time, so a List is a reasonable choice.
            if (!cbs.contains(cb)) {
                cbs.add(cb);
            }
            if (layoutListener == null) {
                ViewTreeObserver observer = view.getViewTreeObserver();
                layoutListener = new DefaultImageViewTarget.DynamicSizeDeterminer
                        .SizeDeterminerLayoutListener(this);
                observer.addOnPreDrawListener(layoutListener);
            }
        }

        /**
         * The callback may be called anyway if it is removed by another
         * {@link SizeReadyCallback} or
         * otherwise removed while we're notifying the list of callbacks.
         *
         * <p>See #2237.
         */
        void removeCallback(@NonNull SizeReadyCallback cb) {
            cbs.remove(cb);
        }

        void clearCallbacksAndListener() {
            // Keep a reference to the layout attachStateListener and remove it here
            // rather than having the observer remove itself because the observer
            // we add the attachStateListener to will be almost immediately merged into
            // another observer and will therefore never be alive. If we instead
            // keep a reference to the attachStateListener and remove it here, we get the
            // current view tree observer and should succeed.
            ViewTreeObserver observer = view.getViewTreeObserver();
            if (observer.isAlive()) {
                observer.removeOnPreDrawListener(layoutListener);
            }
            layoutListener = null;
            cbs.clear();
        }

        private boolean isViewStateAndSizeValid(int width, int height) {
            return isDimensionValid(width) && isDimensionValid(height);
        }

        private int getTargetHeight() {
            if (isOriginal) {
                return SIZE_ORIGINAL;
            }
            int verticalPadding = view.getPaddingTop() + view.getPaddingBottom();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int layoutParamSize = layoutParams != null ? layoutParams.height : PENDING_SIZE;
            return getTargetDimen(view.getHeight(), layoutParamSize, verticalPadding);
        }

        private int getTargetWidth() {
            if (isOriginal) {
                return SIZE_ORIGINAL;
            }
            int horizontalPadding = view.getPaddingLeft() + view.getPaddingRight();
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            int layoutParamSize = layoutParams != null ? layoutParams.width : PENDING_SIZE;
            return getTargetDimen(view.getWidth(), layoutParamSize, horizontalPadding);
        }

        private int getTargetDimen(int viewSize, int paramSize, int paddingSize) {
            // We consider the View state as valid if the View has non-null layout params and a
            // non-zero
            // layout params width and height. This is imperfect. We're making an assumption that
            // View
            // parents will obey their child's layout parameters, which isn't always the case.
            int adjustedParamSize = paramSize - paddingSize;
            if (adjustedParamSize > 0) {
                return adjustedParamSize;
            }

            // Since we always prefer layout parameters with fixed sizes, even if waitForLayout
            // is true,
            // we might as well ignore it and just return the layout parameters above if we have
            // them.
            // Otherwise we should wait for a layout pass before checking the View's dimensions.
            if (waitForLayout && view.isLayoutRequested()) {
                return PENDING_SIZE;
            }

            // We also consider the View state valid if the View has a non-zero width and height.
            // This
            // means that the View has gone through at least one layout pass. It does not mean
            // the Views
            // width and height are from the current layout pass. For example, if a View is
            // re-used in
            // RecyclerView or ListView, this width/height may be from an old position. In some
            // cases
            // the dimensions of the View at the old position may be different than the
            // dimensions of the
            // View in the new position because the LayoutManager/ViewParent can arbitrarily
            // decide to
            // change them. Nevertheless, in most cases this should be a reasonable choice.
            int adjustedViewSize = viewSize - paddingSize;
            if (adjustedViewSize > 0) {
                return adjustedViewSize;
            }

            // Finally we consider the view valid if the layout parameter size is set to
            // wrap_content.
            // It's difficult for Glide to figure out what to do here. Although Target
            // .SIZE_ORIGINAL is a
            // coherent choice, it's extremely dangerous because original images may be much too
            // large to
            // fit in memory or so large that only a couple can fit in memory, causing OOMs. If
            // users want
            // the original image, they can always use .override(Target.SIZE_ORIGINAL). Since
            // wrap_content
            // may never resolve to a real size unless we load something, we aim for a square
            // whose length
            // is the largest screen size. That way we're loading something and that something
            // has some
            // hope of being downsampled to a size that the device can support. We also log a
            // warning that
            // tries to explain what Glide is doing and why some alternatives are preferable.
            // Since WRAP_CONTENT is sometimes used as a default layout parameter, we always wait
            // for
            // layout to complete before using this fallback parameter (ConstraintLayout among
            // others).
            if (!view.isLayoutRequested() && paramSize == ViewGroup.LayoutParams.WRAP_CONTENT) {
                if (Log.isLoggable(TAG, Log.INFO)) {
                    Log.i(TAG, "Glide treats LayoutParams.WRAP_CONTENT as a request for an image " +
                            "the size of"
                            + " this device's screen dimensions. If you want to load the original" +
                            " image and are"
                            + " ok with the corresponding memory cost and OOMs (depending on the " +
                            "input size), use"
                            + " .override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams" +
                            ".MATCH_PARENT, set"
                            + " layout_width and layout_height to fixed dimension, or use " +
                            ".override() with fixed"
                            + " dimensions.");
                }
                return getMaxDisplayLength(view.getContext());
            }

            // If the layout parameters are < padding, the view size is < padding, or the layout
            // parameters are set to match_parent or wrap_content and no layout has occurred, we
            // should
            // wait for layout and repeat.
            return PENDING_SIZE;
        }

        private boolean isDimensionValid(int size) {
            return size > 0 || size == SIZE_ORIGINAL;
        }

        private static final class SizeDeterminerLayoutListener
                implements ViewTreeObserver.OnPreDrawListener {
            private final WeakReference<DefaultImageViewTarget.DynamicSizeDeterminer>
                    sizeDeterminerRef;

            SizeDeterminerLayoutListener(@NonNull DefaultImageViewTarget.DynamicSizeDeterminer
                                                 sizeDeterminer) {
                sizeDeterminerRef = new WeakReference<>(sizeDeterminer);
            }

            @Override
            public boolean onPreDraw() {
                if (Log.isLoggable(TAG, Log.VERBOSE)) {
                    Log.v(TAG, "OnGlobalLayoutListener called attachStateListener=" + this);
                }
                DefaultImageViewTarget.DynamicSizeDeterminer sizeDeterminer = sizeDeterminerRef
                        .get();
                if (sizeDeterminer != null) {
                    sizeDeterminer.checkCurrentDimens();
                }
                return true;
            }
        }
    }
}
