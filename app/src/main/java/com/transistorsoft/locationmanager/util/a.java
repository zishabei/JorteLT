//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.content.Context;
import android.content.res.Resources;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class a {
    private static Class<?> a;
    private static final AtomicBoolean b = new AtomicBoolean(false);
    private static final String c = Application.B("ᱩ趣꒮幰⩀琳셯㧗בּ돗궮");

    public a() {
    }

    public static Object a(Context var0, String var1) {
        return b(var0, var1);
    }

    static Class<?> a(Context var0) {
        if (!b.get()) {
            Context var10000 = var0;
            synchronized(b){}

            boolean var10001;
            Throwable var13;
            Class var14;
            try {
                var14 = a = d(var10000);
            } catch (Throwable var12) {
                var13 = var12;
                var10001 = false;
                throw var13;
            }

            if (var14 != null) {
                try {
                    b.set(true);
                } catch (Throwable var11) {
                    var13 = var11;
                    var10001 = false;
                    throw var13;
                }
            }

            try {
                return a;
            } catch (Throwable var10) {
                var13 = var10;
                var10001 = false;
                throw var13;
            }
        } else {
            return a;
        }
    }

    public static String b(Context var0) {
        Class var1;
        return (var1 = a(var0)) != null && var1.getPackage() != null ? var1.getPackage().getName() : var0.getPackageName();
    }

    private static String c(Context var0) {
        Resources var10000 = var0.getResources();
        String var1 = var0.getPackageName();
        int var2;
        return (var2 = var10000.getIdentifier(Application.B("ⶨ䞃桽蠅窻☲\ud995빦\uf1b3흥\ue82e낚酌⥅\ud955疛㭛뱆⌖\uf5dd"), Application.B("ⶹ䞂桦蠀窱☊"), var1)) != 0 ? var0.getString(var2) : var0.getPackageName();
    }

    private static synchronized Class<?> d(Context var0) {
        String var1 = c(var0);

        ClassNotFoundException var10000;
        boolean var10001;
        StringBuilder var7;
        StringBuilder var8;
        label43: {
            try {
                var7 = new StringBuilder;
            } catch (ClassNotFoundException var6) {
                var10000 = var6;
                var10001 = false;
                break label43;
            }

            var8 = var7;

            try {
                var8.<init>();
                return Class.forName(var7.append(var1).append(Application.B("췁")).append(Application.B("춭赢ɉ◆》妅芹ꔕ㸣箤쐮")).toString());
            } catch (ClassNotFoundException var5) {
                var10000 = var5;
                var10001 = false;
            }
        }

        ClassNotFoundException var2 = var10000;
        String[] var9 = var1.split(Application.B("춳费"));
        var1 = Util.joinString(Arrays.asList((String[])Arrays.copyOf(var9, var9.length - 1)), Application.B("췁"));

        label44: {
            try {
                var7 = new StringBuilder;
            } catch (ClassNotFoundException var4) {
                var10001 = false;
                break label44;
            }

            var8 = var7;

            try {
                var8.<init>();
                return Class.forName(var7.append(var1).append(Application.B("췁")).append(Application.B("춭赢ɉ◆》妅芹ꔕ㸣箤쐮")).toString());
            } catch (ClassNotFoundException var3) {
                var10001 = false;
            }
        }

        TSLog.logger.error(TSLog.error(Application.B("충赶ɉ◆《妢苶ꔏ㸪篭쐭莯崿\ueec8ꉕ嶮洗\uf7d3㱵蟠\ud837䋁洹\uf5ec♰䔾▵幄ꍙ\ue3d2륳ꏍၒ痈\uefdaꌅ㉳ƀ깄墯鷜韁㬑-솀ઈꦍ䏪㓜剳祇\uede2Ꮲᡦ꼃౸숟맲헉◂綇៲숲謨憎ಡ") + c(var0) + Application.B("췏赸ɒ▊") + var1));
        var2.printStackTrace();
        return null;
    }

    private static Object b(Context var0, String var1) {
        Class var10000 = a(var0);

        try {
            return var10000.getField(var1).get((Object)null);
        } catch (NoSuchFieldException var2) {
            TSLog.logger.error(TSLog.error(var2.getMessage()), var2);
        } catch (IllegalAccessException var3) {
            TSLog.logger.error(TSLog.error(var3.getMessage()), var3);
        }

        return null;
    }
}
