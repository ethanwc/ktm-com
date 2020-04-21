package com.com.navapp.utilities;

import com.here.android.ui.guidance.NavigationManager;

public class NavigationManagerProxy
{
  public NavigationManagerProxy() {}
  
  public static NavigationManager getInstance()
  {
    return NavigationManager.getInstance();
  }
}
