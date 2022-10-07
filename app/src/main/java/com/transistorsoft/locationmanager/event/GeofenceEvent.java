//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.google.android.gms.location.GeofencingEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class GeofenceEvent {
    private final GeofencingEvent mGeofencingEvent;
    private final TSGeofence mGeofenceRecord;
    private final TSLocation mLocation;

    public GeofenceEvent(GeofencingEvent var1, TSGeofence var2, TSLocation var3) {
        this.mGeofenceRecord = var2;
        this.mLocation = var3;
        var3.addGeofencingEvent(var1, var2);
        this.mGeofencingEvent = var1;
    }

    public TSGeofence getGeofence() {
        return this.mGeofenceRecord;
    }

    public GeofencingEvent getGeofencingEvent() {
        return this.mGeofencingEvent;
    }

    public TSLocation getLocation() {
        return this.mLocation;
    }

    public JSONObject toJson() {
        GeofenceEvent var10000 = this;
        JSONObject var10001 = new JSONObject;
        JSONObject var10002 = var10001;
        JSONObject var1;
        JSONObject var10003 = var1 = var10001;
        var10001.<init>();
        String var10004 = Application.B("얲䪭䚾ꁀٹ\uef8c\udb2aｱ");

        JSONException var9;
        label54: {
            boolean var10;
            try {
                var10003.put(var10004, this.mLocation.toJson());
            } catch (JSONException var6) {
                var9 = var6;
                var10 = false;
                break label54;
            }

            String var13 = Application.B("얷䪦䚸ꁏٹ\uef8c\udb23ｶ\uf58a\u1ccf");

            try {
                var10002.put(var13, this.mGeofenceRecord.getIdentifier());
            } catch (JSONException var5) {
                var9 = var5;
                var10 = false;
                break label54;
            }

            String var12 = Application.B("얿䪡䚩ꁈ٢\uef8b");

            try {
                var10001.put(var12, this.mLocation.getGeofenceAction());
            } catch (JSONException var4) {
                var9 = var4;
                var10 = false;
                break label54;
            }

            JSONObject var11;
            try {
                var11 = var10000.mLocation.getGeofenceExtras();
            } catch (JSONException var3) {
                var9 = var3;
                var10 = false;
                break label54;
            }

            JSONObject var7 = var11;
            if (var11 == null) {
                return var1;
            }

            try {
                var1.put(Application.B("얻䪺䚩ꁓ٬\uef96"), var7);
                return var1;
            } catch (JSONException var2) {
                var9 = var2;
                var10 = false;
            }
        }

        JSONException var8 = var9;
        TSLog.logger.error(TSLog.error(var8.getMessage()));
        var8.printStackTrace();
        return var1;
    }

    public Map<String, Object> toMap() {
        GeofenceEvent var10000 = this;
        HashMap var1;
        HashMap var10001 = var1 = new HashMap;
        var1.<init>();
        var1.put(Application.B("썝\ud93e뼁െ僢㗸\uda28ꘊȤ匈"), this.mGeofenceRecord.getIdentifier());
        var10001.put(Application.B("썕\ud939뼐ു價㗿"), this.mLocation.getGeofenceAction());
        String var10002 = Application.B("썘\ud935뼇\u0d49僢㗸\uda21꘍");

        JSONException var8;
        label40: {
            boolean var10;
            try {
                var10001.put(var10002, this.mLocation.toMap());
            } catch (JSONException var4) {
                var8 = var4;
                var10 = false;
                break label40;
            }

            JSONObject var9;
            try {
                var9 = var10000.mLocation.getGeofenceExtras();
            } catch (JSONException var3) {
                var8 = var3;
                var10 = false;
                break label40;
            }

            JSONObject var5 = var9;
            if (var9 == null) {
                return var1;
            }

            HashMap var11 = var1;
            JSONObject var12 = var5;
            String var6 = Application.B("썑\ud922뼐൚僷㗢");

            try {
                var11.put(var6, Util.toMap(var12));
                return var1;
            } catch (JSONException var2) {
                var8 = var2;
                var10 = false;
            }
        }

        JSONException var7 = var8;
        TSLog.logger.warn(TSLog.warn(var7.getMessage()));
        var7.printStackTrace();
        return var1;
    }
}
