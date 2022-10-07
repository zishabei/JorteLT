//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.content.Context;
import com.transistorsoft.locationmanager.adapter.TSConfig;

public class TSMotionChangeRequest extends SingleLocationRequest {
    private TSMotionChangeRequest(TSMotionChangeRequest.Builder var1) {
        super(var1);
        super.mAction = 1;
    }

    public static class Builder extends com.transistorsoft.locationmanager.location.SingleLocationRequest.Builder<TSMotionChangeRequest.Builder> {
        public Builder(Context var1) {
            super(var1);
            TSConfig var2;
            if ((var2 = TSConfig.getInstance(var1.getApplicationContext())).getEnabled() && var2.isLocationTrackingMode()) {
                int var3;
                boolean var4;
                if ((var3 = var2.getPersistMode()) != 2 && var3 != 1) {
                    var4 = false;
                } else {
                    var4 = true;
                }

                super.mPersist = var4;
            } else {
                super.mPersist = false;
            }

        }

        public TSMotionChangeRequest build() {
            return new TSMotionChangeRequest(this);
        }
    }
}
