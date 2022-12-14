//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.http;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.ConnectivityManager.NetworkCallback;
import android.os.Bundle;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSAuthorizationCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSBackgroundTaskCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSSyncCallback;
import com.transistorsoft.locationmanager.c.a;
import com.transistorsoft.locationmanager.config.TSAuthorization;
import com.transistorsoft.locationmanager.config.TransistorAuthorizationToken;
import com.transistorsoft.locationmanager.data.LocationModel;
import com.transistorsoft.locationmanager.data.sqlite.b;
import com.transistorsoft.locationmanager.event.AuthorizationEvent;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.event.ConnectivityChangeEvent;
import com.transistorsoft.locationmanager.event.HeadlessEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.provider.TSProviderManager;
import com.transistorsoft.locationmanager.scheduler.TSScheduleManager;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient.Builder;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpService {
    public static final MediaType JSON = MediaType.parse(Application.B("???????????????\ud830?????????????????????????????????????????\ueae6\uecdb\ue390???\uf65b???\ua634????????????"));
    private static HttpService k = null;
    private final Context a;
    private final List<LocationModel> b = new ArrayList();
    private final AtomicBoolean c = new AtomicBoolean(false);
    private TSSyncCallback d;
    private final AtomicInteger e = new AtomicInteger(0);
    private final AtomicInteger f = new AtomicInteger(0);
    private final OkHttpClient g;
    private BroadcastReceiver h;
    private NetworkCallback i;
    private final List<TSAuthorizationCallback> j = new ArrayList();

    public static HttpService getInstance(Context var0) {
        if (k == null) {
            k = a(var0.getApplicationContext());
        }

        return k;
    }

    private static synchronized HttpService a(Context var0) {
        if (k == null) {
            k = new HttpService(var0.getApplicationContext());
        }

        return k;
    }

    private HttpService(Context var1) {
        TSConfig var3 = TSConfig.getInstance(this.a = var1.getApplicationContext());
        this.g = (new Builder()).followRedirects(false).connectTimeout((long)var3.getHttpTimeout(), TimeUnit.MILLISECONDS).build();
        EventBus var2;
        if (!(var2 = EventBus.getDefault()).isRegistered(this)) {
            var2.register(this);
        }

        this.f.set(var3.getAutoSyncThreshold());
        if (var3.getEnabled()) {
            this.startMonitoringConnectivityChanges(this.a);
        }

        LifecycleManager.f().a((LifecycleManager.b) (var2x) -> {
            if (var2x && var3.getEnabled() && var3.getAutoSync() && var3.hasUrl() && !var3.getIsMoving()) {
                BackgroundGeolocation.getThreadPool().execute(() -> {
                    this.flush(true);
                });
            }

        });
    }

    private void f() {
        LocationModel var1;
        if ((var1 = com.transistorsoft.locationmanager.data.sqlite.b.a(this.a).first()) != null) {
            this.a(TSConfig.getInstance(this.a).getUrl(), var1);
        } else {
            this.a();
        }

    }

    private void e() {
        TSConfig var1 = TSConfig.getInstance(this.a);
        List var2;
        if ((var2 = com.transistorsoft.locationmanager.data.sqlite.b.a(this.a).allWithLocking(var1.getMaxBatchSize())).size() > 0) {
            this.a(var1.getUrl(), var2);
        } else {
            this.a();
        }

    }

    private void a(String var1, LocationModel var2) {
        TSConfig var3;
        TSConfig var10000 = var3 = TSConfig.getInstance(this.a);
        LocationModel var10001 = var2;
        TSLog.logger.info(TSLog.notice(Application.B("???????????????") + var3.getMethod().toUpperCase() + Application.B("??????") + var2.getUUID()));

        Object var30;
        label118: {
            JSONException var33;
            label117: {
                IllegalArgumentException var32;
                label123: {
                    boolean var34;
                    Object var36;
                    try {
                        var36 = var10001.getJson();
                    } catch (IllegalArgumentException var28) {
                        var32 = var28;
                        var34 = false;
                        break label123;
                    }

                    Object var4 = var36;

                    String var35;
                    try {
                        var35 = var10000.getHttpRootProperty();
                    } catch (IllegalArgumentException var26) {
                        var32 = var26;
                        var34 = false;
                        break label123;
                    }

                    String var5 = var35;

                    JSONObject var38;
                    try {
                        var38 = var3.getParams();
                    } catch (IllegalArgumentException var24) {
                        var32 = var24;
                        var34 = false;
                        break label123;
                    }

                    JSONObject var31 = var38;

                    boolean var37;
                    try {
                        var37 = var35.equalsIgnoreCase(Application.B("???"));
                    } catch (IllegalArgumentException var22) {
                        var32 = var22;
                        var34 = false;
                        break label123;
                    }

                    Object var6;
                    JSONObject var39;
                    if (var37) {
                        try {
                            var37 = var4 instanceof JSONObject;
                        } catch (IllegalArgumentException var20) {
                            var32 = var20;
                            var34 = false;
                            break label123;
                        }

                        if (var37) {
                            if (var31 != null) {
                                try {
                                    var39 = Util.mergeJson((JSONObject)var4, var31);
                                } catch (IllegalArgumentException var18) {
                                    var32 = var18;
                                    var34 = false;
                                    break label123;
                                }

                                var4 = var39;
                            }
                        } else if (var31 != null) {
                            try {
                                TSLog.logger.warn(Application.B("??????????????????\ue802????????????\uedd9????????????\u139a\uec65\uf85b????????????????????????????????????????????????????????\uddc7???????????\ua7bd?????????????????????????????????????????\ud8bc???????????????\ue473?????????????????????????????????????????????\u0ee9???\uda61?????????\ue0a9????????????\ue197???????????????????????????\ue3eb?????????????????????????????????\uf6a3????????????\ue4ad?????????????????????\uec6f?????????\uf0e3\u0fef???"));
                            } catch (IllegalArgumentException var16) {
                                var32 = var16;
                                var34 = false;
                                break label123;
                            }
                        }

                        var6 = var4;
                    } else {
                        try {
                            var39 = var31;
                            var38 = new JSONObject();
                        } catch (IllegalArgumentException var14) {
                            var32 = var14;
                            var34 = false;
                            break label123;
                        }

                        var6 = var38;

                        try {
                            var38.put(var5, var4);
                        } catch (JSONException var11) {
                            var33 = var11;
                            var34 = false;
                            break label117;
                        } catch (IllegalArgumentException var12) {
                            var32 = var12;
                            var34 = false;
                            break label123;
                        }

                        if (var39 != null) {
                            try {
                                var39 = Util.mergeJson((JSONObject)var6, var31);
                            } catch (IllegalArgumentException var10) {
                                var32 = var10;
                                var34 = false;
                                break label123;
                            }

                            var6 = var39;
                        }
                    }

                    try {
                        this.g.newCall(this.a(var1, var6.toString())).enqueue(new HttpService.h(var2));
                        return;
                    } catch (IllegalArgumentException var8) {
                        var32 = var8;
                        var34 = false;
                    }
                }

                var30 = var32;
                break label118;
            }

            var30 = var33;
        }

        Object var41 = var30;
        HttpService var40 = this;
        TSLog.logger.error(TSLog.error(((Exception)var30).getMessage()));
        Context var29 = this.a;
        var1 = ((Exception)var30).getMessage();
        var40.a(new HttpResponse(var29, 0, var1), var2);
        ((Exception)var41).printStackTrace();
    }

    private void a(String var1, List<LocationModel> var2) {
        TSConfig var3 = TSConfig.getInstance(this.a);
        TSLog.logger.info(TSLog.notice(Application.B("???????????????") + var3.getMethod().toUpperCase() + Application.B("???????????????\u2efc\ue84b???") + var2.size() + Application.B("???")));
        JSONArray var4;
        var4 = new JSONArray();
        Iterator var5 = var2.iterator();

        while(var5.hasNext()) {
            var4.put(((LocationModel)var5.next()).getJson());
        }

        Object var24;
        label91: {
            JSONException var27;
            label90: {
                IllegalArgumentException var10000;
                label101: {
                    boolean var10001;
                    TSConfig var28;
                    JSONObject var29;
                    try {
                        var28 = var3;
                        var29 = var3.getParams();
                    } catch (IllegalArgumentException var22) {
                        var10000 = var22;
                        var10001 = false;
                        break label101;
                    }

                    JSONObject var25 = var29;

                    String var30;
                    try {
                        var30 = var28.getHttpRootProperty();
                    } catch (IllegalArgumentException var20) {
                        var10000 = var20;
                        var10001 = false;
                        break label101;
                    }

                    String var26 = var30;

                    boolean var31;
                    try {
                        var31 = var30.equalsIgnoreCase(Application.B("???"));
                    } catch (IllegalArgumentException var18) {
                        var10000 = var18;
                        var10001 = false;
                        break label101;
                    }

                    Object var6;
                    if (var31) {
                        if (var25 != null) {
                            try {
                                TSLog.logger.warn(Application.B("??????????????????\ue802????????????\uedd9????????????\u139a\uec65\uf85b????????????????????????????????????????????????????????\uddc7???????????\ua7bd?????????????????????????????????????????\ud8bc???????????????\ue473?????????????????????????????????????????????\u0ee9???\uda61?????????\ue0a9????????????\ue197???????????????????????????\ue3eb?????????????????????????????????\uf6a3????????????\ue4ad?????????????????????\uec6f?????????\uf0e3\u0fef???"));
                            } catch (IllegalArgumentException var16) {
                                var10000 = var16;
                                var10001 = false;
                                break label101;
                            }
                        }

                        var6 = var4;
                    } else {
                        JSONObject var32;
                        try {
                            var32 = var25;
                            var29 = new JSONObject();
                        } catch (IllegalArgumentException var14) {
                            var10000 = var14;
                            var10001 = false;
                            break label101;
                        }

                        var6 = var29;

                        try {
                            var29.put(var26, var4);
                        } catch (JSONException var11) {
                            var27 = var11;
                            var10001 = false;
                            break label90;
                        } catch (IllegalArgumentException var12) {
                            var10000 = var12;
                            var10001 = false;
                            break label101;
                        }

                        if (var32 != null) {
                            try {
                                var32 = Util.mergeJson((JSONObject)var6, var25);
                            } catch (IllegalArgumentException var10) {
                                var10000 = var10;
                                var10001 = false;
                                break label101;
                            }

                            var6 = var32;
                        }
                    }

                    try {
                        this.g.newCall(this.a(var1, var6.toString())).enqueue(new HttpService.h(var2));
                        return;
                    } catch (IllegalArgumentException var8) {
                        var10000 = var8;
                        var10001 = false;
                    }
                }

                var24 = var10000;
                break label91;
            }

            var24 = var27;
        }

        Object var34 = var24;
        HttpService var33 = this;
        TSLog.logger.error(TSLog.error(((Exception)var24).getMessage()));
        Context var23 = this.a;
        var1 = ((Exception)var24).getMessage();
        var33.a(new HttpResponse(var23, 0, var1), var2);
        ((Exception)var34).printStackTrace();
    }

    private Request a(String var1, String var2) {
        TSConfig var7;
        TSConfig var10000 = var7 = TSConfig.getInstance(this.a);
        okhttp3.Request.Builder var9 = (new okhttp3.Request.Builder()).url(var1);
        MediaType var3 = JSON;
        JSONObject var4;
        if ((var4 = var10000.getHeaders()) != null) {
            Iterator var5 = var4.keys();

            while(var5.hasNext()) {
                String var6;
                if ((var6 = (String)var5.next()).equalsIgnoreCase(Application.B("???????????????\u2efa\ue81f????????????\uedcc"))) {
                    try {
                        var3 = MediaType.parse(var4.getString(var6));
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }

                if (var6 != null) {
                    try {
                        var9.header(var6, var4.getString(var6));
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        var7.getAuthorization().apply(var9);
        String var8 = var7.getMethod();
        var9.method(var8, RequestBody.create(var3, var2));
        return var9.build();
    }

    private void a(HttpResponse var1, List<LocationModel> var2) {
        EventBus.getDefault().post(var1);
        b var3 = com.transistorsoft.locationmanager.data.sqlite.b.a(this.a);
        if (var1.isSuccess()) {
            List var4;
            List var10003 = var4 = this.b;
            HttpService var10004 = this;
            synchronized(var4){}

            Throwable var10000;
            boolean var10001;
                var10004.b.addAll(var2);


            var3.destroyAll(var2);
            this.e();
        } else {
            var3.unlock(var2);
            this.a(var1);
        }

        this.b(var1);
    }

    private void a(HttpResponse var1, LocationModel var2) {
        EventBus.getDefault().post(var1);
        b var3 = com.transistorsoft.locationmanager.data.sqlite.b.a(this.a);
        if (var1.isSuccess()) {
            List var4;
            List var10003 = var4 = this.b;
            HttpService var10004 = this;
            synchronized(var4){}

            Throwable var10000;
            boolean var10001;
                var10004.b.add(var2);



            var3.destroy(var2);
            this.f();
        } else {
            var3.unlock(var2);
            this.a(var1);
        }

        this.b(var1);
    }

    private void b(HttpResponse var1) {
        String var2;
        if ((var2 = var1.responseText) != null && !var2.isEmpty()) {
            JSONObject var10000;
            boolean var10001;
            var10000 = new JSONObject();

            JSONObject var6;
            JSONObject var8 = var6 = var10000;

            boolean var7;
            var7 = var10000.has(Application.B("??????\uf1e0????????????????????????\uf1a9\uf877??????????????????????????"));

            if (var7) {
                try {
                    com.transistorsoft.locationmanager.c.a.a(this.a, var6.getJSONArray(Application.B("??????\uf1e0????????????????????????\uf1a9\uf877??????????????????????????")));
                } catch (JSONException var3) {
                    var10001 = false;
                }
            }

        }
    }

    private void a() {
        this.b.clear();
        this.g();
        if (this.d != null) {
            final ArrayList var1;
            var1 = new ArrayList(this.b);
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    HttpService.this.d.onSuccess(var1);
                    HttpService.this.c.set(false);
                    HttpService.this.d = null;
                }
            });
        } else {
            this.c.set(false);
        }

    }

    private void a(final HttpResponse var1) {
        this.g();
        TSConfig var2;
        if (var1.getStatus() == 0 && (var2 = TSConfig.getInstance(this.a)).getEnabled() && var2.getAutoSync()) {
            TSScheduleManager var10000 = TSScheduleManager.getInstance(this.a);
            long var4 = TimeUnit.MINUTES.toMillis(15L);
            var10000.oneShot(Application.B("??????????????????\ue807?????????"), var4);
        }

        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            public void run() {
                if (HttpService.this.d != null) {
                    try {
                        HttpService.this.d.onFailure(var1.responseText);
                    } catch (Exception var2) {
                        TSLog.logger.warn(TSLog.warn(Application.B("????????????????????\ued23?????????????????????????????????????????????????????\ud808???????????????\uf7ce???\ue8bf????????\ue21e???\ueca6?????????????????????????????????\ue09f???\ue0d4??????????????????????????\ue9d5???????????\uf889??????????????????\ue978\uf5fb?????????\uf49a????????\uf54f??????\u0a63??????\f???\ude58")), var2);
                    }

                    HttpService.this.c.set(false);
                    HttpService.this.d = null;
                } else {
                    HttpService.this.c.set(false);
                }

            }
        });
    }

    private void g() {
        if (this.e.get() > 0) {
            BackgroundTaskManager.getInstance().stopBackgroundTask(this.a, this.e.get());
            this.e.set(0);
        }

    }

    private boolean a(Response var1) {
        int var2;
        return (var2 = var1.code()) == 301 || var2 == 302 || var2 == 307 || var2 == 308;
    }

    private boolean b(Response var1) {
        int var2;
        return (var2 = var1.code()) == 307 || var2 == 308;
    }

    private String c(Response var1) {
        HttpUrl var3;
        HttpUrl var10000 = var3 = var1.request().url();
        String var2 = var3.scheme() + Application.B("???\uf6dc???") + var3.host();
        if (var10000.port() != 80) {
            var2 = var2 + Application.B("???") + var3.port();
        }

        return var2 + var1.header(Application.B("???\uf69c??????\ueb6e?????????"));
    }

    private void a(final AuthorizationEvent var1) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            public void run() {
                if (LifecycleManager.f().b()) {
                    Context var33 = HttpService.this.a;
                    AuthorizationEvent var1x = var1;
                    com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var33, Application.B("????????????\udf4b\ue5c5??????\ue9b3???\uddb0??????"), var1x));
                } else {
                    synchronized(HttpService.this.j){
                        Iterator var35 = HttpService.this.j.iterator();
                        Iterator var2 = var35;

                        boolean var36 = var35.hasNext();
                        if (var36) {
                            while(true) {
                                var36 = var2.hasNext();
                                if (!var36) {
                                    break;
                                }
                                ((TSAuthorizationCallback)var2.next()).onResponse(var1);
                            }
                        }
                    }
                }
            }
        });
    }

    private void a(boolean var1) {
        TSProviderManager.getInstance(this.a).onConnectivityChange(this.a, var1);
        if (var1 == (var1 = this.isNetworkAvailable())) {
            TSLog.logger.debug(TSLog.header(Application.B("???????????????\u2efb\ue805????????????\uedc0???????????????\uec26\uf847??????????????????????????????????????????????????") + var1));
            TSConfig var2 = TSConfig.getInstance(this.a);
            this.f.set(var2.getAutoSyncThreshold());
            if (var1 && var2.getAutoSync() && var2.hasUrl()) {
                BackgroundGeolocation.getUiHandler().postDelayed(() -> {
                    BackgroundGeolocation.getThreadPool().execute(() -> {
                        this.flush();
                    });
                }, 1000L);
            }

            ConnectivityChangeEvent var3;
            var3 = new ConnectivityChangeEvent(var1);
            EventBus.getDefault().post(var3);
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onConfigChange(ConfigChangeEvent var1) {
        TSConfig var2 = TSConfig.getInstance(this.a);
        if ((var1.isDirty(Application.B("??????\uf146???????????????")) || var1.isDirty(Application.B("??????\uf15e")) || var1.isDirty(Application.B("??????\uf140?????????")) || var1.isDirty(Application.B("??????\uf146?????????")) || var1.isDirty(Application.B("??????\uf153????????????"))) && var2.hasUrl() && var2.getAutoSync() && this.isNetworkAvailable()) {
            this.flush();
        }

        if (var1.isDirty(Application.B("??????\uf146???????????????\uf756\ud927?????????\ua87a?????????"))) {
            this.f.set(var2.getAutoSyncThreshold());
        }

    }

    public OkHttpClient getClient() {
        return this.g;
    }

    public void flush(@Nullable final TSSyncCallback var1) {
        if (!this.isNetworkAvailable()) {
            if (var1 != null) {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        var1.onFailure(Application.B("?????????????????????\ua87c??????????????????????????????\ueeea???????????????\udaba???"));
                    }
                });
            }

        } else if (this.c.get()) {
            TSLog.logger.info(TSLog.info(Application.B("??????????????????\ue308????????????????????????????????????")));
            if (var1 != null) {
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        var1.onFailure(Application.B("???????????????????????????\ud97c??????\ue8c4????????????"));
                    }
                });
            }

        } else {
            this.d = var1;
            this.flush(true);
        }
    }

    public void flush(boolean var1) {
        if (var1) {
            this.f.set(0);
        }

        this.flush();
    }

    public void flush() {
        if (this.isNetworkAvailable()) {
            TSConfig var1 = TSConfig.getInstance(this.a);
            if (this.d == null && var1.getDisableAutoSyncOnCellular()) {
                boolean var2;
                boolean var10000 = var2 = this.isConnectedWifi();
                TSLog.logger.info(TSLog.info(Application.B("??????????????????\ue315???????????????????????????") + var2));
                if (!var10000) {
                    return;
                }
            }

            if (this.c.compareAndSet(false, true)) {
                int var3;
                int var4 = var3 = com.transistorsoft.locationmanager.data.sqlite.b.a(this.a).count();
                TSLog.logger.info(TSLog.header(Application.B("??????????????????\ue31f??????????????????????????????????????????") + var3 + Application.B("???")));
                if (var4 < 1 || var3 < this.f.get()) {
                    this.a();
                    return;
                }

                new HttpService.g();
            } else {
                TSLog.logger.info(TSLog.info(Application.B("??????????????????\ue308????????????????????????????????????")));
            }

        }
    }

    public boolean isBusy() {
        return this.c.get();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager var1;
        if ((var1 = (ConnectivityManager)this.a.getSystemService(Application.B("???????????????????????????\udc94??????"))) == null) {
            return false;
        } else {
            NetworkInfo var2;
            return (var2 = var1.getActiveNetworkInfo()) != null && var2.isConnected();
        }
    }

    public boolean isConnectedMobile() {
        ConnectivityManager var1;
        if ((var1 = (ConnectivityManager)this.a.getSystemService(Application.B("???\uf14c??????????????????\ue635????????"))) == null) {
            return false;
        } else if (VERSION.SDK_INT >= 23) {
            NetworkCapabilities var3;
            return (var3 = var1.getNetworkCapabilities(var1.getActiveNetwork())) != null && var3.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
        } else {
            NetworkInfo var2;
            return (var2 = var1.getActiveNetworkInfo()) != null && var2.isConnected() && var2.getType() == 0;
        }
    }

    public boolean isConnectedWifi() {
        ConnectivityManager var1;
        if ((var1 = (ConnectivityManager)this.a.getSystemService(Application.B("???\ue0f7???\ued6b\uf112??????\u0f6d??????\ud87f\ue9c7"))) == null) {
            return false;
        } else if (VERSION.SDK_INT >= 23) {
            NetworkCapabilities var3;
            return (var3 = var1.getNetworkCapabilities(var1.getActiveNetwork())) != null && var3.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            NetworkInfo var2;
            return (var2 = var1.getActiveNetworkInfo()) != null && var2.getType() == 1;
        }
    }

    public void startMonitoringConnectivityChanges(Context var1) {
        if (!this.isNetworkAvailable()) {
            EventBus.getDefault().post(new ConnectivityChangeEvent(false));
        }

        TSLog.logger.debug(TSLog.on(Application.B("???????????????????????????????????\udaff??????\ue60c\udd2c????????????\ueddc??????\ue4bb???\ue178???\uf84a???????????????????????")));
        if (this.i == null && this.h == null) {
            if (VERSION.SDK_INT >= 23) {
                ConnectivityManager var10000 = (ConnectivityManager)var1.getSystemService(Application.B("???????????????????????????????????"));
                var10000.isDefaultNetworkActive();
                NetworkRequest var4 = (new android.net.NetworkRequest.Builder()).build();
                HttpService.i var2;
                HttpService.i var10002 = var2 = new HttpService.i();
                this.i = var10002;
                var10000.registerNetworkCallback(var4, var2);
            } else {
                HttpService var10001 = this;
                this.h = new HttpService.f();
                IntentFilter var3;
                IntentFilter var5 = var3 = new IntentFilter();
                var5.addAction(Application.B("???????????????????????????????????\udaee??????\ue605\udd22????????????\uedfc??????\ue49b???\ue158???\uf86a???????????\u0cd2??????"));
                var1.registerReceiver(var10001.h, var3);
            }

        }
    }

    public void removeListeners() {
        List var1;
        List var10000 = var1 = this.j;
        HttpService var10001 = this;
        synchronized(var1) {
            var10001.j.clear();
        }
    }

    public Object removeListener(String var1, Object var2) {
        if (Application.B("????????????\uef68???\uf391???????????????\ue0aa").equalsIgnoreCase(var1)) {
            HttpService var10000 = this;
            synchronized(this.j){}

            boolean var16;
            boolean var10001;
            Throwable var15;
                var16 = var10000.j.contains((TSAuthorizationCallback)var2);


            if (var16) {
                    return this.j.remove(var2);

            } else {
                    return null;

            }
        } else {
            return null;
        }
    }

    public void onAuthorization(TSAuthorizationCallback var1) {
        synchronized(this.j){
            j.add(var1);
        }
    }

    public void stopMonitoringConnectivityChanges(Context var1) {
        TSLog.logger.debug(TSLog.off(Application.B("\ueec0??????????????????????????????????\udfeb????????????\uebb5????????\uf2c3\ufd4f??????\ue6cb????????????\uf744???\ueff5??????")));
        if (VERSION.SDK_INT >= 23) {
            ConnectivityManager var3 = (ConnectivityManager)var1.getSystemService(Application.B("\ueef0?????\u18fe???????????????????????"));
            NetworkCallback var2;
            if ((var2 = this.i) != null) {
                var3.unregisterNetworkCallback(var2);
                this.i = null;
            }
        } else {
            BroadcastReceiver var4;
            if ((var4 = this.h) != null) {
                var1.unregisterReceiver(var4);
                this.h = null;
            }
        }

    }

    public void destroy() {
        EventBus var1;
        if ((var1 = EventBus.getDefault()).isRegistered(this)) {
            var1.unregister(this);
        }

    }

    @TargetApi(21)
    class i extends NetworkCallback {
        i() {
        }

        public void onAvailable(Network var1) {
            super.onAvailable(var1);
            HttpService.this.a(true);
        }

        public void onLost(Network var1) {
            super.onLost(var1);
            HttpService.this.a(false);
        }
    }

    class f extends BroadcastReceiver {
        f() {
        }

        public void onReceive(Context var1, Intent var2) {
            Bundle var3;
            boolean var4;
            if ((var3 = var2.getExtras()) != null && !var3.containsKey(Application.B("????????????\uf6ae???????????????????????????"))) {
                var4 = true;
            } else {
                var4 = false;
            }

            HttpService.this.a(var4);
        }
    }

    private class h implements Callback {
        private LocationModel a;
        private List<LocationModel> b;
        private boolean c = false;

        public h(LocationModel var2) {
            this.a = var2;
        }

        public h(List<LocationModel> var2) {
            this.b = var2;
            this.c = true;
        }

        public void onResponse(@NonNull Call var1, @NonNull Response var2) {
            HttpResponse var7;
            if (!HttpService.this.a(var2)) {
                Response var10000 = var2;
                int var6 = var2.code();

                String var3;
                label46: {
                    String var13;
                    try {
                        var13 = var10000.body().string();
                    } catch (IOException var5) {
                        var3 = var2.message();
                        break label46;
                    }

                    var3 = var13;
                }

                if (var2.isSuccessful()) {
                    HttpService.this.f.set(TSConfig.getInstance(HttpService.this.a).getAutoSyncThreshold());
                    TSLog.logger.info(TSLog.notice(Application.B("??????????????????????????????") + var6));
                } else {
                    if (var6 == 401) {
                        final TSAuthorization var4;
                        if ((var4 = TSConfig.getInstance(HttpService.this.a).getAuthorization()).canRefreshAuthorizationToken()) {
                            BackgroundTaskManager.getInstance().startBackgroundTask(HttpService.this.a, new TSBackgroundTaskCallback() {
                                public void onStart(final int var1) {
                                    var4.refreshAuthorizationToken(HttpService.this.a, new com.transistorsoft.locationmanager.config.TSAuthorization.Callback() {
                                        public void invoke(AuthorizationEvent var1x) {
                                            HttpService.this.a(var1x);
                                            if (var1x.isSuccessful()) {
                                                HttpService.this.flush(true);
                                            }

                                            BackgroundTaskManager var10000 = BackgroundTaskManager.getInstance();
                                            Context var2 = HttpService.this.a;
                                            var10000.stopBackgroundTask(var2, var1);
                                        }
                                    });
                                }
                            });
                        }
                    } else if (var6 == 410) {
                        HttpUrl var11 = var2.request().url();
                        if (TransistorAuthorizationToken.hasTokenForHost(HttpService.this.a, var11.host())) {
                            TransistorAuthorizationToken.destroyTokenForUrl(HttpService.this.a, var11.toString(), (TSCallback)null);
                        }
                    }

                    TSLog.logger.warn(TSLog.warn(Application.B("??????????????????????????????") + var6 + Application.B("??????") + var2.message()));
                }

                HttpResponse var8;
                var8 = new HttpResponse(HttpService.this.a, var6, var3);
                var7 = var8;
            } else {
                if (HttpService.this.b(var2)) {
                    String var9 = HttpService.this.c(var2);
                    TSLog.logger.info(TSLog.info(Application.B("????????????\u0cff??????????????????????????????") + var2.code() + Application.B("???") + var9));
                    if (this.c) {
                        HttpService.this.a(var9, this.b);
                    } else {
                        HttpService.this.a(var9, this.a);
                    }

                    return;
                }

                TSLog.logger.error(TSLog.warn(Application.B("??????????????????????????????") + var2.code() + Application.B("????????????????????????????????????????????????\ue2c6???\uf05c??????????????????\udd06????????????????????????????????????????????????????????????\ue8cf\udbae???\u2e6d???????????????????????????????????\ufb07????????????\ue730???\ud7ab")));
                Context var10 = HttpService.this.a;
                int var12 = var2.code();
                HttpResponse var14 = var7 = new HttpResponse(var10, var12, var2.code() + Application.B("?????????\u202e????????????????????????????????????\ue283???\uf050??????????????????\udd03"));
            }

            if (this.c) {
                HttpService.this.a(var7, this.b);
            } else {
                HttpService.this.a(var7, this.a);
            }

        }

        public void onFailure(Call var1, IOException var2) {
            byte var3 = 0;
            TSLog.logger.warn(TSLog.warn(Application.B("?????????\uf56c?????????????????") + var3 + Application.B("??????") + var2.getMessage()));
            TSConfig var4;
            if ((var4 = TSConfig.getInstance(HttpService.this.a)).getIsMoving() && HttpService.this.isConnectedMobile() && var4.getMaxRecordsToPersist() < 0) {
                int var5 = com.transistorsoft.locationmanager.data.sqlite.b.a(HttpService.this.a).count();
                int var8;
                if (var4.getAutoSyncThreshold() > 0) {
                    var8 = var4.getAutoSyncThreshold();
                } else {
                    var8 = 10;
                }

                HttpService.this.f.set(var5 + var8);
                TSLog.logger.warn(TSLog.warn(var1.request().url().host() + Application.B("?????????\uf53c??????????????????????????\ue7ab???????????\udaee?????????\ue2d7?????\uefde????????????\uf0f7??????????????????\ue69a????????????") + var8 + Application.B("?????????\uf57f??????????????")));
            } else {
                HttpService.this.f.set(var4.getAutoSyncThreshold());
            }

            String var6 = var2.getMessage();
            HttpResponse var7;
            var7 = new HttpResponse(HttpService.this.a, var3, var6);
            if (this.c) {
                HttpService.this.a(var7, this.b);
            } else {
                HttpService.this.a(var7, this.a);
            }

        }
    }

    private class g implements Runnable {
        g() {
            BackgroundTaskManager var10000 = BackgroundTaskManager.getInstance();
            Context var10001 = HttpService.this.a;
            TSBackgroundTaskCallback var2;
            var2 = new TSBackgroundTaskCallback() {
                public void onStart(int var1) {
                    HttpService.this.e.set(var1);
                    BackgroundGeolocation.getThreadPool().execute(g.this);
                }
            };
            var10000.startBackgroundTask(var10001, true, var2);
        }

        public void run() {
            if (TSConfig.getInstance(HttpService.this.a).getBatchSync()) {
                HttpService.this.e();
            } else {
                HttpService.this.f();
            }

        }
    }
}
