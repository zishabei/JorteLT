//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

public class BatteryOptimizationEvent {
    private boolean mIsIgnoringBatteryOptimizations;

    public BatteryOptimizationEvent(boolean var1) {
        this.mIsIgnoringBatteryOptimizations = var1;
    }

    public boolean isIgnoringBatteryOptimizations() {
        return this.mIsIgnoringBatteryOptimizations;
    }
}
