package com.com.navapp.ui.settings.downloadvoices;

import com.com.navapp.downloading.voice.VoicePackageInfo;
import com.com.navapp.ui.settings.DownloadingList.DataStatus;
import java.util.Locale;

public class DownloadVoice
{
  public final String header;
  public final long packageContentSize;
  public final long packageDownloadSize;
  public final long packageId;
  public final int progress;
  public DownloadingList.DataStatus status;
  
  public DownloadVoice(VoicePackageInfo paramVoicePackageInfo, DownloadingList.DataStatus paramDataStatus, int paramInt)
  {
    header = title;
    packageDownloadSize = packageDownloadSizeKb;
    packageContentSize = packageContentSizeKb;
    status = paramDataStatus;
    packageId = subtitle;
    progress = paramInt;
  }
  
  private String fileSizeFormat(long paramLong)
  {
    return String.format(Locale.getDefault(), "%.2f MB", new Object[] { Double.valueOf(paramLong / 1024.0D) });
  }
  
  public String getContentSize()
  {
    return fileSizeFormat(packageContentSize);
  }
  
  public String getDownloadSize()
  {
    return fileSizeFormat(packageDownloadSize);
  }
  
  public String getDownloadedSize()
  {
    return fileSizeFormat(progress * packageDownloadSize / 100L);
  }
}
