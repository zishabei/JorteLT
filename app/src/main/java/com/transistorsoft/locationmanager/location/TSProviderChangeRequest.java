//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.TSConfig;

public class TSProviderChangeRequest extends SingleLocationRequest {
    TSProviderChangeRequest(TSProviderChangeRequest.Builder var1) {
        super(var1);
        super.mAction = 3;
    }

    public static class Builder extends com.transistorsoft.locationmanager.location.SingleLocationRequest.Builder<TSProviderChangeRequest.Builder> {
        public Builder(Context var1) {
            super(var1);
            TSConfig var2;
            boolean var3;
            if ((var2 = TSConfig.getInstance(var1.getApplicationContext())).getEnabled() && var2.isLocationTrackingMode()) {
                var3 = true;
            } else {
                var3 = false;
            }

            super.mPersist = var3;
        }

        public TSProviderChangeRequest build() {
            return new TSProviderChangeRequest(this);
        }
    }
}
