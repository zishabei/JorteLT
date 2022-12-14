//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.logger;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSBackgroundTaskCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.data.SQLQuery;
import com.transistorsoft.locationmanager.device.DeviceInfo;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager;
import com.transistorsoft.tslocationmanager.Application;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MultipartBody.Builder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

class a implements Runnable {
    private final WeakReference<Context> a;
    private final TSCallback b;
    private int c;
    private final String d;
    private final SQLQuery e;

    a(Context var1, String var2, SQLQuery var3, TSCallback var4) {
        this.a = new WeakReference(var1);
        this.d = var2;
        this.b = var4;
        this.e = var3;
        BackgroundTaskManager var10000 = BackgroundTaskManager.getInstance();
        Context var10001 = var1;
        TSBackgroundTaskCallback var5;
        var5 = new TSBackgroundTaskCallback() {
            public void onStart(int var1) {
                a.this.c = var1;
                BackgroundGeolocation.getThreadPool().execute(a.this);
            }
        };
        var10000.startBackgroundTask(var10001, var5);
    }

    private void a(final String var1) {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            public void run() {
                a.this.b.onFailure(var1);
            }
        });
    }

    private void a() {
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            public void run() {
                a.this.b.onSuccess();
            }
        });
    }

    public void run() {
        final Context var1;
        if ((var1 = (Context)this.a.get()) == null) {
            this.b.onFailure(Application.B("??????\uf146??????????????????????????"));
        } else {
            TSConfig var2 = TSConfig.getInstance(var1);
            BackgroundTaskManager var3 = BackgroundTaskManager.getInstance();
            String var4;
            if ((var4 = TSLogReader.getLog(this.e)) == null) {
                this.b.onFailure(Application.B("??????\uf143??????????????????????????\uf2f9????????????"));
                var3.stopBackgroundTask(var1, this.c);
            } else {
                File var14;
                if ((var14 = TSLog.writeLogFile(var1, var4)) == null) {
                    this.b.onFailure(Application.B("??????\uf143??????????????????????????\uf2e8???????????????\ue372?????????\udc8f???????????????????????????????????????\ud932"));
                    var3.stopBackgroundTask(var1, this.c);
                } else {
                    TSConfig var10000 = var2;
                    DeviceInfo var12 = DeviceInfo.getInstance(var1);
                    Builder var10001 = (new Builder()).setType(MultipartBody.FORM);
                    String var10002 = var14.getName();
                    RequestBody var15 = RequestBody.create(MediaType.parse(Application.B("??????\uf15a??????????????????????????\uf2ea?????????")), var14);
                    okhttp3.Request.Builder var11 = (new okhttp3.Request.Builder()).url(this.d).post(var10001.addFormDataPart(Application.B("???\u1a5f\uf146??????"), var10002, var15).addFormDataPart(Application.B("??????\uf14b??????"), var2.toJson().toString()).addFormDataPart(Application.B("??????\uf158???????????"), var12.getVersion()).addFormDataPart(Application.B("??????\uf144??????????????????????????"), var12.getManufacturer()).addFormDataPart(Application.B("??????\uf14e??????"), var12.getModel()).addFormDataPart(Application.B("??????\uf14b??????????????"), var12.getPlatform()).build());
                    JSONObject var13;
                    if ((var13 = var10000.getHeaders()) != null) {
                        Iterator var16 = var13.keys();

                        label57:
                        while(true) {
                            JSONException var17;
                            while(true) {
                                boolean var18;
                                boolean var19;
                                var18 = var16.hasNext();

                                if (!var18) {
                                    break label57;
                                }

                                String var20;
                                var20 = (String)var16.next();

                                String var5 = var20;

                                var18 = var20.equalsIgnoreCase(Application.B("??????\uf144??????????????????????????"));

                                if (!var18) {
                                    try {
                                        var11.header(var5, var13.getString(var5));
                                    } catch (JSONException var6) {
                                        var17 = var6;
                                        var19 = false;
                                        break;
                                    }
                                }
                            }

                            JSONException var10 = var17;
                            this.b.onFailure(var10.getMessage());
                            return;
                        }
                    }

                    HttpService.getInstance(var1).getClient().newCall(var11.build()).enqueue(new Callback() {
                        public void onFailure(@NotNull Call var1x, @NotNull IOException var2) {
                            a.this.a(var2.getMessage());
                            BackgroundTaskManager.getInstance().stopBackgroundTask(var1, a.this.c);
                        }

                        public void onResponse(@NotNull Call var1x, @NotNull Response var2) {
                            if (var2.isSuccessful()) {
                                a.this.a();
                            } else {
                                a.this.a(var2.message());
                            }

                            BackgroundTaskManager.getInstance().stopBackgroundTask(var1, a.this.c);
                        }
                    });
                }
            }
        }
    }
}
