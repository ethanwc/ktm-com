package com.com.navapp.navigation;

import android.app.AppOpsManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.os.SystemClock;
import com.com.navapp.Globals;
import com.here.android.ui.common.GeoCoordinate;

public class MockLocationService
  extends IntentService
{
  MockLocationDataManager mMock;
  
  public MockLocationService()
  {
    super(com.ktm.navapp.navigation.MockLocationService.class.getName());
  }
  
  public boolean isMockLocationEnabled()
  {
    try
    {
      Object localObject = Globals.getContext().getSystemService("appops");
      localObject = (AppOpsManager)localObject;
      if (localObject != null)
      {
        int i = ((AppOpsManager)localObject).checkOp("android:mock_location", Process.myUid(), "com.ktm.myride");
        if (i == 0) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  protected void onHandleIntent(Intent paramIntent) {}
  
  public void simulateSomethingCustomRoute()
  {
    SystemClock.sleep(5000L);
    mMock.setLocation(new GeoCoordinate(53.428858D, 14.552649D));
    SystemClock.sleep(5000L);
    mMock.setLocation(new GeoCoordinate(53.422147D, 14.560524D));
    SystemClock.sleep(5000L);
  }
}
