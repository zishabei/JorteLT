//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import android.content.Intent;

public class BootEvent {
    private final Context mContext;
    private final Intent mIntent;

    public BootEvent(Context var1, Intent var2) {
        this.mContext = var1;
        this.mIntent = var2;
    }

    Context getContext() {
        return this.mContext;
    }
}
