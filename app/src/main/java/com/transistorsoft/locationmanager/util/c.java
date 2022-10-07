//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.core.content.ContextCompat;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.PermissionManager.PermissionRequestListener;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.config.TSBackgroundPermissionRationale;
import com.transistorsoft.locationmanager.config.TSBackgroundPermissionRationale.CompletionHandler;
import com.transistorsoft.locationmanager.d.b;
import com.transistorsoft.locationmanager.event.LocationProviderChangeEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.greenrobot.eventbus.EventBus;

public class c {
    public static final String a = Application.B("툉粲ﰤ\udd39\u0bffﮃ꠲庙\uf352녢\uddaa閳욚ꄝ꺠\u2d99筳䄪↧Ⓩ齹");
    public static final String b = Application.B("툕粸ﰵ\udd35\u0be2ﮙ\ua82e庞\uf37c녹\uddfe閼욇ꄎ꺧\u2d97筷䄺");
    public static final String c = Application.B("툕粸ﰵ\udd35\u0be2ﮙ\ua82e庞\uf37c녹\uddfe閿욐ꄁ꺠ⶆ筶");
    public static final String d = Application.B("툗粸ﰶ\udd2d௮ﮙ꠩庞\uf37d녰\uddfe閫욐ꄝ꺤ⶊ筡䄭↧Ⓩ齹");
    public static final String e = Application.B("툗粸ﰶ\udd2d௮ﮙ꠩庞\uf37d녰\uddfe閙요ꄌ꺢ⶄ筠䄱↻Ⓨ齳穘뚺\ued54坎꛳γ괎汮靗은\u0a7d");
    public static final String f = Application.B("툄粱ﰰ\udd39௲ﮙ");
    public static final String g = Application.B("툒粵ﰢ\udd36ூﮄꠈ庄\uf376");
    public static final String h = Application.B("툄粳ﰾ");
    private static final List<PermissionRequestListener> i = new ArrayList();
    private static final AtomicInteger j = new AtomicInteger(-1);
    private static final AtomicBoolean k = new AtomicBoolean(false);

    public c() {
    }

    public static boolean c(Context var0) {
        return ContextCompat.checkSelfPermission(var0, Application.B("ꐲ쌚\ueb19⢃躷⺝緂\ud87c≬㞽麢䯀斕貎⸈\u17de洝ꂆ\uef3e㼀꾣헚\u1ae0邽Ấ裏ꆗ蘙ཱྀٙ䵗ᮐ搯웷覮葶職쎤骔")) == 0 || ContextCompat.checkSelfPermission(var0, Application.B("ꐲ쌚\ueb19⢃躷⺝緂\ud87c≬㞽麢䯀斕貎⸈\u17de洝ꂆ\uef3e㼀꾣헚\u1ae0邽Ấ裏ꆒ蘟ٖྖ䵛ᮙ搿웸覠葡聿쎿骓ᏽ䭜")) == 0;
    }

    public static boolean a(Context var0) {
        boolean var1 = true;
        boolean var2;
        if (VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(var0, Application.B("麖‰ⲵ࠽䈦ݵ\udd85疧ኄഎ糧붂䘼㦲ᆝٖ츌ᚌ믦삚⽗\u1bfaጻ胜\ue1d9嚰熾뫽ഡ냯薵\uf106퐈䯀ᝒ轁聘鋘︿")) == 0) {
                var2 = true;
            } else {
                var2 = false;
            }
        } else {
            var2 = var1;
        }

        return var2;
    }

    public static boolean b(Context var0) {
        boolean var1 = true;
        boolean var2;
        if (VERSION.SDK_INT >= 29) {
            if (ContextCompat.checkSelfPermission(var0, Application.B("䌷䧳䍅焂\uead5\u0cd0Ժ箅\uf666ု\u187e현ͳ뀭憙遹厑\u2e4e磗\uf051샀\ue5e1杣\uf78b㔏ᭇ钖繒禙秚\uf229週ᤁ黒觐\u09b5䩇ｪ鍍ꂫល镗犐㵖珍")) == 0) {
                var2 = true;
            } else {
                var2 = false;
            }
        } else {
            var2 = var1;
        }

        return var2;
    }

    public static void g(Context var0, final PermissionRequestListener var1) {
        if (!com.transistorsoft.locationmanager.d.b.e(var0)) {
            final DeniedPermissions var4;
            var4 = new DeniedPermissions.<init>();
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    var1.onPermissionDenied(var4);
                }
            });
        } else {
            TSConfig var2 = TSConfig.getInstance(var0);
            if (!c(var0) || !b(var0) && a(var2.getLocationAuthorizationRequest()) || !a(var0) && !var2.getDisableMotionActivityUpdates()) {
                TSLog.logger.info(TSLog.notice(Application.B("苫ꖬ帤\ue741삭绨\uf0e3\uab17\uf7cf䝸\ued44\u088c꧗蒘娬〻徶㉭쁶춀⮎箵식蟫岗烡羚沷᧿ \u2e796䥭㠡ꐯ舱\uef46罿齃ὁ잳\uec9a斨轚扖옌\uefb1覅旨\uf46f貘遙룓궶릤")));
                ArrayList var3;
                ArrayList var10000 = var3 = new ArrayList;
                var10000.<init>();
                var10000.add(Application.B("苆ꖭ帣\ue752삶绨\uf0e8ꭗ\uf7fe䝨\ued42\u0889꧑蒙娶〨徸㉷쀱춮⮣篌슸蟪岡烏羬沝\u19cd†⹃\u001d䥕㡍ꐢ舓\uef64罀\ufaf6ὼ잒"));
                var10000.add(Application.B("苆ꖭ帣\ue752삶绨\uf0e8ꭗ\uf7fe䝨\ued42\u0889꧑蒙娶〨徸㉷쀱춮⮣篌슸蟪岡烏義沛ᧂ‷\u2e4f\u0014䥅㡂ꐬ舄\uef6c罛\ufaf1"));
                if (VERSION.SDK_INT >= 29) {
                    if (!var2.getDisableMotionActivityUpdates()) {
                        var3.add(Application.B("苆ꖭ帣\ue752삶绨\uf0e8ꭗ\uf7fe䝨\ued42\u0889꧑蒙娶〨徸㉷쀱춮⮣篛슴蟯岻烄羶沍᧞‷\u2e53\u0017䥍㡏ꐤ舄\uef6c罛\ufaf1"));
                    }

                    if (c(var2.getLocationAuthorizationRequest())) {
                        if (VERSION.SDK_INT >= 30) {
                            e(var0, var1);
                            return;
                        }

                        var3.add(Application.B("苆ꖭ帣\ue752삶绨\uf0e8ꭗ\uf7fe䝨\ued42\u0889꧑蒙娶〨徸㉷쀱춮⮣篌슸蟪岡烏羭沓\u19cf‹\u2e57\n䥅㡔ꐣ舔\uef7a罘\ufaf0ὰ잝\uecbb斏轱戸"));
                    }
                }

                a(var0, var3, var1);
            } else {
                TSLog.logger.debug(TSLog.info(Application.B("苫ꖬ帤\ue741삭绨\uf0e3\uab17\uf7cf䝸\ued44\u088c꧗蒘娬〻徶㉭쁶춀⮎箵식蟩岗烢羂治᧿ \u2e797䥤㠡ꐊ舢\uef44罺頋ὖ잸")));
                BackgroundGeolocation.getUiHandler().post(new Runnable() {
                    public void run() {
                        var1.onPermissionGranted();
                    }
                });
            }
        }
    }

    @TargetApi(30)
    private static void e(Context var0, PermissionRequestListener var1) {
        TSConfig var2 = TSConfig.getInstance(var0);
        TSConfig var10000;
        if (c(var0) && (a(var0) || var2.getDisableMotionActivityUpdates() || k.get())) {
            var10000 = var2;
            (new ArrayList()).add(Application.B("汦퍰શ撯ㅜ⩳ᢺ藐睓↸뮭캀쐥췙ﲀ\uda2e玔뼑섨װ跴\uef51㴌蓸梩㯁渚ⷼᎨ닔ȫ泩嶵\uf6a9롸\ud7ca튲\ud87a\uf272㩵ൿᕛﾲ\uf682Ꭿ"));
            Activity var6 = BackgroundGeolocation.getInstance(var0).getActivity();
            boolean var4;
            TSBackgroundPermissionRationale var7;
            boolean var9 = var4 = (var7 = var10000.getBackgroundPermissionRationale()).shouldShow(var6);
            TSLog.logger.info(TSLog.notice(Application.B("汔퍶ઽ撨ㅟ⩾\u18fe薍睋↲뮨컍쐮췋ﲐ\uda2c玜뼍셩ׄ跙\uef76㴙蓎梈㯳舘ⷎ᎘닶ȃ法嶨\uf69d롂ퟧ튂\ud858\uf25c㩚൛ᔰￛ") + var4));
            if (!var9) {
                if (b(var0)) {
                    var1.onPermissionGranted();
                } else {
                    d(var0, var1);
                }

                return;
            }

            TSBackgroundPermissionRationale var10 = var7;
            CompletionHandler var8;
            var8 = new CompletionHandler() {
                {
                    this.b = var2;
                }

                public void onClickOk() {
                    com.transistorsoft.locationmanager.util.c.d(c.this, this.b);
                }

                public void onClickCancel() {
                    if (com.transistorsoft.locationmanager.util.c.c((Context)c.this)) {
                        this.b.onPermissionGranted();
                    } else {
                        <undefinedtype> var10000 = this;
                        DeniedPermissions var1;
                        var1 = new DeniedPermissions.<init>();
                        var10000.b.onPermissionDenied(var1);
                    }

                }
            }.<init>(var1);
            var10.show(var6, var8);
        } else {
            var10000 = var2;
            ArrayList var5;
            ArrayList var10001 = var5 = new ArrayList;
            var10001.<init>();
            var10001.add(Application.B("汦퍰શ撯ㅜ⩳ᢺ藐睓↸뮭캀쐥췙ﲀ\uda2e玔뼑섨װ跴\uef51㴌蓸梩㯁漢ⷲᎪ닍ȿ泾嶥\uf6b0롹ퟍ튬\ud862\uf274㩹൰"));
            var10001.add(Application.B("汦퍰શ撯ㅜ⩳ᢺ藐睓↸뮭캀쐥췙ﲀ\uda2e玔뼑섨װ跴\uef51㴌蓸梩㯁既ⷴᎥ닚ȳ泷嶵\uf6bf롷ퟚ튤\ud879\uf273"));
            if (!var10000.getDisableMotionActivityUpdates()) {
                k.set(true);
                var5.add(Application.B("汦퍰શ撯ㅜ⩳ᢺ藐睓↸뮭캀쐥췙ﲀ\uda2e玔뼑섨װ跴\uef46㴀蓽梳㯊艹ⷢᎹ닚ȯ泴嶽\uf6b2롿ퟚ튤\ud879\uf273"));
            }

            PermissionRequestListener var3;
            var3 = new PermissionRequestListener() {
                {
                    this.b = var2;
                }

                public void onPermissionGranted() {
                    if (com.transistorsoft.locationmanager.util.c.b((Context)c.this)) {
                        this.b.onPermissionGranted();
                    } else {
                        BackgroundGeolocation.getUiHandler().post(new Runnable() {
                            public void run() {
                                <undefinedtype> var1;
                                com.transistorsoft.locationmanager.util.c.e(c.this, var1.b);
                            }
                        });
                    }
                }

                public void onPermissionDenied(DeniedPermissions var1) {
                    this.b.onPermissionDenied(var1);
                }
            }.<init>(var1);
            a(var0, var5, var3);
        }

    }

    public static void h(Context var0, final PermissionRequestListener var1) {
        if (!com.transistorsoft.locationmanager.d.b.e(var0)) {
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    <undefinedtype> var10000 = this;
                    DeniedPermissions var1x;
                    var1x = new DeniedPermissions.<init>();
                    var1.onPermissionDenied(var1x);
                }
            });
        } else if (c(var0) && b(var0)) {
            TSLog.logger.debug(TSLog.info(Application.B("ꋸꨒ薜빽\uf1d4⼧垥\uda12ୌ᠗\u08c0鵒ፂ\ue4a7ỏ㻢뭄幉ꁫ窍⦤川\uf71eﴻ柎嵳Ã旎䳅ꂻ\ue83c몵晹扖䉒诩⮒孶矱⢞신")));
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    var1.onPermissionGranted();
                }
            });
        } else {
            TSLog.logger.info(TSLog.notice(Application.B("ꋸꨒ薜빽\uf1d4⼧垥\uda12ୌ᠗\u08c0鵒ፂ\ue4a7ỏ㻢뭄幉ꁫ窍⦤川\uf71eﴹ柎嵰Û旂䳅ꂼ\ue83c몴晰扖䉅课⮁孵矬⢈싷⧑暝㝀")));
            ArrayList var10001 = new ArrayList();
            var10001.add(Application.B("ꋕꨓ薛빮\uf1cf⼧垮\uda52\u0b7d᠇\u08c6鵗ፄ\ue4a6ổ㻱뭊幓ꀬ窣⦉嶤\uf77bﴸ柸嵞í旨䳷ꂚ\ue806몟晈戺䉺诘⮲孌矌⢴싊"));
            var10001.add(Application.B("ꋕꨓ薛빮\uf1cf⼧垮\uda52\u0b7d᠇\u08c6鵗ፄ\ue4a6ổ㻱뭊幓ꀬ窣⦉嶤\uf77bﴸ柸嵞è旮䳸ꂍ\ue80a몖晘戵䉴诏\u2bba字矋"));
            a(var0, var10001, var1);
        }
    }

    public static void f(final Context var0, final PermissionRequestListener var1) {
        if (ContextCompat.checkSelfPermission(var0, Application.B("须\ue229廬㠛㇅殘◒\ued94ꀗ䎐翷剨茓펡皕檌蜤襎蠩伖\ud957ﮣ嫵\udd9fŎ칹㰀瑨荢ꠥ郐凑⬙걅왲䦴珖捦莦")) == 0) {
            var1.onPermissionGranted();
        } else {
            final ArrayList var2;
            ArrayList var10000 = var2 = new ArrayList;
            var10000.<init>();
            var10000.add(Application.B("须\ue229廬㠛㇅殘◒\ued94ꀗ䎐翷剨茓펡皕檌蜤襎蠩伖\ud957ﮣ嫵\udd9fŎ칹㰅瑮荭꠲郜凘⬉걊왼䦣珞捽莡㐁\uefe1"));
            var10000.add(Application.B("须\ue229廬㠛㇅殘◒\ued94ꀗ䎐翷剨茓펡皕檌蜤襎蠩伖\ud957ﮣ嫵\udd9fŎ칹㰀瑨荢ꠥ郐凑⬙걅왲䦴珖捦莦"));
            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    PermissionManager var10000 = PermissionManager.getInstance(var0);
                    <undefinedtype> var10001 = this;
                    List var1x = var2;
                    var10000.checkPermissions(var1x, var1);
                }
            });
        }
    }

    private static void a(Context var0, List<String> var1, PermissionRequestListener var2) {
        boolean var3 = false;
        synchronized(i){}

        Throwable var10000;
        boolean var10001;
        try {
            i.add(var2);
        } catch (Throwable var16) {
            var10000 = var16;
            var10001 = false;
            throw var10000;
        }

        int var17;
        try {
            var17 = i.size();
        } catch (Throwable var15) {
            var10000 = var15;
            var10001 = false;
            throw var10000;
        }

        if (var17 == 1) {
            var3 = true;
        }

        boolean var18;
        try {
            var18 = var3;
        } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            throw var10000;
        }

        if (var18) {
            BackgroundGeolocation.getUiHandler().post(new c.j(var0, var1));
        }
    }

    @TargetApi(29)
    private static void d(Context var0, PermissionRequestListener var1) {
        ArrayList var2;
        ArrayList var10001 = var2 = new ArrayList;
        var10001.<init>();
        var10001.add(Application.B("淙歶\uebbd긭횔㼪\uf5d6䚙㘐감烺㺓笄擠\uf47a\ue337柵쀜\u0c3bⳘ闗숄\ue14b➊\ue241責짥⊼\ue5c6夽녤ຝ┭軹ꊅ軬찹\udbf2쑮肌왁婱౫ჯ駊"));
        PermissionRequestListener var3;
        var3 = new PermissionRequestListener() {
            {
                this.b = var2;
            }

            public void onPermissionGranted() {
                if (!com.transistorsoft.locationmanager.util.c.b((Context)c.this) && !com.transistorsoft.locationmanager.util.c.c((Context)c.this)) {
                    <undefinedtype> var10000 = this;
                    DeniedPermissions var1;
                    var1 = new DeniedPermissions.<init>();
                    var10000.b.onPermissionDenied(var1);
                } else {
                    this.b.onPermissionGranted();
                }

            }

            public void onPermissionDenied(DeniedPermissions var1) {
                if (com.transistorsoft.locationmanager.util.c.c((Context)c.this)) {
                    this.b.onPermissionGranted();
                } else {
                    this.b.onPermissionDenied(var1);
                }

            }
        }.<init>(var1);
        a(var0, var2, var3);
    }

    @TargetApi(30)
    public static void c(final Context var0, final PermissionRequestListener var1) {
        final ArrayList var2;
        ArrayList var10000 = var2 = new ArrayList;
        var10000.<init>();
        var10000.add(Application.B("ꐲ쌚\ueb19⢃躷⺝緂\ud87c≬㞽麢䯀斕貎⸈\u17de洝ꂆ\uef3e㼀꾣헍\u1aec邸Ế溺ꆈ蘏مཱྀ䵋ᮓ搧웺覦葶職쎤骔"));
        BackgroundGeolocation.getUiHandler().post(new Runnable() {
            public void run() {
                PermissionManager var10000 = PermissionManager.getInstance(var0);
                <undefinedtype> var10001 = this;
                List var1x = var2;
                var10000.checkPermissions(var1x, var1);
            }
        });
    }

    private static boolean c(String var0) {
        return a(var0) || b(var0);
    }

    private static boolean a(String var0) {
        return var0.equalsIgnoreCase(Application.B("麶′Ⲧ\u082e䈰ݯ"));
    }

    private static boolean d(String var0) {
        return var0.equalsIgnoreCase(Application.B("淯歰\uebbc긱횲㼭\uf5e7䛄㘅"));
    }

    private static boolean b(String var0) {
        return var0.equalsIgnoreCase(Application.B("䌗䧳䍘"));
    }

    public static String d(Context var0) {
        TSConfig var2;
        TSConfig var10000 = var2 = TSConfig.getInstance(var0);
        String var1 = TSLog.header(Application.B("䬘锗\uebf9깿횹㼢\uf5d1䛜㘇갇烧㺋笃擷\uf429\ue332柵쀑\u0c74Ⳮ闽숨\ue160⟴\ue267貃짃⊜\ue5f1夓념\u0eef┄軃ꊹ車찏\udbda쑅肪왮娅ఊჷ駬罽棫キ꒱\ue2c8⊛\ue91d脶"));
        var1 = var1 + TSLog.boxRow(Application.B("添歹\uebb7긱횔㼷\uf592䛞㘎개烼㺗笌擧\uf46c\ue37e柶쀝\u0c76\u2cf8闠숮\ue161➷\ue232貁짂⊌\ue5f0夓념ົ┑躌ꊢ軆챆\udbca쑉肪옠婇ృჃ駯罿棷る꒪\ue2f3⊌\ue957腷濌鷡淙\uf707ﺘⷋ풧望쿝矪⇄ໆ僚廩偢ꛓ굫懩ˁ䌁즻✁뵼쥀㭎\ufde9῭\ud989ᨡ憠͂獺쾆ປ\uf88eﺷ뿤"));
        var1 = var1 + TSLog.boxRow(Application.B("淴歷\uebba긾횏㼪\uf5dd䛙㙀가烸㺚笌擧\uf46c\ue32d枺쀟ౠ⳪闠쉧\ue166➸\ue264貖즇⊍\ue5f7夓녕\u0ea6┍軙ꊸ軄찟\udb9e쑃肪왥婋ం\u10c9駪罱棱ろ꒾\ue2e9⊍\ue91c脿濞鷩淉\uf703ﻝⷁ풻李쿘瞣⇑ໞ匿庞偣ꛘ괥懔ˇ䌱짨✂봳쥓㭞ﷺῷ\ud989ᨦ憧͜猵쿒໒\uf8a4ﺾ뿰⋯\u2bff뉵\uec78쳾맮\uf7b2ₜᑹ蹩⌾\ueb87쏵鹛ﰒꦨ䗏䵄䋵"));
        if (var10000.getUseSignificantChangesOnly()) {
            var1 = var1 + TSLog.boxRow(Application.B("淍歫\uebbc긌횒㼤\uf5dc䛞㘆개烫㺟笃擧\uf44a\ue336査쀜\u0c72⳼闧숈\ue160➵\ue26b賓진⊜\ue5eb夘녌ົ╂軏ꊤ軆찒\udbd7쑏肺왥娅\u0c4e\u10cf駧罹棱ろ꒰\ue2f3⋅\ue90d腯濍鷡淉\uf70eﺎ\u2d98풵杝쿞矦⇂\u0e8e狀廎偺Ꚗ굱懅˝䌹즡✊봽쥕㭞ﶳ"));
        }

        if (!var2.getStopOnTerminate()) {
            var1 = var1 + TSLog.boxRow(Application.B("淡歷\uebac깿횖㼢\uf5cb䚗㘗갔烦㺊筍擧\uf466\ue37e柹쀝౻⳿闽술\ue17b➫\ue277賓짔⊉\ue5ea夆녬ມ┶軉ꊹ軅찏\udbd0쑀肻왥娟ంე駶罭棠"));
        }

        return var1 + Application.B("䣢么캉謏\uf3abᨓ탢揧ጰ褥嗘ᮮ帽䇃텙옎䋊\ue522⥅\u09c9냄\ue717쑞ʉ읂ꦣ\uecf7ޭ샕簦鑳⮟2\uabfc螛꯸\ue936ﻮ\ue171ꖟ\ue350罵⥲㗰볔婈");
    }

    private static class j implements PermissionRequestListener, Runnable {
        private final Context a;
        private final List<String> b;

        j(Context var1, List<String> var2) {
            this.a = var1;
            this.b = var2;
        }

        public void run() {
            PermissionManager.getInstance(this.a).checkPermissions(this.b, this);
        }

        public void onPermissionGranted() {
            TSLog.logger.info(TSLog.ok(Application.B("⅝粨ᑉ豮ퟱ୵ƨ鈄\uf664ʦ竉樓ㄓ摹㚭取튵\uf4b0旗ᐅ仢崙鷷젩\ue398疊勱Ӧ⮶똚㷚㨛愍༤\ude3f膝⎼ﬠ\ue07a裘栦")));
            synchronized(com.transistorsoft.locationmanager.util.c.i){}

            label209: {
                Throwable var10000;
                boolean var10001;
                Iterator var24;
                try {
                    var24 = com.transistorsoft.locationmanager.util.c.i.iterator();
                } catch (Throwable var22) {
                    var10000 = var22;
                    var10001 = false;
                    throw var10000;
                }

                Iterator var2 = var24;

                while(true) {
                    boolean var25;
                    try {
                        var25 = var2.hasNext();
                    } catch (Throwable var20) {
                        var10000 = var20;
                        var10001 = false;
                        break;
                    }

                    if (!var25) {
                        try {
                            com.transistorsoft.locationmanager.util.c.i.clear();
                            break label209;
                        } catch (Throwable var19) {
                            var10000 = var19;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        ((PermissionRequestListener)var2.next()).onPermissionGranted();
                    } catch (Throwable var21) {
                        var10000 = var21;
                        var10001 = false;
                        break;
                    }
                }

                throw var10000;
            }

            LocationProviderChangeEvent var1;
            LocationProviderChangeEvent var26 = var1 = new LocationProviderChangeEvent;
            var26.<init>(this.a);
            int var23;
            if ((var23 = var26.getStatus()) != com.transistorsoft.locationmanager.util.c.j.get()) {
                EventBus.getDefault().post(var1);
            }

            com.transistorsoft.locationmanager.util.c.j.set(var23);
        }

        public void onPermissionDenied(DeniedPermissions var1) {
            Iterator var78;
            boolean var79;
            Throwable var10000;
            boolean var10001;
            if (!com.transistorsoft.locationmanager.util.c.c(this.a)) {
                label730: {
                    TSLog.logger.warn(TSLog.warn(Application.B("బ\u1979䦘꧸ꮒ仸豋\udc6a◩\uda6e∟橰䗗梹퐅\udd98엎傞ẩᷪ脬郐Ŀ\uea02⸚ម쩐딼̜汓ބ황堧\ue814\udb17๒准衹\uf243\u1715")));
                    synchronized(com.transistorsoft.locationmanager.util.c.i){}

                    try {
                        var78 = com.transistorsoft.locationmanager.util.c.i.iterator();
                    } catch (Throwable var71) {
                        var10000 = var71;
                        var10001 = false;
                        throw var10000;
                    }

                    Iterator var3 = var78;

                    while(true) {
                        try {
                            var79 = var3.hasNext();
                        } catch (Throwable var69) {
                            var10000 = var69;
                            var10001 = false;
                            break;
                        }

                        if (!var79) {
                            try {
                                com.transistorsoft.locationmanager.util.c.i.clear();
                                break label730;
                            } catch (Throwable var68) {
                                var10000 = var68;
                                var10001 = false;
                                break;
                            }
                        }

                        try {
                            ((PermissionRequestListener)var3.next()).onPermissionDenied(var1);
                        } catch (Throwable var70) {
                            var10000 = var70;
                            var10001 = false;
                            break;
                        }
                    }

                    throw var10000;
                }
            } else if (VERSION.SDK_INT >= 29) {
                label731: {
                    synchronized(com.transistorsoft.locationmanager.util.c.i){}

                    try {
                        var78 = com.transistorsoft.locationmanager.util.c.i.iterator();
                    } catch (Throwable var75) {
                        var10000 = var75;
                        var10001 = false;
                        throw var10000;
                    }

                    Iterator var2 = var78;

                    while(true) {
                        try {
                            var79 = var2.hasNext();
                        } catch (Throwable var73) {
                            var10000 = var73;
                            var10001 = false;
                            break;
                        }

                        if (!var79) {
                            try {
                                com.transistorsoft.locationmanager.util.c.i.clear();
                                break label731;
                            } catch (Throwable var72) {
                                var10000 = var72;
                                var10001 = false;
                                break;
                            }
                        }

                        try {
                            ((PermissionRequestListener)var2.next()).onPermissionGranted();
                        } catch (Throwable var74) {
                            var10000 = var74;
                            var10001 = false;
                            break;
                        }
                    }

                    throw var10000;
                }
            }

            LocationProviderChangeEvent var77;
            LocationProviderChangeEvent var80 = var77 = new LocationProviderChangeEvent;
            var80.<init>(this.a);
            int var76;
            if ((var76 = var80.getStatus()) != com.transistorsoft.locationmanager.util.c.j.get()) {
                EventBus.getDefault().post(var77);
            }

            com.transistorsoft.locationmanager.util.c.j.set(var76);
        }
    }
}
