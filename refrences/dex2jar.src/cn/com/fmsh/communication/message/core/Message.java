package cn.com.fmsh.communication.message.core;

import ah;
import ai;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.BCCUtil;
import cn.com.fmsh.util.CRCUtil;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Message
  implements IMessage
{
  public FMLog a = LogFactory.getInstance().getLog();
  public String b = Message.class.getName();

  public Message(MessageHandler paramMessageHandler)
  {
    this.d = paramMessageHandler;
  }

  public Message(MessageHandler paramMessageHandler, int paramInt)
  {
    this.d = paramMessageHandler;
    this.e = paramInt;
  }

  public int addTag(ITag paramITag)
    throws FMCommunicationMessageException
  {
    int i = 0;
    try
    {
      if (this.g)
      {
        i = -1;
        break label30;
        this.h.add(paramITag);
      }
    }
    catch (ah localah)
    {
    }
    while (true)
    {
      label30: return i;
      if (paramITag != null)
        break;
      i = -2;
    }
  }

  public int clear()
  {
    try
    {
      this.h.clear();
      label9: return 0;
    }
    catch (ah localah)
    {
      break label9;
    }
  }

  public int fromPackageBody(int paramInt, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      this.g = true;
      this.e = paramInt;
      this.c = this.d.getMessageDefine(this.e);
      if (this.c == null)
        throw new FMCommunicationMessageException(Util4Java.toString("栻捴仞近剴底剅枎遢\027w9q;u/旴６覱枚菵双剢皎淊怵缄硋丏呒泇", 3, 56));
      a(paramArrayOfByte);
    }
    catch (ah localah)
    {
    }
    return 0;
  }

  public int fromPackageBody(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      this.g = true;
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 2))
        throw new InvalidParameterException(CRCUtil.valueOf(6, "桥挣嬉苍廏分枆逳涌怺新｛佨兼皎孌芎廒剙锠延乌吚泖"));
      while (true)
      {
        this.c = this.d.getMessageDefine(this.e);
        if (this.c != null)
          break;
        throw new FMCommunicationMessageException(FM_Bytes.startsWith("桴挴介辏刷庁刌枌逵\017*/:7du旫＆觴柔菦厈剻盜涍恽缉砍且呎沆", 204, 13));
        this.e = Util4Java.String2Int(FM_CN.bcdBytesToString(Arrays.copyOf(paramArrayOfByte, 2)), -1);
        if (this.e != -1)
          continue;
        this.h.clear();
        throw new InvalidParameterException(FM_Int.lastIndexOf(3, "桯挹嬏苛廕剌柘遽淖怰时－觡枓莳叓制皃缞砈万吃泙"));
      }
      a(Arrays.copyOfRange(paramArrayOfByte, 2, paramArrayOfByte.length));
    }
    catch (ah localah)
    {
    }
    return 0;
  }

  public int fromPackageBodyAndRetCode(int paramInt, byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    this.g = true;
    this.e = paramInt;
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 2))
      throw new InvalidParameterException(FM_Int.lastIndexOf(236, "桦挮嬖苀廌剓柁遦淏性斿ｆ佫儩盉嬙苍廟剆锭廵乙呝沃"));
    this.f = Arrays.copyOfRange(paramArrayOfByte, 2, 2);
    this.c = this.d.getMessageRetDefine(this.e);
    if (this.c == null)
      throw new FMCommunicationMessageException(FM_Int.lastIndexOf(5, "桡挷他辀剪廒剉柛造Lgpwdab旾％觩枛莻叛刾皋涘恾缄砒丙吝泃"));
    a(Arrays.copyOfRange(paramArrayOfByte, 2, paramArrayOfByte.length));
    return 0;
  }

  public int fromPackageBodyAndRetCode(byte[] paramArrayOfByte)
    throws FMCommunicationMessageException
  {
    try
    {
      this.g = true;
      if ((paramArrayOfByte == null) || (paramArrayOfByte.length < 4))
        throw new InvalidParameterException(FM_Exception.getChars(2, 87, "桢挼孞节廘则极遼涛恥斷４伯儣皙孃苉廍刎锯廡专呝沙"));
      while (true)
      {
        a(Arrays.copyOfRange(paramArrayOfByte, 4, paramArrayOfByte.length));
        break;
        this.e = Util4Java.String2Int(FM_CN.bcdBytesToString(Arrays.copyOf(paramArrayOfByte, 2)), -1);
        this.f = Arrays.copyOfRange(paramArrayOfByte, 2, 4);
        this.c = this.d.getMessageRetDefine(this.e);
        if (this.c != null)
          continue;
        throw new FMCommunicationMessageException(CRCUtil.valueOf(1, "桮挦仕辑刭広刊枊避\035$!0ubs旱ｔ親柊菼及刽皚涇息罇硃乞呌泀"));
      }
    }
    catch (ah localah)
    {
    }
    return 0;
  }

  public int getCode()
  {
    return this.e;
  }

  public byte[] getRetCode()
  {
    return this.f;
  }

  public ITag getTag4Id(int paramInt)
    throws FMCommunicationMessageException
  {
    ITag localITag;
    try
    {
      Iterator localIterator = this.h.iterator();
      int i;
      do
      {
        do
        {
          if (!localIterator.hasNext())
          {
            localITag = null;
            break;
          }
          localITag = (ITag)localIterator.next();
        }
        while (localITag == null);
        i = localITag.getId();
      }
      while (paramInt != i);
    }
    catch (ah localah)
    {
      localITag = null;
    }
    return localITag;
  }

  public ITag getTag4Id(int paramInt1, int paramInt2)
    throws FMCommunicationMessageException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.h.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if ((paramInt2 >= 0) && (paramInt2 <= localArrayList.size()))
          break;
        throw new FMCommunicationMessageException(Util4Java.toString("栶捺\r?d桏讋咞庘剋叶莱厝$t}斩（庆剙厤趒畑", 272, 37));
      }
      ITag localITag = (ITag)localIterator.next();
      if ((localITag == null) || (paramInt1 != localITag.getId()))
        continue;
      localArrayList.add(localITag);
    }
    return (ITag)localArrayList.get(paramInt2);
  }

  public ITag getTag4Index(int paramInt)
    throws FMCommunicationMessageException
  {
    if (paramInt >= 0);
    ITag localITag;
    try
    {
      if (paramInt > this.h.size())
        throw new FMCommunicationMessageException(BCCUtil.endsWith("栦捽庈剌厸菴厁?~t旱７庀刔厠跁甓", 288, 116));
      localITag = (ITag)this.h.get(paramInt);
      if (localITag == null)
        localITag = null;
    }
    catch (ah localah)
    {
      localITag = null;
    }
    return localITag;
  }

  public int getTagCount()
  {
    try
    {
      int j = this.h.size();
      i = j;
      return i;
    }
    catch (ah localah)
    {
      while (true)
        int i = 0;
    }
  }

  public int getTagCount4Id(int paramInt)
  {
    return 0;
  }

  public boolean isValid()
  {
    int i = 0;
    if (this.c == null)
    {
      if (this.f == null)
        this.c = this.d.getMessageDefine(this.e);
    }
    else if (this.c == null)
    {
      if (this.a != null)
        this.a.warn(this.b, FM_CN.subSequence("涚恮吘泊恩條枩断＆莮叞涟怩寏不侲恭夠败", 4));
      label64: return i;
    }
    label176: label179: label438: 
    while (true)
    {
      MessageTagDefine[] arrayOfMessageTagDefine;
      int i1;
      MessageTagDefine localMessageTagDefine2 = arrayOfMessageTagDefine[i1];
      ITag localITag;
      int i2;
      if (localITag.getId() == localMessageTagDefine2.getTag())
        i2 = 1;
      i1++;
      while (true)
      {
        if (i1 < n)
          break label438;
        int k;
        int j;
        Iterator localIterator2;
        if (i2 == 0)
        {
          if (this.a == null)
            break label64;
          FMLog localFMLog2 = this.a;
          String str3 = this.b;
          String str4 = FM_Bytes.startsWith("涓怱丬巶纈权皉D\022\021B9\007_)坠鄆罠斖亢乺朰宇三", 186, 35);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[i] = Byte.valueOf(localITag.getId());
          localFMLog2.warn(str3, String.format(str4, arrayOfObject2));
          break label64;
          k++;
          if (k >= j)
            localIterator2 = this.h.iterator();
        }
        else
        {
          if (localIterator2.hasNext())
            break label415;
          i = 1;
          break label64;
        }
        while (true)
        {
          Iterator localIterator1;
          MessageTagDefine localMessageTagDefine1;
          if (((ITag)localIterator1.next()).getId() == localMessageTagDefine1.getTag());
          for (int m = 1; ; m = 0)
          {
            if (localIterator1.hasNext())
              break label413;
            if (m != 0)
              break label176;
            if (this.a == null)
              break label64;
            FMLog localFMLog1 = this.a;
            String str1 = this.b;
            String str2 = CRCUtil.valueOf(5, "鄖缢旚亸宅乙Lwp'$1\"C") + this.e + FM_Bytes.startsWith("\016忄頴孅圣盝S4$JzU\006ｅ业嬝圻－讪涕怤且吏沀", 210, 78);
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[i] = Byte.valueOf(localMessageTagDefine1.getTag());
            localFMLog1.warn(str1, String.format(str2, arrayOfObject1));
            break label64;
            arrayOfMessageTagDefine = this.c.getMessageTagDefines();
            j = arrayOfMessageTagDefine.length;
            k = 0;
            break label179;
            this.c = this.d.getMessageRetDefine(this.e);
            break;
            localMessageTagDefine1 = arrayOfMessageTagDefine[k];
            if (localMessageTagDefine1.getExist() != 1)
              break label176;
            localIterator1 = this.h.iterator();
          }
        }
        localITag = (ITag)localIterator2.next();
        int n = arrayOfMessageTagDefine.length;
        i1 = 0;
        i2 = 0;
      }
    }
  }

  public void setRetCode(byte[] paramArrayOfByte)
  {
    try
    {
      this.f = paramArrayOfByte;
      label5: return;
    }
    catch (ah localah)
    {
      break label5;
    }
  }

  public int setVal(ITag paramITag, int paramInt)
    throws FMCommunicationMessageException
  {
    return -1;
  }

  public byte[] toBytes()
    throws FMCommunicationMessageException
  {
    if (!isValid())
      throw new FMCommunicationMessageException(FM_Exception.getChars(66, 83, "涓恡宸豵轫挸戝字苑庉剎｀涗怽宼豹斫敖"));
    while (true)
    {
      Iterator localIterator;
      byte[] arrayOfByte2 = ((ITag)localIterator.next()).toBytes();
      if (arrayOfByte2 != null);
      byte[] arrayOfByte1;
      for (Object localObject = FM_Bytes.join(localObject, arrayOfByte2); !localIterator.hasNext(); localObject = arrayOfByte1)
      {
        return localObject;
        arrayOfByte1 = FM_CN.intToBcdBytes(this.e, 2);
        new byte[0];
        localIterator = this.h.iterator();
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.core.Message
 * JD-Core Version:    0.6.0
 */