//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.scheduler;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import com.transistorsoft.locationmanager.service.AbstractService;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;

public class ScheduleService extends AbstractService {
    public ScheduleService() {
    }

    static PendingIntent a(Context var0, Intent var1) {
        if (VERSION.SDK_INT >= 26) {
            Context var10000 = var0;
            int var2 = Util.getPendingIntentFlags(134217728);
            return PendingIntent.getForegroundService(var10000, 0, var1, var2);
        } else {
            return PendingIntent.getService(var0, 0, var1, 134217728);
        }
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        if (!this.start(var1, false)) {
            return 2;
        } else {
            if (var1.hasExtra(Application.B("ݣ⌼⓳룘괵ﵲ啄쭻\ue197䐎쯡耢\ue4b9\uf07f㻝쀫"))) {
                Intent var10001 = var1;
                boolean var4 = var1.getBooleanExtra(Application.B("ݣ⌼⓳룘괵ﵲ啄쭻\ue197䐎쯡耢\ue4b9\uf07f㻝쀫"), false);
                var2 = var10001.getIntExtra(Application.B("ݤ⌭⓺룞괺ﵮ商쭹\ue185䐄쯫耦"), 1);
                ScheduleEvent.onScheduleEvent(this.getApplicationContext(), var4, var2);
            }

            this.finish();
            return 3;
        }
    }
}
