package com.com.navapp.ui.settings.downloadmaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import com.com.navapp.Globals;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.offlinemaps.MapDataUpdate;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.ui.settings.SwitchToOnlineFragment;
import java.util.HashMap;
import java.util.List;

public class MapsListAdapter
  extends ArrayAdapter<com.ktm.navapp.ui.settings.downloadmaps.DownloadMap>
{
  private HashMap<Integer, View> convertViews = new HashMap();
  private SwitchToOnlineFragment mSwitchToOnlineFragment;
  
  public MapsListAdapter(Context paramContext, List paramList)
  {
    super(paramContext, 2131427388, paramList);
  }
  
  private void configButton(DownloadMap paramDownloadMap, View paramView)
  {
    ProgressBar localProgressBar = (ProgressBar)paramView.findViewById(2131230862);
    TextView localTextView1 = (TextView)paramView.findViewById(2131230867);
    ImageButton localImageButton1 = (ImageButton)paramView.findViewById(2131230865);
    ImageButton localImageButton2 = (ImageButton)paramView.findViewById(2131230863);
    TextView localTextView2 = (TextView)paramView.findViewById(2131230868);
    TextView localTextView3 = (TextView)paramView.findViewById(2131230864);
    paramView = (ProgressBar)paramView.findViewById(2131230866);
    localTextView3.setText(paramDownloadMap.getTotalSize());
    int i = 3.$SwitchMap$com$ktm$navapp$ui$settings$DownloadingList$DataStatus[status.ordinal()];
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i == 4) {
            for (;;)
            {
              setToInstalled(paramView, localImageButton2, localTextView3, new View[] { localImageButton1, localTextView2, paramView });
            }
          }
        }
        else {
          setToWaiting(paramDownloadMap, localTextView2, paramView, localImageButton2, new View[] { localImageButton1, localTextView3 });
        }
      }
      else
      {
        paramView.setIndeterminate(false);
        paramView.setProgress(progress);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramDownloadMap.getDownloadedSize());
        localStringBuilder.append("/");
        localTextView2.setText(localStringBuilder.toString());
        showViews(new View[] { localTextView2, localTextView3, paramView, localImageButton2 });
        hideViews(new View[] { localImageButton1 });
      }
    }
    else {
      setToNotInstalled(paramView, localImageButton1, localTextView3, new View[] { localImageButton2, localTextView2, paramView });
    }
    localImageButton1.setOnClickListener(new MapsListAdapter.1(this, paramDownloadMap, localTextView2, paramView, localImageButton2, localImageButton1, localTextView3));
    localImageButton2.setOnClickListener(new MapsListAdapter.2(this, paramDownloadMap, paramView, localImageButton1, localTextView3, localImageButton2, localTextView2));
    if (MapDataUpdate.isUpdating())
    {
      hideViews(new View[] { localImageButton1, localImageButton2, paramView });
      showViews(new View[] { localProgressBar });
      localTextView1.setTextColor(Globals.getContext().getColor(2131034200));
      localTextView2.setTextColor(Globals.getContext().getColor(2131034200));
      localTextView3.setTextColor(Globals.getContext().getColor(2131034200));
      return;
    }
    hideViews(new View[] { localProgressBar });
    localTextView1.setTextColor(Globals.getContext().getColor(2131034194));
    localTextView2.setTextColor(Globals.getContext().getColor(2131034172));
    localTextView3.setTextColor(Globals.getContext().getColor(2131034194));
  }
  
  private void hideViews(View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(8);
      i += 1;
    }
  }
  
  private boolean mapLoaderIsNotAvailable()
  {
    return (!OfflineMapsManager.getInstance().isOnlineMode()) || (!OfflineMapsManager.getInstance().isNetworkConnected());
  }
  
  private void setToInstalled(ProgressBar paramProgressBar, ImageButton paramImageButton, TextView paramTextView, View... paramVarArgs)
  {
    paramProgressBar.setIndeterminate(false);
    showViews(new View[] { paramImageButton, paramTextView });
    hideViews(paramVarArgs);
  }
  
  private void setToNotInstalled(ProgressBar paramProgressBar, ImageButton paramImageButton, TextView paramTextView, View... paramVarArgs)
  {
    paramProgressBar.setIndeterminate(false);
    showViews(new View[] { paramImageButton, paramTextView });
    hideViews(paramVarArgs);
  }
  
  private void setToWaiting(DownloadMap paramDownloadMap, TextView paramTextView, ProgressBar paramProgressBar, ImageButton paramImageButton, View... paramVarArgs)
  {
    paramTextView.setText(2131558701);
    paramProgressBar.setIndeterminate(true);
    showViews(new View[] { paramTextView, paramProgressBar, paramImageButton });
    hideViews(paramVarArgs);
    if (OfflineMapsManager.getInstance().isActualPackageId(Integer.valueOf(packageId))) {
      hideViews(new View[] { paramImageButton });
    }
  }
  
  private void showViews(View... paramVarArgs)
  {
    int j = paramVarArgs.length;
    int i = 0;
    while (i < j)
    {
      paramVarArgs[i].setVisibility(0);
      i += 1;
    }
  }
  
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
        paramView = LayoutInflater.from(getContext()).inflate(2131427388, null);
        paramViewGroup = paramView;
        convertViews.put(Integer.valueOf(paramInt), paramView);
      }
    }
    if (getCount() > paramInt)
    {
      paramView = (DownloadMap)getItem(paramInt);
      if (paramView != null)
      {
        ((TextView)paramViewGroup.findViewById(2131230867)).setText(header.toUpperCase());
        configButton(paramView, paramViewGroup);
      }
    }
    return paramViewGroup;
  }
  
  protected void setSwitchToOnlineView()
  {
    if (mSwitchToOnlineFragment == null) {
      mSwitchToOnlineFragment = new SwitchToOnlineFragment();
    }
    ((FragmentActivity)getContext()).getSupportFragmentManager().beginTransaction().replace(2131231033, mSwitchToOnlineFragment).addToBackStack(null).commit();
  }
}
