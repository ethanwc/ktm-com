package com.com.navapp.ui.settings.downloadvoices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.ui.settings.SwitchToOnlineFragment;
import java.util.HashMap;
import java.util.List;

public abstract class VoicesListAdapter
  extends ArrayAdapter<com.ktm.navapp.ui.settings.downloadvoices.DownloadVoice>
{
  HashMap<Integer, View> convertViews = new HashMap();
  private SwitchToOnlineFragment mSwitchToOnlineFragment;
  
  public VoicesListAdapter(Context paramContext, int paramInt, List paramList)
  {
    super(paramContext, paramInt, paramList);
  }
  
  protected boolean VoiceManagerIsNotAvailable()
  {
    return (!VoiceManager.getInstance().isOnlineMode()) || (!VoiceManager.getInstance().isNetworkConnected());
  }
  
  protected abstract void configButton(DownloadVoice paramDownloadVoice, View paramView);
  
  protected abstract int getLayoutResource();
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = paramView;
    if (paramView == null) {
      if ((convertViews.size() > paramInt) && (convertViews.get(Integer.valueOf(paramInt)) != null))
      {
        paramViewGroup = (View)convertViews.get(Integer.valueOf(paramInt));
      }
      else
      {
        paramView = LayoutInflater.from(getContext()).inflate(getLayoutResource(), null);
        paramViewGroup = paramView;
        convertViews.put(Integer.valueOf(paramInt), paramView);
      }
    }
    paramView = (TextView)paramViewGroup.findViewById(2131231335);
    DownloadVoice localDownloadVoice = (DownloadVoice)getItem(paramInt);
    if (localDownloadVoice != null)
    {
      paramView.setText(header.toUpperCase());
      configButton(localDownloadVoice, paramViewGroup);
    }
    return paramViewGroup;
  }
  
  protected void hideViews(View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(8);
      i += 1;
    }
  }
  
  protected void setSwitchToOnlineView()
  {
    if (mSwitchToOnlineFragment == null) {
      mSwitchToOnlineFragment = new SwitchToOnlineFragment();
    }
    ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(2131231033, mSwitchToOnlineFragment).addToBackStack(null).commit();
  }
  
  protected void showViews(View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(0);
      i += 1;
    }
  }
}
