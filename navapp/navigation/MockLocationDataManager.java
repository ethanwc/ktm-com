package com.com.navapp.navigation;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.com.navapp.Globals;
import com.here.android.ui.common.GeoCoordinate;
import java.io.PrintStream;

public class MockLocationDataManager
{
  private static final String FLOAT = com.ktm.navapp.navigation.MockLocationDataManager.class.getSimpleName();
  private static MockLocationDataManager ourInstance = new MockLocationDataManager();
  private LocationManager mLocationManager = (LocationManager)Globals.getContext().getSystemService("location");
  
  private MockLocationDataManager()
  {
    LocationManager localLocationManager = mLocationManager;
    if (localLocationManager != null)
    {
      localLocationManager.addTestProvider("network", false, false, false, false, false, true, true, 0, 5);
      mLocationManager.setTestProviderEnabled("network", true);
    }
  }
  
  public static MockLocationDataManager getInstance()
  {
    return ourInstance;
  }
  
  public void setLocation(GeoCoordinate paramGeoCoordinate)
  {
    Object localObject = new Location("network");
    ((Location)localObject).setLatitude(paramGeoCoordinate.getLatitude());
    ((Location)localObject).setLongitude(paramGeoCoordinate.getLongitude());
    ((Location)localObject).setAltitude(paramGeoCoordinate.getAltitude());
    ((Location)localObject).setTime(System.currentTimeMillis());
    ((Location)localObject).setAccuracy(5.0F);
    ((Location)localObject).setElapsedRealtimeNanos(10L);
    mLocationManager.setTestProviderLocation("network", (Location)localObject);
    localObject = System.out;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MOCK coordinate:");
    localStringBuilder.append(paramGeoCoordinate.toString());
    ((PrintStream)localObject).println(localStringBuilder.toString());
  }
  
  public void stop()
  {
    mLocationManager.removeTestProvider("network");
  }
}
