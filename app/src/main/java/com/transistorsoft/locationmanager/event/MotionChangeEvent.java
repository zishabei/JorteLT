//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import org.json.JSONException;
import org.json.JSONObject;

public class MotionChangeEvent {
    public JSONObject json;
    public TSLocation location;
    public Boolean isMoving;

    public MotionChangeEvent(TSLocation var1) {
        this.location = var1;
    }

    public TSLocation getLocation() {
        return this.location;
    }

    public Boolean getIsMoving() {
        return this.location.getIsMoving();
    }

    public JSONObject toJson() {
        JSONObject var10000 = new JSONObject();
        JSONObject var1;
        JSONObject var10001 = var1 = var10000;
        String var10002 = Application.B("梹햕\uef54飲鶥㌁\ue294\ua7d0");

        JSONException var5;
        label29: {
            boolean var6;
            try {
                var10001.put(var10002, this.location.toJson());
            } catch (JSONException var3) {
                var5 = var3;
                var6 = false;
                break label29;
            }

            String var7 = Application.B("梼행\uef7a飼鶧㌁\ue295\ua7d9");

            try {
                var10000.put(var7, this.location.getIsMoving());
                return var1;
            } catch (JSONException var2) {
                var5 = var2;
                var6 = false;
            }
        }

        JSONException var4 = var5;
        TSLog.logger.debug(TSLog.error(var4.getMessage()));
        return var1;
    }
}
