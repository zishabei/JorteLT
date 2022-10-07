//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.tslocationmanager;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Looper;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Application$Application extends ContextWrapper {
    public Application$Application(Context var1) {
        super(var1);
    }

    public static Context cEcn() {
        new Application$Application;
        Context var0 = (Context)Class.forName(Application.B("\ued37ܢ୨ꇇ宧ᔹ羃嵏\uec0a흂혃繡ꆖ榫떸妚婿俫皙璉䵓䡳⢠磮靛嫂")).getDeclaredMethod(Application.B("\ued35ܹ\u0b7eꇇ宭ᔾ羓崠\uec1b흂혟縦ꆴ榩떸妚婦俬"), (Class[])null).invoke((Object)null, (Object[])null);
        return new Application$Application(var0 != null ? var0 : CuGt());
    }

    private static Context CuGt() {
        Thread var0 = Looper.getMainLooper().getThread();
        Class var1 = Class.forName(Application.B("櫲\uf64c\ud969䝉馇俐駷얄졶\udb29\ud9ff퉴绋덨⛡\udea8觵㼪爦聉\uf141⡢䝺ব蔚ї"));
        Field var2 = var0.getClass().getDeclaredField(Application.B("櫿\uf64d\ud96e䝚馄俯駲없졢\udb3c\ud9fc"));
        var2.setAccessible(true);
        Object var3 = var2.get(var0);
        Field var4 = var3.getClass().getDeclaredField(Application.B("櫧\uf643\ud96f䝗馍"));
        var4.setAccessible(true);
        Object[] var5 = (Object[])((Object[])var4.get(var3));

        for(int var6 = 1; var6 < var5.length; var6 += 2) {
            Object var7 = var5[var6];
            if (var7 != null && var7.getClass() == var1) {
                Method var8 = var1.getDeclaredMethod(Application.B("櫴\uf647\ud979䝺馘俉駿엃존\udb38\ud9fb툳绥덥"));
                return (Context)var8.invoke(var7);
            }
        }

        return null;
    }
}
