//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.event;

import com.google.android.gms.location.ActivityTransitionEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.locationmanager.util.Util;
import com.transistorsoft.tslocationmanager.Application;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityChangeEvent {
    private final ActivityTransitionEvent mActivity;

    public ActivityChangeEvent(ActivityTransitionEvent var1) {
        this.mActivity = var1;
    }

    public ActivityTransitionEvent getDetectedActivity() {
        return this.mActivity;
    }

    public String getActivityName() {
        return Util.getActivityName(this.mActivity.getActivityType());
    }

    public JSONObject toJson() {
        JSONObject var10000 = new JSONObject;
        JSONObject var1;
        JSONObject var10001 = var1 = var10000;
        var10000.<init>();
        String var10002 = Application.B("\ue640퉨蜝謍\ua4cd鼪䨣阅");

        JSONException var5;
        label29: {
            boolean var6;
            try {
                var10001.put(var10002, Util.getActivityName(this.mActivity.getActivityType()));
            } catch (JSONException var3) {
                var5 = var3;
                var6 = false;
                break label29;
            }

            try {
                var10000.put(Application.B("\ue642퉤蜇謂ꓒ鼧䨲阒뢸ⓤ"), 100);
                return var1;
            } catch (JSONException var2) {
                var5 = var2;
                var6 = false;
            }
        }

        JSONException var4 = var5;
        TSLog.logger.error(TSLog.error(var4.getMessage()));
        var4.printStackTrace();
        return var1;
    }

    public Map<String, Object> toMap() {
        HashMap var10000 = new HashMap();
        var10000.put(Application.B("葻糶⢇潀ᄑ혯洞읫"), this.getActivityName());
        var10000.put(Application.B("葹糺⢝潏ᄎ혢洏일ꁁ辶"), 100);
        return var10000;
    }
}
