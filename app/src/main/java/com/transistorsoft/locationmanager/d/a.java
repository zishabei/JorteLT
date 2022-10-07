//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.d;

import androidx.annotation.Nullable;

class a {
    public static final int i = 0;
    public static final int j = 1;
    public static final int k = 2;
    public static final int l = 3;
    public static final int m = 4;
    public static final int n = 5;
    public static final int o = 6;
    public int a;
    public String b;
    public Object c;
    public Object d;
    public Object e;
    public String f = null;
    public Boolean g = false;
    public Boolean h = false;

    public a(String var1, int var2, Object var3) {
        this.a(var1, var2, var3);
    }

    public a(String var1, int var2, Object var3, @Nullable Object var4, @Nullable String var5, @Nullable Boolean var6, @Nullable Boolean var7) {
        this.a(var1, var2, var3);
        this.f = var5;
        if (var6 != null) {
            this.g = var6;
        }

        if (var7 != null) {
            this.h = var7;
        }

        if (var4 != null) {
            this.d = var4;
        }

    }

    private void a(String var1, int var2, Object var3) {
        this.b = var1;
        this.a = var2;
        this.c = var3;
        this.e = var3;
    }

    public void a(Object var1) {
        this.e = var1;
    }

    public Object a() {
        return this.e;
    }
}
