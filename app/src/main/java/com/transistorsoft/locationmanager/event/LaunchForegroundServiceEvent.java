//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import android.os.Build.VERSION;
import com.transistorsoft.locationmanager.service.AbstractService;
import com.transistorsoft.tslocationmanager.Application;

public class LaunchForegroundServiceEvent {
    public static final String ACTION = Application.B("아ﵓ\ud81d\uf5d4\ue71e\uee81뛫䀚႟ꎻ岸番\ueee4戾梞ૉ죊溿筂嗨࠱硻\u0889닇啸");

    public LaunchForegroundServiceEvent(Context var1) {
        if (VERSION.SDK_INT >= 31) {
            AbstractService.launchQueuedServices(var1);
        }

    }
}
