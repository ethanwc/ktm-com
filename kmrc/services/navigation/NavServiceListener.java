package com.com.kmrc.services.navigation;

import com.com.kmrc.upgrade.KSocket;

public abstract interface NavServiceListener
{
  public abstract void onServiceMsg(KSocket paramKSocket, String paramString);
  
  public abstract void onServiceOffline(KSocket paramKSocket);
  
  public abstract void onServiceOnline(KSocket paramKSocket);
}
