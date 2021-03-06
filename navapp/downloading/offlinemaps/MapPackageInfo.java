package com.com.navapp.downloading.offlinemaps;

import com.here.android.ui.odml.MapPackage;
import com.here.android.ui.odml.MapPackage.InstallationState;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MapPackageInfo
{
  public final List<com.ktm.navapp.downloading.offlinemaps.MapPackageInfo> children = new LinkedList();
  public final String englishTitle;
  public final MapPackage.InstallationState installationState;
  public final long packageSizeKb;
  public final int subtitle;
  public final String title;
  
  MapPackageInfo(MapPackage paramMapPackage)
  {
    subtitle = paramMapPackage.getId();
    title = paramMapPackage.getTitle();
    englishTitle = paramMapPackage.getEnglishTitle();
    installationState = paramMapPackage.getInstallationState();
    packageSizeKb = paramMapPackage.getSize();
    paramMapPackage = paramMapPackage.getChildren();
    if (paramMapPackage != null)
    {
      paramMapPackage = paramMapPackage.iterator();
      while (paramMapPackage.hasNext())
      {
        MapPackage localMapPackage = (MapPackage)paramMapPackage.next();
        children.add(new MapPackageInfo(localMapPackage));
      }
    }
  }
  
  public List getChildren()
  {
    List localList = children;
    if (localList != null) {
      return localList;
    }
    return new LinkedList();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MapPackageInfo{id=");
    localStringBuilder.append(subtitle);
    localStringBuilder.append(", englishTitle='");
    localStringBuilder.append(englishTitle);
    localStringBuilder.append('\'');
    localStringBuilder.append(", installationState=");
    localStringBuilder.append(installationState);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
