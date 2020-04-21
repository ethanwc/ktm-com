package com.com.kmrc.services.navigation.dump;

import com.com.kmrc.services.navigation.TurnIcon;
import com.com.kmrc.services.navigation.Visibility;

public class TurnIconDump
{
  public TurnIcon Image;
  public Visibility Visibility;
  
  public TurnIconDump() {}
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof TurnIconDump)) {
      return false;
    }
    paramObject = (TurnIconDump)paramObject;
    return (Image == Image) && (Visibility == Visibility);
  }
  
  public boolean hasValue()
  {
    return (Image != null) || (Visibility != null);
  }
}
