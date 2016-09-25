package cn.com.fmsh.communication.message.constants;

public abstract interface Constants
{
  public static final String CINFIG_FILE = "/message.xml";

  public static abstract interface Const
  {
    public static final int MESSAGE_TAG_EXIST_NO = 0;
    public static final int MESSAGE_TAG_EXIST_YES = 1;
  }

  public static abstract interface XMLNode
  {
    public static abstract interface XMLMessage
    {
      public static final String DATA = "Data";
      public static final String DATA_EXIST = "exist";
      public static final String DATA_MULTIPLE = "multiple";
      public static final String DATA_ORDER = "order";
      public static final String DATA_TAG = "Tag";
      public static final String MESSAGE = "Message";
      public static final String MESSAGE_CODE = "code";
      public static final String MESSAGE_DEFINE = "MessageDefine";
      public static final String MESSAGE_DESC = "desc";
      public static final String MESSAGE_RET_CODE = "retCode";
    }

    public static abstract interface XMLTag
    {
      public static final String ITEM = "Item";
      public static final String ITEM_DESC = "desc";
      public static final String ITEM_EXIST = "exist";
      public static final String ITEM_MULTIPLE = "multiple";
      public static final String ITEM_ORDER = "order";
      public static final String ITEM_TAG = "tag";
      public static final String TAG = "Tag";
      public static final String TAG_DEFINE = "TagDefine";
      public static final String TAG_DESC = "desc";
      public static final String TAG_ID = "id";
      public static final String TAG_LENGTH = "length";
      public static final String TAG_TYPE = "type";
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cn.com.fmsh.communication.message.constants.Constants
 * JD-Core Version:    0.6.0
 */