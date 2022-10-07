//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager.a;
import com.transistorsoft.tslocationmanager.Application;

public class BackgroundTaskService extends AbstractService {
    public BackgroundTaskService() {
    }

    public static void start(Context var0, int var1) {
        Intent var10001 = getIntent(var0, (String)null);
        var10001.putExtra(BackgroundTaskManager.TASK_ID_FIELD, var1);
        AbstractService.startForegroundService(var0, var10001);
    }

    public static void stop(Context var0) {
        AbstractService.stop(var0, BackgroundTaskService.class);
    }

    private static Intent getIntent(Context var0, @Nullable String var1) {
        Intent var2;
        var2 = new Intent.<init>(var0, BackgroundTaskService.class);
        if (var1 != null) {
            var2.setAction(var1);
        }

        return var2;
    }

    public void onCreate() {
        super.doCreate(BackgroundTaskService.class.getSimpleName());
    }

    public int onStartCommand(Intent var1, int var2, int var3) {
        if (!this.start(var1, false)) {
            return 2;
        } else {
            if (var1.hasExtra(BackgroundTaskManager.TASK_ID_FIELD)) {
                int var5 = var1.getIntExtra(BackgroundTaskManager.TASK_ID_FIELD, 0);
                BackgroundTaskManager var10000 = BackgroundTaskManager.getInstance();
                BackgroundTaskService var10001 = this;
                Context var4 = this.getApplicationContext();
                a var6 = () -> {
                    this.finish(0L);
                };
                var10000.onStartJob(var4, var5, var6);
            } else {
                TSLog.logger.warn(TSLog.warn(Application.B("坛믤驑颣ቁ\uf015剁궴꣦ꯠ䐏꿟X纆\uec6f\ue82f빛붷℄싕\ue536쳬ⴜꓪჩ﹘碐㳔遨\uf20f\udb94ꎎ띋윳ึ㒃첖䇽헜嵃볶隖㯮")));
                this.finish(0L);
            }

            return 3;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
