//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthorizationEvent {
    private JSONObject mResponse;
    private String mError;

    public AuthorizationEvent(String var1) {
        this.mError = var1;
    }

    public AuthorizationEvent(JSONObject var1) {
        this.mResponse = var1;
    }

    public boolean isSuccessful() {
        return this.mError == null && this.mResponse != null;
    }

    public JSONObject getResponse() {
        return this.mResponse;
    }

    public String getError() {
        return this.mError;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10001 = var1 = new HashMap;
        var1.<init>();
        String var10005 = this.mError;
        var1.put(Application.B("ץ\ue2ca嘾䞍ꥨ"), var10005);
        var10001.put(Application.B("׳\ue2cd嘯䞁\ua97fﵝ\uf009"), this.isSuccessful());
        var10001.put(Application.B("ײ\ue2dd嘿䞒ꥵ\ufd40\uf009ｔ"), (Object)null);
        JSONObject var3;
        if ((var3 = this.mResponse) != null) {
            HashMap var10000 = var1;
            JSONObject var5 = var3;
            String var4 = Application.B("ײ\ue2dd嘿䞒ꥵ\ufd40\uf009ｔ");

            try {
                var10000.put(var4, Util.toMap(var5));
            } catch (JSONException var2) {
                TSLog.logger.error(TSLog.error(var2.getMessage()), var2);
            }
        }

        return var1;
    }

    public JSONObject toJson() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject;
        AuthorizationEvent var10001 = this;
        JSONObject var10002 = var1;
        AuthorizationEvent var10003 = this;
        JSONObject var10004 = var1;
        AuthorizationEvent var10005 = this;
        var1.<init>();
        String var5 = Application.B("\udc47鞐즂䴝ᑐꏈ냙");

        JSONException var7;
        label37: {
            boolean var8;
            try {
                var10004.put(var5, var10005.isSuccessful());
            } catch (JSONException var4) {
                var7 = var4;
                var8 = false;
                break label37;
            }

            var5 = Application.B("\udc51鞗즓䴑ᑇ");

            try {
                var10002.put(var5, var10003.mError);
            } catch (JSONException var3) {
                var7 = var3;
                var8 = false;
                break label37;
            }

            var5 = Application.B("\udc46鞀즒䴎ᑚꏕ냙蕴");

            try {
                var10000.put(var5, var10001.mResponse);
                return var1;
            } catch (JSONException var2) {
                var7 = var2;
                var8 = false;
            }
        }

        JSONException var6 = var7;
        TSLog.logger.error(TSLog.error(var6.getMessage()), var6);
        return var1;
    }
}
