package com.com.kmrc.services.navigation.dump;

import com.com.kmrc.services.navigation.NotificationIcon;
import com.com.kmrc.services.navigation.Visibility;

public class NotificationIconDump
{
  public NotificationIcon Image;
  public Visibility Visibility;
  
  public NotificationIconDump() {}
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof NotificationIconDump)) {
      return false;
    }
    paramObject = (NotificationIconDump)paramObject;
    return (Image == Image) && (Visibility == Visibility);
  }
  
  public boolean hasValue()
  {
    return (Image != null) || (Visibility != null);
  }
}
