package com.com.navapp.downloading.offlinemaps;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import com.com.navapp.Globals;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.MapSettings;
import com.jakewharton.processphoenix.ProcessPhoenix;
import com.ktm.navapp.downloading.offlinemaps.MapPackageInfo;
import com.ktm.navapp.downloading.offlinemaps.MyRideMapService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapStorageLocation
{
  public static final boolean AUTO_DELETE_AFTER_SWITCH = false;
  public static final boolean AUTO_DOWNLOAD_AFTER_SWITCH = false;
  private static final String KEY_DOWNLOAD_LIST = "MapStorageLocation.downloadList";
  private static final String KEY_SWITCHED_STORAGE = "MapStorageLocation.switchedStorage";
  private static final String KEY_USE_EXTERNAL_STORAGE = "MapStorageLocation.useExternalStorage";
  private static final String MAPS_DIR_NAME = ".here-maps";
  private static final String MAP_SERVICE_INTENT = "com.ktm.myride.MyRideMapService";
  private static final String TAG = com.ktm.navapp.downloading.offlinemaps.MapStorageLocation.class.getSimpleName();
  public static final boolean USE_SHARED_INTERNAL_PATH = true;
  
  private MapStorageLocation() {}
  
  public static void clearDownloadList()
  {
    Storage.save("MapStorageLocation.downloadList", null);
  }
  
  private static void deleteRecursive(File paramFile)
  {
    if (paramFile != null)
    {
      if (paramFile.isDirectory())
      {
        Object localObject = TAG;
        String str = paramFile.getAbsolutePath();
        int i = 0;
        RealmLog.d((String)localObject, "deleteRecursive(): %s", new Object[] { str });
        localObject = paramFile.listFiles();
        int j = localObject.length;
        while (i < j)
        {
          deleteRecursive(localObject[i]);
          i += 1;
        }
      }
      paramFile.delete();
    }
  }
  
  public static boolean extStorageMissing()
  {
    return (getUseExternalStorage()) && (!isExternalMounted(getExternalPath()));
  }
  
  public static long getAvailable()
  {
    if (getUseExternalStorage()) {
      return getExternalAvailable();
    }
    return getInternalAvailable();
  }
  
  public static List getDownloadList()
  {
    DownloadList localDownloadList = (DownloadList)Storage.load("MapStorageLocation.downloadList", com.ktm.navapp.downloading.offlinemaps.MapStorageLocation.DownloadList.class);
    if (countries.isEmpty()) {
      return null;
    }
    return countries;
  }
  
  public static long getExternalAvailable()
  {
    File localFile = getExternalPath();
    if (localFile != null) {
      return new StatFs(localFile.getPath()).getAvailableBytes();
    }
    return 0L;
  }
  
  public static File getExternalPath()
  {
    Object localObject = Globals.getContext().getExternalFilesDirs(null);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      File localFile = localObject[i];
      if ((localFile != null) && (!Environment.isExternalStorageEmulated(localFile)))
      {
        localObject = new File(localFile, ".here-maps");
        ((File)localObject).mkdirs();
        if (((File)localObject).isDirectory()) {
          return localObject;
        }
        return null;
      }
      i += 1;
    }
    return null;
  }
  
  public static long getExternalTotal()
  {
    File localFile = getExternalPath();
    if (localFile != null) {
      return new StatFs(localFile.getPath()).getTotalBytes();
    }
    return 1L;
  }
  
  public static long getInternalAvailable()
  {
    return new StatFs(getInternalPath().getPath()).getAvailableBytes();
  }
  
  public static File getInternalPath()
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), ".here-maps");
    localFile.mkdirs();
    return localFile;
  }
  
  public static long getInternalTotal()
  {
    return new StatFs(getInternalPath().getPath()).getTotalBytes();
  }
  
  public static long getTotal()
  {
    if (getUseExternalStorage()) {
      return getExternalTotal();
    }
    return getInternalTotal();
  }
  
  public static boolean getUseExternalStorage()
  {
    return Storage.getBoolean("MapStorageLocation.useExternalStorage", false);
  }
  
  public static boolean initMapStorage()
  {
    boolean bool1 = getUseExternalStorage();
    Boolean localBoolean = Boolean.valueOf(false);
    boolean bool2 = Storage.getBoolean("MapStorageLocation.switchedStorage", false);
    File localFile1 = getInternalPath();
    File localFile2 = getExternalPath();
    Object localObject;
    if (bool1) {
      localObject = localFile2;
    } else {
      localObject = localFile1;
    }
    boolean bool3 = isExternalMounted(localFile2);
    RealmLog.append(TAG, "initMapStorage()\n  AUTO_DOWNLOAD_AFTER_SWITCH: %b,\n  AUTO_DELETE_AFTER_SWITCH: %b,\n  SHARED_INTERNAL: %b,\n  internal_path: %s,\n  external_path: %s,\n  external_mounted: %b,\n  use_external: %b,\n  switched: %b", new Object[] { localBoolean, localBoolean, Boolean.valueOf(true), localFile1, localFile2, Boolean.valueOf(bool3), Boolean.valueOf(bool1), Boolean.valueOf(bool2) });
    if (!bool1)
    {
      bool1 = true;
    }
    else
    {
      if (localObject != null) {
        localObject = ((File)localObject).getPath();
      } else {
        localObject = null;
      }
      bool1 = MapSettings.setIsolatedDiskCacheRootPath((String)localObject, "com.ktm.myride.MyRideMapService");
    }
    if (bool2)
    {
      OfflineMapsManager.clearPackages();
      VoiceManager.clearPackages();
      Storage.enable("MapStorageLocation.switchedStorage", false);
    }
    RealmLog.append(TAG, "initMapStorage(): %b", new Object[] { Boolean.valueOf(bool1) });
    return bool1;
  }
  
  private static boolean isExternalMounted(File paramFile)
  {
    return (paramFile != null) && ("mounted".equals(Environment.getExternalStorageState(paramFile)));
  }
  
  public static void setUseExternalStorage(boolean paramBoolean1, boolean paramBoolean2)
  {
    RealmLog.append(TAG, "setUseExternalStorage( %b ):", new Object[] { Boolean.valueOf(paramBoolean1) });
    if (paramBoolean1 != getUseExternalStorage())
    {
      Storage.enable("MapStorageLocation.useExternalStorage", paramBoolean1);
      Storage.enable("MapStorageLocation.switchedStorage", true);
      if (paramBoolean2)
      {
        Storage.forceCommit();
        Globals.getContext().stopService(new Intent(Globals.getContext(), MyRideMapService.class));
        ProcessPhoenix.triggerRebirth(Globals.getContext());
      }
    }
  }
  
  public class DownloadList
  {
    public final List<MapPackageInfo> countries = new ArrayList();
    
    public DownloadList() {}
  }
}
