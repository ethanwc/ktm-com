package com.com.navapp.guidance;

import com.here.android.ui.guidance.NavigationManager.UnitSystem;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class LengthFormatter
{
  private static final char DECIMAL_SEPARATOR;
  private static final double DEFAULT_ACCURACY = 0.9144D;
  private static final double FOOT = 0.3048D;
  private static final DecimalFormat FORMAT_1 = new DecimalFormat("0.0");
  private static final double LOAD_FACTOR = 1.0D;
  private static final double METERS_PER_STATUTE_MILE = 1609.344D;
  private static double mLower = 0.0D;
  private static String mLowerUnit = "m";
  private static NavigationManager.UnitSystem mUnitSystem;
  private static double mUpper = 0.0D;
  private static String mUpperUnit;
  private static final double msPerSecond = 1000.0D;
  
  static
  {
    DECIMAL_SEPARATOR = FORMAT_1.getDecimalFormatSymbols().getDecimalSeparator();
    mUnitSystem = NavigationManager.UnitSystem.METRIC;
    mUpper = 1000.0D;
    mLower = 1.0D;
    mUpperUnit = "km";
  }
  
  public LengthFormatter() {}
  
  public static String getFormatted(long paramLong)
  {
    try
    {
      Object localObject = getFormattedExt(paramLong);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(localObject[0]);
      localStringBuilder.append(" ");
      localStringBuilder.append(localObject[1]);
      localObject = localStringBuilder.toString();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static String[] getFormattedExt(long paramLong)
  {
    try
    {
      int i = 1.$SwitchMap$com$here$android$mpa$guidance$NavigationManager$UnitSystem[mUnitSystem.ordinal()];
      String str;
      if (i != 2)
      {
        if (i != 3)
        {
          if (paramLong < 0L)
          {
            localObject = mUpperUnit;
            return new String[] { "0", localObject };
          }
          if (paramLong < 998L)
          {
            d1 = paramLong;
            paramLong = Math.round(d1 / mLower / 5.0D);
            localObject = mLowerUnit;
            return new String[] { String.valueOf(paramLong * 5L), localObject };
          }
          if (paramLong < 9950L)
          {
            d1 = paramLong;
            paramLong = Math.round(d1 / mUpper * 10.0D);
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(String.valueOf(paramLong / 10L));
            ((StringBuilder)localObject).append(DECIMAL_SEPARATOR);
            ((StringBuilder)localObject).append(String.valueOf(paramLong % 10L));
            localObject = ((StringBuilder)localObject).toString();
            str = mUpperUnit;
            return new String[] { localObject, str };
          }
          d1 = paramLong;
          paramLong = Math.round(d1 / mUpper);
          localObject = mUpperUnit;
          return new String[] { String.valueOf(paramLong), localObject };
        }
        if (paramLong < 0L)
        {
          localObject = mUpperUnit;
          return new String[] { "0", localObject };
        }
        if (paramLong <= 289L)
        {
          d1 = paramLong;
          paramLong = Math.round(d1 / mLower / 10.0D);
          localObject = mLowerUnit;
          return new String[] { String.valueOf(paramLong * 10L), localObject };
        }
        d1 = paramLong;
        d1 /= mUpper;
        if (d1 < 1.1D)
        {
          long l = Math.round(d1 * 10.0D);
          paramLong = l;
          if (l > 10L) {
            paramLong = 10L;
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(String.valueOf(paramLong / 10L));
          ((StringBuilder)localObject).append(DECIMAL_SEPARATOR);
          ((StringBuilder)localObject).append(String.valueOf(paramLong % 10L));
          localObject = ((StringBuilder)localObject).toString();
          str = mUpperUnit;
          return new String[] { localObject, str };
        }
        if (d1 < 9.95D)
        {
          paramLong = Math.round(d1 * 10.0D);
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(String.valueOf(paramLong / 10L));
          ((StringBuilder)localObject).append(DECIMAL_SEPARATOR);
          ((StringBuilder)localObject).append(String.valueOf(paramLong % 10L));
          localObject = ((StringBuilder)localObject).toString();
          str = mUpperUnit;
          return new String[] { localObject, str };
        }
        paramLong = Math.round(d1);
        localObject = mUpperUnit;
        return new String[] { String.valueOf(paramLong), localObject };
      }
      if (paramLong < 0L)
      {
        localObject = mUpperUnit;
        return new String[] { "0", localObject };
      }
      if (paramLong <= 802L)
      {
        d1 = paramLong;
        paramLong = Math.round(d1 / mLower / 5.0D);
        localObject = mLowerUnit;
        return new String[] { String.valueOf(paramLong * 5L), localObject };
      }
      double d1 = paramLong;
      double d2 = d1 / mUpper;
      if (d2 < 0.95D)
      {
        paramLong = Math.round(d1 / mUpper * 10.0D);
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(String.valueOf(paramLong / 10L));
        ((StringBuilder)localObject).append(DECIMAL_SEPARATOR);
        ((StringBuilder)localObject).append(String.valueOf(paramLong % 10L));
        localObject = ((StringBuilder)localObject).toString();
        str = mUpperUnit;
        return new String[] { localObject, str };
      }
      if (d2 < 9.95D)
      {
        paramLong = Math.round(d1 / mUpper * 10.0D);
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(String.valueOf(paramLong / 10L));
        ((StringBuilder)localObject).append(DECIMAL_SEPARATOR);
        ((StringBuilder)localObject).append(String.valueOf(paramLong % 10L));
        localObject = ((StringBuilder)localObject).toString();
        str = mUpperUnit;
        return new String[] { localObject, str };
      }
      paramLong = Math.round(d2);
      Object localObject = mUpperUnit;
      return new String[] { String.valueOf(paramLong), localObject };
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static String[] getFormattedExtOLD(long paramLong)
  {
    double d1 = paramLong;
    try
    {
      double d2 = d1 / mUpper;
      if (d2 > 99.9D)
      {
        paramLong = Math.round(d2);
        localObject = mUpperUnit;
        return new String[] { String.valueOf(paramLong), localObject };
      }
      if (d2 > 0.9945D)
      {
        paramLong = Math.round(d2 * 10.0D);
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(String.valueOf(paramLong / 10L));
        ((StringBuilder)localObject).append(DECIMAL_SEPARATOR);
        ((StringBuilder)localObject).append(String.valueOf(paramLong % 10L));
        localObject = ((StringBuilder)localObject).toString();
        String str = mUpperUnit;
        return new String[] { localObject, str };
      }
      d1 /= mLower;
      if (d1 > 99.9D)
      {
        paramLong = Math.round(d1 / 10.0D);
        localObject = mLowerUnit;
        return new String[] { String.valueOf(paramLong * 10L), localObject };
      }
      paramLong = Math.round(d1);
      Object localObject = mLowerUnit;
      return new String[] { String.valueOf(paramLong), localObject };
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public static void setUnitSystem(NavigationManager.UnitSystem paramUnitSystem)
  {
    try
    {
      mUnitSystem = paramUnitSystem;
      int i = 1.$SwitchMap$com$here$android$mpa$guidance$NavigationManager$UnitSystem[paramUnitSystem.ordinal()];
      if (i != 1)
      {
        if (i != 2)
        {
          if (i == 3)
          {
            mUpper = 1609.344D;
            mLower = 0.3048D;
            mUpperUnit = "mi";
            mLowerUnit = "ft";
          }
        }
        else
        {
          mUpper = 1609.344D;
          mLower = 0.9144D;
          mUpperUnit = "mi";
          mLowerUnit = "yd";
        }
      }
      else
      {
        mUpper = 1000.0D;
        mLower = 1.0D;
        mUpperUnit = "km";
        mLowerUnit = "m";
      }
      return;
    }
    catch (Throwable paramUnitSystem)
    {
      throw paramUnitSystem;
    }
  }
}
