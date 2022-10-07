//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.b;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static final int c = 1;
    private static final Map<Integer, String> d = new HashMap();
    private static final String e = Application.B("绋谘滝뱬ꫧ珆\u1a8f凱⡽ᇋ癁\uee71\uf5ff\ue6e0듄ᴏꗁ╆ጼ\ue5ab嫉썮ࢶ遷㹱\uda1a\uf3bb쳖ꉑ戔ᨩ端⚏磶ᥘ⽵\udef3䔍뎺\u1ca2");
    private static final String f = Application.B("纚谔溈뱳ꪠ珖\u1ad7冬⡫ᆔ瘇\uee37\uf5f2\ue6a1뒇ᵗꗂ┆፰\ue5ac媕썾ࢰ遰㸠\uda59\uf3ea쳂ꈒ手ᩧ竵⛇磼ᤅ⽳\udea5䔂돿\u1ca1");
    private static final String g = Application.B("纩谞溗밮ꪽ珗\u1ac2冇⡮ᆘ瘚\uee37");
    private static a h;
    private static final List<String> i = Arrays.asList(Application.B("纝谞溓"), Application.B("纝谞溓밸ꪸ珋\u1ac6冯⡽ᆓ瘀"), Application.B("纊谏溄밺ꪽ珊\u1ad1"), Application.B("纈谚"), Application.B("續谚溑"), Application.B("纍谞準방"), Application.B("纝谞溇밨ꪳ"));
    private Boolean a = false;
    private Boolean b = null;

    public static a a() {
        if (h == null) {
            h = b();
        }

        return h;
    }

    private static synchronized a b() {
        if (h == null) {
            h = new a();
        }

        return h;
    }

    public a() {
    }

    private boolean c(Context var1, String var2) {
        String var3 = var1.getPackageName();
        String var12;
        if ((var12 = this.a(var1, var2)) == null) {
            this.a(Application.B("\uf7cf䝳杨ঁ湺烽\u18ab쑍㐚ៗ塙⒵䜃벿㫗\u0f70係律杜뀳졗臌饂䐙戮\ued35㲈㹋༯") + var2, Application.B("\uf789䝼杊ভ湘烋ᢊ쑍㐸៹堵⒚䜮벐㫧༙信徬期뀐졸臶饫䑬戗\ued15㳑㹆ས뮷⺩疷\udcb5轥㓰ꁝ㒧첁ꐮᶲ๛\ufd90㌺表؍\ue6f1嶠\udf73㱛\uda1cﳒ폃\u1bf7맋悌⮽넸\uee50낅㴲ܾ鵽㬣\uf8ad勵ἔ낫撀\ua7dfˏኌ\uea78獹⊑\ua7e7ܜ煸ﵖ䀴迿ࡡ뤼䐣⁍잃㶱婏\ue225ᖏ\uf26f持Ⱀ\ue66a兿Ꮗ튇頋研᠖︾誮蝧㫲७ꄷ␛燅駑\uf2aa\uded4噛븂\ude61\ue846\u3040젒줝\uf5eb鼺즞砡䭷") + var2 + Application.B("\uf7a1䜚杊প湐烜ᢁ쐄㐨ឬ塣⒝䜫벋㫦༄侯忹朥뀺졃臗餮䐀戵\ued33㳭㸨ཛྷ뮜⺩疽\udc9e轘㒼ꀐ㓮쳊ꑝ"));
            return false;
        } else {
            label73: {
                boolean var10000;
                label72: {
                    label79: {
                        boolean var10001;
                        try {
                            var10000 = this.a(var3, var2, var12);
                        } catch (Exception var11) {
                            var10001 = false;
                            break label79;
                        }

                        if (var10000) {
                            return true;
                        }

                        String[] var13;
                        try {
                            var13 = var3.split(Application.B("\uf7df䜔"));
                        } catch (Exception var10) {
                            var10001 = false;
                            break label79;
                        }

                        String[] var4;
                        String[] var15 = var4 = var13;

                        int var16;
                        try {
                            var16 = var15.length;
                        } catch (Exception var9) {
                            var10001 = false;
                            break label79;
                        }

                        --var16;

                        try {
                            var10000 = i.contains(var13[var16]);
                        } catch (Exception var8) {
                            var10001 = false;
                            break label79;
                        }

                        if (!var10000) {
                            break label73;
                        }

                        a var14;
                        int var10002;
                        try {
                            var14 = this;
                            var15 = var4;
                            var10002 = var4.length;
                        } catch (Exception var7) {
                            var10001 = false;
                            break label79;
                        }

                        --var10002;

                        String var17;
                        try {
                            var17 = a(Arrays.asList((String[])Arrays.copyOf(var15, var10002)), Application.B("\uf7ad"));
                        } catch (Exception var6) {
                            var10001 = false;
                            break label79;
                        }

                        var3 = var17;

                        try {
                            var10000 = var14.a(var17, var2, var12);
                            break label72;
                        } catch (Exception var5) {
                            var10001 = false;
                        }
                    }

                    TSLog.logger.error(TSLog.error(Application.B("\uf7cf䝓杈ড湚烝ᢋ쑍㐺៷塹⒕䜣벟㫷ཐ俢徫杜뀐졤臷饡䐾")));
                    break label73;
                }

                if (var10000) {
                    return true;
                }
            }

            this.a(Application.B("\uf7cf䝳杨ঁ湺烽\u18ab쑍㐚ៗ塙⒵䜃벿㫗\u0f70係律杜뀳졗臌饂䐙戮\ued35㲈㹋༯") + var2, var3);
            return false;
        }
    }

    private String a(Context var1, String var2) {
        String var10000;
        try {
            var10000 = var1.getPackageManager().getApplicationInfo(var1.getPackageName(), 128).metaData.getString(var2);
        } catch (NameNotFoundException var3) {
            return null;
        }

        String var4 = var10000;
        return var10000 == null ? null : var4;
    }

    private boolean a(String var1, String var2, String var3) {
        MessageDigest var10000;
        try {
            var10000 = MessageDigest.getInstance(Application.B("粖襘隟㑦䩤ⷑ䡤"));
        } catch (Exception var4) {
            this.a(Application.B("粉襙隝㐎䨘\u2db7䠗\u1317ᒌ萩ⵈ掠亹旵ﷲ撰䆸ʀ傞构氱Ȱ嵷廆瘚䑱ȕ雜갰") + var2, var1);
            return false;
        }

        boolean var15;
        label63: {
            label58: {
                StringBuilder var14;
                boolean var10001;
                try {
                    var14 = new StringBuilder;
                } catch (Exception var9) {
                    var10001 = false;
                    break label58;
                }

                StringBuilder var10002 = var14;

                byte[] var11;
                try {
                    var10002.<init>();
                    var11 = var10000.digest(var14.append(var2).append(Application.B("糿")).append(var1).append(Application.B("糿")).append(Application.B("糷襳雦㑺䩥ⶆ䡫ጄᒿ葞ⴱ換亟文ﶖ擎䆒˺僜枣汅ț嵘延癸䐄ȁ隕갡帝蒢Ծ\u2d69讥\ud964\uf72c₧骸蕰圇")).toString().getBytes(Application.B("粐襄隘㑦䩮")));
                } catch (Exception var8) {
                    var10001 = false;
                    break label58;
                }

                byte[] var10 = var11;

                StringBuilder var12;
                int var16;
                try {
                    var12 = (new StringBuilder()).append(Application.B("糠褠"));
                    var16 = var10.length;
                } catch (Exception var7) {
                    var10001 = false;
                    break label58;
                }

                var16 *= 2;

                String var13;
                Object[] var18;
                try {
                    var13 = var12.append(var16).append(Application.B("粝")).toString();
                    var18 = new Object[1];
                } catch (Exception var6) {
                    var10001 = false;
                    break label58;
                }

                Object[] var17 = var18;
                byte var10003 = 0;

                try {
                    var17[var10003] = new BigInteger(1, var10);
                    var15 = String.format(var13, var18).equalsIgnoreCase(var3);
                    break label63;
                } catch (Exception var5) {
                    var10001 = false;
                }
            }

            TSLog.logger.error(TSLog.error(Application.B("粉襹隽㐮䨸\u2d97䠷\u1317ᒬ萉\u2d68掀亙旕\ufdd2撐䆘ʠ傞枧氂ȋ嵔廡")));
            return false;
        }

        if (var15) {
            return true;
        } else {
            return false;
        }
    }

    private boolean b(Context param1) {
        // $FF: Couldn't be decompiled
    }

    private void a(String var1, String var2) {
        TSLog.logger.error(TSLog.error(var1 + Application.B("糿褰") + var2));
    }

    private static String a(List<String> var0, String var1) {
        List var10000 = var0;
        StringBuilder var5;
        var5 = new StringBuilder.<init>();
        boolean var2 = true;

        String var4;
        for(Iterator var3 = var10000.iterator(); var3.hasNext(); var5.append(var4)) {
            var4 = (String)var3.next();
            if (var2) {
                var2 = false;
            } else {
                var5.append(var1);
            }
        }

        return var5.toString();
    }

    private void b(Context var1, String var2) {
        NoSuchMethodException var28;
        label104: {
            IllegalAccessException var27;
            label105: {
                InvocationTargetException var26;
                label106: {
                    Exception var10000;
                    label107: {
                        ClassNotFoundException var29;
                        label86: {
                            boolean var10001;
                            Class var30;
                            try {
                                var30 = Class.forName(var2);
                            } catch (ClassNotFoundException var18) {
                                var29 = var18;
                                var10001 = false;
                                break label86;
                            } catch (NoSuchMethodException var19) {
                                var28 = var19;
                                var10001 = false;
                                break label104;
                            } catch (IllegalAccessException var20) {
                                var27 = var20;
                                var10001 = false;
                                break label105;
                            } catch (InvocationTargetException var21) {
                                var26 = var21;
                                var10001 = false;
                                break label106;
                            } catch (Exception var22) {
                                var10000 = var22;
                                var10001 = false;
                                break label107;
                            }

                            Class var23;
                            Class var31 = var23 = var30;

                            Class[] var10002;
                            try {
                                var10002 = new Class[1];
                            } catch (ClassNotFoundException var13) {
                                var29 = var13;
                                var10001 = false;
                                break label86;
                            } catch (NoSuchMethodException var14) {
                                var28 = var14;
                                var10001 = false;
                                break label104;
                            } catch (IllegalAccessException var15) {
                                var27 = var15;
                                var10001 = false;
                                break label105;
                            } catch (InvocationTargetException var16) {
                                var26 = var16;
                                var10001 = false;
                                break label106;
                            } catch (Exception var17) {
                                var10000 = var17;
                                var10001 = false;
                                break label107;
                            }

                            Class[] var24 = var10002;
                            byte var10003 = 0;

                            Method var32;
                            try {
                                var10002[var10003] = Context.class;
                                var32 = var31.getMethod(Application.B("\uf45bя℄潾睅\ue16e롤\ueadf坰Ṅ傄"), var24);
                            } catch (ClassNotFoundException var8) {
                                var29 = var8;
                                var10001 = false;
                                break label86;
                            } catch (NoSuchMethodException var9) {
                                var28 = var9;
                                var10001 = false;
                                break label104;
                            } catch (IllegalAccessException var10) {
                                var27 = var10;
                                var10001 = false;
                                break label105;
                            } catch (InvocationTargetException var11) {
                                var26 = var11;
                                var10001 = false;
                                break label106;
                            } catch (Exception var12) {
                                var10000 = var12;
                                var10001 = false;
                                break label107;
                            }

                            Method var25 = var32;
                            String var33 = Application.B("\uf44fџℒ潄睈\ue16f롹\ueadc坻");

                            try {
                                var30.getMethod(var33).invoke(var25.invoke(var23, var1));
                                return;
                            } catch (ClassNotFoundException var3) {
                                var29 = var3;
                                var10001 = false;
                            } catch (NoSuchMethodException var4) {
                                var28 = var4;
                                var10001 = false;
                                break label104;
                            } catch (IllegalAccessException var5) {
                                var27 = var5;
                                var10001 = false;
                                break label105;
                            } catch (InvocationTargetException var6) {
                                var26 = var6;
                                var10001 = false;
                                break label106;
                            } catch (Exception var7) {
                                var10000 = var7;
                                var10001 = false;
                                break label107;
                            }
                        }

                        Log.e(Application.B("\uf468ѹℼ潘睈\ue17c롤\uead7坱ṉ催\udf93⠧罀焆朂檝"), var29.toString());
                        return;
                    }

                    Log.e(Application.B("\uf468ѹℼ潘睈\ue17c롤\uead7坱ṉ催\udf93⠧罀焆朂檝"), var10000.toString());
                    return;
                }

                Log.e(Application.B("\uf468ѹℼ潘睈\ue17c롤\uead7坱ṉ催\udf93⠧罀焆朂檝"), var26.toString());
                return;
            }

            Log.e(Application.B("\uf468ѹℼ潘睈\ue17c롤\uead7坱ṉ催\udf93⠧罀焆朂檝"), var27.toString());
            return;
        }

        Log.e(Application.B("\uf468ѹℼ潘睈\ue17c롤\uead7坱ṉ催\udf93⠧罀焆朂檝"), var28.toString());
    }

    static {
        d.put(1, Application.B("纚谔溈뱳ꪠ珖\u1ad7冬⡫ᆔ瘇\uee37\uf5f2\ue6a1뒇ᵗꗂ┆፰\ue5be媏썪ࢼ遠㸤\uda48\uf3ee쳁ꈅ扔ᩭ竣⚑磩\u1942⽄\ude93䔪돥\u1cb6坁攦墿瓳\u1a8f\ue597첨鈨Ò\uffdd"));
    }

    public void a(Context var1, int var2) {
        if (var2 == 1) {
            TSConfig.getInstance(var1).setPluginForEvent(1, Application.B("粕襵隬㐸䨿\u2d97䠦፲ᒬ萍\u2d6a掝"));
            this.a(var1);
        }

    }

    public void a(Context var1, JSONObject var2) {
        Iterator var3 = var2.keys();

        while(var3.hasNext()) {
            a var10000 = this;
            Context var10001 = var1;
            JSONObject var10002 = var2;
            String var10003 = (String)var3.next();

            try {
                var10000.b(var10001, (String)d.get(var10002.getInt(var10003)));
            } catch (JSONException var5) {
                TSLog.logger.debug(var5.getMessage(), var5);
                var5.printStackTrace();
            }
        }

    }

    public boolean a(Context var1) {
        if (this.a) {
            return this.a;
        } else {
            int var2;
            if ((var2 = TSConfig.getInstance(var1).getPluginForEvent(Application.B("粕襵隬㐸䨿\u2d97䠦፲ᒬ萍\u2d6a掝"))) > 0) {
                int var10000 = var2;
                String var3 = null;
                if (var10000 == 1) {
                    var3 = Application.B("粦西隳㑥䨢ⶖ䠳ፙᒩ萁\u2d77掝互旆\ufdd5撖䆑ʺ傐枤氙ȋ嵞廱瘩䑇ɐ隁걢幂蓬Ԥⴡ讯\ud939\uf72a\u20f1骷蔵圄");
                }

                if (var3 != null) {
                    this.a = this.c(var1, var3);
                }
            }

            if (!this.a && this.b(var1)) {
                this.a = true;
            }

            return this.a;
        }
    }
}
