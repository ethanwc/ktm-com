package com.com.navapp.connectivity.bluetooth;

import com.com.kmrc.TransportMap;
import com.com.kmrc.upgrade.Connection.LinkingListener;
import com.com.navapp.connectivity.endpoints.Dashboard;
import java.io.IOException;

public class BtDashboardSocket
  extends BtAndroidKSocket
{
  private final Dashboard dashboard;
  
  public BtDashboardSocket(Dashboard paramDashboard)
  {
    super(paramDashboard.macAddress(), TransportMap.NAVIGATION.uuidString(), TransportMap.NAVIGATION.iAPIdentifier());
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
