package com.com.navapp.routing;

public abstract interface RoutingCallback
{
  public abstract void onProgress(int paramInt);
  
  public abstract void onResult(RouteResult paramRouteResult, boolean paramBoolean);
}
