package cmb.pb.a;

import android.content.Context;
import android.content.res.Resources;

public final class d
{
  public static int a(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getResources().getIdentifier(paramString2, paramString1, paramContext.getPackageName());
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.a.d
 * JD-Core Version:    0.6.0
 */