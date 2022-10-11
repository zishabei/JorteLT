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
    private String b;
    private Object c;
    private a.j d;

    public static void a(Context param0, JSONArray param1) {
        // $FF: Couldn't be decompiled
    }

    private static void a(JSONArray var0) {
        g.add(new a(var0));
    }

    private static void b(final Context context) {
        List<a> list = g;
        synchronized (list) {
            if (g.isEmpty()) {
                h.set(false);
                return;
            }
            g.remove(0).a(context, new j() {

                @Override
                public void onSuccess() {
                    com.transistorsoft.locationmanager.c.a.b(context);
                }

                @Override
                public void a(k k2) {
                    TSLog.logger.error(TSLog.error(k2.toString() + Application.B("̀")), (Throwable) k2);
                    com.transistorsoft.locationmanager.c.a.b(context);
                }
            });
            return;
        }
    }

    private a(JSONArray var1) {
        super();
        JSONArray var10000 = var1;
        JSONArray var10002 = var1;
        this.c = null;

        int var4 = var10000.length();
        try {
            b = var10002.getString(0);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        if (var4 > 1) {
            try {
                this.c = var1.get(1);
            } catch (JSONException ex) {
                ex.printStackTrace();
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
        };
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
        boolean var2 = (Boolean) this.c;
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
            };
            var4.changePace(var2, var5);
        } else {
            this.d.onSuccess();
        }

    }

    private void h() {
        this.a(JSONObject.class);
        TSConfig.getInstance(this.e()).updateWithJSONObject((JSONObject) this.c);
        this.d.onSuccess();
    }

    private void a() {
        a var10000 = this;
        a var10001 = this;
        this.a(JSONObject.class);

        Object var1;
        BackgroundGeolocation.getInstance(var10001.e()).addGeofence(a((JSONObject) this.c));
        var10000.d.onSuccess();
    }

    private void b() {
        this.a(JSONArray.class);
        JSONArray var1 = (JSONArray) this.c;
        ArrayList var2 = new ArrayList();

        for (int var3 = 0; var3 < var1.length(); ++var3) {
            Object var6;
            try {
                var2.add(a(var1.getJSONObject(var3)));
                continue;
            } catch (JSONException var4) {
                var6 = var4;
            }

            this.d.a(new a.k(((Throwable) var6).getMessage()));
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
        };
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
            var1 = new JSONArray();
            this.c = var1;
        }

        a var10000 = this;
        this.a(JSONArray.class);
        ArrayList var9 = new ArrayList();

        JSONException var11;
        label44:
        {
            JSONArray var12;
            boolean var10001;
            var12 = (JSONArray) var10000.c;

            JSONArray var2 = var12;

            int var13;
            var13 = var12.length();

            int var3 = var13;
            int var4 = 0;

            while (true) {
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
                    };
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
        try {
            throw new k(var8.getMessage());
        } catch (k ex) {
            ex.printStackTrace();
        }
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
        };
        TSLog.uploadLog(var10000, var10001, var2, var3);
    }

    private Context e() {
        return this.a.get();
    }

    private void a(Class<?> var1) {
        Object var2;
        if ((var2 = this.c) != null) {
            if (var2.getClass() != var1) {
                try {
                    throw new k(this.b + Application.B("㹡⧿隶刀췀ˁ\ud8c1\u12c1緰䢬磙䴼ᬞ霓흏灂중\uea77巵磝ꢧ쬣໑鋟\ude1a纣\u0fff춗壓䣘") + var1 + Application.B("㹡⧸隻刄춅ː\ud8d0\u12c7緱䣥磎䴷᭚青") + this.c.getClass().toString());
                } catch (k ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            try {
                throw new k(this.b + Application.B("㹡⧿隶刀췀ˁ\ud8c1\u12c1緰䢬磙䴼ᬞ霓흏灂중\uea77巵磝ꢧ쬣໑鋟\ude1a纣\u0fff춗壓䣘") + var1 + Application.B("㹡⧸隻刄춅ː\ud8d0\u12c7緱䣥磎䴷᭚青흓灐줈\uea76"));
            } catch (k ex) {
                ex.printStackTrace();
            }
        }
    }

    private static TSGeofence a(JSONObject var0) {
        Builder var1;
        var1 = new Builder();
        try {
            if (var0.has(Application.B("㸨⧾隫刞췑ˋ\ud8d3ው緱䣾"))) {
                var1.setIdentifier(var0.getString(Application.B("㸨⧾隫刞췑ˋ\ud8d3ው緱䣾")));
            }

            if (var0.has(Application.B("㸭⧻隺则췑˗\ud8d1\u12c1"))) {
                var1.setLatitude(var0.getDouble(Application.B("㸭⧻隺则췑˗\ud8d1\u12c1")));
            }

            if (var0.has(Application.B("㸭⧵隠列췌˖\ud8c0ዀ緱"))) {
                var1.setLongitude((Double) var0.get(Application.B("㸭⧵隠列췌˖\ud8c0ዀ緱")));
            }

            if (var0.has(Application.B("㸳⧻險则췐ˑ"))) {
                var1.setRadius((float) var0.getDouble(Application.B("㸳⧻險则췐ˑ")));
            }

            if (var0.has(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣢磌䴠ᭇ"))) {
                var1.setNotifyOnEntry((Boolean) var0.get(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣢磌䴠ᭇ")));
            }

            if (var0.has(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣴磑䴦"))) {
                var1.setNotifyOnExit((Boolean) var0.get(Application.B("㸯⧵隺则췃˛\ud8faዊ緑䣴磑䴦")));
            }

            if (var0.has(Application.B("㸯⧵隺则췃˛\ud8faዊ緐䣻磝䴾᭒"))) {
                var1.setNotifyOnDwell((Boolean) var0.get(Application.B("㸯⧵隺则췃˛\ud8faዊ緐䣻磝䴾᭒")));
            }

            if (var0.has(Application.B("㸭⧵隧刄췀ː\ud8dcዊ緳䣈磝䴾᭟霋"))) {
                var1.setLoiteringDelay((Integer) var0.get(Application.B("㸭⧵隧刄췀ː\ud8dcዊ緳䣈磝䴾᭟霋")));
            }

            if (var0.has(Application.B("㸤⧢隺刂췄ˑ"))) {
                var1.setExtras(var0.getJSONObject(Application.B("㸤⧢隺刂췄ˑ")));
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return var1.build();
    }

    public void run() {
        a var10000 = this;
        TSLog.logger.info(Application.B("閿錃豣ᕟ\uda40\uf53e\uebbbꌋ") + this.b + Application.B("댤洬") + this.c + Application.B("덃"));

        a.k var25;
        label145:
        {
            boolean var10001;
            boolean var26;
            var26 = var10000.b.equalsIgnoreCase(Application.B("덭浸豗ᕽ"));

            if (var26) {
                this.i();
                return;
            } else {
                label141:
                {
                    var26 = this.b.equalsIgnoreCase(Application.B("덭浸豙ᕿ\uda64"));

                    if (var26) {
                        this.a(1);
                        return;
                    } else {
                        label137:
                        {
                            var26 = this.b.equalsIgnoreCase(Application.B("덭浸豙ᕿ\uda64\uf53a\uebe4ꍄ䎥ﳴ萊採ᲈ匯"));

                            if (var26) {
                                this.a(0);
                                return;
                            } else {
                                label133:
                                {
                                    var26 = this.b.equalsIgnoreCase(Application.B("덽浤豙ᕣ\uda77\uf518\uebd1ꍊ䎠ﳴ"));

                                    if (var26) {
                                        this.c();
                                        return;
                                    } else {
                                        label129:
                                        {
                                            var26 = this.b.equalsIgnoreCase(Application.B("덭浩豌ᕎ\uda7f\uf513\uebe7ꍂ䎤"));

                                            if (var26) {
                                                this.h();
                                                return;
                                            } else {
                                                label125:
                                                {
                                                    var26 = this.b.equalsIgnoreCase(Application.B("덿浨豜ᕊ\uda75\uf512\uebe7ꍎ䎭ﳲ萁"));

                                                    if (var26) {
                                                        this.a();
                                                        return;
                                                    } else {
                                                        label121:
                                                        {
                                                            var26 = this.b.equalsIgnoreCase(Application.B("덿浨豜ᕊ\uda75\uf512\uebe7ꍎ䎭ﳲ萁掱"));

                                                            if (var26) {
                                                                this.b();
                                                                return;
                                                            } else {
                                                                label117:
                                                                {
                                                                    var26 = this.b.equalsIgnoreCase(Application.B("덬浩豕ᕢ\uda66\uf518\uebc6ꍎ䎬ﳷ萁掬\u1c8e匹"));

                                                                    if (var26) {
                                                                        this.f();
                                                                        return;
                                                                    } else {
                                                                        label113:
                                                                        {
                                                                            var26 = this.b.equalsIgnoreCase(Application.B("덬浩豕ᕢ\uda66\uf518\uebc6ꍎ䎬ﳷ萁掬\u1c8e匹佊"));

                                                                            if (var26) {
                                                                                this.g();
                                                                                return;
                                                                            } else {
                                                                                label109:
                                                                                {
                                                                                    var26 = this.b.equalsIgnoreCase(Application.B("덫浼豔ᕢ\uda71\uf519\uebcdꍄ䎤"));

                                                                                    if (var26) {
                                                                                        this.j();
                                                                                        return;
                                                                                    } else {
                                                                                        label105:
                                                                                        {
                                                                                            var26 = this.b.equalsIgnoreCase(Application.B("덺浩豋ᕹ\uda62\uf512\uebf8ꍧ䎬ﳶ"));

                                                                                            if (var26) {
                                                                                                this.d();
                                                                                                return;
                                                                                            } else {
                                                                                                this.d.a(new k(Application.B("덋浢豓ᕣ\uda7f\uf50a\uebefꌋ䎠ﳾ萉掯\u1c8c匲佝㫨뷏") + this.b));
                                                                                                return;
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
