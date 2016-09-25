package cmb.pb.cmbsafe;

import cmb.pb.ui.cmbwidget.CmbEditText;
import java.util.HashMap;

public final class b
{
  public static HashMap a = new HashMap();
  public static HashMap b = new HashMap();
  private static String c;

  public static CmbEditText a()
  {
    return (CmbEditText)a.get(Long.valueOf(1L));
  }

  public static void a(CmbEditText paramCmbEditText)
  {
    a.clear();
    a.put(Long.valueOf(1L), paramCmbEditText);
  }

  public static void a(Object paramObject)
  {
    b.clear();
    b.put(Long.valueOf(1L), paramObject);
  }

  public static void a(String paramString)
  {
    c = paramString;
  }

  public static String b()
  {
    return c;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.cmbsafe.b
 * JD-Core Version:    0.6.0
 */