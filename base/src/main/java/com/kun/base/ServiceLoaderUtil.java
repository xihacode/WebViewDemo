package com.kun.base;

import java.util.ServiceLoader;

/**
 * Author: liukun on 2020/8/9.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public final class ServiceLoaderUtil {
    private ServiceLoaderUtil() {
    }

    public static <S> S getService(Class<S> sClass) {
        try {
            return ServiceLoader.load(sClass).iterator().next();
        } catch (Exception e) {
            return null;
        }
    }
}
