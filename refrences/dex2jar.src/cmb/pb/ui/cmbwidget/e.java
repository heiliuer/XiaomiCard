package cmb.pb.ui.cmbwidget;

import cmb.pb.a.a;
import cmb.pb.a.b;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.KeyGenerator;

public final class e
{
  private int a = 0;
  private Key b = null;
  private List c = null;

  public e(int paramInt, String paramString)
  {
    this.a = paramInt;
    try
    {
      KeyGenerator localKeyGenerator = KeyGenerator.getInstance("AES");
      localKeyGenerator.init(new SecureRandom());
      this.b = localKeyGenerator.generateKey();
      this.c = new ArrayList();
      if (!a.b(paramString))
        while (i < paramString.length())
        {
          String str = paramString.substring(i, i + 1);
          a(this.c.size(), str);
          i++;
        }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public final int a()
  {
    return this.c.size();
  }

  public final void a(int paramInt)
  {
    if ((paramInt <= 0) || (this.c == null));
    while (true)
    {
      return;
      if (this.c != null)
      {
        this.c.remove(paramInt - 1);
        continue;
      }
    }
  }

  public final void a(int paramInt, String paramString)
  {
    if ((this.c == null) || (this.c.size() >= this.a));
    while (true)
    {
      return;
      if ((!a.b(paramString)) && (this.b != null))
      {
        String str = b.a(paramString, this.b);
        if (a.b(str))
          continue;
        this.c.add(paramInt, str);
        continue;
      }
    }
  }

  public final String b()
  {
    String str1;
    if ((this.b == null) || (this.c == null) || (this.c.size() == 0))
    {
      str1 = "";
      return str1;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i = 0; ; i++)
    {
      if (i >= this.c.size())
      {
        str1 = localStringBuilder.toString();
        break;
      }
      String str2 = b.b((String)this.c.get(i), this.b);
      if (a.b(str2))
      {
        str1 = null;
        break;
      }
      localStringBuilder.append(str2);
    }
  }

  public final void c()
  {
    if (this.c != null)
      this.c.clear();
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.ui.cmbwidget.e
 * JD-Core Version:    0.6.0
 */