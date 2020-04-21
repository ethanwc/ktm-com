package com.com.navapp.connectivity.bluetooth;

import com.com.kmrc.TransportMap;
import com.com.kmrc.upgrade.Connection.LinkingListener;
import com.com.kmrc.upgrade.socket.labs.TcpKSocket;
import com.com.navapp.connectivity.endpoints.Dashboard;
import java.io.IOException;
import java.net.UnknownHostException;

public class TcpDashboardSocket
  extends TcpKSocket
{
  private final Dashboard dashboard;
  
  public TcpDashboardSocket(Dashboard paramDashboard)
    throws UnknownHostException
  {
    super(paramDashboard.aliasName(), TransportMap.NAVIGATION.port());
    dashboard = paramDashboard;
  }
  
  public void establishConnection(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    super.connect(paramLinkingListener);
  }
  
  public Dashboard identifier()
  {
    return dashboard;
  }
}
