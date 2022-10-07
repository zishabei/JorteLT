//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.scheduler;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;

public class ScheduleAlarmReceiver extends BroadcastReceiver {
    public static String a = Application.B("逨妵ᯗ페\ueb19\ufaf9ᰯ㋅汻Ꮑ奲ғ맇న麉㿴뢎");

    public ScheduleAlarmReceiver() {
    }

    public void onReceive(final Context var1, final Intent var2) {
        if (var2.hasExtra(Application.B("ׅ诛갊柞㈘⻤阶"))) {
            String var5;
            int var3 = (var5 = var2.getStringExtra(Application.B("\u05eb诶갻柤㈿⻅"))).hashCode();
            int var4 = Util.getPendingIntentFlags(536870912);
            PendingIntent var6;
            if ((var6 = PendingIntent.getBroadcast(var1, var3, var2, var4)) != null) {
                var6.cancel();
            }

            BackgroundGeolocation.getThreadPool().execute(() -> {
                ScheduleEvent.onOneShot(var1, var5, () -> {
                });
            });
        } else {
            BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                public void run() {
                    <undefinedtype> var10000 = this;
                    <undefinedtype> var10001 = this;
                    boolean var2x = var2.getBooleanExtra(Application.B("ࣜ枨鑫聥쒗䐁ࣰ\ue4d9倾䑯䤖\u1aea佨䋲ࠂ혆"), false);
                    int var1x = var2.getIntExtra(Application.B("ࣛ枹鑢聣쒘䐝ࣲ\ue4db倬䑥䤜\u1aee"), 1);
                    ScheduleEvent.onScheduleEvent(var1.getApplicationContext(), var2x, var1x);
                }
            });
        }

    }
}
