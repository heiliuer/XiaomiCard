package rx.schedulers;

public class TimeInterval<T>
{
  private final long intervalInMilliseconds;
  private final T value;

  public TimeInterval(long paramLong, T paramT)
  {
    this.value = paramT;
    this.intervalInMilliseconds = paramLong;
  }

  public boolean equals(Object paramObject)
  {
    int i = 1;
    if (this == paramObject);
    while (true)
    {
      return i;
      if (paramObject == null)
      {
        i = 0;
        continue;
      }
      if (getClass() != paramObject.getClass())
      {
        i = 0;
        continue;
      }
      TimeInterval localTimeInterval = (TimeInterval)paramObject;
      if (this.intervalInMilliseconds != localTimeInterval.intervalInMilliseconds)
      {
        i = 0;
        continue;
      }
      if (this.value == null)
      {
        if (localTimeInterval.value == null)
          continue;
        i = 0;
        continue;
      }
      if (this.value.equals(localTimeInterval.value))
        continue;
      i = 0;
    }
  }

  public long getIntervalInMilliseconds()
  {
    return this.intervalInMilliseconds;
  }

  public T getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int i = 31 * (31 + (int)(this.intervalInMilliseconds ^ this.intervalInMilliseconds >>> 32));
    if (this.value == null);
    for (int j = 0; ; j = this.value.hashCode())
      return i + j;
  }

  public String toString()
  {
    return "TimeInterval [intervalInMilliseconds=" + this.intervalInMilliseconds + ", value=" + this.value + "]";
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.schedulers.TimeInterval
 * JD-Core Version:    0.6.0
 */