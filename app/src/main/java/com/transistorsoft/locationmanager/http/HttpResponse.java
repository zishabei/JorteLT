//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.http;

import android.content.Context;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpResponse {
    private final Context a;
    public int status;
    public String responseText = "";

    HttpResponse(Context var1, int var2, String var3) {
        this.a = var1;
        this.status = var2;
        if (var3 != null) {
            this.responseText = var3;
        }

    }

    public Context getContext() {
        return this.a;
    }

    public int getStatus() {
        return this.status;
    }

    public String getResponseText() {
        return this.responseText;
    }

    public Boolean isSuccess() {
        int var1;
        return (var1 = this.status) == 200 || var1 == 201 || var1 == 204;
    }

    public JSONObject toJson() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject();
        HttpResponse var10001 = this;
        JSONObject var10002 = var1;
        HttpResponse var10003 = this;
        JSONObject var10004 = var1;
        HttpResponse var10005 = this;
        String var5 = Application.B("\u1afcꠌ鶈踄\ue594ꄣ");

        JSONException var7;
        label37: {
            boolean var8;
            try {
                var10004.put(var5, var10005.status);
            } catch (JSONException var4) {
                var7 = var4;
                var8 = false;
                break label37;
            }

            var5 = Application.B("\u1afdꠝ鶚踀\ue58eꄾ\ue058\ue3ee빅⽢ᑈ蓴");

            try {
                var10002.put(var5, var10003.responseText);
            } catch (JSONException var3) {
                var7 = var3;
                var8 = false;
                break label37;
            }

            var5 = Application.B("\u1afcꠍ鶊踓\ue584ꄣ\ue058");

            try {
                var10000.put(var5, var10001.isSuccess());
                return var1;
            } catch (JSONException var2) {
                var7 = var2;
                var8 = false;
            }
        }

        JSONException var6 = var7;
        TSLog.logger.error(TSLog.error(var6.getMessage()));
        var6.printStackTrace();
        return var1;
    }
}
