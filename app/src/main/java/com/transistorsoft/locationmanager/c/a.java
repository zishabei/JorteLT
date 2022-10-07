//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.c;

import android.content.Context;
import androidx.annotation.NonNull;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.data.SQLQuery;
import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.geofence.TSGeofence.Builder;
import com.transistorsoft.locationmanager.geofence.TSGeofence.Exception;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.tslocationmanager.Application;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a implements Runnable {
    public static final String e = Application.B("❈冫弶㮻\uf241뀸밒\ue28d뗗ⱒ团撨菈ԯ☲ƍ닿\u2d77傝헝ଛꩴ");
    private static final String f = Application.B("❟冺弹㮿\uf247뀮백\ue297뗞");
    private static final List<a> g = new ArrayList();
    private static final AtomicBoolean h = new AtomicBoolean(false);
    private WeakReference<Context> a;
    private final String b;
    private Object c;
    private a.j d;

    public static void a(Context param0, JSONArray param1) {
        // $FF: Couldn't be decompiled
    }

    private static void a(JSONArray var0) {
        try {
            g.add(new a(var0));
        } catch (a.k var1) {
            TSLog.logger.error(TSLog.error(var1.toString()), var1);
        }

    }

    private static void b(final Context var0) {
        synchronized(g){}

        Throwable var10000;
        boolean var10001;
        boolean var14;
        try {
            var14 = g.isEmpty();
        } catch (Throwable var13) {
            var10000 = var13;
            var10001 = false;
            throw var10000;
        }

        if (var14) {
            try {
                h.set(false);
            } catch (Throwable var11) {
                var10000 = var11;
                var10001 = false;
                throw var10000;
            }
        } else {
            try {
                ((a)g.remove(0)).a(var0, new a.j() {
                    public void onSuccess() {
                        com.transistorsoft.locationmanager.c.a.b(var0);
                    }

                    public void a(a.k var1) {
                        TSLog.logger.error(TSLog.error(var1.toString() + Application.B("̀")), var1);
                        com.transistorsoft.locationmanager.c.a.b(var0);
                    }
                });
            } catch (Throwable var12) {
                var10000 = var12;
                var10001 = false;
                throw var10000;
            }
        }
    }

    private a(JSONArray var1) {
        JSONArray var10000 = var1;
        a var10001 = this;
        JSONArray var10002 = var1;
        super();
        this.c = null;

        int var4;
        boolean var5;
        try {
            var10001.b = var10002.getString(0);
            var4 = var10000.length();
        } catch (JSONException var3) {
            var5 = false;
            throw new a.k(Application.B("\uef29瀵唟\udb3f꙽ꆪ浿틬嚄安\ue565\uf3cb譺\udd61⩮錏騣瓴钨沂链爷糡뵥녠Ꝭ嬖\ude0a\ude75ȧ㜜뺰帛㘼☟\ue3ee봷ᆦ辱"));
        }

        if (var4 > 1) {
            try {
                this.c = var1.get(1);
            } catch (JSONException var2) {
                var5 = false;
                throw new a.k(Application.B("\uef29瀵唟\udb3f꙽ꆪ浿틬嚄安\ue565\uf3cb譺\udd61⩮錏騣瓴钨沂链爷糡뵥녠Ꝭ嬖\ude0a\ude75ȧ㜜뺰帛㘼☟\ue3ee봷ᆦ辱"));
            }
        }
    }

    private void a(Context var1, a.j var2) {
        this.a = new WeakReference(var1);
        this.d = var2;
        BackgroundGeolocation.getThreadPool().execute(this);
    }

    private void i() {
        BackgroundGeolocation.getInstance(this.e()).stop(new TSCallback() {
            public void onSuccess() {
                a.this.d.onSuccess();
            }

            public void onFailure(String var1) {
                a.this.d.a(new a.k(var1));
            }
        });
    }

    private void a(int var1) {
        Context var2;
        TSConfig var3;
        TSConfig var10000 = var3 = TSConfig.getInstance(var2 = this.e());
        boolean var4 = var10000.getEnabled();
        boolean var5 = var10000.getTrackingMode() != var1;
        var3.setTrackingMode(var1);
        TSLocationCallback var6;
        var6 = new TSLocationCallback() {
            public void onLocation(TSLocation var1) {
                a.this.d.onSuccess();
            }

            public void onError(Integer var1) {
                a.this.d.a(new a.k(var1.toString()));
            }
        }.<init>();
        if (var5 && var4) {
            TrackingService.changeTrackingMode(var2, var1, var6);
        } else {
            TrackingService.start(var2, var6);
        }

    }

    private void c() {
        this.a(Boolean.class);
        Context var10000 = this.e();
        BackgroundGeolocation var1 = BackgroundGeolocation.getInstance(var10000);
        TSConfig var3 = TSConfig.getInstance(var10000);
        boolean var2 = (Boolean)this.c;
        if (var3.getIsMoving() != var2) {
            BackgroundGeolocation var4 = var1;
            TSCallback var5;
            var5 = new TSCallback() {
                public void onSuccess() {
                    a.this.d.onSuccess();
                }

                public void onFailure(String var1) {
                    a.this.d.onSuccess();
                }
            }.<init>();
            var4.changePace(var2, var5);
        } else {
            this.d.onSuccess();
        }

    }

    private void h() {
        this.a(JSONObject.class);
        TSConfig.getInstance(this.e()).updateWithJSONObject((JSONObject)this.c);
        this.d.onSuccess();
    }

    private void a() {
        a var10000 = this;
        a var10001 = this;
        this.a(JSONObject.class);

        Object var1;
        try {
            BackgroundGeolocation.getInstance(var10001.e()).addGeofence(a((JSONObject)this.c));
            var10000.d.onSuccess();
            return;
        } catch (JSONException var2) {
            var1 = var2;
        } catch (Exception var3) {
            var1 = var3;
        }

        this.d.a(new a.k(((Throwable)var1).getMessage()));
    }

    private void b() {
        this.a(JSONArray.class);
        JSONArray var1 = (JSONArray)this.c;
        ArrayList var2;
        var2 = new ArrayList.<init>();

        for(int var3 = 0; var3 < var1.length(); ++var3) {
            Object var6;
            try {
                var2.add(a(var1.getJSONObject(var3)));
                continue;
            } catch (JSONException var4) {
                var6 = var4;
            } catch (Exception var5) {
                var6 = var5;
            }

            this.d.a(new a.k(((Throwable)var6).getMessage()));
            return;
        }

        BackgroundGeolocation var10000 = BackgroundGeolocation.getInstance(this.e());
        TSCallback var7;
        var7 = new TSCallback() {
            public void onSuccess() {
                a.this.d.onSuccess();
            }

            public void onFailure(String var1) {
                a.this.d.a(new a.k(var1));
            }
        }.<init>();
        var10000.addGeofences(var2, var7);
    }

    private void f() {
        this.a(String.class);
        Context var10000 = this.e();
        String var1 = String.valueOf(this.c);
        BackgroundGeolocation.getInstance(var10000).removeGeofence(var1, new TSCallback() {
            public void onSuccess() {
                a.this.d.onSuccess();
            }

            public void onFailure(String var1) {
                a.this.d.onSuccess();
            }
        });
    }

    private void g() {
        if (this.c == null) {
            JSONArray var1;
            var1 = new JSONArray.<init>();
            this.c = var1;
        }

        a var10000 = this;
        this.a(JSONArray.class);
        ArrayList var9;
        var9 = new ArrayList.<init>();

        JSONException var11;
        label44: {
            JSONArray var12;
            boolean var10001;
            try {
                var12 = (JSONArray)var10000.c;
            } catch (JSONException var7) {
                var11 = var7;
                var10001 = false;
                break label44;
            }

            JSONArray var2 = var12;

            int var13;
            try {
                var13 = var12.length();
            } catch (JSONException var6) {
                var11 = var6;
                var10001 = false;
                break label44;
            }

            int var3 = var13;
            int var4 = 0;

            while(true) {
                if (var4 >= var3) {
                    BackgroundGeolocation var15 = BackgroundGeolocation.getInstance(this.e());
                    TSCallback var10;
                    var10 = new TSCallback() {
                        public void onSuccess() {
                            a.this.d.onSuccess();
                        }

                        public void onFailure(String var1) {
                            a.this.d.onSuccess();
                        }
                    }.<init>();
                    var15.removeGeofences(var9, var10);
                    return;
                }

                try {
                    var9.add(var2.get(var4).toString());
                } catch (JSONException var5) {
                    var11 = var5;
                    var10001 = false;
                    break;
                }

                ++var4;
            }
        }

        JSONException var8 = var11;
        throw new a.k(var8.getMessage());
    }

    private void d() {
        BackgroundGeolocation.getInstance(this.e()).destroyLog(new TSCallback() {
            public void onSuccess() {
                a.this.d.onSuccess();
            }

            public void onFailure(String var1) {
                a.this.d.a(new a.k(var1));
            }
        });
    }

    private void j() {
        this.a(String.class);
        String var1 = String.valueOf(this.c);
        SQLQuery var2 = SQLQuery.create();
        Context var10000 = this.e();
        String var10001 = var1;
        TSCallback var3;
        var3 = new TSCallback() {
            public void onSuccess() {
                a.this.d.onSuccess();
            }

            public void onFailure(String var1) {
                a.this.d.a(new a.k(var1));
            }
        }.<init>();
        TSLog.uploadLog(var10000, var10001, var2, var3);
    }

    private Context e() {
        Context var1;
        if ((var1 = (Context)this.a.get()) != null) {
            return var1;
        } else {
            throw new a.k(Application.B("㲙껎榽\ueacdᣐሯ協건傤솓ᱷ伫"));
        }
    }

    private void a(Class<?> var1) {
        Object var2;
        if ((var2 = this.c) != null) {
            if (var2.getClass() != var1) {
                throw new a.k(this.b + Application.B("㹡⧿隶刀췀ˁ\ud8c1\u12c1緰䢬磙䴼ᬞ霓흏灂중\uea77巵磝ꢧ쬣໑鋟\ude1a纣\u0fff춗壓䣘") + var1 + Application.B("㹡⧸隻刄춅ː\ud8d0\u12c7緱䣥磎䴷᭚青") + this.c.getClass().toString());
            }
        } else {
            throw new a.k(this.b + Application.B("㹡⧿隶刀췀ˁ\ud8c1\u12c1緰䢬磙䴼ᬞ霓흏灂중\uea77巵磝ꢧ쬣໑鋟\ude1a纣\u0fff춗壓䣘") + var1 + Application.B("㹡⧸隻刄춅ː\ud8d0\u12c7緱䣥磎䴷᭚青흓灐줈\uea76"));
        }
    }

    private static TSGeofence a(JSONObject var0) {
        Builder var1;
        var1 = new Builder.<init>();
        if (var0.has(Application.B("㸨⧾隫刞췑ˋ\ud8d3ው緱䣾"))) {
            var1.setIdentifier(var0.getString(Application.B("㸨⧾隫刞췑ˋ\ud8d3ው緱䣾")));
        }

        if (var0.has(Application.B("㸭⧻隺则췑˗\ud8d1\u12c1"))) {
            var1.setLatitude(var0.getDouble(Application.B("㸭⧻隺则췑˗\ud8d1\u12c1")));
        }

        if (var0.has(Application.B("㸭⧵隠列췌˖\ud8c0ዀ緱"))) {
            var1.setLongitude((Double)var0.get(Application.B("㸭⧵隠列췌˖\ud8c0ዀ緱")));
        }

        if (var0.has(Application.B("㸳⧻險则췐ˑ"))) {
            var1.setRadius((float)var0.getDouble(Application.B("㸳⧻險则췐ˑ")));
        }

        if (var0.has(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣢磌䴠ᭇ"))) {
            var1.setNotifyOnEntry((Boolean)var0.get(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣢磌䴠ᭇ")));
        }

        if (var0.has(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣴磑䴦"))) {
            var1.setNotifyOnExit((Boolean)var0.get(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣴磑䴦")));
        }

        if (var0.has(Application.B("㸯⧵隺则췃˛\ud8faዊ緐䣻磝䴾᭒"))) {
            var1.setNotifyOnDwell((Boolean)var0.get(Application.B("㸯⧵隺则췃˛\ud8faዊ緐䣻磝䴾᭒")));
        }

        if (var0.has(Application.B("㸭⧵隧刄췀ː\ud8dcዊ緳䣈磝䴾᭟霋"))) {
            var1.setLoiteringDelay((Integer)var0.get(Application.B("㸭⧵隧刄췀ː\ud8dcዊ緳䣈磝䴾᭟霋")));
        }

        if (var0.has(Application.B("㸤⧢隺刂췄ˑ"))) {
            var1.setExtras(var0.getJSONObject(Application.B("㸤⧢隺刂췄ˑ")));
        }

        return var1.build();
    }

    public void run() {
        a var10000 = this;
        TSLog.logger.info(Application.B("閿錃豣ᕟ\uda40\uf53e\uebbbꌋ") + this.b + Application.B("댤洬") + this.c + Application.B("덃"));

        a.k var25;
        label145: {
            boolean var10001;
            boolean var26;
            try {
                var26 = var10000.b.equalsIgnoreCase(Application.B("덭浸豗ᕽ"));
            } catch (a.k var24) {
                var25 = var24;
                var10001 = false;
                break label145;
            }

            if (var26) {
                try {
                    this.i();
                    return;
                } catch (a.k var2) {
                    var25 = var2;
                    var10001 = false;
                }
            } else {
                label141: {
                    try {
                        var26 = this.b.equalsIgnoreCase(Application.B("덭浸豙ᕿ\uda64"));
                    } catch (a.k var23) {
                        var25 = var23;
                        var10001 = false;
                        break label141;
                    }

                    if (var26) {
                        try {
                            this.a(1);
                            return;
                        } catch (a.k var3) {
                            var25 = var3;
                            var10001 = false;
                        }
                    } else {
                        label137: {
                            try {
                                var26 = this.b.equalsIgnoreCase(Application.B("덭浸豙ᕿ\uda64\uf53a\uebe4ꍄ䎥ﳴ萊採ᲈ匯"));
                            } catch (a.k var22) {
                                var25 = var22;
                                var10001 = false;
                                break label137;
                            }

                            if (var26) {
                                try {
                                    this.a(0);
                                    return;
                                } catch (a.k var4) {
                                    var25 = var4;
                                    var10001 = false;
                                }
                            } else {
                                label133: {
                                    try {
                                        var26 = this.b.equalsIgnoreCase(Application.B("덽浤豙ᕣ\uda77\uf518\uebd1ꍊ䎠ﳴ"));
                                    } catch (a.k var21) {
                                        var25 = var21;
                                        var10001 = false;
                                        break label133;
                                    }

                                    if (var26) {
                                        try {
                                            this.c();
                                            return;
                                        } catch (a.k var5) {
                                            var25 = var5;
                                            var10001 = false;
                                        }
                                    } else {
                                        label129: {
                                            try {
                                                var26 = this.b.equalsIgnoreCase(Application.B("덭浩豌ᕎ\uda7f\uf513\uebe7ꍂ䎤"));
                                            } catch (a.k var20) {
                                                var25 = var20;
                                                var10001 = false;
                                                break label129;
                                            }

                                            if (var26) {
                                                try {
                                                    this.h();
                                                    return;
                                                } catch (a.k var6) {
                                                    var25 = var6;
                                                    var10001 = false;
                                                }
                                            } else {
                                                label125: {
                                                    try {
                                                        var26 = this.b.equalsIgnoreCase(Application.B("덿浨豜ᕊ\uda75\uf512\uebe7ꍎ䎭ﳲ萁"));
                                                    } catch (a.k var19) {
                                                        var25 = var19;
                                                        var10001 = false;
                                                        break label125;
                                                    }

                                                    if (var26) {
                                                        try {
                                                            this.a();
                                                            return;
                                                        } catch (a.k var7) {
                                                            var25 = var7;
                                                            var10001 = false;
                                                        }
                                                    } else {
                                                        label121: {
                                                            try {
                                                                var26 = this.b.equalsIgnoreCase(Application.B("덿浨豜ᕊ\uda75\uf512\uebe7ꍎ䎭ﳲ萁掱"));
                                                            } catch (a.k var18) {
                                                                var25 = var18;
                                                                var10001 = false;
                                                                break label121;
                                                            }

                                                            if (var26) {
                                                                try {
                                                                    this.b();
                                                                    return;
                                                                } catch (a.k var8) {
                                                                    var25 = var8;
                                                                    var10001 = false;
                                                                }
                                                            } else {
                                                                label117: {
                                                                    try {
                                                                        var26 = this.b.equalsIgnoreCase(Application.B("덬浩豕ᕢ\uda66\uf518\uebc6ꍎ䎬ﳷ萁掬\u1c8e匹"));
                                                                    } catch (a.k var17) {
                                                                        var25 = var17;
                                                                        var10001 = false;
                                                                        break label117;
                                                                    }

                                                                    if (var26) {
                                                                        try {
                                                                            this.f();
                                                                            return;
                                                                        } catch (a.k var9) {
                                                                            var25 = var9;
                                                                            var10001 = false;
                                                                        }
                                                                    } else {
                                                                        label113: {
                                                                            try {
                                                                                var26 = this.b.equalsIgnoreCase(Application.B("덬浩豕ᕢ\uda66\uf518\uebc6ꍎ䎬ﳷ萁掬\u1c8e匹佊"));
                                                                            } catch (a.k var16) {
                                                                                var25 = var16;
                                                                                var10001 = false;
                                                                                break label113;
                                                                            }

                                                                            if (var26) {
                                                                                try {
                                                                                    this.g();
                                                                                    return;
                                                                                } catch (a.k var10) {
                                                                                    var25 = var10;
                                                                                    var10001 = false;
                                                                                }
                                                                            } else {
                                                                                label109: {
                                                                                    try {
                                                                                        var26 = this.b.equalsIgnoreCase(Application.B("덫浼豔ᕢ\uda71\uf519\uebcdꍄ䎤"));
                                                                                    } catch (a.k var15) {
                                                                                        var25 = var15;
                                                                                        var10001 = false;
                                                                                        break label109;
                                                                                    }

                                                                                    if (var26) {
                                                                                        try {
                                                                                            this.j();
                                                                                            return;
                                                                                        } catch (a.k var11) {
                                                                                            var25 = var11;
                                                                                            var10001 = false;
                                                                                        }
                                                                                    } else {
                                                                                        label105: {
                                                                                            try {
                                                                                                var26 = this.b.equalsIgnoreCase(Application.B("덺浩豋ᕹ\uda62\uf512\uebf8ꍧ䎬ﳶ"));
                                                                                            } catch (a.k var14) {
                                                                                                var25 = var14;
                                                                                                var10001 = false;
                                                                                                break label105;
                                                                                            }

                                                                                            if (var26) {
                                                                                                try {
                                                                                                    this.d();
                                                                                                    return;
                                                                                                } catch (a.k var12) {
                                                                                                    var25 = var12;
                                                                                                    var10001 = false;
                                                                                                }
                                                                                            } else {
                                                                                                try {
                                                                                                    this.d.a(new a.k(Application.B("덋浢豓ᕣ\uda7f\uf50a\uebefꌋ䎠ﳾ萉掯\u1c8c匲佝㫨뷏") + this.b));
                                                                                                    return;
                                                                                                } catch (a.k var13) {
                                                                                                    var25 = var13;
                                                                                                    var10001 = false;
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

        a.k var1 = var25;
        this.d.a(var1);
    }

    interface j {
        void onSuccess();

        void a(a.k var1);
    }

    static class k extends java.lang.Exception {
        private final String a;

        k(String var1) {
            this.a = var1;
        }

        @NonNull
        public String toString() {
            return Application.B("捑䄆់✗\u0893썥䧰\ue6f1\uaafb큦Ᾰ漸罝믐ᮝ\u0e6c") + this.a;
        }
    }
}
