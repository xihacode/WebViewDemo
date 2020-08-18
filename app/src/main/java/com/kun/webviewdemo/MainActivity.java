package com.kun.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;


import com.kun.base.ServiceLoaderUtil;
import com.kun.common.autoserver.IWebViewServer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonPanel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IWebViewServer webViewServer = ServiceLoaderUtil.getService(IWebViewServer.class);
                if (webViewServer != null) {
//                    webViewServer.startWebView(MainActivity.this, "百度",
////                            "https://www.baidu.com", true);
                    webViewServer.startDemoHtml(MainActivity.this);
                }
            }
        });
    }
}