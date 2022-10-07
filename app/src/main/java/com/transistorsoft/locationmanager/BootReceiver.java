//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.BootEvent;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import org.greenrobot.eventbus.EventBus;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    private void a(Context var1, Intent var2) {
        TSConfig var4 = TSConfig.getInstance(var1.getApplicationContext());
        BackgroundGeolocation var3 = BackgroundGeolocation.getInstance(var1);
        EventBus.getDefault().post(new BootEvent(var1, var2));
        if (LifecycleManager.f().b()) {
            if (var4.getEnabled() && (var4.getStopOnTerminate() || !var4.getStartOnBoot())) {
                var4.setEnabled(false);
            }

            if (var4.getStopOnTerminate()) {
                TSLog.logger.warn(TSLog.warn(Application.B("䷐ᒨ著썖퇰Ŵ蘫丶ꍲ\ue908禌号\uf694욜\ua638৩㕉\uf3d0͆\uf359諲綝\ueb57蠻蓃怲篢\u0383ȧ茲漜럦酺⚨乣\ud98b㌈ܼ߮⟊嵳㝤箂貜詾⊴誸菓ꭴ\uf6da猨糃与錑రꓵ\uf060＋\ue9da퉱\uefd9疭큈\uf5d7ᷙ菧뿫\ude25䙽ờ\uaa3e決켻圎理⊰㪪\ue162緎\uf84d\udab0䝥㨼췁簢䨵㬯餾ꮀ丒雵䏓쎩৹樽")));
            } else if (var4.getStartOnBoot()) {
                if (var4.getSchedulerEnabled()) {
                    var3.startSchedule();
                }

                if (var4.getEnabled()) {
                    var3.startOnBoot();
                }
            }

        }
    }

    public void onReceive(final Context var1, final Intent var2) {
        BackgroundGeolocation.getThreadPool().execute(new Runnable() {
            public void run() {
                TSConfig var1x = TSConfig.getInstance(var1.getApplicationContext());
                String var2x;
                String var10000 = var2x = var2.getAction();
                StringBuilder var3;
                StringBuilder var10001 = var3 = new StringBuilder;
                var3.<init>();
                var3.append(TSLog.header(Application.B("舜ѱ؍濡䌷⪬趔\uda59ᬎ籱ᏹ䲔\ueafc\u0df5") + var1.getPackageName()));
                var10001.append(TSLog.boxRow(var2x));
                TSLog.logger.info(var3.toString());
                if (var10000 == null) {
                    TSLog.warn(TSLog.warn(Application.B("舜ѱ؍濡䌷⪬趔\uda59ᬎ籱ᏹ䲔\ueae6ධ炂㏗硘晏ᘗḣ\u1ac0ꅒґ⺨㭗持糝᪑ꁻ哛䊭ቪ釓ヌᇔ쇐탟ⷤ绐퉨駃\uea00炠៩")));
                } else {
                    Context var4;
                    BootReceiver var5;
                    <undefinedtype> var6;
                    if (var2x.equalsIgnoreCase(Application.B("舿Ѱ؆濧䌊⪠趓\uda12ᬎ籩Ꮸ䲃\ueaa8ඡ烔㏓硘晎ᘊḩ\u1acaꅜҤ⺎㭬挽粢᪼ꁛ咶䊴ቈ釢ヽᇿ쇠"))) {
                        var1x.setDidDeviceReboot(true);
                        var5 = BootReceiver.this;
                        var6 = this;
                        var4 = var1;
                        var5.a(var4, var2);
                    } else if (var2x.equalsIgnoreCase(Application.B("舿Ѱ؆濧䌊⪠趓\uda12ᬎ籩Ꮸ䲃\ueaa8ඡ烔㏓硘晎ᘊḩ\u1acaꅜҫ⺘㭼挹粼᪼ꁟ咺䊣ቁ釸・ᇿ쇴킳ⷄ绰퉙駮"))) {
                        var5 = BootReceiver.this;
                        var6 = this;
                        var4 = var1;
                        var5.a(var4, var2);
                    }

                }
            }
        });
    }
}
