//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.logger;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import ch.qos.logback.classic.db.names.ColumnName;
import ch.qos.logback.classic.db.names.DefaultDBNameResolver;
import ch.qos.logback.classic.db.names.TableName;
import com.transistorsoft.locationmanager.data.SQLQuery;
import com.transistorsoft.tslocationmanager.Application;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class TSLogReader {
    private static final DefaultDBNameResolver dbNameResolver;
    private static final SimpleDateFormat dateFormatter;
    private static final String DATE_FORMAT = Application.B("\ue832Ⓣ\uf7fe펓ದ麺償㶞滃뷭嗳ﶼ釮駺뤘\uf59f祒\ue5a0");
    private static final String ERROR_FAILED_TO_OPEN_DATABASE = Application.B("\ue839ⓥ\uf7ba펛ಧ黾具㶢準붠嗱ﷶ釸駧뤖\uf5a8祠\ue587氅毴寂騿塶");
    private static final String[] columns;

    public TSLogReader() {
    }

    public static String getLog(SQLQuery var0) {
        SQLiteDatabase var1;
        if ((var1 = openDatabase(1)) != null && var1.isOpen()) {
            SQLiteDatabase var10000 = var1;
            SQLQuery var10001 = var0;
            StringBuffer var2;
            var2 = new StringBuffer();
            Cursor var3 = null;

            Cursor var523;
            label4364: {
                Throwable var521;
                label4365: {
                    boolean var522;
                    String var524;
                    try {
                        var524 = var10001.getSelection(dbNameResolver);
                    } catch (Throwable var515) {
                        var521 = var515;
                        var522 = false;
                        break label4365;
                    }

                    String var4 = var524;
                    var522 = false;

                    String var10002;
                    try {
                        var10002 = dbNameResolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION);
                    } catch (Throwable var514) {
                        var521 = var514;
                        var522 = false;
                        break label4365;
                    }

                    Long var10003 = null;
                    String var10004 = null;
                    Object var10005 = null;
                    Object var10006 = null;
                    Object var10007 = null;

                    try {
                        var523 = var10000.query(var522, var10002, var10003, var10004, (String[])var10005, (String)var10006, (String)var10007, ColumnName.EVENT_ID + Application.B("\uf20e閐흣龜"), (String)null);
                    } catch (Throwable var513) {
                        var521 = var513;
                        var522 = false;
                        break label4365;
                    }

                    var3 = var523;

                    HashMap var525;
                    try {
                        var525 = new HashMap;
                    } catch (Throwable var512) {
                        var521 = var512;
                        var522 = false;
                        break label4365;
                    }

                    HashMap var5 = var525;

                    try {
                        var525.<init>();
                    } catch (Throwable var511) {
                        var521 = var511;
                        var522 = false;
                        break label4365;
                    }

                    long var6 = 0L;

                    label4336:
                    while(true) {
                        boolean var528;
                        try {
                            var528 = var3.moveToNext();
                        } catch (Throwable var504) {
                            var521 = var504;
                            var522 = false;
                            break;
                        }

                        Long var518;
                        int var531;
                        if (!var528) {
                            if (var6 != 0L) {
                                Long var527;
                                StringBuffer var535;
                                try {
                                    var525 = var5;
                                    var535 = var2;
                                    var527 = var6;
                                } catch (Throwable var503) {
                                    var521 = var503;
                                    var522 = false;
                                    break;
                                }

                                var518 = var527;

                                try {
                                    var525.put(var518, var535.toString());
                                } catch (Throwable var502) {
                                    var521 = var502;
                                    var522 = false;
                                    break;
                                }
                            }

                            int var536;
                            try {
                                var2.setLength(0);
                                var3.close();
                                var536 = var0.getOrder();
                                var531 = SQLQuery.ORDER_ASC;
                            } catch (Throwable var501) {
                                var521 = var501;
                                var522 = false;
                                break;
                            }

                            String var520;
                            if (var536 == var531) {
                                var520 = Application.B("\uf26f閂흳");
                            } else {
                                var520 = Application.B("\uf26a閔흣龜");
                            }

                            try {
                                var536 = var0.getLimit();
                            } catch (Throwable var500) {
                                var521 = var500;
                                var522 = false;
                                break;
                            }

                            String var516;
                            if (var536 > 0) {
                                String var537;
                                try {
                                    var537 = String.valueOf(var0.getLimit());
                                } catch (Throwable var499) {
                                    var521 = var499;
                                    var522 = false;
                                    break;
                                }

                                var516 = var537;
                            } else {
                                var516 = null;
                            }

                            var10000 = var1;
                            var522 = false;

                            String[] var530;
                            try {
                                var10002 = dbNameResolver.getTableName(TableName.LOGGING_EVENT);
                                var530 = columns;
                            } catch (Throwable var498) {
                                var521 = var498;
                                var522 = false;
                                break;
                            }

                            var10004 = var4;
                            var10005 = null;
                            var10006 = null;
                            var10007 = null;

                            try {
                                var523 = var10000.query(var522, var10002, var530, var10004, (String[])var10005, (String)var10006, (String)var10007, ColumnName.TIMESTMP + Application.B("\uf20e") + var520, var516);
                            } catch (Throwable var497) {
                                var521 = var497;
                                var522 = false;
                                break;
                            }

                            var3 = var523;

                            while(true) {
                                try {
                                    var528 = var3.moveToNext();
                                } catch (Throwable var495) {
                                    var521 = var495;
                                    var522 = false;
                                    break label4336;
                                }

                                if (!var528) {
                                    try {
                                        var523 = var3;
                                        var3.close();
                                        break label4364;
                                    } catch (Throwable var494) {
                                        var521 = var494;
                                        var522 = false;
                                        break label4336;
                                    }
                                }

                                try {
                                    var2.append(hydrate(var3, var5));
                                } catch (Throwable var496) {
                                    var521 = var496;
                                    var522 = false;
                                    break label4336;
                                }
                            }
                        }

                        long var529;
                        try {
                            var529 = var6;
                            var531 = var3.getInt(0);
                        } catch (Throwable var510) {
                            var521 = var510;
                            var522 = false;
                            break;
                        }

                        long var8 = (long)var531;
                        StringBuffer var532;
                        if (var529 != 0L && var8 != var6) {
                            StringBuffer var526;
                            HashMap var533;
                            try {
                                var532 = var2;
                                var533 = var5;
                                var526 = var2;
                                var10003 = var6;
                            } catch (Throwable var509) {
                                var521 = var509;
                                var522 = false;
                                break;
                            }

                            var518 = var10003;

                            try {
                                var533.put(var518, var526.toString());
                            } catch (Throwable var508) {
                                var521 = var508;
                                var522 = false;
                                break;
                            }

                            try {
                                var532.setLength(0);
                            } catch (Throwable var507) {
                                var521 = var507;
                                var522 = false;
                                break;
                            }
                        }

                        StringBuilder var534;
                        try {
                            var532 = var2;
                            var534 = new StringBuilder;
                        } catch (Throwable var506) {
                            var521 = var506;
                            var522 = false;
                            break;
                        }

                        StringBuilder var519 = var534;

                        try {
                            var519.<init>();
                            var532.append(var534.append(var3.getString(var3.getColumnIndex(dbNameResolver.getColumnName(ColumnName.TRACE_LINE)))).append(Application.B("\uf224")).toString());
                        } catch (Throwable var505) {
                            var521 = var505;
                            var522 = false;
                            break;
                        }

                        var6 = var8;
                    }
                }

                Throwable var517 = var521;
                if (var3 != null && !var3.isClosed()) {
                    var3.close();
                }

                var1.close();
                throw var517;
            }

            if (var523 != null && !var3.isClosed()) {
                var3.close();
            }

            var1.close();
            return var2.toString();
        } else {
            TSLog.logger.error(TSLog.error(Application.B("\uf268閰흙龳蝶䁢唹㹥艏\u2fe2掲顱믥שׂ衩璭ꉚ䔾ᄫ黠\ud9e4迃놩")));
            return null;
        }
    }

    public static boolean destroy() {
        SQLiteDatabase var0;
        if ((var0 = openDatabase(0)) != null && var0.isOpen()) {
            SQLiteDatabase var10000 = var0;
            synchronized(var0){}

            Throwable var44;
            int var45;
            boolean var10001;
            try {
                var45 = var10000.delete(dbNameResolver.getTableName(TableName.LOGGING_EVENT), (String)null, (String[])null);
            } catch (Throwable var43) {
                var44 = var43;
                var10001 = false;
                throw var44;
            }

            int var1 = var45;
            if (var45 >= 0) {
                try {
                    var10000 = var0;
                    var0.delete(dbNameResolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION), (String)null, (String[])null);
                } catch (Throwable var42) {
                    var44 = var42;
                    var10001 = false;
                    throw var44;
                }

                try {
                    var10000.delete(dbNameResolver.getTableName(TableName.LOGGING_EVENT_PROPERTY), (String)null, (String[])null);
                } catch (Throwable var41) {
                    var44 = var41;
                    var10001 = false;
                    throw var44;
                }

                try {
                    TSLog.logger.info(TSLog.ok(Application.B("암ꃴ끁井㞍鸓鯯\u1adcꬑ ︻")));
                } catch (Throwable var40) {
                    var44 = var40;
                    var10001 = false;
                    throw var44;
                }
            } else {
                try {
                    TSLog.logger.error(TSLog.error(Application.B("앑ꃙ끭亸㞺鸲")));
                } catch (Throwable var39) {
                    var44 = var39;
                    var10001 = false;
                    throw var44;
                }
            }

            SQLiteDatabase var46;
            try {
                var45 = var1;
                var46 = var0;
            } catch (Throwable var38) {
                var44 = var38;
                var10001 = false;
                throw var44;
            }

            var46.close();
            return var45 >= 0;
        } else {
            TSLog.logger.error(TSLog.error(Application.B("앑ꃹ끍亘㞚鸒鮫᪈ꬒ⁊︳\ue34d۸삨神\u31e9质╟毎\u2451⥬뇐꒟")));
            return false;
        }
    }

    private static String hydrate(Cursor var0, Map<Long, String> var1) {
        Cursor var10000 = var0;
        Cursor var10001 = var0;
        StringBuffer var10002 = new StringBuffer;
        StringBuffer var2;
        StringBuffer var10003 = var2 = var10002;
        Cursor var10004 = var0;
        Cursor var10005 = var0;
        StringBuffer var10006 = var2;
        Cursor var10007 = var0;
        Cursor var10008 = var0;
        var2.<init>(1024);

        label128: {
            IllegalStateException var22;
            label133: {
                boolean var23;
                int var36;
                try {
                    var36 = var10007.getInt(var10008.getColumnIndex(dbNameResolver.getColumnName(ColumnName.EVENT_ID)));
                } catch (IllegalStateException var20) {
                    var22 = var20;
                    var23 = false;
                    break label133;
                }

                long var3 = (long)var36;

                try {
                    var10006.append(dateFormatter.format(var0.getLong(var0.getColumnIndex(dbNameResolver.getColumnName(ColumnName.TIMESTMP))))).append(Application.B("쪽"));
                } catch (IllegalStateException var19) {
                    var22 = var19;
                    var23 = false;
                    break label133;
                }

                try {
                    var10003.append(var10004.getString(var10005.getColumnIndex(dbNameResolver.getColumnName(ColumnName.LEVEL_STRING)))).append(Application.B("쪽"));
                } catch (IllegalStateException var18) {
                    var22 = var18;
                    var23 = false;
                    break label133;
                }

                try {
                    var10002.append(Application.B("쫆"));
                } catch (IllegalStateException var17) {
                    var22 = var17;
                    var23 = false;
                    break label133;
                }

                String[] var24;
                try {
                    var24 = var10000.getString(var10001.getColumnIndex(dbNameResolver.getColumnName(ColumnName.CALLER_CLASS))).split(Application.B("쫁拱"));
                } catch (IllegalStateException var16) {
                    var22 = var16;
                    var23 = false;
                    break label133;
                }

                String[] var5 = var24;

                int var26;
                try {
                    var26 = var24.length;
                } catch (IllegalStateException var15) {
                    var22 = var15;
                    var23 = false;
                    break label133;
                }

                StringBuffer var27;
                if (var26 > 0) {
                    int var25;
                    String[] var30;
                    try {
                        var27 = var2;
                        var30 = var5;
                        var25 = var5.length;
                    } catch (IllegalStateException var14) {
                        var22 = var14;
                        var23 = false;
                        break label133;
                    }

                    --var25;

                    try {
                        var27.append(var30[var25]);
                    } catch (IllegalStateException var13) {
                        var22 = var13;
                        var23 = false;
                        break label133;
                    }
                }

                Cursor var28;
                Map var31;
                long var32;
                StringBuffer var33;
                try {
                    var31 = var1;
                    var32 = var3;
                    var10002 = var2;
                    var28 = var0;
                    var10004 = var0;
                    var33 = var2;
                    var10006 = var2;
                    var10007 = var0;
                    var10008 = var0;
                    var2.append(Application.B("쪽"));
                } catch (IllegalStateException var12) {
                    var22 = var12;
                    var23 = false;
                    break label133;
                }

                try {
                    var10006.append(var10007.getString(var10008.getColumnIndex(dbNameResolver.getColumnName(ColumnName.CALLER_METHOD))));
                } catch (IllegalStateException var11) {
                    var22 = var11;
                    var23 = false;
                    break label133;
                }

                try {
                    var33.append(Application.B("쫀拿"));
                } catch (IllegalStateException var10) {
                    var22 = var10;
                    var23 = false;
                    break label133;
                }

                try {
                    var10002.append(var28.getString(var10004.getColumnIndex(dbNameResolver.getColumnName(ColumnName.FORMATTED_MESSAGE))));
                } catch (IllegalStateException var9) {
                    var22 = var9;
                    var23 = false;
                    break label133;
                }

                boolean var34;
                try {
                    var34 = var31.containsKey(var32);
                } catch (IllegalStateException var8) {
                    var22 = var8;
                    var23 = false;
                    break label133;
                }

                if (!var34) {
                    break label128;
                }

                long var29;
                Map var35;
                try {
                    var27 = var2;
                    var35 = var1;
                    var29 = var3;
                    var2.append(Application.B("쪗"));
                } catch (IllegalStateException var7) {
                    var22 = var7;
                    var23 = false;
                    break label133;
                }

                try {
                    var27.append((String)var35.get(var29));
                    break label128;
                } catch (IllegalStateException var6) {
                    var22 = var6;
                    var23 = false;
                }
            }

            IllegalStateException var21 = var22;
            var21.printStackTrace();
            var2.append(TSLog.error(Application.B("쫛択튣\uf1a6悧\ue743嬉䆶痞뚣反錮ꇏ䁭룑螺렞촍ꓢ니틬儳Ḿ") + var21.getMessage()));
        }

        var2.append(Application.B("쪗"));
        return var2.toString();
    }

    private static SQLiteDatabase openDatabase(int var0) {
        File var1;
        return (var1 = TSLog.getDatabaseFile()) == null ? null : SQLiteDatabase.openDatabase(var1.getPath(), (CursorFactory)null, var0);
    }

    static {
        columns = new String[]{ColumnName.EVENT_ID.toString(), ColumnName.TIMESTMP.toString(), ColumnName.LEVEL_STRING.toString(), ColumnName.CALLER_CLASS.toString(), ColumnName.CALLER_METHOD.toString(), ColumnName.FORMATTED_MESSAGE.toString()};
        dbNameResolver = new DefaultDBNameResolver();
        Locale var10003 = Locale.ENGLISH;
        (dateFormatter = new SimpleDateFormat(Application.B("\ue832Ⓣ\uf7fe펓ದ麺償㶞滃뷭嗳ﶼ釮駺뤘\uf59f祒\ue5a0"), var10003)).setTimeZone(TimeZone.getDefault());
    }
}
