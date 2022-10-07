//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.locationmanager.location.TSLocation;
import org.json.JSONObject;

public class PersistEvent {
    private final TSLocation mLocation;
    private final JSONObject mParams;
    private final Context mContext;

    public PersistEvent(Context var1, TSLocation var2, JSONObject var3) {
        this.mContext = var1;
        this.mParams = var3;
        this.mLocation = var2;
    }

    public TSLocation getLocation() {
        return this.mLocation;
    }

    public JSONObject getParams() {
        return this.mParams;
    }

    public Context getContext() {
        return this.mContext;
    }
}
