//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.Nullable;
import com.transistorsoft.locationmanager.activity.TSLocationManagerActivity;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import org.greenrobot.eventbus.EventBus;

public class TSProviderManager {
    public static int PERMISSION_DENIED;
    public static int PERMISSION_ALWAYS;
    public static int PERMISSION_WHEN_IN_USE;
    public static int ACCURACY_AUTHORIZATION_FULL;
    public static int ACCURACY_AUTHORIZATION_REDUCED;
    private static TSProviderManager sInstance;
    private BroadcastReceiver mProviderChangeReceiver;
    private final LocationProviderChangeEvent mLastEvent;

    public static TSProviderManager getInstance(Context var0) {
        if (sInstance == null) {
            sInstance = getInstanceSynchronized(var0);
        }

        return sInstance;
    }

    private static synchronized TSProviderManager getInstanceSynchronized(Context var0) {
        if (sInstance == null) {
            sInstance = new TSProviderManager(var0);
        }

        return sInstance;
    }

    private TSProviderManager(Context var1) {
        TSProviderManager var10000 = this;
        super();
        LocationProviderChangeEvent var2;
        var2 = new LocationProviderChangeEvent.<init>(var1);
        var10000.mLastEvent = var2;
    }

    private void handleProviderChangeEvent(Context var1, @Nullable Intent var2) {
        TSProviderManager var10000 = this;
        synchronized(this.mLastEvent){}

        Throwable var48;
        long var49;
        boolean var10001;
        try {
            var49 = var10000.mLastEvent.elapsed();
        } catch (Throwable var45) {
            var48 = var45;
            var10001 = false;
            throw var48;
        }

        if (var49 < 250L) {
            try {
                ;
            } catch (Throwable var43) {
                var48 = var43;
                var10001 = false;
                throw var48;
            }
        } else {
            Context var50;
            try {
                var50 = var1;
            } catch (Throwable var44) {
                var48 = var44;
                var10001 = false;
                throw var48;
            }

            TSConfig var46 = TSConfig.getInstance(var50);
            if (LifecycleManager.f().b() && !var46.getEnabled()) {
                TSLog.logger.info(TSLog.off(Application.B("\ue1de│ρ뗺䩌㭨켴\uefd0\uddc7㷒ቹꬩ㺂\ue6f1ꁬ㙇嗣陧\ue5ae马䔭蘈蔴\uf073軦䩠Ⲕ뫊蝀麫ណຣ遑︧⁘뇇ﾴ爟毘塳\ue724")));
                this.stopMonitoring(var1);
            }

            LocationProviderChangeEvent var47;
            LocationProviderChangeEvent var52 = var47 = new LocationProviderChangeEvent;
            TSProviderManager var51 = this;
            var47.<init>(var1);
            synchronized(this.mLastEvent){}

            boolean var53;
            try {
                var53 = var52.equals(var51.mLastEvent);
            } catch (Throwable var42) {
                var48 = var42;
                var10001 = false;
                throw var48;
            }

            if (var53) {
                try {
                    ;
                } catch (Throwable var40) {
                    var48 = var40;
                    var10001 = false;
                    throw var48;
                }
            } else {
                Context var54;
                try {
                    var50 = var1;
                    var54 = var1;
                    this.mLastEvent.update(var47);
                } catch (Throwable var41) {
                    var48 = var41;
                    var10001 = false;
                    throw var48;
                }

                TSLocationManager.getInstance(var54).onProviderChange(var47);
                TSLocationManagerActivity.startIfEnabled(var50, Application.B("\ue1e1┙ύ뗫䨘㭬켴\uefd0\udddd㷃ቢ\uab2f㺂\ue6f1ꁬ㘔"));
                TSLog.logger.info(TSLog.header(Application.B("\ue1c1┙ύ뗫䨘㭬켴\uefd0\udd83㷖ቤꬴ㺝\ue6f6ꁯ㘂嗽阨\ue5ae驥䔸蘏蔼\uf078軱䨰") + var47.isEnabled()) + TSLog.boxRow(Application.B("\ue1ca┦Ͻ떰䩌") + var47.isGPSEnabled()) + TSLog.boxRow(Application.B("\ue1c3┓Ϛ뗽䨃㭷켰\uef84\udd8e") + var47.isNetworkEnabled()) + TSLog.boxRow(Application.B("\ue1cc┦Ύ뗇䨃㭡켾\uef84\udd8e") + var47.isAirplaneMode()));
                EventBus.getDefault().post(var47);
            }
        }
    }

    public void startMonitoring(Context var1) {
        LocationProviderChangeEvent var2;
        LocationProviderChangeEvent var10001 = var2 = this.mLastEvent;
        TSProviderManager var10002 = this;
        synchronized(var2) {
            var10002.mLastEvent.init(var1);
        }

        if (this.mProviderChangeReceiver == null) {
            TSLog.logger.info(TSLog.on(Application.B("臩䘐賦㴔\udab3ꖝ끀\uda36優䙉\ud97d쮔\u0530搇ʷ坰죷鳢睻惵ⷲ蚚슅玻匞廧Ⴠ뱤촗\ufbc9\uf267\ue08a쒈邪⋑넡ΐ밀샞趘깄떿")));
            IntentFilter var5;
            IntentFilter var6 = var5 = new IntentFilter;
            var6.<init>();
            var6.addAction(Application.B("臛䘊賣㴔\udaa8ꗔ끉\uda77儨䙏\ud96a쮚Զ搇ʶ坹죹鳞睆惙ⷅ蚧슨玑匢庙ჯ뱕촰ﯾ\uf240\ue0a9쒨邜"));
            var6.addAction(Application.B("臛䘊賣㴔\udaa8ꗔ끉\uda77儭䙎\ud97d쮞Ԭ搚˷坶좴鳺睽惹ⷽ蛀슭玝匢庚ჼ뱗촶ﯺ\uf251\ue0a3쒢邜⊴"));
            this.mProviderChangeReceiver = new TSProviderManager.b();
            var1.getApplicationContext().registerReceiver(this.mProviderChangeReceiver, var5);
            if (LifecycleManager.f().b() && TSConfig.getInstance(var1).getEnabled()) {
                LocationProviderChangeEvent var4;
                var4 = new LocationProviderChangeEvent.<init>(var1);
                EventBus.getDefault().post(var4);
            }
        }

    }

    public void stopMonitoring(Context var1) {
        if (this.mProviderChangeReceiver != null) {
            try {
                var1.getApplicationContext().unregisterReceiver(this.mProviderChangeReceiver);
                this.mProviderChangeReceiver = null;
                TSLog.logger.info(TSLog.off(Application.B("曡䥰붛\ue57d㸳ꋤ凌﨩胧奀Ҹ桽Ã嘣\u208f庺쵭딾\ue630ꙁ쯡\ueb54ꍵ癗\uf028㌈\u1ca9\uea0d\ue6b6㝃\ud955킹鶞\uf6a9㝨定랖ꈿ殓潳꽿")));
            } catch (IllegalArgumentException var2) {
                TSLog.logger.error(TSLog.error(var2.getMessage()));
            }
        }

    }

    public void onConnectivityChange(Context var1, boolean var2) {
        TSProviderManager var10000 = this;
        LocationProviderChangeEvent var16;
        var16 = new LocationProviderChangeEvent.<init>(var1);
        synchronized(this.mLastEvent){}

        Throwable var17;
        boolean var10001;
        boolean var18;
        try {
            var18 = var10000.mLastEvent.equals(var16);
        } catch (Throwable var15) {
            var17 = var15;
            var10001 = false;
            throw var17;
        }

        if (!var18) {
            try {
                this.handleProviderChangeEvent(var1, (Intent)null);
            } catch (Throwable var14) {
                var17 = var14;
                var10001 = false;
                throw var17;
            }
        }

        try {
            ;
        } catch (Throwable var13) {
            var17 = var13;
            var10001 = false;
            throw var17;
        }
    }

    private static class b extends BroadcastReceiver {
        private b() {
        }

        public void onReceive(final Context var1, final Intent var2) {
            final TSProviderManager var3 = TSProviderManager.getInstance(var1.getApplicationContext());
            boolean var10000 = var2.getAction().equalsIgnoreCase(Application.B("齒㎹粑ꕶ緧漪覂㰥\uebe4齟Ȕ髕锱ᤸ剫ㄪ\u0ece䳴纈帳䛜ၖힹㆥ⼋ꯋ\ue5e0㼑髀ƭ鈠၉マ깒暈"));
            boolean var4 = var2.getBooleanExtra(Application.B("齀㎣粔ꕰ緭"), false);
            if (var10000 && !var4) {
                BackgroundGeolocation.getUiHandler().postDelayed(new Runnable() {
                    public void run() {
                        TSProviderManager var10000 = var3;
                        <undefinedtype> var10001 = this;
                        Context var1x = var1;
                        var10000.handleProviderChangeEvent(var1x, var2);
                    }
                }, 1000L);
                var3.handleProviderChangeEvent(var1, var2);
            } else {
                var3.handleProviderChangeEvent(var1, var2);
            }

        }
    }
}
