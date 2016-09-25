package cn.com.fmsh.communication.message.core;

import am;
import an;
import ap;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.TLVParse;
import cn.com.fmsh.communication.message.TLVParse.TagEntry;
import cn.com.fmsh.communication.message.enumerate.ETagType;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.communication.message.tagvalue.HandlerFactory;
import cn.com.fmsh.communication.message.tagvalue.StringValueHandler;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;
import cn.com.fmsh.util.FM_Utils;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Tag
  implements ITag
{
  public Tag(MessageHandler paramMessageHandler)
  {
    this.g = paramMessageHandler;
  }

  public Tag(MessageHandler paramMessageHandler, byte paramByte)
  {
    this.g = paramMessageHandler;
    this.c = paramByte;
    this.i = this.g.getTagDefine(paramByte);
  }

  public int addValue(int paramInt)
    throws FMCommunicationMessageException
  {
    try
    {
      if (this.h)
      {
        String str3 = FM_Bytes.startsWith("绝S\013\nK6\016\004赗倣斴）语\037/v叾讬", 3, 35);
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str3, arrayOfObject3));
      }
      if (this.i == null)
      {
        String str2 = FM_Bytes.startsWith("络\026\036[\0023\013M贆倶斱（菶又\017Y\022籩埄夽赬", 4, 61);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str2, arrayOfObject2));
      }
      while (true)
      {
        this.d = FM_Bytes.intToBytes(paramInt, this.i.getLength());
        break;
        this.e = this.i.getType();
        if (this.e == ETagType.I)
          continue;
        String str1 = FM_Utils.copyValueOf(2, 89, "绔\022^_\no[\001贞倲旱ｌ伹具\037\005Z偪盋米垊冠锊");
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str1, arrayOfObject1));
      }
    }
    catch (am localam)
    {
    }
    return 0;
  }

  public int addValue(ITag paramITag)
    throws FMCommunicationMessageException
  {
    int k;
    int j;
    int m;
    if (paramITag == null)
    {
      String str5 = FM_CN.subSequence("绊VP\007\024{\025\001赀倦旿４佧儳盁��BU主穪", 5);
      Object[] arrayOfObject5 = new Object[1];
      arrayOfObject5[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str5, arrayOfObject5));
      k++;
      if (k >= j)
      {
        m = 0;
        label57: if (m == 0)
          break label190;
        this.f.add(paramITag);
        return 0;
      }
    }
    ap[] arrayOfap;
    do
    {
      if (this.i != null)
        break label252;
      String str4 = FM_Long.concat("纇]UXQ0\030\026贝偽斺｛莵叛LBI籢埏奾赿", 6);
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str4, arrayOfObject4));
      if (arrayOfap[k].getTag() != paramITag.getId())
        break;
      m = 1;
      break label57;
    }
    while (!this.h);
    String str1 = FM_Long.concat("绛YYDU<\034\022贑偹斦ｗ诣E}`司详", 266);
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Byte.valueOf(this.c);
    throw new FMCommunicationMessageException(String.format(str1, arrayOfObject1));
    label190: String str3 = FM_CN.subSequence("终TNY\026y\023\007赂値旱：佥儱\027\023F0栘诈丐呄沎", 3);
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = Byte.valueOf(paramITag.getId());
    throw new FMCommunicationMessageException(String.format(str3, arrayOfObject3));
    label252: 
    do
    {
      arrayOfap = this.i.getTagItemDefines();
      j = arrayOfap.length;
      k = 0;
      break;
      this.e = this.i.getType();
    }
    while (this.e == ETagType.C);
    String str2 = FM_Long.concat("纂RP[\\7E\025贘偢斿ｘ使兯AAL倪盅簷埜冸镔", 3);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Byte.valueOf(this.c);
    throw new FMCommunicationMessageException(String.format(str2, arrayOfObject2));
  }

  public int addValue(String paramString)
    throws FMCommunicationMessageException
  {
    if (this.h)
    {
      String str3 = Util4Java.toString("纀N\032[\006;\007]贊倾斵（讠R&o厣诱", 218, 65);
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str3, arrayOfObject3));
    }
    if (this.i == null)
    {
      String str2 = Util4Java.toString("给[\037JGnB\024贓倻斠）莣厕F��\027籤埅奬贩", 1, 47);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str2, arrayOfObject2));
    }
    StringValueHandler localStringValueHandler;
    do
    {
      String str1 = FM_Bytes.startsWith("绝\020E\003_a\\\031赏偸旲ｈ莳厒P\005C簿垏义同沑", 3, 64);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str1, arrayOfObject1));
      this.e = this.i.getType();
      localStringValueHandler = HandlerFactory.instance().getStringValueHandle(this.e);
    }
    while (localStringValueHandler == null);
    this.d = localStringValueHandler.setTagValue(paramString);
    return 0;
  }

  public int addValue(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    if (this.h)
    {
      String str4 = FM_Utils.copyValueOf(2, 115, "绔T\022\001\002iGO赎値旽ｒ讴\0206-号诫");
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str4, arrayOfObject4));
    }
    do
    {
      this.e = this.i.getType();
      if (this.e != ETagType.B)
      {
        String str3 = BCCUtil.endsWith("绛\016SM\031?J\027贉倦斤ｆ伢儿\006KE偦皖籱埉几锋", 3, 88);
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str3, arrayOfObject3));
      }
      do
      {
        this.d = paramArrayOfByte;
        return 0;
      }
      while ((this.i.getLength() == 0) || (paramArrayOfByte.length <= this.i.getLength()));
      String str2 = FM_Utils.copyValueOf(38, 44, "终\tHR\032(A\030赚倡斿９佡儨M\004\026倡盍锪座乀呑泐");
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str2, arrayOfObject2));
    }
    while (this.i != null);
    String str1 = CRCUtil.valueOf(3, "纀\036\032\013F+GM赊偮斵ｘ菲叀SYN簡埀奭赨");
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Byte.valueOf(this.c);
    throw new FMCommunicationMessageException(String.format(str1, arrayOfObject1));
  }

  public int clear()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    int j = 0;
    if (paramObject == null);
    while (true)
    {
      return j;
      try
      {
        if (!(paramObject instanceof Tag))
          continue;
        Tag localTag = (Tag)paramObject;
        int k = this.c;
        int m = localTag.c;
        if (k != m)
          continue;
        j = 1;
      }
      catch (am localam)
      {
      }
    }
  }

  public int fromPackageBody(byte paramByte, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    int j = -2;
    this.c = paramByte;
    this.h = true;
    this.i = this.g.getTagDefine(paramByte);
    if (this.i == null)
      if (this.a != null)
      {
        FMLog localFMLog2 = this.a;
        String str3 = this.b;
        String str4 = FM_Utils.copyValueOf(288, 58, "圣鄈罱旞以习札寛丒A\016N\0308\017L");
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Byte.valueOf(paramByte);
        localFMLog2.warn(str3, String.format(str4, arrayOfObject2));
      }
    while (true)
    {
      return j;
      this.e = this.i.getType();
      int k = paramArrayOfByte.length;
      if ((this.i.getLength() == 0) || (k <= this.i.getLength()))
        break;
      if (this.a == null)
        continue;
      FMLog localFMLog1 = this.a;
      String str1 = this.b;
      String str2 = CRCUtil.valueOf(5, "\017\r\032\025:H\\守乊盐锺廰哋敨捧练皏锣廫乓乏膴");
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Byte.valueOf(paramByte);
      localFMLog1.warn(str1, String.format(str2, arrayOfObject1));
    }
    this.d = paramArrayOfByte;
    Iterator localIterator;
    if (ETagType.C == this.e)
      localIterator = TLVParse.intance().parse(this.d, 1).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        j = 0;
        break;
      }
      TLVParse.TagEntry localTagEntry = (TLVParse.TagEntry)localIterator.next();
      Tag localTag = new Tag(this.g);
      localTag.fromPackageBody(localTagEntry.getTag()[0], localTagEntry.getData());
      if (!localTag.isValid())
        continue;
      this.f.add(localTag);
    }
  }

  public int fromPackageBody(byte[] paramArrayOfByte)
  {
    int j = 2;
    try
    {
      this.h = true;
      if (paramArrayOfByte != null)
      {
        int m;
        if (paramArrayOfByte.length < j)
        {
          break label334;
          if ((this.i.getLength() != 0) && (m > this.i.getLength()))
          {
            k = -2;
            break label338;
          }
        }
        an localan;
        do
        {
          this.e = this.i.getType();
          m = 0xFF & paramArrayOfByte[1];
          byte[] arrayOfByte = new byte[2];
          if (m == 255)
          {
            j = 4;
            arrayOfByte[0] = paramArrayOfByte[2];
            arrayOfByte[1] = paramArrayOfByte[3];
            m = FM_Bytes.bytesToInt(arrayOfByte);
          }
          if (paramArrayOfByte.length >= j + m)
            break;
          k = -1;
          break label338;
          if (j + m > paramArrayOfByte.length)
          {
            if (this.a == null)
              break label341;
            FMLog localFMLog = this.a;
            String str1 = this.b;
            StringBuilder localStringBuilder = new StringBuilder(BCCUtil.endsWith("敯捫镴廷\f", 192, 102)).append(paramArrayOfByte.length).append(BCCUtil.endsWith("\016挗寗尅仉整振挙寁镧廳I", 244, 61));
            String str2 = FM_Long.concat("}\033", 352);
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(m);
            localFMLog.warn(str1, String.format(str2, arrayOfObject) + CRCUtil.valueOf(5, "\006l哑放捱镯座孅芁廛剒盒锸庾R") + paramArrayOfByte.length + CRCUtil.valueOf(6, "\001品"));
            break label341;
          }
          this.d = Arrays.copyOfRange(paramArrayOfByte, j, m + j);
          k = -1;
          break label338;
          this.c = paramArrayOfByte[0];
          this.i = this.g.getTagDefine(this.c);
          localan = this.i;
        }
        while (localan != null);
        k = -2;
      }
    }
    catch (am localam)
    {
      k = 0;
    }
    label334: label338: label341: for (int k = -1; ; k = -3)
      return k;
  }

  public byte[] getBytesVal()
    throws FMCommunicationMessageException
  {
    byte[] arrayOfByte = null;
    try
    {
      if (ETagType.B == this.e)
        arrayOfByte = this.d;
      label17: return arrayOfByte;
    }
    catch (am localam)
    {
      break label17;
    }
  }

  public byte getId()
  {
    return this.c;
  }

  public int getIntVal()
    throws FMCommunicationMessageException
  {
    try
    {
      int k;
      if (ETagType.I == this.e)
        k = FM_Bytes.bytesToInt(this.d);
      for (j = k; ; j = -1)
        return j;
    }
    catch (am localam)
    {
      while (true)
        int j = 0;
    }
  }

  public byte[] getItemBytesVal(int paramInt)
    throws FMCommunicationMessageException
  {
    if (paramInt >= 0);
    byte[] arrayOfByte1;
    try
    {
      if (paramInt > this.f.size())
      {
        String str = FM_Int.lastIndexOf(252, "菸历奜朐\00752\rr��\004盞卞呷\t?8的倽旴／伤兠皂丌栏趃畆");
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str, arrayOfObject));
      }
      byte[] arrayOfByte2 = ((ITag)this.f.get(paramInt)).getBytesVal();
      arrayOfByte1 = arrayOfByte2;
    }
    catch (am localam)
    {
      arrayOfByte1 = null;
    }
    return arrayOfByte1;
  }

  public int getItemCount()
    throws FMCommunicationMessageException
  {
    try
    {
      int k = this.f.size();
      j = k;
      return j;
    }
    catch (am localam)
    {
      while (true)
        int j = 0;
    }
  }

  public int getItemIntVal(int paramInt)
    throws FMCommunicationMessageException
  {
    if (paramInt >= 0);
    int j;
    try
    {
      if (paramInt > this.f.size())
      {
        String str = CRCUtil.valueOf(5, "菬厚奐朌KqfI&\f\030盒卂吳]{l盘偱斨ｃ你兴皆丘栃跟甊");
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str, arrayOfObject));
      }
      int k = ((ITag)this.f.get(paramInt)).getIntVal();
      j = k;
    }
    catch (am localam)
    {
      j = 0;
    }
    return j;
  }

  public String getItemStringVal(int paramInt)
    throws FMCommunicationMessageException
  {
    if (paramInt >= 0);
    String str1;
    try
    {
      if (paramInt > this.f.size())
      {
        String str2 = FM_Long.concat("菪叞夞杜]ux\021p\030\026盒匄吧Ccj皜偿斸ｕ佤優盞与栗趑畊", 5);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str2, arrayOfObject));
      }
      String str3 = ((ITag)this.f.get(paramInt)).getStringVal();
      str1 = str3;
    }
    catch (am localam)
    {
      str1 = null;
    }
    return str1;
  }

  public int getItemTagID(int paramInt)
    throws FMCommunicationMessageException
  {
    if (paramInt >= 0);
    int j;
    try
    {
      if (paramInt > this.f.size())
      {
        String str = Util4Java.toString("莲受夐杛ApjR Y��盝卐呺\031(\"盅桚讟斣｝佭儬盁乊栚趓留", 166, 124);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str, arrayOfObject));
      }
      int k = ((ITag)this.f.get(paramInt)).getId();
      j = k;
    }
    catch (am localam)
    {
      j = 0;
    }
    return j;
  }

  public ITag getItemTagVal(int paramInt)
    throws FMCommunicationMessageException
  {
    if ((paramInt < 0) || (paramInt > this.f.size()))
    {
      String str = FM_Bytes.startsWith("莴厅夎朑\027r$H&\013^盗卆吸\027rd盗倿斥ｏ伳儦皗丈桔趉生", 2, 80);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str, arrayOfObject));
    }
    return (ITag)this.f.get(paramInt);
  }

  public ITag[] getItemTags()
    throws FMCommunicationMessageException
  {
    try
    {
      ITag[] arrayOfITag2 = new ITag[0];
      arrayOfITag1 = (ITag[])this.f.toArray(arrayOfITag2);
      return arrayOfITag1;
    }
    catch (am localam)
    {
      while (true)
        ITag[] arrayOfITag1 = null;
    }
  }

  public ETagType getItemType(int paramInt)
    throws FMCommunicationMessageException
  {
    if ((paramInt < 0) || (paramInt > this.f.size()))
    {
      String str = FM_CN.subSequence("莧叉夃束\030:-\002-O[皑十呸\0260g皋籥垆旪ｇ佺儬盜丌树趏畘", 2);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Byte.valueOf(this.c);
      throw new FMCommunicationMessageException(String.format(str, arrayOfObject));
    }
    return ((ITag)this.f.get(paramInt)).getType();
  }

  public String getStringVal()
    throws FMCommunicationMessageException
  {
    Object localObject = null;
    try
    {
      StringValueHandler localStringValueHandler = HandlerFactory.instance().getStringValueHandle(this.e);
      if (localStringValueHandler != null)
      {
        String str = localStringValueHandler.getTagvalue(this.d);
        localObject = str;
      }
      label32: return localObject;
    }
    catch (am localam)
    {
      break label32;
    }
  }

  public byte[] getTagValue()
  {
    return this.d;
  }

  public ETagType getType()
  {
    return this.e;
  }

  public boolean isValid()
  {
    int j = 0;
    if (this.i == null)
      if (this.a != null)
      {
        FMLog localFMLog3 = this.a;
        String str5 = this.b;
        String str6 = FM_Int.lastIndexOf(4, "\003\031\036\001~\004��髒讞时－莵叕酉罫斁仱宒乀夻贮");
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[j] = Byte.valueOf(this.c);
        localFMLog3.warn(str5, String.format(str6, arrayOfObject3));
      }
    while (true)
    {
      return j;
      do
      {
        if (this.d.length <= this.i.getLength())
          break label241;
        if (this.a == null)
          break;
        FMLog localFMLog2 = this.a;
        String str3 = this.b;
        String str4 = BCCUtil.endsWith("P\037_Ii\036]攪挺镱庮哎酑罸旗亼实丗盜敢换锹廦丗筲", 5, 90);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[j] = Byte.valueOf(this.c);
        localFMLog2.warn(str3, String.format(str4, arrayOfObject2));
        break;
        if (this.i.getLength() == 0)
          break label241;
      }
      while ((this.d != null) && (this.d.length >= 1));
      if (this.a == null)
        continue;
      FMLog localFMLog1 = this.a;
      String str1 = this.b;
      String str2 = Util4Java.toString("U\027\f\033p\022B骘诈旨？\\\034\025盃偠乫稼", 2, 117);
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[j] = Byte.valueOf(this.c);
      localFMLog1.warn(str1, String.format(str2, arrayOfObject1));
      continue;
      label241: j = 1;
    }
  }

  public int setValue(Tag paramTag, int paramInt)
    throws FMCommunicationMessageException
  {
    return 0;
  }

  public byte[] toBytes()
    throws FMCommunicationMessageException
  {
    int j = 2;
    int k = 0;
    label117: int m;
    byte[] arrayOfByte3;
    int n;
    label231: label369: byte[] arrayOfByte1;
    try
    {
      byte[] arrayOfByte2;
      if (!isValid())
      {
        if (this.a != null)
        {
          FMLog localFMLog2 = this.a;
          String str5 = this.b;
          String str6 = CRCUtil.valueOf(164, "NJ\033\026{\027\035载捠戃孓苗廉剀斾５會敓怫條枫乒辗");
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Byte.valueOf(this.c);
          localFMLog2.warn(str5, String.format(str6, arrayOfObject4));
        }
        String str4 = FM_Long.concat("TJQZ)O\037輡挺打嬙苛庋刘旬）DZ\001攡挲斧攚", 264);
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Byte.valueOf(this.c);
        throw new FMCommunicationMessageException(String.format(str4, arrayOfObject3));
        break label231;
        m = this.d.length;
        arrayOfByte2 = new byte[4];
        arrayOfByte2[0] = this.c;
        if (m < 255)
          break label387;
        arrayOfByte2[1] = -1;
        byte[] arrayOfByte4 = FM_Bytes.intToBytes(m, 2);
        arrayOfByte2[2] = arrayOfByte4[0];
        arrayOfByte2[3] = arrayOfByte4[1];
        j = 4;
      }
      while (true)
      {
        arrayOfByte3 = new byte[j + m];
        n = 0;
        break label422;
        while (true)
        {
          Iterator localIterator;
          ITag localITag = (ITag)localIterator.next();
          this.d = FM_Bytes.join(this.d, localITag.toBytes());
          while (true)
          {
            if (localIterator.hasNext())
              break label369;
            if (this.d != null)
              break label117;
            if (this.a != null)
            {
              FMLog localFMLog1 = this.a;
              String str2 = this.b;
              String str3 = FM_Long.concat("\tITE,LB輦挷扐嬜苔庎创旡.YY\004放挷乾稵", 5);
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = Byte.valueOf(this.c);
              localFMLog1.warn(str2, String.format(str3, arrayOfObject2));
            }
            String str1 = FM_Long.concat("\016DW@#IA輫挰才嬟苑廑刞既3^T\007攻挸乻稶", 2);
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = Byte.valueOf(this.c);
            throw new FMCommunicationMessageException(String.format(str1, arrayOfObject1));
            if (ETagType.C != this.e)
              break;
            this.d = new byte[0];
            localIterator = this.f.iterator();
          }
        }
        arrayOfByte3[n] = arrayOfByte2[n];
        n++;
        break label422;
        label387: arrayOfByte2[1] = (byte)m;
      }
      label397: arrayOfByte3[(j + k)] = this.d[k];
      k++;
    }
    catch (am localam)
    {
      arrayOfByte1 = null;
    }
    while (true)
    {
      return arrayOfByte1;
      label422: if (n < j)
        break;
      if (k < m)
        break label397;
      arrayOfByte1 = arrayOfByte3;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.core.Tag
 * JD-Core Version:    0.6.0
 */