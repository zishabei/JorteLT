//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.tslocationmanager.Application;
import org.greenrobot.eventbus.EventBus;

public class MotionActivityCheckEvent {
    public static final String ACTION = Application.B("猵췣溞江䊣䢶\u2bba\ue46d彸៕㟻ꚷ焍瘀ᄲ\ud97c⾃៷䟥輹噝");

    public MotionActivityCheckEvent(Context var1) {
        TSConfig var2;
        if ((var2 = TSConfig.getInstance(var1)).getEnabled() && var2.getIsMoving()) {
            EventBus.getDefault().post(this);
        }
    }
}
