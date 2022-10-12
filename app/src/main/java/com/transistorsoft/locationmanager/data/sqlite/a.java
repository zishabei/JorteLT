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
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import com.transistorsoft.locationmanager.logger.TSLog;
import com.transistorsoft.tslocationmanager.Application;

public class a extends SQLiteOpenHelper {
    private static a a;
    private static final String b = Application.B("ሁ汙幗訦Ⓔ툘怃䕤ṣ\u4dbd\ue949ᴿ吟뜾쑴鹂敽\uf6d2頹呭\ue6fe䎠鲚\uf262䥻і膝");
    private static final int c = 5;
    public static final String d = Application.B("ሙ汄幕訩Ⓗ툘怟䕾ṿ");
    public static final String e = Application.B("ሒ汎幙訮⒬툟怓䕵ṿ");
    private static final String f = Application.B("ሜ汏帖訁⒇툥怵䕗ṉ䶝\ue936ᴃ吢뜔쑘鹷敆\uf6e4顷呹\ue6d6䎘鳔\uf242䥉ѧ膠㺇췢쐫쵽天㙂\ue364睊멮࠵㪤氢鷹橾샩ۍ絇\uf325Ｗ㠦㫳錭㢳ꇎﯬ်秺跋陡༳長竮\udfe8鐡ౄ\uf511噘∷䱢ႊ영봒\ue06d좞塚㣮枱熝\uddbbꆷ鹄ž躷᜶姓쌧ῶ\uf806랬쀂\udb84\uef5eჴ㏻분줷悷췭늱蟺䷛⌈䨥쑷\ue09b馔口⧷\uee2fἙꯜ䳻ꉒ\ue20f礂躵\uf5f7靯ꤨ讆㬭赈纍ы뛦罣Ᏺ▵刿吃䂛Ḙ㤃㈓鍠礓ٿ\ud897잿𥉉漵딖䧖獏엪ﳙ狤태픁豗\u05cf䶉ᘬ鈶⍱燀ڤ匃\udcf3赊䙦춹暬\ue209\u09e5ჿﻖ렫仦폴等㠓竌擶즞ꯌ癡ۦ蹘\udcac積");
    private static final String g = Application.B("ሶ汹平訉⒝툴恐䕄ṍ䶍\ue95aᴖ呐뜔쑓鸖敚\uf6f2頃吒\ue6d6䎙鲽\uf250䥈Ѡ臏㺢췃쐋쵎夘㙦\ue34e憎멉࠹㪬氾鷨樷샄ڣ絇\uf325Ｈ㠷㪁鍃㢬ꇈﮅ္秮跕陴༳镸竮\udff7鑀\u0c50\uf508噘≘䰌უ왮뵠\ue05c좺器㣅枖燅\uddfaꆯ鹁ķ躇ᝓ姟쌶ᾂ\uf872럦쀿\udba4\uef64ჴ㏡붔줣悯췡닕蟛䷼⌽䨑쐛\ue08d駸压⦒\uee2fἙꯍ䳼ꉜ\ue218礈躱\uf5e2靧ꤼ讆㬻赂纚ѓ뚏缂Ꮦ◦刞吢䃯Ṭ㤈㈞鍸祳ٿ\ud8b7잛\ufae7漕땣䧘獗얅ﲫ狤태픈豖\u05cf䶐ᘰ鈢⌥燧ڏ卬\udcfd赀䙨춻曉\ue206\u09e4ႋﺸ렪仧페筋㡦竄擿째ꯉ癱۬蹍\udcd9稱휎ࠋ흣῾몀䵐\uf6f5ࢥ廷珢劄\ue224뒧⪹楈䅔灝\ue379⛢酡塵䟲寃ㄞ绅\uf54d큟⣜௳櫿ᥕ쐷闍䌛䑺赛츿뷗\ue123뀑");
    private static final String h = Application.B("ሱ汹幹記ⓩ툥怱䕒Ṁ䶊\ue936ᴚ吶락쑐鹮敝\uf6ee頃呡\ue6b3䎭鲛\uf260䥽ч膆㺡췂쐛");
    private static final String i = Application.B("ሜ汏帖訁⒇툥怵䕗ṉ䶝\ue936ᴃ吢뜔쑘鹷敆\uf6e4顷呹\ue6d6䎘鳔\uf242䥉ѧ膠㺇췢쐫쵽天㙂\ue364睊멮࠵㪤氾鷨橲샣ڙ絺\uf306＆㠗㪡鍃㢨ꇟﮔဠ福跉院ཇ锓童\udffb鐬\u0c5d\uf57d噙≙䰌ჼ외뵷\ue035죗﨑㣪架熀\uddaeꆯ鹐Ļ軃\u1737姄쌦ᾘ\uf86a랃쁑\udba5\uef7fႀ㎏붏줺悯춍늽螾䷉⌕䨪쐈\ue0b5馹变⧜\uee77Ὄꯝ䳰ꈑ\ue239礴躐\uf5c1靆꤉讆㬡赈纖Ч뛭罷Ᏸ◙剝呬䂬ṗ㤾㈙鍀社ث\ud8ba잎\ufae6漐딦䦺獟얅ﲼ犊킰픨谘ע䶭ᘝ鉲⌟燗ڧ匀\udc93贯䙋춘曢\ue220ৃჟﺃ렁他페筁㡼竝擱즔ꯈ瘔ۤ蹃\udcd8穝휔\u087e휟ᾞ몌䴜\uf6e9ࢯ廲珘劌\ue26b뒋⪑楮䅬灭\ue35c⛉酡塿䟲寂ㅼ织\uf55d퀳⣞ஜ櫯ᤰ쐿闙䌂䑺责츿분\ue165끙\uf3a5\udba4咹ཡ珟\ue3dc按\u09bb⩕븇\uf7e0ᶦ鸸쏢㗬ꩅⶀ歼\ue32f㚊律棚澪\ue2c0仞뾲刐劕瑬썺면दः̣葘눻毣ᖻ丆\uea97貖\ue742㾲ꑝ扳镼‒贲雾ʲ\ue750렡ꆘꅴ䷰装剑偄ꡄ\uf182팑咐퉪␅짵묟\udc8f\ue3f7뱐ꏉ⢱\u1ac0램꓿뉯㽼ơ휇灨꯸뵆ﴴힰ볩뉵\u18fb贰袄ݟힽǨ䀙忏Վ鶢䥠\ue36f覘䂰逯陁럡ೃ킫䥴鄯\udaa1覷ӡ역\uead4殠倖ᬜ虃礜⛄⩣\udfcc鹈ǌ疅厱\ue1c8罋椫竬᱔僞ᨖ\ued43ᇴꅹ̤Ỿ旈\uf561裃\u1979调븣쁂秊鳽㺅觗浶韎\ude91ᄈ㮥궚隠믃겻矛앛삕∗졧\uedba郀듁㋗紐굤வ뷴賛ܷ\ue344觭꙽☚핽耐ҿ⤯릺䇶詇燄璦샎ꐭ伓郔穡\ue381溿辌폔因抅듘睖㖖\u2bd3\uf173䊳榚곔։獺덞\ue65eᖳ쟁╠㢛惔⌯띥\ue700馓\uf4a6\u2433븃軯퀝鰒碢\uef9fಇ蝺刏餿\udef1跘\ue583狫");
    private static final String j = Application.B("ሶ汹平訉⒝툴恐䕄ṍ䶍\ue95aᴖ呐뜔쑓鸖敚\uf6f2頃吒\ue6d6䎙鲽\uf250䥈Ѡ臏㺩췉쐇쵉変㙡\ue342廒멉࠹㪬氾鷨樷샄ڣ絇\uf325Ｈ㠷㪁鍃㢬ꇈﮅ္秮跕陴༳镸竮\udff7鑀\u0c50\uf508噘≘䰌უ왮뵠\ue05c좺器㣅枖燅\uddfaꆳ鹐Ļ躍ᜇ姢쌕ᾳ\uf843랴쁑\udbbf\uef75ႌ㏻뷡줡悬축늱蟐䷯⌰䨈쑷\ue08c首句⧤\uee56ὼꮕ䲵ꉝ\ue21c礏躬\uf5f7靿ꤨ诃㭏赃纍ђ뛡置ᏹ▵刿吃䂛Ḙ㤃㈓鍠礓ٳ\ud8f3잉\ufafa漚딜䧶獺얾ﲀ犼킉픉豝\u058c䶦ᘆ鈇⌓燎ڮ卬\udcf1赀䙳췗曂\ue212০ყﻚ롅仐폗筶㡬竤擒즬ꯤ癀۟蹨\udce9穝휞ࡤ휆ᾐ뫬䵹\uf6ba\u0888廓珓勀\ue24a뒰⪺楋䄴瀸\ue354⛃鄯塜䟔寣ㅋ绯\uf57d퀳⣔ஜ櫮ᥒ쐽闉䍮䑸赀칋뷇\ue144끿\uf3b6\udb84哺༯珋\ue3dc挓\u0991⩝븍\uf7aeᶅ鸞쏃㗛ꩭⶠ歼\ue325㚊慄梸澨\ue2d0亲뾰剳勡琾썕멅ःऺͼ葘눜毃ᖝ丛\ueab7貼\ue70c㾛ꑻ打锩‸贂隒ʺ\ue733롔ꆴꅗ䷁袌剹偲ꡟ\uf1cc팚咫퉒\u243b즠묟\udcbf\ue3d9뱻ꏬ⢁\u1af2럆꒛뉮㽦Ʒ흫灣ꮍ뵄ﴷퟄ벍뉾ᣨ贽袝ܿ\ud7c9Ʀ䁆得ԇ鶪䥶\ue354覟䂓逸険럽\u0cff탳䥟鄔\udace覹ӫ엣\uead6毅候ᬝ蘷祲⛅⩢\udfa0鹊ƹ疍厸\ue1ae罎椻竦᱁傫ᩪ\ued3bᇴꄧͧẪ族\uf568裎ᥟ谋븟쁵漣鳍㻒觰浕韭\udefdᄏ㮫궛雌믈겵矁앛삕∍졿\uedd6邮듐㋞暈괅த뷽賉ݖ\ue321覍ꘉ♔픢聈Ӷ⤥리䇐詝燥璣샂ꐯ优邰穆\ue3a2溑边펱囨抅뒬睝㖞⯂\uf101䋝榁곗֑獺더\ue64eᖹ쟌┕㢓情⍉뜔\ue779駿\uf49e⑼빚躷큘鰅碳\uef85ಒ蝟则饳\udec4跤\ue5fb狶珝ⵟ㒔蒥\u1a8e\ude3e㾮辠툗墰¸榋滤\ued32䲇\u0e79ꉟ\uda66儆탃珢梈䊛쒤流相ꂥꅨᆔꄷ릲顱컙⡘휥\udf06惁鹑䨡ᕡ");
    private static final String k = Application.B("ሱ汹幹記ⓩ툥怱䕒Ṁ䶊\ue936ᴚ吶락쑐鹮敝\uf6ee頃呡\ue6b3䎦鲑\uf26c䥺і膁㺭췉쐛");
    private static final String l = Application.B("ሴ汧幢訍⒛퉑怤䕑Ṏ䶃\ue953ᵳ吜뜲쑶鹗敠\uf6d4頸呜\ue6e0䏡鲵\uf247䥘Г膬㺁췠쐽쵢夢㘯\ue354朗멓\u087d㪤氃鷉橏샙ۍ絝\uf32f［㡒㪝錶㢰ꇖﯬူ秪跁陬ཆ长竿\udf8e鑇శ");
    private static final String m = Application.B("ሴ汧幢訍⒛퉑怤䕑Ṏ䶃\ue953ᵳ吜뜲쑶鹗敠\uf6d4頸呜\ue6e0䏡鲵\uf247䥘Г膬㺁췠쐽쵢夢㘯\ue345惘멎\u0878㪤氕鷀橘샏");
    private static final String n = Application.B("ሴ汧幢訍⒛퉑怤䕑Ṏ䶃\ue953ᵳ吜뜲쑶鹗敠\uf6d4頸呜\ue6e0䏡鲵\uf247䥘Г膬㺁췠쐽쵢夢㘯\ue344憎멙\u086b㫽氧鷸橲샩ۍ絑\uf32f＠㠾㪖錢㢲ꆺﮂျ移趧陣ཆ长竧\udf8e鐤\u0c54\uf51b噍≂䰉ჹ옍봂");

    public static a a(Context var0) {
        if (a == null) {
            a = b(var0.getApplicationContext());
        }

        return a;
    }

    private static synchronized a b(Context var0) {
        if (a == null) {
            a = new a(var0.getApplicationContext());
        }

        return a;
    }

    private a(Context var1) {
        super(var1, Application.B("ཆ낃ꄷ皺䏵膌\ue796㲥ꀭ甋䡴垃꧕Đ룤\udf50\udec1枎ﳆ约雦\ue71c뮥澯\udb70줋鏖"), (CursorFactory)null, 5);
    }

    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase var1) {
        var1.execSQL(Application.B("蹣뛂삒쭭軔欔탈Ⲣ浔俓뭎ɂ璓倜椈ꡲ\u2b75⼩滹멢䃿뎇퐔黗\uf032\udb32厩폂䔺▵곱ᎉ領苔柌\uf771冟壩▲胅髼鼸緧Đ\uda90\uf19f哥燇㾢違㸫幨ᦺ껧㝦첧彾냽ᶜ踲턋쾷쐝너皶鲧㱈玅ₖ蓗햔䦝д庄椗\uda29럦\ue060斡㼨※苃曱풗绖\uef23銳ᬞ㱁鯳魲鈁䠫㦵\ue240蚢瓆酪⩻篛ꄞ샢䵲劯꩔Ѿ⏼㚾녮思\u2d7dᅃ兩痮ꌻコ⌮鎺薰ݥ恟ヶꁹ⏥､ﳐ힌볫겄\ue682\ued6e妡띾渝䷴悂\ue35e桦盧\uf36dṎ㽤媳鮐츋ꞕ쎬멊ꐳ⠚㑼鞵梛\udd7f\uefba\uf808媐핮皸雛僈⼘퉰躛騎㯵諰ⵉ᭰莆䡲\ue35a\u09caʽ鉏妃㏞몁鴄画썫隭ୂ鬐闏魵\ud8e8㮁\uebee荔\ue72a㮚ψદ崈禪ㅽ⡅뗖⢔嵍掐ꇶ㋼뚠䖢䴐駭缔\ud8a9墇ᵈ\uf255奕\ue2b2㤦ဠ\ud8db"));
        var1.execSQL(Application.B("蹣뛂삒쭭軔欔탈Ⲣ浔俓뭎ɂ璓倜椈ꡲ\u2b75⼩滹멢䃿뎇퐔黗\uf032\udb32厩평䔰▹곶᎘頟苘柇\uf771冟壩▲胅髼鼸緧Đ\uda90\uf19f哥燇㾢違㸫幨ᦺ껧㝦첧彾냽ᶜ踲턋쾷쐝너皶鲧㱈玅ₖ蓗햔䦝д庄椗\uda29럺\ue071断㼢\u206f苾曒풦继\uef71鋝ᬅ㱐鮋魨鉴䠩㦶\ue234蛆瓍金⩶篂ꅲ샣䴜勁ꨢЇ⎙㛦넧怜\u2d79ᅄ兴痻ꌣェ⍫鏎薱ݲ恞\u3098ꀕ⏊Ｗﳱ\ud7ad벟곰\ue689\ued63妹뜞渑䶰悐\ue343桩皘\uf343ṣ㽟媘鯈칞ꞔ쎧먉ꐅ⠬㑙鞃梲\udd5e\uefba\uf804媐핵盔雐僜⼚툜軹驡㯂調\u2d74᭺莦䡟\ue30e১ʌ鉼妦㏮뫭鴔頻쌎雃ମ鬹門魘\ud8cc㮰\uebaa茺\ue73d㮙ϋ\u0ac6嵭禇ㅜ⠋뗿⢲嵭揅ꇜ㋌뛌䖪䵿駼缓\ud8a3境ᴽ\uf257奎\ue2c6㤶၇\ud8b5컸ᆸ磕엽캘\ue404\ueb4b迤脎출硘驔촧鈉涵\ue268ᆍ䄇粩″廊騃냽᠊昩\u4db9렙\uf720\ueabe쵿\udfe2垃雿\udf95楮犨곊㰺矢웬샏烳뷭밽琗ጯΉ\u177b鸞뷫覤\uda13ப쨃礯蒸㤹鷲삞Ꮲ\ue166ⶲ┗\uf776륍啨ꏕ耼\ue8b2∲邫\u0a63躎䷑挐藻\uf346\ue227\ue46dĞ멁絙㼉⠸紭朤\uf1bd\u2061\ue465䅆ᏻྲྀ隄팬\uf6a3\ue719\uf60dሞ唻凄䝳බڐ噀ら\ue512ᖸ绺홗줚揋䟔\udad5更妔蛼趌伷絝﬩뼧戮\uf232啓༬疇◸뉨舟ḓ闣Á쒸箊覜븤癡髻襺㫥樃ƫ瑡ᝄሄᵊ檈㊲똆ꕎṼ靃ක㝁\ue2c3ሲ礯맚䷬︤뤙籞퉠攏퐵᭣\ued0d㶴ꬄ欃쮷㘘즺ⶇ繁틐錁䯒橭픃➮⽽४槶繽ਿﺄ둉쨟悥볅䄤ﱚ퇲줐з㷨\uef30\ue9dd叽ᯀꮭ\udac0쪺\uedca韵Ͼ쌿僙㞣반ލ霷\uf089ᤪ䶦镘긱刿遣뽉魶햪쓡铷숚뤰獂뇕붠輌⇦勐᱄\uf17c勵첻㵥\ue891\u0a00苿筍\u19aeﳎ\ud8b2ᴈ燦䐈쳼酬髂钗⟣㒒枯\uf85c╁볽䌏㷀䜬쳽력⍎⥼뚗咋එ\uea3c♄蝐"));
        TSLog.logger.debug(Application.B("蹣뛂삒쭭軔欔탈Ⲣ浔俓뭎ɂ璓倜椈ꡲ\u2b75⼩滹멢䃿뎇퐔黗\uf032\udb32厩폂䔺▵곱ᎉ領苔柌\uf771冟壩▲胅髼鼸緧Đ\uda90\uf19f哥燇㾢違㸫幨ᦺ껧㝦첧彾냽ᶜ踲턋쾷쐝너皶鲧㱈玅ₖ蓗햔䦝д庄椗\uda29럦\ue060斡㼨※苃曱풗绖\uef23銳ᬞ㱁鯳魲鈁䠫㦵\ue240蚢瓆酪⩻篛ꄞ샢䵲劯꩔Ѿ⏼㚾녮思\u2d7dᅃ兩痮ꌻコ⌮鎺薰ݥ恟ヶꁹ⏥､ﳐ힌볫겄\ue682\ued6e妡띾渝䷴悂\ue35e桦盧\uf36dṎ㽤媳鮐츋ꞕ쎬멊ꐳ⠚㑼鞵梛\udd7f\uefba\uf808媐핮皸雛僈⼘퉰躛騎㯵諰ⵉ᭰莆䡲\ue35a\u09caʽ鉏妃㏞몁鴄画썫隭ୂ鬐闏魵\ud8e8㮁\uebee荔\ue72a㮚ψદ崈禪ㅽ⡅뗖⢔嵍掐ꇶ㋼뚠䖢䴐駭缔\ud8a9墇ᵈ\uf255奕\ue2b2㤦ဠ\ud8db"));
        TSLog.logger.debug(Application.B("蹣뛂삒쭭軔欔탈Ⲣ浔俓뭎ɂ璓倜椈ꡲ\u2b75⼩滹멢䃿뎇퐔黗\uf032\udb32厩평䔰▹곶᎘頟苘柇\uf771冟壩▲胅髼鼸緧Đ\uda90\uf19f哥燇㾢違㸫幨ᦺ껧㝦첧彾냽ᶜ踲턋쾷쐝너皶鲧㱈玅ₖ蓗햔䦝д庄椗\uda29럺\ue071断㼢\u206f苾曒풦继\uef71鋝ᬅ㱐鮋魨鉴䠩㦶\ue234蛆瓍金⩶篂ꅲ샣䴜勁ꨢЇ⎙㛦넧怜\u2d79ᅄ兴痻ꌣェ⍫鏎薱ݲ恞\u3098ꀕ⏊Ｗﳱ\ud7ad벟곰\ue689\ued63妹뜞渑䶰悐\ue343桩皘\uf343ṣ㽟媘鯈칞ꞔ쎧먉ꐅ⠬㑙鞃梲\udd5e\uefba\uf804媐핵盔雐僜⼚툜軹驡㯂調\u2d74᭺莦䡟\ue30e১ʌ鉼妦㏮뫭鴔頻쌎雃ମ鬹門魘\ud8cc㮰\uebaa茺\ue73d㮙ϋ\u0ac6嵭禇ㅜ⠋뗿⢲嵭揅ꇜ㋌뛌䖪䵿駼缓\ud8a3境ᴽ\uf257奎\ue2c6㤶၇\ud8b5컸ᆸ磕엽캘\ue404\ueb4b迤脎출硘驔촧鈉涵\ue268ᆍ䄇粩″廊騃냽᠊昩\u4db9렙\uf720\ueabe쵿\udfe2垃雿\udf95楮犨곊㰺矢웬샏烳뷭밽琗ጯΉ\u177b鸞뷫覤\uda13ப쨃礯蒸㤹鷲삞Ꮲ\ue166ⶲ┗\uf776륍啨ꏕ耼\ue8b2∲邫\u0a63躎䷑挐藻\uf346\ue227\ue46dĞ멁絙㼉⠸紭朤\uf1bd\u2061\ue465䅆ᏻྲྀ隄팬\uf6a3\ue719\uf60dሞ唻凄䝳බڐ噀ら\ue512ᖸ绺홗줚揋䟔\udad5更妔蛼趌伷絝﬩뼧戮\uf232啓༬疇◸뉨舟ḓ闣Á쒸箊覜븤癡髻襺㫥樃ƫ瑡ᝄሄᵊ檈㊲똆ꕎṼ靃ක㝁\ue2c3ሲ礯맚䷬︤뤙籞퉠攏퐵᭣\ued0d㶴ꬄ欃쮷㘘즺ⶇ繁틐錁䯒橭픃➮⽽४槶繽ਿﺄ둉쨟悥볅䄤ﱚ퇲줐з㷨\uef30\ue9dd叽ᯀꮭ\udac0쪺\uedca韵Ͼ쌿僙㞣반ލ霷\uf089ᤪ䶦镘긱刿遣뽉魶햪쓡铷숚뤰獂뇕붠輌⇦勐᱄\uf17c勵첻㵥\ue891\u0a00苿筍\u19aeﳎ\ud8b2ᴈ燦䐈쳼酬髂钗⟣㒒枯\uf85c╁볽䌏㷀䜬쳽력⍎⥼뚗咋එ\uea3c♄蝐"));
    }

    @SuppressLint("Range")
    public void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
        Log.i(Application.B("ᦢ䮸ۦ줌쭼낈┗᧭炶ᎏ짆瘗亞Ԯ\uedd4\uea5b"), Application.B("ᦙ䮅ۿ줓쭸낛│᧠炼Ꮑ짯瘗亄Ԩ\uedd3\uea48䒅\udf13睹\uf64b") + var2 + Application.B("\u19db䯕") + var3);
        SQLiteDatabase var10000;
        switch(var2) {
            case 1:
                var1.execSQL(Application.B("ᦵ䮹ۯ줢쭋났╃᧐炘Ꭳ짇瘳仐Ԁ\uedf7\uea09䒸\udf39眗\uf64b\uf61b桀넛\ue333༅䪏ⶃ芻祝ᢥ\u0fdb穋\uda8f⩯쌏ṿᏃ梹ᅇⅳ浈\ue599싈袠\ue2a5綽ᱪ鞀鉶텧拣に\ue74d窐ڞﱽ䣣Ⅻეቧ\udf96䏖谟憱뱃鰺긒煈＝鿡鮰ⴲꞫ\ued16惪홙捾콣픏蕛ݲಜ짦ﰉ쫙難䮅醎\ue9c4Ỡ짩㞲⣝\udd69搜쮴决\ue744娵럁ㅐ壺협깝\ued44퀇ꛐᰄ鰤忊럒⧷\ue6b9ﳻ仌傈鎂巊\uecca䨤\ued27\u0600\udeeaਹ\uf830\ued15\uf689羒웍䅫\ue4e0鷮譊ড\udf23\ue273帏䤵嬐좷㽌¶練⏲쟰ኤ却煦훔\ue2a8虘帆툂先砷덑骺溯\uef67熕\uf5a0僱粘ℑ⨖ꦈ㚈㩸\ued9aꮸ䖳燊䬢ꇝᓑ霕긼玆㪰褭枧䔉ශ㏗吉\uf6a7짊鳺魃\ue455弬ᓇਓ跋瓠笩൹䏪ܨ柡忔㹁웞踔\u1ac4鏌굈抢侤ꮬ昌ℽ旞鱶鷃\ude05䔁撾檑䭠\ue545㭄頲岫᱕⧰霁靿쒀䢬蔜菉榒ℨ穭\uda64됡뵀謻뜦\uf6c1꘥燕\ua63f幗蔛斷\ue6e7옦渓辳鼇뉭瞩\uf8a1쪙핂ꉓ풿뼍\ufdd1ꋞ犳ꅨ䔂Ờ\ue07a\ueaf5滻\u1316朏䱦澜潡幁ꛑѝ墦樦詔\ue348话賧ꢁ薭飱\ue0b0\uf251\u0a92\ufdc8᯽￪ﯚ\ue823粯톸笠允珴驜垶蟡쎃㞠瀅ر终\ue1b6\uda25䔵딒枢ꇰ濏扁軋赶ნ㱩꺖眎疂误櫑癡䙃읏㦿뒑坿\uebc5릈孔⮊寊扂ꓗ竰\ue922ӣ桚ት\uf0f2ꋑӍ郷枆\ue5a1炓\uee45\uffc8Ґ曗ꍥ늽孥⼌㌊訩쉛ቼ碵퇈닛⻤㒻\ue3ac繊䨵璉\udd63ゕ괋랛݀⻊봑舾턇騾䀊ᑙ萪빬ᴊ⸜懀᱈儅砷\ue513\udd9c̀\udf2bו쬒凞ᔏ侾巳⋏׆퉐൚ӯ纙菱主㔅櫩맥凂㐦ꌚ뤪숭疿횩뎽偉ᓘ諾戽楓ꜽ\uddc9䌬踄ꁦ\ue24d韂ꃰ⃭Ȿ쨺ኗ唰ꂠḮ䅄㊔俻\uf4a4䆣\ud8fb놃튷鵯챕욹\uee9eꪌⰍꬪ\uf18c밮᯼遌帞鵪褨\uf1ad뤦⪱샧邸쎕\uf1fbḨ它\ue6d4⼧ᐅ萀\ue523鉠⽩ἄ\ud8c9䜱❉○ᒴ罪縫\uf71d몀ѻ觷"));
            case 2:
                try {
                    var1.execSQL(Application.B("ᦷ䮧۾줦쭍냉┷ᧅ炛Ꭽ짎癖亜Ԧ\uedd2\uea48䒂\udf1f眬\uf605\uf62d核넓\ue324༕䫼ⷠ芓喝ᢟ\u0ff0穠\udac1⩹쌟ṥᎇ梱ᅺ⅒洰\ue584슦袺\ue2af綮ᰏ鞜練텻拽。\ue744窔ڊﱥ䢖ⅬჅሞ\udf91䎰"));
                } catch (SQLiteException var186) {
                    Log.i(Application.B("ᦢ䮸ۦ줌쭼낈┗᧭炶ᎏ짆瘗亞Ԩ\uedd6\uea4c䒄"), Application.B("ᦿ䮌ۄ줌쭭낌┇ᦤ炊Ꮀ짇瘿交Ԍ\ued91\uea4c䒄\udf04眬\uf619\uf664核") + var186.getMessage());
                }
            case 3:
                var10000 = var1;
                var1.execSQL(Application.B("ᦵ䮹ۯ줢쭋났╃᧐炘Ꭳ짇瘳仐Ԁ\uedf7\uea09䒸\udf39眗\uf64b\uf61b桀넛\ue333༅䪏ⶃ芻祝ᢥ\u0fdb穋\uda8f⩯쌏ṿᏃ梹ᅇⅳ浈\ue599싈袠\ue2a5綽ᱪ鞀鉶텧拣に\ue74d窐ڞﱽ䣣Ⅻეቧ\udf96䏖谟憱뱃鰺긒煈＝鿡鮰ⴲꞫ\ued16惪홙捾콣픏蕛ݲಜ짦ﰉ쫙難䮅醎\ue9c4Ỡ짩㞲⣝\udd69搜쮴决\ue744娵럁ㅐ壺협깝\ued44퀇ꛐᰄ鰤忊럒⧷\ue6b9ﳻ仌傈鎂巊\uecca䨤\ued27\u0600\udeeaਹ\uf830\ued15\uf689羒웍䅫\ue4e0鷮譊ড\udf23\ue273帏䤵嬐좷㽌¶練⏲쟰ኤ却煦훔\ue2a8虘帆툂先砷덑骺溯\uef67熕\uf5a0僱粘ℑ⨖ꦈ㚈㩸\ued9aꮸ䖳燊䬢ꇝᓑ霕긼玆㪰褭枧䔉ශ㏗吉\uf6a7짊鳺魃\ue455弬ᓇਓ跋瓠笩൹䏪ܨ柡忔㹁웞踔\u1ac4鏌굈抢侤ꮬ昌ℽ旞鱶鷃\ude05䔁撾檑䭠\ue545㭄頲岫᱕⧰霁靿쒀䢬蔜菉榒ℨ穭\uda64됡뵀謻뜦\uf6c1꘥燕\ua63f幗蔛斷\ue6e7옦渓辳鼇뉭瞩\uf8a1쪙핂ꉓ풿뼍\ufdd1ꋞ犳ꅨ䔂Ờ\ue07a\ueaf5滻\u1316朏䱦澜潡幁ꛑѝ墦樦詔\ue348话賧ꢁ薭飱\ue0b0\uf251\u0a92\ufdc8᯽￪ﯚ\ue823粯톸笠允珴驜垶蟡쎃㞠瀅ر终\ue1b6\uda25䔵딒枢ꇰ濏扁軋赶ნ㱩꺖眎疂误櫑癡䙃읏㦿뒑坿\uebc5릈孔⮊寊扂ꓗ竰\ue922ӣ桚ት\uf0f2ꋑӍ郷枆\ue5a1炓\uee45\uffc8Ґ曗ꍥ늽孥⼌㌊訩쉛ቼ碵퇈닛⻤㒻\ue3ac繊䨵璉\udd63ゕ괋랛݀⻊봑舾턇騾䀊ᑙ萪빬ᴊ⸜懀᱈儅砷\ue513\udd9c̀\udf2bו쬒凞ᔏ侾巳⋏׆퉐൚ӯ纙菱主㔅櫩맥凂㐦ꌚ뤪숭疿횩뎽偉ᓘ諾戽楓ꜽ\uddc9䌬踄ꁦ\ue24d韂ꃰ⃭Ȿ쨺ኗ唰ꂠḮ䅄㊔俻\uf4a4䆣\ud8fb놃튷鵯챕욹\uee9eꪌⰍꬪ\uf18c밮᯼遌帞鵪褨\uf1ad뤦⪱샧邸쎕\uf1fbḨ它\ue6d4⼧ᐅ萀\ue523鉠⽩ἄ\ud8c9䜱❉○ᒴ罪縫\uf71d몀ѻ觷"));

                try {
                    var10000.execSQL(Application.B("ᦷ䮧۾줦쭍냉┷ᧅ炛Ꭽ짎癖亜Ԧ\uedd2\uea48䒂\udf1f眬\uf605\uf62d核넓\ue324༕䫼ⷠ芓喝ᢟ\u0ff0穠\udac1⩹쌟ṥᎇ梱ᅺ⅒洰\ue584슦袺\ue2af綮ᰏ鞜練텻拽。\ue744窔ڊﱥ䢖ⅬჅሞ\udf91䎰"));
                } catch (SQLiteException var185) {
                    Log.i(Application.B("ᦢ䮸ۦ줌쭼낈┗᧭炶ᎏ짆瘗亞Ԩ\uedd6\uea4c䒄"), Application.B("ᦿ䮌ۄ줌쭭낌┇ᦤ炊Ꮀ짇瘿交Ԍ\ued91\uea4c䒄\udf04眬\uf619\uf664核") + var185.getMessage());
                }
            case 4:
                SQLiteException var208;
                label1473: {
                    SQLiteDatabase var209;
                    boolean var10001;
                    SQLiteDatabase var10002;
                    try {
                        var10000 = var1;
                        var209 = var1;
                        var10002 = var1;
                        Log.i(Application.B("ᦢ䮸ۦ줌쭼낈┗᧭炶ᎏ짆瘗亞Ԩ\uedd6\uea4c䒄"), Application.B("ᦷ䮧۾줦쭍냉┷ᧅ炛Ꭽ짎癖亜Ԧ\uedd2\uea48䒂\udf1f眬\uf605\uf62d核넓\ue324༕䫼ⷠ芓喝ᢟ\u0ff0穠\udac1⩨쌋Ṹᎂ梱ᅬ⅛洧\ue592"));
                    } catch (SQLiteException var202) {
                        var208 = var202;
                        var10001 = false;
                        break label1473;
                    }

                    try {
                        Log.i(Application.B("ᦢ䮸ۦ줌쭼낈┗᧭炶ᎏ짆瘗亞Ԩ\uedd6\uea4c䒄"), Application.B("ᦷ䮧۾줦쭍냉┷ᧅ炛Ꭽ짎癖亜Ԧ\uedd2\uea48䒂\udf1f眬\uf605\uf62d核넓\ue324༕䫼ⷠ芓喝ᢟ\u0ff0穠\udac1⩩쌄ṯ᎑棨ᅞⅣ洍\ue5b4슦袶\ue2af綵ᱣ鞗瘝텹抑ぬ\ue74f窅۬ﱪ䢖Ⅼოሞ\udff2䏒谌憤뱙鰿금焫ｿ"));
                    } catch (SQLiteException var201) {
                        var208 = var201;
                        var10001 = false;
                        break label1473;
                    }

                    try {
                        var10002.execSQL(Application.B("ᦷ䮧۾줦쭍냉┷ᧅ炛Ꭽ짎癖亜Ԧ\uedd2\uea48䒂\udf1f眬\uf605\uf62d核넓\ue324༕䫼ⷠ芓喝ᢟ\u0ff0穠\udac1⩨쌋Ṹᎂ梱ᅬ⅛洧\ue592"));
                        var209.execSQL(Application.B("ᦷ䮧۾줦쭍냉┷ᧅ炛Ꭽ짎癖亜Ԧ\uedd2\uea48䒂\udf1f眬\uf605\uf62d核넓\ue324༕䫼ⷠ芓喝ᢟ\u0ff0穠\udac1⩩쌄ṯ᎑棨ᅞⅣ洍\ue5b4슦袶\ue2af綵ᱣ鞗瘝텹抑ぬ\ue74f窅۬ﱪ䢖Ⅼოሞ\udff2䏒谌憤뱙鰿금焫ｿ"));
                    } catch (SQLiteException var200) {
                        var208 = var200;
                        var10001 = false;
                        break label1473;
                    }

                    Cursor var203 = null;

                    Throwable var210;
                    label1470: {
                        Cursor var211;
                        try {
                            var211 = var10000.query(false, Application.B("ᦚ䮄ۉ줂쭫낀┌᧪炪"), (String[])null, (String)null, (String[])null, (String)null, (String)null, (String)null, (String)null);
                        } catch (Throwable var199) {
                            var210 = var199;
                            var10001 = false;
                            break label1470;
                        }

                        var203 = var211;

                        while(true) {
                            boolean var213;
                            try {
                                var213 = var203.moveToNext();
                            } catch (Throwable var198) {
                                var210 = var198;
                                var10001 = false;
                                break;
                            }

                            if (!var213) {
                                if (var203 == null) {
                                    return;
                                }

                                try {
                                    var203.close();
                                    return;
                                } catch (SQLiteException var189) {
                                    var208 = var189;
                                    var10001 = false;
                                    break label1473;
                                }
                            }

                            Cursor var10003;
                            Cursor var212;
                            Cursor var10004;
                            String var10005;
                            Cursor var215;
                            try {
                                var10000 = var1;
                                var215 = var203;
                                var212 = var203;
                                var10003 = var203;
                                var10004 = var203;
                                var10005 = var203.getString(var203.getColumnIndex(Application.B("ᦜ䮘ۅ줍")));
                            } catch (Throwable var197) {
                                var210 = var197;
                                var10001 = false;
                                break;
                            }

                            String var206 = var10005;

                            int var216;
                            try {
                                var216 = var10003.getInt(var10004.getColumnIndex(Application.B("ᦟ䮏")));
                            } catch (Throwable var196) {
                                var210 = var196;
                                var10001 = false;
                                break;
                            }

                            var3 = var216;

                            String var219;
                            try {
                                var219 = var215.getString(var212.getColumnIndex(Application.B("ᦃ䮞ۃ줇")));
                            } catch (Throwable var195) {
                                var210 = var195;
                                var10001 = false;
                                break;
                            }

                            String var4 = var219;

                            ContentValues var221;
                            try {
                                TSLog.logger.debug(TSLog.ok(Application.B("ᦻ䮂ۍ중쭾낝┆ᦤ") + var4 + Application.B("᧖䮡۹줬쭑냉┗᧫烹ᎅ짪瘂云թ\uedf3\uea65䒹\udf34")));
                                var221 = new ContentValues();
                            } catch (Throwable var194) {
                                var210 = var194;
                                var10001 = false;
                                break;
                            }

                            ContentValues var214 = var221;
                            ContentValues var207;
                            ContentValues var217 = var207 = var221;

                            String var220;
                            try {
                                var220 = var206;
                            } catch (Throwable var193) {
                                var210 = var193;
                                var10001 = false;
                                break;
                            }

                            var206 = Application.B("ᦒ䮊۞줂");

                            try {
                                var217.put(var206, var220.getBytes());
                            } catch (Throwable var192) {
                                var210 = var192;
                                var10001 = false;
                                break;
                            }

                            String var218 = Application.B("ᦓ䮅ۉ중쭦낙┗᧡炽");

                            try {
                                var214.put(var218, 0);
                                var221.put(Application.B("ᦜ䮘ۅ줍"), "");
                            } catch (Throwable var191) {
                                var210 = var191;
                                var10001 = false;
                                break;
                            }

                            var219 = Application.B("ᦚ䮄ۉ줂쭫낀┌᧪炪");

                            try {
                                var10000.update(var219, var207, Application.B("ᦟ䮏ڗ") + var3, (String[])null);
                            } catch (Throwable var190) {
                                var210 = var190;
                                var10001 = false;
                                break;
                            }
                        }
                    }

                    Throwable var205 = var210;
                    if (var203 != null) {
                        try {
                            var203.close();
                        } catch (SQLiteException var188) {
                            var208 = var188;
                            var10001 = false;
                            break label1473;
                        }
                    }

                    try {
                        try {
                            throw var205;
                        } catch (Throwable ex) {
                            ex.printStackTrace();
                        }
                    } catch (SQLiteException var187) {
                        var208 = var187;
                        var10001 = false;
                    }
                }
//
//                SQLiteException var204 = var208;
//                Log.i(Application.B("ᦢ䮸ۦ줌쭼낈┗᧭炶ᎏ짆瘗亞Ԩ\uedd6\uea4c䒄"), Application.B("ᦿ䮌ۄ줌쭭낌┇ᦤ炊Ꮀ짇瘿交Ԍ\ued91\uea4c䒄\udf04眬\uf619\uf664核") + var204.getMessage());
        }

    }

    public void onDowngrade(SQLiteDatabase var1, int var2, int var3) {
        if (var3 == 1) {
            var1.execSQL(Application.B("╿뭩\uebef\uf473좏䍟劇劵筿残聀嵂啙ʼᐭ퍹ﱙ븤뗯㳔蝅췅髾瘦찂묈\uddcb㼺䙒\ueccf"));
        }

        this.onCreate(var1);
    }
}
