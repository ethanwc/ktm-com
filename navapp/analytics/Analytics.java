package com.com.navapp.analytics;

import android.os.BaseBundle;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.com.navapp.Globals;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.here.android.ui.common.GeoCoordinate;
import com.here.android.ui.common.GeoPosition;
import com.here.android.ui.routing.Maneuver;

public class Analytics
{
  private static final String EVENT_NAVIGATION_END = "navigation_end";
  private static final String EVENT_NAVIGATION_MANEUVER = "navigation_maneuver";
  private static final String EVENT_NAVIGATION_START = "navigation_start";
  private static final String EVENT_NAVIGATION_TIME = "navigation_time";
  private static final String PARAM_MANEUVER_NAME = "maneuver_name";
  private static final String USER_DEVICE_NAME = "device_name";
  private static final String USER_OS_VERSION = "os_version";
  private static Analytics sInstance;
  private final FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(Globals.getContext());
  private GeoPosition mLastPosition = null;
  private long mNavigationStartTime = 0L;
  
  private Analytics() {}
  
  private String coords()
  {
    Object localObject = mLastPosition;
    if (localObject != null) {
      localObject = ((GeoPosition)localObject).getCoordinate();
    } else {
      localObject = null;
    }
    return string((GeoCoordinate)localObject);
  }
  
  public static Analytics getInstance()
  {
    if (sInstance == null) {
      sInstance = new Analytics();
    }
    return sInstance;
  }
  
  private static String string(GeoCoordinate paramGeoCoordinate)
  {
    if ((paramGeoCoordinate != null) && (paramGeoCoordinate.isValid()))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramGeoCoordinate.getLatitude());
      localStringBuilder.append(",");
      localStringBuilder.append(paramGeoCoordinate.getLongitude());
      return localStringBuilder.toString();
    }
    return "<UNKKNOWN>";
  }
  
  public void logManeuver(Maneuver paramManeuver)
  {
    if (paramManeuver != null)
    {
      String str = string(paramManeuver.getCoordinate());
      Bundle localBundle = new Bundle();
      localBundle.putString("maneuver_name", paramManeuver.getIcon().name());
      localBundle.putString("location", str);
      mFirebaseAnalytics.logEvent("navigation_maneuver", localBundle);
    }
  }
  
  public void logNavigationEnd()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("location", coords());
    mFirebaseAnalytics.logEvent("navigation_end", localBundle);
    long l = mNavigationStartTime;
    if (l > 0L)
    {
      double d = (System.currentTimeMillis() - l) / 3600000.0D;
      localBundle = new Bundle();
      localBundle.putDouble("value", d);
      mFirebaseAnalytics.logEvent("navigation_time", localBundle);
      mNavigationStartTime = 0L;
    }
  }
  
  public void logNavigationStart()
  {
    mNavigationStartTime = System.currentTimeMillis();
    Bundle localBundle = new Bundle();
    localBundle.putString("location", coords());
    mFirebaseAnalytics.logEvent("navigation_start", localBundle);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mFirebaseAnalytics.setAnalyticsCollectionEnabled(paramBoolean);
    if (paramBoolean)
    {
      mFirebaseAnalytics.setUserProperty("device_name", String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL }));
      mFirebaseAnalytics.setUserProperty("os_version", String.format("%s", new Object[] { Build.VERSION.RELEASE }));
    }
  }
  
  public void setPosition(GeoPosition paramGeoPosition)
  {
    mLastPosition = paramGeoPosition;
  }
}
