package cn.com.fmsh.script.constants;

public class ScriptToolsConst
{
  public static abstract interface A2Response4ApduExceFail
  {
    public static final byte NO_REPONSE = 1;
    public static final byte TIMEOUT = 2;
  }

  public static abstract interface TagName
  {
    public static final byte CommandMultiple = -92;
    public static final byte CommandSingle = -96;
    public static final byte ResponseMultiple = -93;
    public static final byte ResponseSingle = -94;
    public static final byte ScriptDown = -95;
    public static final byte TagApdu = 57;
    public static final byte TagExpectationAndNext = 60;
    public static final byte TagSerial = 56;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.script.constants.ScriptToolsConst
 * JD-Core Version:    0.6.0
 */