package com.com.kmrc.services.navigation;

import com.google.gson.annotations.Expose;
import com.ktm.kmrc.services.navigation.Visibility;

public class Widget<T>
{
  @Expose
  private com.ktm.kmrc.services.navigation.StagedValue<Visibility> Visibility = new StagedValue();
  @Expose
  private com.ktm.kmrc.services.navigation.StagedValue<T> content = new StagedValue();
  
  public Widget() {}
  
  void commit()
  {
    content.commit();
    Visibility.commit();
  }
  
  public StagedValue content()
  {
    return content;
  }
  
  void reset()
  {
    content.reset();
    Visibility.reset();
  }
  
  public StagedValue visibility()
  {
    return Visibility;
  }
}
