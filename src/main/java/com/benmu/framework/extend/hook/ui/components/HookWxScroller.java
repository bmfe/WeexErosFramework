package com.benmu.framework.extend.hook.ui.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.benmu.framework.extend.hook.HookConstants;
import com.benmu.framework.extend.hook.ui.view.HookBounceScrollerView;
import com.benmu.framework.extend.hook.ui.view.HookWXHorizontalScrollView;
import com.benmu.framework.extend.hook.ui.view.refresh.bmrefresh.BMBaseRefresh;
import com.benmu.framework.extend.hook.ui.view.refresh.bmrefresh.BMLoadingRefresh;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.ICheckBindingScroller;
import com.taobao.weex.common.OnWXScrollListener;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.ComponentCreator;
import com.taobao.weex.ui.component.AppearanceHelper;
import com.taobao.weex.ui.component.Scrollable;
import com.taobao.weex.ui.component.WXBaseRefresh;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXLoading;
import com.taobao.weex.ui.component.WXRefresh;
import com.taobao.weex.ui.component.WXScroller;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.component.helper.ScrollStartEndHelper;
import com.taobao.weex.ui.component.helper.WXStickyHelper;
import com.taobao.weex.ui.view.IWXScroller;
import com.taobao.weex.ui.view.WXBaseRefreshLayout;
import com.taobao.weex.ui.view.WXHorizontalScrollView;
import com.taobao.weex.ui.view.WXScrollView;
import com.taobao.weex.ui.view.refresh.wrapper.BaseBounceView;
import com.taobao.weex.ui.view.refresh.wrapper.BounceScrollerView;
import com.taobao.weex.utils.WXLogUtils;
import com.taobao.weex.utils.WXUtils;
import com.taobao.weex.utils.WXViewUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Carry on 2018/4/19.
 */

public class HookWxScroller extends WXScroller implements WXScrollView.WXScrollViewListener, Scrollable {
    public static final String DIRECTION = "direction";
    protected int mOrientation;
    private List<WXComponent> mRefreshs;
    private int mChildrenLayoutOffset;
    private boolean mForceLoadmoreNextTime;
    private int mOffsetAccuracy;
    private Point mLastReport;
    private boolean mHasAddScrollEvent;
    private ScrollStartEndHelper mScrollStartEndHelper;
    private Map<String, AppearanceHelper> mAppearanceComponents;
    private Map<String, Map<String, WXComponent>> mStickyMap;
    private FrameLayout mRealView;
    private int mContentHeight;
    private WXStickyHelper stickyHelper;
    private Handler handler;
    private boolean isScrollable;

    /** @deprecated */
    @Deprecated
    public HookWxScroller(WXSDKInstance instance, WXDomObject dom, WXVContainer parent, String instanceId, boolean isLazy) {
        this(instance, dom, parent);
    }

    public HookWxScroller(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
        this.mOrientation = 1;
        this.mRefreshs = new ArrayList();
        this.mChildrenLayoutOffset = 0;
        this.mForceLoadmoreNextTime = false;
        this.mOffsetAccuracy = 10;
        this.mLastReport = new Point(-1, -1);
        this.mHasAddScrollEvent = false;
        this.mAppearanceComponents = new HashMap();
        this.mStickyMap = new HashMap();
        this.mContentHeight = 0;
        this.handler = new Handler();
        this.isScrollable = true;
        this.stickyHelper = new WXStickyHelper(this);
    }

    public ViewGroup getRealView() {
        return this.mRealView;
    }

    public void createViewImpl() {
        super.createViewImpl();
    }

    public ViewGroup getInnerView() {
        return this.getHostView() == null?null:(this.getHostView() instanceof BounceScrollerView?(ViewGroup)((BounceScrollerView)this.getHostView()).getInnerView():(ViewGroup)this.getHostView());
    }

    public void addEvent(String type) {
        super.addEvent(type);
    }

    private void fireScrollEvent(Rect contentFrame, int x, int y, int oldx, int oldy) {
        this.fireEvent("scroll", this.getScrollEvent(x, y));
    }

    public Map<String, Object> getScrollEvent(int x, int y) {
        Rect contentFrame = new Rect();
        if(this.getInnerView() instanceof WXScrollView) {
            contentFrame = ((WXScrollView)this.getInnerView()).getContentFrame();
        } else if(this.getInnerView() instanceof WXHorizontalScrollView) {
            contentFrame = ((WXHorizontalScrollView)this.getInnerView()).getContentFrame();
        }

        Map<String, Object> event = new HashMap(2);
        Map<String, Object> contentSize = new HashMap(2);
        Map<String, Object> contentOffset = new HashMap(2);
        int viewport = this.getInstance().getInstanceViewPortWidth();
        contentSize.put("width", Float.valueOf(WXViewUtils.getWebPxByWidth((float)contentFrame.width(), viewport)));
        contentSize.put("height", Float.valueOf(WXViewUtils.getWebPxByWidth((float)contentFrame.height(), viewport)));
        contentOffset.put("x", Float.valueOf(-WXViewUtils.getWebPxByWidth((float)x, viewport)));
        contentOffset.put("y", Float.valueOf(-WXViewUtils.getWebPxByWidth((float)y, viewport)));
        event.put("contentSize", contentSize);
        event.put("contentOffset", contentOffset);
        return event;
    }

    private boolean shouldReport(int x, int y) {
        if(this.mLastReport.x == -1 && this.mLastReport.y == -1) {
            this.mLastReport.x = x;
            this.mLastReport.y = y;
            return true;
        } else if(this.mOrientation == 0 && Math.abs(x - this.mLastReport.x) >= this.mOffsetAccuracy) {
            this.mLastReport.x = x;
            this.mLastReport.y = y;
            return true;
        } else if(this.mOrientation == 1 && Math.abs(y - this.mLastReport.y) >= this.mOffsetAccuracy) {
            this.mLastReport.x = x;
            this.mLastReport.y = y;
            return true;
        } else {
            return false;
        }
    }

    public void addSubView(View child, int index) {
        if(child != null && this.getRealView() != null) {
            if(!(child instanceof WXBaseRefreshLayout)) {
                int count = this.getRealView().getChildCount();
                index = index >= count?-1:index;
                if(index == -1) {
                    this.getRealView().addView(child);
                } else {
                    this.getRealView().addView(child, index);
                }

            }
        }
    }

    protected int getChildrenLayoutTopOffset() {
        return this.mChildrenLayoutOffset;
    }

    public void addChild(WXComponent child, int index) {
        super.addChild(child, index);
    }

    private boolean checkRefreshOrLoading(final WXComponent child) {
        boolean result = false;
        Runnable runnable;
        if(child instanceof WXRefresh && this.getHostView() != null) {
            ((BaseBounceView)this.getHostView()).setOnRefreshListener((WXRefresh)child);
            runnable = WXThread.secure(new Runnable() {
                public void run() {
                    ((BaseBounceView)getHostView()).setHeaderView(child);
                }
            });
            this.handler.postDelayed(runnable, 100L);
        }

        if(child instanceof WXLoading && this.getHostView() != null) {
            ((BaseBounceView)this.getHostView()).setOnLoadingListener((WXLoading)child);
            runnable = WXThread.secure(new Runnable() {
                public void run() {
                    ((BaseBounceView)getHostView()).setFooterView(child);
                }
            });
            this.handler.postDelayed(runnable, 100L);
            result = true;
        }

        return result;
    }

    public void remove(WXComponent child, boolean destory) {
        super.remove(child, destory);

    }

    public void destroy() {
        super.destroy();
    }

    protected MeasureOutput measure(int width, int height) {
        MeasureOutput measureOutput = new MeasureOutput();
        int screenW;
        int weexW;
        if(this.mOrientation == 0) {
            screenW = WXViewUtils.getScreenWidth(WXEnvironment.sApplication);
            weexW = WXViewUtils.getWeexWidth(this.getInstanceId());
            measureOutput.width = width > (weexW >= screenW?screenW:weexW)?-1:width;
            measureOutput.height = height;
        } else {
            screenW = WXViewUtils.getScreenHeight(WXEnvironment.sApplication);
            weexW = WXViewUtils.getWeexHeight(this.getInstanceId());
            measureOutput.height = height > (weexW >= screenW?screenW:weexW)?-1:height;
            measureOutput.width = width;
        }

        return measureOutput;
    }

    protected ViewGroup initComponentHostView(@NonNull Context context) {
        String scroll;
        if(this.getDomObject() != null && !this.getDomObject().getAttrs().isEmpty()) {
            scroll = this.getDomObject().getAttrs().getScrollDirection();
        } else {
            scroll = "vertical";
        }

        Object host;
        if("horizontal".equals(scroll)) {
            this.mOrientation = 0;
            HookWXHorizontalScrollView scrollView = new HookWXHorizontalScrollView(context);
            this.mRealView = new FrameLayout(context);
            scrollView.setScrollViewListener(new WXHorizontalScrollView.ScrollViewListener() {
                public void onScrollChanged(WXHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                    procAppear(x, y, oldx, oldy);
                }
            });
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            scrollView.addView(this.mRealView, layoutParams);
            scrollView.setHorizontalScrollBarEnabled(false);
            host = scrollView;
        } else {
            this.mOrientation = 1;
            HookBounceScrollerView scrollerView = new HookBounceScrollerView(context, this.mOrientation, this);
            this.mRealView = new FrameLayout(context);
            WXScrollView innerView = (WXScrollView)scrollerView.getInnerView();
            innerView.addScrollViewListener(this);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            innerView.addView(this.mRealView, layoutParams);
            innerView.setVerticalScrollBarEnabled(true);
            innerView.setNestedScrollingEnabled(WXUtils.getBoolean(this.getDomObject().getAttrs().get("nestedScrollingEnabled"), Boolean.valueOf(true)).booleanValue());
            innerView.addScrollViewListener(new WXScrollView.WXScrollViewListener() {
                public void onScrollChanged(WXScrollView scrollView, int x, int y, int oldx, int oldy) {
                }

                public void onScrollToBottom(WXScrollView scrollView, int x, int y) {
                }

                public void onScrollStopped(WXScrollView scrollView, int x, int y) {
                    List<OnWXScrollListener> listeners = getInstance().getWXScrollListeners();
                    if(listeners != null && listeners.size() > 0) {
                        Iterator var5 = listeners.iterator();

                        while(var5.hasNext()) {
                            OnWXScrollListener listener = (OnWXScrollListener)var5.next();
                            if(listener != null) {
                                listener.onScrollStateChanged(scrollView, x, y, 0);
                            }
                        }
                    }

                }

                public void onScroll(WXScrollView scrollView, int x, int y) {
                    List<OnWXScrollListener> listeners = getInstance().getWXScrollListeners();
                    if(listeners != null && listeners.size() > 0) {
                        Iterator var5 = listeners.iterator();

                        while(var5.hasNext()) {
                            OnWXScrollListener listener = (OnWXScrollListener)var5.next();
                            if(listener != null) {
                                if(listener instanceof ICheckBindingScroller) {
                                    if(((ICheckBindingScroller)listener).isNeedScroller(getRef(), (Object)null)) {
                                        listener.onScrolled(scrollView, x, y);
                                    }
                                } else {
                                    listener.onScrolled(scrollView, x, y);
                                }
                            }
                        }
                    }

                }
            });
            host = scrollerView;
        }

        ((ViewGroup)host).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(16)
            public void onGlobalLayout() {
            procAppear(0, 0, 0, 0);
                View view;
                if((view = getHostView()) != null) {
                    if(Build.VERSION.SDK_INT >= 16) {
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                }
            }
        });
        return (ViewGroup)host;
    }

    public int getScrollY() {
        return this.getInnerView() == null?0:this.getInnerView().getScrollY();
    }

    public int getScrollX() {
        return this.getInnerView() == null?0:this.getInnerView().getScrollX();
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public Map<String, Map<String, WXComponent>> getStickMap() {
        return this.mStickyMap;
    }

    protected boolean setProperty(String key, Object param) {
      return super.setProperty(key,param);
    }

    @WXComponentProp(
            name = "showScrollbar"
    )
    public void setShowScrollbar(boolean show) {
        if(this.getInnerView() != null) {
            if(this.mOrientation == 1) {
                this.getInnerView().setVerticalScrollBarEnabled(show);
            } else {
                this.getInnerView().setHorizontalScrollBarEnabled(show);
            }

        }
    }

    @WXComponentProp(
            name = "scrollable"
    )
    public void setScrollable(boolean scrollable) {
        this.isScrollable = scrollable;
        View hostView = this.getInnerView();
        if(hostView instanceof WXHorizontalScrollView) {
            ((WXHorizontalScrollView)hostView).setScrollable(scrollable);
        } else if(hostView instanceof WXScrollView) {
            ((WXScrollView)hostView).setScrollable(scrollable);
        }

    }

    @WXComponentProp(
            name = "offsetAccuracy"
    )
    public void setOffsetAccuracy(int accuracy) {
        float realPx = WXViewUtils.getRealPxByWidth((float)accuracy, this.getInstance().getInstanceViewPortWidth());
        this.mOffsetAccuracy = (int)realPx;
    }

    public boolean isScrollable() {
        return this.isScrollable;
    }

    public void bindStickStyle(WXComponent component) {
        this.stickyHelper.bindStickStyle(component, this.mStickyMap);
    }

    public void unbindStickStyle(WXComponent component) {
        this.stickyHelper.unbindStickStyle(component, this.mStickyMap);
    }

    public void bindAppearEvent(WXComponent component) {
        this.setWatch(0, component, true);
    }

    private void setWatch(int event, WXComponent component, boolean isWatch) {
        AppearanceHelper item = (AppearanceHelper)this.mAppearanceComponents.get(component.getRef());
        if(item == null) {
            item = new AppearanceHelper(component);
            this.mAppearanceComponents.put(component.getRef(), item);
        }

        item.setWatchEvent(event, isWatch);
        this.procAppear(0, 0, 0, 0);
    }

    public void bindDisappearEvent(WXComponent component) {
        this.setWatch(1, component, true);
    }

    public void unbindAppearEvent(WXComponent component) {
        this.setWatch(0, component, false);
    }

    public void unbindDisappearEvent(WXComponent component) {
        this.setWatch(1, component, false);
    }

    public void scrollTo(WXComponent component, Map<String, Object> options) {
        float offsetFloat = 0.0F;
        boolean smooth = true;
        if(options != null) {
            String offset = options.get("offset") == null?"0":options.get("offset").toString();
            smooth = WXUtils.getBoolean(options.get("animated"), Boolean.valueOf(true)).booleanValue();
            if(offset != null) {
                try {
                    offsetFloat = WXViewUtils.getRealPxByWidth(Float.parseFloat(offset), this.getInstance().getInstanceViewPortWidth());
                } catch (Exception var7) {
                    WXLogUtils.e("Float parseFloat error :" + var7.getMessage());
                }
            }
        }

        int viewYInScroller = component.getAbsoluteY() - this.getAbsoluteY();
        int viewXInScroller = component.getAbsoluteX() - this.getAbsoluteX();
        this.scrollBy(viewXInScroller - this.getScrollX() + (int)offsetFloat, viewYInScroller - this.getScrollY() + (int)offsetFloat, smooth);
    }

    public void scrollBy(int x, int y) {
        this.scrollBy(x, y, false);
    }

    public void scrollBy(final int x, final int y, final boolean smooth) {
        if(this.getInnerView() != null) {
            this.getInnerView().postDelayed(WXThread.secure(new Runnable() {
                public void run() {
                    if(mOrientation == 1) {
                        if(smooth) {
                            ((WXScrollView)getInnerView()).smoothScrollBy(0, y);
                        } else {
                            ((WXScrollView)getInnerView()).scrollBy(0, y);
                        }
                    } else if(smooth) {
                        ((WXHorizontalScrollView)getInnerView()).smoothScrollBy(x, 0);
                    } else {
                        ((WXHorizontalScrollView)getInnerView()).scrollBy(x, 0);
                    }

                    getInnerView().invalidate();
                }
            }), 16L);
        }
    }

    public void onScrollChanged(WXScrollView scrollView, int x, int y, int oldx, int oldy) {
        this.procAppear(x, y, oldx, oldy);
    }

    private void procAppear(int x, int y, int oldx, int oldy) {
        int moveY = y - oldy;
        int moveX = x - oldx;
        String direction = moveY > 0?"up":(moveY < 0?"down":null);
        if(this.mOrientation == 0 && moveX != 0) {
            direction = moveX > 0?"right":"left";
        }

        Iterator var8 = this.mAppearanceComponents.entrySet().iterator();

        while(var8.hasNext()) {
            Map.Entry<String, AppearanceHelper> item = (Map.Entry)var8.next();
            AppearanceHelper helper = (AppearanceHelper)item.getValue();
            if(helper.isWatch()) {
                boolean visible = helper.isViewVisible(false);
                int result = helper.setAppearStatus(visible);
                if(result != 0) {
                    helper.getAwareChild().notifyAppearStateChange(result == 1?"appear":"disappear", direction);
                }
            }
        }

    }

    public void onScrollToBottom(WXScrollView scrollView, int x, int y) {
    }

    public void onScrollStopped(WXScrollView scrollView, int x, int y) {
    }

    public void onScroll(WXScrollView scrollView, int x, int y) {
        this.onLoadMore(scrollView, x, y);
    }

    protected void onLoadMore(WXScrollView scrollView, int x, int y) {
        try {
            String offset = this.getDomObject().getAttrs().getLoadMoreOffset();
            if(TextUtils.isEmpty(offset)) {
                return;
            }

            int offsetInt = (int)WXViewUtils.getRealPxByWidth(Float.parseFloat(offset), this.getInstance().getInstanceViewPortWidth());
            int contentH = scrollView.getChildAt(0).getHeight();
            int scrollerH = scrollView.getHeight();
            int offScreenY = contentH - y - scrollerH;
            if(offScreenY < offsetInt) {
                if(WXEnvironment.isApkDebugable()) {
                    WXLogUtils.d("[WXScroller-onScroll] offScreenY :" + offScreenY);
                }

                if(this.mContentHeight != contentH || this.mForceLoadmoreNextTime) {
                    this.fireEvent("loadmore");
                    this.mContentHeight = contentH;
                    this.mForceLoadmoreNextTime = false;
                }
            }
        } catch (Exception var9) {
            WXLogUtils.d("[WXScroller-onScroll] ", var9);
        }

    }

    @JSMethod
    public void resetLoadmore() {
        this.mForceLoadmoreNextTime = true;
    }

    public ScrollStartEndHelper getScrollStartEndHelper() {
        if(this.mScrollStartEndHelper == null) {
            this.mScrollStartEndHelper = new ScrollStartEndHelper(this);
        }

        return this.mScrollStartEndHelper;
    }

    public static class Creator implements ComponentCreator {
        public Creator() {
        }

        public WXComponent createInstance(WXSDKInstance instance, WXDomObject node, WXVContainer parent) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            return new HookWxScroller(instance, node, parent);
        }
    }


    private boolean mPagingEnable;
    private BMBaseRefresh mBMRefresh;

    @WXComponentProp(name = HookConstants.NAME.PAGINGENABLED)
    public void setPagingEnable(boolean pagingEnable) {
        this.mPagingEnable = pagingEnable;
        ViewGroup innerView = getInnerView();
        if (innerView instanceof WXHorizontalScrollView) {
            ((HookWXHorizontalScrollView) innerView).setPageEnable(this.mPagingEnable);
        }
    }

    @WXComponentProp(name = HookConstants.NAME.SHOW_REFRESH)
    public void setBMRefresh(String showRefresh) {
        boolean customerRefresh = WXUtils.getBoolean(showRefresh, false);
        if (customerRefresh && mBMRefresh == null) {
            mBMRefresh = new BMLoadingRefresh(getContext(), this);
            ((HookBounceScrollerView) this.getHostView()).setOnRefreshListener(mBMRefresh);
            Runnable runnable = WXThread.secure(new Runnable() {
                public void run() {
                    ((HookBounceScrollerView) getHostView()).setCustomHeaderView(mBMRefresh);
                }
            });
            this.handler.postDelayed(runnable, 100L);
        }
    }

    @JSMethod
    public void refreshEnd() {
        if (mBMRefresh != null && mBMRefresh.mCurrentState == BMBaseRefresh.STATE_REFRESHING) {
            mBMRefresh.onRefreshComplete();
        }
    }
}
