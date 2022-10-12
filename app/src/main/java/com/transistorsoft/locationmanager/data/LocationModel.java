//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.data;

import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationModel {
    public Integer id;
    public Object json;
    private final String a;
    private final String b;
    public boolean destroyed;
    private final LocationDAO c;

    public LocationModel(LocationDAO var1, Integer var2, String var3, String var4, String var5) {
        super();
        String var10000 = var5;
        this.json = null;
        this.destroyed = false;
        this.c = var1;
        this.id = var2;
        this.a = var3;
        this.b = var4;

        JSONException var12;
        label33: {
            char var13;
            boolean var10001;
            var13 = var10000.charAt(0);

            char var11 = var13;
            if (var13 == '{') {
                try {
                    this.json = new JSONObject(var5);
                    return;
                } catch (JSONException var8) {
                    var12 = var8;
                    var10001 = false;
                }
            } else if (var11 == '[') {
                try {
                    this.json = new JSONArray(var5);
                    return;
                } catch (JSONException var7) {
                    var12 = var7;
                    var10001 = false;
                }
            } else {
                try {
                    throw new JSONException(Application.B("ၸ䃗㎥胁ⷾ找斪槉\udfcaᐴ\uf29c㨏"));
                } catch (JSONException var6) {
                    var12 = var6;
                    var10001 = false;
                }
            }
        }

        JSONException var10 = var12;
        TSLog.logger.error(TSLog.error(Application.B("ၻ䃪㎜胮ⶲ扒於榛\udfefᐕ\uf2e9㩡") + var10.getMessage()));
        var10.printStackTrace();
    }

    public Boolean unlock() {
        return this.c.unlock(this);
    }

    public Boolean destroy() {
        this.destroyed = true;
        return this.c.destroy(this);
    }

    public Integer getId() {
        return this.id;
    }

    public Object getJson() {
        return this.json;
    }

    public String getUUID() {
        return this.a;
    }

    public String getTimestamp() {
        return this.b;
    }
}
