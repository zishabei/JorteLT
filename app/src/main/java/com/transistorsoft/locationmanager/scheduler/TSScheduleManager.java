//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.scheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobInfo.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.os.Build.VERSION;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.ConfigChangeEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.service.TrackingService;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.slf4j.Logger;

public class TSScheduleManager {
    private static TSScheduleManager e;
    private static final int f = 666;
    public static final String ACTION_ONESHOT = Application.B("㧢誆؋퓀ꂪ\u1f4f뇬");
    public static final String ACTION_NAME = Application.B("㧌誫غ퓺ꂍὮ");
    private final Context a;
    private final SimpleDateFormat b;
    private final List<Schedule> c = new ArrayList();
    private final AtomicBoolean d = new AtomicBoolean(false);

    public static TSScheduleManager getInstance(Context var0) {
        if (e == null) {
            TSConfig.getInstance(var0.getApplicationContext());
            e = a(var0);
        }

        return e;
    }

    private static synchronized TSScheduleManager a(Context var0) {
        if (e == null) {
            e = new TSScheduleManager(var0);
        }

        return e;
    }

    public TSScheduleManager(Context var1) {
        Locale var10005 = Locale.US;
        this.b = new SimpleDateFormat(Application.B("徚櫼禟赈ػ"), var10005);
        this.a = var1;
        EventBus var2;
        if (!(var2 = EventBus.getDefault()).isRegistered(this)) {
            var2.register(this);
        }

    }

    private void a(Boolean var1, Calendar var2, int var3) {
        TSConfig var4 = TSConfig.getInstance(this.a);
        Locale var5 = Locale.getDefault();
        Logger var6 = TSLog.logger;
        StringBuilder var7 = (new StringBuilder()).append(Application.B("隷ס帔䂔⇏垃흤\ue638ⰾꏬ쯡㒉躞粐ⲇ侑理"));
        String var8;
        if (var1) {
            var8 = Application.B("勉\u05cc");
        } else {
            var8 = Application.B("勉ׄ帺");
        }

        var6.info(TSLog.calendar(var7.append(var8).append(Application.B("臭ף师䃑")).append(this.b.format(var2.getTime())).append(Application.B("臭\u05ed帒䃑")).append(var2.getDisplayName(7, 2, var5)).toString()));
        long var11;
        if (!var4.getScheduleUseAlarmManager() && VERSION.SDK_INT >= 22) {
            PersistableBundle var15;
            PersistableBundle var10001 = var15 = new PersistableBundle;
            var15.<init>();
            var10001.putBoolean(Application.B("神\u05ec帝䂓⇇垓희"), var1);
            var10001.putInt(Application.B("行װ帝䂒⇀垟흦\ue63aⰗꎣ쯄㒀"), var3);
            Calendar var12;
            if (var2.after(var12 = Calendar.getInstance(Locale.US))) {
                var11 = var2.getTimeInMillis() - var12.getTimeInMillis();
            } else {
                var11 = 0L;
            }

            TSLog.logger.debug(TSLog.info(Application.B("喝\u05ed帞䂢⇈垞흭\ue639\u2c2fꎠ쯅㒗軟粖Ⲙ係瑁⺥抠儯ᙪ莟嘘ꀗ殗⟍\uf75a") + var11));
            JobScheduler var17 = (JobScheduler)this.a.getSystemService(Application.B("猪\u05ed帞䂂⇈垞흭\ue639\u2c2fꎠ쯅㒗"));
            ComponentName var13;
            var13 = new ComponentName.<init>(this.a, ScheduleJobService.class);
            var17.schedule((new Builder(666, var13)).setRequiredNetworkType(0).setOverrideDeadline(var11).setMinimumLatency(var11).setRequiresDeviceIdle(false).setRequiresCharging(false).setExtras(var15).setPersisted(false).build());
        } else {
            TSScheduleManager var10000 = this;
            TSLog.logger.debug(TSLog.info(Application.B("隷ס帔䂔⇏垃흤\ue638ⱺꎻ쯉㒑躗糂ⲫ俇瑇⺰抨儐ᙏ莔嘕ꀑ残➅")));
            Intent var14;
            Intent var10002 = var14 = new Intent;
            var10002.<init>(this.a, ScheduleService.class);
            var10002.putExtra(Application.B("﨏ס帔䂔⇏垃흤\ue638Ⰵꎩ쯎㒄躝粎ⲏ俏"), var1);
            var10002.putExtra(Application.B("行װ帝䂒⇀垟흦\ue63aⰗꎣ쯄㒀"), var3);
            PendingIntent var9 = ScheduleService.a(this.a, var14);
            AlarmManager var10 = (AlarmManager)var10000.a.getSystemService(Application.B("精\u05ee帝䂃⇆"));
            AlarmManager var16;
            if ((var3 = VERSION.SDK_INT) >= 23) {
                if (var3 > 30 && !var10.canScheduleExactAlarms()) {
                    var16 = var10;
                    var11 = var2.getTimeInMillis();
                    var16.setAndAllowWhileIdle(0, var11, var9);
                } else {
                    var16 = var10;
                    var11 = var2.getTimeInMillis();
                    var16.setExactAndAllowWhileIdle(0, var11, var9);
                }
            } else if (var3 >= 19) {
                var16 = var10;
                var11 = var2.getTimeInMillis();
                var16.setExact(0, var11, var9);
            } else {
                var16 = var10;
                var11 = var2.getTimeInMillis();
                var16.set(0, var11, var9);
            }
        }

    }

    private boolean a(String var1) {
        boolean var2 = false;
        if (VERSION.SDK_INT >= 22) {
            PersistableBundle var10001 = new PersistableBundle();
            var10001.putBoolean(Application.B("勉\u05cc帹䂢⇣垹흜"), true);
            var10001.putString(Application.B("精ס师䂘⇄垘"), var1);
            JobScheduler var3;
            if ((var3 = (JobScheduler)this.a.getSystemService(Application.B("猪\u05ed帞䂂⇈垞흭\ue639\u2c2fꎠ쯅㒗"))) != null) {
                if (VERSION.SDK_INT >= 24) {
                    if (var3.getPendingJob(var1.hashCode()) != null) {
                        var2 = true;
                    }
                } else {
                    Iterator var6 = var3.getAllPendingJobs().iterator();

                    while(var6.hasNext()) {
                        if (((JobInfo)var6.next()).getId() == var1.hashCode()) {
                            var2 = true;
                        }
                    }
                }
            }
        }

        boolean var4;
        if (!var2) {
            Intent var5;
            Intent var7 = var5 = new Intent;
            var5.<init>(this.a, ScheduleAlarmReceiver.class);
            var7.setAction(var1);
            var7.putExtra(Application.B("勉\u05cc帹䂢⇣垹흜"), true);
            var7.putExtra(Application.B("精ס师䂘⇄垘"), var1);
            if (PendingIntent.getBroadcast(this.a, var1.hashCode(), var5, Util.getPendingIntentFlags(536870912)) != null) {
                var4 = true;
            } else {
                var4 = false;
            }
        } else {
            var4 = var2;
        }

        return var4;
    }

    private boolean b() {
        TSConfig var10000 = TSConfig.getInstance(this.a);
        List var1 = var10000.getSchedule();
        if (!var10000.hasSchedule()) {
            TSLog.logger.warn(TSLog.warn(Application.B("\ue755㒄먷씸왿뮰㈯丰ନ坾눎ჴ阘㤶뛊襹卉\u05ee胷ళ鏊ἅ\u1ca6\ue58d섆耧")));
            return false;
        } else {
            List var96 = var1;
            this.d.set(true);
            synchronized(this.c){}

            Throwable var97;
            Iterator var98;
            boolean var10001;
            try {
                var98 = var96.iterator();
            } catch (Throwable var94) {
                var97 = var94;
                var10001 = false;
                throw var97;
            }

            Iterator var2 = var98;

            while(true) {
                boolean var99;
                try {
                    var99 = var2.hasNext();
                } catch (Throwable var88) {
                    var97 = var88;
                    var10001 = false;
                    break;
                }

                if (!var99) {
                    try {
                        Collections.sort(this.c, (var0, var1x) -> {
                            if (var0.b.before(var1x.b)) {
                                return -1;
                            } else {
                                return var0.b.after(var1x.b) ? 1 : 0;
                            }
                        });
                        this.d.set(false);
                        var99 = this.c.isEmpty();
                    } catch (Throwable var87) {
                        var97 = var87;
                        var10001 = false;
                        break;
                    }

                    boolean var95;
                    if (!var99) {
                        var95 = true;
                    } else {
                        var95 = false;
                    }

                    try {
                        return var95;
                    } catch (Throwable var86) {
                        var97 = var86;
                        var10001 = false;
                        break;
                    }
                }

                String var100;
                try {
                    var100 = (String)var2.next();
                } catch (Throwable var93) {
                    var97 = var93;
                    var10001 = false;
                    break;
                }

                String var3 = var100;

                Schedule var102;
                try {
                    var102 = new Schedule;
                } catch (Throwable var92) {
                    var97 = var92;
                    var10001 = false;
                    break;
                }

                Schedule var4;
                Schedule var101 = var4 = var102;

                try {
                    var101.<init>(var3);
                    var99 = var102.b();
                } catch (Throwable var91) {
                    var97 = var91;
                    var10001 = false;
                    break;
                }

                if (var99) {
                    try {
                        var99 = var4.a();
                    } catch (Throwable var90) {
                        var97 = var90;
                        var10001 = false;
                        break;
                    }

                    if (var99) {
                        continue;
                    }
                }

                try {
                    this.c.add(var4);
                } catch (Throwable var89) {
                    var97 = var89;
                    var10001 = false;
                    break;
                }
            }

            throw var97;
        }
    }

    @Subscribe(
            threadMode = ThreadMode.MAIN
    )
    public void onConfigChange(ConfigChangeEvent var1) {
        TSConfig.getInstance(this.a);
        if (var1.isDirty(Application.B("薉밖撒㲹㸃矚䆢궅"))) {
            this.restart(var1.getContext());
        }

    }

    public void start() {
        if (!this.d.get()) {
            TSScheduleManager var10000 = this;
            TSConfig var1 = TSConfig.getInstance(this.a);
            synchronized(this.c){}

            boolean var10001;
            Throwable var116;
            boolean var117;
            try {
                var117 = var10000.c.isEmpty();
            } catch (Throwable var114) {
                var116 = var114;
                var10001 = false;
                throw var116;
            }

            if (var117) {
                try {
                    var117 = this.b();
                } catch (Throwable var113) {
                    var116 = var113;
                    var10001 = false;
                    throw var116;
                }

                if (!var117) {
                    try {
                        this.stop();
                        return;
                    } catch (Throwable var109) {
                        var116 = var109;
                        var10001 = false;
                        throw var116;
                    }
                }
            } else {
                try {
                    var117 = var1.getSchedulerEnabled();
                } catch (Throwable var112) {
                    var116 = var112;
                    var10001 = false;
                    throw var116;
                }

                if (var117) {
                    try {
                        TSLog.logger.warn(Application.B("饶ᾌ짅惐ꪽ\udd8f둭ऱ躙䄼隟嫨\uf3e0돔谰ꇳ斐\uf8e9傫햮끗젿켓쩚\uddf4⁓豸ꤹﯻ栌䎭ㄇꤰ戻⺤"));
                        return;
                    } catch (Throwable var110) {
                        var116 = var110;
                        var10001 = false;
                        throw var116;
                    }
                }
            }

            TSConfig var10002;
            TSScheduleManager var119;
            try {
                var10000 = this;
                var119 = this;
                var10002 = var1;
            } catch (Throwable var111) {
                var116 = var111;
                var10001 = false;
                throw var116;
            }

            var10002.setSchedulerEnabled(true);
            StringBuffer var2;
            StringBuffer var118 = var2 = new StringBuffer;
            var118.<init>();
            var118.append(TSLog.header(Application.B("䄙쁑즍悕ꪊ\udd99둩ऱ躏䅩隒嫡\uf3e0뎑谞ꇙ")));
            synchronized(var119.c){}

            TSConfig var121;
            label940: {
                Iterator var120;
                try {
                    var120 = var10000.c.iterator();
                } catch (Throwable var108) {
                    var116 = var108;
                    var10001 = false;
                    throw var116;
                }

                Iterator var4 = var120;

                while(true) {
                    try {
                        var117 = var4.hasNext();
                    } catch (Throwable var106) {
                        var116 = var106;
                        var10001 = false;
                        break;
                    }

                    if (!var117) {
                        try {
                            var10000 = this;
                            var121 = var1;
                            var118 = var2;
                            break label940;
                        } catch (Throwable var105) {
                            var116 = var105;
                            var10001 = false;
                            break;
                        }
                    }

                    try {
                        var2.append(TSLog.boxRow(((Schedule)var4.next()).toString()));
                    } catch (Throwable var107) {
                        var116 = var107;
                        var10001 = false;
                        break;
                    }
                }

                throw var116;
            }

            var118.append(Application.B("뱿㪿\uecfd䗥辉\uf8aa酑Ⰴꮻ摌뎮翔훂雡꤁蓇䂹\udd99疈\uf08a镦\ued1d\uea37\uef6f\uf8c0ԭ꤈豉\udee2䴛暳ᐘ谲䜮ர嚫嶺烅\ue576吠돆䄸坒䈧眶똃"));
            TSLog.logger.info(var2.toString());
            Calendar var115 = Calendar.getInstance(Locale.US);
            var10000.a(var115, var121.getEnabled());
        }
    }

    public void stop() {
        TSConfig var10001 = TSConfig.getInstance(this.a);
        TSLog.logger.info(TSLog.off(Application.B("鍘ꇟ嶉雬；䩤蹧鹡波糏衑孡\uf3f8")));
        var10001.setSchedulerEnabled(false);
        this.a();
    }

    public void restart(Context var1) {
        TSConfig var4 = TSConfig.getInstance(this.a);
        List var2 = this.c;
        List var10001 = var2;
        TSScheduleManager var10002 = this;
        synchronized(var2) {
            var10002.c.clear();
        }

        if (var4.getSchedulerEnabled()) {
            TSLog.logger.debug(TSLog.calendar(Application.B("렩\ud8a3\u0602릨嵎톟\uf290뒚ᜠ\ue0e2⏃\uf58f冔酖蠟₢潏\uf100䇛◃㜋샪ⷦø쟵ﾾ暔᧗俖氋㼾욁")));
            this.a();
            var4.setSchedulerEnabled(false);
            this.start();
        }

    }

    void a(Calendar var1, Boolean var2) {
        TSScheduleManager var10000 = this;
        synchronized(this.c){}

        boolean var10001;
        Throwable var100;
        boolean var101;
        try {
            var101 = var10000.c.isEmpty();
        } catch (Throwable var97) {
            var100 = var97;
            var10001 = false;
            throw var100;
        }

        if (var101) {
            try {
                var101 = this.b();
            } catch (Throwable var96) {
                var100 = var96;
                var10001 = false;
                throw var100;
            }

            if (!var101) {
                try {
                    this.stop();
                    return;
                } catch (Throwable var94) {
                    var100 = var94;
                    var10001 = false;
                    throw var100;
                }
            }
        }

        try {
            ;
        } catch (Throwable var95) {
            var100 = var95;
            var10001 = false;
            throw var100;
        }

        if (TimeUnit.MILLISECONDS.toDays(var1.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) >= 7L) {
            TSLog.logger.warn(TSLog.warn(Application.B("墨ף帕䂝⇎垒휨\ue629ⰵꏬ쯆㒌躑粆Ⳋ俊理⺱抦儵ᙋ莞嘁ꀚ残⟙\uf75a\ue73e䘇喊鵯\ue0be\ue93eꈵ攊ᖈ\uee8a\uebad")));
        } else {
            var10000 = this;
            int var3 = var1.get(7);
            TSLog.logger.debug(TSLog.calendar(Application.B("器ף帅䃑ↈ") + var3 + Application.B("渚֢帯䂔⇊垄흫\ue635ⰳꎢ쯇㓅躌粁Ⲃ俎瑂⺷抩儸ᘎ莜嘛ꀄ毎➖\uf716\ue77f䘲喎鵪\ue0f9\ue97eꉼ")));
            Object var4 = null;
            synchronized(this.c){}

            Iterator var102;
            try {
                var102 = var10000.c.iterator();
            } catch (Throwable var93) {
                var100 = var93;
                var10001 = false;
                throw var100;
            }

            Iterator var6 = var102;

            Schedule var7;
            Schedule var104;
            do {
                try {
                    var101 = var6.hasNext();
                } catch (Throwable var92) {
                    var100 = var92;
                    var10001 = false;
                    throw var100;
                }

                if (!var101) {
                    var7 = (Schedule)var4;
                    break;
                }

                try {
                    var104 = (Schedule)var6.next();
                } catch (Throwable var91) {
                    var100 = var91;
                    var10001 = false;
                    throw var100;
                }

                var7 = var104;

                try {
                    var101 = var104.a(var1);
                } catch (Throwable var90) {
                    var100 = var90;
                    var10001 = false;
                    throw var100;
                }
            } while(!var101);

            try {
                var104 = var7;
            } catch (Throwable var89) {
                var100 = var89;
                var10001 = false;
                throw var100;
            }

            if (var104 != null) {
                Boolean var98;
                int var99;
                if (var1.after(var7.b) && var1.before(var7.c)) {
                    if (!var2) {
                        var10000 = this;
                        TSLog.logger.debug(TSLog.calendar(Application.B("隷ס帔䂔⇏垃흤\ue638Ⱘꏬ쯓㒄躆粑Ⳋ俜瑃⻢抶儵ᙁ莏嘘ꀒ毎➕\uf71f\ue73e䘅喭鵘\ue095\ue91cꈗ敮ᗝ\uee98\uebf6瘁鵇⫏멌쒕埊\ue572끗ꠓ\uea21햱⏌")));
                        var98 = true;
                        var99 = var7.a;
                        var10000.a(var98, var1, var99);
                    } else {
                        var10000 = this;
                        TSScheduleManager var103 = this;
                        var98 = false;
                        var1 = var7.c;
                        var99 = var7.a;
                        var103.a(var98, var1, var99);
                        TrackingService.start(var10000.a);
                    }
                } else {
                    if (var1.before(var7.b)) {
                        if (!var2) {
                            var10000 = this;
                            var98 = true;
                            var1 = var7.b;
                            var99 = var7.a;
                            var10000.a(var98, var1, var99);
                            return;
                        }
                    } else {
                        if (!var1.after(var7.c)) {
                            TSLog.logger.warn(TSLog.warn(Application.B("墨ף帕䂝⇎垒휨\ue629ⰵꏬ쯆㒌躑粆Ⳋ俅瑃⺺抱兽ᙏ莖嘕ꀄ殃")));
                            return;
                        }

                        if (!var2) {
                            TSLog.logger.debug(TSLog.calendar(Application.B("隷ס帔䂔⇏垃흤\ue638Ⱘꏬ쯆㒄躖粎ⲏ俏理⺶抪兽ᙈ莓嘚ꀒ毎➖\uf714\ue767䙠喂鵵\ue0b6\ue922ꈿ教ᗝ\uee8e\uebec瘑鴆⫁먇쒕型\ue543끚ꡖ\uea0c햕⏱\ue273䀦\u31e9⫔襃\ue53d‡ᇤⷑٹ岲ɬ팜쭄")));
                            var1.add(6, 1);
                            var1.set(11, 0);
                            var1.set(12, 0);
                            this.a(var1, var2);
                            return;
                        }
                    }

                    var10000 = this;
                    TSLog.logger.debug(TSLog.calendar(Application.B("隷ס帔䂔⇏垃흤\ue638Ⱘꏬ쯓㒄躆粑Ⳋ俜瑃⻢抶儵ᙁ莏嘘ꀒ毎➕\uf71f\ue73e䘄喪鵊\ue096\ue912ꈞ敯ᖹ\ueeda\uebe1瘀鴓⪘멞쓐型\ue561끀ꡖ\uea4f햰⏗\ue249")));
                    var98 = false;
                    var99 = var7.a;
                    var10000.a(var98, var1, var99);
                }
            } else if (var2) {
                TSLog.logger.debug(TSLog.calendar(Application.B("隷ס帔䂔⇏垃흤\ue638Ⱘꏬ쯓㒄躆粑Ⳋ俜瑃⻢抶儵ᙁ莏嘘ꀒ毎➕\uf71f\ue73e䘄喪鵊\ue096\ue912ꈞ敯ᖹ\ueeda\uebe1瘀鴓⪘멞쓐型\ue561끀ꡖ\uea4f햰⏗\ue249")));
                this.a(false, var1, 1);
            } else {
                TSLog.logger.debug(TSLog.calendar(Application.B("器ף帅䃑ↈ") + var3 + Application.B("渚֢帺䂐⇂垚흭\ue639ⱺꎸ쯏㓅躙粋Ⲅ俏理⺣抩儼ᙜ莗嘇ꁖ殁➙\uf75a\ue76a䘨喊鵪\ue0f7\ue934ꈳ敓ᗓ\ueeda\ueba3瘡鴕⫁멀쓛埌\ue520끆ꡜ\uea02햑⏪\ue26f䀮\u31be⪎褂\ue57e")));
                var1.add(6, 1);
                var1.set(11, 0);
                var1.set(12, 0);
                this.a(var1, var2);
            }

        }
    }

    void a() {
        if (!TSConfig.getInstance(this.a).getScheduleUseAlarmManager() && VERSION.SDK_INT >= 22) {
            ((JobScheduler)this.a.getSystemService(Application.B("猪\u05ed帞䂂⇈垞흭\ue639\u2c2fꎠ쯅㒗"))).cancel(666);
        } else {
            TSScheduleManager var10000 = this;
            Intent var1;
            var1 = new Intent.<init>(this.a, ScheduleService.class);
            PendingIntent var2 = ScheduleService.a(this.a, var1);
            ((AlarmManager)var10000.a.getSystemService(Application.B("精\u05ee帝䂃⇆"))).cancel(var2);
        }

    }

    public void oneShot(String var1, long var2) {
        this.oneShot(var1, var2, false);
    }

    public void oneShot(String var1, long var2, boolean var4) {
        if (this.a(var1)) {
            TSLog.logger.info(TSLog.alarm(Application.B("ᑺ\uf35b\u0abb\ue470砏퀂嚃\uf4f9") + var1 + Application.B("ᐕ\uf35cભ\ue423砆퀁嚅\uf4bc⑂\ue91e\uedf0体㇡㝻娂癀슺룎㖱")));
        } else {
            TSLog.logger.info(TSLog.alarm(Application.B("ᑦ\uf356શ\ue466砃퀘嚛\uf4bc⑇\ue95a\uedc6伝ㇴ㝍娄癋슧뢚㗶") + var1 + Application.B("ᐕ\uf35cર\ue423") + var2 + Application.B("ᑘ\uf346૾\ue42b砍퀂嚕\uf490⑧\ue940\ueda9") + var1.hashCode() + Application.B("ᐜ")));
            if (VERSION.SDK_INT >= 22 && !var4) {
                PersistableBundle var10;
                PersistableBundle var10001 = var10 = new PersistableBundle;
                var10001.<init>();
                var10001.putBoolean(Application.B("ᑺ\uf37bછ\ue450砯퀢嚣"), true);
                var10001.putString(Application.B("ᑔ\uf356પ\ue46a砈퀃"), var1);
                JobScheduler var5;
                if ((var5 = (JobScheduler)this.a.getSystemService(Application.B("ᑟ\uf35a઼\ue470砄퀅嚒\uf4bd\u2456\ue916\uedec企"))) == null) {
                    TSLog.logger.warn(TSLog.warn(Application.B("ᑿ\uf35a઼\ue450砄퀅嚒\uf4bd\u2456\ue916\uedec企ㆱ㝷娟瘄슽룕㖺낞鱪ᓜ糲刚瀧阇\uefdb奓캽拶釾㡺鴱鸱條喫\uf392콀弒儕鋇佸曗\ufbca") + var1));
                    return;
                }

                var5.schedule((new Builder(var1.hashCode(), new ComponentName(this.a, ScheduleJobService.class))).setRequiredNetworkType(0).setOverrideDeadline(var2).setMinimumLatency(var2).setRequiresDeviceIdle(false).setRequiresCharging(false).setExtras(var10).setPersisted(false).build());
            } else {
                TSScheduleManager var10000 = this;
                Intent var8;
                Intent var10002 = var8 = new Intent;
                var8.<init>(this.a, ScheduleAlarmReceiver.class);
                var10002.setAction(var1);
                var10002.putExtra(Application.B("ᑺ\uf37bછ\ue450砯퀢嚣"), true);
                var10002.putExtra(Application.B("ᑔ\uf356પ\ue46a砈퀃"), var1);
                PendingIntent var6 = PendingIntent.getBroadcast(this.a, var1.hashCode(), var8, Util.getPendingIntentFlags(134217728));
                AlarmManager var9;
                if ((var9 = (AlarmManager)var10000.a.getSystemService(Application.B("ᑔ\uf359િ\ue471砊"))) == null) {
                    TSLog.logger.warn(TSLog.warn(Application.B("ᑴ\uf359િ\ue471砊퀠嚖\uf4b7⑂\ue91d\uedec企ㆱ㝷娟瘄슽룕㖺낞鱪ᓜ糲刚瀧阇\uefdb奓캽拶釾㡺鴱鸱條喫\uf392콀弒儕鋇佸曗\ufbca") + var1));
                    return;
                }

                long var7 = var2 + System.currentTimeMillis();
                int var3;
                if ((var3 = VERSION.SDK_INT) >= 23) {
                    if (var3 > 30 && var9.canScheduleExactAlarms()) {
                        var9.setExactAndAllowWhileIdle(0, var7, var6);
                    } else {
                        var9.setAndAllowWhileIdle(0, var7, var6);
                    }
                } else if (var3 >= 19) {
                    var9.setExact(0, var7, var6);
                } else {
                    var9.set(0, var7, var6);
                }
            }

        }
    }

    public void cancelOneShot(String var1) {
        if (this.a(var1)) {
            TSLog.logger.info(TSLog.alarm(Application.B("䖐Ⱬጴ쇳ך\uf8e3媊鼋쳑璩攟ୄ뵧頼쳓玉") + var1));
            JobScheduler var2;
            if (VERSION.SDK_INT >= 22 && (var2 = (JobScheduler)this.a.getSystemService(Application.B("䖹ⱥጸ쇣ל\uf8e7嫏鼠쳊璠攩\u0b5e"))) != null) {
                var2.cancel(var1.hashCode());
            }

            TSScheduleManager var10000 = this;
            Intent var5;
            Intent var10002 = var5 = new Intent;
            var5.<init>(this.a, ScheduleAlarmReceiver.class);
            var10002.setAction(var1);
            var10002.putExtra(Application.B("䖜ⱄጟ쇃\u05f7\uf8c0嫾"), true);
            var10002.putExtra(Application.B("䖲Ⱪጮ쇹א\uf8e1"), var1);
            PendingIntent var3 = PendingIntent.getBroadcast(this.a, var1.hashCode(), var5, Util.getPendingIntentFlags(134217728));
            AlarmManager var4;
            if ((var4 = (AlarmManager)var10000.a.getSystemService(Application.B("䖲ⱦጻ쇢ג"))) != null) {
                var4.cancel(var3);
                var3.cancel();
            }

        }
    }

    public void destroy() {
        if (TSConfig.getInstance(this.a).getStopOnTerminate()) {
            this.stop();
        }

        EventBus var1;
        if ((var1 = EventBus.getDefault()).isRegistered(this)) {
            var1.unregister(this);
        }

    }
}
