//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.os.Build.VERSION;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
    public static final String ACTIVITY_NAME_STILL = Application.B("쳬\ud8c6\uecc5\ue8d7ﾲ");
    public static final String ACTIVITY_NAME_ON_FOOT = Application.B("쳰\ud8dc\uecf3\ue8ddﾱ뀱祯");
    public static final String ACTIVITY_NAME_IN_VEHICLE = Application.B("쳶\ud8dc\uecf3\ue8cdﾻ뀶祲爔\ufaf7쨒");
    public static final String ACTIVITY_NAME_ON_BICYCLE = Application.B("쳰\ud8dc\uecf3\ue8d9ﾷ뀽祢爔\ufaf7쨒");
    public static final String ACTIVITY_NAME_RUNNING = Application.B("쳭\ud8c7\uecc2\ue8d5ﾷ뀰祼");
    public static final String ACTIVITY_NAME_WALKING = Application.B("쳨\ud8d3\uecc0\ue8d0ﾷ뀰祼");
    public static final String ACTIVITY_NAME_UNKNOWN = Application.B("쳪\ud8dc\uecc7\ue8d5ﾱ뀩祵");
    public static final String ACTIVITY_NAME_TILTING = Application.B("쳫\ud8db\uecc0\ue8cfﾷ뀰祼");
    private static AtomicBoolean a;

    public Util() {
    }

    public static boolean isRunningTest() {
        if (a == null) {
            boolean var0;
            label14: {
                try {
                    Class.forName(Application.B("쌧캤纚哧絗톪\uf675㩟\uda14嶭䒱搁␇䦩䖃虺\udae6隞燘╧椣툥ꈏﴰ莓\uf6f9㢄䅅\ue414컣園"));
                } catch (ClassNotFoundException var1) {
                    var0 = false;
                    break label14;
                }

                var0 = true;
            }

            a = new AtomicBoolean(var0);
        }

        return a.get();
    }

    public static JSONObject mergeJson(JSONObject param0, JSONObject param1) {
        // $FF: Couldn't be decompiled
    }

    public static Integer getActivityId(String var0) {
        if (var0.equals(Application.B("磁⫛\uf41eᠼ悒ﰨﯚ꺤ʕ劁"))) {
            return 0;
        } else if (var0.equals(Application.B("磇⫛\uf41eᠨ悞ﰣ\ufbca꺤ʕ劁"))) {
            return 1;
        } else if (var0.equals(Application.B("磇⫛\uf41eᠬ悘ﰯ\ufbc7"))) {
            return 2;
        } else if (var0.equals(Application.B("磚⫀\uf42fᠤ悞ﰮﯔ"))) {
            return 8;
        } else if (var0.equals(Application.B("磟⫔\uf42dᠡ悞ﰮﯔ"))) {
            return 7;
        } else if (var0.equals(Application.B("磛⫁\uf428ᠦ悛"))) {
            return 3;
        } else if (var0.equals(Application.B("磝⫛\uf42aᠤ悘ﰷﯝ"))) {
            return 4;
        } else {
            return var0.equals(Application.B("磜⫝̸\uf42dᠾ悞ﰮﯔ")) ? 5 : 4;
        }
    }

    public static String getActivityName(int var0) {
        switch(var0) {
            case 0:
                return Application.B("\ue395쌗殅撳ᇷꀵ\ue68f霂\uf1d1퍻");
            case 1:
                return Application.B("\ue393쌗殅撧ᇻꀾ\ue69f霂\uf1d1퍻");
            case 2:
                return Application.B("\ue393쌗殅撣ᇽꀲ\ue692");
            case 3:
                return Application.B("\ue38f쌍殳撩ᇾ");
            case 4:
            case 6:
            default:
                return Application.B("\ue389쌗殱撫ᇽꀪ\ue688");
            case 5:
                return Application.B("\ue388쌐殶撱ᇻꀳ\ue681");
            case 7:
                return Application.B("\ue38b쌘殶撮ᇻꀳ\ue681");
            case 8:
                return Application.B("\ue38e쌌殴撫ᇻꀳ\ue681");
        }
    }

    public static Map<String, Object> toMap(JSONObject var0) {
        HashMap var1;
        var1 = new HashMap.<init>();

        String var3;
        Object var4;
        for(Iterator var2 = var0.keys(); var2.hasNext(); var1.put(var3, var4)) {
            if ((var4 = var0.get(var3 = (String)var2.next())) instanceof JSONArray) {
                var4 = toList((JSONArray)var4);
            } else if (var4 instanceof JSONObject) {
                var4 = toMap((JSONObject)var4);
            }
        }

        return var1;
    }

    public static List<Object> toList(JSONArray var0) {
        ArrayList var1;
        var1 = new ArrayList.<init>();

        for(int var2 = 0; var2 < var0.length(); ++var2) {
            Object var3;
            if ((var3 = var0.get(var2)) instanceof JSONArray) {
                var3 = toList((JSONArray)var3);
            } else if (var3 instanceof JSONObject) {
                var3 = toMap((JSONObject)var3);
            }

            var1.add(var3);
        }

        return var1;
    }

    public static String joinString(List<String> var0, String var1) {
        List var10000 = var0;
        StringBuilder var5;
        var5 = new StringBuilder.<init>();
        boolean var2 = true;

        String var4;
        for(Iterator var3 = var10000.iterator(); var3.hasNext(); var5.append(var4)) {
            var4 = (String)var3.next();
            if (var2) {
                var2 = false;
            } else {
                var5.append(var1);
            }
        }

        return var5.toString();
    }

    public static int getPendingIntentFlags(int var0) {
        return VERSION.SDK_INT >= 31 ? var0 | 33554432 : var0;
    }
}
