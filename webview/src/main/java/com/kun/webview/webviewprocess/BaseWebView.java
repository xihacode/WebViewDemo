package com.kun.webview.webviewprocess;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kun.webview.WebViewCallback;
import com.kun.webview.bean.JsParam;
import com.kun.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.kun.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.kun.webview.webviewprocess.webviewclient.MyWebViewClient;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author: liukun on 2020/8/14.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class BaseWebView extends WebView {
    private static final String TAG = "BaseWebView";

    public BaseWebView(@NonNull Context context) {
        this(context, null);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getViewDefaultSettings().setSettings(this);
        addJavascriptInterface(this, "customWebView");
    }

    public void registerWebViewCallBack(WebViewCallback webViewCallback) {
        setWebViewClient(new MyWebViewClient(webViewCallback));
        setWebChromeClient(new MyWebChromeClient(webViewCallback));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.d(TAG, "takeNativeAction: " + jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            JsParam jsParamObj = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObj != null) {
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObj.name, new Gson().toJson(jsParamObj.param), this);

            }
        }
    }

    public void handleHandleCallBack(String callbackName, String response) {
        if (!TextUtils.isEmpty(callbackName) && !TextUtils.isEmpty(response)) {
            post(new Runnable() {
                @Override
                public void run() {
                    String jscode = "javascript:demojs.callback('" + callbackName + "'," + response + ")";
                    evaluateJavascript(jscode, null);
                }
            });
        }
    }

}
