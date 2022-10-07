//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Build.VERSION;
import androidx.annotation.Nullable;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.intentfilter.androidpermissions.PermissionManager.PermissionRequestListener;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.transistorsoft.locationmanager.activity.TSLocationManagerActivity;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.device.DeviceSettings;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.event.MotionActivityCheckEvent;
import com.transistorsoft.locationmanager.event.StopAfterElapsedMinutesEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofenceManager;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.location.TSMotionChangeRequest;
import com.transistorsoft.locationmanager.location.TSMotionChangeRequest.Builder;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.scheduler.TSScheduleManager;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TrackingService extends AbstractService {
    private static final AtomicInteger sMotionChangeRequestId = new AtomicInteger(0);
    private static final int STOP_TIMEOUT_SLC = 1200000;
    private boolean mIsStopped = false;
    private Location mStoppedAtLocation = null;
    private Location mMotionActivityCheckLocation = null;
    private Date mStopUpdatingLocationAt;
    private boolean mStopOnNextStationary = false;
    private LocationAvailability mLocationAvailability;
    private LocationResult mLastLocationResult;

    public TrackingService() {
    }

    @TargetApi(26)
    public static PendingIntent getPendingIntent(Context var0) {
        return getPendingIntent(var0, (String)null);
    }

    public static PendingIntent getPendingIntent(Context var0, @Nullable String var1) {
        var0 = var0.getApplicationContext();
        if (VERSION.SDK_INT >= 26) {
            Context var10000 = var0;
            Intent var10001 = getIntent(var0);
            int var2 = Util.getPendingIntentFlags(134217728);
            return PendingIntent.getForegroundService(var10000, 0, var10001, var2);
        } else {
            return PendingIntent.getService(var0, 0, getIntent(var0), 134217728);
        }
    }

    public static Intent getIntent(Context var0) {
        return new Intent(var0, TrackingService.class);
    }

    public static void start(Context var0) {
        start(var0, (TSLocationCallback)null);
    }

    public static void start(Context var0, TSLocationCallback var1) {
        TSConfig var2;
        TSConfig var10001 = var2 = TSConfig.getInstance(var0);
        boolean var3 = var10001.getEnabled();
        var10001.setEnabled(true);
        TSLocationManagerActivity.startIfEnabled(var0, Application.B("\ue671馘遷쥨㫎ሞ쐯呺齫ໟ͒䶈䳍堆\ud911⡉"));
        TSGeofenceManager.getInstance(var0).start();
        HttpService.getInstance(var0).startMonitoringConnectivityChanges(var0);
        DeviceSettings.getInstance().startMonitoringPowerSaveChanges(var0);
        if (var2.isLocationTrackingMode()) {
            if (var3) {
                TSMotionChangeRequest var4 = ((Builder)((Builder)((Builder)((Builder)(new Builder(var0)).setCallback(new TrackingService.c(var0, var2.getIsMoving(), var1))).setSamples(3)).setDesiredAccuracy(40)).setPersist(false)).build();
                sMotionChangeRequestId.set(var4.getId());
                TSLocationManager.getInstance(var0).getCurrentPosition(var4);
                if (!var2.getIsMoving()) {
                    HeartbeatService.start(var0);
                }
            } else {
                changePace(var0, var2.getIsMoving(), var1);
            }
        } else {
            GeofencingService.changePace(var0, var2.getIsMoving(), var1);
        }

    }

    public static void stop(Context var0) {
        TSConfig var1 = TSConfig.getInstance(var0);
        TSScheduleManager var2 = TSScheduleManager.getInstance(var0);
        if (var1.getIsMoving()) {
            var2.cancelOneShot(Application.B("椩루䖲骄≊삎莣▹蝵抲䠜紙"));
        } else {
            var2.cancelOneShot(Application.B("椷룳䖩骝≚삔莵■蝢抴䠎紊珤숱飉䕜鮎玅宅璐"));
        }

        var1.setEnabled(false);
        var1.setIsMoving(false);
        TSLocationManager var4 = TSLocationManager.getInstance(var0);
        TSGeofenceManager var3 = TSGeofenceManager.getInstance(var0);
        if (isAcquiringMotionChange()) {
            var4.cancelRequest(sMotionChangeRequestId.get());
            sMotionChangeRequestId.set(0);
        }

        if (var1.getStopAfterElapsedMinutes() > 0) {
            TSScheduleManager.getInstance(var0).cancelOneShot(Application.B("椩루䖲骄≊삛莬■蝵抯䠖紈班숢飆䕋鮎玍祥璄ဧ颍먞屡⼲鱫"));
        }

        stopService(var0);
        GeofencingService.stop(var0);
        var4.stop();
        var3.stop();
        var3.stopMonitoringStationaryRegion();
        ActivityRecognitionService.stop(var0);
        HeartbeatService.stop(var0);
        HttpService.getInstance(var0).stopMonitoringConnectivityChanges(var0);
        DeviceSettings.getInstance().stopMonitoringPowerSaveChanges(var0);
    }

    public static void changeTrackingMode(Context var0, int var1, TSLocationCallback var2) {
        TSLocationManager var4;
        TSLocationManager var10001 = var4 = TSLocationManager.getInstance(var0);
        TSConfig var3 = TSConfig.getInstance(var0);
        var10001.stopUpdatingLocation();
        ActivityRecognitionService.stop(var0);
        if (isAcquiringMotionChange()) {
            var4.cancelRequest(sMotionChangeRequestId.get());
            sMotionChangeRequestId.set(0);
        }

        var3.setEnabled(false, true);
        start(var0, var2);
    }

    public static void changePace(Context var0, boolean var1, @Nullable TSLocationCallback var2) {
        TSConfig var3;
        if (!(var3 = TSConfig.getInstance(var0)).getEnabled()) {
            TSLog.warn(TSLog.warn(Application.B("瀕횷᎓잝揪榯썐\ue2b2采⩯ﵑ찼᳥᷆\uf19e뀪≡ढ़ヵ䳄Հ̫峓ﯬ\uefcf뿶聃\uf730鉣\ue4b3氢렔\ueaef\ue8eeฮ\ue3d3攼乙馰䝦ꋳ⚙䂷䙒\ua7bd∥㓟\uf7e9羂䞀펾䢆蘲\ufd47钄ﳝ吭紩묵\uefb8䳝组")));
            if (var2 != null) {
                var2.onError(-1);
            }

        } else {
            boolean var4 = com.transistorsoft.locationmanager.util.c.b(var0);
            if (LifecycleManager.f().a() && !var3.getIsMoving() && var1 && !var4) {
                TSLog.logger.warn(TSLog.warn(Application.B("瀄횳\u139b잆揶榾쌔\ue2f1野⩺\ufd4b찾᷎᳅\uf18b끩≰चァ䳓Ս̫岙﮹\uefcc뿻耊\uf73f鉮\ue4f2氥렝\ueaaa\ue8caว\ue3d5敹丛馦䝭ꋱ⚒䂠䘝Ɡ∢㓈\uf7a8羗䞅펯䢊蘲ﴬ钥ﳿ名累묉\uefa8䳪纟떵ᛈ∨\ue2cf傻쌠ഘ꼐呥ֿ㴨锵\ue4e9ᣗ")));
                if (var3.requestsLocationAlways()) {
                    PermissionRequestListener var7;
                    var7 = new PermissionRequestListener() {
                        {
                            this.b = var2;
                            this.c = var3;
                        }

                        public void onPermissionGranted() {
                            if (com.transistorsoft.locationmanager.util.c.b(TrackingService.this)) {
                                Context var10000 = TrackingService.this;
                                <undefinedtype> var10001 = this;
                                boolean var1 = this.b;
                                TrackingService.changePace(var10000, var1, var10001.c);
                            } else {
                                TSLocationCallback var2;
                                if ((var2 = this.c) != null) {
                                    var2.onError(3);
                                }
                            }

                        }

                        public void onPermissionDenied(DeniedPermissions var1) {
                            TSLocationCallback var2;
                            if ((var2 = this.c) != null) {
                                var2.onError(3);
                            }

                        }
                    }.<init>(var1, var2);
                    com.transistorsoft.locationmanager.util.c.g(var0, var7);
                } else if (var2 != null) {
                    var2.onError(3);
                }

            } else {
                if (var3.getMotionTriggerDelay() >= 0L) {
                    TSScheduleManager.getInstance(var0).cancelOneShot(Application.B("瀊횝Ꭱ잡揖榄썫\ue2c6釴⩒ﵢ찞ᷮ᳇\uf1b5끎≐ॾゔ䳸"));
                }

                TSLocationManager var8 = TSLocationManager.getInstance(var0);
                boolean var5 = var3.getIsMoving();
                if (var2 == null && isAcquiringMotionChange() && var8.getRequest(sMotionChangeRequestId.get()) != null && var1 == var5) {
                    TSLog.logger.debug(TSLog.warn(Application.B("瀐횳\u139c잜揰榤썓\ue2b2釀⩴ﵗ챹᷎᳭\uf183끹≡ज़セ䳆Ԙ̣峟ﯭ\uefd2뿼耍\uf730鉣\ue4b3氢렔\ueaef\ue89e\u0e3d\ue3d5攨丌馢䝽ꋮ⛕䃱") + sMotionChangeRequestId.get() + Application.B("灧횦\u139a쟈揺榥썙\ue2e2释⩾ﵑ찼")));
                } else {
                    var3.setIsMoving(var1);
                    TSGeofenceManager var6 = TSGeofenceManager.getInstance(var0);
                    if (var1) {
                        HeartbeatService.stop(var0);
                        var6.startMonitoringSignificantLocationChanges();
                        var6.stopMonitoringStationaryRegion();
                        if (!var3.getUseSignificantChangesOnly()) {
                            startService(var0, Application.B("瀪횽ᎁ잁揶榤썗\ue2fa采⩵\ufd42찼"));
                        }
                    } else {
                        TSScheduleManager.getInstance(var0).cancelOneShot(Application.B("瀊횝Ꭱ잡揖榄썫\ue2d3釥⩏ﵬ찏ᷢ᳁\uf1b3끕≖ॺゐ䳢ճ"));
                        var6.stopMonitoringSignificantLocationChanges();
                        HeartbeatService.start(var0);
                    }

                    if (isAcquiringMotionChange()) {
                        var8.cancelRequest(sMotionChangeRequestId.get());
                        sMotionChangeRequestId.set(0);
                    }

                    TSMotionChangeRequest var10002 = ((Builder)(new Builder(var0)).setCallback(new TrackingService.c(var0, var1, var2))).build();
                    sMotionChangeRequestId.set(var10002.getId());
                    var8.getCurrentPosition(var10002);
                    if (var5 && !var1) {
                        var8.stopUpdatingLocation();
                        TSScheduleManager.getInstance(var0).cancelOneShot(Application.B("瀔횆Ꮊ잸揆榞썽\ue2df釣⩔ﵰ찍"));
                    }

                    TSLog.logger.info(TSLog.notice(Application.B("瀴횷ᎁ잸揸榩썑\ue2a8醆") + var5 + Application.B("灧\uf740Ꮥ") + var1));
                }
            }
        }
    }

    public static boolean isAcquiringMotionChange() {
        return sMotionChangeRequestId.get() != 0;
    }

    static void beginStopTimer(Context var0) {
        if (!TSConfig.getInstance(var0).getDisableStopDetection()) {
            Intent var1;
            Intent var10001 = var1 = new Intent;
            var10001.<init>(var0, TrackingService.class);
            var10001.setAction(Application.B("䝏焘麕燸늬旐㌢렓\ud801㚩ਧ朩"));
            AbstractService.startForegroundService(var0, var1);
        }
    }

    public static void changePace(Context var0, boolean var1) {
        changePace(var0, var1, (TSLocationCallback)null);
    }

    private static void startService(Context var0, String var1) {
        AbstractService.launchService(var0, TrackingService.class, var1);
    }

    public static void stopService(Context var0) {
        AbstractService.stop(var0, TrackingService.class);
    }

    private void handleLocationResult(Intent var1) {
        TSConfig var2 = TSConfig.getInstance(this.getApplicationContext());
        if (LocationAvailability.hasLocationAvailability(var1)) {
            LocationAvailability var3 = LocationAvailability.extractLocationAvailability(var1);
            TSLog.logger.info(TSLog.info(Application.B("乍扭ꆢᱦ卺뢆飅\ue0f5좛\udd3c㿼纠\ue2e9î쌌㥳符砜䚰咱ᶓ瓁\uf044") + var3.isLocationAvailable()));
        }

        Intent var10000 = var1;
        StringBuilder var8;
        StringBuilder var10001 = var8 = new StringBuilder;
        var10001.<init>();
        var10001.append(TSLog.header(Application.B("乕扰ꆠᱤ卥뢆飄\ue0fc죨\udd38㿸纷\ue2e9á쌈㤫筯砼䚶咦ᶋ璏\uf00d슊䇆ੵ힛伹\udc7aཱྀꫪ")));
        final LocationResult var10;
        LocationResult var11 = var10 = LocationResult.extractResult(var10000);
        TSGeofenceManager.getInstance(this).setLocation(var10.getLastLocation(), var2.getIsMoving());
        if (var11.getLastLocation() == null) {
            TSLog.logger.warn(TSLog.error(Application.B("乔扬ꆤ᱿卾뢊飉\ue0ef죞\udd39㾪纯\ue2f5î쌁㤱笩砂䚶咨᷊璷\uf00b슆䇉\u0a53힗伥\udc61྿\uaafb卾\uf428\uea04拺탷鈯ᮩ\uef48㖛\ue484嫿渘䮫䞜饈銛孂ꯅﵛ␤툑鯩腃㒗級㹩蘖\ue11c\ue13a\ue278⸬륛㝟軞䖃掦\uf61f駄໌Ꙍ椗솕溻ꧭ⢞䙬\ua957㼡⍭뉧뮦쥑➋ꈴ研生㻌蟟괹⚦ϥᥔ췓긃ꖤ◤ጱ鲿씭\ue1ee\u09d9駽㲖䣱郆\uf386䴰睳㨿褙")));
        } else {
            Iterator var4 = var10.getLocations().iterator();

            while(var4.hasNext()) {
                Location var5;
                if ((var5 = (Location)var4.next()).getExtras() == null) {
                    Bundle var6;
                    var6 = new Bundle.<init>();
                    var5.setExtras(var6);
                }

                long var12 = TSLocationManager.locationAge(var5);
                var8.append(TSLog.boxRow(Application.B("阼뻏ꇡᰧ") + var5));
                var8.append(TSLog.boxRow(Application.B("乀扥ꆤ᰽匮") + var12 + Application.B("乬扱ꇭᰧ卺뢆飇\ue0fe좁\udd7d") + var5.getTime()));
            }

            TSLog.logger.debug(var8.toString());
            final TSLocationManager var9 = TSLocationManager.getInstance(this.getApplicationContext());
            BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                public void run() {
                    var9.onLocationResult(var10);
                }
            });
            if (!var2.getDisableStopDetection()) {
                this.performStopDetection(var10.getLastLocation());
            }

            this.mLastLocationResult = var10;
        }
    }

    private void performStopDetection(Location var1) {
        if (this.mIsStopped && this.mStoppedAtLocation == null) {
            TSLog.logger.debug(Application.B("빰㏜\uda9d윃ᣌ\ue0b1랫煉醜炥枂⥆뼢\uf0cd䆱︩թ額\ue7b6뛉饶騨玛\udd89ᯛၩ䍚"));
            this.mStoppedAtLocation = var1;
        }

        LocationResult var2;
        if ((var2 = this.mLastLocationResult) != null) {
            long var6 = TSLocationManager.elapsedTimeMillis(var1, var2.getLastLocation());
            if (com.transistorsoft.locationmanager.util.c.a(this.getApplicationContext())) {
                if (ActivityRecognitionService.getLastActivity().getActivityType() != 3) {
                    this.cancelMotionActivityCheckTimer();
                    return;
                }

                if (!this.mIsStopped && this.beginStopTimer(var1)) {
                    return;
                }
            } else if (!this.mIsStopped) {
                this.beginMotionActivityCheckTimer(var6, var1);
                return;
            }

            if (this.mStoppedAtLocation == null) {
                TSLog.logger.warn(TSLog.warn(Application.B("빒㏜\uda8c윀ᣊ\ue0b5랣煾釈点枆⥭뼷\uf0c9䆱︮՜顐\ue7f9뛋餹騭玕\udd88ᯜၢ䌔귀\u1942踓棔\uf2fdᬶ갣็崿Ồ≌\uf257垎鈆渦퐍㛸ᇺ\ue4ea礌ꑈ൬\fퟂ揵ꅎ")));
            } else {
                TSConfig var4 = TSConfig.getInstance(this.getApplicationContext());
                float var5 = var1.distanceTo(this.mStoppedAtLocation) - this.mStoppedAtLocation.getAccuracy() - var1.getAccuracy();
                float var7;
                if ((var7 = (float)var4.getStationaryRadius()) <= 25.0F) {
                    var7 = 25.0F;
                }

                TSLog.logger.info(TSLog.info(Application.B("빦㏐\uda8d윒ᣄ\ue0a9랭煈醜炰构⥆뼿\uf09d䆧︹Շ顉\ue7e6뛀饽騊玎\uddb1ᯝၥ䍕귙\u1978踈棕\uf2b7᭦") + var5));
                if (var5 > var7) {
                    TSLog.logger.debug(TSLog.info(Application.B("빤㏖\uda8c윅ᣀ\ue0e7랭煌釒炵枓⥅뽲\uf0de䆵︣Ջ顜\ue7fa뚅饪騿玕\udd8d᯦ၯ䍙귈\u197e踒棏\uf2adᬢ갳ๆ嵞Ồ≯\uf218垌鈗渢퐅㛥ᇱ\ue4a4祅ꑕഡ\rퟁ揼ꅏ㹍会뀰씡㭥┅喽비Ṫ斫˳\ueca0⡖ধⓆ䜃ꄏ⨫괚㉣匯ᄓ죩\u0cfa른碑\udf20롐엾")));
                    if (this.mIsStopped) {
                        this.cancelStopTimer();
                    }

                    this.beginMotionActivityCheckTimer(var6, var1);
                }

            }
        }
    }

    private void handleMotionChangeResult() {
        TSConfig var1 = TSConfig.getInstance(this.getApplicationContext());
        TSLog.logger.info(TSLog.header(Application.B("记ፄ壥\ue516\uee63탕媰㢓힢䲋脨荇鋙ﰜ๓\ue006煴\ue273妀둖\ueb1d䂾ᓧ뙢权\ua87d䘃罶Ꝣ\ue537") + var1.getIsMoving()));
        if (this.mIsStopped && !var1.getIsMoving()) {
            this.cancelStopTimer();
        }

        if (var1.getIsMoving()) {
            if (var1.getStopAfterElapsedMinutes() > 0 && this.mStopUpdatingLocationAt == null) {
                long var2 = (long)var1.getStopAfterElapsedMinutes() * 60L * 1000L;
                this.mStopUpdatingLocationAt = new Date(System.currentTimeMillis() + var2);
                TSScheduleManager.getInstance(this.getApplicationContext()).oneShot(Application.B("讷።壋\ue525\uee57탽媘㢠ힴ䲼脅荴鋼ﰾ\u0e66\ue075煜\ue258妫둲\ueb3b䂞ᓑ뙞杧ꡀ"), var2, true);
            }

            if (var1.getStopOnStationary()) {
                this.mStopOnNextStationary = true;
                EventBus var4;
                if (!(var4 = EventBus.getDefault()).isRegistered(this)) {
                    var4.register(this);
                }
            }
        } else if (this.mStopOnNextStationary) {
            TSLog.logger.info(TSLog.info(Application.B("讷ፂ士\ue505\uee78탕媰㢓ퟑ䲁脴茑鋃ﰋ๗\ue052煰\ue273妚둞\ueb00䂩")));
            stop(this.getApplicationContext());
        } else {
            this.mStopUpdatingLocationAt = null;
            this.cancelStopTimer();
            this.cancelMotionActivityCheckTimer();
            TSLocationManager.getInstance(this.getApplicationContext()).stopUpdatingLocation();
        }

    }

    private void handleLocationAvailability(Intent var1) {
        LocationAvailability var2 = LocationAvailability.extractLocationAvailability(var1);
        if (this.mLocationAvailability == null || var2.isLocationAvailable() != this.mLocationAvailability.isLocationAvailable()) {
            TSLog.logger.info(TSLog.info(Application.B("ꊂᎳ眉\uf601\ud891㬻녶둙鿠\uf027හ\uec40䬉쫟힙쾺친똛珵念\uec3eꃙ毜") + var2.isLocationAvailable()));
        }

        this.mLocationAvailability = var2;
    }

    private void beginMotionActivityCheckTimer(long var1, Location var3) {
        if (var3 == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("ꇟ\ue201\ue1bd圮࿂藩ꊳȅ\ueaf4Ὲ⽏໋\uf53a阚鷒沊쑨\uf785㫱\uf394䟉\ueb85裨馍呜ᐛ\ue470⹈⡿ؓ祜䧸쳳\ue9bf㯈跉뭎⁎쨒ྒྷￔ减煉\ue52dꙨ㽰䱝穬癨ᔼ銋\uabff൬蠃ඣê")));
        } else {
            TSConfig var4;
            if (!(var4 = TSConfig.getInstance(this.getApplicationContext())).getDisableStopDetection()) {
                if (this.mMotionActivityCheckLocation == null || !(var4.getDistanceFilter() <= 0.0F)) {
                    this.mMotionActivityCheckLocation = var3;
                    long var10000 = var1 += 60000L;
                    long var7 = 300000L;
                    if (var10000 <= 300000L) {
                        var7 = var1;
                    }

                    TSScheduleManager var6 = TSScheduleManager.getInstance(this.getApplicationContext());
                    var6.cancelOneShot(Application.B("ꇰ\ue22b\ue18e圎\u0fe3藪ꊃȰ\ueadeῳ⽨ໜ\uf510阺鷢沣쑂\uf7b9㫍\uf394䟪"));
                    var6.oneShot(Application.B("ꇰ\ue22b\ue18e圎\u0fe3藪ꊃȰ\ueadeῳ⽨ໜ\uf510阺鷢沣쑂\uf7b9㫍\uf394䟪"), var7, true);
                }
            }
        }
    }

    private void cancelMotionActivityCheckTimer() {
        TSScheduleManager.getInstance(this.getApplicationContext()).cancelOneShot(Application.B("ﰃﯻை◺㵢☖鵫鮿\ufaf4㏡谏㺻걳䲗\uf64f踴됃䦬㳄\uf313肄"));
        this.mMotionActivityCheckLocation = null;
    }

    private void cancelStopTimer() {
        TrackingService var10000 = this;
        TrackingService var10001 = this;
        Context var1;
        TSScheduleManager.getInstance(var1 = this.getApplicationContext()).cancelOneShot(Application.B("凢㇉瘷ࡨ픬쾠䒪䮹㐩떽淇삲"));
        var10001.mIsStopped = false;
        var10000.mStoppedAtLocation = null;
        if (TSConfig.getInstance(var1).getIsMoving()) {
            TSMediaPlayer.getInstance().debug(var1, Application.B("凅\u31ee瘔ࡗ픐쾕䒗䮝㐃떜淿삇㙪뇗蹽̲琀藶勃廋띐芥౨Ⳋ䩐䕏⾬筜香\ue7b2Ꜿ"));
        }

    }

    private boolean beginStopTimer(Location var1) {
        if (this.mIsStopped) {
            return true;
        } else {
            TSConfig var2;
            if ((var2 = TSConfig.getInstance(this.getApplicationContext())).getDisableStopDetection()) {
                return false;
            } else if (VERSION.SDK_INT >= 18 && var1 != null && var1.isFromMockProvider()) {
                TSLog.logger.debug(Application.B("鼡굒黺燥늜旧㌀롾\ud828㚉\u0a11朜쯤佅ኵ\ud8ca뷅》梬雭鵒㠬\u1737ㄔꅟ霭露踎즺彎縉諕Ẓꖻ\ue363 䇴˹ꪉ\uecd0\u09ca噀妽㣾뺖涔뚿\ude65磽\ud81d쏆궗䣮颇蕟囒䕱餧賚쩂仝䁜탈挴\ueb52楚줯\u173b枺탔쪱붊溈부ꉠ撬\uf07a数䰍ᬹ⽨컋抺懢闭ֹ燡웒뢄\ue5a7헁컛헴쀵\ueebc萯씶ᅃ\ue89e꣬ႇ\ufb19墻勉ㆠ㻞\udd68㨯\ud82f"));
                return false;
            } else {
                this.mIsStopped = true;
                long var3 = var2.getStopTimeout() * 60L * 1000L;
                if (VERSION.SDK_INT >= 26 && var2.getIsMoving() && var2.getUseSignificantChangesOnly()) {
                    var3 = 1200000L;
                }

                if (var3 <= 0L) {
                    TSLog.logger.debug(TSLog.notice(Application.B("䝏焸麵燘닞旰㌂렳\ud821㚉ਇ有쮰佉\u12b6\ud8c5붕〜梬雽鴖")));
                    changePace(this.getApplicationContext(), false, (TSLocationCallback)null);
                    return true;
                } else {
                    TSMediaPlayer.getInstance().debug(this.getApplicationContext(), Application.B("䝨焿麶燇느日㌟렷\ud82b㚈ਟ朜쯾位ኽ\ud8c1붗〰梪雱鵞㠢ᜦㄮꅙ靨霩踋즑彅益論ẛꖦ\ue378¢"));
                    TSScheduleManager var10001 = TSScheduleManager.getInstance(this.getApplicationContext());
                    var10001.oneShot(Application.B("䝏焘麕燸늬旐㌢렓\ud801㚩ਧ朩"), var3, true);
                    var10001.cancelOneShot(Application.B("䝑焃麎燡늼旊㌴렟\ud807㚲\u0a3b末쯙佸ኃ\ud8fb붦〧梌雚鵼"));
                    LocationAvailability var5;
                    if ((var5 = this.mLocationAvailability) != null && var5.isLocationAvailable()) {
                        TSLocationManager.getInstance(this.getApplicationContext()).requestLocationUpdates();
                    } else {
                        this.mStoppedAtLocation = var1;
                    }

                    return true;
                }
            }
        }
    }

    public void onCreate() {
        super.doCreate(TrackingService.class.getSimpleName());
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        if (!this.start(var1, true)) {
            return 3;
        } else {
            EventBus var5;
            if (!(var5 = EventBus.getDefault()).isRegistered(this)) {
                var5.register(this);
            }

            String var6;
            String var10000 = var6 = var1.getAction();
            TSConfig var8 = TSConfig.getInstance(this.getApplicationContext());
            if (var10000 != null) {
                if (var6.contains(Application.B("缚칋\uf598ꐰ캧懓煯뫵飪蟡嶻餏"))) {
                    boolean var4;
                    if ((!var8.getIsMoving() || var8.getUseSignificantChangesOnly()) && !var8.getNotification().getSticky()) {
                        var4 = false;
                    } else {
                        var4 = true;
                    }

                    this.setSticky(var4);
                    if (var8.getIsMoving()) {
                        ForegroundNotification.setStartedAt((new Date()).getTime());
                    } else {
                        ForegroundNotification.setStartedAt(0L);
                    }

                    this.handleMotionChangeResult();
                } else if (var6.equalsIgnoreCase(Application.B("缤칰\uf5a3ꐉ캗懩煅뫐风蟀嶉餾"))) {
                    if (var8.isLocationTrackingMode() && var8.getIsMoving()) {
                        this.beginStopTimer(TSLocationManager.getInstance(this.getApplicationContext()).getLastGoodLocation());
                    } else {
                        this.setSticky(false);
                    }
                } else if (var6.equalsIgnoreCase(Application.B("缄칐\uf58dꐫ캼"))) {
                    TSLog.logger.debug(Application.B("编칇\uf598ꐰ캧懓然몽飸蟻嶽餘䟌"));
                    this.setSticky(true);
                }
            } else if (LocationResult.hasResult(var1)) {
                boolean var7;
                if (var8.getIsMoving() && !var8.getUseSignificantChangesOnly()) {
                    var7 = true;
                } else {
                    var7 = false;
                }

                this.setSticky(var7);
                this.handleLocationResult(var1);
            } else if (LocationAvailability.hasLocationAvailability(var1)) {
                this.handleLocationAvailability(var1);
            } else {
                TSLog.logger.warn(TSLog.warn(Application.B("缢칪\uf5a7ꐗ캇懪煂몽飂蟁嶈餯䟶㽵䫋槞\udc82倍爫ﮦ퍍\ud929듔ꉲ鿜") + var1.toString() + Application.B("罛츄") + var1.getExtras()));
            }

            if (var8.getEnabled() && var8.getNotification().getSticky()) {
                this.setSticky(true);
            }

            this.finish();
            return 3;
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onActivityTransitionEvent(ActivityTransitionEvent var1) {
        if (TSConfig.getInstance(this.getApplicationContext()).isLocationTrackingMode()) {
            if (var1.getTransitionType() == 0 && var1.getActivityType() != 3 && this.mIsStopped) {
                this.cancelStopTimer();
            }

        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onStopAfterElapsedMinutesEvent(StopAfterElapsedMinutesEvent var1) {
        this.mStopUpdatingLocationAt = null;
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onConfigChange(ConfigChangeEvent var1) {
        TSConfig var2 = TSConfig.getInstance(this);
        if (var1.isDirty(Application.B("ႝἁ\u0006\u0ff8킆案웗틒咴\uf334栂Ḅ栫ꁰ䜅뜼㦹")) && VERSION.SDK_INT < 26) {
            if (var2.getForegroundService()) {
                this.startForeground(9942585, ForegroundNotification.build(this));
            } else {
                this.stopForeground(true);
            }
        }

        if (var2.getForegroundService() && var1.isDirty(Application.B("႕ἁ\u0000\u0ff4킇桓웛틆咮\uf339栾ḏ"))) {
            ((NotificationManager)this.getSystemService(Application.B("႕ἁ\u0000\u0ff4킇桓웛틆咮\uf339栾ḏ"))).notify(9942585, ForegroundNotification.build(this.getApplicationContext()));
        }

        if (var1.isDirty(Application.B("႓Ἃ\u0015\u0fef킕桘웝틆咮\uf319栿ḕ格ꁴ䜚뜾㦰")) && !var2.getIsMoving()) {
            if (var2.getHeartbeatInterval() > 0) {
                HeartbeatService.start(this.getApplicationContext());
            } else {
                HeartbeatService.stop(this.getApplicationContext());
            }
        }

        if (var1.isDirty(Application.B("ႈἚ\u001b\u0fed킵桓웕틂咵\uf325栥")) && this.mIsStopped) {
            this.cancelStopTimer();
            this.beginStopTimer(TSLocationManager.getInstance(this.getApplicationContext()).getLastGoodLocation());
        }

        if (var1.isDirty(Application.B("ႈἚ\u001b\u0fed킠桜워틂咨\uf315栽Ḁ栩ꁵ䜉뜻㦑㯐旵㛿\uedf9糒\ud882"))) {
            int var3;
            int var10000 = var3 = var2.getStopAfterElapsedMinutes();
            TSScheduleManager var4;
            (var4 = TSScheduleManager.getInstance(this.getApplicationContext())).cancelOneShot(Application.B("ႨἺ;\u0fcd킾桻웾틳咟\uf302栎Ḥ栕ꁇ䜼뜌㦙㯽旄㛇\uedc4糹\ud8a4콻ﱕ쩥"));
            this.mStopUpdatingLocationAt = null;
            if (var10000 > 0) {
                long var5 = (long)var3 * 60L * 1000L;
                this.mStopUpdatingLocationAt = new Date(System.currentTimeMillis() + var5);
                var4.oneShot(Application.B("ႨἺ;\u0fcd킾桻웾틳咟\uf302栎Ḥ栕ꁇ䜼뜌㦙㯽旄㛇\uedc4糹\ud8a4콻ﱕ쩥"), var5, true);
            }
        }

        if (var1.isDirty(Application.B("ႎἝ\u0011࿎킈桝웖틎咼\uf339栲Ḁ样ꁲ䜯뜷㦽㯗旼㛯\uedfe糸\ud89f콃ﱩ")) && var2.getUseSignificantChangesOnly() && var2.getIsMoving()) {
            this.setSticky(false);
        }

        if (var1.isDirty(Application.B("႟ἇ\u0007\u0ffc킃桖웝틴咮\uf33f校ḥ格ꁲ䜉뜼㦨㯐旴㛤"))) {
            if (!var2.getDisableStopDetection()) {
                if (this.mLastLocationResult != null) {
                    Location var10001 = new Location(Application.B("ႯἽ8\u0ff2킂桛워틎咵\uf33e栜Ḁ样ꁧ䜋뜺㦮"));
                    var10001.set(this.mLastLocationResult.getLastLocation());
                    var10001.setTime(System.currentTimeMillis());
                    this.performStopDetection(var10001);
                } else {
                    TSLocationManager.getInstance(this.getApplicationContext()).updateLocationRequest();
                }
            } else {
                this.cancelMotionActivityCheckTimer();
                this.cancelStopTimer();
            }
        }

    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onMotionActivityCheckEvent(MotionActivityCheckEvent var1) {
        if (!this.mIsStopped) {
            if (com.transistorsoft.locationmanager.util.c.a(this.getApplicationContext())) {
                ActivityRecognitionService.start(this.getApplicationContext());
                return;
            }

            LocationResult var8;
            if ((var8 = this.mLastLocationResult) == null) {
                TSLog.logger.warn(TSLog.warn(Application.B("\u2fe9걦땭䳐跜复\ue575黺涕ರ弉껔祜编鋡れ窞牜週㹛ꮱ旗蒢댩\u2fe2\udcb6ᤝ\u2450鳨ힽ袎뜲ᶓ硱㭷㾓䬲\uded9隣즼☰䄯궊\ude33짪Ῠ楸뛎蒜撚\uf47dꕙṞ里꺥蒡銧忥惛鱽ꋂꭷඥ寲雛녬ꤸ㱢㴯")));
                TSLocationManager.getInstance(this.getApplicationContext()).updateLocationRequest();
                return;
            }

            Location var2;
            Location var9;
            if ((var9 = var8.getLastLocation()) == null || (var2 = this.mMotionActivityCheckLocation) == null) {
                TSLog.logger.warn(TSLog.warn(Application.B("⿋걧땔䳖跇夊\ue55b黷涠\u0cba弋껔神缆鋖ゝ窸牗逿㸘ꮼ旳蒽댠\u2fe9\udca6ᤝ⑁鳨ힻ袃띶ᷝ硦㭮㾋䬲\uded3隣즪☿䄿귃\ude3d즤ᾨ椭뚂蒢擟\uf464ꕧṆ釖꺿蒊鋦後悗鰩ꋇꭹම寔雲녰꤮㱯㴯뀘ﾎ襛袱鎗") + var9 + Application.B("⾌걥땏䳋跁夋\ue574黕涷ಧ弔껋祃缋鋬ザ窵牑逷㹓ꮖ旽蒷댭⿸\udcabᥒ\u2458鲻ퟯ") + this.mMotionActivityCheckLocation));
                TSLocationManager.getInstance(this.getApplicationContext()).updateLocationRequest();
                return;
            }

            float var11 = var9.distanceTo(var2) - this.mMotionActivityCheckLocation.getAccuracy() - var9.getAccuracy();
            long var3 = TSLocationManager.elapsedTimeMillis(var9, this.mMotionActivityCheckLocation);
            this.mMotionActivityCheckLocation = null;
            TSLog.logger.info(TSLog.info(Application.B("⿂걡땓䳋跉夊\ue579黱淴ವ式껒祇罟鋸゚窩牝逻㹖꯷旳蒷댸\u2fe5\udcb4ᥔ⑂鳸ퟯ袈뜾ᷖ硰㭩㿇䭾\uded0什즨☪䄢귅\ude3c짰ᾦ") + var11));
            TSConfig var5;
            int var6;
            if ((var6 = (var5 = TSConfig.getInstance(this.getApplicationContext())).getStationaryRadius()) < 25) {
                var6 = 25;
            }

            if (var5.getDistanceFilter() > 0.0F || var11 <= (float)var6) {
                if (!this.beginStopTimer(var9)) {
                    TrackingService var10000 = this;
                    Location var7 = this.mMotionActivityCheckLocation;
                    var10000.beginMotionActivityCheckTimer(var3, var7);
                }

                return;
            }

            LocationAvailability var10;
            if ((var10 = this.mLocationAvailability) == null || !var10.isLocationAvailable()) {
                TSLog.logger.warn(TSLog.warn(Application.B("⿋걧땔䳖跇夊\ue55b黷涠\u0cba弋껔神缆鋖ゝ窸牗逿㸘ꮼ旻蒦댩\u2fe8\udce2᥊\u245f鳵\ud7a7裋뜸ᷜ砳㭮㾈䭱\udede離즠☱䄥궊\ude33즼ῧ楤뛎蒑擘\uf479ꕹṖ釋꺨蓗鋨徦惨鱬ꋟ\uab6a\u0db2寉雐노")));
                this.beginMotionActivityCheckTimer(var3, this.mMotionActivityCheckLocation);
                return;
            }

            TSLocationManager.getInstance(this.getApplicationContext()).updateLocationRequest();
        }

    }

    public IBinder onBind(Intent var1) {
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        TSConfig var1 = TSConfig.getInstance(this.getApplicationContext());
        if (this.mIsStopped && !var1.getUseSignificantChangesOnly()) {
            this.cancelStopTimer();
        }

        TSScheduleManager.getInstance(this.getApplicationContext()).cancelOneShot(Application.B("䚵뙉㌴સ黅쌅餴榠델춹롅슐\ue028ퟷ陽丟桒ṁ휮\udff7ퟫ"));
        EventBus var2;
        if ((var2 = EventBus.getDefault()).isRegistered(this)) {
            var2.unregister(this);
        }

    }

    static class c implements TSLocationCallback {
        private final boolean a;
        private final Context b;
        private final TSLocationCallback c;

        c(Context var1, boolean var2, TSLocationCallback var3) {
            this.b = var1;
            this.a = var2;
            this.c = var3;
        }

        public void onLocation(TSLocation var1) {
            TrackingService.sMotionChangeRequestId.set(0);
            TSGeofenceManager var2 = TSGeofenceManager.getInstance(this.b);
            TSLocationCallback var3;
            if ((var3 = this.c) != null) {
                var3.onLocation(var1);
            }

            if (!this.a) {
                var2.startMonitoringStationaryRegion(var1.getLocation());
                if (!TSConfig.getInstance(this.b).getUseSignificantChangesOnly()) {
                    TrackingService.startService(this.b, Application.B("⧌贷ꖼ빶왪冁渭\ue826৯샷㾻ꬣ"));
                }
            }

        }

        public void onError(Integer var1) {
            TrackingService.sMotionChangeRequestId.set(0);
            TSLocationCallback var2;
            if ((var2 = this.c) != null) {
                var2.onError(var1);
            }

            if (this.a) {
                TSLocationManager.getInstance(this.b).requestLocationUpdates();
            } else if (TSConfig.getInstance(this.b).getDisableMotionActivityUpdates()) {
                TrackingService.changePace(this.b, this.a, (TSLocationCallback)null);
            }

        }
    }
}
