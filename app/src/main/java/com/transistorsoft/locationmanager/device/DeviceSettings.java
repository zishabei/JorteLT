//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.device;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Build.VERSION;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import com.transistorsoft.locationmanager.event.PowerSaveModeChangeEvent;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;
import java.util.Calendar;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;

public class DeviceSettings {
    private static final String TAG = Application.B("婥멄ꬥ䍈и⋡쪈릊汝倗ꬻር鶌\ude6b");
    private static Intent[] POWERMANAGER_INTENTS = new Intent[]{(new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏ж⋭쪮릆氇倐ꬷሠ鶞\ude6a\ue245慥埒≩摛是\ue678梈姅"), Application.B("婂멎ꬾ䌏ж⋭쪮릆氇倓ꬷሱ鶆\ude7b\ue249慿域≯摌景\ue66d梘姃ㆄ邞롣ꡁ봝獨馺\u2dc7Ỉ㿅㑘◿젟恑흁\ud923鋙⽸ꘒ⨮\ufddb\ud806壎\ueaccꌊ\ue936ᵣ⼉\ue705刜忘㵦쥶ƴ"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏з⋡쪯릙氇倂ꬼሧ鶙\ude77\ue245慵垅≦摛昵\ue67a梞姖ㆍ邈"), Application.B("婂멎ꬾ䌏з⋡쪯릙氇倂ꬼሧ鶙\ude77\ue245慵垅≦摛昵\ue67a梞姖ㆍ邈령ꡡ봚獨駻ⷤỒ㿞㑃◡젊恞흒\ud930鋱⽘ꘟ⨻\ufdd5\ud815壊\ueaddꌝ"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏г⋱쪺릘汌倊ꭼሰ鶒\ude6b\ue258慴埆≧摟是\ue66d梊姒㆙"), Application.B("婂멎ꬾ䌏г⋱쪺릘汌倊ꭼሰ鶒\ude6b\ue258慴埆≧摟是\ue66d梊姒㆙郃롤ꡔ봎獮駠ⷳọ㿜㑐◞졅恅흚\ud979鋇⽭ꘝ⨽\ufdc8\ud816壓\ueae7ꌋ\ue930ᵏ⼋\ue71d刴忞㵿쥎Ƥ쇫耥遫\uf72e㥢瑠穎蟽햤⢭"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏г⋱쪺릘汌倊ꭼሰ鶒\ude6b\ue258慴埆≧摟是\ue66d梊姒㆙"), Application.B("婂멎ꬾ䌏г⋱쪺릘汌倊ꭼሰ鶒\ude6b\ue258慴埆≧摟是\ue66d梊姒㆙郃롸ꡐ봛獵駹ⷯệ㿔㐙◜젙恟흐\ud932鋧⽪Ꙓ⨟\ufdce\ud80c壗\ueaccꌇ\ue936ᵣ⼉\ue705刜忘㵦쥶ƴ"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏г⋱쪺릘汌倊ꭼሰ鶒\ude6b\ue258慴埆≧摟是\ue66d梊姒㆙"), Application.B("婂멎ꬾ䌏г⋱쪺릘汌倊ꭼሰ鶒\ude6b\ue258慴埆≧摟是\ue66d梊姒㆙郃롶ꡐ봟獿駻ⷨỉ㿃㑘◀졅恑흐\ud923鋽⽯ꘕ⨻ﷅ\ud84d声\ueaddꌅ\ue930ᵖ⼟\ue701刴忞㵿쥁Ƣ쇶耥遘\uf722㥺瑈穛蟠햹⢢㳧楕ͮ"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏и⋫쪷릀汛倌ꬡቭ鶘\ude79\ue24a慴埈≯摐昵\ue669梟"), Application.B("婂멎ꬾ䌏и⋫쪷릀汛倌ꬡቭ鶘\ude79\ue24a慴埈≯摐昵\ue669梟妙㆛邈롥ꡍ봆獯駧ⷯỒ㿟㐙◟젟恑흁\ud923鋡⽩Ꙓ⨜\ufdc8\ud802壑\ueaddꌑ\ue932ᵣ⼚\ue701刹忇㵼쥶ƌ쇻耥遃\uf73b㥿瑽穁"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏и⋫쪷릀汛倌ꬡቭ鶘\ude79\ue24a慴埈≯摐昵\ue669梟"), Application.B("婂멎ꬾ䌏и⋫쪷릀汛倌ꬡቭ鶘\ude79\ue24a慴埈≯摐昵\ue669梟妙㆘邙롶ꡒ봛獩駤ⷧọ㿁㐙◿젟恑흁\ud923鋡⽩\ua63d⨿\ufdcc\ud82f壊\ueadaꌐ\ue903ᵁ⼞\ue718刃忇㵻쥻"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏д⋴쪫릀氇倐ꬳሥ鶎"), Application.B("婂멎ꬾ䌏д⋴쪫릀氇倐ꬳሥ鶎\ude36\ue25c慴埙≧摗昲\ue67f梄姘ㆅ郃롤ꡔ봎獮駠ⷳọ㾟㑤◘젊恂흇\ud922鋤⽘ꘌ⨿ﷰ\ud80a壐\ueaddꌥ\ue921ᵖ⼃\ue707刜忚㵶"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏в⋵쪴릀氇倐ꬷሠ鶞\ude6a\ue249"), Application.B("婂멎ꬾ䌏в⋵쪴릀氇倐ꬷሠ鶞\ude6a\ue249愿埞≣搐昱\ue664梂姙ㆎ邂롧ꡔ봆獱駽ⷼỘ㾟㑶◈젏恧흛\ud93e鋠⽼\ua630⨦\ufdcf\ud817壢\ueacaꌐ\ue92bᵔ⼃\ue705刌"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏в⋵쪴릀氇倐ꬷሠ鶞\ude6a\ue249"), Application.B("婂멎ꬾ䌏в⋵쪴릀氇倐ꬷሠ鶞\ude6a\ue249愿埞≣搐昱\ue664梂姙ㆎ邂롧ꡔ봆獱駽ⷼỘ㾟㑵○져恄흒\ud925鋠⽌ꘌ⨂\ufddd\ud80d壂\ueaceꌁ\ue930"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏Э⋭쪭릀氇倓ꬷሱ鶆\ude71\ue25f慢埂≥摐昬\ue66d梃姖ㆌ邈롥"), Application.B("婂멎ꬾ䌏Э⋭쪭릀氇倓ꬷሱ鶆\ude71\ue25f慢埂≥摐昬\ue66d梃姖ㆌ邈롥ꠎ봎獿駠ⷯị㿘㑃◕졅恲흔\ud904鋠⽸꘎⨻\ufde9\ud813壮\ueac8ꌊ\ue923ᵅ⼏\ue703刴忍㵻쥫ƻ쇱耥道"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏Ш⋥쪶릜汜倍ꬵቭ鶊\ude76\ue248慣埄≣摚景\ue660梂姘ㆇ"), Application.B("婂멎ꬾ䌏Ш⋥쪶릜汜倍ꬵቭ鶊\ude76\ue248慣埄≣摚景\ue67f梀妙ㆉ邌롣ꡔ봊獮駭ⶨỈ㿘㐙◮젊恄흇\ud932鋦⽠\ua63d⨬\ufdc8\ud80a壕\ueac0ꌐ\ue93b"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏Ш⋥쪶릜汜倍ꬵቭ鶊\ude76\ue248慣埄≣摚景\ue660梂姘ㆇ"), Application.B("婂멎ꬾ䌏Ш⋥쪶릜汜倍ꬵቭ鶊\ude76\ue248慣埄≣摚景\ue67f梀妙㆞還령ꡂ봎獨駠ⷣỏ㿈㐙◮젊恄흇\ud932鋦⽠\ua63d⨬\ufdc8\ud80a壕\ueac0ꌐ\ue93b"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏г⋰쪸링汙倊ꬦሱ鶄\ude79\ue248"), Application.B("婂멎ꬾ䌏г⋰쪸링汙倊ꬦሱ鶄\ude79\ue248愿埇≫摐春\ue665梃姐㆛邌롰ꡅ뵁獽駷ⷲỔ㿇㑞◘젒怞흿\ud936鋺⽽ꘕ⨡\ufddb\ud833壂\ueaceꌁ\ue903ᵁ⼞\ue718刃忇㵻쥻"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏к⋷쪮릜氇倎ꬽሡ鶂\ude74\ue249慼埊≤摟昦\ue669梟"), Application.B("婂멎ꬾ䌏к⋷쪮릜氇倎ꬽሡ鶂\ude74\ue249慼埊≤摟昦\ue669梟妙ㆦ邌롾ꡎ봮獿駠ⷯị㿘㑃◕"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏к⋷쪮릜氇倎ꬽሡ鶂\ude74\ue249慼埊≤摟昦\ue669梟"), Application.B("婂멎ꬾ䌏к⋷쪮릜氇倎ꬽሡ鶂\ude74\ue249慼埊≤摟昦\ue669梟妙ㆎ邃롣ꡒ봖猲駒ⷳồ㿒㑃◅전恞흲\ud934鋠⽰ꘊ⨦\ufdc8\ud81a"))).setData(Uri.parse(Application.B("婌멎ꬱ䍈з⋡쪶릎汇倂ꬵሦ鶙\ude22\ue203愾埍≿摐昢\ue678梄姘ㆅ郂롲ꡎ봛獮駭ⶩỼ㿄㑃◃져恄흒\ud925鋠"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏Я⋶쪺릁汚倐ꬻሬ鶅\ude36\ue25c慹埄≤摛昬\ue66d梃姖ㆌ邈롥"), Application.B("婂멎ꬾ䌏в⋰쪾릃氇倂\uab27ሷ鶄\ude7a\ue243慾域≧摟是\ue66d梊姒㆙郃롶ꡃ봛獵駢ⷯỉ㿈㐙◭젞恄흜\ud915鋻⽶ꘈ⨂\ufddb\ud811壢\ueacaꌐ\ue92bᵔ⼃\ue705刌"))), (new Intent()).setComponent(new ComponentName(Application.B("婂멎ꬾ䌏п⋡쪬릎江偍ꬶሴ鶊\ude68\ue25c慼埊≤摟昦\ue669梟"), Application.B("婂멎ꬾ䌏п⋡쪬릎江偍ꬶሴ鶊\ude68\ue25c慼埊≤摟昦\ue669梟妙ㆆ邈롺ꡏ봝獥馺ⷕỐ㿐㑅◘젨恜흖\ud936鋦⽬ꘌ⨘\ufdd4\ud80a壗\ueaccꌨ\ue92bᵑ⼞")))};
    public static final String IGNORE_BATTERY_OPTIMIZATION = Application.B("婨멦\uab1d䍮Љ⋁쪄릭汨倷ꬆሆ鶹\ude41\ue273慞埻≞摷昌\ue645梷姶\u31bf邤롘ꡮ봼");
    public static final String POWER_MANAGER = Application.B("婱멮ꬄ䍤Љ⋛쪖릮汧倢ꬕሆ鶹");
    private static final String HUAWEI_POWER_MODE_CHANGED_ACTION = Application.B("婉메ꬲ䍖о⋭쫵릆汇倗ꬷር鶟\ude36\ue24d慲域≣摑是\ue622梽姸\u31bc邨롅\ua87f봢獓駐ⷃỢ㿲㑿◭젥恷흶\ud913鋋⽘\ua63f⨛ﷵ\ud82c壭");
    private static final int HUAWEI_POWER_MODE_STATE_OFF = 2;
    private static final int HUAWEI_POWER_MODE_STATE_ON = 1;
    private static final String HUAWEI_SMART_MODE_STATUS = Application.B("婲멌ꬲ䍓Я⋉쪴릋汌倰ꬦሢ鶟\ude6d\ue25f");
    private static final int HUAWEI_SMART_MODE_STATUS_ON = 4;
    private static DeviceSettings mInstance = null;
    private BroadcastReceiver mPowerSaveChangeReceiver;

    public static DeviceSettings getInstance() {
        if (mInstance == null) {
            mInstance = getInstanceSynchronized();
        }

        return mInstance;
    }

    private static synchronized DeviceSettings getInstanceSynchronized() {
        if (mInstance == null) {
            mInstance = new DeviceSettings();
        }

        return mInstance;
    }

    private DeviceSettings() {
    }

    @TargetApi(21)
    private Boolean isPowerSaveModeAndroid(Context var1) {
        boolean var2 = false;
        PowerManager var3;
        if (VERSION.SDK_INT >= 21 && (var3 = (PowerManager)var1.getSystemService(Application.B("롃ᱱ\ue03b᭦要"))) != null) {
            var2 = var3.isPowerSaveMode();
        }

        return var2;
    }

    private Boolean isPowerSaveModeHuawei(Context var1) {
        Context var10000 = var1;
        TSLog.logger.info(TSLog.info(Application.B("璑忶㓭㧓㭆煈\ue91c调붶ᔭጛఆ⩬\u0c51쀠ᘞ뛐᮷") + DeviceInfo.MANUFACTURER_HUAWEI + Application.B("瓪忻㓻㧷㭌煜\ue90d谔북")));

        label28: {
            boolean var10001;
            int var5;
            try {
                var5 = System.getInt(var10000.getContentResolver(), Application.B("璙忲㓿㧱㭝煲\ue916谕부ᔟጙం⩕ో쀷"));
            } catch (SettingNotFoundException var4) {
                var10001 = false;
                break label28;
            }

            boolean var2;
            if (var5 == 4) {
                var2 = true;
            } else {
                var2 = false;
            }

            return var2;
        }

        TSLog.logger.warn(TSLog.warn(DeviceInfo.MANUFACTURER_HUAWEI + Application.B("瓪忌㓧㧰㭝煚\ue914豑붖ᔩጙగ⩈\u0c50쀣ᙛ뚪") + Application.B("璙忲㓿㧱㭝煲\ue916谕부ᔟጙం⩕ో쀷") + Application.B("瓭徿㓰㧬㭝焟\ue91f谞붐ᔢጉ")));
        return this.isPowerSaveModeAndroid(var1);
    }

    private Intent getIntent(Context var1, String var2) {
        Intent var3 = null;
        if (var2.equalsIgnoreCase(Application.B("旬罂낋郧ꀦ㍋ܘ天褰뤺꜖巫쥤⪾騊⧾༵始\u0c51缄淪拺௵舴兴箚ﱳ쉑"))) {
            var3 = this.getBatteryOptimizationsIntent();
        } else if (var2.equalsIgnoreCase(Application.B("旵罊낒郭ꀦ㍑܊太褿뤯꜅巫쥤"))) {
            var3 = this.getPowerManagerIntent(var1);
        }

        if (var3 != null && var1.getPackageManager().resolveActivity(var3, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            var3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return var3;
        } else {
            return null;
        }
    }

    @TargetApi(23)
    private Intent getBatteryOptimizationsIntent() {
        if (VERSION.SDK_INT < 23) {
            TSLog.logger.debug(TSLog.info(Application.B("琙ᕭ᧡⦵ະꟿ첬ᘮ촔摹㢩") + VERSION.SDK_INT + Application.B("瑽ᕬ᧸⦹\u0ea0\ua7ba쳢ᘒ촤搒㣺\udb1d\uda06껊\uda68暿\ue6c4\u2bd6\udb65ጂ獬鮲⪆藒敿∓ڵ伎﹀㴜得꣸൵\ue87d╠퉡먡㊳썂뻛盼")));
            return null;
        } else {
            Intent var10000 = new Intent();
            var10000.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            var10000.setAction(Application.B("琼ᕦ᧳⦮ຼ\ua7f3쳨ᙓ촣摗㣽\udb1c\uda1f껔\uda60暾\ue69e⮿\udb6bጋ獭鮯⪑藈敢∐ڠ伎﹑㴋徜\ua8fe\u0d65\ue87e╱퉼머㊳썖뻝盻ꮆᒪ꙳ꎇ㮴箤聅촁犐ﾩ屬惆"));
            return var10000;
        }
    }

    private Intent getPowerManagerIntent(Context var1) {
        PackageManager var6 = var1.getPackageManager();
        var1 = null;
        Intent[] var2;
        int var3 = (var2 = POWERMANAGER_INTENTS).length;
        int var4 = 0;

        Intent var5;
        while(true) {
            if (var4 >= var3) {
                var5 = new Intent();
                break;
            }

            if (var6.resolveActivity(var5 = var2[var4], PackageManager.MATCH_DEFAULT_ONLY) != null) {
                break;
            }

            ++var4;
        }

        return var5;
    }

    private void markSeen(Context var1, String var2) {
        Editor var10000 = var1.getSharedPreferences(Application.B("\uf558碖ㇾᨇ窬貵枓俖췊慍궈\ua8da뱜幰끼⑷䔟읗惧宕ቮ⪵툀\ue38d㔈땖葠㈘鰢圮魴"), 0).edit();
        var10000.putLong(var2, Calendar.getInstance().getTime().getTime());
        var10000.apply();
    }

    private long lastSeenAt(Context var1, String var2) {
        return var1.getSharedPreferences(Application.B("\ued93\ue98b\uf015柬⹄訓\u0b5e㽦殏깗嫵糶醺㈹ﴁ\ue705\ue024\ue13a\uebe5ᷪ蟺걒駆爭\ueda4\uefb3눉퀔離뺱㭣"), 0).getLong(var2, 0L);
    }

    public void setPowermanagerIntents(Intent[] var1) {
        POWERMANAGER_INTENTS = var1;
    }

    @TargetApi(23)
    public Boolean isIgnoringBatteryOptimization(Context var1) {
        return VERSION.SDK_INT < 23 ? true : ((PowerManager)var1.getSystemService(Application.B("氀嶾鬦\ue02d횼"))).isIgnoringBatteryOptimizations(var1.getPackageName());
    }

    public Boolean isPowerSaveMode(Context var1) {
        return Build.MANUFACTURER.equalsIgnoreCase(DeviceInfo.MANUFACTURER_HUAWEI) ? this.isPowerSaveModeHuawei(var1) : this.isPowerSaveModeAndroid(var1);
    }

    public void startMonitoringPowerSaveChanges(Context var1) {
        if (VERSION.SDK_INT >= 21) {
            TSLog.logger.debug(TSLog.on(Application.B("ﾬ땜穽⠓쮛䲩䜻醩⩪\u0c49㙂뢒\uf67d\udd89跲캦У㟓ꎾ䑐븎ꔣ煻ઓ欋婪츟줵袺膤⤕桜ᝢﮓ")));
            if (this.mPowerSaveChangeReceiver != null) {
                return;
            }

            this.mPowerSaveChangeReceiver = new DeviceSettings.a();
            IntentFilter var2;
            IntentFilter var10000 = var2 = new IntentFilter();
            var10000.addAction(Application.B("ﾞ땆穸⠓쮀䳠䜲釨⩫\u0c53㘘뢜\uf66c\udd94践캮ѭ㞍ꎁ䑨븼ꔔ煚ભ欮婎칩줓袍膈⤴桿ᝂ﮿✳ᯭ傑ҏ쒻癚ꫀ"));
            var10000.addAction(Application.B("ﾗ땝穽⠖쮊䳠䝸醯⩪\u0c54㙓뢓\uf67b\uddce跽캢ѷ㟊ꎾ䑉빅ꔁ煇થ欸婝칠줛袝膁⤾桤ᝄﮨ✱ᯫ傗҄쒸癀\uaac5䇸\ud9be迚勺蚖"));
            if (VERSION.SDK_INT >= 23) {
                var2.addAction(Application.B("ﾞ땆穸⠓쮀䳠䜲釨⩷\u0c45㙂뢉\uf666\udd8e跻캲Э㟪ꎖ䑩븤ꔃ煍ભ欿婎칫줂袗膗⤢桤ᝈﮰ✤ᯬ傝҈쒦癞\uaad0䇲\ud9a5违逸蚋崳\uf0b1閈Ⓢ큂舖\uf13c"));
            }

            var1.registerReceiver(this.mPowerSaveChangeReceiver, var2);
        }

    }

    public void stopMonitoringPowerSaveChanges(Context var1) {
        if (this.mPowerSaveChangeReceiver != null) {
            Context var10000 = var1;
            DeviceSettings var10001 = this;
            TSLog.logger.debug(TSLog.off(Application.B("\udf15쎡얡戝\ueb80㱊楻暕숥鞂눳ᰇ㘣℮펟啊틵～ﺥ跋햬㪥ਵ≳涗㻾乁缤燮蕢ୁ欌㖚")));

            try {
                var10000.unregisterReceiver(var10001.mPowerSaveChangeReceiver);
            } catch (IllegalArgumentException var2) {
                TSLog.logger.error(TSLog.error(var2.getMessage()), var2);
            }

            this.mPowerSaveChangeReceiver = null;
        }

    }

    public DeviceSettingsRequest request(Context var1, String var2) {
        if (this.getIntent(var1, var2) == null) {
            TSLog.logger.debug(TSLog.info(Application.B("쁓犽꛲\uf897ቹꔋ灑\uf683膷潂ﷴ㪨酐\udd9f\uaa3a") + var2 + Application.B("쀵犯\ua6f8\uf889ቹꔊ瀟\uf6d7膾漍\ufde0㫡酚\udd9eꩬ᰾뤊娢ꠝ") + Build.MANUFACTURER + Application.B("쀵") + Build.MODEL + Application.B("쁕") + VERSION.RELEASE));
            return null;
        } else {
            long var3 = this.lastSeenAt(var1, var2);
            return new DeviceSettingsRequest(var2, var3);
        }
    }

    public boolean show(Context var1, String var2) {
        Intent var3;
        if ((var3 = this.getIntent(var1, var2)) == null) {
            TSLog.logger.debug(TSLog.info(Application.B("魇읖䎾끲䘴鸩ᦌ২熽敏陎鴵礽읁\uf852") + var2 + Application.B("鬡을䎴끬䘴鸨ᧂ়熴攀陚鵼礷은\uf804\ude4b뮻㧬⦤") + Build.MANUFACTURER + Application.B("鬡") + Build.MODEL + Application.B("魁") + VERSION.RELEASE));
            return false;
        } else {
            Context var10000 = var1;
            Intent var10001 = var3;
            this.markSeen(var1, var2);

            try {
                var10000.startActivity(var10001);
                return true;
            } catch (SecurityException var4) {
                TSLog.logger.error(TSLog.error(var4.getMessage()), var4);
                return false;
            }
        }
    }

    @TargetApi(21)
    static class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context var1, Intent var2) {
            boolean var4 = false;
            if (var2.getAction().equals(Application.B("㿂嫃\uf6b7\udfac㢍园䘨뵞뗢ⶒ㞈\uf76d\ud98f즼᳧籬ॠ벫걷츱ꐻ粪⩿츰㉱\uf2e9ጺ\uef50쪇\ue358吕\u13fe틷謼䫌섂칳ᐣ껱⭬猘銏푚须ꫧ莲"))) {
                Bundle var6;
                Bundle var10000 = var6 = var2.getExtras();
                TSLog.logger.debug(TSLog.info(DeviceInfo.MANUFACTURER_HUAWEI + Application.B("㾊嫒\uf6b3\udfaf㢍囧䙲뵒뗨ⷜ㟍") + var2));
                if (var10000 != null && var6.containsKey(Application.B("㿙嫂\uf6b7\udfaf㢍"))) {
                    Iterator var5 = var6.keySet().iterator();

                    while(var5.hasNext()) {
                        String var3 = (String)var5.next();
                        TSLog.logger.debug(Application.B("㿱嫓\uf6ae\udfaf㢚囥䙵뵪떬") + var3 + Application.B("㾐媖") + var6.get(var3));
                    }

                    if (var2.getExtras().getInt(Application.B("㿙嫂\uf6b7\udfaf㢍")) == 1) {
                        var4 = true;
                    } else {
                        var4 = false;
                    }
                }
            } else {
                var4 = ((PowerManager)var1.getSystemService(Application.B("㿚嫙\uf6a1\udfbe㢚"))).isPowerSaveMode();
            }

            String var7;
            if (var4) {
                var7 = TSLog.on(Application.B("㿺嫙\uf6a1\udfbe㢚囗䙧뵁뗩ⶫ㞂\uf767\ud99e"));
            } else {
                var7 = TSLog.off(Application.B("㿺嫙\uf6a1\udfbe㢚囗䙧뵁뗩ⶫ㞂\uf767\ud99e"));
            }

            TSLog.logger.info(var7);
            EventBus.getDefault().post(new PowerSaveModeChangeEvent(var4));
        }
    }
}
