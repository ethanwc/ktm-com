package com.com.navapp.downloading.voice;

import com.here.android.ui.guidance.VoicePackage;

public class VoicePackageInfo
{
  public final boolean isLocal;
  public final long packageContentSizeKb;
  public final long packageDownloadSizeKb;
  public final long subtitle;
  public final String title;
  
  public VoicePackageInfo(String paramString, VoicePackage paramVoicePackage)
  {
    title = paramString;
    if (paramVoicePackage == null)
    {
      subtitle = -1L;
      isLocal = true;
      packageDownloadSizeKb = 0L;
      packageContentSizeKb = 0L;
      return;
    }
    subtitle = paramVoicePackage.getId();
    isLocal = paramVoicePackage.isLocal();
    packageDownloadSizeKb = ((paramVoicePackage.getDownloadSize() * 1000.0F));
    packageContentSizeKb = ((paramVoicePackage.getContentSize() * 1000.0F));
  }
}
