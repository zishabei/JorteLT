//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Build.VERSION;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSLocationCallback;
import com.transistorsoft.locationmanager.b.a;
import com.transistorsoft.locationmanager.d.b;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.event.LocationErrorEvent;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.event.MotionChangeEvent;
import com.transistorsoft.locationmanager.event.PersistEvent;
import com.transistorsoft.locationmanager.event.StopDetectionEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofenceManager;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.locationmanager.location.TSCurrentPositionRequest.Builder;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.service.ActivityRecognitionService;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

public class TSLocationManager {
    private static TSLocationManager mInstance;
    public static final int LOCATION_ERROR_NOT_INITIALIZED = -1;
    public static final int LOCATION_ERROR_UNKNOWN = 0;
    public static final int LOCATION_ERROR_DENIED = 1;
    public static final int LOCATION_ERROR_NETWORK = 2;
    public static final int LOCATION_ERROR_BACKGROUND_WHEN_IN_USE = 3;
    public static final int LOCATION_ERROR_MINIMUM_ACCURACY = 100;
    public static final int LOCATION_ERROR_TRACKING_MODE_DISABLED = 101;
    public static final int LOCATION_ERROR_TIMEOUT = 408;
    public static final int LOCATION_ERROR_CANCELLED = 499;
    private static final float MINIMUM_DISTANCE_FILTER_SLC = 250.0F;
    private static final String ODOMETER_LATITUDE_KEY = Application.B("感뾅牸놝っੜ㽅穵瘩\ud915㪻ᲅ勥湊ㆢ\uecd8듌");
    private static final String ODOMETER_LONGITUDE_KEY = Application.B("感뾅牸놝っੜ㽅穵瘩\ud915㪵\u1c9f勫湗ㆣ\uecc9듍斣");
    private static final String ODOMETER_ACCURACY_KEY = Application.B("感뾅牸놝っੜ㽅穵瘩\ud918㪹\u1c92勹湌ㆶ\uecdf듐");
    private final Context mContext;
    private final Map<Integer, SingleLocationRequest> locationRequests;
    private final LocationRequest mLocationRequest;
    private final AtomicBoolean mIsUpdatingLocation;
    private final AtomicBoolean mIsWatchingPosition;
    private TSWatchPositionRequest mWatchPositionRequest;
    private TSProviderChangeRequest mProviderChangeRequest;
    private final Location mLastLocation;
    private final Location mLastGoodLocation;
    private final Location mLastOdometerLocation;
    private final ArrayList<Float> mAccuracyQueue;
    private float mMedianLocationAccuracy;
    private LocationProviderChangeEvent mCurrentLocationProvider;

    public static TSLocationManager getInstance(Context var0) {
        if (mInstance == null) {
            mInstance = getInstanceSynchronized(var0.getApplicationContext());
        }

        return mInstance;
    }

    private static synchronized TSLocationManager getInstanceSynchronized(Context var0) {
        if (mInstance == null) {
            mInstance = new TSLocationManager(var0.getApplicationContext());
        }

        return mInstance;
    }

    public static long locationAge(Location var0) {
        return VERSION.SDK_INT >= 17 ? (SystemClock.elapsedRealtimeNanos() - var0.getElapsedRealtimeNanos()) / 1000000L : System.currentTimeMillis() - TSLocation.getTime(var0);
    }

    static Location buildLocation(Location var0) {
        Location var1;
        Location var10000 = var1 = new Location;
        var10000.<init>(var0);
        var10000.setTime(System.currentTimeMillis());
        if (VERSION.SDK_INT >= 17) {
            var1.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
            var1.setExtras(new Bundle());
        }

        return var1;
    }

    public static long elapsedTimeMillis(Location var0, Location var1) {
        return VERSION.SDK_INT >= 17 ? (var0.getElapsedRealtimeNanos() - var1.getElapsedRealtimeNanos()) / 1000000L : TSLocation.getTime(var0) - TSLocation.getTime(var1);
    }

    public static float speedBetween(Location var0, Location var1) {
        Location var10000 = var0;
        long var2 = elapsedTimeMillis(var0, var1);
        return var10000.distanceTo(var1) / (float)var2 * 1000.0F;
    }

    private TSLocationManager(Context var1) {
        Context var10001 = var1;
        super();
        this.locationRequests = new HashMap();
        this.mLocationRequest = LocationRequest.create();
        this.mIsUpdatingLocation = new AtomicBoolean(false);
        this.mIsWatchingPosition = new AtomicBoolean(false);
        this.mLastLocation = new Location(Application.B("問쇎虃\uf177ꔈꪙ蛍䲔殩₦䖬\ue2c6江鑀\ud86d믱ಮ"));
        this.mLastGoodLocation = new Location(Application.B("問쇎虃\uf177ꔈꪙ蛍䲔殩₦䖬\ue2c6江鑀\ud86d믱ಮ"));
        this.mLastOdometerLocation = new Location(Application.B("問쇎虃\uf177ꔈꪙ蛍䲔殩₦䖬\ue2c6江鑀\ud86d믱ಮ"));
        this.mAccuracyQueue = new ArrayList();
        TSConfig var3 = TSConfig.getInstance(var1.getApplicationContext());
        this.mContext = var10001;
        EventBus var2;
        if (!(var2 = EventBus.getDefault()).isRegistered(this)) {
            var2.register(this);
        }

        if (var3.getEnabled() && var3.isLocationTrackingMode()) {
            this.loadLastOdometerLocation();
        }

    }

    private Location forceAcquireStationaryLocation() {
        Location var2;
        if ((var2 = this.getLastLocation()) != null) {
            TSLog.logger.info(TSLog.notice(Application.B("흫\ue603滭抴ꨧ\ue5e9誊⓳\ue367\uf407聛阥ꊭ紽슐뙘쟰蕁䣃錁⛞ƥ璄굗◘ꖝ㋹㻃奺佄沥⤮\ufbc3벺鄺᜵亲\uea80㲁ｲ२쳚畭療傠\udccc۸ᅰ\uf0bc瀴몖Ρ冴酮鐁ﾜͤ䋰")));
            Bundle var1;
            if ((var1 = var2.getExtras()) == null) {
                var1 = new Bundle.<init>();
            }

            var2.setTime(System.currentTimeMillis());
            var1.putString(Application.B("흈\ue61a滺抹ꨶ"), Application.B("흂\ue602滒抸ꨶ\ue5a0誄⓾\ue355\uf41a聓阹ꊯ絸"));
            return var2;
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("흫\ue60d滶抻ꨧ\ue5ad請ⓤ\ue379\uf452聓阴ꊹ絨슊뙞쟴蔕䣙錚⛑ư璟굁▖ꖐ㋤㻙夻作沣⤢\ufbcc볮鄤ᜳ亨\ueac8㳇ｬ०쳄甹呂傪\udcd1ۣᄪ\uf0b9灷몕ι冹鄢鐙ﾚͨ䋿岵꺋\ueca9թ")));
            return null;
        }
    }

    private void incrementOdometer(Location var1) {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        Location var2;
        synchronized(var2 = this.mLastOdometerLocation){}

        Throwable var61;
        boolean var62;
        boolean var63;
        try {
            var62 = var10000.hasLocation(var10001.mLastOdometerLocation);
        } catch (Throwable var59) {
            var61 = var59;
            var63 = false;
            throw var61;
        }

        if (!var62) {
            try {
                this.persistLastOdometerLocation(var1);
            } catch (Throwable var53) {
                var61 = var53;
                var63 = false;
                throw var61;
            }
        } else {
            float var64;
            try {
                var64 = var1.distanceTo(this.mLastOdometerLocation);
            } catch (Throwable var58) {
                var61 = var58;
                var63 = false;
                throw var61;
            }

            float var3 = var64;

            float var66;
            float var10002;
            try {
                var66 = var1.getAccuracy();
                var10002 = this.mLastOdometerLocation.getAccuracy();
            } catch (Throwable var57) {
                var61 = var57;
                var63 = false;
                throw var61;
            }

            if (var64 < (var66 + var10002) / 2.0F) {
                try {
                    ;
                } catch (Throwable var54) {
                    var61 = var54;
                    var63 = false;
                    throw var61;
                }
            } else {
                Location var65;
                Location var67;
                Float var10003;
                try {
                    var67 = var2;
                    var10001 = this;
                    var65 = var1;
                    var10003 = TSConfig.getInstance(this.mContext).incrementOdometer(var3);
                } catch (Throwable var56) {
                    var61 = var56;
                    var63 = false;
                    throw var61;
                }

                Float var60 = var10003;

                try {
                    TSLog.logger.debug(Application.B("蠌\ue46e瓅迗韼棳쪠ꁬ\u0878悄") + var60);
                    var10001.persistLastOdometerLocation(var65);
                } catch (Throwable var55) {
                    var61 = var55;
                    var63 = false;
                    throw var61;
                }
            }
        }
    }

    private void setLastLocation(Location var1) {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        synchronized(this.mLastLocation){}

        Throwable var152;
        boolean var153;
        boolean var154;
        try {
            var153 = var10000.hasLocation(var10001.mLastLocation);
        } catch (Throwable var149) {
            var152 = var149;
            var154 = false;
            throw var152;
        }

        if (var153) {
            long var155;
            long var156;
            try {
                var155 = TSLocation.getTime(var1);
                var156 = TSLocation.getTime(this.mLastLocation);
            } catch (Throwable var148) {
                var152 = var148;
                var154 = false;
                throw var152;
            }

            if (var155 < var156) {
                try {
                    return;
                } catch (Throwable var146) {
                    var152 = var146;
                    var154 = false;
                    throw var152;
                }
            }
        }

        Location var10002;
        Location var157;
        try {
            var157 = var1;
            var10001 = this;
            var10002 = var1;
        } catch (Throwable var147) {
            var152 = var147;
            var154 = false;
            throw var152;
        }

        float var2;
        var10001.calculateMedianAccuracy(var2 = var10002.getAccuracy());
        boolean var3 = false;
        Bundle var4;
        String var5;
        if ((var4 = var157.getExtras()).containsKey(Application.B("膠株믢㾓뵏")) && (var5 = var4.getString(Application.B("膠株믢㾓뵏"))) != null) {
            var3 = var5.equalsIgnoreCase(Application.B("膨栳믳㾔뵔廓৺㧌솥陁鉐᱄"));
        }

        if (!var4.containsKey(Application.B("膶栽믪㾍뵗切"))) {
            TSConfig var151;
            if ((var151 = TSConfig.getInstance(this.mContext)).isLocationTrackingMode() && var2 <= var151.getDesiredOdometerAccuracy()) {
                if (!var151.getIsMoving() && !var3) {
                    var10000 = this;
                    var10001 = this;
                    synchronized(this.mLastOdometerLocation){}

                    try {
                        var153 = var10000.hasLocation(var10001.mLastOdometerLocation);
                    } catch (Throwable var145) {
                        var152 = var145;
                        var154 = false;
                        throw var152;
                    }

                    if (!var153) {
                        try {
                            this.persistLastOdometerLocation(var1);
                        } catch (Throwable var144) {
                            var152 = var144;
                            var154 = false;
                            throw var152;
                        }
                    }

                    try {
                        ;
                    } catch (Throwable var143) {
                        var152 = var143;
                        var154 = false;
                        throw var152;
                    }
                } else {
                    this.incrementOdometer(var1);
                }
            }

            var10000 = this;
            var10001 = this;
            synchronized(this.mLastGoodLocation){}

            try {
                var153 = var10000.hasLocation(var10001.mLastGoodLocation);
            } catch (Throwable var142) {
                var152 = var142;
                var154 = false;
                throw var152;
            }

            label1403: {
                if (var153) {
                    float var158;
                    float var159;
                    try {
                        var159 = var2;
                        var158 = TSConfig.MAXIMUM_LOCATION_ACCURACY;
                    } catch (Throwable var141) {
                        var152 = var141;
                        var154 = false;
                        throw var152;
                    }

                    if (!(var159 <= var158)) {
                        break label1403;
                    }
                }

                try {
                    this.mLastGoodLocation.set(var1);
                } catch (Throwable var140) {
                    var152 = var140;
                    var154 = false;
                    throw var152;
                }
            }

            try {
                ;
            } catch (Throwable var139) {
                var152 = var139;
                var154 = false;
                throw var152;
            }
        }

        Location var150;
        var157 = var150 = this.mLastLocation;
        var10001 = this;
        synchronized(var150) {
            var10001.mLastLocation.set(var1);
        }
    }

    private void calculateMedianAccuracy(float var1) {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        synchronized(this.mAccuracyQueue){}

        Throwable var187;
        boolean var189;
        try {
            var10001.mAccuracyQueue.add(var1);
        } catch (Throwable var185) {
            var187 = var185;
            var189 = false;
            throw var187;
        }

        int var188;
        try {
            var188 = var10000.mAccuracyQueue.size();
        } catch (Throwable var184) {
            var187 = var184;
            var189 = false;
            throw var187;
        }

        if (var188 > 11) {
            try {
                this.mAccuracyQueue.remove(0);
            } catch (Throwable var183) {
                var187 = var183;
                var189 = false;
                throw var187;
            }
        }

        ArrayList var190;
        try {
            var190 = new ArrayList;
        } catch (Throwable var182) {
            var187 = var182;
            var189 = false;
            throw var187;
        }

        ArrayList var191 = var190;
        ArrayList var10002 = var190;
        ArrayList var186;
        ArrayList var10003 = var186 = var190;

        int var193;
        try {
            var10003.<init>(this.mAccuracyQueue);
            Collections.sort(var10002, new Comparator<Float>() {
                public int a(Float var1, Float var2) {
                    return (int)(var1 - var2);
                }
            });
            var193 = var191.size() / 2;
        } catch (Throwable var181) {
            var187 = var181;
            var189 = false;
            throw var187;
        }

        int var3 = var193;

        try {
            var188 = var190.size();
        } catch (Throwable var180) {
            var187 = var180;
            var189 = false;
            throw var187;
        }

        float var195;
        if (var188 == 1) {
            try {
                var10000 = this;
                var195 = (Float)var186.get(0);
            } catch (Throwable var179) {
                var187 = var179;
                var189 = false;
                throw var187;
            }
        } else {
            try {
                var188 = var186.size() % 2;
            } catch (Throwable var178) {
                var187 = var178;
                var189 = false;
                throw var187;
            }

            if (var188 > 0) {
                try {
                    var10000 = this;
                    var195 = (Float)var186.get(var3);
                } catch (Throwable var177) {
                    var187 = var177;
                    var189 = false;
                    throw var187;
                }
            } else {
                try {
                    var10000 = this;
                    var195 = (Float)var186.get(var3);
                } catch (Throwable var176) {
                    var187 = var176;
                    var189 = false;
                    throw var187;
                }

                var10002 = var186;
                int var194 = var3 - 1;

                float var192;
                try {
                    var192 = (Float)var10002.get(var194);
                } catch (Throwable var175) {
                    var187 = var175;
                    var189 = false;
                    throw var187;
                }

                var195 = (var195 + var192) / 2.0F;
            }
        }

        try {
            var10000.mMedianLocationAccuracy = var195;
        } catch (Throwable var174) {
            var187 = var174;
            var189 = false;
            throw var187;
        }

        try {
            TSLog.logger.debug(Application.B("ゐ毘鵧ꑶ鍢է捤\uf434撓聱뱟菴숹葨䳂\uf46d\udeac") + this.mMedianLocationAccuracy);
        } catch (Throwable var173) {
            var187 = var173;
            var189 = false;
            throw var187;
        }
    }

    private void stopSingleLocationRequests() {
        TSLocationManager var10000 = this;
        synchronized(this.locationRequests){}

        Throwable var33;
        Iterator var34;
        boolean var10001;
        try {
            var34 = var10000.locationRequests.entrySet().iterator();
        } catch (Throwable var32) {
            var33 = var32;
            var10001 = false;
            throw var33;
        }

        Iterator var1 = var34;

        while(true) {
            boolean var35;
            try {
                var35 = var1.hasNext();
            } catch (Throwable var29) {
                var33 = var29;
                var10001 = false;
                break;
            }

            if (!var35) {
                try {
                    return;
                } catch (Throwable var28) {
                    var33 = var28;
                    var10001 = false;
                    break;
                }
            }

            SingleLocationRequest var36;
            try {
                var34 = var1;
                var36 = (SingleLocationRequest)((Entry)var1.next()).getValue();
            } catch (Throwable var31) {
                var33 = var31;
                var10001 = false;
                break;
            }

            SingleLocationRequest var2 = var36;

            try {
                var36.finish();
                TSLog.logger.debug(TSLog.off(Application.B("،될Τᝯ㥖\uf254顖ꡗ蘵➕㎚ԍ䙍䞭᧫貿௷鄑⊽\uf166푣侫") + var2.getId()));
                var34.remove();
            } catch (Throwable var30) {
                var33 = var30;
                var10001 = false;
                break;
            }
        }

        throw var33;
    }

    private void removeLocationUpdates() {
        if (this.mIsUpdatingLocation.get()) {
            TSLog.logger.info(TSLog.off(Application.B("丘Ԡ뤀⚻鞢ꇬ捼㏢晌Ⅽ务⭅褾\uf1d4급⧁\ue3b5\uf425趁세纰⍤")));
        }

        this.mIsUpdatingLocation.set(false);
        LocationServices.getFusedLocationProviderClient(this.mContext).removeLocationUpdates(TrackingService.getPendingIntent(this.mContext));
    }

    private boolean locationSameAsLast(Location var1) {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        synchronized(this.mLastLocation){}

        Throwable var59;
        boolean var60;
        boolean var61;
        try {
            var60 = var10000.hasLocation(var10001.mLastLocation);
        } catch (Throwable var58) {
            var59 = var58;
            var61 = false;
            throw var59;
        }

        if (!var60) {
            var60 = false;

            try {
                return var60;
            } catch (Throwable var52) {
                var59 = var52;
                var61 = false;
                throw var59;
            }
        } else {
            long var62;
            long var63;
            try {
                var62 = this.mLastLocation.getTime();
                var63 = var1.getTime();
            } catch (Throwable var57) {
                var59 = var57;
                var61 = false;
                throw var59;
            }

            if (var62 == var63) {
                double var64;
                double var65;
                try {
                    var64 = this.mLastLocation.getLatitude();
                    var65 = var1.getLatitude();
                } catch (Throwable var56) {
                    var59 = var56;
                    var61 = false;
                    throw var59;
                }

                if (var64 == var65) {
                    try {
                        var64 = this.mLastLocation.getLongitude();
                        var65 = var1.getLongitude();
                    } catch (Throwable var55) {
                        var59 = var55;
                        var61 = false;
                        throw var59;
                    }

                    if (var64 == var65) {
                        var60 = true;

                        try {
                            return var60;
                        } catch (Throwable var53) {
                            var59 = var53;
                            var61 = false;
                            throw var59;
                        }
                    }
                }
            }

            Location var66;
            try {
                var66 = var1;
            } catch (Throwable var54) {
                var59 = var54;
                var61 = false;
                throw var59;
            }

            if (var66.getLatitude() == this.mLastLocation.getLatitude() && var1.getLongitude() == this.mLastLocation.getLongitude() && var1.getSpeed() == this.mLastLocation.getSpeed() && var1.getBearing() == this.mLastLocation.getBearing()) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean locationIsInvalid(Location var1) {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        synchronized(this.mLastLocation){}

        Throwable var387;
        boolean var388;
        boolean var389;
        try {
            var388 = var10000.hasLocation(var10001.mLastLocation);
        } catch (Throwable var386) {
            var387 = var386;
            var389 = false;
            throw var387;
        }

        if (!var388) {
            var388 = false;

            try {
                return var388;
            } catch (Throwable var368) {
                var387 = var368;
                var389 = false;
                throw var387;
            }
        } else {
            float var390;
            try {
                var10000 = this;
                var390 = speedBetween(var1, this.mLastLocation);
            } catch (Throwable var385) {
                var387 = var385;
                var389 = false;
                throw var387;
            }

            float var3 = var390;

            float var391;
            try {
                var391 = var10000.mLastLocation.distanceTo(var1);
            } catch (Throwable var384) {
                var387 = var384;
                var389 = false;
                throw var387;
            }

            float var4 = var391;

            Location var392;
            long var10002;
            try {
                var392 = var1;
                var10002 = elapsedTimeMillis(var1, this.mLastLocation);
            } catch (Throwable var383) {
                var387 = var383;
                var389 = false;
                throw var387;
            }

            float var5 = (float)var10002;

            try {
                var390 = var392.getAccuracy();
            } catch (Throwable var382) {
                var387 = var382;
                var389 = false;
                throw var387;
            }

            if (var391 < var390) {
                var388 = false;

                try {
                    return var388;
                } catch (Throwable var369) {
                    var387 = var369;
                    var389 = false;
                    throw var387;
                }
            } else {
                int var393;
                try {
                    var391 = var3;
                    TSLog.logger.debug(Application.B("揞芞驪۫\u2fda壘\uf376ﮁ\u0a5d㨍\uf22d䕼騤髯\uf2a5燇㱎秮\ue8ab拯☫균㱭㤨⚃㰬搙\uea56؋") + var4 + Application.B("掶苗驸ۯ⿋壗\uf367ﮁਓ㨟\uf27f䕠騹骪\uf2ac燂㰇禺") + var3);
                    var393 = TSConfig.getInstance(this.mContext).getSpeedJumpFilter();
                } catch (Throwable var381) {
                    var387 = var381;
                    var389 = false;
                    throw var387;
                }

                if (var391 > (float)var393) {
                    StringBuffer var394;
                    try {
                        var394 = new StringBuffer;
                    } catch (Throwable var379) {
                        var387 = var379;
                        var389 = false;
                        throw var387;
                    }

                    StringBuffer var6;
                    StringBuffer var395 = var6 = var394;

                    try {
                        var395.<init>();
                        var394.append(TSLog.warn(Application.B("揞芒驭ۺ\u2fd8壂\uf370ﮀ\u0a5d㨂\uf231䕥騨骣\uf2a0燂㰝秶\ue8e4拠☥귷㱥㤳⚄㱣摟\uea18َ\udcfd皜搡◣♨㶀⋈䛄쟃ṻꢃ뫍㍉춒痠ႆ뉙攳遾뙨껬딂屡尻羶\ud808ဍⲅ䚘롈ᤅ") + var3 + Application.B("掺芚驼۫\u2fde壄\uf366\ufbcb\u0a0e㩋\uf277䕷騠骼\uf2bd燇㱓秹\ue8ee抣☢귱㱣㤱⛊㰯搖\uea1fٟ\udcb1皕搾◯♻㶀⊈䚋쟚Ḩ꣗") + var4 + Application.B("掺芚驼۫\u2fde壄\uf366\ufbc8\u0a5d㨏\uf22b䔩驩") + var5 + Application.B("掳")));
                    } catch (Throwable var378) {
                        var387 = var378;
                        var389 = false;
                        throw var387;
                    }

                    int var396;
                    try {
                        var396 = VERSION.SDK_INT;
                    } catch (Throwable var377) {
                        var387 = var377;
                        var389 = false;
                        throw var387;
                    }

                    label2818: {
                        if (var396 >= 18) {
                            try {
                                var388 = var1.isFromMockProvider();
                            } catch (Throwable var376) {
                                var387 = var376;
                                var389 = false;
                                throw var387;
                            }

                            if (var388) {
                                break label2818;
                            }

                            try {
                                var388 = this.mLastLocation.isFromMockProvider();
                            } catch (Throwable var375) {
                                var387 = var375;
                                var389 = false;
                                throw var387;
                            }

                            if (var388) {
                                break label2818;
                            }
                        }

                        try {
                            TSLog.logger.warn(var6.toString());
                        } catch (Throwable var374) {
                            var387 = var374;
                            var389 = false;
                            throw var387;
                        }

                        var388 = true;

                        try {
                            return var388;
                        } catch (Throwable var373) {
                            var387 = var373;
                            var389 = false;
                            throw var387;
                        }
                    }

                    try {
                        var6.append(TSLog.warn(Application.B("插芘驮ۺ⿍壓\uf367\ufbc8\u0a5d㨇\uf230䕰騨骻\uf2a0燉㱓禺\ue8e6括☧귨㱥㤲⚍㱣搞\uea1f؋\udcf5皜搥◩♹㶀⊄䚀잚Ḳ꣗뫫㌆춁痽႗뉔攭遢똪꺸땖屺尢羠\ud84dစⳊ䚔롏ᥑ뚙\uf02b쮓쀪敼\ue295렉覡\uf81dᨐ鱳鮍풆⅒债⌀䖈\ufadf餉⊀\uda41䋧茅⋾尠ีѧჷ넜됉櫎톂閳祆ᆊ")));
                    } catch (Throwable var372) {
                        var387 = var372;
                        var389 = false;
                        throw var387;
                    }

                    try {
                        TSLog.logger.warn(var6.toString());
                    } catch (Throwable var371) {
                        var387 = var371;
                        var389 = false;
                        throw var387;
                    }

                    var388 = false;

                    try {
                        return var388;
                    } catch (Throwable var370) {
                        var387 = var370;
                        var389 = false;
                        throw var387;
                    }
                } else {
                    var388 = false;

                    try {
                        return var388;
                    } catch (Throwable var380) {
                        var387 = var380;
                        var389 = false;
                        throw var387;
                    }
                }
            }
        }
    }

    private void loadLastOdometerLocation() {
        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
            public void run() {
                SharedPreferences var1;
                if ((var1 = TSLocationManager.this.mContext.getSharedPreferences(TSLocationManager.class.getSimpleName(), 0)).contains(Application.B("\ued35\udd8c뇎侱뵒ꈚǛ㦳ϭ퇰ᠧ鏅쒨\uf69d\ud7a7扫퉉")) && var1.contains(Application.B("\ued35\udd8c뇎侱뵒ꈚǛ㦳ϭ퇰ᠩ鏟쒦\uf680\ud7a6扺퉈\ueea6"))) {
                    double var2;
                    double var10000 = var2 = Double.longBitsToDouble(var1.getLong(Application.B("\ued35\udd8c뇎侱뵒ꈚǛ㦳ϭ퇰ᠧ鏅쒨\uf69d\ud7a7扫퉉"), 0L));
                    double var4 = Double.longBitsToDouble(var1.getLong(Application.B("\ued35\udd8c뇎侱뵒ꈚǛ㦳ϭ퇰ᠩ鏟쒦\uf680\ud7a6扺퉈\ueea6"), 0L));
                    if (var10000 != 0.0D && var4 != 0.0D) {
                        Location var6;
                        Location var11 = var6 = new Location;
                        var6.<init>(Application.B("\ued0e\uddbb뇭侳뵔ꈏǊ㦨ϝ퇲᠋鏐쒯\uf688ힵ扪퉞"));
                        var6.setLatitude(var2);
                        var6.setLongitude(var4);
                        var6.setAccuracy(var1.getFloat(Application.B("\ued35\udd8c뇎侱뵒ꈚǛ㦳ϭ퇽ᠥ鏒쒴\uf69bힳ扬퉕"), 0.0F));
                        TSConfig var8 = TSConfig.getInstance(TSLocationManager.this.mContext);
                        Bundle var10;
                        if ((var10 = var11.getExtras()) == null) {
                            var10 = new Bundle.<init>();
                        }

                        var10.putFloat(Application.B("\ued35\udd8c뇎侱뵒ꈚǛ㦳"), var8.getOdometer());
                        var6.setExtras(var10);
                        TSLog.logger.debug(TSLog.info(Application.B("\ued16\udd87뇀侸봗ꈂǟ㦲φ톼ᠩ鏕쒮\uf684ힷ扻퉉\ueeb1貉㤬⑦芄ཝ뼙饔痩䒰鳹､") + var6));
                        Location var9;
                        var11 = var9 = TSLocationManager.this.mLastOdometerLocation;
                        <undefinedtype> var10001 = this;
                        synchronized(var9) {
                            TSLocationManager.this.mLastOdometerLocation.set(var6);
                        }
                    }
                }

            }
        });
    }

    private boolean hasLocation(Location var1) {
        return var1.getTime() != 0L;
    }

    private void persistLastOdometerLocation(Location var1) {
        if (TSConfig.getInstance(this.mContext).getEnabled()) {
            TSLocation.applyExtras(this.mContext, var1);
            Location var2;
            Location var10001 = var2 = this.mLastOdometerLocation;
            TSLocationManager var10002 = this;
            synchronized(var2) {
                var10002.mLastOdometerLocation.set(var1);
            }

            Editor var10000 = this.mContext.getSharedPreferences(TSLocationManager.class.getSimpleName(), 0).edit();
            long var5 = Double.doubleToRawLongBits(var1.getLatitude());
            long var6 = Double.doubleToRawLongBits(var1.getLongitude());
            var10000.putLong(Application.B("᧯玫Ꙉ殲긒攨Ԩꉚꆟ馩뫕贐뱪뒎놹檻뮇"), var5);
            var10000.putLong(Application.B("᧯玫Ꙉ殲긒攨Ԩꉚꆟ馩뫛贊뱤뒓놸檪뮆뢪"), var6);
            var10000.putFloat(Application.B("᧯玫Ꙉ殲긒攨Ԩꉚꆟ馤뫗贇뱶뒈놭檼뮛"), var1.getAccuracy());
            var10000.apply();
        }
    }

    private void clearLastOdometerLocation() {
        TSLog.logger.debug(TSLog.info(Application.B("톥\uf488볓뫘衰璨踑җ論靊뗚簭\ue2e9篴贓兊ﲨᲁ悟螱煢늺斃\uf6bf흠？羜皠")));
        Location var1;
        Location var10001 = var1 = this.mLastOdometerLocation;
        TSLocationManager var10002 = this;
        synchronized(var1) {
            var10002.mLastOdometerLocation.reset();
        }

        Editor var10000 = this.mContext.getSharedPreferences(TSLocationManager.class.getSimpleName(), 0).edit();
        var10000.remove(Application.B("톉\uf480볙뫔衧瓼踘҄諺青떛簶\ue2e4篯贋克ﲹ"));
        var10000.remove(Application.B("톉\uf480볙뫔衧瓼踘҄諺青떕簬\ue2ea篲贊党ﲸᲁ"));
        var10000.remove(Application.B("톉\uf480볙뫔衧瓼踘҄諺靟떙簡\ue2f8篩负兌ﲥ"));
        var10000.apply();
    }

    @TargetApi(17)
    private void sendLocationErrorEvent(Integer var1) {
        TSLog.logger.warn(TSLog.warn(Application.B("羡鈯疃\uf47cﺿ疆៵䵼䋬苌浪\uf24c웄\uebd8\u125e鴬") + var1));
        new LocationErrorEvent(var1);
    }

    public void stop() {
        this.stopUpdatingLocation();
        this.clearLastOdometerLocation();
    }

    public void destroy() {
        this.stopUpdatingLocation();
        this.stopWatchPosition();
        this.stopSingleLocationRequests();
        EventBus var1;
        if ((var1 = EventBus.getDefault()).isRegistered(this)) {
            var1.unregister(this);
        }

    }

    public void getCurrentPosition(SingleLocationRequest var1) {
        this.register(var1);
        var1.start();
    }

    public void flush() {
        TSLocationManager var10000 = this;
        synchronized(this.locationRequests){}

        boolean var10001;
        Throwable var22;
        Iterator var23;
        try {
            var23 = var10000.locationRequests.entrySet().iterator();
        } catch (Throwable var21) {
            var22 = var21;
            var10001 = false;
            throw var22;
        }

        Iterator var1 = var23;

        while(true) {
            boolean var24;
            try {
                var24 = var1.hasNext();
            } catch (Throwable var19) {
                var22 = var19;
                var10001 = false;
                break;
            }

            if (!var24) {
                try {
                    return;
                } catch (Throwable var18) {
                    var22 = var18;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((SingleLocationRequest)((Entry)var1.next()).getValue()).start();
            } catch (Throwable var20) {
                var22 = var20;
                var10001 = false;
                break;
            }
        }

        throw var22;
    }

    public void setOdometer(Float var1, final TSLocationCallback var2) {
        TSConfig var3;
        (var3 = TSConfig.getInstance(this.mContext)).setOdometer(var1);
        TSLog.logger.info(TSLog.info(Application.B("Ƨᙖ拀ⴽ溝靦ᧁ铱㒐ዖꔩ価ᔝ") + var1 + Application.B("Ǹᘓ拝ⴁ溴靦᧚铽㒊ዔꕡ侻") + var3.getIsMoving()));
        Location var77 = this.mLastOdometerLocation;
        TSConfig var10000 = var3;
        synchronized(var77){}

        Throwable var79;
        boolean var80;
        boolean var10001;
        try {
            var80 = var10000.getIsMoving();
        } catch (Throwable var76) {
            var79 = var76;
            var10001 = false;
            throw var79;
        }

        label541: {
            if (!var80) {
                try {
                    var80 = this.hasLocation(this.mLastOdometerLocation);
                } catch (Throwable var75) {
                    var79 = var75;
                    var10001 = false;
                    throw var79;
                }

                if (var80) {
                    Location var83;
                    try {
                        var83 = new Location;
                    } catch (Throwable var74) {
                        var79 = var74;
                        var10001 = false;
                        throw var79;
                    }

                    Location var78 = var83;

                    TSLocation var84;
                    try {
                        var78.<init>(Application.B("ƀᙠ拸ⴝ溚靨᧘铽㒋ዝꔖ俺ᕓ\uec4a萩\udf79ࣝ"));
                        var83.set(this.mLastOdometerLocation);
                        var84 = new TSLocation;
                    } catch (Throwable var73) {
                        var79 = var73;
                        var10001 = false;
                        throw var79;
                    }

                    final TSLocation var4 = var84;

                    try {
                        var84.<init>(this.mContext, var78, ActivityRecognitionService.getMostProbableActivity());
                        BackgroundGeolocation.getUiHandler().post(new Runnable() {
                            public void run() {
                                var2.onLocation(var4);
                            }
                        });
                        break label541;
                    } catch (Throwable var72) {
                        var79 = var72;
                        var10001 = false;
                        throw var79;
                    }
                }
            }

            TSLocationManager var81;
            Builder var82;
            try {
                var81 = this;
                this.clearLastOdometerLocation();
                var82 = new Builder;
            } catch (Throwable var71) {
                var79 = var71;
                var10001 = false;
                throw var79;
            }

            Builder var10002 = var82;

            try {
                var10002.<init>(this.mContext);
                var81.getCurrentPosition(((Builder)((Builder)((Builder)((Builder)var82.setCallback(var2)).setPersist(false)).setDesiredAccuracy(var3.getDesiredOdometerAccuracy().intValue())).setSamples(3)).build());
            } catch (Throwable var70) {
                var79 = var70;
                var10001 = false;
                throw var79;
            }
        }

        try {
            ;
        } catch (Throwable var69) {
            var79 = var69;
            var10001 = false;
            throw var79;
        }
    }

    public void watchPosition(TSWatchPositionRequest var1) {
        TSLog.logger.info(TSLog.on(Application.B("퐀抦\uda69ࠬ⚊㰻ꥵ㟉큍\u0ff1뺌ꞧ⼑槒\ue5c2⚑ꞡ")));
        TSWatchPositionRequest var2;
        if (this.mIsWatchingPosition.get() && (var2 = this.mWatchPositionRequest) != null) {
            var2.stop();
        }

        this.mIsWatchingPosition.set(true);
        this.mWatchPositionRequest = var1;
        var1.start();
    }

    public void stopWatchPosition() {
        if (this.mIsWatchingPosition.compareAndSet(true, false)) {
            TSLog.logger.info(TSLog.off(Application.B("텓䡠颬蘰桐㩡朑\uf279靻騔㭁㫄\u2bbb橳㪶\ue033⍽ꗛ")));
        }

        TSWatchPositionRequest var1;
        if ((var1 = this.mWatchPositionRequest) != null) {
            var1.stop();
        }

    }

    public void stopUpdatingLocation() {
        this.removeLocationUpdates();
    }

    public Location getLastGoodLocation() {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        synchronized(this.mLastGoodLocation){}

        Throwable var23;
        boolean var24;
        boolean var25;
        try {
            var24 = var10000.hasLocation(var10001.mLastGoodLocation);
        } catch (Throwable var22) {
            var23 = var22;
            var25 = false;
            throw var23;
        }

        if (var24) {
            Location var26;
            try {
                var26 = new Location;
            } catch (Throwable var20) {
                var23 = var20;
                var25 = false;
                throw var23;
            }

            Location var2 = var26;

            try {
                var2.<init>(Application.B("䇚ᘍ蠟܀螹ꓣꑓ侅\uf556䡉蠟ㄩ牥댻뽣\uf29cꝫ"));
                var2.set(this.mLastGoodLocation);
                return var26;
            } catch (Throwable var19) {
                var23 = var19;
                var25 = false;
                throw var23;
            }
        } else {
            try {
                return this.getLastLocation();
            } catch (Throwable var21) {
                var23 = var21;
                var25 = false;
                throw var23;
            }
        }
    }

    public Location getLastOdometerLocation() {
        TSLocationManager var10000 = this;
        TSLocationManager var10001 = this;
        synchronized(this.mLastOdometerLocation){}

        Throwable var23;
        boolean var24;
        boolean var25;
        try {
            var24 = var10000.hasLocation(var10001.mLastOdometerLocation);
        } catch (Throwable var22) {
            var23 = var22;
            var25 = false;
            throw var23;
        }

        if (!var24) {
            var10000 = null;

            try {
                return var10000;
            } catch (Throwable var19) {
                var23 = var19;
                var25 = false;
                throw var23;
            }
        } else {
            Location var26;
            try {
                var26 = new Location;
            } catch (Throwable var21) {
                var23 = var21;
                var25 = false;
                throw var23;
            }

            Location var2 = var26;

            try {
                var2.<init>(Application.B("\udb43\uf796袗펥邊㫩굇芧┕꣮\uf1d2왜撀凲⁺䢂꽽"));
                var2.set(this.mLastOdometerLocation);
                return var26;
            } catch (Throwable var20) {
                var23 = var20;
                var25 = false;
                throw var23;
            }
        }
    }

    public Location getLastLocation() {
        // $FF: Couldn't be decompiled
    }

    public void cancelRequest(SingleLocationRequest var1) {
        if (var1 != null) {
            TSLocationManager var10000 = this;
            synchronized(this.locationRequests){}

            boolean var16;
            boolean var10001;
            Throwable var15;
            try {
                var16 = var10000.locationRequests.containsKey(var1.getId());
            } catch (Throwable var14) {
                var15 = var14;
                var10001 = false;
                throw var15;
            }

            if (var16) {
                try {
                    var1.finish();
                    var1.onError(499);
                    this.locationRequests.remove(var1.getId());
                } catch (Throwable var13) {
                    var15 = var13;
                    var10001 = false;
                    throw var15;
                }
            }

            try {
                ;
            } catch (Throwable var12) {
                var15 = var12;
                var10001 = false;
                throw var15;
            }
        }
    }

    public void cancelRequest(int var1) {
        SingleLocationRequest var2;
        if ((var2 = this.getRequest(var1)) != null) {
            this.cancelRequest(var2);
        }

    }

    public SingleLocationRequest getRequest(int var1) {
        TSLocationManager var10000 = this;
        synchronized(this.locationRequests) {
            return (SingleLocationRequest)var10000.locationRequests.get(var1);
        }
    }

    public void onProviderChange(LocationProviderChangeEvent var1) {
        this.mCurrentLocationProvider = var1;
        final TSConfig var2;
        if ((var2 = TSConfig.getInstance(this.mContext)).getEnabled()) {
            TSGeofenceManager var3 = TSGeofenceManager.getInstance(this.mContext);
            if (var1.isEnabled()) {
                if (!var3.isMonitoringStationaryRegion() && !this.isUpdatingLocation()) {
                    TrackingService.changePace(this.mContext, var2.getIsMoving(), (TSLocationCallback)null);
                }
            } else {
                var3.stopMonitoringStationaryRegion();
            }

            TSProviderChangeRequest var10;
            Location var11;
            TSLocationManager var10000;
            if (var1.isEnabled() && !var1.isAirplaneMode()) {
                if (!var1.isPermissionGranted()) {
                    TSLog.logger.warn(TSLog.warn(Application.B("᮹雯鼥ঔơ鈸Ꜹ￼鸠폱\ue2fd坺\ue720퇄禒뷥呂邒潑\u0dbc현㻌쯶숃푎პ\ue914问⪔ݔ龩㲶\uefce䥅㚣粰\ue32f䃞䫗춋\ue1df\uf5a8ᴗ瞡瞾谒蓜䦂鈺ﲵ궝씔돗⠍둲봊ᄋꀵ욠柔毲ｻ澢ᑭ쵵겫ፓ୵欆㸪⽵᠆\uf04c덏ꋣ兩ㅋ") + this.mLastLocation) + Application.B("᯿"));
                } else {
                    TSProviderChangeRequest var12;
                    if ((var12 = this.mProviderChangeRequest) != null) {
                        this.cancelRequest(var12);
                    }

                    var10000 = this;
                    Location var10002 = var11 = this.mLastLocation;
                    synchronized(var10002){}

                    Throwable var14;
                    boolean var15;
                    Location var10003;
                    try {
                        var10003 = new Location;
                    } catch (Throwable var9) {
                        var14 = var9;
                        var15 = false;
                        throw var14;
                    }

                    final Location var13 = var10003;

                    try {
                        var10003.<init>(this.mLastLocation);
                    } catch (Throwable var8) {
                        var14 = var8;
                        var15 = false;
                        throw var14;
                    }

                    this.mProviderChangeRequest = var10 = ((com.transistorsoft.locationmanager.location.TSProviderChangeRequest.Builder)((com.transistorsoft.locationmanager.location.TSProviderChangeRequest.Builder)(new com.transistorsoft.locationmanager.location.TSProviderChangeRequest.Builder(this.mContext)).setSamples(3)).setCallback(new TSLocationCallback() {
                        public void onLocation(TSLocation var1) {
                            Location var3;
                            Location var10000 = var3 = var1.getLocation();
                            float var2x = var10000.distanceTo(var13);
                            if (var10000.hasAccuracy()) {
                                var2x += var3.getAccuracy();
                            }

                            if (var13.hasAccuracy()) {
                                var2x += var13.getAccuracy();
                            }

                            TSLog.logger.debug(TSLog.info(Application.B("槠喊멶ₘ䩯\uabee條棲䥒ᰵ㞼\uebf1⭼환ศ兦⌥屁\udbe7礢㭨灠탪噤旁\udc2e銫㊵㣬") + var2x));
                            if (!(var2x < 200.0F)) {
                                if (!var2.getIsMoving()) {
                                    TSGeofenceManager.getInstance(TSLocationManager.this.mContext).startMonitoringStationaryRegion(var3);
                                } else if (var2.isLocationTrackingMode()) {
                                    TSLocationManager.this.requestLocationUpdates();
                                } else {
                                    TSGeofenceManager.getInstance(TSLocationManager.this.mContext).startMonitoringSignificantLocationChanges();
                                }

                            }
                        }

                        public void onError(Integer var1) {
                            if (TSLocationManager.this.getLastLocation() != null) {
                                <undefinedtype> var10000 = this;
                                Location var2x = TSLocationManager.buildLocation(TSLocationManager.this.mLastLocation);
                                TSLocationManager var3;
                                (var3 = TSLocationManager.this).onSingleLocationResult(new SingleLocationResult(var3.mProviderChangeRequest.getId(), var2x));
                            }
                        }
                    })).build();
                    var10000.getCurrentPosition(var10);
                }
            } else {
                if (this.getLastLocation() == null) {
                    return;
                }

                var10000 = this;
                TSLocationManager var10001 = this;
                this.register(var10 = ((com.transistorsoft.locationmanager.location.TSProviderChangeRequest.Builder)(new com.transistorsoft.locationmanager.location.TSProviderChangeRequest.Builder(this.mContext)).setSamples(0)).build());
                var11 = buildLocation(var10001.mLastLocation);
                var10000.onSingleLocationResult(new SingleLocationResult(var10.getId(), var11));
            }

        }
    }

    public synchronized void onLocationResult(LocationResult var1) {
        TSLog.logger.debug(TSLog.header(Application.B("撪\uf643茔廸㐻ೡω֞歘邘ᶪ\ue2d4魼\u0ff4듰\ue719鏴㔶\u088c\ue635\uddb8Ⴖ")));
        TSConfig var2;
        TSLocationManager var122;
        boolean var124;
        if ((var2 = TSConfig.getInstance(this.mContext)).isLocationTrackingMode() && !var2.getUseSignificantChangesOnly() && !var2.getDisableElasticity() && var2.getDistanceFilter() > 0.0F) {
            List var10001 = var1.getLocations();
            Location var3 = (Location)var10001.get(var10001.size() - 1);
            if (this.mIsUpdatingLocation.get() && var3.hasSpeed() && !Float.isNaN(var3.getSpeed()) && var3.getAccuracy() <= TSConfig.MAXIMUM_LOCATION_ACCURACY) {
                float var117;
                float var10000 = var117 = var2.calculateDistanceFilter(var3.getSpeed());
                TSLocationManager var123 = this;
                synchronized(this.mLocationRequest){}

                Throwable var121;
                float var125;
                try {
                    var125 = var123.mLocationRequest.getSmallestDisplacement();
                } catch (Throwable var114) {
                    var121 = var114;
                    var124 = false;
                    throw var121;
                }

                if (var10000 != var125) {
                    try {
                        var122 = this;
                        TSLog.logger.info(TSLog.notice(Application.B("撨\uf654荖廨㐽\u0cf3ϖכ歰郗ᶭ\ue2dc魻\u0fe9듾\ue719鏅㔶ࢹ\ue629\uddb8Ⴖ斊⚢挆⑾") + this.mLocationRequest.getSmallestDisplacement() + Application.B("擗\uf60f") + var117 + Application.B("擓")));
                        this.mLocationRequest.setSmallestDisplacement(var117);
                    } catch (Throwable var113) {
                        var121 = var113;
                        var124 = false;
                        throw var121;
                    }

                    try {
                        var122.updateLocationRequest();
                    } catch (Throwable var112) {
                        var121 = var112;
                        var124 = false;
                        throw var121;
                    }
                }

                try {
                    ;
                } catch (Throwable var111) {
                    var121 = var111;
                    var124 = false;
                    throw var121;
                }
            }
        }

        if (this.mContext == null) {
            TSLog.logger.error(TSLog.warn(Application.B("撴\uf644茗廷㑾\u0cd1ϕא歠邒ᶱ\ue2c1鬨\u0ff4등\ue757鏵㔺\u0891\ue627\uddb8Ⴇ斣⚿损\u243fኽ꡷ꍝ睔饚黿햦䈳‐쒷戇Ⓨ♒讫珤䅣")));
        } else {
            NullPointerException var126;
            label964: {
                ArrayList var129;
                List var127;
                try {
                    var127 = var1.getLocations();
                    var129 = new ArrayList;
                } catch (NullPointerException var110) {
                    var126 = var110;
                    var124 = false;
                    break label964;
                }

                ArrayList var116 = var129;

                Iterator var128;
                try {
                    var129.<init>();
                    var128 = var127.iterator();
                } catch (NullPointerException var109) {
                    var126 = var109;
                    var124 = false;
                    break label964;
                }

                Iterator var118 = var128;

                while(true) {
                    boolean var130;
                    try {
                        var130 = var118.hasNext();
                    } catch (NullPointerException var98) {
                        var126 = var98;
                        var124 = false;
                        break;
                    }

                    TSConfig var133;
                    if (!var130) {
                        try {
                            var130 = var116.isEmpty();
                        } catch (NullPointerException var97) {
                            var126 = var97;
                            var124 = false;
                            break;
                        }

                        if (var130) {
                            return;
                        }

                        ExecutorService var134;
                        try {
                            var133 = var2;
                            TSMediaPlayer.getInstance().debug(this.mContext, Application.B("撎\uf642茗廴㐽\u0cf3ώח死邙ᶤ\ue2d4魦\u0ffc듸\ue712鏔㔌\u0890\ue62f\uddbbႭ斆⚹捕⑭ኖ\ua878ꍇ睖饤黅햣䈩‐"));
                            var134 = BackgroundGeolocation.getThreadPool();
                        } catch (NullPointerException var96) {
                            var126 = var96;
                            var124 = false;
                            break;
                        }

                        ExecutorService var119 = var134;

                        try {
                            var130 = var133.getPersist();
                        } catch (NullPointerException var95) {
                            var126 = var95;
                            var124 = false;
                            break;
                        }

                        if (!var130) {
                            return;
                        }

                        try {
                            var130 = var2.isLocationTrackingMode();
                        } catch (NullPointerException var94) {
                            var126 = var94;
                            var124 = false;
                            break;
                        }

                        if (!var130) {
                            return;
                        }

                        try {
                            var119.execute(new TSLocationManager.f(this.mContext, var116));
                            return;
                        } catch (NullPointerException var93) {
                            var126 = var93;
                            var124 = false;
                            break;
                        }
                    }

                    Location var131;
                    try {
                        var122 = this;
                        var131 = (Location)var118.next();
                    } catch (NullPointerException var108) {
                        var126 = var108;
                        var124 = false;
                        break;
                    }

                    Location var4 = var131;

                    try {
                        var130 = var122.locationSameAsLast(var131);
                    } catch (NullPointerException var107) {
                        var126 = var107;
                        var124 = false;
                        break;
                    }

                    if (var130) {
                        try {
                            var130 = var2.getAllowIdenticalLocations();
                        } catch (NullPointerException var106) {
                            var126 = var106;
                            var124 = false;
                            break;
                        }

                        if (!var130) {
                            try {
                                TSLog.logger.debug(TSLog.info(Application.B("撳\uf676茵廔㐌\u0cd7Ͼք欴還ᶨ\ue2d8魭\u0fbd듾\ue704鎆㔿\u089e\ue633\udda0ტ斃⚿损\u243fኽ꡷ꍝ睔")));
                                continue;
                            } catch (NullPointerException var105) {
                                var126 = var105;
                                var124 = false;
                                break;
                            }
                        }

                        try {
                            TSLog.logger.debug(TSLog.info(Application.B("撩\uf650茖廾㑾\u0cf3ω֞歸邖ᶺ\ue2c1鬨\u0ff1듰\ue714鏇㔧\u0896\ue62f\uddba")));
                        } catch (NullPointerException var103) {
                            var126 = var103;
                            var124 = false;
                            break;
                        }
                    } else {
                        try {
                            var130 = this.locationIsInvalid(var4);
                        } catch (NullPointerException var104) {
                            var126 = var104;
                            var124 = false;
                            break;
                        }

                        if (var130) {
                            continue;
                        }
                    }

                    TSLocation var132;
                    try {
                        var133 = var2;
                        var132 = this.buildTSLocation(var4);
                    } catch (NullPointerException var102) {
                        var126 = var102;
                        var124 = false;
                        break;
                    }

                    TSLocation var120 = var132;

                    try {
                        var130 = var133.shouldPersist(var132);
                    } catch (NullPointerException var101) {
                        var126 = var101;
                        var124 = false;
                        break;
                    }

                    if (var130) {
                        try {
                            var116.add(var120);
                        } catch (NullPointerException var100) {
                            var126 = var100;
                            var124 = false;
                            break;
                        }
                    }

                    try {
                        EventBus.getDefault().post(var120);
                    } catch (NullPointerException var99) {
                        var126 = var99;
                        var124 = false;
                        break;
                    }
                }
            }

            NullPointerException var115 = var126;
            TSLog.logger.error(TSLog.error(var115.getMessage()), var115);
            var115.printStackTrace();
        }
    }

    @Subscribe(
            threadMode = ThreadMode.BACKGROUND
    )
    public synchronized void onWatchPositionResult(WatchPositionResult var1) {
        TSMediaPlayer.getInstance().debug(this.mContext, Application.B("ḁ\u0fe1\ue101䓫웯\uf040騹戢佽㧻쾼ꈿ睔쐫㹗扼竡້膹笋␛芣ෂ㥢ﱊ\uf230\uefcf賛몾㌑㺓\ue051ᇏ萔㪽"));
        if (!this.mIsWatchingPosition.get()) {
            TSWatchPositionRequest var2;
            TSWatchPositionRequest var10001 = var2 = (new com.transistorsoft.locationmanager.location.TSWatchPositionRequest.Builder(this.mContext)).build();
            this.mWatchPositionRequest = var2;
            var10001.setId(var1.getRequestId());
            this.stopWatchPosition();
            TSLog.logger.warn(TSLog.warn(Application.B("Ḛ\u0ffc\ue13a䓥웸\uf042騥戛佽㧦쾸ꈪ睓쐥㹞手競\u0ee5膣笈␀苶උ㤫ﱔ\uf262\uefe4賞몣㌭㺐\ue07dᇐ萏㪸협\ue66b㌩욤\uf42aㆉŤ⢞Ꞛ垙\uee50忰娼پ끔\uf484壭")));
        } else {
            TSLocation var3 = this.buildTSLocation(var1.getLocation());
            if (this.mWatchPositionRequest.hasExtras()) {
                var3.setExtras(this.mWatchPositionRequest.getExtras());
            }

            if (this.mWatchPositionRequest.getPersist()) {
                BackgroundGeolocation.getThreadPool().execute(new TSLocationManager.f(this.mContext, var3));
            }

            this.mWatchPositionRequest.onSuccess(var3);
            EventBus.getDefault().post(var3);
        }
    }

    public synchronized void onSingleLocationResult(SingleLocationResult var1) {
        TSLocationManager var10000 = this;
        synchronized(this.locationRequests){}

        Throwable var33;
        SingleLocationRequest var34;
        boolean var10001;
        try {
            var34 = (SingleLocationRequest)var10000.locationRequests.get(var1.getRequestId());
        } catch (Throwable var25) {
            var33 = var25;
            var10001 = false;
            throw var33;
        }

        final SingleLocationRequest var3 = var34;

        try {
            ;
        } catch (Throwable var24) {
            var33 = var24;
            var10001 = false;
            throw var33;
        }

        if (var34 == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("鐣\udae5깤㊢딣僤淌肧ෲ\uf282\ue691轢ṛԏﶜ캮塪᭵ṁ䤲嗰֛\u173f䉕ゐ鵖ྐྵ酋扠㳉⣀폥ᒳ詗⬫饔")));
        } else {
            long var4 = locationAge(var1.getLocation());
            if (!var3.isComplete() && var3.hasSample() && var4 > 10000L) {
                TSLog.logger.info(TSLog.warn(Application.B("鑙\udacd깊㊀딉僒涩肗ඣ\uf282\ue6df麗ṚԈ\ufddd캉塪᭴Ṉ䥳嗴ְ᜵䈌パ") + var4 + Application.B("鑅\udae9깾㋧")));
            } else {
                TSConfig var2 = TSConfig.getInstance(this.mContext);
                var3.addLocation(var1.getLocation());
                TSGeofenceManager.getInstance(this.mContext).setLocation(var1.getLocation(), var2.getIsMoving());
                if (var3.mAction == 1) {
                    label592: {
                        if (var3.getElapsed() <= 10000L) {
                            long var35 = var4;
                            Location var29 = var3.getBestLocation();
                            if (var35 >= 5000L || !(var29.getAccuracy() <= 25.0F)) {
                                break label592;
                            }
                        }

                        var3.finish();
                    }
                }

                if (var3.isComplete()) {
                    var3.finish();
                    Map var30;
                    Map var10002 = var30 = this.locationRequests;
                    TSLocationManager var10003 = this;
                    synchronized(var30){}

                    try {
                        var10003.locationRequests.remove(var1.getRequestId());
                    } catch (Throwable var23) {
                        var33 = var23;
                        var10001 = false;
                        throw var33;
                    }

                    try {
                        ;
                    } catch (Throwable var22) {
                        var33 = var22;
                        var10001 = false;
                        throw var33;
                    }

                    Location var27;
                    Bundle var31 = (var27 = var3.getBestLocation()).getExtras();
                    int var5;
                    if ((var5 = var3.mAction) == 1) {
                        var31.putString(Application.B("鐀\udaf2깨㊠딲"), Application.B("鐈\udaeb깹㊧딩僮涏肻\u0dfc\uf2cc\ue690女"));
                        TSLog.logger.info(TSLog.notice(Application.B("鐤\udae7깼㊻딯僲涉肷ල\uf2cf\ue698戀ṜԄ\ufdd2캞填᭺Ṉ䤹嗰\u05f7ᜠ䉙も鵋ྤ配扡㳵⢉펴ᒯ詁⬕饏㫮ԁ쎠䵴굿錨") + var2.getIsMoving()));
                    } else if (var5 == 2) {
                        TSMediaPlayer.getInstance().debug(this.mContext, Application.B("鐑\udaf7깡㊡딥僡涘肺ෲ\uf2cc\ue69a礪ṛԊ\ufddb캘塱᭄ṉ䤱嗺ָ\u1739䉟\u3098鴑ྏ酂扻㳷⣉폋ᒰ詝⬴"));
                        TSLog.logger.info(TSLog.notice(Application.B("鐤\udae7깼㊻딯僲涉肷ල\uf2c1\ue682練ṇԎ\ufdd2캉堣᭫ṉ䤭嗼֣\u1739䉙ゟ")));
                    } else if (var5 == 3) {
                        TSMediaPlayer.getInstance().debug(this.mContext, Application.B("鐑\udaf7깡㊡딥僡涘肺ෲ\uf2cc\ue69a礪ṛԊ\ufddb캘塱᭄ṉ䤱嗺ָ\u1739䉟\u3098鴑ྏ酂扻㳷⣉폋ᒰ詝⬴"));
                        TSLog.logger.info(TSLog.notice(Application.B("鐤\udae7깼㊻딯僲涉肷ල\uf2d2\ue685曆ṃԂ\ufdd8캘塱᭸Ṏ䤿嗻ְ᜵䈖め鵍ྣ配扺㳲⣊폺")));
                        var31.putString(Application.B("鐀\udaf2깨㊠딲"), Application.B("鐕\udaf6깢㊸딯僤涉股\u0dfe\uf2ca\ue696力ṒԎ"));
                    } else if (var5 == 4) {
                        var31.putBoolean(Application.B("鐕\udae1깿㊽딯僳涘"), false);
                    }

                    final TSLocation var28 = this.buildTSLocation(var27);
                    if (var3.hasExtras()) {
                        var28.setExtras(var3.getExtras());
                    }

                    EventBus.getDefault().post(var28);
                    if (var3.mAction == 1) {
                        EventBus.getDefault().post(new MotionChangeEvent(var28));
                        if (var2.getIsMoving()) {
                            TSMediaPlayer.getInstance().debug(this.mContext, Application.B("鐑\udaf7깡㊡딥僡涘肺ෲ\uf2cc\ue69a礪ṛԊ\ufddb캘塱᭄ṅ䤶嗼ֺ᜵䉩も鵊྿酖扺㳄⣆폼ᒩ詀⬼饿㫭Ԙ"));
                            if (var2.isLocationTrackingMode()) {
                                this.requestLocationUpdates();
                            }
                        } else {
                            TSMediaPlayer.getInstance().debug(this.mContext, Application.B("鐑\udaf7깡㊡딥僡涘肺ෲ\uf2cc\ue69a礪ṛԊ\ufddb캘塱᭄ṋ䤿嗧־\u173d䉔ゐ鵽ྴ酖扡㳫"));
                            if (var2.isLocationTrackingMode()) {
                                this.removeLocationUpdates();
                            }
                        }
                    }

                    BackgroundGeolocation.getUiHandler().post(new Runnable() {
                        public void run() {
                            var3.onSuccess(var28);
                        }
                    });
                    if (var3.getPersist()) {
                        boolean var32;
                        if (var3.mAction == 1) {
                            var32 = true;
                        } else {
                            var32 = false;
                        }

                        if (var3.mAction != 2 && !var2.shouldPersist(var28)) {
                            return;
                        }

                        BackgroundGeolocation.getThreadPool().execute(new TSLocationManager.f(this.mContext, var28, var32));
                    }
                } else {
                    TSMediaPlayer.getInstance().debug(this.mContext, Application.B("鐑\udaf7깡㊡딥僡涘肺ෲ\uf2cc\ue69a礪ṛԊ\ufddb캘塱᭄ṅ䤲嗼ִ\u173b䉩ゅ鵃ྠ酻扪㳴⣋폱ᒙ詑⬰饅㫻ԃ쎬䵼괽錽詈㽻沧䑐ڸ뷏凼粦⎞"));
                    TSLocation var26 = this.buildTSLocation(var1.getLocation());
                    if (var3.hasExtras()) {
                        var26.setExtras(var3.getExtras());
                    }

                    EventBus.getDefault().post(var26);
                }

            }
        }
    }

    @Subscribe(
            threadMode = ThreadMode.BACKGROUND
    )
    public void onLocationTimeout(SingleLocationRequest var1) {
        TSLog.logger.warn(TSLog.warn(Application.B("聕\uf353覜〹\ueaf1\ud8ac\u181d좂甮䱄뮦ʱᜯ䏞8ҥண籊雸\u089e짅鲁햙╉\ud967梉襜㇐\u10cbΞ૭櫘쏪꜡ꬫ愼氇禘內莎賚뼲╕")));
        TSConfig var2 = TSConfig.getInstance(this.mContext);
        EventBus.getDefault().post(new LocationErrorEvent(408));
        Location var4;
        if (var1.getAction() == 1 && !var2.getIsMoving() && (var4 = this.forceAcquireStationaryLocation()) != null) {
            TSLocationManager var10000 = this;
            Location var3;
            var1.addLocation(var3 = buildLocation(var4));
            var1.setSamples(0);
            var10000.onSingleLocationResult(new SingleLocationResult(var1.getId(), var3));
        } else {
            this.cancelRequest(var1);
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onConfigChange(ConfigChangeEvent var1) {
        if (this.mIsUpdatingLocation.get()) {
            if (var1.isDirty(Application.B("\uf40d\ue7d5꣐憛⓻▆峥ᶎ釄콷\u09d5ᒡﬧ䴫덚")) || var1.isDirty(Application.B("\uf40d\ue7d9꣐憆ⓨ▍峢ᶪ釡콽ৌᒧﬣ䴺")) || var1.isDirty(Application.B("\uf405\ue7dfꣀ憓⓽▊峮ᶡ釲콤ৄᒲגּ䴭덪ᆾ꾡ꪑ传ꎓ墳宆")) || var1.isDirty(Application.B("\uf40f\ue7d1꣐憆⓬▐峵ᶃ釈콷ুᒧאָ䴧덍ᆅ꾥ꪐ伳ꎑ啕宣␑棏匒⑈琖旼µ")) || var1.isDirty(Application.B("\uf40d\ue7d5ꣅ憗⓻▷峨ᶢ釂")) || var1.isDirty(Application.B("\uf40d\ue7d9꣐憓⓫▏峤ᶊ釋콵\u09d3ᒧאָ䴫덊ᆤ꾬")) || var1.isDirty(Application.B("\uf40c\ue7dcꣂ憁⓽▊峢ᶦ釓콭৭ᒦשׁ䴼덊ᆠ꾹ꪝ伷ꎗ"))) {
                this.requestLocationUpdates();
            }

        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onStopDetection(StopDetectionEvent var1) {
        if (this.mIsUpdatingLocation.get()) {
            LocationRequest var8;
            LocationRequest var10000 = var8 = this.mLocationRequest;
            TSLocationManager var10001 = this;
            TSLocationManager var10002 = this;
            synchronized(var8){}

            Throwable var9;
            boolean var10;
            try {
                var10002.mLocationRequest.setSmallestDisplacement(TSConfig.getInstance(this.mContext).getDistanceFilter());
            } catch (Throwable var7) {
                var9 = var7;
                var10 = false;
                throw var9;
            }

            try {
                var10001.updateLocationRequest();
            } catch (Throwable var6) {
                var9 = var6;
                var10 = false;
                throw var9;
            }
        }
    }

    public LocationProviderChangeEvent getCurrentLocationProvider() {
        return this.mCurrentLocationProvider;
    }

    public synchronized TSLocation buildTSLocation(Location var1) {
        this.setLastLocation(var1);
        TSLocationManager var10002 = this;
        Context var4 = this.mContext;
        ActivityTransitionEvent var2 = ActivityRecognitionService.getMostProbableActivity();
        LocationProviderChangeEvent var3 = var10002.mCurrentLocationProvider;
        return new TSLocation(var4, var1, var2, var3);
    }

    public void register(SingleLocationRequest var1) {
        Map var2;
        Map var10000 = var2 = this.locationRequests;
        TSLocationManager var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.locationRequests.put(var1.getId(), var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    public LocationRequest buildLocationRequest() {
        TSConfig var1 = TSConfig.getInstance(this.mContext);
        LocationRequest var2 = this.mLocationRequest;
        TSConfig var10000 = var1;
        synchronized(var2){}

        boolean var10001;
        Throwable var276;
        float var277;
        try {
            var277 = var10000.getDistanceFilter();
        } catch (Throwable var275) {
            var276 = var275;
            var10001 = false;
            throw var276;
        }

        float var3 = var277;
        if (var277 < 0.0F) {
            try {
                TSLog.logger.warn(TSLog.warn(Application.B("좝ｃ襀쬷⍋뀐龲朼脛ꐞ䴵↦ݐ橪⧪攴쯘ẙ\ue0a3㯽瓎ꉚᆿ\ue3f7") + var3) + Application.B("죺－褖쬗⍗뀉龺来脖ꐙ䴡⇲ݕ橡⧯攰쯫ẜ\ue0bb㮩") + 10.0F);
            } catch (Throwable var274) {
                var276 = var274;
                var10001 = false;
                throw var276;
            }

            var3 = 10.0F;
        }

        long var278;
        try {
            var278 = var1.getFastestLocationUpdateInterval();
        } catch (Throwable var273) {
            var276 = var273;
            var10001 = false;
            throw var276;
        }

        if (var278 >= 0L) {
            try {
                this.mLocationRequest.setFastestInterval(var1.getFastestLocationUpdateInterval());
            } catch (Throwable var272) {
                var276 = var272;
                var10001 = false;
                throw var276;
            }
        }

        boolean var279;
        try {
            var279 = var1.isLocationTrackingMode();
        } catch (Throwable var271) {
            var276 = var271;
            var10001 = false;
            throw var276;
        }

        TSLocationManager var10002;
        TSLocationManager var280;
        TSLocationManager var281;
        if (var279) {
            try {
                var279 = var1.getUseSignificantChangesOnly();
            } catch (Throwable var270) {
                var276 = var270;
                var10001 = false;
                throw var276;
            }

            if (var279 && var3 < 250.0F) {
                var3 = 250.0F;
            }

            try {
                var281 = this;
                var280 = this;
                var10002 = this;
                this.mLocationRequest.setSmallestDisplacement(var3);
            } catch (Throwable var269) {
                var276 = var269;
                var10001 = false;
                throw var276;
            }

            try {
                var10002.mLocationRequest.setInterval(var1.getLocationUpdateInterval());
            } catch (Throwable var268) {
                var276 = var268;
                var10001 = false;
                throw var276;
            }

            try {
                var280.mLocationRequest.setMaxWaitTime(var1.getDeferTime());
            } catch (Throwable var267) {
                var276 = var267;
                var10001 = false;
                throw var276;
            }

            try {
                var281.mLocationRequest.setPriority(var1.getDesiredAccuracy());
            } catch (Throwable var266) {
                var276 = var266;
                var10001 = false;
                throw var276;
            }
        } else {
            LocationRequest var10003;
            long var10004;
            try {
                var281 = this;
                var280 = this;
                var10002 = this;
                var10003 = this.mLocationRequest;
                var10004 = var1.getGeofenceProximityRadius();
            } catch (Throwable var265) {
                var276 = var265;
                var10001 = false;
                throw var276;
            }

            float var282 = (float)var10004 / 2.0F;

            try {
                var10003.setSmallestDisplacement(var282);
            } catch (Throwable var264) {
                var276 = var264;
                var10001 = false;
                throw var276;
            }

            try {
                var10002.mLocationRequest.setInterval(var1.getLocationUpdateInterval());
            } catch (Throwable var263) {
                var276 = var263;
                var10001 = false;
                throw var276;
            }

            try {
                var280.mLocationRequest.setMaxWaitTime(0L);
            } catch (Throwable var262) {
                var276 = var262;
                var10001 = false;
                throw var276;
            }

            try {
                var281.mLocationRequest.setPriority(105);
            } catch (Throwable var261) {
                var276 = var261;
                var10001 = false;
                throw var276;
            }
        }

        try {
            var281 = this;
            return var281.mLocationRequest;
        } catch (Throwable var260) {
            var276 = var260;
            var10001 = false;
            throw var276;
        }
    }

    public void requestLocationUpdates() {
        if (this.mIsUpdatingLocation.get()) {
            this.removeLocationUpdates();
        }

        if (TSConfig.getInstance(this.mContext).getEnabled() && b.e(this.mContext)) {
            TSLocationManager var10000 = this;
            TSLog.logger.info(TSLog.on(Application.B("픖後䂃ᕭ짫뒐ܞ뜦䈘\ueb89⏫\uf754㊷\u00ad㰦ᵩ金稷ꝅ礮ྶ")));
            FusedLocationProviderClient var10001 = LocationServices.getFusedLocationProviderClient(this.mContext);

            SecurityException var4;
            label38: {
                boolean var5;
                try {
                    var10001.requestLocationUpdates(this.buildLocationRequest(), TrackingService.getPendingIntent(this.mContext));
                } catch (SecurityException var2) {
                    var4 = var2;
                    var5 = false;
                    break label38;
                }

                try {
                    var10000.mIsUpdatingLocation.set(true);
                    return;
                } catch (SecurityException var1) {
                    var4 = var1;
                    var5 = false;
                }
            }

            SecurityException var3 = var4;
            TSLog.logger.error(TSLog.error(Application.B("픉徆䂃ᕹ짭뒐܅뜱䉰\ueb82⏭\uf743㊱°㰬ᵣ里稭꜒礉ྑ켹魍\ueb83䯉\ufa6e䑸ៃ䙄ㅳ穧⠼\uf4d6ꋈ͞蠧錳归\udd40㔽㸢伓畽뷂緲⟛팩嶙\ue351\u0084耜솼览캞\uf746锎仱鸹滈䅖砼宺") + var3.getMessage()), var3);
        }
    }

    public void updateLocationRequest() {
        // $FF: Couldn't be decompiled
    }

    public Boolean isUpdatingLocation() {
        return this.mIsUpdatingLocation.get();
    }

    public Boolean isLocationServicesEnabled() {
        if (!c.c(this.mContext)) {
            return false;
        } else {
            LocationManager var5;
            LocationManager var10000 = var5 = (LocationManager)this.mContext.getSystemService(Application.B("渎졨୶㠭鯞ﭜ\ue4d3珇"));
            boolean var1 = false;
            boolean var2 = false;

            boolean var6;
            label33: {
                try {
                    var6 = var10000.isProviderEnabled(Application.B("清졷୦"));
                } catch (Exception var4) {
                    break label33;
                }

                var1 = var6;
            }

            try {
                var6 = var5.isProviderEnabled(Application.B("渌졢ୡ㠻鯅קּ\ue4d7"));
            } catch (Exception var3) {
                TSLog.logger.error(TSLog.error(var3.getMessage()));
                return var1 || var2;
            }

            var2 = var6;
            return var1 || var2;
        }
    }

    private static class f implements Runnable {
        private final WeakReference<Context> a;
        private TSLocation b;
        private List<TSLocation> c;
        private boolean d;

        f(Context var1, TSLocation var2) {
            TSLocationManager.f var10000 = this;
            TSLocationManager.f var10001 = this;
            super();
            this.d = false;
            WeakReference var3;
            var3 = new WeakReference.<init>(var1);
            var10001.a = var3;
            var10000.b = var2;
        }

        f(Context var1, TSLocation var2, boolean var3) {
            TSLocationManager.f var10000 = this;
            TSLocationManager.f var10001 = this;
            TSLocationManager.f var10002 = this;
            super();
            this.d = false;
            WeakReference var4;
            var4 = new WeakReference.<init>(var1);
            var10002.a = var4;
            var10001.d = var3;
            var10000.b = var2;
        }

        f(Context var1, List<TSLocation> var2) {
            TSLocationManager.f var10000 = this;
            TSLocationManager.f var10001 = this;
            super();
            this.d = false;
            WeakReference var3;
            var3 = new WeakReference.<init>(var1);
            var10001.a = var3;
            var10000.c = var2;
        }

        private void b() {
            TSConfig var1 = TSConfig.getInstance((Context)this.a.get());
            com.transistorsoft.locationmanager.data.sqlite.b var2 = com.transistorsoft.locationmanager.data.sqlite.b.a((Context)this.a.get());
            TSLocation var3;
            if ((var3 = this.b) != null) {
                var2.persist(var3);
            } else {
                List var5;
                if ((var5 = this.c) != null) {
                    Iterator var6 = var5.iterator();

                    while(var6.hasNext()) {
                        var2.persist((TSLocation)var6.next());
                    }
                }
            }

            if (var1.getAutoSync() && var1.hasUrl()) {
                Integer var4;
                if (var4 = var1.getAutoSyncThreshold() > 0 && !this.d && var2.count() < var4) {
                    return;
                }

                HttpService.getInstance((Context)this.a.get()).flush(this.d);
            }

        }

        private void a() {
            JSONObject var1 = TSConfig.getInstance((Context)this.a.get()).getParams();
            if (this.b != null) {
                EventBus var10000 = EventBus.getDefault();
                TSLocationManager.f var10003 = this;
                Context var4 = (Context)this.a.get();
                var10000.post(new PersistEvent(var4, var10003.b, var1));
            } else {
                List var2;
                if ((var2 = this.c) != null) {
                    Iterator var5 = var2.iterator();

                    while(var5.hasNext()) {
                        TSLocation var3 = (TSLocation)var5.next();
                        EventBus.getDefault().post(new PersistEvent((Context)this.a.get(), var3, var1));
                    }
                }
            }

        }

        public void run() {
            if (this.a.get() == null) {
                TSLog.logger.error(TSLog.warn(Application.B("颖삖\ud8a0䉒ⲧ\ue199ﵾ嬠燾뷒凤ﶋ䙨\uf0ad洜擬ⱼꬪ敊墫佪╞瓻冈㆔퍋냞")));
            } else {
                if (EventBus.getDefault().hasSubscriberForEvent(PersistEvent.class)) {
                    if (a.a().a((Context)this.a.get())) {
                        this.a();
                    } else {
                        TSLog.logger.warn(TSLog.warn(Application.B("颞삂\ud8a5䉒Ⳣ\ue1beﴱ嬺燥붗凬ﶚ䘺\uf0b7洛撿ⱘ\uab6f敔墷你╌瓻况㆚퍖")));
                    }
                } else {
                    this.b();
                }

            }
        }
    }
}
