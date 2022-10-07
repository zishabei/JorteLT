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
    public static final String TAG = Application.B("뇤乓香ゑ㘿ᘏ\ue482를嘋ⰷῂ\udcf1⢖ɨⰦ蠵ꈒ");
    public static final String EVENT_BOOT = Application.B("뇒乯馺り");
    public static final String EVENT_TERMINATE = Application.B("뇄乥馧ん㘵ᘀ\ue497륡嘁");
    public static final String EVENT_HEARTBEAT = Application.B("뇘乥馴れ㘨ᘌ\ue493르嘐");
    public static final String EVENT_SCHEDULE = Application.B("뇃乣馽゛㘸ᘛ\ue49a륰");
    public static final String EVENT_LOCATION = Application.B("뇜乯馶ゟ㘨ᘇ\ue499륻");
    public static final String EVENT_MOTIONCHANGE = Application.B("뇝乯馡\u3097㘳ᘀ\ue495륽嘅ⰷῨ\udcf5");
    public static final String EVENT_GEOFENCE = Application.B("뇗乥馺\u3098㘹ᘀ\ue495륰");
    public static final String EVENT_POWERSAVECHANGE = Application.B("뇀乯馢゛㘮ᘝ\ue497륣嘁ⰺῧ\udcf1⢖ɮⰤ");
    public static final String EVENT_HTTP = Application.B("뇘乴馡ゎ");
    public static final String EVENT_ERROR = Application.B("뇕乲馧ゑ㘮");
    public static final String EVENT_ACTIVITYCHANGE = Application.B("뇑乣馡\u3097㘪ᘇ\ue482륬嘇ⰱ΅\udcfe⢟ɬ");
    public static final String EVENT_PLAY_SERVICES_CONNECT_ERROR = Application.B("뇀乬馴ょ㘯ᘋ\ue484륣嘍ⰺῪ\udce3⢛ɦ\u2c2f蠾ꈅ퀑\u1879埈붕샱\uaa39챥");
    public static final String EVENT_ENABLEDCHANGE = Application.B("뇕乮馴゜㘰ᘋ\ue492륶嘌ⰸῡ\udcf7⢝");
    public static final String EVENT_CONNECTIVITYCHANGE = Application.B("뇓乯馻ゐ㘹ᘍ\ue482를嘒ⰰΏ\udce9⢛ɡⰠ蠾ꈇ퀗");
    public static final String EVENT_PROVIDERCHANGE = Application.B("뇀乲馺よ㘵ᘊ\ue493륧嘇ⰱ΅\udcfe⢟ɬ");
    public static final String EVENT_SECURITY_EXCEPTION = Application.B("뇃乥馶る㘮ᘇ\ue482륬嘁ⰡῬ\udcf5⢈ɽⰨ蠿ꈎ");
    public static final String EVENT_GEOFENCES_CHANGE = Application.B("뇗乥馺\u3098㘹ᘀ\ue495륰嘗ⰺῧ\udcf1⢖ɮⰤ");
    public static final String EVENT_GEOFENCESCHANGE = Application.B("뇗乥馺\u3098㘹ᘀ\ue495륰嘗ⰺῧ\udcf1⢖ɮⰤ");
    public static final String EVENT_NOTIFICATIONACTION = Application.B("뇞乯馡\u3097㘺ᘇ\ue495르嘐ⰰῠ\udcfe⢙ɪⰵ蠹ꈏ퀜");
    public static final String EVENT_AUTHORIZATION = Application.B("뇑乵馡ゖ㘳ᘜ\ue49f륯嘅Ⱝῦ\udcff⢖");
    public static final String ACTION_START = Application.B("뇃乴馴れ㘨");
    public static final String ACTION_STOP = Application.B("뇃乴馺ゎ");
    public static final String ACTION_START_GEOFENCES = Application.B("뇃乴馴れ㘨ᘩ\ue493륺嘂ⰼῡ\udcf3⢝ɺ");
    public static final String ACTION_SET_CONFIG = Application.B("뇃乥馡ソ㘳ᘀ\ue490를嘃");
    public static final String ACTION_SET_NOTIFICATION = Application.B("뇃乥馡グ㘳ᘚ\ue49f륳嘍ⰺ΅\udce4⢑ɦ\u2c2f");
    public static final String ACTION_GET_LOCATIONS = Application.B("뇗乥馡ゲ㘳ᘍ\ue497륡嘍ⰶῡ\udce3");
    public static final String ACTION_CHANGE_PACE = Application.B("뇓乨馴ゐ㘻ᘋ\ue4a6르嘇ⰼ");
    public static final String ACTION_ON_MOTION_CHANGE = Application.B("뇟乮馘ゑ㘨ᘇ\ue499륻嘧ⰱ΅\udcfe⢟ɬ");
    public static final String ACTION_ON_GEOFENCE = Application.B("뇟乮馒゛㘳ᘈ\ue493륻嘇ⰼ");
    public static final String ACTION_SYNC = Application.B("뇃乹馻ゝ");
    public static final String ACTION_GET_ODOMETER = Application.B("뇗乥馡ケ㘸ᘁ\ue49b륰嘐ⰼ´");
    public static final String ACTION_RESET_ODOMETER = Application.B("뇂乥馦゛㘨ᘡ\ue492륺嘉ⰼΏ\udcf5⢊");
    public static final String ACTION_SET_ODOMETER = Application.B("뇃乥馡ケ㘸ᘁ\ue49b륰嘐ⰼ´");
    public static final String ACTION_ADD_GEOFENCE = Application.B("뇑乤馱ス㘹ᘁ\ue490륰嘊ⰺῪ");
    public static final String ACTION_ADD_GEOFENCES = Application.B("뇑乤馱ス㘹ᘁ\ue490륰嘊ⰺῪ\udce3");
    public static final String ACTION_GET_GEOFENCES = Application.B("뇗乥馡ス㘹ᘁ\ue490륰嘊ⰺῪ\udce3");
    public static final String ACTION_GET_GEOFENCE = Application.B("뇗乥馡ス㘹ᘁ\ue490륰嘊ⰺῪ");
    public static final String ACTION_REMOVE_GEOFENCE = Application.B("뇂乥馸ゑ㘪ᘋ\ue4b1륰嘋ⰿῪ\udcfe⢛ɬ");
    public static final String ACTION_REMOVE_GEOFENCES = Application.B("뇂乥馸ゑ㘪ᘋ\ue4b1륰嘋ⰿῪ\udcfe⢛ɬⰲ");
    public static final String ACTION_GEOFENCE_EXISTS = Application.B("뇗乥馺\u3098㘹ᘀ\ue495륰嘡Ⱑῦ\udce3⢌ɺ");
    public static final String ACTION_PLAY_SOUND = Application.B("뇀乬馴ょ㘏ᘁ\ue483륻嘀");
    public static final String ACTION_GET_CURRENT_POSITION = Application.B("뇗乥馡ソ㘩ᘜ\ue484륰嘊Ⱝ῟\udcff⢋ɠⰵ蠹ꈏ퀜");
    public static final String ACTION_WATCH_POSITION = Application.B("뇇乡馡ゝ㘴ᘾ\ue499륦嘍Ⱝῦ\udcff⢖");
    public static final String ACTION_STOP_WATCH_POSITION = Application.B("뇃乴馺ゎ㘋ᘏ\ue482륶嘌Ⰹῠ\udce3⢑ɽⰨ蠿ꈎ");
    public static final String ACTION_GOOGLE_PLAY_SERVICES_CONNECT_ERROR = Application.B("뇗乯馺゙㘰ᘋ\ue4a6륹嘅Ⱐ\u1fdc\udcf5⢊ɿⰨ蠳ꈅ퀱ᡢ埃붉샦ꨵ챣ꠝ닗䄔≰䟙");
    public static final String ACTION_LOCATION_ERROR = Application.B("뇜乯馶ゟ㘨ᘇ\ue499륻嘡Ⱛ´\udcff⢊");
    public static final String ACTION_HTTP_RESPONSE = Application.B("뇘乴馡ゎ");
    public static final String ACTION_CLEAR_DATABASE = Application.B("뇓乬馰ゟ㘮ᘪ\ue497륡嘅ⰻ΅\udce3⢝");
    public static final String ACTION_DESTROY_LOCATIONS = Application.B("뇔乥馦り㘮ᘁ\ue48f륙嘋ⰺ΅\udce4⢑ɦ\u2c2f蠣");
    public static final String ACTION_DESTROY_LOCATION = Application.B("뇔乥馦り㘮ᘁ\ue48f륙嘋ⰺ΅\udce4⢑ɦ\u2c2f");
    public static final String ACTION_DESTROY_LOG = Application.B("뇔乥馦り㘮ᘁ\ue48f륙嘋ⰾ");
    public static final String ACTION_INSERT_LOCATION = Application.B("뇙乮馦゛㘮ᘚ\ue4ba륺嘇ⰸΏ\udcf9⢗ɧ");
    public static final String ACTION_GET_COUNT = Application.B("뇗乥馡ソ㘳ᘛ\ue498륡");
    public static final String ACTION_START_ON_BOOT = Application.B("뇃乴馴れ㘨ᘡ\ue498륗嘋ⰶΏ");
    public static final String ACTION_HEARTBEAT = Application.B("뇘乥馴れ㘨ᘌ\ue493르嘐");
    public static final String ACTION_SCHEDULE = Application.B("뇃乣馽゛㘸ᘛ\ue49a륰");
    public static final String ACTION_START_BACKGROUND_TASK = Application.B("뇃乴馴れ㘨ᘬ\ue497륶嘏ⰾ´\udcff⢍ɧⰥ蠄ꈁ퀁ᡦ");
    public static final String ACTION_STOP_BACKGROUND_TASK = Application.B("뇃乴馺ゎ㘞ᘏ\ue495륾嘃Ⱛῠ\udce5⢖ɭⰕ蠱ꈓ퀙");
    public static final String ACTION_FINISH = Application.B("뇖乩馻\u3097㘯ᘆ");
    public static final String ACTION_GET_SENSORS = Application.B("뇗乥馡キ㘹ᘀ\ue485륺嘖Ⱚ");
    public static final String ACTION_REMOVE_LISTENER = Application.B("뇂乥馸ゑ㘪ᘋ\ue4ba를嘗ⰭῪ\udcfe⢝ɻ");
    public static final String ACTION_IS_POWER_SAVE_MODE = Application.B("뇙乳馅ゑ㘫ᘋ\ue484륆嘅\u2c2fῪ\udcdd⢗ɭⰤ");
    public static final String ACTION_IS_IGNORING_BATTERY_OPTIMIZATIONS = Application.B("뇙乳馜゙㘲ᘁ\ue484를嘊ⰾ῍\udcf1⢌ɽⰤ蠢ꈙ퀽\u187d埙붎샮\uaa3f챭꠹닑䄏≰䟅㹎");
    public static final String ACTION_SHOW_SETTINGS = Application.B("뇃乨馺ら㘏ᘋ\ue482륡嘍ⰷῨ\udce3");
    public static final String ACTION_GET_PROVIDER_STATE = Application.B("뇗乥馡ギ㘮ᘁ\ue480를嘀ⰼ´\udcc3⢌ɨⰵ蠵");
    public static final String ACTION_REQUEST_PERMISSION = Application.B("뇂乥馤る㘹ᘝ\ue482륅嘁Ⱛῢ\udcf9⢋ɺⰨ蠿ꈎ");
    public static final int FORCE_RELOAD_LOCATION_CHANGE = 1;
    public static final int FORCE_RELOAD_MOTION_CHANGE = 2;
    public static final int FORCE_RELOAD_GEOFENCE = 3;
    public static final int FORCE_RELOAD_HEARTBEAT = 4;
    public static final int FORCE_RELOAD_SCHEDULE = 5;
    public static final int FORCE_RELOAD_BOOT = 6;
    private static final String y = Application.B("뇝乡馼ゐ㘝ᘍ\ue482를嘒ⰰΏ\udce9⢱ɧⰠ蠳ꈔ퀛\u187b埈");
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
            z.setActivity((Activity)var0);
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
        return Application.B("觏Ṅ퇊\uf7fd淥\uf45c\u2d2fꀂ喌\ue3f0䴌╴婠䈋⚏妖䄐똵⧴웾\ue685┑宜满㝜뙆\u1ac8Ꙅ䣺\uf61f댖镊⸃壍欳헦ᒉ딞Ὕ\uf82e헐") + var0.toUpperCase();
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
        TSLog.logger.info(TSLog.ok(Application.B("蚳杷㿍찯\uf09f\ufaf7ⵯ垼䒽妗\ued0eꓱﺩ縘薄胎ᐥ竐涫㰼걫愾校䗅鲢嘠\ud8b1ꓦ\ue705ꧦඛ\uef17䃴ᴯ\u20fbﭽአ滶⤸\ue8e5妞盃웺\uea96旇桏") + GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE + Application.B("蛝")));
    }

    private void a(final TSCallback var1) {
        com.transistorsoft.locationmanager.util.c.g(this.c, new PermissionRequestListener() {
            public void onPermissionGranted() {
                TSConfig var1x = TSConfig.getInstance(BackgroundGeolocation.this.c);
                if (BackgroundGeolocation.this.f.get() && var1x.getEnabled() && var1x.isLocationTrackingMode()) {
                    TSLog.logger.warn(TSLog.warn(Application.B("毴䜿\ud998∫戢\ue7c1憱᥊Ԯ\uf318會\ue534쭠뜗ẗ왆ꓻ䁭싞ೡ竉ฅ쓁뜺⾐")));
                    var1.onSuccess();
                } else {
                    BackgroundGeolocation var2;
                    BackgroundGeolocation var10000 = var2 = BackgroundGeolocation.this;
                    TSCallback var10004 = var1;
                    var10000.h = var2.new z0(Application.B("毆䜧\ud98b∼户"), var10004);
                    BackgroundGeolocation.getThreadPool().execute(BackgroundGeolocation.this.h);
                }
            }

            public void onPermissionDenied(DeniedPermissions var1x) {
                if (com.transistorsoft.locationmanager.util.c.c(BackgroundGeolocation.this.c)) {
                    TSConfig var2 = TSConfig.getInstance(BackgroundGeolocation.this.c);
                    if (BackgroundGeolocation.this.f.get() && var2.getEnabled() && var2.isLocationTrackingMode()) {
                        TSLog.logger.warn(TSLog.warn(Application.B("몯묭瑀鉿쌏雖\ued26켜\u0ee1᧺≯ᑊꍨ꺙юꂢ捽䳉\ue1f8뺣炑ݦ⬁裙줮")));
                        var1.onSuccess();
                        return;
                    }

                    BackgroundGeolocation var3;
                    BackgroundGeolocation var10000 = var3 = BackgroundGeolocation.this;
                    TSCallback var10004 = var1;
                    var10000.h = var3.new z0(Application.B("몝묵瑓鉨쌚"), var10004);
                    BackgroundGeolocation.getThreadPool().execute(BackgroundGeolocation.this.h);
                } else {
                    var1.onFailure(Application.B("몾묤瑀鉷쌇雁\ued2c콕\u0efd᧠∮ᑜꍹ꺒уꃩ挹"));
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
                    TSLog.logger.warn(TSLog.warn(Application.B("\ud972뮐\ueacd酐轆䦋ꗷ㞎\uf29c벉袇헴\uf83e⁸늓ᰯ▙ⅺꑱ爃煤꫱くﯓ\ue5be넍ꫂ礖\uef76ਕ큐湩樐뛂ﵔ")));
                    var1.onSuccess();
                } else {
                    BackgroundGeolocation var2;
                    BackgroundGeolocation var10000 = var2 = BackgroundGeolocation.this;
                    TSCallback var10004 = var1;
                    var10000.h = var2.new z0(Application.B("\ud940뮈\ueade酇轓䦨ꗫ㟁\uf289벘袈헥\uf82f\u206e"), var10004);
                    BackgroundGeolocation.getThreadPool().execute(BackgroundGeolocation.this.h);
                }
            }

            public void onPermissionDenied(DeniedPermissions var1x) {
                var1.onFailure(Application.B("혥ᎇ↔钪䋁\ue4c1\u0bce䴳ﳳ眓㌴\uea11哰ꂊ순懼\uee4e"));
            }
        });
    }

    private List a(String var1) {
        if (Application.B("셧Ɯ፸㞇젲\ud9c8苾梏").equalsIgnoreCase(var1)) {
            return this.i;
        } else if (Application.B("셦Ɯ፯㞏젩\ud9cf苲梉컉ኤࠖ\u18af").equals(var1)) {
            return this.j;
        } else if (Application.B("셪Ɛ፯㞏젰\ud9c8若梘컋ኢࠐᢤ冴꜔").equalsIgnoreCase(var1)) {
            return this.p;
        } else if (Application.B("셬Ɩ፴㞀젣\ud9cf苲梄").equalsIgnoreCase(var1)) {
            return this.k;
        } else if (Application.B("셻Ɓ፴㞐젯\ud9c5苴梓컋ኢࠐᢤ冴꜔").equalsIgnoreCase(var1)) {
            return this.r;
        } else if (Application.B("셣Ɩ፺㞔젲\ud9c3苴梀컜").equalsIgnoreCase(var1)) {
            return this.o;
        } else if (Application.B("셣Ƈ፯㞖").equalsIgnoreCase(var1)) {
            return this.n;
        } else if (Application.B("셸Ɛ፳㞃젢\ud9d4苽梄").equalsIgnoreCase(var1)) {
            return this.s;
        } else if (Application.B("셻Ɯ፬㞃젴\ud9d2苰梗컍ኩ࠙\u18ab冽꜖씤").equalsIgnoreCase(var1)) {
            return this.q;
        } else if (Application.B("셮Ɲ፺㞄젪\ud9c4苵梂컀ካࠟ\u18ad冶").equalsIgnoreCase(var1)) {
            return this.l;
        } else if (Application.B("셨Ɯ፵㞈젣\ud9c2若梈컞ኣࠅᢳ冰ꜙ씠弡߯ခ").equalsIgnoreCase(var1)) {
            return this.m;
        } else {
            return Application.B("셥Ɯ፯㞏젠\ud9c8苲梀컜ኣࠞᢤ冲꜒씵弦ߧည").equalsIgnoreCase(var1) ? this.v : null;
        }
    }

    private void a(TSLocationCallback var1) {
        List var2;
        List var10000 = var2 = this.i;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.i.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void b(TSLocationCallback var1) {
        List var2;
        List var10000 = var2 = this.j;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.j.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSHttpResponseCallback var1) {
        List var2;
        List var10000 = var2 = this.n;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.n.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSHeartbeatCallback var1) {
        List var2;
        List var10000 = var2 = this.o;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.o.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSActivityChangeCallback var1) {
        List var2;
        List var10000 = var2 = this.p;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.p.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSPowerSaveChangeCallback var1) {
        List var2;
        List var10000 = var2 = this.q;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.q.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSLocationProviderChangeCallback var1) {
        List var2;
        List var10000 = var2 = this.r;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.r.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSConnectivityChangeCallback var1) {
        List var2;
        List var10000 = var2 = this.m;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.m.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSEnabledChangeCallback var1) {
        List var2;
        List var10000 = var2 = this.l;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.l.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSScheduleCallback var1) {
        List var2;
        List var10000 = var2 = this.s;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.s.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSGeofencesChangeCallback var1) {
        TSGeofenceManager.getInstance(this.c).onGeofencesChange(var1);
    }

    private void a(TSGeofenceCallback var1) {
        List var2;
        List var10000 = var2 = this.k;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.k.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSNotificationActionCallback var1) {
        List var2;
        List var10000 = var2 = this.v;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.v.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(TSPlayServicesConnectErrorCallback var1) {
        List var2;
        List var10000 = var2 = this.t;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.t.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(final int var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.t){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.t.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSPlayServicesConnectErrorCallback)var2.next()).onPlayServicesConnectError(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(TSSecurityExceptionCallback var1) {
        List var2;
        List var10000 = var2 = this.u;
        BackgroundGeolocation var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.u.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    private void a(final ScheduleEvent var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.s){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.s.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSScheduleCallback)var2.next()).onSchedule(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(final ActivityTransitionEvent var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                ActivityChangeEvent var1x;
                var1x = new ActivityChangeEvent.<init>(var1);
                synchronized(BackgroundGeolocation.this.p){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.p.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSActivityChangeCallback)var2.next()).onActivityChange(var1x);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(final TSLocation var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.i){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.i.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSLocationCallback)var2.next()).onLocation(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void b(final int var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.i){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.i.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSLocationCallback)var2.next()).onError(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(final LocationProviderChangeEvent var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.r){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.r.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSLocationProviderChangeCallback)var2.next()).onLocationProviderChange(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(final HeartbeatEvent var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.o){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.o.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSHeartbeatCallback)var2.next()).onHeartbeat(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(final GeofenceEvent var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.k){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.k.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSGeofenceCallback)var2.next()).onGeofence(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void a(final MotionChangeEvent var1) {
        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.j){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.j.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSLocationCallback)var2.next()).onLocation(var1.getLocation());
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    private void b() {
        TSLog.logger.debug(TSLog.off(Application.B("떫愥뤪琶ᏺ쩕\udf86∪䖼釟∊㎔嗟襴竀ᨲ劁")));
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
        synchronized(this) {
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
        (new File(this.c.getCacheDir(), Application.B("\ude40㑮ᷩ웂褈輎奻뭯쑡銝\udf0fÙ蟍烴ꚥ낐\u19ceﱐའ\ue3b8涂ᘠ㌾喐\udfe5獬ꄅೱ㘞"))).delete();
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
            TSLog.logger.warn(TSLog.warn(Application.B("셌Ɯ፴㞁젪\ud9c4苁梍컉ኳࠢ\u18af冡꜇씨弬߭ဗ൱Ᵽ膘Ԍ읝鍦냽䓖졎\ue3cf囦鯉䉄龋腓\uf77f획쇉邼닄\u0b78鞫陔梲\u2e7d\ud8e3\ud9a8") + var3));
        }

        OnChangeCallback var4;
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        Boolean var1 = TSConfig.getInstance(BackgroundGeolocation.this.c).getEnabled();
                        if (LifecycleManager.f().b()) {
                            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(BackgroundGeolocation.this.c, Application.B("ម\uf1c4\uda68\ue028饭ឦおखᯞؐꂚ裿瑙"), var1));
                        }

                        <undefinedtype> var10000 = this;
                        synchronized(BackgroundGeolocation.this.l){}

                        boolean var10001;
                        Throwable var23;
                        Iterator var24;
                        try {
                            var24 = BackgroundGeolocation.this.l.iterator();
                        } catch (Throwable var22) {
                            var23 = var22;
                            var10001 = false;
                            throw var23;
                        }

                        Iterator var2 = var24;

                        while(true) {
                            boolean var25;
                            try {
                                var25 = var2.hasNext();
                            } catch (Throwable var20) {
                                var23 = var20;
                                var10001 = false;
                                break;
                            }

                            if (!var25) {
                                try {
                                    return;
                                } catch (Throwable var19) {
                                    var23 = var19;
                                    var10001 = false;
                                    break;
                                }
                            }

                            try {
                                ((TSEnabledChangeCallback)var2.next()).onEnabledChange(var1);
                            } catch (Throwable var21) {
                                var23 = var21;
                                var10001 = false;
                                break;
                            }
                        }

                        throw var23;
                    }
                });
            }
        }.<init>();
        var1.onChange(Application.B("셮Ɲ፺㞄젪\ud9c4苵"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                TSLocation.resetGeofenceTemplate();
            }
        }.<init>();
        var1.onChange(Application.B("셬Ɩ፴㞀젣\ud9cf苲梄컼ኯࠜᢺ冿꜐씵弪"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                TSLocation.resetLocationTemplate();
            }
        }.<init>();
        var1.onChange(Application.B("셧Ɯ፸㞇젲\ud9c8苾梏컼ኯࠜᢺ冿꜐씵弪"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                if (var1.getEnabled() && !var1.getIsMoving()) {
                    HeartbeatService.start(BackgroundGeolocation.this.c);
                }

            }
        }.<init>();
        var1.onChange(Application.B("셣Ɩ፺㞔젲\ud9c3苴梀컜ኃࠟᢾ冶꜃씷弮ߤ"), var4);
        var4 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                if (var1.getEnabled()) {
                    ActivityRecognitionService.start(BackgroundGeolocation.this.c);
                }

            }
        }.<init>();
        var1.onChange(Application.B("셥Ɯ፯㞏젠\ud9c8苲梀컜ኣࠞᢤ函꜂씵弦߫ဏന"), var4);
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
        }.<init>();
        var1.onChange(Application.B("셧Ɯ፸㞇젲\ud9c8苾梏컩\u12bfࠅᢢ冼꜃씨張ߩတസⱹ膘Կ읎鍶냡䓟졜\ue3d9"), var4);
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
        }.<init>();
        var1.onChange(Application.B("셯ƚ፨㞇젤\ud9cd苴梬컇ኾ࠘ᢥ冽ꜰ씢弻ߡဒസⱢ膏Ը읛鍣냵䓎졊\ue3de"), var4);
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
            TSConfig var7 = var2;
            Runnable var5;
            var5 = new Runnable() {
                {
                    this.a = var2;
                }

                public void run() {
                    BackgroundGeolocation.this.ready(this.a);
                }
            }.<init>(var1);
            var1 = new TSCallback() {
                {
                    this.a = var2;
                }

                public void onSuccess() {
                    BackgroundGeolocation.getUiHandler().post(this.a);
                }

                public void onFailure(String var1) {
                    TSLog.logger.warn(TSLog.warn(var1));
                    BackgroundGeolocation.getUiHandler().post(this.a);
                }
            }.<init>(var5);
            var7.loadConfig(var1);
        } else if (this.e.get()) {
            if (var2.getEnabled()) {
                BackgroundGeolocation var6 = this;
                TSCurrentPositionRequest var3 = ((Builder)((Builder)((Builder)(new Builder(this.c)).setPersist(false)).setSamples(1)).setDesiredAccuracy(100)).setMaximumAge(60000L).build();
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
            var4 = new LocationProviderChangeEvent.<init>(this.c);
            EventBus.getDefault().post(var4);
        }
    }

    public void start(TSCallback var1) {
        if (this.h != null) {
            var1.onFailure(Application.B("㳓䠉룧㼈꧍\uf6f4ᠺ⁃鬚㢵ꖙᶝ琣혨䘯\u0e98뭏ḿ맖첰✥튍雥\ua48e㧋搨摵\ue144佣ቹ\u082e♅㚟\uf6aa䀓吟찌ᵎК鄟衠\uf68c躂䜉螭"));
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
        }.<init>();
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
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var2, Application.B("鲹瘝틢펴"), var3));
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
            TSLog.logger.warn(TSLog.warn(Application.B("諁겱饿\ue784暻즰薀\udd75砏ᙾ颸\ue035\udb86ⲋ䩱䨘؟\ue3c5숝)䦏ꓶ㒎䋗젻㱣녆\ua7ea้炔뗯䙒㌆廙ᯌང馺㝔")));
            return false;
        }
    }

    public void stopSchedule() {
        TSScheduleManager.getInstance(this.c).stop();
    }

    public void startGeofences(TSCallback var1) {
        if (this.h != null) {
            var1.onFailure(Application.B("吿綃ડᏁ\ued52㑇䴥僈黃鹧淌㋊\uef26蜞Лḇ钿＂럸肃ἳ캭콺郍硁冒\ue6e0퐌塍圣ﳴ\uda83骮ꥡ퓽\ued99\ue4a1⬂䝘袳๑ﲪ\ue4c9鶊프"));
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
        }.<init>();
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
        }.<init>();
        this.stop(var1);
    }

    public void changePace(boolean var1, TSCallback var2) {
        TSConfig var3;
        if (!(var3 = TSConfig.getInstance(this.c)).getEnabled()) {
            var2.onFailure(Application.B("\ue215\ue500\uee71➊本\udc4d吟\uebf7흿츚欅텤㇋ዲ쵳ၷ剅\uef10蝛狹羁\ue3f7⅒믧曋갍\udc92ኰ덁臤⒊幷몱"));
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
        }.<init>();
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
                    TSLog.logger.warn(TSLog.warn(Application.B("\udf63ꢧ6呡陭龫顂瞩⢅ဗⒷ㏳\ud81e鹷\u2bf6\udc99摁ꃣꪕ⌈䟬ⴷ垡⯍ꅔ爚")));
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
        }.<init>();
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
        }.<init>();
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
                            var2.onFailure(Application.B("㔅ἶ얥"));
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
        }.<init>();
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
        }.<init>();
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
        }.<init>();
        this.removeGeofences(var1);
    }

    public void geofenceExists(String var1, TSGeofenceExistsCallback var2) {
        TSGeofenceManager.getInstance(this.c).geofenceExists(var1, var2);
    }

    public Float getOdometer() {
        return TSConfig.getInstance(this.c).getOdometer();
    }

    public void setOdometer(final Float var1, final TSLocationCallback var2) {
        final TSLocationManager var3;
        if (!(var3 = TSLocationManager.getInstance(this.c)).isLocationServicesEnabled()) {
            var2.onError(1);
            this.b(1);
        } else {
            getThreadPool().execute(new Runnable() {
                public void run() {
                    TSLocationManager var10000 = var3;
                    <undefinedtype> var10001 = this;
                    Float var1x = var1;
                    var10000.setOdometer(var1x, var2);
                }
            });
        }
    }

    public JSONArray getLocations() {
        List var10000 = c(this.c).all();
        JSONArray var2;
        var2 = new JSONArray.<init>();
        Iterator var1 = var10000.iterator();

        while(var1.hasNext()) {
            var2.put(((LocationModel)var1.next()).json);
        }

        return var2;
    }

    public void getLocations(TSGetLocationsCallback var1) {
        getThreadPool().execute(new BackgroundGeolocation.v0(var1));
    }

    public void insertLocation(JSONObject var1, TSInsertLocationCallback var2) {
        if (!var1.has(Application.B("㔝\ue9a3⩨婇聽뿺\uf137䦍돜"))) {
            var2.onFailure(Application.B("㔀\ue9a4⩶婇聼뿺\uf11a䦏돏︴蕱躈ݹ룷Ͻ凱뚺᯿亳ੳ庌憾喀ྏ㠥춑瀟訡\uddc2䡻ꦝ鹯꒻䭑‖쬬큏퉹䰄쩸亣蛛꺝복"));
        } else if (!var1.has(Application.B("㔊\ue9a5⩪婐聪뿽"))) {
            var2.onFailure(Application.B("㔀\ue9a4⩶婇聼뿺\uf11a䦏돏︴蕱躈ݹ룷Ͻ凱뚺᯿亳ੳ庌憾喀ྏ㠥춑瀟訡\uddc2䡻ꦝ鹯꒻䭑⁅쭸큇툴䰂쩤亸蛈꺔볶£恑릷"));
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
        }.<init>();
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
        }.<init>();
        this.destroyLocations(var1);
    }

    public void destroyLocation(String var1) {
        TSCallback var2;
        var2 = new TSCallback() {
            public void onSuccess() {
            }

            public void onFailure(String var1) {
            }
        }.<init>();
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
                TSLocationManagerActivity.start(BackgroundGeolocation.this.c, Application.B("ꤏ\udeb6㼅\uf340⾾肪夑ᮚ\uf2bb\ue97c엔韛䑢ꅭ✣髪"));
            }

            public void onPermissionDenied(DeniedPermissions var1x) {
                var1.onFailure(TSProviderManager.PERMISSION_DENIED);
            }
        });
    }

    public void requestTemporaryFullAccuracy(String var1, final TSRequestPermissionCallback var2) {
        if (ContextCompat.checkSelfPermission(this.c, Application.B("\uea76芐\uee1b窎ΐ㲰鍔堎ꬳࠤ쎩娘翥྿⌨㮑ꊎ禔누ꀂ껓遗\ufe6c✾쁏䝰ૺ瞞ᵋ훼宺쳡ꟾプ\u0e62델\uf75e긯ᦋ")) == 0) {
            var2.onSuccess(TSProviderManager.ACCURACY_AUTHORIZATION_FULL);
        } else {
            com.transistorsoft.locationmanager.util.c.f(this.c, new PermissionRequestListener() {
                public void onPermissionGranted() {
                    <undefinedtype> var10000 = this;
                    <undefinedtype> var10001 = this;
                    <undefinedtype> var10002 = this;
                    TSConfig var1 = TSConfig.getInstance(BackgroundGeolocation.this.c);
                    TrackingService.changePace(BackgroundGeolocation.this.c, var1.getIsMoving(), (TSLocationCallback)null);
                    BackgroundGeolocation var2x;
                    (var2x = BackgroundGeolocation.this).a(new LocationProviderChangeEvent(var2x.c));
                    var2.onSuccess(TSProviderManager.ACCURACY_AUTHORIZATION_FULL);
                }

                public void onPermissionDenied(DeniedPermissions var1) {
                    var2.onFailure(TSProviderManager.ACCURACY_AUTHORIZATION_REDUCED);
                }
            });
        }
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
        TSLog.logger.debug(TSLog.ok(Application.B("販䫑\u2ffd蜂鴎ꨃ\ueb10") + var1));
        if (var1.equalsIgnoreCase(Application.B("貫䫂⿷蜊鴟꩗\ueb53\ue7e0ꢛ텎\udff7㫪徽\ud9c1痔"))) {
            TSGeofenceManager.getInstance(this.c).removeListeners();
        } else if (Application.B("貭䫒\u2fec蜄鴕ꩋ\ueb59\ue7ffꢉ텙\udff6㫤徽").equalsIgnoreCase(var1)) {
            HttpService.getInstance(this.c).removeListeners();
        } else {
            List var3;
            if ((var3 = this.a(var1)) != null) {
                List var10000 = var3;
                List var10001 = var3;
                synchronized(var3) {
                    var10001.clear();
                }
            }
        }

    }

    public Object removeListener(String var1, Object var2) {
        Boolean var3 = null;
        if (var1.equalsIgnoreCase(Application.B("춓潄䶞銌儔⟲徳ᵶ恏\uf882\uf542\uee59颸軔筍"))) {
            TSGeofenceManager.getInstance(this.c).removeListener(var1, var2);
        } else if (Application.B("축潔䶅銂儞⟮徹ᵩ恝\uf895\uf543\uee57颸").equalsIgnoreCase(var1)) {
            HttpService.getInstance(this.c).removeListener(var1, var2);
        } else {
            List var4;
            if ((var4 = this.a(var1)) != null) {
                var3 = var4.remove(var2);
            }
        }

        if (var3 != null) {
            TSLog.logger.debug(TSLog.ok(Application.B("춆潄䶜銅儇⟹徜ᵺ恏\uf895\uf54f\uee56颳軁笈焛竻ꍩ訷㜩ᔨ⒯") + var1));
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("춲潀䶘銆儔⟸忰ᵧ恓\uf8c1\uf558\uee5d颻軜筞焛竁ꍥ訪㜩ᕷⓡ줪냍糍ᰇ抋듴Ԣ\uea7f⦝\uf36c苆") + var1));
        }

        return var3;
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onTemplateError(TemplateErrorEvent var1) {
        TSMediaPlayer.getInstance().debug(this.c, Application.B("╲&\uda32姰但ꌙ䵶瞤\uf897⪑\u0bce⍈\u0601△쬪変튡鴥\uedb9쉨槇닸转픙廳钽쬎췊\ue65c\udef6됮䪈蚡윆噰₦噏ꦍ\ue80c琽"));
        Handler var10000 = getUiHandler();
        Context var10003 = this.c;
        String var2 = Application.B("╟:\uda2b妿位ꌙ䵴瞨\uf8d8⪞்⌉؊■쬿夃튡鵚\uedbd쉳榔단轠픳廵铴") + var1.getTemplateName();
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
            TSLog.logger.warn(TSLog.warn(Application.B("酬ﱃ\udcc1┅疫\ue2cb됺漜\u1cad㜞⺴푕珎㐙ꌃ돠ꦚ汖\udd31튥䯲춷\uf5f8글\u2d78됂ｔ⊮\ue1c2簛阇\uddd5\udbe5偡騹\udbb4䱔僬\uf3c1ഺ逽筱街⹄鼜ꭱ捯\ude3f萺埾哮賷巘\ud936ృ郻") + var1));
        } else {
            this.a(var1);
            if (LifecycleManager.f().b()) {
                Context var2 = this.c;
                JSONObject var3 = var1.getState();
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var2, Application.B("酖ﱇ\udcc7┏疽\ue2db됲潙"), var3));
            }

        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onActivityTransitionEvent(ActivityTransitionEvent var1) {
        if (LifecycleManager.f().b()) {
            ActivityChangeEvent var2;
            var2 = new ActivityChangeEvent.<init>(var1);
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("␅璹牋㚌髴蒷漭뿨볜怸ᱢ\udd64⚑獪"), var2));
        }

        this.a(var1);
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onLocationChange(TSLocation var1) {
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("ⅸ㙜뎊鞙聩Ἑᾆ뺐"), var1));
        }

        this.a(var1);
    }

    public void fireNotificationActionListeners(final String var1) {
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("䓐Ӻ섃䌬胯Ⲓ뮥\uea6c\ue36a䂪┭暉嫈ݗ⌎쪈ǡ⤒"), var1));
        }

        getUiHandler().post(new Runnable() {
            public void run() {
                <undefinedtype> var10000 = this;
                synchronized(BackgroundGeolocation.this.v){}

                boolean var10001;
                Throwable var23;
                Iterator var24;
                try {
                    var24 = BackgroundGeolocation.this.v.iterator();
                } catch (Throwable var22) {
                    var23 = var22;
                    var10001 = false;
                    throw var23;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var23 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            return;
                        } catch (Throwable var19) {
                            var23 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((TSNotificationActionCallback)var2.next()).onClick(var1);
                    } catch (Throwable var21) {
                        var23 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var23;
            }
        });
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onLocationProviderChange(LocationProviderChangeEvent var1) {
        this.b = var1;
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("\u312f맢햄颊띰詎ᛲ糖轁箎ꇒ䪕\uabfd襒"), var1));
        }

        this.a(var1);
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onConnectivityChange(ConnectivityChangeEvent var1) {
        BackgroundGeolocation var10000 = this;
        synchronized(this.m){}

        Throwable var46;
        Iterator var47;
        boolean var10001;
        try {
            var47 = var10000.m.iterator();
        } catch (Throwable var45) {
            var46 = var45;
            var10001 = false;
            throw var46;
        }

        Iterator var3 = var47;

        boolean var48;
        try {
            var48 = LifecycleManager.f().b();
        } catch (Throwable var44) {
            var46 = var44;
            var10001 = false;
            throw var46;
        }

        if (var48) {
            try {
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("䘒鈺\u2d7cჟ뮉冽喬➭例傥䦓嶵㖍離鍥㗖쐜ᄔ"), var1));
            } catch (Throwable var43) {
                var46 = var43;
                var10001 = false;
                throw var46;
            }
        }

        while(true) {
            try {
                var48 = var3.hasNext();
            } catch (Throwable var41) {
                var46 = var41;
                var10001 = false;
                break;
            }

            if (!var48) {
                try {
                    return;
                } catch (Throwable var40) {
                    var46 = var40;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((TSConnectivityChangeCallback)var3.next()).onConnectivityChange(var1);
            } catch (Throwable var42) {
                var46 = var42;
                var10001 = false;
                break;
            }
        }

        throw var46;
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onPowerSaveModeChange(PowerSaveModeChangeEvent var1) {
        BackgroundGeolocation var10000 = this;
        synchronized(this.q){}

        Throwable var46;
        Iterator var47;
        boolean var10001;
        try {
            var47 = var10000.q.iterator();
        } catch (Throwable var45) {
            var46 = var45;
            var10001 = false;
            throw var46;
        }

        Iterator var3 = var47;

        boolean var48;
        try {
            var48 = LifecycleManager.f().b();
        } catch (Throwable var44) {
            var46 = var44;
            var10001 = false;
            throw var46;
        }

        if (var48) {
            try {
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("⣩箽孹퇏\u0383ޚꢳ뿑\u0dfc\ue101봚剷힊\ueb7fừ"), var1));
            } catch (Throwable var43) {
                var46 = var43;
                var10001 = false;
                throw var46;
            }
        }

        while(true) {
            try {
                var48 = var3.hasNext();
            } catch (Throwable var41) {
                var46 = var41;
                var10001 = false;
                break;
            }

            if (!var48) {
                try {
                    return;
                } catch (Throwable var40) {
                    var46 = var40;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((TSPowerSaveChangeCallback)var3.next()).onPowerSaveChange(var1.isPowerSaveMode());
            } catch (Throwable var42) {
                var46 = var42;
                var10001 = false;
                break;
            }
        }

        throw var46;
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onLocationError(LocationErrorEvent var1) {
        TSLog.logger.warn(TSLog.warn(Application.B("ᎂ鼼ꄐ芅楖譅凼w펍螔⋺爆\u9fecꏰर笤") + var1.errorCode));
        TSConfig var2 = TSConfig.getInstance(this.c);
        TSMediaPlayer.getInstance().debug(this.c, Application.B("Ꮊ鼠ꄟ芋楁譍凧p폂螟⋥爕\u9fedꏣ७筡\ueac8퇲萄֎\uf014݄霛ᶄ穤覍\ud9d3"));
        if (var1.errorCode == 1 && var2.getDebug()) {
            Handler var10000 = getUiHandler();
            Context var4 = this.c;
            String var3 = var1.message;
            var10000.post(new d(var4, Application.B("ᎂ鼼ꄐ芅楖譅凼w펍螂⋭爆\u9ff5ꏫ३筡\ueac9톍萄֎\uf000\u074c霦ᶟ穠覛"), var3));
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
            synchronized(var2) {
                var10002.w.clear();
            }

            this.w.add(var1);
            if (LifecycleManager.f().b()) {
                com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("썹嫥ⱇ噮ྃ毢エᒪ"), var1));
            }

            this.a(var1);
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onSecurityException(SecurityExceptionEvent var1) {
        BackgroundGeolocation var10000 = this;
        TSLog.logger.warn(TSLog.warn(Application.B("ﴸ빅ฌ䙜㇜㋮⿄飞캑킴\udd2f闶畀㢶邙띿荩\uf4b0뉰ો凛灖ꎟ\uecac\uf5dcꊁ\uf4a5\uf7a3峵晫螇氠\ue7f0墦棘컀✌芇ᖑꂙꯡ咈\ue341") + var1.toString()));
        synchronized(this.u){}

        boolean var10001;
        Throwable var23;
        Iterator var24;
        try {
            var24 = var10000.u.iterator();
        } catch (Throwable var22) {
            var23 = var22;
            var10001 = false;
            throw var23;
        }

        Iterator var2 = var24;

        while(true) {
            boolean var25;
            try {
                var25 = var2.hasNext();
            } catch (Throwable var20) {
                var23 = var20;
                var10001 = false;
                break;
            }

            if (!var25) {
                try {
                    return;
                } catch (Throwable var19) {
                    var23 = var19;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((TSSecurityExceptionCallback)var2.next()).onSecurityException(var1);
            } catch (Throwable var21) {
                var23 = var21;
                var10001 = false;
                break;
            }
        }

        throw var23;
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void _onHttpResponse(HttpResponse var1) {
        if (LifecycleManager.f().b()) {
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("蓐ɏ婥텦"), var1));
        }

        BackgroundGeolocation var10000 = this;
        synchronized(this.n){}

        boolean var10001;
        Throwable var23;
        Iterator var24;
        try {
            var24 = var10000.n.iterator();
        } catch (Throwable var22) {
            var23 = var22;
            var10001 = false;
            throw var23;
        }

        Iterator var2 = var24;

        while(true) {
            boolean var25;
            try {
                var25 = var2.hasNext();
            } catch (Throwable var20) {
                var23 = var20;
                var10001 = false;
                break;
            }

            if (!var25) {
                try {
                    return;
                } catch (Throwable var19) {
                    var23 = var19;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((TSHttpResponseCallback)var2.next()).onHttpResponse(var1);
            } catch (Throwable var21) {
                var23 = var21;
                var10001 = false;
                break;
            }
        }

        throw var23;
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
            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(this.c, Application.B("锵码䩺㱊⟴᱖䬔睝긴\udbc7녈裾"), var1));
        }

        this.a(var1);
    }

    public void onActivityResumed() {
        TSScheduleManager.getInstance(this.c).cancelOneShot(Application.B("鷅\uf81cฆ齅瞜࠻쨤堵炸"));
    }

    public void onActivityStopped() {
        TSConfig var1;
        if ((var1 = TSConfig.getInstance(this.c)).getEnabled() && var1.getEnableHeadless() && !var1.getStopOnTerminate()) {
            TSScheduleManager.getInstance(this.c).oneShot(Application.B("肬᭢넢\uf444ꂽ봻䟑敬㋰Ṯ\ue8ab쒯⥺偰䖌"), 5000L, true);
        }

    }

    public void onActivityDestroy() {
        this.e.set(false);
        TSLocationManager.getInstance(this.c).stopWatchPosition();
        TSScheduleManager.getInstance(this.c).cancelOneShot(Application.B("轝魃柟㭗ᴞ利솕ỿꩪ車䃭Ùᄧ\udd2b돲"));
        LifecycleManager.f().a(true);
        Context var10003 = this.c;
        com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var10003, Application.B("载魣柿㭷ᴾ什솵ởꩊ"), TSConfig.getInstance(var10003).toJson()));
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

                <undefinedtype> var10000 = this;
                StringBuilder var2 = (new StringBuilder()).append(TSLog.header(Application.B("\ue136䞚猼\ud817\ufff5햗큲蟦̌焩갾䔯譌릠\ue5df扆骹흯⁑㨊\ude2fxꠊ\udead틣䙮"))).append(TSLog.boxRow(Application.B("\ue108䞏猺\ud809\ufffb햚큒蟪̈焭갣䔸謍릣\ue5db戏骹") + var1.getStopOnTerminate())).append(TSLog.boxRow(Application.B("\ue11e䞕猴\ud81b\uffd8햑큢螵͚") + var1.getEnabled()));
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
                    Integer var4;
                    if (var4 = var3.getAutoSyncThreshold() > 0 && com.transistorsoft.locationmanager.data.sqlite.b.a(BackgroundGeolocation.this.c).count() < var4) {
                        return;
                    }

                    var2.flush();
                }
            } else {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        x0.this.b.onFailure(Application.B("뗸ꓒ\uecb8ᅓﮜ똎繶촵糧晇ಣᧆ늝ᙍ휐坲잶蟨퉜郄\ud7ac쭶"));
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
            if (Boolean.valueOf(BackgroundGeolocation.c(BackgroundGeolocation.this.c).persist((TSLocation)this.a))) {
                if (TSConfig.getInstance(BackgroundGeolocation.this.c).getAutoSync()) {
                    BackgroundGeolocation.this.sync();
                }
            } else {
                Log.w(Application.B("㬭ꖕ쀆켲\u09bb䤼兟睴Ͳ韂놄嬿⤟躨陃茫\uf6c0"), Application.B("㬰ꖈ쀙켘ঊ䤉儋睛͜韥놅嬋⤣躌") + this.a);
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
                                a1.this.a.onFailure(Application.B("꣑穞㴦歮錆睽傸댦侩띒饅䭓ꇷ㥫ℒų諸㻧꒰ᶜ튟"));
                            }
                        });
                    }

                    return;
                }

                if (!var1.isBusy()) {
                    var1.flush(this.a);
                } else {
                    TSLog.logger.info(TSLog.notice(Application.B("櫭蒑韫\ueabe⋹媈羘蹢턲됟ꔙ䍘֊밡醆궙ჄǼ践嵖牵ﱴⲩ\udbac⫵∦箊弆涮戤㽿髻컖罯춬\udbc1摀\u1976壣⾊爓橢轀\ud87eᆵ")));
                    if (this.a != null) {
                        BackgroundGeolocation.getUiHandler().post(new Runnable() {
                            public void run() {
                                a1.this.a.onFailure(Application.B("ℯ섒ゲ螧풟흑菋広ᐪ␞좆㌊⦩퇍ぬ≰跏\ud917\uf45f"));
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
            }.<init>();
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
                        z0.this.b.onFailure(Application.B("\uf8a2\ue5be譚翠艬ᘲ䑾嵓밐耎\ue1ef滘辀㜄ͳ\ue59d痛⛤螱쵂ҽ\udd32㜆啷長찲"));
                    }
                });
            } else {
                byte var2;
                boolean var10000 = (var2 = this.a.equalsIgnoreCase(Application.B("笸炻쵤톑⥐"))) != var1.getTrackingMode();
                var1.setTrackingMode(Integer.valueOf(var2));
                Boolean var3 = var1.getEnabled();
                Log.i(Application.B("笟炜쵉톌⥇섢\ue017ꬻ\udae3醊ὀ\uf8bb⩳䘱㍾ㅎ\uf474"), Application.B("筦烯쵀톍⥅섡\ue00fꬷ\udab6釄") + var3 + Application.B("筫兝촥") + true + Application.B("筧烯쵱톑⥅선\ue008ꬻ\udae2醃ὀ\uf8b5⩹䘵㌣ㄋ") + var1.getTrackingMode());
                TSLocationCallback var4;
                var4 = new TSLocationCallback() {
                    public void onLocation(TSLocation var1) {
                        BackgroundGeolocation.this.h = null;
                    }

                    public void onError(Integer var1) {
                        BackgroundGeolocation.this.h = null;
                    }
                }.<init>();
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
            String var4 = Application.B("Ⲹᣓᥩ\u19ac탙䲎ꪋ뾻ⵢٟ큥㒢᱆\ue926✗䗾\ue049쨃뀳ꏣ") + var2.getMessage();
            TSLog.logger.error(TSLog.error(var4 + Application.B("⳧") + var3.toJson().toString() + Application.B("⳧")), var2);
            UncaughtExceptionHandler var5;
            if ((var5 = var10000.a) != null) {
                var5.uncaughtException(var1, var2);
            } else {
                System.exit(2);
            }

        }
    }
}
