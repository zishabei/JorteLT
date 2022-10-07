//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import androidx.core.content.ContextCompat;
import androidx.core.location.LocationManagerCompat;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.provider.TSProviderManager;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationProviderChangeEvent {
    private int mPermission;
    private boolean mGpsEnabled;
    private boolean mNetworkEnabled;
    private int mStatus;
    private int mAccuracyAuthorization;
    private boolean mIsAirplaneMode;
    private long mLastUpdatedAt;

    public LocationProviderChangeEvent(Context var1) {
        this.init(var1);
    }

    public void init(Context var1) {
        this.mLastUpdatedAt = System.currentTimeMillis();
        this.mAccuracyAuthorization = TSProviderManager.ACCURACY_AUTHORIZATION_FULL;
        this.mPermission = ContextCompat.checkSelfPermission(var1, Application.B("퓥（ᣧ\uda34\uf049渑짒礣뢤俁⭻앜\ue312⊄労饊㷠瑋䙴᾿\u20c3ᷳ䢂⤍ﭓЮ鰩瓉坃\uf58e買\ue793\ua959⪜뚇琻哇龤ᜤ"));
        boolean var2 = c.c(var1);
        boolean var3;
        if (VERSION.SDK_INT >= 17) {
            if (android.provider.Settings.System.getInt(var1.getContentResolver(), Application.B("퓥／ᣱ\uda36\uf04a渙짘票뢋俉⭦압\ue31e⊨动饍"), 0) != 0) {
                var3 = true;
            } else {
                var3 = false;
            }

            this.mIsAirplaneMode = var3;
        }

        WifiManager var9 = (WifiManager)var1.getApplicationContext().getSystemService(Application.B("퓳／ᣥ\uda2f"));
        LocationManager var4;
        if ((var4 = (LocationManager)var1.getSystemService(Application.B("퓨）ᣠ\uda27\uf052渑짙祣"))) != null) {
            boolean var5;
            boolean var6;
            if ((var5 = LocationManagerCompat.isLocationEnabled(var4)) && var4.isProviderEnabled(Application.B("퓣６ᣰ"))) {
                var6 = true;
            } else {
                var6 = false;
            }

            this.mGpsEnabled = var6;
            if (var5 && var4.isProviderEnabled(Application.B("퓪＃\u18f7\uda31\uf049渊짝")) && var9.isWifiEnabled()) {
                var3 = true;
            } else {
                var3 = false;
            }

            this.mNetworkEnabled = var3;
        }

        this.mStatus = 0;
        if (!var2) {
            this.mStatus = TSProviderManager.PERMISSION_DENIED;
        } else if (VERSION.SDK_INT >= 29) {
            int var8;
            if (c.b(var1)) {
                var8 = TSProviderManager.PERMISSION_ALWAYS;
            } else {
                var8 = TSProviderManager.PERMISSION_WHEN_IN_USE;
            }

            this.mStatus = var8;
            if (VERSION.SDK_INT >= 31) {
                int var7;
                if (ContextCompat.checkSelfPermission(var1, Application.B("퓥（ᣧ\uda34\uf049渑짒礣뢤俁⭻앜\ue312⊄労饊㷠瑋䙴᾿\u20c3ᷳ䢂⤍ﭓЮ鰩瓉坃\uf58e買\ue793\ua959⪜뚇琻哇龤ᜤ")) == 0) {
                    var7 = TSProviderManager.ACCURACY_AUTHORIZATION_FULL;
                } else {
                    var7 = TSProviderManager.ACCURACY_AUTHORIZATION_REDUCED;
                }

                this.mAccuracyAuthorization = var7;
            }
        } else {
            this.mStatus = TSProviderManager.PERMISSION_ALWAYS;
        }

    }

    public void update(LocationProviderChangeEvent var1) {
        this.mLastUpdatedAt = System.currentTimeMillis();
        this.mAccuracyAuthorization = var1.getAccuracyAuthorization();
        this.mPermission = var1.getPermission();
        this.mIsAirplaneMode = var1.isAirplaneMode();
        this.mGpsEnabled = var1.isGPSEnabled();
        this.mNetworkEnabled = var1.isNetworkEnabled();
        this.mStatus = var1.getStatus();
    }

    public long elapsed() {
        return System.currentTimeMillis() - this.mLastUpdatedAt;
    }

    public boolean equals(LocationProviderChangeEvent var1) {
        return var1.isGPSEnabled() == this.mGpsEnabled && var1.isNetworkEnabled() == this.mNetworkEnabled && var1.isPermissionGranted() == this.isPermissionGranted() && var1.isEnabled() == this.isEnabled() && var1.getAccuracyAuthorization() == this.mAccuracyAuthorization && var1.isAirplaneMode() == this.mIsAirplaneMode && var1.getStatus() == this.mStatus;
    }

    public boolean isAirplaneMode() {
        return this.mIsAirplaneMode;
    }

    public boolean isGPSEnabled() {
        return this.mGpsEnabled;
    }

    public boolean isNetworkEnabled() {
        return this.mNetworkEnabled;
    }

    public boolean isEnabled() {
        return this.mGpsEnabled || this.mNetworkEnabled;
    }

    public boolean isPermissionGranted() {
        return this.mPermission == 0;
    }

    public int getAccuracyAuthorization() {
        return this.mAccuracyAuthorization;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public int getPermission() {
        return this.mPermission;
    }

    public JSONObject toJson() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject;
        LocationProviderChangeEvent var10001 = this;
        JSONObject var10002 = var1;
        LocationProviderChangeEvent var10003 = this;
        JSONObject var10004 = var1;
        LocationProviderChangeEvent var10005 = this;
        JSONObject var10006 = var1;
        LocationProviderChangeEvent var10007 = this;
        JSONObject var10008 = var1;
        LocationProviderChangeEvent var10009 = this;
        JSONObject var10010 = var1;
        LocationProviderChangeEvent var10011 = this;
        var1.<init>();
        String var8 = Application.B("䖟駄뿴幤땮Ù捝");

        JSONException var10;
        label61: {
            boolean var11;
            try {
                var10010.put(var8, var10011.mNetworkEnabled);
            } catch (JSONException var7) {
                var10 = var7;
                var11 = false;
                break label61;
            }

            var8 = Application.B("䖖駑뿳");

            try {
                var10008.put(var8, var10009.mGpsEnabled);
            } catch (JSONException var6) {
                var10 = var6;
                var11 = false;
                break label61;
            }

            var8 = Application.B("䖔駏뿡幱땭Î捒");

            try {
                var10006.put(var8, var10007.isEnabled());
            } catch (JSONException var5) {
                var10 = var5;
                var11 = false;
                break label61;
            }

            var8 = Application.B("䖂駕뿡幧땴Ø");

            try {
                var10004.put(var8, var10005.mStatus);
            } catch (JSONException var4) {
                var10 = var4;
                var11 = false;
                break label61;
            }

            var8 = Application.B("䖐駂뿣幦땳Ê捕堋\uefb0\ue0d0\uda3d蚇ꩆ쫞氵刉䥤㣼餅⩉ᤡ");

            try {
                var10002.put(var8, var10003.mAccuracyAuthorization);
            } catch (JSONException var3) {
                var10 = var3;
                var11 = false;
                break label61;
            }

            var8 = Application.B("䖐駈뿲幣땭Ê捘堗");

            try {
                var10000.put(var8, var10001.mIsAirplaneMode);
                return var1;
            } catch (JSONException var2) {
                var10 = var2;
                var11 = false;
            }
        }

        JSONException var9 = var10;
        TSLog.logger.error(TSLog.error(var9.getMessage()));
        var9.printStackTrace();
        return var1;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap;
        var1.<init>();
        var1.put(Application.B("팘漰夈畀\u2bf7ｃ䡕"), this.mNetworkEnabled);
        var1.put(Application.B("팑漥夏"), this.mGpsEnabled);
        var1.put(Application.B("팓漻夝畕\u2bf4ｔ䡚"), this.isEnabled());
        var1.put(Application.B("팅漡夝畃⯭ｂ"), this.mStatus);
        var1.put(Application.B("팗漶够畂\u2beaｐ䡝往\uf254\ue75a뒓\uf87f緟䇰\udd25㐴⺟佛梐飴ၨ"), this.mAccuracyAuthorization);
        var10000.put(Application.B("팗漼夎畇\u2bf4ｐ䡐徜"), this.mIsAirplaneMode);
        return var10000;
    }

    public void save(Context var1) {
        Editor var2;
        Editor var10000 = var2 = var1.getSharedPreferences(TSProviderManager.class.getSimpleName(), 0).edit();
        boolean var10012 = this.mNetworkEnabled;
        var2.putBoolean(Application.B("梕\udb04ㄧ筯聪㩌ぬ㿪蝅쒏肉\u18f6⁋尳"), var10012);
        boolean var10010 = this.mGpsEnabled;
        var2.putBoolean(Application.B("梜\udb11ㄠ筝聫㩟づ㿃蝎쒊"), var10010);
        int var10008 = this.mPermission;
        var2.putInt(Application.B("梋\udb04ㄡ筵聬㩍ぴ㿆蝄쒀"), var10008);
        int var10006 = this.mAccuracyAuthorization;
        var2.putInt(Application.B("梚\udb02\u3130筭職㩟つ㿖蝪쒛肟ᣲ⁁尥\ud9a6为컠譟\uf738뼻鯤"), var10006);
        boolean var10004 = this.mIsAirplaneMode;
        var2.putBoolean(Application.B("梒\udb12ㄒ筱職㩎に㿎蝅쒋肦ᣵ⁊尲"), var10004);
        int var10002 = this.mStatus;
        var10000.putInt(Application.B("梈\udb15ㄲ筬聰㩍"), var10002);
        var10000.apply();
    }

    public void load(Context var1) {
        this.init(var1);
        SharedPreferences var2;
        if ((var2 = var1.getSharedPreferences(TSProviderManager.class.getSimpleName(), 0)).contains(Application.B("㈇᪠뵫麟鹀뼨⺧ɞ獏ᶃ袪ᩚ\u31ee눪"))) {
            boolean var10017 = this.mNetworkEnabled;
            this.mNetworkEnabled = var2.getBoolean(Application.B("㈇᪠뵫麟鹀뼨⺧ɞ獏ᶃ袪ᩚ\u31ee눪"), var10017);
            boolean var10014 = this.mGpsEnabled;
            this.mGpsEnabled = var2.getBoolean(Application.B("㈎᪵뵬麭鹁뼻⺮ɷ獄ᶆ"), var10014);
            int var10011 = this.mPermission;
            this.mPermission = var2.getInt(Application.B("㈙᪠뵭麅鹆뼩⺿ɲ獎ᶌ"), var10011);
            int var10008 = this.mAccuracyAuthorization;
            this.mAccuracyAuthorization = var2.getInt(Application.B("㈈᪦뵼麝鹝뼻⺯ɢ獠ᶗ袼ᩞ\u31e4눼\ueec7ᐥ怰뇦蔵ۖ槃"), var10008);
            boolean var10005 = this.mIsAirplaneMode;
            this.mIsAirplaneMode = var2.getBoolean(Application.B("㈀᪶뵞麁鹝뼪⺠ɺ獏ᶇ袅ᩙ\u31ef눫"), var10005);
            int var10002 = this.mStatus;
            this.mStatus = var2.getInt(Application.B("㈚᪱뵾麜鹚뼩"), var10002);
        }
    }
}
