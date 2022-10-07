//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.tslocationmanager.Application;

public class StartGeofencesEvent {
    public static final String ACTION = Application.B("閗䵡ඥ䪉퀞豿㼻\uf0e6鐳谤婓\ue23d钫倌휊");

    public StartGeofencesEvent(Context var1) {
        BackgroundGeolocation.getInstance(var1).startGeofences();
    }
}
