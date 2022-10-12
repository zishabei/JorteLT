//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.logger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSEmailLogCallback;
import com.transistorsoft.locationmanager.adapter.callback.TSGetLogCallback;
import com.transistorsoft.locationmanager.data.SQLQuery;
import com.transistorsoft.locationmanager.device.DeviceInfo;
import com.transistorsoft.locationmanager.logger.LoggerFacade.a;
import com.transistorsoft.locationmanager.util.Sensors;
import com.transistorsoft.tslocationmanager.Application;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TSLog {
    public static final String LOG_FILENAME = Application.B("Ὀ鵋骫셬ꆀᱨﭽ\ue305磢ꊱ馸\ue851\u9ff3རᓇ־캜颩㮥\uf7a1茏\udef5冚〶\uf7d3\ue669滣澣\uecc8");
    public static final String ACTION_UPLOAD_LOG = Application.B("Ὗ鵚骤셨ꆆ᱾ﭞ\ue31f磫");
    public static final String ACTION_GET_LOG = Application.B("Ὅ鵏骼셋ꆈᱽ");
    public static final String ACTION_EMAIL_LOG = Application.B("\u1f4f鵇骩셮ꆋ᱖ﭽ\ue317");
    public static final String ACTION_LOG = Application.B("\u1f46鵅骯");
    private static final String LOG_PATTERN = Application.B("ά鴏骫셫ꆆᱩﭡ\ue30b碿ꋥ駨\ue816龳འᓎ֥캗颧㮵\uf795荀\udebe凙〩\uf7db");
    private static final String DB_APPENDER_NAME = Application.B("Ὑ鵛骤셮ꆓ᱿");
    private static final String DB_APPENDER_MAX_HISTORY = Application.B("Ἕ鴊骬셦ꆞᱩ");
    private static final String LOG_LEVEL_DEBUG = Application.B("\u1f4e鵏骪셲ꆀ");
    private static final String LOG_LEVEL_WARN = Application.B("Ὕ鵋骺셩");
    private static final String LOG_LEVEL_ERROR = Application.B("\u1f4f鵘骺셨ꆕ");
    private static final String LOG_LEVEL_INFO = Application.B("ὃ鵄骮셨");
    private static final String LOG_LEVEL_NOTICE = Application.B("ὄ鵅骼셮ꆄ᱿");
    private static final String LOG_LEVEL_HEADER = Application.B("ὂ鵏骩셣ꆂᱨ");
    private static final String LOG_LEVEL_ON = Application.B("ὅ鵄");
    private static final String LOG_LEVEL_OFF = Application.B("ὅ鵌骮");
    private static final String LOG_LEVEL_OK = Application.B("ὅ鵁");
    public static Logger logger = new LoggerFacade();
    public static final String BOX_HEADER_TOP = Application.B("㩾롺뾘\ue457蒷㥊\ude42옠巜螅볅쵦뫆⩝ㇻ₁\uebaf붘ẁ튘\ua630\ufbcb瓤ᔊ틬썞䮝䪔짢濘㗀썡ﻌቤ\ue87a螲\uf71b␤ᤫ\ua4c8촓禘ꉬࠅ싗\uf158");
    public static final String BOX_HEADER_BOTTOM = Application.B("㩊롺뾘\ue457蒷㥊\ude42옠巜螅볅쵦뫆⩝ㇻ₁\uebaf붘ẁ튘\ua630\ufbcb瓤ᔊ틬썞䮝䪔짢濘㗀썡ﻌቤ\ue87a螲\uf71b␤ᤫ\ua4c8촓禘ꉬࠅ싗\uf158");
    public static final String BOX_BOTTOM = Application.B("㩰롺뾘\ue457蒷㥊\ude42옠巜螅볅쵦뫆⩝ㇻ₁\uebaf붘ẁ튘\ua630\ufbcb瓤ᔊ틬썞䮝䪔짢濘㗀썡ﻌቤ\ue87a螲\uf71b␤ᤫ\ua4c8촓禘ꉬࠅ싗\uf158");
    public static final String TREE_SW = Application.B("㨾");
    public static final String BOX_HEADER_MIDDLE = Application.B("㩻");
    public static final String HR = Application.B("㩾롺뾘\ue457蒷㥊\ude42옠巜螅볅쵦뫆⩝ㇻ₁\uebaf붘ẁ튘\ua630\ufbcb瓤ᔊ틬썞䮝䪔짢濘㗀썡ﻌቤ\ue87a螲\uf71b␤ᤫ\ua4c8촓禘ꉬࠅ싗\uf158꾏");
    public static final String BOX_ROW = Application.B("㩵렪髨");
    public static final String CRLF = Application.B("ἠ");
    public static final String TAB = Application.B("Ἂ鴊");
    public static final String ICON_CHECK = Application.B("㠯鴊髨");
    public static final String ICON_WARN = Application.B("㦊挥髨섧");
    public static final String ICON_ERROR = Application.B("㼖挥髨섧");
    public static final String ICON_ALARM = Application.B("㳚鴊");
    public static final String ICON_INFO = Application.B("㸓挥髨섧");
    public static final String ICON_HOURGLASS = Application.B("㳙");
    public static final String ICON_CANCEL = Application.B("㡦鴊");
    public static final String ICON_SIGNAL_BARS = Application.B("윗䇜髨섧");
    public static final String ICON_ACTIVITY = Application.B("윗䎲髨㼈");
    public static final String ICON_OFF = Application.B("윗䀞髨섧");
    public static final String ICON_ON = Application.B("윖䊔髨섧");
    public static final String ICON_NOTICE = Application.B("윗䀟髨섧");
    public static final String ICON_PIN = Application.B("윗䇧髨섧");
    public static final String ICON_CALENDAR = Application.B("윗䇯髨섧");

    public TSLog() {
    }

    public static String box(String var0) {
        return Application.B("\uf565\ud885᱑徂筙냩浣\udb60\udbf9骓胛Ᏻ䫼ý덶錴ꨙ㫈\ud888춾㙸ᛃ梥欧멂횘\ue8e8먟ⓙ狶ᷫ\u0b65\ue325셳ꏀ䒠샡蜰晽᳃\uf891놣\u9ffe\ue335菚鵛乽എ⺋") + var0 + Application.B("퀻") + Application.B("\uf551\ud885᱑徂筙냩浣\udb60\udbf9骓胛Ᏻ䫼ý덶錴ꨙ㫈\ud888춾㙸ᛃ梥欧멂횘\ue8e8먟ⓙ狶ᷫ\u0b65\ue325셳ꏀ䒠샡蜰晽᳃\uf891놣\u9ffe\ue335菚鵛") + Application.B("퀻");
    }

    public static String boxRow(String var0) {
        return Application.B("銇鬜䭽") + var0 + Application.B("럒");
    }

    public static String header(String var0) {
        return Application.B("\ue17c") + box(var0);
    }

    public static String notice(String var0) {
        return Application.B("불裶㛈琴璣\ued8a䂤") + var0;
    }

    public static String warn(String var0) {
        return Application.B("ꩾ\ue820ᆀ䲧釳\ue610詠") + var0;
    }

    public static String error(String var0) {
        return Application.B("꜋징쏳㱹靓㿠鶳") + var0;
    }

    public static String info(String var0) {
        return Application.B("龱꽇Њ됕愘䌤᪖") + var0;
    }

    public static String on(String var0) {
        return Application.B("舞˿\udb17ှ奔\u2bda啢") + var0;
    }

    public static String off(String var0) {
        return Application.B("怴蹒ᚢ\u1cc8⫰荠큮") + var0;
    }

    public static String ok(String var0) {
        return Application.B("槔灩填埦퓮驰") + var0;
    }

    public static String pin(String var0) {
        return Application.B("柾읳縮쯒ᔼᶈˢ") + var0;
    }

    public static String calendar(String var0) {
        return Application.B("鼝͏鍵稚墬胹玢") + var0;
    }

    public static String alarm(String var0) {
        return Application.B("ᄊ\ued6e洛뤣鹸") + var0;
    }

    public static String activity(String var0) {
        return Application.B("㘷쯜肄\ue6a6\uecf7ㅽ\ue009") + var0;
    }

    public static String connectivity(String var0) {
        return Application.B("輌⪷寉\uea12横贬\uffc1") + var0;
    }

    public static String cancel(String var0) {
        return Application.B("늯岯\uefa7絼뿂") + var0;
    }

    public static void initialize(final int var0, final int var1) {
        if (logger.getClass() == LoggerFacade.class) {
            BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                public void run() {
                    ch.qos.logback.classic.Logger var1x;
                    ch.qos.logback.classic.Logger var10000 = var1x = TSLog.getRootLogger();
                    var10000.setLevel(TSLog.decodeLogLevel(var0));
                    LoggerContext var2;
                    (var2 = (LoggerContext) LoggerFactory.getILoggerFactory()).reset();
                    PatternLayoutEncoder var3;
                    PatternLayoutEncoder var10001 = var3 = new PatternLayoutEncoder();
                    var10001.setContext(var2);
                    var10001.setPattern(Application.B("\uedb4넧ꐋἿ᷻蒊\uf231緬i쨘ꭈ塪뀖国\ufe1dЅ㺓愯䣲œ䭀쀸ꯖḲ\u2e52"));
                    var10001.start();
                    LogcatAppender var4;
                    LogcatAppender var9 = var4 = new LogcatAppender();
                    var4.setContext(var2);
                    var9.setEncoder(var3);
                    var9.start();
                    var10000.addAppender(var9);
                    TSSQLiteAppender var6;
                    TSSQLiteAppender var7 = var6 = new TSSQLiteAppender();
                    var6.setName(Application.B("\ued9c녳ꐄἺᷮ蒜"));
                    var6.setMaxHistory(var1 + Application.B("\uedcf녦ꐉἪᷩ"));
                    var6.setContext(var2);
                    var1x.addAppender(var6);
                    var7.start();
                    List var8 = ((LoggerFacade) TSLog.logger).getQueue();
                    TSLog.logger = LoggerFactory.getLogger(Application.B("\uedbb녑ꐤἼ᷹蒘\uf236緾5쩆ꭸ堫끝囱\ufe1fД㺉"));
                    TSLog.setLogLevel(var0);
                    TSLog.setMaxHistory(var1);
                    Iterator var5 = var8.iterator();

                    while (var5.hasNext()) {
                        ((a) var5.next()).a(TSLog.logger);
                    }

                }
            });
        }
    }

    public static void setLogLevel(final int var0) {
        if (logger.getClass() != LoggerFacade.class) {
            BackgroundGeolocation.getThreadPool().execute(new Runnable() {
                public void run() {
                    TSLog.getRootLogger().setLevel(TSLog.decodeLogLevel(var0));
                }
            });
        }
    }

    public static void setMaxHistory(final int var0) {
        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
            public void run() {
                TSLog.getDatabaseAppender().setMaxHistory(var0 + Application.B("\u2d7dꑮ\udd54䡵㶕"));
            }
        });
    }

    public static TSSQLiteAppender getDatabaseAppender() {
        return (TSSQLiteAppender) getRootLogger().getAppender(Application.B("裡纥쓆채承㭅"));
    }

    public static void getLog(TSGetLogCallback var0) {
        getLog(SQLQuery.create(), var0);
    }

    public static void getLog(SQLQuery var0, TSGetLogCallback var1) {
        (new TSLog.e(var0, var1)).execute(new Void[0]);
    }

    public static void destroyLog(TSCallback var0) {
        (new TSLog.DestroyLogTask(var0)).execute(new Void[0]);
    }

    public static void emailLog(String var0, Activity var1, TSEmailLogCallback var2) {
        Activity var10002 = var1;
        SQLQuery var3;
        var3 = new SQLQuery();
        (new TSLog.d(var10002, var0, var3, var2)).execute(new Void[0]);
    }

    public static void emailLog(Activity var0, String var1, SQLQuery var2, TSEmailLogCallback var3) {
        (new TSLog.d(var0, var1, var2, var3)).execute(new Void[0]);
    }

    public static void uploadLog(Context var0, String var1, SQLQuery var2, TSCallback var3) {
        new com.transistorsoft.locationmanager.logger.a(var0, var1, var2, var3);
    }

    public static File writeLogFile(Context var0, String var1) {
        File var2;
        File var10000 = var2 = new File(var0.getCacheDir(), Application.B("ឹ䄋꒖\uf026翖\u0cdd\ua83eు틂ꃒ瘻춣ઉ째ꅭ\ue236센戬혻矆\ue181ꬁ鞜圭㓹綑˭璆㼝"));

        FileNotFoundException var14;
        label54:
        {
            FileOutputStream var16;
            boolean var10001;
            FileOutputStream var10002;
            try {
                var16 = new FileOutputStream(var2);
                var10002 = var16;
            } catch (FileNotFoundException var8) {
                var14 = var8;
                var10001 = false;
                break label54;
            }

            IOException var15;
            label55:
            {
                try {
                    ByteArrayOutputStream var10003;
                    var10003 = new ByteArrayOutputStream(var1.length());

                    ByteArrayOutputStream var10004 = var10003;
                    ByteArrayOutputStream var10;
                    ByteArrayOutputStream var10005 = var10 = var10003;

                    GZIPOutputStream var9;
                    var9 = new GZIPOutputStream(var10);

                    GZIPOutputStream var10006 = var9;
                    GZIPOutputStream var10007 = var9;

                    byte[] var17;
                    var10006.write(var1.getBytes());
                    var9.close();
                    var17 = var10004.toByteArray();

                    byte[] var11 = var17;


                    var10003.close();
                    var10002.write(var11);
                    var16.close();
                    return var10000;
                } catch (IOException var4) {
                    var15 = var4;
                    var10001 = false;
                }
            }

            IOException var12 = var15;

            logger.error(error(var12.getMessage()), var12);
            var12.printStackTrace();
            return null;
        }

        FileNotFoundException var13 = var14;
        logger.error(error(var13.getMessage()), var13);
        return null;
    }

    private static String decodeMaxHistory(int var0) {
        return var0 + Application.B("\ue4c5㒜詊爮\uf41e");
    }

    private static Level decodeLogLevel(int var0) {
        int var10000 = var0;
        Level var1 = Level.ALL;
        switch (var10000) {
            case 0:
                var1 = Level.OFF;
                break;
            case 1:
                var1 = Level.ERROR;
                break;
            case 2:
                var1 = Level.WARN;
                break;
            case 3:
                var1 = Level.INFO;
                break;
            case 4:
                var1 = Level.DEBUG;
            case 5:
        }

        return var1;
    }

    private static ch.qos.logback.classic.Logger getRootLogger() {
        return (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Application.B("㯟ﻼ✔\ue099"));
    }

    public static File getDatabaseFile() {
        TSSQLiteAppender var0;
        if ((var0 = getDatabaseAppender()) == null) {
            logger.error(error(Application.B("\ued86鎯\u1af1ᆑ胀\uea6e퇦耭\uf6e8줄Ⱝ먒碡䢂捣䚎ߧ癰쩄茬ᔼ铃綾䝣阋ꢴ훃Ș燰褢钀礝狧\uf58d쟬ᔴ囼蓀ꂔ毈萀")));
            return null;
        } else {
            return var0.getDatabaseFile(var0.getFilename());
        }
    }

    public static void log(String var0, String var1) {
        if (Application.B("⪨朴\uf328櫆秧").equalsIgnoreCase(var0)) {
            logger.error(error(var1));
        } else if (Application.B("⪺朧\uf328櫇").equalsIgnoreCase(var0)) {
            logger.warn(warn(var1));
        } else if (Application.B("⪩朣\uf338櫜秲").equalsIgnoreCase(var0)) {
            logger.debug(var1);
        } else if (Application.B("⪤木\uf33c櫆").equalsIgnoreCase(var0)) {
            logger.info(info(var1));
        } else if (Application.B("⪣朩\uf32e櫀秶\udd98").equalsIgnoreCase(var0)) {
            logger.info(notice(var1));
        } else if (Application.B("⪥朣\uf33b櫍称\udd8f").equalsIgnoreCase(var0)) {
            logger.info(header(var1));
        } else if (Application.B("⪢木").equalsIgnoreCase(var0)) {
            logger.info(on(var1));
        } else if (Application.B("⪢朠\uf33c").equalsIgnoreCase(var0)) {
            logger.info(off(var1));
        } else if (Application.B("⪢札").equalsIgnoreCase(var0)) {
            logger.info(ok(var1));
        }

    }

    private static class d extends AsyncTask<Void, Void, Intent> {
        private static final String e = Application.B("\uf0eb\uead7䖮\ueab0蜙Ԥ⩢鹒\ue8a3\u0ece⺶₸狗캒낈陲㢋᠇𤋮㾲\ue4e6\udb93滕侵❝");
        private static final String f = Application.B("\uf0c4\uead3䖾\ueaa8蜟Ա⩨鸈\ue8bf໌⺒⃥犊컌");
        private final String a;
        private final WeakReference<Activity> b;
        private final TSEmailLogCallback c;
        private final SQLQuery d;

        public d(Activity var1, String var2, @Nullable SQLQuery var3, TSEmailLogCallback var4) {
            super();
            this.a = var2;
            WeakReference var5;
            var5 = new WeakReference(var1);
            b = var5;
            c = var4;
            d = var3;
        }

        @Override
        protected Intent doInBackground(Void... var1) {
            Activity var7;
            if ((var7 = (Activity) this.b.get()) == null) {
                this.c.onFailure(Application.B("\uf77eட쳹緰毑壉瞀颤쌾䣕\ue54f밋潕鄹⃣ᵌ蕙ᯄ델\ue595\uefcd쿳桼벾ነ㫊ꝭ㸱嘊\ueb68\u07b2\uf5ef\uaafc憅\ue89a缵㇡⤗\u208f厼옊텊區᧘隱\uf039\uf043쥑ﶤ鋃楧\ue233㑇血\ue2cb䕧短ɠ㩝\u0e3e鲣尊鷲牺旍ꩮ蘼童\udd5c괄\u0af6璴猪흧華败Ꭳ뵥₋ᜉ鸁泼\udc1a镺읿桛士䅕\u0dd5㫼\ud89eრ뾝⊂\uab66뵵\udd58\udb73"));
                return null;
            } else {
                Context var8 = var7.getApplicationContext();
                String var2;
                if ((var2 = TSLogReader.getLog(this.d)) == null) {
                    this.c.onFailure(Application.B("\uf77eட쳹緰毑壉瞀颤쌾䣕\ue55b밋潀鄾₫ᴀ蕷ᯀ댬\ue598\uefda쿮桩벥ዑ㫟ꝺ㹰"));
                    return null;
                } else {
                    Intent var3;
                    Intent var10001 = var3 = new Intent(Application.B("\uf759ஐ쳴緮毛壄矄飾쌸䢛\ue55d밋潏鄮₥ᴍ蕻ᯓ덥\ue593\uefd5쾴桛벂ዾ㫨"));
                    var10001.setType(Application.B("\uf755\u0b9b쳣緯毕壊矅飿쌣䢓\ue54a뱖漓酨"));
                    String[] var4;
                    (var4 = new String[1])[0] = this.a;
                    var10001.putExtra(Application.B("\uf759ஐ쳴緮毛壄矄飾쌸䢛\ue55d밋潏鄮₥ᴉ蕠ᯓ덾\ue59d\uef95쿟桅벆ዹ㫠"), var4);
                    var10001.putExtra(Application.B("\uf759ஐ쳴緮毛壄矄飾쌸䢛\ue55d밋潏鄮₥ᴉ蕠ᯓ덾\ue59d\uef95쿉桝벅ዺ㫩Ꝝ㸊"), Application.B("\uf77aட쳳緷毓壟矏颥쌿䢑\ue56e밋潎鄶⃤ᴏ蕹ᯓ덥\ue593\uefd5쾺桤벨\u12d7"));
                    TSConfig var10 = TSConfig.getInstance(var8);
                    StringBuilder var5;
                    StringBuilder var10000 = var5 = new StringBuilder();
                    TSConfig var11 = var10;
                    var5.append(TSLog.header(Application.B("\uf76c\u0bad쳜緳毗壌矔颹쌾䢛\ue564및潏鄻⃬ᴉ蕪ᮇ덺\ue599\uefc9쿩桡벨ዞ㪖ꜿ㹭噉\ueb7aߋ\uf5bbꪽ懆\ue8fc罠ㆰ⥛")));
                    var5.append(TSLog.boxRow(DeviceInfo.getInstance(var8).print()));

                    try {
                        var10000.append(var11.toJson(true).toString(2));
                    } catch (JSONException var6) {
                        TSLog.logger.error(TSLog.error(Application.B("\uf77eட쳹緰毑壉瞀颤쌾䣕\ue55e발潈鄮⃮ᵌ蕫ᯓ덭\ue588\uefde쾺桼벨ነ㫉ꝲ㸿嘎\ueb24߅\uf5e8ꫲ憊\ue8b1罪ㆧ") + var6.getMessage()), var6);
                    }

                    var5.append(Sensors.getInstance(var8).print().toString());
                    var3.putExtra(Application.B("\uf759ஐ쳴緮毛壄矄飾쌸䢛\ue55d밋潏鄮₥ᴉ蕠ᯓ덾\ue59d\uef95쿎桍벟ዤ"), var5.toString());
                    File var9;
                    if ((var9 = TSLog.writeLogFile(var8, var2)) == null) {
                        this.c.onFailure(Application.B("\uf77eட쳹緰毑壉瞀颤쌾䣕\ue55e발潈鄮⃮ᵌ蕴ᯈ덫\ue5dc\uefdd쿳桤벢"));
                        return null;
                    } else {
                        var3.putExtra(Application.B("\uf759ஐ쳴緮毛壄矄飾쌸䢛\ue55d밋潏鄮₥ᴉ蕠ᯓ덾\ue59d\uef95쿉桜법ድ㫭Ꝓ"), FileProvider.getUriForFile(var8, var8.getPackageName() + Application.B("\uf716ஊ쳣緰毛壎矁颤쌸䢚\ue547밃潀鄴⃪ᴋ蕽ᯕ댢\ue59a\uefd2쿶桭벷ዂ㫃ꝩ㸷嘃\ueb2dޗ"), var9));
                        var9.deleteOnExit();
                        var3.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        var3.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        return var3;
                    }
                }
            }
        }

        protected void a(Intent var1) {
            if (var1.hasExtra(Application.B("\uf75d\u0b8c쳢緳毆"))) {
                this.c.onFailure(var1.getStringExtra(Application.B("\uf75d\u0b8c쳢緳毆")));
            } else {
                Activity var2;
                if ((var2 = (Activity) this.b.get()) == null) {
                    TSLog.logger.error(TSLog.error(Application.B("\uf77eட쳹緰毑壉瞀颤쌾䣕\ue54e밋潕酺\u20caᴏ蕬ᯎ덺\ue595\uefcf쿣栨벡ዂ㫃ꝲ㹾嘰\ueb2dބ\uf5e1\uaacf憋\ue8ae缵ㇵ⤗ₓ厺영턇包᧖雘\uf06d\uf02a쥖\ufde1鋕楯\ue225㐊衇\ue283䕶瞥ɤ㨍ฯ鲿尓鷽牲旚ꩦ蘧竢\udd13괚ત璫缾흭菿贳Ᏸ뵾\u20caᜒ鹀泭\udc5f镽이栞壱䄐ස㫴\ud884ჺ뾎⊙ꭺ뵴\udd12")));
                    this.c.onFailure(Application.B("\uf76dர쳛緒毻壺矮颏쌔䢧\ue57b밡潳"));
                } else {
                    var2.startActivityForResult(Intent.createChooser(var1, Application.B("\uf76b\u0b9b쳾緸殔壁矏颷썫䣕") + this.a + Application.B("\uf716ௐ첾")), 1);
                    this.c.onSuccess();
                }
            }
        }
    }

    private static class e extends AsyncTask<Void, Void, String> {
        private final TSGetLogCallback a;
        private final SQLQuery b;

        e(SQLQuery var1, TSGetLogCallback var2) {
            this.b = var1;
            this.a = var2;
        }

        protected void a(String var1) {
            if (var1 == null) {
                this.a.onFailure(Application.B("뺀\uf51f\ue617ᗒㅑ뒢琲蒶潡⊏傉瑋㶾뷢염膓㻉윲냣✉"));
            } else {
                this.a.onSuccess(var1);
            }

        }

        @Override
        protected String doInBackground(Void... voids) {
            return TSLogReader.getLog(this.b);
        }
    }

    public static class DestroyLogTask extends AsyncTask<Void, Void, Boolean> {
        private final TSCallback mCallback;

        DestroyLogTask(TSCallback var1) {
            this.mCallback = var1;
        }

        protected Boolean doInBackground(Void... var1) {
            return TSLogReader.destroy();
        }

        protected void onPostExecute(Boolean var1) {
            if (var1) {
                this.mCallback.onSuccess();
            } else {
                this.mCallback.onFailure(Application.B("㷘꧌몝螞\ue0ab탾⦏礳⪍澵盿⺉\uec27ꔁ㡈튌倞ą\uf7edꖀ쁮"));
            }

        }
    }
}
