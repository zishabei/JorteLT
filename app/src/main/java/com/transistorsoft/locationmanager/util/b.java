//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.annotation.TargetApi;
import android.app.job.JobScheduler;
import android.app.job.JobInfo.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.event.HeadlessEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Date;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

public class b {
    public static final String a = Application.B("箏찇厂ᴳ풰떑龰㜹顚\u05ee샪ﲾⲮꢍ訐柆쯉䅐蓨케\u9fec\ue21f䚱ꯀɭ憖〾馰⌀鰗지㈪轑");

    public b() {
    }

    public static void a(HeadlessEvent var0) {
        TSConfig var1;
        if ((var1 = TSConfig.getInstance(var0.getContext().getApplicationContext())).getEnableHeadless()) {
            EventBus var2;
            if (!(var2 = EventBus.getDefault()).hasSubscriberForEvent(HeadlessEvent.class)) {
                b(var0.getContext(), var1.getHeadlessJobService());
            }

            if (var2.hasSubscriberForEvent(HeadlessEvent.class)) {
                var2.post(var0);
            } else {
                TSLog.logger.warn(TSLog.warn(Application.B("复腓༼鳓괂碜偅\ue314䅙\ua7ee\udb4c䦬쎪縚惏\ue711⫍\ue0ca睚맞榳Φᤸ峰駣\ue525\uf693颩䕟㍢楌駙鈛") + var0.getName() + Application.B("奬腅༽鳂굏碘偙\ue314䅏Ɜ\udb18䦢쏸縏悀\ue70c⫖\ue0ca睞맒榡ζᤱ峻駵\ue524\uf6c0飢")));
            }

        }
    }

    private static boolean b(Context param0, String param1) {
        // $FF: Couldn't be decompiled
    }

    private static Class<?> a(Context var0, String var1) {
        try {
            return Class.forName(var1);
        } catch (ClassNotFoundException var2) {
            return Class.forName(var0.getPackageName() + Application.B("奢") + Application.B("夎腆༫鳝괈碞偞\ue304䅓Ɦ\udb7f䦦쏥縆惏\ue701⫘\ue09e睛맔榼Ίᤱ峴駴\ue53a\uf6d6颿䕚㍓楃駞鉐"));
        }
    }

    private static void a(Exception var0) {
        TSLog.logger.error(TSLog.error(var0.getMessage()));
        var0.printStackTrace();
    }

    public static void a(Context var0, String var1, JSONObject var2) {
    }

    @TargetApi(21)
    private static void c(Context var0, String var1, JSONObject var2) {
        JobScheduler var3;
        JobScheduler var10000 = var3 = (JobScheduler)var0.getSystemService(Application.B("웚ⱈˊ爽힉\ue492\udbbb簀瓚앃檫淎"));
        Context var10001 = var0;
        TSConfig var4 = TSConfig.getInstance(var0.getApplicationContext());

        IllegalArgumentException var15;
        label54: {
            boolean var16;
            PersistableBundle var10002;
            try {
                var10002 = new PersistableBundle;
            } catch (IllegalArgumentException var10) {
                var15 = var10;
                var16 = false;
                break label54;
            }

            PersistableBundle var5 = var10002;

            JSONObject var10003;
            try {
                var10003 = var2;
                var5.<init>();
                var5.putString(Application.B("웕ⱑˍ爠힞"), var1);
            } catch (IllegalArgumentException var9) {
                var15 = var9;
                var16 = false;
                break label54;
            }

            String var14 = Application.B("움ⱆ˚爯힇\ue489");

            int var17;
            try {
                var10002.putString(var14, var10003.toString());
                var17 = a(var10001.getPackageName(), var1);
            } catch (IllegalArgumentException var8) {
                var15 = var8;
                var16 = false;
                break label54;
            }

            int var13 = var17;

            Builder var18;
            try {
                var18 = (new Builder(var13, new ComponentName(var0, var4.getHeadlessJobService()))).setRequiredNetworkType(0).setRequiresDeviceIdle(false).setRequiresCharging(false).setOverrideDeadline(0L).setExtras(var5).setMinimumLatency(0L).setPersisted(false);
            } catch (IllegalArgumentException var7) {
                var15 = var7;
                var16 = false;
                break label54;
            }

            Builder var11 = var18;
            if (var10000 == null) {
                return;
            }

            try {
                var3.schedule(var11.build());
                return;
            } catch (IllegalArgumentException var6) {
                var15 = var6;
                var16 = false;
            }
        }

        IllegalArgumentException var12 = var15;
        TSLog.logger.error(TSLog.error(Application.B("웶ⱆˁ爢힟\ue488\udbbb籄瓍앝檡淝艅䲜槇㌞ꕾḳ꓅Ꝅ‣뚠ᕔ漣蚸恗苐枟帹㣸쎥攧盷范龌閭뚘䣒\ue005䥈䑃\ud7aeꊒ\uf626沘踮퓴\uf7cc\ue3a3\uea5cි踃鵾跥惋卫닙☦\uea6f") + var4.getHeadlessJobService() + Application.B("욺") + var12.getMessage()));
    }

    private static void b(Context var0, String var1, JSONObject var2) {
        Intent var10001 = new Intent();
        var10001.setAction(var0.getPackageName() + Application.B("⼲頟媹耯⊗⟚\uf0f5됿痾হ꽦岔诮\ueb74\uf07d揺႒↮ﳶ⚢쥰弤硡郤䘄") + var1.toUpperCase());
        Bundle var10003 = new Bundle();
        var10003.putString(Application.B("⽹頝媯耭⊌"), var1);
        var10003.putString(Application.B("⽬頊媸耢⊕⟊"), var2.toString());
        var10001.putExtras(var10003);
        var0.sendBroadcast(var10001);
    }

    private static int a(String var0, String var1) {
        return !var1.equalsIgnoreCase(Application.B("夠腈༫鳗괛碅偞\ue31f")) && !var1.equalsIgnoreCase(Application.B("夤腓༼鳆")) && !var1.equalsIgnoreCase(Application.B("夫腂༧鳐괊碂偒\ue314")) ? (var0 + Application.B("奡") + var1).hashCode() : (new Date()).hashCode();
    }
}
