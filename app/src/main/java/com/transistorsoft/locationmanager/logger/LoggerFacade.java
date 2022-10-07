//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.logger;

import ch.qos.logback.classic.Level;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.Marker;

public class LoggerFacade implements Logger {
    private final List<LoggerFacade.a> mEntries;

    public LoggerFacade() {
        LoggerFacade var10000 = this;
        super();
        ArrayList var1;
        var1 = new ArrayList.<init>();
        var10000.mEntries = var1;
    }

    public List<LoggerFacade.a> getQueue() {
        synchronized(this.mEntries){}

        Throwable var10000;
        boolean var10001;
        ArrayList var9;
        try {
            var9 = new ArrayList;
        } catch (Throwable var8) {
            var10000 = var8;
            var10001 = false;
            throw var10000;
        }

        ArrayList var2 = var9;

        try {
            var2.<init>(this.mEntries);
            this.mEntries.clear();
            return var9;
        } catch (Throwable var7) {
            var10000 = var7;
            var10001 = false;
            throw var10000;
        }
    }

    public void warn(String var1) {
        List var2;
        List var10000 = var2 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.WARN, var1));
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

    public void warn(String var1, Throwable var2) {
        List var3;
        List var10000 = var3 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var3){}

        Throwable var10;
        boolean var11;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.WARN, var1, var2));
        } catch (Throwable var9) {
            var10 = var9;
            var11 = false;
            throw var10;
        }

        try {
            ;
        } catch (Throwable var8) {
            var10 = var8;
            var11 = false;
            throw var10;
        }
    }

    public void debug(String var1) {
        List var2;
        List var10000 = var2 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.DEBUG, var1));
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

    public void debug(String var1, Throwable var2) {
        List var3;
        List var10000 = var3 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var3){}

        Throwable var10;
        boolean var11;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.DEBUG, var1, var2));
        } catch (Throwable var9) {
            var10 = var9;
            var11 = false;
            throw var10;
        }

        try {
            ;
        } catch (Throwable var8) {
            var10 = var8;
            var11 = false;
            throw var10;
        }
    }

    public void info(String var1) {
        List var2;
        List var10000 = var2 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.INFO, var1));
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

    public void info(String var1, Throwable var2) {
        List var3;
        List var10000 = var3 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var3){}

        Throwable var10;
        boolean var11;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.INFO, var1, var2));
        } catch (Throwable var9) {
            var10 = var9;
            var11 = false;
            throw var10;
        }

        try {
            ;
        } catch (Throwable var8) {
            var10 = var8;
            var11 = false;
            throw var10;
        }
    }

    public void error(String var1, Throwable var2) {
        List var3;
        List var10000 = var3 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var3){}

        Throwable var10;
        boolean var11;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.ERROR, var1, var2));
        } catch (Throwable var9) {
            var10 = var9;
            var11 = false;
            throw var10;
        }

        try {
            ;
        } catch (Throwable var8) {
            var10 = var8;
            var11 = false;
            throw var10;
        }
    }

    public void error(String var1) {
        List var2;
        List var10000 = var2 = this.mEntries;
        LoggerFacade var10001 = this;
        synchronized(var2){}

        Throwable var9;
        boolean var10;
        try {
            var10001.mEntries.add(new LoggerFacade.a(Level.ERROR, var1));
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

    public String getName() {
        return Application.B("뺧㍆練篸ꖼ\ued7a\ueac6鸖Í");
    }

    public boolean isTraceEnabled() {
        return false;
    }

    public boolean isTraceEnabled(Marker var1) {
        return false;
    }

    public void trace(String var1) {
    }

    public void trace(String var1, Object var2) {
    }

    public void trace(String var1, Object var2, Object var3) {
    }

    public void trace(String var1, Object... var2) {
    }

    public void trace(String var1, Throwable var2) {
    }

    public void trace(Marker var1, String var2) {
    }

    public void trace(Marker var1, String var2, Object var3) {
    }

    public void trace(Marker var1, String var2, Object var3, Object var4) {
    }

    public void trace(Marker var1, String var2, Object... var3) {
    }

    public void trace(Marker var1, String var2, Throwable var3) {
    }

    public boolean isDebugEnabled() {
        return true;
    }

    public boolean isDebugEnabled(Marker var1) {
        return true;
    }

    public void debug(String var1, Object var2) {
    }

    public void debug(String var1, Object var2, Object var3) {
    }

    public void debug(String var1, Object... var2) {
    }

    public void debug(Marker var1, String var2) {
    }

    public void debug(Marker var1, String var2, Object var3) {
    }

    public void debug(Marker var1, String var2, Object var3, Object var4) {
    }

    public void debug(Marker var1, String var2, Object... var3) {
    }

    public void debug(Marker var1, String var2, Throwable var3) {
    }

    public boolean isInfoEnabled() {
        return true;
    }

    public boolean isInfoEnabled(Marker var1) {
        return true;
    }

    public void info(String var1, Object var2) {
    }

    public void info(String var1, Object var2, Object var3) {
    }

    public void info(String var1, Object... var2) {
    }

    public void info(Marker var1, String var2) {
    }

    public void info(Marker var1, String var2, Object var3) {
    }

    public void info(Marker var1, String var2, Object var3, Object var4) {
    }

    public void info(Marker var1, String var2, Object... var3) {
    }

    public void info(Marker var1, String var2, Throwable var3) {
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public boolean isWarnEnabled(Marker var1) {
        return true;
    }

    public void warn(String var1, Object var2) {
    }

    public void warn(String var1, Object var2, Object var3) {
    }

    public void warn(String var1, Object... var2) {
    }

    public void warn(Marker var1, String var2) {
    }

    public void warn(Marker var1, String var2, Object var3) {
    }

    public void warn(Marker var1, String var2, Object var3, Object var4) {
    }

    public void warn(Marker var1, String var2, Object... var3) {
    }

    public void warn(Marker var1, String var2, Throwable var3) {
    }

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isErrorEnabled(Marker var1) {
        return true;
    }

    public void error(String var1, Object var2) {
    }

    public void error(String var1, Object var2, Object var3) {
    }

    public void error(String var1, Object... var2) {
    }

    public void error(Marker var1, String var2) {
    }

    public void error(Marker var1, String var2, Object var3) {
    }

    public void error(Marker var1, String var2, Object var3, Object var4) {
    }

    public void error(Marker var1, String var2, Object... var3) {
    }

    public void error(Marker var1, String var2, Throwable var3) {
    }

    static class a {
        private final Level a;
        private final String b;
        private Throwable c;

        a(Level var1, String var2) {
            this.a = var1;
            this.b = var2;
        }

        a(Level var1, String var2, Throwable var3) {
            this.a = var1;
            this.b = var2;
            this.c = var3;
        }

        void a(Logger var1) {
            Level var2;
            Throwable var3;
            if ((var2 = this.a) == Level.DEBUG) {
                if ((var3 = this.c) != null) {
                    var1.debug(this.b, var3);
                } else {
                    var1.debug(this.b);
                }
            } else if (var2 == Level.INFO) {
                if ((var3 = this.c) != null) {
                    var1.info(this.b);
                } else {
                    var1.info(this.b, var3);
                }
            } else if (var2 == Level.WARN) {
                if ((var3 = this.c) != null) {
                    var1.warn(this.b);
                } else {
                    var1.warn(this.b, var3);
                }
            } else if (var2 == Level.ERROR) {
                if ((var3 = this.c) != null) {
                    var1.error(this.b);
                } else {
                    var1.error(this.b, var3);
                }
            }

        }
    }
}
