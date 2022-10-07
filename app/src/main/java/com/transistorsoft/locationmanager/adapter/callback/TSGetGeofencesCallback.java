//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.adapter.callback;

import com.transistorsoft.locationmanager.geofence.TSGeofence;
import java.util.List;

public interface TSGetGeofencesCallback {
    void onSuccess(List<TSGeofence> var1);

    void onFailure(String var1);
}
