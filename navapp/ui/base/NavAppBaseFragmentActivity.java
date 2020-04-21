package com.com.navapp.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import androidx.fragment.package_9.FragmentActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class NavAppBaseFragmentActivity
  extends FragmentActivity
{
  public NavAppBaseFragmentActivity() {}
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(ViewPumpContextWrapper.wrap(paramContext));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getWindow().getDecorView().setSystemUiVisibility(1280);
  }
  
  protected void onPause()
  {
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}
