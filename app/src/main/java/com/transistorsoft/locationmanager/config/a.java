//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.config;

import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.List;

class a {
    private final List<String> mDirtyFields;
    private final String mModuleName;

    a(String var1) {
        a var10000 = this;
        a var10001 = this;
        super();
        ArrayList var2;
        var2 = new ArrayList.<init>();
        var10001.mDirtyFields = var2;
        var10000.mModuleName = var1;
    }

    public List<String> getDirtyFields() {
        return this.mDirtyFields;
    }

    void addDirty(String var1) {
        this.mDirtyFields.add(this.mModuleName + Application.B("粮") + var1);
    }

    void resetDirty() {
        this.mDirtyFields.clear();
    }
}
