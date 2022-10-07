//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build.VERSION;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.HeadlessEvent;
import com.transistorsoft.locationmanager.event.HeartbeatEvent;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.locationmanager.util.b;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Calendar;
import org.greenrobot.eventbus.EventBus;

public class HeartbeatService extends AbstractService {
    public HeartbeatService() {
    }

    public static void stop(Context var0) {
        TSLog.logger.info(TSLog.off(Application.B("驎嫐䘁惀ꝭ沉蚚砣眡ጷ\u1b4c빰騮\ue5e9")));
        ((AlarmManager)var0.getSystemService(Application.B("驼嫈䘏惂꜠"))).cancel(getPendingIntent(var0));
    }

    public static void start(Context var0) {
        int var1;
        if ((var1 = TSConfig.getInstance(var0).getHeartbeatInterval()) <= 0) {
            stop(var0);
        } else {
            TSLog.logger.info(TSLog.on(Application.B("뻥朚銋\uf8db\uf890非ፆ\u193d\u0dcd\ueb0a鮎酕蟧\ueac4폏敋㳨") + var1 + Application.B("뻅杇")));
            Calendar var2;
            Calendar var10001 = var2 = Calendar.getInstance();
            var10001.setTimeInMillis(System.currentTimeMillis());
            var10001.add(13, var1);
            AlarmManager var3;
            AlarmManager var10000 = var3 = (AlarmManager)var0.getSystemService(Application.B("뻗朂銋\uf8db\uf889"));
            int var8 = var1;
            PendingIntent var5;
            var3.cancel(var5 = getPendingIntent(var0));
            long var6 = var2.getTimeInMillis();
            long var7 = (long)(var8 * 1000);
            var10000.setRepeating(0, var6, var7, var5);
        }
    }

    private static PendingIntent getPendingIntent(Context var0) {
        Intent var1;
        var1 = new Intent.<init>(var0, HeartbeatService.class);
        if (VERSION.SDK_INT >= 26) {
            Context var10000 = var0;
            int var2 = Util.getPendingIntentFlags(134217728);
            return PendingIntent.getForegroundService(var10000, 0, var1, var2);
        } else {
            return PendingIntent.getService(var0, 0, var1, 134217728);
        }
    }

    public void onCreate() {
        super.doCreate(HeartbeatService.class.getSimpleName());
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        if (!this.start(var1, true)) {
            return 2;
        } else {
            BackgroundGeolocation.getThreadPool().execute(new HeartbeatService.a());
            return 3;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    class a implements Runnable {
        a() {
        }

        public void run() {
            TSLog.logger.debug(Application.B("颱纙"));
            TSMediaPlayer.getInstance().debug(HeartbeatService.this.getApplicationContext(), Application.B("뾡胥\uf195툌ᣕ畧蓘ꆸ딐䗓儺뿼탠\uddb1笶ㆮ\ue835ك敀\udbb0㘟잁試珅䞖氚籔컃"));
            HeartbeatEvent var1;
            var1 = new HeartbeatEvent.<init>();
            Location var2;
            if ((var2 = TSLocationManager.getInstance(HeartbeatService.this.getApplicationContext()).getLastLocation()) != null) {
                TSLocation var10001 = new TSLocation(HeartbeatService.this.getApplicationContext(), var2, ActivityRecognitionService.getMostProbableActivity());
                var10001.setEvent(Application.B("뾽胳\uf198툑ᣂ畤蓉ꆰ딋"));
                var1.setLocation(var10001);
            }

            if (LifecycleManager.f().b()) {
                b.a(new HeadlessEvent(HeartbeatService.this.getApplicationContext(), Application.B("뾽胳\uf198툑ᣂ畤蓉ꆰ딋"), var1));
            }

            com.transistorsoft.locationmanager.data.sqlite.b var10000 = com.transistorsoft.locationmanager.data.sqlite.b.a(HeartbeatService.this.getApplicationContext());
            HttpService var4 = HttpService.getInstance(HeartbeatService.this.getApplicationContext());
            TSConfig var3 = TSConfig.getInstance(HeartbeatService.this.getApplicationContext());
            if (var10000.count() > 0 && var3.hasUrl() && var3.getAutoSync() && var4.isNetworkAvailable()) {
                var4.flush();
            }

            EventBus.getDefault().post(var1);
            HeartbeatService.this.finish(250L);
        }
    }
}
