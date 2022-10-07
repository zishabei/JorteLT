//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.PersistableBundle;
import androidx.annotation.RequiresApi;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.scheduler.ScheduleEvent.Callback;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager;
import com.transistorsoft.locationmanager.util.BackgroundTaskManager.a;
import com.transistorsoft.tslocationmanager.Application;

@RequiresApi(
        api = 22
)
public class ScheduleJobService extends JobService {
    public ScheduleJobService() {
    }

    public boolean onStartJob(final JobParameters var1) {
        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
            public void run() {
                PersistableBundle var1x;
                if ((var1x = var1.getExtras()).containsKey(Application.B("㐜\uf6aaꖐ怣ꍄ\uf082頺"))) {
                    ScheduleEvent.onOneShot(ScheduleJobService.this.getApplicationContext(), var1x.getString(Application.B("㐲\uf687ꖡ怙ꍣ\uf0a3"), ""), new Callback() {
                        public void onFinish() {
                            <undefinedtype> var1x;
                            ScheduleJobService.this.jobFinished(var1, false);
                        }
                    });
                } else if (var1x.containsKey(BackgroundTaskManager.ACTION)) {
                    int var3 = var1x.getInt(BackgroundTaskManager.TASK_ID_FIELD);
                    BackgroundTaskManager var10000 = BackgroundTaskManager.getInstance();
                    Context var10001 = ScheduleJobService.this.getApplicationContext();
                    int var10002 = var3;
                    a var4;
                    var4 = new a() {
                        public void onFinish() {
                            <undefinedtype> var1x;
                            ScheduleJobService.this.jobFinished(var1, false);
                        }
                    }.<init>();
                    var10000.onStartJob(var10001, var10002, var4);
                } else {
                    boolean var5 = var1.getExtras().getBoolean(Application.B("㐶\uf68aꖴ怒ꍠ\uf0a8頊"));
                    int var2 = var1.getExtras().getInt(Application.B("㐧\uf696ꖴ怓ꍧ\uf0a4頀阣騩\ua7df\u1cc8球"), 1);
                    ScheduleEvent.onScheduleEvent(ScheduleJobService.this.getApplicationContext(), var5, var2);
                    ScheduleJobService.this.jobFinished(var1, false);
                }

            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters var1) {
        TSLog.logger.debug("");
        return true;
    }
}
