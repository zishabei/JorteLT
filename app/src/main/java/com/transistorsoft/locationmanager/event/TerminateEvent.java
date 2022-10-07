//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import android.content.Context;
import android.content.Intent;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager;
import com.transistorsoft.locationmanager.lifecycle.LifecycleManager.b;
import com.transistorsoft.locationmanager.location.TSLocationManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.c;
import com.transistorsoft.tslocationmanager.Application;
import org.json.JSONObject;

public class TerminateEvent {
    public static final String ACTION = Application.B("০䩲㰠獦蝐磸뛚\uef38麟˄펮膷贇⎮\u0d49");

    public TerminateEvent(final Context var1) {
        final BackgroundGeolocation var2 = BackgroundGeolocation.getInstance(var1.getApplicationContext(), new Intent());
        LifecycleManager.f().a(new b() {
            public void a(boolean var1x) {
                if (!var1x) {
                    TSLog.logger.debug(TSLog.info(Application.B("䚔灠饪鴤଼由堦\ue3cc逥\ua8c9￬샴蟙\uf7d8㰇쫟䭦∓ˎ톟ᔤ䢼四丶嚥민簨\u0be0\udc71蚿骱⯏\uef72胣㘻優\uf4fe抣힎畊ﯩ➎㨈늦ʥ\uf46d딘즟\ue7f4ಗ咦\ufbc4ᵨ侗萊")));
                } else {
                    TSConfig var6;
                    if ((var6 = TSConfig.getInstance(var1.getApplicationContext())).getStopOnTerminate()) {
                        var2.onActivityDestroy();
                    } else {
                        boolean var2x = c.b(var1);
                        boolean var3 = TSLocationManager.getInstance(var1).isUpdatingLocation();
                        boolean var4 = var6.getUseSignificantChangesOnly();
                        if (var6.getEnabled() && !var2x && (!var3 || var4)) {
                            TSLog.logger.warn(c.d(var1));
                        }

                        if (var6.getEnableHeadless()) {
                            Context var5 = var1.getApplicationContext();
                            JSONObject var7 = var6.toJson();
                            com.transistorsoft.locationmanager.util.b.a(new HeadlessEvent(var5, Application.B("䚴灀饊鴄ଜ甑堆\ue3ec逅"), var7));
                        }
                    }

                }
            }
        });
    }
}
