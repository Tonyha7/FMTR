package com.th7.fmiuithemer;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import miui.drm.DrmManager;
import miui.drm.ThemeReceiver;

public class System {
    public static void hook(IXposedHookZygoteInit.StartupParam startupParam) throws Throwable {
        XposedBridge.hookAllMethods(ThemeReceiver.class, "onReceive", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedHelpers.setObjectField(param.thisObject, "mIsValidating", true);
            }
        });

        XposedBridge.hookAllMethods(DrmManager.class, "isPermanentRights", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });

        XposedBridge.hookAllMethods(DrmManager.class, "isRightsFileLegal", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(true);
            }
        });

        XposedBridge.hookAllMethods(DrmManager.class, "isSupportAd", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(false);
            }
        });

        XposedBridge.hookAllMethods(DrmManager.class, "setSupportAd", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.args[1] = false;
            }
        });

        XposedBridge.hookAllMethods(DrmManager.class, "isLegal", new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(DrmManager.DrmResult.DRM_SUCCESS);
            }
        });
    }
}
