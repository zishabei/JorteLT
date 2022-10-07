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

public class MotionTriggerDelayEvent {
    public static final String ACTION = Application.B("ꫯ臔䃘\uf358ਈ鯣댅翈締䞀㵮㠭짇ᮺ픒ꕙ됓凱砾䚝");

    public MotionTriggerDelayEvent(Context var1) {
        if (TSConfig.getInstance(var1).getEnabled()) {
            TrackingService.changePace(var1.getApplicationContext(), true, (TSLocationCallback)null);
        }
    }
}
