package com.com.navapp.ui.onboarding.permissions;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.core.content.ContextCompat;
import androidx.core.package_8.ActivityCompat;
import com.com.navapp.ui.base.NavAppBaseActivity;
import java.util.ArrayList;
import java.util.List;

public abstract class PermissionActivity
  extends NavAppBaseActivity
{
  public static final String ACCESS_BACKGROUND_LOCATION = "android.permission.ACCESS_BACKGROUND_LOCATION";
  public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
  private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
  
  public PermissionActivity() {}
  
  protected String[] getLocationPermissions()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("android.permission.ACCESS_FINE_LOCATION");
    if (Build.VERSION.SDK_INT > 28) {
      localArrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
    }
    return (String[])localArrayList.toArray(new String[0]);
  }
  
  protected abstract String[] getRequiredPermissions();
  
  protected abstract void onDenyPermission();
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    if (paramInt != 1) {
      return;
    }
    paramInt = paramArrayOfString.length - 1;
    while (paramInt >= 0)
    {
      if (paramArrayOfInt[paramInt] != 0)
      {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, paramArrayOfString[paramInt]))
        {
          onDenyPermission();
          return;
        }
        paramArrayOfString = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        paramArrayOfString.setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(paramArrayOfString);
        return;
      }
      paramInt -= 1;
    }
    permissionsGranted();
  }
  
  protected abstract void permissionsGranted();
  
  protected void requestPermissions(String[] paramArrayOfString)
  {
    int k = paramArrayOfString.length;
    int i = 0;
    int j = 0;
    while (i < k)
    {
      if (ContextCompat.checkSelfPermission(this, paramArrayOfString[i]) == -1) {
        j = -1;
      }
      i += 1;
    }
    if (j != 0)
    {
      ActivityCompat.requestPermissions(this, paramArrayOfString, 1);
      return;
    }
    permissionsGranted();
  }
}
