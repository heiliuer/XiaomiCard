package rx.exceptions;

import java.util.HashSet;
import java.util.Set;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OnErrorThrowable extends RuntimeException
{
  private static final long serialVersionUID = -569558213262703934L;
  private final boolean hasValue;
  private final Object value;

  private OnErrorThrowable(Throwable paramThrowable)
  {
    super(paramThrowable);
    this.hasValue = false;
    this.value = null;
  }

  private OnErrorThrowable(Throwable paramThrowable, Object paramObject)
  {
    super(paramThrowable);
    this.hasValue = true;
    this.value = paramObject;
  }

  public static Throwable addValueAsLastCause(Throwable paramThrowable, Object paramObject)
  {
    if (paramThrowable == null)
      paramThrowable = new NullPointerException();
    Throwable localThrowable = Exceptions.getFinalCause(paramThrowable);
    if ((localThrowable != null) && ((localThrowable instanceof OnNextValue)) && (((OnNextValue)localThrowable).getValue() == paramObject));
    while (true)
    {
      return paramThrowable;
      Exceptions.addCause(paramThrowable, new OnNextValue(paramObject));
    }
  }

  public static OnErrorThrowable from(Throwable paramThrowable)
  {
    if (paramThrowable == null)
      paramThrowable = new NullPointerException();
    Throwable localThrowable = Exceptions.getFinalCause(paramThrowable);
    if ((localThrowable instanceof OnNextValue));
    for (OnErrorThrowable localOnErrorThrowable = new OnErrorThrowable(paramThrowable, ((OnNextValue)localThrowable).getValue()); ; localOnErrorThrowable = new OnErrorThrowable(paramThrowable))
      return localOnErrorThrowable;
  }

  public Object getValue()
  {
    return this.value;
  }

  public boolean isValueNull()
  {
    return this.hasValue;
  }

  public static class OnNextValue extends RuntimeException
  {
    private static final long serialVersionUID = -3454462756050397899L;
    private final Object value;

    public OnNextValue(Object paramObject)
    {
      super();
      this.value = paramObject;
    }

    static String renderValue(Object paramObject)
    {
      Object localObject;
      if (paramObject == null)
        localObject = "null";
      while (true)
      {
        return localObject;
        if (Primitives.INSTANCE.contains(paramObject.getClass()))
        {
          localObject = paramObject.toString();
          continue;
        }
        if ((paramObject instanceof String))
        {
          localObject = (String)paramObject;
          continue;
        }
        if ((paramObject instanceof Enum))
        {
          localObject = ((Enum)paramObject).name();
          continue;
        }
        String str = RxJavaPlugins.getInstance().getErrorHandler().handleOnNextValueRendering(paramObject);
        if (str != null)
        {
          localObject = str;
          continue;
        }
        localObject = paramObject.getClass().getName() + ".class";
      }
    }

    public Object getValue()
    {
      return this.value;
    }

    private static final class Primitives
    {
      static final Set<Class<?>> INSTANCE = create();

      private static Set<Class<?>> create()
      {
        HashSet localHashSet = new HashSet();
        localHashSet.add(Boolean.class);
        localHashSet.add(Character.class);
        localHashSet.add(Byte.class);
        localHashSet.add(Short.class);
        localHashSet.add(Integer.class);
        localHashSet.add(Long.class);
        localHashSet.add(Float.class);
        localHashSet.add(Double.class);
        return localHashSet;
      }
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.exceptions.OnErrorThrowable
 * JD-Core Version:    0.6.0
 */