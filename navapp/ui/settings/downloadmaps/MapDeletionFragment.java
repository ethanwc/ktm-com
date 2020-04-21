package com.com.navapp.ui.settings.downloadmaps;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.utilities.Utilities;

public class MapDeletionFragment
  extends NavAppBaseFragment
{
  private String mCountryName;
  private Runnable mDeletionRunnable;
  @BindView(2131231042)
  protected TextView mDescription;
  
  public MapDeletionFragment() {}
  
  public static MapDeletionFragment newInstance(String paramString, Runnable paramRunnable)
  {
    MapDeletionFragment localMapDeletionFragment = new MapDeletionFragment();
    mCountryName = paramString;
    mDeletionRunnable = paramRunnable;
    return localMapDeletionFragment;
  }
  
  public void onCancel()
  {
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427410, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    mDescription.setText(Utilities.replaceBoldWithColor(getContext().getString(2131558542, new Object[] { mCountryName }), getContext().getColor(2131034172)));
    return paramLayoutInflater;
  }
  
  public void onOk()
  {
    Runnable localRunnable = mDeletionRunnable;
    if (localRunnable != null) {
      localRunnable.run();
    }
    getActivity().getSupportFragmentManager().popBackStack();
  }
}
