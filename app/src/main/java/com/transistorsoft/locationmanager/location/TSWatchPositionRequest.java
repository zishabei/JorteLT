//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.intentfilter.androidpermissions.PermissionManager.PermissionRequestListener;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.d.b;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.service.LocationRequestService;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.util.concurrent.atomic.AtomicBoolean;

public class TSWatchPositionRequest extends SingleLocationRequest {
    private long interval;
    private PendingIntent pendingIntent;
    private final AtomicBoolean mEnabled = new AtomicBoolean(false);

    TSWatchPositionRequest(TSWatchPositionRequest.Builder var1) {
        super(var1);
        super.mDesiredAccuracy = TSConfig.getInstance(super.mContext).translateDesiredAccuracy(var1.mDesiredAccuracy);
        this.interval = var1.mInterval;
        super.mAction = 5;
    }

    public long getInterval() {
        return this.interval;
    }

    void start() {
        c.h(super.mContext, new PermissionRequestListener() {
            public void onPermissionGranted() {
                TSLocationManager var1;
                if ((var1 = TSLocationManager.getInstance(TSWatchPositionRequest.super.mContext)) == null) {
                    TSWatchPositionRequest.this.onError(-1);
                } else if (!var1.isLocationServicesEnabled()) {
                    TSWatchPositionRequest.this.onError(1);
                } else {
                    TSWatchPositionRequest.this.startUpdatingLocation();
                }
            }

            public void onPermissionDenied(DeniedPermissions var1) {
                TSWatchPositionRequest.this.onError(1);
            }
        });
    }

    void stop() {
        TSWatchPositionRequest var10000 = this;
        AtomicBoolean var1;
        AtomicBoolean var10002 = var1 = this.mEnabled;
        TSWatchPositionRequest var10003 = this;
        synchronized(var1) {
            var10003.mEnabled.set(false);
        }

        FusedLocationProviderClient var10001 = LocationServices.getFusedLocationProviderClient(super.mContext);
        Context var4 = super.mContext;
        var10003 = this;
        int var3 = super.mAction;
        var10001.removeLocationUpdates(SingleLocationRequest.getPendingIntent(var4, var3, var10003.getId()));
        LocationRequestService.stopService(var10000.mContext);
    }

    public void startUpdatingLocation() {
        TSConfig var1 = TSConfig.getInstance(super.mContext);
        if (b.e(super.mContext)) {
            TSWatchPositionRequest var10000 = this;
            synchronized(this.mEnabled){}

            boolean var10001;
            Throwable var25;
            boolean var26;
            try {
                var26 = var10000.mEnabled.get();
            } catch (Throwable var22) {
                var25 = var22;
                var10001 = false;
                throw var25;
            }

            if (var26) {
                try {
                    this.stop();
                } catch (Throwable var21) {
                    var25 = var21;
                    var10001 = false;
                    throw var25;
                }
            }

            try {
                var10000 = this;
                this.mEnabled.set(true);
            } catch (Throwable var20) {
                var25 = var20;
                var10001 = false;
                throw var25;
            }

            LocationRequest var2;
            LocationRequest var27 = var2 = LocationRequest.create();
            var2.setPriority(var1.getDesiredAccuracy());
            var2.setInterval(this.interval);
            var27.setFastestInterval(this.interval);
            var27.setSmallestDisplacement(0.0F);
            var27.setMaxWaitTime(0L);
            FusedLocationProviderClient var28 = LocationServices.getFusedLocationProviderClient(var10000.mContext);

            SecurityException var29;
            label218: {
                Context var10002;
                TSWatchPositionRequest var10003;
                int var10004;
                try {
                    var27 = var2;
                    var10002 = super.mContext;
                    var10003 = this;
                    var10004 = super.mAction;
                } catch (SecurityException var19) {
                    var29 = var19;
                    var10001 = false;
                    break label218;
                }

                int var23 = var10004;

                try {
                    var28.requestLocationUpdates(var27, SingleLocationRequest.getPendingIntent(var10002, var23, var10003.getId()));
                    return;
                } catch (SecurityException var18) {
                    var29 = var18;
                    var10001 = false;
                }
            }

            SecurityException var24 = var29;
            TSLog.logger.error(TSLog.error(Application.B("ﲺ渴\udee4홎襨춣궔烪龱\ude4d쾳⬣ᦨᡯ䃔ᠪ㋢ꂶ헿䣇ᰵ\uf38d⇄鄰딣뙦⎴ꗪ◬훁㉾饰ㄼ䳧\uec92깔\ufaf4츹뤷蟤㎺왫킱쐔풤嶡㾍啜ࠢ믵㝠賑嘬") + var24.getMessage()), var24);
        }
    }

    public void onSuccess(TSLocation var1) {
        TSLocationCallback var2;
        if ((var2 = super.mCallback) != null) {
            var2.onLocation(var1);
        }

    }

    public void onError(int var1) {
        TSLocationCallback var2;
        if ((var2 = super.mCallback) != null) {
            var2.onError(var1);
        }

    }

    public static class Builder extends com.transistorsoft.locationmanager.location.SingleLocationRequest.Builder<TSWatchPositionRequest.Builder> {
        private long mInterval = 1000L;

        public Builder(Context var1) {
            super(var1);
            super.mDesiredAccuracy = TSConfig.getInstance(var1).getDesiredAccuracy();
        }

        public TSWatchPositionRequest.Builder setInterval(Long var1) {
            this.mInterval = var1;
            return this;
        }

        public TSWatchPositionRequest build() {
            return new TSWatchPositionRequest(this);
        }
    }
}
