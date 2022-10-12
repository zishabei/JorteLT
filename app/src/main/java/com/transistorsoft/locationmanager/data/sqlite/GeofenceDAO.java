//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.data.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.transistorsoft.locationmanager.geofence.TSGeofence;
import com.transistorsoft.locationmanager.geofence.TSGeofence.Builder;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

public class GeofenceDAO {
    private static GeofenceDAO c;
    private static final long d = 6371L;
    private final Context a;
    private final List<String> b;

    private static double a(double var0) {
        return var0 * 3.141592653589793D / 180.0D;
    }

    public static GeofenceDAO getInstance(Context var0) {
        if (c == null) {
            c = a(var0);
        }

        return c;
    }

    private static synchronized GeofenceDAO a(Context var0) {
        if (c == null) {
            c = new GeofenceDAO(var0.getApplicationContext());
        }

        return c;
    }

    private GeofenceDAO(Context var1) {
        super();
        GeofenceDAO var10000 = this;
        GeofenceDAO var10001 = this;
        b = new ArrayList<>();
        a = var1;
    }

    private void a() {
        this.b.clear();
    }

    private SQLiteDatabase b() {
        return com.transistorsoft.locationmanager.data.sqlite.a.a(this.a).getWritableDatabase();
    }

    private ContentValues a(TSGeofence var1) {
        ContentValues var3;
        var3 = new ContentValues();
        JSONObject var2;
        JSONObject var10000 = var2 = var1.getExtras();
        var3.put(Application.B("\ud90b\udd63ᠸಐ높\ue574揬熜ಌ惰"), var1.getIdentifier());
        var3.put(Application.B("\ud910\udd66ᠹಗ놓\ue56e"), var1.getRadius());
        var3.put(Application.B("\ud90e\udd66ᠩಗ높\ue568揮熐"), var1.getLatitude());
        var3.put(Application.B("\ud911\udd6eᠳಡ놊\ue57c揾熜ಝ惷侸ै"), Math.sin(a(var1.getLatitude())));
        var3.put(Application.B("\ud901\udd68ᠮಡ놊\ue57c揾熜ಝ惷侸ै"), Math.cos(a(var1.getLatitude())));
        var3.put(Application.B("\ud90e\udd68ᠳಙ놏\ue569揿熑ಌ"), var1.getLongitude());
        var3.put(Application.B("\ud911\udd6eᠳಡ놊\ue572揤熒ಀ惶侩ॉ셯"), Math.sin(a(var1.getLongitude())));
        var3.put(Application.B("\ud901\udd68ᠮಡ놊\ue572揤熒ಀ惶侩ॉ셯"), Math.cos(a(var1.getLongitude())));
        var3.put(Application.B("\ud90c\udd68ᠩಗ놀\ue564揅熛ಬ惬侨य़셳"), var1.getNotifyOnEntry());
        var3.put(Application.B("\ud90c\udd68ᠩಗ놀\ue564揅熛ಬ惺侵ख़"), var1.getNotifyOnExit());
        var3.put(Application.B("\ud90c\udd68ᠩಗ놀\ue564揅熛ಭ惵侹ु셦"), var1.getNotifyOnDwell());
        var3.put(Application.B("\ud90e\udd68ᠴಊ놃\ue56f揣熛ಎ惆侹ु셫깟"), var1.getLoiteringDelay());
        String var4 = Application.B("\ud907\udd7fᠩಌ놇\ue56e");
        String var5;
        if (var10000 != null) {
            var5 = var2.toString();
        } else {
            var5 = null;
        }

        var3.put(var4, var5);
        return var3;
    }

    @SuppressLint("Range")
    private TSGeofence a(Cursor var1) {
        boolean var5;
        if (var1.getInt(var1.getColumnIndex(Application.B("\ud90c\udd68ᠩಗ놀\ue564揅熛ಬ惬侨य़셳"))) == 1) {
            var5 = true;
        } else {
            var5 = false;
        }

        boolean var2;
        if (var1.getInt(var1.getColumnIndex(Application.B("\ud90c\udd68ᠩಗ놀\ue564揅熛ಬ惺侵ख़"))) == 1) {
            var2 = true;
        } else {
            var2 = false;
        }

        boolean var3;
        if (var1.getInt(var1.getColumnIndex(Application.B("\ud90c\udd68ᠩಗ놀\ue564揅熛ಭ惵侹ु셦"))) == 1) {
            var3 = true;
        } else {
            var3 = false;
        }

        Builder var4;
        Builder var10000 = var4 = new Builder();
        return var10000.setIdentifier(var1.getString(var1.getColumnIndex(Application.B("\ud90b\udd63ᠸಐ높\ue574揬熜ಌ惰")))).setRadius(var1.getFloat(var1.getColumnIndex(Application.B("\ud910\udd66ᠹಗ놓\ue56e")))).setLatitude(var1.getDouble(var1.getColumnIndex(Application.B("\ud90e\udd66ᠩಗ높\ue568揮熐")))).setLongitude(var1.getDouble(var1.getColumnIndex(Application.B("\ud90e\udd68ᠳಙ놏\ue569揿熑ಌ")))).setNotifyOnEntry(var5).setNotifyOnExit(var2).setNotifyOnDwell(var3).setLoiteringDelay(var1.getInt(var1.getColumnIndex(Application.B("\ud90e\udd68ᠴಊ놃\ue56f揣熛ಎ惆侹ु셫깟")))).setExtras(var1.getString(var1.getColumnIndex(Application.B("\ud907\udd7fᠩಌ놇\ue56e")))).build();
    }

    public List<String> getErrors() {
        return this.b;
    }

    public int count() {
        SQLiteDatabase var10000 = this.b();
        Cursor var14 = null;
        String var10001 = Application.B("\u2d9e⾟싘냩챬빁㰉쎮ꆣ\u2fef\udce3崈䎆砞\ue294砪䘽鬾鄷\uf779⤪\ue163䣛쇞\ueedc῟\u20f5ƨ帑ﲿ㸔\uf12cꯃ㐜큤㷼콻퇞蘨");

        Cursor var17;
        int var20 = 0;
        label159:
        {
            Throwable var16;
            label160:
            {
                boolean var18;
                try {
                    var17 = var10000.rawQuery(var10001, (String[]) null);
                } catch (Throwable var13) {
                    var16 = var13;
                    var18 = false;
                    break label160;
                }

                Cursor var19 = var17;
                Cursor var10002 = var14 = var17;

                try {
                    var10002.moveToFirst();
                } catch (Throwable var12) {
                    var16 = var12;
                    var18 = false;
                    break label160;
                }

                label146:
                try {
                    var20 = var19.getInt(0);
                    break label159;
                } catch (Throwable var11) {
                    var16 = var11;
                    var18 = false;
                    break label146;
                }
            }

            Throwable var1 = var16;
            if (var14 != null) {
                var14.close();
            }

            try {
                throw var1;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        var14.close();
        return var20;
    }

    public boolean create(TSGeofence var1) {
        GeofenceDAO var10000 = this;
        GeofenceDAO var10001 = this;
        TSGeofence var10002 = var1;
        this.a();
        SQLiteDatabase var12 = this.b();
        long var2 = 0L;

        SQLiteException var14;
        label63:
        {
            boolean var15;
            ContentValues var17;
            try {
                var17 = var10001.a(var10002);
            } catch (SQLiteException var11) {
                var14 = var11;
                var15 = false;
                break label63;
            }

            ContentValues var4 = var17;

            String var18;
            try {
                var18 = var17.get(Application.B("᭞릒ਭ蚏삓\u1a9cꜸ巬\uffde蛴")).toString();
            } catch (SQLiteException var10) {
                var14 = var10;
                var15 = false;
                break label63;
            }

            String var5 = var18;

            boolean var16;
            try {
                var16 = var10000.exists(var18);
            } catch (SQLiteException var9) {
                var14 = var9;
                var15 = false;
                break label63;
            }

            if (var16) {
                try {
                    this.destroy(var5);
                } catch (SQLiteException var8) {
                    var14 = var8;
                    var15 = false;
                    break label63;
                }
            }

            long var19;
            try {
                var19 = var12.insertOrThrow(Application.B("᭐릓ਧ蚇삂\u1a9bꜽ巠\uffc8"), (String) null, var4);
            } catch (SQLiteException var7) {
                var14 = var7;
                var15 = false;
                break label63;
            }

            var2 = var19;

            try {
                TSLog.logger.info(TSLog.ok(Application.B("\u1b7e릸ਛ蚤삵᪡Ꝿ巢\uffde蛩亡俐뚯躣섋퀡섮") + var5));
                return var2 > 0L;
            } catch (SQLiteException var6) {
                var14 = var6;
                var15 = false;
            }
        }

        SQLiteException var13 = var14;
        this.b.add(var13.getMessage());
        TSLog.logger.error(TSLog.error(var13.getMessage()), var13);
        return var2 > 0L;
    }

    public int create(List<TSGeofence> var1) {
        this.a();
        int var12 = 0;
        SQLiteDatabase var2;
        (var2 = this.b()).beginTransaction();
        Iterator var3 = var1.iterator();

        while (var3.hasNext()) {
            GeofenceDAO var14 = this;
            TSGeofence var10001 = (TSGeofence) var3.next();
            TSGeofence var10002 = var10001;

            label61:
            {
                SQLiteException var15;
                label69:
                {
                    boolean var16;
                    ContentValues var19;
                    try {
                        var19 = this.a(var10002);
                    } catch (SQLiteException var11) {
                        var15 = var11;
                        var16 = false;
                        break label69;
                    }

                    ContentValues var4 = var19;

                    String var18;
                    try {
                        var18 = var10001.getIdentifier();
                    } catch (SQLiteException var10) {
                        var15 = var10;
                        var16 = false;
                        break label69;
                    }

                    String var5 = var18;

                    boolean var17;
                    try {
                        var17 = var14.exists(var18);
                    } catch (SQLiteException var9) {
                        var15 = var9;
                        var16 = false;
                        break label69;
                    }

                    if (var17) {
                        try {
                            this.destroy(var5);
                        } catch (SQLiteException var8) {
                            var15 = var8;
                            var16 = false;
                            break label69;
                        }
                    }

                    long var20;
                    try {
                        var20 = var2.insertOrThrow(Application.B("᭐릓ਧ蚇삂\u1a9bꜽ巠\uffc8"), (String) null, var4);
                    } catch (SQLiteException var7) {
                        var15 = var7;
                        var16 = false;
                        break label69;
                    }

                    if (var20 <= 0L) {
                        continue;
                    }

                    try {
                        TSLog.logger.info(TSLog.ok(var5));
                        break label61;
                    } catch (SQLiteException var6) {
                        var15 = var6;
                        var16 = false;
                    }
                }

                SQLiteException var13 = var15;
                this.b.add(var13.getMessage());
                TSLog.logger.error(TSLog.error(var13.getMessage()), var13);
                continue;
            }

            ++var12;
        }

        var2.setTransactionSuccessful();
        var2.endTransaction();
        return var12;
    }

    public boolean destroy(String var1) {
        this.a();
        SQLiteDatabase var10000 = this.b();
        String[] var2;
        (var2 = new String[1])[0] = var1;
        int var3;
        if ((var3 = var10000.delete(Application.B("㷧鄲㌛鈞\ue04d䍃戳杏䫒"), Application.B("㷩鄳㌑鈖\ue05c䍄戶权䫄횰酚쇕ൂ雠"), var2)) == 1) {
            TSLog.logger.info(TSLog.ok(var1));
        } else if (var3 == 0) {
            this.b.add(Application.B("㷆鄶㌝鈔\ue04d䍉扰杞䫎훢鄜솁ഌ隻轪폇\uec86ꆮ沠\u2c2f兓ƚ갖膷촬") + var1 + Application.B("㶧"));
            TSLog.logger.warn(TSLog.warn(Application.B("㷦鄶㌝鈔\ue04d䍉扰杞䫎훢鄜솁ഌ隻轪") + var1));
        } else {
            this.b.add(Application.B("㷆鄶㌝鈔\ue04d䍉扰杞䫎훢鄞속\u0d11隫輸폏\uec9aꇡ没\u2c2f兒Ɵ갖臹쵨鍒῍ᙞ") + var1 + Application.B("㶧"));
            TSLog.logger.error(TSLog.error(Application.B("㷆鄖㌽鈴\ue008") + var1));
        }

        return var3 == 1;
    }

    public boolean destroyAll() {
        this.a();
        int var1;
        if ((var1 = this.b().delete(Application.B("좏꼀⫹퓊仿㿂\uee89迮ꫪ"), (String) null, (String[]) null)) >= 0) {
            TSLog.logger.info(Application.B("\uefed꽅⪶"));
        } else {
            this.b.add(Application.B("좬꼀⫥퓘仨㿃\uee93辫\uaaf8쯫噀䝬\ue47f쩯䴻↓뼃璷㹜Ὣ욈\ufb3d\ue278翮䣆뜺\uf6cbᶡ"));
            TSLog.logger.error(TSLog.error(Application.B("좮꼤⫟퓠仟㿨")));
        }

        return var1 >= 0;
    }

    private static final String TAG = "GeofenceDAO";

    public List<TSGeofence> all() {
        // $FF: Couldn't be decompiled
        Log.i(TAG, "all: aaaaaa");
        return null;
    }

    @SuppressLint("Range")
    public List<String> getIdentifiers() {
        GeofenceDAO var10000 = this;
        this.a();
        ArrayList var14;
        var14 = new ArrayList();
        Cursor var1 = null;
        SQLiteDatabase var16 = var10000.b();
        String var10001 = Application.B("乁ᜏ\udeb4\uf447阪᭑⦧ꮰ\ue673粺짰㕗㌦\uebcfቩ쯕ޠ豈靯蘹䚰穢䋾ࠈ톂㸇磞ᶻ迈\uf602䚽ແ");

        Throwable var17;
        label175:
        {
            Cursor var18;
            boolean var19;
            try {
                var18 = var16.rawQuery(var10001, (String[]) null);
            } catch (Throwable var13) {
                var17 = var13;
                var19 = false;
                break label175;
            }

            var1 = var18;

            while (true) {
                boolean var20;
                try {
                    var20 = var1.moveToNext();
                } catch (Throwable var12) {
                    var17 = var12;
                    var19 = false;
                    break;
                }

                if (!var20) {
                    if (var1 != null) {
                        var1.close();
                    }

                    return var14;
                }

                try {
                    var14.add(var1.getString(var1.getColumnIndex(Application.B("乻ᜮ\ude9d\uf46c阝᭬⧡ꮰ\ue672粭"))));
                } catch (Throwable var11) {
                    var17 = var11;
                    var19 = false;
                    break;
                }
            }
        }

        Throwable var15 = var17;
        if (var1 != null) {
            var1.close();
        }

        try {
            throw var15;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getIdentifiers: ");
        return null;
    }

    public List<TSGeofence> allWithinRadius(double param1, double param3, double param5, int param7) {
        // $FF: Couldn't be decompiled
        Log.i(TAG, "allWithinRadius: ");
        return null;
    }

    public TSGeofence find(String param1) {
        // $FF: Couldn't be decompiled
        Log.i(TAG, "find: ");
        return null;
    }

//    public List<TSGeofence> find(List<String> param1) {
//        // $FF: Couldn't be decompiled
//    }

    public boolean exists(String var1) {
        SQLiteDatabase var10000 = this.b();
        String[] var3;
        (var3 = new String[1])[0] = Application.B("㧄笩");
        String[] var2;
        (var2 = new String[1])[0] = var1;
        Cursor var4;
        boolean var5 = (var4 = var10000.query(Application.B("㧊笨磉\ue8da徨\uedb1ㄦ㥦ࠛ"), var3, Application.B("㧄笩磃\ue8d2徹\uedb6ㄣ㥪ࠍ\ufbd1늩ᖨ"), var2, (String) null, (String) null, (String) null, Application.B("㦜"))).getCount() > 0;
        var4.close();
        return var5;
    }

    public boolean exists(List<String> var1) {
        SQLiteDatabase var10000 = this.b();
        String[] var3;
        (var3 = new String[1])[0] = var1.toString();
        String[] var2;
        (var2 = new String[1])[0] = Application.B("㧄笩");
        Cursor var4;
        boolean var5 = (var4 = var10000.query(Application.B("㧊笨磉\ue8da徨\uedb1ㄦ㥦ࠛ"), var2, Application.B("㧄笩磃\ue8d2徹\uedb6ㄣ㥪ࠍ\ufbd1늴ᗞ쏿鵧䆶䎌"), var3, (String) null, (String) null, (String) null, Application.B("㦜"))).getCount() == var1.size();
        var4.close();
        return var5;
    }
}
