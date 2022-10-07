//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.geofence;

import com.google.android.gms.location.Geofence;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class TSGeofence {
    public static final String FIELD_IDENTIFIER = Application.B("ﰫజ巨⪏魿\uda7b\uf6fd㻙퀶\ueceb");
    public static final String FIELD_LATITUDE = Application.B("ﰮఙ巹⪈魿\uda67\uf6ff㻕");
    public static final String FIELD_LONGITUDE = Application.B("ﰮగ巣⪆魢\uda66\uf6ee㻔퀶");
    public static final String FIELD_RADIUS = Application.B("ﰰఙ巩⪈魾\uda61");
    public static final String FIELD_NOTIFY_ON_ENTRY = Application.B("ﰬగ巹⪈魭\uda6b\uf6d4㻞퀖\uecf7묍\udfb1氆");
    public static final String FIELD_NOTIFY_ON_EXIT = Application.B("ﰬగ巹⪈魭\uda6b\uf6d4㻞퀖\uece1묐\udfb7");
    public static final String FIELD_NOTIFY_ON_DWELL = Application.B("ﰬగ巹⪈魭\uda6b\uf6d4㻞퀗\uecee묜\udfaf氓");
    public static final String FIELD_LOITERING_DELAY = Application.B("ﰮగ巤⪕魮\uda60\uf6f2㻞퀴\uecdd묜\udfaf氞绿");
    public static final String FIELD_EXTRAS = Application.B("ﰧఀ巹⪓魪\uda61");
    private static final String l = Application.B("ﰎఙ巹⪈魿\uda67\uf6ff㻕큳\uecf0묊\udfe3氍绣梤偗సᶓꬄ\udcf1");
    private static final String m = Application.B("ﰎగ巣⪆魢\uda66\uf6ee㻔퀶\uecb9묐\udfb0江维械偓తᶈꬓ\udcf0ᘙ");
    private static final String n = Application.B("ﰐఙ巩⪈魾\uda61\uf6bb㻙퀠\uecb9묋\udfa6氎绳梼偐ఴᶅ");
    private static final String o = Application.B("ﰋజ巨⪏魿\uda7b\uf6fd㻙퀶\ueceb뭙\udfaa氌约梧假ఠᶔ\uab08\udce7ᘘ홬");
    private static final String p = Application.B("ﰃౘ巹⪓魪\uda7c\uf6e8㻙퀧\uecf0묖\udfad汒绲梬偒ఴ᷁\uab08\udce6ᙝ홺ⶦ畠ﴩꇜ䓾✚㦺臌媪묯崹栩ᰪ\u0b64屺噛虶\ue267Ꝣ⬕퍦鍭깑쐟䤠ם\ue0db℥㎩㠞㚀䡠쐐㯚猴鐼禵俈￤㮴逩堾쀯ᶵ삤\uf728\u05cf\uee2d⒠渎环布껍걢");
    private static final String q = Application.B("ﰋఖ巻⪀魧\uda7b\uf6ff㺐퀙\uecca묶\udf8d江绠梺偐\u0c71ᶄ\uab19\udce1ᘏ황ⶰ");
    private final String a;
    private final Double b;
    private final Double c;
    private final Float d;
    private final Boolean e;
    private final Boolean f;
    private final Boolean g;
    private final Integer h;
    private final Integer i;
    private final JSONObject j;
    private Geofence k;

    public TSGeofence(TSGeofence.Builder var1) {
        this.a = var1.a;
        this.b = var1.b;
        this.c = var1.c;
        this.d = var1.d;
        this.e = var1.e;
        this.f = var1.f;
        this.g = var1.g;
        this.h = var1.h;
        this.j = var1.j;
        this.i = var1.i;
    }

    public String getIdentifier() {
        return this.a;
    }

    public double getLatitude() {
        return this.b;
    }

    public double getLongitude() {
        return this.c;
    }

    public float getRadius() {
        return this.d;
    }

    public boolean getNotifyOnEntry() {
        return this.e;
    }

    public boolean getNotifyOnExit() {
        return this.f;
    }

    public boolean getNotifyOnDwell() {
        return this.g;
    }

    public int getLoiteringDelay() {
        return this.h;
    }

    public JSONObject getExtras() {
        return this.j;
    }

    public int getNoificationResponsiveness() {
        return this.i;
    }

    public Geofence build() {
        Geofence var1;
        if ((var1 = this.k) != null) {
            return var1;
        } else {
            int var5 = 0;
            if (this.e) {
                var5 = 1;
            }

            if (this.f) {
                var5 |= 2;
            }

            if (this.g) {
                var5 |= 4;
            }

            com.google.android.gms.location.Geofence.Builder var10000 = (new com.google.android.gms.location.Geofence.Builder()).setRequestId(this.a);
            double var10001 = this.b;
            double var2 = this.c;
            float var4 = this.d;
            return this.k = var10000.setCircularRegion(var10001, var2, var4).setExpirationDuration(-1L).setTransitionTypes(var5).setLoiteringDelay(this.h).build();
        }
    }

    public JSONObject toJson() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject;
        TSGeofence var10001 = this;
        JSONObject var10002 = var1;
        TSGeofence var10003 = this;
        JSONObject var10004 = var1;
        TSGeofence var10005 = this;
        JSONObject var10006 = var1;
        TSGeofence var10007 = this;
        JSONObject var10008 = var1;
        TSGeofence var10009 = this;
        JSONObject var10010 = var1;
        TSGeofence var10011 = this;
        JSONObject var10012 = var1;
        TSGeofence var10013 = this;
        JSONObject var10014 = var1;
        JSONObject var10015 = var1;
        TSGeofence var10016 = this;
        var1.<init>();
        String var2 = Application.B("\u0a7c뫈霊ถ죄Иྪό\u0bc4᧲");

        JSONException var15;
        label93: {
            boolean var16;
            try {
                var10015.put(var2, var10016.a);
            } catch (JSONException var12) {
                var15 = var12;
                var16 = false;
                break label93;
            }

            String var17 = Application.B("੧뫍霋ฑ죅Ђ");

            float var18;
            try {
                var18 = this.d;
            } catch (JSONException var11) {
                var15 = var11;
                var16 = false;
                break label93;
            }

            double var19 = (double)var18;

            try {
                var10014.put(var17, var19);
            } catch (JSONException var10) {
                var15 = var10;
                var16 = false;
                break label93;
            }

            String var13 = Application.B("\u0a79뫍霛ฑ죄Єྨή");

            try {
                var10012.put(var13, var10013.b);
            } catch (JSONException var9) {
                var15 = var9;
                var16 = false;
                break label93;
            }

            var13 = Application.B("\u0a79뫃霁ฟ죙Ѕྐྵὴ\u0bc4");

            try {
                var10010.put(var13, var10011.c);
            } catch (JSONException var8) {
                var15 = var8;
                var16 = false;
                break label93;
            }

            var13 = Application.B("\u0a7b뫃霛ฑ죖Јྃ\u1f7e\u0be4᧮\u242aೀट");

            try {
                var10008.put(var13, var10009.e);
            } catch (JSONException var7) {
                var15 = var7;
                var16 = false;
                break label93;
            }

            var13 = Application.B("\u0a7b뫃霛ฑ죖Јྃ\u1f7e\u0be4᧸\u2437ೆ");

            try {
                var10006.put(var13, var10007.f);
            } catch (JSONException var6) {
                var15 = var6;
                var16 = false;
                break label93;
            }

            var13 = Application.B("\u0a7b뫃霛ฑ죖Јྃ\u1f7e\u0be5᧷\u243bೞऊ");

            try {
                var10004.put(var13, var10005.g);
            } catch (JSONException var5) {
                var15 = var5;
                var16 = false;
                break label93;
            }

            var13 = Application.B("\u0a79뫃霆ฌ죕Ѓྥ\u1f7eெᧄ\u243bೞइૣ");

            try {
                var10002.put(var13, var10003.h);
            } catch (JSONException var4) {
                var15 = var4;
                var16 = false;
                break label93;
            }

            var13 = Application.B("ੰ뫔霛ช죑Ђ");

            try {
                var10000.put(var13, var10001.j);
                return var1;
            } catch (JSONException var3) {
                var15 = var3;
                var16 = false;
            }
        }

        JSONException var14 = var15;
        TSLog.logger.error(var14.getMessage());
        var14.printStackTrace();
        return var1;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10001 = var1 = new HashMap;
        var1.<init>();
        String var10016 = this.a;
        var1.put(Application.B("ᳶ4䏝饤鮪鴓靈椖뾀雝"), var10016);
        var1.put(Application.B("᳭1䏜饣鮫鴉"), (double)this.d);
        Double var10012 = this.b;
        var1.put(Application.B("ᳳ1䏌饣鮪鴏鈴椚"), var10012);
        Double var10010 = this.c;
        var1.put(Application.B("ᳳ?䏖饭鮷鴎裂椛뾀"), var10010);
        Boolean var10008 = this.e;
        var1.put(Application.B("ᳱ?䏌饣鮸鴃連椑뾠雁\ue8ae⾤⚅"), var10008);
        Boolean var10006 = this.f;
        var1.put(Application.B("ᳱ?䏌饣鮸鴃連椑뾠雗\ue8b3⾢"), var10006);
        Boolean var10004 = this.g;
        var1.put(Application.B("ᳱ?䏌饣鮸鴃連椑뾡雘\ue8bf⾺⚐"), var10004);
        Integer var10002 = this.h;
        var10001.put(Application.B("ᳳ?䏑饾鮻鴈寮椑뾂雫\ue8bf⾺⚝쐢"), var10002);
        JSONObject var3;
        if ((var3 = this.j) != null) {
            HashMap var10000 = var1;
            JSONObject var5 = var3;
            String var4 = Application.B("\u1cfa(䏌饸鮿鴉");

            try {
                var10000.put(var4, Util.toMap(var5));
            } catch (JSONException var2) {
                TSLog.logger.warn(TSLog.warn(Application.B("᳙1䏑饦鮻鴞淋椋뾊随\ue8b9⾹⚒쐭Ꝣ\ued8a\uaad5͙ꀃ는Ηᆙ㗜㛔禡䌴ꎒ쒦ꮧḰ꣯稬\uec94䵨ꕸ\ue2cf硎ᬵ쫪ￃ瞂")));
                var2.printStackTrace();
            }
        }

        return var1;
    }

    public static class Exception extends Throwable {
        public Exception(String var1) {
            super(var1);
        }
    }

    public static class Builder {
        private String a;
        private Double b;
        private Double c;
        private Float d = 200.0F;
        private Boolean e = false;
        private Boolean f = false;
        private Boolean g = false;
        private Integer h = 0;
        private Integer i;
        private JSONObject j = null;

        public Builder() {
        }

        public TSGeofence.Builder setIdentifier(String var1) {
            this.a = var1;
            return this;
        }

        public TSGeofence.Builder setLatitude(double var1) {
            this.b = var1;
            return this;
        }

        public TSGeofence.Builder setLongitude(double var1) {
            this.c = var1;
            return this;
        }

        public TSGeofence.Builder setRadius(float var1) {
            this.d = var1;
            return this;
        }

        public TSGeofence.Builder setNotifyOnEntry(boolean var1) {
            this.e = var1;
            return this;
        }

        public TSGeofence.Builder setNotifyOnExit(boolean var1) {
            this.f = var1;
            return this;
        }

        public TSGeofence.Builder setNotifyOnDwell(boolean var1) {
            this.g = var1;
            return this;
        }

        public TSGeofence.Builder setLoiteringDelay(int var1) {
            this.h = var1;
            return this;
        }

        public TSGeofence.Builder setExtras(JSONObject var1) {
            this.j = var1;
            return this;
        }

        public TSGeofence.Builder setExtras(String var1) {
            if (var1 != null) {
                try {
                    this.j = new JSONObject(var1);
                } catch (JSONException var2) {
                    TSLog.logger.error(TSLog.error(Application.B("舀㑝圱֒⥮谡퀾͝䨗텄\ueff9歈舋砈⫺範擁ၢ覻ㄒ螁쎚쮽驳듓Ђ紽逯毃᪷㝣ਣ⪖\uef77勻㘕쑮귃鄲\ue292ﬖ奰㈳\udf67泣랒ꃁ") + var2.getMessage()));
                }
            }

            return this;
        }

        public TSGeofence.Builder setNotificationResponsiveness(int var1) {
            this.i = var1;
            return this;
        }

        public TSGeofence build() {
            if (this.b != null) {
                if (this.c != null) {
                    if (this.d != null) {
                        if (this.a != null) {
                            if (!this.e && !this.f && !this.g) {
                                throw new TSGeofence.Exception(Application.B("〢ᓒ宆鱝ᢛ\ue437킟\ufae7䰿緪\u0b58ꬪ쪬쇵㉭ꀹ桛荃䖒€ஓﳦ봩穐㫙ᚖ⧙肰쎥㥈卋\uf0da㺈⏪褿\u1c8d缦㣏鐤嫯\ue61e蓉\ue763ㄑ좉⻬군骮姒멻\u0bfb\ufd40\u209eᡴ\ue626閴ߞ鍕鹯\uee8a칎帥\ueae2掠૭䆦䩖↪\ue632⧵惙༰ऌ搸賛\uf6d5"));
                            } else {
                                return new TSGeofence(this);
                            }
                        } else {
                            throw new TSGeofence.Exception(Application.B("〪ᒖ宗鱁ᢎ\ue430킊\ufae7䰮緱ଗꬭ쫲송㉦ꀬ桏茖䖒₭\u0bd6ﳰ"));
                        }
                    } else {
                        throw new TSGeofence.Exception(Application.B("〱ᒓ宖鱆ᢏ\ue42a탌\ufae7䰸綣\u0b45ꬡ쫰쇴㉽ꀻ桛茇"));
                    }
                } else {
                    throw new TSGeofence.Exception(Application.B("〯ᒝ宜鱈ᢓ\ue42d킙\ufaea䰮綣\u0b5eꬷ쪡쇳㉱ꀸ桋茊䖉₺ௗ"));
                }
            } else {
                throw new TSGeofence.Exception(Application.B("〯ᒓ宆鱆ᢎ\ue42c킈\ufaeb䱫緪ୄꭤ쫳쇤㉥ꀼ桗茑䖞₻"));
            }
        }
    }
}
