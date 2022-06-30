package com.cve20xx;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class HookDemo implements IXposedHookLoadPackage {
    private HashMap<String, Integer> cnt = new HashMap<>();

    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        final String pkg = lpparam.packageName;
        cnt.put(pkg, 0);
        XposedBridge.log("LoadPackage : "+ pkg);
        if (pkg.equals("com.cve202x")) {
            Class clazz = lpparam.classLoader.loadClass("com.cve202x.CVE202X");
            XposedHelpers.findAndHookMethod(clazz, "onClick", View.class, new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Button v = (Button) param.args[0];
                    v.setText("Changeed Button before Hook onClick");
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //param.setResult("你已被劫持");
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod(clazz, "clickMsg", new XC_MethodHook() {
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    cnt.put(pkg, cnt.get(pkg) + 1);
                    super.beforeHookedMethod(param);
                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult("你已被劫持 " + cnt.get(pkg) + "次");
                }
            });
        }

    }
}
