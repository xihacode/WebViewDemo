package com.kun.webview;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebView;

/**
 * Author: liukun on 2020/8/11.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public interface WebViewCallback {

    public void onPageStarted(String url);

    public void onPageFinished(String url);

    public void onPageError(WebResourceError error);

    public void updateTitle(String title);


}
