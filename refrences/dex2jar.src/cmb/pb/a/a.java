package cmb.pb.a;

public final class a
{
  private static final char[] a;
  private static final byte[] b;
  private static final char[] c;
  private static final byte[] d;

  static
  {
    int i = 0;
    char[] arrayOfChar1 = new char[64];
    arrayOfChar1[0] = 65;
    arrayOfChar1[1] = 66;
    arrayOfChar1[2] = 67;
    arrayOfChar1[3] = 68;
    arrayOfChar1[4] = 69;
    arrayOfChar1[5] = 70;
    arrayOfChar1[6] = 71;
    arrayOfChar1[7] = 72;
    arrayOfChar1[8] = 73;
    arrayOfChar1[9] = 74;
    arrayOfChar1[10] = 75;
    arrayOfChar1[11] = 76;
    arrayOfChar1[12] = 77;
    arrayOfChar1[13] = 78;
    arrayOfChar1[14] = 79;
    arrayOfChar1[15] = 80;
    arrayOfChar1[16] = 81;
    arrayOfChar1[17] = 82;
    arrayOfChar1[18] = 83;
    arrayOfChar1[19] = 84;
    arrayOfChar1[20] = 85;
    arrayOfChar1[21] = 86;
    arrayOfChar1[22] = 87;
    arrayOfChar1[23] = 88;
    arrayOfChar1[24] = 89;
    arrayOfChar1[25] = 90;
    arrayOfChar1[26] = 97;
    arrayOfChar1[27] = 98;
    arrayOfChar1[28] = 99;
    arrayOfChar1[29] = 100;
    arrayOfChar1[30] = 101;
    arrayOfChar1[31] = 102;
    arrayOfChar1[32] = 103;
    arrayOfChar1[33] = 104;
    arrayOfChar1[34] = 105;
    arrayOfChar1[35] = 106;
    arrayOfChar1[36] = 107;
    arrayOfChar1[37] = 108;
    arrayOfChar1[38] = 109;
    arrayOfChar1[39] = 110;
    arrayOfChar1[40] = 111;
    arrayOfChar1[41] = 112;
    arrayOfChar1[42] = 113;
    arrayOfChar1[43] = 114;
    arrayOfChar1[44] = 115;
    arrayOfChar1[45] = 116;
    arrayOfChar1[46] = 117;
    arrayOfChar1[47] = 118;
    arrayOfChar1[48] = 119;
    arrayOfChar1[49] = 120;
    arrayOfChar1[50] = 121;
    arrayOfChar1[51] = 122;
    arrayOfChar1[52] = 48;
    arrayOfChar1[53] = 49;
    arrayOfChar1[54] = 50;
    arrayOfChar1[55] = 51;
    arrayOfChar1[56] = 52;
    arrayOfChar1[57] = 53;
    arrayOfChar1[58] = 54;
    arrayOfChar1[59] = 55;
    arrayOfChar1[60] = 56;
    arrayOfChar1[61] = 57;
    arrayOfChar1[62] = 43;
    arrayOfChar1[63] = 47;
    a = arrayOfChar1;
    b = new byte[''];
    int j = 0;
    int k;
    label409: int m;
    if (j >= b.length)
    {
      k = 0;
      if (k < a.length)
        break label912;
      char[] arrayOfChar2 = new char[64];
      arrayOfChar2[0] = 65;
      arrayOfChar2[1] = 66;
      arrayOfChar2[2] = 67;
      arrayOfChar2[3] = 68;
      arrayOfChar2[4] = 69;
      arrayOfChar2[5] = 70;
      arrayOfChar2[6] = 71;
      arrayOfChar2[7] = 72;
      arrayOfChar2[8] = 73;
      arrayOfChar2[9] = 74;
      arrayOfChar2[10] = 75;
      arrayOfChar2[11] = 76;
      arrayOfChar2[12] = 77;
      arrayOfChar2[13] = 78;
      arrayOfChar2[14] = 79;
      arrayOfChar2[15] = 80;
      arrayOfChar2[16] = 81;
      arrayOfChar2[17] = 82;
      arrayOfChar2[18] = 83;
      arrayOfChar2[19] = 84;
      arrayOfChar2[20] = 85;
      arrayOfChar2[21] = 86;
      arrayOfChar2[22] = 87;
      arrayOfChar2[23] = 88;
      arrayOfChar2[24] = 89;
      arrayOfChar2[25] = 90;
      arrayOfChar2[26] = 97;
      arrayOfChar2[27] = 98;
      arrayOfChar2[28] = 99;
      arrayOfChar2[29] = 100;
      arrayOfChar2[30] = 101;
      arrayOfChar2[31] = 102;
      arrayOfChar2[32] = 103;
      arrayOfChar2[33] = 104;
      arrayOfChar2[34] = 105;
      arrayOfChar2[35] = 106;
      arrayOfChar2[36] = 107;
      arrayOfChar2[37] = 108;
      arrayOfChar2[38] = 109;
      arrayOfChar2[39] = 110;
      arrayOfChar2[40] = 111;
      arrayOfChar2[41] = 112;
      arrayOfChar2[42] = 113;
      arrayOfChar2[43] = 114;
      arrayOfChar2[44] = 115;
      arrayOfChar2[45] = 116;
      arrayOfChar2[46] = 117;
      arrayOfChar2[47] = 118;
      arrayOfChar2[48] = 119;
      arrayOfChar2[49] = 120;
      arrayOfChar2[50] = 121;
      arrayOfChar2[51] = 122;
      arrayOfChar2[52] = 48;
      arrayOfChar2[53] = 49;
      arrayOfChar2[54] = 50;
      arrayOfChar2[55] = 51;
      arrayOfChar2[56] = 52;
      arrayOfChar2[57] = 53;
      arrayOfChar2[58] = 54;
      arrayOfChar2[59] = 55;
      arrayOfChar2[60] = 56;
      arrayOfChar2[61] = 57;
      arrayOfChar2[62] = 42;
      arrayOfChar2[63] = 45;
      c = arrayOfChar2;
      d = new byte[''];
      m = 0;
      label881: if (m < d.length)
        break label929;
    }
    while (true)
    {
      if (i >= c.length)
      {
        return;
        b[j] = 127;
        j++;
        break;
        label912: b[a[k]] = (byte)k;
        k++;
        break label409;
        label929: d[m] = 127;
        m++;
        break label881;
      }
      d[c[i]] = (byte)i;
      i++;
    }
  }

  public static String a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    int i;
    for (String str = ""; ; str = "")
    {
      return str;
      i = paramArrayOfByte.length;
      if (i > 0)
        break;
    }
    char[] arrayOfChar = new char[4 + 4 * (i / 3)];
    int j = i + 0;
    int k = 0;
    int m = j;
    int n = 0;
    label48: if (m < 3)
    {
      if (m != 1)
        break label278;
      int i9 = 0xFF & paramArrayOfByte[k];
      int i10 = n + 1;
      arrayOfChar[n] = a[(i9 >> 2)];
      int i11 = i10 + 1;
      arrayOfChar[i10] = a[(0x3F & i9 << 4)];
      int i12 = i11 + 1;
      arrayOfChar[i11] = '=';
      n = i12 + 1;
      arrayOfChar[i12] = '=';
    }
    while (true)
    {
      str = new String(arrayOfChar, 0, n);
      break;
      int i1 = ((0xFF & paramArrayOfByte[k]) << 16) + ((0xFF & paramArrayOfByte[(k + 1)]) << 8) + (0xFF & paramArrayOfByte[(k + 2)]);
      int i2 = n + 1;
      arrayOfChar[n] = a[(i1 >> 18)];
      int i3 = i2 + 1;
      arrayOfChar[i2] = a[(0x3F & i1 >> 12)];
      int i4 = i3 + 1;
      arrayOfChar[i3] = a[(0x3F & i1 >> 6)];
      n = i4 + 1;
      arrayOfChar[i4] = a[(i1 & 0x3F)];
      k += 3;
      m -= 3;
      break label48;
      label278: if (m != 2)
        continue;
      int i5 = ((0xFF & paramArrayOfByte[k]) << 8) + (0xFF & paramArrayOfByte[(k + 1)]);
      int i6 = n + 1;
      arrayOfChar[n] = a[(i5 >> 10)];
      int i7 = i6 + 1;
      arrayOfChar[i6] = a[(0x3F & i5 >> 4)];
      int i8 = i7 + 1;
      arrayOfChar[i7] = a[(0x3F & i5 << 2)];
      n = i8 + 1;
      arrayOfChar[i8] = '=';
    }
  }

  public static byte[] a(String paramString)
  {
    char[] arrayOfChar = new char[4];
    byte[] arrayOfByte1 = new byte[3 + 3 * (paramString.length() / 4)];
    int i = 0;
    int j = 0;
    int k = 0;
    byte[] arrayOfByte2;
    label43: int n;
    int i1;
    label110: int i2;
    int i3;
    int i4;
    int i5;
    int i8;
    if (i >= paramString.length())
    {
      if (j == arrayOfByte1.length)
      {
        arrayOfByte2 = arrayOfByte1;
        return arrayOfByte2;
      }
    }
    else
    {
      int m = paramString.charAt(i);
      if ((m == 61) || ((m < b.length) && (b[m] != 127)))
      {
        n = k + 1;
        arrayOfChar[k] = m;
        if (n != arrayOfChar.length)
          break label376;
        if (arrayOfChar[3] != '=')
          break label370;
        i1 = 2;
        if (arrayOfChar[2] == '=')
          i1 = 1;
        i2 = b[arrayOfChar[0]];
        i3 = b[arrayOfChar[1]];
        i4 = b[arrayOfChar[2]];
        i5 = b[arrayOfChar[3]];
        switch (i1)
        {
        default:
          arrayOfByte1[j] = (byte)(0xFC & i2 << 2 | 0x3 & i3 >> 4);
          i8 = 1;
          label203: j += i8;
        case 2:
        case 3:
        }
      }
    }
    label370: label376: for (k = 0; ; k = n)
    {
      i++;
      break;
      int i9 = j + 1;
      arrayOfByte1[j] = (byte)(0xFC & i2 << 2 | 0x3 & i3 >> 4);
      arrayOfByte1[i9] = (byte)(0xF0 & i3 << 4 | 0xF & i4 >> 2);
      i8 = 2;
      break label203;
      int i6 = j + 1;
      arrayOfByte1[j] = (byte)(0xFC & i2 << 2 | 0x3 & i3 >> 4);
      int i7 = i6 + 1;
      arrayOfByte1[i6] = (byte)(0xF0 & i3 << 4 | 0xF & i4 >> 2);
      arrayOfByte1[i7] = (byte)(0xC0 & i4 << 6 | i5 & 0x3F);
      i8 = 3;
      break label203;
      arrayOfByte2 = new byte[j];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, j);
      break label43;
      i1 = 3;
      break label110;
    }
  }

  public static String b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null);
    int i;
    for (String str = ""; ; str = "")
    {
      return str;
      i = paramArrayOfByte.length;
      if (i > 0)
        break;
    }
    char[] arrayOfChar = new char[4 + 4 * (i / 3)];
    int j = i + 0;
    int k = 0;
    int m = j;
    int n = 0;
    label48: if (m < 3)
    {
      if (m != 1)
        break label278;
      int i9 = 0xFF & paramArrayOfByte[k];
      int i10 = n + 1;
      arrayOfChar[n] = c[(i9 >> 2)];
      int i11 = i10 + 1;
      arrayOfChar[i10] = c[(0x3F & i9 << 4)];
      int i12 = i11 + 1;
      arrayOfChar[i11] = '_';
      n = i12 + 1;
      arrayOfChar[i12] = '_';
    }
    while (true)
    {
      str = new String(arrayOfChar, 0, n);
      break;
      int i1 = ((0xFF & paramArrayOfByte[k]) << 16) + ((0xFF & paramArrayOfByte[(k + 1)]) << 8) + (0xFF & paramArrayOfByte[(k + 2)]);
      int i2 = n + 1;
      arrayOfChar[n] = c[(i1 >> 18)];
      int i3 = i2 + 1;
      arrayOfChar[i2] = c[(0x3F & i1 >> 12)];
      int i4 = i3 + 1;
      arrayOfChar[i3] = c[(0x3F & i1 >> 6)];
      n = i4 + 1;
      arrayOfChar[i4] = c[(i1 & 0x3F)];
      k += 3;
      m -= 3;
      break label48;
      label278: if (m != 2)
        continue;
      int i5 = ((0xFF & paramArrayOfByte[k]) << 8) + (0xFF & paramArrayOfByte[(k + 1)]);
      int i6 = n + 1;
      arrayOfChar[n] = c[(i5 >> 10)];
      int i7 = i6 + 1;
      arrayOfChar[i6] = c[(0x3F & i5 >> 4)];
      int i8 = i7 + 1;
      arrayOfChar[i7] = c[(0x3F & i5 << 2)];
      n = i8 + 1;
      arrayOfChar[i8] = '_';
    }
  }

  public static boolean b(String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0));
    for (int i = 1; ; i = 0)
      return i;
  }
}

/* Location:           d:\Users\Administrator\Desktop\111111_dex2jar.jar
 * Qualified Name:     cmb.pb.a.a
 * JD-Core Version:    0.6.0
 */