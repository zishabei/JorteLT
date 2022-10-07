//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.scheduler;

import android.content.Context;
import android.content.Intent;
import com.transistorsoft.locationmanager.adapter.BackgroundGeolocation;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.HttpFlushEvent;
import com.transistorsoft.locationmanager.event.LaunchForegroundServiceEvent;
import com.transistorsoft.locationmanager.event.MotionActivityCheckEvent;
import com.transistorsoft.locationmanager.event.MotionTriggerDelayEvent;
import com.transistorsoft.locationmanager.event.StartGeofencesEvent;
import com.transistorsoft.locationmanager.event.StopAfterElapsedMinutesEvent;
import com.transistorsoft.locationmanager.event.StopTimeoutEvent;
import com.transistorsoft.locationmanager.event.TerminateEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Calendar;
import java.util.Locale;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

public class ScheduleEvent {
    private final Boolean mEnabled;
    private final JSONObject mState;

    public ScheduleEvent(Boolean var1, JSONObject var2) {
        this.mState = var2;
        this.mEnabled = var1;
    }

    static void onScheduleEvent(Context var0, boolean var1, int var2) {
        TSLog.logger.debug("");
        TSConfig var3;
        if (!(var3 = TSConfig.getInstance(var0)).getSchedulerEnabled()) {
            TSScheduleManager.getInstance(var0).a();
            TSLog.logger.warn(TSLog.warn(Application.B("竅\ue62d鷐兺텼Ή뛍紟覬फ\uf049櫾㝭\uea2f㠑\ue6a2頄ﱟ꣘뷵\ue345ủጼ\udcf9\udb65伓鑭庭\u0ba0\uf390㬇밑㷯앾ಪ鸄\ueccc꡵吹㴎ﲒ㷸ረ墷쩽消뙗맬髛\uaac4\uda19﹡")));
        } else {
            BackgroundGeolocation var4 = BackgroundGeolocation.getInstance(var0, new Intent());
            TSLog.logger.info(TSLog.header(Application.B("ꊱ㪏鶞儵텝Ώ뛁絚覻ऽ\uf04d櫾㜩\uea3b㠑\ue6a6顖ﱓꢔ뷲\ue35eỸ፹\udcf8\udb32佖鐣庼௮\uf3d9㬖밞㷢앿\u0cf4鹑") + var1 + Application.B("窠\ue66a鷊內텯Ώ뛂絖覱य\uf06c櫴㝭\uea3f㡇\ue6e7") + var2));
            var3.setTrackingMode(var2);
            if (var1) {
                var4.startOnSchedule();
            } else {
                var4.stopOnSchedule();
            }

            Context var10000 = var0;
            EventBus var10001 = EventBus.getDefault();
            Boolean var5 = var1;
            var10001.post(new ScheduleEvent(var5, var3.toJson()));
            TSScheduleManager var6 = TSScheduleManager.getInstance(var10000);
            Calendar var7 = Calendar.getInstance(Locale.US);
            var6.a(var7, var3.getEnabled());
        }
    }

    static void onOneShot(Context var0, String var1, ScheduleEvent.Callback var2) {
        TSLog.logger.info(TSLog.header(Application.B("腦Ğ\uf0e3媳恮╻릗䗉굶帀캥ㄆ쭍痍\uf6b1ꨓ\uf529铌܄具ꐖ\u0be4풣") + var1));
        BackgroundGeolocation.getInstance(var0);
        if (var1.equalsIgnoreCase(Application.B("ꋂŻ\uf0fe媐恂╦릾䗲굇广캅ㄦ쭭痭\uf691"))) {
            new TerminateEvent(var0);
            var2.onFinish();
        } else if (var1.equalsIgnoreCase(Application.B("ꋛű\uf0f8媔恄╦릠䗧굁年캉ㄦ쭡痷\uf69cꩬ\uf50c铭ܳ兑ꐹ"))) {
            new MotionActivityCheckEvent(var0);
            var2.onFinish();
        } else if (var1.equalsIgnoreCase(Application.B("ꋅŪ\uf0e3媍恔╩릹䗲굇干캟ㄵ쭤痢\uf695ꩠ\uf50a铡ܩ兟ꐻஐ퓖雨\ue1bd磇"))) {
            new StopAfterElapsedMinutesEvent(var0);
            var2.onFinish();
        } else if (var1.equalsIgnoreCase(Application.B("ꋅŪ\uf0e3媍恔╼릶䗫굇幯캕ㄤ"))) {
            new StopTimeoutEvent(var0);
            var2.onFinish();
        } else if (var1.equalsIgnoreCase(Application.B("ꋛű\uf0f8媔恄╦릠䗲교幩캇ㄷ쭭痱\uf69a꩷\uf50a铩ܷ克"))) {
            new MotionTriggerDelayEvent(var0);
            var2.onFinish();
        } else if (var1.equalsIgnoreCase(Application.B("ꋅŪ\uf0ed媏恟╷릸䗣굍幦캅ㄾ쭫痦\uf696"))) {
            new StartGeofencesEvent(var0);
        } else if (var1.equalsIgnoreCase(Application.B("ꋚſ\uf0f9媓恈╠릠䗠굍干캅ㄷ쭺痬\uf690ꩽ\uf50b铺ܥ兗ꐠஈ퓊雿\ue1bd"))) {
            new LaunchForegroundServiceEvent(var0);
        } else if (var1.equalsIgnoreCase(Application.B("ꋾŊ\uf0d8媭恔╎릓䗓굱幈"))) {
            HttpFlushEvent.run(var0);
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("ꋃŐ\uf0c7媳恤╟릑䖆굍幎캥ㄣ쭀痌\uf6b1ꨓ\uf52a铓ܓ兼ꐆ\u0be4풣") + var1 + Application.B("ꊶĂ\uf0e5媚恅╧릭䗣굆帞")));
        }

        var2.onFinish();
    }

    public Boolean getEnabled() {
        return this.mEnabled;
    }

    public JSONObject getState() {
        return this.mState;
    }

    public interface Callback {
        void onFinish();
    }
}
