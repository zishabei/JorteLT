//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.tslocationmanager.Application;

public class StopTimeoutEvent {
    public static final String ACTION = Application.B("ᢋ厪눺滕ﮒ奌뿁慘뚝艒濌ꝏ");

    public StopTimeoutEvent(Context var1) {
        TSConfig var2;
        if ((var2 = TSConfig.getInstance(var1)).getEnabled() && var2.getIsMoving()) {
            TrackingService.changePace(var1, false, (TSLocationCallback)null);
        }

    }
}
