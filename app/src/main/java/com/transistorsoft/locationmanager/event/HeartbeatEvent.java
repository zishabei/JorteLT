//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class HeartbeatEvent {
    private TSLocation mLocation;

    public HeartbeatEvent() {
    }

    public void setLocation(TSLocation var1) {
        this.mLocation = var1;
    }

    public TSLocation getLocation() {
        return this.mLocation;
    }

    public JSONObject toJson() {
        HeartbeatEvent var10000 = this;
        JSONObject var1;
        var1 = new JSONObject();

        JSONException var5;
        label32: {
            boolean var10001;
            TSLocation var6;
            var6 = var10000.mLocation;

            if (var6 == null) {
                return var1;
            }

            JSONObject var7 = var1;
            String var8 = Application.B("騠\udfa5ါ팦봧⣃㺙謖");

            try {
                var7.put(var8, this.mLocation.toJson());
                return var1;
            } catch (JSONException var2) {
                var5 = var2;
                var10001 = false;
            }
        }

        JSONException var4 = var5;
        TSLog.logger.error(TSLog.error(var4.getMessage()));
        var4.printStackTrace();
        return var1;
    }

    public Map<String, Object> toMap() {
        HeartbeatEvent var10000 = this;
        HashMap var3;
        var3 = new HashMap();
        TSLocation var1;
        if ((var1 = var10000.mLocation) != null) {
            HashMap var4 = var3;
            TSLocation var10001 = var1;
            String var5 = Application.B("Šﲻ籶糦ᰮꩨ겐\uf85f");

            var4.put(var5, var10001.toMap());
        }

        return var3;
    }
}
