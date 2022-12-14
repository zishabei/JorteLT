//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Build.VERSION;
import androidx.annotation.Nullable;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.data.sqlite.GeofenceDAO;
import com.transistorsoft.locationmanager.event.GeofenceEvent;
import com.transistorsoft.locationmanager.event.MotionChangeEvent;
import com.transistorsoft.locationmanager.event.PersistEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.geofence.TSGeofenceManager;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.location.TSMotionChangeRequest;
import com.transistorsoft.locationmanager.location.TSMotionChangeRequest.Builder;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.EventBus;

public class GeofencingService extends AbstractService {
    private static final AtomicInteger sMotionChangeRequestId = new AtomicInteger(0);

    public GeofencingService() {
    }

    public static PendingIntent getPendingIntent(Context var0) {
        return getPendingIntent(var0, (String)null);
    }

    public static PendingIntent getPendingIntent(Context var0, @Nullable String var1) {
        Intent var2;
        var2 = new Intent.<init>(var0, GeofencingService.class);
        if (var1 != null) {
            var2.setAction(var1);
        }

        if (VERSION.SDK_INT >= 26) {
            Context var10000 = var0;
            int var3 = Util.getPendingIntentFlags(134217728);
            return PendingIntent.getForegroundService(var10000, 0, var2, var3);
        } else {
            return PendingIntent.getService(var0, 0, var2, 134217728);
        }
    }

    public static void stop(Context var0) {
        stopService(var0);
        if (isAcquiringMotionChange()) {
            TSLocationManager.getInstance(var0).cancelRequest(sMotionChangeRequestId.get());
            sMotionChangeRequestId.set(0);
        }

    }

    public static void changePace(Context var0, boolean var1, @Nullable TSLocationCallback var2) {
        TSConfig var3;
        if (!(var3 = TSConfig.getInstance(var0)).getGeofenceModeHighAccuracy()) {
            var1 = false;
        }

        var3.setIsMoving(var1);
        TSGeofenceManager var4 = TSGeofenceManager.getInstance(var0);
        if (var1) {
            HeartbeatService.stop(var0);
            var4.startMonitoringSignificantLocationChanges();
        } else {
            if (var3.getNotification().getSticky()) {
                AbstractService.launchService(var0, GeofencingService.class, Application.B("???\u0e6c\uf393??????"));
            } else {
                stopService(var0);
            }

            HeartbeatService.start(var0);
            var4.stopMonitoringSignificantLocationChanges();
            var4.setIsMoving(false);
        }

        if (var3.getGeofenceModeHighAccuracy()) {
            ForegroundNotification.setStartedAt(var1 ? (new Date()).getTime() : 0L);
        }

        TSLocationManager var5 = TSLocationManager.getInstance(var0);
        if (isAcquiringMotionChange()) {
            var5.cancelRequest(sMotionChangeRequestId.get());
            sMotionChangeRequestId.set(0);
        }

        TSMotionChangeRequest var10001 = ((Builder)(new Builder(var0)).setCallback(new GeofencingService.b(var0, var1, var2))).build();
        sMotionChangeRequestId.set(var10001.getId());
        var5.getCurrentPosition(var10001);
    }

    public static void stopService(Context var0) {
        AbstractService.stop(var0, GeofencingService.class);
    }

    public static boolean isAcquiringMotionChange() {
        return sMotionChangeRequestId.get() != 0;
    }

    private void handleGeofencingEvent(GeofencingEvent var1) {
        Context var2 = this.getApplicationContext();
        if (var1.hasError()) {
            TSLog.logger.warn(TSLog.warn(Application.B("???\ue821????????????\uf239???\ue432????????????????????????\ue87e") + var1.getErrorCode()));
            if (var1.getErrorCode() == 1000) {
                TSGeofenceManager.getInstance(var2).reset();
            }

        } else if (var1.getTriggeringGeofences() == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("???\ue821????????????\uf239???\ue432????????????????????????\ue83b???\uea46??????????????????\u243a??????\uf585???????????????????????????????????????????????????\uf7e4??????\u0b96???\uf5c7\u12d7????????????\udc95???????????????")));
        } else {
            int var3;
            if ((var3 = var1.getGeofenceTransition()) == 1 || var3 == 2 || var3 == 4) {
                String var10 = Application.B("???\ue80a????????????\uf214");
                String var4 = "";
                int var5;
                if ((var5 = var1.getGeofenceTransition()) != 1) {
                    if (var5 != 2) {
                        if (var5 == 4) {
                            var4 = Application.B("???\ue837????????????\uf22e???\ue433????????????????????????\ue801???\uea77???????????????????????????\uf5b2???????????????");
                            var10 = Application.B("???\ue813?????????");
                        }
                    } else {
                        var4 = Application.B("???\ue837????????????\uf22e???\ue433????????????????????????\ue801???\uea77???????????????????????????\uf5b0???");
                        var10 = Application.B("???\ue81c??????");
                    }
                } else {
                    var4 = Application.B("???\ue837????????????\uf22e???\ue433????????????????????????\ue801???\uea77???????????????????????????\uf5b2????????????");
                    var10 = Application.B("???\ue80a?????????");
                }

                TSMediaPlayer.getInstance().debug(var2, var4);
                StringBuilder var14;
                StringBuilder var10001 = var14 = new StringBuilder;
                var10001.<init>();
                var10001.append(TSLog.header(Application.B("???\ue821????????????\uf239???\ue432????????????????????????\ue87e") + var10));
                Iterator var11 = var1.getTriggeringGeofences().iterator();

                while(var11.hasNext()) {
                    var14.append(TSLog.boxRow(((Geofence)var11.next()).getRequestId()));
                }

                var14.append(Application.B("??????????????????????????\ue75c?????????????????????????????\u0874????????????\uef11???????????????????????????????\ue4a1???\u0d80??????????????\ueba2"));
                TSLog.logger.info(var14.toString());
                TSConfig var12 = TSConfig.getInstance(var2);
                Location var15;
                Location var19 = var15 = var1.getTriggeringLocation();
                TSLocation.applyExtras(var2, var15);
                TSGeofenceManager.getInstance(var2).setLocation(var15, var12.getIsMoving());
                GeofenceDAO var13 = GeofenceDAO.getInstance(var2);
                TSLocationManager var6 = TSLocationManager.getInstance(var2);
                Bundle var20 = var19.getExtras();
                var20.putBoolean(Application.B("???\ue837????????????\uf234???"), var12.getIsMoving());
                var20.putFloat(Application.B("???\ue820????????????\uf23f???"), var12.getOdometer());
                EventBus var21 = EventBus.getDefault();
                TSLocationManager var10006 = var6;
                ActivityTransitionEvent var16 = ActivityRecognitionService.getMostProbableActivity();
                var21.post(new TSLocation(var2, var15, var16, var10006.getCurrentLocationProvider()));
                Iterator var17 = var1.getTriggeringGeofences().iterator();

                while(var17.hasNext()) {
                    Geofence var7;
                    TSGeofence var8;
                    if ((var8 = var13.find((var7 = (Geofence)var17.next()).getRequestId())) == null) {
                        TSLog.logger.error(Application.B("???\ue825????????????\uf27a???\ue433????????????????????????\ue831???\uea77??????????????????\u2430??????\uf5a6??????????????????????????????????????????") + var7.getRequestId());
                    } else {
                        TSLocation var18;
                        var18 = new TSLocation.<init>(var2, var15, ActivityRecognitionService.getMostProbableActivity());
                        GeofenceEvent var9;
                        var9 = new GeofenceEvent.<init>(var1, var8, var18);
                        if (var12.getMaxRecordsToPersist() != 0) {
                            this.persist(var9.getLocation());
                        }

                        EventBus.getDefault().post(var9);
                    }
                }

            }
        }
    }

    private void persist(TSLocation var1) {
        TSConfig var2;
        Context var3;
        if ((var2 = TSConfig.getInstance(var3 = this.getApplicationContext())).shouldPersist(var1)) {
            if (EventBus.getDefault().hasSubscriberForEvent(PersistEvent.class)) {
                if (com.transistorsoft.locationmanager.b.a.a().a(var3)) {
                    EventBus.getDefault().post(new PersistEvent(var3, var1, var2.getParams()));
                } else {
                    TSLog.logger.warn(TSLog.warn(Application.B("???\ud9e5?????????????????????????????????????????????????????????\ue1a2??????\uead3???")));
                }
            } else {
                if (var2.getMaxDaysToPersist() == 0 || !var2.getPersist()) {
                    return;
                }

                if (com.transistorsoft.locationmanager.data.sqlite.b.a(var3).persist(var1)) {
                    if (var2.getAutoSync() && var2.hasUrl()) {
                        HttpService.getInstance(var3).flush(true);
                    }
                } else {
                    TSLog.logger.error(TSLog.error(Application.B("???\ud9ca????????????????????????????????????") + var1));
                }
            }

        }
    }

    private void handleStationaryGeofenceExit(GeofencingEvent var1) {
        Context var2;
        Context var10000 = var2 = this.getApplicationContext();
        TSGeofenceManager var3 = TSGeofenceManager.getInstance(var10000);
        TSConfig var4;
        if (!(var4 = TSConfig.getInstance(var10000)).getEnabled()) {
            TSLog.logger.warn(TSLog.warn(Application.B("???\ue693\u08c4????????????????????????????????????\uf1d4\u001b??????\ue739????????????????????????\uf076?????????\u0d81????????????????????????\uee4b???\uea53???\u1aca\uf411????????????\ud867??????\udd42")));
            var3.stopMonitoringStationaryRegion();
        } else {
            Location var7;
            var3.setLocation(var7 = var1.getTriggeringLocation(), var4.getIsMoving());
            if (!var4.isLocationTrackingMode()) {
                TSGeofenceManager var10 = var3;
                TSLocation.applyExtras(var2, var7);
                var7.getExtras().putString(Application.B("???\ue691\u08c0??????"), Application.B("???\ue688\u08d1?????????\u2be2???????????????"));
                var7.getExtras().remove(Application.B("???\ue686\u08c8?????????"));
                TSLocation var8;
                var8 = new TSLocation.<init>(var2, var7, ActivityRecognitionService.getMostProbableActivity());
                EventBus.getDefault().post(new MotionChangeEvent(var8));
                var10.startMonitoringStationaryRegion(var7);
            } else {
                boolean var9 = true;
                int var5 = 3;
                if (c.a(var2)) {
                    var9 = var4.hasTriggerActivity(var5 = ActivityRecognitionService.getLastActivity().getActivityType());
                }

                if (var9 || var5 == 3) {
                    TrackingService.changePace(var2, true);
                }
            }

            TSMediaPlayer.getInstance().debug(this.getApplicationContext(), Application.B("???\ue694\u08c9?????????\u2bf5???\u175d??????????????????\uf1d4\u0007??????\ue778??????????????????"));
            StringBuilder var6;
            StringBuilder var11 = var6 = new StringBuilder;
            var11.<init>();
            var11.append(TSLog.header(Application.B("???\ue682\u08ca?????????\u2be2???\u175c??????????????????\uf1d2\u0010??????\ue74a????????????????????????\uf06a????????????????????????????????????\uee60???")));
            var11.append(TSLog.boxRow(Application.B("??????\u0885???") + var7));
            TSLog.logger.info(var6.toString());
        }
    }

    private void handleLocationResult(LocationResult var1) {
        LocationResult var10000 = var1;
        Context var9 = this.getApplicationContext();
        Location var2;
        Location var5 = var2 = var10000.getLastLocation();
        GeofencingService var10001 = this;
        TSConfig var6 = TSConfig.getInstance(var9);
        TSGeofenceManager.getInstance(var10001).setLocation(var2, var6.getIsMoving());
        long var3 = TSLocationManager.locationAge(var5);
        StringBuilder var7;
        StringBuilder var8 = var7 = new StringBuilder;
        var8.<init>();
        var8.append(TSLog.header(Application.B("?????????????????\uf7d7??????\uf3a2????????????\uf184?????????????????????\uea3d\ue2fb????????????????????????????????????\uf288???")));
        var8.append(TSLog.boxRow(Application.B("????????????") + var2.toString() + Application.B("?????????????????\uf794") + var3 + Application.B("?????????????????\uf7d9??????\uf3e5") + var2.getTime()));
        TSLog.logger.info(var7.toString());
        TSMediaPlayer.getInstance().debug(var9, Application.B("?????????????????\uf7c0??????\uf3ab????????????\uf18a?????????????????????\uea2e\ue2cd????????????????????????????????????\uf284????????????????????????????????????\uec48???"));
    }

    private void handleLocationAvailability(LocationAvailability var1) {
    }

    public void onCreate() {
        super.doCreate(GeofencingService.class.getSimpleName());
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        if (!this.start(var1, true)) {
            return 2;
        } else {
            BackgroundGeolocation.getThreadPool().execute(new GeofencingService.a(var1));
            return 3;
        }
    }

    public IBinder onBind(Intent var1) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private static class b implements TSLocationCallback {
        private final TSLocationCallback a;
        private final boolean b;
        private final Context c;

        b(Context var1, boolean var2, @Nullable TSLocationCallback var3) {
            this.c = var1;
            this.b = var2;
            this.a = var3;
        }

        public void onLocation(TSLocation var1) {
            GeofencingService.sMotionChangeRequestId.set(0);
            TSLocationCallback var2;
            if ((var2 = this.a) != null) {
                var2.onLocation(var1);
            }

            TSGeofenceManager.getInstance(this.c).startMonitoringStationaryRegion(var1.getLocation());
            if (!this.b) {
                ActivityRecognitionService.start(this.c);
            }

        }

        public void onError(Integer var1) {
            TSLocationCallback var2;
            if ((var2 = this.a) != null) {
                var2.onError(var1);
            }

            if (!this.b) {
                ActivityRecognitionService.start(this.c);
            }

        }
    }

    class a implements Runnable {
        private final Intent a;

        a(Intent var2) {
            this.a = var2;
        }

        public void run() {
            Context var10001 = GeofencingService.this.getApplicationContext();
            String var1 = this.a.getAction();
            TSConfig var2 = TSConfig.getInstance(var10001);
            TSGeofenceManager var3 = TSGeofenceManager.getInstance(var10001);
            GeofencingService var4 = GeofencingService.this;
            boolean var6;
            if (var2.isLocationTrackingMode() || (!var2.getIsMoving() || !var2.getGeofenceModeHighAccuracy() || !var3.isMonitoringGeofencesInProximity()) && !var2.getNotification().getSticky()) {
                var6 = false;
            } else {
                var6 = true;
            }

            var4.setSticky(var6);
            if (var1 != null) {
                GeofencingEvent var5;
                if (!var1.equalsIgnoreCase(Application.B("???????????????")) && var1.equalsIgnoreCase(Application.B("\u16fa???????????????\u0e89???????????????????????????????????")) && (var5 = GeofencingEvent.fromIntent(this.a)).getTriggeringGeofences() != null) {
                    GeofencingService.this.handleStationaryGeofenceExit(var5);
                }
            } else if (LocationResult.hasResult(this.a)) {
                GeofencingService.this.handleLocationResult(LocationResult.extractResult(this.a));
                if (!GeofencingService.this.isSticky() && var2.getIsMoving()) {
                    GeofencingService.this.setStopDelay(500L);
                }
            } else if (LocationAvailability.hasLocationAvailability(this.a)) {
                GeofencingService.this.handleLocationAvailability(LocationAvailability.extractLocationAvailability(this.a));
            } else {
                GeofencingService.this.handleGeofencingEvent(GeofencingEvent.fromIntent(this.a));
            }

            GeofencingService.this.finish(500L);
        }
    }
}
