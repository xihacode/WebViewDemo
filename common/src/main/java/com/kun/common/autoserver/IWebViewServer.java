package com.kun.common.autoserver;

import android.content.Context;

/**
 * Author: liukun on 2020/8/9.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public interface IWebViewServer {
    public void startWebView(Context context, String title, String url, boolean isShowActionBar);

    public void startWebViewByFragment(String url, boolean isNativeRefresh);

    void startDemoHtml(Context context);
}
