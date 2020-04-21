package com.com.navapp.ui.guidance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.package_9.Fragment;
import butterknife.ButterKnife;
import com.com.navapp.guidance.GuidanceManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;

public class ReachedDestinationFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  public ReachedDestinationFragment() {}
  
  public boolean onBackPressed()
  {
    return true;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427417, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onOk()
  {
    GuidanceManager.getInstance().cancelGuidance();
    ((MainScreen)getActivity()).onGuidanceFinished();
  }
}
