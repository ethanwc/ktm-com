package com.com.navapp.logging;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.com.kmrc.KmrcVersion;
import com.com.navapp.Globals;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.managers.OptionsManager;
import com.here.android.ui.common.Version;

public class AppInfo
{
  public AppInfo() {}
  
  public static void logAppInfo()
  {
    RealmLog.append("VERSION_NAME", "2.3.2.566-release");
    RealmLog.append("VERSION_CODE", String.valueOf(566));
    RealmLog.append("KMRC_VERSION", KmrcVersion.getVersion());
    RealmLog.append("HERE_SDK_VERSION", Version.getSdkVersion());
    RealmLog.append("BUILD", Globals.getContext().getString(2131558447));
    RealmLog.append("MANUFACTURER_NAME", Build.MANUFACTURER);
    RealmLog.append("MODEL_NAME", Build.MODEL);
    RealmLog.append("OS_TYPE", "Android");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Build.VERSION.RELEASE);
    localStringBuilder.append(" (SDK:");
    localStringBuilder.append(Build.VERSION.SDK_INT);
    localStringBuilder.append(")");
    RealmLog.append("OS_VERSION", localStringBuilder.toString());
    RealmLog.i("INSTALLED_MAPS", OfflineMapsManager.getInstance().getInstalledCountries().toString());
    RealmLog.i("SETTINGS", OptionsManager.getInstance().toString());
  }
}
