package com.com.kmrc.services.navigation;

import com.google.gson.annotations.Expose;

public class Value<T>
{
  @Expose
  private T value = null;
  
  public Value() {}
  
  static Value cloner()
  {
    return new Value();
  }
  
  public Object get()
  {
    return value;
  }
  
  public void put(Object paramObject)
  {
    value = paramObject;
  }
}
