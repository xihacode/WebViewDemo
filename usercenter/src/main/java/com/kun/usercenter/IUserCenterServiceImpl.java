package com.kun.usercenter;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.kun.base.BaseApplication;
import com.kun.common.autoserver.IUserCenterService;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
@AutoService({IUserCenterService.class})
public class IUserCenterServiceImpl implements IUserCenterService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
