//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.location.Location;

public class SingleLocationResult {
    private final int mRequestId;
    private final Location mLocation;

    public SingleLocationResult(int var1, Location var2) {
        this.mRequestId = var1;
        this.mLocation = var2;
    }

    int getRequestId() {
        return this.mRequestId;
    }

    public Location getLocation() {
        return this.mLocation;
    }
}
