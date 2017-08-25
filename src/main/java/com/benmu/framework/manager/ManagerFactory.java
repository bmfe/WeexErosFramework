package com.benmu.framework.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carry on 2017/8/7. manager instance factory
 */

public class ManagerFactory {

    private static Map<String, Manager> mInstances = new HashMap();

    public static <T extends Manager> T getManagerService(Class<T> flag) {
        if (flag == null) throw new IllegalArgumentException("error flag");
        if (mInstances.get(flag.getName()) == null) {
            try {
                mInstances.put(flag.getName(), flag.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (T) mInstances.get(flag.getName());
    }

}
