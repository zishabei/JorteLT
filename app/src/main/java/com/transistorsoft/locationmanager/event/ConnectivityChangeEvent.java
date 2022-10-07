//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectivityChangeEvent {
    private final Boolean mHasConnection;

    public ConnectivityChangeEvent(boolean var1) {
        this.mHasConnection = var1;
    }

    public boolean hasConnection() {
        return this.mHasConnection;
    }

    public Map<String, Object> toMap() {
        HashMap var10000 = new HashMap();
        Boolean var10002 = this.mHasConnection;
        var10000.put(Application.B("㭅ꔬ꾈ᙅ褼ꈆ\uddadṩ멎"), var10002);
        return var10000;
    }

    public JSONObject toJson() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject;
        ConnectivityChangeEvent var10001 = this;
        var1.<init>();
        String var3 = Application.B("쎴\uab6bࠞ䦚\uec32⬹됄\udce8憳");

        try {
            var10000.put(var3, var10001.mHasConnection);
        } catch (JSONException var2) {
            var2.printStackTrace();
        }

        return var1;
    }
}
