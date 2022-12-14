//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.b.a;
import com.transistorsoft.locationmanager.config.TSAuthorization;
import com.transistorsoft.locationmanager.config.TSBackgroundPermissionRationale;
import com.transistorsoft.locationmanager.config.TSNotification;
import com.transistorsoft.locationmanager.device.DeviceInfo;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.service.ForegroundNotification;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.locationmanager.util.Sensors;
import com.transistorsoft.tslocationmanager.Application;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.http.HttpResponse;

public class TSConfig {
    private static TSConfig q;
    private static String r = Application.B("???\uf1d0?????????????????????\u0dcc??????\uf6ee???????????????????????????????????????");
    public static final Float MAXIMUM_LOCATION_ACCURACY = 100.0F;
    public static final int TRACKING_MODE_GEOFENCE = 0;
    public static final int TRACKING_MODE_LOCATION = 1;
    public static final float DEFAULT_DISTANCE_FILTER = 10.0F;
    public static final int DEFAULT_DESIRED_ACCURACY = 100;
    public static final long DEFAULT_LOCATION_UPDATE_INTERVAL = 1000L;
    public static final long DEFAULT_FASTEST_LOCATION_UPDATE_INTERVAL = -1L;
    public static final long DEFAULT_GEOFENCE_PROXIMITY_RADIUS = 1000L;
    public static final boolean DEFAULT_GEOFENCE_INITIAL_TRIGGER_ENTRY = true;
    public static final int DEFAULT_LOCATION_TIMEOUT = 60;
    public static final boolean DEFAULT_DISABLE_ELASTICITY = false;
    public static final float DEFAULT_ELASTICITY_MULTIPLIER = 1.0F;
    public static final long DEFAULT_DEFER_TIME = 0L;
    public static final int DEFAULT_STATIONARY_RADIUS = 25;
    public static final int MINIMUM_STATIONARY_RADIUS = 25;
    public static final long DEFAULT_STOP_TIMEOUT = 5L;
    public static final float DEFAULT_DESIRED_ODOMETER_ACCURACY = 100.0F;
    public static final boolean DEFAULT_ALLOW_IDENTICAL_LOCATIONS = false;
    public static final int DEFAULT_SPEED_JUMP_FILTER = 300;
    public static final long DEFAULT_ACTIVITY_RECOGNITION_INTERVAL = 10000L;
    public static final int DEFAULT_MINIMUM_ACTIVITY_RECOGNITION_CONFIDENCE = 75;
    public static final String DEFAULT_TRIGGER_ACTIVITIES = Application.B("???\uf1ed?????????????????????\u0dc7??????\uf6ef????????????????????????????????????????????????\uf248???????????????????????????\uedb0???????????????????????????");
    public static final boolean DEFAULT_DISABLE_STOP_DETECTION = false;
    public static final int DEFAULT_STOP_AFTER_ELAPSED_MINUTES = 0;
    public static final boolean DEFAULT_STOP_ON_STATIONARY = false;
    public static final int DEFAULT_MAX_DAYS_TO_PERSIST = 1;
    public static final int DEFAULT_MAX_RECORDS_TO_PERSIST = -1;
    public static final String DEFAULT_URL = "";
    public static final String DEFAULT_HTTP_METHOD = Application.B("???\uf1cc??????");
    public static final boolean DEFAULT_AUTO_SYNC = true;
    public static final Integer DEFAULT_AUTO_SYNC_THRESHOLD = 0;
    public static final boolean DEFAULT_BATCH_SYNC = false;
    public static final int DEFAULT_MAX_BATCH_SIZE = -1;
    public static final String DEFAULT_HTTP_ROOT_PROPERTY = Application.B("???\uf1ec??????????????????");
    public static final String DEFAULT_LOCATION_TEMPLATE = "";
    public static final String DEFAULT_GEOFENCE_TEMPLATE = "";
    public static final String DEFAULT_LOCATIONS_ORDER_DIRECTION = Application.B("???\uf1d0???");
    public static final int DEFAULT_HTTP_TIMEOUT = 60000;
    public static final boolean DEFAULT_DEBUG = false;
    public static final boolean DEFAULT_STOP_ON_TERMINATE = true;
    public static final boolean DEFAULT_START_ON_BOOT = false;
    public static final int DEFAULT_HEARTBEAT_INTERVAL = -1;
    public static final boolean DEFAULT_FOREGROUND_SERVICE = true;
    public static final String DEFAULT_MAIN_ACTIVITY_NAME = null;
    public static final ArrayList<String> DEFAULT_SCHEDULE = new ArrayList();
    public static final int DEFAULT_LOG_LEVEL = 0;
    public static final int DEFAULT_LOG_MAX_DAYS = 3;
    public static final int PERSIST_MODE_ALL = 2;
    public static final int PERSIST_MODE_NONE = 0;
    public static final int PERSIST_MODE_LOCATION = 1;
    public static final int PERSIST_MODE_GEOFENCE = -1;
    private static AtomicBoolean s = new AtomicBoolean(false);
    private TSConfig.Builder a;
    private Context b;
    private AtomicBoolean c = new AtomicBoolean(false);
    private Boolean d;
    private Integer e;
    private Boolean f;
    private Boolean g;
    private Float h;
    private Boolean i;
    private boolean j;
    private Boolean k;
    private Boolean l;
    private String m;
    private JSONObject n;
    private ArrayList<Integer> o;
    private final Map<String, ArrayList<TSConfig.OnChangeCallback>> p = new HashMap();

    public static boolean isLoaded() {
        return s.get();
    }

    public static TSConfig getInstance(Context var0) {
        if (q == null) {
            if (var0.getApplicationContext() != null) {
                var0 = var0.getApplicationContext();
            }

            q = a(var0);
        }

        return q;
    }

    private static synchronized TSConfig a(Context var0) {
        if (q == null) {
            q = new TSConfig(var0);
        }

        return q;
    }

    public TSConfig(Context var1) {
        this.b = var1;
        this.i = true;
        this.k = false;
        this.d = false;
        this.f = false;
        this.e = 1;
        this.j = false;
        this.h = 0.0F;
        this.l = false;
        this.m = DEFAULT_MAIN_ACTIVITY_NAME;
        this.n = new JSONObject();
        com.transistorsoft.locationmanager.d.b.f(var1);
        this.load();
        TSConfig.Builder var2;
        if ((var2 = this.a) != null) {
            this.o = this.a(var2.triggerActivities);
        }

    }

    private void b() {
        TSConfig tSConfig = this;
        List list = tSConfig.a.dirtyFields;
        synchronized (list) {
            if (!com.transistorsoft.locationmanager.d.b.e(tSConfig.b)) {
                this.a.dirtyFields.clear();
                return;
            }
            if (this.a.dirtyFields.isEmpty()) {
                return;
            }
            if (this.isDirty(Application.B("????????????????????????"))) {
                TSLog.setLogLevel(this.a.logLevel);
            }
            if (this.isDirty(Application.B("??????????????????????????????"))) {
                TSLog.setMaxHistory(this.a.logMaxDays);
            }
            if (this.isDirty(Application.B("??????????????????????????????????????????\udc33???????????????\ue80f?????????"))) {
                ForegroundNotification.createNotificationChannel(this.b);
            }
            if (this.isDirty(Application.B("???????????????????????????")) && !this.a.configUrl.isEmpty()) {
                this.loadConfig(new TSCallback() {

                    @Override
                    public void onSuccess() {
                        TSLog.logger.debug(TSLog.ok(Application.B("?????????\udbd7\ue9ed???\ue141???????????????\uef02\uf7a5???\ue77a??????")));
                    }

                    @Override
                    public void onFailure(String string) {
                        TSLog.logger.warn(TSLog.warn(string));
                    }
                });
            }
            TSConfig tSConfig2 = this;
            tSConfig2.d();
            EventBus.getDefault().post((Object) new ConfigChangeEvent(this.b, this.a.dirtyFields));
            for (String string : tSConfig2.a.dirtyFields) {
                if (string.equalsIgnoreCase(Application.B("??????????????????????????????????????????\udc32??????"))) {
                    TSConfig tSConfig3 = this;
                    tSConfig3.o = tSConfig3.a(tSConfig3.a.triggerActivities);
                }
                this.b(string);
            }
            this.a.dirtyFields.clear();
            return;
        }
    }

    private void d() {
        List list = this.a.dirtyFields;
        synchronized (list) {
            TSLog.logger.debug(Application.B("?????????\udba4??????\uea37??????????????????\udeb6???????????????\ud9cb\ue01e?????????\udcbd?????????") + this.a.dirtyFields.toString());
            SharedPreferences.Editor editor = this.b.getSharedPreferences(r, 0).edit();
            SharedPreferences.Editor editor2 = editor;
            editor.putString(Builder.class.getName(), this.a.b(false).toString());
            editor2.apply();
            return;
        }
    }

    private void a(String var1, Object var2) {
        Editor var3 = this.b.getSharedPreferences(r, 0).edit();
        if (var2 != null) {
            Class var4;
            if ((var4 = var2.getClass()) == Boolean.class) {
                var3.putBoolean(var1, (Boolean) var2);
            } else if (var4 == String.class) {
                var3.putString(var1, (String) var2);
            } else if (var4 == Double.class) {
                var3.putLong(var1, Double.doubleToRawLongBits((Double) var2));
            } else if (var4 == Integer.class) {
                var3.putInt(var1, (Integer) var2);
            } else if (var4 == Float.class) {
                var3.putFloat(var1, (Float) var2);
            } else if (var4 == JSONObject.class) {
                var3.putString(var1, var2.toString());
            } else {
                TSLog.logger.error(TSLog.error(Application.B("?????????????????????\ue66b????????????\udd76?????????????????????") + var4.getName()));
                TSMediaPlayer.getInstance().debug(this.b, Application.B("?????????????????????\ue622????????????\udd7d??????????????????\u1cb0\ue2f7??????????????????????????????\ueae2???\u0d11???\uf181?????????\uf086"));
            }
        } else {
            var3.remove(var1);
        }

        var3.apply();
    }

    private ArrayList<Integer> a(String var1) {
        String var10000 = var1;
        ArrayList var4;
        var4 = new ArrayList();
        Iterator var2 = Arrays.asList(var10000.replaceAll(Application.B("?????????"), "").split(Application.B("???"))).iterator();

        while (var2.hasNext()) {
            String var3;
            if ((var3 = (String) var2.next()).equalsIgnoreCase(Application.B("?????????????????????\ue628??????"))) {
                var4.add(0);
            } else if (var3.equalsIgnoreCase(Application.B("?????????????????????\ue628??????"))) {
                var4.add(1);
            } else if (var3.equalsIgnoreCase(Application.B("?????????????????????"))) {
                var4.add(2);
            } else if (var3.equalsIgnoreCase(Application.B("?????????????????????"))) {
                var4.add(8);
            } else if (var3.equalsIgnoreCase(Application.B("?????????????????????"))) {
                var4.add(7);
            } else {
                TSLog.logger.warn(TSLog.error(Application.B("?????????????????????\ue619??????\u0e8f???\udd41??????????????????\u1cae\ue2ea????????????\u4db8???????????????\ueac9?????????\uf18f?????????\uf0de\uf79d??????\uee2a??????f\ue735?????????\uf637???\ud914???????????????????????") + var3));
                TSMediaPlayer.getInstance().debug(this.b, Application.B("?????????????????????\ue622????????????\udd7d??????????????????\u1cac\ue2e3??????????????????"));
            }
        }

        return var4;
    }

    private void b(String var1) {
        if (this.p.containsKey(var1)) {
            Iterator var2 = ((ArrayList) this.p.get(var1)).iterator();

            while (var2.hasNext()) {
                ((TSConfig.OnChangeCallback) var2.next()).a(this);
            }
        }

    }

    private void c() {
        (new TSConfig.c()).execute(new Void[0]);
    }

    public TSConfig.Builder updateWithBuilder() {
        return this.a.b();
    }

    public void updateWithJSONObject(JSONObject var1) {
        try {
            this.a.a(var1);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        this.b();
    }

    public JSONObject toJson(boolean var1) {
        JSONObject var12;
        JSONObject var10000 = var12 = this.a.b(var1);
        TSConfig var10001 = this;
        JSONObject var10002 = var12;
        TSConfig var10003 = this;
        JSONObject var10004 = var12;
        TSConfig var10005 = this;
        JSONObject var10006 = var12;
        JSONObject var10007 = var12;
        TSConfig var10008 = this;
        JSONObject var10009 = var12;
        TSConfig var10010 = this;
        JSONObject var10011 = var12;
        JSONObject var10012 = var12;
        String var10013 = Application.B("????????????????????");

        JSONException var14;
        label77:
        {
            boolean var15;
            try {
                var10012.put(var10013, this.getEnabled());
            } catch (JSONException var10) {
                var14 = var10;
                var15 = false;
                break label77;
            }

            String var17 = Application.B("????????????????????\ue03e??????\uf468???????????????");

            try {
                var10011.put(var17, this.getSchedulerEnabled());
            } catch (JSONException var9) {
                var14 = var9;
                var15 = false;
                break label77;
            }

            String var2 = Application.B("???????????????\u1fff??\ue03a??????\uf468???????????????");

            try {
                var10009.put(var2, var10010.g);
            } catch (JSONException var8) {
                var14 = var8;
                var15 = false;
                break label77;
            }

            var2 = Application.B("????????????????????\ue03c??????\uf462???");

            try {
                var10007.put(var2, var10008.getTrackingMode());
            } catch (JSONException var7) {
                var14 = var7;
                var15 = false;
                break label77;
            }

            String var16 = Application.B("????????????????????\ue029");

            try {
                var10006.put(var16, this.getOdometer().doubleValue());
            } catch (JSONException var6) {
                var14 = var6;
                var15 = false;
                break label77;
            }

            String var11 = Application.B("????????????????????\ue019\ufefe???\uf472");

            try {
                var10004.put(var11, var10005.i);
            } catch (JSONException var5) {
                var14 = var5;
                var15 = false;
                break label77;
            }

            var11 = Application.B("????????????????????\ue038??????\uf468??????????????????????????????");

            try {
                var10002.put(var11, var10003.k);
            } catch (JSONException var4) {
                var14 = var4;
                var15 = false;
                break label77;
            }

            var11 = Application.B("????????????????????\ue038??????\uf463????????????");

            try {
                var10000.put(var11, var10001.j);
                return var12;
            } catch (JSONException var3) {
                var14 = var3;
                var15 = false;
            }
        }

        JSONException var13 = var14;
        TSLog.logger.error(TSLog.error(var13.getMessage()), var13);
        var13.printStackTrace();
        return var12;
    }

    public JSONObject toJson() {
        return this.toJson(false);
    }

    public void onChange(String var1, TSConfig.OnChangeCallback var2) {
        if (!this.p.containsKey(var1)) {
            Map var10000 = this.p;
            ArrayList var3;
            var3 = new ArrayList();
            var10000.put(var1, var3);
        }

        ((ArrayList) this.p.get(var1)).add(var2);
    }

    public boolean removeListener(String string, OnChangeCallback onChangeCallback) {
        TSConfig tSConfig = this;
        ArrayList<TSConfig.OnChangeCallback> list = this.p.get(string);
        boolean bl = false;
        Map<String, ArrayList<OnChangeCallback>> map = tSConfig.p;
        synchronized (map) {
            block7:
            {
                Object object;
                block8:
                {
                    if (!tSConfig.p.containsKey(string)) break block7;
                    string = null;
                    Iterator iterator = list.iterator();
                    while (iterator.hasNext()) {
                        object = (OnChangeCallback) iterator.next();
                        if (!object.equals(onChangeCallback)) continue;
                        break block8;
                    }
                    object = string;
                }
                if (object == null) break block7;
                list.remove(object);
                bl = true;
            }
            return bl;
        }
    }

    public void removeListeners() {
        this.p.clear();
    }

    public boolean isDirty(String var1) {
        TSConfig var10000 = this;
        synchronized (this.a.dirtyFields) {
            return var10000.a.dirtyFields.contains(var1);
        }
    }

    public void reset(boolean var1) {
        this.a.a(var1);
    }

    public void reset() {
        this.reset(false);
    }

    public Boolean isFirstBoot() {
        return this.i;
    }

    public Boolean getEnabled() {
        return this.d;
    }

    public void setEnabled(Boolean var1) {
        this.setEnabled(var1, false);
    }

    public void setEnabled(Boolean var1, boolean var2) {
        if (this.d != var1) {
            this.d = var1;
            this.a(Application.B("???????????????\ue857???"), var1);
            if (!var2) {
                this.b(Application.B("???????????????\ue857???"));
            }
        }

    }

    public Integer getTrackingMode() {
        return this.e;
    }

    public void setTrackingMode(Integer var1) {
        if (!this.e.equals(var1)) {
            this.e = var1;
            this.a(Application.B("????????????????????????\ud96d?????????"), var1);
        }

    }

    public boolean isLocationTrackingMode() {
        return this.e == 1;
    }

    public Boolean getSchedulerEnabled() {
        return this.f;
    }

    public void setSchedulerEnabled(Boolean var1) {
        if (this.f != var1) {
            this.f = var1;
            this.a(Application.B("\uf4fd?????????????????????????????\uefb5????????????"), var1);
        }

    }

    public void setDidDeviceReboot(boolean var1) {
        if (this.j != var1) {
            this.j = var1;
            this.a(Application.B("???????????????\udbf9???????????????????????????"), var1);
        }

    }

    public Boolean getDidDeviceReboot() {
        return this.j;
    }

    public Float getOdometer() {
        return this.h;
    }

    public void setOdometer(Float var1) {
        if (!this.h.equals(var1)) {
            this.h = var1;
            this.a(Application.B("???\u1cb5????????\ue08c?????"), var1);
        }

    }

    public Float incrementOdometer(Float var1) {
        TSConfig var10000 = this;
        TSConfig var10001 = this;
        Float var2;
        this.h = var2 = this.h + var1;
        var10001.a(Application.B("\uf71a\uf134??????????????????"), var2);
        return var10000.h;
    }

    public void useCLLocationAccuracy(Boolean var1) {
        if (this.l != var1) {
            this.a(Application.B("\u0df1????????????\ueda9?????????\ue962????????????\uf3c8?????????????????"), var1);
            TSConfig.Builder var2 = this.a;
            var2.desiredAccuracy = var1 ? 0 : 100;
        }

        this.l = var1;
    }

    public void setMainActivityName(String var1) {
        if (this.m != var1) {
            this.a(Application.B("?????????????????????\ueaba?????????\u0a0d????????????"), var1);
        }

        this.m = var1;
    }

    public String getMainActivityName() {
        return this.m;
    }

    public Boolean getIsMoving() {
        return this.a.isMoving;
    }

    public void setIsMoving(Boolean var1) {
        this.a.setIsMoving(var1);
        this.b();
    }

    public Float getDistanceFilter() {
        return this.a.distanceFilter;
    }

    public float calculateDistanceFilter(float var1) {
        if (!(var1 <= 0.0F) && !this.getDisableElasticity()) {
            if ((var1 = (float) (Math.floor((double) var1 / 5.0D + 0.5D) * 5.0D / 5.0D)) < 0.0F) {
                var1 = 0.0F;
            }

            return this.a.distanceFilter + this.a.distanceFilter * this.a.elasticityMultiplier * var1;
        } else {
            return this.a.distanceFilter;
        }
    }

    public Integer getDesiredAccuracy() {
        return this.translateDesiredAccuracy(this.a.desiredAccuracy);
    }

    public Float getDesiredOdometerAccuracy() {
        return this.a.desiredOdometerAccuracy;
    }

    public Long getLocationUpdateInterval() {
        return this.a.locationUpdateInterval;
    }

    public Long getFastestLocationUpdateInterval() {
        return this.a.fastestLocationUpdateInterval;
    }

    public Integer getLocationTimeout() {
        return this.a.locationTimeout;
    }

    public Long getDeferTime() {
        return this.a.deferTime;
    }

    public Boolean getDisableElasticity() {
        return this.a.disableElasticity;
    }

    public Float getElasticityMultiplier() {
        return this.a.elasticityMultiplier;
    }

    public Boolean getAllowIdenticalLocations() {
        return this.a.allowIdenticalLocations;
    }

    public Boolean getEnableTimestampMeta() {
        return this.a.enableTimestampMeta;
    }

    public Integer getSpeedJumpFilter() {
        return this.a.speedJumpFilter;
    }

    public Boolean getUseSignificantChangesOnly() {
        return this.a.useSignificantChangesOnly;
    }

    public String getLocationAuthorizationRequest() {
        return this.a.locationAuthorizationRequest;
    }

    public boolean requestsLocationAlways() {
        return this.a.locationAuthorizationRequest.equalsIgnoreCase(Application.B("??????????????????"));
    }

    public Boolean getDisableLocationAuthorizationAlert() {
        return this.a.disableLocationAuthorizationAlert;
    }

    public Long getActivityRecognitionInterval() {
        return this.a.activityRecognitionInterval;
    }

    public Integer getMinimumActivityRecognitionConfidence() {
        return this.a.minimumActivityRecognitionConfidence;
    }

    public String getTriggerActivities() {
        return this.a.triggerActivities;
    }

    public boolean hasTriggerActivity(int var1) {
        return this.o.contains(var1);
    }

    public Boolean getDisableStopDetection() {
        return this.a.disableStopDetection;
    }

    public Boolean getDisableMotionActivityUpdates() {
        return this.a.disableMotionActivityUpdates;
    }

    public Integer getStationaryRadius() {
        return this.a.stationaryRadius;
    }

    public Long getStopTimeout() {
        return this.a.stopTimeout;
    }

    public Boolean getStopOnStationary() {
        return this.a.stopOnStationary;
    }

    public Long getMotionTriggerDelay() {
        return this.a.motionTriggerDelay;
    }

    public Boolean getPersist() {
        return this.a.persist;
    }

    public Integer getPersistMode() {
        return this.a.persistMode;
    }

    public Integer getMaxDaysToPersist() {
        return this.a.maxDaysToPersist;
    }

    public Integer getMaxRecordsToPersist() {
        return this.a.maxRecordsToPersist;
    }

    public String getUrl() {
        return this.a.url;
    }

    public boolean hasUrl() {
        return this.a.url != null && !this.a.url.isEmpty();
    }

    public boolean shouldPersist(TSLocation var1) {
        if (this.a.persist && this.a.maxRecordsToPersist != 0) {
            int var2;
            if ((var2 = this.getPersistMode()) != 2) {
                if (var2 == 0) {
                    return false;
                } else if (var1.getGeofence() != null) {
                    return var2 == -1;
                } else {
                    return var2 == 1;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public String getMethod() {
        return this.a.method;
    }

    public Boolean getAutoSync() {
        return this.a.autoSync;
    }

    public Integer getAutoSyncThreshold() {
        return this.a.autoSyncThreshold;
    }

    public Boolean getBatchSync() {
        return this.a.batchSync;
    }

    public Integer getMaxBatchSize() {
        return this.a.maxBatchSize;
    }

    public JSONObject getParams() {
        return this.a.params;
    }

    public JSONObject getHeaders() {
        return this.a.headers;
    }

    public JSONObject getExtras() {
        return this.a.extras;
    }

    public String getHttpRootProperty() {
        return this.a.httpRootProperty;
    }

    public boolean hasLocationTemplate() {
        return this.a.locationTemplate.isEmpty() ^ true;
    }

    public String getLocationTemplate() {
        return this.a.locationTemplate;
    }

    public boolean hasGeofenceTemplate() {
        return this.a.geofenceTemplate.isEmpty() ^ true;
    }

    public String getGeofenceTemplate() {
        return this.a.geofenceTemplate;
    }

    public String getLocationsOrderDirection() {
        return this.a.locationsOrderDirection;
    }

    public Integer getHttpTimeout() {
        return this.a.httpTimeout;
    }

    public boolean getDisableAutoSyncOnCellular() {
        return this.a.disableAutoSyncOnCellular;
    }

    public TSAuthorization getAuthorization() {
        return this.a.authorization;
    }

    public Long getGeofenceProximityRadius() {
        return this.a.geofenceProximityRadius;
    }

    public Boolean getGeofenceInitialTriggerEntry() {
        return this.a.geofenceInitialTriggerEntry;
    }

    public Boolean getGeofenceModeHighAccuracy() {
        return this.a.geofenceModeHighAccuracy;
    }

    public Boolean getStopOnTerminate() {
        return this.a.stopOnTerminate;
    }

    public Boolean getStartOnBoot() {
        return this.a.startOnBoot;
    }

    public Integer getStopAfterElapsedMinutes() {
        return this.a.stopAfterElapsedMinutes;
    }

    public Integer getHeartbeatInterval() {
        return this.a.heartbeatInterval;
    }

    public Boolean getForegroundService() {
        return this.a.foregroundService;
    }

    public TSNotification getNotification() {
        return this.a.notification;
    }

    public TSBackgroundPermissionRationale getBackgroundPermissionRationale() {
        return this.a.backgroundPermissionRationale;
    }

    public String getConfigUrl() {
        return this.a.configUrl;
    }

    public List<String> getSchedule() {
        return this.a.schedule;
    }

    public Boolean getScheduleUseAlarmManager() {
        return this.a.scheduleUseAlarmManager;
    }

    public boolean hasSchedule() {
        return this.a.schedule.size() > 0;
    }

    public String getHeadlessJobService() {
        return this.a.headlessJobService;
    }

    public boolean hasHeadlessJobService() {
        return this.a.headlessJobService.isEmpty() ^ true;
    }

    public Boolean getEnableHeadless() {
        return this.a.enableHeadless;
    }

    public Boolean getDebug() {
        return this.a.debug;
    }

    public Integer getLogLevel() {
        return this.a.logLevel;
    }

    public Integer getLogMaxDays() {
        return this.a.logMaxDays;
    }

    public void load() {
        SharedPreferences var1;
        if (!(var1 = this.b.getSharedPreferences(r, 0)).contains(TSConfig.Builder.class.getName())) {
            this.i = true;
            this.a = new TSConfig.Builder();
            TSLog.initialize(this.getLogLevel(), this.getLogMaxDays());
            this.d();
            s.set(true);
        } else {
            TSConfig var10000 = this;
            this.l = var1.getBoolean(Application.B("???????????????????????????????????????\uf3b5\ue80d\ue07b?????\uef8b??????"), false);
            this.m = var1.getString(Application.B("?????????????????????????????????\u0be5???\uf395\ue803\ue07d"), (String) null);
            this.d = var1.getBoolean(Application.B("?????????????????????"), false);
            this.f = var1.getBoolean(Application.B("?????????\u098d?????????????????????\u0bfd???\uf398\ue80b\ue07c"), false);
            this.e = var1.getInt(Application.B("????????????????????????????????????"), 1);
            this.h = var1.getFloat(Application.B("???????????????\u2d26??????"), 0.0F);

            JSONObject var2;
            try {
                var10000.n = new JSONObject(var1.getString(Application.B("?????????????????????"), Application.B("??????")));
            } catch (JSONException var3) {
                var2 = new JSONObject();
                this.n = var2;
            }

            String var7;
            TSConfig var10001;
            if ((var7 = var1.getString(TSConfig.Builder.class.getName(), (String) null)) != null) {
                label40:
                {
                    var10000 = this;
                    var10001 = this;
                    this.i = true;

                    JSONException var10;
                    label41:
                    {
                        boolean var11;
                        JSONObject var10002;
                        var10002 = new JSONObject();

                        var2 = var10002;

                        //                            var10002.<init>(var7);
                        var10001.a = new Builder(var2);
                        var10000.i = false;
                        s.set(true);
                        break label40;
                    }
                }
            } else {
                TSConfig.Builder var9;
                var9 = new TSConfig.Builder();
                this.a = var9;
                this.d();
            }

            var10000 = this;
            var10001 = this;
            TSConfig var12 = this;
            TSLog.initialize(this.getLogLevel(), this.getLogMaxDays());
            a var10003 = com.transistorsoft.locationmanager.b.a.a();
            TSConfig var10004 = this;
            Context var6 = this.b;
            var10003.a(var6, var10004.n);
            var12.print();
            Sensors.getInstance(var10001.b).print();
            var10000.c();
        }
    }

    public void print() {
        StringBuilder var1;
        StringBuilder var10000 = var1 = new StringBuilder();
        TSConfig var10001 = this;
        var1.append(TSLog.header(Application.B("???????????????????????????\u2cf7???\ueacb?????????????????????????????????????????????\ue171\uf859\u0fdb?????????????????????\ue19f")));
        var1.append(TSLog.boxRow(DeviceInfo.getInstance(this.b).print()));

        try {
            var10000.append(var10001.toJson(true).toString(2));
        } catch (JSONException var2) {
            Log.i(Application.B("???????????????????????????\u2cf7???\ueacb???????????????"), TSLog.error(var2.getMessage()));
            var2.printStackTrace();
        }

        TSLog.logger.info(var1.toString());
        var10000 = var1 = new StringBuilder();
        var10000.append(Sensors.getInstance(this.b).print().toString());
        TSLog.logger.info(var1.toString());
    }

    public Integer translateDesiredAccuracy(Integer var1) {
        TSLog.logger.debug(Application.B("???????????????\uefe4?????????????????????\uf858?????????????????????\ueea8????????????") + this.l + Application.B("\u17ee??????") + var1);
        if (!this.l) {
            return var1;
        } else {
            Integer var10000;
            int var2;
            if ((var2 = var1) != -2 && var2 != -1 && var2 != 0) {
                if (var2 != 10) {
                    if (var2 == 100) {
                        var10000 = 104;
                        return var10000;
                    }

                    if (var2 == 1000 || var2 == 3000) {
                        var10000 = 105;
                        return var10000;
                    }
                }

                var10000 = 102;
            } else {
                var10000 = 100;
            }

            return var10000;
        }
    }

    public void setPluginForEvent(int param1, String param2) {
        // $FF: Couldn't be decompiled
    }

    public int getPluginForEvent(String param1) {
        // $FF: Couldn't be decompiled
        return 1;
    }

    public void loadConfig(final TSCallback var1) {
        if (!this.a.configUrl.isEmpty()) {
            OkHttpClient var2 = HttpService.getInstance(this.b).getClient();
            okhttp3.Request.Builder var3 = (new okhttp3.Request.Builder()).url(this.a.configUrl).get();
            JSONObject var4;
            if ((var4 = this.a.headers) != null) {
                Iterator var5 = var4.keys();

                label52:
                while (true) {
                    JSONException var10000;
                    while (true) {
                        boolean var12;
                        boolean var10001;
                        var12 = var5.hasNext();

                        if (!var12) {
                            break label52;
                        }

                        String var13;
                        var13 = (String) var5.next();

                        String var6 = var13;

                        var12 = var13.equalsIgnoreCase(Application.B("????????????????????????????????????"));

                        if (!var12) {
                            try {
                                var3.header(var6, var4.getString(var6));
                            } catch (JSONException var7) {
                                var10000 = var7;
                                var10001 = false;
                                break;
                            }
                        }
                    }

                    JSONException var11 = var10000;
                    var1.onFailure(Application.B("??????\ud7c8????????????????????????????????????????????????????????????") + var11.getMessage());
                    return;
                }
            }

            var2.newCall(var3.build()).enqueue(new Callback() {
                public void onFailure(@NotNull Call var1x, IOException var2) {
                    var1.onFailure(Application.B("???????????????????????????????????????\uf5e1????????????????????????\u2e77?????????") + var2.getMessage());
                }

                public void onResponse(@NotNull Call param1, @NotNull Response param2) {
                    // $FF: Couldn't be decompiled
                }
            });
        }
    }

    private class c extends AsyncTask<Void, Void, Void> {
        private c() {
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        protected Void a(Void... var1) {
            ForegroundNotification.createNotificationChannel(TSConfig.this.b);
            LifecycleManager.f().a(new com.transistorsoft.locationmanager.lifecycle.LifecycleManager.b() {
                public void a(boolean var1) {
                    if (TSConfig.this.d && TSConfig.this.getStopOnTerminate()) {
                        TSConfig.this.setEnabled(false);
                        TSLog.logger.info(TSLog.warn(Application.B("????????????\u070f???????????????????????????\ue476?????????\u0c77???????????????????????????????????????\ueaa0??????\ud8ec\udd22?????????\ue13b????????????????????????\uefec????????????\udffd???\uf4a3???????????\udf78???[??????\ud885???\ue91c????????????????????????")));
                        TrackingService.stop(TSConfig.this.b);
                        if (var1) {
                            BackgroundGeolocation.getInstance(TSConfig.this.b).onActivityDestroy();
                        }
                    }

                }
            });
            return null;
        }
    }

    public interface OnChangeCallback {
        void a(TSConfig var1);
    }

    public static class Builder {
        private static final Set<String> IGNORED_FIELDS = new HashSet(Arrays.asList(Application.B("???????????????\udbc4???\ude32?????????")));
        private final List<String> dirtyFields = new ArrayList<>();
        private Boolean isMoving;
        private Float distanceFilter;
        private Integer desiredAccuracy;
        private Float desiredOdometerAccuracy;
        private Long locationUpdateInterval;
        private Long fastestLocationUpdateInterval;
        private Integer locationTimeout;
        private Long deferTime;
        private Boolean disableElasticity;
        private Float elasticityMultiplier;
        private Boolean allowIdenticalLocations;
        private Boolean enableTimestampMeta;
        private Integer speedJumpFilter;
        private Boolean useSignificantChangesOnly;
        private Boolean disableLocationAuthorizationAlert;
        private String locationAuthorizationRequest;
        private Long activityRecognitionInterval;
        private Integer minimumActivityRecognitionConfidence;
        private String triggerActivities;
        private Boolean disableStopDetection;
        private Boolean disableMotionActivityUpdates;
        private Integer stationaryRadius;
        private Long stopTimeout;
        private Boolean stopOnStationary;
        private Long motionTriggerDelay;
        private Boolean persist;
        private Integer persistMode;
        private Integer maxDaysToPersist;
        private Integer maxRecordsToPersist;
        private String url;
        private String method;
        private Boolean autoSync;
        private Integer autoSyncThreshold;
        private Boolean batchSync;
        private Integer maxBatchSize;
        private JSONObject params;
        private JSONObject headers;
        private JSONObject extras;
        private String httpRootProperty;
        private String locationTemplate;
        private String geofenceTemplate;
        private String locationsOrderDirection;
        private Integer httpTimeout;
        private Boolean disableAutoSyncOnCellular;
        private TSAuthorization authorization;
        private Long geofenceProximityRadius;
        private Boolean geofenceInitialTriggerEntry;
        private Boolean geofenceModeHighAccuracy;
        private Boolean stopOnTerminate;
        private Boolean startOnBoot;
        private Integer stopAfterElapsedMinutes;
        private Integer heartbeatInterval;
        private Boolean foregroundService;
        private TSNotification notification;
        private TSBackgroundPermissionRationale backgroundPermissionRationale;
        private String configUrl;
        private List<String> schedule;
        private Boolean scheduleUseAlarmManager;
        private String headlessJobService;
        private Boolean enableHeadless;
        private Boolean debug;
        private Integer logLevel;
        private Integer logMaxDays;

        public Builder() {
            super();
            a();
        }

        public Builder(JSONObject var1) {
            this.a();
            Field[] var88;
            int var3 = (var88 = TSConfig.Builder.class.getDeclaredFields()).length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Field var5;
                Field var10001 = var5 = var88[var4];
                String var6 = var10001.getName();
                Class var7 = var10001.getType();
                if (var1.has(var6) && this.a(var5)) {
                    JSONException var96;
                    label349:
                    {
                        IllegalAccessException var10000;
                        label348:
                        {
                            Class var97;
                            boolean var98;
                            Class var99;
                            var97 = var7;
                            var99 = Boolean.class;

                            if (var97 == var99) {
                                try {
                                    var5.set(this, var1.getBoolean(var6));
                                    continue;
                                } catch (JSONException var10) {
                                    var96 = var10;
                                    var98 = false;
                                    break label349;
                                } catch (IllegalAccessException var11) {
                                    var10000 = var11;
                                    var98 = false;
                                }
                            } else {
                                label344:
                                {
                                    var97 = var7;
                                    var99 = Long.class;

                                    if (var97 == var99) {
                                        try {
                                            var5.set(this, var1.getLong(var6));
                                            continue;
                                        } catch (JSONException var12) {
                                            var96 = var12;
                                            var98 = false;
                                            break label349;
                                        } catch (IllegalAccessException var13) {
                                            var10000 = var13;
                                            var98 = false;
                                        }
                                    } else {
                                        label340:
                                        {
                                            var97 = var7;
                                            var99 = Integer.class;

                                            if (var97 == var99) {
                                                try {
                                                    var5.set(this, var1.getInt(var6));
                                                    continue;
                                                } catch (JSONException var14) {
                                                    var96 = var14;
                                                    var98 = false;
                                                    break label349;
                                                } catch (IllegalAccessException var15) {
                                                    var10000 = var15;
                                                    var98 = false;
                                                }
                                            } else {
                                                label336:
                                                {
                                                    var97 = var7;
                                                    var99 = Float.class;

                                                    Field var103;
                                                    TSConfig.Builder var104;
                                                    if (var97 == var99) {
                                                        label356:
                                                        {
                                                            double var10002;
                                                            try {
                                                                var103 = var5;
                                                                var104 = this;
                                                                var10002 = var1.getDouble(var6);
                                                            } catch (JSONException var18) {
                                                                var96 = var18;
                                                                var98 = false;
                                                                break label349;
                                                            }

                                                            float var100 = (float) var10002;

                                                            try {
                                                                var103.set(var104, var100);
                                                                continue;
                                                            } catch (IllegalAccessException var17) {
                                                                var10000 = var17;
                                                                var98 = false;
                                                            }
                                                        }
                                                    } else {
                                                        label332:
                                                        {
                                                            var97 = var7;
                                                            var99 = Double.class;

                                                            if (var97 == var99) {
                                                                try {
                                                                    var5.set(this, var1.getDouble(var6));
                                                                    continue;
                                                                } catch (JSONException var20) {
                                                                    var96 = var20;
                                                                    var98 = false;
                                                                    break label349;
                                                                } catch (IllegalAccessException var21) {
                                                                    var10000 = var21;
                                                                    var98 = false;
                                                                }
                                                            } else {
                                                                label328:
                                                                {
                                                                    var97 = var7;
                                                                    var99 = JSONObject.class;

                                                                    if (var97 == var99) {
                                                                        try {
                                                                            var5.set(this, var1.getJSONObject(var6));
                                                                            continue;
                                                                        } catch (JSONException var22) {
                                                                            var96 = var22;
                                                                            var98 = false;
                                                                            break label349;
                                                                        } catch (IllegalAccessException var23) {
                                                                            var10000 = var23;
                                                                            var98 = false;
                                                                        }
                                                                    } else {
                                                                        label324:
                                                                        {
                                                                            var97 = var7;
                                                                            var99 = JSONArray.class;

                                                                            if (var97 == var99) {
                                                                                try {
                                                                                    var5.set(this, var1.getJSONArray(var6));
                                                                                    continue;
                                                                                } catch (JSONException var24) {
                                                                                    var96 = var24;
                                                                                    var98 = false;
                                                                                    break label349;
                                                                                } catch (IllegalAccessException var25) {
                                                                                    var10000 = var25;
                                                                                    var98 = false;
                                                                                }
                                                                            } else {
                                                                                label357:
                                                                                {
                                                                                    var97 = var7;
                                                                                    var99 = List.class;

                                                                                    Object var94;
                                                                                    if (var97 == var99) {
                                                                                        JSONArray var105;
                                                                                        try {
                                                                                            var105 = var1.getJSONArray(var6);
                                                                                        } catch (JSONException var68) {
                                                                                            var96 = var68;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        }

                                                                                        JSONArray var91 = var105;

                                                                                        ArrayList var106 = new ArrayList();

                                                                                        var94 = var106;

                                                                                        int var8 = 0;

                                                                                        while (true) {
                                                                                            int var107;
                                                                                            int var108;
                                                                                            var107 = var8;
                                                                                            var108 = var91.length();

                                                                                            if (var107 >= var108) {
                                                                                                break;
                                                                                            }

                                                                                            try {
                                                                                                ((List) var94).add(var91.getString(var8));
                                                                                            } catch (JSONException var62) {
                                                                                                var96 = var62;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            }

                                                                                            ++var8;
                                                                                        }
                                                                                    } else {
                                                                                        var97 = var7;
                                                                                        var99 = Map.class;

                                                                                        if (var97 != var99) {
                                                                                            var97 = var7;
                                                                                            var99 = TSNotification.class;

                                                                                            JSONObject var89;
                                                                                            JSONObject var102;
                                                                                            if (var97 == var99) {
                                                                                                try {
                                                                                                    var103 = var5;
                                                                                                    var104 = this;
                                                                                                    var102 = var1.getJSONObject(var6);
                                                                                                } catch (JSONException var30) {
                                                                                                    var96 = var30;
                                                                                                    var98 = false;
                                                                                                    break label349;
                                                                                                }

                                                                                                var89 = var102;

                                                                                                try {
                                                                                                    var103.set(var104, new TSNotification(var89, true));
                                                                                                    continue;
                                                                                                } catch (IllegalAccessException var29) {
                                                                                                    var10000 = var29;
                                                                                                    var98 = false;
                                                                                                    break label357;
                                                                                                }
                                                                                            } else {
                                                                                                var97 = var7;
                                                                                                var99 = TSAuthorization.class;

                                                                                                if (var97 == var99) {
                                                                                                    try {
                                                                                                        var103 = var5;
                                                                                                        var104 = this;
                                                                                                        var102 = var1.getJSONObject(var6);
                                                                                                    } catch (JSONException var34) {
                                                                                                        var96 = var34;
                                                                                                        var98 = false;
                                                                                                        break label349;
                                                                                                    }

                                                                                                    var89 = var102;

                                                                                                    try {
                                                                                                        var103.set(var104, new TSAuthorization(var89, true));
                                                                                                        continue;
                                                                                                    } catch (IllegalAccessException var33) {
                                                                                                        var10000 = var33;
                                                                                                        var98 = false;
                                                                                                        break label357;
                                                                                                    }
                                                                                                } else {
                                                                                                    var97 = var7;
                                                                                                    var99 = TSBackgroundPermissionRationale.class;

                                                                                                    if (var97 == var99) {
                                                                                                        try {
                                                                                                            var103 = var5;
                                                                                                            var104 = this;
                                                                                                            var102 = var1.getJSONObject(var6);
                                                                                                        } catch (JSONException var38) {
                                                                                                            var96 = var38;
                                                                                                            var98 = false;
                                                                                                            break label349;
                                                                                                        }

                                                                                                        var89 = var102;

                                                                                                        try {
                                                                                                            var103.set(var104, new TSBackgroundPermissionRationale(var89, true));
                                                                                                            continue;
                                                                                                        } catch (IllegalAccessException var37) {
                                                                                                            var10000 = var37;
                                                                                                            var98 = false;
                                                                                                            break label357;
                                                                                                        }
                                                                                                    } else {
                                                                                                        try {
                                                                                                            var5.set(this, var1.get(var6));
                                                                                                            continue;
                                                                                                        } catch (JSONException var40) {
                                                                                                            var96 = var40;
                                                                                                            var98 = false;
                                                                                                            break label349;
                                                                                                        } catch (IllegalAccessException var41) {
                                                                                                            var10000 = var41;
                                                                                                            var98 = false;
                                                                                                            break label357;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }

                                                                                        JSONObject var109;
                                                                                        try {
                                                                                            var109 = var1.getJSONObject(var6);
                                                                                        } catch (JSONException var58) {
                                                                                            var96 = var58;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        }

                                                                                        JSONObject var93 = var109;

                                                                                        HashMap var110 = new HashMap();

                                                                                        var94 = var110;

                                                                                        Iterator var111 = var109.keys();

                                                                                        Iterator var95 = var111;

                                                                                        while (true) {
                                                                                            boolean var112;
                                                                                            var112 = var95.hasNext();

                                                                                            if (!var112) {
                                                                                                break;
                                                                                            }

                                                                                            String var101;
                                                                                            Object var113;
                                                                                            JSONObject var114;
                                                                                            var113 = var94;
                                                                                            var114 = var93;
                                                                                            var101 = (String) var95.next();

                                                                                            String var9 = var101;

                                                                                            try {
                                                                                                ((Map) var113).put(var9, var114.getString(var101));
                                                                                            } catch (JSONException var50) {
                                                                                                var96 = var50;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            }
                                                                                        }
                                                                                    }

                                                                                    try {
                                                                                        var5.set(this, var94);
                                                                                        continue;
                                                                                    } catch (IllegalAccessException var27) {
                                                                                        var10000 = var27;
                                                                                        var98 = false;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        IllegalAccessException var90 = var10000;
                        TSLog.logger.error(TSLog.error(var90.getMessage()), var90);
                        continue;
                    }

                    JSONException var92 = var96;
                    TSLog.logger.error(TSLog.error(var92.getMessage()), var92);
                }
            }

        }

        private void a() {
            this.distanceFilter = 10.0F;
            this.desiredAccuracy = 100;
            if (TSConfig.q != null && TSConfig.q.l) {
                this.desiredAccuracy = 0;
            }

            this.desiredOdometerAccuracy = 100.0F;
            this.locationUpdateInterval = 1000L;
            this.fastestLocationUpdateInterval = -1L;
            this.locationTimeout = 60;
            this.deferTime = 0L;
            this.disableElasticity = false;
            this.elasticityMultiplier = 1.0F;
            this.allowIdenticalLocations = false;
            this.enableTimestampMeta = false;
            this.speedJumpFilter = 300;
            this.useSignificantChangesOnly = false;
            this.locationAuthorizationRequest = Application.B("??????????????????");
            this.disableLocationAuthorizationAlert = false;
            this.isMoving = false;
            this.activityRecognitionInterval = 10000L;
            this.minimumActivityRecognitionConfidence = 75;
            this.triggerActivities = Application.B("???????????????????????????????????????\ue425\udc8f?????????????????????\ue01c????????????????????????\ufff2\ue497???????????????????????\u1c93\uf2d0??????????????????");
            this.disableStopDetection = false;
            this.disableMotionActivityUpdates = false;
            this.stationaryRadius = 25;
            this.stopTimeout = 5L;
            this.stopOnStationary = false;
            this.motionTriggerDelay = 0L;
            this.persist = true;
            this.persistMode = 2;
            this.maxDaysToPersist = 1;
            this.maxRecordsToPersist = -1;
            this.url = "";
            this.method = Application.B("????????????");
            this.autoSync = true;
            this.autoSyncThreshold = TSConfig.DEFAULT_AUTO_SYNC_THRESHOLD;
            this.batchSync = false;
            this.maxBatchSize = -1;
            this.params = new JSONObject();
            this.headers = new JSONObject();
            this.extras = new JSONObject();
            this.httpRootProperty = Application.B("????????????????????????");
            this.locationTemplate = "";
            this.geofenceTemplate = "";
            this.locationsOrderDirection = Application.B("?????????");
            this.httpTimeout = 60000;
            this.disableAutoSyncOnCellular = false;
            (this.authorization = new TSAuthorization()).applyDefaults();
            this.geofenceProximityRadius = 1000L;
            this.geofenceInitialTriggerEntry = true;
            this.geofenceModeHighAccuracy = false;
            this.stopOnTerminate = true;
            this.startOnBoot = false;
            this.stopAfterElapsedMinutes = 0;
            this.heartbeatInterval = -1;
            this.foregroundService = true;
            (this.notification = new TSNotification()).applyDefaults();
            (this.backgroundPermissionRationale = new TSBackgroundPermissionRationale()).applyDefaults();
            this.configUrl = "";
            this.schedule = TSConfig.DEFAULT_SCHEDULE;
            this.scheduleUseAlarmManager = false;
            this.headlessJobService = "";
            this.enableHeadless = false;
            this.debug = false;
            this.logLevel = 0;
            this.logMaxDays = 3;
        }

        private TSConfig.Builder b() {
            TSConfig.Builder var10000 = this;
            List var1;
            List var10001 = var1 = this.dirtyFields;
            TSConfig.Builder var10002 = this;
            synchronized (var1) {
                var10002.dirtyFields.clear();
                return var10000;
            }
        }

        private void a(boolean var1) {
            TSConfig.Builder var2;
            var2 = new TSConfig.Builder();
            Field[] var3;
            int var4 = (var3 = TSConfig.Builder.class.getDeclaredFields()).length;

            TSConfig.Builder var10001;
            for (int var5 = 0; var5 < var4; ++var5) {
                Field var6;
                if (this.a(var6 = var3[var5])) {
                    Field var10000 = var6;
                    var10001 = var2;
                    TSConfig.Builder var10002 = this;
                    String var7 = var6.getName();

                    NoSuchMethodException var33;
                    label93:
                    {
                        IllegalAccessException var32;
                        label92:
                        {
                            InvocationTargetException var31;
                            label101:
                            {
                                String var10003;
                                boolean var34;
                                var10003 = Application.B("?????????") + var7.substring(0, 1).toUpperCase() + var7.substring(1);

                                var7 = var10003;

                                Class[] var10004;
                                Class var35;
                                var35 = var10002.getClass();
                                var10003 = var7;
                                var10004 = new Class[1];

                                Class[] var10005 = var10004;
                                Field var10006 = var6;
                                byte var25 = 0;

                                Method var36;
                                try {
                                    var10005[var25] = var10006.getType();
                                    var36 = var35.getMethod(var10003, var10004);
                                } catch (NoSuchMethodException var15) {
                                    var33 = var15;
                                    var34 = false;
                                    break label93;
                                }

                                Method var26 = var36;

                                Object var37;
                                try {
                                    var37 = var10000.get(var10001);
                                } catch (IllegalAccessException var13) {
                                    var32 = var13;
                                    var34 = false;
                                    break label92;
                                }

                                Object var30 = var37;
                                if (var37 == null) {
                                    continue;
                                }

                                try {
                                    var26.invoke(this, var30);
                                    continue;
                                } catch (IllegalAccessException var10) {
                                    var32 = var10;
                                    var34 = false;
                                    break label92;
                                } catch (InvocationTargetException var11) {
                                    var31 = var11;
                                    var34 = false;
                                }
                            }

                            InvocationTargetException var27 = var31;
                            Log.e(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var27.getMessage()), var27);
                            continue;
                        }

                        IllegalAccessException var28 = var32;
                        Log.e(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var28.getMessage()), var28);
                        continue;
                    }

                    NoSuchMethodException var29 = var33;
                    Log.e(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var29.getMessage()), var29);
                }
            }

            if (var1) {
                List var24;
                List var38 = var24 = this.dirtyFields;
                var10001 = this;
                synchronized (var24) {
                    var10001.dirtyFields.clear();
                }
            }

            this.commit();
        }

        private void a(JSONObject var1) throws JSONException {
            List var2;
            List var10000 = var2 = this.dirtyFields;
            TSConfig.Builder var10001 = this;
            synchronized (var2) {
                var10001.dirtyFields.clear();
            }

            Field[] var239;
            int var3 = (var239 = TSConfig.Builder.class.getDeclaredFields()).length;

            label749:
            for (int var4 = 0; var4 < var3; ++var4) {
                Field var5;
                String var6;
                if (var1.has(var6 = (var5 = var239[var4]).getName()) && this.a(var5)) {
                    JSONException var255;
                    label744:
                    {
                        NoSuchMethodException var254;
                        label743:
                        {
                            IllegalAccessException var253;
                            label742:
                            {
                                InvocationTargetException var252;
                                label753:
                                {
                                    boolean var256;
                                    Field var257;
                                    String var10002;
                                    var257 = var5;
                                    var10001 = this;
                                    var10002 = Application.B("?????????") + var6.substring(0, 1).toUpperCase() + var6.substring(1);

                                    String var7 = var10002;

                                    Class var260;
                                    Class[] var10003;
                                    var260 = var10001.getClass();
                                    var10002 = var7;
                                    var10003 = new Class[1];

                                    Class[] var10004 = var10003;
                                    Field var10005 = var5;
                                    byte var243 = 0;

                                    Method var265;
                                    try {
                                        var10004[var243] = var10005.getType();
                                        var265 = var260.getMethod(var10002, var10003);
                                    } catch (NoSuchMethodException var228) {
                                        var254 = var228;
                                        var256 = false;
                                        break label743;
                                    }

                                    Method var244 = var265;

                                    Class var266;
                                    var266 = var257.getType();
                                    var260 = Boolean.class;

                                    byte var240;
                                    Object[] var258;
                                    Object[] var259;
                                    JSONObject var261;
                                    String var262;
                                    Method var268;
                                    if (var266 == var260) {
                                        label754:
                                        {
                                            var268 = var244;
                                            var10001 = this;
                                            var258 = new Object[1];

                                            var259 = var258;
                                            var261 = var1;
                                            var262 = var6;
                                            var240 = 0;

                                            try {
                                                var259[var240] = var261.getBoolean(var262);
                                                var268.invoke(var10001, var258);
                                                continue;
                                            } catch (JSONException var11) {
                                                var255 = var11;
                                                var256 = false;
                                                break label744;
                                            } catch (IllegalAccessException var13) {
                                                var253 = var13;
                                                var256 = false;
                                                break label742;
                                            } catch (InvocationTargetException var14) {
                                                var252 = var14;
                                                var256 = false;
                                            }
                                        }
                                    } else {
                                        label725:
                                        {
                                            var266 = var5.getType();
                                            var260 = Long.class;

                                            if (var266 == var260) {
                                                label755:
                                                {
                                                    var268 = var244;
                                                    var10001 = this;
                                                    var258 = new Object[1];

                                                    var259 = var258;
                                                    var261 = var1;
                                                    var262 = var6;
                                                    var240 = 0;

                                                    try {
                                                        var259[var240] = var261.getLong(var262);
                                                        var268.invoke(var10001, var258);
                                                        continue;
                                                    } catch (JSONException var19) {
                                                        var255 = var19;
                                                        var256 = false;
                                                        break label744;
                                                    } catch (IllegalAccessException var21) {
                                                        var253 = var21;
                                                        var256 = false;
                                                        break label742;
                                                    } catch (InvocationTargetException var22) {
                                                        var252 = var22;
                                                        var256 = false;
                                                    }
                                                }
                                            } else {
                                                label721:
                                                {
                                                    var266 = var5.getType();
                                                    var260 = Integer.class;

                                                    if (var266 == var260) {
                                                        label756:
                                                        {
                                                            var268 = var244;
                                                            var10001 = this;
                                                            var258 = new Object[1];

                                                            var259 = var258;
                                                            var261 = var1;
                                                            var262 = var6;
                                                            var240 = 0;

                                                            try {
                                                                var259[var240] = var261.getInt(var262);
                                                                var268.invoke(var10001, var258);
                                                                continue;
                                                            } catch (JSONException var27) {
                                                                var255 = var27;
                                                                var256 = false;
                                                                break label744;
                                                            } catch (IllegalAccessException var29) {
                                                                var253 = var29;
                                                                var256 = false;
                                                                break label742;
                                                            } catch (InvocationTargetException var30) {
                                                                var252 = var30;
                                                                var256 = false;
                                                            }
                                                        }
                                                    } else {
                                                        label717:
                                                        {
                                                            var266 = var5.getType();
                                                            var260 = Float.class;

                                                            if (var266 == var260) {
                                                                label757:
                                                                {
                                                                    var268 = var244;
                                                                    var10001 = this;
                                                                    var258 = new Object[1];

                                                                    var259 = var258;
                                                                    var261 = var1;
                                                                    var262 = var6;
                                                                    var240 = 0;

                                                                    double var263;
                                                                    try {
                                                                        var263 = var261.getDouble(var262);
                                                                    } catch (JSONException var39) {
                                                                        var255 = var39;
                                                                        var256 = false;
                                                                        break label744;
                                                                    }

                                                                    float var267 = (float) var263;

                                                                    try {
                                                                        var259[var240] = var267;
                                                                        var268.invoke(var10001, var258);
                                                                        continue;
                                                                    } catch (IllegalAccessException var37) {
                                                                        var253 = var37;
                                                                        var256 = false;
                                                                        break label742;
                                                                    } catch (InvocationTargetException var38) {
                                                                        var252 = var38;
                                                                        var256 = false;
                                                                    }
                                                                }
                                                            } else {
                                                                label713:
                                                                {
                                                                    var266 = var5.getType();
                                                                    var260 = Double.class;

                                                                    if (var266 == var260) {
                                                                        label758:
                                                                        {
                                                                            var268 = var244;
                                                                            var10001 = this;
                                                                            var258 = new Object[1];

                                                                            var259 = var258;
                                                                            var261 = var1;
                                                                            var262 = var6;
                                                                            var240 = 0;

                                                                            try {
                                                                                var259[var240] = var261.getDouble(var262);
                                                                                var268.invoke(var10001, var258);
                                                                                continue;
                                                                            } catch (JSONException var47) {
                                                                                var255 = var47;
                                                                                var256 = false;
                                                                                break label744;
                                                                            } catch (IllegalAccessException var49) {
                                                                                var253 = var49;
                                                                                var256 = false;
                                                                                break label742;
                                                                            } catch (InvocationTargetException var50) {
                                                                                var252 = var50;
                                                                                var256 = false;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        label709:
                                                                        {
                                                                            var266 = var5.getType();
                                                                            var260 = String.class;

                                                                            if (var266 == var260) {
                                                                                label759:
                                                                                {
                                                                                    var268 = var244;
                                                                                    var10001 = this;
                                                                                    var258 = new Object[1];

                                                                                    var259 = var258;
                                                                                    var261 = var1;
                                                                                    var262 = var6;
                                                                                    var240 = 0;

                                                                                    try {
                                                                                        var259[var240] = var261.getString(var262);
                                                                                        var268.invoke(var10001, var258);
                                                                                        continue;
                                                                                    } catch (JSONException var55) {
                                                                                        var255 = var55;
                                                                                        var256 = false;
                                                                                        break label744;
                                                                                    } catch (IllegalAccessException var57) {
                                                                                        var253 = var57;
                                                                                        var256 = false;
                                                                                        break label742;
                                                                                    } catch (InvocationTargetException var58) {
                                                                                        var252 = var58;
                                                                                        var256 = false;
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                label705:
                                                                                {
                                                                                    var266 = var5.getType();
                                                                                    var260 = JSONObject.class;

                                                                                    if (var266 == var260) {
                                                                                        label760:
                                                                                        {
                                                                                            var268 = var244;
                                                                                            var10001 = this;
                                                                                            var258 = new Object[1];

                                                                                            var259 = var258;
                                                                                            var261 = var1;
                                                                                            var262 = var6;
                                                                                            var240 = 0;

                                                                                            try {
                                                                                                var259[var240] = var261.getJSONObject(var262);
                                                                                                var268.invoke(var10001, var258);
                                                                                                continue;
                                                                                            } catch (JSONException var63) {
                                                                                                var255 = var63;
                                                                                                var256 = false;
                                                                                                break label744;
                                                                                            } catch (IllegalAccessException var65) {
                                                                                                var253 = var65;
                                                                                                var256 = false;
                                                                                                break label742;
                                                                                            } catch (InvocationTargetException var66) {
                                                                                                var252 = var66;
                                                                                                var256 = false;
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        label701:
                                                                                        {
                                                                                            var266 = var5.getType();
                                                                                            var260 = JSONArray.class;

                                                                                            if (var266 == var260) {
                                                                                                label761:
                                                                                                {
                                                                                                    var268 = var244;
                                                                                                    var10001 = this;
                                                                                                    var258 = new Object[1];

                                                                                                    var259 = var258;
                                                                                                    var261 = var1;
                                                                                                    var262 = var6;
                                                                                                    var240 = 0;

                                                                                                    try {
                                                                                                        var259[var240] = var261.getJSONArray(var262);
                                                                                                        var268.invoke(var10001, var258);
                                                                                                        continue;
                                                                                                    } catch (JSONException var71) {
                                                                                                        var255 = var71;
                                                                                                        var256 = false;
                                                                                                        break label744;
                                                                                                    } catch (IllegalAccessException var73) {
                                                                                                        var253 = var73;
                                                                                                        var256 = false;
                                                                                                        break label742;
                                                                                                    } catch (InvocationTargetException var74) {
                                                                                                        var252 = var74;
                                                                                                        var256 = false;
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                label697:
                                                                                                {
                                                                                                    var266 = var5.getType();
                                                                                                    var260 = List.class;

                                                                                                    if (var266 == var260) {
                                                                                                        label762:
                                                                                                        {
                                                                                                            JSONArray var270;
                                                                                                            try {
                                                                                                                var270 = var1.getJSONArray(var6);
                                                                                                            } catch (JSONException var99) {
                                                                                                                var255 = var99;
                                                                                                                var256 = false;
                                                                                                                break label744;
                                                                                                            }

                                                                                                            JSONArray var245 = var270;

                                                                                                            ArrayList var271 = new ArrayList();
                                                                                                            var271 = new ArrayList();

                                                                                                            ArrayList var241 = var271;

                                                                                                            int var8 = 0;

                                                                                                            while (true) {
                                                                                                                int var272;
                                                                                                                int var273;
                                                                                                                var272 = var8;
                                                                                                                var273 = var245.length();

                                                                                                                if (var272 >= var273) {
                                                                                                                    try {
                                                                                                                        var244.invoke(this, var241);
                                                                                                                        continue label749;
                                                                                                                    } catch (IllegalAccessException var81) {
                                                                                                                        var253 = var81;
                                                                                                                        var256 = false;
                                                                                                                        break label742;
                                                                                                                    } catch (InvocationTargetException var82) {
                                                                                                                        var252 = var82;
                                                                                                                        var256 = false;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                }

                                                                                                                try {
                                                                                                                    var241.add(var245.getString(var8));
                                                                                                                } catch (JSONException var87) {
                                                                                                                    var255 = var87;
                                                                                                                    var256 = false;
                                                                                                                    break label744;
                                                                                                                }

                                                                                                                ++var8;
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        label693:
                                                                                                        {
                                                                                                            var266 = var5.getType();
                                                                                                            var260 = Map.class;

                                                                                                            JSONObject var246;
                                                                                                            if (var266 == var260) {
                                                                                                                label764:
                                                                                                                {
                                                                                                                    JSONObject var274;
                                                                                                                    try {
                                                                                                                        var274 = var1.getJSONObject(var6);
                                                                                                                    } catch (JSONException var127) {
                                                                                                                        var255 = var127;
                                                                                                                        var256 = false;
                                                                                                                        break label744;
                                                                                                                    }

                                                                                                                    var246 = var274;

                                                                                                                    HashMap var275 = new HashMap();

                                                                                                                    HashMap var242 = var275;

                                                                                                                    Iterator var276 = var274.keys();

                                                                                                                    Iterator var247 = var276;

                                                                                                                    while (true) {
                                                                                                                        boolean var277;
                                                                                                                        var277 = var247.hasNext();

                                                                                                                        if (!var277) {
                                                                                                                            try {
                                                                                                                                var244.invoke(this, var242);
                                                                                                                                continue label749;
                                                                                                                            } catch (IllegalAccessException var105) {
                                                                                                                                var253 = var105;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var106) {
                                                                                                                                var252 = var106;
                                                                                                                                var256 = false;
                                                                                                                                break;
                                                                                                                            }
                                                                                                                        }

                                                                                                                        HashMap var278;
                                                                                                                        JSONObject var279;
                                                                                                                        var278 = var242;
                                                                                                                        var279 = var246;
                                                                                                                        var10002 = (String) var247.next();

                                                                                                                        String var9 = var10002;

                                                                                                                        try {
                                                                                                                            var278.put(var9, var279.getString(var10002));
                                                                                                                        } catch (JSONException var111) {
                                                                                                                            var255 = var111;
                                                                                                                            var256 = false;
                                                                                                                            break label744;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            } else {
                                                                                                                label689:
                                                                                                                {
                                                                                                                    var266 = var5.getType();
                                                                                                                    var260 = TSNotification.class;

                                                                                                                    JSONObject var264;
                                                                                                                    byte var269;
                                                                                                                    if (var266 == var260) {
                                                                                                                        label766:
                                                                                                                        {
                                                                                                                            try {
                                                                                                                                var268 = var244;
                                                                                                                                var10001 = this;
                                                                                                                                var264 = var1.getJSONObject(var6);
                                                                                                                            } catch (JSONException var139) {
                                                                                                                                var255 = var139;
                                                                                                                                var256 = false;
                                                                                                                                break label744;
                                                                                                                            }

                                                                                                                            var246 = var264;

                                                                                                                            var258 = new Object[1];

                                                                                                                            var259 = var258;
                                                                                                                            var269 = 0;

                                                                                                                            try {
                                                                                                                                var259[var269] = new TSNotification(var246, false);
                                                                                                                                var268.invoke(var10001, var258);
                                                                                                                                continue;
                                                                                                                            } catch (IllegalAccessException var133) {
                                                                                                                                var253 = var133;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var134) {
                                                                                                                                var252 = var134;
                                                                                                                                var256 = false;
                                                                                                                            }
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        label685:
                                                                                                                        {
                                                                                                                            var266 = var5.getType();
                                                                                                                            var260 = TSAuthorization.class;

                                                                                                                            if (var266 == var260) {
                                                                                                                                label767:
                                                                                                                                {
                                                                                                                                    try {
                                                                                                                                        var268 = var244;
                                                                                                                                        var10001 = this;
                                                                                                                                        var264 = var1.getJSONObject(var6);
                                                                                                                                    } catch (JSONException var151) {
                                                                                                                                        var255 = var151;
                                                                                                                                        var256 = false;
                                                                                                                                        break label744;
                                                                                                                                    }

                                                                                                                                    var246 = var264;

                                                                                                                                    var258 = new Object[1];

                                                                                                                                    var259 = var258;
                                                                                                                                    var269 = 0;

                                                                                                                                    try {
                                                                                                                                        var259[var269] = new TSAuthorization(var246, false);
                                                                                                                                        var268.invoke(var10001, var258);
                                                                                                                                        continue;
                                                                                                                                    } catch (IllegalAccessException var145) {
                                                                                                                                        var253 = var145;
                                                                                                                                        var256 = false;
                                                                                                                                        break label742;
                                                                                                                                    } catch (InvocationTargetException var146) {
                                                                                                                                        var252 = var146;
                                                                                                                                        var256 = false;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                label681:
                                                                                                                                {
                                                                                                                                    var266 = var5.getType();
                                                                                                                                    var260 = TSBackgroundPermissionRationale.class;

                                                                                                                                    if (var266 == var260) {
                                                                                                                                        label768:
                                                                                                                                        {
                                                                                                                                            try {
                                                                                                                                                var268 = var244;
                                                                                                                                                var10001 = this;
                                                                                                                                                var264 = var1.getJSONObject(var6);
                                                                                                                                            } catch (JSONException var163) {
                                                                                                                                                var255 = var163;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            }

                                                                                                                                            var246 = var264;

                                                                                                                                            var258 = new Object[1];

                                                                                                                                            var259 = var258;
                                                                                                                                            var269 = 0;

                                                                                                                                            try {
                                                                                                                                                var259[var269] = new TSBackgroundPermissionRationale(var246, false);
                                                                                                                                                var268.invoke(var10001, var258);
                                                                                                                                                continue;
                                                                                                                                            } catch (IllegalAccessException var157) {
                                                                                                                                                var253 = var157;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var158) {
                                                                                                                                                var252 = var158;
                                                                                                                                                var256 = false;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        label769:
                                                                                                                                        {
                                                                                                                                            var268 = var244;
                                                                                                                                            var10001 = this;
                                                                                                                                            var258 = new Object[1];

                                                                                                                                            var259 = var258;
                                                                                                                                            var261 = var1;
                                                                                                                                            var262 = var6;
                                                                                                                                            var240 = 0;

                                                                                                                                            try {
                                                                                                                                                var259[var240] = var261.get(var262);
                                                                                                                                                var268.invoke(var10001, var258);
                                                                                                                                                continue;
                                                                                                                                            } catch (JSONException var167) {
                                                                                                                                                var255 = var167;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            } catch (IllegalAccessException var169) {
                                                                                                                                                var253 = var169;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var170) {
                                                                                                                                                var252 = var170;
                                                                                                                                                var256 = false;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                InvocationTargetException var248 = var252;
                                Log.i(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var248.getMessage()), var248);
                                continue;
                            }

                            IllegalAccessException var249 = var253;
                            Log.i(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var249.getMessage()), var249);
                            continue;
                        }

                        NoSuchMethodException var250 = var254;
                        Log.i(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var250.getMessage()), var250);
                        continue;
                    }

                    JSONException var251 = var255;
                    Log.e(Application.B("???????????????????????????????????????\ue42a\udcb7??????"), TSLog.error(var251.getMessage()), var251);
                }
            }

        }

        private void a(String string) {
            Builder builder = this;
            synchronized (builder.dirtyFields) {
                if (builder.dirtyFields.contains(string)) {
                    return;
                }
                this.dirtyFields.add(string);
            }
        }

        private void a(List<String> list) {
            List<String> list2 = this.dirtyFields;
            synchronized (list2) {
                this.dirtyFields.addAll(list);
                return;
            }
        }

        private Boolean a(String var1, Boolean var2, Boolean var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private String a(String var1, String var2, String var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Integer a(String var1, Integer var2, Integer var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Long a(String var1, Long var2, Long var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Double a(String var1, Double var2, Double var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Float a(String var1, Float var2, Float var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private JSONObject a(String var1, JSONObject var2, JSONObject var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private JSONArray a(String var1, JSONArray var2, JSONArray var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private List<String> a(String var1, List<String> var2, List<String> var3) {
            if (var3.containsAll(var2) && !var2.isEmpty()) {
                var3 = var2;
            } else {
                if (var2 != null) {
                    var3 = var2;
                }

                this.a(var1);
            }

            return var3;
        }

        private Map<String, String> a(String var1, Map<String, String> var2, Map<String, String> var3) {
            if (var3 == null || !var3.equals(var2)) {
                this.a(var1);
            }

            return var2;
        }

        private JSONObject b(boolean var1) {
            TSConfig.Builder var10000 = this;
            JSONObject var2;
            var2 = new JSONObject();

            JSONException var78;
            label290:
            {
                IllegalAccessException var77;
                label272:
                {
                    boolean var10001;
                    Field[] var79;
                    var79 = var10000.getClass().getDeclaredFields();

                    Field[] var3 = var79;

                    int var80;
                    var80 = var79.length;

                    int var4 = var80;
                    int var5 = 0;

                    label258:
                    while (true) {
                        if (var5 >= var4) {
                            return var2;
                        }

                        Field var82;
                        var10000 = this;
                        var82 = var3[var5];

                        Field var6 = var82;

                        boolean var84;
                        var84 = var10000.a(var82);

                        if (var84) {
                            String var10002;
                            TSConfig.Builder var85;
                            Field var86;
                            var86 = var6;
                            var85 = this;
                            var10002 = var6.getName();

                            String var7 = var10002;

                            Object var87;
                            try {
                                var87 = var86.get(var85);
                            } catch (IllegalAccessException var62) {
                                var77 = var62;
                                var10001 = false;
                                break;
                            }

                            Object var8 = var87;
                            if (var87 != null) {
                                Class var88;
                                Class var89;
                                var89 = var6.getType();
                                var88 = Map.class;

                                if (var89 == var88) {
                                    try {
                                        var2.put(var7, new JSONObject((Map) var8));
                                    } catch (JSONException var57) {
                                        var78 = var57;
                                        var10001 = false;
                                        break label290;
                                    }
                                } else {
                                    var89 = var6.getType();
                                    var88 = List.class;

                                    if (var89 == var88) {
                                        JSONArray var90 = new JSONArray();
                                        var87 = var8;

                                        JSONArray var75 = var90;

                                        var89 = var87.getClass();
                                        var88 = ArrayList.class;

                                        if (var89 == var88) {
                                            Iterator var91;
                                            var91 = ((List) var8).iterator();

                                            Iterator var76 = var91;

                                            while (true) {
                                                var84 = var76.hasNext();

                                                if (!var84) {
                                                    try {
                                                        var2.put(var7, var75);
                                                        break;
                                                    } catch (JSONException var43) {
                                                        var78 = var43;
                                                        var10001 = false;
                                                        break label290;
                                                    }
                                                }

                                                var75.put(var76.next());
                                            }
                                        }
                                    } else {
                                        var89 = var6.getType();
                                        var88 = Integer.class;

                                        if (var89 == var88) {
                                            try {
                                                var2.put(var7, (Integer) var8);
                                            } catch (JSONException var39) {
                                                var78 = var39;
                                                var10001 = false;
                                                break label290;
                                            }
                                        } else {
                                            var89 = var6.getType();
                                            var88 = Long.class;

                                            if (var89 == var88) {
                                                try {
                                                    var2.put(var7, (Long) var8);
                                                } catch (JSONException var35) {
                                                    var78 = var35;
                                                    var10001 = false;
                                                    break label290;
                                                }
                                            } else {
                                                label278:
                                                {
                                                    var89 = var6.getType();
                                                    var88 = Double.class;

                                                    double var81;
                                                    String var92;
                                                    JSONObject var93;
                                                    if (var89 == var88) {
                                                        var93 = var2;
                                                        var92 = var7;
                                                        var81 = (Double) var8;
                                                    } else {
                                                        var89 = var6.getType();
                                                        var88 = Float.class;

                                                        if (var89 != var88) {
                                                            var89 = var6.getType();
                                                            var88 = TSNotification.class;

                                                            JSONObject var83;
                                                            if (var89 == var88) {
                                                                var93 = var2;
                                                                var92 = var7;
                                                                var83 = ((TSNotification) var8).toJson(var1);
                                                            } else {
                                                                var89 = var6.getType();
                                                                var88 = TSAuthorization.class;

                                                                if (var89 == var88) {
                                                                    var93 = var2;
                                                                    var92 = var7;
                                                                    var83 = ((TSAuthorization) var8).toJson(var1);
                                                                } else {
                                                                    var89 = var6.getType();
                                                                    var88 = TSBackgroundPermissionRationale.class;

                                                                    if (var89 != var88) {
                                                                        try {
                                                                            var2.put(var7, var8);
                                                                            break label278;
                                                                        } catch (JSONException var9) {
                                                                            var78 = var9;
                                                                            var10001 = false;
                                                                            break label290;
                                                                        }
                                                                    }

                                                                    var93 = var2;
                                                                    var92 = var7;
                                                                    var83 = ((TSBackgroundPermissionRationale) var8).toJson(var1);
                                                                }
                                                            }

                                                            try {
                                                                var93.put(var92, var83);
                                                                break label278;
                                                            } catch (JSONException var11) {
                                                                var78 = var11;
                                                                var10001 = false;
                                                                break label290;
                                                            }
                                                        }

                                                        var93 = var2;
                                                        var92 = var7;
                                                        var81 = ((Float) var8).doubleValue();
                                                    }

                                                    try {
                                                        var93.put(var92, var81);
                                                    } catch (JSONException var25) {
                                                        var78 = var25;
                                                        var10001 = false;
                                                        break label290;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        ++var5;
                    }
                }

                IllegalAccessException var73 = var77;
                Log.i(Application.B("???\udf93????????????????????????????????\uefc0?????????"), TSLog.error(var73.getMessage()));
                TSLog.logger.error(TSLog.error(var73.getMessage()), var73);
                return var2;
            }

            JSONException var74 = var78;
            Log.i(Application.B("???\udf93????????????????????????????????\uefc0?????????"), TSLog.error(var74.getMessage()));
            TSLog.logger.error(TSLog.error(var74.getMessage()), var74);
            return var2;
        }

        private boolean a(Field var1) {
            return !IGNORED_FIELDS.contains(var1.getName()) && !Modifier.isStatic(var1.getModifiers());
        }

        public void commit() {
            if (TSConfig.q != null) {
                TSConfig.q.b();
            }

        }

        public TSConfig.Builder setIsMoving(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.isMoving;
            var10001.isMoving = var10002.a(Application.B("\ufd44\uf65a??????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDistanceFilter(Float var1) {
            if (var1 < 0.0F) {
                TSLog.logger.warn(TSLog.warn(Application.B("??????\ufe6d?????????????????????\uf225???????????????\ud82e??????\udf3c????????????") + var1 + Application.B("??????????????????????????????\uf231???????????????\ud83d??????\udf17?????????????????????\ue225\ueabe??????????????????\ue965\uf59e") + 10.0F));
                var1 = 10.0F;
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Float var2 = this.distanceFilter;
            var10001.distanceFilter = var10002.a(Application.B("??????????????????????????????\uf23a?????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDesiredAccuracy(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.desiredAccuracy;
            var10001.desiredAccuracy = var10002.a(Application.B("???\ue3b3?????????\uef3f???????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDesiredOdometerAccuracy(Float var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Float var2 = this.desiredOdometerAccuracy;
            var10001.desiredOdometerAccuracy = var10002.a(Application.B("???????????????\udbad????????????????????????\uf1b2?????????\uf4dc???????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationUpdateInterval(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.locationUpdateInterval;
            var10001.locationUpdateInterval = var10002.a(Application.B("????????????????????????????????????????????????\uf678??????\uf850???\ue144"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setFastestLocationUpdateInterval(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.fastestLocationUpdateInterval;
            var10001.fastestLocationUpdateInterval = var10002.a(Application.B("??????\ue66b?????????\u0002?????????\ud9cf??????????????????????????????\ue7ce\uf0af?????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationTimeout(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.locationTimeout;
            var10001.locationTimeout = var10002.a(Application.B("????????????\uf733????????????????????????\ue89a???"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDeferTime(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.deferTime;
            var10001.deferTime = var10002.a(Application.B("????????????????????????\u2434"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableElasticity(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableElasticity;
            var10001.disableElasticity = var10002.a(Application.B("??????\ud8ed???\uf38c???\uf791\ud97a???\ue61c???\uf456???\uec66?????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setElasticityMultiplier(Float var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Float var2 = this.elasticityMultiplier;
            var10001.elasticityMultiplier = var10002.a(Application.B("?????????\uda2a????????????????????????????????\uead1?????????\uf878"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAllowIdenticalLocations(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.allowIdenticalLocations;
            var10001.allowIdenticalLocations = var10002.a(Application.B("???????????????\u1cca?????????????????????????????????\u0fe7????????????\ue883"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setActivityRecognitionInterval(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.activityRecognitionInterval;
            var10001.activityRecognitionInterval = var10002.a(Application.B("??????????????????\ue234???????????????\uf784????????????\ued1d???\uec37\uf383???\ue7dd????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMinimumActivityRecognitionConfidence(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.minimumActivityRecognitionConfidence;
            var10001.minimumActivityRecognitionConfidence = var10002.a(Application.B("??????\uf34b???????????????????????????????????????????????????\ue692???????????????\ued08???????????????\uf889????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setTriggerActivities(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.triggerActivities;
            var10001.triggerActivities = var10002.a(Application.B("\ue3c7??????\uf3dd???\ufa6f\udbc6??????????????\ue1c8????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableStopDetection(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableStopDetection;
            var10001.disableStopDetection = var10002.a(Application.B("\ue633?????????????????????\uf013????????????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableMotionActivityUpdates(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableMotionActivityUpdates;
            var10001.disableMotionActivityUpdates = var10002.a(Application.B("???????????????????????????????????\ueca2????????????????????????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStationaryRadius(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.stationaryRadius;
            var10001.stationaryRadius = var10002.a(Application.B("?????????????????????????????\udc57???????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopTimeout(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.stopTimeout;
            var10001.stopTimeout = var10002.a(Application.B("?????????????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopOnStationary(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.stopOnStationary;
            var10001.stopOnStationary = var10002.a(Application.B("????????????????????????\ue4a1?????????\ue5b8?????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMotionTriggerDelay(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.motionTriggerDelay;
            var10001.motionTriggerDelay = var10002.a(Application.B("?????????????????\uef0d????????????\uf39d\udf2f\ud8fb\ue1d8\ue08c??????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setEnableTimestampMeta(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.enableTimestampMeta;
            var10001.enableTimestampMeta = var10002.a(Application.B("???\udc8a????????????????????????\udfa8???????????????\u0b8c??????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setSpeedJumpFilter(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.speedJumpFilter;
            var10001.speedJumpFilter = var10002.a(Application.B("???????????????????????????\ue0e0???????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setUseSignificantChangesOnly(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.useSignificantChangesOnly;
            var10001.useSignificantChangesOnly = var10002.a(Application.B("???\ue2a5??????\udc0a??????????????????\udcad????????????\uec59???\uf244\ueffb???????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationAuthorizationRequest(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.locationAuthorizationRequest;
            var10001.locationAuthorizationRequest = var10002.a(Application.B("????????????????????\uf181?????????\ue3e1?????????????????????????????????????????????\ue8ca"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableLocationAuthorizationAlert(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableLocationAuthorizationAlert;
            var10001.disableLocationAuthorizationAlert = var10002.a(Application.B("????????????????????????????????????\ue3ba??????????????????????????????\udc08?????????????????????\uf5f1???"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setPersist(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.persist;
            var10001.persist = var10002.a(Application.B("???\udabc?????????\ufdde???"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setPersistMode(Integer var1) {
            switch (var1) {
                default:
                    TSLog.logger.warn(TSLog.warn(Application.B("??????\uf447???????????????\uf0b0??????????????????????????????\uddd5\uda51???\uf072??????\ude16??????????????") + var1 + Application.B("??????\uf411???????????????\uf0e6??????????????????????????????\udddc\uda56???\uf021???") + 2));
                    var1 = 2;
                case -1:
                case 0:
                case 1:
                case 2:
                    TSConfig.Builder var10000 = this;
                    TSConfig.Builder var10001 = this;
                    TSConfig.Builder var10002 = this;
                    Integer var2 = this.persistMode;
                    var10001.persistMode = var10002.a(Application.B("??????\uf443???\u0a12?????????\uf0a9??????"), var1, var2);
                    return var10000;
            }
        }

        public TSConfig.Builder setMaxDaysToPersist(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.maxDaysToPersist;
            var10001.maxDaysToPersist = var10002.a(Application.B("?????????\uef2f\ufaf5????????????\udf6c??????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMaxRecordsToPersist(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.maxRecordsToPersist;
            var10001.maxRecordsToPersist = var10002.a(Application.B("???\uf64e?????????\uf056??????????????????????????????\ue21c??????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setUrl(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.url;
            var10001.url = var10002.a(Application.B("?????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMethod(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.method;
            var10001.method = var10002.a(Application.B("????????\ue187??????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAutoSync(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.autoSync;
            var10001.autoSync = var10002.a(Application.B("???\uf66c???\ue617????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAutoSyncThreshold(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.autoSyncThreshold;
            var10001.autoSyncThreshold = var10002.a(Application.B("????????????\ue3d6???????????????\ue30e??????\uf2a4???\uda72???"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setBatchSync(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.batchSync;
            var10001.batchSync = var10002.a(Application.B("??????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMaxBatchSize(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.maxBatchSize;
            var10001.maxBatchSize = var10002.a(Application.B("\ud8cd?????????\uf3e0?????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setParams(JSONObject var1) {
            if (var1 == null) {
                var1 = new JSONObject();
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            JSONObject var2 = this.params;
            var10001.params = var10002.a(Application.B("??????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHeader(String var1, String var2) {
            if (this.headers == null) {
                JSONObject var3;
                var3 = new JSONObject();
                this.headers = var3;
            }

            JSONException var10000;
            label28:
            {
                boolean var10001;
                TSConfig.Builder var7;
                try {
                    var7 = this;
                    this.headers.put(var1, var2);
                } catch (JSONException var5) {
                    var10000 = var5;
                    var10001 = false;
                    break label28;
                }

                var7.a(Application.B("????????\ue1bd\u2068???\ufe6d"));
                return this;
            }

            JSONException var6 = var10000;
            TSLog.logger.error(TSLog.error(var6.getMessage()), var6);
            return this;
        }

        public TSConfig.Builder setHeaders(JSONObject var1) {
            if (var1 == null) {
                var1 = new JSONObject();
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            JSONObject var2 = this.headers;
            var10001.headers = var10002.a(Application.B("\u0c73??????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setExtras(JSONObject var1) {
            if (var1 == null) {
                var1 = new JSONObject();
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            JSONObject var2 = this.extras;
            var10001.extras = var10002.a(Application.B("??????\ue16c???\uf232???"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHttpRootProperty(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.httpRootProperty;
            var10001.httpRootProperty = var10002.a(Application.B("????????????????????????????????????\ue159???\uf5a3\uf888"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationTemplate(String var1) {
            if (var1 == null) {
                var1 = "";
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.locationTemplate;
            var10001.locationTemplate = var10002.a(Application.B("????????????????????????????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setGeofenceTemplate(String var1) {
            if (var1 == null) {
                var1 = "";
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.geofenceTemplate;
            var10001.geofenceTemplate = var10002.a(Application.B("?????????????????????\ue897?????????\uf717????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationsOrderDirection(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.locationsOrderDirection;
            var10001.locationsOrderDirection = var10002.a(Application.B("??????????????????\uee42???\ud8d2????????????\uf836???????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHttpTimeout(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.httpTimeout;
            var10001.httpTimeout = var10002.a(Application.B("\udd14???\udfc1????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableAutoSyncOnCellular(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableAutoSyncOnCellular;
            var10001.disableAutoSyncOnCellular = var10002.a(Application.B("\uf76b???\uf6db??????\ue8a0???????????\uf0db?????????????????????????????\uf120?????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAuthorization(TSAuthorization var1) {
            if (this.authorization.update(var1)) {
                this.a(Application.B("\uf82b?????????????????????\uef68????????????"));
                this.a(this.authorization.getDirtyFields());
            }

            this.authorization.applyDefaults();
            return this;
        }

        public TSConfig.Builder setGeofenceProximityRadius(Long var1) {
            var1 = var1 < 1000L ? 1000L : var1;
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.geofenceProximityRadius;
            var10001.geofenceProximityRadius = var10002.a(Application.B("??????????????????\uea2f????????????\uecb5????????????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setGeofenceInitialTriggerEntry(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.geofenceInitialTriggerEntry;
            var10001.geofenceInitialTriggerEntry = var10002.a(Application.B("???????????????????????????????????\ue0bf???????????????????????????\uf1d8????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setGeofenceModeHighAccuracy(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.geofenceModeHighAccuracy;
            var10001.geofenceModeHighAccuracy = var10002.a(Application.B("\u070f??????????\u0c50?????????\u192f????????????????????????\uf82a???????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopOnTerminate(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.stopOnTerminate;
            var10001.stopOnTerminate = var10002.a(Application.B("\uf6c1??????????????????????\ue1fe???????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStartOnBoot(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.startOnBoot;
            var10001.startOnBoot = var10002.a(Application.B("?????????????????????????????\uea18"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopAfterElapsedMinutes(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.stopAfterElapsedMinutes;
            var10001.stopAfterElapsedMinutes = var10002.a(Application.B("???????????????????????\u2d29\u0cce??????\ufaec??????\uda92????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHeartbeatInterval(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.heartbeatInterval;
            var10001.heartbeatInterval = var10002.a(Application.B("???????????????????????????\uf5e5???\udd0e??????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setForegroundService(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.foregroundService;
            var10001.foregroundService = var10002.a(Application.B("????????????????????????????????????????????????\ued07"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setNotification(TSNotification var1) {
            if (this.notification.update(var1)) {
                this.a(Application.B("?????????\uea67??????????????????\ue3a0???"));
                this.a(this.notification.getDirtyFields());
            }

            this.notification.applyDefaults();
            return this;
        }

        public TSConfig.Builder setBackgroundPermissionRationale(TSBackgroundPermissionRationale var1) {
            if (this.backgroundPermissionRationale.update(var1)) {
                this.a(Application.B("\udfb0????????????????????????????????????????????????\uf47e???????????????????????????\ue799???"));
                this.a(this.backgroundPermissionRationale.getDirtyFields());
            }

            this.backgroundPermissionRationale.applyDefaults();
            return this;
        }

        public TSConfig.Builder setConfigUrl(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.configUrl;
            var10001.configUrl = var10002.a(Application.B("???????????????\ue0e6?????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setSchedule(List<String> var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            List var2 = this.schedule;
            var10001.schedule = var10002.a(Application.B("???????????????\uf6a9???\ue27f"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setScheduleUseAlarmManager(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.scheduleUseAlarmManager;
            var10001.scheduleUseAlarmManager = var10002.a(Application.B("??????\udfc1\ue94d????????????????????????\uda53????????????\ue81f???????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setEnableHeadless(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.enableHeadless;
            var10001.enableHeadless = var10002.a(Application.B("???\ue23c\uf59f???\u09d4????????????\uede8????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHeadlessJobService(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.headlessJobService;
            var10001.headlessJobService = var10002.a(Application.B("??????\uf13d\uf196????????????\uf7d6???????????????????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDebug(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.debug;
            var10001.debug = var10002.a(Application.B("\uf291\uf50a\uf398??????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLogLevel(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.logLevel;
            var10001.logLevel = var10002.a(Application.B("\ue947???\uf270??????????????"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLogMaxDays(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.logMaxDays;
            var10001.logMaxDays = var10002.a(Application.B("??????\uec7b???????????????\ua83d???"), var1, var2);
            return var10000;
        }
    }
}
