package com.com.kmrc.services.navigation;

import com.com.kmrc.upgrade.Connection;
import com.com.kmrc.upgrade.ConnectionListener;
import com.com.kmrc.upgrade.KSocket;

class NavConnectionListener
  implements ConnectionListener
{
  NavServiceListener serviceListener;
  
  NavConnectionListener(NavServiceListener paramNavServiceListener)
  {
    if (paramNavServiceListener != null)
    {
      serviceListener = paramNavServiceListener;
      return;
    }
    throw new IllegalArgumentException();
  }
  
  public void onConnected(Connection paramConnection, String paramString)
  {
    serviceListener.onServiceOnline(paramConnection.ksocket());
  }
  
  public void onConnectionClosed(Connection paramConnection, String paramString)
  {
    serviceListener.onServiceMsg(paramConnection.ksocket(), "connection closed locally");
    serviceListener.onServiceOffline(paramConnection.ksocket());
  }
  
  public void onConnectionDisconnected(Connection paramConnection, String paramString)
  {
    serviceListener.onServiceMsg(paramConnection.ksocket(), "disconnected by peer ");
    serviceListener.onServiceOffline(paramConnection.ksocket());
  }
  
  public void onError(Connection paramConnection, String paramString, Error paramError)
  {
    NavServiceListener localNavServiceListener = serviceListener;
    KSocket localKSocket = paramConnection.ksocket();
    StringBuilder localStringBuilder = new StringBuilder();
    String str = "";
    localStringBuilder.append("");
    localStringBuilder.append(paramString);
    paramString = str;
    if (paramError != null)
    {
      paramString = new StringBuilder();
      paramString.append(" ");
      paramString.append(paramError.getMessage());
      paramString = paramString.toString();
    }
    localStringBuilder.append(paramString);
    localNavServiceListener.onServiceMsg(localKSocket, localStringBuilder.toString());
    serviceListener.onServiceOffline(paramConnection.ksocket());
  }
  
  public void onEstablishing(Connection paramConnection, String paramString)
  {
    NavServiceListener localNavServiceListener = serviceListener;
    paramConnection = paramConnection.ksocket();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("connecting ");
    localStringBuilder.append(paramString);
    localNavServiceListener.onServiceMsg(paramConnection, localStringBuilder.toString());
  }
  
  public void onLog(Connection paramConnection, String paramString)
  {
    NavServiceListener localNavServiceListener = serviceListener;
    paramConnection = paramConnection.ksocket();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(paramString);
    localNavServiceListener.onServiceMsg(paramConnection, localStringBuilder.toString());
  }
  
  public void onMessageReceived(Connection paramConnection, byte[] paramArrayOfByte) {}
}
