package com.com.navapp.connectivity.endpoints;

import com.com.kmrc.services.navigation.NavClientConnection;
import com.com.kmrc.services.navigation.NavigationRequest;
import com.com.kmrc.upgrade.Connection;
import com.com.kmrc.upgrade.KSocket;
import com.com.navapp.connectivity.bluetooth.BtControl;
import com.com.navapp.connectivity.bluetooth.BtDashboardSocket;
import com.com.navapp.connectivity.bluetooth.TcpDashboardSocket;
import com.com.navapp.utilities.Storage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class DashboardManager
{
  private static final String KEY_PAIREDDASHBOARDS = "PairedDashboards";
  private static DashboardManager instance;
  private final BtControl btControl = BtControl.getInstance();
  private DashboardConnectionListener dashboardConnectionListener = null;
  private NavClientConnection navClientConnection = new NavClientConnection(new DashboardManager.1(this));
  
  private DashboardManager()
  {
    navClientConnection.setReconnectTimer(4000L);
  }
  
  private Dashboard getDashboardIdentifier(KSocket paramKSocket)
  {
    if ((paramKSocket instanceof BtDashboardSocket)) {
      return ((BtDashboardSocket)paramKSocket).identifier();
    }
    if ((paramKSocket instanceof TcpDashboardSocket)) {
      return ((TcpDashboardSocket)paramKSocket).identifier();
    }
    return null;
  }
  
  public static DashboardManager getInstance()
  {
    try
    {
      if (instance == null) {
        instance = new DashboardManager();
      }
      DashboardManager localDashboardManager = instance;
      return localDashboardManager;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private Set loadHistory()
  {
    return load"PairedDashboards"dashboards;
  }
  
  private SortedSet pairedDevicesSorted(Set paramSet)
  {
    return new TreeSet(paramSet);
  }
  
  private void saveHistory(Set paramSet)
  {
    History localHistory = new History();
    dashboards.addAll(paramSet);
    Storage.save("PairedDashboards", localHistory);
  }
  
  private Set updatePairedDashboards()
  {
    Object localObject = btControl.getPairedDevices();
    Set localSet = loadHistory();
    int i = 0;
    int j = 0;
    while (i < ((ArrayList)localObject).size())
    {
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext())
      {
        Dashboard localDashboard = (Dashboard)localIterator.next();
        if (localDashboard.equals(((ArrayList)localObject).get(i)))
        {
          if (!localDashboard.btDeviceName().equals(((Dashboard)((ArrayList)localObject).get(i)).btDeviceName())) {
            localDashboard.setBtDeviceName(((Dashboard)((ArrayList)localObject).get(i)).btDeviceName());
          }
          ((ArrayList)localObject).set(i, localDashboard);
          if (localDashboard.isFavorite()) {
            j = 1;
          }
        }
      }
      i += 1;
    }
    if ((j == 0) && (((ArrayList)localObject).size() > 0)) {
      ((Dashboard)((ArrayList)localObject).get(0)).setFavorite(true);
    }
    localObject = new HashSet((Collection)localObject);
    saveHistory((Set)localObject);
    return localObject;
  }
  
  public void connectFavorite()
  {
    Dashboard localDashboard = favorite();
    if (localDashboard != null)
    {
      Object localObject = 2.$SwitchMap$com$ktm$kmrc$shell$TransportType;
      try
      {
        int i = localDashboard.transportType().ordinal();
        i = localObject[i];
        if (i != 1)
        {
          if (i != 2) {
            return;
          }
          localObject = navClientConnection;
          ((Connection)localObject).establish(new BtDashboardSocket(localDashboard));
          return;
        }
        localObject = navClientConnection;
        ((Connection)localObject).establish(new TcpDashboardSocket(localDashboard));
        return;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException) {}
    }
  }
  
  public Dashboard favorite()
  {
    Iterator localIterator = updatePairedDashboards().iterator();
    while (localIterator.hasNext())
    {
      Dashboard localDashboard = (Dashboard)localIterator.next();
      if (localDashboard.isFavorite()) {
        return localDashboard;
      }
    }
    return null;
  }
  
  public Dashboard getConnectedDashboard()
  {
    return getDashboardIdentifier(navClientConnection.getConnectedSocket());
  }
  
  public SortedSet listDashboards()
  {
    return pairedDevicesSorted(updatePairedDashboards());
  }
  
  public NavigationRequest navigationRequest()
  {
    return navClientConnection;
  }
  
  public SortedSet setDashboardAliasName(String paramString1, String paramString2)
  {
    Set localSet = updatePairedDashboards();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      Dashboard localDashboard = (Dashboard)localIterator.next();
      if (localDashboard.macAddress().equals(paramString1))
      {
        localDashboard.setAliasName(paramString2);
        saveHistory(localSet);
      }
    }
    return pairedDevicesSorted(localSet);
  }
  
  public void setDashboardConnectionListener(DashboardConnectionListener paramDashboardConnectionListener)
  {
    dashboardConnectionListener = paramDashboardConnectionListener;
  }
  
  public SortedSet setFavoriteDashboard(Dashboard paramDashboard)
  {
    Set localSet = updatePairedDashboards();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext())
    {
      Dashboard localDashboard = (Dashboard)localIterator.next();
      if (localDashboard.equals(paramDashboard)) {
        localDashboard.setFavorite(true);
      } else if (localDashboard.isFavorite()) {
        localDashboard.setFavorite(false);
      }
    }
    saveHistory(localSet);
    connectFavorite();
    return pairedDevicesSorted(localSet);
  }
  
  public class History
  {
    public final Set<com.ktm.navapp.connectivity.endpoints.Dashboard> dashboards = new HashSet();
    
    public History() {}
  }
}
