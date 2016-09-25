package miui.external;

abstract interface SdkConstants
{
  public static final String LOG_TAG = "miuisdk";

  public static enum SdkError
  {
    public static final String INTENT_EXTRA_KEY = "com.miui.sdk.error";

    static
    {
      LOW_SDK_VERSION = new SdkError("LOW_SDK_VERSION", 2);
      SdkError[] arrayOfSdkError = new SdkError[3];
      arrayOfSdkError[0] = GENERIC;
      arrayOfSdkError[1] = NO_SDK;
      arrayOfSdkError[2] = LOW_SDK_VERSION;
      e = arrayOfSdkError;
    }
  }

  public static abstract interface SdkReturnCode
  {
    public static final int LOW_SDK_VERSION = 1;
    public static final int SUCCESS;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.SdkConstants
 * JD-Core Version:    0.6.0
 */