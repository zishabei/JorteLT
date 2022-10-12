//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.List;

public class ConfigChangeEvent {
    private final Context mContext;
    private final List<String> mDirtyFields;

    public ConfigChangeEvent(Context var1, List<String> var2) {
        ArrayList var3;
        ArrayList var10001 = var3 = new ArrayList();
        this.mDirtyFields = var3;
        var10001.addAll(var2);
        this.mContext = var1;
    }

    public boolean isDirty(String var1) {
        ConfigChangeEvent var10000 = this;
        synchronized(this.mDirtyFields) {
            return var10000.mDirtyFields.contains(var1);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public String toString() {
        return Application.B("\ue75d걎힣췷㽔㳸䉈") + this.mDirtyFields.toString();
    }
}
