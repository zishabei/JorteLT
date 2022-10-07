//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;

import com.transistorsoft.locationmanager.location.TSLocation;
import org.json.JSONObject;

public class HeadlessEvent {
    private final Object mEvent;
    private final String mName;
    private final Context mContext;

    public HeadlessEvent(Context var1, String var2, Object var3) {
        this.mName = var2;
        this.mEvent = var3;
        this.mContext = var1;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getName() {
        return this.mName;
    }

    public Object getEvent() {
        return this.mEvent;
    }

    public JSONObject getBootEvent() {
        return (JSONObject)this.mEvent;
    }

    public JSONObject getTerminateEvent() {
        return (JSONObject)this.mEvent;
    }

    public TSLocation getLocationEvent() {
        return (TSLocation)this.mEvent;
    }

    public MotionChangeEvent getMotionChangeEvent() {
        return (MotionChangeEvent)this.mEvent;
    }

    public GeofenceEvent getGeofenceEvent() {
        return (GeofenceEvent)this.mEvent;
    }

    public GeofencesChangeEvent getGeofencesChangeEvent() {
        return (GeofencesChangeEvent)this.mEvent;
    }

    public ActivityChangeEvent getActivityChangeEvent() {
        return (ActivityChangeEvent)this.mEvent;
    }

    public JSONObject getScheduleEvent() {
        return (JSONObject)this.mEvent;
    }

    public HttpResponse getHttpEvent() {
        return (HttpResponse)this.mEvent;
    }

    public HeartbeatEvent getHeartbeatEvent() {
        return (HeartbeatEvent)this.mEvent;
    }

    public LocationProviderChangeEvent getProviderChangeEvent() {
        return (LocationProviderChangeEvent)this.mEvent;
    }

    public PowerSaveModeChangeEvent getPowerSaveChangeEvent() {
        return (PowerSaveModeChangeEvent)this.mEvent;
    }

    public ConnectivityChangeEvent getConnectivityChangeEvent() {
        return (ConnectivityChangeEvent)this.mEvent;
    }

    public Boolean getEnabledChangeEvent() {
        return (Boolean)this.mEvent;
    }

    public String getNotificationEvent() {
        return (String)this.mEvent;
    }

    public AuthorizationEvent getAuthorizationEvent() {
        return (AuthorizationEvent)this.mEvent;
    }
}
