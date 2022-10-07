//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.logger;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import com.transistorsoft.locationmanager.adapter.TSConfig;
import com.transistorsoft.locationmanager.adapter.TSConfig.OnChangeCallback;
import com.transistorsoft.tslocationmanager.Application;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TSMediaPlayer {
    public static final String BEEP_ON = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090\ua630\ued25");
    public static final String BEEP_OFF = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090\ua630\ued2dᮬ");
    public static final String BEEP_TRIP_DRY = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090ꘫ\ued39ᮣ㺆짣墆浰欜");
    public static final String BEEP_TRIP_UP_DRY = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090ꘫ\ued39ᮣ㺆짣増浲欺\u2cf5㸐粢");
    public static final String BEEP_TRIP_UP_ECHO = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090ꘫ\ued39ᮣ㺆짣増浲欺\u2cf4㸁粳᤻");
    public static final String CHIME_BELL_CONFIRM = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␒퀑\udf96\uf0aaꘀ\ued29ᮯ㺚짐墽浡權⳿㸄粲ᤦ딽");
    public static final String CHIME_SHORT_CHORD_UP = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␒퀑\udf96\uf0aaꘀ\ued38ᮢ㺙짎墖浝欆⳹㸍粩ᤰ딏禽쵈");
    public static final String BELL_DING_POP = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀔\udf97\uf090\ua63b\ued22ᮤ㺑짣墒浭欕");
    public static final String PIPE_OPEN = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␏퀋\udf92\uf0acꘀ\ued3bᮣ㺆짙墽浡欍⳾㸐粿ᤋ딾禡쵛氂");
    public static final String PIPE_CLOSE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␏퀋\udf92\uf0acꘀ\ued3bᮣ㺆짙墽浡欄⳿㸁精ᤸ");
    public static final String PIPE_ALERT = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␏퀋\udf92\uf0acꘀ\ued3bᮣ㺆짙墽浡欍⳾㸐粿");
    public static final String PIPE_CONFIRM = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␏퀋\udf92\uf0acꘀ\ued3bᮣ㺆짙墽浡權⳿㸄粲ᤦ딽");
    public static final String DOT_RETRY = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␕퀌\udfa4\uf0bd\ua63a\ued3f᮸㺏");
    public static final String DOT_START = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␕퀌\udfa4\uf0bcꘫ\ued2a᮸㺂짝墁浶欌⳾㸌糪");
    public static final String DOT_STOP = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␕퀌\udfa4\uf0bcꘫ\ued24ᮺ㺗짟墖浫權⳿㹐");
    public static final String DOT_SUCCESS = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␕퀌\udfa4\uf0bcꘪ\ued28ᮩ㺓짏墑");
    public static final String POP = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柣␕퀈\udfa4\uf0a1\ua630\ued3fᮣ㺐징墁浣欑\u2cf8㸍粵ᥠ");
    public static final String POP_OPEN = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柣␕퀈\udfa4\uf0a1\ua630\ued3fᮣ㺐징墁浣欑\u2cf8㸍粵ᥥ");
    public static final String POP_CLOSE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柣␕퀈\udfa4\uf0a1\ua630\ued3fᮣ㺐징墁浣欑\u2cf8㸍粵ᥦ");
    public static final String BUTTON_CLICK = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␖퀑\udf98\uf0a4ꘀ\ued3f᮫㺆짣墆浭欋\u2cf4㸽粸\u193c딵禫쵓氅\uf7a8蝎弶");
    public static final String CLICK_TAP_DONE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␖퀑\udf98\uf0a4ꘀ\ued3f᮫㺆짣墆浭欋\u2cf4㸽粸\u193c딵禫쵓氅\uf7a8蝎弲渥\u0e5eᛢ蘆沦휙砥쪫㢨");
    public static final String MUSIC_TIMPANI_ERROR = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␏퀋\udf92\uf0acꘀ\ued3fᮣ㺛짌境浬欌Ⳏ㸇粩ᤦ딿禺쵧汗\uf7f6");
    public static final String DIGI_WARN = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␓퀟\udf92\uf090꘨\ued2a᮸㺘");
    public static final String WHOO_SEND_SHARE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柤␒퀗\udf94\uf090\ua62c\ued2eᮤ㺒짣墑浪欄ⳣ㸇糪");
    public static final String OOOOIII = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柼␕퀗\udf94\uf0a6\ua636\ued22\u1bf9㺩짚増浮欉Ⳏ㸔粴ᤸ");
    public static final String MARIMBA_DROP = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␛퀊\udf92\uf0a2\ua63d\ued2aᮕ㺒짎墍浲");
    public static final String CLOCK_TICK = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␖퀑\udf98\uf0a4ꘀ\ued28ᮦ㺙짟墉浝欑\u2cf8㸁粰");
    public static final String CLOCK_TOCK = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␖퀑\udf98\uf0a4ꘀ\ued28ᮦ㺙짟墉浝欑⳾㸁粰");
    public static final String PEEP_NOTE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柣␟퀝\udf8b\uf090\ua631\ued24ᮾ㺓즍");
    public static final String TINY_RETRY_FAILURE3 = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柧␓퀖\udf82\uf090\ua62d\ued2eᮾ㺄짅墽浤欄\u2cf8㸎粮ᤦ딵移");
    public static final String TINY_RETRY_FAILURE1 = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柧␓퀖\udf82\uf090\ua62d\ued2eᮾ㺄짅墽浤欄\u2cf8㸎粮ᤦ딵秹");
    public static final String ZAP_FAST = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柩␛퀈\udfa4\uf0a9\ua63e\ued38ᮾ");
    public static final String LOCATION_RECORDED = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柼␕퀗\udf94\uf0a6\ua636\ued22\u1bf9㺩짚増浮欉Ⳏ㸔粴ᤸ");
    public static final String LOCATION_SAMPLE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␖퀑\udf98\uf0a4ꘀ\ued3f᮫㺆짣墆浭欋\u2cf4㸽粸\u193c딵禫쵓氅\uf7a8蝎弲渥\u0e5eᛢ蘆沦휙砥쪫㢨");
    public static final String LOCATION_ERROR = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␓퀟\udf92\uf090꘨\ued2a᮸㺘");
    public static final String MOTIONCHANGE_FALSE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␛퀊\udf92\uf0a2\ua63d\ued2aᮕ㺒짎墍浲");
    public static final String MOTIONCHANGE_TRUE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␒퀑\udf96\uf0aaꘀ\ued38ᮢ㺙짎墖浝欆⳹㸍粩ᤰ딏禽쵈");
    public static final String STATIONARY_GEOFENCE_EXIT = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柩␛퀈\udfa4\uf0a9\ua63e\ued38ᮾ");
    public static final String STOP_TIMER_ON = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柰␒퀑\udf96\uf0aaꘀ\ued29ᮯ㺚짐墽浡權⳿㸄粲ᤦ딽");
    public static final String STOP_TIMER_OFF = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀔\udf97\uf090\ua63b\ued22ᮤ㺑짣墒浭欕");
    public static final String HEARTBEAT = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柣␟퀝\udf8b\uf090\ua631\ued24ᮾ㺓즍");
    public static final String GEOFENCE_ENTER = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090ꘫ\ued39ᮣ㺆짣増浲欺\u2cf5㸐粢");
    public static final String GEOFENCE_DWELL = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090ꘫ\ued39ᮣ㺆짣増浲欺\u2cf4㸁粳᤻");
    public static final String GEOFENCE_EXIT = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柱␟퀝\udf8b\uf090ꘫ\ued39ᮣ㺆짣墆浰欜");
    public static final String WARNING = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柷␓퀟\udf92\uf090꘨\ued2a᮸㺘");
    public static final String ERROR = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柾␏퀋\udf92\uf0acꘀ\ued3fᮣ㺛짌境浬欌Ⳏ㸇粩ᤦ딿禺쵧汗\uf7f6");
    public static final String OPEN = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柧␓퀖\udf82\uf090\ua62d\ued2eᮾ㺄짅墽浤欄\u2cf8㸎粮ᤦ딵移");
    public static final String CLOSE = Application.B("ⶵ뜃纃ᕩ흈㡉\ue5f0\uf0ee㽲큹䐄뮙섅\ue19c㱝\uf052骙定柧␓퀖\udf82\uf090\ua62d\ued2eᮾ㺄짅墽浤欄\u2cf8㸎粮ᤦ딵秹");
    private static TSMediaPlayer sInstance;
    private MediaPlayer mMediaPlayer;
    private AtomicBoolean mDebug;
    private AtomicBoolean mIsPlaying;
    private final List<Integer> mQueue;

    public TSMediaPlayer() {
        TSMediaPlayer var10000 = this;
        TSMediaPlayer var10001 = this;
        TSMediaPlayer var10002 = this;
        super();
        AtomicBoolean var1;
        var1 = new AtomicBoolean.<init>(false);
        var10002.mDebug = var1;
        var1 = new AtomicBoolean.<init>(false);
        var10001.mIsPlaying = var1;
        ArrayList var2;
        var2 = new ArrayList.<init>();
        var10000.mQueue = var2;
    }

    public static TSMediaPlayer getInstance() {
        if (sInstance == null) {
            sInstance = getInstanceSynchronized();
        }

        return sInstance;
    }

    private static synchronized TSMediaPlayer getInstanceSynchronized() {
        if (sInstance == null) {
            sInstance = new TSMediaPlayer();
        }

        return sInstance;
    }

    private int soundId(Context var1, String var2) {
        Resources var10000 = var1.getResources();
        String var3 = var2.toLowerCase();
        String var4 = var1.getPackageName();
        return var10000.getIdentifier(var3, Application.B("큣씜҃"), var4);
    }

    public void init(Context var1) {
        TSConfig var10000 = TSConfig.getInstance(var1);
        this.mDebug.set(var10000.getDebug());
        OnChangeCallback var2;
        var2 = new OnChangeCallback() {
            public void a(TSConfig var1) {
                TSMediaPlayer.this.mDebug.set(var1.getDebug());
            }
        }.<init>();
        var10000.onChange(Application.B("柀⠀쐳\ue22f鲑"), var2);
    }

    public void play(Context param1, String param2) {
        // $FF: Couldn't be decompiled
    }

    public void run(final Context var1) {
        (this.mMediaPlayer = MediaPlayer.create(var1, (Integer)this.mQueue.remove(0))).setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer var1x) {
                <undefinedtype> var10000 = this;
                TSMediaPlayer.this.mMediaPlayer.release();
                TSMediaPlayer.this.mMediaPlayer = null;
                boolean var3;
                synchronized(TSMediaPlayer.this.mQueue) {
                    var3 = TSMediaPlayer.this.mQueue.isEmpty();
                }

                if (!var3) {
                    TSMediaPlayer.this.run(var1);
                } else {
                    TSMediaPlayer.this.mIsPlaying.set(false);
                }

            }
        });
        this.mMediaPlayer.start();
    }

    public void debug(Context var1, String var2) {
        if (this.mDebug.get()) {
            this.play(var1, var2);
        }
    }

    public void tickTock(Context var1, boolean var2) {
        boolean var10000 = var2;
        List var9;
        List var10001 = var9 = this.mQueue;
        TSMediaPlayer var10002 = this;
        synchronized(var9){}

        Throwable var10;
        boolean var11;
        try {
            var10002.mQueue.add(this.soundId(var1, Application.B("햮亊\u1bf9뒕ⱒꦤꈭ⅝ⷩ㷨☣ᑃଠ䒟빫\ue393Ϥⶴ彬\ud99a\ufaef␣赟\u4db8\u0d50Ďኞﺫ鳅戎뷸옒ﻹ俴")));
        } catch (Throwable var8) {
            var10 = var8;
            var11 = false;
            throw var10;
        }

        try {
            ;
        } catch (Throwable var7) {
            var10 = var7;
            var11 = false;
            throw var10;
        }

        if (!var10000 || this.mDebug.get()) {
            this.play(var1, Application.B("햮亊\u1bf9뒕ⱒꦤꈭ⅝ⷩ㷨☣ᑃଠ䒟빫\ue393Ϥⶴ彬\ud99a\ufaef␣赟\u4db8\u0d50Ďኞﺫ鳅戎뷸옔ﻹ俴"));
        }
    }
}
