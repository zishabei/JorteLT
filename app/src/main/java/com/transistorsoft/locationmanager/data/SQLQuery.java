//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.data;

import ch.qos.logback.classic.db.names.ColumnName;
import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Map;

public class SQLQuery {
    public static int ORDER_ASC;
    public static int ORDER_DESC;
    public static String FIELD_START = Application.B("갸ꉔ巭㣬\udacc");
    public static String FIELD_END = Application.B("갮ꉎ巨");
    public static String FIELD_ORDER = Application.B("갤ꉒ巨㣻\udaca");
    public static String FIELD_LIMIT = Application.B("갧ꉉ巡㣷\udacc");
    private long a;
    private long b;
    private int c;
    private int d;

    public SQLQuery() {
        this.c = ORDER_ASC;
    }

    public static SQLQuery create() {
        return new SQLQuery();
    }

    public static SQLQuery fromMap(Map var0) {
        SQLQuery var1;
        var1 = new SQLQuery.<init>();
        if (var0.containsKey(FIELD_START)) {
            var1.setStart((Long)var0.get(FIELD_START));
        }

        if (var0.containsKey(FIELD_END)) {
            var1.setEnd((Long)var0.get(FIELD_END));
        }

        if (var0.containsKey(FIELD_ORDER)) {
            var1.setOrder((Integer)var0.get(FIELD_ORDER));
        }

        if (var0.containsKey(FIELD_LIMIT)) {
            var1.setLimit((Integer)var0.get(FIELD_LIMIT));
        }

        return var1;
    }

    public SQLQuery setStart(long var1) {
        this.a = var1;
        return this;
    }

    public long getStart() {
        return this.a;
    }

    public SQLQuery setEnd(long var1) {
        this.b = var1;
        return this;
    }

    public long getEnd() {
        return this.b;
    }

    public SQLQuery setOrder(int var1) {
        int var2;
        if (var1 == (var2 = ORDER_ASC) || var1 == ORDER_DESC) {
            var2 = var1;
        }

        this.c = var2;
        return this;
    }

    public int getOrder() {
        return this.c;
    }

    public SQLQuery setLimit(int var1) {
        this.d = var1;
        return this;
    }

    public int getLimit() {
        return this.d;
    }

    public String toString() {
        return Application.B("㖔⺳礸坡ᴅﭳ\uea03拌芨屚") + FIELD_START + Application.B("㗲") + this.a + Application.B("㗯") + FIELD_END + Application.B("㗲") + this.b + Application.B("㗯") + FIELD_ORDER + Application.B("㗲") + this.c + Application.B("㗯") + FIELD_LIMIT + Application.B("㗲") + this.d + Application.B("㖒");
    }

    public String getSelection(DefaultDBNameResolver var1) {
        if (this.a == 0L && this.b == 0L) {
            return null;
        } else {
            ArrayList var2;
            var2 = new ArrayList.<init>();
            if (this.a > 0L) {
                var2.add(var1.getColumnName(ColumnName.TIMESTMP) + Application.B("ﮜ\ud92a\ued1d峖") + this.a);
            }

            if (this.b > 0L) {
                var2.add(var1.getColumnName(ColumnName.TIMESTMP) + Application.B("ﮜ\ud928\ued1d峖") + this.b);
            }

            String var3 = "" + (String)var2.get(0);
            if (var2.size() > 1) {
                var3 = var3 + Application.B("ﮜ\ud955\ued6e岲\udc14") + (String)var2.get(1);
            }

            return var3;
        }
    }
}
