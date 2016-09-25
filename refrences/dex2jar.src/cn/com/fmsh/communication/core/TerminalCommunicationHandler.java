package cn.com.fmsh.communication.core;

import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.CommunicationNotify;
import cn.com.fmsh.communication.TerminalCommunication;
import cn.com.fmsh.communication.exception.CommunicationException;
import cn.com.fmsh.communication.exception.CommunicationException.CommandDirection;
import cn.com.fmsh.communication.exception.CommunicationException.CommunicationExceptionType;
import cn.com.fmsh.communication.exception.SocketException;
import cn.com.fmsh.communication.exception.session.CloseSessionException;
import cn.com.fmsh.communication.exception.session.OpenSessionException;
import cn.com.fmsh.communication.exception.session.OpenSessionException.OpenSessionExceptionType;
import cn.com.fmsh.exception.InvalidParameterException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.algorithm.DES;
import cn.com.fmsh.util.algorithm.MAC;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import cn.com.fmsh.util.socket.DataLengthHandle;
import cn.com.fmsh.util.socket.ReceiveHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;
import n;
import q;

public class TerminalCommunicationHandler
  implements TerminalCommunication
{
  public byte[] r;

  /** @deprecated */
  // ERROR //
  public final void a(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield 524	cn/com/fmsh/communication/core/TerminalCommunicationHandler:t	Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_3
    //   11: goto -4 -> 7
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	10	q
    //   2	7	14	finally
  }

  public void cancel()
  {
    this.r = null;
    if (this.ab != null)
      this.ab.cancel();
    this.y = true;
  }

  public boolean closeSession(CloseSessionRequest paramCloseSessionRequest)
    throws InvalidParameterException, SocketException, CommunicationException, CloseSessionException
  {
    if (this._ == null)
      this._ = LogFactory.getInstance().getLog();
    if (this._ != null)
      this._.debug(this.aa, FM_Bytes.startsWith("8< dh,0*)nn|\0064~z,.37;c", 4, 28));
    if (this.x)
      this.p.end();
    this.r = null;
    this.w = false;
    ControlWord localControlWord1 = new ControlWord();
    localControlWord1.setDirection(ControlWord.Direction.REQUEST);
    localControlWord1.setType(ControlWord.MessageType.CONTROL);
    localControlWord1.setCommandType(ControlWord.CommandType.CLOSESESSION);
    b();
    MessageHead localMessageHead = new MessageHead();
    localMessageHead.setProtocolVersion(17);
    localMessageHead.setSessionNumber(this.n);
    localMessageHead.setSerialNumber(this.o);
    byte[] arrayOfByte1 = new byte[2];
    arrayOfByte1[0] = (byte)MessageHead.SecurityLevel.PLAIN.getValue();
    arrayOfByte1[1] = (byte)MessageHead.CheckType.MAC.getValue();
    localMessageHead.setSecurityLevel(arrayOfByte1);
    localMessageHead.setControlWord(localControlWord1);
    if (paramCloseSessionRequest == null)
      paramCloseSessionRequest = new CloseSessionRequest();
    byte[] arrayOfByte2 = FM_Bytes.join(localMessageHead.toBytes(), paramCloseSessionRequest.toBytes());
    byte[] arrayOfByte3 = FM_Bytes.join(arrayOfByte2, Arrays.copyOf(MAC.calculateMAC4DES(Arrays.copyOfRange(this.j, this.k, this.l), new byte[8], arrayOfByte2), 4));
    if (this._ != null)
      this._.debug(this.aa, FM_Exception.getChars(5, 1, "=3org#w`utafd+~h}zubf)") + FM_Bytes.bytesToHexString(arrayOfByte3));
    this.p.end();
    try
    {
      byte[] arrayOfByte4 = a(this.q.initDataSize(arrayOfByte3.length), arrayOfByte3);
      if (arrayOfByte4 == null)
      {
        if (this._ != null)
          this._.error(this.aa, FM_Int.lastIndexOf(1, "纜窺笨遗斮ｕ帩厫咑廉攮挱为穻．筽逄头责"));
        throw new CommunicationException(FM_Exception.getChars(148, 120, "绅竪笣違斻ｉ帮厥咀廑敭捻丷穿１筫逍头赸"));
      }
      if (this._ != null)
        this._.debug(this.aa, FM_Exception.getChars(2, 9, "笥逄哀庂％") + FM_Bytes.bytesToHexString(arrayOfByte4));
      if (arrayOfByte4.length < 12)
      {
        if (this._ != null)
          this._.error(this.aa, FM_Int.lastIndexOf(2, "纝窹笩遘斯ｖ敭剬咐廊支据栽弍锚诫）筸逇夹贬"));
        CommunicationException localCommunicationException3 = new CommunicationException(FM_CN.subSequence("绚竮筮速斸ｑ敺剫哇庍數捹桺彚镝讼．筯退夾贻", 4));
        localCommunicationException3.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_FORMAT);
        localCommunicationException3.setDirection(CommunicationException.CommandDirection.RESPONSE);
        throw localCommunicationException3;
      }
      while (localControlWord2.getReponseCode() != 0)
      {
        CommunicationException.CommunicationExceptionType localCommunicationExceptionType = CommunicationException.CommunicationExceptionType.instance(localControlWord2.getReponseCode());
        CommunicationException localCommunicationException2 = new CommunicationException(FM_Exception.getChars(5, 104, "笠遆奿贳$") + localCommunicationExceptionType.getDescription());
        localCommunicationException2.setExceptionType(localCommunicationExceptionType);
        localCommunicationException2.setDirection(CommunicationException.CommandDirection.RESPONSE);
        throw localCommunicationException2;
        localMessageHead.fromBytes(Arrays.copyOf(arrayOfByte4, 12));
        localControlWord2 = localMessageHead.getControlWord();
        if (localControlWord2.getDirection() == ControlWord.Direction.RESPONSE)
          continue;
        CommunicationException localCommunicationException1 = new CommunicationException(FM_Utils.copyValueOf(148, 20, "绗竼笹遛方／攡刻皛帠厷攫捡与昸咆廋攣捩７筱遃奦赮"));
        localCommunicationException1.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_DIRECTION);
        localCommunicationException1.setDirection(CommunicationException.CommandDirection.RESPONSE);
        throw localCommunicationException1;
      }
    }
    catch (Exception localException)
    {
      ControlWord localControlWord2;
      this.v = false;
      throw new SocketException(Util4Java.getExceptionInfo(localException));
      if (localControlWord2.getCommandType() != ControlWord.CommandType.CLOSESESSION)
        throw new CommunicationException(FM_Exception.getChars(216, 115, "纙窫筩逊诪汒旵ｚ敿剬盋帱句數捵七昮笪遇廎笙"));
    }
    catch (IOException localIOException)
    {
      this.v = false;
      throw new SocketException(Util4Java.getExceptionInfo(localIOException));
    }
    return true;
  }

  /** @deprecated */
  public boolean connect(LinkInfo paramLinkInfo)
    throws InvalidParameterException, SocketException
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this._ != null)
          continue;
        this._ = LogFactory.getInstance().getLog();
        boolean bool = isConnect();
        if (!bool)
          break label314;
        return true;
        if ((paramLinkInfo.getAddress() != null) && (paramLinkInfo.getAddress().length() >= 1))
          break label167;
        if (this._ == null)
          continue;
        this._.error(this.aa, Util4Java.toString("钫探利常叭方－伳儠皓帺厫圽土丫稹", 86, 50));
        throw new InvalidParameterException(FM_Exception.getChars(4, 87, "钣揱刻幱厩旦＋佾兰皈帰只圡圈严穬"));
      }
      finally
      {
        monitorexit;
      }
      label102: if ((paramLinkInfo.getPort() >= 1) && (paramLinkInfo.getPort() < 65535))
        continue;
      if (this._ != null)
        this._.error(this.aa, FM_Exception.getChars(6, 30, "钡掸别帪厧旣？佱優皉竤厪厰丈吋沔"));
      throw new InvalidParameterException(FM_Utils.copyValueOf(2, 118, "铳掦剩帼厵断｝佧典皗竦叼叢丆吉沂"));
      label167: if (paramLinkInfo.getTimeout() != -1)
      {
        this.p.setTimeout(paramLinkInfo.getTimeout());
        this.z = paramLinkInfo.getTimeout();
      }
      try
      {
        this.d = new Socket(paramLinkInfo.getAddress(), paramLinkInfo.getPort());
        if (this.d.getSoTimeout() == 0)
          this.d.setSoTimeout(this.z);
        this.e = new DataInputStream(this.d.getInputStream());
        this.f = new DataOutputStream(this.d.getOutputStream());
        if ((this.w) && (this.x))
          new Thread(this.p).start();
        this.v = true;
        this.ac = System.currentTimeMillis();
        continue;
        label314: if (paramLinkInfo != null)
          break label102;
        if (this._ != null)
          this._.warn(this.aa, CRCUtil.valueOf(4, "钤揮剬帾叮旹，伱內盗钺揰收捹串穣"));
        throw new InvalidParameterException(CRCUtil.valueOf(2, "钦揬剪常召旻２伯入盕钼揶攴捻丼穭"));
      }
      catch (IOException localIOException)
      {
        if (this._ != null)
        {
          this._.debug(this.aa, FM_Long.concat("\013jpha}o%s", 3) + paramLinkInfo.getAddress() + ":" + paramLinkInfo.getPort());
          this._.error(this.aa, Util4Java.getExceptionInfo(localIOException));
        }
      }
    }
    throw new SocketException(Util4Java.getExceptionInfo(localIOException));
  }

  public boolean disconnect()
    throws SocketException
  {
    if (this._ != null)
      this._.debug(this.aa, BCCUtil.endsWith("edjf~s'0\"9w+?3", 2, 12));
    this.v = false;
    this.p.end();
    try
    {
      if (this.f != null)
        this.f.close();
      try
      {
        if (this.e != null)
          this.e.close();
      }
      catch (IOException localIOException2)
      {
        try
        {
          while (true)
          {
            if (this.d != null)
              this.d.close();
            return true;
            localIOException2 = localIOException2;
            if (this._ == null)
              continue;
            this._.error(this.aa, Util4Java.getExceptionInfo(localIOException2));
          }
        }
        catch (IOException localIOException3)
        {
          while (true)
          {
            if (this._ == null)
              continue;
            this._.error(this.aa, Util4Java.getExceptionInfo(localIOException3));
          }
        }
      }
    }
    catch (IOException localIOException1)
    {
      while (true)
      {
        if (this._ == null)
          continue;
        this._.error(this.aa, Util4Java.getExceptionInfo(localIOException1));
      }
    }
  }

  public long getLastCalledTime()
  {
    return this.ac;
  }

  public Date getLastHeartBeat()
  {
    return null;
  }

  public byte[] getSessionNumber()
  {
    return this.n;
  }

  public long getSessionSerialNumber()
  {
    return this.m;
  }

  public boolean isConnect()
  {
    if ((this.v) && (System.currentTimeMillis() - this.ac >= 1200000L))
    {
      if (this._ != null)
        this._.debug(getClass().getName(), FM_Bytes.startsWith("(xm7fwg%bc$qg|:<th+ ", 122, 34));
      this.v = false;
    }
    return this.v;
  }

  public boolean isOpenSession()
  {
    return this.w;
  }

  public boolean lastRequestDataIsNull()
  {
    int i1 = 0;
    try
    {
      byte[] arrayOfByte = this.r;
      if (arrayOfByte == null)
        i1 = 1;
      label13: return i1;
    }
    catch (q localq)
    {
      break label13;
    }
  }

  /** @deprecated */
  public boolean openSession(TerminalInfo paramTerminalInfo, boolean paramBoolean)
    throws InvalidParameterException, SocketException, CommunicationException, OpenSessionException
  {
    monitorenter;
    OpenSessionResponse localOpenSessionResponse;
    try
    {
      if (this._ == null)
        this._ = LogFactory.getInstance().getLog();
      boolean bool = this.w;
      if (bool)
        while (true)
        {
          return true;
          byte[] arrayOfByte11 = localOpenSessionResponse.getSerialNumber();
          this.m = (FM_Bytes.bytesToLong(FM_Bytes.join(new byte[1], arrayOfByte11)) - 1L);
          this.n = localOpenSessionResponse.getSessionNumber();
          if (paramBoolean)
            new Thread(this.p).start();
          this.w = true;
          this.ac = System.currentTimeMillis();
          this.r = null;
        }
    }
    finally
    {
      monitorexit;
    }
    label229: 
    do
    {
      this.j = localOpenSessionResponse.getSessionKey();
      if (this.j != null)
      {
        int i1 = this.j.length;
        localOpenSessionResponse.getClass();
        if (i1 == 16)
          break;
      }
      throw new OpenSessionException(FM_Bytes.startsWith("笭刣斥？帠口咞庇盗伉讎寕银旳攛", 210, 64));
      byte[] arrayOfByte8;
      byte[] arrayOfByte9 = DES.decrypt4des3(this.h, arrayOfByte8);
      if (!FM_Bytes.isPatch4Des(arrayOfByte9))
      {
        if (this._ != null)
          this._.warn(this.aa, FM_Exception.getChars(1, 111, "帩厹咕廓敦捫'G72観寙吀１朦蠾伇"));
        throw new OpenSessionException(FM_CN.subSequence("幵句哉庇攲挿s\013;~觿寍吔ｅ杲蠢伛", 120));
        byte[] arrayOfByte2;
        byte[] arrayOfByte3;
        byte[] arrayOfByte4 = FM_Bytes.join(arrayOfByte2, arrayOfByte3);
        byte[] arrayOfByte5 = FM_Bytes.join(arrayOfByte4, CRCUtil.calculateCRC16(arrayOfByte4));
        do
          try
          {
            arrayOfByte6 = a(this.q.initDataSize(arrayOfByte5.length), arrayOfByte5);
            if (arrayOfByte6 == null)
            {
              if (this._ != null)
                this._.error(this.aa, CRCUtil.valueOf(2, "纐窦笤剻诫汏旨＃平厡咏廇攴捻丼穭＄笧剺奪赩"));
              throw new CommunicationException(Util4Java.toString("绊窯筠刬训氚无８帡厠哃庘攺挦丼穾ｎ笾刮奭赿", 3, 94));
            }
          }
          catch (Exception localException)
          {
            byte[] arrayOfByte6;
            this.v = false;
            throw new SocketException(Util4Java.getExceptionInfo(localException));
            ControlWord localControlWord2;
            if (paramTerminalInfo == null)
            {
              throw new InvalidParameterException(FM_Exception.getChars(3, 123, "纔窸第剽斾ｏ让氛攤挡害谤乺穡：筯刼夶货"));
              do
              {
                localMessageHead.fromBytes(Arrays.copyOf(arrayOfByte6, 12));
                localControlWord2 = localMessageHead.getControlWord();
                if (localControlWord2.getDirection() == ControlWord.Direction.RESPONSE)
                  break;
                CommunicationException localCommunicationException2 = new CommunicationException(Util4Java.toString("绝窸笧别诪氝旷／敳剷皍幸厽政捿丞智咚庍敫挳ｓ筿剳奴财", 182, 34));
                localCommunicationException2.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_DIRECTION);
                localCommunicationException2.setDirection(CommunicationException.CommandDirection.RESPONSE);
                throw localCommunicationException2;
              }
              while (arrayOfByte6.length >= 12);
              CommunicationException localCommunicationException1 = new CommunicationException(Util4Java.toString("绋窣笫刮诰汒斯ｎ敽判幮叶咂廌攱捤镬建么呆泂，筷剢奪赡", 4, 105));
              localCommunicationException1.setExceptionType(CommunicationException.CommunicationExceptionType.INVALID_FORMAT);
              localCommunicationException1.setDirection(CommunicationException.CommandDirection.RESPONSE);
              throw localCommunicationException1;
              if (localControlWord2.getReponseCode() != 14)
                break;
              OpenSessionException.OpenSessionExceptionType localOpenSessionExceptionType = OpenSessionException.OpenSessionExceptionType.instance(arrayOfByte8[0]);
              OpenSessionException localOpenSessionException = new OpenSessionException(localOpenSessionExceptionType.getDescription());
              localOpenSessionException.setExceptionType(localOpenSessionExceptionType);
              throw localOpenSessionException;
            }
            this.x = paramBoolean;
            ControlWord localControlWord1 = new ControlWord();
            localControlWord1.setDirection(ControlWord.Direction.REQUEST);
            localControlWord1.setType(ControlWord.MessageType.CONTROL);
            localControlWord1.setCommandType(ControlWord.CommandType.OPENSESSION);
            MessageHead localMessageHead = new MessageHead();
            localMessageHead.setProtocolVersion(17);
            byte[] arrayOfByte1 = new byte[2];
            arrayOfByte1[0] = (byte)MessageHead.SecurityLevel.PLAIN.getValue();
            arrayOfByte1[1] = (byte)MessageHead.CheckType.CRC16.getValue();
            localMessageHead.setSecurityLevel(arrayOfByte1);
            localMessageHead.setControlWord(localControlWord1);
            arrayOfByte2 = localMessageHead.toBytes();
            n localn = new n();
            localn.setTerminalType(paramTerminalInfo.getTerminalType());
            localn.setAppend(paramTerminalInfo.getAppend());
            localn.setKeyIndex(paramTerminalInfo.getKeyIndex());
            localn.setExponent(paramTerminalInfo.getExponent());
            localn.setModulus(paramTerminalInfo.getModulus());
            localn.setSecurityCode(paramTerminalInfo.getSecurityCode());
            localn.setTerminalNumber(paramTerminalInfo.getTerminalNumber());
            Random localRandom = new Random();
            this.i = new byte[8];
            localRandom.nextBytes(this.i);
            localn.setTerminalRandom(this.i);
            this.h = new byte[16];
            localRandom.nextBytes(this.h);
            localn.setTempKey(this.h);
            arrayOfByte3 = localn.toBytes();
            if (arrayOfByte3 != null)
              break label229;
            while (true)
            {
              throw new OpenSessionException(FM_Utils.copyValueOf(96, 104, "纃窼筥刳旽｟讬氁敻捽9,/j両穹ｇ笭别夲贮"));
              while (true)
              {
                arrayOfByte8 = Arrays.copyOfRange(arrayOfByte6, 12, -2 + arrayOfByte6.length);
                if ((localControlWord2.getReponseCode() != 0) && (localControlWord2.getReponseCode() != 14))
                {
                  CommunicationException.CommunicationExceptionType localCommunicationExceptionType = CommunicationException.CommunicationExceptionType.instance(localControlWord2.getReponseCode());
                  CommunicationException localCommunicationException3 = new CommunicationException(FM_CN.subSequence("筼刡奱赪d", 148) + localCommunicationExceptionType.getDescription());
                  localCommunicationException3.setExceptionType(localCommunicationExceptionType);
                  localCommunicationException3.setDirection(CommunicationException.CommandDirection.RESPONSE);
                  throw localCommunicationException3;
                  if (Arrays.equals(CRCUtil.calculateCRC16(Arrays.copyOf(arrayOfByte6, -2 + arrayOfByte6.length)), Arrays.copyOfRange(arrayOfByte6, -2 + arrayOfByte6.length, arrayOfByte6.length)))
                    continue;
                  if (this._ != null)
                    this._.warn(this.aa, FM_Utils.copyValueOf(154, 51, "绍窷筵刮讦汆斡＆筣剠哎廂敹捲\fP\026骄诚奿贤"));
                  throw new OpenSessionException(Util4Java.toString("纕竴筧剧订汑旧ｃ笳刻哄廓攵捭B\r^骗讘奦贰", 222, 94));
                  byte[] arrayOfByte7 = localMessageHead.getSecurityLevel();
                  if ((arrayOfByte7[0] == MessageHead.SecurityLevel.PLAIN.getValue()) && (arrayOfByte7[1] == MessageHead.CheckType.CRC16.getValue()))
                    break;
                  if (this._ != null)
                    this._.warn(this.aa, BCCUtil.endsWith("绌竩笶剺读汌斦～攢刦咕廎抹料盄宋公绡剣旪敄", 5, 34));
                  throw new OpenSessionException(Util4Java.toString("绉窡笥剸询汀旹ｐ敿剦咎庄抸斍盓寍儹纹删旸敍", 2, 109));
                }
              }
            }
          }
          catch (IOException localIOException)
          {
            this.v = false;
            if (this._ != null)
              this._.error(this.aa, FM_Exception.getChars(2, 103, "笥割晦＜罆纂冿珼弑帢；") + Util4Java.getExceptionInfo(localIOException));
            throw new SocketException(Util4Java.getExceptionInfo(localIOException));
          }
        while (arrayOfByte8.length >= 1);
        throw new CommunicationException(FM_Exception.getChars(320, 48, "幪厹七勨奝琏ｕ哄庍改捷卌伊丳稣％七勨夝瑏奨贬"));
      }
      byte[] arrayOfByte10 = FM_Bytes.byteRemovePatch4Des(arrayOfByte9);
      localOpenSessionResponse = new OpenSessionResponse();
      localOpenSessionResponse.fromBytes(arrayOfByte10);
    }
    while (Arrays.equals(localOpenSessionResponse.getTerminalRandom(), this.i));
    throw new OpenSessionException(Util4Java.toString("签刴旾＀幣古哕庈的隋朲敼丝昻绐竳厑冾盌雃杪攤", 1, 4));
  }

  public void registerCommunicationNotify(CommunicationNotify paramCommunicationNotify)
  {
    try
    {
      this.g = paramCommunicationNotify;
      label5: return;
    }
    catch (q localq)
    {
      break label5;
    }
  }

  public byte[] repeat()
    throws SocketException, CommunicationException
  {
    if (this._ == null)
      this._ = LogFactory.getInstance().getLog();
    a(true);
    if (this.r != null)
      try
      {
        byte[] arrayOfByte = a(this.q.initDataSize(this.r.length), this.r);
        this.r = null;
        this.ac = System.currentTimeMillis();
        a(false);
        return a(arrayOfByte, false);
      }
      catch (IOException localIOException)
      {
        if (this._ != null)
          this._.error(this.aa, Util4Java.toString("丞勦夎瑋旦｟叇遘処珯开幽", 5, 67) + Util4Java.getExceptionInfo(localIOException));
        this.v = false;
        throw new SocketException(Util4Java.getExceptionInfo(localIOException));
      }
      catch (Exception localException)
      {
        this.v = false;
        if (this._ != null)
          this._.warn(this.aa, FM_Exception.getChars(236, 12, "也勰奙琏旣－式歼厔遐冧珹彗帹") + Util4Java.getExceptionInfo(localException));
        throw new SocketException(Util4Java.getExceptionInfo(localException));
      }
    throw new CommunicationException(FM_Bytes.startsWith("泬朜霝覄醀厄皙敵捣", 332, 104));
  }

  public int repeatAsynchronous()
    throws InvalidParameterException
  {
    try
    {
      this.u = true;
      this.p.notify();
      label12: return 0;
    }
    catch (q localq)
    {
      break label12;
    }
  }

  /** @deprecated */
  // ERROR //
  public byte[] sendMessage(byte[] paramArrayOfByte)
    throws InvalidParameterException, SocketException, CommunicationException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   6: ifnonnull +13 -> 19
    //   9: aload_0
    //   10: invokestatic 105	cn/com/fmsh/util/log/LogFactory:getInstance	()Lcn/com/fmsh/util/log/LogFactory;
    //   13: invokevirtual 109	cn/com/fmsh/util/log/LogFactory:getLog	()Lcn/com/fmsh/util/log/FMLog;
    //   16: putfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   19: aload_0
    //   20: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   23: ifnull +42 -> 65
    //   26: aload_0
    //   27: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   30: aload_0
    //   31: getfield 87	cn/com/fmsh/communication/core/TerminalCommunicationHandler:aa	Ljava/lang/String;
    //   34: new 351	java/lang/StringBuilder
    //   37: dup
    //   38: ldc_w 854
    //   41: iconst_2
    //   42: bipush 26
    //   44: invokestatic 456	cn/com/fmsh/util/Util4Java:toString	(Ljava/lang/String;II)Ljava/lang/String;
    //   47: invokespecial 354	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   50: aload_1
    //   51: invokestatic 360	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
    //   54: invokevirtual 364	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 377	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokeinterface 413 3 0
    //   65: aload_1
    //   66: ifnull +9 -> 75
    //   69: aload_1
    //   70: arraylength
    //   71: iconst_1
    //   72: if_icmpge +137 -> 209
    //   75: aload_0
    //   76: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   79: ifnull +23 -> 102
    //   82: aload_0
    //   83: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   86: aload_0
    //   87: getfield 87	cn/com/fmsh/communication/core/TerminalCommunicationHandler:aa	Ljava/lang/String;
    //   90: iconst_4
    //   91: ldc_w 856
    //   94: invokestatic 199	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
    //   97: invokeinterface 139 3 0
    //   102: new 542	cn/com/fmsh/exception/InvalidParameterException
    //   105: dup
    //   106: ldc_w 858
    //   109: iconst_4
    //   110: invokestatic 159	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
    //   113: invokespecial 615	cn/com/fmsh/exception/InvalidParameterException:<init>	(Ljava/lang/String;)V
    //   116: athrow
    //   117: astore_2
    //   118: aload_0
    //   119: monitorexit
    //   120: aload_2
    //   121: athrow
    //   122: astore 13
    //   124: aload_0
    //   125: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   128: ifnull +25 -> 153
    //   131: aload_0
    //   132: getfield 79	cn/com/fmsh/communication/core/TerminalCommunicationHandler:_	Lcn/com/fmsh/util/log/FMLog;
    //   135: aload_0
    //   136: getfield 87	cn/com/fmsh/communication/core/TerminalCommunicationHandler:aa	Ljava/lang/String;
    //   139: iconst_2
    //   140: bipush 120
    //   142: ldc_w 860
    //   145: invokestatic 384	cn/com/fmsh/util/FM_Utils:copyValueOf	(IILjava/lang/String;)Ljava/lang/String;
    //   148: invokeinterface 139 3 0
    //   153: ldc2_w 861
    //   156: invokestatic 865	java/lang/Thread:sleep	(J)V
    //   159: aload_0
    //   160: aload_0
    //   161: getfield 99	cn/com/fmsh/communication/core/TerminalCommunicationHandler:q	Lcn/com/fmsh/util/socket/DataLengthHandle;
    //   164: aload 9
    //   166: arraylength
    //   167: invokeinterface 571 2 0
    //   172: aload 9
    //   174: invokespecial 277	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	([B[B)[B
    //   177: astore 15
    //   179: aload 15
    //   181: astore 11
    //   183: aload_0
    //   184: invokestatic 670	java/lang/System:currentTimeMillis	()J
    //   187: putfield 89	cn/com/fmsh/communication/core/TerminalCommunicationHandler:ac	J
    //   190: aload_0
    //   191: iconst_0
    //   192: invokevirtual 841	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Z)V
    //   195: aload_0
    //   196: aload 11
    //   198: iconst_0
    //   199: invokespecial 269	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	([BZ)[B
    //   202: astore 12
    //   204: aload_0
    //   205: monitorexit
    //   206: aload 12
    //   208: areturn
    //   209: new 141	cn/com/fmsh/communication/core/ControlWord
    //   212: dup
    //   213: invokespecial 167	cn/com/fmsh/communication/core/ControlWord:<init>	()V
    //   216: astore_3
    //   217: aload_3
    //   218: getstatic 203	cn/com/fmsh/communication/core/ControlWord$Direction:REQUEST	Lcn/com/fmsh/communication/core/ControlWord$Direction;
    //   221: invokevirtual 207	cn/com/fmsh/communication/core/ControlWord:setDirection	(Lcn/com/fmsh/communication/core/ControlWord$Direction;)V
    //   224: aload_3
    //   225: getstatic 868	cn/com/fmsh/communication/core/ControlWord$MessageType:BUSINESS	Lcn/com/fmsh/communication/core/ControlWord$MessageType;
    //   228: invokevirtual 217	cn/com/fmsh/communication/core/ControlWord:setType	(Lcn/com/fmsh/communication/core/ControlWord$MessageType;)V
    //   231: new 165	cn/com/fmsh/communication/core/MessageHead
    //   234: dup
    //   235: invokespecial 166	cn/com/fmsh/communication/core/MessageHead:<init>	()V
    //   238: astore 4
    //   240: aload 4
    //   242: bipush 17
    //   244: invokevirtual 225	cn/com/fmsh/communication/core/MessageHead:setProtocolVersion	(B)V
    //   247: iconst_2
    //   248: newarray byte
    //   250: astore 5
    //   252: aload 5
    //   254: iconst_0
    //   255: getstatic 312	cn/com/fmsh/communication/core/MessageHead$SecurityLevel:CIPHER	Lcn/com/fmsh/communication/core/MessageHead$SecurityLevel;
    //   258: invokevirtual 235	cn/com/fmsh/communication/core/MessageHead$SecurityLevel:getValue	()I
    //   261: i2b
    //   262: bastore
    //   263: aload 5
    //   265: iconst_1
    //   266: getstatic 315	cn/com/fmsh/communication/core/MessageHead$CheckType:MAC	Lcn/com/fmsh/communication/core/MessageHead$CheckType;
    //   269: invokevirtual 242	cn/com/fmsh/communication/core/MessageHead$CheckType:getValue	()I
    //   272: i2b
    //   273: bastore
    //   274: aload 4
    //   276: aload 5
    //   278: invokevirtual 245	cn/com/fmsh/communication/core/MessageHead:setSecurityLevel	([B)V
    //   281: aload 4
    //   283: aload_3
    //   284: invokevirtual 249	cn/com/fmsh/communication/core/MessageHead:setControlWord	(Lcn/com/fmsh/communication/core/ControlWord;)V
    //   287: aload_0
    //   288: invokespecial 559	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	()V
    //   291: aload 4
    //   293: aload_0
    //   294: getfield 444	cn/com/fmsh/communication/core/TerminalCommunicationHandler:o	J
    //   297: invokevirtual 255	cn/com/fmsh/communication/core/MessageHead:setSerialNumber	(J)V
    //   300: aload 4
    //   302: aload_0
    //   303: getfield 113	cn/com/fmsh/communication/core/TerminalCommunicationHandler:n	[B
    //   306: invokevirtual 258	cn/com/fmsh/communication/core/MessageHead:setSessionNumber	([B)V
    //   309: aload 4
    //   311: invokevirtual 261	cn/com/fmsh/communication/core/MessageHead:toBytes	()[B
    //   314: astore 6
    //   316: aload_1
    //   317: invokestatic 871	cn/com/fmsh/util/FM_Bytes:bytePatch4Des	([B)[B
    //   320: astore 7
    //   322: aload 6
    //   324: aload_0
    //   325: getfield 111	cn/com/fmsh/communication/core/TerminalCommunicationHandler:j	[B
    //   328: aload 7
    //   330: invokestatic 874	cn/com/fmsh/util/algorithm/DES:encrypt4des3	([B[B)[B
    //   333: invokestatic 494	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
    //   336: astore 8
    //   338: aload 8
    //   340: aload_0
    //   341: getfield 111	cn/com/fmsh/communication/core/TerminalCommunicationHandler:j	[B
    //   344: aload_0
    //   345: getfield 67	cn/com/fmsh/communication/core/TerminalCommunicationHandler:k	I
    //   348: aload_0
    //   349: getfield 69	cn/com/fmsh/communication/core/TerminalCommunicationHandler:l	I
    //   352: invokestatic 339	java/util/Arrays:copyOfRange	([BII)[B
    //   355: bipush 8
    //   357: newarray byte
    //   359: aload 8
    //   361: invokestatic 345	cn/com/fmsh/util/algorithm/MAC:calculateMAC4DES	([B[B[B)[B
    //   364: iconst_4
    //   365: invokestatic 173	java/util/Arrays:copyOf	([BI)[B
    //   368: invokestatic 494	cn/com/fmsh/util/FM_Bytes:join	([B[B)[B
    //   371: astore 9
    //   373: aload_0
    //   374: aload 9
    //   376: putfield 534	cn/com/fmsh/communication/core/TerminalCommunicationHandler:r	[B
    //   379: aload_0
    //   380: iconst_1
    //   381: invokevirtual 841	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Z)V
    //   384: aload_0
    //   385: aload_0
    //   386: getfield 99	cn/com/fmsh/communication/core/TerminalCommunicationHandler:q	Lcn/com/fmsh/util/socket/DataLengthHandle;
    //   389: aload 9
    //   391: arraylength
    //   392: invokeinterface 571 2 0
    //   397: aload 9
    //   399: invokespecial 277	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	([B[B)[B
    //   402: astore 10
    //   404: aload 10
    //   406: astore 11
    //   408: goto -225 -> 183
    //   411: astore 14
    //   413: aload_0
    //   414: iconst_0
    //   415: putfield 73	cn/com/fmsh/communication/core/TerminalCommunicationHandler:v	Z
    //   418: new 266	cn/com/fmsh/communication/exception/SocketException
    //   421: dup
    //   422: aload 14
    //   424: invokestatic 592	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
    //   427: invokespecial 593	cn/com/fmsh/communication/exception/SocketException:<init>	(Ljava/lang/String;)V
    //   430: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	117	117	finally
    //   124	153	117	finally
    //   153	179	117	finally
    //   183	204	117	finally
    //   209	384	117	finally
    //   384	404	117	finally
    //   413	431	117	finally
    //   384	404	122	java/lang/Exception
    //   153	179	411	java/lang/Exception
  }

  public int sendMessageAsynchronous(byte[] paramArrayOfByte)
    throws InvalidParameterException
  {
    try
    {
      this.s.addFirst(paramArrayOfByte);
      this.u = true;
      this.p.notify();
      label20: return 0;
    }
    catch (q localq)
    {
      break label20;
    }
  }

  public void setExceptionTryCount(int paramInt)
  {
    if (paramInt <= 0);
    while (true)
    {
      return;
      this.a = paramInt;
    }
  }

  public void setInterval4Heartbeat(int paramInt)
  {
    if (paramInt <= 0);
    while (true)
    {
      return;
      this.b = paramInt;
    }
  }

  public void setLastCalledTime(long paramLong)
  {
    try
    {
      this.ac = paramLong;
      label5: return;
    }
    catch (q localq)
    {
      break label5;
    }
  }

  public void setTimeout(int paramInt)
  {
    if (paramInt > 0)
      this.z = paramInt;
  }

  private class a
    implements Runnable
  {
    public int c;

    /** @deprecated */
    // ERROR //
    public void end()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iconst_1
      //   4: putfield 25	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:a	Z
      //   7: aload_0
      //   8: iconst_0
      //   9: putfield 27	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:b	Z
      //   12: aload_0
      //   13: invokevirtual 43	java/lang/Object:notifyAll	()V
      //   16: aload_0
      //   17: monitorexit
      //   18: return
      //   19: astore_2
      //   20: aload_0
      //   21: monitorexit
      //   22: aload_2
      //   23: athrow
      //   24: astore_1
      //   25: goto -9 -> 16
      //
      // Exception table:
      //   from	to	target	type
      //   2	16	19	finally
      //   2	16	24	q
    }

    /** @deprecated */
    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_1
      //   2: aload_0
      //   3: monitorenter
      //   4: aload_0
      //   5: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   8: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   11: ifnull +762 -> 773
      //   14: aload_0
      //   15: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   18: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   21: aload_0
      //   22: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   25: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   28: ldc 58
      //   30: iconst_5
      //   31: bipush 94
      //   33: invokestatic 64	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   36: invokeinterface 70 3 0
      //   41: goto +732 -> 773
      //   44: aload_0
      //   45: getfield 25	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:a	Z
      //   48: istore 4
      //   50: iload 4
      //   52: ifeq +536 -> 588
      //   55: aload_0
      //   56: monitorexit
      //   57: return
      //   58: aload_1
      //   59: ifnonnull +676 -> 735
      //   62: aload_0
      //   63: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   66: invokestatic 74	cn/com/fmsh/communication/core/TerminalCommunicationHandler:h	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)[B
      //   69: astore 13
      //   71: aload_0
      //   72: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   75: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   78: ifnull +48 -> 126
      //   81: aload_0
      //   82: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   85: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   88: aload_0
      //   89: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   92: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   95: new 76	java/lang/StringBuilder
      //   98: dup
      //   99: sipush 248
      //   102: ldc 78
      //   104: invokestatic 84	cn/com/fmsh/util/CRCUtil:valueOf	(ILjava/lang/String;)Ljava/lang/String;
      //   107: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   110: aload 13
      //   112: invokestatic 91	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
      //   115: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   118: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   121: invokeinterface 70 3 0
      //   126: aload_0
      //   127: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   130: astore 19
      //   132: aload 19
      //   134: aload 19
      //   136: invokestatic 103	cn/com/fmsh/communication/core/TerminalCommunicationHandler:i	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/socket/DataLengthHandle;
      //   139: aload 13
      //   141: arraylength
      //   142: invokeinterface 109 2 0
      //   147: aload 13
      //   149: invokestatic 112	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;[B[B)[B
      //   152: astore_3
      //   153: aload_0
      //   154: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   157: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   160: ifnull +607 -> 767
      //   163: aload_0
      //   164: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   167: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   170: aload_0
      //   171: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   174: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   177: new 76	java/lang/StringBuilder
      //   180: dup
      //   181: bipush 6
      //   183: ldc 114
      //   185: invokestatic 119	cn/com/fmsh/util/FM_Int:lastIndexOf	(ILjava/lang/String;)Ljava/lang/String;
      //   188: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   191: aload_3
      //   192: invokestatic 91	cn/com/fmsh/util/FM_Bytes:bytesToHexString	([B)Ljava/lang/String;
      //   195: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   198: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   201: invokeinterface 70 3 0
      //   206: aload_3
      //   207: astore 15
      //   209: aload 15
      //   211: ifnonnull +432 -> 643
      //   214: aload_0
      //   215: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   218: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   221: ifnull +28 -> 249
      //   224: aload_0
      //   225: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   228: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   231: aload_0
      //   232: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   235: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   238: ldc 121
      //   240: iconst_2
      //   241: invokestatic 127	cn/com/fmsh/util/FM_CN:subSequence	(Ljava/lang/String;I)Ljava/lang/String;
      //   244: invokeinterface 130 3 0
      //   249: aload_0
      //   250: iconst_1
      //   251: aload_0
      //   252: getfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   255: iadd
      //   256: putfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   259: aload_0
      //   260: getfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   263: aload_0
      //   264: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   267: invokestatic 133	cn/com/fmsh/communication/core/TerminalCommunicationHandler:j	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)I
      //   270: if_icmplt +483 -> 753
      //   273: aload_0
      //   274: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   277: invokestatic 137	cn/com/fmsh/communication/core/TerminalCommunicationHandler:g	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/communication/CommunicationNotify;
      //   280: ifnull +15 -> 295
      //   283: aload_0
      //   284: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   287: invokestatic 137	cn/com/fmsh/communication/core/TerminalCommunicationHandler:g	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/communication/CommunicationNotify;
      //   290: invokeinterface 142 1 0
      //   295: aload_0
      //   296: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   299: iconst_0
      //   300: invokestatic 145	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;Z)V
      //   303: aload_0
      //   304: iconst_1
      //   305: putfield 25	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:a	Z
      //   308: aload 13
      //   310: astore 17
      //   312: aload 15
      //   314: astore 5
      //   316: aload 17
      //   318: astore_1
      //   319: aload_0
      //   320: getfield 25	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:a	Z
      //   323: istore 6
      //   325: iload 6
      //   327: ifne +420 -> 747
      //   330: aload_0
      //   331: aload_0
      //   332: getfield 34	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:d	I
      //   335: i2l
      //   336: invokevirtual 149	java/lang/Object:wait	(J)V
      //   339: aload 5
      //   341: astore_3
      //   342: goto -298 -> 44
      //   345: astore 12
      //   347: aload_0
      //   348: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   351: iconst_0
      //   352: invokestatic 151	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;Z)V
      //   355: aload 12
      //   357: athrow
      //   358: astore_2
      //   359: aload_0
      //   360: monitorexit
      //   361: aload_2
      //   362: athrow
      //   363: astore 18
      //   365: aload_0
      //   366: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   369: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   372: ifnull +46 -> 418
      //   375: aload_0
      //   376: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   379: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   382: aload_0
      //   383: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   386: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   389: new 76	java/lang/StringBuilder
      //   392: dup
      //   393: ldc 153
      //   395: iconst_1
      //   396: invokestatic 158	cn/com/fmsh/util/FM_Long:concat	(Ljava/lang/String;I)Ljava/lang/String;
      //   399: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   402: aload 18
      //   404: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   407: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   410: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   413: invokeinterface 130 3 0
      //   418: aload_0
      //   419: iconst_1
      //   420: aload_0
      //   421: getfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   424: iadd
      //   425: putfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   428: aload_3
      //   429: astore 15
      //   431: goto -222 -> 209
      //   434: astore 14
      //   436: aload_0
      //   437: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   440: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   443: ifnull +50 -> 493
      //   446: aload_0
      //   447: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   450: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   453: aload_0
      //   454: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   457: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   460: new 76	java/lang/StringBuilder
      //   463: dup
      //   464: sipush 198
      //   467: bipush 12
      //   469: ldc 166
      //   471: invokestatic 172	cn/com/fmsh/FM_Exception:getChars	(IILjava/lang/String;)Ljava/lang/String;
      //   474: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   477: aload 14
      //   479: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   482: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   485: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   488: invokeinterface 130 3 0
      //   493: aload_0
      //   494: iconst_1
      //   495: aload_0
      //   496: getfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   499: iadd
      //   500: putfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   503: aload_3
      //   504: astore 15
      //   506: goto -297 -> 209
      //   509: astore 11
      //   511: aload_3
      //   512: astore 5
      //   514: aload_0
      //   515: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   518: iconst_0
      //   519: invokestatic 145	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;Z)V
      //   522: aload_0
      //   523: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   526: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   529: ifnull +48 -> 577
      //   532: aload_0
      //   533: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   536: invokestatic 53	cn/com/fmsh/communication/core/TerminalCommunicationHandler:b	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/util/log/FMLog;
      //   539: aload_0
      //   540: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   543: invokestatic 56	cn/com/fmsh/communication/core/TerminalCommunicationHandler:c	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/lang/String;
      //   546: new 76	java/lang/StringBuilder
      //   549: dup
      //   550: ldc 174
      //   552: iconst_2
      //   553: bipush 71
      //   555: invokestatic 64	cn/com/fmsh/util/FM_Bytes:startsWith	(Ljava/lang/String;II)Ljava/lang/String;
      //   558: invokespecial 87	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   561: aload 11
      //   563: invokestatic 164	cn/com/fmsh/util/Util4Java:getExceptionInfo	(Ljava/lang/Exception;)Ljava/lang/String;
      //   566: invokevirtual 95	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   569: invokevirtual 99	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   572: invokeinterface 130 3 0
      //   577: aload_0
      //   578: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   581: iconst_0
      //   582: invokestatic 151	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;Z)V
      //   585: goto -266 -> 319
      //   588: aload_0
      //   589: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   592: invokestatic 177	cn/com/fmsh/communication/core/TerminalCommunicationHandler:d	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Z
      //   595: ifne +146 -> 741
      //   598: aload_0
      //   599: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   602: invokestatic 179	cn/com/fmsh/communication/core/TerminalCommunicationHandler:e	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Z
      //   605: istore 8
      //   607: iload 8
      //   609: ifeq -551 -> 58
      //   612: aload_0
      //   613: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   616: invokestatic 183	cn/com/fmsh/communication/core/TerminalCommunicationHandler:f	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/util/LinkedList;
      //   619: invokevirtual 189	java/util/LinkedList:isEmpty	()Z
      //   622: istore 9
      //   624: iload 9
      //   626: ifeq +55 -> 681
      //   629: aload_0
      //   630: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   633: iconst_0
      //   634: invokestatic 151	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;Z)V
      //   637: aload_3
      //   638: astore 5
      //   640: goto -321 -> 319
      //   643: aload_0
      //   644: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   647: aload 15
      //   649: invokestatic 192	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;[B)Z
      //   652: ifne -393 -> 259
      //   655: aload_0
      //   656: iconst_1
      //   657: aload_0
      //   658: getfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   661: iadd
      //   662: putfield 29	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:c	I
      //   665: goto -406 -> 259
      //   668: astore 7
      //   670: aload 7
      //   672: invokevirtual 195	java/lang/InterruptedException:printStackTrace	()V
      //   675: aload 5
      //   677: astore_3
      //   678: goto -634 -> 44
      //   681: aload_0
      //   682: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   685: invokestatic 183	cn/com/fmsh/communication/core/TerminalCommunicationHandler:f	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Ljava/util/LinkedList;
      //   688: invokevirtual 199	java/util/LinkedList:removeLast	()Ljava/lang/Object;
      //   691: checkcast 201	[B
      //   694: astore 10
      //   696: aload 10
      //   698: ifnull -86 -> 612
      //   701: aload_0
      //   702: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   705: aload 10
      //   707: invokevirtual 205	cn/com/fmsh/communication/core/TerminalCommunicationHandler:sendMessage	([B)[B
      //   710: astore_3
      //   711: aload_0
      //   712: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   715: invokestatic 137	cn/com/fmsh/communication/core/TerminalCommunicationHandler:g	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;)Lcn/com/fmsh/communication/CommunicationNotify;
      //   718: aload_0
      //   719: getfield 20	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:e	Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;
      //   722: aload_3
      //   723: iconst_1
      //   724: invokestatic 208	cn/com/fmsh/communication/core/TerminalCommunicationHandler:a	(Lcn/com/fmsh/communication/core/TerminalCommunicationHandler;[BZ)[B
      //   727: invokeinterface 212 2 0
      //   732: goto -120 -> 612
      //   735: aload_1
      //   736: astore 13
      //   738: goto -667 -> 71
      //   741: aload_3
      //   742: astore 5
      //   744: goto -425 -> 319
      //   747: aload 5
      //   749: astore_3
      //   750: goto -706 -> 44
      //   753: aload 13
      //   755: astore 16
      //   757: aload 15
      //   759: astore 5
      //   761: aload 16
      //   763: astore_1
      //   764: goto -445 -> 319
      //   767: aload_3
      //   768: astore 15
      //   770: goto -561 -> 209
      //   773: aconst_null
      //   774: astore_3
      //   775: goto -731 -> 44
      //
      // Exception table:
      //   from	to	target	type
      //   514	577	345	finally
      //   612	624	345	finally
      //   681	732	345	finally
      //   4	50	358	finally
      //   62	126	358	finally
      //   126	206	358	finally
      //   214	325	358	finally
      //   330	339	358	finally
      //   347	358	358	finally
      //   365	503	358	finally
      //   577	607	358	finally
      //   629	675	358	finally
      //   126	206	363	java/io/IOException
      //   126	206	434	java/lang/Exception
      //   612	624	509	java/lang/Exception
      //   681	732	509	java/lang/Exception
      //   330	339	668	java/lang/InterruptedException
    }

    /** @deprecated */
    // ERROR //
    public void setTimeout(int paramInt)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iload_1
      //   4: putfield 34	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:d	I
      //   7: aload_0
      //   8: iconst_0
      //   9: putfield 27	cn/com/fmsh/communication/core/TerminalCommunicationHandler$a:b	Z
      //   12: aload_0
      //   13: invokevirtual 43	java/lang/Object:notifyAll	()V
      //   16: aload_0
      //   17: monitorexit
      //   18: return
      //   19: astore_3
      //   20: aload_0
      //   21: monitorexit
      //   22: aload_3
      //   23: athrow
      //   24: astore_2
      //   25: goto -9 -> 16
      //
      // Exception table:
      //   from	to	target	type
      //   2	16	19	finally
      //   2	16	24	q
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.core.TerminalCommunicationHandler
 * JD-Core Version:    0.6.0
 */