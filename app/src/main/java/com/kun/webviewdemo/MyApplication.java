package com.kun.webviewdemo;

import com.kingja.loadsir.core.LoadSir;
import com.kun.base.BaseApplication;
import com.kun.base.callback.CustomCallback;
import com.kun.base.callback.EmptyCallback;
import com.kun.base.callback.ErrorCallback;
import com.kun.base.callback.LoadingCallback;
import com.kun.base.callback.TimeoutCallback;

/**
 * Author: liukun on 2020/8/11.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }

}
