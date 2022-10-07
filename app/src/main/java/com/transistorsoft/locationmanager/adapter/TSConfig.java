//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.b.a;
import com.transistorsoft.locationmanager.config.TSAuthorization;
import com.transistorsoft.locationmanager.config.TSBackgroundPermissionRationale;
import com.transistorsoft.locationmanager.config.TSNotification;
import com.transistorsoft.locationmanager.device.DeviceInfo;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.logger.TSMediaPlayer;
import com.transistorsoft.locationmanager.service.ForegroundNotification;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.locationmanager.util.Sensors;
import com.transistorsoft.tslocationmanager.Application;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TSConfig {
    private static TSConfig q;
    private static String r = Application.B("ᡢ\uf1d0龧䛙솔᷸⪁娷咝\u0dcc닂ￓ\uf6ee艐ษ泳栒䳍倚순魠ᒜ䪌쬁凒㔕");
    public static final Float MAXIMUM_LOCATION_ACCURACY = 100.0F;
    public static final int TRACKING_MODE_GEOFENCE = 0;
    public static final int TRACKING_MODE_LOCATION = 1;
    public static final float DEFAULT_DISTANCE_FILTER = 10.0F;
    public static final int DEFAULT_DESIRED_ACCURACY = 100;
    public static final long DEFAULT_LOCATION_UPDATE_INTERVAL = 1000L;
    public static final long DEFAULT_FASTEST_LOCATION_UPDATE_INTERVAL = -1L;
    public static final long DEFAULT_GEOFENCE_PROXIMITY_RADIUS = 1000L;
    public static final boolean DEFAULT_GEOFENCE_INITIAL_TRIGGER_ENTRY = true;
    public static final int DEFAULT_LOCATION_TIMEOUT = 60;
    public static final boolean DEFAULT_DISABLE_ELASTICITY = false;
    public static final float DEFAULT_ELASTICITY_MULTIPLIER = 1.0F;
    public static final long DEFAULT_DEFER_TIME = 0L;
    public static final int DEFAULT_STATIONARY_RADIUS = 25;
    public static final int MINIMUM_STATIONARY_RADIUS = 25;
    public static final long DEFAULT_STOP_TIMEOUT = 5L;
    public static final float DEFAULT_DESIRED_ODOMETER_ACCURACY = 100.0F;
    public static final boolean DEFAULT_ALLOW_IDENTICAL_LOCATIONS = false;
    public static final int DEFAULT_SPEED_JUMP_FILTER = 300;
    public static final long DEFAULT_ACTIVITY_RECOGNITION_INTERVAL = 10000L;
    public static final int DEFAULT_MINIMUM_ACTIVITY_RECOGNITION_CONFIDENCE = 75;
    public static final String DEFAULT_TRIGGER_ACTIVITIES = Application.B("ᡟ\uf1ed龴䛀솒ᷱ⪜娽咞\u0dc7늣ﾒ\uf6ef艟ฑ泴栉䲔倷숬魏ᒖ䫎쭇凔㔜ꈪ轟踌\uf248ꀘ�᳝ᢅແ痿䅈༑鈺\uedb0축ᡩ轜辅☽洛莤轗酦");
    public static final boolean DEFAULT_DISABLE_STOP_DETECTION = false;
    public static final int DEFAULT_STOP_AFTER_ELAPSED_MINUTES = 0;
    public static final boolean DEFAULT_STOP_ON_STATIONARY = false;
    public static final int DEFAULT_MAX_DAYS_TO_PERSIST = 1;
    public static final int DEFAULT_MAX_RECORDS_TO_PERSIST = -1;
    public static final String DEFAULT_URL = "";
    public static final String DEFAULT_HTTP_METHOD = Application.B("ᡦ\uf1cc龸䛢");
    public static final boolean DEFAULT_AUTO_SYNC = true;
    public static final Integer DEFAULT_AUTO_SYNC_THRESHOLD = 0;
    public static final boolean DEFAULT_BATCH_SYNC = false;
    public static final int DEFAULT_MAX_BATCH_SIZE = -1;
    public static final String DEFAULT_HTTP_ROOT_PROPERTY = Application.B("ᡚ\uf1ec龈䛗솃ᷰ⪚娰");
    public static final String DEFAULT_LOCATION_TEMPLATE = "";
    public static final String DEFAULT_GEOFENCE_TEMPLATE = "";
    public static final String DEFAULT_LOCATIONS_ORDER_DIRECTION = Application.B("ᡷ\uf1d0龨");
    public static final int DEFAULT_HTTP_TIMEOUT = 60000;
    public static final boolean DEFAULT_DEBUG = false;
    public static final boolean DEFAULT_STOP_ON_TERMINATE = true;
    public static final boolean DEFAULT_START_ON_BOOT = false;
    public static final int DEFAULT_HEARTBEAT_INTERVAL = -1;
    public static final boolean DEFAULT_FOREGROUND_SERVICE = true;
    public static final String DEFAULT_MAIN_ACTIVITY_NAME = null;
    public static final ArrayList<String> DEFAULT_SCHEDULE = new ArrayList();
    public static final int DEFAULT_LOG_LEVEL = 0;
    public static final int DEFAULT_LOG_MAX_DAYS = 3;
    public static final int PERSIST_MODE_ALL = 2;
    public static final int PERSIST_MODE_NONE = 0;
    public static final int PERSIST_MODE_LOCATION = 1;
    public static final int PERSIST_MODE_GEOFENCE = -1;
    private static AtomicBoolean s = new AtomicBoolean(false);
    private TSConfig.Builder a;
    private Context b;
    private AtomicBoolean c = new AtomicBoolean(false);
    private Boolean d;
    private Integer e;
    private Boolean f;
    private Boolean g;
    private Float h;
    private Boolean i;
    private boolean j;
    private Boolean k;
    private Boolean l;
    private String m;
    private JSONObject n;
    private ArrayList<Integer> o;
    private final Map<String, ArrayList<TSConfig.OnChangeCallback>> p = new HashMap();

    public static boolean isLoaded() {
        return s.get();
    }

    public static TSConfig getInstance(Context var0) {
        if (q == null) {
            if (var0.getApplicationContext() != null) {
                var0 = var0.getApplicationContext();
            }

            q = a(var0);
        }

        return q;
    }

    private static synchronized TSConfig a(Context var0) {
        if (q == null) {
            q = new TSConfig(var0);
        }

        return q;
    }

    public TSConfig(Context var1) {
        this.b = var1;
        this.i = true;
        this.k = false;
        this.d = false;
        this.f = false;
        this.e = 1;
        this.j = false;
        this.h = 0.0F;
        this.l = false;
        this.m = DEFAULT_MAIN_ACTIVITY_NAME;
        this.n = new JSONObject();
        com.transistorsoft.locationmanager.d.b.f(var1);
        this.load();
        TSConfig.Builder var2;
        if ((var2 = this.a) != null) {
            this.o = this.a(var2.triggerActivities);
        }

    }

    private void b() {
        TSConfig var10000 = this;
        synchronized(this.a.dirtyFields){}

        boolean var10001;
        Throwable var424;
        boolean var425;
        try {
            var425 = com.transistorsoft.locationmanager.d.b.e(var10000.b);
        } catch (Throwable var423) {
            var424 = var423;
            var10001 = false;
            throw var424;
        }

        if (!var425) {
            try {
                this.a.dirtyFields.clear();
                return;
            } catch (Throwable var404) {
                var424 = var404;
                var10001 = false;
                throw var424;
            }
        } else {
            try {
                var425 = this.a.dirtyFields.isEmpty();
            } catch (Throwable var422) {
                var424 = var422;
                var10001 = false;
                throw var424;
            }

            if (var425) {
                try {
                    return;
                } catch (Throwable var405) {
                    var424 = var405;
                    var10001 = false;
                    throw var424;
                }
            } else {
                try {
                    var425 = this.isDirty(Application.B("릫ꮹ㷼紌꿾캖捅醣"));
                } catch (Throwable var421) {
                    var424 = var421;
                    var10001 = false;
                    throw var424;
                }

                if (var425) {
                    try {
                        TSLog.setLogLevel(this.a.logLevel);
                    } catch (Throwable var420) {
                        var424 = var420;
                        var10001 = false;
                        throw var424;
                    }
                }

                try {
                    var425 = this.isDirty(Application.B("릫ꮹ㷼納꿺캘捤醮덵ᄂ"));
                } catch (Throwable var419) {
                    var424 = var419;
                    var10001 = false;
                    throw var424;
                }

                if (var425) {
                    try {
                        TSLog.setMaxHistory(this.a.logMaxDays);
                    } catch (Throwable var418) {
                        var424 = var418;
                        var10001 = false;
                        throw var424;
                    }
                }

                try {
                    var425 = this.isDirty(Application.B("릩ꮹ㷯紩꿽캉捃醮델ᄘᚏ왎璝襱\udc33⿲楸㔉訯ꬃ\ue80f㆟旽ᾕ"));
                } catch (Throwable var417) {
                    var424 = var417;
                    var10001 = false;
                    throw var424;
                }

                if (var425) {
                    try {
                        ForegroundNotification.createNotificationChannel(this.b);
                    } catch (Throwable var416) {
                        var424 = var416;
                        var10001 = false;
                        throw var424;
                    }
                }

                try {
                    var425 = this.isDirty(Application.B("릤ꮹ㷵紦꿲캇捵醽덠"));
                } catch (Throwable var415) {
                    var424 = var415;
                    var10001 = false;
                    throw var424;
                }

                if (var425) {
                    try {
                        var425 = this.a.configUrl.isEmpty();
                    } catch (Throwable var414) {
                        var424 = var414;
                        var10001 = false;
                        throw var424;
                    }

                    if (!var425) {
                        try {
                            this.loadConfig(new TSCallback() {
                                public void onSuccess() {
                                    TSLog.logger.debug(TSLog.ok(Application.B("㣐燬鬪\udbd7\ue9ed滤\ue141靐向輠䕞ട\uef02\uf7a5싧\ue77a逖ই")));
                                }

                                public void onFailure(String var1) {
                                    TSLog.logger.warn(TSLog.warn(var1));
                                }
                            });
                        } catch (Throwable var413) {
                            var424 = var413;
                            var10001 = false;
                            throw var424;
                        }
                    }
                }

                Iterator var426;
                try {
                    this.d();
                    EventBus.getDefault().post(new ConfigChangeEvent(this.b, this.a.dirtyFields));
                    var426 = this.a.dirtyFields.iterator();
                } catch (Throwable var412) {
                    var424 = var412;
                    var10001 = false;
                    throw var424;
                }

                Iterator var2 = var426;

                while(true) {
                    try {
                        var425 = var2.hasNext();
                    } catch (Throwable var407) {
                        var424 = var407;
                        var10001 = false;
                        break;
                    }

                    if (!var425) {
                        try {
                            this.a.dirtyFields.clear();
                            return;
                        } catch (Throwable var406) {
                            var424 = var406;
                            var10001 = false;
                            break;
                        }
                    }

                    String var427;
                    try {
                        var427 = (String)var2.next();
                    } catch (Throwable var411) {
                        var424 = var411;
                        var10001 = false;
                        break;
                    }

                    String var3 = var427;

                    try {
                        var425 = var427.equalsIgnoreCase(Application.B("릳ꮤ㷲紧꿼캅捒醎덯ᄅᚉ왖瓚襦\udc32⿶楥"));
                    } catch (Throwable var410) {
                        var424 = var410;
                        var10001 = false;
                        break;
                    }

                    if (var425) {
                        try {
                            this.o = this.a(this.a.triggerActivities);
                        } catch (Throwable var409) {
                            var424 = var409;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        this.b(var3);
                    } catch (Throwable var408) {
                        var424 = var408;
                        var10001 = false;
                        break;
                    }
                }
            }
        }

        throw var424;
    }

    private void d() {
        List var1;
        List var10000 = var1 = this.a.dirtyFields;
        TSConfig var10001 = this;
        synchronized(var1){}

        Throwable var8;
        boolean var9;
        Editor var10;
        try {
            TSLog.logger.debug(Application.B("낒歲ﰎ\udba4ὅ呲\uea37㐯었奰ᤊ匮礸\udeb6ᘚﲄ썬喇얅\ud9cb\ue01e壐ⴡ햗\udcbd웭胁ꥤ") + this.a.dirtyFields.toString());
            var10 = var10001.b.getSharedPreferences(r, 0).edit();
            var10.putString(TSConfig.Builder.class.getName(), this.a.b(false).toString());
        } catch (Throwable var7) {
            var8 = var7;
            var9 = false;
            throw var8;
        }

        try {
            var10.apply();
        } catch (Throwable var6) {
            var8 = var6;
            var9 = false;
            throw var8;
        }
    }

    private void a(String var1, Object var2) {
        Editor var3 = this.b.getSharedPreferences(r, 0).edit();
        if (var2 != null) {
            Class var4;
            if ((var4 = var2.getClass()) == Boolean.class) {
                var3.putBoolean(var1, (Boolean)var2);
            } else if (var4 == String.class) {
                var3.putString(var1, (String)var2);
            } else if (var4 == Double.class) {
                var3.putLong(var1, Double.doubleToRawLongBits((Double)var2));
            } else if (var4 == Integer.class) {
                var3.putInt(var1, (Integer)var2);
            } else if (var4 == Float.class) {
                var3.putFloat(var1, (Float)var2);
            } else if (var4 == JSONObject.class) {
                var3.putString(var1, var2.toString());
            } else {
                TSLog.logger.error(TSLog.error(Application.B("뵘⬧ꈇ忎犀趛땭\ue66b䱠契ຫ혽\udd76墹脍汄셆᳥릠᳥") + var4.getName()));
                TSMediaPlayer.getInstance().debug(this.b, Application.B("뵹⬺ꈀ忏犌趍땷\ue622䱼邏ວ혨\udd7d壸脾汘셄᳟맷\u1cb0\ue2f7┘➵⦆㽻䶰衺艦鰄큱웓\ueae2淋\u0d11㴁\uf181컲줰ﱄ\uf086"));
            }
        } else {
            var3.remove(var1);
        }

        var3.apply();
    }

    private ArrayList<Integer> a(String var1) {
        String var10000 = var1;
        ArrayList var4;
        var4 = new ArrayList.<init>();
        Iterator var2 = Arrays.asList(var10000.replaceAll(Application.B("뵑⬺ꉇ"), "").split(Application.B("봡"))).iterator();

        while(var2.hasNext()) {
            String var3;
            if ((var3 = (String)var2.next()).equalsIgnoreCase(Application.B("뵤⬧ꈳ忖犊趄땪\ue628䱿落"))) {
                var4.add(0);
            } else if (var3.equalsIgnoreCase(Application.B("뵢⬧ꈳ忂犆趏땺\ue628䱿落"))) {
                var4.add(1);
            } else if (var3.equalsIgnoreCase(Application.B("뵢⬧ꈳ忆犀趃땷"))) {
                var4.add(2);
            } else if (var3.equalsIgnoreCase(Application.B("뵿⬼ꈂ忎犆趂땤"))) {
                var4.add(8);
            } else if (var3.equalsIgnoreCase(Application.B("뵺⬨ꈀ忋犆趂땤"))) {
                var4.add(7);
            } else {
                TSLog.logger.warn(TSLog.error(Application.B("뵎⬆ꈢ忦犦趫땖\ue619䱖諾\u0e8f혛\udd41壖脋氇섖᳕맴\u1cae\ue2ea┞➡⦷㼯\u4db8衴艢鰌큩웓\ueac9里ൎ㴝\uf18f컭줊ﱔ\uf0de\uf79d愝樤\uee2a弑ཝf\ue735⩏伥ꨟ\uf637⁖\ud914允璥ꯠ鉭ᧆϽ鍬꒝") + var3));
                TSMediaPlayer.getInstance().debug(this.b, Application.B("뵹⬺ꈀ忏犌趍땷\ue622䱼邏ວ혨\udd7d壸脾汘셄᳟맾\u1cac\ue2e3┘➉⦮㽮䶫衹"));
            }
        }

        return var4;
    }

    private void b(String var1) {
        if (this.p.containsKey(var1)) {
            Iterator var2 = ((ArrayList)this.p.get(var1)).iterator();

            while(var2.hasNext()) {
                ((TSConfig.OnChangeCallback)var2.next()).a(this);
            }
        }

    }

    private void c() {
        (new TSConfig.c()).execute(new Void[0]);
    }

    public TSConfig.Builder updateWithBuilder() {
        return this.a.b();
    }

    public void updateWithJSONObject(JSONObject var1) {
        this.a.a(var1);
        this.b();
    }

    public JSONObject toJson(boolean var1) {
        JSONObject var12;
        JSONObject var10000 = var12 = this.a.b(var1);
        TSConfig var10001 = this;
        JSONObject var10002 = var12;
        TSConfig var10003 = this;
        JSONObject var10004 = var12;
        TSConfig var10005 = this;
        JSONObject var10006 = var12;
        JSONObject var10007 = var12;
        TSConfig var10008 = this;
        JSONObject var10009 = var12;
        TSConfig var10010 = this;
        JSONObject var10011 = var12;
        JSONObject var10012 = var12;
        String var10013 = Application.B("쇾ᗂ僉錄䫩Ὸ֒");

        JSONException var14;
        label77: {
            boolean var15;
            try {
                var10012.put(var10013, this.getEnabled());
            } catch (JSONException var10) {
                var14 = var10;
                var15 = false;
                break label77;
            }

            String var17 = Application.B("쇨ᗏ僀露䫡Ῠ֚\ue03eﻣ쭰\uf468がⅭꊋᯘ䲴");

            try {
                var10011.put(var17, this.getSchedulerEnabled());
            } catch (JSONException var9) {
                var14 = var9;
                var15 = false;
                break label77;
            }

            String var2 = Application.B("쇳ᗉ僉勞䫱\u1fff֓\ue03aﻥ쭰\uf468がⅭꊋᯘ䲴");

            try {
                var10009.put(var2, var10010.g);
            } catch (JSONException var8) {
                var14 = var8;
                var15 = false;
                break label77;
            }

            var2 = Application.B("쇯ᗞ僉菉䫮ῴ֘\ue03cﻜ쭚\uf462え");

            try {
                var10007.put(var2, var10008.getTrackingMode());
            } catch (JSONException var7) {
                var14 = var7;
                var15 = false;
                break label77;
            }

            String var16 = Application.B("쇴ᗈ僇擄䫠Ῡ֓\ue029");

            try {
                var10006.put(var16, this.getOdometer().doubleValue());
            } catch (JSONException var6) {
                var14 = var6;
                var15 = false;
                break label77;
            }

            String var11 = Application.B("쇲ᗟ僮老䫷΅ւ\ue019\ufefe쭚\uf472");

            try {
                var10004.put(var11, var10005.i);
            } catch (JSONException var5) {
                var14 = var5;
                var15 = false;
                break label77;
            }

            var11 = Application.B("쇿ᗅ僌螺䫤Ῠ֘\ue038ﻹ쭼\uf468はⅮꊄᯖ䲷諸焞近햫ᳫ");

            try {
                var10002.put(var11, var10003.k);
            } catch (JSONException var4) {
                var14 = var4;
                var15 = false;
                break label77;
            }

            var11 = Application.B("쇿ᗅ僌酪䫠Ύ֟\ue038ﻴ쭧\uf463くⅠꊈᯉ");

            try {
                var10000.put(var11, var10001.j);
                return var12;
            } catch (JSONException var3) {
                var14 = var3;
                var15 = false;
            }
        }

        JSONException var13 = var14;
        TSLog.logger.error(TSLog.error(var13.getMessage()), var13);
        var13.printStackTrace();
        return var12;
    }

    public JSONObject toJson() {
        return this.toJson(false);
    }

    public void onChange(String var1, TSConfig.OnChangeCallback var2) {
        if (!this.p.containsKey(var1)) {
            Map var10000 = this.p;
            ArrayList var3;
            var3 = new ArrayList.<init>();
            var10000.put(var1, var3);
        }

        ((ArrayList)this.p.get(var1)).add(var2);
    }

    public boolean removeListener(String var1, TSConfig.OnChangeCallback var2) {
        TSConfig var10000 = this;
        boolean var3 = false;
        synchronized(this.p){}

        Throwable var80;
        boolean var81;
        boolean var10001;
        try {
            var81 = var10000.p.containsKey(var1);
        } catch (Throwable var78) {
            var80 = var78;
            var10001 = false;
            throw var80;
        }

        if (var81) {
            List var82;
            try {
                var82 = (List)this.p.get(var1);
            } catch (Throwable var77) {
                var80 = var77;
                var10001 = false;
                throw var80;
            }

            List var79 = var82;
            var1 = null;

            Iterator var83;
            try {
                var83 = var82.iterator();
            } catch (Throwable var76) {
                var80 = var76;
                var10001 = false;
                throw var80;
            }

            Iterator var5 = var83;

            TSConfig.OnChangeCallback var6;
            do {
                try {
                    var81 = var5.hasNext();
                } catch (Throwable var75) {
                    var80 = var75;
                    var10001 = false;
                    throw var80;
                }

                if (!var81) {
                    var6 = var1;
                    break;
                }

                TSConfig.OnChangeCallback var84;
                try {
                    var84 = (TSConfig.OnChangeCallback)var5.next();
                } catch (Throwable var74) {
                    var80 = var74;
                    var10001 = false;
                    throw var80;
                }

                var6 = var84;

                try {
                    var81 = var84.equals(var2);
                } catch (Throwable var73) {
                    var80 = var73;
                    var10001 = false;
                    throw var80;
                }
            } while(!var81);

            if (var6 != null) {
                try {
                    var79.remove(var6);
                } catch (Throwable var72) {
                    var80 = var72;
                    var10001 = false;
                    throw var80;
                }

                var3 = true;
            }
        }

        try {
            return var3;
        } catch (Throwable var71) {
            var80 = var71;
            var10001 = false;
            throw var80;
        }
    }

    public void removeListeners() {
        this.p.clear();
    }

    public boolean isDirty(String var1) {
        TSConfig var10000 = this;
        synchronized(this.a.dirtyFields) {
            return var10000.a.dirtyFields.contains(var1);
        }
    }

    public void reset(boolean var1) {
        this.a.a(var1);
    }

    public void reset() {
        this.reset(false);
    }

    public Boolean isFirstBoot() {
        return this.i;
    }

    public Boolean getEnabled() {
        return this.d;
    }

    public void setEnabled(Boolean var1) {
        this.setEnabled(var1, false);
    }

    public void setEnabled(Boolean var1, boolean var2) {
        if (this.d != var1) {
            this.d = var1;
            this.a(Application.B("ᄵ处⤴㐾课\ue857跟"), var1);
            if (!var2) {
                this.b(Application.B("ᄵ处⤴㐾课\ue857跟"));
            }
        }

    }

    public Integer getTrackingMode() {
        return this.e;
    }

    public void setTrackingMode(Integer var1) {
        if (!this.e.equals(var1)) {
            this.e = var1;
            this.a(Application.B("廒콣ጋ믙┖樲Ά蕯\ud96dऔ끷ヌ"), var1);
        }

    }

    public boolean isLocationTrackingMode() {
        return this.e == 1;
    }

    public Boolean getSchedulerEnabled() {
        return this.f;
    }

    public void setSchedulerEnabled(Boolean var1) {
        if (this.f != var1) {
            this.f = var1;
            this.a(Application.B("\uf4fd悔讀䖽꓿⬹텅ܟ岷➴醄\uefb5ᄟ瀭惋擭"), var1);
        }

    }

    public void setDidDeviceReboot(boolean var1) {
        if (this.j != var1) {
            this.j = var1;
            this.a(Application.B("㿾챎⭱許㲎\udbf9䅚쇂鉍倾怗閟蠬ච叧"), var1);
        }

    }

    public Boolean getDidDeviceReboot() {
        return this.j;
    }

    public Float getOdometer() {
        return this.h;
    }

    public void setOdometer(Float var1) {
        if (!this.h.equals(var1)) {
            this.h = var1;
            this.a(Application.B("틓\u1cb5鮸ðㆯ\ue08cߜ⏡"), var1);
        }

    }

    public Float incrementOdometer(Float var1) {
        TSConfig var10000 = this;
        TSConfig var10001 = this;
        Float var2;
        this.h = var2 = this.h + var1;
        var10001.a(Application.B("\uf71a\uf134ꋨ펻蜠◸駷ओ"), var2);
        return var10000.h;
    }

    public void useCLLocationAccuracy(Boolean var1) {
        if (this.l != var1) {
            this.a(Application.B("\u0df1톷ⲉ顀䄻\ueda9⧆ꌇ똟\ue962灝⾴奝될\uf3c8ǡ鿘쭦Ἇ龵佚"), var1);
            TSConfig.Builder var2 = this.a;
            var2.desiredAccuracy = var1 ? 0 : 100;
        }

        this.l = var1;
    }

    public void setMainActivityName(String var1) {
        if (this.m != var1) {
            this.a(Application.B("侑㯡㴼銶蛌渝鍩\ueabaﶂ㝕爬\u0a0d쭟赜⮓㊳"), var1);
        }

        this.m = var1;
    }

    public String getMainActivityName() {
        return this.m;
    }

    public Boolean getIsMoving() {
        return this.a.isMoving;
    }

    public void setIsMoving(Boolean var1) {
        this.a.setIsMoving(var1);
        this.b();
    }

    public Float getDistanceFilter() {
        return this.a.distanceFilter;
    }

    public float calculateDistanceFilter(float var1) {
        if (!(var1 <= 0.0F) && !this.getDisableElasticity()) {
            if ((var1 = (float)(Math.floor((double)var1 / 5.0D + 0.5D) * 5.0D / 5.0D)) < 0.0F) {
                var1 = 0.0F;
            }

            return this.a.distanceFilter + this.a.distanceFilter * this.a.elasticityMultiplier * var1;
        } else {
            return this.a.distanceFilter;
        }
    }

    public Integer getDesiredAccuracy() {
        return this.translateDesiredAccuracy(this.a.desiredAccuracy);
    }

    public Float getDesiredOdometerAccuracy() {
        return this.a.desiredOdometerAccuracy;
    }

    public Long getLocationUpdateInterval() {
        return this.a.locationUpdateInterval;
    }

    public Long getFastestLocationUpdateInterval() {
        return this.a.fastestLocationUpdateInterval;
    }

    public Integer getLocationTimeout() {
        return this.a.locationTimeout;
    }

    public Long getDeferTime() {
        return this.a.deferTime;
    }

    public Boolean getDisableElasticity() {
        return this.a.disableElasticity;
    }

    public Float getElasticityMultiplier() {
        return this.a.elasticityMultiplier;
    }

    public Boolean getAllowIdenticalLocations() {
        return this.a.allowIdenticalLocations;
    }

    public Boolean getEnableTimestampMeta() {
        return this.a.enableTimestampMeta;
    }

    public Integer getSpeedJumpFilter() {
        return this.a.speedJumpFilter;
    }

    public Boolean getUseSignificantChangesOnly() {
        return this.a.useSignificantChangesOnly;
    }

    public String getLocationAuthorizationRequest() {
        return this.a.locationAuthorizationRequest;
    }

    public boolean requestsLocationAlways() {
        return this.a.locationAuthorizationRequest.equalsIgnoreCase(Application.B("䃖칲쪤侭硬㾑"));
    }

    public Boolean getDisableLocationAuthorizationAlert() {
        return this.a.disableLocationAuthorizationAlert;
    }

    public Long getActivityRecognitionInterval() {
        return this.a.activityRecognitionInterval;
    }

    public Integer getMinimumActivityRecognitionConfidence() {
        return this.a.minimumActivityRecognitionConfidence;
    }

    public String getTriggerActivities() {
        return this.a.triggerActivities;
    }

    public boolean hasTriggerActivity(int var1) {
        return this.o.contains(var1);
    }

    public Boolean getDisableStopDetection() {
        return this.a.disableStopDetection;
    }

    public Boolean getDisableMotionActivityUpdates() {
        return this.a.disableMotionActivityUpdates;
    }

    public Integer getStationaryRadius() {
        return this.a.stationaryRadius;
    }

    public Long getStopTimeout() {
        return this.a.stopTimeout;
    }

    public Boolean getStopOnStationary() {
        return this.a.stopOnStationary;
    }

    public Long getMotionTriggerDelay() {
        return this.a.motionTriggerDelay;
    }

    public Boolean getPersist() {
        return this.a.persist;
    }

    public Integer getPersistMode() {
        return this.a.persistMode;
    }

    public Integer getMaxDaysToPersist() {
        return this.a.maxDaysToPersist;
    }

    public Integer getMaxRecordsToPersist() {
        return this.a.maxRecordsToPersist;
    }

    public String getUrl() {
        return this.a.url;
    }

    public boolean hasUrl() {
        return this.a.url != null && !this.a.url.isEmpty();
    }

    public boolean shouldPersist(TSLocation var1) {
        if (this.a.persist && this.a.maxRecordsToPersist != 0) {
            int var2;
            if ((var2 = this.getPersistMode()) != 2) {
                if (var2 == 0) {
                    return false;
                } else if (var1.getGeofence() != null) {
                    return var2 == -1;
                } else {
                    return var2 == 1;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public String getMethod() {
        return this.a.method;
    }

    public Boolean getAutoSync() {
        return this.a.autoSync;
    }

    public Integer getAutoSyncThreshold() {
        return this.a.autoSyncThreshold;
    }

    public Boolean getBatchSync() {
        return this.a.batchSync;
    }

    public Integer getMaxBatchSize() {
        return this.a.maxBatchSize;
    }

    public JSONObject getParams() {
        return this.a.params;
    }

    public JSONObject getHeaders() {
        return this.a.headers;
    }

    public JSONObject getExtras() {
        return this.a.extras;
    }

    public String getHttpRootProperty() {
        return this.a.httpRootProperty;
    }

    public boolean hasLocationTemplate() {
        return this.a.locationTemplate.isEmpty() ^ true;
    }

    public String getLocationTemplate() {
        return this.a.locationTemplate;
    }

    public boolean hasGeofenceTemplate() {
        return this.a.geofenceTemplate.isEmpty() ^ true;
    }

    public String getGeofenceTemplate() {
        return this.a.geofenceTemplate;
    }

    public String getLocationsOrderDirection() {
        return this.a.locationsOrderDirection;
    }

    public Integer getHttpTimeout() {
        return this.a.httpTimeout;
    }

    public boolean getDisableAutoSyncOnCellular() {
        return this.a.disableAutoSyncOnCellular;
    }

    public TSAuthorization getAuthorization() {
        return this.a.authorization;
    }

    public Long getGeofenceProximityRadius() {
        return this.a.geofenceProximityRadius;
    }

    public Boolean getGeofenceInitialTriggerEntry() {
        return this.a.geofenceInitialTriggerEntry;
    }

    public Boolean getGeofenceModeHighAccuracy() {
        return this.a.geofenceModeHighAccuracy;
    }

    public Boolean getStopOnTerminate() {
        return this.a.stopOnTerminate;
    }

    public Boolean getStartOnBoot() {
        return this.a.startOnBoot;
    }

    public Integer getStopAfterElapsedMinutes() {
        return this.a.stopAfterElapsedMinutes;
    }

    public Integer getHeartbeatInterval() {
        return this.a.heartbeatInterval;
    }

    public Boolean getForegroundService() {
        return this.a.foregroundService;
    }

    public TSNotification getNotification() {
        return this.a.notification;
    }

    public TSBackgroundPermissionRationale getBackgroundPermissionRationale() {
        return this.a.backgroundPermissionRationale;
    }

    public String getConfigUrl() {
        return this.a.configUrl;
    }

    public List<String> getSchedule() {
        return this.a.schedule;
    }

    public Boolean getScheduleUseAlarmManager() {
        return this.a.scheduleUseAlarmManager;
    }

    public boolean hasSchedule() {
        return this.a.schedule.size() > 0;
    }

    public String getHeadlessJobService() {
        return this.a.headlessJobService;
    }

    public boolean hasHeadlessJobService() {
        return this.a.headlessJobService.isEmpty() ^ true;
    }

    public Boolean getEnableHeadless() {
        return this.a.enableHeadless;
    }

    public Boolean getDebug() {
        return this.a.debug;
    }

    public Integer getLogLevel() {
        return this.a.logLevel;
    }

    public Integer getLogMaxDays() {
        return this.a.logMaxDays;
    }

    public void load() {
        SharedPreferences var1;
        if (!(var1 = this.b.getSharedPreferences(r, 0)).contains(TSConfig.Builder.class.getName())) {
            this.i = true;
            this.a = new TSConfig.Builder();
            TSLog.initialize(this.getLogLevel(), this.getLogMaxDays());
            this.d();
            s.set(true);
        } else {
            TSConfig var10000 = this;
            this.l = var1.getBoolean(Application.B("釥諦曤ফ⸛ⴞ햃崃췍ꍖ曹௳㕴\uf3b5\ue80d\ue07b깵߈\uef8b眕◟"), false);
            this.m = var1.getString(Application.B("釽諴曨আ⸖ⴱ햘崉췚ꍋ曤\u0be5㕔\uf395\ue803\ue07d"), (String)null);
            this.d = var1.getBoolean(Application.B("釵諻曠ঊ⸻ⴷ했"), false);
            this.f = var1.getBoolean(Application.B("釣諶曩\u098d⸳ⴧ햀崅췞ꍧ曾\u0bfd㕸\uf398\ue80b\ue07c"), false);
            this.e = var1.getInt(Application.B("釤諧曠ঋ⸼ⴻ햂崇췡ꍍ更௹"), 1);
            this.h = var1.getFloat(Application.B("釿諱曮অ⸲\u2d26행崒"), 0.0F);

            JSONObject var2;
            try {
                var10000.n = new JSONObject(var1.getString(Application.B("釠諹更এ⸾ⴼ햟"), Application.B("釫諨")));
            } catch (JSONException var3) {
                var2 = new JSONObject.<init>();
                this.n = var2;
            }

            String var7;
            TSConfig var10001;
            if ((var7 = var1.getString(TSConfig.Builder.class.getName(), (String)null)) != null) {
                label40: {
                    var10000 = this;
                    var10001 = this;
                    this.i = true;

                    JSONException var10;
                    label41: {
                        boolean var11;
                        JSONObject var10002;
                        try {
                            var10002 = new JSONObject;
                        } catch (JSONException var5) {
                            var10 = var5;
                            var11 = false;
                            break label41;
                        }

                        var2 = var10002;

                        try {
                            var10002.<init>(var7);
                            var10001.a = new TSConfig.Builder(var2);
                            var10000.i = false;
                            s.set(true);
                            break label40;
                        } catch (JSONException var4) {
                            var10 = var4;
                            var11 = false;
                        }
                    }

                    JSONException var8 = var10;
                    Log.i(Application.B("釄諆曍ই⸴ⴳ햘崉췃ꍌ曝\u0bfd㕴\uf395\ue809\ue07d깲"), TSLog.error(var8.getMessage()));
                    TSLog.logger.error(TSLog.error(var8.getMessage()), var8);
                    this.a = new TSConfig.Builder();
                    this.d();
                }
            } else {
                TSConfig.Builder var9;
                var9 = new TSConfig.Builder.<init>();
                this.a = var9;
                this.d();
            }

            var10000 = this;
            var10001 = this;
            TSConfig var12 = this;
            TSLog.initialize(this.getLogLevel(), this.getLogMaxDays());
            a var10003 = com.transistorsoft.locationmanager.b.a.a();
            TSConfig var10004 = this;
            Context var6 = this.b;
            var10003.a(var6, var10004.n);
            var12.print();
            Sensors.getInstance(var10001.b).print();
            var10000.c();
        }
    }

    public void print() {
        StringBuilder var1;
        StringBuilder var10000 = var1 = new StringBuilder;
        TSConfig var10001 = this;
        var1.<init>();
        var1.append(TSLog.header(Application.B("퀦ፀ搾⦠⤗⩈돠ꍟ횬\u2cf7왂\ueacb牞蘱뼞敽齸ᆶ愝ᦵꕰ䴮폣ᓋ㚦㑥궉\ue171\uf859\u0fdb㛙唡鶒헩垄鼀셖\ue19f")));
        var1.append(TSLog.boxRow(DeviceInfo.getInstance(this.b).print()));

        try {
            var10000.append(var10001.toJson(true).toString(2));
        } catch (JSONException var2) {
            Log.i(Application.B("퀦ፀ搾⦠⤗⩈돠ꍟ횬\u2cf7왂\ueacb牞蘱뼞敽齸"), TSLog.error(var2.getMessage()));
            var2.printStackTrace();
        }

        TSLog.logger.info(var1.toString());
        var10000 = var1 = new StringBuilder;
        var1.<init>();
        var10000.append(Sensors.getInstance(this.b).print().toString());
        TSLog.logger.info(var1.toString());
    }

    public Integer translateDesiredAccuracy(Integer var1) {
        TSLog.logger.debug(Application.B("ឳᛪ⦋ꓫ動\uefe4壈똋枞摜踟㹸퍹\uf858ዦ✓㍭誧烾Ὡ亣\ueea8ᐤ枧㡎옳") + this.l + Application.B("\u17eeᚢ⧊") + var1);
        if (!this.l) {
            return var1;
        } else {
            Integer var10000;
            int var2;
            if ((var2 = var1) != -2 && var2 != -1 && var2 != 0) {
                if (var2 != 10) {
                    if (var2 == 100) {
                        var10000 = 104;
                        return var10000;
                    }

                    if (var2 == 1000 || var2 == 3000) {
                        var10000 = 105;
                        return var10000;
                    }
                }

                var10000 = 102;
            } else {
                var10000 = 100;
            }

            return var10000;
        }
    }

    public void setPluginForEvent(int param1, String param2) {
        // $FF: Couldn't be decompiled
    }

    public int getPluginForEvent(String param1) {
        // $FF: Couldn't be decompiled
    }

    public void loadConfig(final TSCallback var1) {
        if (!this.a.configUrl.isEmpty()) {
            OkHttpClient var2 = HttpService.getInstance(this.b).getClient();
            okhttp3.Request.Builder var3 = (new okhttp3.Request.Builder()).url(this.a.configUrl).get();
            JSONObject var4;
            if ((var4 = this.a.headers) != null) {
                Iterator var5 = var4.keys();

                label52:
                while(true) {
                    JSONException var10000;
                    while(true) {
                        boolean var12;
                        boolean var10001;
                        try {
                            var12 = var5.hasNext();
                        } catch (JSONException var10) {
                            var10000 = var10;
                            var10001 = false;
                            break;
                        }

                        if (!var12) {
                            break label52;
                        }

                        String var13;
                        try {
                            var13 = (String)var5.next();
                        } catch (JSONException var9) {
                            var10000 = var9;
                            var10001 = false;
                            break;
                        }

                        String var6 = var13;

                        try {
                            var12 = var13.equalsIgnoreCase(Application.B("䢩ꖿퟔ绱輂鐬음ᾅ䵞⬴蜈⑭"));
                        } catch (JSONException var8) {
                            var10000 = var8;
                            var10001 = false;
                            break;
                        }

                        if (!var12) {
                            try {
                                var3.header(var6, var4.getString(var6));
                            } catch (JSONException var7) {
                                var10000 = var7;
                                var10001 = false;
                                break;
                            }
                        }
                    }

                    JSONException var11 = var10000;
                    var1.onFailure(Application.B("䢏ꖢ\ud7c8绪輕鑢읈Έ䵘⬾蜑⑦⢥칔䥒뜩ᖖꕖ냣ꂯ┢묗聬") + var11.getMessage());
                    return;
                }
            }

            var2.newCall(var3.build()).enqueue(new Callback() {
                public void onFailure(@NotNull Call var1x, IOException var2) {
                    var1.onFailure(Application.B("갻뼣㦘爡䁴歽惈屾쀆ꨜ鵞齃㼄\uf5e1쎳ᰇ㶑ᵽ搯穻施ꮟ\u2e77双⬳킟") + var2.getMessage());
                }

                public void onResponse(@NotNull Call param1, @NotNull Response param2) {
                    // $FF: Couldn't be decompiled
                }
            });
        }
    }

    private class c extends AsyncTask<Void, Void, Void> {
        private c() {
        }

        protected Void a(Void... var1) {
            ForegroundNotification.createNotificationChannel(TSConfig.this.b);
            LifecycleManager.f().a(new com.transistorsoft.locationmanager.lifecycle.LifecycleManager.b() {
                public void a(boolean var1) {
                    if (TSConfig.this.d && TSConfig.this.getStopOnTerminate()) {
                        TSConfig.this.setEnabled(false);
                        TSLog.logger.info(TSLog.warn(Application.B("ᕝ⃜㟎ꀄ\u070f깄韃亄শ擗㏌抐圛ﰓ\ue476쁫殏沴\u0c77ᅗ翝䕁㗙⤑⓸써䭍綄㺝ⷿ滢Ἂ\ueaa0孨죠\ud8ec\udd22奍轵纾\ue13b䧿퉗ꒁ㵑⒈蘴뀠僔\uefec貎⿅横ᜢ\udffd༮\uf4a3˽ꬒ㞳荘\udf78റ[䈸퍻\ud885ﬂ\ue91c⦃骫庇䝻索掿독篍")));
                        TrackingService.stop(TSConfig.this.b);
                        if (var1) {
                            BackgroundGeolocation.getInstance(TSConfig.this.b).onActivityDestroy();
                        }
                    }

                }
            });
            return null;
        }
    }

    public interface OnChangeCallback {
        void a(TSConfig var1);
    }

    public static class Builder {
        private static final Set<String> IGNORED_FIELDS = new HashSet(Arrays.asList(Application.B("ዘꩩ⛶㺪㚏\udbc4ꈯ\ude32챸⻡㼺")));
        private final List<String> dirtyFields;
        private Boolean isMoving;
        private Float distanceFilter;
        private Integer desiredAccuracy;
        private Float desiredOdometerAccuracy;
        private Long locationUpdateInterval;
        private Long fastestLocationUpdateInterval;
        private Integer locationTimeout;
        private Long deferTime;
        private Boolean disableElasticity;
        private Float elasticityMultiplier;
        private Boolean allowIdenticalLocations;
        private Boolean enableTimestampMeta;
        private Integer speedJumpFilter;
        private Boolean useSignificantChangesOnly;
        private Boolean disableLocationAuthorizationAlert;
        private String locationAuthorizationRequest;
        private Long activityRecognitionInterval;
        private Integer minimumActivityRecognitionConfidence;
        private String triggerActivities;
        private Boolean disableStopDetection;
        private Boolean disableMotionActivityUpdates;
        private Integer stationaryRadius;
        private Long stopTimeout;
        private Boolean stopOnStationary;
        private Long motionTriggerDelay;
        private Boolean persist;
        private Integer persistMode;
        private Integer maxDaysToPersist;
        private Integer maxRecordsToPersist;
        private String url;
        private String method;
        private Boolean autoSync;
        private Integer autoSyncThreshold;
        private Boolean batchSync;
        private Integer maxBatchSize;
        private JSONObject params;
        private JSONObject headers;
        private JSONObject extras;
        private String httpRootProperty;
        private String locationTemplate;
        private String geofenceTemplate;
        private String locationsOrderDirection;
        private Integer httpTimeout;
        private Boolean disableAutoSyncOnCellular;
        private TSAuthorization authorization;
        private Long geofenceProximityRadius;
        private Boolean geofenceInitialTriggerEntry;
        private Boolean geofenceModeHighAccuracy;
        private Boolean stopOnTerminate;
        private Boolean startOnBoot;
        private Integer stopAfterElapsedMinutes;
        private Integer heartbeatInterval;
        private Boolean foregroundService;
        private TSNotification notification;
        private TSBackgroundPermissionRationale backgroundPermissionRationale;
        private String configUrl;
        private List<String> schedule;
        private Boolean scheduleUseAlarmManager;
        private String headlessJobService;
        private Boolean enableHeadless;
        private Boolean debug;
        private Integer logLevel;
        private Integer logMaxDays;

        public Builder() {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            super();
            ArrayList var1;
            var1 = new ArrayList.<init>();
            var10001.dirtyFields = var1;
            var10000.a();
        }

        public Builder(JSONObject var1) {
            ArrayList var2;
            var2 = new ArrayList.<init>();
            this.dirtyFields = var2;
            this.a();
            Field[] var88;
            int var3 = (var88 = TSConfig.Builder.class.getDeclaredFields()).length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Field var5;
                Field var10001 = var5 = var88[var4];
                String var6 = var10001.getName();
                Class var7 = var10001.getType();
                if (var1.has(var6) && this.a(var5)) {
                    JSONException var96;
                    label349: {
                        IllegalAccessException var10000;
                        label348: {
                            Class var97;
                            boolean var98;
                            Class var99;
                            try {
                                var97 = var7;
                                var99 = Boolean.class;
                            } catch (JSONException var86) {
                                var96 = var86;
                                var98 = false;
                                break label349;
                            } catch (IllegalAccessException var87) {
                                var10000 = var87;
                                var98 = false;
                                break label348;
                            }

                            if (var97 == var99) {
                                try {
                                    var5.set(this, var1.getBoolean(var6));
                                    continue;
                                } catch (JSONException var10) {
                                    var96 = var10;
                                    var98 = false;
                                    break label349;
                                } catch (IllegalAccessException var11) {
                                    var10000 = var11;
                                    var98 = false;
                                }
                            } else {
                                label344: {
                                    try {
                                        var97 = var7;
                                        var99 = Long.class;
                                    } catch (JSONException var84) {
                                        var96 = var84;
                                        var98 = false;
                                        break label349;
                                    } catch (IllegalAccessException var85) {
                                        var10000 = var85;
                                        var98 = false;
                                        break label344;
                                    }

                                    if (var97 == var99) {
                                        try {
                                            var5.set(this, var1.getLong(var6));
                                            continue;
                                        } catch (JSONException var12) {
                                            var96 = var12;
                                            var98 = false;
                                            break label349;
                                        } catch (IllegalAccessException var13) {
                                            var10000 = var13;
                                            var98 = false;
                                        }
                                    } else {
                                        label340: {
                                            try {
                                                var97 = var7;
                                                var99 = Integer.class;
                                            } catch (JSONException var82) {
                                                var96 = var82;
                                                var98 = false;
                                                break label349;
                                            } catch (IllegalAccessException var83) {
                                                var10000 = var83;
                                                var98 = false;
                                                break label340;
                                            }

                                            if (var97 == var99) {
                                                try {
                                                    var5.set(this, var1.getInt(var6));
                                                    continue;
                                                } catch (JSONException var14) {
                                                    var96 = var14;
                                                    var98 = false;
                                                    break label349;
                                                } catch (IllegalAccessException var15) {
                                                    var10000 = var15;
                                                    var98 = false;
                                                }
                                            } else {
                                                label336: {
                                                    try {
                                                        var97 = var7;
                                                        var99 = Float.class;
                                                    } catch (JSONException var80) {
                                                        var96 = var80;
                                                        var98 = false;
                                                        break label349;
                                                    } catch (IllegalAccessException var81) {
                                                        var10000 = var81;
                                                        var98 = false;
                                                        break label336;
                                                    }

                                                    Field var103;
                                                    TSConfig.Builder var104;
                                                    if (var97 == var99) {
                                                        label356: {
                                                            double var10002;
                                                            try {
                                                                var103 = var5;
                                                                var104 = this;
                                                                var10002 = var1.getDouble(var6);
                                                            } catch (JSONException var18) {
                                                                var96 = var18;
                                                                var98 = false;
                                                                break label349;
                                                            } catch (IllegalAccessException var19) {
                                                                var10000 = var19;
                                                                var98 = false;
                                                                break label356;
                                                            }

                                                            float var100 = (float)var10002;

                                                            try {
                                                                var103.set(var104, var100);
                                                                continue;
                                                            } catch (JSONException var16) {
                                                                var96 = var16;
                                                                var98 = false;
                                                                break label349;
                                                            } catch (IllegalAccessException var17) {
                                                                var10000 = var17;
                                                                var98 = false;
                                                            }
                                                        }
                                                    } else {
                                                        label332: {
                                                            try {
                                                                var97 = var7;
                                                                var99 = Double.class;
                                                            } catch (JSONException var78) {
                                                                var96 = var78;
                                                                var98 = false;
                                                                break label349;
                                                            } catch (IllegalAccessException var79) {
                                                                var10000 = var79;
                                                                var98 = false;
                                                                break label332;
                                                            }

                                                            if (var97 == var99) {
                                                                try {
                                                                    var5.set(this, var1.getDouble(var6));
                                                                    continue;
                                                                } catch (JSONException var20) {
                                                                    var96 = var20;
                                                                    var98 = false;
                                                                    break label349;
                                                                } catch (IllegalAccessException var21) {
                                                                    var10000 = var21;
                                                                    var98 = false;
                                                                }
                                                            } else {
                                                                label328: {
                                                                    try {
                                                                        var97 = var7;
                                                                        var99 = JSONObject.class;
                                                                    } catch (JSONException var76) {
                                                                        var96 = var76;
                                                                        var98 = false;
                                                                        break label349;
                                                                    } catch (IllegalAccessException var77) {
                                                                        var10000 = var77;
                                                                        var98 = false;
                                                                        break label328;
                                                                    }

                                                                    if (var97 == var99) {
                                                                        try {
                                                                            var5.set(this, var1.getJSONObject(var6));
                                                                            continue;
                                                                        } catch (JSONException var22) {
                                                                            var96 = var22;
                                                                            var98 = false;
                                                                            break label349;
                                                                        } catch (IllegalAccessException var23) {
                                                                            var10000 = var23;
                                                                            var98 = false;
                                                                        }
                                                                    } else {
                                                                        label324: {
                                                                            try {
                                                                                var97 = var7;
                                                                                var99 = JSONArray.class;
                                                                            } catch (JSONException var74) {
                                                                                var96 = var74;
                                                                                var98 = false;
                                                                                break label349;
                                                                            } catch (IllegalAccessException var75) {
                                                                                var10000 = var75;
                                                                                var98 = false;
                                                                                break label324;
                                                                            }

                                                                            if (var97 == var99) {
                                                                                try {
                                                                                    var5.set(this, var1.getJSONArray(var6));
                                                                                    continue;
                                                                                } catch (JSONException var24) {
                                                                                    var96 = var24;
                                                                                    var98 = false;
                                                                                    break label349;
                                                                                } catch (IllegalAccessException var25) {
                                                                                    var10000 = var25;
                                                                                    var98 = false;
                                                                                }
                                                                            } else {
                                                                                label357: {
                                                                                    try {
                                                                                        var97 = var7;
                                                                                        var99 = List.class;
                                                                                    } catch (JSONException var72) {
                                                                                        var96 = var72;
                                                                                        var98 = false;
                                                                                        break label349;
                                                                                    } catch (IllegalAccessException var73) {
                                                                                        var10000 = var73;
                                                                                        var98 = false;
                                                                                        break label357;
                                                                                    }

                                                                                    Object var94;
                                                                                    if (var97 == var99) {
                                                                                        JSONArray var105;
                                                                                        try {
                                                                                            var105 = var1.getJSONArray(var6);
                                                                                        } catch (JSONException var68) {
                                                                                            var96 = var68;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var69) {
                                                                                            var10000 = var69;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        JSONArray var91 = var105;

                                                                                        ArrayList var106;
                                                                                        try {
                                                                                            var106 = new ArrayList;
                                                                                        } catch (JSONException var66) {
                                                                                            var96 = var66;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var67) {
                                                                                            var10000 = var67;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        var94 = var106;

                                                                                        try {
                                                                                            var106.<init>();
                                                                                        } catch (JSONException var64) {
                                                                                            var96 = var64;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var65) {
                                                                                            var10000 = var65;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        int var8 = 0;

                                                                                        while(true) {
                                                                                            int var107;
                                                                                            int var108;
                                                                                            try {
                                                                                                var107 = var8;
                                                                                                var108 = var91.length();
                                                                                            } catch (JSONException var60) {
                                                                                                var96 = var60;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            } catch (IllegalAccessException var61) {
                                                                                                var10000 = var61;
                                                                                                var98 = false;
                                                                                                break label357;
                                                                                            }

                                                                                            if (var107 >= var108) {
                                                                                                break;
                                                                                            }

                                                                                            try {
                                                                                                ((List)var94).add(var91.getString(var8));
                                                                                            } catch (JSONException var62) {
                                                                                                var96 = var62;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            } catch (IllegalAccessException var63) {
                                                                                                var10000 = var63;
                                                                                                var98 = false;
                                                                                                break label357;
                                                                                            }

                                                                                            ++var8;
                                                                                        }
                                                                                    } else {
                                                                                        try {
                                                                                            var97 = var7;
                                                                                            var99 = Map.class;
                                                                                        } catch (JSONException var70) {
                                                                                            var96 = var70;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var71) {
                                                                                            var10000 = var71;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        if (var97 != var99) {
                                                                                            try {
                                                                                                var97 = var7;
                                                                                                var99 = TSNotification.class;
                                                                                            } catch (JSONException var46) {
                                                                                                var96 = var46;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            } catch (IllegalAccessException var47) {
                                                                                                var10000 = var47;
                                                                                                var98 = false;
                                                                                                break label357;
                                                                                            }

                                                                                            JSONObject var89;
                                                                                            JSONObject var102;
                                                                                            if (var97 == var99) {
                                                                                                try {
                                                                                                    var103 = var5;
                                                                                                    var104 = this;
                                                                                                    var102 = var1.getJSONObject(var6);
                                                                                                } catch (JSONException var30) {
                                                                                                    var96 = var30;
                                                                                                    var98 = false;
                                                                                                    break label349;
                                                                                                } catch (IllegalAccessException var31) {
                                                                                                    var10000 = var31;
                                                                                                    var98 = false;
                                                                                                    break label357;
                                                                                                }

                                                                                                var89 = var102;

                                                                                                try {
                                                                                                    var103.set(var104, new TSNotification(var89, true));
                                                                                                    continue;
                                                                                                } catch (JSONException var28) {
                                                                                                    var96 = var28;
                                                                                                    var98 = false;
                                                                                                    break label349;
                                                                                                } catch (IllegalAccessException var29) {
                                                                                                    var10000 = var29;
                                                                                                    var98 = false;
                                                                                                    break label357;
                                                                                                }
                                                                                            } else {
                                                                                                try {
                                                                                                    var97 = var7;
                                                                                                    var99 = TSAuthorization.class;
                                                                                                } catch (JSONException var44) {
                                                                                                    var96 = var44;
                                                                                                    var98 = false;
                                                                                                    break label349;
                                                                                                } catch (IllegalAccessException var45) {
                                                                                                    var10000 = var45;
                                                                                                    var98 = false;
                                                                                                    break label357;
                                                                                                }

                                                                                                if (var97 == var99) {
                                                                                                    try {
                                                                                                        var103 = var5;
                                                                                                        var104 = this;
                                                                                                        var102 = var1.getJSONObject(var6);
                                                                                                    } catch (JSONException var34) {
                                                                                                        var96 = var34;
                                                                                                        var98 = false;
                                                                                                        break label349;
                                                                                                    } catch (IllegalAccessException var35) {
                                                                                                        var10000 = var35;
                                                                                                        var98 = false;
                                                                                                        break label357;
                                                                                                    }

                                                                                                    var89 = var102;

                                                                                                    try {
                                                                                                        var103.set(var104, new TSAuthorization(var89, true));
                                                                                                        continue;
                                                                                                    } catch (JSONException var32) {
                                                                                                        var96 = var32;
                                                                                                        var98 = false;
                                                                                                        break label349;
                                                                                                    } catch (IllegalAccessException var33) {
                                                                                                        var10000 = var33;
                                                                                                        var98 = false;
                                                                                                        break label357;
                                                                                                    }
                                                                                                } else {
                                                                                                    try {
                                                                                                        var97 = var7;
                                                                                                        var99 = TSBackgroundPermissionRationale.class;
                                                                                                    } catch (JSONException var42) {
                                                                                                        var96 = var42;
                                                                                                        var98 = false;
                                                                                                        break label349;
                                                                                                    } catch (IllegalAccessException var43) {
                                                                                                        var10000 = var43;
                                                                                                        var98 = false;
                                                                                                        break label357;
                                                                                                    }

                                                                                                    if (var97 == var99) {
                                                                                                        try {
                                                                                                            var103 = var5;
                                                                                                            var104 = this;
                                                                                                            var102 = var1.getJSONObject(var6);
                                                                                                        } catch (JSONException var38) {
                                                                                                            var96 = var38;
                                                                                                            var98 = false;
                                                                                                            break label349;
                                                                                                        } catch (IllegalAccessException var39) {
                                                                                                            var10000 = var39;
                                                                                                            var98 = false;
                                                                                                            break label357;
                                                                                                        }

                                                                                                        var89 = var102;

                                                                                                        try {
                                                                                                            var103.set(var104, new TSBackgroundPermissionRationale(var89, true));
                                                                                                            continue;
                                                                                                        } catch (JSONException var36) {
                                                                                                            var96 = var36;
                                                                                                            var98 = false;
                                                                                                            break label349;
                                                                                                        } catch (IllegalAccessException var37) {
                                                                                                            var10000 = var37;
                                                                                                            var98 = false;
                                                                                                            break label357;
                                                                                                        }
                                                                                                    } else {
                                                                                                        try {
                                                                                                            var5.set(this, var1.get(var6));
                                                                                                            continue;
                                                                                                        } catch (JSONException var40) {
                                                                                                            var96 = var40;
                                                                                                            var98 = false;
                                                                                                            break label349;
                                                                                                        } catch (IllegalAccessException var41) {
                                                                                                            var10000 = var41;
                                                                                                            var98 = false;
                                                                                                            break label357;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }

                                                                                        JSONObject var109;
                                                                                        try {
                                                                                            var109 = var1.getJSONObject(var6);
                                                                                        } catch (JSONException var58) {
                                                                                            var96 = var58;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var59) {
                                                                                            var10000 = var59;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        JSONObject var93 = var109;

                                                                                        HashMap var110;
                                                                                        try {
                                                                                            var110 = new HashMap;
                                                                                        } catch (JSONException var56) {
                                                                                            var96 = var56;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var57) {
                                                                                            var10000 = var57;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        var94 = var110;

                                                                                        Iterator var111;
                                                                                        try {
                                                                                            var110.<init>();
                                                                                            var111 = var109.keys();
                                                                                        } catch (JSONException var54) {
                                                                                            var96 = var54;
                                                                                            var98 = false;
                                                                                            break label349;
                                                                                        } catch (IllegalAccessException var55) {
                                                                                            var10000 = var55;
                                                                                            var98 = false;
                                                                                            break label357;
                                                                                        }

                                                                                        Iterator var95 = var111;

                                                                                        while(true) {
                                                                                            boolean var112;
                                                                                            try {
                                                                                                var112 = var95.hasNext();
                                                                                            } catch (JSONException var48) {
                                                                                                var96 = var48;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            } catch (IllegalAccessException var49) {
                                                                                                var10000 = var49;
                                                                                                var98 = false;
                                                                                                break label357;
                                                                                            }

                                                                                            if (!var112) {
                                                                                                break;
                                                                                            }

                                                                                            String var101;
                                                                                            Object var113;
                                                                                            JSONObject var114;
                                                                                            try {
                                                                                                var113 = var94;
                                                                                                var114 = var93;
                                                                                                var101 = (String)var95.next();
                                                                                            } catch (JSONException var52) {
                                                                                                var96 = var52;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            } catch (IllegalAccessException var53) {
                                                                                                var10000 = var53;
                                                                                                var98 = false;
                                                                                                break label357;
                                                                                            }

                                                                                            String var9 = var101;

                                                                                            try {
                                                                                                ((Map)var113).put(var9, var114.getString(var101));
                                                                                            } catch (JSONException var50) {
                                                                                                var96 = var50;
                                                                                                var98 = false;
                                                                                                break label349;
                                                                                            } catch (IllegalAccessException var51) {
                                                                                                var10000 = var51;
                                                                                                var98 = false;
                                                                                                break label357;
                                                                                            }
                                                                                        }
                                                                                    }

                                                                                    try {
                                                                                        var5.set(this, var94);
                                                                                        continue;
                                                                                    } catch (JSONException var26) {
                                                                                        var96 = var26;
                                                                                        var98 = false;
                                                                                        break label349;
                                                                                    } catch (IllegalAccessException var27) {
                                                                                        var10000 = var27;
                                                                                        var98 = false;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        IllegalAccessException var90 = var10000;
                        TSLog.logger.error(TSLog.error(var90.getMessage()), var90);
                        continue;
                    }

                    JSONException var92 = var96;
                    TSLog.logger.error(TSLog.error(var92.getMessage()), var92);
                }
            }

        }

        private void a() {
            this.distanceFilter = 10.0F;
            this.desiredAccuracy = 100;
            if (TSConfig.q != null && TSConfig.q.l) {
                this.desiredAccuracy = 0;
            }

            this.desiredOdometerAccuracy = 100.0F;
            this.locationUpdateInterval = 1000L;
            this.fastestLocationUpdateInterval = -1L;
            this.locationTimeout = 60;
            this.deferTime = 0L;
            this.disableElasticity = false;
            this.elasticityMultiplier = 1.0F;
            this.allowIdenticalLocations = false;
            this.enableTimestampMeta = false;
            this.speedJumpFilter = 300;
            this.useSignificantChangesOnly = false;
            this.locationAuthorizationRequest = Application.B("额磃뙈螭剆꾴");
            this.disableLocationAuthorizationAlert = false;
            this.isMoving = false;
            this.activityRecognitionInterval = 10000L;
            this.minimumActivityRecognitionConfidence = 75;
            this.triggerActivities = Application.B("颵磁뙠螺剚꾯峺솶ዴ푓茤ﳲ挟\ue425\udc8f쿄忥Ⴇ㉪繤ꋼ첍\ue01c拓䔻傐唥縝盶䂢쎱\ufff2\ue497잇鯬肂縊۽徳祮劳\u1c93\uf2d0﨑睭툣䀉⺐毒");
            this.disableStopDetection = false;
            this.disableMotionActivityUpdates = false;
            this.stationaryRadius = 25;
            this.stopTimeout = 5L;
            this.stopOnStationary = false;
            this.motionTriggerDelay = 0L;
            this.persist = true;
            this.persistMode = 2;
            this.maxDaysToPersist = 1;
            this.maxRecordsToPersist = -1;
            this.url = "";
            this.method = Application.B("颌磠뙬螘");
            this.autoSync = true;
            this.autoSyncThreshold = TSConfig.DEFAULT_AUTO_SYNC_THRESHOLD;
            this.batchSync = false;
            this.maxBatchSize = -1;
            this.params = new JSONObject();
            this.headers = new JSONObject();
            this.extras = new JSONObject();
            this.httpRootProperty = Application.B("颰磀뙜螭剋꾮峼솻");
            this.locationTemplate = "";
            this.geofenceTemplate = "";
            this.locationsOrderDirection = Application.B("额磼뙼");
            this.httpTimeout = 60000;
            this.disableAutoSyncOnCellular = false;
            (this.authorization = new TSAuthorization()).applyDefaults();
            this.geofenceProximityRadius = 1000L;
            this.geofenceInitialTriggerEntry = true;
            this.geofenceModeHighAccuracy = false;
            this.stopOnTerminate = true;
            this.startOnBoot = false;
            this.stopAfterElapsedMinutes = 0;
            this.heartbeatInterval = -1;
            this.foregroundService = true;
            (this.notification = new TSNotification()).applyDefaults();
            (this.backgroundPermissionRationale = new TSBackgroundPermissionRationale()).applyDefaults();
            this.configUrl = "";
            this.schedule = TSConfig.DEFAULT_SCHEDULE;
            this.scheduleUseAlarmManager = false;
            this.headlessJobService = "";
            this.enableHeadless = false;
            this.debug = false;
            this.logLevel = 0;
            this.logMaxDays = 3;
        }

        private TSConfig.Builder b() {
            TSConfig.Builder var10000 = this;
            List var1;
            List var10001 = var1 = this.dirtyFields;
            TSConfig.Builder var10002 = this;
            synchronized(var1) {
                var10002.dirtyFields.clear();
                return var10000;
            }
        }

        private void a(boolean var1) {
            TSConfig.Builder var2;
            var2 = new TSConfig.Builder.<init>();
            Field[] var3;
            int var4 = (var3 = TSConfig.Builder.class.getDeclaredFields()).length;

            TSConfig.Builder var10001;
            for(int var5 = 0; var5 < var4; ++var5) {
                Field var6;
                if (this.a(var6 = var3[var5])) {
                    Field var10000 = var6;
                    var10001 = var2;
                    TSConfig.Builder var10002 = this;
                    String var7 = var6.getName();

                    NoSuchMethodException var33;
                    label93: {
                        IllegalAccessException var32;
                        label92: {
                            InvocationTargetException var31;
                            label101: {
                                String var10003;
                                boolean var34;
                                try {
                                    var10003 = Application.B("颯磊뙋") + var7.substring(0, 1).toUpperCase() + var7.substring(1);
                                } catch (NoSuchMethodException var21) {
                                    var33 = var21;
                                    var34 = false;
                                    break label93;
                                } catch (IllegalAccessException var22) {
                                    var32 = var22;
                                    var34 = false;
                                    break label92;
                                } catch (InvocationTargetException var23) {
                                    var31 = var23;
                                    var34 = false;
                                    break label101;
                                }

                                var7 = var10003;

                                Class[] var10004;
                                Class var35;
                                try {
                                    var35 = var10002.getClass();
                                    var10003 = var7;
                                    var10004 = new Class[1];
                                } catch (NoSuchMethodException var18) {
                                    var33 = var18;
                                    var34 = false;
                                    break label93;
                                } catch (IllegalAccessException var19) {
                                    var32 = var19;
                                    var34 = false;
                                    break label92;
                                } catch (InvocationTargetException var20) {
                                    var31 = var20;
                                    var34 = false;
                                    break label101;
                                }

                                Class[] var10005 = var10004;
                                Field var10006 = var6;
                                byte var25 = 0;

                                Method var36;
                                try {
                                    var10005[var25] = var10006.getType();
                                    var36 = var35.getMethod(var10003, var10004);
                                } catch (NoSuchMethodException var15) {
                                    var33 = var15;
                                    var34 = false;
                                    break label93;
                                } catch (IllegalAccessException var16) {
                                    var32 = var16;
                                    var34 = false;
                                    break label92;
                                } catch (InvocationTargetException var17) {
                                    var31 = var17;
                                    var34 = false;
                                    break label101;
                                }

                                Method var26 = var36;

                                Object var37;
                                try {
                                    var37 = var10000.get(var10001);
                                } catch (NoSuchMethodException var12) {
                                    var33 = var12;
                                    var34 = false;
                                    break label93;
                                } catch (IllegalAccessException var13) {
                                    var32 = var13;
                                    var34 = false;
                                    break label92;
                                } catch (InvocationTargetException var14) {
                                    var31 = var14;
                                    var34 = false;
                                    break label101;
                                }

                                Object var30 = var37;
                                if (var37 == null) {
                                    continue;
                                }

                                try {
                                    var26.invoke(this, var30);
                                    continue;
                                } catch (NoSuchMethodException var9) {
                                    var33 = var9;
                                    var34 = false;
                                    break label93;
                                } catch (IllegalAccessException var10) {
                                    var32 = var10;
                                    var34 = false;
                                    break label92;
                                } catch (InvocationTargetException var11) {
                                    var31 = var11;
                                    var34 = false;
                                }
                            }

                            InvocationTargetException var27 = var31;
                            Log.e(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var27.getMessage()), var27);
                            continue;
                        }

                        IllegalAccessException var28 = var32;
                        Log.e(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var28.getMessage()), var28);
                        continue;
                    }

                    NoSuchMethodException var29 = var33;
                    Log.e(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var29.getMessage()), var29);
                }
            }

            if (var1) {
                List var24;
                List var38 = var24 = this.dirtyFields;
                var10001 = this;
                synchronized(var24) {
                    var10001.dirtyFields.clear();
                }
            }

            this.commit();
        }

        private void a(JSONObject var1) {
            List var2;
            List var10000 = var2 = this.dirtyFields;
            TSConfig.Builder var10001 = this;
            synchronized(var2) {
                var10001.dirtyFields.clear();
            }

            Field[] var239;
            int var3 = (var239 = TSConfig.Builder.class.getDeclaredFields()).length;

            label749:
            for(int var4 = 0; var4 < var3; ++var4) {
                Field var5;
                String var6;
                if (var1.has(var6 = (var5 = var239[var4]).getName()) && this.a(var5)) {
                    JSONException var255;
                    label744: {
                        NoSuchMethodException var254;
                        label743: {
                            IllegalAccessException var253;
                            label742: {
                                InvocationTargetException var252;
                                label753: {
                                    boolean var256;
                                    Field var257;
                                    String var10002;
                                    try {
                                        var257 = var5;
                                        var10001 = this;
                                        var10002 = Application.B("颯磊뙋") + var6.substring(0, 1).toUpperCase() + var6.substring(1);
                                    } catch (JSONException var235) {
                                        var255 = var235;
                                        var256 = false;
                                        break label744;
                                    } catch (NoSuchMethodException var236) {
                                        var254 = var236;
                                        var256 = false;
                                        break label743;
                                    } catch (IllegalAccessException var237) {
                                        var253 = var237;
                                        var256 = false;
                                        break label742;
                                    } catch (InvocationTargetException var238) {
                                        var252 = var238;
                                        var256 = false;
                                        break label753;
                                    }

                                    String var7 = var10002;

                                    Class var260;
                                    Class[] var10003;
                                    try {
                                        var260 = var10001.getClass();
                                        var10002 = var7;
                                        var10003 = new Class[1];
                                    } catch (JSONException var231) {
                                        var255 = var231;
                                        var256 = false;
                                        break label744;
                                    } catch (NoSuchMethodException var232) {
                                        var254 = var232;
                                        var256 = false;
                                        break label743;
                                    } catch (IllegalAccessException var233) {
                                        var253 = var233;
                                        var256 = false;
                                        break label742;
                                    } catch (InvocationTargetException var234) {
                                        var252 = var234;
                                        var256 = false;
                                        break label753;
                                    }

                                    Class[] var10004 = var10003;
                                    Field var10005 = var5;
                                    byte var243 = 0;

                                    Method var265;
                                    try {
                                        var10004[var243] = var10005.getType();
                                        var265 = var260.getMethod(var10002, var10003);
                                    } catch (JSONException var227) {
                                        var255 = var227;
                                        var256 = false;
                                        break label744;
                                    } catch (NoSuchMethodException var228) {
                                        var254 = var228;
                                        var256 = false;
                                        break label743;
                                    } catch (IllegalAccessException var229) {
                                        var253 = var229;
                                        var256 = false;
                                        break label742;
                                    } catch (InvocationTargetException var230) {
                                        var252 = var230;
                                        var256 = false;
                                        break label753;
                                    }

                                    Method var244 = var265;

                                    Class var266;
                                    try {
                                        var266 = var257.getType();
                                        var260 = Boolean.class;
                                    } catch (JSONException var223) {
                                        var255 = var223;
                                        var256 = false;
                                        break label744;
                                    } catch (NoSuchMethodException var224) {
                                        var254 = var224;
                                        var256 = false;
                                        break label743;
                                    } catch (IllegalAccessException var225) {
                                        var253 = var225;
                                        var256 = false;
                                        break label742;
                                    } catch (InvocationTargetException var226) {
                                        var252 = var226;
                                        var256 = false;
                                        break label753;
                                    }

                                    byte var240;
                                    Object[] var258;
                                    Object[] var259;
                                    JSONObject var261;
                                    String var262;
                                    Method var268;
                                    if (var266 == var260) {
                                        label754: {
                                            try {
                                                var268 = var244;
                                                var10001 = this;
                                                var258 = new Object[1];
                                            } catch (JSONException var15) {
                                                var255 = var15;
                                                var256 = false;
                                                break label744;
                                            } catch (NoSuchMethodException var16) {
                                                var254 = var16;
                                                var256 = false;
                                                break label743;
                                            } catch (IllegalAccessException var17) {
                                                var253 = var17;
                                                var256 = false;
                                                break label742;
                                            } catch (InvocationTargetException var18) {
                                                var252 = var18;
                                                var256 = false;
                                                break label754;
                                            }

                                            var259 = var258;
                                            var261 = var1;
                                            var262 = var6;
                                            var240 = 0;

                                            try {
                                                var259[var240] = var261.getBoolean(var262);
                                                var268.invoke(var10001, var258);
                                                continue;
                                            } catch (JSONException var11) {
                                                var255 = var11;
                                                var256 = false;
                                                break label744;
                                            } catch (NoSuchMethodException var12) {
                                                var254 = var12;
                                                var256 = false;
                                                break label743;
                                            } catch (IllegalAccessException var13) {
                                                var253 = var13;
                                                var256 = false;
                                                break label742;
                                            } catch (InvocationTargetException var14) {
                                                var252 = var14;
                                                var256 = false;
                                            }
                                        }
                                    } else {
                                        label725: {
                                            try {
                                                var266 = var5.getType();
                                                var260 = Long.class;
                                            } catch (JSONException var219) {
                                                var255 = var219;
                                                var256 = false;
                                                break label744;
                                            } catch (NoSuchMethodException var220) {
                                                var254 = var220;
                                                var256 = false;
                                                break label743;
                                            } catch (IllegalAccessException var221) {
                                                var253 = var221;
                                                var256 = false;
                                                break label742;
                                            } catch (InvocationTargetException var222) {
                                                var252 = var222;
                                                var256 = false;
                                                break label725;
                                            }

                                            if (var266 == var260) {
                                                label755: {
                                                    try {
                                                        var268 = var244;
                                                        var10001 = this;
                                                        var258 = new Object[1];
                                                    } catch (JSONException var23) {
                                                        var255 = var23;
                                                        var256 = false;
                                                        break label744;
                                                    } catch (NoSuchMethodException var24) {
                                                        var254 = var24;
                                                        var256 = false;
                                                        break label743;
                                                    } catch (IllegalAccessException var25) {
                                                        var253 = var25;
                                                        var256 = false;
                                                        break label742;
                                                    } catch (InvocationTargetException var26) {
                                                        var252 = var26;
                                                        var256 = false;
                                                        break label755;
                                                    }

                                                    var259 = var258;
                                                    var261 = var1;
                                                    var262 = var6;
                                                    var240 = 0;

                                                    try {
                                                        var259[var240] = var261.getLong(var262);
                                                        var268.invoke(var10001, var258);
                                                        continue;
                                                    } catch (JSONException var19) {
                                                        var255 = var19;
                                                        var256 = false;
                                                        break label744;
                                                    } catch (NoSuchMethodException var20) {
                                                        var254 = var20;
                                                        var256 = false;
                                                        break label743;
                                                    } catch (IllegalAccessException var21) {
                                                        var253 = var21;
                                                        var256 = false;
                                                        break label742;
                                                    } catch (InvocationTargetException var22) {
                                                        var252 = var22;
                                                        var256 = false;
                                                    }
                                                }
                                            } else {
                                                label721: {
                                                    try {
                                                        var266 = var5.getType();
                                                        var260 = Integer.class;
                                                    } catch (JSONException var215) {
                                                        var255 = var215;
                                                        var256 = false;
                                                        break label744;
                                                    } catch (NoSuchMethodException var216) {
                                                        var254 = var216;
                                                        var256 = false;
                                                        break label743;
                                                    } catch (IllegalAccessException var217) {
                                                        var253 = var217;
                                                        var256 = false;
                                                        break label742;
                                                    } catch (InvocationTargetException var218) {
                                                        var252 = var218;
                                                        var256 = false;
                                                        break label721;
                                                    }

                                                    if (var266 == var260) {
                                                        label756: {
                                                            try {
                                                                var268 = var244;
                                                                var10001 = this;
                                                                var258 = new Object[1];
                                                            } catch (JSONException var31) {
                                                                var255 = var31;
                                                                var256 = false;
                                                                break label744;
                                                            } catch (NoSuchMethodException var32) {
                                                                var254 = var32;
                                                                var256 = false;
                                                                break label743;
                                                            } catch (IllegalAccessException var33) {
                                                                var253 = var33;
                                                                var256 = false;
                                                                break label742;
                                                            } catch (InvocationTargetException var34) {
                                                                var252 = var34;
                                                                var256 = false;
                                                                break label756;
                                                            }

                                                            var259 = var258;
                                                            var261 = var1;
                                                            var262 = var6;
                                                            var240 = 0;

                                                            try {
                                                                var259[var240] = var261.getInt(var262);
                                                                var268.invoke(var10001, var258);
                                                                continue;
                                                            } catch (JSONException var27) {
                                                                var255 = var27;
                                                                var256 = false;
                                                                break label744;
                                                            } catch (NoSuchMethodException var28) {
                                                                var254 = var28;
                                                                var256 = false;
                                                                break label743;
                                                            } catch (IllegalAccessException var29) {
                                                                var253 = var29;
                                                                var256 = false;
                                                                break label742;
                                                            } catch (InvocationTargetException var30) {
                                                                var252 = var30;
                                                                var256 = false;
                                                            }
                                                        }
                                                    } else {
                                                        label717: {
                                                            try {
                                                                var266 = var5.getType();
                                                                var260 = Float.class;
                                                            } catch (JSONException var211) {
                                                                var255 = var211;
                                                                var256 = false;
                                                                break label744;
                                                            } catch (NoSuchMethodException var212) {
                                                                var254 = var212;
                                                                var256 = false;
                                                                break label743;
                                                            } catch (IllegalAccessException var213) {
                                                                var253 = var213;
                                                                var256 = false;
                                                                break label742;
                                                            } catch (InvocationTargetException var214) {
                                                                var252 = var214;
                                                                var256 = false;
                                                                break label717;
                                                            }

                                                            if (var266 == var260) {
                                                                label757: {
                                                                    try {
                                                                        var268 = var244;
                                                                        var10001 = this;
                                                                        var258 = new Object[1];
                                                                    } catch (JSONException var43) {
                                                                        var255 = var43;
                                                                        var256 = false;
                                                                        break label744;
                                                                    } catch (NoSuchMethodException var44) {
                                                                        var254 = var44;
                                                                        var256 = false;
                                                                        break label743;
                                                                    } catch (IllegalAccessException var45) {
                                                                        var253 = var45;
                                                                        var256 = false;
                                                                        break label742;
                                                                    } catch (InvocationTargetException var46) {
                                                                        var252 = var46;
                                                                        var256 = false;
                                                                        break label757;
                                                                    }

                                                                    var259 = var258;
                                                                    var261 = var1;
                                                                    var262 = var6;
                                                                    var240 = 0;

                                                                    double var263;
                                                                    try {
                                                                        var263 = var261.getDouble(var262);
                                                                    } catch (JSONException var39) {
                                                                        var255 = var39;
                                                                        var256 = false;
                                                                        break label744;
                                                                    } catch (NoSuchMethodException var40) {
                                                                        var254 = var40;
                                                                        var256 = false;
                                                                        break label743;
                                                                    } catch (IllegalAccessException var41) {
                                                                        var253 = var41;
                                                                        var256 = false;
                                                                        break label742;
                                                                    } catch (InvocationTargetException var42) {
                                                                        var252 = var42;
                                                                        var256 = false;
                                                                        break label757;
                                                                    }

                                                                    float var267 = (float)var263;

                                                                    try {
                                                                        var259[var240] = var267;
                                                                        var268.invoke(var10001, var258);
                                                                        continue;
                                                                    } catch (JSONException var35) {
                                                                        var255 = var35;
                                                                        var256 = false;
                                                                        break label744;
                                                                    } catch (NoSuchMethodException var36) {
                                                                        var254 = var36;
                                                                        var256 = false;
                                                                        break label743;
                                                                    } catch (IllegalAccessException var37) {
                                                                        var253 = var37;
                                                                        var256 = false;
                                                                        break label742;
                                                                    } catch (InvocationTargetException var38) {
                                                                        var252 = var38;
                                                                        var256 = false;
                                                                    }
                                                                }
                                                            } else {
                                                                label713: {
                                                                    try {
                                                                        var266 = var5.getType();
                                                                        var260 = Double.class;
                                                                    } catch (JSONException var207) {
                                                                        var255 = var207;
                                                                        var256 = false;
                                                                        break label744;
                                                                    } catch (NoSuchMethodException var208) {
                                                                        var254 = var208;
                                                                        var256 = false;
                                                                        break label743;
                                                                    } catch (IllegalAccessException var209) {
                                                                        var253 = var209;
                                                                        var256 = false;
                                                                        break label742;
                                                                    } catch (InvocationTargetException var210) {
                                                                        var252 = var210;
                                                                        var256 = false;
                                                                        break label713;
                                                                    }

                                                                    if (var266 == var260) {
                                                                        label758: {
                                                                            try {
                                                                                var268 = var244;
                                                                                var10001 = this;
                                                                                var258 = new Object[1];
                                                                            } catch (JSONException var51) {
                                                                                var255 = var51;
                                                                                var256 = false;
                                                                                break label744;
                                                                            } catch (NoSuchMethodException var52) {
                                                                                var254 = var52;
                                                                                var256 = false;
                                                                                break label743;
                                                                            } catch (IllegalAccessException var53) {
                                                                                var253 = var53;
                                                                                var256 = false;
                                                                                break label742;
                                                                            } catch (InvocationTargetException var54) {
                                                                                var252 = var54;
                                                                                var256 = false;
                                                                                break label758;
                                                                            }

                                                                            var259 = var258;
                                                                            var261 = var1;
                                                                            var262 = var6;
                                                                            var240 = 0;

                                                                            try {
                                                                                var259[var240] = var261.getDouble(var262);
                                                                                var268.invoke(var10001, var258);
                                                                                continue;
                                                                            } catch (JSONException var47) {
                                                                                var255 = var47;
                                                                                var256 = false;
                                                                                break label744;
                                                                            } catch (NoSuchMethodException var48) {
                                                                                var254 = var48;
                                                                                var256 = false;
                                                                                break label743;
                                                                            } catch (IllegalAccessException var49) {
                                                                                var253 = var49;
                                                                                var256 = false;
                                                                                break label742;
                                                                            } catch (InvocationTargetException var50) {
                                                                                var252 = var50;
                                                                                var256 = false;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        label709: {
                                                                            try {
                                                                                var266 = var5.getType();
                                                                                var260 = String.class;
                                                                            } catch (JSONException var203) {
                                                                                var255 = var203;
                                                                                var256 = false;
                                                                                break label744;
                                                                            } catch (NoSuchMethodException var204) {
                                                                                var254 = var204;
                                                                                var256 = false;
                                                                                break label743;
                                                                            } catch (IllegalAccessException var205) {
                                                                                var253 = var205;
                                                                                var256 = false;
                                                                                break label742;
                                                                            } catch (InvocationTargetException var206) {
                                                                                var252 = var206;
                                                                                var256 = false;
                                                                                break label709;
                                                                            }

                                                                            if (var266 == var260) {
                                                                                label759: {
                                                                                    try {
                                                                                        var268 = var244;
                                                                                        var10001 = this;
                                                                                        var258 = new Object[1];
                                                                                    } catch (JSONException var59) {
                                                                                        var255 = var59;
                                                                                        var256 = false;
                                                                                        break label744;
                                                                                    } catch (NoSuchMethodException var60) {
                                                                                        var254 = var60;
                                                                                        var256 = false;
                                                                                        break label743;
                                                                                    } catch (IllegalAccessException var61) {
                                                                                        var253 = var61;
                                                                                        var256 = false;
                                                                                        break label742;
                                                                                    } catch (InvocationTargetException var62) {
                                                                                        var252 = var62;
                                                                                        var256 = false;
                                                                                        break label759;
                                                                                    }

                                                                                    var259 = var258;
                                                                                    var261 = var1;
                                                                                    var262 = var6;
                                                                                    var240 = 0;

                                                                                    try {
                                                                                        var259[var240] = var261.getString(var262);
                                                                                        var268.invoke(var10001, var258);
                                                                                        continue;
                                                                                    } catch (JSONException var55) {
                                                                                        var255 = var55;
                                                                                        var256 = false;
                                                                                        break label744;
                                                                                    } catch (NoSuchMethodException var56) {
                                                                                        var254 = var56;
                                                                                        var256 = false;
                                                                                        break label743;
                                                                                    } catch (IllegalAccessException var57) {
                                                                                        var253 = var57;
                                                                                        var256 = false;
                                                                                        break label742;
                                                                                    } catch (InvocationTargetException var58) {
                                                                                        var252 = var58;
                                                                                        var256 = false;
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                label705: {
                                                                                    try {
                                                                                        var266 = var5.getType();
                                                                                        var260 = JSONObject.class;
                                                                                    } catch (JSONException var199) {
                                                                                        var255 = var199;
                                                                                        var256 = false;
                                                                                        break label744;
                                                                                    } catch (NoSuchMethodException var200) {
                                                                                        var254 = var200;
                                                                                        var256 = false;
                                                                                        break label743;
                                                                                    } catch (IllegalAccessException var201) {
                                                                                        var253 = var201;
                                                                                        var256 = false;
                                                                                        break label742;
                                                                                    } catch (InvocationTargetException var202) {
                                                                                        var252 = var202;
                                                                                        var256 = false;
                                                                                        break label705;
                                                                                    }

                                                                                    if (var266 == var260) {
                                                                                        label760: {
                                                                                            try {
                                                                                                var268 = var244;
                                                                                                var10001 = this;
                                                                                                var258 = new Object[1];
                                                                                            } catch (JSONException var67) {
                                                                                                var255 = var67;
                                                                                                var256 = false;
                                                                                                break label744;
                                                                                            } catch (NoSuchMethodException var68) {
                                                                                                var254 = var68;
                                                                                                var256 = false;
                                                                                                break label743;
                                                                                            } catch (IllegalAccessException var69) {
                                                                                                var253 = var69;
                                                                                                var256 = false;
                                                                                                break label742;
                                                                                            } catch (InvocationTargetException var70) {
                                                                                                var252 = var70;
                                                                                                var256 = false;
                                                                                                break label760;
                                                                                            }

                                                                                            var259 = var258;
                                                                                            var261 = var1;
                                                                                            var262 = var6;
                                                                                            var240 = 0;

                                                                                            try {
                                                                                                var259[var240] = var261.getJSONObject(var262);
                                                                                                var268.invoke(var10001, var258);
                                                                                                continue;
                                                                                            } catch (JSONException var63) {
                                                                                                var255 = var63;
                                                                                                var256 = false;
                                                                                                break label744;
                                                                                            } catch (NoSuchMethodException var64) {
                                                                                                var254 = var64;
                                                                                                var256 = false;
                                                                                                break label743;
                                                                                            } catch (IllegalAccessException var65) {
                                                                                                var253 = var65;
                                                                                                var256 = false;
                                                                                                break label742;
                                                                                            } catch (InvocationTargetException var66) {
                                                                                                var252 = var66;
                                                                                                var256 = false;
                                                                                            }
                                                                                        }
                                                                                    } else {
                                                                                        label701: {
                                                                                            try {
                                                                                                var266 = var5.getType();
                                                                                                var260 = JSONArray.class;
                                                                                            } catch (JSONException var195) {
                                                                                                var255 = var195;
                                                                                                var256 = false;
                                                                                                break label744;
                                                                                            } catch (NoSuchMethodException var196) {
                                                                                                var254 = var196;
                                                                                                var256 = false;
                                                                                                break label743;
                                                                                            } catch (IllegalAccessException var197) {
                                                                                                var253 = var197;
                                                                                                var256 = false;
                                                                                                break label742;
                                                                                            } catch (InvocationTargetException var198) {
                                                                                                var252 = var198;
                                                                                                var256 = false;
                                                                                                break label701;
                                                                                            }

                                                                                            if (var266 == var260) {
                                                                                                label761: {
                                                                                                    try {
                                                                                                        var268 = var244;
                                                                                                        var10001 = this;
                                                                                                        var258 = new Object[1];
                                                                                                    } catch (JSONException var75) {
                                                                                                        var255 = var75;
                                                                                                        var256 = false;
                                                                                                        break label744;
                                                                                                    } catch (NoSuchMethodException var76) {
                                                                                                        var254 = var76;
                                                                                                        var256 = false;
                                                                                                        break label743;
                                                                                                    } catch (IllegalAccessException var77) {
                                                                                                        var253 = var77;
                                                                                                        var256 = false;
                                                                                                        break label742;
                                                                                                    } catch (InvocationTargetException var78) {
                                                                                                        var252 = var78;
                                                                                                        var256 = false;
                                                                                                        break label761;
                                                                                                    }

                                                                                                    var259 = var258;
                                                                                                    var261 = var1;
                                                                                                    var262 = var6;
                                                                                                    var240 = 0;

                                                                                                    try {
                                                                                                        var259[var240] = var261.getJSONArray(var262);
                                                                                                        var268.invoke(var10001, var258);
                                                                                                        continue;
                                                                                                    } catch (JSONException var71) {
                                                                                                        var255 = var71;
                                                                                                        var256 = false;
                                                                                                        break label744;
                                                                                                    } catch (NoSuchMethodException var72) {
                                                                                                        var254 = var72;
                                                                                                        var256 = false;
                                                                                                        break label743;
                                                                                                    } catch (IllegalAccessException var73) {
                                                                                                        var253 = var73;
                                                                                                        var256 = false;
                                                                                                        break label742;
                                                                                                    } catch (InvocationTargetException var74) {
                                                                                                        var252 = var74;
                                                                                                        var256 = false;
                                                                                                    }
                                                                                                }
                                                                                            } else {
                                                                                                label697: {
                                                                                                    try {
                                                                                                        var266 = var5.getType();
                                                                                                        var260 = List.class;
                                                                                                    } catch (JSONException var191) {
                                                                                                        var255 = var191;
                                                                                                        var256 = false;
                                                                                                        break label744;
                                                                                                    } catch (NoSuchMethodException var192) {
                                                                                                        var254 = var192;
                                                                                                        var256 = false;
                                                                                                        break label743;
                                                                                                    } catch (IllegalAccessException var193) {
                                                                                                        var253 = var193;
                                                                                                        var256 = false;
                                                                                                        break label742;
                                                                                                    } catch (InvocationTargetException var194) {
                                                                                                        var252 = var194;
                                                                                                        var256 = false;
                                                                                                        break label697;
                                                                                                    }

                                                                                                    if (var266 == var260) {
                                                                                                        label762: {
                                                                                                            JSONArray var270;
                                                                                                            try {
                                                                                                                var270 = var1.getJSONArray(var6);
                                                                                                            } catch (JSONException var99) {
                                                                                                                var255 = var99;
                                                                                                                var256 = false;
                                                                                                                break label744;
                                                                                                            } catch (NoSuchMethodException var100) {
                                                                                                                var254 = var100;
                                                                                                                var256 = false;
                                                                                                                break label743;
                                                                                                            } catch (IllegalAccessException var101) {
                                                                                                                var253 = var101;
                                                                                                                var256 = false;
                                                                                                                break label742;
                                                                                                            } catch (InvocationTargetException var102) {
                                                                                                                var252 = var102;
                                                                                                                var256 = false;
                                                                                                                break label762;
                                                                                                            }

                                                                                                            JSONArray var245 = var270;

                                                                                                            ArrayList var271;
                                                                                                            try {
                                                                                                                var271 = new ArrayList;
                                                                                                            } catch (JSONException var95) {
                                                                                                                var255 = var95;
                                                                                                                var256 = false;
                                                                                                                break label744;
                                                                                                            } catch (NoSuchMethodException var96) {
                                                                                                                var254 = var96;
                                                                                                                var256 = false;
                                                                                                                break label743;
                                                                                                            } catch (IllegalAccessException var97) {
                                                                                                                var253 = var97;
                                                                                                                var256 = false;
                                                                                                                break label742;
                                                                                                            } catch (InvocationTargetException var98) {
                                                                                                                var252 = var98;
                                                                                                                var256 = false;
                                                                                                                break label762;
                                                                                                            }

                                                                                                            ArrayList var241 = var271;

                                                                                                            try {
                                                                                                                var271.<init>();
                                                                                                            } catch (JSONException var91) {
                                                                                                                var255 = var91;
                                                                                                                var256 = false;
                                                                                                                break label744;
                                                                                                            } catch (NoSuchMethodException var92) {
                                                                                                                var254 = var92;
                                                                                                                var256 = false;
                                                                                                                break label743;
                                                                                                            } catch (IllegalAccessException var93) {
                                                                                                                var253 = var93;
                                                                                                                var256 = false;
                                                                                                                break label742;
                                                                                                            } catch (InvocationTargetException var94) {
                                                                                                                var252 = var94;
                                                                                                                var256 = false;
                                                                                                                break label762;
                                                                                                            }

                                                                                                            int var8 = 0;

                                                                                                            while(true) {
                                                                                                                int var272;
                                                                                                                int var273;
                                                                                                                try {
                                                                                                                    var272 = var8;
                                                                                                                    var273 = var245.length();
                                                                                                                } catch (JSONException var83) {
                                                                                                                    var255 = var83;
                                                                                                                    var256 = false;
                                                                                                                    break label744;
                                                                                                                } catch (NoSuchMethodException var84) {
                                                                                                                    var254 = var84;
                                                                                                                    var256 = false;
                                                                                                                    break label743;
                                                                                                                } catch (IllegalAccessException var85) {
                                                                                                                    var253 = var85;
                                                                                                                    var256 = false;
                                                                                                                    break label742;
                                                                                                                } catch (InvocationTargetException var86) {
                                                                                                                    var252 = var86;
                                                                                                                    var256 = false;
                                                                                                                    break;
                                                                                                                }

                                                                                                                if (var272 >= var273) {
                                                                                                                    try {
                                                                                                                        var244.invoke(this, var241);
                                                                                                                        continue label749;
                                                                                                                    } catch (JSONException var79) {
                                                                                                                        var255 = var79;
                                                                                                                        var256 = false;
                                                                                                                        break label744;
                                                                                                                    } catch (NoSuchMethodException var80) {
                                                                                                                        var254 = var80;
                                                                                                                        var256 = false;
                                                                                                                        break label743;
                                                                                                                    } catch (IllegalAccessException var81) {
                                                                                                                        var253 = var81;
                                                                                                                        var256 = false;
                                                                                                                        break label742;
                                                                                                                    } catch (InvocationTargetException var82) {
                                                                                                                        var252 = var82;
                                                                                                                        var256 = false;
                                                                                                                        break;
                                                                                                                    }
                                                                                                                }

                                                                                                                try {
                                                                                                                    var241.add(var245.getString(var8));
                                                                                                                } catch (JSONException var87) {
                                                                                                                    var255 = var87;
                                                                                                                    var256 = false;
                                                                                                                    break label744;
                                                                                                                } catch (NoSuchMethodException var88) {
                                                                                                                    var254 = var88;
                                                                                                                    var256 = false;
                                                                                                                    break label743;
                                                                                                                } catch (IllegalAccessException var89) {
                                                                                                                    var253 = var89;
                                                                                                                    var256 = false;
                                                                                                                    break label742;
                                                                                                                } catch (InvocationTargetException var90) {
                                                                                                                    var252 = var90;
                                                                                                                    var256 = false;
                                                                                                                    break;
                                                                                                                }

                                                                                                                ++var8;
                                                                                                            }
                                                                                                        }
                                                                                                    } else {
                                                                                                        label693: {
                                                                                                            try {
                                                                                                                var266 = var5.getType();
                                                                                                                var260 = Map.class;
                                                                                                            } catch (JSONException var187) {
                                                                                                                var255 = var187;
                                                                                                                var256 = false;
                                                                                                                break label744;
                                                                                                            } catch (NoSuchMethodException var188) {
                                                                                                                var254 = var188;
                                                                                                                var256 = false;
                                                                                                                break label743;
                                                                                                            } catch (IllegalAccessException var189) {
                                                                                                                var253 = var189;
                                                                                                                var256 = false;
                                                                                                                break label742;
                                                                                                            } catch (InvocationTargetException var190) {
                                                                                                                var252 = var190;
                                                                                                                var256 = false;
                                                                                                                break label693;
                                                                                                            }

                                                                                                            JSONObject var246;
                                                                                                            if (var266 == var260) {
                                                                                                                label764: {
                                                                                                                    JSONObject var274;
                                                                                                                    try {
                                                                                                                        var274 = var1.getJSONObject(var6);
                                                                                                                    } catch (JSONException var127) {
                                                                                                                        var255 = var127;
                                                                                                                        var256 = false;
                                                                                                                        break label744;
                                                                                                                    } catch (NoSuchMethodException var128) {
                                                                                                                        var254 = var128;
                                                                                                                        var256 = false;
                                                                                                                        break label743;
                                                                                                                    } catch (IllegalAccessException var129) {
                                                                                                                        var253 = var129;
                                                                                                                        var256 = false;
                                                                                                                        break label742;
                                                                                                                    } catch (InvocationTargetException var130) {
                                                                                                                        var252 = var130;
                                                                                                                        var256 = false;
                                                                                                                        break label764;
                                                                                                                    }

                                                                                                                    var246 = var274;

                                                                                                                    HashMap var275;
                                                                                                                    try {
                                                                                                                        var275 = new HashMap;
                                                                                                                    } catch (JSONException var123) {
                                                                                                                        var255 = var123;
                                                                                                                        var256 = false;
                                                                                                                        break label744;
                                                                                                                    } catch (NoSuchMethodException var124) {
                                                                                                                        var254 = var124;
                                                                                                                        var256 = false;
                                                                                                                        break label743;
                                                                                                                    } catch (IllegalAccessException var125) {
                                                                                                                        var253 = var125;
                                                                                                                        var256 = false;
                                                                                                                        break label742;
                                                                                                                    } catch (InvocationTargetException var126) {
                                                                                                                        var252 = var126;
                                                                                                                        var256 = false;
                                                                                                                        break label764;
                                                                                                                    }

                                                                                                                    HashMap var242 = var275;

                                                                                                                    Iterator var276;
                                                                                                                    try {
                                                                                                                        var275.<init>();
                                                                                                                        var276 = var274.keys();
                                                                                                                    } catch (JSONException var119) {
                                                                                                                        var255 = var119;
                                                                                                                        var256 = false;
                                                                                                                        break label744;
                                                                                                                    } catch (NoSuchMethodException var120) {
                                                                                                                        var254 = var120;
                                                                                                                        var256 = false;
                                                                                                                        break label743;
                                                                                                                    } catch (IllegalAccessException var121) {
                                                                                                                        var253 = var121;
                                                                                                                        var256 = false;
                                                                                                                        break label742;
                                                                                                                    } catch (InvocationTargetException var122) {
                                                                                                                        var252 = var122;
                                                                                                                        var256 = false;
                                                                                                                        break label764;
                                                                                                                    }

                                                                                                                    Iterator var247 = var276;

                                                                                                                    while(true) {
                                                                                                                        boolean var277;
                                                                                                                        try {
                                                                                                                            var277 = var247.hasNext();
                                                                                                                        } catch (JSONException var107) {
                                                                                                                            var255 = var107;
                                                                                                                            var256 = false;
                                                                                                                            break label744;
                                                                                                                        } catch (NoSuchMethodException var108) {
                                                                                                                            var254 = var108;
                                                                                                                            var256 = false;
                                                                                                                            break label743;
                                                                                                                        } catch (IllegalAccessException var109) {
                                                                                                                            var253 = var109;
                                                                                                                            var256 = false;
                                                                                                                            break label742;
                                                                                                                        } catch (InvocationTargetException var110) {
                                                                                                                            var252 = var110;
                                                                                                                            var256 = false;
                                                                                                                            break;
                                                                                                                        }

                                                                                                                        if (!var277) {
                                                                                                                            try {
                                                                                                                                var244.invoke(this, var242);
                                                                                                                                continue label749;
                                                                                                                            } catch (JSONException var103) {
                                                                                                                                var255 = var103;
                                                                                                                                var256 = false;
                                                                                                                                break label744;
                                                                                                                            } catch (NoSuchMethodException var104) {
                                                                                                                                var254 = var104;
                                                                                                                                var256 = false;
                                                                                                                                break label743;
                                                                                                                            } catch (IllegalAccessException var105) {
                                                                                                                                var253 = var105;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var106) {
                                                                                                                                var252 = var106;
                                                                                                                                var256 = false;
                                                                                                                                break;
                                                                                                                            }
                                                                                                                        }

                                                                                                                        HashMap var278;
                                                                                                                        JSONObject var279;
                                                                                                                        try {
                                                                                                                            var278 = var242;
                                                                                                                            var279 = var246;
                                                                                                                            var10002 = (String)var247.next();
                                                                                                                        } catch (JSONException var115) {
                                                                                                                            var255 = var115;
                                                                                                                            var256 = false;
                                                                                                                            break label744;
                                                                                                                        } catch (NoSuchMethodException var116) {
                                                                                                                            var254 = var116;
                                                                                                                            var256 = false;
                                                                                                                            break label743;
                                                                                                                        } catch (IllegalAccessException var117) {
                                                                                                                            var253 = var117;
                                                                                                                            var256 = false;
                                                                                                                            break label742;
                                                                                                                        } catch (InvocationTargetException var118) {
                                                                                                                            var252 = var118;
                                                                                                                            var256 = false;
                                                                                                                            break;
                                                                                                                        }

                                                                                                                        String var9 = var10002;

                                                                                                                        try {
                                                                                                                            var278.put(var9, var279.getString(var10002));
                                                                                                                        } catch (JSONException var111) {
                                                                                                                            var255 = var111;
                                                                                                                            var256 = false;
                                                                                                                            break label744;
                                                                                                                        } catch (NoSuchMethodException var112) {
                                                                                                                            var254 = var112;
                                                                                                                            var256 = false;
                                                                                                                            break label743;
                                                                                                                        } catch (IllegalAccessException var113) {
                                                                                                                            var253 = var113;
                                                                                                                            var256 = false;
                                                                                                                            break label742;
                                                                                                                        } catch (InvocationTargetException var114) {
                                                                                                                            var252 = var114;
                                                                                                                            var256 = false;
                                                                                                                            break;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            } else {
                                                                                                                label689: {
                                                                                                                    try {
                                                                                                                        var266 = var5.getType();
                                                                                                                        var260 = TSNotification.class;
                                                                                                                    } catch (JSONException var183) {
                                                                                                                        var255 = var183;
                                                                                                                        var256 = false;
                                                                                                                        break label744;
                                                                                                                    } catch (NoSuchMethodException var184) {
                                                                                                                        var254 = var184;
                                                                                                                        var256 = false;
                                                                                                                        break label743;
                                                                                                                    } catch (IllegalAccessException var185) {
                                                                                                                        var253 = var185;
                                                                                                                        var256 = false;
                                                                                                                        break label742;
                                                                                                                    } catch (InvocationTargetException var186) {
                                                                                                                        var252 = var186;
                                                                                                                        var256 = false;
                                                                                                                        break label689;
                                                                                                                    }

                                                                                                                    JSONObject var264;
                                                                                                                    byte var269;
                                                                                                                    if (var266 == var260) {
                                                                                                                        label766: {
                                                                                                                            try {
                                                                                                                                var268 = var244;
                                                                                                                                var10001 = this;
                                                                                                                                var264 = var1.getJSONObject(var6);
                                                                                                                            } catch (JSONException var139) {
                                                                                                                                var255 = var139;
                                                                                                                                var256 = false;
                                                                                                                                break label744;
                                                                                                                            } catch (NoSuchMethodException var140) {
                                                                                                                                var254 = var140;
                                                                                                                                var256 = false;
                                                                                                                                break label743;
                                                                                                                            } catch (IllegalAccessException var141) {
                                                                                                                                var253 = var141;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var142) {
                                                                                                                                var252 = var142;
                                                                                                                                var256 = false;
                                                                                                                                break label766;
                                                                                                                            }

                                                                                                                            var246 = var264;

                                                                                                                            try {
                                                                                                                                var258 = new Object[1];
                                                                                                                            } catch (JSONException var135) {
                                                                                                                                var255 = var135;
                                                                                                                                var256 = false;
                                                                                                                                break label744;
                                                                                                                            } catch (NoSuchMethodException var136) {
                                                                                                                                var254 = var136;
                                                                                                                                var256 = false;
                                                                                                                                break label743;
                                                                                                                            } catch (IllegalAccessException var137) {
                                                                                                                                var253 = var137;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var138) {
                                                                                                                                var252 = var138;
                                                                                                                                var256 = false;
                                                                                                                                break label766;
                                                                                                                            }

                                                                                                                            var259 = var258;
                                                                                                                            var269 = 0;

                                                                                                                            try {
                                                                                                                                var259[var269] = new TSNotification(var246, false);
                                                                                                                                var268.invoke(var10001, var258);
                                                                                                                                continue;
                                                                                                                            } catch (JSONException var131) {
                                                                                                                                var255 = var131;
                                                                                                                                var256 = false;
                                                                                                                                break label744;
                                                                                                                            } catch (NoSuchMethodException var132) {
                                                                                                                                var254 = var132;
                                                                                                                                var256 = false;
                                                                                                                                break label743;
                                                                                                                            } catch (IllegalAccessException var133) {
                                                                                                                                var253 = var133;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var134) {
                                                                                                                                var252 = var134;
                                                                                                                                var256 = false;
                                                                                                                            }
                                                                                                                        }
                                                                                                                    } else {
                                                                                                                        label685: {
                                                                                                                            try {
                                                                                                                                var266 = var5.getType();
                                                                                                                                var260 = TSAuthorization.class;
                                                                                                                            } catch (JSONException var179) {
                                                                                                                                var255 = var179;
                                                                                                                                var256 = false;
                                                                                                                                break label744;
                                                                                                                            } catch (NoSuchMethodException var180) {
                                                                                                                                var254 = var180;
                                                                                                                                var256 = false;
                                                                                                                                break label743;
                                                                                                                            } catch (IllegalAccessException var181) {
                                                                                                                                var253 = var181;
                                                                                                                                var256 = false;
                                                                                                                                break label742;
                                                                                                                            } catch (InvocationTargetException var182) {
                                                                                                                                var252 = var182;
                                                                                                                                var256 = false;
                                                                                                                                break label685;
                                                                                                                            }

                                                                                                                            if (var266 == var260) {
                                                                                                                                label767: {
                                                                                                                                    try {
                                                                                                                                        var268 = var244;
                                                                                                                                        var10001 = this;
                                                                                                                                        var264 = var1.getJSONObject(var6);
                                                                                                                                    } catch (JSONException var151) {
                                                                                                                                        var255 = var151;
                                                                                                                                        var256 = false;
                                                                                                                                        break label744;
                                                                                                                                    } catch (NoSuchMethodException var152) {
                                                                                                                                        var254 = var152;
                                                                                                                                        var256 = false;
                                                                                                                                        break label743;
                                                                                                                                    } catch (IllegalAccessException var153) {
                                                                                                                                        var253 = var153;
                                                                                                                                        var256 = false;
                                                                                                                                        break label742;
                                                                                                                                    } catch (InvocationTargetException var154) {
                                                                                                                                        var252 = var154;
                                                                                                                                        var256 = false;
                                                                                                                                        break label767;
                                                                                                                                    }

                                                                                                                                    var246 = var264;

                                                                                                                                    try {
                                                                                                                                        var258 = new Object[1];
                                                                                                                                    } catch (JSONException var147) {
                                                                                                                                        var255 = var147;
                                                                                                                                        var256 = false;
                                                                                                                                        break label744;
                                                                                                                                    } catch (NoSuchMethodException var148) {
                                                                                                                                        var254 = var148;
                                                                                                                                        var256 = false;
                                                                                                                                        break label743;
                                                                                                                                    } catch (IllegalAccessException var149) {
                                                                                                                                        var253 = var149;
                                                                                                                                        var256 = false;
                                                                                                                                        break label742;
                                                                                                                                    } catch (InvocationTargetException var150) {
                                                                                                                                        var252 = var150;
                                                                                                                                        var256 = false;
                                                                                                                                        break label767;
                                                                                                                                    }

                                                                                                                                    var259 = var258;
                                                                                                                                    var269 = 0;

                                                                                                                                    try {
                                                                                                                                        var259[var269] = new TSAuthorization(var246, false);
                                                                                                                                        var268.invoke(var10001, var258);
                                                                                                                                        continue;
                                                                                                                                    } catch (JSONException var143) {
                                                                                                                                        var255 = var143;
                                                                                                                                        var256 = false;
                                                                                                                                        break label744;
                                                                                                                                    } catch (NoSuchMethodException var144) {
                                                                                                                                        var254 = var144;
                                                                                                                                        var256 = false;
                                                                                                                                        break label743;
                                                                                                                                    } catch (IllegalAccessException var145) {
                                                                                                                                        var253 = var145;
                                                                                                                                        var256 = false;
                                                                                                                                        break label742;
                                                                                                                                    } catch (InvocationTargetException var146) {
                                                                                                                                        var252 = var146;
                                                                                                                                        var256 = false;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            } else {
                                                                                                                                label681: {
                                                                                                                                    try {
                                                                                                                                        var266 = var5.getType();
                                                                                                                                        var260 = TSBackgroundPermissionRationale.class;
                                                                                                                                    } catch (JSONException var175) {
                                                                                                                                        var255 = var175;
                                                                                                                                        var256 = false;
                                                                                                                                        break label744;
                                                                                                                                    } catch (NoSuchMethodException var176) {
                                                                                                                                        var254 = var176;
                                                                                                                                        var256 = false;
                                                                                                                                        break label743;
                                                                                                                                    } catch (IllegalAccessException var177) {
                                                                                                                                        var253 = var177;
                                                                                                                                        var256 = false;
                                                                                                                                        break label742;
                                                                                                                                    } catch (InvocationTargetException var178) {
                                                                                                                                        var252 = var178;
                                                                                                                                        var256 = false;
                                                                                                                                        break label681;
                                                                                                                                    }

                                                                                                                                    if (var266 == var260) {
                                                                                                                                        label768: {
                                                                                                                                            try {
                                                                                                                                                var268 = var244;
                                                                                                                                                var10001 = this;
                                                                                                                                                var264 = var1.getJSONObject(var6);
                                                                                                                                            } catch (JSONException var163) {
                                                                                                                                                var255 = var163;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            } catch (NoSuchMethodException var164) {
                                                                                                                                                var254 = var164;
                                                                                                                                                var256 = false;
                                                                                                                                                break label743;
                                                                                                                                            } catch (IllegalAccessException var165) {
                                                                                                                                                var253 = var165;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var166) {
                                                                                                                                                var252 = var166;
                                                                                                                                                var256 = false;
                                                                                                                                                break label768;
                                                                                                                                            }

                                                                                                                                            var246 = var264;

                                                                                                                                            try {
                                                                                                                                                var258 = new Object[1];
                                                                                                                                            } catch (JSONException var159) {
                                                                                                                                                var255 = var159;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            } catch (NoSuchMethodException var160) {
                                                                                                                                                var254 = var160;
                                                                                                                                                var256 = false;
                                                                                                                                                break label743;
                                                                                                                                            } catch (IllegalAccessException var161) {
                                                                                                                                                var253 = var161;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var162) {
                                                                                                                                                var252 = var162;
                                                                                                                                                var256 = false;
                                                                                                                                                break label768;
                                                                                                                                            }

                                                                                                                                            var259 = var258;
                                                                                                                                            var269 = 0;

                                                                                                                                            try {
                                                                                                                                                var259[var269] = new TSBackgroundPermissionRationale(var246, false);
                                                                                                                                                var268.invoke(var10001, var258);
                                                                                                                                                continue;
                                                                                                                                            } catch (JSONException var155) {
                                                                                                                                                var255 = var155;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            } catch (NoSuchMethodException var156) {
                                                                                                                                                var254 = var156;
                                                                                                                                                var256 = false;
                                                                                                                                                break label743;
                                                                                                                                            } catch (IllegalAccessException var157) {
                                                                                                                                                var253 = var157;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var158) {
                                                                                                                                                var252 = var158;
                                                                                                                                                var256 = false;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        label769: {
                                                                                                                                            try {
                                                                                                                                                var268 = var244;
                                                                                                                                                var10001 = this;
                                                                                                                                                var258 = new Object[1];
                                                                                                                                            } catch (JSONException var171) {
                                                                                                                                                var255 = var171;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            } catch (NoSuchMethodException var172) {
                                                                                                                                                var254 = var172;
                                                                                                                                                var256 = false;
                                                                                                                                                break label743;
                                                                                                                                            } catch (IllegalAccessException var173) {
                                                                                                                                                var253 = var173;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var174) {
                                                                                                                                                var252 = var174;
                                                                                                                                                var256 = false;
                                                                                                                                                break label769;
                                                                                                                                            }

                                                                                                                                            var259 = var258;
                                                                                                                                            var261 = var1;
                                                                                                                                            var262 = var6;
                                                                                                                                            var240 = 0;

                                                                                                                                            try {
                                                                                                                                                var259[var240] = var261.get(var262);
                                                                                                                                                var268.invoke(var10001, var258);
                                                                                                                                                continue;
                                                                                                                                            } catch (JSONException var167) {
                                                                                                                                                var255 = var167;
                                                                                                                                                var256 = false;
                                                                                                                                                break label744;
                                                                                                                                            } catch (NoSuchMethodException var168) {
                                                                                                                                                var254 = var168;
                                                                                                                                                var256 = false;
                                                                                                                                                break label743;
                                                                                                                                            } catch (IllegalAccessException var169) {
                                                                                                                                                var253 = var169;
                                                                                                                                                var256 = false;
                                                                                                                                                break label742;
                                                                                                                                            } catch (InvocationTargetException var170) {
                                                                                                                                                var252 = var170;
                                                                                                                                                var256 = false;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                InvocationTargetException var248 = var252;
                                Log.i(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var248.getMessage()), var248);
                                continue;
                            }

                            IllegalAccessException var249 = var253;
                            Log.i(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var249.getMessage()), var249);
                            continue;
                        }

                        NoSuchMethodException var250 = var254;
                        Log.i(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var250.getMessage()), var250);
                        continue;
                    }

                    JSONException var251 = var255;
                    Log.e(Application.B("颈磼뙳螣剜꾦峧솼ዷ푘荅ﲳ挞\ue42a\udcb7쿃忾"), TSLog.error(var251.getMessage()), var251);
                }
            }

        }

        private void a(String var1) {
            TSConfig.Builder var10000 = this;
            List var2;
            synchronized(var2 = this.dirtyFields){}

            boolean var10001;
            Throwable var23;
            boolean var24;
            try {
                var24 = var10000.dirtyFields.contains(var1);
            } catch (Throwable var22) {
                var23 = var22;
                var10001 = false;
                throw var23;
            }

            if (var24) {
                try {
                    ;
                } catch (Throwable var19) {
                    var23 = var19;
                    var10001 = false;
                    throw var23;
                }
            } else {
                List var25;
                try {
                    var25 = var2;
                    this.dirtyFields.add(var1);
                } catch (Throwable var21) {
                    var23 = var21;
                    var10001 = false;
                    throw var23;
                }

                try {
                    ;
                } catch (Throwable var20) {
                    var23 = var20;
                    var10001 = false;
                    throw var23;
                }
            }
        }

        private void a(List<String> var1) {
            List var2;
            List var10000 = var2 = this.dirtyFields;
            TSConfig.Builder var10001 = this;
            synchronized(var2){}

            Throwable var9;
            boolean var10;
            try {
                var10001.dirtyFields.addAll(var1);
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

        private Boolean a(String var1, Boolean var2, Boolean var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private String a(String var1, String var2, String var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Integer a(String var1, Integer var2, Integer var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Long a(String var1, Long var2, Long var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Double a(String var1, Double var2, Double var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private Float a(String var1, Float var2, Float var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private JSONObject a(String var1, JSONObject var2, JSONObject var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private JSONArray a(String var1, JSONArray var2, JSONArray var3) {
            if (!var2.equals(var3)) {
                this.a(var1);
            }

            return var2;
        }

        private List<String> a(String var1, List<String> var2, List<String> var3) {
            if (var3.containsAll(var2) && !var2.isEmpty()) {
                var3 = var2;
            } else {
                if (var2 != null) {
                    var3 = var2;
                }

                this.a(var1);
            }

            return var3;
        }

        private Map<String, String> a(String var1, Map<String, String> var2, Map<String, String> var3) {
            if (var3 == null || !var3.equals(var2)) {
                this.a(var1);
            }

            return var2;
        }

        private JSONObject b(boolean var1) {
            TSConfig.Builder var10000 = this;
            JSONObject var2;
            var2 = new JSONObject.<init>();

            JSONException var78;
            label290: {
                IllegalAccessException var77;
                label272: {
                    boolean var10001;
                    Field[] var79;
                    try {
                        var79 = var10000.getClass().getDeclaredFields();
                    } catch (JSONException var71) {
                        var78 = var71;
                        var10001 = false;
                        break label290;
                    } catch (IllegalAccessException var72) {
                        var77 = var72;
                        var10001 = false;
                        break label272;
                    }

                    Field[] var3 = var79;

                    int var80;
                    try {
                        var80 = var79.length;
                    } catch (JSONException var69) {
                        var78 = var69;
                        var10001 = false;
                        break label290;
                    } catch (IllegalAccessException var70) {
                        var77 = var70;
                        var10001 = false;
                        break label272;
                    }

                    int var4 = var80;
                    int var5 = 0;

                    label258:
                    while(true) {
                        if (var5 >= var4) {
                            return var2;
                        }

                        Field var82;
                        try {
                            var10000 = this;
                            var82 = var3[var5];
                        } catch (JSONException var67) {
                            var78 = var67;
                            var10001 = false;
                            break label290;
                        } catch (IllegalAccessException var68) {
                            var77 = var68;
                            var10001 = false;
                            break;
                        }

                        Field var6 = var82;

                        boolean var84;
                        try {
                            var84 = var10000.a(var82);
                        } catch (JSONException var65) {
                            var78 = var65;
                            var10001 = false;
                            break label290;
                        } catch (IllegalAccessException var66) {
                            var77 = var66;
                            var10001 = false;
                            break;
                        }

                        if (var84) {
                            String var10002;
                            TSConfig.Builder var85;
                            Field var86;
                            try {
                                var86 = var6;
                                var85 = this;
                                var10002 = var6.getName();
                            } catch (JSONException var63) {
                                var78 = var63;
                                var10001 = false;
                                break label290;
                            } catch (IllegalAccessException var64) {
                                var77 = var64;
                                var10001 = false;
                                break;
                            }

                            String var7 = var10002;

                            Object var87;
                            try {
                                var87 = var86.get(var85);
                            } catch (JSONException var61) {
                                var78 = var61;
                                var10001 = false;
                                break label290;
                            } catch (IllegalAccessException var62) {
                                var77 = var62;
                                var10001 = false;
                                break;
                            }

                            Object var8 = var87;
                            if (var87 != null) {
                                Class var88;
                                Class var89;
                                try {
                                    var89 = var6.getType();
                                    var88 = Map.class;
                                } catch (JSONException var59) {
                                    var78 = var59;
                                    var10001 = false;
                                    break label290;
                                } catch (IllegalAccessException var60) {
                                    var77 = var60;
                                    var10001 = false;
                                    break;
                                }

                                if (var89 == var88) {
                                    try {
                                        var2.put(var7, new JSONObject((Map)var8));
                                    } catch (JSONException var57) {
                                        var78 = var57;
                                        var10001 = false;
                                        break label290;
                                    } catch (IllegalAccessException var58) {
                                        var77 = var58;
                                        var10001 = false;
                                        break;
                                    }
                                } else {
                                    try {
                                        var89 = var6.getType();
                                        var88 = List.class;
                                    } catch (JSONException var55) {
                                        var78 = var55;
                                        var10001 = false;
                                        break label290;
                                    } catch (IllegalAccessException var56) {
                                        var77 = var56;
                                        var10001 = false;
                                        break;
                                    }

                                    if (var89 == var88) {
                                        JSONArray var90;
                                        try {
                                            var87 = var8;
                                            var90 = new JSONArray;
                                        } catch (JSONException var53) {
                                            var78 = var53;
                                            var10001 = false;
                                            break label290;
                                        } catch (IllegalAccessException var54) {
                                            var77 = var54;
                                            var10001 = false;
                                            break;
                                        }

                                        JSONArray var75 = var90;

                                        try {
                                            var90.<init>();
                                            var89 = var87.getClass();
                                            var88 = ArrayList.class;
                                        } catch (JSONException var51) {
                                            var78 = var51;
                                            var10001 = false;
                                            break label290;
                                        } catch (IllegalAccessException var52) {
                                            var77 = var52;
                                            var10001 = false;
                                            break;
                                        }

                                        if (var89 == var88) {
                                            Iterator var91;
                                            try {
                                                var91 = ((List)var8).iterator();
                                            } catch (JSONException var49) {
                                                var78 = var49;
                                                var10001 = false;
                                                break label290;
                                            } catch (IllegalAccessException var50) {
                                                var77 = var50;
                                                var10001 = false;
                                                break;
                                            }

                                            Iterator var76 = var91;

                                            while(true) {
                                                try {
                                                    var84 = var76.hasNext();
                                                } catch (JSONException var45) {
                                                    var78 = var45;
                                                    var10001 = false;
                                                    break label290;
                                                } catch (IllegalAccessException var46) {
                                                    var77 = var46;
                                                    var10001 = false;
                                                    break label258;
                                                }

                                                if (!var84) {
                                                    try {
                                                        var2.put(var7, var75);
                                                        break;
                                                    } catch (JSONException var43) {
                                                        var78 = var43;
                                                        var10001 = false;
                                                        break label290;
                                                    } catch (IllegalAccessException var44) {
                                                        var77 = var44;
                                                        var10001 = false;
                                                        break label258;
                                                    }
                                                }

                                                try {
                                                    var75.put(var76.next());
                                                } catch (JSONException var47) {
                                                    var78 = var47;
                                                    var10001 = false;
                                                    break label290;
                                                } catch (IllegalAccessException var48) {
                                                    var77 = var48;
                                                    var10001 = false;
                                                    break label258;
                                                }
                                            }
                                        }
                                    } else {
                                        try {
                                            var89 = var6.getType();
                                            var88 = Integer.class;
                                        } catch (JSONException var41) {
                                            var78 = var41;
                                            var10001 = false;
                                            break label290;
                                        } catch (IllegalAccessException var42) {
                                            var77 = var42;
                                            var10001 = false;
                                            break;
                                        }

                                        if (var89 == var88) {
                                            try {
                                                var2.put(var7, (Integer)var8);
                                            } catch (JSONException var39) {
                                                var78 = var39;
                                                var10001 = false;
                                                break label290;
                                            } catch (IllegalAccessException var40) {
                                                var77 = var40;
                                                var10001 = false;
                                                break;
                                            }
                                        } else {
                                            try {
                                                var89 = var6.getType();
                                                var88 = Long.class;
                                            } catch (JSONException var37) {
                                                var78 = var37;
                                                var10001 = false;
                                                break label290;
                                            } catch (IllegalAccessException var38) {
                                                var77 = var38;
                                                var10001 = false;
                                                break;
                                            }

                                            if (var89 == var88) {
                                                try {
                                                    var2.put(var7, (Long)var8);
                                                } catch (JSONException var35) {
                                                    var78 = var35;
                                                    var10001 = false;
                                                    break label290;
                                                } catch (IllegalAccessException var36) {
                                                    var77 = var36;
                                                    var10001 = false;
                                                    break;
                                                }
                                            } else {
                                                label278: {
                                                    try {
                                                        var89 = var6.getType();
                                                        var88 = Double.class;
                                                    } catch (JSONException var33) {
                                                        var78 = var33;
                                                        var10001 = false;
                                                        break label290;
                                                    } catch (IllegalAccessException var34) {
                                                        var77 = var34;
                                                        var10001 = false;
                                                        break;
                                                    }

                                                    double var81;
                                                    String var92;
                                                    JSONObject var93;
                                                    if (var89 == var88) {
                                                        try {
                                                            var93 = var2;
                                                            var92 = var7;
                                                            var81 = (Double)var8;
                                                        } catch (JSONException var29) {
                                                            var78 = var29;
                                                            var10001 = false;
                                                            break label290;
                                                        } catch (IllegalAccessException var30) {
                                                            var77 = var30;
                                                            var10001 = false;
                                                            break;
                                                        }
                                                    } else {
                                                        try {
                                                            var89 = var6.getType();
                                                            var88 = Float.class;
                                                        } catch (JSONException var31) {
                                                            var78 = var31;
                                                            var10001 = false;
                                                            break label290;
                                                        } catch (IllegalAccessException var32) {
                                                            var77 = var32;
                                                            var10001 = false;
                                                            break;
                                                        }

                                                        if (var89 != var88) {
                                                            try {
                                                                var89 = var6.getType();
                                                                var88 = TSNotification.class;
                                                            } catch (JSONException var23) {
                                                                var78 = var23;
                                                                var10001 = false;
                                                                break label290;
                                                            } catch (IllegalAccessException var24) {
                                                                var77 = var24;
                                                                var10001 = false;
                                                                break;
                                                            }

                                                            JSONObject var83;
                                                            if (var89 == var88) {
                                                                try {
                                                                    var93 = var2;
                                                                    var92 = var7;
                                                                    var83 = ((TSNotification)var8).toJson(var1);
                                                                } catch (JSONException var17) {
                                                                    var78 = var17;
                                                                    var10001 = false;
                                                                    break label290;
                                                                } catch (IllegalAccessException var18) {
                                                                    var77 = var18;
                                                                    var10001 = false;
                                                                    break;
                                                                }
                                                            } else {
                                                                try {
                                                                    var89 = var6.getType();
                                                                    var88 = TSAuthorization.class;
                                                                } catch (JSONException var21) {
                                                                    var78 = var21;
                                                                    var10001 = false;
                                                                    break label290;
                                                                } catch (IllegalAccessException var22) {
                                                                    var77 = var22;
                                                                    var10001 = false;
                                                                    break;
                                                                }

                                                                if (var89 == var88) {
                                                                    try {
                                                                        var93 = var2;
                                                                        var92 = var7;
                                                                        var83 = ((TSAuthorization)var8).toJson(var1);
                                                                    } catch (JSONException var15) {
                                                                        var78 = var15;
                                                                        var10001 = false;
                                                                        break label290;
                                                                    } catch (IllegalAccessException var16) {
                                                                        var77 = var16;
                                                                        var10001 = false;
                                                                        break;
                                                                    }
                                                                } else {
                                                                    try {
                                                                        var89 = var6.getType();
                                                                        var88 = TSBackgroundPermissionRationale.class;
                                                                    } catch (JSONException var19) {
                                                                        var78 = var19;
                                                                        var10001 = false;
                                                                        break label290;
                                                                    } catch (IllegalAccessException var20) {
                                                                        var77 = var20;
                                                                        var10001 = false;
                                                                        break;
                                                                    }

                                                                    if (var89 != var88) {
                                                                        try {
                                                                            var2.put(var7, var8);
                                                                            break label278;
                                                                        } catch (JSONException var9) {
                                                                            var78 = var9;
                                                                            var10001 = false;
                                                                            break label290;
                                                                        } catch (IllegalAccessException var10) {
                                                                            var77 = var10;
                                                                            var10001 = false;
                                                                            break;
                                                                        }
                                                                    }

                                                                    try {
                                                                        var93 = var2;
                                                                        var92 = var7;
                                                                        var83 = ((TSBackgroundPermissionRationale)var8).toJson(var1);
                                                                    } catch (JSONException var13) {
                                                                        var78 = var13;
                                                                        var10001 = false;
                                                                        break label290;
                                                                    } catch (IllegalAccessException var14) {
                                                                        var77 = var14;
                                                                        var10001 = false;
                                                                        break;
                                                                    }
                                                                }
                                                            }

                                                            try {
                                                                var93.put(var92, var83);
                                                                break label278;
                                                            } catch (JSONException var11) {
                                                                var78 = var11;
                                                                var10001 = false;
                                                                break label290;
                                                            } catch (IllegalAccessException var12) {
                                                                var77 = var12;
                                                                var10001 = false;
                                                                break;
                                                            }
                                                        }

                                                        try {
                                                            var93 = var2;
                                                            var92 = var7;
                                                            var81 = ((Float)var8).doubleValue();
                                                        } catch (JSONException var27) {
                                                            var78 = var27;
                                                            var10001 = false;
                                                            break label290;
                                                        } catch (IllegalAccessException var28) {
                                                            var77 = var28;
                                                            var10001 = false;
                                                            break;
                                                        }
                                                    }

                                                    try {
                                                        var93.put(var92, var81);
                                                    } catch (JSONException var25) {
                                                        var78 = var25;
                                                        var10001 = false;
                                                        break label290;
                                                    } catch (IllegalAccessException var26) {
                                                        var77 = var26;
                                                        var10001 = false;
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        ++var5;
                    }
                }

                IllegalAccessException var73 = var77;
                Log.i(Application.B("抛\udf93瓲俖ꋜ㑑綉桼걪燩盻彫ظ\uefc0目狭攐"), TSLog.error(var73.getMessage()));
                TSLog.logger.error(TSLog.error(var73.getMessage()), var73);
                return var2;
            }

            JSONException var74 = var78;
            Log.i(Application.B("抛\udf93瓲俖ꋜ㑑綉桼걪燩盻彫ظ\uefc0目狭攐"), TSLog.error(var74.getMessage()));
            TSLog.logger.error(TSLog.error(var74.getMessage()), var74);
            return var2;
        }

        private boolean a(Field var1) {
            return !IGNORED_FIELDS.contains(var1.getName()) && !Modifier.isStatic(var1.getModifiers());
        }

        public void commit() {
            if (TSConfig.q != null) {
                TSConfig.q.b();
            }

        }

        public TSConfig.Builder setIsMoving(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.isMoving;
            var10001.isMoving = var10002.a(Application.B("\ufd44\uf65aꕽ菩㧨缵︳⸁"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDistanceFilter(Float var1) {
            if (var1 < 0.0F) {
                TSLog.logger.warn(TSLog.warn(Application.B("ጌ殂\ufe6d畎櫷价⋞䑓㲒鿌\uf225臭ေⅿⷷ쉛\ud82eᐖა\udf3c铵襩暀㞰") + var1 + Application.B("፫毌︻畮櫫仮⋖䐊㲟鿋\uf231行န⅔ⷒ쉿\ud83dᐳშ\udf17铔襒曩㟄渣攛⤬\ue225\ueabe鹐텔덆ꍺ䥜燙\ue965\uf59e") + 10.0F));
                var1 = 10.0F;
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Float var2 = this.distanceFilter;
            var10001.distanceFilter = var10002.a(Application.B("ጡ殅﹨畛櫺仰⋙䐖㲰鿌\uf23a臭ဵⅣ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDesiredAccuracy(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.desiredAccuracy;
            var10001.desiredAccuracy = var10002.a(Application.B("妹\ue3b3ᅙ䲳艴\uef3f䬤᧟䍯疀駢∕䤘狖眷"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDesiredOdometerAccuracy(Float var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Float var2 = this.desiredOdometerAccuracy;
            var10001.desiredOdometerAccuracy = var10002.a(Application.B("ﷂ뭁싮⡉荻\udbad捧ჳ띖鋤萸땭챱㫤\uf1b2쉁㔿ꠗ\uf4dcᙻ꾦ȕ棟"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationUpdateInterval(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.locationUpdateInterval;
            var10001.locationUpdateInterval = var10002.a(Application.B("嗍㢮惺최暲訩ⱕ⠰刅幓泰क戫ꐷ煎듵\uf678퐜괠\uf850ᮆ\ue144"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setFastestLocationUpdateInterval(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.fastestLocationUpdateInterval;
            var10001.fastestLocationUpdateInterval = var10002.a(Application.B("瞝㩱\ue66b끖骳佳\u0002댲뙴褪\ud9cf꿲랋섍똡梾ᜁ瘂퓤쿖笿\ue7ce\uf0af툙嵈⬝Ứܞ賵"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationTimeout(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.locationTimeout;
            var10001.locationTimeout = var10002.a(Application.B("盓Ⴠ뢣뮶\uf733箣⣰裔髤ᦊﴛ膙ꋣ\ue89a꛵"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDeferTime(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.deferTime;
            var10001.deferTime = var10002.a(Application.B("컸炕⯮쁋숟禎辻ⱪ\u2434"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableElasticity(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableElasticity;
            var10001.disableElasticity = var10002.a(Application.B("ᢽ㯹\ud8ed쯔\uf38cᜦ\uf791\ud97a㗫\ue61c㾷\uf456൶\uec66ᙐꂹ욹"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setElasticityMultiplier(Float var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Float var2 = this.elasticityMultiplier;
            var10001.elasticityMultiplier = var10002.a(Application.B("㨅ﾃ縡\uda2aꢖƗ푘垎梚䬦⌭ⰹ㌎툺拏\uead1衵ᡪ쇍\uf878"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAllowIdenticalLocations(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.allowIdenticalLocations;
            var10001.allowIdenticalLocations = var10002.a(Application.B("쥽筥騱⤘㟄\u1cca㺌䢡驻㽝孛ධ䀓잀栣ၥ腳\u0fe7≿Ｈꏁ鈯\ue883"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setActivityRecognitionInterval(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.activityRecognitionInterval;
            var10001.activityRecognitionInterval = var10002.a(Application.B("妳廑삣馫晲蜍\ue234눼鮛은嚠ᠠ\uf784垧⇯㬑睫\ued1dᆁ\uec37\uf383춄\ue7dd圐㙪슯舣"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMinimumActivityRecognitionConfidence(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.minimumActivityRecognitionConfidence;
            var10001.minimumActivityRecognitionConfidence = var10002.a(Application.B("⠡穡\uf34b哶쮫犒ꄽ♮ᇸ犗땐㧻쭐孼硤棨ࢣ뉦䔩ﰰ\ue692篞혈꺞䄔烀\ued08눇⩧젇黎↦\uf889Ԏ哱눚"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setTriggerActivities(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.triggerActivities;
            var10001.triggerActivities = var10002.a(Application.B("\ue3c7ᨪ雑\uf3dd㋬\ufa6f\udbc6ǰ컊簪텙䜻\ue1c8莒秊臛뢏"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableStopDetection(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableStopDetection;
            var10001.disableStopDetection = var10002.a(Application.B("\ue633還㗟㟚抣Ề닎逃\uf013햪ӛ㆐⯋ࣳ㒾滜裵䖈襒鐚"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableMotionActivityUpdates(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableMotionActivityUpdates;
            var10001.disableMotionActivityUpdates = var10002.a(Application.B("䫉輁⯅퇇珐᱿娒䕺ڃ잎詡捉\ueca2㍣氪傆ꖊ눮ꐹ熯㞆喸̦濫鱈乒滆㘦"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStationaryRadius(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.stationaryRadius;
            var10001.stationaryRadius = var10002.a(Application.B("蠪끦唆ｗ휢ꪲ⧁︖۠귕\udc57髐㚰揧䕥ඦ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopTimeout(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.stopTimeout;
            var10001.stopTimeout = var10002.a(Application.B("蕰╾硍鬌蓲㠩敀殛僈송陖"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopOnStationary(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.stopOnStationary;
            var10001.stopOnStationary = var10002.a(Application.B("쨪捦玊縲ꋨ㻏ㆇま\ue4a1ዖ㼗模\ue5b8卆鈩뉩"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMotionTriggerDelay(Long var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.motionTriggerDelay;
            var10001.motionTriggerDelay = var10002.a(Application.B("젤ピᝆڕ䐩隋\uef0dꂸ啠鷨毉\uf39d\udf2f\ud8fb\ue1d8\ue08c魐黿"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setEnableTimestampMeta(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.enableTimestampMeta;
            var10001.enableTimestampMeta = var10002.a(Application.B("쨉\udc8aꡁ踫邮禨᧘哄ﮦ땧\udfa8≮ꉲ뇓啎࠳\u0b8c龤砓"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setSpeedJumpFilter(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.speedJumpFilter;
            var10001.speedJumpFilter = var10002.a(Application.B("ﴵﵝꟹ컥┿㾐쟜뿦ᎉ\ue0e0쌩ꑒ訿ꪐ㦥"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setUseSignificantChangesOnly(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.useSignificantChangesOnly;
            var10001.useSignificantChangesOnly = var10002.a(Application.B("覕\ue2a5빶讬\udc0a䛱惎⡎ᣬ慧㖅\udcad浡ꨢᯓ虈\uec59ㇹ\uf244\ueffb崱糜寧吏霉"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationAuthorizationRequest(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.locationAuthorizationRequest;
            var10001.locationAuthorizationRequest = var10002.a(Application.B("㼃괅·뫁䗧蒞컅\uf181浟뚿라\ue3e1昡ᗞ取ꪉ㪭⾬蠚ⱊ鞻뼸鋄쮹麚쒜歏\ue8ca"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableLocationAuthorizationAlert(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableLocationAuthorizationAlert;
            var10001.disableLocationAuthorizationAlert = var10002.a(Application.B("པ딌㨿掀⪭꽗怶輖㜀븨䇺뗲\ue3ba䣳볈⎧읡ེ⡽퀺抷韑‹\udc08쮚鉑읜뛡栵꿸ࡂ\uf5f1㲓"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setPersist(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.persist;
            var10001.persist = var10002.a(Application.B("槏\udabc艬缛逾\ufdde냜"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setPersistMode(Integer var1) {
            switch(var1) {
                default:
                    TSLog.logger.warn(TSLog.warn(Application.B("榭踏\uf447鳈ਗ뭧錁뜽\uf0b0胦谒ﳉ흶ἠࠦ᳚ᣝ᭴钾\uddd5\uda51섫\uf072㧽⮇\ude16ѕἷⷬᆅ졽") + var1 + Application.B("槊蹁\uf411鳼ਈ뭧錋띺\uf0e6胣谛ﳚ흲ήࠬ᳁ᢏᬢ钯\udddc\uda56섽\uf021㦮") + 2));
                    var1 = 2;
                case -1:
                case 0:
                case 1:
                case 2:
                    TSConfig.Builder var10000 = this;
                    TSConfig.Builder var10001 = this;
                    TSConfig.Builder var10002 = this;
                    Integer var2 = this.persistMode;
                    var10001.persistMode = var10002.a(Application.B("榔踄\uf443鳚\u0a12뭽錑띐\uf0a9胣谛"), var1, var2);
                    return var10000;
            }
        }

        public TSConfig.Builder setMaxDaysToPersist(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.maxDaysToPersist;
            var10001.maxDaysToPersist = var10002.a(Application.B("銀￪꺨\uef2f\ufaf5칭婀❊Ｌ\udf6c둵报骛춗鯳境"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMaxRecordsToPersist(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.maxRecordsToPersist;
            var10001.maxRecordsToPersist = var10002.a(Application.B("妗\uf64e㬆㫫緋\uf056ꇐ䩖딃쁓㕎聦뙖䭽ⴾ陝\ue21cॊ밢"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setUrl(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.url;
            var10001.url = var10002.a(Application.B("ᨎ䷊䩔"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMethod(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.method;
            var10001.method = var10002.a(Application.B("淬乛ϔ\ue187嘬힡"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAutoSync(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.autoSync;
            var10001.autoSync = var10002.a(Application.B("械\uf66c濧\ue617窢挗㯠翨"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAutoSyncThreshold(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.autoSyncThreshold;
            var10001.autoSyncThreshold = var10002.a(Application.B("䵦붚ហ東\ue3d6䡽耾㓙朷訦\ue30eㄪ詟\uf2a4ᐁ\uda72舛"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setBatchSync(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.batchSync;
            var10001.batchSync = var10002.a(Application.B("᎘柨Ở㯞譶ꋇṘГ㤖"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setMaxBatchSize(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.maxBatchSize;
            var10001.maxBatchSize = var10002.a(Application.B("\ud8cd領럡ꤟ\uf3e0냫鑘Ὠ悑㲺瞙퇧"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setParams(JSONObject var1) {
            if (var1 == null) {
                var1 = new JSONObject.<init>();
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            JSONObject var2 = this.params;
            var10001.params = var10002.a(Application.B("✵⚿㖒堙峯ᕤ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHeader(String var1, String var2) {
            if (this.headers == null) {
                JSONObject var3;
                var3 = new JSONObject.<init>();
                this.headers = var3;
            }

            JSONException var10000;
            label28: {
                boolean var10001;
                TSConfig.Builder var7;
                try {
                    var7 = this;
                    this.headers.put(var1, var2);
                } catch (JSONException var5) {
                    var10000 = var5;
                    var10001 = false;
                    break label28;
                }

                try {
                    var7.a(Application.B("熗ꔖْ\ue1bd\u2068蒬\ufe6d"));
                    return this;
                } catch (JSONException var4) {
                    var10000 = var4;
                    var10001 = false;
                }
            }

            JSONException var6 = var10000;
            TSLog.logger.error(TSLog.error(var6.getMessage()), var6);
            return this;
        }

        public TSConfig.Builder setHeaders(JSONObject var1) {
            if (var1 == null) {
                var1 = new JSONObject.<init>();
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            JSONObject var2 = this.headers;
            var10001.headers = var10002.a(Application.B("\u0c73㔽㗿ᬎ肃瑩됇"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setExtras(JSONObject var1) {
            if (var1 == null) {
                var1 = new JSONObject.<init>();
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            JSONObject var2 = this.extras;
            var10001.extras = var10002.a(Application.B("盫인\ue16c綯\uf232㺭"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHttpRootProperty(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.httpRootProperty;
            var10001.httpRootProperty = var10002.a(Application.B("䏔馱潟ἢ刌晔氛䆊ꏌ䜫稟鄢\ue159㯜\uf5a3\uf888"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationTemplate(String var1) {
            if (var1 == null) {
                var1 = "";
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.locationTemplate;
            var10001.locationTemplate = var10002.a(Application.B("ⷱ☣鰾ퟓ珟迸࿉㜑斸伲橭⼆跘㑡罅ധ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setGeofenceTemplate(String var1) {
            if (var1 == null) {
                var1 = "";
            }

            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.geofenceTemplate;
            var10001.geofenceTemplate = var10002.a(Application.B("텫❨젣돸壣㝁댄\ue897腁⚌鄋\uf717ꉸ됓ⱋ㍗"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLocationsOrderDirection(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.locationsOrderDirection;
            var10001.locationsOrderDirection = var10002.a(Application.B("〪捾Ṭ栩谢沊\uee42⼢\ud8d2샊剌ᖽ嶵\uf836嶑芉봧燾⫳䒾륒꽥븧"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHttpTimeout(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.httpTimeout;
            var10001.httpTimeout = var10002.a(Application.B("\udd14뱯\udfc1赠琐ꯇ紬쒺蜘∠晚"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDisableAutoSyncOnCellular(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.disableAutoSyncOnCellular;
            var10001.disableAutoSyncOnCellular = var10002.a(Application.B("\uf76b쩅\uf6db⦫⅞\ue8a0䙿䚍طၞ\uf0db党中氈֕鮩㸀簢Ί盲喫\uf120苕㧦읆"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setAuthorization(TSAuthorization var1) {
            if (this.authorization.update(var1)) {
                this.a(Application.B("\uf82b捹飜ꠞ㗭樋㠭塓\uef68䈕ⶣ僑킵"));
                this.a(this.authorization.getDirtyFields());
            }

            this.authorization.applyDefaults();
            return this;
        }

        public TSConfig.Builder setGeofenceProximityRadius(Long var1) {
            var1 = var1 < 1000L ? 1000L : var1;
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Long var2 = this.geofenceProximityRadius;
            var10001.geofenceProximityRadius = var10002.a(Application.B("笣礨㋴횴럦苏\uea2f뛿䢁랎奐\uecb5鐜韍惘ᢂᝠΖ㔸⎰븨㩷飡"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setGeofenceInitialTriggerEntry(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.geofenceInitialTriggerEntry;
            var10001.geofenceInitialTriggerEntry = var10002.a(Application.B("툚┡캅͢兄䬒氕榘䳓閲名挫\ue0bfឌ꺵뜼챗ꮶ윹늞יִꇔ\uf1d8묧ﶙ⿳濦"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setGeofenceModeHighAccuracy(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.geofenceModeHighAccuracy;
            var10001.geofenceModeHighAccuracy = var10002.a(Application.B("\u070f偊ݕޯ혯\u0c50玨気ㆎ\u192fᯠᑨ벥絞⼘祤犑谄\uf82a䆪토嗑斲ꗎ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopOnTerminate(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.stopOnTerminate;
            var10001.stopOnTerminate = var10002.a(Application.B("\uf6c1ݱᰍ噉荎甭ꌀ灔μ\ue1fe뇤貺耳㦒頫"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStartOnBoot(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.startOnBoot;
            var10001.startOnBoot = var10002.a(Application.B("嵗舙⫂ս杈䏁忕糕籍㻍\uea18"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setStopAfterElapsedMinutes(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.stopAfterElapsedMinutes;
            var10001.stopAfterElapsedMinutes = var10002.a(Application.B("⽴º毈ཨ쓍㈀邶趠\u2d29\u0cceꊃ뮻\ufaec敷瓉\uda92ᐧ볮ﯻ壻ꭜ䕱ĺ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHeartbeatInterval(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.heartbeatInterval;
            var10001.heartbeatInterval = var10002.a(Application.B("폶僼䍵牦夫桅麎垱릓\uf5e5ꮯ\udd0e㔡钸榝噯Ɍ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setForegroundService(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.foregroundService;
            var10001.foregroundService = var10002.a(Application.B("䀚꤅繕嶜ཹឧ啟♱囊탚ᵺ쇅⒅ᾷᖭ鮲\ued07"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setNotification(TSNotification var1) {
            if (this.notification.update(var1)) {
                this.a(Application.B("榓젶谠\uea67䇭㗂॒囄趨軬\ue3a0㚒"));
                this.a(this.notification.getDirtyFields());
            }

            this.notification.applyDefaults();
            return this;
        }

        public TSConfig.Builder setBackgroundPermissionRationale(TSBackgroundPermissionRationale var1) {
            if (this.backgroundPermissionRationale.update(var1)) {
                this.a(Application.B("\udfb0滷딌訒ꂷᾂ㊟譑ᚱ㴁䃺솃ᕛ䠽堮絗嫡\uf47e睧힛쏚剞쥾鷑ﴋ疫뙮\ue799ῗ"));
                this.a(this.backgroundPermissionRationale.getDirtyFields());
            }

            this.backgroundPermissionRationale.applyDefaults();
            return this;
        }

        public TSConfig.Builder setConfigUrl(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.configUrl;
            var10001.configUrl = var10002.a(Application.B("幙凣ᄌᮔ恺\ue0e6피㸇괫"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setSchedule(List<String> var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            List var2 = this.schedule;
            var10001.schedule = var10002.a(Application.B("㷉✒ᙎ갠䵓\uf6a9瘻\ue27f"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setScheduleUseAlarmManager(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.scheduleUseAlarmManager;
            var10001.scheduleUseAlarmManager = var10002.a(Application.B("㊡ঋ\udfc1\ue94d榃⽥諆離Ỵ꿍푺屷\uda53싃껻蹇咽\ue81f펭㬲肠춏ࠥ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setEnableHeadless(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.enableHeadless;
            var10001.enableHeadless = var10002.a(Application.B("ῖ\ue23c\uf59f蘊\u09d4뾈ㄆম䱜\uede8㵿탫莖ധ"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setHeadlessJobService(String var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            String var2 = this.headlessJobService;
            var10001.headlessJobService = var10002.a(Application.B("ᑨ쯷\uf13d\uf196縲│曜䦗\uf7d6㬎⼔黇勗굈裲歊퓩嵿"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setDebug(Boolean var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Boolean var2 = this.debug;
            var10001.debug = var10002.a(Application.B("\uf291\uf50a\uf398蘏䕕"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLogLevel(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.logLevel;
            var10001.logLevel = var10002.a(Application.B("\ue947剳\uf270尐䌫裁涁̕"), var1, var2);
            return var10000;
        }

        public TSConfig.Builder setLogMaxDays(Integer var1) {
            TSConfig.Builder var10000 = this;
            TSConfig.Builder var10001 = this;
            TSConfig.Builder var10002 = this;
            Integer var2 = this.logMaxDays;
            var10001.logMaxDays = var10002.a(Application.B("摨㺵\uec7b㻕ざポ蹚뼀\ua83d쉷"), var1, var2);
            return var10000;
        }
    }
}
