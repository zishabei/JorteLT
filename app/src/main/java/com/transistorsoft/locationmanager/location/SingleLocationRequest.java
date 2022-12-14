//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Build.VERSION;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.intentfilter.androidpermissions.PermissionManager.PermissionRequestListener;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.event.SecurityExceptionEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.service.AbstractService;
import com.transistorsoft.locationmanager.service.LocationRequestService;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

public class SingleLocationRequest {
    public static final int ACTION_MOTION_CHANGE = 1;
    public static final int ACTION_GET_CURRENT_POSITION = 2;
    public static final int ACTION_PROVIDER_CHANGE = 3;
    public static final int ACTION_GET_CURRENT_STATE = 4;
    public static final int ACTION_WATCH_POSITION = 5;
    public static final Float GOOD_ACCURACY_THRESHOLD = 10.0F;
    private static final Integer DEFAULT_LOCATION_ACQUISITION_ATTEMPTS = 5;
    private static final AtomicInteger sNextId = new AtomicInteger();
    protected int mAction = 0;
    protected Context mContext;
    protected long mTimeout;
    protected final AtomicInteger mSamples = new AtomicInteger(3);
    protected boolean mPersist;
    protected int mDesiredAccuracy;
    protected JSONObject mExtras;
    protected TSLocationCallback mCallback;
    private TSLocationCallback mOnCompleteCallback;
    private int mId;
    private final AtomicBoolean mTimedOut = new AtomicBoolean(false);
    private long mStartedAt;
    private final AtomicBoolean mFinished = new AtomicBoolean(false);
    private final AtomicBoolean mHasRunCallback = new AtomicBoolean(false);
    private final Handler mTimerHandler;
    private Runnable mTimeoutRunnable;
    private final ArrayList<Location> mLocations = new ArrayList();

    private static int getNextId() {
        return sNextId.incrementAndGet();
    }

    public static Intent getIntent(Context var0, int var1, int var2) {
        Intent var10000 = new Intent(var0, LocationRequestService.class);
        var10000.setAction(var1 + Application.B("???") + var2);
        return var10000;
    }

    public static PendingIntent getPendingIntent(Context var0, int var1, int var2) {
        Intent var3;
        Intent var10000 = var3 = new Intent(var0, LocationRequestService.class);
        var10000.setAction(var1 + Application.B("\uddf4") + var2);
        if (VERSION.SDK_INT >= 26) {
            Context var4 = var0;
            int var5 = Util.getPendingIntentFlags(134217728);
            return PendingIntent.getForegroundService(var4, 0, var3, var5);
        } else {
            return PendingIntent.getService(var0, 0, var3, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }

    public static void forceStop(Context var0, int var1, int var2) {
        LocationServices.getFusedLocationProviderClient(var0).removeLocationUpdates(getPendingIntent(var0, var1, var2));
    }

    SingleLocationRequest(SingleLocationRequest.Builder<?> var1) {
        this.mContext = var1.mContext;
        this.mTimeout = (long)var1.mTimeout;
        this.mPersist = var1.mPersist;
        this.mSamples.set(var1.mSamples);
        this.mDesiredAccuracy = var1.mDesiredAccuracy;
        this.mExtras = var1.mExtras;
        this.mCallback = var1.mCallback;
        this.mId = getNextId();
        this.mTimerHandler = new Handler(Looper.getMainLooper());
    }

    private TSLocationCallback getCallback() {
        return this.mCallback;
    }

    private Runnable createTimeoutHandler() {
        return new Runnable() {
            public void run() {
                SingleLocationRequest.this.mTimedOut.set(true);
                SingleLocationRequest.this.finish();
            }
        };
    }

    public int getId() {
        return this.mId;
    }

    void setId(int var1) {
        this.mId = var1;
    }

    public int getAction() {
        return this.mAction;
    }

    public boolean didTimeout() {
        return this.mTimedOut.get();
    }

    public int getSamples() {
        return this.mSamples.get();
    }

    void setSamples(int var1) {
        this.mSamples.set(var1);
    }

    public boolean getPersist() {
        return this.mPersist;
    }

    public JSONObject getExtras() {
        return this.mExtras;
    }

    public boolean hasExtras() {
        return this.mExtras != null;
    }

    public long getStartedAt() {
        return this.mStartedAt;
    }

    public void onSuccess(TSLocation var1) {
        TSLocationCallback var2;
        if (this.mHasRunCallback.compareAndSet(false, true) && (var2 = this.mCallback) != null) {
            var2.onLocation(var1);
        }

    }

    public void onError(int var1) {
        TSLocationCallback var2;
        if (this.mHasRunCallback.compareAndSet(false, true) && (var2 = this.mCallback) != null) {
            var2.onError(var1);
        }

    }

    void start() {
        if (this.mSamples.get() > 0) {
            TSConfig var1 = TSConfig.getInstance(this.mContext);
            boolean var2 = c.b(this.mContext);
            boolean var3 = LifecycleManager.f().a();
            boolean var10000 = TSLocationManager.getInstance(this.mContext).isUpdatingLocation();
            boolean var4 = var1.getUseSignificantChangesOnly();
            if ((!var10000 || var4) && !var2 && var3) {
                TSLog.logger.warn(c.d(this.mContext));
                this.onError(3);
                if (var1.requestsLocationAlways()) {
                    c.g(this.mContext, new PermissionRequestListener() {
                        public void onPermissionGranted() {
                        }

                        public void onPermissionDenied(DeniedPermissions var1) {
                        }
                    });
                }

            } else {
                c.h(this.mContext, new PermissionRequestListener() {
                    public void onPermissionGranted() {
                        TSLocationManager var1;
                        if ((var1 = TSLocationManager.getInstance(SingleLocationRequest.this.mContext)) == null) {
                            SingleLocationRequest.this.onError(-1);
                        } else if (!var1.isLocationServicesEnabled()) {
                            SingleLocationRequest.this.onError(1);
                        } else {
                            Intent var2;
                            Intent var10001 = var2 = new Intent(SingleLocationRequest.this.mContext, LocationRequestService.class);
                            var2.setAction(Application.B("???????????????"));
                            var10001.putExtra(Application.B("??????"), SingleLocationRequest.this.mId);
                            AbstractService.startForegroundService(SingleLocationRequest.this.mContext, var2);
                        }
                    }

                    public void onPermissionDenied(DeniedPermissions var1) {
                        SingleLocationRequest.this.onError(1);
                    }
                });
            }
        }
    }

    public void startUpdatingLocation() {
        TSLog.logger.info(TSLog.notice(Application.B("???\uee7e\u0ee6???\ud889??????\udda8???\udbb0????????????\uef27??????????????????\uf1ce??????????????????????????????????????????\ue1f6???") + this.mAction) + Application.B("???\uee0d\u0efd???\ud89f??????\udd97???\udb9a?????????") + this.mId + Application.B("???"));
        FusedLocationProviderClient var1 = LocationServices.getFusedLocationProviderClient(this.mContext);
        long var2 = this.mTimeout * 1000L;
        LocationRequest var4 = LocationRequest.create().setPriority(100).setInterval(0L).setFastestInterval(0L).setSmallestDisplacement(0.0F);
        if (this.mAction != 1) {
            var4.setExpirationDuration(var2);
        }

        LocationRequest var10;
        SingleLocationRequest var11;
        label27: {
            SecurityException var10000;
            label32: {
                boolean var10001;
                FusedLocationProviderClient var10002;
                LocationRequest var10003;
                Context var10004;
                SingleLocationRequest var10005;
                int var10006;
                try {
                    var10 = var4;
                    var11 = this;
                    var10002 = var1;
                    var10003 = var4;
                    var10004 = this.mContext;
                    var10005 = this;
                    var10006 = this.mAction;
                } catch (SecurityException var6) {
                    var10000 = var6;
                    var10001 = false;
                    break label32;
                }

                int var7 = var10006;

                try {
                    var10002.requestLocationUpdates(var10003, getPendingIntent(var10004, var7, var10005.getId()));
                    break label27;
                } catch (SecurityException var5) {
                    var10000 = var5;
                    var10001 = false;
                }
            }

            SecurityException var8 = var10000;
            TSLog.logger.error(TSLog.error(Application.B("???\uee48\u0eec???\ud89c??????\udd9d???\udbab????????????\uef20??????????????????\uf1d6??????????????????????????????????????????\ue1a3???\udd2d????????????????????????????????????????????") + var8.getMessage()));
            EventBus.getDefault().post(new SecurityExceptionEvent(var8, this.mAction));
            return;
        }

        var11.mStartedAt = System.currentTimeMillis();
        if (var10.getExpirationTime() <= var2) {
            Runnable var9;
            this.mTimeoutRunnable = var9 = this.createTimeoutHandler();
            this.mTimerHandler.postDelayed(var9, this.mTimeout * 1000L);
        }
    }

    void addLocation(Location var1) {
        synchronized(this.mLocations){
            mLocations.add(var1);
        }

        if (this.mDesiredAccuracy > 0 && var1.getAccuracy() <= (float)this.mDesiredAccuracy) {
            this.mSamples.set(0);
        }

        Bundle var9;
        if ((var9 = var1.getExtras()) == null) {
            var9 = new Bundle();
        }

        if (!this.isComplete()) {
            var9.putBoolean(Application.B("?????????????????"), true);
        }

    }

    public boolean hasSample() {
        SingleLocationRequest var10000 = this;
        synchronized(this.mLocations){}

        boolean var10001;
        Throwable var8;
        boolean var9 = var10000.mLocations.isEmpty();


        boolean var1;
        if (!var9) {
            var1 = true;
        } else {
            var1 = false;
        }

            return var1;
    }

    public Location getBestLocation() {
        Location var1 = null;
        Iterator var2 = this.mLocations.iterator();

        while(var2.hasNext()) {
            Location var3 = (Location)var2.next();
            if (var1 == null) {
                var1 = var3;
            }

            if (var3.getAccuracy() < var1.getAccuracy()) {
                var1 = var3;
            }
        }

        if (var1 != null) {
            Bundle var4;
            if ((var4 = var1.getExtras()) == null) {
                var4 = new Bundle();
                var1.setExtras(var4);
            }

            var4.remove(Application.B("???????????????\ud867"));
            boolean var10001 = this.mPersist;
            var4.putBoolean(Application.B("?????????\u31bb???\ud871???"), var10001);
        }

        return var1;
    }

    public long getElapsed() {
        return System.currentTimeMillis() - this.mStartedAt;
    }

    public void finish() {
        if (!this.mFinished.get()) {
            this.mFinished.set(true);
            this.mSamples.set(0);
            FusedLocationProviderClient var10002 = LocationServices.getFusedLocationProviderClient(this.mContext);
            int var1 = this.mAction;
            var10002.removeLocationUpdates(getPendingIntent(this.mContext, var1, this.getId()));
            LocationRequestService.stopService(this.mContext);
            if (this.mTimedOut.get()) {
                SingleLocationRequest var10000 = this;
                final TSLocationManager var47 = TSLocationManager.getInstance(this.mContext);
                TSLog.logger.warn(TSLog.warn(Application.B("???????????????\ue883?????\udf0f") + this.mId));
                synchronized(this.mLocations){}

                Throwable var49;
                boolean var10001;
                boolean var50 = var10000.mLocations.isEmpty();


                if (!var50) {
                    SingleLocationRequest var52;
                    int var53;
                    var52 = this;
                    var53 = this.mId;
                    int var4 = var53;
                    SingleLocationResult var51 = new SingleLocationResult(var4, var52.getBestLocation());
                        TSLog.logger.info(TSLog.notice(Application.B("????????????\u0ae4\ue8d6?????\udf4a?????????\u0ee1?????????\uf451????????????")));
                        this.mSamples.set(this.mLocations.size());
                        this.mTimedOut.set(false);
                    final SingleLocationResult var3 = var51;

                        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                            public void run() {
                                var47.onSingleLocationResult(var3);
                            }
                        });

                } else {
                        var47.onLocationTimeout(this);

                }
            }

            Runnable var48;
            if ((var48 = this.mTimeoutRunnable) != null) {
                this.mTimerHandler.removeCallbacks(var48);
            }

        }
    }

    boolean isComplete() {
        SingleLocationRequest var10000 = this;
        synchronized(this.mLocations){}

        boolean var10001;
        Throwable var9;
        int var10;
        int var11;
            var10 = var10000.mLocations.size();
            var11 = this.mSamples.get();


        boolean var8;
        if (var10 >= var11) {
            var8 = true;
        } else {
            var8 = false;
        }
            return var8;

    }

    public boolean isFinished() {
        return this.mFinished.get();
    }

    public static class Builder<T extends SingleLocationRequest.Builder<T>> {
        Context mContext;
        int mTimeout;
        boolean mPersist;
        int mSamples;
        int mDesiredAccuracy;
        private JSONObject mExtras;
        private TSLocationCallback mCallback;

        public Builder(Context var1) {
            super();
            Context var10006 = var1;

            TSConfig var2 = TSConfig.getInstance(var1);
            this.mContext = var10006;
            this.mPersist = var2.getEnabled();
            this.mTimeout = var2.getLocationTimeout();
            this.mSamples = 3;
            this.mDesiredAccuracy = var2.getStationaryRadius();
            this.mCallback = new TSLocationCallback() {
                public void onLocation(TSLocation var1) {
                }

                public void onError(Integer var1) {
                }
            };
        }

//        public T setTimeout(int var1) {
//            this.mTimeout = var1;
//            return this;
//        }

        public Builder<T> setPersist(boolean var1) {
            this.mPersist = var1;
            return this;
        }

        public Builder<T> setSamples(int var1) {
            this.mSamples = var1;
            return this;
        }

        public Builder<T> setDesiredAccuracy(int var1) {
            this.mDesiredAccuracy = var1;
            return this;
        }

        public Builder<T> setExtras(JSONObject var1) {
            this.mExtras = var1;
            return this;
        }

        public Builder<T> setCallback(TSLocationCallback var1) {
            this.mCallback = var1;
            return this;
        }

        public SingleLocationRequest build() {
            return new SingleLocationRequest(this);
        }
    }
}
