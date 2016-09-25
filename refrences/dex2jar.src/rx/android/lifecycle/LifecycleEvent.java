package rx.android.lifecycle;

public enum LifecycleEvent
{
  static
  {
    RESUME = new LifecycleEvent("RESUME", 4);
    PAUSE = new LifecycleEvent("PAUSE", 5);
    STOP = new LifecycleEvent("STOP", 6);
    DESTROY_VIEW = new LifecycleEvent("DESTROY_VIEW", 7);
    DESTROY = new LifecycleEvent("DESTROY", 8);
    DETACH = new LifecycleEvent("DETACH", 9);
    LifecycleEvent[] arrayOfLifecycleEvent = new LifecycleEvent[10];
    arrayOfLifecycleEvent[0] = ATTACH;
    arrayOfLifecycleEvent[1] = CREATE;
    arrayOfLifecycleEvent[2] = CREATE_VIEW;
    arrayOfLifecycleEvent[3] = START;
    arrayOfLifecycleEvent[4] = RESUME;
    arrayOfLifecycleEvent[5] = PAUSE;
    arrayOfLifecycleEvent[6] = STOP;
    arrayOfLifecycleEvent[7] = DESTROY_VIEW;
    arrayOfLifecycleEvent[8] = DESTROY;
    arrayOfLifecycleEvent[9] = DETACH;
    $VALUES = arrayOfLifecycleEvent;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     rx.android.lifecycle.LifecycleEvent
 * JD-Core Version:    0.6.0
 */