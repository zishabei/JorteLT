//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.location.Location;

public class WatchPositionResult {
    private final Location mLocation;
    private final int mRequestId;

    public WatchPositionResult(int var1, Location var2) {
        this.mRequestId = var1;
        this.mLocation = var2;
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    public Location getLocation() {
        return this.mLocation;
    }
}
