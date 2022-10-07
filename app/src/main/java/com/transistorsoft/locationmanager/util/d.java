//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.content.Context;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class d implements Runnable {
    private static final String d = Application.B("\ud8d7⼜髹\uf3a8ꕮʚ톎䏥뜗델㢯殑⚟㥋濧ꂣ⏶ꪈ琇缠");
    private static final String e = Application.B("\ud8db⼓髶\uf3bfꕕʖ톒䎿");
    private static final String f = Application.B("\ud8c5⼚髲\uf3ad");
    private static final int g = 1;
    private String a;
    private final String b;
    private final Context c;

    public d(Context var1, String var2, String var3) {
        this.b = var2 + Application.B("ꖮ") + var3;
        this.c = var1;
    }

    public void run() {
        ClassNotFoundException var47;
        label160: {
            NoSuchMethodException var46;
            label161: {
                IllegalAccessException var45;
                label162: {
                    InvocationTargetException var10000;
                    label145: {
                        boolean var10001;
                        Class var48;
                        try {
                            var48 = Class.forName(Application.B("䁮邏깾濇\uef2d囲嚍၃㦂咎⓱ꔱ踯ݍᩘ⑊䢳䌦ꛝ騫"));
                        } catch (ClassNotFoundException var35) {
                            var47 = var35;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var36) {
                            var46 = var36;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var37) {
                            var45 = var37;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var38) {
                            var10000 = var38;
                            var10001 = false;
                            break label145;
                        }

                        Class var1;
                        Class var49 = var1 = var48;

                        Class[] var10002;
                        try {
                            var10002 = new Class[3];
                        } catch (ClassNotFoundException var31) {
                            var47 = var31;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var32) {
                            var46 = var32;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var33) {
                            var45 = var33;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var34) {
                            var10000 = var34;
                            var10001 = false;
                            break label145;
                        }

                        Class[] var10003 = var10002;
                        Class[] var2;
                        Class[] var10004 = var2 = var10002;
                        byte var10005 = 0;

                        try {
                            var10004[var10005] = Context.class;
                        } catch (ClassNotFoundException var27) {
                            var47 = var27;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var28) {
                            var46 = var28;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var29) {
                            var45 = var29;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var30) {
                            var10000 = var30;
                            var10001 = false;
                            break label145;
                        }

                        byte var53 = 1;

                        try {
                            var10003[var53] = CharSequence.class;
                        } catch (ClassNotFoundException var23) {
                            var47 = var23;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var24) {
                            var46 = var24;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var25) {
                            var45 = var25;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var26) {
                            var10000 = var26;
                            var10001 = false;
                            break label145;
                        }

                        byte var51 = 2;

                        Method var56;
                        try {
                            var10002[var51] = Integer.TYPE;
                            var56 = var49.getMethod(Application.B("䁢邀깱濐\uef16图嚑မ"), var2);
                        } catch (ClassNotFoundException var19) {
                            var47 = var19;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var20) {
                            var46 = var20;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var21) {
                            var45 = var21;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var22) {
                            var10000 = var22;
                            var10001 = false;
                            break label145;
                        }

                        Method var44 = var56;
                        String var57 = Application.B("䁼邉깵濂");

                        Class var50;
                        Object[] var52;
                        Method var58;
                        try {
                            var58 = var48.getMethod(var57);
                            var56 = var44;
                            var50 = var1;
                            var52 = new Object[3];
                        } catch (ClassNotFoundException var15) {
                            var47 = var15;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var16) {
                            var46 = var16;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var17) {
                            var45 = var17;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var18) {
                            var10000 = var18;
                            var10001 = false;
                            break label145;
                        }

                        Object[] var54 = var52;
                        Object[] var55 = var52;
                        Object[] var10007 = var52;
                        d var10006 = this;
                        d var10008 = this;
                        byte var39 = 0;

                        try {
                            var10007[var39] = var10008.c;
                        } catch (ClassNotFoundException var11) {
                            var47 = var11;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var12) {
                            var46 = var12;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var13) {
                            var45 = var13;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var14) {
                            var10000 = var14;
                            var10001 = false;
                            break label145;
                        }

                        var39 = 1;

                        try {
                            var55[var39] = var10006.b;
                        } catch (ClassNotFoundException var7) {
                            var47 = var7;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var8) {
                            var46 = var8;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var9) {
                            var45 = var9;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var10) {
                            var10000 = var10;
                            var10001 = false;
                            break label145;
                        }

                        var10005 = 2;

                        try {
                            var54[var10005] = 1;
                            var58.invoke(var56.invoke(var50, var52));
                            return;
                        } catch (ClassNotFoundException var3) {
                            var47 = var3;
                            var10001 = false;
                            break label160;
                        } catch (NoSuchMethodException var4) {
                            var46 = var4;
                            var10001 = false;
                            break label161;
                        } catch (IllegalAccessException var5) {
                            var45 = var5;
                            var10001 = false;
                            break label162;
                        } catch (InvocationTargetException var6) {
                            var10000 = var6;
                            var10001 = false;
                        }
                    }

                    InvocationTargetException var40 = var10000;
                    TSLog.logger.error(TSLog.error(var40.getMessage()));
                    return;
                }

                IllegalAccessException var41 = var45;
                TSLog.logger.error(TSLog.error(var41.getMessage()));
                return;
            }

            NoSuchMethodException var42 = var46;
            TSLog.logger.error(TSLog.error(var42.getMessage()));
            return;
        }

        ClassNotFoundException var43 = var47;
        TSLog.logger.error(TSLog.error(var43.getMessage()));
    }
}
