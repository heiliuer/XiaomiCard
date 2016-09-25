package miui.external;

import android.util.Log;

class a
  implements SdkConstants
{
  private static final String f = "miui.core.SdkManager";
  private static final String g = "com.miui.internal.core.SdkManager";

  public static Class<?> g()
    throws ClassNotFoundException
  {
    try
    {
      Class localClass2 = Class.forName("miui.core.SdkManager");
      localObject = localClass2;
      return localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException1)
    {
      try
      {
        Class localClass1 = Class.forName("com.miui.internal.core.SdkManager");
        Object localObject = localClass1;
        Log.w("miuisdk", "using legacy sdk");
      }
      catch (ClassNotFoundException localClassNotFoundException2)
      {
        Log.e("miuisdk", "no sdk found");
      }
    }
    throw localClassNotFoundException2;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.a
 * JD-Core Version:    0.6.0
 */