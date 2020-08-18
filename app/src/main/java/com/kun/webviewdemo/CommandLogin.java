package com.kun.webviewdemo;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.kun.base.BaseApplication;
import com.kun.base.ServiceLoaderUtil;
import com.kun.common.autoserver.IUserCenterService;
import com.kun.common.eventbus.LoginEvent;
import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;
import com.kun.webview.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
@AutoService(Command.class)
public class CommandLogin implements Command {
    IUserCenterService mIUserCenterService = ServiceLoaderUtil.getService(IUserCenterService.class);
    private IMainToWebViewProcessCallBackAidlInterface callback;
    private String callbackNameFormNativeJs;
    private String TAG = "CommandLogin";

    @Override
    public String name() {
        return "login";
    }

    //此处必须为public
    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void execute(Map<String, ?> params, IMainToWebViewProcessCallBackAidlInterface callback) {
        Log.d(TAG, "execute1: " + mIUserCenterService);
        if (mIUserCenterService != null && !mIUserCenterService.isLogin()) {
            Log.d(TAG, "execute: " + mIUserCenterService);
            mIUserCenterService.login();
            this.callback = callback;
            this.callbackNameFormNativeJs = params.get("callbackname").toString();
        }
    }

    @Subscribe
    public void MessageEvent(LoginEvent event) {
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        if (this.callback != null) {
            try {
                this.callback.onResult(callbackNameFormNativeJs, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
