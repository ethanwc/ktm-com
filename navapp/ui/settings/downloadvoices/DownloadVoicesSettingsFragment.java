package com.com.navapp.ui.settings.downloadvoices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import butterknife.BindView;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.voice.VoiceManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.settings.DownloadingList;
import com.here.android.ui.guidance.VoiceCatalog;
import java.util.List;

public class DownloadVoicesSettingsFragment
  extends VoicesSettingsFragment
{
  @BindView(2131230860)
  TextView mHeader;
  private final BroadcastReceiver mNetworkStateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (VoiceManager.getInstance().isOnlineMode())
      {
        if (!VoiceManager.getInstance().isNetworkConnected()) {
          return;
        }
        if (!VoiceCatalog.getInstance().isLocalCatalogAvailable())
        {
          VoiceCatalog.getInstance().cancel();
          VoiceManager.getInstance().downloadCatalog(new DownloadVoicesSettingsFragment.1.1(this));
        }
      }
    }
  };
  @BindView(2131231087)
  TextView mNoItemsText;
  
  public DownloadVoicesSettingsFragment() {}
  
  private void checkListStatus()
  {
    if (mNoItemsText == null) {
      return;
    }
    try
    {
      int i = getItems().size();
      if (i == 0)
      {
        mNoItemsText.setVisibility(0);
        return;
      }
      mNoItemsText.setVisibility(8);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;) {}
    }
    mNoItemsText.setVisibility(0);
  }
  
  protected DownloadVoicesListAdapter getAdapter()
  {
    return new DownloadVoicesListAdapter(getContext(), mDownloadItems);
  }
  
  protected List getItems()
  {
    return VoiceManager.getInstance().getAvailableVoices();
  }
  
  protected int getLayoutResource()
  {
    return 2131427394;
  }
  
  protected void initialize()
  {
    super.initialize();
    mHeader.setText(2131558489);
    checkListStatus();
  }
  
  public void onPause()
  {
    getActivity().unregisterReceiver(mNetworkStateReceiver);
    super.onPause();
  }
  
  public void onResume()
  {
    super.onResume();
    getActivity().registerReceiver(mNetworkStateReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
}
