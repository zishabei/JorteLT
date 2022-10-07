//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.device;

import android.os.Build;
import android.os.Build.VERSION;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class DeviceSettingsRequest {
    private String mManufacturer;
    private String mModel;
    private String mVersion;
    private boolean mSeen = false;
    private long mLastSeenAt = 0L;
    private String mAction;

    public DeviceSettingsRequest(String var1) {
        this.mManufacturer = Build.MANUFACTURER;
        this.mModel = Build.MODEL;
        this.mVersion = VERSION.RELEASE;
        this.mAction = var1;
    }

    public DeviceSettingsRequest(String var1, long var2) {
        this.mManufacturer = Build.MANUFACTURER;
        this.mModel = Build.MODEL;
        this.mVersion = VERSION.RELEASE;
        this.mLastSeenAt = var2;
        boolean var4;
        if (var2 > 0L) {
            var4 = true;
        } else {
            var4 = false;
        }

        this.mSeen = var4;
        this.mAction = var1;
    }

    public String getAction() {
        return this.mAction;
    }

    public JSONObject toJson() {
        JSONObject var1;
        JSONObject var10000 = var1 = new JSONObject;
        JSONObject var10003 = var1;
        var1.<init>();
        String var10012 = this.mManufacturer;
        var1.put(Application.B("鹌뭺⿴ï܈賆鄐狄祌ꓒ쵼縄"), var10012);
        String var10010 = this.mModel;
        var1.put(Application.B("鹌뭴\u2ffeÿ܂"), var10010);
        String var10008 = this.mVersion;
        var1.put(Application.B("鹗뭾\u2fe8é܇賈鄝"), var10008);
        boolean var10006 = this.mSeen;
        var1.put(Application.B("鹒뭾\u2fffô"), var10006);
        long var3 = this.mLastSeenAt;
        var10003.put(Application.B("鹍뭺\u2fe9îܽ賂鄖狞祸ꓔ"), var3);
        String var10002 = this.mAction;
        var10000.put(Application.B("鹀뭸\u2feeó܁賉"), var10002);
        return var10000;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap;
        var1.<init>();
        String var10012 = this.mManufacturer;
        var1.put(Application.B("죳ᦲ\u202b䠌軘ꑦ㪶ఌ㸁켳ꨭᛀ"), var10012);
        String var10010 = this.mModel;
        var1.put(Application.B("죳ᦼ‡䠜軒"), var10010);
        String var10008 = this.mVersion;
        var1.put(Application.B("죨ᦶ‷䠊軗ꑨ㪻"), var10008);
        var1.put(Application.B("죭ᦶ†䠗"), this.mSeen);
        var1.put(Application.B("죲ᦲ‶䠍軭ꑢ㪰ఖ㸵켵"), this.mLastSeenAt);
        String var10002 = this.mAction;
        var10000.put(Application.B("죿ᦰ‱䠐軑ꑩ"), var10002);
        return var10000;
    }
}
