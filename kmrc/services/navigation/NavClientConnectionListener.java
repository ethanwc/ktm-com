package com.com.kmrc.services.navigation;

import com.com.kmrc.upgrade.Connection;
import com.com.kmrc.upgrade.KSocket;
import java.util.NoSuchElementException;

public class NavClientConnectionListener
  extends NavConnectionListener
{
  private NavClientConnection navClientConnection;
  
  NavClientConnectionListener(NavServiceListener paramNavServiceListener)
  {
    super(paramNavServiceListener);
  }
  
  public void onConnected(Connection paramConnection, String paramString)
  {
    if ((paramConnection instanceof NavClientConnection))
    {
      NavClientConnection localNavClientConnection = (NavClientConnection)paramConnection;
      try
      {
        localNavClientConnection.restore();
      }
      catch (NoSuchElementException localNoSuchElementException)
      {
        serviceListener.onServiceMsg(paramConnection.ksocket(), localNoSuchElementException.getMessage());
      }
      NavServiceListener localNavServiceListener = serviceListener;
      KSocket localKSocket = paramConnection.ksocket();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("connected ");
      localStringBuilder.append(paramString);
      localNavServiceListener.onServiceMsg(localKSocket, localStringBuilder.toString());
      serviceListener.onServiceOnline(paramConnection.ksocket());
      return;
    }
    throw new IllegalArgumentException("Connection not of type NavClientConnection()");
  }
  
  public void onEstablishing(Connection paramConnection, String paramString)
  {
    if ((paramConnection instanceof NavClientConnection))
    {
      NavServiceListener localNavServiceListener = serviceListener;
      paramConnection = paramConnection.ksocket();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("connecting ");
      localStringBuilder.append(paramString);
      localNavServiceListener.onServiceMsg(paramConnection, localStringBuilder.toString());
      return;
    }
    throw new IllegalArgumentException("Connection not of type NavClientConnection()");
  }
}
