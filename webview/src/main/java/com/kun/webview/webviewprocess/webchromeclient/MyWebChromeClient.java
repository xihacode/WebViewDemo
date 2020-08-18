package com.kun.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.kun.webview.WebViewCallback;

/**
 * Author: liukun on 2020/8/11.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class MyWebChromeClient extends WebChromeClient {

    public static final String TAG = "MyWebChromeClient";

    WebViewCallback mWebViewCallback;

    public MyWebChromeClient(WebViewCallback webViewCallback) {
        mWebViewCallback = webViewCallback;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (mWebViewCallback != null) {
            mWebViewCallback.updateTitle(title);
        } else {
            Log.e(TAG, "mWebViewCallback is not null !!!!");
        }
    }

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.e(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
