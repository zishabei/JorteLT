//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.scheduler;

import com.transistorsoft.tslocationmanager.Application;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class Schedule {
    private static final SimpleDateFormat f = new SimpleDateFormat(Application.B("堬は踞掆맽"));
    private static final Pattern g = Pattern.compile(Application.B("堸ぃ蹟揟맭࡙춧ﵘᄁ쟞\uebd3奞ﶈ䭼済ᛈ謢펻邥"));
    private static final String h = Application.B("堃あ蹋掍맵ࠚ춘ﵙ");
    private static final String i = Application.B("堈え蹇掊매ࠝ추ﵒ");
    public int a;
    public Calendar b;
    public Calendar c;
    private final List<Integer> d;
    private boolean e;

    public Schedule(String var1) {
        Schedule var10000 = this;
        Schedule var10001 = this;
        Schedule var10002 = this;
        super();
        ArrayList var2;
        var2 = new ArrayList.<init>();
        var10002.d = var2;
        var10001.e = false;
        var10000.a(var1);
    }

    private void a(String var1) {
        String[] var4;
        String[] var10000 = var4 = var1.split(Application.B("牗"));
        this.b = Calendar.getInstance(Locale.US);
        this.c = Calendar.getInstance(Locale.US);
        String[] var2;
        int var6;
        if (var10000[0].contains(Application.B("牚"))) {
            if (g.matcher(var4[0]).matches()) {
                this.e = true;
                var2 = var4[0].split(Application.B("牚"));
                this.b.set(1, Integer.parseInt(var2[0]));
                this.b.set(2, Integer.parseInt(var2[1]) - 1);
                this.b.set(5, Integer.parseInt(var2[2]));
                if (g.matcher(var4[1]).matches()) {
                    String[] var3 = var4[1].split(Application.B("牚"));
                    this.c.set(1, Integer.parseInt(var3[0]));
                    this.c.set(2, Integer.parseInt(var3[1]) - 1);
                    this.c.set(5, Integer.parseInt(var3[2]));
                    var4[1] = var2[3] + Application.B("牚") + var3[3];
                } else {
                    this.c.set(1, this.b.get(1));
                    this.c.set(2, this.b.get(2));
                    this.c.set(5, this.b.get(5));
                }
            } else {
                var10000 = var4[0].split(Application.B("牚"));
                int var5 = Integer.parseInt(var10000[0]);

                for(var6 = Integer.parseInt(var10000[1]); var5 <= var6; ++var5) {
                    this.d.add(var5);
                }
            }
        } else {
            var2 = var4[0].split(Application.B("牛"));

            for(var6 = 0; var6 < var2.length; ++var6) {
                this.d.add(Integer.parseInt(var2[var6]));
            }
        }

        String[] var10005 = var2 = var4[1].split(Application.B("牚"));
        var2 = var2[0].split(Application.B("牍"));
        this.b.set(11, Integer.parseInt(var2[0]));
        this.b.set(12, Integer.parseInt(var2[1]));
        this.b.set(13, 0);
        this.b.set(14, 0);
        var2 = var10005[1].split(Application.B("牍"));
        this.c.set(11, Integer.parseInt(var2[0]));
        this.c.set(12, Integer.parseInt(var2[1]));
        this.c.set(13, 0);
        this.c.set(14, 0);
        if (this.e && this.c.before(this.b)) {
            this.c.add(6, 1);
        }

        if (var4.length > 2 && var4[2].contains(Application.B("爐됃㢅㸮ꮻ鹵맩쿢"))) {
            this.a = 0;
        } else {
            this.a = 1;
        }

    }

    public boolean a(Calendar var1) {
        if (!this.e) {
            this.b(var1);
            if (!this.a(var1.get(7))) {
                return false;
            }
        }

        return var1.before(this.c);
    }

    public boolean b() {
        return this.e;
    }

    public boolean a() {
        return Calendar.getInstance(Locale.US).after(this.c);
    }

    public void b(Calendar var1) {
        if (!this.e) {
            this.b.set(6, var1.get(6));
            this.b.set(1, var1.get(1));
            this.c.set(6, var1.get(6));
            this.c.set(1, var1.get(1));
            if (this.c.before(this.b)) {
                this.c.add(6, 1);
            }

        }
    }

    public Boolean a(int var1) {
        return this.d.contains(var1);
    }

    public String toString() {
        return Application.B("䖆臖果\ue571ׂ㋦\uda6b¶摛") + f.format(this.b.getTime()) + Application.B("䗸") + f.format(this.c.getTime()) + Application.B("䗹膕枰\ue575ן㋠\uda3dó") + this.d + Application.B("䗹膕枀\ue566ׇ㋰\uda6cº摮뙴繬䐑쑙\uea2f辗⍾") + this.a + Application.B("䖈");
    }
}
