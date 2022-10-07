//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.content.Context;
import android.location.Location;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.tslocationmanager.Application;

public class TSCurrentPositionRequest extends SingleLocationRequest {
    private final Long mMaximumAge;

    protected TSCurrentPositionRequest(TSCurrentPositionRequest.Builder var1) {
        super(var1);
        super.mAction = 2;
        Long var3;
        this.mMaximumAge = var3 = var1.mMaximumAge;
        final Location var2;
        final TSLocationManager var4;
        if (var3 > 0L && (var2 = (var4 = TSLocationManager.getInstance(super.mContext)).getLastLocation()) != null && TSLocationManager.locationAge(var2) <= this.mMaximumAge && var2.getAccuracy() <= (float)super.mDesiredAccuracy) {
            super.mSamples.set(0);
            var2.getExtras().remove(Application.B("蘜쳼筴湌좚"));
            var4.register(this);
            BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                public void run() {
                    TSLocationManager var10000 = var4;
                    <undefinedtype> var10003 = this;
                    int var1 = TSCurrentPositionRequest.this.getId();
                    var10000.onSingleLocationResult(new SingleLocationResult(var1, var2));
                }
            });
        }

    }

    public long getMaximumAge() {
        return this.mMaximumAge;
    }

    public static class Builder extends com.transistorsoft.locationmanager.location.SingleLocationRequest.Builder<TSCurrentPositionRequest.Builder> {
        private long mMaximumAge = 0L;

        public Builder(Context var1) {
            super(var1);
        }

        public TSCurrentPositionRequest.Builder setMaximumAge(Long var1) {
            this.mMaximumAge = var1;
            return this;
        }

        public TSCurrentPositionRequest build() {
            return new TSCurrentPositionRequest(this);
        }
    }
}
