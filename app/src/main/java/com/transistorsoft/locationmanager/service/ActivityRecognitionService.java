//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import androidx.annotation.Nullable;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.ActivityTransitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.ActivityTransition.Builder;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.geofence.TSGeofenceManager;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.scheduler.TSScheduleManager;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.EventBus;

public class ActivityRecognitionService extends AbstractService {
    private static final AtomicBoolean sHasBooted = new AtomicBoolean(false);
    private static final long MOTION_TRIGGER_DELAY_SLC = 60000L;
    protected static AtomicBoolean sIsStarted = new AtomicBoolean(false);
    private static ActivityTransitionEvent sLastMotionActivity = new ActivityTransitionEvent(3, 0, 0L);
    private static final String[] BAD_VENDORS = new String[]{Application.B("\uef85鍜\udb59䡬姥⚷")};

    public ActivityRecognitionService() {
    }

    public static boolean isBadVendor() {
        return (new ArrayList(Arrays.asList(BAD_VENDORS))).contains(Build.MANUFACTURER);
    }

    public static boolean isStarted() {
        return sIsStarted.get();
    }

    public static void start(Context var0) {
        TSConfig var1;
        if (!(var1 = TSConfig.getInstance(var0 = var0.getApplicationContext())).isLocationTrackingMode()) {
            TSConfig var10000 = var1;
            TSGeofenceManager var4 = TSGeofenceManager.getInstance(var0);
            if (!var10000.getGeofenceModeHighAccuracy()) {
                return;
            }

            if (!var4.hasGeofences()) {
                return;
            }
        }

        if (c.a(var0)) {
            TSLog.logger.info(TSLog.on(Application.B("㫳㯏鵹삁\ueffb辱웄ﻵ\ud81a訽乏ࠗ횅ꙑ\u2d69都腯\udb85\uf674⬦ᔉ\ue218\uedfb乾憇耬Ϻ咚믠")));
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("㫣㯚鵶삝\uefe0迥욉ﻩ\ud81a訵乒ࠍ횈ꙝⵥ都腯\udb9c\uf673⭿ᔑ\ue25b\uedfa乧憕耤Ϻ咆뮳粷\u0cddﳇ\u1c8d㐂땭\uf181瓟꒰Ĥ匥巇鈵ඥ徸훌嗄㸬嶺ꇝ쐮ᯅஂ愼㻑\ue7c6ꐐᢴ\ue4c2\ud948")));
        }

        ActivityRecognitionClient var2 = ActivityRecognition.getClient(var0);
        PendingIntent var3 = getPendingIntent(var0, (String)null);
        var2.requestActivityUpdates(0L, var3);
        sIsStarted.set(true);
    }

    public static void stop(Context var0) {
        Context var10000 = var0;
        TSLog.logger.info(TSLog.off(Application.B("ﳯ빫껖\uf8f8쯉⻍杔쟷䖍䗟𢡄⛘﹙\udd7fＦ쌋\uef3d绋ꉆ\uf877煫䀯❃皌郑\ue31f㻇凉")));
        ActivityRecognitionClient var10001 = ActivityRecognition.getClient(var0);
        PendingIntent var1;
        var10001.removeActivityTransitionUpdates(var1 = getPendingIntent(var0, (String)null));
        var10001.removeActivityUpdates(var1);
        sIsStarted.set(false);
        stopService(var10000);
    }

    public static ActivityTransitionEvent getLastActivity() {
        return sLastMotionActivity;
    }

    public static ActivityTransitionEvent getMostProbableActivity() {
        return sLastMotionActivity;
    }

    public static boolean isMoving(Context var0) {
        return TSConfig.getInstance(var0).hasTriggerActivity(sLastMotionActivity.getActivityType());
    }

    private static void stopService(Context var0) {
        AbstractService.stop(var0, ActivityRecognitionService.class);
    }

    @TargetApi(26)
    private static PendingIntent getPendingIntent(Context var0, @Nullable String var1) {
        var0 = var0.getApplicationContext();
        Intent var2;
        var2 = new Intent.<init>(var0, ActivityRecognitionService.class);
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

    private int handleActivityTransitionResult(ActivityTransitionResult var1) {
        Context var2 = this.getApplicationContext();
        if (var1 == null) {
            return 3;
        } else {
            List var12;
            if ((var12 = var1.getTransitionEvents()) == null) {
                TSLog.logger.warn(TSLog.warn(Application.B("\uf6c7뒆ࣝ뇣ꏀ스덑꿟ṷᖤ뢵䡧䰄\uf212彲⅏磽泳杵ᡕꎱꁗ颒ᄔᬑ옘ᠹ剪\ue8a0\u2dbf\ue197⋂䵞\ud8c5뽬먣۟\ueba4遑꿠㗙⧏铤훡\ufaf1縈駶㡞㾹ꞏ퓟閎㉌\udeca䑃∁鳪\u0a4a茴䐂捱")));
                return 3;
            } else {
                List var10000 = var12;
                TSConfig var13 = TSConfig.getInstance(var2);
                StringBuilder var3;
                StringBuilder var10002 = var3 = new StringBuilder;
                var10002.<init>();
                var10002.append(TSLog.header(Application.B("\uf6e2뒈\u08c7뇮ꏃ슯댰꿨ṱᖬ뢭䡽䰙\uf21f彏⅒磲沽杔ᡙꎶꁋ频ᄎ")));
                TSScheduleManager var4 = TSScheduleManager.getInstance(var2);
                Iterator var5 = var10000.iterator();

                while(true) {
                    while(var5.hasNext()) {
                        ActivityTransitionEvent var6;
                        if ((var6 = (ActivityTransitionEvent)var5.next()).getTransitionType() == 0) {
                            ActivityTransitionEvent var7 = sLastMotionActivity;
                            sLastMotionActivity = var6;
                            var3.append(TSLog.boxRow(Application.B("⺓歙\u0893놧ꏩ슏덄꿹ṑᗷ룣") + Util.getActivityName(var6.getActivityType())));
                            EventBus.getDefault().post(var6);
                            boolean var8;
                            boolean var15 = var8 = var13.getIsMoving();
                            boolean var9 = var13.hasTriggerActivity(var6.getActivityType());
                            boolean var10;
                            if (!var15 && var9) {
                                var10 = true;
                            } else {
                                var10 = false;
                            }

                            boolean var11;
                            if (var8 && !var9) {
                                var11 = true;
                            } else {
                                var11 = false;
                            }

                            TSLog.logger.debug(Application.B("\uf685듍\u0899놧ꏛ슠덣꿱Ṭᖻ뢪䡠䰗\uf251弆") + var8 + Application.B("\uf683듇ࣝ뇨ꏛ슌덿꿊Ṫᖣ뢤䠴䱐") + var9 + Application.B("\uf683듇\u08c0뇳ꏍ슳덤꿙ṧᖀ뢬䡸䰙\uf205彁ℇ碼") + var10 + Application.B("\uf683듇ࣙ뇲ꏟ습덃꿈Ṭᖽ뢳䡫䰔\uf250弆") + var11);
                            if (var6.getActivityType() != 3 && !var11) {
                                if (this.isBackgroundWhenInUse() && !var13.getIsMoving()) {
                                    TSLog.logger.warn(TSLog.warn(Application.B("\uf6fd뒂ࣕ뇲ꏟ스덴꾜ṷᖢ룣䡧䰞\uf202归⅔磽泩杣\u181cꎩꁑ颞ᄛᬷ옔ᠥ剱\ue8e1ⶸ\ue1d2⋂䵍\ud8cf뽪먯ۚ\uebe1道꾲㗘⧗钨훀諭縈駭㡐㾹Ꞩ퓄閛㉋\uded6䑄∰鳵ੀ茴䑖捧҆맓悂ῆ⾥\udf43돠楽\ue780竸愿쎉\ude7a뢆\uea8fᡣ\ue17b귋枖Ӯ藠檞▒輇㿿嘜冷務鐃酟湹ޭ퍑躵\udf4c澺ୈ\ufefd靽英倍ꑆ蟭㋀ꄜ쐍挫ἢ슳ᷡ\uab6a")));
                                } else if (!var10 && sHasBooted.get()) {
                                    if (var8 && var13.isLocationTrackingMode() && var13.getUseSignificantChangesOnly()) {
                                        var4.cancelOneShot(Application.B("\uf6fc뒳ࣼ뇗ꏳ슕덙꿱Ṇᖂ뢖䡚"));
                                        TSMediaPlayer.getInstance().debug(var2, Application.B("\uf6db뒔ࣟ뇨ꏏ슠덤꿕Ṭᖣ뢮䡯䰞\uf20a彁⅘磮泂杤ᡙꎩꁒ颢ᄞᬪ옓ᠭ剀\ue8bcⶤ\ue1c7"));
                                    }
                                } else if (var13.isLocationTrackingMode()) {
                                    long var14 = var13.getMotionTriggerDelay();
                                    if (var13.getUseSignificantChangesOnly() && var14 < 60000L) {
                                        var14 = 60000L;
                                    }

                                    if (var9 && var14 > 0L) {
                                        TSMediaPlayer.getInstance().debug(var2, Application.B("\uf6db뒔ࣟ뇨ꏏ슠덤꿕Ṭᖣ뢮䡯䰞\uf20a彁⅘磮泂杢ᡓꎱꁡ颏ᄟᬷ옏ᠳ"));
                                        var4.oneShot(Application.B("\uf6e2뒨ࣧ뇎ꏣ슏덏꿨ṑᖄ뢄䡉䰵\uf239役ⅹ磙泑杇ᡥ"), var14, true);
                                    } else {
                                        TrackingService.changePace(var2, var9, (TSLocationCallback)null);
                                    }
                                } else {
                                    GeofencingService.changePace(var2, var9, (TSLocationCallback)null);
                                }
                            } else {
                                if (var13.getMotionTriggerDelay() >= 0L && !var8 && var7.getActivityType() != var6.getActivityType()) {
                                    TSMediaPlayer.getInstance().debug(var2, Application.B("\uf6db뒔ࣟ뇨ꏏ슠덤꿕Ṭᖣ뢮䡯䰞\uf20a彁⅘磮泂杢ᡓꎱꁡ颎ᄎᬬ옍ᠫ剼\ue8b8ⶢ\ue1d8⋞䴉"));
                                    var4.cancelOneShot(Application.B("\uf6e2뒨ࣧ뇎ꏣ슏덏꿨ṑᖄ뢄䡉䰵\uf239役ⅹ磙泑杇ᡥ"));
                                }

                                if (var13.getIsMoving()) {
                                    if (var13.isLocationTrackingMode()) {
                                        if (!sHasBooted.get()) {
                                            TrackingService.changePace(var2, var13.getIsMoving(), (TSLocationCallback)null);
                                        } else {
                                            TrackingService.beginStopTimer(var2);
                                        }
                                    } else {
                                        GeofencingService.changePace(var2, false, (TSLocationCallback)null);
                                    }
                                }
                            }

                            sHasBooted.set(true);
                        } else {
                            var3.append(TSLog.boxRow(Application.B("⺒槓\u0893놧ꏩ슙덙꿨ḹᗭ") + Util.getActivityName(var6.getActivityType())));
                        }
                    }

                    var3.append(Application.B("폵醷ⷣ铗蛼\ue791陀諬㭓ゝ鶓浞椠휻究ѭ巌䧍䉖㵬蚕蕮붭㐪㸓\ue32d㴚睏출\u089b쓧ߠ桫ﷶ驙鼚⏹캑땥誐ყ೪뇘\uf3dd\udf81嬬"));
                    TSLog.logger.info(var3.toString());
                    return 3;
                }
            }
        }
    }

    private int handleActivityRecognitionResult(ActivityRecognitionResult var1) {
        DetectedActivity var5 = var1.getMostProbableActivity();
        TSLog.logger.debug(TSLog.activity(var5.toString()));
        TSConfig var2 = TSConfig.getInstance(this.getApplicationContext());
        if (!sHasBooted.get() && var2.getIsMoving() && var5.getType() == 3) {
            if (var2.isLocationTrackingMode()) {
                TrackingService.beginStopTimer(this.getApplicationContext());
            } else {
                GeofencingService.changePace(this.getApplicationContext(), false, (TSLocationCallback)null);
            }
        }

        ArrayList var10000 = new ArrayList(6);
        var10000.add(3);
        var10000.add(2);
        var10000.add(7);
        var10000.add(0);
        var10000.add(1);
        var10000.add(8);
        ArrayList var6;
        var6 = new ArrayList.<init>(2);
        Iterator var7 = var10000.iterator();

        while(var7.hasNext()) {
            int var3 = (Integer)var7.next();
            var6.add((new Builder()).setActivityType(var3).setActivityTransition(0).build());
            var6.add((new Builder()).setActivityType(var3).setActivityTransition(1).build());
        }

        ActivityRecognitionClient var8 = ActivityRecognition.getClient(this.getApplicationContext());
        PendingIntent var4;
        var8.removeActivityUpdates(var4 = getPendingIntent(this.getApplicationContext(), (String)null));
        var8.requestActivityTransitionUpdates(new ActivityTransitionRequest(var6), var4);
        return 3;
    }

    private boolean isBackgroundWhenInUse() {
        return LifecycleManager.f().a() && !c.b(this.getApplicationContext());
    }

    public void onCreate() {
        super.doCreate(ActivityRecognitionService.class.getSimpleName());
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        boolean var10000 = super.start(var1, true);
        sIsStarted.set(var10000);
        if (!var10000) {
            return 2;
        } else {
            BackgroundGeolocation.getThreadPool().execute(new ActivityRecognitionService.a(var1));
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
            if (ActivityTransitionResult.hasResult(this.a)) {
                ActivityRecognitionService.this.handleActivityTransitionResult(ActivityTransitionResult.extractResult(this.a));
                ActivityRecognitionService.this.finish(250L);
            } else if (ActivityRecognitionResult.hasResult(this.a)) {
                ActivityRecognitionService.this.handleActivityRecognitionResult(ActivityRecognitionResult.extractResult(this.a));
                ActivityRecognitionService.this.finish(1000L);
            } else {
                ActivityRecognitionService.this.finish(0L);
            }

        }
    }
}
