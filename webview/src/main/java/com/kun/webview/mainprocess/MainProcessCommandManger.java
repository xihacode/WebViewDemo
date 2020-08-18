package com.kun.webview.mainprocess;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.kun.base.BaseApplication;
import com.kun.base.ServiceLoaderUtil;
import com.kun.webview.IMainToWebViewProcessCallBackAidlInterface;
import com.kun.webview.IWebViewToMainProcessAidlInterface;
import com.kun.webview.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Author: liukun on 2020/8/17.
 * Mail  : 3266817262@qq.com
 * Description:
 */
public class MainProcessCommandManger extends IWebViewToMainProcessAidlInterface.Stub {

    private HashMap<String, Command> mCommands = new HashMap<>();

    private static class Holder {
        private static MainProcessCommandManger sMainProcessCommandManger = new MainProcessCommandManger();
    }

    public static MainProcessCommandManger getInstance() {
        return Holder.sMainProcessCommandManger;
    }

    private MainProcessCommandManger() {
        Log.d(":sss", "MainProcessCommandMangssssser: ");
        ServiceLoader<Command> service = ServiceLoader.load(Command.class);
        Log.d(":sss", "ServiceLoader: "+service);
        for (Command command : service) {
            Log.d(":sss", "MainProcessCommandManger: "+command);
            if (!mCommands.containsKey(command.name())) {
                mCommands.put(command.name(), command);
            }
        }
    }

    private void execute(String commandName, Map<String, ?> params, IMainToWebViewProcessCallBackAidlInterface callback) {
        Log.d("CommandLogin", "execute1: " + params);
        mCommands.get(commandName).execute(params, callback);
    }

    @Override
    public void handleWebCommand(String commandName, String jsonParams, IMainToWebViewProcessCallBackAidlInterface callback) throws RemoteException {
        MainProcessCommandManger.getInstance().execute(commandName, new Gson().fromJson(jsonParams, Map.class), callback);
    }
}
