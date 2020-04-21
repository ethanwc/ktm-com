package com.com.kmrc.services.navigation;

import com.com.kmrc.upgrade.KSocket;

public abstract interface NavServerListener
  extends NavServiceListener
{
  public abstract void onChangeWidgetContent(KSocket paramKSocket, String paramString1, String paramString2);
  
  public abstract void onChangeWidgetVisibility(KSocket paramKSocket, String paramString, Visibility paramVisibility);
  
  public abstract void onDefaultUiContext(KSocket paramKSocket);
  
  public abstract void onGuidanceUiContext(KSocket paramKSocket);
}
