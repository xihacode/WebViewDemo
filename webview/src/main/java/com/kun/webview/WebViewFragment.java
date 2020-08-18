package com.kun.webview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kun.base.callback.ErrorCallback;
import com.kun.base.callback.LoadingCallback;
import com.kun.webview.databinding.FragmentWebViewBinding;
import com.kun.webview.utils.Constants;
import com.kun.webview.webviewprocess.webchromeclient.MyWebChromeClient;
import com.kun.webview.webviewprocess.webviewclient.MyWebViewClient;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment implements WebViewCallback {

    String url;
    boolean isNativeRefresh = false;

    FragmentWebViewBinding mBinding;
    private LoadService loadService;
    private boolean isError;

    public WebViewFragment() {
        // Required empty public constructor
    }

    public static WebViewFragment newInstance(String url, boolean isNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(Constants.URL, url);
        args.putBoolean(Constants.IS_NATIVE_REFRESH, isNativeRefresh);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(Constants.URL);
            isNativeRefresh = getArguments().getBoolean(Constants.IS_NATIVE_REFRESH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false);

        mBinding.webView.loadUrl(url);
        mBinding.webView.registerWebViewCallBack(this);

        mBinding.refreshLayout.setEnableLoadMore(false);
        mBinding.refreshLayout.setEnableRefresh(isNativeRefresh);
        loadService = LoadSir.getDefault().register(mBinding.refreshLayout, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                loadService.showCallback(LoadingCallback.class);
                mBinding.webView.reload();
            }
        });
        mBinding.refreshLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        mBinding.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mBinding.webView.loadUrl(url);
            }
        });
        return loadService.getLoadLayout();
    }

    @Override
    public void onPageStarted(String url) {
        if (loadService != null) {
            loadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onPageFinished(String url) {
        if (isError) {
            mBinding.refreshLayout.setEnableRefresh(true);
        } else {
            if (loadService != null) {
                loadService.showSuccess();
            }
            mBinding.refreshLayout.setEnableRefresh(isNativeRefresh);
        }
        mBinding.refreshLayout.finishRefresh();
        isError = false;
    }

    @Override
    public void onPageError(WebResourceError error) {
        isError = true;
        if (loadService != null) {
            loadService.showCallback(ErrorCallback.class);
        }
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }
}