package com.kun.webview.webviewprocess.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kun.webview.WebViewCallback;

/**
 * Author: liukun on 2020/8/11.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class MyWebViewClient extends WebViewClient {
    public static final String TAG = "MyWebViewClient";

    WebViewCallback mWebViewCallback;

    public MyWebViewClient(WebViewCallback webViewCallback) {
        mWebViewCallback = webViewCallback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mWebViewCallback != null) {
            mWebViewCallback.onPageStarted(url);
        } else {
            Log.e(TAG, "mWebViewCallback is not null !!!!");
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mWebViewCallback != null) {
            mWebViewCallback.onPageFinished(url);
        } else {
            Log.e(TAG, "mWebViewCallback is not null !!!!");
        }
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (mWebViewCallback != null) {
            mWebViewCallback.onPageError(error);
        } else {
            Log.e(TAG, "mWebViewCallback is not null !!!!");
        }
    }
}
