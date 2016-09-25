package rx.schedulers;

public final class Timestamped<T>
{
  private final long timestampMillis;
  private final T value;

  public Timestamped(long paramLong, T paramT)
  {
    this.value = paramT;
    this.timestampMillis = paramLong;
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
      if (!(paramObject instanceof Timestamped))
      {
        i = 0;
        continue;
      }
      Timestamped localTimestamped = (Timestamped)paramObject;
      if (this.timestampMillis != localTimestamped.timestampMillis)
      {
        i = 0;
        continue;
      }
      if (this.value == null)
      {
        if (localTimestamped.value == null)
          continue;
        i = 0;
        continue;
      }
      if (this.value.equals(localTimestamped.value))
        continue;
      i = 0;
    }
  }

  public long getTimestampMillis()
  {
    return this.timestampMillis;
  }

  public T getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int i = 31 * (31 + (int)(this.timestampMillis ^ this.timestampMillis >>> 32));
    if (this.value == null);
    for (int j = 0; ; j = this.value.hashCode())
      return i + j;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(this.timestampMillis);
    arrayOfObject[1] = this.value.toString();
    return String.format("Timestamped(timestampMillis = %d, value = %s)", arrayOfObject);
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.schedulers.Timestamped
 * JD-Core Version:    0.6.0
 */