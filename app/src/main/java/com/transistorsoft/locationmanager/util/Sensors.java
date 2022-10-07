//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.transistorsoft.locationmanager.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;

public class Sensors {
    private static Sensors i;
    private final Sensor a;
    private final Sensor b;
    private final Sensor c;
    private final Sensor d;
    private final Sensor e;
    private final Sensor f;
    private final Sensor g;
    private final Sensor h;

    public static Sensors getInstance(Context var0) {
        if (i == null) {
            i = new Sensors(var0.getApplicationContext());
        }

        return i;
    }

    public Sensors(Context var1) {
        SensorManager var2;
        SensorManager var10001 = var2 = (SensorManager)var1.getSystemService(Application.B("⍩㯱\ue739\ue8d4撆맬"));
        this.c = var2.getDefaultSensor(4);
        this.b = var2.getDefaultSensor(2);
        this.a = var2.getDefaultSensor(1);
        this.d = var2.getDefaultSensor(17);
        this.e = var2.getDefaultSensor(9);
        this.f = var2.getDefaultSensor(11);
        this.g = var2.getDefaultSensor(19);
        this.h = var10001.getDefaultSensor(18);
    }

    public StringBuffer print() {
        StringBuffer var1;
        StringBuffer var10001 = var1 = new StringBuffer;
        var10001.<init>();
        var10001.append(TSLog.header(Application.B("\uf60e髝㛊\uea71替ㅫ돕툮휔ꇽ븨评夹앉")));
        if (!this.hasAccelerometer()) {
            var1.append(TSLog.boxRow(Application.B("탪撗㚼\uea18曽ㅭ뎶툸휝ꇶ븩评夦앟䣜\ue958廴䀿ƪ㼩伶密瀢㜕色\ua7e4୰˰⮍ヽ죄冯䡛㌠陵ḋ\uf39d獶鲎ꢓ\u2e7d㟆㽳\udb8c┋厽榻쁎瑾䊒\u2457쌟䫽祊謪빖㍥ɈⲦ퀳㣰踱綔쫈瑪儆⅕埦䑑ꋵ桯疴楩們⼓πꒉ촽쭓")));
        } else {
            var1.append(TSLog.boxRow(Application.B("텏骸㚼\uea79替ㅭ뎰툱휔ꇡ븴识央앎䣍\ue94f府䀥") + this.a));
        }

        if (!this.hasGyroscope()) {
            var1.append(TSLog.boxRow(Application.B("탪撗㚼\uea18曻ㅷ뎧툲휂ꇰ븴诛央씠䢨\ue93d廈䁪Ǥ㽬佶安灬㜽舳Ʞହ˒⮌イ죉冥䡁㍨丹Ḛ\uf380獼鲃\ua8c7\u2e67㟐㽮\udbd8┝厩槨쁊瑾䊍␑쌀䫪祕謭빗㍴ɀ⳧퀪㣺踸緘쪟瑡儏ℙ垢䑖ꋷ栽疱楨倓⼅")));
        } else {
            var1.append(TSLog.boxRow(Application.B("텏骸㚼\uea7f曥ㅼ뎺툮휒ꇼ븫诎契씺") + this.c));
        }

        if (!this.hasMagnetometer()) {
            var1.append(TSLog.boxRow(Application.B("탪撗㚼\uea18曱ㅯ뎲툳휔ꇧ븴识央앎䣍\ue94f府䀥ƪ㽧伷寇瀩㝞艼\ua7e4ଝ˒\u2b96ム죂冮䠘㍩菱Ḛ\uf38c獰鲙ꢎ\u2e7b㟇㼽\udbdf━厷榼쁟瑶䋟␇쌊䫪神謣빋㍺Ʉⲩ퀾㣶蹴緃쫖瑯儆ℙ垤䑖ꊰ栫疵楫倄⼀υꒈ촼")));
        } else {
            var1.append(TSLog.boxRow(Application.B("텏骸㚼\uea75曽ㅩ뎻툸휅ꇼ븶诎夿앟䣚\ue927庆") + this.b));
        }

        if (!this.hasSignificantMotion()) {
            var1.append(TSLog.boxRow(Application.B("탪撗㚼\uea18曯ㅧ뎲툳휘ꇵ븲诈太암䣜\ue942廫䁊Ǟ㽀众寧灶㝐艼Ɦି˓⮇ェ좍几䡸㍢雷ḇ\uf386獽鳀ꢃ\u2e71㟝㽸\udbcf┌厭榧쁔琻䊌␎쌜䫬祝謡븙㍧ɀⲵ퀻㣼踦緙쫞瑭儉⅜埦䑄ꋹ栣疼椬倔⼄\u0381ꒉ촽쭐ᙢ䊎턬ꂔ蹖")));
        } else {
            var1.append(TSLog.boxRow(Application.B("텏骸㚼\uea6b曵ㅩ뎻툴휗ꇺ븸诊夥앎䣗\ue950廩䁑ǃ㽆伖宓灬") + this.d));
        }

        var1.append(Application.B("판뿈Ꮜ콨䏬ᑾ隥\uf72d\uf201蓣鬫껛簻\ue04a淘챍篶敕ⓚᩙ樈绹唜ሠ꜌芔⸀⟭າᗙ\uedfd璐浥ᙝ\udc6d㬾횹噃립趷ୄዹᩍﻼ(皔"));
        return var1;
    }

    public boolean hasGyroscope() {
        return this.c != null;
    }

    public boolean hasAccelerometer() {
        return this.a != null;
    }

    public boolean hasMagnetometer() {
        return this.b != null;
    }

    public boolean hasSignificantMotion() {
        return this.d != null;
    }

    public boolean hasGravity() {
        return this.e != null;
    }

    public boolean hasRotation() {
        return this.f != null;
    }

    public boolean hasStepCounter() {
        return this.g != null;
    }

    public boolean hasStepDetector() {
        return this.h != null;
    }

    public boolean hasAllActivityRecognitionSensors() {
        return this.hasGyroscope() && this.hasAccelerometer() && this.hasMagnetometer();
    }
}
