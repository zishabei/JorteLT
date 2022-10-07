//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Build.VERSION;
import com.transistorsoft.locationmanager.a.a;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.scheduler.TSScheduleManager;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractService extends Service {
    private static final String FOREGROUND_SERVICE_NOT_ALLOWED_EXCEPTION = Application.B("录턏䚉帧斶\ue198\ue633뗸虋荗羙䵄쥿ᙒ뉃㚠߅륕ᆥ퓥\u089e㢓∄쀗\uec6d䄓藹⚳礡郓䌃歽왊厪䌨齠舸\udb6f謏瓓\uf86b");
    private static final List<String> sActiveServices = new ArrayList();
    private static final List<Intent> sFailedLaunchIntents = new ArrayList();
    protected static long DEFAULT_STOP_DELAY = 250L;
    protected Handler mHandler = new Handler(Looper.getMainLooper());
    protected Runnable mStopServiceTask;
    protected long mStopDelay;
    protected int mEventCount;
    protected boolean mSticky;
    protected Date mStartedAt;
    private String mServiceName;

    public AbstractService() {
        this.mStopDelay = DEFAULT_STOP_DELAY;
        this.mEventCount = 0;
        this.mSticky = false;
        this.mServiceName = Application.B("쑳ꛙ읣\uf455낝諕躚㞝篥徻觽裏愋\udac7\ue71a");
    }

    public static boolean anyActive() {
        synchronized(sActiveServices){}

        Throwable var10000;
        boolean var10001;
        boolean var8;
        try {
            var8 = sActiveServices.isEmpty();
        } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            throw var10000;
        }

        boolean var1;
        if (!var8) {
            var1 = true;
        } else {
            var1 = false;
        }

        try {
            return var1;
        } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            throw var10000;
        }
    }

    public static boolean isActive(String var0) {
        synchronized(sActiveServices) {
            return sActiveServices.contains(var0);
        }
    }

    public static void launchService(Context var0, Class<?> var1, String var2) {
        Intent var3;
        Intent var10001 = var3 = new Intent;
        var10001.<init>(var0, var1);
        var10001.setAction(var2);
        startForegroundService(var0, var3);
    }

    public static void launchQueuedServices(Context var0) {
        synchronized(sFailedLaunchIntents){}

        Throwable var10000;
        boolean var10001;
        ArrayList var8;
        try {
            var8 = new ArrayList;
        } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            throw var10000;
        }

        ArrayList var9 = var8;

        try {
            var9.<init>(sFailedLaunchIntents);
            sFailedLaunchIntents.clear();
        } catch (Throwable var6) {
            var10000 = var6;
            var10001 = false;
            throw var10000;
        }

        Iterator var1 = var8.iterator();

        while(var1.hasNext()) {
            startForegroundService(var0, (Intent)var1.next());
        }

    }

    public static void stop(Context var0, Class<?> var1) {
        Intent var2;
        var2 = new Intent.<init>(var0, var1);
        if (isActive(var1.getSimpleName())) {
            var2.setAction(Application.B("佨筐嗐벢"));
            startForegroundService(var0, var2);
        }

    }

    public static void startForegroundService(Context var0, Intent var1) {
        if (VERSION.SDK_INT >= 26) {
            try {
                var0.startForegroundService(var1);
            } catch (Exception var12) {
                if (VERSION.SDK_INT >= 31 && var12.getClass().getSimpleName().equalsIgnoreCase(Application.B("솞画띱\u1ae0⎓鵼ﺙ솸滰ᆴ词방멳㡿쯌\ue0a3䯐늓\uecedଡ଼盵䁕귝폎\udbb4㰏\ueb07袈ᐘ㺡篎䎒\ue5c3ࢡ我ᅧ䤒陮籪\uda6c\uddd9"))) {
                    int var3;
                    if ((var3 = var1.getIntExtra(Application.B("솴節띶\u1aeb⎗鵦ﺩ솫滿ᆹ诲밹멳㡬쯖"), 0) + 1) > 1) {
                        TSLog.logger.error(TSLog.warn(Application.B("솃謹띷\u1ae4⎆鵺ﺰ솢滬ᆵ诹밾멮㡼쯋\ue0a4䯦능\uecebୋ目䁂귶폼\udbe0㰂\ueb2a袱ᐹ㺕篣䏖\ue5c2\u089c戼ᅋ䤧陞簣\uda2b\udd85\ueb43瞶鹈ꔜ蔕ᢞ殍⿋弛惫湦䕚㸕蕡참꾮鳘㝭⃩箝") + var1), var12);
                        return;
                    }

                    Context var10000 = var0;
                    var1.putExtra(Application.B("솴節띶\u1aeb⎗鵦ﺩ솫滿ᆹ诲밹멳㡬쯖"), var3);
                    TSLog.logger.warn(TSLog.info(Application.B("솚節띠\u1aee⎓鵼ﺙ솸滰ᆴ设밊멆㡚쮅\ue0ac䯔늵\uecf7\u0b5e盯䀁귷폄\udbae㰧\ueb0e袀ᑍ㻶箋䎤\ue5e3ࢭ戀ᅻ䤋陴籤\uda23\uddc0\ueb44瞦鸀ꕈ蔦ᢋ毅⾗彖惡湮䕂㸝蕨찺꿼鲃㜳⃩") + var1));
                    List var13;
                    List var10001 = var13 = sFailedLaunchIntents;
                    synchronized(var10001){}

                    Throwable var14;
                    boolean var15;
                    try {
                        sFailedLaunchIntents.add(var1);
                    } catch (Throwable var11) {
                        var14 = var11;
                        var15 = false;
                        throw var14;
                    }

                    try {
                        ;
                    } catch (Throwable var10) {
                        var14 = var10;
                        var15 = false;
                        throw var14;
                    }

                    TSScheduleManager.getInstance(var10000).oneShot(Application.B("솔揄띖\u1acb⎷鵆ﺩ솋滑ᆂ诛밋멓㡆쯰\ue08e䯱늟\uecca\u0b78盕䁷귚폢\udb85"), 0L, true);
                    return;
                }

                TSLog.logger.error(TSLog.error(Application.B("솃謹띷\u1ae4⎆鵺ﺰ솢滬ᆵ诹밾멮㡼쯋\ue0a4䯦능\uecebୋ目䁂귶폼\udbe0㰋\ueb39袶ᐸ㺄箑䏖") + var1 + Application.B("쇸") + var1.getExtras() + Application.B("쇴\ufaec띦\u1af7⎆鵡ﺄ쇷溾") + var12.getMessage()), var12);
            }
        } else {
            var0.startService(var1);
        }

    }

    private void doStartForeground() {
        if (this.mSticky && this.mStartedAt == null) {
            Date var1;
            var1 = new Date.<init>();
            this.mStartedAt = var1;
        }

        AbstractService var10000 = this;
        AbstractService var10001 = this;
        int var3 = 9942585;

        try {
            var10000.startForeground(var3, ForegroundNotification.build(var10001));
        } catch (Exception var2) {
            this.stopSelf();
            TSLog.logger.error(TSLog.error(var2.getMessage()), var2);
        }

    }

    private void stop() {
        this.mSticky = false;
        this.mEventCount = 0;
        this.finish(0L);
    }

    protected void doCreate(String var1) {
        super.onCreate();
        this.mServiceName = var1;
        this.doStartForeground();
        synchronized(sActiveServices){}

        Throwable var10000;
        boolean var10001;
        boolean var15;
        try {
            var15 = sActiveServices.contains(var1);
        } catch (Throwable var14) {
            var10000 = var14;
            var10001 = false;
            throw var10000;
        }

        if (!var15) {
            try {
                sActiveServices.add(var1);
            } catch (Throwable var13) {
                var10000 = var13;
                var10001 = false;
                throw var10000;
            }
        }

        try {
            ;
        } catch (Throwable var12) {
            var10000 = var12;
            var10001 = false;
            throw var10000;
        }

        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
            public void run() {
                BackgroundGeolocation.getInstance(AbstractService.this.getApplicationContext());
            }
        });
    }

    protected boolean start(Intent var1, boolean var2) {
        TSConfig var3 = TSConfig.getInstance(this.getApplicationContext());
        this.doStartForeground();
        String var4;
        if ((var4 = var1.getAction()) != null) {
            if (var4.equalsIgnoreCase(Application.B("眑\uf7aa\uf0f0饷"))) {
                this.stop();
                return false;
            }

            if (var4.equalsIgnoreCase(Application.B("眑\uf7aa\uf0fe饵鱳"))) {
                this.setSticky(true);
            } else if (var4.equalsIgnoreCase(Application.B("県\uf7b1\uf0eb饮鱡풵첨땥됇鍚左૪珼槯讷럛넚黾"))) {
                TSLog.logger.debug(Application.B("眹\uf7b0\uf0f0饳鱮풺첢땧됒鍇巠૫珳槭讠럆넜黿\uddc7禍\ue983") + var1.getStringExtra(Application.B("看\uf7ba")));
                if (var1.hasExtra(Application.B("看\uf7ba"))) {
                    String var5 = var1.getStringExtra(Application.B("看\uf7ba"));
                    if (var3.getNotification().getLayout().equalsIgnoreCase(Application.B("眆\uf7bb\uf0f9饦鱲풰첿")) && var5.equalsIgnoreCase(Application.B("県\uf7b1\uf0eb饮鱡풵첨땥됇鍚左૪珟槹讷럆넚黾\uddf9\ufa6e\ue9d6㐥㾥"))) {
                        TrackingService.changePace(this.getApplicationContext(), false);
                    }

                    BackgroundGeolocation.getInstance(this.getApplicationContext()).fireNotificationActionListeners(var5);
                } else {
                    TSLog.logger.warn(TSLog.warn(Application.B("眬\uf7b1\uf0eb饮鱡풵첨땥됇鍚左૪玽槭讠럆넜黿\uddc7隷\ue9d1㐳㾣ᾬᖣ憁듒㝖㞄庄》嘿ℰ\uf8faꃇ熤秧㧝砩")));
                }
            }
        }

        if (var2 && !var3.getEnabled()) {
            TSLog.logger.warn(TSLog.warn(Application.B("眰\uf7bb\uf0f9饲鱴풵첥땣둓鍇左ત珮槸订럀넁麰") + this.mServiceName + Application.B("睎\uf7fe\uf0fa饩鱦풾첧땡됗錉嶩ૢ珼槠记럗")));
            this.stop();
            return false;
        } else if (!a.c(this)) {
            var3.reset();
            this.stop();
            return false;
        } else {
            ++this.mEventCount;
            TSLog.logger.debug(TSLog.on(this.mServiceName + Application.B("睂\uf785\uf0fa饱鱢풲첿땇된鍆巧૰玧榬") + this.mEventCount + Application.B("眿")));
            return true;
        }
    }

    protected void startEvent() {
        ++this.mEventCount;
    }

    protected void stopEvent() {
        --this.mEventCount;
    }

    protected void setSticky(boolean var1) {
        this.mSticky = var1;
    }

    protected boolean isSticky() {
        return this.mSticky;
    }

    protected void setStopDelay(long var1) {
        this.mStopDelay = var1;
    }

    protected void finish(long var1) {
        this.mStopDelay = var1;
        this.finish();
    }

    protected void finish() {
        --this.mEventCount;
        TSLog.logger.debug(Application.B("줂䆢\uf38e죯ᑐ堝\uffdeḁሞ嬴칆ᚌ") + this.mServiceName + Application.B("\uefbb뿶\u0de5좹ᐕ堕ￃḬመ嬲칀ᛘꛄ뾛") + Math.max(this.mEventCount, 0) + Application.B("\uefb7뾍ෳ좻ᐙ堘ￜḖቍ孧") + this.mSticky + Application.B("\uefc6"));
        if (!this.mSticky) {
            if (this.mEventCount <= 0) {
                if (this.mStopDelay == 0L) {
                    if (this.mStopServiceTask == null) {
                        this.stopSelf();
                    }
                } else {
                    Runnable var1;
                    if ((var1 = this.mStopServiceTask) != null) {
                        this.mHandler.removeCallbacks(var1);
                    }

                    Runnable var10002 = var1 = new Runnable() {
                        public void run() {
                            AbstractService.this.stopSelf();
                        }
                    };
                    var10002.<init>();
                    this.mStopServiceTask = var10002;
                    this.mHandler.postDelayed(var1, this.mStopDelay);
                }
            }
        }
    }

    public IBinder onBind(Intent var1) {
        return null;
    }

    public void onDestroy() {
        TSLog.logger.debug(TSLog.off(this.mServiceName + Application.B("젲㻽ᨻ\u0ff0㚵唡匩囔")));
        List var1;
        List var10000 = var1 = sActiveServices;
        synchronized(var10000){}

        boolean var10001;
        Throwable var8;
        try {
            sActiveServices.remove(this.mServiceName);
        } catch (Throwable var7) {
            var8 = var7;
            var10001 = false;
            throw var8;
        }

        try {
            ;
        } catch (Throwable var6) {
            var8 = var6;
            var10001 = false;
            throw var8;
        }
    }
}
