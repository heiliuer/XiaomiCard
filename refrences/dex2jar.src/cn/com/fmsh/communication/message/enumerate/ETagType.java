package cn.com.fmsh.communication.message.enumerate;

import ar;

public enum ETagType
{
  static
  {
    try
    {
      I = new ETagType("I", 0, "I");
      S = new ETagType("S", 1, "S");
      B = new ETagType("B", 2, "B");
      C = new ETagType("C", 3, "C");
      N = new ETagType("N", 4, "N");
      U = new ETagType("U", 5, "U");
      G = new ETagType("G", 6, "G");
      H = new ETagType("H", 7, "H");
      ETagType[] arrayOfETagType = new ETagType[8];
      arrayOfETagType[0] = I;
      arrayOfETagType[1] = S;
      arrayOfETagType[2] = B;
      arrayOfETagType[3] = C;
      arrayOfETagType[4] = N;
      arrayOfETagType[5] = U;
      arrayOfETagType[6] = G;
      arrayOfETagType[7] = H;
      b = arrayOfETagType;
      label182: return;
    }
    catch (ar localar)
    {
      break label182;
    }
  }

  public String getValue()
  {
    return this.a;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.enumerate.ETagType
 * JD-Core Version:    0.6.0
 */