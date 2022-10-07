//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.config;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import com.transistorsoft.locationmanager.activity.TSLocationManagerActivity;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import com.transistorsoft.tslocationmanager.R.id;
import com.transistorsoft.tslocationmanager.R.layout;
import com.transistorsoft.tslocationmanager.R.style;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class TSBackgroundPermissionRationale extends a implements IModule {
    public static final String NAME = Application.B("뼊尔扂索ऴ깦\ueeeb쳿䮅훻ᕞ쭸뜵펇ퟺ䎈鸺䕴ᣁ슄祤\uf7b8\uf65c\uf2f7聹쒱\ufae9\ue927眆");
    private static final String TEMPLATE_TAG_APPLICATION_NAME = Application.B("뼉尅扑紥ऺ깷\ueee5쳾䮂훰ᕠ쭓뜦펇ퟶ");
    private static final String TEMPLATE_TAG_BACKGROUND_PERMISSION_OPTION_LABEL = Application.B("뼊尔扂索ऴ깦\ueeeb쳿䮅훻ᕞ쭸뜵펇ퟺ䎈鸺䕴ᣁ슄祹\uf7a9\uf65c\uf2f7聹쒱醙\ue92a省隥搵");
    private static final List<String> sTemplateTags;
    private static final String BACKGROUND_PERMISSION_OPTION_LABEL = Application.B("뼩尙才紦त긴\ueee5쳦䮇횿ᕺ쭵뜢폊ퟧ䎒鸤䕸");
    private static final String DEFAULT_TITLE = Application.B("뼩尙才紦त긴\ueeff쳫䮛훯ᕢ쭴뜤펋ퟧ䎒鸦䕳ᣠ슋祛\uf7bc\uf655\uf2be聢쒰直\ue92a眀隣搼㧵ంޑ텮檖뿦믯少鏩摉\uf366퇴ﾼ\ueb88辟䒛␚ᰴ\u0bac䜇织鋥熊얃技䶮녹\ue33c\uef99〥旦\uabfbੴﯶ䵀䩇ꄡݸ\ued1a\ue043ᬶ瀷ﳾ\uf085\uea36䒓㼷뎁꒯ᮧ㑗㚀腓訆骠ȋꁆ");
    private static final String DEFAULT_MESSAGE = Application.B("뼳尶扩紈झ깓\ueec1쳇䮮훂ᔮ쭉뜯펃ퟠ䏛鸨䕭ᣞ싊祕\uf7b6\uf644\uf2f2聳쒼\ufafc\ue938睃隬搶㧥ఐ߅텳檑뿡뮼展鏬摘\uf371톽ﾹ\ueb82迊䓈⑼ᰝஂ䜰绳鋃熦엌抶䶮녽\ue324\uef98に斀ꯉ\u0a5d\ufbc7䵻䨵ꄇܴ\ued2c\ue01e");
    private static final String DEFAULT_OK_BUTTON = Application.B("뼫尝所紧ऴ깱\ueea4쳾䮄횿ᔬ쭦뜥펋ퟰ䎐鸮䕯ᣁ슟祘\uf7bd\uf678\uf2fb聤쒲\ufae1\ue938眐隩搶㧨ా߁텮檗뿠믲屽鏬摎\uf375퇱ﾢ\uebcf");
    private static final String TITLE_FIELD = Application.B("뼜尜払紥श");
    private static final String MESSAGE_FIELD = Application.B("뼅尐扒紺ल깳\ueee1");
    private static final String POSITIVE_ACTION_FIELD = Application.B("뼘尚扒素ध깽\ueef2쳯䮪훼ᕺ쭴뜨펄");
    private static final String NEGATIVE_ACTION_FIELD = Application.B("뼆尐扆紨ध깽\ueef2쳯䮪훼ᕺ쭴뜨펄");
    private String mTitle;
    private String mMessage;
    private String mPositiveAction;
    private String mNegativeAction;
    private Dialog mDialog;
    private TSBackgroundPermissionRationale.CompletionHandler mDialogCompletionHandler;
    private com.transistorsoft.locationmanager.activity.TSLocationManagerActivity.CompletionHandler mActivityCompletionHandler;
    private final AtomicBoolean misDialogActive;

    private static String renderTemplate(Context var0, String var1) {
        PackageManager var2 = var0.getPackageManager();
        ApplicationInfo var3 = var0.getApplicationInfo();
        Iterator var4 = sTemplateTags.iterator();

        while(var4.hasNext()) {
            String var5;
            String var10000 = var5 = (String)var4.next();
            String var6 = null;
            if (var10000.equals(Application.B("\ua635崚\uda26䫯므鱠뮄珋ᓷᆫ䦖ਠ엜헑℈"))) {
                int var7;
                if ((var7 = var3.labelRes) == 0) {
                    var6 = var3.nonLocalizedLabel.toString();
                } else {
                    var6 = var0.getString(var7);
                }
            } else if (var5.equals(Application.B("\ua636崋\uda35䫨믎鱱뮊珊ᓰᆠ䦨\u0a0b엏헑℄渚锉뎷〭챖⫵\u001a뿸ڷ坅鹁꼆킼䪇묾҈"))) {
                if (VERSION.SDK_INT >= 30) {
                    var6 = var2.getBackgroundPermissionOptionLabel().toString();
                } else {
                    TSLog.logger.warn(TSLog.warn(Application.B("\ua636崋\uda35䫨믎鱱뮊珊ᓰᆠ䦨\u0a0b엏헑℄渚锉뎷〭챖⫨\u000b뿸ڷ坅鹁꼫킱䪀뭻҅\ud919紬푂斞㱓쥭⥝頟\ue976諝꜁띃将缘껴韞柧ᯇ\ue4c8⊣嵊⧄딾⟛睟㰲怇蓜쳛\u0c4f昲\uecfcࢧ撻\ue61f쓍ᕑ躊ᵴ狑\ue2d9䉬楛\uddf5\ue583㶲ႌ猌ᖌ룐죬멋አ螋㳀ㅣ鰳ზ쟪锽藁鿕蘟冷\uda1b겳莫캚뫐⦐\u2ef9鞮\udb18䇼⌤Ⴧ颃ԩ䗝标\ued00쿷냎鳛橌\ue004텯\ua7f0鈉\ue479\uf047燆㗼艜蔶쎓啨\uf71e倫鎾䑰鎉臡\udc27ꦛ偯\ue5ee丛ꓤ꼽䤨ꎰ盳퉯菠ꊮ裷Њ")));
                    var6 = Application.B("ꘕ崆\uda3a䫬믞鰣뮄珓ᓲᇤ䦌ਆ엘햜ℙ臭锗뎻");
                }
            }

            if (var6 != null) {
                var1 = var1.replaceAll(Application.B("ꘈ崑") + var5 + Application.B("ꘈ崗"), var6);
            }
        }

        return var1;
    }

    public TSBackgroundPermissionRationale() {
        TSBackgroundPermissionRationale var10000 = this;
        TSBackgroundPermissionRationale var10001 = this;
        super(Application.B("\ufb12鎓硳\uf6a1ᶳ䪎쀝㽹倁㖔㼽㖳匿娥\uef47ി쎲夫땲㇍곒稗৴ก\uaa4f鲁ꭎї盟"));
        AtomicBoolean var1;
        var1 = new AtomicBoolean.<init>(false);
        var10001.misDialogActive = var1;
        var10000.applyDefaults();
    }

    public TSBackgroundPermissionRationale(JSONObject var1, boolean var2) {
        super(Application.B("\ufb12鎓硳\uf6a1ᶳ䪎쀝㽹倁㖔㼽㖳匿娥\uef47ി쎲夫땲㇍곒稗৴ก\uaa4f鲁ꭎї盟"));
        this.misDialogActive = new AtomicBoolean(false);
        if (var1.has(Application.B("ﬄ鎛硤\uf6a6ᶱ"))) {
            this.mTitle = var1.getString(Application.B("ﬄ鎛硤\uf6a6ᶱ"));
        }

        if (var1.has(Application.B("יִ鎗硣\uf6b9ᶵ䪛쀗"))) {
            this.mMessage = var1.getString(Application.B("יִ鎗硣\uf6b9ᶵ䪛쀗"));
        }

        if (var1.has(Application.B("ﬀ鎝硣\uf6a3ᶠ䪕쀄㽩倮㖓㼙㖿匢娦"))) {
            this.mPositiveAction = var1.getString(Application.B("ﬀ鎝硣\uf6a3ᶠ䪕쀄㽩倮㖓㼙㖿匢娦"));
        }

        if (var1.has(Application.B("ﬞ鎗硷\uf6abᶠ䪕쀄㽩倮㖓㼙㖿匢娦"))) {
            this.mNegativeAction = var1.getString(Application.B("ﬞ鎗硷\uf6abᶠ䪕쀄㽩倮㖓㼙㖿匢娦"));
        }

        if (var2) {
            this.applyDefaults();
        }

    }

    private void handleDialogClick(View var1) {
        this.misDialogActive.set(false);
        Dialog var2;
        if ((var2 = this.mDialog) != null) {
            var2.dismiss();
        }

        com.transistorsoft.locationmanager.activity.TSLocationManagerActivity.CompletionHandler var3;
        if ((var3 = this.mActivityCompletionHandler) != null) {
            var3.onComplete();
        }

        if (this.mDialogCompletionHandler != null) {
            if (var1.getId() == id.btn_positive_action) {
                this.mDialogCompletionHandler.onClickOk();
            } else {
                this.mDialogCompletionHandler.onClickCancel();
            }
        }

        this.mDialog = null;
        this.mDialogCompletionHandler = null;
        this.mActivityCompletionHandler = null;
    }

    static {
        (sTemplateTags = new ArrayList()).add(Application.B("뼉尅扑紥ऺ깷\ueee5쳾䮂훰ᕠ쭓뜦펇ퟶ"));
        sTemplateTags.add(Application.B("뼊尔扂索ऴ깦\ueeeb쳿䮅훻ᕞ쭸뜵펇ퟺ䎈鸺䕴ᣁ슄祹\uf7a9\uf65c\uf2f7聹쒱醙\ue92a省隥搵"));
    }

    @TargetApi(29)
    public boolean shouldShow(Activity var1) {
        return var1 != null && !this.misDialogActive.get() ? ActivityCompat.shouldShowRequestPermissionRationale(var1, Application.B("➑儣ꂇ\u001d♨挵⇐Ẍ尣齃蔉\uf1c0㺃셠⧇悳匣ꗱᒍꄧ昒ჹꕺ鲟밗뢡ꄉ〠ꗀ鞫瘕쫸㸝喦忒Ч\ue489\ue173\uf851均䋳ᗧᇌ䣊\udcb9")) : false;
    }

    public void show(Activity var1, TSBackgroundPermissionRationale.CompletionHandler var2) {
        if (var1 != null && !this.misDialogActive.get()) {
            this.misDialogActive.set(true);
            this.mDialogCompletionHandler = var2;
            TSLocationManagerActivity.start(var1.getApplicationContext(), Application.B("\uec23Ȇ\u0881ｎஶ쭻㺻\ude0e䕎佝넯媘벳纞밊鍴悠ꪼﾇ瀀瞌\uf3dd毕\udb89\ud801耸\ue83e頢挹嗉ᔳꢪ\uf4a4L\uf306䩚冐擥ࢳ깟ዀ엾㗟ﰍࢽ"));
        }
    }

    public void onStartActivity(Activity var1, com.transistorsoft.locationmanager.activity.TSLocationManagerActivity.CompletionHandler var2) {
        if (!this.misDialogActive.get()) {
            var2.onComplete();
        } else {
            this.mActivityCompletionHandler = var2;
            (this.mDialog = new Dialog(var1, style.PermissionRationaleDialog)).setContentView(layout.tslocationmanager_permission_rationale_layout);
            this.mDialog.setCancelable(false);
            ((TextView)this.mDialog.findViewById(id.title)).setText(renderTemplate(var1, this.mTitle));
            ((TextView)this.mDialog.findViewById(id.message)).setText(renderTemplate(var1, this.mMessage));
            Button var10002 = (Button)this.mDialog.findViewById(id.btn_positive_action);
            var10002.setText(renderTemplate(var1, this.mPositiveAction));
            OnClickListener var4;
            OnClickListener var10003 = var4 = new OnClickListener() {
                public void onClick(View var1) {
                    TSBackgroundPermissionRationale.this.handleDialogClick(var1);
                }
            };
            var10003.<init>();
            var10002.setOnClickListener(var10003);
            Button var3 = (Button)this.mDialog.findViewById(id.btn_negative_action);
            if (!this.mNegativeAction.isEmpty()) {
                var3.setText(renderTemplate(var1, this.mNegativeAction));
            }

            var3.setOnClickListener(var4);
            this.mDialog.show();
        }
    }

    public void applyDefaults() {
        if (this.mTitle == null) {
            this.mTitle = Application.B("齋톟픳㟴뎙㜗ᆈ䳀\ud8e5픩ᲅ串쁉쁆뻊㪉ᄬ⛉楓叝ゲ\ue9f3뫌汬枵띐绕\ude20涽逨狞礰爉繻뛙ਞ狜ᔭ蟬⼻鱆ᆲ炜璬쓾ᕏ\uee13뻻똥㌞ᓔ戦㌡\udd01렱\udb01\ud9a3徘៩큔ʙ㱿雵\uec3e婝啈얟壧ꓟ⩹\ue01f\uf3f7ퟷ昰롙掖扇\ue1f0씞潴ු⎢‰틥셖䇂\uebf9酬");
        }

        if (this.mMessage == null) {
            this.mMessage = Application.B("齑톰픗㟚뎠㝰ᆶ䳬\ud8d0프\u1cc9丏쁂쁎뻍㫀ᄢ⛗業厜ゼ\ue9f9뫝氠枤띜纁\ude32淾逧狔礠爛縯뛄ਙ狛ᕾ螨⼾鱗ᆥ烕璩쓴ᔚ\uee40뺝똌㌰ᓣ戒㌇\udd2d롾\udb37\ud9a3徜៱큕˗㰙雇\uec17婬啳역壁꒓⩏\ue042");
        }

        if (this.mPositiveAction == null) {
            this.mPositiveAction = Application.B("齉톛픾㟵뎉㝒ᇓ䳕\ud8fa핹\u1ccb丠쁈쁆뻝㪋ᄤ⛕楲叉ケ\ue9f2뫡氩枳띒纜\ude32涭逢狔礭爵縫뛙ਟ狚ᔰ螀⼾鱁ᆡ炙璲쒹");
        }

        if (this.mNegativeAction == null) {
            this.mNegativeAction = "";
        }

    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String var1) {
        this.mTitle = var1;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String var1) {
        this.mMessage = var1;
    }

    public String getPositiveAction() {
        return this.mPositiveAction;
    }

    public void setPositiveAction(String var1) {
        this.mPositiveAction = var1;
    }

    public String getNegativeAction() {
        return this.mNegativeAction;
    }

    public void setNegativeAction(String var1) {
        this.mNegativeAction = var1;
    }

    public boolean update(TSBackgroundPermissionRationale var1) {
        this.resetDirty();
        if (var1.getTitle() != null && !var1.getTitle().equals(this.mTitle)) {
            this.mTitle = var1.getTitle();
            this.addDirty(Application.B("쳝憵\u1ac5䏫恧"));
        }

        if (var1.getMessage() != null && !var1.getMessage().equals(this.mMessage)) {
            this.mMessage = var1.getMessage();
            this.addDirty(Application.B("쳄憹\u1ac2䏴恣发皶"));
        }

        if (var1.getPositiveAction() != null && !var1.getPositiveAction().equals(this.mPositiveAction)) {
            this.mPositiveAction = var1.getPositiveAction();
            this.addDirty(Application.B("쳙憳\u1ac2䏮恶叟皥㼰庻⻗ៗ⏄윈\ue07c"));
        }

        if (var1.getNegativeAction() != null && !var1.getNegativeAction().equals(this.mNegativeAction)) {
            this.mNegativeAction = var1.getNegativeAction();
            this.addDirty(Application.B("쳇憹\u1ad6䏦恶叟皥㼰庻⻗ៗ⏄윈\ue07c"));
        }

        return this.getDirtyFields().isEmpty() ^ true;
    }

    public Map<String, Object> toMap() {
        HashMap var1;
        HashMap var10000 = var1 = new HashMap;
        var1.<init>();
        String var10008 = this.mTitle;
        var1.put(Application.B("栐鲀၊腾抶"), var10008);
        String var10006 = this.mMessage;
        var1.put(Application.B("栉鲌၍腡抲律ޤ"), var10006);
        String var10004 = this.mPositiveAction;
        var1.put(Application.B("栔鲆၍腻抧待\u07b7솣☝烷\ue1a3ﰬ淈먡"), var10004);
        String var10002 = this.mNegativeAction;
        var10000.put(Application.B("栊鲌ၙ腳抧待\u07b7솣☝烷\ue1a3ﰬ淈먡"), var10002);
        return var10000;
    }

    public JSONObject toJson(boolean var1) {
        JSONObject var7;
        JSONObject var10000 = var7 = new JSONObject;
        TSBackgroundPermissionRationale var10001 = this;
        JSONObject var10002 = var7;
        TSBackgroundPermissionRationale var10003 = this;
        JSONObject var10004 = var7;
        TSBackgroundPermissionRationale var10005 = this;
        JSONObject var10006 = var7;
        TSBackgroundPermissionRationale var10007 = this;
        var7.<init>();
        String var6 = Application.B("\ue9d0囋넨鏉בּ");

        JSONException var9;
        label45: {
            boolean var10;
            try {
                var10006.put(var6, var10007.mTitle);
            } catch (JSONException var5) {
                var9 = var5;
                var10 = false;
                break label45;
            }

            var6 = Application.B("\ue9c9囇넯鏖וּ谡䙩");

            try {
                var10004.put(var6, var10005.mMessage);
            } catch (JSONException var4) {
                var9 = var4;
                var10 = false;
                break label45;
            }

            var6 = Application.B("\ue9d4囍넯鏌ﬠ谯䙺\udc40삘锆왫矦ڏꉈ");

            try {
                var10002.put(var6, var10003.mPositiveAction);
            } catch (JSONException var3) {
                var9 = var3;
                var10 = false;
                break label45;
            }

            var6 = Application.B("\ue9ca囇넻鏄ﬠ谯䙺\udc40삘锆왫矦ڏꉈ");

            try {
                var10000.put(var6, var10001.mNegativeAction);
                return var7;
            } catch (JSONException var2) {
                var9 = var2;
                var10 = false;
            }
        }

        JSONException var8 = var9;
        TSLog.logger.error(TSLog.error(var8.getMessage()), var8);
        var8.printStackTrace();
        return var7;
    }

    public boolean equals(TSBackgroundPermissionRationale var1) {
        String var2;
        String var3;
        return (var2 = this.mTitle) != null && var2.equals(var1.getTitle()) && (var2 = this.mMessage) != null && var2.equals(var1.getMessage()) && (var2 = this.mPositiveAction) != null && var2.equals(var1.getPositiveAction()) && (var3 = this.mNegativeAction) != null && var3.equals(var1.getNegativeAction());
    }

    public interface CompletionHandler {
        void onClickOk();

        void onClickCancel();
    }

    public static class Builder {
        private String mTitle;
        private String mMessage;
        private String mPositiveAction;
        private String mNegativeAction;

        public Builder() {
        }

        public TSBackgroundPermissionRationale.Builder setTitle(String var1) {
            this.mTitle = var1;
            return this;
        }

        public TSBackgroundPermissionRationale.Builder setMessage(String var1) {
            this.mMessage = var1;
            return this;
        }

        public TSBackgroundPermissionRationale.Builder setPositiveAction(String var1) {
            this.mPositiveAction = var1;
            return this;
        }

        public TSBackgroundPermissionRationale.Builder setNegativeAction(String var1) {
            this.mNegativeAction = var1;
            return this;
        }

        public TSBackgroundPermissionRationale build() {
            TSBackgroundPermissionRationale var1;
            TSBackgroundPermissionRationale var10000 = var1 = new TSBackgroundPermissionRationale;
            var1.<init>();
            var1.mTitle = this.mTitle;
            var1.mMessage = this.mMessage;
            var1.mPositiveAction = this.mPositiveAction;
            var10000.mNegativeAction = this.mNegativeAction;
            return var10000;
        }
    }
}
