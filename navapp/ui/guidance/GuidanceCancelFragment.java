package com.com.navapp.ui.guidance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.com.navapp.ui.base.NavAppBaseFragment;

public class GuidanceCancelFragment
  extends NavAppBaseFragment
{
  public GuidanceCancelFragment() {}
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427398, paramViewGroup, false);
    paramViewGroup = (Button)paramLayoutInflater.findViewById(2131230816);
    paramBundle = (ImageButton)paramLayoutInflater.findViewById(2131230815);
    paramViewGroup.setOnClickListener(new GuidanceCancelFragment.1(this));
    paramBundle.setOnClickListener(new GuidanceCancelFragment.2(this));
    return paramLayoutInflater;
  }
}
