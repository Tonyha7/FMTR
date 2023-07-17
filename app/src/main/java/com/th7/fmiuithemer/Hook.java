package com.th7.fmiuithemer;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage, IXposedHookZygoteInit {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.android.thememanager")) {
            ThemeManager.hook(loadPackageParam.classLoader);
        }
    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {
        System.hook(startupParam);
    }
}
