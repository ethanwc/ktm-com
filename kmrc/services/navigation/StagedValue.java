package com.com.kmrc.services.navigation;

import com.google.gson.annotations.Expose;

public class StagedValue<T>
{
  @Expose
  private com.ktm.kmrc.services.navigation.Value<T> committed = Value.cloner();
  @Expose
  private com.ktm.kmrc.services.navigation.Value<T> modified = Value.cloner();
  
  StagedValue() {}
  
  void commit()
  {
    if (modified.get() != null)
    {
      committed.put(modified.get());
      modified.put(null);
    }
  }
  
  Object committed()
  {
    return committed.get();
  }
  
  public void diff(Object paramObject)
  {
    if ((paramObject != null) && (modified.get() != null) && (committed.get() != null))
    {
      if (!modified.get().equals(paramObject))
      {
        if (!committed.get().equals(paramObject))
        {
          modified.put(paramObject);
          return;
        }
        modified.put(null);
      }
    }
    else if ((paramObject != null) && (modified.get() != null))
    {
      if (!modified.get().equals(paramObject)) {
        modified.put(paramObject);
      }
    }
    else if ((paramObject != null) && (committed.get() != null))
    {
      if (!committed.get().equals(paramObject)) {
        modified.put(paramObject);
      }
    }
    else
    {
      if (paramObject != null)
      {
        modified.put(paramObject);
        return;
      }
      if ((modified.get() != null) && (committed.get() != null))
      {
        modified.put(null);
        return;
      }
      if (modified.get() != null) {
        modified.put(null);
      }
    }
  }
  
  public Object modified()
  {
    return modified.get();
  }
  
  void reset()
  {
    modified.put(null);
    committed.put(null);
  }
  
  public Object result()
  {
    if (modified.get() != null) {
      return modified.get();
    }
    return committed.get();
  }
}
