package com.com.navapp.ui.base;

import android.os.Bundle;
import androidx.fragment.package_9.Fragment;

public abstract class NavAppBaseFragment
  extends Fragment
{
  protected final String _name = getClass().getSimpleName();
  
  public NavAppBaseFragment() {}
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
  }
  
  public void onPause()
  {
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
  }
  
  public void onStart()
  {
    super.onStart();
  }
  
  public void onStop()
  {
    super.onStop();
  }
}
