package cn.com.fmsh.tsm.business.exception;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import dd;
import de;

public class BusinessException extends FM_Exception
{
  public BusinessException(String paramString)
  {
    super(paramString);
  }

  public BusinessException(String paramString, ErrorMessage paramErrorMessage)
  {
    super(paramString);
    this.a = paramErrorMessage;
  }

  public ErrorMessage getErrorMsg()
  {
    return this.a;
  }

  public static enum ErrorMessage
  {
    static
    {
      try
      {
        local_business_cancel = new ErrorMessage(FM_Bytes.startsWith(")8'}\003e'na={:g��)t.h3-", 228, 75), 0, FM_Bytes.startsWith(":bqa", 2, 111), a.local, FM_Utils.copyValueOf(5, 34, "上劳奐瑐迟稑乱ｒ接整剴叐涀捍亨"));
        local_business_apdu_handler_null = new ErrorMessage(BCCUtil.endsWith("c7\"k?C'{$)g7h7R7ol$E+m;zk5k\035e!qj", 176, 73), 1, FM_CN.subSequence("(0?,", 3), a.local, Util4Java.toString("@\tU\\捆份夕瑏嘩朳油凅", 2, 88));
        local_business_execute_fail = new ErrorMessage(FM_Exception.getChars(156, 80, "y*vd9Z70f,{`&v\n m vp!`\n#t,y"), 2, FM_CN.subSequence("'}lx", 176), a.local, FM_Bytes.startsWith("DFCM捎亾奏瑚噥多琉\001\001\006\006诳汗夷贲", 4, 17));
        local_business_init_fail = new ErrorMessage(FM_Long.concat("1gpeK}?&)%3rHkcq7\021?%&6", 5), 3, FM_Int.lastIndexOf(258, "lfgl"), a.local, FM_Utils.copyValueOf(276, 71, "丅勧変瑒噳刟如匆奦贻"));
        local_business_no_card_app_type = new ErrorMessage(FM_Bytes.startsWith("i.>x9N/|6h3|&b\022g*^~8g5R(u1B-l!h", 4, 60), 4, FM_CN.subSequence("(0?+", 3), a.local, FM_Long.concat("泻朌審序籽垚皘匦", 2));
        local_business_apdu_handler_busying = new ErrorMessage(FM_CN.subSequence("}ol!\003)/zqis6'\0343qtjQu-5.5meYw1 ;8nh", 3), 5, FM_Long.concat("e7\"+", 4), a.local, Util4Java.toString("JIC@捄亵奛瑋嘳歪忎", 140, 14));
        local_message_platform_business_handle_fail = new ErrorMessage(FM_Int.lastIndexOf(3, ":8;86\00418-,afg\\tigsnfxfSo{|yw`gJ~vv}v~C{vl"), 6, FM_Exception.getChars(250, 125, "j`}}"), a.local, FM_Exception.getChars(5, 69, "席厳夌瑋丈勶夭赤"));
        local_business_local_data_handler_null = new ErrorMessage(FM_CN.subSequence("~ns~\"\002..ypfr5&\033?mracA)=?;Vpfx!8& ^~jbq", 4), 7, FM_Int.lastIndexOf(2, "lfg`"), a.local, FM_CN.subSequence("朼圯敾捳奈瑝嘢乣穲", 2));
        local_business_para_error = new ErrorMessage(FM_Int.lastIndexOf(5, "469:0\002<*shlfwvYwi{kTi|`b"), 8, Util4Java.toString(".q{,", 280, 42), a.local, CRCUtil.valueOf(2, "冥改豙畣旪！叜敿异幩"));
        local_communication_connect_fail = new ErrorMessage(FM_Utils.copyValueOf(1, 25, "`j}6<Vat9 sqq2+wuz \030cv|%!>bP. 3"), 9, FM_Exception.getChars(2, 66, "b-nq"), a.local, FM_Int.lastIndexOf(5, "纐窶哖帨厬盙钠揺失贤"));
        local_communication_connect_param_error = new ErrorMessage(FM_Int.lastIndexOf(4, ";7:;7\003>12mtljgdrnggUhcc`jseMcugwzG|hiso"), 10, FM_Exception.getChars(5, 16, "g>/?"), a.local, CRCUtil.valueOf(2, "纐窦呋常召铳掻诸求斧ｎ佳儡皑幵叧银揼侫怴斬敕"));
        local_communication_disconnect_fail = new ErrorMessage(FM_Exception.getChars(4, 22, "1|j~9\024bx`.,!lxp34<g@q\"24bmw*&/Na|:%"), 11, FM_Int.lastIndexOf(5, "aiki"), a.local, FM_Long.concat("绔竨兡闰剸纛窱盍钪揺凰玥异帳", 164));
        local_communication_sign_in_fail = new ErrorMessage(FM_Long.concat("7ir}kM~'>3<:6ittby/\023$+*6\\gw[i{,<", 3), 12, CRCUtil.valueOf(222, "mu'4"), a.local, FM_Bytes.startsWith("绋童笯剨央赣", 2, 39));
        local_communication_request_param_error = new ErrorMessage(CRCUtil.valueOf(3, "5%8-qQ|l?6:,ufl`5%\003?{~utq'\033%'eitU>>/!m"), 13, Util4Java.toString(";c5a", 3, 81), a.local, CRCUtil.valueOf(2, "纐窦呋常召丗势诸求斧ｎ纛窫挒仢旷敀"));
        local_communication_no_response = new ErrorMessage(FM_Long.concat("6jszjN(?0==7jukcz.\0248.\023%g~hl`j!", 2), 14, FM_Long.concat("`4>-", 1), a.local, FM_Exception.getChars(174, 32, "经窨杭攱刷帴厷皃库笓攷捩"));
        local_communication_invalid_version = new ErrorMessage(CRCUtil.valueOf(2, "4&9*pR}`m<7=-vgca6$\004%sx~|h6\034\" dtqf4"), 15, FM_CN.subSequence("e5+", 216), a.local, FM_CN.subSequence("绚竮逊俾卋盙爄杷旪救", 4));
        local_communication_invalid_format = new ErrorMessage(FM_Exception.getChars(4, 7, "1khsu_daxq6$8;>2$;5]`~aiewE''=;<0"), 16, FM_Int.lastIndexOf(4, "nhhc"), a.local, CRCUtil.valueOf(4, "纒窤讫氏敮捡格弞既攛"));
        local_communication_invalid_verify = new ErrorMessage(Util4Java.toString("h*e&d\026i$a {!y2s'}:x\bq7l:p4z��v$p*b<", 5, 65), 17, CRCUtil.valueOf(3, "`zi|"), a.local, CRCUtil.valueOf(240, "拣旐梈骕夻贾"));
        local_communication_invalid_control = new ErrorMessage(FM_Utils.copyValueOf(154, 103, "ic0;-Wl90)~|0#&z|3-\025xv)'!}]*?yjw#?"), 18, FM_Bytes.startsWith("=q,*", 3, 93), a.local, FM_Long.concat("施敀皗抻斎掳利嬝", 5));
        local_communication_invalid_session = new ErrorMessage(FM_Exception.getChars(1, 70, "6/e-~\007}+g=c2+k/`3o(S;v(%f9r\003q-}'so("), 19, Util4Java.toString(":uu;", 4, 98), a.local, CRCUtil.valueOf(162, "旸敁盞佑讁"));
        local_communication_invalid_session_serial = new ErrorMessage(BCCUtil.endsWith("sf0<+Nxj\"46c~b*!vf}\002.?mdc0'\022ddx&6&}Bt4),nu", 160, 106), 20, FM_Long.concat("km: ", 122), a.local, FM_Int.lastIndexOf(214, "体讗洊汸镔计"));
        local_communication_invalid_direction = new ErrorMessage(BCCUtil.endsWith("cv ,;\036(:2dfsnrz1&6-\022>o}ts`wB#8) ,-jby", 304, 10), 21, FM_CN.subSequence("*2#t", 5), a.local, FM_Exception.getChars(4, 17, "佇诓浞氤镘讽"));
        local_communication_invalid_response = new ErrorMessage(FM_Bytes.startsWith("h1;3 \031#59#=,uuq~mqvMehv;8',\035.3#:+pkw", 3, 122), 22, FM_Exception.getChars(110, 11, "~bo="), a.local, BCCUtil.endsWith("逕诮廇筑攧捧旻攅", 144, 50));
        local_communication_no_key = new ErrorMessage(BCCUtil.endsWith("9+pc}_,1 q~t`;&\",{mMo?��%xu", 86, 111), 23, FM_Int.lastIndexOf(126, "hbac"), a.local, FM_CN.subSequence("逊俾旸１杦戥乇剩递俶兪钰", 2));
        local_communication_sign_out_fail = new ErrorMessage(FM_Utils.copyValueOf(4, 71, "c9~%g\rzo*c r*i0l6)c\013h+n>H10x\f| a#"), 24, FM_Exception.getChars(2, 91, "bf#4"), a.local, CRCUtil.valueOf(3, "笧遊奪赩"));
        local_communication_register_notify_exception = new ErrorMessage(FM_Int.lastIndexOf(240, "/+&'+\027*%&!8 &30&:;;\t%=>3((8,��nnvjb|Ybpjo{xdaa"), 25, FM_Bytes.startsWith(":-d!", 2, 58), a.local, FM_Exception.getChars(2, 11, "遁俧涙恳夃琔噵沠凟彜幱"));
        local_message_load_config_fail = new ErrorMessage(FM_Exception.getChars(5, 51, "2~'v&\002}&e:=h'J$4o%K$5c&z!\006j>{)"), 26, FM_Utils.copyValueOf(3, 10, "7(7<"), a.local, FM_Bytes.startsWith("劥輽Vs\"?fe8酕罽旉亿夵赺", 4, 91));
        local_message_command_data_invaild = new ErrorMessage(CRCUtil.valueOf(2, "4&9*pRsjs\"#4!Jexe4+5(Bj~d`\r*:3wntm"), 27, BCCUtil.endsWith("9.)k", 1, 30), a.local, FM_Exception.getChars(2, 42, "斻敍盋帪右咀廃敱挥"));
        local_message_apdu_execute_exception = new ErrorMessage(Util4Java.toString("!z>$a\np ~&|b(J<u)`\002 u0~0y0B`5v8u9|2+", 366, 72), 28, FM_Int.lastIndexOf(348, "v`da"), a.local, FM_Exception.getChars(6, 56, "\036G\013R捘仳戨衋弝幯"));
        local_message_message_handle_exception = new ErrorMessage(CRCUtil.valueOf(240, "*8+xfDa8=,1&w\\y`%49.Ttl0+,4\035v|vc'<0%u"), 29, FM_Exception.getChars(5, 65, "g/5u"), a.local, CRCUtil.valueOf(5, "\026).=~wd外琅冮珵彔广"));
        local_get_app_info_fail = new ErrorMessage(Util4Java.toString("u|.&mDr*=\\|g!\024lq<\022a`r9", 154, 26), 30, Util4Java.toString("('es", 50, 102), a.local, BCCUtil.endsWith("获反匷丛廘甯侣恲奩贶", 1, 59));
        local_apdu_reponse_invalid = new ErrorMessage(FM_Utils.copyValueOf(282, 10, "i`z\"!\b ;1*Vaxw~u6*\006*#!`g|{"), 31, Util4Java.toString(";kl|", 3, 121), a.local, CRCUtil.valueOf(2, "99>>挛仩皚哂应斱攊"));
        local_app_load_config_fail = new ErrorMessage(CRCUtil.valueOf(2, "4&9*pRp\016.<%qYtg7,2+Bh~ym"), 32, Util4Java.toString("=<#,", 5, 8), a.local, FM_Long.concat("乀劤酝罵斁仧劼輺奣赸", 2));
        local_app_config_invaild_content = new ErrorMessage(FM_Int.lastIndexOf(4, ";7:;7\003<./_bmmblaXag|jeajPs~|gq{b"), 33, CRCUtil.valueOf(3, "`zl}"), a.local, FM_Exception.getChars(3, 14, "乆劫酕罨斓亴凕寧斬攒"));
        local_app_query_app_no_fail = new ErrorMessage(FM_Exception.getChars(274, 126, "gfddo^>-+\006& 6#6\022*97\032-.��;:0;"), 34, Util4Java.toString("tc.-", 334, 38), a.local, BCCUtil.endsWith("绊窼莳厃匧皓廜由庅剌叻奬赫", 3, 81));
        local_app_query_server_fail = new ErrorMessage(FM_Long.concat("j~&>\002)#.Vejog9\024%$>!gGeop(", 174), 35, FM_Int.lastIndexOf(2, "lf`k"), a.local, FM_Bytes.startsWith("桬挼栶换乓勧俢息菪双徒诫閿皊幸厸奴货", 244, 93));
        business_order_codenot_exist = new ErrorMessage(BCCUtil.endsWith("fna .2}vC<8e}}\031>;ogw?3\001pt*)%", 5, 23), 36, FM_Long.concat("tj4", 236), a.remote, FM_Bytes.startsWith("亷晄仸硞乎孟坣", 178, 68));
        business_invalid_message_format = new ErrorMessage(Util4Java.toString("gw,573 #\022#)2 22<\n?*?:'$%B|xf|o", 6, 125), 37, BCCUtil.endsWith("?%+s", 176, 6), a.remote, FM_Int.lastIndexOf(5, "拽旞桦彔镅讲"));
        business_invalid_message_type = new ErrorMessage(FM_Bytes.startsWith("gy 3o-<eB-ed8ln*\nq&9by8cR \"r,", 4, 39), 38, FM_CN.subSequence(">-|h", 32), a.remote, CRCUtil.valueOf(166, "旼故盚淇怯簪埉"));
        business_message_check_fail = new ErrorMessage(FM_Bytes.startsWith("d w:,t3|\001 y8ih?b\t&|&qj\017y/t`", 5, 79), 39, Util4Java.toString("glqr", 88, 5), a.remote, FM_Long.concat("拧旊梘髏奿赼", 330));
        business_business_no_support = new ErrorMessage(FM_Bytes.startsWith("'9`s/m|%\002f>!p.\"}f\003me\016+j6={i6", 196, 39), 40, FM_CN.subSequence("$3\"t", 6), a.remote, FM_Utils.copyValueOf(1, 20, "诩业劵曊乑敿挅"));
        business_platform_busy = new ErrorMessage(FM_Long.concat(">ratfvm:\013/&4tmysaH 8+:", 4), 41, Util4Java.toString("3k#=", 4, 88), a.remote, BCCUtil.endsWith("糹纈徕m计穆偙冘诟", 3, 117));
        business_invalid_terminal = new ErrorMessage(FM_Int.lastIndexOf(258, "7#$17?(/\00271v`nj`Zrbzdcema"), 42, FM_Exception.getChars(2, 104, "ks{$"), a.remote, Util4Java.toString("戎杼经竧厾斪敃", 326, 33));
        business_operate_timeout = new ErrorMessage(FM_Utils.copyValueOf(4, 60, "m>t*1~$`\020d7f-z#v\020.nz4b'"), 43, FM_CN.subSequence("#2!x", 5), a.remote, Util4Java.toString("擉伓跟斳＜诬釋旡瘧弒", 5, 107));
        business_repeat_message = new ErrorMessage(Util4Java.toString("c+(1;7<?\0264&08;#\013<+8;$%z", 2, 125), 44, BCCUtil.endsWith("2 o<", 3, 46), a.remote, FM_CN.subSequence("釟夌匕", 4));
        business_message_invalid_serial = new ErrorMessage(Util4Java.toString("a;*-av#\004+tot3:-Lwg\">&|dT%$>~ca", 4, 107), 45, FM_Int.lastIndexOf(2, "effi"), a.remote, CRCUtil.valueOf(3, "卜廅剌去丐运绲"));
        business_serial_not_exist = new ErrorMessage(Util4Java.toString("gstagoxR}jbxsK{ycG|broi", 166, 1), 46, FM_Exception.getChars(4, 40, "m5|g"), a.remote, Util4Java.toString("厚纋竮任明洚氭厠丘嬋圹", 166, 62));
        business_system_error = new ErrorMessage(BCCUtil.endsWith("`5-u4}%g\rc7>m+['r,s(", 3, 62), 47, FM_Long.concat("k6 (", 3), a.remote, FM_CN.subSequence("粹纎镙讠", 180));
        business_invalid_message_length = new ErrorMessage(FM_Exception.getChars(244, 21, "/wde/38sJcq\"(2zlB?\"/bg|5\0326jj~:+"), 48, CRCUtil.valueOf(104, "nq$"), a.remote, FM_Long.concat("抯斒长庭锏议", 274));
        business_trade_timeout = new ErrorMessage(FM_Utils.copyValueOf(4, 71, "m#n-e7js\030z'}'o\016l6+h;n6"), 49, BCCUtil.endsWith("'c>}", 312, 60), a.remote, CRCUtil.valueOf(300, "亦昀趁斣"));
        business_1920_unknow = new ErrorMessage(FM_CN.subSequence("(,;>hpw`\035`y}nRieq'70", 188), 50, FM_Exception.getChars(5, 75, "ny%h"), a.remote, FM_Long.concat("stjs杤瞼锝诠", 202));
        business_interface_version_error = new ErrorMessage(FM_Int.lastIndexOf(3, "4\"+04>/.\0016nugqbdebWoydaaOt`a{g"), 51, FM_Exception.getChars(124, 61, "e\"~4"), a.remote, Util4Java.toString("九劰揾另爇朵锚订", 72, 74));
        business_merchants_not_exist = new ErrorMessage(Util4Java.toString("g+dy''h'\022k:*rb\"ra=\030n6&Ta%|<", 6, 89), 52, FM_Long.concat("m8\"'", 5), a.remote, FM_Bytes.startsWith("啂戨丗嬍坸", 3, 27));
        business_business_stop = new ErrorMessage(FM_Bytes.startsWith("d$o.|8;`\001k!lc;e8e^?c-}", 5, 75), 53, FM_Utils.copyValueOf(2, 33, "=>}`"), a.remote, FM_Bytes.startsWith("寽讬唈戤巪倁欠杊劭", 3, 101));
        business_business_will_exist = new ErrorMessage(FM_Exception.getChars(2, 104, "968zuf8 Da~ 2-n`(\034<zwo\0246cjx'"), 54, FM_CN.subSequence("$3 p", 6), a.remote, FM_Exception.getChars(4, 126, "乇勺匪屑揽冩｝屲论杔忌"));
        business_system_unknow_error = new ErrorMessage(FM_Exception.getChars(5, 63, "<h/r4|+d\tf-`&t=P;c'e%~\027b4w+q"), 55, FM_Bytes.startsWith("58rw", 4, 35), a.remote, FM_Long.concat("糧绘朸矸镑讼", 164));
        OT_CHECK_FAIL = new ErrorMessage(FM_Utils.copyValueOf(3, 21, "AWG\016\n\022OJI\r\001\034F"), 56, FM_Bytes.startsWith("0/(b", 4, 26), a.remote, BCCUtil.endsWith("庑畹寔袌凓夆朡坩贁桭宼柬夤贤", 6, 108));
        OT_APPLY_SIR_FAIL = new ErrorMessage(FM_Bytes.startsWith("��\t\024XWEO\b��\036R[HC\022\b\003", 110, 110), 57, FM_Bytes.startsWith("b!:w", 86, 90), a.remote, Util4Java.toString("呚死赐爧甼请朜勣寍俏彀甮叠＀j#)ｅ奬贫", 204, 17));
        OT_STATE_CHANGE_NOTICE = new ErrorMessage(FM_Utils.copyValueOf(208, 62, "\024M\bF\007P\033H\024J\017D\rFZ\002U\026C\034P\024"), 58, CRCUtil.valueOf(4, "o{m~"), a.remote, FM_Bytes.startsWith("吗歾贑爦厃赲杕勪犨恐厜曣遐矸夡赦", 5, 83));
        OT_AC_REQUEST_NOTICE = new ErrorMessage(FM_Bytes.startsWith("J\022X\tJ\025Y\t\\\033J\003E\r]\033A\037T\035", 4, 65), 59, FM_Utils.copyValueOf(4, 57, ":x0."), a.remote, Util4Java.toString("吓歮赍牮叏赲摶幵[B觌剖盒弟歡朆勳邱缲诰汌奤赹", 3, 103));
        OT_BUSY = new ErrorMessage(FM_Bytes.startsWith("INQ��\003\031\007", 5, 20), 60, FM_CN.subSequence("*2#y", 5), a.remote, FM_Int.lastIndexOf(296, "TH幮叮歼在夅琄宊裁凃夁"));
        user_unregistered = new ErrorMessage(CRCUtil.valueOf(1, "\";<8Dys|:7(!7qwsc"), 61, FM_Bytes.startsWith("3y g", 1, 71), a.remote, CRCUtil.valueOf(5, "畳扻杷沦冓"));
        user_incorrect_password = new ErrorMessage(FM_Long.concat(")twoWzp*;-80cIqmd1:71*", 4), 62, FM_Bytes.startsWith("4 k7", 4, 43), a.remote, Util4Java.toString("畹扷寉砟一欿砥", 82, 111));
        user_not_sign = new ErrorMessage(FM_Long.concat("q|wOu)%\0034;:f", 268), 63, FM_CN.subSequence(">.=.", 1), a.remote, CRCUtil.valueOf(314, "甸戶朸筽绲"));
        user_sign_apply = new ErrorMessage(CRCUtil.valueOf(4, "/89?A|ivl\f%%6{q"), 64, FM_Bytes.startsWith("4j?7", 4, 85), a.remote, Util4Java.toString("甬戰坢笳纶甠计乴", 5, 35));
        user_sign_fail = new ErrorMessage(Util4Java.toString("weol\r53ilI,?;j", 3, 20), 65, Util4Java.toString("$6g|", 278, 113), a.remote, CRCUtil.valueOf(4, "笤续奭赨"));
        user_sign_sucess = new ErrorMessage(Util4Java.toString("0|<1R$h,{��:f>b\"h", 230, 74), 66, FM_Utils.copyValueOf(5, 101, "!ej*"), a.remote, FM_CN.subSequence("筮纹巼戍勓", 2));
        user_logout = new ErrorMessage(FM_Utils.copyValueOf(6, 76, "d.l'\036a6b>h}"), 67, FM_Bytes.startsWith("d\"?z", 116, 29), a.remote, FM_Int.lastIndexOf(204, "男扷嶳沪镃"));
        user_register = new ErrorMessage(FM_CN.subSequence("grum\021/)<cj|r4", 4), 68, FM_Long.concat("m7\"*", 4), a.remote, FM_Exception.getChars(2, 52, "畳戸嶱泿凇"));
        user_severance = new ErrorMessage(FM_Utils.copyValueOf(3, 85, "{0}\035di7sy!{i:"), 69, FM_Long.concat("o9$'", 6), a.remote, FM_Utils.copyValueOf(3, 89, "甦扰嶲觺维"));
        user_not_login = new ErrorMessage(Util4Java.toString("vtn}Lytk\\kdhzy", 4, 4), 70, CRCUtil.valueOf(1, "fxis"), a.remote, Util4Java.toString("甬戥杪癵彉", 5, 46));
        user_id_not_matching = new ErrorMessage(FM_Exception.getChars(2, 22, ".bbo\f ;Je.#\022nx{&38iz"), 71, FM_Bytes.startsWith("5a/{", 3, 77), a.remote, Util4Java.toString("用戥軯仫讉侻恣乓匩鄏", 1, 50));
        user_locked = new ErrorMessage(CRCUtil.valueOf(3, ",9>>Bbpsj7'"), 72, Util4Java.toString(">2$y", 144, 19), a.remote, FM_CN.subSequence("甹户巽锟寗", 3));
        user_freeze = new ErrorMessage(FM_Long.concat("gn-!\001/&:ooe", 186), 73, FM_Utils.copyValueOf(2, 46, "<+x%"), a.remote, FM_Long.concat("畴戰巠冦绛", 4));
        user_get_password_count_exceed = new ErrorMessage(FM_Utils.copyValueOf(5, 123, "excs\003079\0273?*'8%7$Du~yivB}kmla;"), 74, FM_Int.lastIndexOf(282, "<>>#"), a.remote, BCCUtil.endsWith("寇硅批嚔巿纟辭剦彊方丕阒", 2, 67));
        user_info_incomplete = new ErrorMessage(Util4Java.toString("tz4+^ ?.\026xw\"f|)ml%<", 2, 40), 75, FM_Exception.getChars(3, 90, "mf!>"), a.remote, FM_Exception.getChars(60, 1, "甽戡泿冔俸恵世宐敩２诨蠥億瘹诳皼儶侧怨"));
        sptc_open_exception = new ErrorMessage(FM_Int.lastIndexOf(186, "~~{sN}cq{Ir`zkhtqq"), 76, FM_Utils.copyValueOf(2, 31, "<<y{"), a.remote, BCCUtil.endsWith("仳遉卮弋遝厒畀弙帯｟许釆诒", 216, 28));
        sptc_close_exception = new ErrorMessage(CRCUtil.valueOf(3, "*://Bmsr7\0341=ubh}3$2"), 77, CRCUtil.valueOf(152, "?obs"), a.remote, FM_Int.lastIndexOf(6, "份遀区儯閰厏畀异帹．说釉诐"));
        sptc_personalization_fail = new ErrorMessage(CRCUtil.valueOf(4, ");(.Aecq<*4*~rx~2#3\021yqh~"), 78, BCCUtil.endsWith("3vx=", 3, 100), a.remote, Util4Java.toString("亷遇匦乻亡匓夾赼", 148, 106));
        app_issuer_fail = new ErrorMessage(CRCUtil.valueOf(2, "99*\024u~mze#\0355%|j"), 79, FM_CN.subSequence("\"2#t", 5), a.remote, Util4Java.toString("畻戵匰发衃奯质", 244, 47));
        sptc_data_not_matching = new ErrorMessage(FM_Long.concat(")udxYu}33\002&<*Vy~~v(\"8&", 2), 80, FM_Long.concat("l8!'", 5), a.remote, FM_Long.concat("仹递卲敮捧丙匦鄇", 5));
        card_invaild_check = new ErrorMessage(FM_Exception.getChars(5, 42, "=i 8Y94r/1n(\tc\"1}#"), 81, FM_Utils.copyValueOf(162, 124, "<961"), a.remote, FM_Long.concat("匣爊體诂旮救", 234));
        card_not_order = new ErrorMessage(FM_Bytes.startsWith("`\"q'\\-l7\\,q'f1", 2, 64), 82, FM_Exception.getChars(5, 51, "o!w&"), a.remote, CRCUtil.valueOf(184, "卯牘讲贬儡粸乙嬝圾"));
        card_order_by_other = new ErrorMessage(BCCUtil.endsWith("$hy)\020~a12kD?&\036lq/,y", 200, 98), 83, FM_CN.subSequence("wewa", 184), a.remote, FM_CN.subSequence("危牘讬贰儿粠乇占酅；巴袾儲亅沪凝用戸讼贠", 2));
        user_order_open = new ErrorMessage(FM_Int.lastIndexOf(156, "zct`L{grrjFukys"), 84, Util4Java.toString("07~`", 2, 38), a.remote, FM_Bytes.startsWith("甭戠巻讹赠之匰己引逝", 4, 18));
        user_unsubscribe_closed = new ErrorMessage(CRCUtil.valueOf(5, ".?8<@eoav6655qkT?!1<%u"), 85, FM_Utils.copyValueOf(62, 84, "x-bq"), a.remote, FM_Exception.getChars(4, 57, "畵戡嶽逈诣与儠闡仃勁胪"));
        user_order_no_open = new ErrorMessage(FM_Int.lastIndexOf(5, "-*?)\0032,;es]mkZiwmg"), 86, BCCUtil.endsWith("> \"'", 144, 1), a.remote, FM_Utils.copyValueOf(4, 116, "甧戴嶥诩赲伕辟朱式這"));
        user_order_fail = new ErrorMessage(FM_Bytes.startsWith("s~qi]fbs{7\0235;($", 5, 7), 87, FM_Int.lastIndexOf(290, "$&$."), a.remote, FM_CN.subSequence("町戶讲贲奿赸", 4));
        user_unsubscribe_fail = new ErrorMessage(Util4Java.toString("x(le\032f/<h)*d'js:R}(~)", 302, 46), 88, BCCUtil.endsWith("1u9x", 1, 69), a.remote, FM_Exception.getChars(4, 125, "畵扭遗诶奠赫"));
        user_order_invaild_info = new ErrorMessage(BCCUtil.endsWith("2b>w\020vq)r3T<q2t+u\004,a?l", 200, 74), 89, FM_Bytes.startsWith("2eta", 2, 114), a.remote, FM_CN.subSequence("诬走侭怴敺捷朁闹飞", 64));
        sptc_app_not_issuer = new ErrorMessage(FM_Int.lastIndexOf(5, "+).8\003<./_omw[lut}lx"), 90, BCCUtil.endsWith("0*`5", 2, 57), a.remote, FM_Utils.copyValueOf(3, 118, "亪逞医廄畮杶乙輵"));
        sptc_personalization = new ErrorMessage(FM_Long.concat("e184\035==q}vjnv,*:283i", 190), 91, FM_Utils.copyValueOf(3, 97, "?d!"), a.remote, FM_Long.concat("仸逝即巯宄戃临仳卂", 4));
        invaild_personalization_info = new ErrorMessage(FM_Long.concat("=1<4igr^|r0>7-/5mu{qyt(\0165)42", 220), 92, FM_CN.subSequence("o}h:", 336), a.remote, Util4Java.toString("乡交匇攴捹万跮", 108, 83));
        business_order_not_exist = new ErrorMessage(FM_Long.concat(" 8+j`|w|E*\"?##\003i}iWvf '+", 234), 93, FM_CN.subSequence("=*zh", 158), a.remote, FM_Exception.getChars(56, 73, "讳匏乎孔坽"));
        business_order_apply_no_pay = new ErrorMessage(Util4Java.toString("aqvoimzyTcjjbNscdyoHvvEk}d", 4, 1), 94, FM_Int.lastIndexOf(4, "fiih"), a.remote, FM_Bytes.startsWith("讧化巳甬讪〚杳扴歫", 4, 62));
        business_order_pay_no_write = new ErrorMessage(Util4Java.toString("c,\" /<\":\036vcmdkNy` \016'.\006&;(mt", 2, 120), 95, FM_Long.concat("!*6\"", 152), a.remote, FM_Exception.getChars(268, 39, "讧卙嶡戹欿ぉ来兓倡"));
        business_order_recharge_sucess = new ErrorMessage(BCCUtil.endsWith("fm?i:mo#[7~$1z\003b!;d!f/9O7m/eg;", 5, 52), 96, CRCUtil.valueOf(5, "j}mz"), a.remote, FM_CN.subSequence("讳单巽扽歳そ条償倵", 3));
        business_order_amount_inconsistent = new ErrorMessage(FM_Exception.getChars(246, 45, "-i:?m5.y\bkc:nj\032sr#l('_$4d;o}2{a'ah"), 97, Util4Java.toString("0c3a", 2, 81), a.remote, FM_Int.lastIndexOf(2, "请匃醆飅乔筼"));
        business_order_unsettled_exist = new ErrorMessage(CRCUtil.valueOf(5, "99.'qura\\;72\"jVoe/(*;,tfLa-/$<"), 98, FM_CN.subSequence(" 1?(", 3), a.remote, FM_Bytes.startsWith("孚圧厳疘讴化", 1, 45));
        business_order_recharget_fail = new ErrorMessage(FM_Exception.getChars(4, 25, "?c|!/?`\0321%tl0\004&hew9#mfh\n(fiu"), 99, Util4Java.toString("bp?*", 84, 110), a.remote, FM_Int.lastIndexOf(4, "诵匍份晉奪赹"));
        business_order_apply_refund = new ErrorMessage(FM_Int.lastIndexOf(3, "4\"+04>/.\0010regq[dvwdpUyik{at"), 100, FM_Int.lastIndexOf(1, "edfo"), a.remote, FM_Bytes.startsWith("讦卉甧讻遄止丹", 3, 24));
        business_order_refund = new ErrorMessage(FM_Exception.getChars(140, 10, "gzjjcr28\n0;78uNi`il-)"), 101, FM_CN.subSequence(" 1?'", 3), a.remote, FM_Exception.getChars(82, 23, "诩北嶫逐欹"));
        business_order_rechargeting = new ErrorMessage(FM_CN.subSequence("$ wzlt3<\001\".o{G53&<b`fuk'3+", 216), 102, FM_Utils.copyValueOf(3, 50, "?qc4"), a.remote, FM_Bytes.startsWith("计匑欦圮兂側乤", 2, 97));
        business_order_paying = new ErrorMessage(Util4Java.toString("1$|d%,tv\034.m9>kH%2hf#,", 372, 94), 103, FM_Bytes.startsWith("2(u", 2, 75), a.remote, FM_CN.subSequence("讲半歭圵散亃乧", 2));
        business_order_no_refund = new ErrorMessage(FM_Utils.copyValueOf(4, 22, "mph8)8`z@:9er\0347 Zit!(=m"), 104, FM_Bytes.startsWith("7ley", 5, 119), a.remote, FM_Utils.copyValueOf(1, 90, "议匓乍胧達歰"));
        business_order_card_no_inconsistent = new ErrorMessage(Util4Java.toString("9p|pmhd2\024:--6/XrzwkF-\"\b(%60g`tte~+;", 124, 10), 105, FM_Utils.copyValueOf(2, 9, "<'.;"), a.remote, FM_Bytes.startsWith("讧匘组寇皁匬叢哑杩欬仱明盁卬厢丐丅膹", 260, 72));
        business_order_invoice = new ErrorMessage(BCCUtil.endsWith("f&q8.j-~\003$h-}u\tl:5}(s:", 5, 79), 106, FM_Long.concat("h5>.", 1), a.remote, FM_Exception.getChars(1, 65, "压祳嶮颛厈"));
        business_order_no_invoice = new ErrorMessage(FM_Long.concat("8pcrhto4\r2:7;{KqeJ)% .%4g", 2), 107, FM_Exception.getChars(2, 1, "jmlk"), a.remote, FM_CN.subSequence("诵笋亪明嶾奪攂ｕ丅胪颀參厕礻", 2));
        business_unsettled_overrun = new ErrorMessage(FM_CN.subSequence("su|w#98)Vmie  7>dt@ak))8,f", 3), 108, FM_CN.subSequence(" 1>(", 3), a.remote, FM_Utils.copyValueOf(2, 115, "叢疑诱匓跜辋丕陂）误运蠒奕瑂"));
        trade_not_exist = new ErrorMessage(FM_Int.lastIndexOf(3, "\"%9=?\00422*��eykpp"), 109, BCCUtil.endsWith(" wi<", 146, 52), a.remote, BCCUtil.endsWith("亦昀三孍坮", 3, 17));
        trade_handling = new ErrorMessage(FM_CN.subSequence("*?=oVp&8!8j|f", 80), 110, FM_Long.concat("5=*w", 172), a.remote, FM_Int.lastIndexOf(296, "亿昏夙琘串"));
        trade_fail = new ErrorMessage(FM_Bytes.startsWith("wdhx*\0353irb", 2, 19), 111, CRCUtil.valueOf(4, "kyl~"), a.remote, BCCUtil.endsWith("仹昕奾贽", 350, 73));
        trade_sucess = new ErrorMessage(FM_CN.subSequence("ernz(\0038/j}te", 3), 112, FM_Exception.getChars(5, 84, "o`6n"), a.remote, BCCUtil.endsWith("产晓才劅", 4, 61));
        trade_act_check_fail = new ErrorMessage(CRCUtil.valueOf(2, ",;;/yRlt\016!;!vmHn8#7"), 113, BCCUtil.endsWith("dv#u", 246, 79), a.remote, BCCUtil.endsWith("洬勠仺硋桺骀奬贫", 184, 49));
        no_activity = new ErrorMessage(FM_Int.lastIndexOf(174, "om\\efrn~`~r"), 114, FM_CN.subSequence("%/>,", 2), a.remote, Util4Java.toString("诡叟唐泮杁浺勲侲怣", 5, 121));
        ErrorMessage[] arrayOfErrorMessage = new ErrorMessage[115];
        arrayOfErrorMessage[0] = local_business_cancel;
        arrayOfErrorMessage[1] = local_business_apdu_handler_null;
        arrayOfErrorMessage[2] = local_business_execute_fail;
        arrayOfErrorMessage[3] = local_business_init_fail;
        arrayOfErrorMessage[4] = local_business_no_card_app_type;
        arrayOfErrorMessage[5] = local_business_apdu_handler_busying;
        arrayOfErrorMessage[6] = local_message_platform_business_handle_fail;
        arrayOfErrorMessage[7] = local_business_local_data_handler_null;
        arrayOfErrorMessage[8] = local_business_para_error;
        arrayOfErrorMessage[9] = local_communication_connect_fail;
        arrayOfErrorMessage[10] = local_communication_connect_param_error;
        arrayOfErrorMessage[11] = local_communication_disconnect_fail;
        arrayOfErrorMessage[12] = local_communication_sign_in_fail;
        arrayOfErrorMessage[13] = local_communication_request_param_error;
        arrayOfErrorMessage[14] = local_communication_no_response;
        arrayOfErrorMessage[15] = local_communication_invalid_version;
        arrayOfErrorMessage[16] = local_communication_invalid_format;
        arrayOfErrorMessage[17] = local_communication_invalid_verify;
        arrayOfErrorMessage[18] = local_communication_invalid_control;
        arrayOfErrorMessage[19] = local_communication_invalid_session;
        arrayOfErrorMessage[20] = local_communication_invalid_session_serial;
        arrayOfErrorMessage[21] = local_communication_invalid_direction;
        arrayOfErrorMessage[22] = local_communication_invalid_response;
        arrayOfErrorMessage[23] = local_communication_no_key;
        arrayOfErrorMessage[24] = local_communication_sign_out_fail;
        arrayOfErrorMessage[25] = local_communication_register_notify_exception;
        arrayOfErrorMessage[26] = local_message_load_config_fail;
        arrayOfErrorMessage[27] = local_message_command_data_invaild;
        arrayOfErrorMessage[28] = local_message_apdu_execute_exception;
        arrayOfErrorMessage[29] = local_message_message_handle_exception;
        arrayOfErrorMessage[30] = local_get_app_info_fail;
        arrayOfErrorMessage[31] = local_apdu_reponse_invalid;
        arrayOfErrorMessage[32] = local_app_load_config_fail;
        arrayOfErrorMessage[33] = local_app_config_invaild_content;
        arrayOfErrorMessage[34] = local_app_query_app_no_fail;
        arrayOfErrorMessage[35] = local_app_query_server_fail;
        arrayOfErrorMessage[36] = business_order_codenot_exist;
        arrayOfErrorMessage[37] = business_invalid_message_format;
        arrayOfErrorMessage[38] = business_invalid_message_type;
        arrayOfErrorMessage[39] = business_message_check_fail;
        arrayOfErrorMessage[40] = business_business_no_support;
        arrayOfErrorMessage[41] = business_platform_busy;
        arrayOfErrorMessage[42] = business_invalid_terminal;
        arrayOfErrorMessage[43] = business_operate_timeout;
        arrayOfErrorMessage[44] = business_repeat_message;
        arrayOfErrorMessage[45] = business_message_invalid_serial;
        arrayOfErrorMessage[46] = business_serial_not_exist;
        arrayOfErrorMessage[47] = business_system_error;
        arrayOfErrorMessage[48] = business_invalid_message_length;
        arrayOfErrorMessage[49] = business_trade_timeout;
        arrayOfErrorMessage[50] = business_1920_unknow;
        arrayOfErrorMessage[51] = business_interface_version_error;
        arrayOfErrorMessage[52] = business_merchants_not_exist;
        arrayOfErrorMessage[53] = business_business_stop;
        arrayOfErrorMessage[54] = business_business_will_exist;
        arrayOfErrorMessage[55] = business_system_unknow_error;
        arrayOfErrorMessage[56] = OT_CHECK_FAIL;
        arrayOfErrorMessage[57] = OT_APPLY_SIR_FAIL;
        arrayOfErrorMessage[58] = OT_STATE_CHANGE_NOTICE;
        arrayOfErrorMessage[59] = OT_AC_REQUEST_NOTICE;
        arrayOfErrorMessage[60] = OT_BUSY;
        arrayOfErrorMessage[61] = user_unregistered;
        arrayOfErrorMessage[62] = user_incorrect_password;
        arrayOfErrorMessage[63] = user_not_sign;
        arrayOfErrorMessage[64] = user_sign_apply;
        arrayOfErrorMessage[65] = user_sign_fail;
        arrayOfErrorMessage[66] = user_sign_sucess;
        arrayOfErrorMessage[67] = user_logout;
        arrayOfErrorMessage[68] = user_register;
        arrayOfErrorMessage[69] = user_severance;
        arrayOfErrorMessage[70] = user_not_login;
        arrayOfErrorMessage[71] = user_id_not_matching;
        arrayOfErrorMessage[72] = user_locked;
        arrayOfErrorMessage[73] = user_freeze;
        arrayOfErrorMessage[74] = user_get_password_count_exceed;
        arrayOfErrorMessage[75] = user_info_incomplete;
        arrayOfErrorMessage[76] = sptc_open_exception;
        arrayOfErrorMessage[77] = sptc_close_exception;
        arrayOfErrorMessage[78] = sptc_personalization_fail;
        arrayOfErrorMessage[79] = app_issuer_fail;
        arrayOfErrorMessage[80] = sptc_data_not_matching;
        arrayOfErrorMessage[81] = card_invaild_check;
        arrayOfErrorMessage[82] = card_not_order;
        arrayOfErrorMessage[83] = card_order_by_other;
        arrayOfErrorMessage[84] = user_order_open;
        arrayOfErrorMessage[85] = user_unsubscribe_closed;
        arrayOfErrorMessage[86] = user_order_no_open;
        arrayOfErrorMessage[87] = user_order_fail;
        arrayOfErrorMessage[88] = user_unsubscribe_fail;
        arrayOfErrorMessage[89] = user_order_invaild_info;
        arrayOfErrorMessage[90] = sptc_app_not_issuer;
        arrayOfErrorMessage[91] = sptc_personalization;
        arrayOfErrorMessage[92] = invaild_personalization_info;
        arrayOfErrorMessage[93] = business_order_not_exist;
        arrayOfErrorMessage[94] = business_order_apply_no_pay;
        arrayOfErrorMessage[95] = business_order_pay_no_write;
        arrayOfErrorMessage[96] = business_order_recharge_sucess;
        arrayOfErrorMessage[97] = business_order_amount_inconsistent;
        arrayOfErrorMessage[98] = business_order_unsettled_exist;
        arrayOfErrorMessage[99] = business_order_recharget_fail;
        arrayOfErrorMessage[100] = business_order_apply_refund;
        arrayOfErrorMessage[101] = business_order_refund;
        arrayOfErrorMessage[102] = business_order_rechargeting;
        arrayOfErrorMessage[103] = business_order_paying;
        arrayOfErrorMessage[104] = business_order_no_refund;
        arrayOfErrorMessage[105] = business_order_card_no_inconsistent;
        arrayOfErrorMessage[106] = business_order_invoice;
        arrayOfErrorMessage[107] = business_order_no_invoice;
        arrayOfErrorMessage[108] = business_unsettled_overrun;
        arrayOfErrorMessage[109] = trade_not_exist;
        arrayOfErrorMessage[110] = trade_handling;
        arrayOfErrorMessage[111] = trade_fail;
        arrayOfErrorMessage[112] = trade_sucess;
        arrayOfErrorMessage[113] = trade_act_check_fail;
        arrayOfErrorMessage[114] = no_activity;
        d = arrayOfErrorMessage;
        label5517: return;
      }
      catch (de localde)
      {
        break label5517;
      }
    }

    public static ErrorMessage instance(String paramString)
    {
      ErrorMessage[] arrayOfErrorMessage = values();
      int i = arrayOfErrorMessage.length;
      for (int j = 0; ; j++)
      {
        if (j >= i);
        ErrorMessage localErrorMessage;
        for (Object localObject = business_system_unknow_error; ; localObject = localErrorMessage)
        {
          return localObject;
          localErrorMessage = arrayOfErrorMessage[j];
          if (!localErrorMessage.getId().equals(paramString))
            break;
        }
      }
    }

    public String getDesc()
    {
      return this.c;
    }

    public String getId()
    {
      return this.a;
    }

    public a getType()
    {
      return this.b;
    }

    static enum a
    {
      static
      {
        try
        {
          local = new a(BCCUtil.endsWith("mqx99", 2, 29), 0);
          remote = new a(FM_Int.lastIndexOf(186, "kbew"), 1);
          a[] arrayOfa = new a[2];
          arrayOfa[0] = local;
          arrayOfa[1] = remote;
          a = arrayOfa;
          label59: return;
        }
        catch (dd localdd)
        {
          break label59;
        }
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.tsm.business.exception.BusinessException
 * JD-Core Version:    0.6.0
 */