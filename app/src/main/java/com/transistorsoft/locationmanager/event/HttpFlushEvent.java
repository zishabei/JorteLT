//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.tslocationmanager.Application;

public class HttpFlushEvent {
    public static final String ACTION = Application.B("થ㾤뻋㑅⒕\uf707\u2d79꽲훗쀣");

    public HttpFlushEvent() {
    }

    public static void run(Context var0) {
        TSConfig var1;
        if ((var1 = TSConfig.getInstance(var0)).getEnabled() && var1.getAutoSync() && var1.hasUrl() && !var1.getIsMoving()) {
            BackgroundGeolocation.getThreadPool().execute(() -> {
                HttpService.getInstance(var0).flush(true);
            });
        }

    }
}
