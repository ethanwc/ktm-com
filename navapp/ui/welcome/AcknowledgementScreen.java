package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import com.com.navapp.Globals;
import com.com.navapp.ui.base.NavAppBaseFragmentActivity;

public class AcknowledgementScreen
  extends NavAppBaseFragmentActivity
{
  private AcknowledgementFragment mAcknowledgementFragment;
  private View mBackground;
  
  public AcknowledgementScreen() {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427356);
    mBackground = findViewById(2131230799);
    setAcknowledgementView();
  }
  
  protected void setAcknowledgementView()
  {
    if (mAcknowledgementFragment == null) {
      mAcknowledgementFragment = new AcknowledgementFragment();
    }
    getSupportFragmentManager().beginTransaction().replace(2131230838, mAcknowledgementFragment).commit();
  }
  
  public void setBackgroundToGray()
  {
    mBackground.setBackgroundColor(ContextCompat.getColor(Globals.getContext(), 2131034242));
  }
  
  public void setBackgroundToWhite()
  {
    mBackground.setBackgroundColor(-1);
  }
}
