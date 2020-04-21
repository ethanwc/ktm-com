package com.com.kmrc.services.navigation.dump;

import com.com.kmrc.services.navigation.GpsIcon;
import com.com.kmrc.services.navigation.Visibility;

public class GpsIconDump
{
  public GpsIcon Image;
  public Visibility Visibility;
  
  public GpsIconDump() {}
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof GpsIconDump)) {
      return false;
    }
    paramObject = (GpsIconDump)paramObject;
    return (Image == Image) && (Visibility == Visibility);
  }
  
  public boolean hasValue()
  {
    return (Image != null) || (Visibility != null);
  }
}
