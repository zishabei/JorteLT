//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.callback.TSBeforeInsertBlock;
import com.transistorsoft.locationmanager.data.LocationDAO;
import com.transistorsoft.locationmanager.data.LocationModel;
import com.transistorsoft.locationmanager.location.TSLocation;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class b implements LocationDAO {
    private static b c;
    private static final String d = Application.B("풇鎆");
    private static final String e = Application.B("풚鎋寓㒘鳇蜪棇⪁ﳏ");
    private static final String f = Application.B("풛鎗寗㒙");
    private static final String g = Application.B("풊鎃寊㒜");
    private static final String h = Application.B("풂鎍寝㒖鳑蜺");
    private static final String i = Application.B("풋鎌寝㒏鳍蜮棒⪉ﳛ");
    private Context a;
    private TSBeforeInsertBlock b;

    public static b a(Context var0) {
        if (c == null) {
            c = b(var0);
        }

        return c;
    }

    private static synchronized b b(Context var0) {
        if (c == null) {
            c = new b(var0.getApplicationContext());
        }

        return c;
    }

    public b(Context var1) {
        this.a = var1;
        this.a();
    }

    private SQLiteDatabase a() {
        try {
            return com.transistorsoft.locationmanager.data.sqlite.a.a(this.a).getWritableDatabase();
        } catch (SQLiteCantOpenDatabaseException var1) {
            TSLog.logger.error(TSLog.error(Application.B("醁\ueea7↕ព䉫癎聶㱈岥揮브\ue297赸琨\ud9f4\ueac9꘤佲숄䑚\ufb1cṧ澼鐘\ue649퍏ᆽ\u0ec7̈́솏")), var1);
            var1.printStackTrace();
            return null;
        }
    }

    private LocationModel a(Cursor var1) {
        String var2;
        var2 = new String.<init>(var1.getBlob(var1.getColumnIndex(Application.B("醣\ueea7ↈល"))));
        b var10002 = this;
        Cursor var10003 = var1;
        Cursor var10004 = var1;
        Integer var4 = var1.getInt(var1.getColumnIndex(Application.B("醮\ueea2")));
        String var5 = var1.getString(var1.getColumnIndex(Application.B("醲\ueeb3↕ឞ")));
        String var3 = var10003.getString(var10004.getColumnIndex(Application.B("醳\ueeaf↑ស䉽癞耷㱑岺")));
        return new LocationModel(var10002, var4, var5, var3, var2);
    }

    private ContentValues a(JSONObject var1) {
        TSConfig.getInstance(this.a);
        ContentValues var10000 = new ContentValues;
        ContentValues var6;
        ContentValues var10001 = var6 = var10000;
        JSONObject var10002 = var1;
        ContentValues var10003 = var6;
        ContentValues var10004 = var6;
        var6.<init>();
        String var10005 = Application.B("醣\ueea7ↈល");

        JSONException var9;
        label42: {
            boolean var10;
            try {
                var10004.put(var10005, var1.toString().getBytes());
            } catch (JSONException var5) {
                var9 = var5;
                var10 = false;
                break label42;
            }

            String var12 = Application.B("醢\ueea8↟ឈ䉷癚耢㱙岮");

            try {
                var10003.put(var12, 0);
            } catch (JSONException var4) {
                var9 = var4;
                var10 = false;
                break label42;
            }

            String var7 = Application.B("醳\ueeaf↑ស䉽癞耷㱑岺");

            try {
                var10001.put(var7, var10002.getString(Application.B("醳\ueeaf↑ស䉽癞耷㱑岺")));
            } catch (JSONException var3) {
                var9 = var3;
                var10 = false;
                break label42;
            }

            String var11 = Application.B("醫\ueea9↟ទ䉫癎");

            try {
                var10000.put(var11, 0);
                return var6;
            } catch (JSONException var2) {
                var9 = var2;
                var10 = false;
            }
        }

        JSONException var8 = var9;
        TSLog.logger.error(TSLog.error(var8.getMessage()), var8);
        return var6;
    }

    private ContentValues a(TSLocation var1) {
        TSConfig.getInstance(this.a);
        TSBeforeInsertBlock var2;
        String var4;
        if ((var2 = this.b) != null) {
            JSONObject var3;
            if ((var3 = var2.onBeforeInsert(var1)) == null) {
                return null;
            }

            var4 = var3.toString();
        } else {
            var4 = var1.renderJson(this.a).toString();
        }

        ContentValues var5;
        ContentValues var10000 = var5 = new ContentValues;
        var5.<init>();
        var5.put(Application.B("醣\ueea7ↈល"), var4.getBytes());
        var5.put(Application.B("醢\ueea8↟ឈ䉷癚耢㱙岮"), 0);
        var5.put(Application.B("醲\ueeb3↕ឞ"), var1.getUUID());
        var10000.put(Application.B("醳\ueeaf↑ស䉽癞耷㱑岺"), var1.getTimestamp());
        var10000.put(Application.B("醫\ueea9↟ទ䉫癎"), 0);
        return var10000;
    }

    public void a(TSBeforeInsertBlock var1) {
        this.b = var1;
    }

    public List<LocationModel> all() {
        Cursor var1 = null;
        ArrayList var2;
        var2 = new ArrayList.<init>();
        SQLiteDatabase var3;
        if ((var3 = this.a()) == null) {
            return var2;
        } else {
            SQLiteDatabase var10000 = var3;
            boolean var10001 = false;
            String var10002 = Application.B("Ϩ\ue090벺첍撨\u1aed쑛侷㣜");
            Object var10003 = null;
            Object var10004 = null;
            Object var10005 = null;
            Object var10006 = null;
            Object var10007 = null;

            Throwable var26;
            label274: {
                Cursor var27;
                try {
                    var27 = var10000.query(var10001, var10002, (String[])var10003, (String)var10004, (String[])var10005, (String)var10006, (String)var10007, Application.B("ϭ\ue09b볹") + TSConfig.getInstance(this.a).getLocationsOrderDirection(), (String)null);
                } catch (Throwable var23) {
                    var26 = var23;
                    var10001 = false;
                    break label274;
                }

                var1 = var27;

                while(true) {
                    boolean var28;
                    try {
                        var28 = var1.moveToNext();
                    } catch (Throwable var22) {
                        var26 = var22;
                        var10001 = false;
                        break;
                    }

                    if (!var28) {
                        if (var1 != null) {
                            var1.close();
                        }

                        return var2;
                    }

                    LocationModel var29;
                    try {
                        var29 = this.a(var1);
                    } catch (Throwable var21) {
                        var26 = var21;
                        var10001 = false;
                        break;
                    }

                    LocationModel var25 = var29;
                    if (var29 != null) {
                        try {
                            var2.add(var25);
                        } catch (Throwable var20) {
                            var26 = var20;
                            var10001 = false;
                            break;
                        }
                    }
                }
            }

            Throwable var24 = var26;
            if (var1 != null) {
                var1.close();
            }

            throw var24;
        }
    }

    public List<LocationModel> allWithLocking(Integer var1) {
        Cursor var2 = null;
        ArrayList var3;
        var3 = new ArrayList.<init>();
        SQLiteDatabase var4;
        if ((var4 = this.a()) == null) {
            return var3;
        } else {
            String var5 = null;
            if (var1 > 0) {
                var5 = var1.toString();
            }

            SQLiteDatabase var10000 = var4;
            boolean var10001 = false;
            String var10002 = Application.B("狁㓂鉧\uf8feꭼ큮뷎␠휆");
            ContentValues var10003 = null;
            String var10004 = Application.B("狁㓂鉧\uf8f4\uab6d큣붜⑾");
            Object var10005 = null;
            Object var10006 = null;
            Object var10007 = null;

            Cursor var168;
            label1400: {
                Throwable var167;
                label1401: {
                    try {
                        var168 = var10000.query(var10001, var10002, var10003, var10004, (String[])var10005, (String)var10006, (String)var10007, Application.B("狄㓉鈤") + TSConfig.getInstance(this.a).getLocationsOrderDirection(), var5);
                    } catch (Throwable var161) {
                        var167 = var161;
                        var10001 = false;
                        break label1401;
                    }

                    var2 = var168;

                    ArrayList var169;
                    try {
                        var169 = new ArrayList;
                    } catch (Throwable var160) {
                        var167 = var160;
                        var10001 = false;
                        break label1401;
                    }

                    ArrayList var165 = var169;

                    try {
                        var169.<init>();
                    } catch (Throwable var159) {
                        var167 = var159;
                        var10001 = false;
                        break label1401;
                    }

                    while(true) {
                        boolean var170;
                        try {
                            var170 = var2.moveToNext();
                        } catch (Throwable var155) {
                            var167 = var155;
                            var10001 = false;
                            break;
                        }

                        if (!var170) {
                            ContentValues var171;
                            SQLiteDatabase var174;
                            try {
                                var168 = var2;
                                var174 = var4;
                                var171 = new ContentValues;
                            } catch (Throwable var154) {
                                var167 = var154;
                                var10001 = false;
                                break;
                            }

                            ContentValues var162;
                            var10003 = var162 = var171;

                            try {
                                var10003.<init>();
                            } catch (Throwable var153) {
                                var167 = var153;
                                var10001 = false;
                                break;
                            }

                            String var173 = Application.B("狁㓂鉧\uf8f4\uab6d큣");

                            try {
                                var171.put(var173, 1);
                            } catch (Throwable var152) {
                                var167 = var152;
                                var10001 = false;
                                break;
                            }

                            var10002 = Application.B("狁㓂鉧\uf8feꭼ큮뷎␠휆");

                            int var175;
                            try {
                                var175 = var174.update(var10002, var162, Application.B("狄㓉鈤\uf8d6ꭆ퀧붉") + TextUtils.join(Application.B("犁"), var165) + Application.B("犄"), (String[])null);
                            } catch (Throwable var151) {
                                var167 = var151;
                                var10001 = false;
                                break;
                            }

                            int var163 = var175;

                            try {
                                TSLog.logger.debug(TSLog.ok(Application.B("狡㓂鉧\uf8f4\uab6d큣북") + var163 + Application.B("犍㓟鉡\uf8fc\uab67큵뷅\u243d")));
                                break label1400;
                            } catch (Throwable var150) {
                                var167 = var150;
                                var10001 = false;
                                break;
                            }
                        }

                        LocationModel var172;
                        try {
                            var172 = this.a(var2);
                        } catch (Throwable var158) {
                            var167 = var158;
                            var10001 = false;
                            break;
                        }

                        LocationModel var166 = var172;

                        try {
                            var165.add(var2.getInt(0));
                        } catch (Throwable var157) {
                            var167 = var157;
                            var10001 = false;
                            break;
                        }

                        if (var172 != null) {
                            try {
                                var3.add(var166);
                            } catch (Throwable var156) {
                                var167 = var156;
                                var10001 = false;
                                break;
                            }
                        }
                    }
                }

                Throwable var164 = var167;
                if (var2 != null) {
                    var2.close();
                }

                throw var164;
            }

            if (var168 != null) {
                var2.close();
            }

            return var3;
        }
    }

    @Nullable
    public LocationModel first() {
        Cursor var1 = null;
        SQLiteDatabase var2;
        if ((var2 = this.a()) == null) {
            return null;
        } else {
            SQLiteDatabase var10000 = var2;
            boolean var10001 = false;
            String var10002 = Application.B("\ueabd硰ᆿ讉슱崊퓚菖ᇄ");
            ContentValues var10003 = null;
            String var10004 = Application.B("\ueabd硰ᆿ讃슠崇품莈");
            Object var10005 = null;
            Object var10006 = null;
            Object var10007 = null;

            Throwable var115;
            Throwable var118;
            label1144: {
                Cursor var119;
                try {
                    var119 = var10000.query(var10001, var10002, var10003, var10004, (String[])var10005, (String)var10006, (String)var10007, Application.B("\ueab8硻ᇼ") + TSConfig.getInstance(this.a).getLocationsOrderDirection(), Application.B("\ueae0"));
                } catch (Throwable var114) {
                    var118 = var114;
                    var10001 = false;
                    break label1144;
                }

                var1 = var119;

                boolean var120;
                try {
                    var120 = var119.moveToFirst();
                } catch (Throwable var113) {
                    var118 = var113;
                    var10001 = false;
                    break label1144;
                }

                if (!var120) {
                    var115 = null;
                    if (var1 != null) {
                        var1.close();
                    }

                    return var115;
                }

                LocationModel var121;
                try {
                    var121 = this.a(var1);
                } catch (Throwable var112) {
                    var118 = var112;
                    var10001 = false;
                    break label1144;
                }

                LocationModel var3 = var121;

                int var122;
                SQLiteDatabase var123;
                try {
                    var123 = var2;
                    var122 = var1.getInt(var1.getColumnIndex(Application.B("\ueab8硻")));
                } catch (Throwable var111) {
                    var118 = var111;
                    var10001 = false;
                    break label1144;
                }

                int var117 = var122;

                ContentValues var124;
                try {
                    var124 = new ContentValues;
                } catch (Throwable var110) {
                    var118 = var110;
                    var10001 = false;
                    break label1144;
                }

                ContentValues var4;
                var10003 = var4 = var124;

                try {
                    var10003.<init>();
                } catch (Throwable var109) {
                    var118 = var109;
                    var10001 = false;
                    break label1144;
                }

                String var125 = Application.B("\ueabd硰ᆿ讃슠崇");

                try {
                    var124.put(var125, 1);
                } catch (Throwable var108) {
                    var118 = var108;
                    var10001 = false;
                    break label1144;
                }

                var10002 = Application.B("\ueabd硰ᆿ讉슱崊퓚菖ᇄ");

                int var126;
                try {
                    var126 = var123.update(var10002, var4, Application.B("\ueab8硻ᇡ") + var117, (String[])null);
                } catch (Throwable var107) {
                    var118 = var107;
                    var10001 = false;
                    break label1144;
                }

                var117 = var126;

                try {
                    TSLog.logger.debug(TSLog.ok(Application.B("\uea9d硰ᆿ讃슠崇풕") + var117 + Application.B("\ueaf1硭ᆹ讋슪崑퓑菋")));
                } catch (Throwable var106) {
                    var118 = var106;
                    var10001 = false;
                    break label1144;
                }

                if (var121 != null) {
                    if (var1 != null) {
                        var1.close();
                    }

                    return var3;
                }

                LocationModel var127;
                try {
                    var119 = var1;
                    var127 = this.first();
                } catch (Throwable var105) {
                    var118 = var105;
                    var10001 = false;
                    break label1144;
                }

                LocationModel var116 = var127;
                if (var119 != null) {
                    var1.close();
                }

                return var116;
            }

            var115 = var118;
            if (var1 != null) {
                var1.close();
            }

            throw var115;
        }
    }

    public boolean unlock(LocationModel var1) {
        SQLiteDatabase var4;
        if ((var4 = this.a()) == null) {
            return false;
        } else {
            var4.beginTransaction();
            ContentValues var2;
            ContentValues var10001 = var2 = new ContentValues;
            var10001.<init>();
            var10001.put(Application.B("ꈇ说䶅ೂ俻澛"), 0);
            String[] var3;
            (var3 = new String[1])[0] = var1.id.toString();
            int var10000 = var4.update(Application.B("ꈇ说䶅ೈ俪澖连銽壿"), var2, Application.B("ꈂ诿䷛ಖ"), var3);
            var4.setTransactionSuccessful();
            var4.endTransaction();
            TSLog.logger.debug(TSLog.ok(Application.B("ꈾ试䶪೦保澴迴銗墶馊") + var1.getUUID()));
            return var10000 == 1;
        }
    }

    public boolean unlock(List<LocationModel> var1) {
        SQLiteDatabase var4;
        if ((var4 = this.a()) == null) {
            return false;
        } else {
            ArrayList var2;
            var2 = new ArrayList.<init>();
            Iterator var3 = var1.iterator();

            while(var3.hasNext()) {
                var2.add(((LocationModel)var3.next()).id);
            }

            ContentValues var8;
            ContentValues var10002 = var8 = new ContentValues;
            var10002.<init>();
            var10002.put(Application.B("ꈇ说䶅ೂ俻澛"), 0);
            var4.beginTransaction();
            String var6 = Application.B("ꈂ诿䷆ೠ俐濟辙") + TextUtils.join(Application.B("ꉇ"), var2) + Application.B("ꉂ");
            boolean var5;
            int var7;
            if ((var7 = var4.update(Application.B("ꈇ说䶅ೈ俪澖连銽壿"), var8, var6, (String[])null)) == var1.size()) {
                var5 = true;
            } else {
                var5 = false;
            }

            if (var5) {
                TSLog.logger.debug(TSLog.ok(Application.B("ꈾ试䶪೦保澴迴銗墬馂") + var7 + Application.B("ꉂ")));
            } else {
                TSLog.logger.error(TSLog.error(Application.B("ꈾ试䶪೦保澴辑銕壍駣舣灗渋瓙鋫洘") + var7 + Application.B("ꉂ")));
            }

            var4.setTransactionSuccessful();
            var4.endTransaction();
            return var5;
        }
    }

    public boolean unlock() {
        SQLiteDatabase var2;
        if ((var2 = this.a()) == null) {
            return false;
        } else {
            var2.beginTransaction();
            ContentValues var1;
            ContentValues var10001 = var1 = new ContentValues;
            var10001.<init>();
            var10001.put(Application.B("ꈇ说䶅ೂ俻澛"), 0);
            int var10000 = var2.update(Application.B("ꈇ说䶅ೈ俪澖连銽壿"), var1, (String)null, (String[])null);
            var2.setTransactionSuccessful();
            var2.endTransaction();
            return var10000 == 1;
        }
    }

    public boolean persist(TSLocation var1) {
        SQLiteDatabase var2;
        if ((var2 = this.a()) == null) {
            return false;
        } else {
            Exception var10000;
            label60: {
                ContentValues var14;
                boolean var10001;
                try {
                    var14 = this.a(var1);
                } catch (Exception var10) {
                    var10000 = var10;
                    var10001 = false;
                    break label60;
                }

                ContentValues var3 = var14;
                if (var14 == null) {
                    return false;
                }

                long var15;
                try {
                    var2.beginTransaction();
                    var15 = var2.insert(Application.B("\ue0f2⇐吁뢜\uf46b\ueb2d\ue454\ue7d8֫"), (String)null, var3);
                } catch (Exception var9) {
                    var10000 = var9;
                    var10001 = false;
                    break label60;
                }

                long var13 = var15;

                try {
                    TSLog.logger.info(TSLog.ok(Application.B("\ue0d7⇱吱뢸\uf44d\ueb10\ue401\ue796") + var1.getUUID()));
                    var2.setTransactionSuccessful();
                    var2.endTransaction();
                } catch (Exception var8) {
                    var10000 = var8;
                    var10001 = false;
                    break label60;
                }

                if (var15 > -1L) {
                    label61: {
                        int var16;
                        try {
                            var16 = TSConfig.getInstance(this.a).getMaxRecordsToPersist();
                        } catch (Exception var6) {
                            var10000 = var6;
                            var10001 = false;
                            break label61;
                        }

                        int var12 = var16;
                        if (var16 >= 0) {
                            try {
                                this.a(var12);
                            } catch (Exception var5) {
                                var10000 = var5;
                                var10001 = false;
                                break label61;
                            }
                        }

                        return true;
                    }
                } else {
                    try {
                        TSLog.logger.error(TSLog.error(Application.B("\ue0cd⇮吮뢔\uf46b\ueb21\ue477\ue7d9ֻ쥣蟋涴㰅梪뽽徭〬갑戩帩䮿䲫뇰캼\ue9a1\udb33毯沯\ue141ꗺ\u9ff3訌跧\ue514") + var13));
                        return false;
                    } catch (Exception var7) {
                        var10000 = var7;
                        var10001 = false;
                    }
                }
            }

            Exception var11 = var10000;
            TSLog.logger.error(TSLog.error(Application.B("\ue0ce⇚吐뢎\uf476\ueb37\ue44f\ue7d3ֶ쥡蟚淽㰌梥뽐往〖걃戼并䯭") + var11.getMessage()), var11);
            var11.printStackTrace();
            return false;
        }
    }

    public String persist(JSONObject var1) {
        SQLiteDatabase var2;
        if ((var2 = this.a()) == null) {
            return null;
        } else {
            String var3 = String.valueOf(UUID.randomUUID());
            if (!var1.has(Application.B("\ue0eb⇊吋뢙"))) {
                try {
                    var1.put(Application.B("\ue0eb⇊吋뢙"), var3);
                } catch (JSONException var7) {
                    TSLog.logger.error(TSLog.error(Application.B("\ue0d8⇞吋뢑\uf47a\ueb20\ue41b\ue7c2ַ줢蟌涸㰞棤뽌徙《걕批帣䮣䳸뇕캠\ue9b6\udb72毽沧\ue147ꗸ")), var7);
                    return null;
                }
            } else {
                String var10000;
                try {
                    var10000 = var1.getString(Application.B("\ue0eb⇊吋뢙"));
                } catch (JSONException var6) {
                    TSLog.logger.error(TSLog.error(Application.B("\ue0d8⇞吋뢑\uf47a\ueb20\ue41b\ue7c2ַ줢蟙涸㰞梧뽑忌〖걄戰帨䯭䲾뇫캠\ue9b8\udb33毅没\ue14bꗷ鿢訁趲\ue55a")), var6);
                    return null;
                }

                var3 = var10000;
            }

            ContentValues var8 = this.a(var1);
            var2.beginTransaction();
            long var4;
            long var10 = var4 = var2.insert(Application.B("\ue0f2⇐吁뢜\uf46b\ueb2d\ue454\ue7d8֫"), (String)null, var8);
            var2.setTransactionSuccessful();
            var2.endTransaction();
            if (var10 > -1L) {
                TSLog.logger.info(TSLog.ok(Application.B("\ue0d7⇱吱뢸\uf44d\ueb10\ue401\ue796") + var3));
                int var9;
                if ((var9 = TSConfig.getInstance(this.a).getMaxRecordsToPersist()) >= 0) {
                    this.a(var9);
                }

                return var3;
            } else {
                TSLog.logger.error(TSLog.error(Application.B("\ue0cd⇮吮뢔\uf46b\ueb21\ue477\ue7d9ֻ쥣蟋涴㰅梪뽽徭〬갑戩帩䮿䲫뇰캼\ue9a1\udb33毯沯\ue141ꗺ\u9ff3訌跧\ue514") + var4));
                return null;
            }
        }
    }

    public void a(int var1) {
        SQLiteDatabase var2;
        if ((var2 = this.a()) != null) {
            var2.beginTransaction();
            TSLog.logger.debug(TSLog.info(Application.B("醔\uee8e↮ឳ䉀癡聬㰜") + var1));
            var2.delete(Application.B("醫\ueea9↟ល䉺癃耹㱒岹"), Application.B("醮\ueea2⇜ំ䈳瘊聾㱯岏掂븦\ue2a4赉瑦\ud9bd\ueafeꙕ佸숿䑡הּṧ濰鐪\ue678퍢ᆚ\u0ee5ͣ쇊\ue017갴䠽浺\udcc2ᘱ\ue7dd\uf3d6洢瑶䩶\ue27bी哖⚵섉⪍ᇋ륺ᑟ\uf8fcɖꥊ\ue6d5㽻ఘ婘쉀⅙亨쫲䵇귻ᾞ秪ࡂ療\ue265ᕰ舄덛镧経ᘫ㴰淃抝饳\u135b⮃") + var1 + Application.B("釮\ueee6↚ផ䉡瘃"), (String[])null);
            var2.setTransactionSuccessful();
            var2.endTransaction();
        }
    }

    public void prune(int var1) {
        SQLiteDatabase var2;
        if ((var2 = this.a()) != null) {
            var2.beginTransaction();
            TSLog.logger.debug(TSLog.info(Application.B("┛떛浶伴幷禐뷑") + var1 + Application.B("╫떭浂伃幁")));
            var2.delete(Application.B("┧떦浀伛幆秙붓세蔥"), Application.B("┯떨浗伟幆秙붑섳蕾䅬裒晎ន鷝\uf2b3쮈ᙂ냯곣罁龿긶뼱藺鋶퓲쒆靚완\uefa5\ueb8cา瑙\uedfb龬ꗑ캈퉴뱾\uf6bb") + var1 + Application.B("╫떭浂伃帕禙"), (String[])null);
            var2.setTransactionSuccessful();
            var2.endTransaction();
        }
    }

    public boolean destroy(LocationModel var1) {
        SQLiteDatabase var3;
        if ((var3 = this.a()) == null) {
            return false;
        } else {
            var3.beginTransaction();
            String[] var2;
            (var2 = new String[1])[0] = var1.id.toString();
            int var4;
            if ((var4 = var3.delete(Application.B("㻖ꀇ䵐㶪昡瓈㽐⢞밵"), Application.B("㻓ꀌ䴎㷴"), var2)) == 1) {
                TSLog.logger.debug(TSLog.ok(Application.B("㻾ꀭ䵠㶟昇瓮㽦⣊뱦") + var1.getUUID()));
            } else {
                TSLog.logger.error(TSLog.error(Application.B("㻾ꀭ䵠㶟昇瓮㽦⣐밀\u05cb䴷糪鳺\uebdc䂰ˣשּ") + var1.getUUID()));
            }

            var3.setTransactionSuccessful();
            var3.endTransaction();
            return var4 == 1;
        }
    }

    public boolean a(String var1) {
        SQLiteDatabase var3;
        if ((var3 = this.a()) == null) {
            return false;
        } else {
            var3.beginTransaction();
            String[] var2;
            (var2 = new String[1])[0] = var1;
            int var4;
            if ((var4 = var3.delete(Application.B("醫\ueea9↟ល䉺癃耹㱒岹"), Application.B("醲\ueeb3↕ឞ䈳瘕"), var2)) == 1) {
                TSLog.logger.debug(TSLog.ok(Application.B("醃\uee83↯ឮ䉜癥耏㰆峪") + var1));
            } else {
                TSLog.logger.error(TSLog.error(Application.B("醃\uee83↯ឮ䉜癥耏㰜岌掏븪\ue2ab赈琔\ud991\ueaa0ꙕ") + var1));
            }

            var3.setTransactionSuccessful();
            var3.endTransaction();
            return var4 == 1;
        }
    }

    public void destroyAll(List<LocationModel> var1) {
        SQLiteDatabase var4;
        if ((var4 = this.a()) != null) {
            ArrayList var2;
            var2 = new ArrayList.<init>();
            Iterator var3 = var1.iterator();

            while(var3.hasNext()) {
                var2.add(((LocationModel)var3.next()).id);
            }

            var4.beginTransaction();
            Integer var6;
            if (var6 = var4.delete(Application.B("⛎朚伭ᝏ酂糍೯\uab17峔"), Application.B("⛋朑佮ᝧ酸粄ನ") + TextUtils.join(Application.B("⚎"), var2) + Application.B("⚋"), (String[])null) == var1.size()) {
                TSLog.logger.debug(TSLog.ok(Application.B("⛦朰伂ᝫ酢糡ೄꭃ岇咣") + var6 + Application.B("⚋")));

                for(Iterator var5 = var1.iterator(); var5.hasNext(); ((LocationModel)var5.next()).destroyed = true) {
                }
            } else {
                TSLog.logger.error(TSLog.error(Application.B("⛦朰伂ᝫ酢糡ಠꬿ峦哂矟⭇ᥫ摦ѣ⧅") + var6 + Application.B("⚋")));
            }

            var4.setTransactionSuccessful();
            var4.endTransaction();
        }
    }

    public boolean clear() {
        SQLiteDatabase var1;
        if ((var1 = this.a()) == null) {
            return false;
        } else {
            var1.beginTransaction();
            var1.delete(Application.B("䅺調鎻ᄚ潔᭵៹쇲誢"), (String)null, (String[])null);
            var1.setTransactionSuccessful();
            var1.endTransaction();
            return true;
        }
    }

    public int a(boolean var1) {
        SQLiteDatabase var15;
        if ((var15 = this.a()) == null) {
            return -1;
        } else {
            boolean var10000 = var1;
            Cursor var18 = null;
            String var2 = Application.B("醔\uee83↰ឿ䉍百聶㱟岥掻븍\ue293贵瑬\ud9fd\ueaba\ua633佬숢䑣ﭙḫ澷鐚\ue65c퍚ᆶ້͙솙");
            if (var10000) {
                var2 = var2 + Application.B("釧\uee91↴ឿ䉜癯聶㱐岥掭븈\ue282赹瑻\ud9e4");
            }

            Cursor var20;
            int var22;
            label183: {
                Throwable var19;
                label184: {
                    boolean var10001;
                    try {
                        var20 = var15.rawQuery(var2, (String[])null);
                    } catch (Throwable var14) {
                        var19 = var14;
                        var10001 = false;
                        break label184;
                    }

                    Cursor var21 = var20;
                    Cursor var10002 = var18 = var20;

                    try {
                        var10002.moveToFirst();
                    } catch (Throwable var13) {
                        var19 = var13;
                        var10001 = false;
                        break label184;
                    }

                    label168:
                    try {
                        var22 = var21.getInt(0);
                        break label183;
                    } catch (Throwable var12) {
                        var19 = var12;
                        var10001 = false;
                        break label168;
                    }
                }

                Throwable var16 = var19;
                if (var18 != null) {
                    var18.close();
                }

                throw var16;
            }

            int var17 = var22;
            if (var20 != null) {
                var18.close();
            }

            return var17;
        }
    }

    public int count() {
        return this.a(false);
    }

    public void close() {
        TSLog.logger.info(TSLog.ok(Application.B("\ue2a1皼䞤ꥬ\ue249\ude11㦮噿ꓯ졿魱뿦✓Ľ阶")));
    }
}
