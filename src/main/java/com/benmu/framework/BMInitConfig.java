package com.benmu.framework;

import java.util.HashMap;

/**
 * Created by Carry on 2017/8/23.
 */

public class BMInitConfig {

    private HashMap<String, String> mEnvs;
    private boolean mActice;

    private BMInitConfig() {
    }

    public HashMap<String, String> getmEnvs() {
        return mEnvs;
    }

    public void setmEnvs(HashMap<String, String> mEnvs) {
        this.mEnvs = mEnvs;
    }

    public boolean ismActice() {
        return mActice;
    }

    public void setmActice(boolean mActice) {
        this.mActice = mActice;
    }

    public static class Builder {
        HashMap<String, String> mCustomerEnv;
        private boolean mActiveInterceptor;

        public Builder setCustomerEnv(HashMap<String, String> mCustomerEnv) {
            this.mCustomerEnv = mCustomerEnv;
            return this;
        }

        public Builder isActiceInterceptor(boolean active) {
            this.mActiveInterceptor = active;
            return this;
        }

        public BMInitConfig build() {
            BMInitConfig initConfig = new BMInitConfig();
            initConfig.mEnvs = this.mCustomerEnv;
            initConfig.mActice = this.mActiveInterceptor;
            return initConfig;
        }
    }
}
