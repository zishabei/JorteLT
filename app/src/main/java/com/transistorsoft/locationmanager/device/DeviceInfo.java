//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.transistorsoft.locationmanager.a.a;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo {
    private static DeviceInfo e;
    private static final String f = Application.B("辊浖ၷ쭷\uda90厾\ueeea");
    private static final String g = com.transistorsoft.locationmanager.a.a.a();
    public static String MANUFACTURER_HUAWEI = Application.B("较浍ၲ쭲\uda9a厾");
    public static final String ACTION_GET_DEVICE_INFO = Application.B("辬浝ၧ쭁\uda9a厡\ueee7擨磃ḗꠌ䗡燻");
    private String a;
    private final String b;
    private final String c;
    private final String d;

    public static DeviceInfo getInstance(Context var0) {
        if (e == null) {
            e = a(var0.getApplicationContext());
        }

        return e;
    }

    private static synchronized DeviceInfo a(Context var0) {
        if (e == null) {
            e = new DeviceInfo(var0.getApplicationContext());
        }

        return e;
    }

    @SuppressLint({"HardwareIds"})
    public DeviceInfo(Context var1) {
        this.b = Build.MODEL;
        this.c = Build.MANUFACTURER;
        this.d = VERSION.RELEASE;
    }

    public String getUniqueId() {
        return this.a;
    }

    public String getModel() {
        return this.b;
    }

    public String getPlatform() {
        return Application.B("遀篩\udd2a䍦搋䒪ഘ");
    }

    public String getManufacturer() {
        return this.c;
    }

    public String getVersion() {
        return this.d;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap();
        String var10008 = this.b;
        var1.put(Application.B("柲썚\uf0b6︬\ufbc7"), var10008);
        String var10006 = this.c;
        var1.put(Application.B("柲썔\uf0bc︼\ufbcd誶矾ᛝ\ue42a\udda7營羕"), var10006);
        String var10004 = this.d;
        var10000.put(Application.B("柩썐\uf0a0︺\ufbc2誸石"), var10004);
        var10000.put(Application.B("柯썙\uf0b3︽\ufbcd誸矯ᛄ"), Application.B("柞썛\uf0b6︻\ufbc4誾矹"));
        String var10002 = g;
        var10000.put(Application.B("柹썇\uf0b3︤\ufbce誠矲ᛛ\ue434"), var10002);
        return var10000;
    }

    public JSONObject toJson() {
        JSONObject var10000 = new JSONObject();
        JSONObject var10001 = var10000;
        JSONObject var1;
        JSONObject var10002 = var1 = var10000;
        DeviceInfo var10003 = this;
        JSONObject var10004 = var1;
        DeviceInfo var10005 = this;
        JSONObject var10006 = var1;
        DeviceInfo var10007 = this;
        String var7 = Application.B("瀅諑\ude5d\uee12짍");

        JSONException var9;
        label53: {
            boolean var10;
            try {
                var10006.put(var7, var10007.b);
            } catch (JSONException var6) {
                var9 = var6;
                var10 = false;
                break label53;
            }

            var7 = Application.B("瀅諟\ude57\uee02짇哠䴱悳㚾鞝⟻ၵ");

            try {
                var10004.put(var7, var10005.c);
            } catch (JSONException var5) {
                var9 = var5;
                var10 = false;
                break label53;
            }

            var7 = Application.B("瀞諛\ude4b\uee04질哮䴼");

            try {
                var10002.put(var7, var10003.d);
            } catch (JSONException var4) {
                var9 = var4;
                var10 = false;
                break label53;
            }

            try {
                var10001.put(Application.B("瀘諒\ude58\uee03짇哮䴠悪"), Application.B("瀩諐\ude5d\uee05짎哨䴶"));
            } catch (JSONException var3) {
                var9 = var3;
                var10 = false;
                break label53;
            }

            String var11 = Application.B("瀎諌\ude58\uee1a진哶䴽悵㚠");

            try {
                var10000.put(var11, g);
                return var1;
            } catch (JSONException var2) {
                var9 = var2;
                var10 = false;
            }
        }

        JSONException var8 = var9;
        TSLog.logger.error(TSLog.error(var8.getMessage()), var8);
        return var1;
    }

    public String print() {
        return this.c + Application.B("䫏") + this.b + Application.B("䫏\ued5c痡") + this.d + Application.B("䫏\ued34") + com.transistorsoft.locationmanager.a.a.a() + Application.B("䫆");
    }
}
