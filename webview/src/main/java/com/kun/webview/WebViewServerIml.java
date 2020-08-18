package com.kun.webview;

import android.content.Context;
import android.content.Intent;

import com.google.auto.service.AutoService;
import com.kun.common.autoserver.IWebViewServer;
import com.kun.webview.WebViewActivity;
import com.kun.webview.WebViewFragment;
import com.kun.webview.utils.Constants;

/**
 * Author: liukun on 2020/8/9.
 * Mail  : 3266817262@qq.com
 * Description:
 */
@AutoService(IWebViewServer.class)
public class WebViewServerIml implements IWebViewServer {
    @Override
    public void startWebView(Context context, String title, String url, boolean isShowActionBar) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.TITLE, title);
        intent.putExtra(Constants.URL, url);
        intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
        context.startActivity(intent);
    }

    @Override
    public void startWebViewByFragment(String url, boolean isNativeRefresh) {
        WebViewFragment.newInstance(url, isNativeRefresh);
    }

    @Override
    public void startDemoHtml(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.TITLE, "本地Demo测试页");
        intent.putExtra(Constants.URL, Constants.ANDROID_ASSET_URI + "demo.html");
        context.startActivity(intent);
    }
}
