package com.kun.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.kun.base.BaseApplication;
import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;
import com.kun.webview.IWebViewToMainProcessAidlInterface;
import com.kun.webview.mainprocess.MainProcessCommandService;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class WebViewProcessCommandDispatcher implements ServiceConnection {
    private IWebViewToMainProcessAidlInterface mViewToMainProcessAidlInterface;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        mViewToMainProcessAidlInterface = IWebViewToMainProcessAidlInterface.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        mViewToMainProcessAidlInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        mViewToMainProcessAidlInterface = null;
        initAidlConnection();
    }


    static class Holder {
        private static WebViewProcessCommandDispatcher sCommandDispatcher = new WebViewProcessCommandDispatcher();
    }

    public static WebViewProcessCommandDispatcher getInstance() {
        return Holder.sCommandDispatcher;
    }

    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    public void executeCommand(String commandName, String jsonParas, BaseWebView webView) {
        if (mViewToMainProcessAidlInterface != null) {
            try {
                mViewToMainProcessAidlInterface.handleWebCommand(commandName, jsonParas, new IMainToWebViewProcessCallBackAidlInterface.Stub() {
                    @Override
                    public void onResult(String callbackname, String response) throws RemoteException {
                        webView.handleHandleCallBack(callbackname, response);

                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
