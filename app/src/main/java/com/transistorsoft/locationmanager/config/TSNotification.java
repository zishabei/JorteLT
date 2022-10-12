//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.config;

import android.util.Log;

import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TSNotification extends a implements IModule {
    public static final String NAME = Application.B("垯ᐥ賂\u1cb5㔨䫈婆置梁\uea1f꒞ﵡ");
    static final String DEFAULT_TITLE = "";
    static final String DEFAULT_TEXT = Application.B("垍ᐥ寧\u1cbd㔺䫈婊罡冷\uea25꒔ﵽޔ抖홻\u0dd7ᢶ哔죅\ud92f乖᧭列녻屗\uf7ac");
    static final Integer DEFAULT_PRIORITY = 0;
    static final String DEFAULT_CHANNEL_NAME = Application.B("垕ᐙ殺\u1cb3㔭䫀婑罦率\uea18꒼ﵮތ択홿\u0dd7ᣤ");
    private String mLayout = null;
    private String mTitle = null;
    private String mText = null;
    private String mSmallIcon = null;
    private String mLargeIcon = null;
    private Integer mPriority = null;
    private String mColor = null;
    private String mChannelName = null;
    private Map<String, String> mStrings = null;
    private List<String> mActions = null;
    private Boolean mSticky = null;

    public TSNotification() {
        super(Application.B("镀櫑糅뤈坹뛾쳪桢ꏶ\ue5ed暮ꬨ"));
        this.applyDefaults();
    }

    public TSNotification(JSONObject param1, boolean param2) {
        this();
        // $FF: Couldn't be decompiled
    }

    public void applyDefaults() {
        if (this.mTitle == null) {
            this.mTitle = "";
        }

        if (this.mText == null) {
            this.mText = Application.B("\ue258ǚꓒ訆ࢥ\uaafd\u2ef8눋ꅮ䴠둒ނ恮ᣨ꒧檈\uf44e\uf24b\u0effꀯꉁ춀\udb13磌\ue18c鞟");
        }

        if (this.mPriority == null) {
            this.mPriority = DEFAULT_PRIORITY;
        }

        if (this.mColor == null) {
            this.mColor = "";
        }

        if (this.mSmallIcon == null) {
            this.mSmallIcon = "";
        }

        if (this.mLargeIcon == null) {
            this.mLargeIcon = "";
        }

        if (this.mChannelName == null) {
            this.mChannelName = Application.B("\ue240Ǧꓽ計ࢲꫵ⻣눌ꄡ䴝둺ޑ恶ᣠ꒣檈\uf41c");
        }

        if (this.mLayout == null) {
            this.mLayout = "";
        }

        if (this.mStrings == null) {
            HashMap var1;
            var1 = new HashMap();
            this.mStrings = var1;
        }

        if (this.mActions == null) {
            ArrayList var2;
            var2 = new ArrayList();
            this.mActions = var2;
        }

        if (this.mSticky == null) {
            this.mSticky = false;
        }

    }

    public boolean update(TSNotification var1) {
        this.resetDirty();
        if (var1.getTitle() != null && !var1.getTitle().equals(this.mTitle)) {
            this.mTitle = var1.getTitle();
            this.addDirty(Application.B("퐋\udec4\ue34c祚\ue53f"));
        }

        if (var1.getText() != null && !var1.getText().equals(this.mText)) {
            this.mText = var1.getText();
            this.addDirty(Application.B("퐋\udec8\ue340祂"));
        }

        if (var1.getLayout() != null && !var1.getLayout().equals(this.mLayout)) {
            this.mLayout = var1.getLayout();
            this.addDirty(Application.B("퐓\udecc\ue341祙\ue52f㍷"));
        }

        if (var1.getColor() != null && !var1.getColor().equals(this.mColor)) {
            this.mColor = var1.getColor();
            this.addDirty(Application.B("퐜\udec2\ue354祙\ue528"));
        }

        if (var1.getSmallIcon() != null && !var1.getSmallIcon().equals(this.mSmallIcon)) {
            this.mSmallIcon = var1.getSmallIcon();
            this.addDirty(Application.B("퐌\udec0\ue359祚\ue536㍊滃륙吷"));
        }

        if (var1.getLargeIcon() != null && !var1.getLargeIcon().equals(this.mLargeIcon)) {
            this.mLargeIcon = var1.getLargeIcon();
            this.addDirty(Application.B("퐓\udecc\ue34a祑\ue53f㍊滃륙吷"));
        }

        if (var1.getPriority() != null && !var1.getPriority().equals(this.mPriority)) {
            this.mPriority = var1.getPriority();
            this.addDirty(Application.B("퐏\udedf\ue351祙\ue528㍪滔륏"));
        }

        if (var1.getSticky() != null && !var1.getSticky().equals(this.mSticky)) {
            this.mSticky = var1.getSticky();
            this.addDirty(Application.B("퐌\uded9\ue351祕\ue531㍺"));
        }

        if (var1.getActions() != null && (!this.mActions.containsAll(var1.getActions()) || var1.getActions().size() != this.mActions.size())) {
            this.mActions = var1.getActions();
            this.addDirty(Application.B("퐞\udece\ue34c祟\ue535㍭滓"));
        }

        if (var1.getStrings() != null && !var1.getStrings().equals(this.mStrings)) {
            this.mStrings = var1.getStrings();
            this.addDirty(Application.B("퐌\uded9\ue34a祟\ue534㍤滓"));
        }

        if (var1.getChannelName() != null && !var1.getChannelName().equals(this.mChannelName)) {
            this.mChannelName = var1.getChannelName();
            this.addDirty(Application.B("퐜\udec5\ue359祘\ue534㍦滌른吸跞鑷"));
        }

        return this.getDirtyFields().isEmpty() ^ true;
    }

    public void setLayout(String var1) {
        this.mLayout = var1;
    }

    public String getLayout() {
        return this.mLayout;
    }

    public void setTitle(String var1) {
        this.mTitle = var1;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setText(String var1) {
        this.mText = var1;
    }

    public String getText() {
        return this.mText;
    }

    public void setColor(String var1) {
        this.mColor = var1;
    }

    public String getColor() {
        return this.mColor;
    }

    public Integer getPriority() {
        return this.mPriority;
    }

    public void setPriority(Integer var1) {
        this.mPriority = var1;
    }

    public String getChannelName() {
        return this.mChannelName;
    }

    public void setChannelName(String var1) {
        this.mChannelName = var1;
    }

    public void setSmallIcon(String var1) {
        this.mSmallIcon = var1;
    }

    public String getSmallIcon() {
        return this.mSmallIcon;
    }

    public void setLargeIcon(String var1) {
        this.mLargeIcon = var1;
    }

    public String getLargeIcon() {
        return this.mLargeIcon;
    }

    public void addString(String var1, String var2) {
        this.mStrings.put(var1, var2);
    }

    public String getString(String var1) {
        return !this.mStrings.containsKey(var1) ? null : (String) this.mStrings.get(var1);
    }

    public Map<String, String> getStrings() {
        return this.mStrings;
    }

    public void addAction(String var1) {
        this.mActions.add(var1);
    }

    public List<String> getActions() {
        return this.mActions;
    }

    public void setSticky(Boolean var1) {
        this.mSticky = var1;
    }

    public Boolean getSticky() {
        return this.mSticky;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap();
        String var10022 = this.mLayout;
        var1.put(Application.B("첋懁\ud920ⷸ\u17ea\uaaf8"), var10022);
        String var10020 = this.mTitle;
        var1.put(Application.B("첓應\ud92dⷻ\u17fa"), var10020);
        String var10018 = this.mText;
        var1.put(Application.B("첓懅\ud921ⷣ"), var10018);
        String var10016 = this.mColor;
        var1.put(Application.B("첄懏\ud935ⷸ\u17ed"), var10016);
        String var10014 = this.mChannelName;
        var1.put(Application.B("첄懈\ud938ⷹ៱ꫩ욣៦\ue9f0\udf05⼩"), var10014);
        String var10012 = this.mSmallIcon;
        var1.put(Application.B("첔懍\ud938ⷻ៳\uaac5욬ះ\ue9ff"), var10012);
        String var10010 = this.mLargeIcon;
        var1.put(Application.B("첋懁\ud92bⷰ\u17fa\uaac5욬ះ\ue9ff"), var10010);
        Integer var10008 = this.mPriority;
        var1.put(Application.B("첗懒\ud930ⷸ\u17edꫥ욻៑"), var10008);
        Map var10006 = this.mStrings;
        var1.put(Application.B("첔懔\ud92bⷾ៱ꫫ욼"), var10006);
        List var10004 = this.mActions;
        var1.put(Application.B("첆懃\ud92dⷾ៰ꫢ욼"), var10004);
        Boolean var10002 = this.mSticky;
        var10000.put(Application.B("첔懔\ud930ⷴ៴ꫵ"), var10002);
        return var10000;
    }

    public JSONObject toJson(boolean var1) {
        TSNotification var10000 = this;
        JSONObject var29;
        JSONObject var10001 = var29 = new JSONObject();
        TSNotification var10002 = this;
        JSONObject var10003 = var29;
        TSNotification var10004 = this;
        JSONObject var10005 = var29;
        TSNotification var10006 = this;
        JSONObject var10007 = var29;
        TSNotification var10008 = this;
        JSONObject var10009 = var29;
        TSNotification var10010 = this;
        JSONObject var10011 = var29;
        TSNotification var10012 = this;
        JSONObject var10013 = var29;
        TSNotification var10014 = this;
        JSONObject var10015 = var29;
        TSNotification var10016 = this;
        JSONObject var10017 = var29;
        TSNotification var10018 = this;

        JSONException var31;
        label180:
        {
            JSONObject var2 = new JSONObject();
            JSONArray var3 = new JSONArray();
            String var4 = Application.B("壄᠅嬘\ue5c8嘥棷");

            try {
                var10017.put(var4, var10018.mLayout);
            } catch (JSONException var23) {
                var31 = var23;
                break label180;
            }

            var4 = Application.B("壜᠍嬕\ue5cb嘵");

            try {
                var10015.put(var4, var10016.mTitle);
            } catch (JSONException var22) {
                var31 = var22;
                break label180;
            }

            var4 = Application.B("壜᠁嬙\ue5d3");

            try {
                var10013.put(var4, var10014.mText);
            } catch (JSONException var21) {
                var31 = var21;
                break label180;
            }

            var4 = Application.B("壋᠋嬍\ue5c8嘢");

            try {
                var10011.put(var4, var10012.mColor);
            } catch (JSONException var20) {
                var31 = var20;
                break label180;
            }

            var4 = Application.B("壋᠌嬀\ue5c9嘾棦ㇿ⠸励텵梿");

            try {
                var10009.put(var4, var10010.mChannelName);
            } catch (JSONException var19) {
                var31 = var19;
                break label180;
            }

            var4 = Application.B("壛᠉嬀\ue5cb嘼棊ㇰ⠙劾");

            try {
                var10007.put(var4, var10008.mSmallIcon);
            } catch (JSONException var18) {
                var31 = var18;
                break label180;
            }

            var4 = Application.B("壄᠅嬓\ue5c0嘵棊ㇰ⠙劾");

            try {
                var10005.put(var4, var10006.mLargeIcon);
            } catch (JSONException var17) {
                var31 = var17;
                break label180;
            }

            var4 = Application.B("壘᠖嬈\ue5c8嘢棪\u31e7⠏");

            try {
                var10003.put(var4, var10004.mPriority);
            } catch (JSONException var16) {
                var31 = var16;
                break label180;
            }

            var4 = Application.B("壛᠐嬈\ue5c4嘻棺");

            try {
                var10001.put(var4, var10002.mSticky);
            } catch (JSONException var15) {
                var31 = var15;
                break label180;
            }

            Iterator var34;
            var34 = var10000.mStrings.entrySet().iterator();

            Iterator var30 = var34;

            label122:
            while (true) {
                boolean var35;
                var35 = var30.hasNext();

                if (!var35) {
                    try {
                        var10000 = this;
                        var29.put(Application.B("壛᠐嬓\ue5ce嘾棤㇠"), var2);
                    } catch (JSONException var10) {
                        var31 = var10;
                        break;
                    }

                    var34 = var10000.mActions.iterator();

                    Iterator var27 = var34;

                    while (true) {
                        var35 = var27.hasNext();

                        if (!var35) {
                            try {
                                var29.put(Application.B("壉᠇嬕\ue5ce嘿棭㇠"), var3);
                                return var29;
                            } catch (JSONException var6) {
                                var31 = var6;
                                break label122;
                            }
                        }

                        var3.put((String) var27.next());
                    }
                }

                String var33;
                JSONObject var36;
                Entry var37;
                var36 = var2;
                var37 = (Entry) var30.next();
                var33 = (String) var37.getKey();

                String var5 = var33;

                try {
                    var36.put(var5, var37.getValue());
                } catch (JSONException var12) {
                    var31 = var12;
                    break;
                }
            }
        }

        JSONException var28 = var31;
        Log.i(Application.B("壼ᠷ嬭\ue5c8嘳棢\u31e7⠟势텶梗变惟謁꽊톣갭"), TSLog.error(var28.getMessage()));
        TSLog.logger.error(TSLog.error(var28.getMessage()), var28);
        var28.printStackTrace();
        return var29;
    }

    public boolean equals(TSNotification var1) {
        String var2;
        Boolean var3;
        List var4;
        Map var5;
        Integer var6;
        return (var2 = this.mLayout) != null && var2.equals(var1.getLayout()) && (var4 = this.mActions) != null && var4.containsAll(var1.getActions()) && (var5 = this.mStrings) != null && var5.equals(var1.getStrings()) && (var2 = this.mTitle) != null && var2.equals(var1.getTitle()) && (var2 = this.mText) != null && var2.equals(var1.getText()) && (var2 = this.mChannelName) != null && var2.equals(var1.getChannelName()) && (var2 = this.mColor) != null && var2.equals(var1.getColor()) && (var6 = this.mPriority) != null && var6.equals(var1.getPriority()) && (var2 = this.mSmallIcon) != null && var2.equals(var1.getSmallIcon()) && (var2 = this.mLargeIcon) != null && var2.equals(var1.getLargeIcon()) && (var3 = this.mSticky) != null && var3.equals(var1.getSticky());
    }
}
