package com.com.navapp.ui.settings.common;

import android.os.BaseBundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import com.com.navapp.ui.base.NavAppBaseFragment;

public class SimpleFullscreenFragment
  extends NavAppBaseFragment
{
  private static final String DESC = "description";
  private static final String TITLE = "title";
  
  public SimpleFullscreenFragment() {}
  
  public static SimpleFullscreenFragment newInstance(int paramInt1, int paramInt2)
  {
    SimpleFullscreenFragment localSimpleFullscreenFragment = new SimpleFullscreenFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("title", paramInt1);
    localBundle.putInt("description", paramInt2);
    localSimpleFullscreenFragment.setArguments(localBundle);
    return localSimpleFullscreenFragment;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    int i = getArguments().getInt("title");
    int j = getArguments().getInt("description");
    paramLayoutInflater = paramLayoutInflater.inflate(2131427428, paramViewGroup, false);
    ((Button)paramLayoutInflater.findViewById(2131231216)).setOnClickListener(new SimpleFullscreenFragment.1(this));
    ((TextView)paramLayoutInflater.findViewById(2131231217)).setText(getString(i));
    ((TextView)paramLayoutInflater.findViewById(2131231218)).setText(getString(j));
    return paramLayoutInflater;
  }
}
