package com.kun.webview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.kun.webview.databinding.ActivityWebViewBinding;
import com.kun.webview.utils.Constants;

public class WebViewActivity extends AppCompatActivity {

    ActivityWebViewBinding mBinding;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);

        mBinding.title.setText(getIntent().getStringExtra(Constants.TITLE));
        mBinding.actionBar.setVisibility(getIntent().getBooleanExtra(Constants.IS_SHOW_ACTION_BAR,
                true) ? View.VISIBLE : View.GONE);
        mBinding.back.setOnClickListener(v->{
            finish();
        });

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.webViewFrameLayout, WebViewFragment.newInstance(getIntent()
                .getStringExtra(Constants.URL), true)).commit();
    }

    public void updateTitle(String title) {
        mBinding.title.setText(title);
    }
}