package com.com.navapp.connectivity.endpoints;

import com.com.kmrc.shell.TransportType;

public class Dashboard
  implements Comparable
{
  private String aliasName;
  private String btDeviceName;
  private boolean isFavorite;
  private final String macAddress;
  private final TransportType transportType;
  
  public Dashboard(String paramString1, String paramString2, String paramString3, boolean paramBoolean, TransportType paramTransportType)
  {
    btDeviceName = paramString1;
    macAddress = paramString2;
    aliasName = paramString3;
    isFavorite = paramBoolean;
    transportType = paramTransportType;
  }
  
  public String aliasName()
  {
    return aliasName;
  }
  
  public String btDeviceName()
  {
    return btDeviceName;
  }
  
  public int compareTo(Object paramObject)
  {
    if ((paramObject instanceof Dashboard))
    {
      Dashboard localDashboard = (Dashboard)paramObject;
      if (isFavorite) {
        return -1;
      }
      if (localDashboard.isFavorite()) {
        return 1;
      }
      Object localObject = btDeviceName;
      String str1 = localDashboard.btDeviceName();
      String str2 = aliasName;
      paramObject = localObject;
      if (str2 != null)
      {
        paramObject = localObject;
        if (str2.length() > 0) {
          paramObject = aliasName;
        }
      }
      localObject = str1;
      if (localDashboard.aliasName() != null)
      {
        localObject = str1;
        if (localDashboard.aliasName().length() > 0) {
          localObject = localDashboard.aliasName();
        }
      }
      return paramObject.compareTo((String)localObject);
    }
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof Dashboard)) {
      return false;
    }
    paramObject = (Dashboard)paramObject;
    return macAddress.equals(macAddress);
  }
  
  public String getName()
  {
    if (aliasName.isEmpty()) {
      return btDeviceName();
    }
    return aliasName();
  }
  
  public int hashCode()
  {
    return macAddress.hashCode();
  }
  
  public boolean isFavorite()
  {
    return isFavorite;
  }
  
  public String macAddress()
  {
    return macAddress;
  }
  
  void setAliasName(String paramString)
  {
    aliasName = paramString;
  }
  
  public void setBtDeviceName(String paramString)
  {
    btDeviceName = paramString;
  }
  
  void setFavorite(boolean paramBoolean)
  {
    isFavorite = paramBoolean;
  }
  
  TransportType transportType()
  {
    return transportType;
  }
}
