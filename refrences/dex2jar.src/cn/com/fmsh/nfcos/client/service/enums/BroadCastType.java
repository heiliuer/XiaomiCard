package cn.com.fmsh.nfcos.client.service.enums;

import a6;
import cn.com.fmsh.FM_Exception;
import cn.com.fmsh.util.FM_Int;
import cn.com.fmsh.util.FM_Long;

public enum BroadCastType
{
  static
  {
    try
    {
      ISSUER_PROCESS = new BroadCastType(FM_Exception.getChars(322, 55, "R\001Z\025R\034ZL\001E\002]\034U"), 0, 1, FM_Long.concat("廈甯參衑迓庵", 4));
      PLATFORM_NOTICE = new BroadCastType(FM_Exception.getChars(1, 82, "\n��_\004\004[\024UU\022AT\033G\023"), 1, 2, FM_Int.lastIndexOf(3, "帥厧淐怶"));
      SOCKET_EXCEPTION_RECONNECT = new BroadCastType(FM_Long.concat("\tJSPCEC\002\n\036\r\003\n@[QUG\005\b\031\017\002\022AY", 2), 2, 3, FM_Exception.getChars(184, 97, "罀绎彑幬君釛辉授礣"));
      BroadCastType[] arrayOfBroadCastType = new BroadCastType[3];
      arrayOfBroadCastType[0] = ISSUER_PROCESS;
      arrayOfBroadCastType[1] = PLATFORM_NOTICE;
      arrayOfBroadCastType[2] = SOCKET_EXCEPTION_RECONNECT;
      c = arrayOfBroadCastType;
      label109: return;
    }
    catch (a6 locala6)
    {
      break label109;
    }
  }

  public String getDesc()
  {
    return this.b;
  }

  public int getId()
  {
    return this.a;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.nfcos.client.service.enums.BroadCastType
 * JD-Core Version:    0.6.0
 */