package com.kun.webview.command;

import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;

import java.util.Map;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public interface Command {
    String name();

    void execute(Map<String, ?> params, IMainToWebViewProcessCallBackAidlInterface callback);
}
