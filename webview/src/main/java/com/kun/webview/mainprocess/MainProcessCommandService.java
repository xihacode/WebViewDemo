package com.kun.webview.mainprocess;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class MainProcessCommandService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return MainProcessCommandManger.getInstance();
    }
}
