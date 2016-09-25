package cn.com.fmsh.communication.contants;

public abstract interface Contants
{
  public static final int MAC_LENGTH = 4;
  public static final byte VERSION = 17;

  public static abstract interface Message
  {
    public static final int MESSAGE_HEAD_LENGTH = 12;
    public static final int MESSAGE_LENGTH_BIT_COUNT = 4;
    public static final String PACKET_CODE_DEFAULT = "9000";
    public static final byte PROTOCOL_VERSION = -128;
    public static final int RESPONSE_CODE_LENGTH = 2;
    public static final int SECURITY_LEVEL_BYTE_COUNT = 2;

    public static abstract interface ReponseCode
    {
      public static final byte BUSINESS_DEFINE = 14;
      public static final byte SUCESS;
    }

    public static abstract interface RtResponseCmdType
    {
      public static final int CMD_FIELD_ON = 2;
    }

    public static abstract interface SecurityLevel
    {
      public static final int DATA_CHECK_CRC = 2;
      public static final int DATA_CHECK_MAC = 1;
      public static final int DATA_ENCRYPT_3DES = 1;
      public static final int DATA_NO_CHECK;
      public static final int DATA_NO_ENCRYPT;
    }

    public static abstract interface TerminalType
    {
      public static final byte FM_MSG_HEAD_TTYPE_LTP = 64;
      public static final byte FM_MSG_HEAD_TTYPE_READER_1915 = 32;
      public static final byte FM_MSG_HEAD_TTYPE_READER_1920 = 64;
      public static final byte FM_MSG_HEAD_TTYPE_USB_CARD = 16;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.contants.Contants
 * JD-Core Version:    0.6.0
 */