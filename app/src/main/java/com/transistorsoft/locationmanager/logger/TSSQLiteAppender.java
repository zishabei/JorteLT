//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.logger;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import ch.qos.logback.classic.android.SQLiteLogCleaner;
import ch.qos.logback.classic.db.SQLBuilder;
import ch.qos.logback.classic.db.names.DBNameResolver;
import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.android.AndroidContextUtil;
import ch.qos.logback.core.util.Duration;
import com.transistorsoft.tslocationmanager.Application;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

public class TSSQLiteAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    private static final String DATABASE_FILENAME = Application.B("䒂鸽箌ꎭ똼聳줣䂹阩牎샌숆㘀挫❳洀왬ﭐ穋첅瑴");
    private static final long MILLISECONDS_ONE_DAY = 86400000L;
    private static final int TIMESTMP_INDEX = 1;
    private static final int FORMATTED_MESSAGE_INDEX = 2;
    private static final int LOGGER_NAME_INDEX = 3;
    private static final int LEVEL_STRING_INDEX = 4;
    private static final int THREAD_NAME_INDEX = 5;
    private static final int REFERENCE_FLAG_INDEX = 6;
    private static final int ARG0_INDEX = 7;
    private static final int CALLER_FILENAME_INDEX = 11;
    private static final int CALLER_CLASS_INDEX = 12;
    private static final int CALLER_METHOD_INDEX = 13;
    private static final int CALLER_LINE_INDEX = 14;
    private static final short PROPERTIES_EXIST = 1;
    private static final short EXCEPTION_EXISTS = 2;
    private SQLiteDatabase db;
    private String insertPropertiesSQL;
    private String insertExceptionSQL;
    private String insertSQL;
    private String filename;
    private DBNameResolver dbNameResolver;
    private Duration maxHistory;
    private final AtomicLong lastCleanupTime = new AtomicLong(0L);
    private SQLiteLogCleaner logCleaner;
    private final ThreadPoolExecutor mExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);

    public TSSQLiteAppender() {
    }

    private void clearExpiredLogs(SQLiteDatabase var1) {
        if (this.lastCheckExpired(this.maxHistory, this.lastCleanupTime.get())) {
            this.lastCleanupTime.set(System.currentTimeMillis());
            this.getLogCleaner().performLogCleanup(var1, this.maxHistory);
        }

    }

    private boolean lastCheckExpired(Duration var1, long var2) {
        boolean var4 = false;
        if (var1 != null && var1.getMilliseconds() > 0L) {
            long var10000 = var2;
            var2 = System.currentTimeMillis() - var2;
            if (var10000 > 0L && var2 <= 86400000L && var2 < var1.getMilliseconds()) {
                var4 = false;
            } else {
                var4 = true;
            }
        }

        return var4;
    }

    private long subAppend(ILoggingEvent var1, SQLiteStatement var2) {
        SQLiteStatement var10000 = var2;
        this.bindLoggingEvent(var2, var1);
        this.bindLoggingEventArguments(var2, var1.getArgumentArray());
        this.bindCallerData(var2, var1.getCallerData());
        long var6 = -1L;

        long var5;
        try {
            var5 = var10000.executeInsert();
        } catch (SQLiteException var4) {
            this.addWarn(Application.B("㸽㠫潰㝝齭ꑄ\ude50됒躛东轕겐郧选氚碍榼ﰀ\ueef7찁\ue0bf鈶\u07fc䥝脚歩㾬\ue665슫"), var4);
            return var6;
        }

        var6 = var5;
        return var6;
    }

    private void secondarySubAppend(ILoggingEvent var1, long var2) {
        this.insertProperties(this.mergePropertyMaps(var1), var2);
        if (var1.getThrowableProxy() != null) {
            this.insertThrowable(var1.getThrowableProxy(), var2);
        }

    }

    private void bindLoggingEvent(SQLiteStatement var1, ILoggingEvent var2) {
        long var3 = var2.getTimeStamp();
        var1.bindLong(1, var3);
        var1.bindString(2, var2.getFormattedMessage());
        var1.bindString(3, var2.getLoggerName());
        var1.bindString(4, var2.getLevel().toString());
        var1.bindString(5, var2.getThreadName());
        var3 = (long)computeReferenceMask(var2);
        var1.bindLong(6, var3);
    }

    private void bindLoggingEventArguments(SQLiteStatement var1, Object[] var2) {
        int var3;
        if (var2 != null) {
            var3 = var2.length;
        } else {
            var3 = 0;
        }

        for(int var4 = 0; var4 < var3 && var4 < 4; ++var4) {
            int var5 = var4 + 7;
            var1.bindString(var5, this.asStringTruncatedTo254(var2[var4]));
        }

    }

    private String asStringTruncatedTo254(Object var1) {
        String var2 = null;
        if (var1 != null) {
            var2 = var1.toString();
        }

        if (var2 != null && var2.length() > 254) {
            var2 = var2.substring(0, 254);
        }

        if (var2 == null) {
            var2 = "";
        }

        return var2;
    }

    private static short computeReferenceMask(ILoggingEvent var0) {
        short var1 = 0;
        int var2 = 0;
        if (var0.getMDCPropertyMap() != null) {
            var2 = var0.getMDCPropertyMap().keySet().size();
        }

        int var3 = 0;
        if (var0.getLoggerContextVO().getPropertyMap() != null) {
            var3 = var0.getLoggerContextVO().getPropertyMap().size();
        }

        if (var2 > 0 || var3 > 0) {
            var1 = 1;
        }

        if (var0.getThrowableProxy() != null) {
            var1 = (short)(var1 | 2);
        }

        return var1;
    }

    private Map<String, String> mergePropertyMaps(ILoggingEvent var1) {
        HashMap var3;
        var3 = new HashMap.<init>();
        Map var2;
        if ((var2 = var1.getLoggerContextVO().getPropertyMap()) != null) {
            var3.putAll(var2);
        }

        Map var4;
        if ((var4 = var1.getMDCPropertyMap()) != null) {
            var3.putAll(var4);
        }

        return var3;
    }

    private void insertProperties(Map<String, String> var1, long var2) {
        if (var1.size() > 0) {
            Map var10000 = var1;
            SQLiteStatement var47 = this.db.compileStatement(this.insertPropertiesSQL);

            Throwable var49;
            label419: {
                boolean var10001;
                Iterator var50;
                try {
                    var50 = var10000.entrySet().iterator();
                } catch (Throwable var46) {
                    var49 = var46;
                    var10001 = false;
                    break label419;
                }

                Iterator var48 = var50;

                while(true) {
                    boolean var51;
                    try {
                        var51 = var48.hasNext();
                    } catch (Throwable var45) {
                        var49 = var45;
                        var10001 = false;
                        break;
                    }

                    if (!var51) {
                        var47.close();
                        return;
                    }

                    SQLiteStatement var10002;
                    SQLiteStatement var10003;
                    SQLiteStatement var52;
                    Entry var10004;
                    SQLiteStatement var53;
                    try {
                        var52 = var47;
                        var53 = var47;
                        var10002 = var47;
                        var10003 = var47;
                        var10004 = (Entry)var48.next();
                    } catch (Throwable var44) {
                        var49 = var44;
                        var10001 = false;
                        break;
                    }

                    Entry var4 = var10004;

                    try {
                        var10003.bindLong(1, var2);
                    } catch (Throwable var43) {
                        var49 = var43;
                        var10001 = false;
                        break;
                    }

                    byte var55 = 2;

                    try {
                        var10002.bindString(var55, (String)var4.getKey());
                    } catch (Throwable var42) {
                        var49 = var42;
                        var10001 = false;
                        break;
                    }

                    byte var54 = 3;

                    try {
                        var53.bindString(var54, (String)var4.getValue());
                        var52.executeInsert();
                    } catch (Throwable var41) {
                        var49 = var41;
                        var10001 = false;
                        break;
                    }
                }
            }

            var47.close();
            throw var49;
        }
    }

    private void bindCallerData(SQLiteStatement var1, StackTraceElement[] var2) {
        StackTraceElement var4;
        if (var2 != null && var2.length > 0 && (var4 = var2[0]) != null) {
            byte var5 = 11;
            String var3;
            if (var4.getFileName() != null) {
                var3 = var4.getFileName();
            } else {
                var3 = Application.B("脑檄薶懢령瞁跿");
            }

            var1.bindString(var5, var3);
            var1.bindString(12, var4.getClassName());
            var1.bindString(13, var4.getMethodName());
            var1.bindString(14, Integer.toString(var4.getLineNumber()));
        }

    }

    private void insertException(SQLiteStatement var1, String var2, short var3, long var4) {
        var1.bindLong(1, var4);
        long var6 = (long)var3;
        var1.bindLong(2, var6);
        var1.bindString(3, var2);
        var1.executeInsert();
    }

    private void insertThrowable(IThrowableProxy var1, long var2) {
        SQLiteStatement var4 = this.db.compileStatement(this.insertExceptionSQL);

        short var196;
        for(short var5 = 0; var1 != null; var5 = var196) {
            IThrowableProxy var198;
            label1488: {
                Throwable var10000;
                label1496: {
                    boolean var10001;
                    TSSQLiteAppender var10002;
                    SQLiteStatement var10003;
                    short var10004;
                    StringBuilder var10005;
                    IThrowableProxy var199;
                    try {
                        var198 = var1;
                        var199 = var1;
                        var10002 = this;
                        var10003 = var4;
                        var10004 = var5;
                        var10005 = new StringBuilder;
                    } catch (Throwable var192) {
                        var10000 = var192;
                        var10001 = false;
                        break label1496;
                    }

                    StringBuilder var10006 = var10005;
                    StringBuilder var10007 = var10005;

                    String var209;
                    try {
                        var10007.<init>();
                        ThrowableProxyUtil.subjoinFirstLine(var10006, var1);
                        var209 = var10005.toString();
                    } catch (Throwable var191) {
                        var10000 = var191;
                        var10001 = false;
                        break label1496;
                    }

                    String var6 = var209;
                    short var7 = (short)(var10004 + 1);

                    int var201;
                    try {
                        var10002.insertException(var10003, var6, var5, var2);
                        var201 = var199.getCommonFrames();
                    } catch (Throwable var190) {
                        var10000 = var190;
                        var10001 = false;
                        break label1496;
                    }

                    int var193 = var201;

                    StackTraceElementProxy[] var200;
                    try {
                        var200 = var198.getStackTraceElementProxyArray();
                    } catch (Throwable var189) {
                        var10000 = var189;
                        var10001 = false;
                        break label1496;
                    }

                    StackTraceElementProxy[] var195 = var200;
                    int var8 = 0;

                    while(true) {
                        int var202;
                        try {
                            var202 = var8;
                            var201 = var195.length;
                        } catch (Throwable var184) {
                            var10000 = var184;
                            var10001 = false;
                            break;
                        }

                        short var203;
                        TSSQLiteAppender var204;
                        StringBuilder var205;
                        String var206;
                        SQLiteStatement var207;
                        if (var202 >= var201 - var193) {
                            if (var193 > 0) {
                                try {
                                    var204 = this;
                                    var207 = var4;
                                    var203 = var7;
                                    var205 = new StringBuilder();
                                    var205.append('\t').append(Application.B("縸괞白迪")).append(var193).append(Application.B("縶굓瘼辧ꅉ詐㽉ᘶ\udf9d蕧四\udafcཛྷ⢋䡳ဌ냨䧮ḟ튎侞淜"));
                                } catch (Throwable var183) {
                                    var10000 = var183;
                                    var10001 = false;
                                    break;
                                }

                                try {
                                    var206 = var205.toString();
                                } catch (Throwable var182) {
                                    var10000 = var182;
                                    var10001 = false;
                                    break;
                                }

                                String var194 = var206;
                                var196 = (short)(var203 + 1);

                                try {
                                    var204.insertException(var207, var194, var7, var2);
                                } catch (Throwable var181) {
                                    var10000 = var181;
                                    var10001 = false;
                                    break;
                                }
                            } else {
                                var196 = var7;
                            }

                            try {
                                var198 = var1.getCause();
                                break label1488;
                            } catch (Throwable var180) {
                                var10000 = var180;
                                var10001 = false;
                                break;
                            }
                        }

                        try {
                            var204 = this;
                            var207 = var4;
                            var203 = var7;
                            var205 = new StringBuilder;
                        } catch (Throwable var188) {
                            var10000 = var188;
                            var10001 = false;
                            break;
                        }

                        StringBuilder var9;
                        StringBuilder var208 = var9 = var205;

                        StackTraceElementProxy[] var210;
                        int var211;
                        try {
                            var210 = var195;
                            var211 = var8;
                            var9.<init>();
                            var9.append('\t');
                        } catch (Throwable var187) {
                            var10000 = var187;
                            var10001 = false;
                            break;
                        }

                        try {
                            ThrowableProxyUtil.subjoinSTEP(var208, var210[var211]);
                            var206 = var205.toString();
                        } catch (Throwable var186) {
                            var10000 = var186;
                            var10001 = false;
                            break;
                        }

                        String var197 = var206;
                        short var10 = (short)(var203 + 1);

                        try {
                            var204.insertException(var207, var197, var7, var2);
                        } catch (Throwable var185) {
                            var10000 = var185;
                            var10001 = false;
                            break;
                        }

                        ++var8;
                        var7 = var10;
                    }
                }

                var4.close();
                throw var10000;
            }

            var1 = var198;
        }

        var4.close();
    }

    public void setDbNameResolver(DBNameResolver var1) {
        this.dbNameResolver = var1;
    }

    public String getMaxHistory() {
        Duration var1;
        return (var1 = this.maxHistory) != null ? var1.toString() : "";
    }

    public long getMaxHistoryMs() {
        Duration var1;
        return (var1 = this.maxHistory) != null ? var1.getMilliseconds() : 0L;
    }

    public void setMaxHistory(String var1) {
        this.maxHistory = Duration.valueOf(var1);
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String var1) {
        this.filename = var1;
    }

    public File getDatabaseFile(String var1) {
        File var2 = null;
        if (var1 != null && var1.trim().length() > 0) {
            var2 = new File.<init>(var1);
        }

        if (var2 == null || var2.isDirectory()) {
            if (this.getContext() != null) {
                AndroidContextUtil var3;
                AndroidContextUtil var10000 = var3 = new AndroidContextUtil;
                var10000.<init>();
                if ((var1 = var10000.getPackageName()) != null && var1.trim().length() > 0) {
                    var2 = new File.<init>(var3.getDatabasePath(Application.B("䵔㠪瞕묝\ufde8\u0088ꮗᡇ䵞漰籔\udbea砯\ueaf6ᔂ锜杹䐍痗鑹ㇱ")));
                }
            } else {
                var2 = null;
            }
        }

        return var2;
    }

    public void start() {
        super.started = false;
        File var1;
        if ((var1 = this.getDatabaseFile(this.filename)) == null) {
            this.addError(Application.B("甆䷦鴩왁눱鼭蜄\uef16共ᵶ쒔ꔪ鄁킄㚂\uf7f1呅ၶ殕ᄱ\uef04맬东쀪邧\ue09e\ue355ᔀ┺矃辉䀖ᢒ枰"));
        } else {
            TSSQLiteAppender var10000 = this;
            File var10001 = var1;
            TSSQLiteAppender var10002 = this;
            File var10003 = var1;
            boolean var2 = false;

            label40: {
                label39: {
                    SQLiteException var8;
                    label46: {
                        boolean var9;
                        try {
                            var10003.getParentFile().mkdirs();
                        } catch (SQLiteException var5) {
                            var8 = var5;
                            var9 = false;
                            break label46;
                        }

                        try {
                            var10002.addInfo(Application.B("徭䷥鵧왟눿鼭蝌\uef48儴") + var1.getAbsolutePath());
                            var10000.db = SQLiteDatabase.openOrCreateDatabase(var10001.getPath(), (CursorFactory)null);
                            break label39;
                        } catch (SQLiteException var4) {
                            var8 = var4;
                            var9 = false;
                        }
                    }

                    SQLiteException var6 = var8;
                    this.addError(Application.B("甆䷦鴩왁눱鼭蜄\uef1d兤ᵧ쒟ꕸ鄈킌㚘\uf7f5吇ၳ殇ᄠ"), var6);
                    break label40;
                }

                var2 = true;
            }

            if (var2) {
                if (this.dbNameResolver == null) {
                    DefaultDBNameResolver var7;
                    var7 = new DefaultDBNameResolver.<init>();
                    this.dbNameResolver = var7;
                }

                var10000 = this;
                TSSQLiteAppender var10 = this;
                var10002 = this;
                TSSQLiteAppender var11 = this;
                TSSQLiteAppender var10004 = this;
                TSSQLiteAppender var10005 = this;
                TSSQLiteAppender var10006 = this;
                this.insertExceptionSQL = SQLBuilder.buildInsertExceptionSQL(this.dbNameResolver);
                this.insertPropertiesSQL = SQLBuilder.buildInsertPropertiesSQL(this.dbNameResolver);
                this.insertSQL = SQLBuilder.buildInsertSQL(this.dbNameResolver);

                try {
                    var10006.db.execSQL(SQLBuilder.buildCreateLoggingEventTableSQL(this.dbNameResolver));
                    var10005.db.execSQL(SQLBuilder.buildCreatePropertyTableSQL(this.dbNameResolver));
                    var10004.db.execSQL(SQLBuilder.buildCreateExceptionTableSQL(this.dbNameResolver));
                    var10002.clearExpiredLogs(var11.db);
                    var10.start();
                    var10000.started = true;
                } catch (SQLiteException var3) {
                    this.addError(Application.B("甆䷦鴩왁눱鼭蜄\uef11兦ᵧ쒐ꔬ鄉탍㚈\uf7f5向ၳ殖ᄤ\uef16맫九쀭那\ue0dc\ue35fᔌ┥"), var3);
                }
            }

        }
    }

    public SQLiteLogCleaner getLogCleaner() {
        if (this.logCleaner == null) {
            SQLiteLogCleaner var1;
            var1 = new SQLiteLogCleaner() {
                public void performLogCleanup(SQLiteDatabase var1, Duration var2) {
                    TSSQLiteAppender.this.mExecutor.execute(TSSQLiteAppender.this.new c(var1, var2));
                }
            }.<init>();
            this.logCleaner = var1;
        }

        return this.logCleaner;
    }

    public void setLogCleaner(SQLiteLogCleaner var1) {
        this.logCleaner = var1;
    }

    protected void finalize() {
        this.db.close();
    }

    public void stop() {
        this.db.close();
        this.lastCleanupTime.set(0L);
    }

    public void append(ILoggingEvent var1) {
        if (this.isStarted()) {
            this.mExecutor.execute(new TSSQLiteAppender.b(var1));
        }

    }

    class b implements Runnable {
        private final ILoggingEvent a;

        b(ILoggingEvent var2) {
            this.a = var2;
        }

        public void run() {
            // $FF: Couldn't be decompiled
        }
    }

    private class c implements Runnable {
        private final SQLiteDatabase a;
        private final Duration b;

        c(SQLiteDatabase var2, Duration var3) {
            this.a = var2;
            this.b = var3;
        }

        public void run() {
            TSSQLiteAppender.c var10000 = this;
            long var1 = System.currentTimeMillis() - this.b.getMilliseconds();
            String var4 = SQLBuilder.buildDeleteExpiredLogsSQL(TSSQLiteAppender.this.dbNameResolver, var1);

            try {
                var10000.a.execSQL(var4);
            } catch (SQLiteException var3) {
                var3.printStackTrace();
            }

        }
    }
}
