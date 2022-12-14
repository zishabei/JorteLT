//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.intentfilter.androidpermissions.PermissionManager.PermissionRequestListener;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.transistorsoft.locationmanager.activity.TSLocationManagerActivity;
import com.transistorsoft.locationmanager.adapter.TSConfig.OnChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSActivityChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSBackgroundTaskCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSBeforeInsertBlock;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSConnectivityChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSEmailLogCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSEnabledChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGeofenceCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGeofenceExistsCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGeofencesChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGetCountCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGetGeofenceCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGetGeofencesCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGetLocationsCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGetLogCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSHasGeofenceCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSHeartbeatCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSHttpResponseCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSInsertLocationCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationProviderChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSNotificationActionCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSPlayServicesConnectErrorCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSPowerSaveChangeCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSRequestPermissionCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSScheduleCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSSecurityExceptionCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSSyncCallback;
import com.transistorsoft.locationmanager.data.LocationModel;
import com.transistorsoft.locationmanager.data.SQLQuery;
import com.transistorsoft.locationmanager.data.sqlite.GeofenceDAO;
import com.transistorsoft.locationmanager.data.sqlite.b;
import com.transistorsoft.locationmanager.device.DeviceSettings;
import com.transistorsoft.locationmanager.device.DeviceSettingsRequest;
import com.transistorsoft.locationmanager.event.ActivityChangeEvent;
import com.transistorsoft.locationmanager.event.ConnectivityChangeEvent;
import com.transistorsoft.locationmanager.event.GeofenceEvent;
import com.transistorsoft.locationmanager.event.HeadlessEvent;
import com.transistorsoft.locationmanager.event.HeartbeatEvent;
import com.transistorsoft.locationmanager.event.LocationErrorEvent;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.event.MotionChangeEvent;
import com.transistorsoft.locationmanager.event.PersistEvent;
import com.transistorsoft.locationmanager.event.PowerSaveModeChangeEvent;
import com.transistorsoft.locationmanager.event.SecurityExceptionEvent;
import com.transistorsoft.locationmanager.event.SettingsFailureEvent;
import com.transistorsoft.locationmanager.event.TemplateErrorEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.geofence.TSGeofenceManager;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.http.HttpResponse;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSCurrentPositionRequest;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.location.TSWatchPositionRequest;
import com.transistorsoft.locationmanager.location.TSCurrentPositionRequest.Builder;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSLogReader;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.provider.TSProviderManager;
import com.transistorsoft.locationmanager.scheduler.ScheduleEvent;
import com.transistorsoft.locationmanager.scheduler.TSScheduleManager;
import com.transistorsoft.locationmanager.service.ActivityRecognitionService;
import com.transistorsoft.locationmanager.service.ForegroundNotification;
import com.transistorsoft.locationmanager.service.GeofencingService;
import com.transistorsoft.locationmanager.service.HeartbeatService;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager;
import com.transistorsoft.locationmanager.http.HttpResponse;
import com.transistorsoft.locationmanager.util.Sensors;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.locationmanager.util.d;
import com.transistorsoft.tslocationmanager.Application;

import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

public class BackgroundGeolocation {
    public static final String TAG = Application.B("??????????????????\ue482????????????\udcf1??????????????");
    public static final String EVENT_BOOT = Application.B("????????????");
    public static final String EVENT_TERMINATE = Application.B("??????????????????\ue497??????");
    public static final String EVENT_HEARTBEAT = Application.B("??????????????????\ue493??????");
    public static final String EVENT_SCHEDULE = Application.B("??????????????????\ue49a???");
    public static final String EVENT_LOCATION = Application.B("??????????????????\ue499???");
    public static final String EVENT_MOTIONCHANGE = Application.B("?????????\u3097??????\ue495????????????\udcf5");
    public static final String EVENT_GEOFENCE = Application.B("?????????\u3098??????\ue495???");
    public static final String EVENT_POWERSAVECHANGE = Application.B("??????????????????\ue497????????????\udcf1????????");
    public static final String EVENT_HTTP = Application.B("????????????");
    public static final String EVENT_ERROR = Application.B("???????????????");
    public static final String EVENT_ACTIVITYCHANGE = Application.B("?????????\u3097??????\ue482????????????\udcfe?????");
    public static final String EVENT_PLAY_SERVICES_CONNECT_ERROR = Application.B("??????????????????\ue484????????????\udce3?????\u2c2f?????????\u1879?????????\uaa39???");
    public static final String EVENT_ENABLEDCHANGE = Application.B("??????????????????\ue492????????????\udcf7???");
    public static final String EVENT_CONNECTIVITYCHANGE = Application.B("??????????????????\ue482????????????\udce9?????????????????");
    public static final String EVENT_PROVIDERCHANGE = Application.B("??????????????????\ue493????????????\udcfe?????");
    public static final String EVENT_SECURITY_EXCEPTION = Application.B("??????????????????\ue482????????????\udcf5??????????????");
    public static final String EVENT_GEOFENCES_CHANGE = Application.B("?????????\u3098??????\ue495????????????\udcf1????????");
    public static final String EVENT_GEOFENCESCHANGE = Application.B("?????????\u3098??????\ue495????????????\udcf1????????");
    public static final String EVENT_NOTIFICATIONACTION = Application.B("?????????\u3097??????\ue495????????????\udcfe?????????????????");
    public static final String EVENT_AUTHORIZATION = Application.B("??????????????????\ue49f????????????\udcff???");
    public static final String ACTION_START = Application.B("???????????????");
    public static final String ACTION_STOP = Application.B("????????????");
    public static final String ACTION_START_GEOFENCES = Application.B("??????????????????\ue493????????????\udcf3?????");
    public static final String ACTION_SET_CONFIG = Application.B("??????????????????\ue490??????");
    public static final String ACTION_SET_NOTIFICATION = Application.B("??????????????????\ue49f????????????\udce4?????\u2c2f");
    public static final String ACTION_GET_LOCATIONS = Application.B("??????????????????\ue497????????????\udce3");
    public static final String ACTION_CHANGE_PACE = Application.B("??????????????????\ue4a6?????????");
    public static final String ACTION_ON_MOTION_CHANGE = Application.B("??????????????????\ue499????????????\udcfe?????");
    public static final String ACTION_ON_GEOFENCE = Application.B("??????????????????\ue493?????????");
    public static final String ACTION_SYNC = Application.B("????????????");
    public static final String ACTION_GET_ODOMETER = Application.B("??????????????????\ue49b????????????");
    public static final String ACTION_RESET_ODOMETER = Application.B("??????????????????\ue492????????????\udcf5???");
    public static final String ACTION_SET_ODOMETER = Application.B("??????????????????\ue49b????????????");
    public static final String ACTION_ADD_GEOFENCE = Application.B("??????????????????\ue490????????????");
    public static final String ACTION_ADD_GEOFENCES = Application.B("??????????????????\ue490????????????\udce3");
    public static final String ACTION_GET_GEOFENCES = Application.B("??????????????????\ue490????????????\udce3");
    public static final String ACTION_GET_GEOFENCE = Application.B("??????????????????\ue490????????????");
    public static final String ACTION_REMOVE_GEOFENCE = Application.B("??????????????????\ue4b1????????????\udcfe?????");
    public static final String ACTION_REMOVE_GEOFENCES = Application.B("??????????????????\ue4b1????????????\udcfe????????");
    public static final String ACTION_GEOFENCE_EXISTS = Application.B("?????????\u3098??????\ue495????????????\udce3?????");
    public static final String ACTION_PLAY_SOUND = Application.B("??????????????????\ue483??????");
    public static final String ACTION_GET_CURRENT_POSITION = Application.B("??????????????????\ue484????????????\udcff?????????????????");
    public static final String ACTION_WATCH_POSITION = Application.B("??????????????????\ue499????????????\udcff???");
    public static final String ACTION_STOP_WATCH_POSITION = Application.B("??????????????????\ue482????????????\udce3??????????????");
    public static final String ACTION_GOOGLE_PLAY_SERVICES_CONNECT_ERROR = Application.B("??????????????????\ue4a6?????????\u1fdc\udcf5??????????????????????????????????????????????????");
    public static final String ACTION_LOCATION_ERROR = Application.B("??????????????????\ue499????????????\udcff???");
    public static final String ACTION_HTTP_RESPONSE = Application.B("????????????");
    public static final String ACTION_CLEAR_DATABASE = Application.B("??????????????????\ue497????????????\udce3???");
    public static final String ACTION_DESTROY_LOCATIONS = Application.B("??????????????????\ue48f????????????\udce4?????\u2c2f???");
    public static final String ACTION_DESTROY_LOCATION = Application.B("??????????????????\ue48f????????????\udce4?????\u2c2f");
    public static final String ACTION_DESTROY_LOG = Application.B("??????????????????\ue48f?????????");
    public static final String ACTION_INSERT_LOCATION = Application.B("??????????????????\ue4ba????????????\udcf9?????");
    public static final String ACTION_GET_COUNT = Application.B("??????????????????\ue498???");
    public static final String ACTION_START_ON_BOOT = Application.B("??????????????????\ue498????????????");
    public static final String ACTION_HEARTBEAT = Application.B("??????????????????\ue493??????");
    public static final String ACTION_SCHEDULE = Application.B("??????????????????\ue49a???");
    public static final String ACTION_START_BACKGROUND_TASK = Application.B("??????????????????\ue497????????????\udcff????????????????????");
    public static final String ACTION_STOP_BACKGROUND_TASK = Application.B("??????????????????\ue495????????????\udce5?????????????????");
    public static final String ACTION_FINISH = Application.B("?????????\u3097??????");
    public static final String ACTION_GET_SENSORS = Application.B("??????????????????\ue485?????????");
    public static final String ACTION_REMOVE_LISTENER = Application.B("??????????????????\ue4ba????????????\udcfe?????");
    public static final String ACTION_IS_POWER_SAVE_MODE = Application.B("??????????????????\ue484??????\u2c2f???\udcdd????????");
    public static final String ACTION_IS_IGNORING_BATTERY_OPTIMIZATIONS = Application.B("??????????????????\ue484????????????\udcf1?????????????????\u187d?????????\uaa3f?????????????????????");
    public static final String ACTION_SHOW_SETTINGS = Application.B("??????????????????\ue482????????????\udce3");
    public static final String ACTION_GET_PROVIDER_STATE = Application.B("??????????????????\ue480????????????\udcc3???????????");
    public static final String ACTION_REQUEST_PERMISSION = Application.B("??????????????????\ue482????????????\udcf9??????????????");
    public static final int FORCE_RELOAD_LOCATION_CHANGE = 1;
    public static final int FORCE_RELOAD_MOTION_CHANGE = 2;
    public static final int FORCE_RELOAD_GEOFENCE = 3;
    public static final int FORCE_RELOAD_HEARTBEAT = 4;
    public static final int FORCE_RELOAD_SCHEDULE = 5;
    public static final int FORCE_RELOAD_BOOT = 6;
    private static final String y = Application.B("??????????????????\ue482????????????\udce9?????????????????\u187b???");
    private static BackgroundGeolocation z;
    private static final ExecutorService A = Executors.newCachedThreadPool();
    private static Handler B;
    private final int a = 0;
    private LocationProviderChangeEvent b;
    private final Context c;
    private Activity d;
    private final AtomicBoolean e = new AtomicBoolean(false);
    private final AtomicBoolean f = new AtomicBoolean(false);
    private final AtomicBoolean g = new AtomicBoolean(false);
    private BackgroundGeolocation.z0 h;
    private final List<TSLocationCallback> i = new ArrayList();
    private final List<TSLocationCallback> j = new ArrayList();
    private final List<TSGeofenceCallback> k = new ArrayList();
    private final List<TSEnabledChangeCallback> l = new ArrayList();
    private final List<TSConnectivityChangeCallback> m = new ArrayList();
    private final List<TSHttpResponseCallback> n = new ArrayList();
    private final List<TSHeartbeatCallback> o = new ArrayList();
    private final List<TSActivityChangeCallback> p = new ArrayList();
    private final List<TSPowerSaveChangeCallback> q = new ArrayList();
    private final List<TSLocationProviderChangeCallback> r = new ArrayList();
    private final List<TSScheduleCallback> s = new ArrayList();
    private final List<TSPlayServicesConnectErrorCallback> t = new ArrayList();
    private final List<TSSecurityExceptionCallback> u = new ArrayList();
    private final List<TSNotificationActionCallback> v = new ArrayList();
    private final List<GeofenceEvent> w = new ArrayList();
    private final List<TSCurrentPositionRequest> x = new ArrayList();

    public static BackgroundGeolocation getInstance(Context var0, Intent var1) {
        return getInstance(var0);
    }

    public static BackgroundGeolocation getInstance(Context var0) {
        BackgroundGeolocation var1;
        if ((var1 = z) == null || var1.isDead()) {
            z = b(var0);
        }

        if (var0 instanceof Activity) {
            z.setActivity((Activity) var0);
        }

        return z;
    }

    private static synchronized BackgroundGeolocation b(Context var0) {
        if (z == null) {
            (z = new BackgroundGeolocation(var0)).a();
        }

        return z;
    }

    public static ExecutorService getThreadPool() {
        return A;
    }

    private static b c(Context var0) {
        return com.transistorsoft.locationmanager.data.sqlite.b.a(var0);
    }

    public static Handler getUiHandler() {
        if (B == null) {
            B = new Handler(Looper.getMainLooper());
        }

        return B;
    }

    public static long deltaT(String var0) {
        Calendar var1;
        Calendar var10000 = var1 = Calendar.getInstance();
        var10000.set(11, 0);
        var10000.set(12, 0);
        var10000.set(13, 0);
        var10000.set(14, 0);
        var10000 = Calendar.getInstance();
        var10000.setTimeInMillis(Long.parseLong(var0) * 1000L);
        var10000.set(11, 0);
        var10000.set(12, 0);
        var10000.set(13, 0);
        var10000.set(14, 0);
        long var5 = var1.getTimeInMillis();
        long var3 = var10000.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toDays(var3 - var5);
    }

    public static String getBroadcastAction(String var0) {
        return Application.B("?????????\uf7fd???\uf45c\u2d2f??????\ue3f0??????????????????????????????\ue685???????????????\u1ac8??????\uf61f???????????????????????????\uf82e???") + var0.toUpperCase();
    }

    @TargetApi(26)
    public BackgroundGeolocation(Context var1) {
        this.c = var1.getApplicationContext();
        EventBus var2;
        if (!(var2 = EventBus.getDefault()).isRegistered(this)) {
            var2.register(this);
        }

        Thread.setDefaultUncaughtExceptionHandler(new BackgroundGeolocation.w0());
        getUiHandler().post(LifecycleManager.f());
        ForegroundNotification.createNotificationChannel(this.c);
        TSLog.logger.info(TSLog.ok(Application.B("????????????\uf09f\ufaf7????????????\ued0e?????????????????????????????????????????????\ud8b1???\ue705??????\uef17??????\u20fb????????????\ue8e5?????????\uea96??????") + GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE + Application.B("???")));
    }

    private void a(final TSCallback var1) {
        com.transistorsoft.locationmanager.util.c.g(this.c, new PermissionRequestListener() {
            public void onPermissionGranted() {
                TSConfig var1x = TSConfig.getInstance(BackgroundGeolocation.this.c);
                if (BackgroundGeolocation.this.f.get() && var1x.getEnabled() && var1x.isLocationTrackingMode()) {
                    TSLog.logger.warn(TSLog.warn(Application.B("??????\ud998??????\ue7c1????????\uf318???\ue534???????????????????????????????????????")));
                    var1.onSuccess();
                } else {
                    BackgroundGeolocation var2;
                    BackgroundGeolocation var10000 = var2 = BackgroundGeolocation.this;
                    TSCallback var10004 = var1;
                    var10000.h = var2.new z0(Application.B("??????\ud98b??????"), var10004);
                    BackgroundGeolocation.getThreadPool().execute(BackgroundGeolocation.this.h);
                }
            }

            public void onPermissionDenied(DeniedPermissions var1x) {
                if (com.transistorsoft.locationmanager.util.c.c(BackgroundGeolocation.this.c)) {
                    TSConfig var2 = TSConfig.getInstance(BackgroundGeolocation.this.c);
                    if (BackgroundGeolocation.this.f.get() && var2.getEnabled() && var2.isLocationTrackingMode()) {
                        TSLog.logger.warn(TSLog.warn(Application.B("??????????????????\ued26???\u0ee1??????????????????????????\ue1f8?????????????????")));
                        var1.onSuccess();
                        return;
                    }

                    BackgroundGeolocation var3;
                    BackgroundGeolocation var10000 = var3 = BackgroundGeolocation.this;
                    TSCallback var10004 = var1;
                    var10000.h = var3.new z0(Application.B("???????????????"), var10004);
                    BackgroundGeolocation.getThreadPool().execute(BackgroundGeolocation.this.h);
                } else {
                    var1.onFailure(Application.B("??????????????????\ued2c???\u0efd???????????????????????"));
                    BackgroundGeolocation.this.f.set(false);
                }

            }
        });
        GoogleApiAvailability var2;
        if ((var2 = GoogleApiAvailability.getInstance()).isGooglePlayServicesAvailable(this.c) != 0) {
            this.a(var2.isGooglePlayServicesAvailable(this.c));
        }

    }

    private void b(final TSCallback var1) {
        com.transistorsoft.locationmanager.util.c.g(this.c, new PermissionRequestListener() {
            public void onPermissionGranted() {
                TSConfig var1x = TSConfig.getInstance(BackgroundGeolocation.this.c);
                if (BackgroundGeolocation.this.f.get() && var1x.getEnabled() && !var1x.isLocationTrackingMode()) {
                    TSLog.logger.warn(TSLog.warn(Application.B("\ud972???\ueacd???????????????\uf29c?????????\uf83e?????????????????????????????????\ue5be?????????\uef76??????????????????")));
                    var1.onSuccess();
                } else {
                    BackgroundGeolocation var2;
                    BackgroundGeolocation var10000 = var2 = BackgroundGeolocation.this;
                    TSCallback var10004 = var1;
                    var10000.h = var2.new z0(Application.B("\ud940???\ueade???????????????\uf289?????????\uf82f\u206e"), var10004);
                    BackgroundGeolocation.getThreadPool().execute(BackgroundGeolocation.this.h);
                }
            }

            public void onPermissionDenied(DeniedPermissions var1x) {
                var1.onFailure(Application.B("???????????????\ue4c1\u0bce????????????\uea11????????????\uee4e"));
            }
        });
    }

    private List a(String var1) {
        if (Application.B("??????????????\ud9c8??????").equalsIgnoreCase(var1)) {
            return this.i;
        } else if (Application.B("??????????????\ud9cf???????????????\u18af").equals(var1)) {
            return this.j;
        } else if (Application.B("??????????????\ud9c8????????????????????????").equalsIgnoreCase(var1)) {
            return this.p;
        } else if (Application.B("??????????????\ud9cf??????").equalsIgnoreCase(var1)) {
            return this.k;
        } else if (Application.B("??????????????\ud9c5????????????????????????").equalsIgnoreCase(var1)) {
            return this.r;
        } else if (Application.B("??????????????\ud9c3?????????").equalsIgnoreCase(var1)) {
            return this.o;
        } else if (Application.B("???????????").equalsIgnoreCase(var1)) {
            return this.n;
        } else if (Application.B("??????????????\ud9d4??????").equalsIgnoreCase(var1)) {
            return this.s;
        } else if (Application.B("??????????????\ud9d2???????????????\u18ab?????????").equalsIgnoreCase(var1)) {
            return this.q;
        } else if (Application.B("??????????????\ud9c4???????????????\u18ad???").equalsIgnoreCase(var1)) {
            return this.l;
        } else if (Application.B("??????????????\ud9c2???????????????????????????????????").equalsIgnoreCase(var1)) {
            return this.m;
        } else {
            return Application.B("??????????????\ud9c8???????????????????????????????????").equalsIgnoreCase(var1) ? this.v : null;
        }
    }

    private void a(TSLocationCallback var1) {
        List<TSLocationCallback> list = this.i;
        synchronized (list) {
            this.i.add(var1);
            return;
        }
    }

    private void b(TSLocationCallback var1) {
        List<TSLocationCallback> list = this.j;
        synchronized (list) {
            this.j.add(var1);
            return;
        }
    }

    private void a(TSHttpResponseCallback tSHttpResponseCallback) {
        List<TSHttpResponseCallback> list = this.n;
        synchronized (list) {
            this.n.add(tSHttpResponseCallback);
            return;
        }
    }

    private void a(TSHeartbeatCallback tSHeartbeatCallback) {
        List<TSHeartbeatCallback> list = this.o;
        synchronized (list) {
            this.o.add(tSHeartbeatCallback);
            return;
        }
    }

    private void a(TSActivityChangeCallback tSActivityChangeCallback) {
        List<TSActivityChangeCallback> list = this.p;
        synchronized (list) {
            this.p.add(tSActivityChangeCallback);
            return;
        }
    }

    private void a(TSPowerSaveChangeCallback tSPowerSaveChangeCallback) {
        List<TSPowerSaveChangeCallback> list = this.q;
        synchronized (list) {
            this.q.add(tSPowerSaveChangeCallback);
            return;
        }
    }

    private void a(TSLocationProviderChangeCallback tSLocationProviderChangeCallback) {
        List<TSLocationProviderChangeCallback> list = this.r;
        synchronized (list) {
            this.r.add(tSLocationProviderChangeCallback);
            return;
        }
    }

    private void a(TSConnectivityChangeCallback tSConnectivityChangeCallback) {
        List<TSConnectivityChangeCallback> list = this.m;
        synchronized (list) {
            this.m.add(tSConnectivityChangeCallback);
            return;
        }
    }

    private void a(TSEnabledChangeCallback tSEnabledChangeCallback) {
        List<TSEnabledChangeCallback> list = this.l;
        synchronized (list) {
            this.l.add(tSEnabledChangeCallback);
            return;
        }
    }

    private void a(TSScheduleCallback tSScheduleCallback) {
        List<TSScheduleCallback> list = this.s;
        synchronized (list) {
            this.s.add(tSScheduleCallback);
            return;
        }
    }

    private void a(TSGeofencesChangeCallback tSGeofencesChangeCallback) {
        TSGeofenceManager.getInstance(this.c).onGeofencesChange(tSGeofencesChangeCallback);
    }

    private void a(TSGeofenceCallback tSGeofenceCallback) {
        List<TSGeofenceCallback> list = this.k;
        synchronized (list) {
            this.k.add(tSGeofenceCallback);
            return;
        }
    }

    private void a(TSNotificationActionCallback tSNotificationActionCallback) {
        List<TSNotificationActionCallback> list = this.v;
        synchronized (list) {
            this.v.add(tSNotificationActionCallback);
            return;
        }
    }

    private void a(TSPlayServicesConnectErrorCallback tSPlayServicesConnectErrorCallback) {
        List<TSPlayServicesConnectErrorCallback> list = this.t;
        synchronized (list) {
            this.t.add(tSPlayServicesConnectErrorCallback);
            return;
        }
    }

    private void a(final int var1) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.t) {
                    Iterator iterator = BackgroundGeolocation.this.t.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSPlayServicesConnectErrorCallback) iterator.next()).onPlayServicesConnectError(var1);
                    }
                }
            }
        });
    }

    private void a(TSSecurityExceptionCallback tSSecurityExceptionCallback) {
        List<TSSecurityExceptionCallback> list = this.u;
        synchronized (list) {
            this.u.add(tSSecurityExceptionCallback);
            return;
        }
    }

    private void a(final ScheduleEvent scheduleEvent) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.s) {
                    Iterator iterator = BackgroundGeolocation.this.s.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSScheduleCallback) iterator.next()).onSchedule(scheduleEvent);
                    }
                }
            }
        });
    }

    private void a(final ActivityTransitionEvent activityTransitionEvent) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                ActivityChangeEvent activityChangeEvent = new ActivityChangeEvent(activityTransitionEvent);
                synchronized (BackgroundGeolocation.this.p) {
                    Iterator iterator = BackgroundGeolocation.this.p.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSActivityChangeCallback) iterator.next()).onActivityChange(activityChangeEvent);
                    }
                }
            }
        });
    }

    private void a(final TSLocation tSLocation) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.i) {
                    Iterator iterator = BackgroundGeolocation.this.i.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSLocationCallback) iterator.next()).onLocation(tSLocation);
                    }
                }
            }
        });
    }

    private void b(final int n2) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.i) {
                    Iterator iterator = BackgroundGeolocation.this.i.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSLocationCallback) iterator.next()).onError(n2);
                    }
                }
            }
        });
    }

    private void a(final LocationProviderChangeEvent locationProviderChangeEvent) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.r) {
                    Iterator iterator = BackgroundGeolocation.this.r.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSLocationProviderChangeCallback) iterator.next()).onLocationProviderChange(locationProviderChangeEvent);
                    }
                }
            }
        });
    }

    private void a(final HeartbeatEvent heartbeatEvent) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.o) {
                    Iterator iterator = BackgroundGeolocation.this.o.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSHeartbeatCallback) iterator.next()).onHeartbeat(heartbeatEvent);
                    }
                }
            }
        });
    }

    private void a(final GeofenceEvent geofenceEvent) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.k) {
                    Iterator iterator = BackgroundGeolocation.this.k.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSGeofenceCallback) iterator.next()).onGeofence(geofenceEvent);
                    }
                }
            }
        });
    }

    private void a(final MotionChangeEvent motionChangeEvent) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.j) {
                    Iterator iterator = BackgroundGeolocation.this.j.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSLocationCallback) iterator.next()).onLocation(motionChangeEvent.getLocation());
                    }
                }
            }
        });
    }

    private void b() {
        TSLog.logger.debug(TSLog.off(Application.B("??????????????????\udf86??????????????????????????????")));
        if (this.h != null) {
            this.h = null;
        }

        BackgroundGeolocation var10000 = this;
        BackgroundGeolocation var10001 = this;
        BackgroundGeolocation var10002 = this;
        BackgroundGeolocation var10003 = this;
        BackgroundGeolocation var10004 = this;
        BackgroundGeolocation var10005 = this;
        BackgroundGeolocation var10006 = this;
        BackgroundGeolocation var10007 = this;
        BackgroundGeolocation var10008 = this;
        BackgroundGeolocation var10009 = this;
        BackgroundGeolocation var10010 = this;
        BackgroundGeolocation var10011 = this;
        BackgroundGeolocation var10012 = this;
        BackgroundGeolocation var10013 = this;
        TSGeofenceManager.getInstance(this.c).removeListeners();
        HttpService.getInstance(this.c).removeListeners();
        synchronized (this) {
            var10013.i.clear();
            var10012.j.clear();
            var10011.k.clear();
            var10010.n.clear();
            var10009.o.clear();
            var10008.p.clear();
            var10007.q.clear();
            var10006.s.clear();
            var10005.t.clear();
            var10004.r.clear();
            var10003.m.clear();
            var10002.l.clear();
            var10001.v.clear();
        }
    }

    private void c() {
        (new File(this.c.getCacheDir(), Application.B("\ude40???????????????????????????\udf0f??????????????\u19ce??????\ue3b8????????????\udfe5????????????"))).delete();
    }

    private void d() {
    }

    void a() {
        TSLocationManager.getInstance(this.c);
        HttpService.getInstance(this.c);
        TSScheduleManager.getInstance(this.c);
        TSGeofenceManager.getInstance(this.c);
        final TSConfig var1 = TSConfig.getInstance(this.c);
        TSProviderManager.getInstance(this.c).startMonitoring(this.c);
        GoogleApiAvailability var2;
        if ((var2 = GoogleApiAvailability.getInstance()).isGooglePlayServicesAvailable(this.c) != 0) {
            int var3 = var2.isGooglePlayServicesAvailable(this.c);
            TSLog.logger.warn(TSLog.warn(Application.B("??????????????\ud9c4???????????????\u18af???????????????????????????????????????????\ue3cf???????????????\uf77f????????????\u0b78?????????\u2e7d\ud8e3\ud9a8") + var3));
        }

        OnChangeCallback var4;
        var4 = new OnChangeCallback() {
            @Override
            public void a(TSConfig tSConfig) {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {

                    /*
                     * WARNING - Removed try catching itself - possible behaviour change.
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    @Override
                    public void run() {
                        Boolean bl = TSConfig.getInstance(BackgroundGeolocation.this.c).getEnabled();
                        if (LifecycleManager.f().b()) {
                            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(BackgroundGeolocation.this.c, Application.B("???\uf1c4\uda68\ue028??????????????????????????"), var1));
                        }
                        synchronized (BackgroundGeolocation.this.l) {
                            Iterator iterator = BackgroundGeolocation.this.l.iterator();
                            while (true) {
                                if (!iterator.hasNext()) {
                                    return;
                                }
                                ((TSEnabledChangeCallback) iterator.next()).onEnabledChange(bl);
                            }
                        }
                    }
                });
            }
        };
        var1.onChange(Application.B("??????????????\ud9c4???"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                TSLocation.resetGeofenceTemplate();
            }
        };
        var1.onChange(Application.B("??????????????\ud9cf??????????????????????????????"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                TSLocation.resetLocationTemplate();
            }
        };
        var1.onChange(Application.B("??????????????\ud9c8??????????????????????????????"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                if (var1.getEnabled() && !var1.getIsMoving()) {
                    HeartbeatService.start(BackgroundGeolocation.this.c);
                }

            }
        };
        var1.onChange(Application.B("??????????????\ud9c3????????????????????????????????"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                if (var1.getEnabled()) {
                    ActivityRecognitionService.start(BackgroundGeolocation.this.c);
                }

            }
        };
        var1.onChange(Application.B("??????????????\ud9c8??????????????????????????????????????"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                if (var1.getEnabled() && BackgroundGeolocation.this.e.get()) {
                    com.transistorsoft.locationmanager.util.c.g(BackgroundGeolocation.this.c, new PermissionRequestListener() {
                        public void onPermissionGranted() {
                        }

                        public void onPermissionDenied(DeniedPermissions var1) {
                        }
                    });
                }
            }
        };
        var1.onChange(Application.B("??????????????\ud9c8?????????\u12bf?????????????????????????????????????????????????\ue3d9"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                if (var1.getEnabled() && BackgroundGeolocation.this.e.get()) {
                    if (!var1.getDisableMotionActivityUpdates() && !com.transistorsoft.locationmanager.util.c.a(BackgroundGeolocation.this.c)) {
                        com.transistorsoft.locationmanager.util.c.c(BackgroundGeolocation.this.c, new PermissionRequestListener() {
                            public void onPermissionGranted() {
                            }

                            public void onPermissionDenied(DeniedPermissions var1) {
                            }
                        });
                    }

                }
            }
        };
        var1.onChange(Application.B("??????????????\ud9cd?????????????????????????????????????????????????????????????\ue3de"), var4);
        TSMediaPlayer.getInstance().init(this.c);
        A.execute(new Runnable() {
            public void run() {
                b var10001 = BackgroundGeolocation.c(BackgroundGeolocation.this.c);
                var10001.unlock();
                var10001.prune(var1.getMaxDaysToPersist());
                BackgroundGeolocation.this.c();
            }
        });
        if (LifecycleManager.f().b() && var1.getEnabled()) {
            getThreadPool().execute(TSGeofenceManager.getInstance(this.c));
        }

    }

    public void setActivity(Activity var1) {
        if (this.d != var1) {
            this.d = var1;
        }
    }

    public Activity getActivity() {
        return this.d;
    }

    public boolean isDead() {
        return this.c == null;
    }

    public void registerPlugin(int var1) {
        com.transistorsoft.locationmanager.b.a.a().a(this.c, var1);
    }

    public void ready(TSCallback var1) {
        TSConfig var2 = TSConfig.getInstance(this.c);
        if (!this.e.get() && !var2.getConfigUrl().isEmpty() && this.g.compareAndSet(false, true)) {
            Log.i(TAG, "ready: ?????????");
//            TSConfig var7 = var2;
//            Runnable var5;
//            var5 = new Runnable() {
//                {
//                    this.a = var2;
//                }
//
//                public void run() {
//                    BackgroundGeolocation.this.ready(this.a);
//                }
//            }.<init>(var1);
//            var1 = new TSCallback() {
//                {
//                    this.a = var2;
//                }
//
//                public void onSuccess() {
//                    BackgroundGeolocation.getUiHandler().post(this.a);
//                }
//
//                public void onFailure(String var1) {
//                    TSLog.logger.warn(TSLog.warn(var1));
//                    BackgroundGeolocation.getUiHandler().post(this.a);
//                }
//            }.<init>(var5);
//            var7.loadConfig(var1);
        } else if (this.e.get()) {
            if (var2.getEnabled()) {
                BackgroundGeolocation var6 = this;
                TSCurrentPositionRequest var3 = ((Builder) ((Builder) ((Builder) (new Builder(this.c)).setPersist(false)).setSamples(1)).setDesiredAccuracy(100)).setMaximumAge(60000L).build();
                TSLocationManager.getInstance(var6.c).getCurrentPosition(var3);
            }

            var1.onSuccess();
        } else {
            this.e.set(true);
            TSProviderManager.getInstance(this.c).startMonitoring(this.c);
            Boolean var10000;
            if (var2.hasSchedule() && var2.getSchedulerEnabled()) {
                this.startSchedule();
                var10000 = false;
            } else {
                var2.setSchedulerEnabled(false);
                var10000 = var2.getEnabled();
            }

            if (var10000) {
                if (var2.isLocationTrackingMode()) {
                    this.start(var1);
                } else {
                    this.startGeofences(var1);
                }
            } else {
                var1.onSuccess();
            }

            LocationProviderChangeEvent var4;
            var4 = new LocationProviderChangeEvent(this.c);
            EventBus.getDefault().post(var4);
        }
    }

    public void start(TSCallback var1) {
        if (this.h != null) {
            var1.onFailure(Application.B("???????????????\uf6f4???????????????????????????\u0e98?????????????????????\ua48e?????????\ue144??????\u082e??????\uf6aa????????????????????\uf68c?????????"));
        } else {
            this.a(var1);
        }
    }

    public void start() {
        TSCallback var1;
        var1 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        BackgroundGeolocation.z0 var2;
        if ((var2 = this.h) != null) {
            var1 = var2.a();
            this.h = null;
        }

        this.a(var1);
    }

    public void startOnBoot() {
        TSConfig var1 = TSConfig.getInstance(this.c);
        TrackingService.start(this.c);
        if (LifecycleManager.f().b()) {
            Context var2 = this.c;
            JSONObject var3 = var1.toJson();
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var2, Application.B("????????????"), var3));
        }

    }

    public void startOnSchedule() {
        TSProviderManager.getInstance(this.c).startMonitoring(this.c);
        TrackingService.start(this.c);
    }

    public void stopOnSchedule() {
        TSConfig.getInstance(this.c).setIsMoving(false);
        if (LifecycleManager.f().b()) {
            TSProviderManager.getInstance(this.c).stopMonitoring(this.c);
        }

        this.f.set(false);
        this.h = null;
        TrackingService.stop(this.c);
    }

    public boolean startSchedule() {
        if (TSConfig.getInstance(this.c).hasSchedule()) {
            TSScheduleManager.getInstance(this.c).start();
            return true;
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("?????????\ue784?????????\udd75?????????\ue035\udb86???????????\ue3c5???)?????????????????????\ua7ea??????????????????????????????")));
            return false;
        }
    }

    public void stopSchedule() {
        TSScheduleManager.getInstance(this.c).stop();
    }

    public void startGeofences(TSCallback var1) {
        if (this.h != null) {
            var1.onFailure(Application.B("????????????\ued52?????????????????????\uef26??????????????????????????????????????\ue6e0????????????\uda83?????????\ued99\ue4a1???????????????\ue4c9??????"));
        } else {
            this.b(var1);
        }
    }

    public void startGeofences() {
        TSCallback var1;
        var1 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        BackgroundGeolocation.z0 var2;
        if ((var2 = this.h) != null) {
            var1 = var2.a();
        }

        this.b(var1);
    }

    public void stop(TSCallback var1) {
        if (this.h != null) {
            this.h = null;
        }

        this.f.set(false);
        getThreadPool().execute(() -> {
            TrackingService.stop(this.c);
            Handler var10000 = getUiHandler();
            Objects.requireNonNull(var1);
            var10000.post(var1::onSuccess);
        });
    }

    public void stop() {
        TSCallback var1;
        var1 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.stop(var1);
    }

    public void changePace(boolean var1, TSCallback var2) {
        TSConfig var3;
        if (!(var3 = TSConfig.getInstance(this.c)).getEnabled()) {
            var2.onFailure(Application.B("\ue215\ue500\uee71??????\udc4d???\uebf7???????????????????????????\uef10?????????\ue3f7????????????\udc92??????????????????"));
        } else {
            if (!var3.isLocationTrackingMode()) {
                var1 = false;
            }

            getThreadPool().execute(new BackgroundGeolocation.ChangePaceTask(var1, var2));
        }
    }

    public void changePace(boolean var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.changePace(var1, var2);
    }

    public void getCurrentPosition(final TSCurrentPositionRequest var1) {
        getThreadPool().execute(new Runnable() {
            public void run() {
                TSLocationManager.getInstance(BackgroundGeolocation.this.c).getCurrentPosition(var1);
            }
        });
    }

    public void watchPosition(final TSWatchPositionRequest var1) {
        getThreadPool().execute(new Runnable() {
            public void run() {
                TSLocationManager.getInstance(BackgroundGeolocation.this.c).watchPosition(var1);
            }
        });
    }

    public void stopWatchPosition(TSCallback var1) {
        getThreadPool().execute(new Runnable() {
            public void run() {
                TSLocationManager.getInstance(BackgroundGeolocation.this.c).stopWatchPosition();
            }
        });
        var1.onSuccess();
    }

    public void sync(TSSyncCallback var1) {
        getThreadPool().execute(new BackgroundGeolocation.a1(var1));
    }

    public void sync() {
        getThreadPool().execute(new BackgroundGeolocation.a1());
    }

    public void persistLocation(TSLocation var1) {
        TSConfig var2;
        if ((var2 = TSConfig.getInstance(this.c)).shouldPersist(var1)) {
            if (EventBus.getDefault().hasSubscriberForEvent(PersistEvent.class)) {
                if (com.transistorsoft.locationmanager.b.a.a().a(this.c)) {
                    EventBus var10000 = EventBus.getDefault();
                    Context var3 = this.c;
                    JSONObject var4 = var2.getParams();
                    var10000.post(new PersistEvent(var3, var1, var4));
                } else {
                    TSLog.logger.warn(TSLog.warn(Application.B("\udf63???6???????????????????????????\ud81e???\u2bf6\udc99??????????????????????????????")));
                }
            } else {
                if (var2.getMaxDaysToPersist() == 0 || !var2.getPersist()) {
                    return;
                }

                getThreadPool().execute(new BackgroundGeolocation.y0(var1));
            }

        }
    }

    public int getCount() {
        return c(this.c).count();
    }

    public void getCount(TSGetCountCallback var1) {
        getThreadPool().execute(new BackgroundGeolocation.t0(var1));
    }

    public void addGeofence(TSGeofence var1, TSCallback var2) {
        TSGeofenceManager.getInstance(this.c).add(var1, var2);
    }

    public void addGeofence(TSGeofence var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.addGeofence(var1, var2);
    }

    public void addGeofences(List<TSGeofence> var1, TSCallback var2) {
        TSGeofenceManager.getInstance(this.c).add(var1, var2);
    }

    public void addGeofences(List<TSGeofence> var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.addGeofences(var1, var2);
    }

    public void getGeofences(TSGetGeofencesCallback var1) {
        getThreadPool().execute(new BackgroundGeolocation.u0(var1));
    }

    public void getGeofence(@NonNull final String var1, final TSGetGeofenceCallback var2) {
        getThreadPool().execute(new Runnable() {
            public void run() {
                final TSGeofence var1x = GeofenceDAO.getInstance(BackgroundGeolocation.this.c).find(var1);
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        TSGeofence var1xx;
                        if ((var1xx = var1x) != null) {
                            var2.onSuccess(var1xx);
                        } else {
                            var2.onFailure(Application.B("?????????"));
                        }

                    }
                });
            }
        });
    }

    public void geofenceExists(@NonNull final String var1, final TSHasGeofenceCallback var2) {
        getThreadPool().execute(new Runnable() {
            public void run() {
                final boolean var1x = GeofenceDAO.getInstance(BackgroundGeolocation.this.c).exists(var1);
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        var2.onComplete(var1x);
                    }
                });
            }
        });
    }

    public void removeGeofence(String var1, TSCallback var2) {
        TSGeofenceManager.getInstance(this.c).remove(var1, var2);
    }

    public void removeGeofence(String var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.removeGeofence(var1, var2);
    }

    public void removeGeofences(List<String> var1, TSCallback var2) {
        TSGeofenceManager.getInstance(this.c).remove(var1, var2);
    }

    public void removeGeofences(List<String> var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.removeGeofences(var1, var2);
    }

    public void removeGeofences(TSCallback var1) {
        TSGeofenceManager.getInstance(this.c).remove(new ArrayList(), var1);
    }

    public void removeGeofences() {
        TSCallback var1;
        var1 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.removeGeofences(var1);
    }

    public void geofenceExists(String var1, TSGeofenceExistsCallback var2) {
        TSGeofenceManager.getInstance(this.c).geofenceExists(var1, var2);
    }

    public Float getOdometer() {
        return TSConfig.getInstance(this.c).getOdometer();
    }


    public void setOdometer(final Float f2, final TSLocationCallback tSLocationCallback) {
        final TSLocationManager tSLocationManager = TSLocationManager.getInstance(this.c);
        if (!tSLocationManager.isLocationServicesEnabled().booleanValue()) {
            tSLocationCallback.onError(1);
            this.b(1);
            return;
        }
        BackgroundGeolocation.getThreadPool().execute(new Runnable() {

            @Override
            public void run() {
                tSLocationManager.setOdometer(f2, tSLocationCallback);
            }
        });
    }

    public JSONArray getLocations() {
        List var10000 = c(this.c).all();
        JSONArray var2;
        var2 = new JSONArray();
        Iterator var1 = var10000.iterator();

        while (var1.hasNext()) {
            var2.put(((LocationModel) var1.next()).json);
        }

        return var2;
    }

    public void getLocations(TSGetLocationsCallback var1) {
        getThreadPool().execute(new BackgroundGeolocation.v0(var1));
    }

    public void insertLocation(JSONObject var1, TSInsertLocationCallback var2) {
        if (!var1.has(Application.B("???\ue9a3????????????\uf137??????"))) {
            var2.onFailure(Application.B("???\ue9a4????????????\uf11a?????????????????????????????????????????????????????????????\uddc2?????????????????????????????????????????????"));
        } else if (!var1.has(Application.B("???\ue9a5????????????"))) {
            var2.onFailure(Application.B("???\ue9a4????????????\uf11a?????????????????????????????????????????????????????????????\uddc2?????????????????????????????????????????????????????"));
        } else {
            getThreadPool().execute(new BackgroundGeolocation.x0(var1, var2));
        }
    }

    public String getLog() {
        return TSLogReader.getLog(new SQLQuery());
    }

    public void getLog(TSGetLogCallback var1) {
        TSLog.getLog(var1);
    }

    public void emailLog(String var1, Activity var2, TSEmailLogCallback var3) {
        TSLog.emailLog(var1, var2, var3);
    }

    public void destroyLog(TSCallback var1) {
        TSLog.destroyLog(var1);
    }

    public void destroyLog() {
        TSCallback var1;
        var1 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.destroyLog(var1);
    }

    public void clearDatabase(TSCallback var1) {
        this.destroyLocations(var1);
    }

    public void destroyLocations(TSCallback var1) {
        getThreadPool().execute(new BackgroundGeolocation.s0(var1));
    }

    public void destroyLocations() {
        TSCallback var1;
        var1 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.destroyLocations(var1);
    }

    public void destroyLocation(String var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        };
        this.destroyLocation(var1, var2);
    }

    public void destroyLocation(final String var1, final TSCallback var2) {
        getThreadPool().execute(new Runnable() {
            public void run() {
                final boolean var1x = BackgroundGeolocation.c(BackgroundGeolocation.this.c).a(var1);
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        if (var1x) {
                            var2.onSuccess();
                        } else {
                            var2.onFailure("");
                        }

                    }
                });
            }
        });
    }

    public Sensors getSensors() {
        return Sensors.getInstance(this.c);
    }

    public Boolean isPowerSaveMode() {
        return DeviceSettings.getInstance().isPowerSaveMode(this.c);
    }

    public Boolean isIgnoringBatteryOptimizations() {
        return DeviceSettings.getInstance().isIgnoringBatteryOptimization(this.c);
    }

    public DeviceSettingsRequest requestSettings(String var1) {
        return DeviceSettings.getInstance().request(this.c, var1);
    }

    public boolean showSettings(String var1) {
        return DeviceSettings.getInstance().show(this.c, var1);
    }

    public LocationProviderChangeEvent getProviderState() {
        return new LocationProviderChangeEvent(this.c);
    }

    public void requestPermission(final TSRequestPermissionCallback var1) {
        com.transistorsoft.locationmanager.util.c.g(this.c, new PermissionRequestListener() {
            public void onPermissionGranted() {
                int var1x;
                if (com.transistorsoft.locationmanager.util.c.b(BackgroundGeolocation.this.c)) {
                    var1x = TSProviderManager.PERMISSION_ALWAYS;
                } else {
                    var1x = TSProviderManager.PERMISSION_WHEN_IN_USE;
                }

                var1.onSuccess(var1x);
                TSLocationManagerActivity.start(BackgroundGeolocation.this.c, Application.B("???\udeb6???\uf340????????????\uf2bb\ue97c??????????????????"));
            }

            public void onPermissionDenied(DeniedPermissions var1x) {
                var1.onFailure(TSProviderManager.PERMISSION_DENIED);
            }
        });
    }

    public void requestTemporaryFullAccuracy(String string, final TSRequestPermissionCallback tSRequestPermissionCallback) {
        if (ContextCompat.checkSelfPermission((Context) this.c, (String) Application.B("\uea76???\uee1b????????????????????????????????????????????????????????\ufe6c?????????????????????????????????\u0e62???\uf75e??????")) == 0) {
            tSRequestPermissionCallback.onSuccess(TSProviderManager.ACCURACY_AUTHORIZATION_FULL);
            return;
        }
        com.transistorsoft.locationmanager.util.c.f(this.c, new PermissionRequestListener() {

            public void onPermissionGranted() {
                Object object = TSConfig.getInstance(BackgroundGeolocation.this.c);
                TrackingService.changePace(BackgroundGeolocation.this.c, ((TSConfig) object).getIsMoving(), null);
                object = BackgroundGeolocation.this;
                ((BackgroundGeolocation) object).a(new LocationProviderChangeEvent(((BackgroundGeolocation) object).c));
                tSRequestPermissionCallback.onSuccess(TSProviderManager.ACCURACY_AUTHORIZATION_FULL);
            }

            public void onPermissionDenied(DeniedPermissions deniedPermissions) {
                tSRequestPermissionCallback.onFailure(TSProviderManager.ACCURACY_AUTHORIZATION_REDUCED);
            }
        });
    }

    public void startBackgroundTask(TSBackgroundTaskCallback var1) {
        BackgroundTaskManager.getInstance().startBackgroundTask(this.c, var1);
    }

    public void stopBackgroundTask(int var1) {
        BackgroundTaskManager.getInstance().stopBackgroundTask(this.c, var1);
    }

    public void startTone(int var1) {
    }

    public void startTone(String param1) {
        // $FF: Couldn't be decompiled
    }

    public void onLocation(TSLocationCallback var1) {
        this.a(var1);
    }

    public void onMotionChange(TSLocationCallback var1) {
        this.b(var1);
    }

    public void onActivityChange(TSActivityChangeCallback var1) {
        this.a(var1);
    }

    public void onPowerSaveChange(TSPowerSaveChangeCallback var1) {
        this.a(var1);
    }

    public void onHttp(TSHttpResponseCallback var1) {
        this.a(var1);
    }

    public void onGeofence(TSGeofenceCallback var1) {
        this.a(var1);
    }

    public void onGeofencesChange(TSGeofencesChangeCallback var1) {
        this.a(var1);
    }

    public void onLocationProviderChange(TSLocationProviderChangeCallback var1) {
        this.a(var1);
    }

    public void onConnectivityChange(TSConnectivityChangeCallback var1) {
        this.a(var1);
    }

    public void onEnabledChange(TSEnabledChangeCallback var1) {
        this.a(var1);
    }

    public void onNotificationAction(TSNotificationActionCallback var1) {
        this.a(var1);
    }

    public void onHeartbeat(TSHeartbeatCallback var1) {
        this.a(var1);
    }

    public void onSchedule(TSScheduleCallback var1) {
        this.a(var1);
    }

    public void onPlayServicesConnectError(TSPlayServicesConnectErrorCallback var1) {
        this.a(var1);
    }

    public void onSecurityException(TSSecurityExceptionCallback var1) {
        this.a(var1);
    }

    public void setBeforeInsertBlock(TSBeforeInsertBlock var1) {
        com.transistorsoft.locationmanager.data.sqlite.b.a(this.c).a(var1);
    }

    public void removeListeners() {
        this.b();
    }

    public void removeListeners(String var1) {
        TSLog.logger.debug(TSLog.ok(Application.B("??????\u2ffd?????????\ueb10") + var1));
        if (var1.equalsIgnoreCase(Application.B("??????????????????\ueb53\ue7e0??????\udff7??????\ud9c1???"))) {
            TSGeofenceManager.getInstance(this.c).removeListeners();
        } else if (Application.B("??????\u2fec?????????\ueb59\ue7ff??????\udff6??????").equalsIgnoreCase(var1)) {
            HttpService.getInstance(this.c).removeListeners();
        } else {
            List var3;
            if ((var3 = this.a(var1)) != null) {
                List var10000 = var3;
                List var10001 = var3;
                synchronized (var3) {
                    var10001.clear();
                }
            }
        }

    }

    public Object removeListener(String var1, Object var2) {
        Boolean var3 = null;
        if (var1.equalsIgnoreCase(Application.B("???????????????????????????\uf882\uf542\uee59?????????"))) {
            TSGeofenceManager.getInstance(this.c).removeListener(var1, var2);
        } else if (Application.B("???????????????????????????\uf895\uf543\uee57???").equalsIgnoreCase(var1)) {
            HttpService.getInstance(this.c).removeListener(var1, var2);
        } else {
            List var4;
            if ((var4 = this.a(var1)) != null) {
                var3 = var4.remove(var2);
            }
        }

        if (var3 != null) {
            TSLog.logger.debug(TSLog.ok(Application.B("???????????????????????????\uf895\uf54f\uee56??????????????????????????????") + var1));
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("???????????????????????????\uf8c1\uf558\uee5d??????????????????????????????????????????????????\uea7f???\uf36c???") + var1));
        }

        return var3;
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onTemplateError(TemplateErrorEvent var1) {
        TSMediaPlayer.getInstance().debug(this.c, Application.B("???&\uda32???????????????\uf897???\u0bce???\u0601???????????????\uedb9???????????????????????????\ue65c\udef6????????????????????????\ue80c???"));
        Handler var10000 = getUiHandler();
        Context var10003 = this.c;
        String var2 = Application.B("???:\uda2b???????????????\uf8d8??????????????????????????\uedbd?????????????????????") + var1.getTemplateName();
        var10000.post(new d(var10003, var2, var1.getError().getMessage()));
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onSettingsFailure(SettingsFailureEvent var1) {
        Handler var10000 = getUiHandler();
        SettingsFailureEvent var10003 = var1;
        Context var3 = this.c;
        String var4 = var1.title;
        String var2 = var10003.message;
        var10000.post(new d(var3, var4, var2));
    }

    @Subscribe
    public void _onScheduleEvent(ScheduleEvent var1) {
        if (!TSConfig.getInstance(this.c).getSchedulerEnabled()) {
            TSLog.logger.warn(TSLog.warn(Application.B("??????\udcc1??????\ue2cb??????\u1cad???????????????????????????\udd31?????????\uf5f8???\u2d78?????????\ue1c2??????\uddd5\udbe5??????\udbb4??????\uf3c1????????????????????????\ude3f???????????????\ud936??????") + var1));
        } else {
            this.a(var1);
            if (LifecycleManager.f().b()) {
                Context var2 = this.c;
                JSONObject var3 = var1.getState();
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var2, Application.B("??????\udcc7??????\ue2db??????"), var3));
            }

        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onActivityTransitionEvent(ActivityTransitionEvent var1) {
        if (LifecycleManager.f().b()) {
            ActivityChangeEvent var2;
            var2 = new ActivityChangeEvent(var1);
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("?????????????????????????????????\udd64??????"), var2));
        }

        this.a(var1);
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onLocationChange(TSLocation var1) {
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("????????????????????????"), var1));
        }

        this.a(var1);
    }

    public void fireNotificationActionListeners(final String string) {
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("????????????????????\uea6c\ue36a?????????????????????????"), string));
        }
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                synchronized (BackgroundGeolocation.this.v) {
                    Iterator iterator = BackgroundGeolocation.this.v.iterator();
                    while (true) {
                        if (!iterator.hasNext()) {
                            return;
                        }
                        ((TSNotificationActionCallback) iterator.next()).onClick(string);
                    }
                }
            }
        });
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onLocationProviderChange(LocationProviderChangeEvent var1) {
        this.b = var1;
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("\u312f?????????????????????????????????\uabfd???"), var1));
        }

        this.a(var1);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectivityChange(ConnectivityChangeEvent connectivityChangeEvent) {
        BackgroundGeolocation backgroundGeolocation = this;
        List<TSConnectivityChangeCallback> list = backgroundGeolocation.m;
        synchronized (list) {
            Iterator<TSConnectivityChangeCallback> iterator = backgroundGeolocation.m.iterator();
            if (LifecycleManager.f().b()) {
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("??????\u2d7c?????????????????????????????????????????????"), connectivityChangeEvent));
            }
            while (true) {
                if (!iterator.hasNext()) {
                    return;
                }
                iterator.next().onConnectivityChange(connectivityChangeEvent);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPowerSaveModeChange(PowerSaveModeChangeEvent powerSaveModeChangeEvent) {
        BackgroundGeolocation backgroundGeolocation = this;
        List<TSPowerSaveChangeCallback> list = backgroundGeolocation.q;
        synchronized (list) {
            Iterator<TSPowerSaveChangeCallback> iterator = backgroundGeolocation.q.iterator();
            if (LifecycleManager.f().b()) {
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("????????????\u0383????????\u0dfc\ue101?????????\ueb7f???"), powerSaveModeChangeEvent));
            }
            while (true) {
                if (!iterator.hasNext()) {
                    return;
                }
                iterator.next().onPowerSaveChange(powerSaveModeChangeEvent.isPowerSaveMode());
            }
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onLocationError(LocationErrorEvent var1) {
        TSLog.logger.warn(TSLog.warn(Application.B("?????????????????????w????????????\u9fec?????????") + var1.errorCode));
        TSConfig var2 = TSConfig.getInstance(this.c);
        TSMediaPlayer.getInstance().debug(this.c, Application.B("?????????????????????p????????????\u9fed?????????\ueac8????????\uf014??????????????\ud9d3"));
        if (var1.errorCode == 1 && var2.getDebug()) {
            Handler var10000 = getUiHandler();
            Context var4 = this.c;
            String var3 = var1.message;
            var10000.post(new d(var4, Application.B("?????????????????????w????????????\u9ff5?????????\ueac9????????\uf000\u074c????????????"), var3));
        }

        this.b(var1.errorCode);
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onGeofenceEvent(GeofenceEvent var1) {
        TSConfig.getInstance(this.c);
        if (com.transistorsoft.locationmanager.d.b.e(this.c)) {
            List var2;
            List var10001 = var2 = this.w;
            BackgroundGeolocation var10002 = this;
            synchronized (var2) {
                var10002.w.clear();
            }

            this.w.add(var1);
            if (LifecycleManager.f().b()) {
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("????????????????????????"), var1));
            }

            this.a(var1);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void _onSecurityException(SecurityExceptionEvent securityExceptionEvent) {
        TSLog.logger.warn(TSLog.warn(Application.B("??????????????????????????????\udd2f??????????????????\uf4b0???????????????\uecac\uf5dc???\uf4a5\uf7a3????????????\ue7f0???????????????????????????\ue341") + securityExceptionEvent.toString()));
        synchronized (BackgroundGeolocation.this.u) {
            Iterator<TSSecurityExceptionCallback> iterator = BackgroundGeolocation.this.u.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    return;
                }
                iterator.next().onSecurityException(securityExceptionEvent);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void _onHttpResponse(HttpResponse httpResponse) {
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("???????????"), httpResponse));
        }
        synchronized (BackgroundGeolocation.this.n) {
            Iterator<TSHttpResponseCallback> iterator = BackgroundGeolocation.this.n.iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    return;
                }
                iterator.next().onHttpResponse(httpResponse);
            }
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onHeartbeat(HeartbeatEvent var1) {
        this.a(var1);
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onMotionChange(MotionChangeEvent var1) {
        if (LifecycleManager.f().b()) {
            TSConfig.getInstance(this.c);
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("???????????????????????????\udbc7??????"), var1));
        }

        this.a(var1);
    }

    public void onActivityResumed() {
        TSScheduleManager.getInstance(this.c).cancelOneShot(Application.B("???\uf81c?????????????????????"));
    }

    public void onActivityStopped() {
        TSConfig var1;
        if ((var1 = TSConfig.getInstance(this.c)).getEnabled() && var1.getEnableHeadless() && !var1.getStopOnTerminate()) {
            TSScheduleManager.getInstance(this.c).oneShot(Application.B("?????????\uf444??????????????????\ue8ab????????????"), 5000L, true);
        }

    }

    public void onActivityDestroy() {
        this.e.set(false);
        TSLocationManager.getInstance(this.c).stopWatchPosition();
        TSScheduleManager.getInstance(this.c).cancelOneShot(Application.B("??????????????????????????????????????\udd2b???"));
        LifecycleManager.f().a(true);
        Context var10003 = this.c;
        com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var10003, Application.B("???????????????????????????"), TSConfig.getInstance(var10003).toJson()));
        this.b();
        getThreadPool().execute(new Runnable() {
            public void run() {
                TSConfig var1;
                if ((var1 = TSConfig.getInstance(BackgroundGeolocation.this.c)).getStopOnTerminate()) {
                    if (var1.getEnabled()) {
                        var1.setEnabled(false);
                        TrackingService.stop(BackgroundGeolocation.this.c);
                    }

                    BackgroundGeolocation.this.f.set(false);
                    TSProviderManager.getInstance(BackgroundGeolocation.this.c).stopMonitoring(BackgroundGeolocation.this.c);
                    TSScheduleManager.getInstance(BackgroundGeolocation.this.c).stop();
                } else if (!var1.getForegroundService()) {
                    EventBus.getDefault().post(new BackgroundGeolocation.ActivityDestroyed());
                }

                StringBuilder var2 = (new StringBuilder()).append(TSLog.header(Application.B("\ue136??????\ud817\ufff5??????????????????????????\ue5df???????????????\ude2fx???\udead??????"))).append(TSLog.boxRow(Application.B("\ue108??????\ud809\ufffb??????????????????????????\ue5db??????") + var1.getStopOnTerminate())).append(TSLog.boxRow(Application.B("\ue11e??????\ud81b\uffd8???????????") + var1.getEnabled()));
                BackgroundGeolocation.this.c();
                TSLog.logger.info(var2.toString());
            }
        });
    }

    public static class ActivityDestroyed {
        public ActivityDestroyed() {
        }
    }

    private class s0 implements Runnable {
        TSCallback a;

        s0(TSCallback var2) {
            this.a = var2;
        }

        public void run() {
            BackgroundGeolocation.c(BackgroundGeolocation.this.c).clear();
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    s0.this.a.onSuccess();
                }
            });
        }
    }

    private class x0 implements Runnable {
        private final JSONObject a;
        private final TSInsertLocationCallback b;
        private String c;

        public x0(JSONObject var2, TSInsertLocationCallback var3) {
            this.a = var2;
            this.b = var3;
        }

        public void run() {
            final String var1;
            if ((var1 = com.transistorsoft.locationmanager.data.sqlite.b.a(BackgroundGeolocation.this.c).persist(this.a)) != null) {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        x0.this.b.onSuccess(var1);
                    }
                });
                TSConfig var3;
                TSConfig var10000 = var3 = TSConfig.getInstance(BackgroundGeolocation.this.c);
                HttpService var2 = HttpService.getInstance(BackgroundGeolocation.this.c);
                if (var10000.hasUrl() && var3.getAutoSync() && var2.isNetworkAvailable()) {
                    Integer var4 = var3.getAutoSyncThreshold();
                    if (var4 > 0 && com.transistorsoft.locationmanager.data.sqlite.b.a(BackgroundGeolocation.this.c).count() < var4) {
                        return;
                    }

                    var2.flush();
                }
            } else {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        x0.this.b.onFailure(Application.B("??????\uecb8???????????????????????????????????????????????????\ud7ac???"));
                    }
                });
            }

        }
    }

    private class v0 implements Runnable {
        private final TSGetLocationsCallback a;

        v0(TSGetLocationsCallback var2) {
            this.a = var2;
        }

        public void run() {
            final List var1 = BackgroundGeolocation.c(BackgroundGeolocation.this.c).all();
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    v0.this.a.onSuccess(var1);
                }
            });
        }
    }

    private class u0 implements Runnable {
        private final TSGetGeofencesCallback a;

        public u0(TSGetGeofencesCallback var2) {
            this.a = var2;
        }

        public void run() {
            final List var1 = GeofenceDAO.getInstance(BackgroundGeolocation.this.c).all();
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    u0.this.a.onSuccess(var1);
                }
            });
        }
    }

    private class t0 implements Runnable {
        private TSGetCountCallback a;

        public t0(TSGetCountCallback var2) {
            this.a = var2;
        }

        public void run() {
            final int var1 = BackgroundGeolocation.c(BackgroundGeolocation.this.c).count();
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    t0.this.a.onSuccess(var1);
                }
            });
        }
    }

    private class y0 implements Runnable {
        private final Object a;

        y0(Object var2) {
            this.a = var2;
        }

        public void run() {
            if (Boolean.valueOf(BackgroundGeolocation.c(BackgroundGeolocation.this.c).persist((TSLocation) this.a))) {
                if (TSConfig.getInstance(BackgroundGeolocation.this.c).getAutoSync()) {
                    BackgroundGeolocation.this.sync();
                }
            } else {
                Log.w(Application.B("????????????\u09bb????????????????????????????????\uf6c0"), Application.B("?????????????????????????????????????????") + this.a);
            }

        }
    }

    private class a1 implements Runnable {
        private TSSyncCallback a;

        a1(TSSyncCallback var2) {
            this.a = var2;
        }

        a1() {
        }

        public void run() {
            if (TSConfig.getInstance(BackgroundGeolocation.this.c).hasUrl()) {
                HttpService var1;
                if (!(var1 = HttpService.getInstance(BackgroundGeolocation.this.c)).isNetworkAvailable()) {
                    if (this.a != null) {
                        BackgroundGeolocation.getUiHandler().post(new Runnable() {
                            public void run() {
                                a1.this.a.onFailure(Application.B("??????????????????????????????????????????????????????????????"));
                            }
                        });
                    }

                    return;
                }

                if (!var1.isBusy()) {
                    var1.flush(this.a);
                } else {
                    TSLog.logger.info(TSLog.notice(Application.B("?????????\ueabe???????????????????????????????????????????????????????\udbac?????????????????????????????????\udbc1???\u1976???????????????\ud87e???")));
                    if (this.a != null) {
                        BackgroundGeolocation.getUiHandler().post(new Runnable() {
                            public void run() {
                                a1.this.a.onFailure(Application.B("???????????????????????????????????????????????????\ud917\uf45f"));
                            }
                        });
                    }
                }
            } else if (this.a != null) {
                b var10000 = BackgroundGeolocation.c(BackgroundGeolocation.this.c);
                final List var2;
                var10000.destroyAll(var2 = var10000.all());
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        a1.this.a.onSuccess(var2);
                    }
                });
            }

        }
    }

    public class ChangePaceTask implements Runnable {
        private final Boolean a;
        private final TSCallback b;

        ChangePaceTask(boolean var2, TSCallback var3) {
            this.a = var2;
            this.b = var3;
        }

        public Boolean b() {
            return this.a;
        }

        public TSCallback a() {
            return this.b;
        }

        public void run() {
            TSLocationCallback var1;
            var1 = new TSLocationCallback() {
                public void onLocation(TSLocation var1) {
                    BackgroundGeolocation.getUiHandler().post(new Runnable() {
                        public void run() {
                            ChangePaceTask.this.b.onSuccess();
                        }
                    });
                }

                public void onError(final Integer var1) {
                    BackgroundGeolocation.getUiHandler().post(new Runnable() {
                        public void run() {
                            ChangePaceTask.this.b.onFailure(var1.toString());
                        }
                    });
                }
            };
            if (TSConfig.getInstance(BackgroundGeolocation.this.c).isLocationTrackingMode()) {
                TrackingService.changePace(BackgroundGeolocation.this.c, this.a, var1);
            } else {
                GeofencingService.changePace(BackgroundGeolocation.this.c, this.a, var1);
            }

        }
    }

    private class z0 implements Runnable {
        private final String a;
        private final TSCallback b;

        z0(String var2, TSCallback var3) {
            this.a = var2;
            this.b = var3;
        }

        private void a(final String var1) {
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    z0.this.b.onFailure(var1);
                }
            });
        }

        private void b() {
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    z0.this.b.onSuccess();
                }
            });
        }

        public TSCallback a() {
            return this.b;
        }

        public void run() {
            TSConfig var1 = TSConfig.getInstance(BackgroundGeolocation.this.c);
            if (!com.transistorsoft.locationmanager.d.b.e(BackgroundGeolocation.this.c)) {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        z0.this.b.onFailure(Application.B("\uf8a2\ue5be????????????????????????\ue1ef???????????\ue59d??????????????\udd32????????????"));
                    }
                });
            } else {
                // TODO: 2022/10/11
                /// Whether the plugin is in the location-tracking mode ([BackgroundGeolocation.start] or geofences-only mode ([BackgroundGeolocation.startGeofences]).
                /// - `1` = Location + Geofence tracking (ie: [BackgroundGeolocation.start]).
                /// - `0` = Geofences-only tracking (ie: [BackgroundGeolocation.startGeofences]).
                ///
                // late int trackingMode;
                int var2 = this.a.equalsIgnoreCase(Application.B("???????????????")) ? 0 : 1;
                boolean var10000 = (var2 != var1.getTrackingMode());
                var1.setTrackingMode(Integer.valueOf(var2));
                Boolean var3 = var1.getEnabled();
                Log.i(Application.B("??????????????????\ue017???\udae3??????\uf8bb????????????\uf474"), Application.B("??????????????????\ue00f???\udab6???") + var3 + Application.B("?????????") + true + Application.B("??????????????????\ue008???\udae2??????\uf8b5????????????") + var1.getTrackingMode());
                TSLocationCallback var4;
                var4 = new TSLocationCallback() {
                    public void onLocation(TSLocation var1) {
                        BackgroundGeolocation.this.h = null;
                    }

                    public void onError(Integer var1) {
                        BackgroundGeolocation.this.h = null;
                    }
                };
                if (var10000 && var3) {
                    TrackingService.changeTrackingMode(BackgroundGeolocation.this.c, var2, var4);
                } else {
                    TrackingService.start(BackgroundGeolocation.this.c, var4);
                }

                this.b();
            }
        }
    }

    private class w0 implements UncaughtExceptionHandler {
        private UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

        w0() {
        }

        public void uncaughtException(Thread var1, Throwable var2) {
            TSConfig var3;
            if ((var3 = TSConfig.getInstance(BackgroundGeolocation.this.c)).getIsMoving()) {
                TSLocationManager.getInstance(BackgroundGeolocation.this.c).stopUpdatingLocation();
            }

            BackgroundGeolocation.w0 var10000 = this;
            String var4 = Application.B("?????????\u19ac??????????????????????????\ue926??????\ue049?????????") + var2.getMessage();
            TSLog.logger.error(TSLog.error(var4 + Application.B("???") + var3.toJson().toString() + Application.B("???")), var2);
            UncaughtExceptionHandler var5;
            if ((var5 = var10000.a) != null) {
                var5.uncaughtException(var1, var2);
            } else {
                System.exit(2);
            }

        }
    }
}
