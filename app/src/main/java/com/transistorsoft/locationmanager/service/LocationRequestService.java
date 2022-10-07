//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.geofence.TSGeofenceManager;
import com.transistorsoft.locationmanager.location.SingleLocationRequest;
import com.transistorsoft.locationmanager.location.SingleLocationResult;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.location.WatchPositionResult;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;

public class LocationRequestService extends AbstractService {
    public LocationRequestService() {
    }

    public static void stopService(Context var0) {
        AbstractService.stop(var0, LocationRequestService.class);
    }

    private void handleStartAction(Intent var1) {
        int var2 = var1.getIntExtra(Application.B("쑍즐"), 0);
        SingleLocationRequest var3;
        if ((var3 = TSLocationManager.getInstance(this.getApplicationContext()).getRequest(var2)) == null) {
            TSLog.logger.error(TSLog.error(Application.B("쑢즕璥\ueca3칗\u0ba2羼潚듗ﷆ䄝닳\ue9c6ّ㮌䗙壸㷵臣ぐ側鑭㪒ﳮ\ue5c7ꕁ䆵ཻ뱔∽\ueb74㧻⢐\uf866兞짪ᚑ뤨\ue912锽襂韘\udccc箲\udb1d\u0cfcጹ坷\uf130뫀ᎈ\u175a伄鋑㸠왡䇈챨㎑ݰԂ등ᠥ碎掾") + var2 + Application.B("쐟짔璅\ueca1칆ண翲潚뒂ﷆ") + var1));
            this.setSticky(false);
            this.finish();
        } else {
            var3.startUpdatingLocation();
            if (var3.getAction() == 1 && TSConfig.getInstance(this.getApplicationContext()).getEnabled()) {
                ActivityRecognitionService.start(this.getApplicationContext());
            }

        }
    }

    private void handleLocationResult(Intent var1) {
        TSConfig var2 = TSConfig.getInstance(this.getApplicationContext());
        if (var1.getAction() == null) {
            TSLog.logger.error(TSLog.error(Application.B("\uecc9Ͱ\ueb5c\ue3af\udd26溟ਖ਼⯏\udebfૌ垟릟挅樗\uf627䜼멹㣺ᙐ\u0fec䱸\ue044锉⫅穬\u200d\ueb66\uf001〪焫㜘퍜酣쑧\uf7ce")));
            this.setSticky(false);
        } else {
            Intent var10000 = var1;
            String[] var10001 = var1.getAction().split(Application.B("\uec9b"));
            int var10 = Integer.valueOf(var10001[0]);
            int var3 = Integer.valueOf(var10001[1]);
            Location var4;
            Bundle var5;
            if ((var5 = (var4 = LocationResult.extractResult(var10000).getLastLocation()).getExtras()) == null) {
                var5 = new Bundle.<init>();
                var4.setExtras(var5);
            }

            StringBuffer var6;
            var6 = new StringBuffer.<init>();
            String var7 = Application.B("\uecf2\u0378\ueb5c\ue3ac\udd26溟ਖ਼⯏\udebfૌ垟릟挅樗\uf627䜼멹㣺ᙐ\u0fec");
            if (var10 != 1) {
                if (var10 != 2) {
                    if (var10 != 3) {
                        if (var10 == 5) {
                            var7 = Application.B("\uecd6Ͱ\ueb46\ue3a8\udd22溪\u0a7a\u2bd3\udeb5\u0ad9垂릙挄");
                        }
                    } else {
                        var7 = Application.B("\uecd1ͣ\ueb5d\ue3bd\udd23溞ੰ⯒\udebfૅ垊릘挍樜");
                    }
                } else {
                    var7 = Application.B("\uecc6ʹ\ueb46\ue388\udd3f溈੧⯅\udeb2\u0ad9垻릙挙樐\uf601䜰멥㣡");
                }
            } else {
                var7 = Application.B("\ueccc;\ueb46\ue3a2\udd25溔\u0a76⯈\udebdૃ垌릓");
            }

            var6.append(TSLog.header(var7 + Application.B("\uec81͝\ueb5d\ue3a8\udd2b溎\u0a7c⯏\udeb2૿垎릅挟樕\uf601䝣먪") + var3));
            long var12 = TSLocationManager.locationAge(var4);
            var6.append(TSLog.boxRow(Application.B("㒜\udfdc\ueb12\ue3eb") + var4 + Application.B("\uec8ḏ\ueb53\ue3ac\udd2f滀ਵ") + var12 + Application.B("\ueccc͢\ueb1e\ue3eb\udd3e溓\u0a78⯅\udee6ઍ") + var4.getTime()));
            TSLog.logger.info(var6.toString());
            TSLocationManager var11 = TSLocationManager.getInstance(this.getApplicationContext());
            if (var10 == 5) {
                WatchPositionResult var9;
                var9 = new WatchPositionResult.<init>(var3, var4);
                var11.onWatchPositionResult(var9);
            } else {
                SingleLocationRequest var13;
                if ((var13 = var11.getRequest(var3)) == null) {
                    TSLog.logger.warn(TSLog.warn(Application.B("\uece7Ͱ\ueb5b\ue3a7\udd2f溞ਵ\u2bd4\udeb3ઍ垍릟挄樝\uf655䜊멣㣡ᙛ\u0ff4䰽\ue07f锏⫒穥⁙\ueb61\uf001つ焘㜞퍙酿쑭\uf7d3ｂ\u0e91쮝") + var3));
                    SingleLocationRequest.forceStop(this.getApplicationContext(), var10, var3);
                    this.setSticky(false);
                    return;
                }

                if (var10 == 1 && !var2.getEnabled()) {
                    TSLog.logger.warn(TSLog.warn(Application.B("\uece0ͥ\ueb46\ue3ae\udd27溊\u0a61⮀\udea8ૂ埋릑挏樍\uf655䜴멥㣻ᙕ\u0ff7䰶\ue050锈⫐穪⁊\ueb6d\uf04eぺ焥㜈퍁酾쑡\uf7cfｘ\u0e8b쯊\ue548秃㔀榞鷸퍤왩㟪厠뤈団䘄\uf776")));
                    var13.finish();
                    return;
                }

                var5.putInt(Application.B("\uecd3ʹ\ueb43\ue3be\udd2f溉\u0a61\u2be9\udeb8"), var3);
                var11.onSingleLocationResult(new SingleLocationResult(var3, var4));
                TSLog.logger.debug(Application.B("\uecf2\u0378\ueb5c\ue3ac\udd26溟ਖ਼⯏\udebfૌ垟릟挅樗\uf627䜼멻㣺ᙙ\u0feb䰬\ue013") + var3 + Application.B("\uec81\u0378\ueb41\ue38d\udd23溔\u0a7c\u2bd3\udeb4ૈ垏막捊") + var13.isFinished());
                if (var13.isFinished()) {
                    if (var2.getIsMoving() && var13.getAction() == 1) {
                        ActivityRecognitionService.start(this.getApplicationContext());
                    }

                    if (var10 == 2 || var10 == 1) {
                        TSGeofenceManager.getInstance(this.getApplicationContext()).setLocation(var4, var2.getIsMoving());
                    }

                    this.setSticky(false);
                }
            }

        }
    }

    private void handleLocationAvailability(Intent var1) {
        LocationAvailability var2 = LocationAvailability.extractLocationAvailability(var1);
        TSLog.logger.info(TSLog.info(Application.B("⊊靻熾蘜㛺龋\ue394㲶淞ꈥ䕢堽ꨇ❞写푍\uddb2켷ꨩ사钽湕琫") + var2.isLocationAvailable()));
    }

    public void onCreate() {
        super.doCreate(LocationRequestService.class.getSimpleName());
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        if (!this.start(var1, false)) {
            return 3;
        } else {
            BackgroundGeolocation.getThreadPool().execute(new LocationRequestService.a(var1));
            return 3;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    class a implements Runnable {
        private final Intent a;

        a(Intent var2) {
            this.a = var2;
        }

        public void run() {
            String var1 = this.a.getAction();
            if (LocationResult.hasResult(this.a)) {
                LocationRequestService.this.setSticky(true);
                LocationRequestService.this.handleLocationResult(this.a);
            } else if (LocationAvailability.hasLocationAvailability(this.a)) {
                LocationRequestService.this.handleLocationAvailability(this.a);
            } else if (var1 != null && var1.equalsIgnoreCase(Application.B("쉳踂繽嘥莹"))) {
                LocationRequestService.this.handleStartAction(this.a);
            }

            LocationRequestService.this.finish(250L);
        }
    }
}
