//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.lifecycle;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LifecycleManager implements DefaultLifecycleObserver, Runnable {
    private static LifecycleManager i;
    private final List<LifecycleManager.b> a = new ArrayList();
    private final List<LifecycleManager.c> b = new ArrayList();
    private final Handler c = new Handler(Looper.getMainLooper());
    private Runnable d;
    private final AtomicBoolean e = new AtomicBoolean(true);
    private final AtomicBoolean f = new AtomicBoolean(true);
    private final AtomicBoolean g = new AtomicBoolean(false);
    private final AtomicBoolean h = new AtomicBoolean(false);

    public static LifecycleManager f() {
        if (i == null) {
            i = g();
        }

        return i;
    }

    private static synchronized LifecycleManager g() {
        if (i == null) {
            i = new LifecycleManager();
        }

        return i;
    }

    private LifecycleManager() {
        this.a((var0) -> {
            if (var0) {
                TSLog.logger.debug(TSLog.header(Application.B("쥬敪ꚿ윎㗬튇⬙\ue361量끭\uddb7㥬⢢돪\ua97d⩳钗挺") + var0));
            }

        });
    }

    private void b(boolean var1) {
        LifecycleManager var10000 = this;
        synchronized(this.b){}

        boolean var10001;
        Throwable var23;
        Iterator var24;
        try {
            var24 = var10000.b.iterator();
        } catch (Throwable var22) {
            var23 = var22;
            var10001 = false;
            throw var23;
        }

        Iterator var2 = var24;

        while(true) {
            boolean var25;
            try {
                var25 = var2.hasNext();
            } catch (Throwable var20) {
                var23 = var20;
                var10001 = false;
                break;
            }

            if (!var25) {
                try {
                    return;
                } catch (Throwable var19) {
                    var23 = var19;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((LifecycleManager.c)var2.next()).a(var1);
            } catch (Throwable var21) {
                var23 = var21;
                var10001 = false;
                break;
            }
        }

        throw var23;
    }

    private void e() {
        Runnable var1;
        if ((var1 = this.d) != null) {
            this.c.removeCallbacks(var1);
            this.d = null;
        }

        LifecycleManager var10000 = this;
        synchronized(this.a){}

        boolean var10001;
        Throwable var23;
        Iterator var24;
        try {
            var24 = var10000.a.iterator();
        } catch (Throwable var22) {
            var23 = var22;
            var10001 = false;
            throw var23;
        }

        Iterator var2 = var24;

        while(true) {
            boolean var25;
            try {
                var25 = var2.hasNext();
            } catch (Throwable var20) {
                var23 = var20;
                var10001 = false;
                break;
            }

            if (!var25) {
                try {
                    this.a.clear();
                    return;
                } catch (Throwable var19) {
                    var23 = var19;
                    var10001 = false;
                    break;
                }
            }

            try {
                ((LifecycleManager.b)var2.next()).a(this.f.get());
            } catch (Throwable var21) {
                var23 = var21;
                var10001 = false;
                break;
            }
        }

        throw var23;
    }

    public void c() {
        this.h.set(true);
    }

    public void d() {
        this.h.set(false);
    }

    public boolean a() {
        return this.e.get();
    }

    public boolean b() {
        return this.f.get();
    }

    public void a(boolean var1) {
        this.f.set(var1);
        if (this.f.get()) {
            TSLog.logger.debug(TSLog.header(Application.B("蠳뉃ꨯ譪踴℆㫰⯏\ueb70壅\uef98핶ᨭ将还귉뫶耘") + this.f));
        }

        Runnable var2;
        if ((var2 = this.d) != null) {
            this.c.removeCallbacks(var2);
            this.g.set(true);
            this.e();
        }

    }

    public void a(LifecycleManager.b var1) {
        if (this.g.get()) {
            var1.a(this.f.get());
        } else {
            List var2;
            List var10000 = var2 = this.a;
            LifecycleManager var10001 = this;
            synchronized(var2){}

            Throwable var9;
            boolean var10;
            try {
                var10001.a.add(var1);
            } catch (Throwable var8) {
                var9 = var8;
                var10 = false;
                throw var9;
            }

            try {
                ;
            } catch (Throwable var7) {
                var9 = var7;
                var10 = false;
                throw var9;
            }
        }
    }

    public void a(LifecycleManager.c var1) {
        List var2;
        List var10000 = var2 = this.b;
        LifecycleManager var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.b.add(var1);
        } catch (Throwable var8) {
            var9 = var8;
            var10 = false;
            throw var9;
        }

        try {
            ;
        } catch (Throwable var7) {
            var9 = var7;
            var10 = false;
            throw var9;
        }
    }

    public void run() {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void onCreate(@NonNull LifecycleOwner var1) {
        TSLog.logger.debug(Application.B("Ѻⓟ㳦\uddfd匀\uddeb袑\ude16뢂᪦䘫싹"));
        Runnable var2;
        Runnable var10004 = var2 = new Runnable() {
            public void run() {
                LifecycleManager.this.g.set(true);
                LifecycleManager.this.e();
            }
        };
        var10004.<init>();
        this.d = var10004;
        this.c.postDelayed(var2, 50L);
        this.f.set(true);
        this.e.set(true);
    }

    public void onStart(@NonNull LifecycleOwner var1) {
        TSLog.logger.debug(Application.B("ὥၣ蒯㊢떣ॉ\uf0d9ᶎ主Ś밦"));
        if (!this.h.get()) {
            Runnable var2;
            if ((var2 = this.d) != null) {
                this.c.removeCallbacks(var2);
            }

            this.g.set(true);
            this.f.set(false);
            this.e.set(false);
            this.e();
        }
    }

    public void onDestroy(@NonNull LifecycleOwner var1) {
        TSLog.logger.debug(Application.B("啍쳔ሒባ禆崙朾瑳\uee74紦뤩㷁癦"));
        this.e.set(true);
        this.f.set(true);
    }

    public void onStop(@NonNull LifecycleOwner var1) {
        TSLog.logger.debug(Application.B("픖㭯\uf593魽\udac6稴瓜篜褭揖"));
        if (!this.h.compareAndSet(true, false)) {
            this.e.set(true);
        }
    }

    public void onPause(@NonNull LifecycleOwner var1) {
        TSLog.logger.debug(Application.B("湷涥䤭빚\udee1㮓鐻뫃ᗆ嵔莞"));
        this.e.set(true);
        this.b(false);
    }

    public void onResume(@NonNull LifecycleOwner var1) {
        TSLog.logger.debug(Application.B("貨ᕓ瑧頍\uee8c㹛⑴䜘폭\uef64壉貋"));
        if (!this.h.get()) {
            this.e.set(false);
            this.f.set(false);
            this.b(true);
        }
    }

    public interface c {
        void a(boolean var1);
    }

    public interface b {
        void a(boolean var1);
    }
}
