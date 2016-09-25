package miui.external;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

final class b extends Instrumentation
  implements SdkConstants
{
  private SdkConstants.SdkError p;

  private b(SdkConstants.SdkError paramSdkError)
  {
    this.p = paramSdkError;
  }

  private static Field a(Class<?> paramClass1, Object paramObject1, Object paramObject2, String paramString, Class<?> paramClass2)
    throws NoSuchFieldException
  {
    Field[] arrayOfField = paramClass1.getDeclaredFields();
    int i1;
    Object localObject1;
    if ((paramObject1 != null) && (paramObject2 != null))
    {
      int n = arrayOfField.length;
      i1 = 0;
      if (i1 < n)
      {
        localObject1 = arrayOfField[i1];
        ((Field)localObject1).setAccessible(true);
      }
    }
    while (true)
    {
      try
      {
        Object localObject2 = ((Field)localObject1).get(paramObject1);
        if (localObject2 == paramObject2)
          return localObject1;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        i1++;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        continue;
      }
      if (paramString != null)
      {
        int k = arrayOfField.length;
        for (int m = 0; ; m++)
        {
          if (m >= k)
            break label135;
          localObject1 = arrayOfField[m];
          if (!((Field)localObject1).getName().equals(paramString))
            continue;
          ((Field)localObject1).setAccessible(true);
          break;
        }
      }
      label135: localObject1 = null;
      if (paramClass2 != null)
        continue;
      int i = arrayOfField.length;
      int j = 0;
      while (j < i)
      {
        Field localField = arrayOfField[j];
        if ((localField.getType() == paramClass2) || (localField.getType().isInstance(paramClass2)))
        {
          if (localObject1 == null)
            localObject1 = localField;
        }
        else
        {
          j++;
          continue;
        }
        throw new NoSuchFieldException("More than one matched field found: " + ((Field)localObject1).getName() + " and " + localField.getName());
      }
      if (localObject1 == null)
        throw new NoSuchFieldException("No such field found of value " + paramObject2);
      ((Field)localObject1).setAccessible(true);
    }
  }

  static void a(SdkConstants.SdkError paramSdkError)
  {
    try
    {
      Class localClass = Class.forName("android.app.ActivityThread");
      Object localObject1 = localClass.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
      Field localField1 = a(localClass, localObject1, (Instrumentation)localClass.getMethod("getInstrumentation", new Class[0]).invoke(localObject1, new Object[0]), null, null);
      Instrumentation localInstrumentation = (Instrumentation)localField1.get(localObject1);
      b localb = new b(paramSdkError);
      for (Object localObject2 = Instrumentation.class; localObject2 != null; localObject2 = ((Class)localObject2).getSuperclass())
        for (Field localField2 : ((Class)localObject2).getDeclaredFields())
        {
          localField2.setAccessible(true);
          localField2.set(localb, localField2.get(localInstrumentation));
        }
      localField1.set(localObject1, localb);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public Activity newActivity(Class<?> paramClass, Context paramContext, IBinder paramIBinder, Application paramApplication, Intent paramIntent, ActivityInfo paramActivityInfo, CharSequence paramCharSequence, Activity paramActivity, String paramString, Object paramObject)
    throws InstantiationException, IllegalAccessException
  {
    Object localObject;
    Intent localIntent;
    if (!paramClass.getSimpleName().startsWith("SdkError"))
    {
      localObject = SdkErrorActivity.class;
      if (paramIntent == null)
        paramIntent = new Intent();
      SdkConstants.SdkError localSdkError = this.p;
      paramIntent.putExtra("com.miui.sdk.error", localSdkError);
      localIntent = paramIntent;
    }
    while (true)
    {
      return super.newActivity((Class)localObject, paramContext, paramIBinder, paramApplication, localIntent, paramActivityInfo, paramCharSequence, paramActivity, paramString, paramObject);
      localIntent = paramIntent;
      localObject = paramClass;
    }
  }

  public Activity newActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent)
    throws InstantiationException, IllegalAccessException, ClassNotFoundException
  {
    if (!paramString.startsWith("SdkError"))
    {
      paramString = SdkErrorActivity.class.getName();
      if (paramIntent == null)
        paramIntent = new Intent();
      paramIntent.putExtra("com.miui.sdk.error", this.p);
    }
    return super.newActivity(paramClassLoader, paramString, paramIntent);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.b
 * JD-Core Version:    0.6.0
 */