//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.tslocationmanager.Application;
import org.greenrobot.eventbus.EventBus;

public class StopAfterElapsedMinutesEvent {
    public static final String ACTION = Application.B("凌\uf0c4鬿ꥁ퓼뗎ለ价⬬蓆㿠ヌ佌ℸ\ueb5e䏝鐋㔁㞱ﺺ눯䨐Ꞵタ\u187c쿀");

    public StopAfterElapsedMinutesEvent(Context var1) {
        if (TSConfig.getInstance(var1).getEnabled()) {
            EventBus.getDefault().post(this);
            TrackingService.stop(var1);
        }
    }
}
