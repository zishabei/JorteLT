//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import com.transistorsoft.tslocationmanager.Application;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

public class e {
    private final String a;

    public e(String var1) {
        this.a = var1;
    }

    private static String a(String var0) {
        return Application.B("떅赱襆鋋‚댋垎\u0de3ࠤ᳷퀬㱁䐀艷");
    }

    private static String a(Reader var0) {
        StringBuilder var1;
        var1 = new StringBuilder.<init>();

        int var2;
        for(; (var2 = var0.read()) != -1; var1.append((char)var2)) {
            if (var2 == 37) {
                int var3;
                if ((var3 = var0.read()) == -1) {
                    throw new IllegalStateException(Application.B("떣赂襱鋯›댽垷ුࠞᲁ퀨㱂䐓舜"));
                }

                if (var3 == 62) {
                    return var1.toString().replace(Application.B("뗳"), "");
                }

                var1.append((char)var3);
            }
        }

        throw new IllegalStateException(Application.B("떣赂襱鋯›댽垷ුࠞᲁ퀨㱂䐓舜"));
    }

    public String a(HashMap<String, String> var1) {
        StringReader var2;
        var2 = new StringReader.<init>(this.a);
        StringWriter var6;
        var6 = new StringWriter.<init>();

        int var3;
        while((var3 = var2.read()) != -1) {
            if (var3 == 60) {
                int var4;
                if ((var4 = var2.read()) == -1) {
                    break;
                }

                if (var4 == 37) {
                    int var5;
                    if ((var5 = var2.read()) == -1) {
                        break;
                    }

                    if (var5 == 61) {
                        String var7;
                        if (!var1.containsKey(var7 = a((Reader)var2))) {
                            throw new IllegalArgumentException(Application.B("떆赞西鋬‴댾垬ආࠏ᳄퀀㱽䐹艓癈䳹냜ꛡ粋㤽㳡昼洆媿珱셐䴧") + var7);
                        }

                        var6.append((CharSequence)var1.get(var7));
                    } else {
                        var6.write(var3);
                        var6.write(var4);
                        var6.write(var5);
                    }
                } else {
                    var6.write(var3);
                    var6.write(var4);
                }
            } else {
                var6.write(var3);
            }
        }

        return var6.toString();
    }
}
