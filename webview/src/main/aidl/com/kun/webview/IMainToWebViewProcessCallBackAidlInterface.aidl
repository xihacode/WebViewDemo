// IMainToWebViewProcessCallBackAidlInterface.aidl
package com.kun.webview;

// Declare any non-default types here with import statements

interface IMainToWebViewProcessCallBackAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onResult(String callbackname,String response);
}
