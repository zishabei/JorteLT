//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSSyncCallback;
import com.transistorsoft.locationmanager.data.LocationModel;
import com.transistorsoft.locationmanager.data.sqlite.GeofenceDAO;
import com.transistorsoft.locationmanager.data.sqlite.b;
import com.transistorsoft.locationmanager.event.GeofenceEvent;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.event.PersistEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.service.ActivityRecognitionService;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

public class a {
    public static String c = Application.B("ご嬀㤼ꗽ䐆\uedf3휬ﻌ嫋璝쇭\uf405쀮煰");
    private final Context a;
    private final int b;

    public a(Context var1, GeofencingEvent var2, int var3) {
        this.b = var3;
        this.a = var1;
        this.a(var2);
    }

    private void a(GeofencingEvent var1) {
        String var2 = Application.B("\ud953닺ꨏ㉙\uf5c6ą氅");
        String var3 = "";
        int var4;
        if ((var4 = var1.getGeofenceTransition()) != 1) {
            if (var4 != 2) {
                if (var4 == 4) {
                    var3 = Application.B("\ud972닇ꨨ㉸\uf5eaĳ氿賈\uf179뮺ἕ\ue19a윜陋碀辂ʮ楮괛䱥浨酓\uf367䪀⻖\uda82꓆સ稷쯩艘憚㸱\udfbe⡑");
                    var2 = Application.B("\ud942닣ꨁ㉛\uf5c5");
                }
            } else {
                var3 = Application.B("\ud972닇ꨨ㉸\uf5eaĳ氿賈\uf179뮺ἕ\ue19a윜陋碀辂ʮ楮괛䱥浨酓\uf367䪀⻖\uda82꓆સ稦쯫艾");
                var2 = Application.B("\ud943달ꨍ㉃");
            }
        } else {
            var3 = Application.B("\ud972닇ꨨ㉸\uf5eaĳ氿賈\uf179뮺ἕ\ue19a윜陋碀辂ʮ楮괛䱥浨酓\uf367䪀⻖\uda82꓆સ稷쯩艘憛㸠\udfaf");
            var2 = Application.B("\ud943닺ꨐ㉒\uf5db");
        }

        TSMediaPlayer.getInstance().debug(this.a, var3);
        StringBuilder var12;
        StringBuilder var10001 = var12 = new StringBuilder;
        var10001.<init>();
        var10001.append(TSLog.header(Application.B("\ud941닑ꨫ㉱\uf5ecļ氨賈\uf178뮳\u1f58\ue1be위陏碉输˦椑") + var2));
        Iterator var9 = var1.getTriggeringGeofences().iterator();

        while(var9.hasNext()) {
            var12.append(TSLog.boxRow(((Geofence)var9.next()).getRequestId()));
        }

        var12.append(Application.B("ﱜ韤輔ᝇ탙␂䤛\udc3a푆麄㨨쒫\ue222덺嶷ꪷ➌䱡蠩楐䡝둳홨澤௴ﾻ臦⾷弒\ueec9ꝗ䒯ᬂ惘൮㢅䑺䅋療텔꒳╫\u2fe8뇌綤䲟"));
        TSLog.logger.info(var12.toString());
        Location var10;
        Location var19 = var10 = var1.getTriggeringLocation();
        this.a(var10);
        TSConfig var13 = TSConfig.getInstance(this.a);
        GeofenceDAO var11 = GeofenceDAO.getInstance(this.a);
        TSLocationManager var5 = TSLocationManager.getInstance(this.a);
        Bundle var20 = var19.getExtras();
        var20.putBoolean(Application.B("\ud96f닇ꨉ㉸\uf5ffĻ氥懶"), var13.getIsMoving());
        var20.putFloat(Application.B("\ud969닐ꨫ㉺\uf5ecĦ氮落"), var13.getOdometer());
        EventBus var21 = EventBus.getDefault();
        TSLocationManager var10004 = var5;
        Context var14 = this.a;
        ActivityTransitionEvent var6 = ActivityRecognitionService.getMostProbableActivity();
        LocationProviderChangeEvent var7 = var10004.getCurrentLocationProvider();
        var21.post(new TSLocation(var14, var10, var6, var7));
        Iterator var15 = var1.getTriggeringGeofences().iterator();

        while(var15.hasNext()) {
            Geofence var16 = (Geofence)var15.next();
            if (var13.getMaxRecordsToPersist() != 0) {
                TSGeofence var18;
                if ((var18 = var11.find(var16.getRequestId())) == null) {
                    TSLog.logger.error(Application.B("\ud940닕ꨭ㉻\uf5ecĶ汫爛\uf179믴\u1f1e\ue192윜陎磇辀ʹ楞괟䱥浣酀\uf35d䫔⻖\uda8eꓕઈ稰쯽舧憖㸼\udff6⡚ᶴ慞摺\udcf3\uf465膐^ં钼") + var16.getRequestId());
                } else {
                    TSLocation var17;
                    var17 = new TSLocation.<init>(this.a, var10, ActivityRecognitionService.getMostProbableActivity());
                    GeofenceEvent var8;
                    GeofenceEvent var22 = var8 = new GeofenceEvent;
                    var22.<init>(var1, var18, var17);
                    this.a(var22.getLocation());
                    EventBus.getDefault().post(var8);
                }
            }
        }

    }

    private void a(TSLocation var1) {
        TSConfig var2;
        if (!(var2 = TSConfig.getInstance(this.a)).shouldPersist(var1)) {
            this.a(1000L);
        } else {
            if (EventBus.getDefault().hasSubscriberForEvent(PersistEvent.class)) {
                if (com.transistorsoft.locationmanager.b.a.a().a(this.a)) {
                    EventBus var10000 = EventBus.getDefault();
                    TSConfig var10003 = var2;
                    Context var4 = this.a;
                    JSONObject var3 = var10003.getParams();
                    var10000.post(new PersistEvent(var4, var1, var3));
                } else {
                    TSLog.logger.warn(TSLog.warn(Application.B("\ud940닕ꨭ㉻\uf5ecĶ汫爛\uf179믴Ἀ\ue19e윀陙碎辔ʨ椑괕䱯浮酂\uf34c䪝⻋\uda85")));
                }

                this.a(1000L);
            } else {
                if (var2.getMaxDaysToPersist() == 0 || !var2.getPersist()) {
                    this.a(1000L);
                    return;
                }

                if (com.transistorsoft.locationmanager.data.sqlite.b.a(this.a).persist(var1)) {
                    if (TSConfig.getInstance(this.a).getAutoSync()) {
                        BackgroundGeolocation.getInstance(this.a).sync(new TSSyncCallback() {
                            public void onSuccess(List<LocationModel> var1) {
                                a.this.a(1000L);
                            }

                            public void onFailure(String var1) {
                                a.this.a(1000L);
                            }
                        });
                    }
                } else {
                    TSLog.logger.error(TSLog.error(Application.B("\ud94f닺ꨗ㉒\uf5dbĆ汫郎\uf157뮝ἴ\ue1ae유陯") + var1));
                    this.a(1000L);
                }
            }

        }
    }

    private void a(long var1) {
        (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
            public void run() {
                TSLog.logger.debug(com.transistorsoft.locationmanager.e.a.c + Application.B("삐\uf8ce槐\ue34c秩돴"));
                BackgroundTaskManager.getInstance().stopBackgroundTask(a.this.a, a.this.b);
            }
        }, var1);
    }

    private void a(Location var1) {
        Bundle var2;
        Bundle var10000 = var2 = var1.getExtras();
        TSConfig var3 = TSConfig.getInstance(this.a);
        if (var10000 == null) {
            var2 = new Bundle.<init>();
        }

        var2.putString(Application.B("\ud967닗ꨰ㉾\uf5e6ļ"), Application.B("\ud969닚ꨃ㉲\uf5e6Ĵ氮滑\uf175뮱"));
        var2.putFloat(Application.B("\ud969닐ꨫ㉺\uf5ecĦ氮落"), var3.getOdometer());
        Context var8 = this.a;
        IntentFilter var4;
        var4 = new IntentFilter.<init>(Application.B("\ud967닚ꨠ㉥\uf5e6Ļ氯籠\uf17f뮺Ἄ\ue19e윜陞磉辆ʿ楅괐䱯浣鄍\uf37a䪵⻰\udabfꓳવ稛쯆艄憷㸓\udf98⡹ᶐ慮"));
        Intent var5;
        if ((var5 = var8.registerReceiver((BroadcastReceiver)null, var4)) != null) {
            Intent var9 = var5;
            Intent var10002 = var5;
            int var6 = var5.getIntExtra(Application.B("\ud96a닑ꨲ㉲\uf5e5"), -1);
            var2.putFloat(Application.B("\ud964닕ꨰ㉣\uf5ecĠ氲蘆\uf17a뮱Ἆ\ue19e윞"), (float)var6 / (float)var10002.getIntExtra(Application.B("\ud975닗ꨥ㉻\uf5ec"), -1));
            boolean var7;
            if ((var6 = var9.getIntExtra(Application.B("\ud975닀ꨥ㉣\uf5fcġ"), -1)) != 2 && var6 != 5) {
                var7 = false;
            } else {
                var7 = true;
            }

            var2.putBoolean(Application.B("\ud96f닇ꨛ㉴\uf5e1ĳ氹懶\uf17f뮺\u1f1f"), var7);
        }

        var1.setExtras(var2);
    }
}
