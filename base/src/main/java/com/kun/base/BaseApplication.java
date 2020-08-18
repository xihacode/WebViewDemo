package com.kun.base;

import android.app.Application;

/**
 * Author: liukun on 2020/8/11.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class BaseApplication extends Application {
    public static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
