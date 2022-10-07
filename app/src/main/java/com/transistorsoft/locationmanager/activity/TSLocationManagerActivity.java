//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsRequest.Builder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.config.TSBackgroundPermissionRationale;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.concurrent.atomic.AtomicInteger;

public class TSLocationManagerActivity extends AppCompatActivity {
    public static final String ACTION_ACTIVITY_IS_ACTIVE = Application.B("蹹쉶煁ꤦ䗀鈆铍박紌᷁遍▟汢裂幇焧ѯ엒");
    private static final AtomicInteger sEventCount = new AtomicInteger(0);
    public static final int LOCATION_SETTINGS_REQUEST_CODE = 101;
    public static final String ACTION_LOCATION_SETTINGS = Application.B("蹔쉚煶ꤎ䗢鈦银밢素ᷭ遪▴汊轢年焝");
    public static final String ACCESS_BACKGROUND_LOCATION = Application.B("蹙쉛煱ꤝ䗹鈦铽뱢紣ᷭ遬▭汊戀幠焇і엹쑑렴\uf6a3퇂䪻䞎針\ue5d7홀덅㡰ম뻊㣡쑒ࠓ\udfa9䠸\ue220ꆜ坩ൔ춡ᐗ۾㡩矹");

    public TSLocationManagerActivity() {
    }

    public static boolean isActive() {
        return sEventCount.get() > 0;
    }

    public static void startIfEnabled(Context var0, String var1) {
        TSConfig var2 = TSConfig.getInstance(var0);
        if (!var1.equalsIgnoreCase(Application.B("셤ῤꟺ䫉덀ʤ氡\ueef8⎫ⴱ槝䕯Ꜽ\u0af4䞡吳")) || var2.getEnabled()) {
            start(var0, var1);
        }
    }

    public static void start(final Context var0, String var1) {
        if (!var1.equalsIgnoreCase(Application.B("鬺ﱠ쫨鑅ቆ욱䢈츆⢸渥\uecdf䈍噙鱡刭蒓")) || !TSConfig.getInstance(var0).getDisableLocationAuthorizationAlert()) {
            final Intent var2;
            Intent var10000 = var2 = new Intent;
            var2.<init>(var0, TSLocationManagerActivity.class);
            var2.setFlags(268435456);
            var10000.setAction(var1);
            (new Handler(Looper.getMainLooper())).postDelayed(new Runnable() {
                public void run() {
                    try {
                        var0.startActivity(var2);
                    } catch (ActivityNotFoundException var1) {
                        TSLog.logger.error(TSLog.error(var1.getMessage()), var1);
                    }

                }
            }, 200L);
        }
    }

    private void execute(Intent var1) {
        sEventCount.incrementAndGet();
        String var2;
        if ((var2 = var1.getAction()) == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("\ua7e2䎼䫛杪ｘ뗞㹐॥̒榸콥㶦ᒹ⬎糯ޢ漑\uebd1\uf8bb\u4dbfㅎ\udebb⠌ꆑ\ue1a2鶻莚盃孷\uf60f䟙\uf366繦깢䐔ᙴ摼饺誦ཁﻛ攳螋\udef6깏\ueae7隆횲웒䥐䇔དྷ순ゆ\udd90\uf5e1\ue502ㅤ쳡廯")));
            this.stop();
        } else {
            TSLog.logger.debug(var2);
            if (var2.equalsIgnoreCase(Application.B("\ua7db䎽䫓来ｃ뗀㹑फ̀榾콥㶻ᒿ⬎粨ޢ"))) {
                this.checkLocationSettings();
            } else if (var2.equalsIgnoreCase(Application.B("\ua7d6䎼䫔杶ｘ뗀㹚५̃榾콣㶢ᒿ⬓粼\u07b8漛\uebd1\uf8e1䷞ㅹ\ude97⡩ꆖ\ue1a2鶨获盡孕\uf630䟷\uf35b繇깺䐻ᙞ摂饑誌\u0f70ﻛ攄螶\uded0깷"))) {
                this.requestBackgroundLocationPermission();
            }

        }
    }

    private void checkLocationSettings() {
        LocationRequest var1 = TSLocationManager.getInstance(this.getApplicationContext()).buildLocationRequest();
        Builder var2 = (new Builder()).addLocationRequest(var1);
        LocationServices.getSettingsClient(this).checkLocationSettings(var2.build()).addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            public void onComplete(Task<LocationSettingsResponse> param1) {
                // $FF: Couldn't be decompiled
            }
        });
    }

    private void requestBackgroundLocationPermission() {
        TSBackgroundPermissionRationale var10000 = TSConfig.getInstance(this.getApplicationContext()).getBackgroundPermissionRationale();
        LifecycleManager.f().c();
        TSLocationManagerActivity.CompletionHandler var1;
        var1 = new TSLocationManagerActivity.CompletionHandler() {
            public void onComplete() {
                TSLocationManagerActivity.this.stop();
            }
        }.<init>();
        var10000.onStartActivity(this, var1);
    }

    private void stop() {
        int var1;
        int var10000 = var1 = sEventCount.decrementAndGet();
        TSLog.logger.debug(Application.B("뼾䈊鵤섵궝Ԗ⦠\ue14aី迭\uf764吷") + var1);
        if (var10000 <= 0) {
            sEventCount.set(0);
            LifecycleManager.f().d();
            this.finish();
        }

    }

    protected void onCreate(@Nullable Bundle var1) {
        super.onCreate(var1);
        LifecycleManager.f().c();
        this.execute(this.getIntent());
    }

    protected void onNewIntent(Intent var1) {
        super.onNewIntent(var1);
        this.execute(var1);
    }

    protected void onActivityResult(int var1, int var2, Intent var3) {
        Intent var10001 = var3;
        super.onActivityResult(var1, var2, var3);

        try {
            LocationSettingsStates.fromIntent(var10001);
        } catch (NullPointerException var4) {
            TSLog.logger.debug(TSLog.ok(Application.B("㶋앾\ude57\udf20臋\u17ee⌃⊶聋\ue63f\uf762䅙ᷨեꍼ拎娟縨┼맧뗇설럩\uf4df搇谧ᡮ\udca4垴傆韚믚\u2e65镀쎘\ua87cﮕ啊")));
            this.stop();
            return;
        }

        if (var1 == 101) {
            if (var2 != -1) {
                if (var2 == 0) {
                    TSLog.logger.debug(TSLog.cancel(Application.B("㶋앾\ude57\udf20臋\u17ee⌃⊶聋\ue63f\uf762䅙ᷨեꍼ拎娟縨┼맧뗇설럩\uf4df搇谧ᡮ\udca4垴傆韟믜\u2e68镌쎍ꡬ")));
                }
            } else {
                TSLog.logger.debug(TSLog.ok(Application.B("㶋앾\ude57\udf20臋\u17ee⌃⊶聋\ue63f\uf762䅙ᷨեꍼ拎娟縨┼맧뗇설럩\uf4df搇谧ᡮ\udca4垴傆韚믚\u2e65镀쎘\ua87cﮕ啊")));
            }
        }

        this.stop();
    }

    protected void onDestroy() {
        super.onDestroy();
        TSLog.logger.debug("");
    }

    public interface CompletionHandler {
        void onComplete();
    }
}

