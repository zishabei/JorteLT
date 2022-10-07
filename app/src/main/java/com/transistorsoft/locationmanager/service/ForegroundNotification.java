//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat.BigTextStyle;
import androidx.core.app.NotificationCompat.Builder;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.config.TSNotification;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.notification.TSLocalNotification;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import com.transistorsoft.tslocationmanager.R.layout;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

public class ForegroundNotification {
    public static final int NOTIFICATION_ID = 9942585;
    public static final String NOTIFICATION_ACTION = Application.B("睨쥘䧋卛吹䀀⚞鮚砐璇뿏䷰ⱂ忒沼忯眃垹");
    static final String DEFAULT_TEMPLATE = Application.B("睢쥒䧙卓吪䀅⚉");
    static final String NOTIFICATION_ACTION_PAUSE = Application.B("睨쥘䧋卛吹䀀⚞鮚砐璇뿏䷰ⱡ忄沼忲眃垹莛觽ﺕퟢқ");
    private static final AtomicLong sStartedAt = new AtomicLong(0L);

    public ForegroundNotification() {
    }

    static void setStartedAt(long var0) {
        sStartedAt.set(var0);
    }

    public static Notification build(Context var0) {
        Builder var1;
        Builder var10000 = var1 = TSLocalNotification.build(var0);
        var10000.setOnlyAlertOnce(true);
        var10000.setSound((Uri)null);
        if (sStartedAt.get() > 0L) {
            var1.setWhen(sStartedAt.get());
        }

        if (TSConfig.isLoaded()) {
            TSConfig var2;
            var1.setPriority((var2 = TSConfig.getInstance(var0)).getNotification().getPriority());
            buildDefaultLayout(var0, var1);
            if (!var2.getNotification().getLayout().isEmpty()) {
                buildCustomLayout(var0, var1);
            }
        } else {
            buildEmptyLayout(var0, var1);
        }

        Notification var3 = var1.build();
        var3.flags |= 98;
        return var3;
    }

    private static void buildCustomLayout(Context var0, Builder var1) {
        TSConfig var10000 = TSConfig.getInstance(var0);
        ApplicationInfo var2 = var0.getApplicationInfo();
        TSNotification var3;
        String var4;
        int var8;
        if ((var4 = (var3 = var10000.getNotification()).getLayout()).equalsIgnoreCase(Application.B("衾ḑ\uf30b疏俅⩫끃"))) {
            var8 = layout.tslocationmanager_notification_layout;
        } else {
            var8 = var0.getResources().getIdentifier(var4, Application.B("衶ḕ\uf314疁俅⩳"), var0.getPackageName());
        }

        if (var8 == 0) {
            TSLog.logger.error(TSLog.error(Application.B("衙ḛ\uf318疂俔⨧끙桦젛熰譲䀈蕿㿘ꥀນ\ue30e휳ស\uf37d颎✔≢愽ᰓ렦羜앂ᖞ卶\u0af8ᩗ伳窷ᦣ砜鴯瓐秡敧ⶱ雉\u2e74") + var3.getLayout() + Application.B("蠽Ṕ\uf304疀侐⩦끇桹졀燣警䀂蔾㿑꤁\u0e93\ue315흯យ\uf377颐✛≠愳ᰞ렠羏앟")));
            buildDefaultLayout(var0, var1);
        } else {
            RemoteViews var5;
            var5 = new RemoteViews.<init>(var0.getPackageName(), var8);
            if (var8 == layout.tslocationmanager_notification_layout) {
                setOnClickPendingIntent(var0, var5, Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕓㿉ꤔ\u0e8e\ue314휮ុ\uf373颖❇≩"));
            }

            Resources var14 = var0.getResources();
            var4 = var0.getPackageName();
            if ((var8 = var14.getIdentifier(Application.B("衻Ḅ\uf31d疂俙⩤끖桽젆燿譺䀯蕰㿑꤅"), Application.B("衳Ḑ"), var4)) != 0) {
                int var6;
                String var12;
                if ((var6 = var2.labelRes) == 0) {
                    var12 = var2.nonLocalizedLabel.toString();
                } else {
                    var12 = var0.getString(var6);
                }

                var5.setTextViewText(var8, var12);
            }

            var14 = var0.getResources();
            var4 = var0.getPackageName();
            if ((var8 = var14.getIdentifier(Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕂㿑꤁ຖ\ue317휉ឈ\uf37d颍"), Application.B("衳Ḑ"), var4)) != 0) {
                var5.setImageViewResource(var8, TSLocalNotification.getSmallIcon(var0));
            }

            if (!(var4 = var3.getLargeIcon()).isEmpty()) {
                Bitmap var13;
                if ((var13 = BitmapFactory.decodeResource(var0.getResources(), var0.getResources().getIdentifier(var4, (String)null, var0.getPackageName()))) != null) {
                    var14 = var0.getResources();
                    var4 = var0.getPackageName();
                    if ((var8 = var14.getIdentifier(Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕝㿝ꤒຝ\ue31e휉ឈ\uf37d颍"), Application.B("衳Ḑ"), var4)) != 0) {
                        var5.setImageViewBitmap(var8, var13);
                    }
                } else {
                    TSLog.logger.warn(Application.B("衜ḕ\uf304疂俕⩣뀗桽저熰譲䀈蕿㿘ꥀດ\ue314휴គ\uf374颊❗≭愦ᰎ렠羔앧ᖜ卥૫ᩛ伕窺᧬砞鵴璉") + var4);
                }
            }

            var14 = var0.getResources();
            var4 = var0.getPackageName();
            String var7;
            if (var14.getIdentifier(Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕅㿕ꤔຖ\ue31e"), Application.B("衳Ḑ"), var4) != 0) {
                if ((var4 = var3.getTitle()).isEmpty()) {
                    if ((var8 = var2.labelRes) == 0) {
                        var7 = var2.nonLocalizedLabel.toString();
                    } else {
                        var7 = var0.getString(var8);
                    }
                } else {
                    var7 = var4;
                }

                setTextViewText(var0, var5, Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕅㿕ꤔຖ\ue31e"), var7);
            }

            var14 = var0.getResources();
            var7 = var0.getPackageName();
            if (var14.getIdentifier(Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕅㿙ꤘ\u0e8e"), Application.B("衳Ḑ"), var7) != 0) {
                var7 = var3.getText();
                setTextViewText(var0, var5, Application.B("衴ḛ\uf319疇俖⩮끔桨젛燹譻䀏蕅㿙ꤘ\u0e8e"), var7);
            }

            Map var9;
            Iterator var10;
            if (!(var9 = var3.getStrings()).isEmpty()) {
                var10 = var9.entrySet().iterator();

                while(var10.hasNext()) {
                    Entry var10002 = (Entry)var10.next();
                    var4 = var10002.getKey().toString();
                    setTextViewText(var0, var5, var4, var10002.getValue().toString());
                }
            }

            List var11;
            if (!(var11 = var3.getActions()).isEmpty()) {
                var10 = var11.iterator();

                while(var10.hasNext()) {
                    setOnClickPendingIntent(var0, var5, (String)var10.next());
                }
            }

            var1.setSmallIcon(TSLocalNotification.getSmallIcon(var0));
            var1.setCustomBigContentView(var5);
            if (VERSION.SDK_INT >= 26) {
                var1.setContent(var5);
            }

        }
    }

    private static void setTextViewText(Context var0, RemoteViews var1, String var2, String var3) {
        int var4;
        if ((var4 = var0.getResources().getIdentifier(var2, Application.B("铄룟"), var0.getPackageName())) != 0) {
            var1.setTextViewText(var4, var3);
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("铫룚泻旫繲\ua6fd轏㚻噧影\u0ffe㍆ཪ橌榭ഒ䟮\uf8ef繕볝\u009e\u312f┐문⏆ȋ碠㎋\ue890⸂꒛\uf4cd⚭鑋") + var2));
        }

    }

    private static void setOnClickPendingIntent(Context var0, RemoteViews var1, String var2) {
        int var3;
        if ((var3 = var0.getResources().getIdentifier(var2, Application.B("\ud964\u09d3"), var0.getPackageName())) != 0) {
            Intent var4;
            Intent var10000 = var4 = new Intent;
            var10000.<init>(var0, TrackingService.class);
            var10000.setAction(Application.B("\ud963\u09d8Ỗⱒ뜐盉쓇啶㔺庞ꠟ褝금躤⡫Â\ud8e7둀"));
            var10000.putExtra(Application.B("\ud964\u09d3"), var2);
            PendingIntent var6;
            if (VERSION.SDK_INT >= 26) {
                Context var7 = var0;
                int var5 = Util.getPendingIntentFlags(134217728);
                var6 = PendingIntent.getForegroundService(var7, var3, var4, var5);
            } else {
                var6 = PendingIntent.getService(var0, var3, var4, 134217728);
            }

            var1.setOnClickPendingIntent(var3, var6);
        } else {
            TSLog.logger.warn(TSLog.warn(Application.B("\ud94b\u09d6ịⱗ뜓盄쒄啣㔡廗ꠖ褚긇躣⠿é\ud8fd둚茵傖毝㎪櫐疨◌銊ꭉ\u0eec絃\u0e6d\ue55b鱱喡ꗁぃ\ue38b봗큼㸐\uf888꩘뜂\uf5f9쿝솘\uec0cො⨐\uf178캣⫄㲏톚ᾠɌ礞䷂ꝧ澔䠝ᔹ⚀✟졾껳ꖔ笋䤏똹ᜒ튧풌氈쳸嚲㬷ᰒ嬮௩") + var2));
        }

    }

    private static void buildEmptyLayout(Context var0, Builder var1) {
        ApplicationInfo var2;
        int var3;
        String var4;
        if ((var3 = (var2 = var0.getApplicationInfo()).labelRes) == 0) {
            var4 = var2.nonLocalizedLabel.toString();
        } else {
            var4 = var0.getString(var3);
        }

        var1.setContentTitle(var4);
        var1.setSmallIcon(TSLocalNotification.getSmallIcon(var0));
    }

    private static void buildDefaultLayout(Context var0, Builder var1) {
        TSConfig var10000 = TSConfig.getInstance(var0);
        TSNotification var2 = var10000.getNotification();
        ApplicationInfo var3 = var0.getApplicationInfo();
        TSNotification var4;
        String var5;
        String var8;
        if ((var5 = (var4 = var10000.getNotification()).getTitle()).isEmpty()) {
            int var9;
            if ((var9 = var3.labelRes) == 0) {
                var8 = var3.nonLocalizedLabel.toString();
            } else {
                var8 = var0.getString(var9);
            }
        } else {
            var8 = var5;
        }

        var1.setContentTitle(var8);
        var1.setContentText(var4.getText());
        var1.setStyle((new BigTextStyle()).bigText(var4.getText()));
        var1.setSmallIcon(TSLocalNotification.getSmallIcon(var0));
        if (!(var8 = var4.getLargeIcon()).isEmpty()) {
            Bitmap var6;
            if ((var6 = BitmapFactory.decodeResource(var0.getResources(), var0.getResources().getIdentifier(var8, (String)null, var0.getPackageName()))) != null) {
                var1.setLargeIcon(var6);
            } else {
                TSLog.logger.warn(Application.B("刿엢싲功\udd68Ɇ\uf7ea௵隅댺䐠բ⾒둏뷕姡ଈ\uf1e0첪ㆌ\ue478ᢀ㐎\ue3dd墳甝囻⩘艍ꫝ䠖ꏵ\uf512ḫￆ\udf61㣤ҝ") + var8);
            }
        }

        String var7;
        if (!(var7 = var2.getColor()).isEmpty() && VERSION.SDK_INT >= 21) {
            var1.setColor(Color.parseColor(var7));
        }

    }

    public static void createNotificationChannel(Context var0) {
        NotificationManager var1;
        if (VERSION.SDK_INT >= 26 && (var1 = (NotificationManager)var0.getSystemService(Application.B("仔㔈ꄓ귟ᓣ鱥\u218f操〫㴃ᅔܲ"))) != null) {
            String var2 = var0.getPackageName() + Application.B("仮㔴ꄫ귙ᓦ鱭↘擅〰㴄ᅶܽ⻱聘䄆£넥");
            String var3 = Application.B("仮㔴ꄫ귙ᓦ鱭↘擅〰㴄ᅶܽ⻱聘䄆£넥");
            if (var0.getApplicationInfo().labelRes != 0) {
                var3 = var0.getString(var0.getApplicationInfo().labelRes);
            }

            TSConfig var4;
            if (TSConfig.isLoaded() && !(var4 = TSConfig.getInstance(var0)).getNotification().getChannelName().isEmpty()) {
                var3 = var4.getNotification().getChannelName();
            }

            NotificationChannel var5;
            NotificationChannel var10001 = var5 = new NotificationChannel;
            var10001.<init>(var2, var3, 1);
            var10001.setShowBadge(false);
            var10001.enableLights(false);
            var10001.setSound((Uri)null, (AudioAttributes)null);
            var10001.enableVibration(false);
            var10001.setLockscreenVisibility(-1);
            var1.createNotificationChannel(var5);
        }

    }
}
