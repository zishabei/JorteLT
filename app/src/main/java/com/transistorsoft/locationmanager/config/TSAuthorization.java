//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.config;

import android.content.Context;
import android.util.Log;

import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.AuthorizationEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.locationmanager.http.HttpResponse;
import com.transistorsoft.locationmanager.http.HttpService;
import com.transistorsoft.tslocationmanager.Application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TSAuthorization extends a implements IModule {
    public static final String NAME = Application.B("茮鶣݆螄\udf72骇봛⛩妅哑\uee64Ό뇰");
    public static final String STRATEGY_JWT = Application.B("茅鶁ݦ");
    public static final String STRATEGY_SAS = Application.B("茜鶗ݡ");
    public static final String CONTENT_TYPE_FORM = Application.B("茮鶦݂螀\udf74骖봓⛧妍哊\uee63Ᾱ뇦\uf86aꀚ紏샶쵢嚩㖚럁혯\uf565恚䷵\u0fdf㡊㎖둋궺ﯳ▊籪");
    public static final String FIELD_STRATEGY = Application.B("茼鶢݀融\udf69骐봕⛪");
    public static final String FIELD_ACCESS_TOKEN = Application.B("茮鶵ݑ螉\udf6e骆봦⛼妏哀\uee63");
    public static final String FIELD_REFRESH_TOKEN = Application.B("茽鶳ݔ螞\udf78骆봚⛇妋哎\uee68Ὸ");
    public static final String FIELD_EXPIRES = Application.B("茪鶮݂螅\udf6f骐봁");
    public static final String FIELD_REFRESH_URL = Application.B("茽鶳ݔ螞\udf78骆봚⛆妖哉");
    public static final String FIELD_REFRESH_PAYLOAD = Application.B("茽鶳ݔ螞\udf78骆봚⛃妅哜\uee61Ό뇿\uf823");
    private static final String REFRESH_TOKEN_TEMPLATE = Application.B("茴鶤ݗ螊\udf6f骐봁⛻妰哊\uee66ῳ뇰\uf83a");
    private static final Pattern JWT_ACCESS_TOKEN_PATTERN = Pattern.compile(Application.B("茑鶍ݳ蟁\udf47骔뵟⛩委咈\uee34Ά뇁\uf87aꀰ絓생쵡嚔㖴랞혘\uf529怂䷽ྃ㠂㏁됅궊ﮪ▲簥謬諭멚䧄댆꿎⛳⦏냀\u05ec觋\ue264튽\u18af璨厇״뗻䮡Ἲ\ued9c싁"));
    private static final Pattern REFRESH_TOKEN_PATTERN = Pattern.compile(Application.B("茑鶍ݳ蟁\udf47骔뵟⛩委咈\uee34Ά뇁\uf87aꀰ絓삥"));
    private static final Pattern ACCESS_PATTERN = Pattern.compile(Application.B("茑鷾ݓ螏\udf7e骐봁⛠妘哄\uee78ῢ뇶\uf86e"));
    private static final Pattern REFRESH_OR_RENEW_PATTERN = Pattern.compile(Application.B("茑鷾݀螉\udf73骐봅⛯妖哀\uee6bῤ뇻\uf834ꀅ絑"));
    private static final Pattern EXPIRES_PATTERN = Pattern.compile(Application.B("茑鶳݊螜\udf74骇뵜⚹"));
    private String mStrategy = null;
    private String mAccessToken = null;
    private String mRefreshUrl = null;
    private String mRefreshToken = null;
    private Map<String, Object> mRefreshPayload = null;
    private long mExpires = -1L;
    private boolean mFoundAccessToken = false;
    private boolean mFoundRefreshToken = false;
    private boolean mFoundExpires = false;
    private static final String TAG = "TSAuthorization";

    public TSAuthorization() {
        super(Application.B("쎩廖厬䩊☙六\udede辨ᬫጿ䌶\uab0f咍"));
        this.applyDefaults();
    }

    public TSAuthorization(Map<String, Object> var1) {
        super(Application.B("쎩廖厬䩊☙六\udede辨ᬫጿ䌶\uab0f咍"));
        if (var1.containsKey(Application.B("쎻廗厪䩃☂阮\uded0辫"))) {
            this.mStrategy = (String) var1.get(Application.B("쎻廗厪䩃☂阮\uded0辫"));
        }

        if (var1.containsKey(Application.B("쎩廀去䩇★類\udee3辽ᬡጮ䌱"))) {
            this.mAccessToken = (String) var1.get(Application.B("쎩廀去䩇★類\udee3辽ᬡጮ䌱"));
        }

        if (var1.containsKey(Application.B("쎺廆厾䩐☓類\udedf辆ᬥጠ䌺ꬎ"))) {
            this.mRefreshToken = (String) var1.get(Application.B("쎺廆厾䩐☓類\udedf辆ᬥጠ䌺ꬎ"));
        }

        if (var1.containsKey(Application.B("쎺廆厾䩐☓類\udedf辇ᬸጧ"))) {
            this.mRefreshUrl = (String) var1.get(Application.B("쎺廆厾䩐☓類\udedf辇ᬸጧ"));
        }

        if (var1.containsKey(Application.B("쎺廆厾䩐☓類\udedf辂ᬫጲ䌳\uab0f咂\uf7fa"))) {
            this.mRefreshPayload = (Map) var1.get(Application.B("쎺廆厾䩐☓類\udedf辂ᬫጲ䌳\uab0f咂\uf7fa"));
        }

        Integer var2;
        if (var1.containsKey(Application.B("쎭廛厨䩋☄阮\udec4")) && (var2 = (Integer) var1.get(Application.B("쎭廛厨䩋☄阮\udec4"))) != null) {
            this.mExpires = var2.longValue();
        }

    }

    public TSAuthorization(JSONObject var1, boolean var2) {
        super(Application.B("쎩廖厬䩊☙六\udede辨ᬫጿ䌶\uab0f咍"));
        try {
            if (var1.has(Application.B("쎻廗厪䩃☂阮\uded0辫"))) {
                this.mStrategy = var1.getString(Application.B("쎻廗厪䩃☂阮\uded0辫"));
            }

            if (var1.has(Application.B("쎩廀去䩇★類\udee3辽ᬡጮ䌱"))) {
                this.mAccessToken = var1.getString(Application.B("쎩廀去䩇★類\udee3辽ᬡጮ䌱"));
            }

            if (var1.has(Application.B("쎺廆厾䩐☓類\udedf辆ᬥጠ䌺ꬎ"))) {
                this.mRefreshToken = var1.getString(Application.B("쎺廆厾䩐☓類\udedf辆ᬥጠ䌺ꬎ"));
            }

            if (var1.has(Application.B("쎺廆厾䩐☓類\udedf辇ᬸጧ"))) {
                this.mRefreshUrl = var1.getString(Application.B("쎺廆厾䩐☓類\udedf辇ᬸጧ"));
            }

            if (var1.has(Application.B("쎺廆厾䩐☓類\udedf辂ᬫጲ䌳\uab0f咂\uf7fa"))) {
                this.mRefreshPayload = Util.toMap(var1.getJSONObject(Application.B("쎺廆厾䩐☓類\udedf辂ᬫጲ䌳\uab0f咂\uf7fa")));
            }

            if (var1.has(Application.B("쎭廛厨䩋☄阮\udec4"))) {
                this.mExpires = var1.getLong(Application.B("쎭廛厨䩋☄阮\udec4"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (var2) {
            this.applyDefaults();
        }

    }

    private void _onFailure(String var1, TSAuthorization.Callback var2) {
        TSLog.logger.warn(TSLog.warn(Application.B("တ㇒ퟠ") + var1));
        var2.invoke(new AuthorizationEvent(var1));
    }

    private void _onSuccess(Context var1, JSONObject var2, TSAuthorization.Callback var3) {
        Context var10001 = var1;
        TSAuthorization var10002 = this;
        JSONObject var10003 = var2;
        this.mFoundAccessToken = false;
        this.mFoundRefreshToken = false;
        this.mFoundExpires = false;

        label27:
        {
            label31:
            {
                boolean var9;
                TSAuthorization var10004 = new TSAuthorization();

                TSAuthorization var7;
                TSAuthorization var10005 = var7 = var10004;

                var7.setStrategy(this.mStrategy);
                var7.setRefreshToken(this.mRefreshToken);
                var10005.setRefreshPayload(this.mRefreshPayload);
                var10002.applyResponseData(var10003, var10004);
                TSConfig.getInstance(var10001).updateWithBuilder().setAuthorization(var7).commit();
                break label27;
            }
        }

        if (this.mFoundAccessToken) {
            TSLog.logger.debug(Application.B("꯫\udb31죄⣔偨舜ﭝ僈颌⩦嶍툫帕芍㞹㼧齲‘\u175c읦ꟺ蛓ᯃ\ue513"));
            var3.invoke(new AuthorizationEvent(var2));
        } else {
            var3.invoke(new AuthorizationEvent(TSLog.error(Application.B("꯫\udb31죄⣀偬舓ףּ僈颛⨮巙툰幚芀㞵㼧鼶⁋\u175b읠ꟿ蛄ᯕ\ue513唹簬⇁⒮ᄢ佊쉏\u0899ﵪ̨ꄃ둎㛩㰠ﴐ瓧햩鿉\udac2䷲督ꥃﶴ䣿迷⣔ꉎ渦뼢졃欘截篐帒\uea61糃衖닛䩱") + this.mRefreshUrl)));
        }

    }

    private void applyResponseData(JSONObject var1, TSAuthorization var2) {
        Iterator var3 = var1.keys();

        while (true) {
            while (var3.hasNext()) {
                String var4;
                Object var5;
                try {
                    if ((var5 = var1.get(var4 = (String) var3.next())) instanceof JSONObject) {
                        this.applyResponseData((JSONObject) var5, var2);
                    } else if (!(var5 instanceof JSONArray)) {
                        String var6 = var5.toString();
                        if (!this.mFoundAccessToken && ACCESS_PATTERN.matcher(var4).find()) {
                            this.mFoundAccessToken = true;
                            TSLog.logger.debug(Application.B("\ue93aᑠ\udc98별\uea6b䑄ꈑႯ倊Ḯ麏\ue4fc\uf150퓮傁㽪儍蔩\ud938地穩♋Ě"));
                            var2.setAccessToken(var6);
                        } else if (!this.mFoundRefreshToken && REFRESH_OR_RENEW_PATTERN.matcher(var4).find()) {
                            this.mFoundRefreshToken = true;
                            TSLog.logger.debug(Application.B("\ue93aᑠ\udc98별\uea6b䑄ꈑႯ倊Ḯ麏\ue4fc\uf143퓨傄㽽儛蔩\ud904國穭♅đ䖁"));
                            var2.setRefreshToken(var6);
                        } else if (!this.mFoundExpires && EXPIRES_PATTERN.matcher(var4).find()) {
                            this.mFoundExpires = true;
                            TSLog.logger.debug(Application.B("\ue93aᑠ\udc98별\uea6b䑄ꈑႯ倊Ḯ麏\ue4fc\uf154퓵傒㽦儌蔿\ud91f"));
                            var2.setExpires(Long.valueOf(var6));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return;
        }
    }

    public void apply(okhttp3.Request.Builder var1) {
        if (this.mAccessToken != null) {
            if (this.mStrategy.equalsIgnoreCase(Application.B("뇀\ue165\u0a53"))) {
                var1.header(Application.B("뇋\ue147ੳᒭꈩ升뱁\uec38\u09d2הﻨ끁闑"), Application.B("뇈\ue157੦ᒷꈣ升밈") + this.mAccessToken);
            } else if (this.mStrategy.equalsIgnoreCase(Application.B("뇙\ue173\u0a54"))) {
                String var10001 = this.mAccessToken;
                var1.header(Application.B("뇋\ue147ੳᒭꈩ升뱁\uec38\u09d2הﻨ끁闑"), var10001);
            }
        }

    }

    public boolean update(TSAuthorization var1) {
        this.resetDirty();
        if (var1.getStrategy() != null && !var1.getStrategy().equals(this.mStrategy)) {
            this.mStrategy = var1.getStrategy();
            this.addDirty(Application.B("栗뵗煊ꦕ\ue279匩ﲮ륍"));
        }

        if (var1.getAccessToken() != null && !var1.getAccessToken().equals(this.mAccessToken)) {
            this.mAccessToken = var1.getAccessToken();
            this.addDirty(Application.B("栅뵀煛ꦑ\ue27e匿ﲝ륛淑쇜桵"));
        }

        if (var1.getRefreshToken() != null && !var1.getRefreshToken().equals(this.mRefreshToken)) {
            this.mRefreshToken = var1.getRefreshToken();
            this.addDirty(Application.B("栖뵆煞ꦆ\ue268匿ﲡ률淕쇒桾磑"));
        }

        if (var1.getRefreshUrl() != null && !var1.getRefreshUrl().equals(this.mRefreshUrl)) {
            this.mRefreshUrl = var1.getRefreshUrl();
            this.addDirty(Application.B("栖뵆煞ꦆ\ue268匿ﲡ륡淈쇕"));
        }

        if (var1.getRefreshPayload() != null && !var1.getRefreshPayload().equals(this.mRefreshPayload)) {
            this.mRefreshPayload = var1.getRefreshPayload();
            this.addDirty(Application.B("栖뵆煞ꦆ\ue268匿ﲡ륤淛쇀桷磐꽚⛚"));
        }

        if (var1.getExpires() != this.mExpires) {
            this.mExpires = var1.getExpires();
            this.addDirty(Application.B("栁뵛煈ꦝ\ue27f匩ﲺ"));
        }

        return this.getDirtyFields().isEmpty() ^ true;
    }

    public String getStrategy() {
        return this.mStrategy;
    }

    public void setStrategy(String var1) {
        this.mStrategy = var1;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String var1) {
        this.mAccessToken = var1;
    }

    public String getRefreshToken() {
        return this.mRefreshToken;
    }

    public void setRefreshToken(String var1) {
        this.mRefreshToken = var1;
    }

    public String getRefreshUrl() {
        return this.mRefreshUrl;
    }

    public void setRefreshUrl(String var1) {
        this.mRefreshUrl = var1;
    }

    public Map<String, Object> getRefreshPayload() {
        return this.mRefreshPayload;
    }

    public void setRefreshPayload(Map<String, Object> var1) {
        this.mRefreshPayload = var1;
    }

    public long getExpires() {
        return this.mExpires;
    }

    public void setExpires(long var1) {
        this.mExpires = var1;
    }

    public boolean canRefreshAuthorizationToken() {
        String var1;
        Map var2;
        return (var1 = this.mRefreshUrl) != null && !var1.isEmpty() && (var1 = this.mRefreshToken) != null && !var1.isEmpty() && (var2 = this.mRefreshPayload) != null && !var2.keySet().isEmpty();
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap();
        String var10012 = this.mStrategy;
        var1.put(Application.B("㈦ྐ⺓்佝享㈳\uf8de"), var10012);
        String var10010 = this.mAccessToken;
        var1.put(Application.B("㈴྇⺂\u0bc9佚亽㈀\uf8c8䐞칮ꙧ"), var10010);
        String var10008 = this.mRefreshToken;
        var1.put(Application.B("㈧ཱྀ⺇\u0bde佌亽㈼\uf8f3䐚칠Ꙭ옵"), var10008);
        String var10006 = this.mRefreshUrl;
        var1.put(Application.B("㈧ཱྀ⺇\u0bde佌亽㈼\uf8f2䐇칧"), var10006);
        Map var10004 = this.mRefreshPayload;
        var1.put(Application.B("㈧ཱྀ⺇\u0bde佌亽㈼\uf8f7䐔칲ꙥ옴迴异"), var10004);
        var10000.put(Application.B("㈰ྜ⺑\u0bc5佛享㈧"), this.mExpires);
        return var10000;
    }

    public JSONObject toJson(boolean var1) {
        JSONObject var2;
        var2 = new JSONObject();
        String var3;
        if ((var3 = this.mAccessToken) == null) {
            return var2;
        } else {
            StringBuilder var10000;
            String var10001;
            if (var1) {
                var10000 = new StringBuilder();
                var10001 = this.mAccessToken;
                var3 = var10000.append(var10001.substring(0, Math.min(var10001.length(), 5))).append(Application.B("綵㓴אָ횼꾥딊썙䘑븳ᶤ")).toString();
            }

            String var17;
            if (this.mRefreshToken != null && var1) {
                var10000 = new StringBuilder();
                var10001 = this.mRefreshToken;
                var17 = var10000.append(var10001.substring(0, Math.min(var10001.length(), 5))).append(Application.B("綵㓴אָ횼꾥딊썙䘑븳ᶤ")).toString();
            } else {
                var17 = this.mRefreshToken;
            }

            TSAuthorization var20 = this;
            JSONObject var22 = var2;
            TSAuthorization var10002 = this;
            JSONObject var10003 = var2;
            JSONObject var10004 = var2;
            JSONObject var10005 = var2;
            TSAuthorization var10006 = this;
            String var4 = Application.B("緺㓲טּ횹꾰딌썊䘍");

            JSONException var21;
            label108:
            {
                boolean var23;
                try {
                    var10005.put(var4, var10006.mStrategy);
                } catch (JSONException var14) {
                    var21 = var14;
                    var23 = false;
                    break label108;
                }

                try {
                    var10004.put(Application.B("編㓥﬩횽꾷딚썹䘛븼᷿ഔ"), var3);
                } catch (JSONException var13) {
                    var21 = var13;
                    var23 = false;
                    break label108;
                }

                try {
                    var10003.put(Application.B("緻㓣שּׁ횪꾡딚썅䘠븸ᷱട㡻"), var17);
                } catch (JSONException var12) {
                    var21 = var12;
                    var23 = false;
                    break label108;
                }

                var17 = Application.B("緻㓣שּׁ횪꾡딚썅䘡븥᷶");

                try {
                    var22.put(var17, var10002.mRefreshUrl);
                } catch (JSONException var11) {
                    var21 = var11;
                    var23 = false;
                    break label108;
                }

                var17 = Application.B("緻㓣שּׁ횪꾡딚썅䘤븶ᷣഖ㡺䏬⦌");

                Map var24;
                var24 = var20.mRefreshPayload;

                JSONObject var18;
                JSONObject var25;
                if (var24 != null) {
                    var25 = new JSONObject();
                    var18 = var25;
                } else {
                    var18 = null;
                }

                TSAuthorization var26;
                try {
                    var25 = var2;
                    var26 = this;
                    var2.put(var17, var18);
                } catch (JSONException var7) {
                    var21 = var7;
                    var23 = false;
                    break label108;
                }

                String var15 = Application.B("緬㓾ךּ횱꾶딌썞");

                long var27;
                var27 = var26.mExpires;

                long var19 = var27;

                try {
                    var25.put(var15, var19);
                    return var2;
                } catch (JSONException var5) {
                    var21 = var5;
                    var23 = false;
                }
            }

            JSONException var16 = var21;
            Log.i(Application.B("緝㓕ﬆ횷꾧딈썙䘝븸ᷴഷ㡴䏣⦉ꗞ液싌"), TSLog.error(var16.getMessage()));
            TSLog.logger.error(TSLog.error(var16.getMessage()), var16);
            return var2;
        }
    }

    public boolean equals(TSAuthorization var1) {
        String var2;
        Map var3;
        return (var2 = this.mStrategy) != null && var2.equalsIgnoreCase(var1.getStrategy()) && (var2 = this.mAccessToken) != null && var2.equals(var1.getAccessToken()) && (var2 = this.mRefreshToken) != null && var2.equals(var1.getRefreshToken()) && (var2 = this.mRefreshUrl) != null && var2.equals(var1.getRefreshUrl()) && (var3 = this.mRefreshPayload) != null && var3.equals(var1.getRefreshPayload()) && this.mExpires == var1.getExpires();
    }

    public void applyDefaults() {
        if (this.mStrategy == null) {
            this.mStrategy = Application.B("站䦛ᯃ");
        }

        if (this.mRefreshPayload == null) {
            TSAuthorization var10000 = this;
            HashMap var1;
            var1 = new HashMap();
            var10000.mRefreshPayload = var1;
        }

    }

    public void refreshAuthorizationToken(final Context var1, final TSAuthorization.Callback var2) {
        OkHttpClient var3 = HttpService.getInstance(var1).getClient();
        okhttp3.FormBody.Builder var4;
        var4 = new okhttp3.FormBody.Builder();

        String var6;
        Object var7;
        String var10001;
        for (Iterator var5 = this.mRefreshPayload.entrySet().iterator(); var5.hasNext(); var4.add(var6, var7.toString())) {
            Entry var10000 = (Entry) var5.next();
            var6 = (String) var10000.getKey();
            String var8;
            if ((var7 = var10000.getValue()).getClass() == String.class && (var8 = (String) var7).contains(Application.B("⮉뒿챺ኰ뉴ኺ搨俞骹礥\ue47bἮ라幜"))) {
                var10001 = this.mRefreshToken;
                var7 = var8.replace(Application.B("⮉뒿챺ኰ뉴ኺ搨俞骹礥\ue47bἮ라幜"), var10001);
            }
        }

        okhttp3.Request.Builder var10 = (new okhttp3.Request.Builder()).url(this.mRefreshUrl).post(var4.build());
        JSONObject var11;
        if ((var11 = TSConfig.getInstance(var1).getHeaders()) != null) {
            Iterator var12 = var11.keys();

            label43:
            while (true) {
                String var13;
                do {
                    do {
                        if (!var12.hasNext()) {
                            break label43;
                        }
                    } while ((var13 = (String) var12.next()) == null);
                } while (var13.equalsIgnoreCase(Application.B("⮑뒢챱ኢ뉣\u12b1搯供骙礳\ue460Ἦ")));

                try {
                    var10.header(var13, var11.getString(var13));
                } catch (JSONException var9) {
                    TSLog.logger.warn(Application.B("\u2bbb뒣챩\u12b7뉪\u12b6搿侖骅礯\ue471Ἧ띷幓禒㰆\uda1b⅞툪섫\u1a1d\uee63ᑂ㪖") + var13);
                }
            }
        }

        var10.header(Application.B("⮱뒢챱ኢ뉣\u12b1搯供骹礳\ue460Ἦ"), Application.B("⮓뒽챯ኺ뉯ኼ携係骄礥\ue47eὤ띪希秅㰘\uda0bℝ툣섶ᨊ\uee6aᑕ㫃ꡉ쳄㗘呉৪\uf080⩄滰侞"));
        if (this.mStrategy.equalsIgnoreCase(Application.B("⮡뒌챌"))) {
            var10001 = this.mRefreshToken;
            var10.header(Application.B("⮳뒸챫ኾ뉩ክ搲俌验社\ue479ἤ라"), var10001);
        } else {
            var10.header(Application.B("⮳뒸챫ኾ뉩ክ搲俌验社\ue479ἤ라"), Application.B("⮰뒨챾ኤ뉣ክ摻") + this.mAccessToken);
        }

        var3.newCall(var10.build()).enqueue(new okhttp3.Callback() {
            public void onFailure(@NotNull Call var1x, @NotNull IOException var2x) {
                Log.i(TAG, "onFailure: aaaaaaaaa");
            }

            public void onResponse(@NotNull Call var1x, @NotNull Response var2x) {
                Log.i(TAG, "onResponse: aaaaaaaaa");
            }
        });
    }

    public interface Callback {
        void invoke(AuthorizationEvent var1);
    }

    public static class Builder {
        private String mStrategy = null;
        private String mAccessToken = null;
        private String mRefreshUrl = null;
        private String mRefreshToken = null;
        private Map<String, Object> mRefreshPayload = null;
        private long mExpires = -1L;

        public Builder() {
        }

        public TSAuthorization.Builder setStrategy(String var1) {
            this.mStrategy = var1;
            return this;
        }

        public TSAuthorization.Builder setAccessToken(String var1) {
            this.mAccessToken = var1;
            return this;
        }

        public TSAuthorization.Builder setRefreshUrl(String var1) {
            this.mRefreshUrl = var1;
            return this;
        }

        public TSAuthorization.Builder setRefreshToken(String var1) {
            this.mRefreshToken = var1;
            return this;
        }

        public TSAuthorization.Builder setRefreshPayload(Map<String, Object> var1) {
            this.mRefreshPayload = var1;
            return this;
        }

        public TSAuthorization.Builder setExpires(long var1) {
            this.mExpires = var1;
            return this;
        }

        public TSAuthorization build() {
            TSAuthorization var1;
            TSAuthorization var10000 = var1 = new TSAuthorization();
            var1.mStrategy = this.mStrategy;
            var1.mAccessToken = this.mAccessToken;
            var1.mRefreshUrl = this.mRefreshUrl;
            var1.mRefreshToken = this.mRefreshToken;
            var1.mRefreshPayload = this.mRefreshPayload;
            var10000.mExpires = this.mExpires;
            return var10000;
        }
    }
}
