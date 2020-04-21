package com.com.navapp.utilities;

import com.here.android.ui.search.DiscoveryResult;
import com.here.android.ui.search.PlaceLink;

public class HEREHelper
{
  public HEREHelper() {}
  
  public static PlaceLink discoveryResultToPlaceLink(DiscoveryResult paramDiscoveryResult)
  {
    return (PlaceLink)paramDiscoveryResult;
  }
}
