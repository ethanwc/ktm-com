package com.com.navapp.elevation;

import com.com.navapp.ui.widgets.ChartView.HeightUnit;
import com.here.android.ui.common.GeoCoordinate;
import java.util.List;

public class ChartCalculator
{
  private static final double FOOT_IN_METERS = 3.28084D;
  private static final float MAXIMUM_POS = 0.8F;
  private static final float MIDDLE_POS = 0.55F;
  private static final float MINIMUM_POS = 0.4F;
  private float mAllDistance = 0.0F;
  private float mMaxDistance = 0.0F;
  private float mMinDistance = 0.0F;
  private int max_i = 0;
  private int min_i = 0;
  
  public ChartCalculator() {}
  
  public static String getHeightUnit(ChartView.HeightUnit paramHeightUnit)
  {
    if (paramHeightUnit == ChartView.HeightUnit.METRES) {
      return "m";
    }
    return "ft";
  }
  
  public static double getHeightUnitMultiplier(ChartView.HeightUnit paramHeightUnit)
  {
    if (paramHeightUnit == ChartView.HeightUnit.METRES) {
      return 1.0D;
    }
    return 3.28084D;
  }
  
  public static float getMiddleLineY(float paramFloat)
  {
    return paramFloat * 0.55F;
  }
  
  public float calculateDistance(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 / mAllDistance * paramFloat2;
  }
  
  public void doCalculates(List paramList)
  {
    mAllDistance = 0.0F;
    mMaxDistance = 0.0F;
    mMinDistance = 0.0F;
    int i = 0;
    min_i = 0;
    max_i = 0;
    while (i < paramList.size())
    {
      if (i > 0) {
        mAllDistance = ((float)(mAllDistance + ((GeoCoordinate)paramList.get(i - 1)).distanceTo((GeoCoordinate)paramList.get(i))));
      }
      if ((int)((GeoCoordinate)paramList.get(i)).getAltitude() != 1073741824)
      {
        if (((GeoCoordinate)paramList.get(i)).getAltitude() > ((GeoCoordinate)paramList.get(max_i)).getAltitude())
        {
          max_i = i;
          mMaxDistance = mAllDistance;
        }
        if (((GeoCoordinate)paramList.get(i)).getAltitude() < ((GeoCoordinate)paramList.get(min_i)).getAltitude())
        {
          min_i = i;
          mMinDistance = mAllDistance;
        }
      }
      i += 1;
    }
  }
  
  public int getMax_i()
  {
    return max_i;
  }
  
  public float getMaximumX(float paramFloat)
  {
    return mMaxDistance / mAllDistance * paramFloat;
  }
  
  public float getMaximumY(float paramFloat)
  {
    return paramFloat - 0.83F * paramFloat;
  }
  
  public int getMiddleValue(float paramFloat1, float paramFloat2, ChartView.HeightUnit paramHeightUnit)
  {
    return (int)((paramFloat1 + (paramFloat2 - paramFloat1) * 0.375F) * getHeightUnitMultiplier(paramHeightUnit));
  }
  
  public int getMin_i()
  {
    return min_i;
  }
  
  public float getMinimumX(float paramFloat)
  {
    return mMinDistance / mAllDistance * paramFloat;
  }
  
  public float getMinimumY(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 - (0.4F - paramFloat2) * paramFloat1;
  }
  
  public float getYonChart(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float f = 0.4F * paramFloat4;
    return paramFloat4 - f - (paramFloat1 - paramFloat2) / (paramFloat3 - paramFloat2) * f;
  }
  
  public boolean isElevationIncorrect(int paramInt)
  {
    return paramInt == 1073741824;
  }
}
