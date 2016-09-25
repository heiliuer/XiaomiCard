package cmb.pb.cmbsafe;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class a
{
  public static void a(Keyboard paramKeyboard)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramKeyboard.getKeys();
    int i = localList.size();
    int j = 0;
    int m;
    Random localRandom;
    int n;
    label49: int i3;
    if (j >= i)
    {
      m = localArrayList.size();
      localRandom = new Random();
      n = 0;
      if (n < m)
        break label126;
      i3 = 0;
    }
    label126: int i6;
    for (int i4 = 0; ; i4 = i6)
    {
      if (i3 >= m);
      do
      {
        return;
        int k = ((Keyboard.Key)localList.get(j)).codes[0];
        if ((k >= 48) && (k <= 57))
          localArrayList.add((Keyboard.Key)localList.get(j));
        j++;
        break;
        int i1 = localRandom.nextInt(m);
        int i2 = ((Keyboard.Key)localArrayList.get(n)).codes[0];
        CharSequence localCharSequence = ((Keyboard.Key)localArrayList.get(n)).label;
        ((Keyboard.Key)localArrayList.get(n)).codes[0] = ((Keyboard.Key)localArrayList.get(i1)).codes[0];
        ((Keyboard.Key)localArrayList.get(n)).label = ((Keyboard.Key)localArrayList.get(i1)).label;
        ((Keyboard.Key)localArrayList.get(i1)).codes[0] = i2;
        ((Keyboard.Key)localArrayList.get(i1)).label = localCharSequence;
        n++;
        break label49;
        int i5 = ((Keyboard.Key)localList.get(i3)).codes[0];
        if ((i5 >= 48) && (i5 <= 57))
        {
          ((Keyboard.Key)localList.get(i3)).codes[0] = ((Keyboard.Key)localArrayList.get(i4)).codes[0];
          ((Keyboard.Key)localList.get(i3)).label = ((Keyboard.Key)localArrayList.get(i4)).label;
        }
        i6 = i4 + 1;
      }
      while (i6 == localArrayList.size());
      i3++;
    }
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.cmbsafe.a
 * JD-Core Version:    0.6.0
 */