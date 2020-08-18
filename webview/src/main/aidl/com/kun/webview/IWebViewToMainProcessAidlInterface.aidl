// IWebViewToMainProcessAidlInterface.aidl
package com.kun.webview;
import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;
// Declare any non-default types here with import statements

interface IWebViewToMainProcessAidlInterface {
   void handleWebCommand(String commandName,String jsonParams,in IMainToWebViewProcessCallBackAidlInterface callback);
}
