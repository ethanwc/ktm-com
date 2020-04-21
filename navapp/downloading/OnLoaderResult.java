package com.com.navapp.downloading;

public abstract interface OnLoaderResult<T>
{
  public abstract void failed(Object paramObject);
  
  public abstract void onProgress(int paramInt);
  
  public abstract void success();
}
