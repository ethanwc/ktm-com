package com.com.navapp.connectivity.endpoints;

public abstract interface DashboardConnectionListener
{
  public abstract void dashboardConnected(Dashboard paramDashboard);
  
  public abstract void dashboardDisConnected(Dashboard paramDashboard);
}
