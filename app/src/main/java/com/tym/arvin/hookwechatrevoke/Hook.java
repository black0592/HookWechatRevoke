package com.tym.arvin.hookwechatrevoke;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.setObjectField;


/**
 * Created by arvin on 2016/3/1.
 */

public class Hook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        // only care about Wechat package
        if (!loadPackageParam.packageName.equals(Wechat.PACKAGENAME))
            return;

        // hook the setContent() function to avoid change the msg revoked content
        findAndHookMethod(Wechat.REVOKE_CONTENT_CLASS, loadPackageParam.classLoader, Wechat.REVOKE_CONTENT_FUNCTION, Wechat.REVOKE_CONTENT_ARG_CLASS, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                setObjectField(param.args[0], Wechat.REVOKE_CONTENT_ARG_MSG, null);
            }
        });

        // hook the setType() function
        findAndHookMethod(Wechat.REVOKE_TYPE_CLASS, loadPackageParam.classLoader, Wechat.REVOKE_TYPE_FUNCTION, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                // set TYPE to 1 to avoid msg hidden from list
                param.args[0] = 1;
            }
        });
    }
}
