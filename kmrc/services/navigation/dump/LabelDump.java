package com.com.kmrc.services.navigation.dump;

import com.com.kmrc.services.navigation.Visibility;

public class LabelDump
{
  public String Text;
  public Visibility Visibility;
  
  public LabelDump() {}
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof LabelDump)) {
      return false;
    }
    paramObject = (LabelDump)paramObject;
    if (Visibility == Visibility) {
      if ((Text != null) || (Text != null))
      {
        if ((Text == null) || (Text != null))
        {
          String str = Text;
          if ((str == null) || (!str.equals(Text))) {}
        }
      }
      else {
        return true;
      }
    }
    return false;
  }
  
  boolean hasValue()
  {
    return (Text != null) || (Visibility != null);
  }
}
