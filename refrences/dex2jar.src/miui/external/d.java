package miui.external;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

class d
{
  private static final String q = "dalvik.system.DexPathList";
  private static final String r = "dalvik.system.DexPathList$Element";
  private static final String s = "dexElements";
  private static final String t = "nativeLibraryPathElements";

  private static Object a(ClassLoader paramClassLoader)
    throws NoSuchFieldException
  {
    Field[] arrayOfField;
    int i;
    int j;
    if ((paramClassLoader instanceof BaseDexClassLoader))
    {
      arrayOfField = BaseDexClassLoader.class.getDeclaredFields();
      i = arrayOfField.length;
      j = 0;
    }
    while (true)
    {
      Field localField;
      if (j < i)
      {
        localField = arrayOfField[j];
        if ("dalvik.system.DexPathList".equals(localField.getType().getName()))
          localField.setAccessible(true);
      }
      try
      {
        Object localObject = localField.get(paramClassLoader);
        return localObject;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        j++;
        continue;
        throw new NoSuchFieldException("dexPathList field not found.");
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        label63: break label63;
      }
    }
  }

  private static Field a(Object paramObject)
    throws NoSuchFieldException
  {
    for (Field localField : paramObject.getClass().getDeclaredFields())
    {
      Class localClass = localField.getType();
      if ((!localClass.isArray()) || (localClass.getComponentType() != File.class))
        continue;
      localField.setAccessible(true);
      return localField;
    }
    throw new NoSuchFieldException("nativeLibraryDirectories field not found.");
  }

  private static Field a(Object paramObject, String paramString)
    throws NoSuchFieldException
  {
    Field[] arrayOfField = paramObject.getClass().getDeclaredFields();
    int i = arrayOfField.length;
    int j = 0;
    if (j < i)
    {
      Field localField = arrayOfField[j];
      if (!localField.getName().equals(paramString));
      Class localClass;
      do
      {
        j++;
        break;
        localClass = localField.getType();
      }
      while ((!localClass.isArray()) || (!"dalvik.system.DexPathList$Element".equals(localClass.getComponentType().getName())));
      localField.setAccessible(true);
      return localField;
    }
    throw new NoSuchFieldException(paramString + " field not found.");
  }

  private static void a(Object paramObject1, Object paramObject2)
    throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException
  {
    b(paramObject1, paramObject2, "dexElements");
  }

  private static void a(Object paramObject1, Object paramObject2, String paramString)
    throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException
  {
    if (Build.VERSION.SDK_INT >= 23)
      b(paramObject1, paramObject2, "nativeLibraryPathElements");
    while (true)
    {
      return;
      b(paramObject1, paramString);
    }
  }

  private static void b(Object paramObject1, Object paramObject2, String paramString)
    throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException
  {
    Object[] arrayOfObject1 = (Object[])(Object[])a(paramObject2, paramString).get(paramObject2);
    Field localField = a(paramObject1, paramString);
    Object[] arrayOfObject2 = (Object[])(Object[])localField.get(paramObject1);
    Object[] arrayOfObject3 = (Object[])(Object[])Array.newInstance(Class.forName("dalvik.system.DexPathList$Element"), 1 + arrayOfObject2.length);
    arrayOfObject3[0] = arrayOfObject1[0];
    System.arraycopy(arrayOfObject2, 0, arrayOfObject3, 1, arrayOfObject2.length);
    localField.set(paramObject1, arrayOfObject3);
  }

  private static void b(Object paramObject, String paramString)
    throws NoSuchFieldException, IllegalAccessException
  {
    Field localField = a(paramObject);
    File[] arrayOfFile1 = (File[])(File[])localField.get(paramObject);
    File[] arrayOfFile2 = new File[1 + arrayOfFile1.length];
    arrayOfFile2[0] = new File(paramString);
    System.arraycopy(arrayOfFile1, 0, arrayOfFile2, 1, arrayOfFile1.length);
    localField.set(paramObject, arrayOfFile2);
  }

  public static boolean load(String paramString1, String paramString2, String paramString3, ClassLoader paramClassLoader)
  {
    return load(paramString1, paramString2, paramString3, paramClassLoader, null);
  }

  static boolean load(String paramString1, String paramString2, String paramString3, ClassLoader paramClassLoader, Context paramContext)
  {
    int i = 0;
    if ((paramString1 == null) && ((paramString3 == null) || (paramContext == null)));
    while (true)
    {
      return i;
      while (true)
      {
        try
        {
          Object localObject1 = a(paramClassLoader);
          if (paramString1 != null)
            break label158;
          if (Build.VERSION.SDK_INT >= 23)
            continue;
          b(localObject1, paramString3);
          i = 1;
          break;
          String str2 = paramContext.getApplicationInfo().sourceDir;
          paramString2 = null;
          str1 = str2;
          if (paramString2 != null)
            continue;
          Object localObject2 = new PathClassLoader(str1, paramString3, paramClassLoader.getParent());
          Object localObject3 = a((ClassLoader)localObject2);
          if (paramString1 == null)
            continue;
          a(localObject1, localObject3);
          if (paramString3 == null)
            break label164;
          a(localObject1, localObject3, paramString3);
          break label164;
          localObject2 = new DexClassLoader(str1, paramString2, paramString3, paramClassLoader.getParent());
          continue;
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
        }
        catch (ClassNotFoundException localClassNotFoundException)
        {
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
        }
        break;
        label158: String str1 = paramString1;
      }
      label164: i = 1;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     miui.external.d
 * JD-Core Version:    0.6.0
 */