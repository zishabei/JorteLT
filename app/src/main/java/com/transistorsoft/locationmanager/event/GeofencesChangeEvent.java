//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeofencesChangeEvent {
    private final List<TSGeofence> mOn;
    private final List<String> mOff;

    public GeofencesChangeEvent() {
        GeofencesChangeEvent var10000 = this;
        GeofencesChangeEvent var10001 = this;
        super();
        ArrayList var1;
        var1 = new ArrayList.<init>();
        var10001.mOn = var1;
        var1 = new ArrayList.<init>();
        var10000.mOff = var1;
    }

    public GeofencesChangeEvent(List<TSGeofence> var1, List<String> var2) {
        this.mOn = var1;
        this.mOff = var2;
    }

    public List<TSGeofence> getActivatedGeofences() {
        return this.mOn;
    }

    public List<String> getDeactivatedGeofences() {
        return this.mOff;
    }

    public JSONObject toJson() {
        GeofencesChangeEvent var10000 = this;
        JSONArray var1;
        var1 = new JSONArray.<init>();
        JSONArray var2;
        var2 = new JSONArray.<init>();
        JSONObject var3;
        var3 = new JSONObject.<init>();

        JSONException var15;
        label77: {
            Iterator var16;
            boolean var10001;
            try {
                var16 = var10000.mOff.iterator();
            } catch (JSONException var12) {
                var15 = var12;
                var10001 = false;
                break label77;
            }

            Iterator var4 = var16;

            label67:
            while(true) {
                boolean var17;
                try {
                    var17 = var4.hasNext();
                } catch (JSONException var10) {
                    var15 = var10;
                    var10001 = false;
                    break;
                }

                if (!var17) {
                    try {
                        var16 = this.mOn.iterator();
                    } catch (JSONException var9) {
                        var15 = var9;
                        var10001 = false;
                        break;
                    }

                    Iterator var13 = var16;

                    while(true) {
                        try {
                            var17 = var13.hasNext();
                        } catch (JSONException var7) {
                            var15 = var7;
                            var10001 = false;
                            break label67;
                        }

                        if (!var17) {
                            JSONObject var18;
                            try {
                                var18 = var3;
                                var3.put(Application.B("짩袉"), var1);
                            } catch (JSONException var6) {
                                var15 = var6;
                                var10001 = false;
                                break label67;
                            }

                            try {
                                var18.put(Application.B("짩袁\uf6e3"), var2);
                                return var3;
                            } catch (JSONException var5) {
                                var15 = var5;
                                var10001 = false;
                                break label67;
                            }
                        }

                        try {
                            var1.put(((TSGeofence)var13.next()).toJson());
                        } catch (JSONException var8) {
                            var15 = var8;
                            var10001 = false;
                            break label67;
                        }
                    }
                }

                try {
                    var2.put((String)var4.next());
                } catch (JSONException var11) {
                    var15 = var11;
                    var10001 = false;
                    break;
                }
            }
        }

        JSONException var14 = var15;
        TSLog.logger.error(TSLog.error(var14.getMessage()));
        var14.printStackTrace();
        return var3;
    }

    public Map<String, List> toMap() {
        HashMap var1;
        HashMap var10001 = var1 = new HashMap;
        var10001.<init>();
        ArrayList var2;
        var2 = new ArrayList.<init>();
        ArrayList var3;
        var3 = new ArrayList.<init>(this.mOff);
        var10001.put(Application.B("伊뫻걊"), var3);
        var10001.put(Application.B("伊뫳"), var2);
        Iterator var4 = this.mOn.iterator();

        while(var4.hasNext()) {
            var2.add(((TSGeofence)var4.next()).toMap());
        }

        return var1;
    }
}
