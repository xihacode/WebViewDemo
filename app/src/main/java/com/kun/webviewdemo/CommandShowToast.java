package com.kun.webviewdemo;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.kun.base.BaseApplication;
import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;
import com.kun.webview.command.Command;

import java.util.Map;
import java.util.Objects;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
@AutoService(Command.class)
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map<String, ?> params, IMainToWebViewProcessCallBackAidlInterface callback) {
        Handler handler = new Handler(Objects.requireNonNull(Looper.getMainLooper()));
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.sApplication, String.valueOf(params.get("message")), Toast.LENGTH_LONG).show();
            }
        });
    }
}
