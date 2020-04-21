package com.com.navapp.managers;

import com.com.navapp.logging.RealmLog;
import com.com.navapp.utilities.Storage;
import com.here.android.ui.common.MapEngine;
import com.here.android.ui.guidance.NavigationManager.UnitSystem;

public class OptionsManager
{
  private static OptionsManager INSTANCE = new OptionsManager();
  public static final String KEY_MAP_TYPE;
  public static final String KEY_TRAFFIC_INFO;
  public static final String KEY_UNIT_SYSTEM;
  public static final String KEY_USE_OFFLINE_MAPS;
  static final String dataType = com.ktm.navapp.managers.OptionsManager.class.getSimpleName();
  private MapManager mMapManager;
  private String mMapType = null;
  private boolean mTrafficInformation;
  private NavigationManager.UnitSystem mUnitSystem;
  private boolean mUseOfflineMaps;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(dataType);
    localStringBuilder.append(".MAP_TYPE");
    KEY_MAP_TYPE = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(dataType);
    localStringBuilder.append(".TRAFFIC_INFORMATION");
    KEY_TRAFFIC_INFO = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(dataType);
    localStringBuilder.append(".USE_OFFLINE_MAPS");
    KEY_USE_OFFLINE_MAPS = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(dataType);
    localStringBuilder.append(".UNIT_SYSTEM");
    KEY_UNIT_SYSTEM = localStringBuilder.toString();
  }
  
  private OptionsManager() {}
  
  public static OptionsManager getInstance()
  {
    return INSTANCE;
  }
  
  public String getMapType()
  {
    try
    {
      String str = mMapType;
      return str;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean getTrafficInformation()
  {
    try
    {
      boolean bool = mTrafficInformation;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public NavigationManager.UnitSystem getUnitSystem()
  {
    try
    {
      NavigationManager.UnitSystem localUnitSystem = mUnitSystem;
      return localUnitSystem;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public boolean getUseOfflineMaps()
  {
    try
    {
      boolean bool = mUseOfflineMaps;
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void initialize()
  {
    if (mMapManager == null) {
      mMapManager = MapManager.getInstance();
    }
    mMapType = Storage.get(KEY_MAP_TYPE, "normal.day");
    mTrafficInformation = Storage.getBoolean(KEY_TRAFFIC_INFO, false);
    mUseOfflineMaps = Storage.getBoolean(KEY_USE_OFFLINE_MAPS, false);
    mUnitSystem = NavigationManager.UnitSystem.valueOf(Storage.get(KEY_UNIT_SYSTEM, NavigationManager.UnitSystem.METRIC.name()));
  }
  
  public void navigateWithOptions()
  {
    mMapManager.setMapType(mMapType);
    mMapManager.setTrafficInformation(Boolean.valueOf(mTrafficInformation));
    MapEngine.setOnline(mUseOfflineMaps ^ true);
  }
  
  public void setMapType(String paramString)
  {
    try
    {
      if (!mMapType.equals(paramString))
      {
        RealmLog.append(dataType, "setMapType( %s )", new Object[] { paramString });
        String str = KEY_MAP_TYPE;
        mMapType = paramString;
        Storage.put(str, paramString);
        mMapManager.setMapType(mMapType);
      }
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  public void setTrafficInformation(boolean paramBoolean)
  {
    try
    {
      if (mTrafficInformation != paramBoolean)
      {
        RealmLog.append(dataType, "setTrafficInformation( %b )", new Object[] { Boolean.valueOf(paramBoolean) });
        String str = KEY_TRAFFIC_INFO;
        mTrafficInformation = paramBoolean;
        Storage.enable(str, paramBoolean);
        mMapManager.setTrafficInformation(Boolean.valueOf(mTrafficInformation));
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setUnitSystem(NavigationManager.UnitSystem paramUnitSystem)
  {
    try
    {
      if (mUnitSystem != paramUnitSystem)
      {
        RealmLog.append(dataType, "setUnitSystem( %s )", new Object[] { paramUnitSystem.name() });
        String str = KEY_UNIT_SYSTEM;
        mUnitSystem = paramUnitSystem;
        Storage.put(str, paramUnitSystem.name());
      }
      return;
    }
    catch (Throwable paramUnitSystem)
    {
      throw paramUnitSystem;
    }
  }
  
  public void setUseOfflineMaps(boolean paramBoolean)
  {
    for (;;)
    {
      try
      {
        if (mUseOfflineMaps != paramBoolean)
        {
          String str = dataType;
          boolean bool = true;
          RealmLog.append(str, "setUseOfflineMaps( %b )", new Object[] { Boolean.valueOf(paramBoolean) });
          str = KEY_USE_OFFLINE_MAPS;
          mUseOfflineMaps = paramBoolean;
          Storage.enable(str, paramBoolean);
          if (!mUseOfflineMaps)
          {
            paramBoolean = bool;
            MapEngine.setOnline(paramBoolean);
          }
        }
        else
        {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
      paramBoolean = false;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OptionsManager{mMapType='");
    localStringBuilder.append(mMapType);
    localStringBuilder.append('\'');
    localStringBuilder.append(", mTrafficInformation=");
    localStringBuilder.append(mTrafficInformation);
    localStringBuilder.append(", mUseOfflineMaps=");
    localStringBuilder.append(mUseOfflineMaps);
    localStringBuilder.append(", mUnitSystem=");
    localStringBuilder.append(mUnitSystem);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
