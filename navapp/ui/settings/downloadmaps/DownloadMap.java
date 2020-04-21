package com.com.navapp.ui.settings.downloadmaps;

import com.com.navapp.downloading.offlinemaps.MapPackageInfo;
import com.com.navapp.ui.settings.DownloadingList.DataStatus;
import java.util.Locale;

public class DownloadMap
{
  public final String header;
  public final int packageId;
  public final long packageSize;
  public final int progress;
  public DownloadingList.DataStatus status;
  
  DownloadMap(MapPackageInfo paramMapPackageInfo, DownloadingList.DataStatus paramDataStatus, int paramInt)
  {
    header = title;
    packageSize = packageSizeKb;
    status = paramDataStatus;
    packageId = subtitle;
    progress = paramInt;
  }
  
  private String fileSizeFormat(long paramLong)
  {
    return String.format(Locale.getDefault(), "%.2f MB", new Object[] { Double.valueOf(paramLong / 1024.0D) });
  }
  
  public String getDownloadedSize()
  {
    return fileSizeFormat(progress * packageSize / 100L);
  }
  
  public String getTotalSize()
  {
    return fileSizeFormat(packageSize);
  }
}
