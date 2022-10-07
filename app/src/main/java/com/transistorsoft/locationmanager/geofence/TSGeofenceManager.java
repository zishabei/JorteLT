//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.geofence;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Geofence.Builder;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.intentfilter.androidpermissions.PermissionManager.PermissionRequestListener;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGeofenceExistsCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGeofencesChangeCallback;
import com.transistorsoft.locationmanager.data.sqlite.GeofenceDAO;
import com.transistorsoft.locationmanager.event.BootEvent;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.event.GeofencesChangeEvent;
import com.transistorsoft.locationmanager.event.HeadlessEvent;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.service.ActivityRecognitionService;
import com.transistorsoft.locationmanager.service.GeofencingService;
import com.transistorsoft.locationmanager.util.b;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TSGeofenceManager implements Runnable {
    private static final int r = 99;
    private static TSGeofenceManager s;
    private static final long t = 1000L;
    public static final String ACTION_STATIONARY_GEOFENCE = Application.B("滏姥帷䢧飓峒謦須빶\u1c8e鮎氥鴝攛葁䩶窔뉒㘘");
    public static final float MINIMUM_STATIONARY_RADIUS = 150.0F;
    private final Context a;
    private final ArrayList<String> b;
    private final List<TSGeofencesChangeCallback> c;
    private final Location d;
    private Location e;
    private final AtomicLong f;
    private Runnable g;
    private final List<String> h;
    private final long i;
    private final long j;
    private final AtomicBoolean k;
    private final AtomicBoolean l;
    private final AtomicBoolean m;
    private final AtomicInteger n;
    private final AtomicBoolean o;
    private final AtomicBoolean p;
    private final Handler q;

    public static TSGeofenceManager getInstance(Context var0) {
        if (s == null) {
            s = a(var0.getApplicationContext());
        }

        return s;
    }

    private static synchronized TSGeofenceManager a(Context var0) {
        if (s == null) {
            s = new TSGeofenceManager(var0.getApplicationContext());
        }

        return s;
    }

    private TSGeofenceManager(Context var1) {
        Context var10004 = var1;
        super();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.d = new Location(Application.B("㖠벐\uf77e聧犅댖캗鿀蜲⇰뼵Ⲫ晈꽇\udc1d\uefaa冬"));
        this.f = new AtomicLong(0L);
        this.h = new ArrayList();
        this.k = new AtomicBoolean(false);
        this.l = new AtomicBoolean(false);
        this.m = new AtomicBoolean(false);
        this.n = new AtomicInteger(-1);
        this.o = new AtomicBoolean(false);
        this.p = new AtomicBoolean(false);
        TSConfig var2 = TSConfig.getInstance(var1.getApplicationContext());
        this.a = var10004.getApplicationContext();
        this.q = new Handler(Looper.getMainLooper());
        this.i = 30000L;
        this.j = 250L;
        this.k.set(false);
        this.l.set(false);
        this.m.set(false);
        this.n.set(-1);
        if (var2.getIsMoving()) {
            this.startMonitoringSignificantLocationChanges();
        }

        EventBus var3;
        if (!(var3 = EventBus.getDefault()).isRegistered(this)) {
            var3.register(this);
        }

    }

    private void b() {
        List var1;
        List var10001 = var1 = this.h;
        TSGeofenceManager var10002 = this;
        synchronized(var1) {
            var10002.h.clear();
        }

        this.d();
    }

    private void g() {
        this.n.set(GeofenceDAO.getInstance(this.a).count());
    }

    private void a(Location var1) {
        if (!this.l.get()) {
            TSLog.logger.warn(TSLog.warn(Application.B("姫\ue3bcẐ䊢飢獫舋\uef47㇂\uf3a3䮓狽蠠宮謟ᬙ⩩汖⠥揂벝敶뽳\uee52㎇\u20cdဈހ\u2e56")));
        } else if (var1 == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("姺\ue397Ẳ䊤飸獹舋\uef4dㆁ\uf3b1䮷狨蠦寯謖ᬉ⩷氚⡬揝볒敱뽻\uee55㎏\u20c0ည")));
        } else {
            BackgroundGeolocation.getThreadPool().execute(new TSGeofenceManager.f(var1));
        }
    }

    private void f() {
        List var1;
        List var10003 = var1 = this.h;
        TSGeofenceManager var10004 = this;
        synchronized(var1) {
            var10004.h.clear();
        }

        this.d();
        TSLog.logger.debug(TSLog.off(Application.B("㋓\uf876\u0af7䅠\uddabﷅ걢\ue13c䷐졭韰ꯋ〄\u1776벿洰ꌖ郎顟ꤱ킎\udbd6셋\uea97Ž")));
        GeofencingClient var5 = LocationServices.getGeofencingClient(this.a);
        List var2;
        if (!(var2 = GeofenceDAO.getInstance(this.a).getIdentifiers()).isEmpty()) {
            var5.removeGeofences(var2);
        }

        TSGeofenceManager var10000 = this;
        GeofencesChangeEvent var4;
        var4 = new GeofencesChangeEvent.<init>();
        var10000.a(var4);
    }

    private void a(List<String> var1) {
        if (var1.size() > 0) {
            List var10000 = var1;
            ArrayList var61;
            var61 = new ArrayList.<init>();
            List var2;
            synchronized(var2 = this.h){}

            ArrayList var68;
            label551: {
                boolean var10001;
                Throwable var62;
                Iterator var63;
                try {
                    var63 = var10000.iterator();
                } catch (Throwable var60) {
                    var62 = var60;
                    var10001 = false;
                    throw var62;
                }

                Iterator var3 = var63;

                while(true) {
                    boolean var64;
                    try {
                        var64 = var3.hasNext();
                    } catch (Throwable var56) {
                        var62 = var56;
                        var10001 = false;
                        break;
                    }

                    if (!var64) {
                        List var67;
                        TSGeofenceManager var10002;
                        try {
                            var68 = var61;
                            var67 = var2;
                            var10002 = this;
                            this.h.removeAll(var61);
                        } catch (Throwable var55) {
                            var62 = var55;
                            var10001 = false;
                            break;
                        }

                        try {
                            var10002.d();
                            break label551;
                        } catch (Throwable var54) {
                            var62 = var54;
                            var10001 = false;
                            break;
                        }
                    }

                    TSGeofenceManager var65;
                    String var66;
                    try {
                        var65 = this;
                        var66 = (String)var3.next();
                    } catch (Throwable var59) {
                        var62 = var59;
                        var10001 = false;
                        break;
                    }

                    String var4 = var66;

                    try {
                        var64 = var65.h.contains(var4);
                    } catch (Throwable var58) {
                        var62 = var58;
                        var10001 = false;
                        break;
                    }

                    if (var64) {
                        try {
                            var61.add(var4);
                        } catch (Throwable var57) {
                            var62 = var57;
                            var10001 = false;
                            break;
                        }
                    }
                }

                throw var62;
            }

            if (var68.size() > 0) {
                LocationServices.getGeofencingClient(this.a).removeGeofences(var61);
            }
        }

    }

    private void a(final GeofencesChangeEvent var1) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            public void run() {
                if (LifecycleManager.f().b()) {
                    <undefinedtype> var10002 = this;
                    Context var33 = TSGeofenceManager.this.a;
                    GeofencesChangeEvent var1x = var1;
                    com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var33, Application.B("跸⟳诶췼줤䙋✦衿ꏒ釒됃⋃둂萠䵿"), var1x));
                } else {
                    <undefinedtype> var10000 = this;
                    synchronized(TSGeofenceManager.this.c){}

                    Throwable var34;
                    Iterator var35;
                    boolean var10001;
                    try {
                        var35 = TSGeofenceManager.this.c.iterator();
                    } catch (Throwable var32) {
                        var34 = var32;
                        var10001 = false;
                        throw var34;
                    }

                    Iterator var2 = var35;

                    boolean var36;
                    try {
                        var36 = var35.hasNext();
                    } catch (Throwable var31) {
                        var34 = var31;
                        var10001 = false;
                        throw var34;
                    }

                    if (var36) {
                        while(true) {
                            try {
                                var36 = var2.hasNext();
                            } catch (Throwable var29) {
                                var34 = var29;
                                var10001 = false;
                                throw var34;
                            }

                            if (!var36) {
                                break;
                            }

                            try {
                                ((TSGeofencesChangeCallback)var2.next()).onGeofencesChange(var1);
                            } catch (Throwable var30) {
                                var34 = var30;
                                var10001 = false;
                                throw var34;
                            }
                        }
                    }

                    try {
                        ;
                    } catch (Throwable var28) {
                        var34 = var28;
                        var10001 = false;
                        throw var34;
                    }
                }
            }
        });
    }

    private void d() {
        Editor var1;
        Editor var10000 = var1 = this.a.getSharedPreferences(TSGeofenceManager.class.getCanonicalName(), 0).edit();
        List var2;
        List var10001 = var2 = this.h;
        Editor var10002 = var1;
        synchronized(var2){}
        String var10003 = Application.B("뻧\u1cca⻟▋崺⿄\ue1c4\uf75e䐴诐ꪹ섊䄧ፐ槒\ude05\uec4f㨺谆派纜");

        Throwable var9;
        boolean var10;
        try {
            var10002.putStringSet(var10003, new HashSet(this.h));
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            TSLog.logger.debug(Application.B("龳\ue288⺐◅崃⿕\ue1d9\uf75f䐸诇ꪄ셎䄯ፑ槈\ude05\uec5d㨼谑洩纋酶ᾶ\ue2fd龊轩\uee47赀싈溋⯈ꆆ阹") + this.h);
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }

        var10000.apply();
    }

    private boolean c() {
        TSGeofenceManager var10000 = this;
        synchronized(this.d){}

        boolean var10001;
        Throwable var8;
        long var9;
        try {
            var9 = var10000.d.getTime();
        } catch (Throwable var7) {
            var8 = var7;
            var10001 = false;
            throw var8;
        }

        boolean var1;
        if (var9 > 0L) {
            var1 = true;
        } else {
            var1 = false;
        }

        try {
            return var1;
        } catch (Throwable var6) {
            var8 = var6;
            var10001 = false;
            throw var8;
        }
    }

    private void b(Location var1) {
        Location var2;
        Location var10000 = var2 = this.d;
        TSGeofenceManager var10001 = this;
        synchronized(var2) {
            var10001.d.set(var1);
        }
    }

    private void e() {
        Location var1;
        Location var10000 = var1 = this.d;
        TSGeofenceManager var10001 = this;
        synchronized(var1) {
            var10001.d.reset();
        }
    }

    @Subscribe(
            threadMode = ThreadMode.BACKGROUND
    )
    public void _onBootEvent(BootEvent var1) {
        List var3;
        List var10000 = var3 = this.h;
        TSGeofenceManager var10001 = this;
        TSGeofenceManager var10002 = this;
        TSGeofenceManager var10003 = this;
        synchronized(var3) {
            var10003.h.clear();
            var10002.d();
            var10001.reEvaluate();
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onConfigChange(ConfigChangeEvent var1) {
        if (var1.isDirty(Application.B("㶓蹶ᗚ썶\uf672ܛ箿Ỗ必䙛콋ꇊ㢜㠀쎃ꐝ趎딝П\ud95f褕ꋠ꣔"))) {
            this.evaluate();
        }

        TSConfig var2;
        if (!(var2 = TSConfig.getInstance(var1.getContext())).isLocationTrackingMode()) {
            if (var1.isDirty(Application.B("㶓蹶ᗚ썶\uf672ܛ箿Ỗ忘䙆콀ꇗ㢽㠄쎍ꐁ趶딬Н\ud94e褎ꋴ꣄拹"))) {
                if (var2.getGeofenceModeHighAccuracy()) {
                    ActivityRecognitionService.start(this.a);
                    if (var2.getIsMoving()) {
                        this.startMonitoringSignificantLocationChanges();
                    } else {
                        this.startMonitoringStationaryRegion(this.d);
                    }
                } else {
                    ActivityRecognitionService.stop(this.a);
                    if (var2.getIsMoving()) {
                        var2.setIsMoving(false);
                        this.stopMonitoringSignificantLocationChanges();
                        GeofencingService.stop(this.a);
                    }

                    if (this.isMonitoringInfiniteGeofences()) {
                        this.startMonitoringSignificantLocationChanges();
                        this.startMonitoringStationaryRegion(this.d);
                    }
                }
            }

            if (var2.getGeofenceModeHighAccuracy() && var2.getIsMoving() && (var1.isDirty(Application.B("㶘蹼ᗖ썱\uf663ܜ箳ờ忀䙙콀ꇓ㢁㠈쎣ꐇ趃딪Ќ\ud94d褝ꋹ")) || var1.isDirty(Application.B("㶐蹺ᗆ썤\uf676ܛ箿Ỗ忓䙀콈ꇆ㢐㠟")) || var1.isDirty(Application.B("㶐蹶ᗓ썵\uf665ܡ箵Ở忰")))) {
                this.startMonitoringSignificantLocationChanges();
            }
        }

    }

    @Subscribe(
            threadMode = ThreadMode.BACKGROUND
    )
    public void onProviderChangeEvent(LocationProviderChangeEvent var1) {
        if (TSConfig.getInstance(this.a).getEnabled()) {
            if (!var1.isEnabled()) {
                this.reset();
            }

        }
    }

    public void run() {
        this.start();
    }

    public void start() {
        if (!this.l.get()) {
            TSGeofenceManager var10000 = this;
            TSGeofenceManager var10001 = this;
            this.l.set(true);
            this.m.set(false);
            synchronized(this.h){}

            Throwable var33;
            SharedPreferences var34;
            boolean var35;
            try {
                var10001.h.clear();
                var34 = var10000.a.getSharedPreferences(this.getClass().getCanonicalName(), 0);
            } catch (Throwable var32) {
                var33 = var32;
                var35 = false;
                throw var33;
            }

            String var37 = Application.B("蟑Ȥ铮碼쳳혝ᘘ⪩ỹﺯ⻜稿➮藦ैꜷ㑚쩲ĵ㼈덧");

            Set var36;
            try {
                var36 = var34.getStringSet(var37, new HashSet());
            } catch (Throwable var31) {
                var33 = var31;
                var35 = false;
                throw var33;
            }

            Set var2 = var36;
            if (var36 != null) {
                int var38;
                try {
                    var38 = var2.size();
                } catch (Throwable var30) {
                    var33 = var30;
                    var35 = false;
                    throw var33;
                }

                if (var38 > 0) {
                    try {
                        this.h.addAll(var2);
                    } catch (Throwable var29) {
                        var33 = var29;
                        var35 = false;
                        throw var33;
                    }
                }
            }

            try {
                var10000 = this;
            } catch (Throwable var28) {
                var33 = var28;
                var35 = false;
                throw var33;
            }

            var10000.n.set(GeofenceDAO.getInstance(this.a).count());
        }
    }

    public void stop() {
        this.l.set(false);
        this.e();
        this.f.set(0L);
        Runnable var1;
        if ((var1 = this.g) != null) {
            this.q.removeCallbacks(var1);
        }

        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
            public void run() {
                TSGeofenceManager.this.f();
            }
        });
        this.stopMonitoringSignificantLocationChanges();
    }

    public void reset() {
        this.e();
        this.f.set(0L);
        this.b();
    }

    public boolean hasGeofences() {
        return this.n.get() > 0;
    }

    public boolean isMonitoringInfiniteGeofences() {
        return this.n.get() > 99;
    }

    @TargetApi(26)
    public void startMonitoringStationaryRegion(Location var1) {
        TSConfig var2 = TSConfig.getInstance(this.a);
        if (!com.transistorsoft.locationmanager.util.c.b(this.a)) {
            TSLog.logger.debug(TSLog.info(Application.B("픳划绮\ue4f5ᗏⱛ\ue4d0汿\uf085초適ⶢ\uf01a➣퓶\uea11\udbeb\ue5cd\u0b9d\u2d73❆Ňᖢ읢䉦뛭췼헪㣂ᣧ괧ꎫ㉾\uedff谖앰珫\uf26d陭筓圾鲦䮧\ue091㩤\u1716믏łꅼ\ue212Ĥ榛\ue49f\u05ed吭쫶礁꼪ⱗ䇏剖회ን")));
        } else if (!var2.getIsMoving()) {
            if (var2.isLocationTrackingMode() || this.isMonitoringInfiniteGeofences()) {
                GeofencingClient var3 = LocationServices.getGeofencingClient(this.a.getApplicationContext());
                float var4 = (float)var2.getStationaryRadius();
                if (var2.isLocationTrackingMode()) {
                    if (var4 < 150.0F) {
                        var4 = 150.0F;
                    }
                } else {
                    var4 = (float)var2.getGeofenceProximityRadius() / 2.0F;
                }

                TSGeofenceManager var10000 = this;
                Location var10001 = var1;
                TSGeofenceManager var10002 = this;
                GeofencingClient var10003 = var3;
                TSLog.logger.debug(TSLog.on(Application.B("픣切绡\ue4e9ᗔⰏ\ue49d汽\uf084촏遴ⶹ\uf007➸풸\uea05\udbbf\ue5df\u0b9d\u2d7b❝ŀᖬ읾䉾뚲췷햯㣗ᣫ괯ꎬ㈱\uede6豟씬珱\uf22c阮筭圣鲰䯳\ue0f8") + var4 + Application.B("픝剓") + var1.getLatitude() + Application.B("한") + var1.getLongitude() + Application.B("핐创绁\ue4f8ᗃⰒ") + var1.getAccuracy() + Application.B("학")));
                Builder var10004 = (new Builder()).setRequestId(Application.B("픣刧绁\ue4cfᗩⱠ\ue4be汓\uf0b8촿遟ⶑ\uf030➞풐\uea27\udbd1\ue5ef\u0bac"));
                double var11 = var1.getLatitude();
                double var5 = var1.getLongitude();
                Geofence var9 = var10004.setCircularRegion(var11, var5, var4).setExpirationDuration(-1L).setTransitionTypes(2).build();
                GeofencingRequest var14 = (new com.google.android.gms.location.GeofencingRequest.Builder()).addGeofence(var9).build();

                SecurityException var12;
                label47: {
                    boolean var13;
                    try {
                        var10003.addGeofences(var14, GeofencingService.getPendingIntent(this.a, Application.B("픣刧绁\ue4cfᗩⱠ\ue4be汓\uf0b8촿遟ⶑ\uf030➞풐\uea27\udbd1\ue5ef\u0bac")));
                    } catch (SecurityException var8) {
                        var12 = var8;
                        var13 = false;
                        break label47;
                    }

                    try {
                        var10002.o.set(true);
                        var10000.e = var10001;
                        return;
                    } catch (SecurityException var7) {
                        var12 = var7;
                        var13 = false;
                    }
                }

                SecurityException var10 = var12;
                TSLog.logger.warn(TSLog.warn(Application.B("픣刖绣\ue4eeᗒⱆ\ue484汫\uf0af촞遣ⶳ\uf005➥풿\uea0d\udbf1\ue58cஞ\u2d72❀Ņᖦ윰䉾뚴췺헪㣈\u18fe괼ꎬ㈰\uedef豟앰珬\uf26d阫筠圲鲄䮬\ue0b7㩬ᜦ믒ńꄾ\ue241ſ槎") + var10.getMessage()));
                this.o.set(false);
            }
        }
    }

    public Location getStationaryLocation() {
        return this.e;
    }

    public void stopMonitoringStationaryRegion() {
        if (this.o.get()) {
            TSLog.logger.debug(TSLog.off(Application.B("\u2455钠齷힊ᤷ⭈쑕蜡柔稪ﱌ깫ᣑ軟싖牏菂\uf805䪘輳쟑\u0889뵣\ue935䞄曉肹\uddbb텎ഢ\uf81c䀫ᕟ")));
            this.o.set(false);
            this.e = null;
            LocationServices.getGeofencingClient(this.a.getApplicationContext()).removeGeofences(GeofencingService.getPendingIntent(this.a, Application.B("\u2455钀齙\ud7aeᥞ⭪쑴蜎柯稇ﱼ깞\u18fd軾싷爪菿\uf832䪼")));
        }
    }

    public boolean isMonitoringStationaryRegion() {
        return this.o.get();
    }

    public void startMonitoringSignificantLocationChanges() {
        final TSConfig var1;
        if ((var1 = TSConfig.getInstance(this.a)).getEnabled() && com.transistorsoft.locationmanager.d.b.e(this.a)) {
            if (!var1.isLocationTrackingMode()) {
                if (var1.getGeofenceModeHighAccuracy()) {
                    if (!var1.getIsMoving()) {
                        return;
                    }
                } else if (!this.isMonitoringInfiniteGeofences()) {
                    return;
                }

                if (!this.l.get()) {
                    this.start();
                }

                BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                    public void run() {
                        if (GeofenceDAO.getInstance(TSGeofenceManager.this.a).count() > 0) {
                            TSLog.logger.info(TSLog.on(Application.B("貕鯱\ud7c9䴢歏㮑膍雾ᣣ彏봋̢\u0ef6r淎辏︰殁昵\ud860꺎烄\uee5f文淼짝졙த绳嘆\u139e벪蕨沧﮿\ud7c7』노䀎黊\u0092꿠뛘\uda2e掾")));
                            final FusedLocationProviderClient var1x = LocationServices.getFusedLocationProviderClient(TSGeofenceManager.this.a);
                            final LocationRequest var2 = LocationRequest.create();
                            if (!var1.isLocationTrackingMode() && var1.getGeofenceModeHighAccuracy()) {
                                var2.setPriority(var1.getDesiredAccuracy());
                                var2.setInterval(var1.getLocationUpdateInterval());
                                var2.setSmallestDisplacement(var1.getDistanceFilter());
                                var2.setMaxWaitTime(var1.getDeferTime());
                                if (var1.getFastestLocationUpdateInterval() >= 0L) {
                                    var2.setFastestInterval(var1.getFastestLocationUpdateInterval());
                                }
                            } else {
                                var2.setPriority(104);
                                var2.setSmallestDisplacement((float)var1.getGeofenceProximityRadius() / 2.0F);
                                var2.setInterval(60000L);
                                var2.setFastestInterval(60000L);
                                var2.setMaxWaitTime(60000L);
                            }

                            SecurityException var10000;
                            label47: {
                                <undefinedtype> var8;
                                FusedLocationProviderClient var9;
                                boolean var10001;
                                PendingIntent var10002;
                                try {
                                    var8 = this;
                                    var9 = var1x;
                                    var10002 = GeofencingService.getPendingIntent(TSGeofenceManager.this.a);
                                } catch (SecurityException var6) {
                                    var10000 = var6;
                                    var10001 = false;
                                    break label47;
                                }

                                final PendingIntent var3 = var10002;

                                try {
                                    var9.removeLocationUpdates(var10002);
                                } catch (SecurityException var5) {
                                    var10000 = var5;
                                    var10001 = false;
                                    break label47;
                                }

                                try {
                                    com.transistorsoft.locationmanager.util.c.g(TSGeofenceManager.this.a, new PermissionRequestListener() {
                                        public void onPermissionGranted() {
                                            <undefinedtype> var10000 = this;
                                            FusedLocationProviderClient var10001 = var1x;
                                            <undefinedtype> var10002 = this;
                                            LocationRequest var1xx = var2;
                                            var10001.requestLocationUpdates(var1xx, var3);
                                            TSGeofenceManager.this.p.set(true);
                                        }

                                        public void onPermissionDenied(DeniedPermissions var1xx) {
                                            TSLog.logger.warn(TSLog.warn(Application.B("ꈆ괖꙰潄츈\ude29䋽蹗痚뮑雼⅀囖틡ⶫ⋲蔈ܤ\u0e3b䈕ꈉ㳍⽏⩭料ᵙ薔㳾\udeed\ue677◭✽茜\udbfd㶕娜擓꾛씟ς˔䙞ါ㿠䢈䢘➛鄦랳黈ᒃἂ偍⫡沛렞柅ꗝ枆著䩿穑闆髑ꒊ⥸墛沶邖钛䣒珑\udc0e㏸ס塧긳\u0eea萘葠矵奁\uf0da憁䕨舑ቴ턄")));
                                            TSGeofenceManager.this.p.set(false);
                                        }
                                    });
                                    return;
                                } catch (SecurityException var4) {
                                    var10000 = var4;
                                    var10001 = false;
                                }
                            }

                            SecurityException var7 = var10000;
                            TSLog.logger.error(TSLog.error(Application.B("貕鯠ퟋ䴥歉㯘膔雨ᣈ彞봜̨\u0ef4o淉辇ﹾ毒昫\ud86f꺉烁\uee5c旎淾질졃வ纾飼ᎅ베蕧沴ﯶퟜ『노䀟黇\u0082꿻뛚\uda38掹斡訕㭵\uf17b軹瞘䔿\ud86e₳\u1adc┉喃崽哳䥞韃氫") + var7.getMessage()), var7);
                        }
                    }
                });
            }
        }
    }

    public void stopMonitoringSignificantLocationChanges() {
        if (this.p.get()) {
            TSLog.logger.info(TSLog.off(Application.B("쉹䒫쫝棸䔸퍯\ueb3bꉹ\udd9c退ᬗ鮽랞밲讹⮈여\uec47ʢ祲츎赒诡᙮쯏\ude7bꛘ갫캍ᣍ쎳K乯숋놅攘\uf6b0呤ꅵⵛ⦴烮뤓ⱘ")));
            this.p.set(false);
            LocationServices.getFusedLocationProviderClient(this.a).removeLocationUpdates(GeofencingService.getPendingIntent(this.a));
        }
    }

    public boolean isMonitoringGeofencesInProximity() {
        TSGeofenceManager var10000 = this;
        synchronized(this.h){}

        boolean var10001;
        Throwable var8;
        boolean var9;
        try {
            var9 = var10000.h.isEmpty();
        } catch (Throwable var7) {
            var8 = var7;
            var10001 = false;
            throw var8;
        }

        boolean var1;
        if (!var9) {
            var1 = true;
        } else {
            var1 = false;
        }

        try {
            return var1;
        } catch (Throwable var6) {
            var8 = var6;
            var10001 = false;
            throw var8;
        }
    }

    public void onGeofencesChange(TSGeofencesChangeCallback var1) {
        List var2;
        List var10001 = var2 = this.c;
        TSGeofenceManager var10002 = this;
        synchronized(var2){}

        Throwable var10000;
        boolean var9;
        try {
            var10002.c.add(var1);
        } catch (Throwable var8) {
            var10000 = var8;
            var9 = false;
            throw var10000;
        }

        try {
            ;
        } catch (Throwable var7) {
            var10000 = var7;
            var9 = false;
            throw var10000;
        }

        this.reEvaluate();
    }

    public Object removeListener(String var1, Object var2) {
        if (Application.B("Ŷ혏굂렚餹苕\ue778퍓珊ﷰ⦐䥗\ue1c1겥\ue059").equalsIgnoreCase(var1)) {
            TSGeofenceManager var10000 = this;
            synchronized(this.c){}

            boolean var16;
            boolean var10001;
            Throwable var15;
            try {
                var16 = var10000.c.contains((TSGeofencesChangeCallback)var2);
            } catch (Throwable var14) {
                var15 = var14;
                var10001 = false;
                throw var15;
            }

            if (var16) {
                try {
                    return this.c.remove(var2);
                } catch (Throwable var12) {
                    var15 = var12;
                    var10001 = false;
                    throw var15;
                }
            } else {
                var10000 = null;

                try {
                    return var10000;
                } catch (Throwable var13) {
                    var15 = var13;
                    var10001 = false;
                    throw var15;
                }
            }
        } else {
            return null;
        }
    }

    public void removeListeners() {
        List var1;
        List var10000 = var1 = this.c;
        TSGeofenceManager var10001 = this;
        synchronized(var1) {
            var10001.c.clear();
        }
    }

    public void add(TSGeofence var1, TSCallback var2) {
        TSGeofenceManager var10000 = this;
        ArrayList var3;
        ArrayList var10001 = var3 = new ArrayList;
        var10001.<init>();
        var10001.add(var1);
        var10000.add((List)var3, var2);
    }

    public void add(List<TSGeofence> var1, TSCallback var2) {
        (new TSGeofenceManager.d(this.a, var2)).execute(new ArrayList[]{(ArrayList)var1});
    }

    public void remove(String var1, TSCallback var2) {
        TSGeofenceManager var10000 = this;
        ArrayList var3;
        ArrayList var10001 = var3 = new ArrayList;
        var10001.<init>();
        var10001.add(var1);
        var10000.remove((List)var3, var2);
    }

    public void remove(List<String> var1, TSCallback var2) {
        (new TSGeofenceManager.h(this.a, var2)).execute(new List[]{var1});
    }

    public void geofenceExists(String var1, TSGeofenceExistsCallback var2) {
        (new TSGeofenceManager.g(this.a, var2)).execute(new String[]{var1});
    }

    public void setIsMoving(boolean var1) {
        this.k.set(var1);
    }

    public void setLocation(Location var1, boolean var2) {
        if (TSConfig.getInstance(this.a).getEnabled()) {
            if (!this.l.get()) {
                this.start();
            }

            if (this.n.get() == -1) {
                this.n.set(GeofenceDAO.getInstance(this.a).count());
            }

            if (this.n.get() == 0) {
                this.b(var1);
            } else {
                long var3 = -1L;
                if (this.f.get() > 0L) {
                    var3 = var1.getTime() - this.f.get();
                }

                boolean var5;
                if (var2 != this.k.get()) {
                    var5 = true;
                } else {
                    var5 = false;
                }

                this.k.set(var2);
                boolean var6;
                if (this.f.get() != 0L && var3 < this.i) {
                    var6 = false;
                } else {
                    var6 = true;
                }

                String var13 = Application.B("䛔罪ළ䧆㠲床㴆\u2bf0唧蜟") + var2 + Application.B("䚝罥ඨ䧚㠰庂㴜\u2bf2啞蝗쵘⩚ᓯ抐᱃꙯⊘") + var5 + Application.B("䚝罥ඨ䧝㠭庎㴍\u2be5啘蝇쵉⩝ᓺ抐᱃꙯⊘") + var6 + Application.B("䚝罥ඨ䧌㠨庂㴘\u2be4啸蝛촃⨔") + var3;
                if (var3 >= 0L && var3 < 10000L) {
                    TSLog.logger.debug(var13);
                } else {
                    if (this.c()) {
                        Location var14;
                        Location var10001 = var14 = this.d;
                        TSGeofenceManager var10002 = this;
                        synchronized(var14){}

                        boolean var15;
                        float var16;
                        Throwable var10000;
                        try {
                            var16 = var10002.d.distanceTo(var1);
                        } catch (Throwable var12) {
                            var10000 = var12;
                            var15 = false;
                            throw var10000;
                        }

                        float var4 = var16;

                        try {
                            ;
                        } catch (Throwable var11) {
                            var10000 = var11;
                            var15 = false;
                            throw var10000;
                        }

                        if (TSConfig.getInstance(this.a).getDebug()) {
                            TSLog.logger.debug(var13 + Application.B("䚝罥ඨ䧍㡾廃") + var4);
                        }

                        if (var4 > (float)TSConfig.getInstance(this.a).getGeofenceProximityRadius() / 2.0F) {
                            var5 = true;
                        } else if (var4 < 200.0F && !var5) {
                            return;
                        }
                    }

                    if (!var6 && !var5) {
                        if (!this.c()) {
                            this.b(var1);
                            this.a(var1);
                        }
                    } else {
                        this.a(var1);
                    }

                    this.b(var1);
                }
            }
        }
    }

    public void reEvaluate() {
        this.m.set(false);
        this.evaluate();
    }

    public void evaluate() {
        if (this.c()) {
            this.a(this.d);
        }

    }

    void a() {
        Runnable var1;
        if ((var1 = this.g) != null) {
            this.q.removeCallbacks(var1);
        } else {
            TSGeofenceManager.e var4;
            var4 = new TSGeofenceManager.e.<init>(null);
            this.g = var4;
        }

        Handler var10000 = this.q;
        TSGeofenceManager var10001 = this;
        Runnable var3 = this.g;
        long var5 = var10001.j;
        var10000.postDelayed(var3, var5);
    }

    public void destroy() {
        this.stop();
        EventBus var1;
        if ((var1 = EventBus.getDefault()).isRegistered(this)) {
            var1.unregister(this);
        }

    }

    private class f implements Runnable {
        private final Location a;

        f(Location var2) {
            this.a = var2;
        }

        public void run() {
            final TSConfig var1;
            long var2 = (var1 = TSConfig.getInstance(TSGeofenceManager.this.a)).getGeofenceProximityRadius();
            if (this.a.hasAccuracy()) {
                var2 = (long)((float)var2 + this.a.getAccuracy());
            }

            int var4;
            int var10000 = var4 = TSGeofenceManager.this.n.get();
            final ArrayList var5;
            var5 = new ArrayList.<init>();
            final ArrayList var6;
            var6 = new ArrayList.<init>();
            final ArrayList var7;
            var7 = new ArrayList.<init>();
            HashMap var8;
            var8 = new HashMap.<init>();
            boolean var10001;
            TSGeofenceManager.f var574;
            Throwable var575;
            boolean var576;
            ArrayList var578;
            if (var10000 > 0) {
                if (var4 < 99) {
                    var574 = this;
                    synchronized(TSGeofenceManager.this.h){}

                    try {
                        var576 = TSGeofenceManager.this.m.get();
                    } catch (Throwable var562) {
                        var575 = var562;
                        var10001 = false;
                        throw var575;
                    }

                    if (var576) {
                        try {
                            var10000 = TSGeofenceManager.this.h.size();
                        } catch (Throwable var561) {
                            var575 = var561;
                            var10001 = false;
                            throw var575;
                        }

                        if (var10000 == var4) {
                            try {
                                return;
                            } catch (Throwable var559) {
                                var575 = var559;
                                var10001 = false;
                                throw var575;
                            }
                        }
                    }

                    TSGeofenceManager.f var577;
                    try {
                        var578 = var5;
                        var577 = this;
                    } catch (Throwable var560) {
                        var575 = var560;
                        var10001 = false;
                        throw var575;
                    }

                    var578.addAll(GeofenceDAO.getInstance(TSGeofenceManager.this.a).all());
                } else {
                    GeofenceDAO var579 = GeofenceDAO.getInstance(TSGeofenceManager.this.a);
                    double var10002 = (double)var2;
                    double var563 = this.a.getLatitude();
                    double var9 = this.a.getLongitude();
                    var5.addAll(var579.allWithinRadius(var10002, var563, var9, 99));
                }
            }

            if (TSGeofenceManager.this.m.compareAndSet(false, true)) {
                var6.addAll(var5);
            }

            Iterator var564 = var5.iterator();

            while(var564.hasNext()) {
                TSGeofence var3;
                var8.put((var3 = (TSGeofence)var564.next()).getIdentifier(), var3);
            }

            final StringBuffer var566;
            var566 = new StringBuffer.<init>();
            String var565 = Application.B("冲臕当핾梘婚\ue192홖፶탅榐痢먻㬶坌ﺎ\u2067\ue7f4롙䬓绖禢թ骠\ueb59諸\ufdd8㞰杻") + var5.size() + Application.B("凉") + TSGeofenceManager.this.n.get();
            if (TSGeofenceManager.this.isMonitoringInfiniteGeofences()) {
                var565 = var565 + Application.B("准臱彽핯梟婕\ue199혘") + var1.getGeofenceProximityRadius() + Application.B("准臫影핯梒婎\ue184");
            }

            var574 = this;
            var566.append(TSLog.header(var565));
            synchronized(TSGeofenceManager.this.h){}

            Iterator var580;
            label6267: {
                try {
                    var580 = TSGeofenceManager.this.h.iterator();
                } catch (Throwable var558) {
                    var575 = var558;
                    var10001 = false;
                    throw var575;
                }

                Iterator var573 = var580;

                while(true) {
                    try {
                        var576 = var573.hasNext();
                    } catch (Throwable var553) {
                        var575 = var553;
                        var10001 = false;
                        break;
                    }

                    if (!var576) {
                        try {
                            var578 = var7;
                            break label6267;
                        } catch (Throwable var552) {
                            var575 = var552;
                            var10001 = false;
                            break;
                        }
                    }

                    String var581;
                    HashMap var582;
                    try {
                        var582 = var8;
                        var581 = (String)var573.next();
                    } catch (Throwable var557) {
                        var575 = var557;
                        var10001 = false;
                        break;
                    }

                    String var10 = var581;

                    try {
                        var576 = var582.containsKey(var581);
                    } catch (Throwable var556) {
                        var575 = var556;
                        var10001 = false;
                        break;
                    }

                    if (var576) {
                        try {
                            var5.remove(var8.get(var10));
                        } catch (Throwable var555) {
                            var575 = var555;
                            var10001 = false;
                            break;
                        }
                    } else {
                        try {
                            var7.add(var10);
                        } catch (Throwable var554) {
                            var575 = var554;
                            var10001 = false;
                            break;
                        }
                    }
                }

                throw var575;
            }

            if (var578.size() > 0) {
                TSGeofenceManager.this.a((List)var7);
            }

            if (var5.size() > 0) {
                label6227: {
                    com.google.android.gms.location.GeofencingRequest.Builder var571;
                    var571 = new com.google.android.gms.location.GeofencingRequest.Builder.<init>();
                    if (var1.getGeofenceInitialTriggerEntry()) {
                        var571.setInitialTrigger(5);
                    } else {
                        var571.setInitialTrigger(0);
                    }

                    Iterator var572 = var5.iterator();

                    while(var572.hasNext()) {
                        var571.addGeofence(((TSGeofence)var572.next()).build());
                    }

                    GeofencingClient var584 = LocationServices.getGeofencingClient(TSGeofenceManager.this.a);

                    SecurityException var585;
                    label6215: {
                        Task var586;
                        try {
                            var586 = var584.addGeofences(var571.build(), GeofencingService.getPendingIntent(TSGeofenceManager.this.a));
                            var586.addOnSuccessListener(new OnSuccessListener<Void>() {
                                public void a(Void var1x) {
                                    TSGeofenceManager.this.f.set(f.this.a.getTime());
                                    TSLog.logger.debug(Application.B("\ue692\uf360뽷\ued0f㼕둡쑜鉃䫰ꂓ觫챜滩胝༟钷ꤐﰬ깐\ue35d\uf36a\ue39c텊谇\ue2ca㭬\uec53䥬\uea8f\ue561ࣤ喻嚌"));
                                    if (!var1.isLocationTrackingMode() && !ActivityRecognitionService.isStarted()) {
                                        ActivityRecognitionService.start(TSGeofenceManager.this.a);
                                    }

                                    <undefinedtype> var10000 = this;
                                    synchronized(TSGeofenceManager.this.h){}

                                    <undefinedtype> var168;
                                    label1291: {
                                        boolean var10001;
                                        Throwable var163;
                                        Iterator var164;
                                        try {
                                            var164 = var5.iterator();
                                        } catch (Throwable var159) {
                                            var163 = var159;
                                            var10001 = false;
                                            throw var163;
                                        }

                                        Iterator var2 = var164;

                                        while(true) {
                                            boolean var165;
                                            try {
                                                var165 = var2.hasNext();
                                            } catch (Throwable var153) {
                                                var163 = var153;
                                                var10001 = false;
                                                break;
                                            }

                                            if (!var165) {
                                                try {
                                                    TSGeofenceManager.this.d();
                                                    var164 = TSGeofenceManager.this.h.iterator();
                                                } catch (Throwable var152) {
                                                    var163 = var152;
                                                    var10001 = false;
                                                    break;
                                                }

                                                var2 = var164;

                                                while(true) {
                                                    try {
                                                        var165 = var2.hasNext();
                                                    } catch (Throwable var149) {
                                                        var163 = var149;
                                                        var10001 = false;
                                                        throw var163;
                                                    }

                                                    if (!var165) {
                                                        try {
                                                            var10000 = this;
                                                            var168 = this;
                                                            break label1291;
                                                        } catch (Throwable var148) {
                                                            var163 = var148;
                                                            var10001 = false;
                                                            throw var163;
                                                        }
                                                    }

                                                    String var167;
                                                    try {
                                                        var10000 = this;
                                                        var167 = (String)var2.next();
                                                    } catch (Throwable var151) {
                                                        var163 = var151;
                                                        var10001 = false;
                                                        throw var163;
                                                    }

                                                    String var162 = var167;

                                                    try {
                                                        var566.append(TSLog.boxRow(Application.B("ᾗ틑뽷\ued0f") + var162));
                                                    } catch (Throwable var150) {
                                                        var163 = var150;
                                                        var10001 = false;
                                                        throw var163;
                                                    }
                                                }
                                            }

                                            TSGeofence var166;
                                            try {
                                                var10000 = this;
                                                var166 = (TSGeofence)var2.next();
                                            } catch (Throwable var158) {
                                                var163 = var158;
                                                var10001 = false;
                                                break;
                                            }

                                            TSGeofence var3 = var166;

                                            try {
                                                var165 = var6.contains(var3);
                                            } catch (Throwable var157) {
                                                var163 = var157;
                                                var10001 = false;
                                                break;
                                            }

                                            if (!var165) {
                                                try {
                                                    var6.add(var3);
                                                } catch (Throwable var156) {
                                                    var163 = var156;
                                                    var10001 = false;
                                                    break;
                                                }
                                            }

                                            try {
                                                var165 = TSGeofenceManager.this.h.contains(var3.getIdentifier());
                                            } catch (Throwable var155) {
                                                var163 = var155;
                                                var10001 = false;
                                                break;
                                            }

                                            if (!var165) {
                                                try {
                                                    TSGeofenceManager.this.h.add(var3.getIdentifier());
                                                } catch (Throwable var154) {
                                                    var163 = var154;
                                                    var10001 = false;
                                                    break;
                                                }
                                            }
                                        }

                                        throw var163;
                                    }

                                    var566.append(Application.B("\ue2f1⠿騇졿ᨂ酔\ue163띵濅薭곘\ue965䯗ꗪ⨌놋谩\ud919譮왹혚욭\uf47eꤳ쟝ṙ쥬汚쾺쁟\u2dd7炎环\udbcb\ueb46ᘞ礼肸汳嬳ᩝ큾ᦉ\ude02꣣駷"));
                                    TSLog.logger.debug(var566.toString());
                                    GeofencesChangeEvent var161;
                                    GeofencesChangeEvent var169 = var161 = new GeofencesChangeEvent;
                                    <undefinedtype> var10002 = this;
                                    List var160 = var6;
                                    var169.<init>(var160, var7);
                                    TSGeofenceManager.this.a(var161);
                                }
                            });
                        } catch (SecurityException var546) {
                            var585 = var546;
                            var10001 = false;
                            break label6215;
                        }

                        try {
                            var586.addOnFailureListener(new OnFailureListener() {
                                public void onFailure(@NonNull Exception var1) {
                                    String var2 = Application.B("狜旤\udc42䉿娽혷\uf0f9髁声Ὡ颴놈ⴓ䙕飯⮰㸌⼞Ń誯ӡ绸ⅈ啹볳쿏\uf0ff⛃ꋩ㶠飿헫骸ꛈᔈ쐆锃빞") + var1.getMessage();
                                    if (var1 instanceof ApiException && ((ApiException)var1).getStatusCode() == 1004) {
                                        var2 = Application.B("狝无\udc44䉵娽혽\uf0ba髐墿ἤ風높ⴛ䙓飴\u2be2㸈⼟Ŋ諦Ӽ绤ℚ啶볲쿚\uf0bd⛍ꋨ㶫飼헠髶ꛜᔄ쐁镑빞Ⳏ렌찂\ue1e5\ue699澫ﻺ발쫢싒\uef0a釀뾑ே武\u0ef9圦茟㊭絙\uda94릆珤葟\udf9bལ\ue29bﮠ蕞谺格");
                                    }

                                    TSLog.logger.warn(TSLog.warn(var2));
                                    TSGeofenceManager.this.b();
                                    TSGeofenceManager.this.a(new GeofencesChangeEvent());
                                }
                            });
                            break label6227;
                        } catch (SecurityException var545) {
                            var585 = var545;
                            var10001 = false;
                        }
                    }

                    SecurityException var567 = var585;
                    TSLog.logger.error(TSLog.error(Application.B("况臣彷핮梅婕\ue183홁ፐ탘榾痦먥㬣坂ﺄ⁻\ue7f4롃䬔绑禧ո髯\ueb4a憎ﷂ㞲礪㣔\ue973勇\ue90c഼\uddb7帝鄰䇲攊⒔つ욨┛\ufde6ᣒⰢ\ud97b䨖뷒⏶㈥Ϧ\ua8de\ue3f5㏡ꃣ\uf8d6羴找") + var567.getMessage()), var567);
                }
            } else {
                label6268: {
                    TSGeofenceManager.this.f.set(this.a.getTime());
                    if (var4 == 0 && !var1.isLocationTrackingMode()) {
                        if (var1.getGeofenceModeHighAccuracy() && ActivityRecognitionService.isStarted()) {
                            ActivityRecognitionService.stop(TSGeofenceManager.this.a);
                        }

                        TSGeofenceManager.this.stopMonitoringStationaryRegion();
                        TSGeofenceManager.this.stopMonitoringSignificantLocationChanges();
                    }

                    var574 = this;
                    List var568;
                    synchronized(var568 = TSGeofenceManager.this.h){}

                    try {
                        var580 = TSGeofenceManager.this.h.iterator();
                    } catch (Throwable var551) {
                        var575 = var551;
                        var10001 = false;
                        throw var575;
                    }

                    Iterator var569 = var580;

                    while(true) {
                        try {
                            var576 = var569.hasNext();
                        } catch (Throwable var549) {
                            var575 = var549;
                            var10001 = false;
                            break;
                        }

                        if (!var576) {
                            List var583;
                            try {
                                var583 = var568;
                                var566.append(Application.B("璼ꓖ穄\uf04b䶧罬쒧\uf368㙅\uf5f0䲍僓鼅ḇ牻\udbbbՅ슄鵤測寨岛⁍뾟칻\udf1b\ud8e6ኇ\udcb8ᷴ챗矾찲⠋\uf8c7笹됏撂䀶ƭᕇ\ue38c.\ud8d8㶢आ"));
                            } catch (Throwable var548) {
                                var575 = var548;
                                var10001 = false;
                                break;
                            }

                            try {
                                TSLog.logger.debug(var566.toString());
                                break label6268;
                            } catch (Throwable var547) {
                                var575 = var547;
                                var10001 = false;
                                break;
                            }
                        }

                        try {
                            var566.append(TSLog.boxRow(Application.B("觚常弴픻") + (String)var569.next()));
                        } catch (Throwable var550) {
                            var575 = var550;
                            var10001 = false;
                            break;
                        }
                    }

                    throw var575;
                }
            }

            if (var7.size() + var6.size() != 0 || TSGeofenceManager.this.n.get() <= 0) {
                if (var5.isEmpty()) {
                    GeofencesChangeEvent var570;
                    var570 = new GeofencesChangeEvent.<init>(var6, var7);
                    TSGeofenceManager.this.a(var570);
                }

                if (!var1.isLocationTrackingMode() && var1.getGeofenceModeHighAccuracy() && var1.getIsMoving()) {
                    var578 = var7;
                    synchronized(TSGeofenceManager.this.h){}

                    try {
                        var576 = var578.isEmpty();
                    } catch (Throwable var544) {
                        var575 = var544;
                        var10001 = false;
                        throw var575;
                    }

                    label6221: {
                        if (!var576) {
                            try {
                                var576 = TSGeofenceManager.this.h.isEmpty();
                            } catch (Throwable var543) {
                                var575 = var543;
                                var10001 = false;
                                throw var575;
                            }

                            if (var576) {
                                try {
                                    var1.setIsMoving(false);
                                    GeofencingService.stopService(TSGeofenceManager.this.a);
                                    TSGeofenceManager.this.stopMonitoringSignificantLocationChanges();
                                    break label6221;
                                } catch (Throwable var542) {
                                    var575 = var542;
                                    var10001 = false;
                                    throw var575;
                                }
                            }
                        }

                        try {
                            var576 = var6.isEmpty();
                        } catch (Throwable var541) {
                            var575 = var541;
                            var10001 = false;
                            throw var575;
                        }

                        if (!var576) {
                            try {
                                TSGeofenceManager.this.startMonitoringSignificantLocationChanges();
                            } catch (Throwable var540) {
                                var575 = var540;
                                var10001 = false;
                                throw var575;
                            }
                        }
                    }

                    try {
                        ;
                    } catch (Throwable var539) {
                        var575 = var539;
                        var10001 = false;
                        throw var575;
                    }
                }
            }
        }
    }

    private class e implements Runnable {
        private e() {
        }

        public void run() {
            TSLog.logger.debug(Application.B("䎪뽓\ueb26傳ꓖ싩㷘\uecb6䝍\ue0c3武\uf6e4燐\uf30d➥㝎\ued7a䳹ሰ쬾캔풤땮劲귁ؕ걕狀鏼裝꜍"));
            if (TSGeofenceManager.this.g != null) {
                TSGeofenceManager.this.q.removeCallbacks(TSGeofenceManager.this.g);
                TSGeofenceManager.this.g = null;
            }

            BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                public void run() {
                    TSGeofenceManager.this.n.set(GeofenceDAO.getInstance(TSGeofenceManager.this.a).count());
                    Location var1 = TSLocationManager.getInstance(TSGeofenceManager.this.a).getLastLocation();
                    if (TSGeofenceManager.this.l.get() && var1 != null) {
                        TSGeofenceManager.this.a(var1);
                    }

                }
            });
        }
    }

    private static class g extends AsyncTask<String, Void, Boolean> {
        private final WeakReference<Context> a;
        private final TSGeofenceExistsCallback b;

        g(Context var1, TSGeofenceExistsCallback var2) {
            TSGeofenceManager.g var10000 = this;
            TSGeofenceManager.g var10001 = this;
            super();
            WeakReference var3;
            var3 = new WeakReference.<init>(var1);
            var10001.a = var3;
            var10000.b = var2;
        }

        protected Boolean a(String... var1) {
            Context var2;
            if ((var2 = (Context)this.a.get()) == null) {
                TSLog.logger.warn(TSLog.warn(Application.B("\ue077\uea40淄帴폠\ue169齉蚊쫇姬䡋⤜\udc72Ⲓ㕩멻ছ分芤蟸先迷첋\ue688淳뻀놔ↇ굀\ue274䣄ᰞᇠ\uf13f㻶켟\ue004欫\ue93d㱟\uf370䈃\ue9faꀖ荗\ue7aa藻᪑\ue1c7\udf69漶慘먾顯ᧅ묐뫛섽虉㗱媆樥긝昂ᜤ\uf3ac侊\uee76終됒\ue32c醌\uf10e㻾Ⱕ\udadc䐽\uf869隁챸떛숑潴憧ꯄޢऽ䏷⾞又찬䎅\ue083ꀜɰ⊭\ude21ኟ夶書")));
                return null;
            } else {
                Context var10000 = var2;
                String var3 = var1[0];
                return GeofenceDAO.getInstance(var10000).exists(var3);
            }
        }

        protected void a(Boolean var1) {
            if (var1 == null) {
                var1 = false;
            }

            this.b.onResult(var1);
        }
    }

    private static class h extends AsyncTask<List<String>, Void, Exception> {
        private final WeakReference<Context> a;
        private final TSCallback b;

        h(Context var1, TSCallback var2) {
            TSGeofenceManager.h var10000 = this;
            TSGeofenceManager.h var10001 = this;
            super();
            WeakReference var3;
            var3 = new WeakReference.<init>(var1);
            var10001.a = var3;
            var10000.b = var2;
        }

        protected Exception a(List<String>... var1) {
            TSGeofenceManager.h var10000 = this;
            List var6 = var1[0];
            Context var9;
            if ((var9 = (Context)var10000.a.get()) == null) {
                TSLog.logger.warn(TSLog.warn(Application.B("穩塜씣썜飑茍鴺⎺繁掿る♷ꇻ踌Ƀꒁ䱋쓄뙜ᗫ秎ő浅褼☌뫘\uf1edꆦ콦匋㉃秡᮰\uf496䏆糖\ue0ec\uebbe埞㹒뀋驕构⢑䣂䥂褈ໞ‛蘚烗뮛\uf12a蜊쨈尅\udb03\udc6d싅⎖ꒌ訏꿐㐃뽕ള㶲헑ꌣࡦ᠂\ue3ec㧞蔜烎쁺᪰쉑닶燮ᶋ쮉좋㳢뇓\uf0a9뻡莮䠝쵼稾駟ూ㹡〖磁受혱땗䋭")));
                return null;
            } else {
                Context var10001 = var9;
                GeofenceDAO var10 = GeofenceDAO.getInstance(var9);
                TSGeofenceManager var2 = TSGeofenceManager.getInstance(var10001);
                if (var6.size() == 0) {
                    var10.destroyAll();
                    var2.g();
                    var2.evaluate();
                    return null;
                } else {
                    Exception var11;
                    label43: {
                        Iterator var12;
                        boolean var13;
                        try {
                            var12 = var6.iterator();
                        } catch (Exception var5) {
                            var11 = var5;
                            var13 = false;
                            break label43;
                        }

                        Iterator var7 = var12;

                        while(true) {
                            boolean var14;
                            try {
                                var14 = var7.hasNext();
                            } catch (Exception var4) {
                                var11 = var4;
                                var13 = false;
                                break;
                            }

                            if (!var14) {
                                var2.g();
                                var2.a();
                                return null;
                            }

                            try {
                                var10.destroy((String)var7.next());
                            } catch (Exception var3) {
                                var11 = var3;
                                var13 = false;
                                break;
                            }
                        }
                    }

                    Exception var8 = var11;
                    TSLog.logger.error(TSLog.error(var8.getMessage()), var8);
                    return var8;
                }
            }
        }

        protected void a(Exception var1) {
            if (var1 != null) {
                this.b.onFailure(var1.getMessage());
            } else {
                this.b.onSuccess();
            }

        }
    }

    private static class d extends AsyncTask<ArrayList<TSGeofence>, Void, String> {
        private final WeakReference<Context> a;
        private final TSCallback b;

        d(Context var1, TSCallback var2) {
            TSGeofenceManager.d var10000 = this;
            TSGeofenceManager.d var10001 = this;
            super();
            WeakReference var3;
            var3 = new WeakReference.<init>(var1);
            var10001.a = var3;
            var10000.b = var2;
        }

        protected String a(ArrayList<TSGeofence>... var1) {
            ArrayList var5;
            if ((var5 = var1[0]).size() == 0) {
                return Application.B("凳낛氺Ǎጔ╕羺\ude36枷줪민\uf5b0鈏⚰⫂魰亀螋綁뇒ꘓ");
            } else {
                Context var3;
                if ((var3 = (Context)this.a.get()) == null) {
                    return Application.B("击낕汳ǆጔ╞翼\ude27架쥩믫\uf5a6鉛⚲⫙魺亀螇緅뇴ꘘ\ue0ad꧲銤䍷괡挷䝀\uf426\uda3d铑及Ꙡ㣷\uddd4\ue491郿邥ୢ鑾檻⳦\udc89ﰴ뚍蜠\udd37熆눁ᝑ\ue9af볡톗ﶯ≪\ue278鏀鸅紓㰮컽芎닏ྰ︒ⷈ爧ꪭ곟㽃좌桧合뿽콠\uf66b⼖텤㧉᱓啙뙾ಁ翤נ獲感藣敾\uf52d彍ন昙䫹쫅");
                } else if (GeofenceDAO.getInstance(var3).create(var5) <= 0) {
                    return Application.B("凴낚汩Ǐጃ╎翼\ude35枸줠믵\uf5a6鉋");
                } else {
                    ArrayList var10000 = var5;
                    var5 = new ArrayList.<init>();
                    Iterator var2 = var10000.iterator();

                    while(var2.hasNext()) {
                        var5.add(((TSGeofence)var2.next()).getIdentifier());
                    }

                    TSGeofenceManager var4 = TSGeofenceManager.getInstance(var3);
                    var4.g();
                    var4.a((List)var5);
                    var4.a();
                    return null;
                }
            }
        }

        protected void a(String var1) {
            if (var1 == null) {
                this.b.onSuccess();
            } else {
                this.b.onFailure(var1);
            }

        }
    }
}
