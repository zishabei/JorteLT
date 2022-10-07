//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Build.VERSION;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.GeofencingEvent;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.event.TemplateErrorEvent;
import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.locationmanager.util.e;
import com.transistorsoft.tslocationmanager.Application;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TSLocation {
    private static final String DATE_FORMAT = Application.B("ꝥᴎ䳿\ue66b쯙뮄\uf4adᙫ疅愬̊쩏\uf369搡\uddfc䢥滟翍ᣗ闕谴崸ə㛦䛓⢴搋꼅");
    public static final String LOCATION_OPTIONS_ODOMETER = Application.B("ꝳᴓ䳩\ue67f쮑뮽\uf485ᘴ");
    private static final ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>() {
        protected SimpleDateFormat initialValue() {
            Locale var10003 = Locale.ENGLISH;
            SimpleDateFormat var10000 = new SimpleDateFormat(Application.B("綿齩⃞ق䫧⽧콷鷼ԩꫲ욒\ue250嘁뫿✺弄퇢\uf7c0鈕띄扥鬭빙즀\uaada\ueaf1\ue40e煙"), var10003);
            var10000.setTimeZone(TimeZone.getTimeZone(Application.B("經齄⃤")));
            return var10000;
        }
    };
    private static e locationTemplate;
    private static e geofenceTemplate;
    private static DecimalFormat twoDForm;
    private static DecimalFormat oneDForm;
    public Integer id = null;
    public Location mLocation = null;
    public JSONObject json = null;
    private String uuid;
    private String timestamp;
    private boolean enableTimestampMeta;
    private Double accuracy;
    private Double speedAccuracy;
    private Double altitude;
    private Double altitudeAccuracy;
    private Double heading;
    private Double headingAccuracy;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private String event = "";
    private Float odometer;
    private Boolean isMoving;
    private Boolean isMock = false;
    private Boolean isSample = false;
    private DetectedActivity detectedActivity;
    private String activityName;
    private Integer activityConfidence;
    private Boolean batteryIsCharging = false;
    private Double batteryLevel = -1.0D;
    private TSGeofence geofence;
    private String geofenceAction;
    private JSONObject extras;
    private LocationProviderChangeEvent mProvider;

    private static String formatDate(long var0) {
        return ((SimpleDateFormat)dateFormatter.get()).format(new Date(var0));
    }

    public static synchronized Location applyExtras(Context var0, Location var1) {
        Bundle var2;
        Bundle var10000 = var2 = var1.getExtras();
        TSConfig var3 = TSConfig.getInstance(var0);
        if (var10000 == null) {
            var2 = new Bundle.<init>();
        }

        Context var8 = var0;
        var2.putFloat(Application.B("덉\ud85b囎톺멹ᢓ\u2064ﻛ"), var3.getOdometer());
        IntentFilter var4;
        var4 = new IntentFilter.<init>(Application.B("덇\ud851囅톥멳ᢎ\u2065ﺇ鿣Ժ胧蚋鉸鎁ᘥȲ祠\ue320踙\u202c\uf0df묹\ud8a7ສ㶕⒌⚷ꇞ꼧鿊걕鱠鶍\ue06c캅摂柿"));
        Intent var5;
        if ((var5 = var8.registerReceiver((BroadcastReceiver)null, var4)) != null) {
            Intent var9 = var5;
            Intent var10002 = var5;
            int var6 = var5.getIntExtra(Application.B("덊\ud85a囗톲며"), -1);
            var2.putFloat(Application.B("덄\ud85e囕톣멹ᢕ⁸ﻶ鿦Ա胥蚋鉺"), (float)var6 / (float)var10002.getIntExtra(Application.B("덕\ud85c囀톻멹"), -1));
            boolean var7;
            if ((var6 = var9.getIntExtra(Application.B("덕\ud84b囀톣멩ᢔ"), -1)) != 2 && var6 != 5) {
                var7 = false;
            } else {
                var7 = true;
            }

            var2.putBoolean(Application.B("덏\ud84c图톴면ᢆ\u2073ﻎ鿣Ժ胴"), var7);
        }

        var1.setExtras(var2);
        return var1;
    }

    public static TSLocation buildFromJson(Context var0, JSONObject var1) {
        Location var2;
        Location var10000 = var2 = new Location;
        JSONObject var10001 = var1;
        var2.<init>(Application.B("ﲉ牬푮ᐋ珏"));
        Bundle var3;
        var3 = new Bundle.<init>();

        JSONException var19;
        label97: {
            boolean var20;
            try {
                var10001 = var10001.getJSONObject(Application.B("ﲌ牶푲ᐜ珏፞"));
            } catch (JSONException var15) {
                var19 = var15;
                var20 = false;
                break label97;
            }

            JSONObject var4 = var10001;

            Location var10002;
            JSONObject var10003;
            Location var10004;
            JSONObject var10005;
            Location var10006;
            JSONObject var10007;
            Location var10008;
            JSONObject var10009;
            Location var10010;
            JSONObject var10011;
            Bundle var10012;
            JSONObject var10013;
            Bundle var10014;
            Bundle var10015;
            JSONObject var10016;
            JSONObject var10017;
            try {
                var10002 = var2;
                var10003 = var4;
                var10004 = var2;
                var10005 = var4;
                var10006 = var2;
                var10007 = var4;
                var10008 = var2;
                var10009 = var4;
                var10010 = var2;
                var10011 = var4;
                var10012 = var3;
                var10013 = var1;
                var10014 = var3;
                var10015 = var3;
                var10016 = var1;
                var10017 = var1;
                var1.getJSONObject(Application.B("ﲎ牺푩ᐇ珝ፄ⨔躅"));
            } catch (JSONException var14) {
                var19 = var14;
                var20 = false;
                break label97;
            }

            try {
                var10017.getJSONObject(Application.B("ﲍ牸푩ᐚ珎፟⨙"));
            } catch (JSONException var13) {
                var19 = var13;
                var20 = false;
                break label97;
            }

            ActivityTransitionEvent var29;
            try {
                var29 = new ActivityTransitionEvent;
            } catch (JSONException var12) {
                var19 = var12;
                var20 = false;
                break label97;
            }

            ActivityTransitionEvent var17 = var29;

            try {
                var29.<init>(3, 0, 0L);
            } catch (JSONException var11) {
                var19 = var11;
                var20 = false;
                break label97;
            }

            String var18 = Application.B("ﲀ牽푲ᐃ珎ፙ⨅躎");

            double var27;
            try {
                var27 = var10016.getDouble(Application.B("ﲀ牽푲ᐃ珎ፙ⨅躎"));
            } catch (JSONException var10) {
                var19 = var10;
                var20 = false;
                break label97;
            }

            float var28 = (float)var27;

            try {
                var10015.putFloat(var18, var28);
                var10014.putBoolean(Application.B("ﲜ牸푰ᐞ珇ፈ"), false);
            } catch (JSONException var9) {
                var19 = var9;
                var20 = false;
                break label97;
            }

            var18 = Application.B("ﲊ牯푸᐀珟");

            double var25;
            try {
                var10012.putString(var18, var10013.getString(Application.B("ﲊ牯푸᐀珟")));
                var10010.setLatitude(var10011.getDouble(Application.B("ﲃ牸푩ᐇ珟ፘ⨄躙")));
                var10008.setLongitude(var10009.getDouble(Application.B("ﲃ牶푳ᐉ珂ፙ⨕躘旞")));
                var25 = var10007.getDouble(Application.B("ﲎ牺푾ᐛ珙ፌ⨃躅"));
            } catch (JSONException var8) {
                var19 = var8;
                var20 = false;
                break label97;
            }

            float var26 = (float)var25;

            double var23;
            try {
                var10006.setAccuracy(var26);
                var23 = var10005.getDouble(Application.B("ﲜ物푸ᐋ珏"));
            } catch (JSONException var7) {
                var19 = var7;
                var20 = false;
                break label97;
            }

            float var24 = (float)var23;

            double var21;
            try {
                var10004.setSpeed(var24);
                var21 = var10003.getDouble(Application.B("ﲇ牼푼ᐊ珂ፃ⨇"));
            } catch (JSONException var6) {
                var19 = var6;
                var20 = false;
                break label97;
            }

            float var22 = (float)var21;

            try {
                var10002.setBearing(var22);
                var10000.setAltitude(var10001.getDouble(Application.B("ﲎ牵푩ᐇ珟ፘ⨄躙")));
                return new TSLocation(var0, var2, var17);
            } catch (JSONException var5) {
                var19 = var5;
                var20 = false;
            }
        }

        JSONException var16 = var19;
        TSLog.logger.error(Application.B("ﲪ牫푯ᐁ珙ግ⨂躉旒㾣䴇虵ၪ긃蠬₠琎\ue32fᨬ숉ݙ흌쟴裯쿢넶쀲⪸臢䣭무᎖繇豘ᥞ꞊࡙") + var16.getMessage());
        var16.printStackTrace();
        throw var16;
    }

    private static void initialize() {
        DecimalFormatSymbols var0;
        var0 = new DecimalFormatSymbols.<init>(Locale.US);
        twoDForm = new DecimalFormat(Application.B("硎\ue976鸈ꬠ"), var0);
        oneDForm = new DecimalFormat(Application.B("硎\ue976鸈"), var0);
    }

    public TSLocation(Context var1, Location var2, ActivityTransitionEvent var3) {
        this.initialize(var1, var2, var3);
    }

    public TSLocation(Context var1, Location var2, ActivityTransitionEvent var3, LocationProviderChangeEvent var4) {
        this.mProvider = var4;
        this.initialize(var1, var2, var3);
    }

    private void initialize(Context var1, Location var2, ActivityTransitionEvent var3) {
        this.mLocation = var2;
        TSConfig var4 = TSConfig.getInstance(var1);
        Bundle var5;
        if ((var5 = var2.getExtras()) == null) {
            var5 = new Bundle.<init>();
            var2.setExtras(var5);
        }

        int var6;
        if ((var6 = VERSION.SDK_INT) >= 18) {
            label105: {
                if (var6 >= 31) {
                    if (!var2.isMock()) {
                        break label105;
                    }
                } else if (!var2.isFromMockProvider()) {
                    break label105;
                }

                this.isMock = true;
            }
        }

        this.uuid = UUID.randomUUID().toString();
        this.timestamp = formatDate(getTime(this.mLocation));
        this.enableTimestampMeta = var4.getEnableTimestampMeta();
        this.latitude = var2.getLatitude();
        this.longitude = var2.getLongitude();
        Double var7 = var2.hasAccuracy() && !Float.isNaN(var2.getAccuracy()) ? Double.valueOf(oneDForm.format((double)var2.getAccuracy())) : -1.0D;
        this.accuracy = var7;
        var7 = var2.hasAltitude() && !Double.isNaN(var2.getAltitude()) ? Double.valueOf(oneDForm.format(var2.getAltitude())) : -1.0D;
        this.altitude = var7;
        this.altitudeAccuracy = -1.0D;
        if (VERSION.SDK_INT >= 26 && var2.hasVerticalAccuracy() && !Double.isNaN((double)var2.getVerticalAccuracyMeters())) {
            this.altitudeAccuracy = Double.valueOf(oneDForm.format((double)var2.getVerticalAccuracyMeters()));
        }

        var7 = var2.hasSpeed() && !Float.isNaN(var2.getSpeed()) ? Double.valueOf(twoDForm.format((double)var2.getSpeed())) : -1.0D;
        this.speed = var7;
        this.speedAccuracy = -1.0D;
        if (VERSION.SDK_INT >= 26) {
            this.speedAccuracy = var2.hasSpeedAccuracy() && !Float.isNaN(var2.getSpeedAccuracyMetersPerSecond()) ? Double.valueOf(twoDForm.format((double)var2.getSpeedAccuracyMetersPerSecond())) : -1.0D;
        }

        var7 = var2.hasBearing() && !Float.isNaN(var2.getBearing()) ? Double.valueOf(twoDForm.format((double)var2.getBearing())) : -1.0D;
        this.heading = var7;
        this.headingAccuracy = -1.0D;
        if (VERSION.SDK_INT >= 26) {
            this.headingAccuracy = var2.hasBearingAccuracy() && !Float.isNaN(var2.getBearingAccuracyDegrees()) ? Double.valueOf(twoDForm.format((double)var2.getBearingAccuracyDegrees())) : -1.0D;
        }

        if (var3 != null) {
            this.activityName = Util.getActivityName(var3.getActivityType());
            this.activityConfidence = 100;
        } else {
            this.activityName = Application.B("砘\ue936鹀\uab6d㫎\uee51裄");
            this.activityConfidence = 100;
        }

        this.isMoving = var4.getIsMoving();
        if (var5.containsKey(Application.B("砞\ue939鹆ꭳ㫍\uee43"))) {
            this.isSample = true;
        }

        if (var5.containsKey(Application.B("砈\ue92e鹎\uab6d㫕"))) {
            this.event = var5.getString(Application.B("砈\ue92e鹎\uab6d㫕"));
        }

        if (var5.containsKey(Application.B("砄\ue92b鹴\uab6b㫄\uee47裘崲ᢶ㐂웊궄"))) {
            this.event = Application.B("砅\ue93d鹊ꭱ㫕\uee44裏崧ᢠ");
        }

        this.odometer = Float.valueOf(oneDForm.format(var4.getOdometer()));
        this.updateBatteryLevel(var1);
        this.extras = var4.getExtras();
    }

    static long getTime(Location var0) {
        long var1;
        if ((var1 = var0.getTime()) > 0L && var1 < 1546300800000L) {
            var1 += 619315200000L;
        }

        return var1;
    }

    private void updateBatteryLevel(Context var1) {
        int var2;
        int var3;
        if (VERSION.SDK_INT >= 26) {
            BatteryManager var10000 = (BatteryManager)var1.getSystemService(Application.B("兎\uea22ꏘ犚桄狸Å⿓崛⼭ﳔ\uea90葽㟩"));
            var3 = var10000.getIntProperty(4);
            int var6 = var2 = var10000.getIntProperty(6);
            this.batteryLevel = Double.valueOf(twoDForm.format((double)((float)var3 / 100.0F)));
            this.batteryIsCharging = var6 == 2 || var2 == 5;
        } else {
            Context var7 = var1;
            IntentFilter var4;
            var4 = new IntentFilter.<init>(Application.B("免\uea2dꏈ犜桎狣Ø⾐崓⼭ﳁ\uea92葶㟯幽臺\uda06ᢶ憂棝\uefdd䔂㶸洍弨痢⌀뻨楴졸䎥\ud8ee\ue659鼳愗냊圮"));
            Intent var5;
            if ((var5 = var7.registerReceiver((BroadcastReceiver)null, var4)) != null && var5.hasExtra(Application.B("兀\uea26ꏚ犋桍"))) {
                Intent var8 = var5;
                Intent var10002 = var5;
                var3 = var5.getIntExtra(Application.B("兀\uea26ꏚ犋桍"), -1);
                var2 = var10002.getIntExtra(Application.B("兟\uea20ꏍ犂桄"), -1);
                this.batteryLevel = Double.valueOf(twoDForm.format((double)((float)var3 / (float)var2)));
                this.batteryIsCharging = (var3 = var8.getIntExtra(Application.B("兟\uea37ꏍ犚桔狹"), -1)) == 2 || var3 == 5;
            }
        }

    }

    private HashMap<String, String> getLocationData() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap;
        var1.<init>();
        var1.put(Application.B("⠌몷ꭋ璀"), this.isMock.toString());
        String var10036 = this.timestamp;
        var1.put(Application.B("⠕몱ꭅ璎\uefaaᨤ鬬녖閖"), var10036);
        String var10034 = this.uuid;
        var1.put(Application.B("⠔몭ꭁ璏"), var10034);
        var1.put(Application.B("⠍몹ꭜ璂\uefadᨥ鬩녞"), this.latitude.toString());
        var1.put(Application.B("⠍몷ꭆ璌\uefb0ᨤ鬸녟閃"), this.longitude.toString());
        var1.put(Application.B("⠒모ꭍ璎\uefbd"), this.speed.toString());
        var1.put(Application.B("⠒모ꭍ璎\uefbdᨏ鬬녘閅莚否䡽擔ᇜ"), this.speedAccuracy.toString());
        var1.put(Application.B("⠉몽ꭉ璏\uefb0ᨾ鬪"), this.heading.toString());
        var1.put(Application.B("⠉몽ꭉ璏\uefb0ᨾ鬪녤閇莌吷䡩擅ᇄꗓ\udba4"), this.headingAccuracy.toString());
        var1.put(Application.B("⠀못ꭋ璞\uefabᨱ鬮녂"), this.accuracy.toString());
        var1.put(Application.B("⠀몴ꭜ璂\uefadᨥ鬩녞"), this.altitude.toString());
        var1.put(Application.B("⠀몴ꭜ璂\uefadᨥ鬩녞閹莎吷䡿擂ᇗꗑ\udbbe愣"), this.altitudeAccuracy.toString());
        var1.put(Application.B("⠈몫ꭷ璆\uefb6ᨦ鬤녕閁"), this.isMoving.toString());
        String var10012 = this.event;
        var1.put(Application.B("⠄몮ꭍ璅\uefad"), var10012);
        var1.put(Application.B("⠎몼ꭇ璆\uefbcᨤ鬨녉"), this.odometer.toString());
        String var10008 = this.activityName;
        var1.put(Application.B("⠀못ꭜ璂\uefafᨹ鬹녂闈莛吭䡬擒"), var10008);
        var1.put(Application.B("⠀못ꭜ璂\uefafᨹ鬹녂闈莌吻䡲擑ᇌꗔ\udbb8愴ꎿ㚢"), this.activityConfidence.toString());
        var1.put(Application.B("⠃몹ꭜ璟\uefbcᨢ鬴넕閊莊吢䡹擛"), this.batteryLevel.toString());
        var10000.put(Application.B("⠃몹ꭜ璟\uefbcᨢ鬴넕閏莜吋䡿擟ᇄꗂ\udbba愳ꎲ㚠"), this.batteryIsCharging.toString());
        String var10001 = Application.B("⠕몱ꭅ璎\uefaaᨤ鬬녖閖莢吱䡨擖");

        try {
            var10000.put(var10001, this.getTimestampMeta().toString());
        } catch (JSONException var2) {
        }

        return var1;
    }

    private HashMap<String, String> getGeofenceData() {
        HashMap var10000 = this.getLocationData();
        var10000.put(Application.B("\uf09b굕\udb14ⶎ♱ᗀ꿤聻켶\uf543ی\uf467\ue76b➠孼䐝랛ꮥ噚"), this.geofence.getIdentifier());
        String var10002 = this.geofenceAction;
        var10000.put(Application.B("\uf09b굕\udb14ⶎ♱ᗀ꿤聻켶\uf54bۋ\uf476\ue76c➻孻"), var10002);
        return var10000;
    }

    private JSONObject buildJSONObject() {
        TSLocation var10000 = this;
        JSONObject var1;
        var1 = new JSONObject.<init>();
        JSONObject var2;
        var2 = new JSONObject.<init>();
        JSONObject var3;
        var3 = new JSONObject.<init>();

        JSONException var52;
        label369: {
            boolean var10001;
            boolean var53;
            try {
                var53 = var10000.event.isEmpty();
            } catch (JSONException var47) {
                var52 = var47;
                var10001 = false;
                break label369;
            }

            String var4;
            TSLocation var10002;
            JSONObject var54;
            String var56;
            JSONObject var57;
            if (!var53) {
                var10000 = this;
                var54 = var1;
                var10002 = this;
                var4 = Application.B("㓩遤陜촟烺");

                try {
                    var54.put(var4, var10002.event);
                } catch (JSONException var46) {
                    var52 = var46;
                    var10001 = false;
                    break label369;
                }

                try {
                    var53 = var10000.event.equalsIgnoreCase(Application.B("㓼遠陖촇烧荊㣊끫\u0888䵽ᩫ\ue15b⥜虎"));
                } catch (JSONException var45) {
                    var52 = var45;
                    var10001 = false;
                    break label369;
                }

                if (var53) {
                    LocationProviderChangeEvent var55;
                    try {
                        var55 = this.mProvider;
                    } catch (JSONException var44) {
                        var52 = var44;
                        var10001 = false;
                        break label369;
                    }

                    if (var55 != null) {
                        var57 = var1;
                        var56 = Application.B("㓼遠陖촇烧荊㣊끫");

                        try {
                            var57.put(var56, this.mProvider.toJson());
                        } catch (JSONException var43) {
                            var52 = var43;
                            var10001 = false;
                            break label369;
                        }
                    }
                }
            }

            var10000 = this;
            var54 = var1;
            var10002 = this;
            JSONObject var10003 = var1;
            TSLocation var10004 = this;
            JSONObject var10005 = var1;
            TSLocation var10006 = this;
            var4 = Application.B("㓥遡陦촜烡荘㣆끷\u088c");

            try {
                var10005.put(var4, var10006.isMoving);
            } catch (JSONException var42) {
                var52 = var42;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓹遧限촕");

            try {
                var10003.put(var4, var10004.uuid);
            } catch (JSONException var41) {
                var52 = var41;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓸遻陔촔烽荚㣎끴\u089b");

            try {
                var54.put(var4, var10002.timestamp);
            } catch (JSONException var40) {
                var52 = var40;
                var10001 = false;
                break label369;
            }

            try {
                var53 = var10000.enableTimestampMeta;
            } catch (JSONException var39) {
                var52 = var39;
                var10001 = false;
                break label369;
            }

            TSLocation var61;
            if (var53) {
                var57 = var1;
                var61 = this;
                var4 = Application.B("㓸遻陔촔烽荚㣎끴\u089b䵘ᩯ\ue141⥚");

                try {
                    var57.put(var4, var61.getTimestampMeta());
                } catch (JSONException var38) {
                    var52 = var38;
                    var10001 = false;
                    break label369;
                }
            }

            var10000 = this;
            var54 = var1;
            var10002 = this;
            var4 = Application.B("㓣遶陖촜烫荚㣊끫");

            try {
                var54.put(var4, var10002.odometer);
            } catch (JSONException var37) {
                var52 = var37;
                var10001 = false;
                break label369;
            }

            try {
                var53 = var10000.isMock;
            } catch (JSONException var36) {
                var52 = var36;
                var10001 = false;
                break label369;
            }

            if (var53) {
                var57 = var1;
                var61 = this;
                var4 = Application.B("㓡遽陚촚");

                try {
                    var57.put(var4, var61.isMock);
                } catch (JSONException var35) {
                    var52 = var35;
                    var10001 = false;
                    break label369;
                }
            }

            try {
                var53 = this.isSample;
            } catch (JSONException var34) {
                var52 = var34;
                var10001 = false;
                break label369;
            }

            if (var53) {
                var57 = var1;
                var61 = this;
                var4 = Application.B("㓿遳陔촁烢荋");

                try {
                    var57.put(var4, var61.isSample);
                } catch (JSONException var33) {
                    var52 = var33;
                    var10001 = false;
                    break label369;
                }
            }

            var10000 = this;
            var54 = var1;
            JSONObject var58 = var1;
            var10003 = var3;
            var10004 = this;
            var10005 = var3;
            var10006 = this;
            JSONObject var10007 = var1;
            JSONObject var10008 = var2;
            TSLocation var10009 = this;
            JSONObject var10010 = var2;
            TSLocation var10011 = this;
            JSONObject var10012 = var2;
            TSLocation var10013 = this;
            JSONObject var10014 = var2;
            TSLocation var10015 = this;
            JSONObject var10016 = var2;
            TSLocation var10017 = this;
            JSONObject var10018 = var2;
            TSLocation var10019 = this;
            JSONObject var10020 = var2;
            TSLocation var10021 = this;
            JSONObject var10022 = var2;
            TSLocation var10023 = this;
            JSONObject var10024 = var2;
            TSLocation var10025 = this;
            var4 = Application.B("㓠遳降촘烺荛㣋끼");

            try {
                var10024.put(var4, var10025.latitude);
            } catch (JSONException var32) {
                var52 = var32;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓠遽陗촖烧荚㣚끽\u088e");

            try {
                var10022.put(var4, var10023.longitude);
            } catch (JSONException var31) {
                var52 = var31;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓭遱陚촄烼荏㣌끠");

            try {
                var10020.put(var4, var10021.accuracy);
            } catch (JSONException var30) {
                var52 = var30;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓿遢陜촔烪");

            try {
                var10018.put(var4, var10019.speed);
            } catch (JSONException var29) {
                var52 = var29;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓿遢陜촔烪荱㣎끺\u0888䵠᩸\ue154⥘虒");

            try {
                var10016.put(var4, var10017.speedAccuracy);
            } catch (JSONException var28) {
                var52 = var28;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓤遷陘촕烧荀㣈");

            try {
                var10014.put(var4, var10015.heading);
            } catch (JSONException var27) {
                var52 = var27;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓤遷陘촕烧荀㣈끆\u088a䵶ᩩ\ue140⥉虊蝡ⳳ");

            try {
                var10012.put(var4, var10013.headingAccuracy);
            } catch (JSONException var26) {
                var52 = var26;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓭遾降촘烺荛㣋끼");

            try {
                var10010.put(var4, var10011.altitude);
            } catch (JSONException var25) {
                var52 = var25;
                var10001 = false;
                break label369;
            }

            var4 = Application.B("㓭遾降촘烺荛㣋끼ࢴ䵴ᩩ\ue156⥎虙蝣⳩Λ");

            try {
                var10008.put(var4, var10009.altitudeAccuracy);
            } catch (JSONException var24) {
                var52 = var24;
                var10001 = false;
                break label369;
            }

            try {
                var10007.put(Application.B("㓯遽陖촃烪荝"), var2);
            } catch (JSONException var23) {
                var52 = var23;
                var10001 = false;
                break label369;
            }

            String var50 = Application.B("㓸遫陉촔");

            try {
                var10005.put(var50, var10006.activityName);
            } catch (JSONException var22) {
                var52 = var22;
                var10001 = false;
                break label369;
            }

            var50 = Application.B("㓯遽陗촗烧荊㣊끷\u0888䵰");

            try {
                var10003.put(var50, var10004.activityConfidence);
            } catch (JSONException var21) {
                var52 = var21;
                var10001 = false;
                break label369;
            }

            try {
                var58.put(Application.B("㓭遱降촘烸荇㣛끠"), var3);
            } catch (JSONException var20) {
                var52 = var20;
                var10001 = false;
                break label369;
            }

            try {
                var58 = new JSONObject;
            } catch (JSONException var19) {
                var52 = var19;
                var10001 = false;
                break label369;
            }

            var2 = var58;

            TSLocation var59;
            JSONObject var60;
            TSLocation var63;
            try {
                var59 = this;
                var60 = var2;
                var63 = this;
                var2.<init>();
            } catch (JSONException var18) {
                var52 = var18;
                var10001 = false;
                break label369;
            }

            String var51 = Application.B("㓥遡陦촒烦荏㣝끾\u0882䵻ᩭ");

            try {
                var60.put(var51, var63.batteryIsCharging);
            } catch (JSONException var17) {
                var52 = var17;
                var10001 = false;
                break label369;
            }

            var51 = Application.B("㓠遷陏촔烢");

            try {
                var58.put(var51, var59.batteryLevel);
            } catch (JSONException var16) {
                var52 = var16;
                var10001 = false;
                break label369;
            }

            try {
                var54.put(Application.B("㓮遳降촅烫荜㣖"), var2);
            } catch (JSONException var15) {
                var52 = var15;
                var10001 = false;
                break label369;
            }

            TSGeofence var64;
            try {
                var64 = var10000.geofence;
            } catch (JSONException var14) {
                var52 = var14;
                var10001 = false;
                break label369;
            }

            if (var64 != null) {
                try {
                    var10000 = this;
                    var54 = new JSONObject;
                } catch (JSONException var13) {
                    var52 = var13;
                    var10001 = false;
                    break label369;
                }

                var2 = var54;

                try {
                    var10002 = this;
                    var10003 = var2;
                    var2.<init>();
                } catch (JSONException var12) {
                    var52 = var12;
                    var10001 = false;
                    break label369;
                }

                String var62 = Application.B("㓥遶陜촟烺荇㣉끰\u088e䵧");

                try {
                    var10003.put(var62, this.geofence.getIdentifier());
                } catch (JSONException var11) {
                    var52 = var11;
                    var10001 = false;
                    break label369;
                }

                var51 = Application.B("㓭遱降촘烡荀");

                try {
                    var54.put(var51, var10002.geofenceAction);
                } catch (JSONException var10) {
                    var52 = var10;
                    var10001 = false;
                    break label369;
                }

                try {
                    var57 = var10000.geofence.getExtras();
                } catch (JSONException var9) {
                    var52 = var9;
                    var10001 = false;
                    break label369;
                }

                if (var57 != null) {
                    var57 = var2;
                    var56 = Application.B("㓩遪降촃烯荝");

                    try {
                        var57.put(var56, this.geofence.getExtras());
                    } catch (JSONException var8) {
                        var52 = var8;
                        var10001 = false;
                        break label369;
                    }
                }

                try {
                    var1.put(Application.B("㓫遷陖촗烫荀㣌끼"), var2);
                } catch (JSONException var7) {
                    var52 = var7;
                    var10001 = false;
                    break label369;
                }
            }

            try {
                var57 = this.extras;
            } catch (JSONException var6) {
                var52 = var6;
                var10001 = false;
                break label369;
            }

            if (var57 == null) {
                return var1;
            }

            var57 = var1;
            var61 = this;
            String var48 = Application.B("㓩遪降촃烯荝");

            try {
                var57.put(var48, var61.extras);
                return var1;
            } catch (JSONException var5) {
                var52 = var5;
                var10001 = false;
            }
        }

        JSONException var49 = var52;
        TSLog.logger.error(TSLog.error(Application.B("㓆遁陶촿炮荫㣝끫\u0884䵧")), var49);
        var49.printStackTrace();
        throw var49;
    }

    private JSONObject getTimestampMeta() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject;
        var1.<init>();
        long var2 = getTime(this.mLocation);
        var10000.put(Application.B("麋\u0dfe懽ퟏ"), var2);
        var2 = System.currentTimeMillis();
        var10000.put(Application.B("麌෮懣ퟞ㳄幂\ue232䮵꒴樭"), var2);
        if (VERSION.SDK_INT >= 17) {
            var2 = SystemClock.elapsedRealtimeNanos() / 1000000L;
            var1.put(Application.B("麌෮懣ퟞ㳄幂\ue225䮰꒶樫ᗂ㈇衑푻ꪑ\uf69d뾕ن陵鿄働慎ꘛ\ue180➐떅頼"), var2);
            var2 = this.mLocation.getElapsedRealtimeNanos() / 1000000L;
            var1.put(Application.B("麚\u0dfb懱ퟚ㳒幊\ue202䮎꒼権ᗅ㈶衔푷ꪇ"), var2);
        }

        return var1;
    }

    public static void resetLocationTemplate() {
        locationTemplate = null;
    }

    private static e getLocationTemplate(TSConfig var0) {
        if (locationTemplate == null && var0.hasLocationTemplate()) {
            String var1;
            if ((var1 = var0.getLocationTemplate()) == null) {
                return null;
            }

            locationTemplate = new e(var1);
        }

        return locationTemplate;
    }

    public static void resetGeofenceTemplate() {
        geofenceTemplate = null;
    }

    private static e getGeofenceTemplate(TSConfig var0) {
        if (geofenceTemplate == null && var0.hasGeofenceTemplate()) {
            String var1;
            if ((var1 = var0.getGeofenceTemplate()) == null) {
                return null;
            }

            geofenceTemplate = new e(var1);
        }

        return geofenceTemplate;
    }

    private void logError(TSConfig var1, e var2, Exception var3) {
        String var4;
        if (var2 == getLocationTemplate(var1)) {
            var4 = Application.B("鬷뭶뽱隀슨짘\uf4de칷釭葁᭔\ue614褱\ude54㻥\uf7e4");
        } else {
            var4 = Application.B("鬼뭼뽽隇승짟\uf4d2칼釭葁᭔\ue614褱\ude54㻥\uf7e4");
        }

        if (var1.getDebug()) {
            TemplateErrorEvent var5;
            var5 = new TemplateErrorEvent.<init>(var4, var3);
            EventBus.getDefault().post(var5);
        }

        TSLog.logger.error(TSLog.error(Application.B("鬂뭶뽧雁슴짐\uf4c7칼醙葅᭗\ue644褸\ude47㻣\uf7ee櫃ﴦ뺟汧뷹旳\ue90aე輭䨨") + var4 + Application.B("魡묓뼰") + var3.getMessage() + Application.B("魹")));
    }

    static {
        initialize();
    }

    public void addGeofencingEvent(GeofencingEvent var1, TSGeofence var2) {
        this.event = Application.B("\ue520㹩垕|블뇴߀Ḕ");
        this.geofence = var2;
        int var3;
        if ((var3 = var1.getGeofenceTransition()) != 1) {
            if (var3 != 2) {
                if (var3 == 4) {
                    this.geofenceAction = Application.B("\ue503㹛垿V븽");
                }
            } else {
                this.geofenceAction = Application.B("\ue502㹔垳N");
            }
        } else {
            this.geofenceAction = Application.B("\ue502㹂垮_븣");
        }

    }

    public void setEvent(String var1) {
        this.event = var1;
    }

    public Object getJson() {
        return this.json;
    }

    public JSONObject toJson() {
        if (this.json == null) {
            this.json = this.buildJSONObject();
        }

        return this.json;
    }

    public Object renderJson(Context var1) {
        TSConfig var32 = TSConfig.getInstance(var1);
        HashMap var2;
        var2 = new HashMap.<init>();
        e var3;
        if (this.geofence == null) {
            if ((var3 = getLocationTemplate(var32)) != null) {
                var2 = this.getLocationData();
            }
        } else if ((var3 = getGeofenceTemplate(var32)) != null) {
            var2 = this.getGeofenceData();
        }

        if (var3 == null) {
            return this.toJson();
        } else {
            TSLocation var10000;
            String var10001;
            try {
                var10000 = this;
                var10001 = var3.a(var2);
            } catch (IllegalArgumentException var5) {
                this.logError(var32, var3, var5);
                throw var5;
            }

            String var33 = var10001;

            JSONException var36;
            label214: {
                boolean var38;
                char var39;
                try {
                    var39 = var10001.charAt(0);
                } catch (JSONException var31) {
                    var36 = var31;
                    var38 = false;
                    break label214;
                }

                char var4 = var39;

                TSGeofence var37;
                try {
                    var37 = var10000.geofence;
                } catch (JSONException var30) {
                    var36 = var30;
                    var38 = false;
                    break label214;
                }

                JSONObject var40;
                if (var37 != null) {
                    try {
                        var40 = this.geofence.getExtras();
                    } catch (JSONException var29) {
                        var36 = var29;
                        var38 = false;
                        break label214;
                    }

                    if (var40 != null) {
                        try {
                            var40 = this.extras;
                        } catch (JSONException var28) {
                            var36 = var28;
                            var38 = false;
                            break label214;
                        }

                        if (var40 == null) {
                            try {
                                this.extras = new JSONObject();
                            } catch (JSONException var27) {
                                var36 = var27;
                                var38 = false;
                                break label214;
                            }
                        }

                        try {
                            this.extras = Util.mergeJson(this.extras, this.geofence.getExtras());
                        } catch (JSONException var26) {
                            var36 = var26;
                            var38 = false;
                            break label214;
                        }
                    }
                }

                Object var35;
                boolean var42;
                LocationProviderChangeEvent var43;
                if (var4 == '{') {
                    try {
                        var40 = new JSONObject;
                    } catch (JSONException var25) {
                        var36 = var25;
                        var38 = false;
                        break label214;
                    }

                    Object var41 = var35 = var40;

                    try {
                        var41.<init>(var33);
                        var42 = var40.has(Application.B("짎쉛⽢~烵"));
                    } catch (JSONException var24) {
                        var36 = var24;
                        var38 = false;
                        break label214;
                    }

                    if (var42) {
                        try {
                            var42 = ((JSONObject)var35).getString(Application.B("짎쉛⽢~烵")).isEmpty();
                        } catch (JSONException var23) {
                            var36 = var23;
                            var38 = false;
                            break label214;
                        }

                        if (var42) {
                            try {
                                ((JSONObject)var35).remove(Application.B("짎쉛⽢~烵"));
                            } catch (JSONException var22) {
                                var36 = var22;
                                var38 = false;
                                break label214;
                            }
                        }
                    }

                    try {
                        var42 = this.event.equalsIgnoreCase(Application.B("짛쉟⽨f烨氂菭竁涢킚㧓賦뵅䃢"));
                    } catch (JSONException var21) {
                        var36 = var21;
                        var38 = false;
                        break label214;
                    }

                    Object var44;
                    if (var42) {
                        try {
                            var43 = this.mProvider;
                        } catch (JSONException var20) {
                            var36 = var20;
                            var38 = false;
                            break label214;
                        }

                        if (var43 != null) {
                            var44 = var35;
                            var10001 = Application.B("짛쉟⽨f烨氂菭竁");

                            try {
                                ((JSONObject)var44).put(var10001, this.mProvider.toJson());
                            } catch (JSONException var19) {
                                var36 = var19;
                                var38 = false;
                                break label214;
                            }
                        }
                    }

                    try {
                        var42 = this.isMock;
                    } catch (JSONException var18) {
                        var36 = var18;
                        var38 = false;
                        break label214;
                    }

                    if (var42) {
                        var44 = var35;
                        TSLocation var45 = this;
                        var33 = Application.B("짆쉂⽤{");

                        try {
                            ((JSONObject)var44).put(var33, var45.isMock);
                        } catch (JSONException var17) {
                            var36 = var17;
                            var38 = false;
                            break label214;
                        }
                    }

                    try {
                        var40 = this.extras;
                    } catch (JSONException var16) {
                        var36 = var16;
                        var38 = false;
                        break label214;
                    }

                    if (var40 != null) {
                        try {
                            var40 = Util.mergeJson((JSONObject)var35, this.extras);
                        } catch (JSONException var15) {
                            var36 = var15;
                            var38 = false;
                            break label214;
                        }

                        var35 = var40;
                    }
                } else {
                    if (var4 != '[') {
                        try {
                            throw new JSONException(Application.B("짢쉃⽱q热氏菬窓涋킡㧽賆봘䂧") + var33);
                        } catch (JSONException var6) {
                            var36 = var6;
                            var38 = false;
                            break label214;
                        }
                    }

                    JSONArray var46;
                    try {
                        var10000 = this;
                        var46 = new JSONArray;
                    } catch (JSONException var14) {
                        var36 = var14;
                        var38 = false;
                        break label214;
                    }

                    var35 = var46;

                    try {
                        var46.<init>(var33);
                        var42 = var10000.isMock;
                    } catch (JSONException var13) {
                        var36 = var13;
                        var38 = false;
                        break label214;
                    }

                    if (var42) {
                        try {
                            TSLog.logger.warn(TSLog.warn(Application.B("짪쉝⽷u烯氂菡竝润탒㦑賡뵑䃊ꭐ䱬彛퇘\udb73㶹臀鄐\ue401♢것띤읶\udfb5༈摓﹆⪇ﶋ㒹⇉䮒利咝믙卣ힿ\uf5b9⿓ଜ㣲뀩撑\uf3c0궊㜞槰䟧쫀괞⥃݄疕伍뫈")));
                            ((JSONArray)var35).put(this.isMock);
                        } catch (JSONException var12) {
                            var36 = var12;
                            var38 = false;
                            break label214;
                        }
                    }

                    try {
                        var40 = this.extras;
                    } catch (JSONException var11) {
                        var36 = var11;
                        var38 = false;
                        break label214;
                    }

                    if (var40 != null) {
                        try {
                            TSLog.logger.warn(TSLog.warn(Application.B("짪쉝⽷u烯氂菡竝润탒㦑賭뵚䃳ꭍ䱮彃퇘\udb73㶹臀鄐\ue401♢것띤읶\udfb5༈摓﹆⪇ﶋ㒹⇉䮒利咝믙卣ힿ\uf5b9⿓ଜ㣲뀩撑\uf3c0궊㜞槰䟧쫀괞⥃݄疕伍뫈")));
                            ((JSONArray)var35).put(this.extras);
                        } catch (JSONException var10) {
                            var36 = var10;
                            var38 = false;
                            break label214;
                        }
                    }

                    try {
                        var42 = this.event.equalsIgnoreCase(Application.B("짛쉟⽨f烨氂菭竁涢킚㧓賦뵅䃢"));
                    } catch (JSONException var9) {
                        var36 = var9;
                        var38 = false;
                        break label214;
                    }

                    if (var42) {
                        try {
                            var43 = this.mProvider;
                        } catch (JSONException var8) {
                            var36 = var8;
                            var38 = false;
                            break label214;
                        }

                        if (var43 != null) {
                            try {
                                ((JSONArray)var35).put(this.mProvider.toJson());
                            } catch (JSONException var7) {
                                var36 = var7;
                                var38 = false;
                                break label214;
                            }
                        }
                    }
                }

                return var35;
            }

            JSONException var34 = var36;
            this.logError(var32, var3, var34);
            throw var34;
        }
    }

    public Map<String, Object> toMap() {
        return Util.toMap(this.toJson());
    }

    public boolean isSample() {
        return this.isSample;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getEvent() {
        return this.event;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public boolean getIsMoving() {
        return this.isMoving;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public String getGeofenceIdentifier() {
        return this.geofence.getIdentifier();
    }

    public String getGeofenceAction() {
        return this.geofenceAction;
    }

    public TSGeofence getGeofence() {
        return this.geofence;
    }

    public boolean hasGeofence() {
        return this.geofence != null;
    }

    public JSONObject getGeofenceExtras() {
        return this.geofence.getExtras();
    }

    public JSONObject getExtras() {
        return this.extras;
    }

    public void setExtras(JSONObject var1) {
        JSONObject var2;
        if ((var2 = this.extras) != null) {
            try {
                this.extras = Util.mergeJson(var2, var1);
            } catch (JSONException var3) {
                TSLog.logger.error(TSLog.error(var3.getMessage()));
                var3.printStackTrace();
            }
        } else {
            this.extras = var1;
        }

    }

    public double getBatteryLevel() {
        return this.batteryLevel;
    }

    public boolean getBatteryIsCharging() {
        return this.batteryIsCharging;
    }

    public DetectedActivity getDetectedActivity() {
        return this.detectedActivity;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public int getActivityConfidence() {
        return this.activityConfidence;
    }
}
