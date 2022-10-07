//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.NotificationCompat.Builder;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;

public class TSLocalNotification {
    public TSLocalNotification() {
    }

    public static Builder build(Context var0) {
        Builder var1;
        if (VERSION.SDK_INT >= 26) {
            label40: {
                label44: {
                    Builder var10000;
                    boolean var10001;
                    try {
                        var10000 = new Builder;
                    } catch (NoSuchMethodError var6) {
                        var10001 = false;
                        break label44;
                    }

                    var1 = var10000;

                    StringBuilder var10002;
                    Context var8;
                    try {
                        var8 = var0;
                        var10002 = new StringBuilder;
                    } catch (NoSuchMethodError var5) {
                        var10001 = false;
                        break label44;
                    }

                    StringBuilder var10003 = var10002;

                    try {
                        var10003.<init>();
                        var10000.<init>(var8, var10002.append(var0.getPackageName()).append(Application.B("旸肴퍵ꁱ⢡뎅㰭ﴃᯈ蜆抠ᕬ\uaac3ﰦ㻆ꕈ\uf8d8")).toString());
                        break label40;
                    } catch (NoSuchMethodError var4) {
                        var10001 = false;
                    }
                }

                var1 = new Builder.<init>(var0);
            }
        } else {
            var1 = new Builder.<init>(var0);
        }

        Intent var2;
        if ((var2 = var0.getPackageManager().getLaunchIntentForPackage(var0.getPackageName())) != null) {
            var2.setAction(Application.B("旍肉퍝ꁬ⢭뎍㰽\ufd44ᯎ蜆抙ᕨ\uaac3ﰳ㺏ꕌ\uf8c9쁴\ued9a췩䲫ﳔ뤠甛\udb73\ueed8"));
            var2.putExtra(Application.B("旸肴퍵ꁱ⢡뎅㰭ﴃᯈ蜆抠ᕬ\uaac3ﰦ㻆ꕈ\uf8d8"), true);
            var2.addCategory(Application.B("旍肉퍝ꁬ⢭뎍㰽\ufd44ᯎ蜆抙ᕨ\uaac3ﰳ㺏ꕎ\uf8cb쁴\ued96췡䲪ﲈ뤔畴\udb76\ueed7ㅤቍ\uf162姀嚀˱"));
            var2.setPackage((String)null);
            var2.setFlags(272629760);
            int var3 = Util.getPendingIntentFlags(134217728);
            var1.setContentIntent(PendingIntent.getActivity(var0, 0, var2, var3));
        } else {
            TSLog.logger.warn(Application.B("旪肆퍐ꁲ⢧뎀㱹ﴞᯈ蝈抋ᕤ\uaac3ﰣ㺁ꕁ\uf8cb쁵\ued9d췥䲭ﲳ뤃甮\udb5f\ueef8ㅅሣ\uf147姧嚷ʃ属ᜥ\udf09䫴\uf5af\ue2b8\u31e5잏⑆") + var0.getPackageName());
        }

        int var7;
        if ((var7 = getSmallIcon(var0)) > 0) {
            var1.setSmallIcon(var7);
        }

        var1.setOnlyAlertOnce(true);
        return var1;
    }

    public static int getSmallIcon(Context var0) {
        int var1 = 0;
        String var2;
        if (TSConfig.isLoaded() && !(var2 = TSConfig.getInstance(var0).getNotification().getSmallIcon()).isEmpty()) {
            var1 = var0.getResources().getIdentifier(var2, (String)null, var0.getPackageName());
        }

        if (var1 <= 0) {
            var1 = var0.getApplicationInfo().icon;
        }

        return var1;
    }

    public static void notify(Context var0, Notification var1, int var2) {
        NotificationManagerCompat.from(var0).notify(var2, var1);
    }
}
