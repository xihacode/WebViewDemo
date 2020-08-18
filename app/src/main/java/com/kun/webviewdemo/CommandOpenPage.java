package com.kun.webviewdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.kun.base.BaseApplication;
import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;
import com.kun.webview.command.Command;

import java.util.Map;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
@AutoService(Command.class)
public class CommandOpenPage implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map<String, ?> params, IMainToWebViewProcessCallBackAidlInterface callback) {
        String targetClass = String.valueOf(params.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }
}
