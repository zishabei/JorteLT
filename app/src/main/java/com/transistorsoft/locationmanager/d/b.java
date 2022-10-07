//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.d;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.util.Log;
import com.transistorsoft.locationmanager.event.SettingsFailureEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static final String a = Application.B("\uf1d1擌᭐愭褵梽읐뱻Ą쬨䰎鉭䛖ꧧ䪼哼ꆡ");
    public static final Float b = 100.0F;
    public static final String c = Application.B("\uf1e9擰\u1b7f愣褢梵읋뱼");
    public static final String d = Application.B("\uf1e2擺᭳愤褳梲읇뱷");
    public static ArrayList<a> e = new ArrayList();
    public static ArrayList<String> f = new ArrayList();
    private static SharedPreferences g;
    private static boolean h = false;
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;
    private static boolean l = false;
    private static boolean m = false;
    private static boolean n = false;
    private static final String o = Application.B("\uf1b7擼ᬤ慳襥梾윝밡Ď쭰䱶鈾䛚ꦵ䫫咮ꆶ完鲝ꫪ\uf7ea\ue2c9\u0ba6ꐺ넏쇛輓⛋ꎆ皓㲣\ue2d5䶣䀑椣漳잠氤鷘첼");
    public static final Float p = 10.0F;
    public static final int q = 0;
    public static final long r = 1000L;
    public static final long s = 10000L;
    public static final long t = 1000L;
    public static final boolean u = true;
    public static final Integer v = 60;
    public static final boolean w = false;
    public static final Float x = 1.0F;
    public static final long y = 0L;
    public static final int z = 25;
    public static final Integer A = 25;
    public static final long B = 5L;
    public static final float C = 100.0F;
    public static final Boolean D = false;
    public static final long E = 10000L;
    public static final int F = 75;
    public static final String G = Application.B("\uf1ec擱ᭃ愴褳梴읍뱱ć쬣䱯鈬䛗ꧨ䪄哻ꆺ寛鲆ꫨ\uf7b3\ue2ce௩ꑿ념솅轸⛉ꏘ盌㳡\ue29a䷥䀅楢漸쟽氬鶀첽ꦘ仴렽䌪㻟陫쮩图\uf21a");
    public static final boolean H = false;
    public static final int I = 0;
    public static final boolean J = false;
    public static final int K = 1;
    public static final int L = -1;
    public static final String M = "";
    public static final String N = Application.B("\uf1d5擐\u1b4f愖");
    public static final boolean O = true;
    public static final Integer P = 0;
    public static final boolean Q = false;
    public static final int R = -1;
    public static final String S = Application.B("\uf1e9擰\u1b7f愣褢梵읋뱼");
    public static final String T = "";
    public static final String U = "";
    public static final String V = Application.B("\uf1c4擌᭟");
    public static final Integer W = 60000;
    public static final boolean X = false;
    public static final boolean Y = true;
    public static final boolean Z = false;
    public static final boolean a0 = false;
    public static final int b0 = -1;
    public static final boolean c0 = false;
    public static final Integer d0 = 0;
    public static final String e0 = "";
    public static final String f0 = Application.B("\uf1c9擰\u1b7f愣褢梵읋뱼ŋ쬕䰦鉾䛎ꧯ䪸哼ꇳ寙鲜\uaaff\uf7b6\ue2ddதꐫ녚솏");
    public static final String g0 = "";
    public static final String h0 = "";
    public static final String i0 = null;
    public static final int j0 = 5;
    public static final int k0 = 3;
    public static Boolean l0;
    public static Boolean m0;
    public static String n0;
    public static Boolean o0;
    public static Float p0;
    public static Float q0;
    public static Integer r0;
    public static Float s0;
    public static Long t0;
    public static Long u0;
    public static Integer v0;
    public static Long w0;
    public static Boolean x0;
    public static Float y0;
    public static Boolean z0;
    public static Long A0;
    public static Integer B0;
    public static ArrayList<Integer> C0;
    public static Boolean D0;
    public static Integer E0;
    public static Long F0;
    public static Boolean G0;
    public static Boolean H0;
    public static Integer I0;
    public static Integer J0;
    public static String K0;
    public static String L0;
    public static Boolean M0;
    public static Integer N0;
    public static Boolean O0;
    public static Integer P0;
    public static JSONObject Q0;
    public static JSONObject R0;
    public static JSONObject S0;
    public static String T0;
    public static String U0;
    public static String V0;
    public static String W0;
    public static Integer X0;
    public static Long Y0;
    public static Boolean Z0;
    public static Boolean a1;
    public static Boolean b1;
    public static Integer c1;
    public static Boolean d1;
    public static Boolean e1;
    public static Boolean f1;
    public static Boolean g1;
    public static Boolean h1;
    public static Boolean i1;
    public static Integer j1;
    public static Boolean k1;
    public static Integer l1;
    public static String m1;
    public static String n1;
    public static String o1;
    public static String p1;
    public static String q1;
    public static String r1;
    public static JSONArray s1;
    public static String t1;
    public static Boolean u1;
    public static String v1;
    public static Integer w1;
    public static Boolean x1 = false;
    public static Integer y1 = 5;
    public static Integer z1 = 3;

    public b() {
    }

    private static void r0() {
    }

    public static boolean s0() {
        return n;
    }

    public static void f(Context var0) {
        com.transistorsoft.locationmanager.a.a.d(var0);
    }

    private static void v0() {
        Editor var0 = g.edit();

        for(int var1 = 0; var1 < e.size(); ++var1) {
            a var2 = (a)e.get(var1);
            if (!g.contains(var2.b) || !var2.g) {
                b(a(var2.c, var2), var2);
                a(var2.c, var2, var0);
            }
        }

        var0.apply();
    }

    private static void a(JSONObject var0) {
        f.clear();
        Editor var1 = g.edit();

        for(int var2 = 0; var2 < e.size(); ++var2) {
            a var3;
            if (!(var3 = (a)e.get(var2)).h && var0.has(var3.b)) {
                JSONException var10000;
                label47: {
                    JSONObject var8;
                    a var10;
                    boolean var10001;
                    try {
                        var8 = var0;
                        var10 = var3;
                        f.add(var3.b);
                    } catch (JSONException var7) {
                        var10000 = var7;
                        var10001 = false;
                        break label47;
                    }

                    Object var9;
                    try {
                        var9 = a(var8, var10);
                    } catch (JSONException var6) {
                        var10000 = var6;
                        var10001 = false;
                        break label47;
                    }

                    Object var4 = var9;

                    try {
                        b(a(var4, var3), var3);
                        a(var9, var3, var1);
                        continue;
                    } catch (JSONException var5) {
                        var10000 = var5;
                        var10001 = false;
                    }
                }

                var10000.printStackTrace();
            }
        }

        TSLog.setLogLevel(P());
        TSLog.setMaxHistory(Q());
        var1.apply();
        n = true;
    }

    private static Boolean a(String var0) {
        return f.contains(var0);
    }

    private static void u0() {
        StringBuffer var0;
        StringBuffer var10000 = var0 = new StringBuffer;
        var10000.<init>(4096);
        var10000.append(TSLog.header(Application.B("娅솣ꠠ㲜삀ᙤ⏛㶝\uf5d7ض㦜魡껷ꩀ視袼超鼥팉쬕ቹ\ued22貎峐徳\ueeb4䘶౮슺臅")));

        try {
            var10000.append(i0().toString(2));
        } catch (JSONException var1) {
            var1.printStackTrace();
        }

        var0.append(Application.B("缝\ue492贓ᦧ\ue5b7㍆ۤᢸ탩⌂\u1c8b빔诈轼\udf87궏ꢴ먁\uf630\uee2a㝇졒ꦍ秥窗쮐挏⥐\ue78dꓦ\u173f굩솧鰾窘㭱웢\udae1\uf68e〿乞씩鈳뮕\udd85茮"));
        TSLog.logger.info(var0.toString());
    }

    private static JSONObject i0() {
        JSONObject var0;
        var0 = new JSONObject.<init>();

        for(int var1 = 0; var1 < e.size(); ++var1) {
            a var2 = (a)e.get(var1);
            if (g.contains(var2.b)) {
                Object var3 = a(var2);
                if (var2.a == 2) {
                    var3 = Double.valueOf(var3.toString());
                }

                try {
                    var0.put(var2.b, var3);
                } catch (JSONException var4) {
                    var4.printStackTrace();
                    TSLog.logger.error(TSLog.error(Application.B("⬢嵶龾国佅\uf8c7句ꏤ㭀醅鞦ᙡ잎ᶱ䊩") + var2.b + Application.B("⭃")), var4);
                }
            }
        }

        return var0;
    }

    private static Float c0() {
        return p0;
    }

    private static void a(Float var0) {
        p0 = var0;
        Editor var10000 = g.edit();
        var10000.putFloat(Application.B("ᙩឲ\uf00f窴цᑠ堗\ue308"), p0);
        var10000.apply();
    }

    private static float a(float var0) {
        if (!(var0 <= 0.0F) && !m()) {
            if ((var0 = (float)(Math.floor((double)var0 / 5.0D + 0.5D) * 5.0D / 5.0D)) < 0.0F) {
                var0 = 0.0F;
            }

            return q0 + q0 * y0 * var0;
        } else {
            return q0;
        }
    }

    private static Integer j() {
        return r0;
    }

    private static float k() {
        return s0;
    }

    private static int P() {
        return y1;
    }

    private static int Q() {
        return z1;
    }

    private static long C() {
        return Y0;
    }

    private static void a(long var0) {
        Y0 = var0;
        Editor var10000 = g.edit();
        var0 = Y0;
        var10000.putLong(Application.B("ᙡឳ\uf00f窿цᑺ堑\ue31f믐㌈⧳\ue0a7糹ꖨ筹섣變㺭\ud902\uebcc튆衔㎐"), var0);
        var10000.apply();
    }

    private static boolean B() {
        return Z0;
    }

    private static boolean l0() {
        return G0;
    }

    private static boolean r() {
        return l0;
    }

    private static void b(boolean var0) {
        l0 = var0;
        Editor var10000 = g.edit();
        var10000.putBoolean(Application.B("诸⊑⊓㺮竧윲ꋴ"), var0);
        var10000.apply();
    }

    private static boolean g0() {
        return o0;
    }

    private static void c(boolean var0) {
        o0 = var0;
        Editor var10000 = g.edit();
        var10000.putBoolean(Application.B("遑\udd95麵晦\uaaf8놀릿晋嵵앢جㅕ퓻㓚鿨\ue00a"), var0);
        var10000.apply();
    }

    private static void b(String var0) {
        n0 = var0;
        Editor var10000 = g.edit();
        String var10002 = n0;
        var10000.putString(Application.B("诩⊍⊓㺯章윾ꋾ赙\udc6a㋿\uf1ff贾"), var10002);
        var10000.apply();
    }

    private static String o0() {
        return n0;
    }

    private static boolean t0() {
        return n0.equalsIgnoreCase(Application.B("엣蠫ꜹ꣕兜\ue00c썩块"));
    }

    private static boolean m() {
        return x0;
    }

    private static float o() {
        return q0;
    }

    private static float p() {
        return y0;
    }

    private static void b(float var0) {
        q0 = var0;
    }

    private static boolean b() {
        return z0;
    }

    private static String q0() {
        return K0;
    }

    private static void c(String var0) {
        K0 = var0;
    }

    private static JSONObject d0() {
        return Q0;
    }

    private static JSONObject E() {
        return R0;
    }

    private static JSONObject s() {
        return S0;
    }

    private static String H() {
        String var0;
        if ((var0 = L0) == null) {
            var0 = Application.B("뮻ᳲҽ鳨");
        }

        return var0;
    }

    private static String I() {
        return T0;
    }

    private static String L() {
        return !U0.isEmpty() ? U0 : null;
    }

    private static String D() {
        return !V0.isEmpty() ? V0 : null;
    }

    private static void a(boolean var0) {
        h = var0;
    }

    private static boolean l() {
        return h;
    }

    private static boolean c() {
        return M0 && !h;
    }

    private static Integer d() {
        return N0;
    }

    private static boolean e() {
        return O0;
    }

    private static int R() {
        return P0;
    }

    private static String O() {
        if (!W0.equalsIgnoreCase(Application.B("\uefc8ꗞ큍")) && !W0.equalsIgnoreCase(Application.B("\uefcdꗈ큝ဈ"))) {
            TSLog.logger.warn(TSLog.warn(Application.B("\uefc0ꗣ큸ဪ꾞콰컶\ufff3㜑悆ᐙ␄鸞殫้縩㲈⇉\ue05b\ud9c6图灌髱㍇㶒ꛉ뭗坦披\uf14e渞醆駲\ue7b7塸鿜鈅\uea4a頸㉙肤멘恬") + W0));
            W0 = Application.B("\uefc8ꗞ큍");
        }

        return W0;
    }

    private static Integer J() {
        return X0;
    }

    private static int S() {
        return I0;
    }

    private static void a(Boolean var0) {
        Editor var10000 = g.edit();
        m0 = var0;
        var10000.putBoolean(Application.B("ᙯឥ\uf02d窶ѕᑽ堜\ue31d"), var0);
        var10000.apply();
    }

    private static boolean K() {
        return m0;
    }

    private static boolean A() {
        if (VERSION.SDK_INT >= 26) {
            k1 = true;
        }

        return k1;
    }

    private static boolean m0() {
        return a1;
    }

    private static long n0() {
        return F0;
    }

    private static long i() {
        return w0;
    }

    private static long N() {
        return t0;
    }

    private static long t() {
        return u0;
    }

    private static Integer M() {
        return v0;
    }

    private static Integer j0() {
        return E0;
    }

    private static void a(Integer var0) {
        E0 = var0;
    }

    private static boolean h0() {
        return b1;
    }

    private static boolean x() {
        return d1;
    }

    private static boolean y() {
        return e1;
    }

    private static boolean v() {
        return f1;
    }

    private static boolean u() {
        return g1;
    }

    private static boolean w() {
        return h1;
    }

    private static boolean z() {
        return i1;
    }

    private static Integer G() {
        return j1;
    }

    private static Integer U() {
        return B0;
    }

    private static ArrayList<Integer> p0() {
        return C0;
    }

    private static JSONArray f0() {
        return s1;
    }

    private static long a() {
        return A0;
    }

    private static boolean n() {
        return D0;
    }

    private static Integer Y() {
        return l1;
    }

    private static String b0() {
        return m1;
    }

    private static String a0() {
        return n1;
    }

    private static String V() {
        return o1;
    }

    private static String W() {
        return p1;
    }

    private static String Z() {
        return !q1.isEmpty() ? q1 : p1;
    }

    private static String X() {
        return r1;
    }

    private static boolean h() {
        return x1;
    }

    private static Integer T() {
        return J0;
    }

    private static boolean e0() {
        return H0 && J0 != 0;
    }

    private static Integer k0() {
        return c1;
    }

    private static boolean q() {
        return u1;
    }

    private static String F() {
        return t1;
    }

    private static String g() {
        return v1;
    }

    private static Integer f() {
        return w1;
    }

    private static boolean a(Context var0) {
        return g(var0);
    }

    public static boolean e(Context var0) {
        return com.transistorsoft.locationmanager.a.a.c(var0);
    }

    private static void b(Context var0) {
        if (k) {
            g(var0);
        } else if (!d(var0) && (!i || !j || !l)) {
            v0();
        }

    }

    private static void w0() {
        i = true;
    }

    private static boolean g(Context var0) {
        Context var10000 = var0;
        m = d(var0);
        String var9 = var0.getPackageName();
        String var1;
        if ((var1 = c(var10000)) == null) {
            a(Application.B("郳黴楑㌭볣髞⫚碑ⶉ둛\uf618\uf590사ꋕ㦾뙦\uf40a\uf143ꐼ큈邀藎钄妬ֵᠼ"), Application.B("郹黜楻㌄볈髩⪿﨟ⶰ됺\uf632\uf5b0삆ꋰ㧊뙃\uf42c\uf16eꑹ큠邲藢铨妒ւ᠀꣹퓤ᾃ騜蝟\ufb3d\uf825뿙쎖琯ᢤ鲳湩篛ꁍȸ흨ⶾ솲\ue532\u1ccd⡥ⱺጽ\u1979\ud892圔ꎑ깦䍎뇩佃ᗄ련\ue3e5뺲\ue1da⬳慉耉愐蠬泉锜鈢箜噬휚\uee99뻺홧ᬰ\u0e75糔\uf5db钳\ueaf2둭旐༼⩫ۛ礐䟌첈갴緾ꌕ핖ࣛ㮽ᯥ\u0a56肇鄟荱ἕ䦅䶝\ue129\uefce\u0af2\ueb90羲❢麤縲鈩⅙呢唠ꮱᛥ䄏贁ɳ藄硴嘪䛦㶣\ueab2磩\uebba☗덺驷셽ꎞ袖௸້\u0e3e̦뷜員뮜䁥≏낪卽㑺ꢇ襔\uf4abス헩\ueb4d蔩\uf37a誀㠧伃谸錄투䂣\udb9e\uf825⽳磜쎗뒸\ud892抳褋癩眞篗\ue15c졥庵㶦䊇㈆榉쿠\udad1ᐻ꩒荻옼♖䤆푏榎擽줫\u1ccd\u0e77駲\u17ed槒㈝\uebfd쇸"));
            return false;
        } else {
            MessageDigest var10;
            try {
                var10 = MessageDigest.getInstance(Application.B("郬黵楓㍅벟骸⪩"));
            } catch (Exception var3) {
                a(Application.B("郳黴楑㌭볣髞⫚碑ⶉ둛\uf618\uf590사ꋕ㦾뙦\uf40a\uf143ꐼ큈邀藎钄妬ֵᠼ"), var9);
                return false;
            }

            w0();

            label58: {
                boolean var15;
                label57: {
                    label64: {
                        StringBuilder var13;
                        boolean var10001;
                        try {
                            var13 = new StringBuilder;
                        } catch (Exception var8) {
                            var10001 = false;
                            break label64;
                        }

                        StringBuilder var10002 = var13;

                        byte[] var11;
                        try {
                            var10002.<init>();
                            var11 = var10.digest(var13.append(var9).append(Application.B("邅")).append(Application.B("邍點椪㍙벞髯⪦縉ⶺ됬\uf661\uf5eb삊ꊧ㧚똘\uf420\uf139ꑾ큯郴藥钫妜חᡉ꣭퓩\u1fdc騌蜨אּ\uf827뿍쏍琣ᣳ鲟渾篓")).toString().getBytes(Application.B("郪黩楔㍅법")));
                        } catch (Exception var7) {
                            var10001 = false;
                            break label64;
                        }

                        byte[] var2 = var11;

                        StringBuilder var12;
                        int var16;
                        try {
                            var12 = (new StringBuilder()).append(Application.B("邚麍"));
                            var16 = var2.length;
                        } catch (Exception var6) {
                            var10001 = false;
                            break label64;
                        }

                        var16 *= 2;

                        String var14;
                        Object[] var18;
                        try {
                            var14 = var12.append(var16).append(Application.B("郧")).toString();
                            var18 = new Object[1];
                        } catch (Exception var5) {
                            var10001 = false;
                            break label64;
                        }

                        Object[] var17 = var18;
                        byte var10003 = 0;

                        try {
                            var17[var10003] = new BigInteger(1, var2);
                            var15 = String.format(var14, var18).equalsIgnoreCase(var1);
                            break label57;
                        } catch (Exception var4) {
                            var10001 = false;
                        }
                    }

                    TSLog.logger.error(TSLog.error(Application.B("郳黔楱㌍볃髾⫺碑ⶩ둻\uf638\uf5b0삌ꋵ㦞뙆\uf42a\uf163ꐼ큫邳藵钧妋")));
                    break label58;
                }

                if (var15) {
                    return true;
                }
            }

            a(Application.B("郳黴楑㌭볣髞⫚碑ⶉ둛\uf618\uf590사ꋕ㦾뙦\uf40a\uf143ꐼ큈邀藎钄妬ֵᠼ"), var9);
            return false;
        }
    }

    private static String c(Context param0) {
        // $FF: Couldn't be decompiled
    }

    private static boolean d(Context param0) {
        // $FF: Couldn't be decompiled
    }

    private static void a(String var0, String var1) {
        if (m) {
            var0 = Application.B("ᙊឿ\uf003窼эᑧ堗\ue35a믖㌛⧰\ue0b6糴ꖤ筤섾讜㺑\ud943\uebee튎衈㎏칄\uf84a\uded7");
            var1 = Application.B("ᙄិ\uf003窲фᑦ堝\ue30f믮㌞⧛\ue0ba糿ꖩ筿섴讒㺋\ud90a\uebc7튁蠁㎊칂\uf818\udec0䳛菅鸝ꩩ\udd27ᢊ쭁䴀顦\u243b\ue0db\u0ff7뻅鯑폶〲妎핲។뙨뎫骪馈熡怟꣘");
        }

        Log.i(Application.B("ᙒច\uf02c窶рᑵ堆\ue313믯㌔⧑\ue0be糾ꖤ筷섲讁"), TSLog.header(var0) + TSLog.boxRow(var1));
        EventBus.getDefault().post(new SettingsFailureEvent(var0, var1));
    }

    private static Integer b(Integer var0) {
        Integer var10000;
        int var1;
        if ((var1 = var0) != -2 && var1 != -1 && var1 != 0) {
            if (var1 != 10) {
                if (var1 == 100) {
                    var10000 = 104;
                    return var10000;
                }

                if (var1 == 1000 || var1 == 3000) {
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

    private static ArrayList<Integer> d(String var0) {
        String var10000 = var0;
        ArrayList var3;
        var3 = new ArrayList.<init>();
        Iterator var1 = Arrays.asList(var10000.replaceAll(Application.B("率ᇷ\uf3ce"), "").split(Application.B("嶺"))).iterator();

        while(var1.hasNext()) {
            String var2;
            if ((var2 = (String)var1.next()).equalsIgnoreCase(Application.B("燐ᇪ\uf3ba\ueb22뼴\ue30c⺅쁤㽨瓀"))) {
                var3.add(0);
            } else if (var2.equalsIgnoreCase(Application.B("裡ᇪ\uf3ba\ueb36뼸\ue307⺕쁤㽨瓀"))) {
                var3.add(1);
            } else if (var2.equalsIgnoreCase(Application.B("裡ᇪ\uf3ba\ueb32뼾\ue30b⺘"))) {
                var3.add(2);
            } else if (var2.equalsIgnoreCase(Application.B("淋ᇱ\uf38b\ueb3a뼸\ue30a⺋"))) {
                var3.add(8);
            } else if (var2.equalsIgnoreCase(Application.B("藺ᇥ\uf389\ueb3f뼸\ue30a⺋"))) {
                var3.add(7);
            } else {
                TSLog.logger.warn(TSLog.error(Application.B("龍ᇋ\uf3ab\ueb12뼘\ue323⺹쁕㽁璅\uf464餚鬘䝠旧\ue273믝ଐ㿔摔牴༿枌䁙㈲⮆ꀈ賘籵\uea3a\udb66ꨍ\uf188얛ၿᮻଇ暪祎뮜熮偮\uea1c⭀꾤헙\uec9a\udd1c㩬祡蠟厉뵘ꖍ펖\uf4b6吧\ufae3ﾅꈻ䪏芭") + var2));
            }
        }

        return var3;
    }

    private static Integer c(Integer var0) {
        switch(var0) {
            case -2:
                var0 = -2;
                break;
            case -1:
                var0 = -1;
                break;
            case 0:
                var0 = 0;
                break;
            case 1:
                var0 = 1;
                break;
            case 2:
                var0 = 2;
        }

        return var0;
    }

    private static Object a(JSONObject var0, a var1) {
        Object var2;
        if ((var2 = var0.get(var1.b)).equals((Object)null)) {
            return var1.c;
        } else {
            label86: {
                label85: {
                    int var10000;
                    boolean var10001;
                    try {
                        var10000 = var1.a;
                    } catch (NumberFormatException var9) {
                        var10001 = false;
                        break label85;
                    }

                    switch(var10000) {
                        case 1:
                            Integer var16;
                            try {
                                var16 = var0.getInt(var1.b);
                            } catch (NumberFormatException var8) {
                                var10001 = false;
                                break;
                            }

                            var2 = var16;
                            break label86;
                        case 2:
                            double var13;
                            try {
                                var13 = var0.getDouble(var1.b);
                            } catch (NumberFormatException var7) {
                                var10001 = false;
                                break;
                            }

                            float var14 = (float)var13;

                            Float var15;
                            try {
                                var15 = var14;
                            } catch (NumberFormatException var6) {
                                var10001 = false;
                                break;
                            }

                            var2 = var15;
                            break label86;
                        case 3:
                            Boolean var12;
                            try {
                                var12 = var0.getBoolean(var1.b);
                            } catch (NumberFormatException var5) {
                                var10001 = false;
                                break;
                            }

                            var2 = var12;
                            break label86;
                        case 4:
                            Long var11;
                            try {
                                var11 = var0.getLong(var1.b);
                            } catch (NumberFormatException var4) {
                                var10001 = false;
                                break;
                            }

                            var2 = var11;
                        default:
                            break label86;
                    }
                }

                TSLog.logger.warn(TSLog.warn(Application.B("ᙏី\uf016窸яᑽ堖\ue35a믮㌏⧱\ue0bd糵ꖷ笰성讜㺍\ud90e\uebc9튛蠁㎅칞\uf84a\ude92䳝菎鸇ꩴ\udd20ᢃ쬆䵉") + var1.b + Application.B("ᘼ៶") + var2 + Application.B("ᘦ\u17fe\uf035窪ъᑺ堕\ue35a믤㌟⧺\ue0be糥ꖩ筤섁讒㺓\ud916\uebcd틕蠁") + var1.c + Application.B("ᘯ")));
                return var1.c;
            }

            if (var1.d == null) {
                return var2;
            } else {
                Object var10;
                label60: {
                    var10 = null;
                    int var3;
                    if ((var3 = var1.a) != 1) {
                        if (var3 != 2) {
                            if (var3 != 4 || (Long)var2 >= (Long)var1.d) {
                                break label60;
                            }
                        } else if (!((Float)var2 < (Float)var1.d)) {
                            break label60;
                        }
                    } else if ((Integer)var2 >= (Integer)var1.d) {
                        break label60;
                    }

                    var10 = var1.d;
                }

                if (var10 != null) {
                    TSLog.logger.warn(TSLog.warn(Application.B("ᙃី\uf006窶ёᑷ堗\ue31e뮠㌗⧵\ue0b1糹ꖨ筥섺讥㺞\ud90f\uebdd튊蠍㏃") + var1.b + Application.B("ᘼ៶") + var2 + Application.B("ᘦ\u17fb\uf05e竹") + var10));
                } else {
                    var10 = var2;
                }

                return var10;
            }
        }
    }

    private static Object a(Object var0, a var1) {
        if (var0 == null) {
            var0 = var1.c;
        }

        String var2;
        if ((var2 = var1.f) == null) {
            return var0;
        } else {
            a var10000 = var1;
            Method var3 = null;

            Object var48;
            label158: {
                NoSuchMethodException var43;
                label159: {
                    IllegalAccessException var42;
                    label160: {
                        InvocationTargetException var41;
                        label146: {
                            boolean var10001;
                            int var44;
                            try {
                                var44 = var10000.a;
                            } catch (NoSuchMethodException var35) {
                                var43 = var35;
                                var10001 = false;
                                break label159;
                            } catch (IllegalAccessException var36) {
                                var42 = var36;
                                var10001 = false;
                                break label160;
                            } catch (InvocationTargetException var37) {
                                var41 = var37;
                                var10001 = false;
                                break label146;
                            }

                            label131: {
                                int var4 = var44;
                                Class[] var10002;
                                Class[] var10003;
                                byte var10004;
                                Class var45;
                                String var46;
                                Method var47;
                                if (var44 != 0) {
                                    if (var4 != 1) {
                                        if (var4 != 2) {
                                            if (var4 != 4) {
                                                break label131;
                                            }

                                            try {
                                                var45 = b.class;
                                                var46 = var2;
                                                var10002 = new Class[1];
                                            } catch (NoSuchMethodException var32) {
                                                var43 = var32;
                                                var10001 = false;
                                                break label159;
                                            } catch (IllegalAccessException var33) {
                                                var42 = var33;
                                                var10001 = false;
                                                break label160;
                                            } catch (InvocationTargetException var34) {
                                                var41 = var34;
                                                var10001 = false;
                                                break label146;
                                            }

                                            var10003 = var10002;
                                            var10004 = 0;

                                            try {
                                                var10003[var10004] = Long.class;
                                                var47 = var45.getMethod(var46, var10002);
                                            } catch (NoSuchMethodException var29) {
                                                var43 = var29;
                                                var10001 = false;
                                                break label159;
                                            } catch (IllegalAccessException var30) {
                                                var42 = var30;
                                                var10001 = false;
                                                break label160;
                                            } catch (InvocationTargetException var31) {
                                                var41 = var31;
                                                var10001 = false;
                                                break label146;
                                            }
                                        } else {
                                            try {
                                                var45 = b.class;
                                                var46 = var2;
                                                var10002 = new Class[1];
                                            } catch (NoSuchMethodException var26) {
                                                var43 = var26;
                                                var10001 = false;
                                                break label159;
                                            } catch (IllegalAccessException var27) {
                                                var42 = var27;
                                                var10001 = false;
                                                break label160;
                                            } catch (InvocationTargetException var28) {
                                                var41 = var28;
                                                var10001 = false;
                                                break label146;
                                            }

                                            var10003 = var10002;
                                            var10004 = 0;

                                            try {
                                                var10003[var10004] = Float.class;
                                                var47 = var45.getMethod(var46, var10002);
                                            } catch (NoSuchMethodException var23) {
                                                var43 = var23;
                                                var10001 = false;
                                                break label159;
                                            } catch (IllegalAccessException var24) {
                                                var42 = var24;
                                                var10001 = false;
                                                break label160;
                                            } catch (InvocationTargetException var25) {
                                                var41 = var25;
                                                var10001 = false;
                                                break label146;
                                            }
                                        }
                                    } else {
                                        try {
                                            var45 = b.class;
                                            var46 = var2;
                                            var10002 = new Class[1];
                                        } catch (NoSuchMethodException var20) {
                                            var43 = var20;
                                            var10001 = false;
                                            break label159;
                                        } catch (IllegalAccessException var21) {
                                            var42 = var21;
                                            var10001 = false;
                                            break label160;
                                        } catch (InvocationTargetException var22) {
                                            var41 = var22;
                                            var10001 = false;
                                            break label146;
                                        }

                                        var10003 = var10002;
                                        var10004 = 0;

                                        try {
                                            var10003[var10004] = Integer.class;
                                            var47 = var45.getMethod(var46, var10002);
                                        } catch (NoSuchMethodException var17) {
                                            var43 = var17;
                                            var10001 = false;
                                            break label159;
                                        } catch (IllegalAccessException var18) {
                                            var42 = var18;
                                            var10001 = false;
                                            break label160;
                                        } catch (InvocationTargetException var19) {
                                            var41 = var19;
                                            var10001 = false;
                                            break label146;
                                        }
                                    }
                                } else {
                                    try {
                                        var45 = b.class;
                                        var46 = var2;
                                        var10002 = new Class[1];
                                    } catch (NoSuchMethodException var14) {
                                        var43 = var14;
                                        var10001 = false;
                                        break label159;
                                    } catch (IllegalAccessException var15) {
                                        var42 = var15;
                                        var10001 = false;
                                        break label160;
                                    } catch (InvocationTargetException var16) {
                                        var41 = var16;
                                        var10001 = false;
                                        break label146;
                                    }

                                    var10003 = var10002;
                                    var10004 = 0;

                                    try {
                                        var10003[var10004] = String.class;
                                        var47 = var45.getMethod(var46, var10002);
                                    } catch (NoSuchMethodException var11) {
                                        var43 = var11;
                                        var10001 = false;
                                        break label159;
                                    } catch (IllegalAccessException var12) {
                                        var42 = var12;
                                        var10001 = false;
                                        break label160;
                                    } catch (InvocationTargetException var13) {
                                        var41 = var13;
                                        var10001 = false;
                                        break label146;
                                    }
                                }

                                var3 = var47;
                            }

                            if (var3 != null) {
                                try {
                                    var48 = var3.invoke(b.class, var0);
                                    break label158;
                                } catch (NoSuchMethodException var5) {
                                    var43 = var5;
                                    var10001 = false;
                                    break label159;
                                } catch (IllegalAccessException var6) {
                                    var42 = var6;
                                    var10001 = false;
                                    break label160;
                                } catch (InvocationTargetException var7) {
                                    var41 = var7;
                                    var10001 = false;
                                }
                            } else {
                                try {
                                    var48 = var1.c;
                                    break label158;
                                } catch (NoSuchMethodException var8) {
                                    var43 = var8;
                                    var10001 = false;
                                    break label159;
                                } catch (IllegalAccessException var9) {
                                    var42 = var9;
                                    var10001 = false;
                                    break label160;
                                } catch (InvocationTargetException var10) {
                                    var41 = var10;
                                    var10001 = false;
                                }
                            }
                        }

                        InvocationTargetException var38 = var41;
                        TSLog.logger.error(TSLog.error(var38.getMessage()));
                        var38.printStackTrace();
                        return null;
                    }

                    IllegalAccessException var39 = var42;
                    TSLog.logger.error(TSLog.error(var39.getMessage()));
                    var39.printStackTrace();
                    return null;
                }

                NoSuchMethodException var40 = var43;
                TSLog.logger.error(TSLog.error(var40.getMessage()));
                var40.printStackTrace();
                return null;
            }

            var0 = var48;
            return var0;
        }
    }

    private static void b(Object var0, a var1) {
        try {
            Field var10000 = b.class.getDeclaredField(var1.b);
            var10000.set(var10000, var0);
        } catch (NoSuchFieldException var2) {
            TSLog.logger.error(TSLog.error(Application.B("诛⊞⊛㺠竮윳ꊰ赊\udc48㊰\uf1fd贲涵妆휶쀑꽬\ud801䄁꓄苀㩢") + var1.b));
            var2.printStackTrace();
        } catch (IllegalAccessException var3) {
            TSLog.logger.error(TSLog.error(Application.B("诛⊞⊛㺠竮윳ꊰ赊\udc48㊰\uf1e8贾涯姂흰쀞꽠\ud808䄉꒚苚") + var1.b));
            var3.printStackTrace();
        }

    }

    private static Object a(a var0) {
        a var10000 = var0;
        Object var1 = null;

        label150: {
            boolean var10001;
            int var27;
            try {
                var27 = var10000.a;
            } catch (ClassCastException var25) {
                var10001 = false;
                break label150;
            }

            JSONException var28;
            switch(var27) {
                case 0:
                    SharedPreferences var10002;
                    a var39;
                    try {
                        var10000 = var0;
                        var39 = var0;
                        var10002 = g;
                    } catch (ClassCastException var24) {
                        var10001 = false;
                        break;
                    }

                    SharedPreferences var26 = var10002;

                    String var40;
                    try {
                        var40 = var39.b;
                    } catch (ClassCastException var23) {
                        var10001 = false;
                        break;
                    }

                    String var2 = var40;

                    Object var36;
                    try {
                        var36 = var10000.c;
                    } catch (ClassCastException var22) {
                        var10001 = false;
                        break;
                    }

                    String var3;
                    String var38;
                    if (var36 != null) {
                        try {
                            var38 = var0.c.toString();
                        } catch (ClassCastException var21) {
                            var10001 = false;
                            break;
                        }

                        var3 = var38;
                    } else {
                        var3 = null;
                    }

                    try {
                        var38 = var26.getString(var2, var3);
                    } catch (ClassCastException var20) {
                        var10001 = false;
                        break;
                    }

                    var1 = var38;
                    return var1;
                case 1:
                    Integer var35;
                    try {
                        var35 = g.getInt(var0.b, (Integer)var0.c);
                    } catch (ClassCastException var19) {
                        var10001 = false;
                        break;
                    }

                    var1 = var35;
                    return var1;
                case 2:
                    Float var34;
                    try {
                        var34 = g.getFloat(var0.b, (Float)var0.c);
                    } catch (ClassCastException var18) {
                        var10001 = false;
                        break;
                    }

                    var1 = var34;
                    return var1;
                case 3:
                    Boolean var33;
                    try {
                        var33 = g.getBoolean(var0.b, (Boolean)var0.c);
                    } catch (ClassCastException var17) {
                        var10001 = false;
                        break;
                    }

                    var1 = var33;
                    return var1;
                case 4:
                    Long var32;
                    try {
                        var32 = g.getLong(var0.b, (Long)var0.c);
                    } catch (ClassCastException var16) {
                        var10001 = false;
                        break;
                    }

                    var1 = var32;
                    return var1;
                case 5:
                    label156: {
                        JSONObject var30;
                        try {
                            var30 = new JSONObject;
                        } catch (JSONException var14) {
                            var28 = var14;
                            var10001 = false;
                            break label156;
                        } catch (ClassCastException var15) {
                            var10001 = false;
                            break;
                        }

                        var1 = var30;

                        try {
                            var30.<init>(g.getString(var0.b, var0.c.toString()));
                            return var1;
                        } catch (JSONException var12) {
                            var28 = var12;
                            var10001 = false;
                        } catch (ClassCastException var13) {
                            var10001 = false;
                            break;
                        }
                    }

                    JSONObject var37;
                    try {
                        TSLog.logger.error(TSLog.error(Application.B("ᙀិ\uf009窵цᑰ塒\ue30e믯㍚⧸\ue0ba糳ꖪ筴섲诓㺵\ud930\uebe7튡衮㎁칛\uf85d\uded1䳚莋") + var0.b));
                        var37 = new JSONObject;
                    } catch (ClassCastException var6) {
                        var10001 = false;
                        break;
                    }

                    var1 = var37;

                    try {
                        var37.<init>();
                        var28.printStackTrace();
                        return var1;
                    } catch (ClassCastException var5) {
                        var10001 = false;
                        break;
                    }
                case 6:
                    label159: {
                        JSONArray var29;
                        try {
                            var29 = new JSONArray;
                        } catch (JSONException var10) {
                            var28 = var10;
                            var10001 = false;
                            break label159;
                        } catch (ClassCastException var11) {
                            var10001 = false;
                            break;
                        }

                        var1 = var29;

                        try {
                            var29.<init>(g.getString(var0.b, var0.c.toString()));
                            return var1;
                        } catch (JSONException var8) {
                            var28 = var8;
                            var10001 = false;
                        } catch (ClassCastException var9) {
                            var10001 = false;
                            break;
                        }
                    }

                    JSONArray var31;
                    try {
                        TSLog.logger.error(TSLog.error(Application.B("ᙀិ\uf009窵цᑰ塒\ue30e믯㍚⧸\ue0ba糳ꖪ筴섲诓㺵\ud930\uebe7튡衠㎑칃\uf859\udecb䲎") + var0.b));
                        var31 = new JSONArray;
                    } catch (ClassCastException var7) {
                        var10001 = false;
                        break;
                    }

                    var1 = var31;

                    try {
                        var31.<init>();
                        var28.printStackTrace();
                        return var1;
                    } catch (ClassCastException var4) {
                        var10001 = false;
                        break;
                    }
                default:
                    return var1;
            }
        }

        TSLog.logger.warn(TSLog.warn(Application.B("ᙀិ\uf009窵цᑰ塒\ue30e믯㍚⧿\ue0be糣ꖱ笰설讖㺋\ud917\uebc1튁衆㏃") + var0.b + Application.B("ᘨ៶\uf040窘ѓᑤ堞\ue303믩㌔⧻\ue0ff糴ꖠ筶섶讆㺓\ud917\uebfe튎衍㎖칔")));
        var1 = var0.c;
        return var1;
    }

    private static void a(Object var0, a var1, Editor var2) {
        String var3;
        switch(var1.a) {
            case 0:
                String var4 = var1.b;
                if (var0 != null) {
                    var3 = var0.toString();
                } else {
                    var3 = null;
                }

                var2.putString(var4, var3);
                break;
            case 1:
                var2.putInt(var1.b, (Integer)var0);
                break;
            case 2:
                var2.putFloat(var1.b, (Float)var0);
                break;
            case 3:
                var2.putBoolean(var1.b, (Boolean)var0);
                break;
            case 4:
                var2.putLong(var1.b, (Long)var0);
                break;
            case 5:
            case 6:
                Object var10001 = var0;
                var3 = var1.b;
                var2.putString(var3, var10001.toString());
        }

    }
}
