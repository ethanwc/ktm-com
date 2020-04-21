package com.com.navapp.downloading.voice;

public abstract class OnDownloadCatalog
{
  public OnDownloadCatalog() {}
  
  public void failed() {}
  
  public abstract void success();
}
