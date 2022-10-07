//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.callback.TSBackgroundTaskCallback;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.service.BackgroundTaskService;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BackgroundTaskManager {
    public static String ACTION = Application.B("\ue9ff\uf105ㇾ汬飅ﯸ變캐\uf7fd몞埳\ud86a\uaac4䕌");
    public static String TASK_ID_FIELD = Application.B("\ue9c9\uf105\u31ee汬飫ﯮ");
    private static final long MAX_TASK_DURATION = 120000L;
    private static final AtomicInteger sNextTaskId = new AtomicInteger(0);
    private static BackgroundTaskManager mInstance = null;
    private final List<BackgroundTaskManager.Task> mTasks = new ArrayList();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private static int getNextTaskId() {
        return sNextTaskId.incrementAndGet();
    }

    public static BackgroundTaskManager getInstance() {
        if (mInstance == null) {
            mInstance = getInstanceSynchronized();
        }

        return mInstance;
    }

    private static synchronized BackgroundTaskManager getInstanceSynchronized() {
        if (mInstance == null) {
            mInstance = new BackgroundTaskManager();
        }

        return mInstance;
    }

    private BackgroundTaskManager() {
    }

    private BackgroundTaskManager.Task find(int var1) {
        BackgroundTaskManager var10000 = this;
        synchronized(this.mTasks){}

        Throwable var46;
        Iterator var47;
        boolean var10001;
        try {
            var47 = var10000.mTasks.iterator();
        } catch (Throwable var45) {
            var46 = var45;
            var10001 = false;
            throw var46;
        }

        Iterator var2 = var47;

        while(true) {
            boolean var48;
            try {
                var48 = var2.hasNext();
            } catch (Throwable var42) {
                var46 = var42;
                var10001 = false;
                break;
            }

            if (!var48) {
                try {
                    return null;
                } catch (Throwable var41) {
                    var46 = var41;
                    var10001 = false;
                    break;
                }
            }

            BackgroundTaskManager.Task var49;
            try {
                var49 = (BackgroundTaskManager.Task)var2.next();
            } catch (Throwable var44) {
                var46 = var44;
                var10001 = false;
                break;
            }

            BackgroundTaskManager.Task var3 = var49;

            int var50;
            try {
                var50 = var49.getId();
            } catch (Throwable var43) {
                var46 = var43;
                var10001 = false;
                break;
            }

            if (var50 == var1) {
                try {
                    return var3;
                } catch (Throwable var40) {
                    var46 = var40;
                    var10001 = false;
                    break;
                }
            }
        }

        throw var46;
    }

    public void startBackgroundTask(Context var1, TSBackgroundTaskCallback var2) {
        new BackgroundTaskManager.Task(var1, false, var2);
    }

    public void startBackgroundTask(Context var1, boolean var2, TSBackgroundTaskCallback var3) {
        new BackgroundTaskManager.Task(var1, var2, var3);
    }

    public void stopBackgroundTask(Context var1, int var2) {
        BackgroundTaskManager.Task var9;
        if ((var9 = this.find(var2)) == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("阱殲ᬀ䰥䚀㥝虵眾얗ᵋ䡈ﭠ疀ἃ细䕐⦟혃\ue030桥\udc89Ꝩ應ᖟ饶孃鶦ݓ륿ﷂ牟䪶騚┴ϲꠊ䒨") + var2));
        } else {
            var9.stop();
            List var10;
            List var10000 = var10 = this.mTasks;
            BackgroundTaskManager var10001 = this;
            synchronized(var10){}

            Throwable var11;
            boolean var12;
            try {
                var10001.mTasks.remove(var9);
            } catch (Throwable var8) {
                var11 = var8;
                var12 = false;
                throw var11;
            }

            try {
                ;
            } catch (Throwable var7) {
                var11 = var7;
                var12 = false;
                throw var11;
            }
        }
    }

    public void onStartJob(Context var1, int var2, BackgroundTaskManager.a var3) {
        BackgroundTaskManager.Task var4;
        if ((var4 = this.find(var2)) == null) {
            TSLog.logger.warn(TSLog.warn(Application.B("⽞媖띾䙥内虱白엘皵ᇸ싡湮醊뮬㽀‐竂廤뿔偦毖") + var2));
            var3.onFinish();
        } else {
            var4.start(var3);
        }
    }

    public interface a {
        void onFinish();
    }

    public class Task {
        private final int mId;
        private final TSBackgroundTaskCallback mCallback;
        private BackgroundTaskManager.a mCompletionHandler;
        private Runnable mStopTask;
        private boolean mDisableTimeout;

        Task(Context var2, boolean var3, TSBackgroundTaskCallback var4) {
            Context var10000 = var2;
            super();
            this.mDisableTimeout = false;
            this.mId = BackgroundTaskManager.getNextTaskId();
            this.mDisableTimeout = var3;
            List var11;
            List var10004 = var11 = BackgroundTaskManager.this.mTasks;
            BackgroundTaskManager var10005 = BackgroundTaskManager.this;
            synchronized(var11){}

            Throwable var12;
            boolean var10001;
            try {
                var10005.mTasks.add(this);
            } catch (Throwable var10) {
                var12 = var10;
                var10001 = false;
                throw var12;
            }

            try {
                ;
            } catch (Throwable var9) {
                var12 = var9;
                var10001 = false;
                throw var12;
            }

            this.mCallback = var4;
            BackgroundTaskService.start(var10000, this.mId);
        }

        private void startTimer() {
            this.mStopTask = new Runnable() {
                public void run() {
                    TSLog.logger.warn(TSLog.warn(Application.B("㳊\ud96d㽰菖碟혠硷椻裍厝챦싌䯉癧쏡组뭱꽵䐶琔\uea96ꗀ賨ം\uf803\uec3bᅃ絬忓띦짓뱥\ueae8붢ᝫ쪔슃ｱ\uf070偻൹섎綹\ue5f1賣崐\u1775᱁㠵펀ﲩ") + Task.this.mId));
                    Task.this.stop();
                    Task.this.mStopTask = null;
                }
            };
            BackgroundTaskManager.this.mHandler.postDelayed(this.mStopTask, 120000L);
        }

        private void stopTimer() {
            if (this.mStopTask != null) {
                BackgroundTaskManager.this.mHandler.removeCallbacks(this.mStopTask);
            }

            this.mStopTask = null;
        }

        public int getId() {
            return this.mId;
        }

        public void start(BackgroundTaskManager.a var1) {
            TSLog.logger.info(Application.B("᷵屢哊Ὃꯛ鸽肤Ꝣ撹驒细냞갿熫ꓱ믽ᴽ᯲ぜ췾吉뮂봫") + this.mId);
            this.mCompletionHandler = var1;
            if (!this.mDisableTimeout) {
                this.startTimer();
            }

            BackgroundGeolocation.getUiHandler().post(new Runnable() {
                public void run() {
                    Task.this.mCallback.onStart(Task.this.mId);
                }
            });
        }

        public void stop() {
            TSLog.logger.info(Application.B("㗏궫\u2e67\udb35驪됵⣸\ue6d9탠㐩\ude10묮ⶊ遪㚎\ue835쟾얳\ue9ecꆺウ흋") + this.mId);
            BackgroundTaskManager.a var1;
            if ((var1 = this.mCompletionHandler) != null) {
                var1.onFinish();
            }

            this.stopTimer();
        }
    }
}
