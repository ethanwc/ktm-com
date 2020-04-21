package com.com.navapp.ui.settings.downloadvoices;

import android.view.View;
import butterknife.internal.Utils;
import com.com.navapp.ui.settings.DownloadingList_ViewBinding;

public class VoicesSettingsFragment_ViewBinding
  extends DownloadingList_ViewBinding
{
  private VoicesSettingsFragment target;
  private View view7f080154;
  
  public VoicesSettingsFragment_ViewBinding(VoicesSettingsFragment paramVoicesSettingsFragment, View paramView)
  {
    super(paramVoicesSettingsFragment, paramView);
    target = paramVoicesSettingsFragment;
    paramView = Utils.findRequiredView(paramView, 2131231060, "method 'closeOfflineMapsSettings'");
    view7f080154 = paramView;
    paramView.setOnClickListener(new VoicesSettingsFragment_ViewBinding.1(this, paramVoicesSettingsFragment));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080154.setOnClickListener(null);
      view7f080154 = null;
      super.unbind();
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
