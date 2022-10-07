//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.d;
import com.transistorsoft.tslocationmanager.Application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class a {
    private static final String a = Application.B("垱웟뤼⃥밳荏ፐ尠⨜춼\uf2b3탱ﲫ턈㡙Ư杕ℴ町킲䂁䗲ㅲ\uf362騃\uef97傗돼趒綃\uf8edꯓ㔕쿊齯࿒ᑥ攤ꛑ\ueb4a");
    private static final String b = Application.B("埪웓뤪₲뱬荘ጝ屧⨜췸\uf2a8킢ﲹ텋㡇Ǟ杜ⅵ甬킧䃑䗢ㅐ\uf364驇\uefce僕돱跗緊");
    private static final String c = Application.B("埠웓륩\u20fa뱴荟ገ屽⨊췣\uf2f5킷ﲦ텉㠚Ƿ杖ⅴ當킵䃘䗥ㅥ\uf373驖\uefd5傍돺跂緐\uf8b0ꯗ㔁쿃鼮࿙ᐲ攢Ꚃ\ueb43\u09d3댇촻뜇ᓹ崌稛ꉉㇸ缹댋᧓ﶁ큛蓮霓㿟軋鎁\ue638艵\uf2a6版屗玛ｓ剞涅เḭ蕏䆤ꇳ\ue80d슿䖃שׂꂛ꾇");
    private static final String d = Application.B("埬웎륣\u20fa뱡荝ገ屰⨑췯\uf2a8킠ﲦ텉㠍Ƿ杆Ⅱ當킐䃛䗢ㅵ\uf368驅\uefc6僢돻跗線\uf8adꯙ㔇쿕");
    private static final String e = Application.B("埠웓륩\u20fa뱴荟ገ屽⨊췣\uf2f5킷ﲦ텉㠚Ƿ杖ⅴ當킰䃛䗢ㅵ\uf368驅\uefc6傍돺跄緔\uf8beꯟ㕝쿯鼟\u0fe1ᐔ攤Ꚅ\ueb47\u09d8댚촷뜓ᓣ崁稳ꉂㆹ缓댨᧤ﶢ큎煉霗㿖");
    private static final String f = Application.B("埠웓륩\u20fa뱦荌ጊ屶⨛췥\uf2e9킨ﳧ텉㠌ǹ杓ⅴ當킁䃑䗱ㅲ\uf373驲\uefc4僗돱跕線\uf8afꯉ");
    private static final String g = Application.B("埠웓륩\u20fa뱴荟ገ屽⨊췣\uf2f5킷ﲦ텉㠚Ƿ杖ⅴ當킡䃚䗲ㅰ\uf364驘\uefc0僑돷跖緝\uf8bfꯗ㔖쿃鼷࿘ᐵ攤ꚓ\ueb45\u09d0댆쵶뜴ᓃ崧稕ꉄ\u31bd缘댵᧨ﶶ큔咽霿㿝軖鎂\ue622艸\uf2a3牻屛玛ｑ");
    private static final String h = Application.B("埠웓륩\u20fa뱴荃ጚ尽⨷췫\uf2f2킪ﲿ텞㠺ǻ杂Ⅹ用킧䃵䗳ㅥ\uf36e驅\uefce僗돡");
    private static final String i = Application.B("埠웓륩\u20fa뱴荟ገ屽⨊췣\uf2f5킷ﲦ텉㠚Ƿ杖ⅴ當킱䃓䗷ㅴ\uf368騝\uefc4僂돨跂緐\uf8b2ꯄ㔜쿞齵\u0ff5ᐷ攦Ꚍ\ueb4b্댇촭뜈ᓩ崢稑ꉈㆺ缐댤᧦ﶷ큓秊霖㿨軕鎛\ue62a色\uf2ac");
    private static final String j = Application.B("埠웓륩\u20fa뱴荟ገ屽⨊췣\uf2f5킷ﲦ텉㠚Ƿ杖ⅴ當킿䃛䗳ㅰ\uf373驚\uefc8働돵跂緝\uf8baꯗ㔖쿞齵\u0fdbᐿ攦Ꚃ\ueb42ৌ댍");
    private static final String k = Application.B("埠웓륩\u20fa뱴荟ገ屽⨊췣\uf2f5킷ﲦ텉㠚Ƿ杖ⅴ當킿䃛䗳ㅰ\uf373驚\uefc8働돵跂緝\uf8baꯗ㔖쿞齵\u0fdbᐿ攦Ꚃ\ueb42ৌ댍촇뜍ᓨ崜");
    private static final String l = Application.B("埥원륱₠뱴荈ጛ");
    private static final String m = Application.B("埠웓륶₰뱯荛ገ");
    private static final String n = Application.B("埱웙륥₷뱴");
    private static final String o = Application.B("埭웝륰₽뱶荈ጚ屰⨋췣\uf2f6킷");
    private static final String p = Application.B("埭웝륰₽뱶荈");
    private static final String q = Application.B("埠웝르₵뱣荄ጝ屼⨋");
    private static final List<String> r = Arrays.asList(Application.B("埧웙륲"), Application.B("埧웙륲₱뱬荂ጙ屾⨜췤\uf2f2"), Application.B("埰웈륥₳뱩荃ጎ"), Application.B("埲웝"), Application.B("埶웝륰"), Application.B("執웙륷₠"), Application.B("埧웙륦₡뱧"));
    private static final String s = Application.B("埩웝륲₵밮荞ጌ屰⨌췸\uf2ef킷ﲰ턕㠤ǽ权ⅳ甹킴䃑䗔ㅸ\uf360驖\uefd4僗");
    private static final String t = Application.B("埏웵륇ₑ뱎荾ጬ尳⨯췋\uf2ca킊ﲍ텺㠽Ǒ板ⅎ畸킕䃵䗙ㅝ\uf352驡\uefe2");
    private static final String u = Application.B("埊웒륲₵뱬荄ግ尳⨕췣\uf2e5킦ﲧ텈㠌Ƹ杛Ⅵ甡");
    private static final String v = Application.B("埅웝륭₸뱥草ፉ屧⨖춪\uf2e0킪ﲧ텟㡉Ǵ杙Ⅳ甽킽䃇䗵ㄱ\uf36c驖\uefde傃돱跍經\uf89aꯞ㔗쿞鼴\u0fdeᐲ攈Ꚇ\ueb42\u09d6댎촽뜕ᓹ嵋穔ꈇ㆓缑댴᧲ﶱ큟栗霁㿗軌鏉\ue63b艾\uf2e2牮屖玐ｚ剕淆๕ḱ蕃䇫ꇶ\ue838슪䗖כּꂛ꾝迊퍚뭮䈴춱咾㘉乁ꎐ蹹\ue506먵侫Đ韶泸캔㴪曍\udbc9\ue9c0獧퇵\uda34톖\udc18⡃읛榠꧴홴鉒徍傪턼䜙ꨠ北쥷乗뤽얆\uded3\ue0f4揫蕠\u1cc9\udb67䞪ኰ晥\uf148䬭蝁ꆟ\ud8a8㴯\ua7c3ࡀ≄躽颏脁쑷ྱ\rࣞߟ⨡眕㱻\uebea뉗䴬㛅\ufff3쒈ۙ곧쾑兔촅\ue998嗹Ꝋ䔤\uece8۵\ue501\ue1be\ue9a9뵖白⻛馒뼂犢鬏둀㜐\uf04d\ue4b4㍚浆紫껖ᎈ\ua7d3䆵긩闏諎貇\ue4f3ﾹ仹䵅\uebed覩\ue935袀籿\ue140");
    private static boolean w = false;
    private static boolean x = false;
    private static boolean y = false;
    private static Handler z;

    public a() {
    }

    public static void d(Context var0) {
        c(var0);
    }

    public static boolean c(Context var0) {
        if (!w) {
            y = e(var0);
        }

        return b(var0) || w && y && x;
    }

    private static boolean b(Context param0) {
        // $FF: Couldn't be decompiled
        return false;
    }

    public static Handler b() {
        if (z == null) {
            z = new Handler(Looper.getMainLooper());
        }

        return z;
    }

    private static boolean a(String var0) {
        try {
            Class.forName(var0);
        } catch (ClassNotFoundException var1) {
            return false;
        }

        return true;
    }

    private static boolean e() {
        return a(Application.B("齊䷎啧귓\u1acd⻀섽뮻礀웸綔燤ऊ啎娭䦫䭃\ud80d濚憌ꔿ᱃❂隌棂⅓㿫\uf02f뉄줥ꅐ\udf32◬ꖵ쥭跔\u1cceﳚⴤ앲\uef7d쭸䟓㈡⊍\u001a덩ￌ崎쏢让詷⌊ண\ue2d5緰\uf4a6Ո×\uebc0\ue436䎅远똑\ue55aෝ헤풩壳\u17ed䙲㛰뤅ᛩ᪘ꯑ暏\udb80ત")) || a(Application.B("齀䷎唤궛\u1ad5⻇섨뮡礖웣緉燱क啌婰䦂䭉\ud80c澀憞ꔶ᱄❷際棓ⅈ㾳\uf024뉑줿"));
    }

    private static boolean g() {
        return a(Application.B("\ue9a9쾣縴奻巅孜驕坁蘼\u20c0佊䞎嵁ڧག᜶\u2ef8뉴䂃䱧\uf6c2\ue605傰ᇶ\ueffd餞︧혘쀂뱵葜䄞뵼왏엯沣噦泽眲輹뚍톮\ue4ad릖ಛƾ\uf4bcख़䃨꽆닺픑克咨륵괒㇛爍ㅰ⫆쬑\ue5e4\udd1f⑸㣴θ")) || a(Application.B("\ue9a9쾣縴奻巗孏驗坊蘭\u20c6佖䞑崀ڧཔ\u1738\u2efd뉴䂃䱇\uf6c9\ue606傲ᇡ\uefd7餚︡혞쀁뱲葌䄀"));
    }

    private static boolean d() {
        return a(Application.B("쒍롋\udb71쇳瑲멩赜㸆晏렐뒽ᶆ\uf02d⊖ጋ\ude51啈꣣鹹\ua8cc섶ᄐ\udd3eᭊ都塪\u0b8b鰬攇\udee8\uf31eప뿡全赆䡒\uf677깇ꀶ떄踹ᩱﶓ⡂軼刹蟁ꡑ\u1cca㔃\uea81䥞\ud891厥ή䖻⮀")) || a(Application.B("쒁롖\udb7b쇳瑧멫赜㸋晔렜든ᶑ\uf02d⊖ጜ\ude51啘ꣶ鹹꣬섶ᄐ\udd3eᭊ都塪\u0be4鰭攔\udee6\uf30dబ뾻煮"));
    }

    private static boolean f() {
        return a(Application.B("Ƚ姾磤觡뙬ꎂ\u1778昿褞䦈癆ꁙ敖㮝态\uf017웾൛⡗褷ቍ骫랶첏졔\u0a3a―龜"));
    }

    private static boolean c() {
        return a(Application.B("\udfc9\udfd0Ꝇ럊㚀痾頰転閃뱪㬸恥\ue3f8鋜카녕遒Ⴍ锜\uf672ꞕ魾\ude17\ue9b2妿珟㘆\u1ac0壱谮\uf433齙於瓑쾲鏞\udcc9Ǣ㐼劅쒟թ詠䃔嗨퉥㖗쇾똚똒\uee08ȣ㊘\ua8de濹⍀褂퇣\ue2d2帩癒\ud7fc"));
    }

    public static String a() {
        return e() ? Application.B("쭫⏰省屫捰銽\uf5bc") : (c() ? Application.B("쭮⏽眄屾捧銱\uf5ba軚鶏") : (d() ? Application.B("쭮⏳眆屻捫銮\uf5af") : (g() ? Application.B("쭿⏹眕屼捰鋵\uf5a0軔鶉㰀ͦ炬") : (f() ? Application.B("쭣⏽眀屶捲銽\uf5bd軖鶏㰀͠炽") : Application.B("쭣⏽眀屶捲銽")))));
    }

    private static boolean e(Context var0) {
        String var1;
        if (e()) {
            var1 = Application.B("齏䷍啿궉\u1acd⻗섮");
        } else if (c()) {
            var1 = Application.B("齊䷀啺궜\u1ada⻛섨뮺礁");
        } else if (d()) {
            var1 = Application.B("齊䷎啸궙\u1ad6⻄섽");
        } else if (g()) {
            var1 = Application.B("齛䷄啫궞\u1acd");
        } else if (f()) {
            var1 = Application.B("齇䷀啾궔\u1acf⻗섯뮶礁웸綗燤");
        } else {
            var1 = Application.B("齇䷀啾궔\u1acf⻗");
        }

        String var2 = var0.getPackageName();
        String var3;
        String var10000 = var3 = a(var0);
        boolean var4 = b(var0);
        h();
        if (var10000 == null) {
            a(var0, Application.B("齥䷨啉궸\u1af7⻡섙믵礥원綫燙ड啽娊䦍䭪\ud837濔憬ꔒ᱿❺隭棵Ⅴ"), Application.B("齯䷀啣궑\u1adc⻖셼뮡礜욱綁燹ऋ啘婾䦨䭌\ud81a澑憄ꔠ᱓✖隓棂⅘㿥\uf024뉋쥦ꅺ\udf3b◺ꖨ쥷跓\u1cceﳰⴠ앳\uef78쭱䟕㈳⊍]댦ﾂ嵥쏊讖詖⌺\u0ba7\ue296緢\uf4aeՏ\u009f\uebc3\ue43d䏁迺또\ue551ු헯퓪壦៱䙾㚿뤀ᛜ\u1a8dꮄ暟\udb80ા\ufbc2\ue052媬婙ꊆ㵬ꢁ튑끉\ue160镮輺邫䧏슣멒墏朢鯴弾髬牯司\uef8c黎\u0ea0Ն봜\uf116㛶ገ\uebd4\ud997㫠䬱툉뇗帜\ue5f3巄誗亯睲훃่腩⣯흧ꨍ并녧\udc21卧猂ﹻ媯ኇￗ妇盀暚꾤魰\ueb03ꐬ묞씄\ue02aﰉ갸獑뒧⢬\u2bf2ǁ￪侳ꗅ圾\ue862ꮍꭷ༏㪘硅\ue484\uea43\ue629\u0e67낫\ue267崶\uf47f咘李ⳇ覯틳轐鲧\ue606죝⚓옘⤨曒䨨ᕛ粑Ὥ\udc40鏖釯ퟨ䩆Ь㸒Ḣ︴団\uf250竚熃"));
            if (!var4) {
                w = false;
            }

            return false;
        } else {
            label89:
            {
                boolean var14;
                label88:
                {
                    Exception var13;
                    label97:
                    {
                        boolean var10001;
                        try {
                            var14 = a(var2, var1, var3);
                        } catch (Exception var10) {
                            var13 = var10;
                            var10001 = false;
                            break label97;
                        }

                        if (var14) {
                            return true;
                        }

                        String[] var15;
                        try {
                            var15 = var2.split(Application.B("齵䶏"));
                        } catch (Exception var9) {
                            var13 = var9;
                            var10001 = false;
                            break label97;
                        }

                        String[] var11;
                        String[] var16 = var11 = var15;

                        int var17;
                        try {
                            var17 = var16.length;
                        } catch (Exception var8) {
                            var13 = var8;
                            var10001 = false;
                            break label97;
                        }

                        --var17;

                        try {
                            var14 = r.contains(var15[var17]);
                        } catch (Exception var7) {
                            var13 = var7;
                            var10001 = false;
                            break label97;
                        }

                        if (!var14) {
                            break label89;
                        }

                        try {
                            var15 = var11;
                            var17 = var11.length;
                        } catch (Exception var6) {
                            var13 = var6;
                            var10001 = false;
                            break label97;
                        }

                        --var17;

                        try {
                            var14 = a(a(Arrays.asList((String[]) Arrays.copyOf(var15, var17)), Application.B("鼇")), var1, var3);
                            break label88;
                        } catch (Exception var5) {
                            var13 = var5;
                            var10001 = false;
                        }
                    }

                    Exception var12 = var13;
                    Log.e(Application.B("齽䷲商궒\u1ada⻓섨뮼礜웿綪燱ऋ啝娹䦡䭗"), TSLog.error(Application.B("齥䷨啉궸\u1af7⻡섙믵礥원綫燙ड啽娊䦍䭪\ud837濔憬ꔒ᱿❺隭棵Ⅴ㿿") + var12.getMessage()));
                    break label89;
                }

                if (var14) {
                    return true;
                }
            }

            a(var0, Application.B("齥䷨啉궸\u1af7⻡섙믵礥원綫燙ड啽娊䦍䭪\ud837濔憬ꔒ᱿❺隭棵Ⅴ"), Application.B("齠䷏啼궜\u1ad5⻛세믵礟웸綄燵ऋ問娻䧤䭎\ud81c澍懐ꕳ") + var3);
            if (!var4) {
                w = false;
            }

            return false;
        }
    }

    private static boolean a(String var0, String var1, String var2) {
        byte[] var3;
        if ((var3 = b(var1 + Application.B("쬷") + var0 + Application.B("쬷") + Application.B("쬿⏿睌尮挷銺\uf5f7躆鶘㱟̥烻唋⬗\ue576驄汞鲃諗滲핤鸒범\ue7d7陑ቊ쏇弾拞菧퀷垧俇㘅ꡉ䃾梒鉱ບ\u1cbd"))) != null) {
            String var10000 = Application.B("쬨⎬") + var3.length * 2 + Application.B("쭕");
            Object[] var10001 = new Object[1];
            BigInteger var4;
            var4 = new BigInteger(1, var3);
            var10001[0] = var4;
            return String.format(var10000, var10001).equalsIgnoreCase(var2);
        } else {
            return false;
        }
    }

    private static byte[] b(String var0) {
        ClassNotFoundException var44;
        label130:
        {
            NoSuchMethodException var43;
            label131:
            {
                IllegalAccessException var42;
                label132:
                {
                    InvocationTargetException var41;
                    label133:
                    {
                        Exception var10000;
                        label116:
                        {
                            boolean var10001;
                            Class var45;
                            try {
                                var45 = Class.forName(Application.B("\u0cd1솟된憋펾绑ꅾ袁⅏脢褗䜒⟫뉇\ue818ĳ\uf28c鍞\uee54Ꮵ\uedca\uefd3䱾䏠䝧컚유"));
                            } catch (ClassNotFoundException var29) {
                                var44 = var29;
                                var10001 = false;
                                break label130;
                            } catch (Exception var33) {
                                var10000 = var33;
                                var10001 = false;
                                break label116;
                            }

                            Class var1;
                            Class var47 = var1 = var45;

                            Class[] var10002;
                            try {
                                var10002 = new Class[1];
                            } catch (Exception var28) {
                                var10000 = var28;
                                var10001 = false;
                                break label116;
                            }

                            Class[] var2 = var10002;
                            byte var10003 = 0;

                            Method var50;
                            try {
                                var10002[var10003] = String.class;
                                var50 = var47.getMethod(Application.B("\u0cdc솛됞憣폾绑ꅯ袃⅔脳褛"), var2);
                            } catch (NoSuchMethodException var20) {
                                var43 = var20;
                                var10001 = false;
                                break label131;
                            } catch (Exception var23) {
                                var10000 = var23;
                                var10001 = false;
                                break label116;
                            }

                            Method var40 = var50;

                            Class[] var51;
                            try {
                                var51 = new Class[1];
                            } catch (Exception var18) {
                                var10000 = var18;
                                var10001 = false;
                                break label116;
                            }

                            Class[] var3 = var51;
                            byte var46 = 0;

                            Object[] var48;
                            Method var52;
                            Object var53;
                            try {
                                var51[var46] = byte[].class;
                                var52 = var45.getMethod(Application.B("\u0cdf솗됍憏폣绖"), var3);
                                var53 = var40.invoke(var1, Application.B("೨솶됫懇펢纗ꄭ"));
                                var48 = new Object[1];
                            } catch (NoSuchMethodException var10) {
                                var43 = var10;
                                var10001 = false;
                                break label131;
                            } catch (IllegalAccessException var11) {
                                var42 = var11;
                                var10001 = false;
                                break label132;
                            } catch (InvocationTargetException var12) {
                                var41 = var12;
                                var10001 = false;
                                break label133;
                            } catch (Exception var13) {
                                var10000 = var13;
                                var10001 = false;
                                break label116;
                            }

                            Object[] var49 = var48;
                            String var10004 = var0;
                            byte var34 = 0;

                            try {
                                var49[var34] = var10004.getBytes(Application.B("೮솪됬懇펨"));
                                return (byte[]) var52.invoke(var53, var48);
                            } catch (IllegalAccessException var6) {
                                var42 = var6;
                                var10001 = false;
                                break label132;
                            } catch (InvocationTargetException var7) {
                                var41 = var7;
                                var10001 = false;
                                break label133;
                            } catch (Exception var8) {
                                var10000 = var8;
                                var10001 = false;
                            }
                        }

                        Exception var35 = var10000;
                        Log.e(Application.B("೯솭됦憅폳练ꅯ袋⅕脾褳䜇⟼눈\ue832ĳ\uf28d"), Application.B("\u0cf7솷됩憯폞绱ꅞ裂Ⅼ脑褲䜯⟖눨\ue801ğ\uf2b0鍣\uee15Ꮔ\uedee\uefde䱛䏒䝐컬읮樗") + var35.getMessage());
                        return null;
                    }

                    InvocationTargetException var36 = var41;
                    Log.e(Application.B("೯솭됦憅폳练ꅯ袋⅕脾褳䜇⟼눈\ue832ĳ\uf28d"), Application.B("\u0cf7솷됩憯폞绱ꅞ裂Ⅼ脑褲䜯⟖눨\ue801ğ\uf2b0鍣\uee15Ꮔ\uedee\uefde䱛䏒䝐컬읮樗") + var36.getMessage());
                    return null;
                }

                IllegalAccessException var37 = var42;
                Log.e(Application.B("೯솭됦憅폳练ꅯ袋⅕脾褳䜇⟼눈\ue832ĳ\uf28d"), Application.B("\u0cf7솷됩憯폞绱ꅞ裂Ⅼ脑褲䜯⟖눨\ue801ğ\uf2b0鍣\uee15Ꮔ\uedee\uefde䱛䏒䝐컬읮樗") + var37.getMessage());
                return null;
            }

            NoSuchMethodException var38 = var43;
            Log.e(Application.B("೯솭됦憅폳练ꅯ袋⅕脾褳䜇⟼눈\ue832ĳ\uf28d"), Application.B("\u0cf7솷됩憯폞绱ꅞ裂Ⅼ脑褲䜯⟖눨\ue801ğ\uf2b0鍣\uee15Ꮔ\uedee\uefde䱛䏒䝐컬읮樗") + var38.getMessage());
            return null;
        }

        ClassNotFoundException var39 = var44;
        Log.e(Application.B("೯솭됦憅폳练ꅯ袋⅕脾褳䜇⟼눈\ue832ĳ\uf28d"), Application.B("\u0cf7솷됩憯폞绱ꅞ裂Ⅼ脑褲䜯⟖눨\ue801ğ\uf2b0鍣\uee15Ꮔ\uedee\uefde䱛䏒䝐컬읮樗") + var39.getMessage());
        return null;
    }

    private static void h() {
        w = true;
    }

    private static String a(Context var0) {
        Context var10000 = var0;
        String var1 = null;

        NameNotFoundException var9;
        label60:
        {
            Bundle var10;
            boolean var10001;
            try {
                var10 = var10000.getPackageManager().getApplicationInfo(var0.getPackageName(), PackageManager.GET_META_DATA).metaData;
            } catch (NameNotFoundException var8) {
                var9 = var8;
                var10001 = false;
                break label60;
            }

            Bundle var2 = var10;
            if (var10 == null) {
                return null;
            }

            boolean var11;
            var11 = var2.containsKey(Application.B("쭮⏳眙就捰銪\uf5af軛鶎㰀ͣ炽唆⭖\ue535騜汝鳃誛滿픾鸓벖\ue7c6阈ሕ쎝強抎莹큠垣俄㘑ꡓ䃷棈鉳້\u1cb5\ued9f뇤"));

            label47:
            {
                String var12;
                if (var11) {
                    var12 = var2.getString(Application.B("쭮⏳眙就捰銪\uf5af軛鶎㰀ͣ炽唆⭖\ue535騜汝鳃誛滿픾鸓벖\ue7c6阈ሕ쎝強抎莹큠垣俄㘑ꡓ䃷棈鉳້\u1cb5\ued9f뇤"));
                } else {
                    var11 = var2.containsKey(Application.B("쭮⏳眙就捰銪\uf5af軛鶎㰀ͣ炽唆⭖\ue535騜汝鳃誛滿픾鸓벖\ue7c6阈ሕ쎝強抎莹큠垣俄㘑ꡓ䃷棈鉳້\u1cb5\ued9f뇤揃핺憕῟"));

                    if (!var11) {
                        break label47;
                    }

                    var12 = var2.getString(Application.B("쭮⏳眙就捰銪\uf5af軛鶎㰀ͣ炽唆⭖\ue535騜汝鳃誛滿픾鸓벖\ue7c6阈ሕ쎝強抎莹큠垣俄㘑ꡓ䃷棈鉳້\u1cb5\ued9f뇤揃핺憕῟"));
                }

                var1 = var12;
            }

            if (var1 == null) {
                return null;
            }

            x = true;
            return var1;
        }

        var1 = var9.getMessage();
        a(var0, Application.B("쭁⏕眷屚捊銋\uf58b躕鶫㰨͜炀唭⭥\ue512騺汴鳹誕滕픐鸹벻\ue7e7阳ሿ"), var1);
        return null;
    }

    private static void a(Context var0, String var1, String var2) {
        StringBuffer var3;
        StringBuffer var10001 = var3 = new StringBuffer();
        var3.append(TSLog.header(var1 + Application.B("쬷⎼") + var0.getPackageName()));
        var10001.append(TSLog.boxRow(var2));
        if (b(var0)) {
            var3.append(TSLog.boxRow(Application.B("쭏⏽眗屴捣銪\uf5a1軀鶓㰍͗炬唆⭈\ue529騐汚鳃諜滼픿鹐벞\ue7c1陁ሜ쎆弶抃莮퀡垢俔㘍ꠞ䃯棈鉿ໂ\u1cba\ued80놡揵핿懐ῢ抻分账\uf105⏤ㅕ\ue8c5㙫콙儽铮ꂽ鲩뭄⸓濹᳴\ue255쏬휢嶃㣖⫭㍴峝渆\ue1df¸\u0991萡")));
        }

        var3.append(Application.B("\uee57ی判祏䙔랈킞ꯥ뢭᤹♀喙瀹\u0e74쀖뼣䥫맧꿥䯃\uf001묠馧싢댱㜪\ue6a3稊䞿ꚇ\uf551犔櫱ጳ购旋䷱띀\u2bfc㦋좼铑䛌\uf041䒠㫶"));
        Log.e(Application.B("쭙⏏眸屰捧銹\uf5ba軜鶒㰇͝炨唇⭅\ue521騖汉"), var3.toString());
        b().post(new d(var0, var1, var2));
    }

    private static String a(List<String> var0, String var1) {
        List var10000 = var0;
        StringBuilder var5;
        var5 = new StringBuilder();
        boolean var2 = true;

        String var4;
        for (Iterator var3 = var10000.iterator(); var3.hasNext(); var5.append(var4)) {
            var4 = (String) var3.next();
            if (var2) {
                var2 = false;
            } else {
                var5.append(var1);
            }
        }

        return var5.toString();
    }
}

