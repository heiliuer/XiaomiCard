package cmb.pb.cmbsafe;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.lang.reflect.Method;

public class CmbService extends Service
{
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    try
    {
      b.a().getClass().getMethod(b.b(), new Class[0]).invoke(b.a(), new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.cmbsafe.CmbService
 * JD-Core Version:    0.6.0
 */