package com.benmu.framework;

import com.alibaba.weex.plugin.loader.WeexPluginContainer;
import com.benmu.framework.constant.Constant;
import com.benmu.framework.manager.Manager;
import com.benmu.framework.manager.ManagerFactory;
import com.benmu.framework.manager.impl.CustomerEnvOptionManager;

import java.util.HashMap;

/**
 * Created by liuyuanxiao on 17/12/5.
 */

public class PlugManager {
    public static void initPlug() {
        pluginInit("com.plugamap.manager.GeoManager");
        pluginInit("com.benmu.erosplugingt.manager.PushManager");
    }

    private static void pluginInit(String clazzName) {
        Manager geoManager = ManagerFactory.getManagerService(clazzName);
        if (geoManager != null) {
            geoManager.init();
//            registerCompnentsAndModules(geoManager.getComponentsAndModules());

        }
    }

    /**
     * 注册 compent 和 Modules
     *
     * @param compentAndModules compent 和 Modules 集合
     */
    private static void registerCompnentsAndModules(HashMap<String, HashMap<String, String>> compentAndModules) {
        if (compentAndModules == null) return;
        HashMap<String, String> compents = compentAndModules.get(Constant.CUSTOMER_COMPONETS);
        if (compents != null) {
            CustomerEnvOptionManager.registerComponents(compents);
        }
        HashMap<String, String> modules = compentAndModules.get(Constant.CUSTOMER_MODULES);
        if (modules != null) {
            CustomerEnvOptionManager.registerModules(compents);
        }
    }
}
