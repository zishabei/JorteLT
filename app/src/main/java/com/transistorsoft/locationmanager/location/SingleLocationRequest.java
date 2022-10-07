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
        var10000.setAction(var1 + Application.B("講") + var2);
        return var10000;
    }

    public static PendingIntent getPendingIntent(Context var0, int var1, int var2) {
        Intent var3;
        Intent var10000 = var3 = new Intent;
        var10000.<init>(var0, LocationRequestService.class);
        var10000.setAction(var1 + Application.B("\uddf4") + var2);
        if (VERSION.SDK_INT >= 26) {
            Context var4 = var0;
            int var5 = Util.getPendingIntentFlags(134217728);
            return PendingIntent.getForegroundService(var4, 0, var3, var5);
        } else {
            return PendingIntent.getService(var0, 0, var3, 134217728);
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
                            Intent var10001 = var2 = new Intent;
                            var2.<init>(SingleLocationRequest.this.mContext, LocationRequestService.class);
                            var2.setAction(Application.B("컂푝ꁫ魸ଭ"));
                            var10001.putExtra(Application.B("컘푍"), SingleLocationRequest.this.mId);
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
        TSLog.logger.info(TSLog.notice(Application.B("橮\uee7e\u0ee6៳\ud889ᐝ깅\udda8皍\udbb0ࢳ輫큟㿘\uef27⛤倁⟠늤钗壤\uf1ce깷邳ꌭ⋔掃恄㐁䭩⊎雝ﯰ埀ଧ輂\ue1f6祻") + this.mAction) + Application.B("標\uee0d\u0efd៸\ud89fᐄ깅\udd97皖\udb9aࢶ轥퀖") + this.mId + Application.B("橨"));
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
            TSLog.logger.error(TSLog.error(Application.B("橦\uee48\u0eec៨\ud89cᐘ깔\udd9d皧\udbabࢱ輺큆㿃\uef20⛙倊➱늦钚壾\uf1d6긲郠ꌸ⋁掅恕㑀䬹⊛雗ﯪ城୨輘\ue1a3祻\udd2d붹鎣ē♀∈읫ổ㓷㨐恀嶤降匳䑰瀥") + var8.getMessage()));
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
        ArrayList var2;
        ArrayList var10001 = var2 = this.mLocations;
        SingleLocationRequest var10002 = this;
        synchronized(var2){}

        Throwable var10000;
        boolean var10;
        try {
            var10002.mLocations.add(var1);
        } catch (Throwable var8) {
            var10000 = var8;
            var10 = false;
            throw var10000;
        }

        try {
            ;
        } catch (Throwable var7) {
            var10000 = var7;
            var10 = false;
            throw var10000;
        }

        if (this.mDesiredAccuracy > 0 && var1.getAccuracy() <= (float)this.mDesiredAccuracy) {
            this.mSamples.set(0);
        }

        Bundle var9;
        if ((var9 = var1.getExtras()) == null) {
            var9 = new Bundle.<init>();
        }

        if (!this.isComplete()) {
            var9.putBoolean(Application.B("瞈醡㥨σ捅샬"), true);
        }

    }

    public boolean hasSample() {
        SingleLocationRequest var10000 = this;
        synchronized(this.mLocations){}

        boolean var10001;
        Throwable var8;
        boolean var9;
        try {
            var9 = var10000.mLocations.isEmpty();
        } catch (Throwable var7) {
            var8 = var7;
            var10001 = false;
            throw var8;
        }

        boolean var1;
        if (!var9) {
            var1 = true;
        } else {
            var1 = false;
        }

        try {
            return var1;
        } catch (Throwable var6) {
            var8 = var6;
            var10001 = false;
            throw var8;
        }
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
                var4 = new Bundle.<init>();
                var1.setExtras(var4);
            }

            var4.remove(Application.B("⁼㴈東ㆸ蟘\ud867"));
            boolean var10001 = this.mPersist;
            var4.putBoolean(Application.B("ⁿ㴌杮\u31bb蟝\ud871၅"), var10001);
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
                TSLog.logger.warn(TSLog.warn(Application.B("迧閊엔둊૯\ue883凶Ǖ\udf0f") + this.mId));
                synchronized(this.mLocations){}

                Throwable var49;
                boolean var10001;
                boolean var50;
                try {
                    var50 = var10000.mLocations.isEmpty();
                } catch (Throwable var46) {
                    var49 = var46;
                    var10001 = false;
                    throw var49;
                }

                if (!var50) {
                    SingleLocationResult var51;
                    try {
                        TSLog.logger.info(TSLog.notice(Application.B("迕閌엌둁\u0ae4\ue8d6凲Ɲ\udf4a빙끭䄩\u0ee1苷쾅哘\uf451㐝勍☗䀜")));
                        this.mSamples.set(this.mLocations.size());
                        this.mTimedOut.set(false);
                        var51 = new SingleLocationResult;
                    } catch (Throwable var45) {
                        var49 = var45;
                        var10001 = false;
                        throw var49;
                    }

                    final SingleLocationResult var3 = var51;

                    SingleLocationRequest var52;
                    int var53;
                    try {
                        var52 = this;
                        var53 = this.mId;
                    } catch (Throwable var44) {
                        var49 = var44;
                        var10001 = false;
                        throw var49;
                    }

                    int var4 = var53;

                    try {
                        var51.<init>(var4, var52.getBestLocation());
                        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                            public void run() {
                                var47.onSingleLocationResult(var3);
                            }
                        });
                    } catch (Throwable var43) {
                        var49 = var43;
                        var10001 = false;
                        throw var49;
                    }
                } else {
                    try {
                        var47.onLocationTimeout(this);
                    } catch (Throwable var42) {
                        var49 = var42;
                        var10001 = false;
                        throw var49;
                    }
                }

                try {
                    ;
                } catch (Throwable var41) {
                    var49 = var41;
                    var10001 = false;
                    throw var49;
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
        try {
            var10 = var10000.mLocations.size();
            var11 = this.mSamples.get();
        } catch (Throwable var7) {
            var9 = var7;
            var10001 = false;
            throw var9;
        }

        boolean var8;
        if (var10 >= var11) {
            var8 = true;
        } else {
            var8 = false;
        }

        try {
            return var8;
        } catch (Throwable var6) {
            var9 = var6;
            var10001 = false;
            throw var9;
        }
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
            Context var10006 = var1;
            super();
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

        public T setTimeout(int var1) {
            this.mTimeout = var1;
            return this;
        }

        public T setPersist(boolean var1) {
            this.mPersist = var1;
            return this;
        }

        public T setSamples(int var1) {
            this.mSamples = var1;
            return this;
        }

        public T setDesiredAccuracy(int var1) {
            this.mDesiredAccuracy = var1;
            return this;
        }

        public T setExtras(JSONObject var1) {
            this.mExtras = var1;
            return this;
        }

        public T setCallback(TSLocationCallback var1) {
            this.mCallback = var1;
            return this;
        }

        public SingleLocationRequest build() {
            return new SingleLocationRequest(this);
        }
    }
}
