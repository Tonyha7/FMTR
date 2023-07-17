package com.th7.fmiuithemer;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import java.lang.System;
public class ThemeManager {
    public static void hook(ClassLoader classLoader) throws Throwable {
        long left = System.currentTimeMillis()*2;

        Class Resource = classLoader.loadClass("com.android.thememanager.basemodule.resource.model.Resource");
        Class OnlineResourceDetail = classLoader.loadClass("com.android.thememanager.detail.theme.model.OnlineResourceDetail");
        Class ResourceOnlineProperties = classLoader.loadClass("com.android.thememanager.basemodule.resource.model.ResourceOnlineProperties");
        Class AdInfo = classLoader.loadClass("com.android.thememanager.basemodule.ad.model.AdInfo");
        Class AdInfoResponse = classLoader.loadClass("com.android.thememanager.basemodule.ad.model.AdInfoResponse");

        XposedHelpers.findAndHookMethod(Resource, "isAuthorizedResource", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });

        XposedHelpers.findAndHookMethod(Resource, "isProductBought", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });

        XposedHelpers.findAndHookMethod(Resource, "setProductBought", Boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = true;
            }
        });

        XposedHelpers.findAndHookMethod(Resource, "getTrialTime", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(left);
            }
        });

        XposedHelpers.findAndHookMethod(Resource, "setTrialTime", long.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = left;
            }
        });

        XposedHelpers.findAndHookMethod(Resource, "getProductPrice", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(0);
            }
        });

        XposedHelpers.findAndHookMethod(Resource, "setProductPrice", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = 0;
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "getTrialTime", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(left);
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "setTrialTime", long.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = left;
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "getProductPrice", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(0);
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "setProductPrice", int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = 0;
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "isProductBought", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "setProductBought", Boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[0] = true;
            }
        });

        XposedHelpers.findAndHookMethod(ResourceOnlineProperties, "updateFrom", ResourceOnlineProperties, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedHelpers.setObjectField(param.thisObject, "productPrice", 0);
                XposedHelpers.setObjectField(param.thisObject, "bought", true);
                XposedHelpers.setObjectField(param.thisObject, "trialTime", left);
            }
        });

        XposedHelpers.findAndHookMethod(OnlineResourceDetail, "toResource", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedHelpers.setObjectField(param.thisObject, "productPrice", 0);
                XposedHelpers.setObjectField(param.thisObject, "bought", true);
            }
        });

        XposedHelpers.findAndHookMethod(AdInfoResponse, "isAdValid", AdInfo, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(false);
            }
        });
    }
}