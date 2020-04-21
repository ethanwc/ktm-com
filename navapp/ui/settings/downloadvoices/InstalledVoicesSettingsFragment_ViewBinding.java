package com.com.navapp.ui.settings.downloadvoices;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.internal.Utils;

public class InstalledVoicesSettingsFragment_ViewBinding
  extends VoicesSettingsFragment_ViewBinding
{
  private InstalledVoicesSettingsFragment target;
  private View view7f080114;
  private View view7f080154;
  
  public InstalledVoicesSettingsFragment_ViewBinding(InstalledVoicesSettingsFragment paramInstalledVoicesSettingsFragment, View paramView)
  {
    super(paramInstalledVoicesSettingsFragment, paramView);
    target = paramInstalledVoicesSettingsFragment;
    mAvailableMemory = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230997, "field 'mAvailableMemory'", TextView.class));
    mProgressBar = ((ProgressBar)Utils.findRequiredViewAsType(paramView, 2131230998, "field 'mProgressBar'", ProgressBar.class));
    View localView = Utils.findRequiredView(paramView, 2131230996, "method 'onDownloadVoicesSettings'");
    view7f080114 = localView;
    localView.setOnClickListener(new InstalledVoicesSettingsFragment_ViewBinding.1(this, paramInstalledVoicesSettingsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231060, "method 'closeOfflineMapsSettings'");
    view7f080154 = paramView;
    paramView.setOnClickListener(new InstalledVoicesSettingsFragment_ViewBinding.2(this, paramInstalledVoicesSettingsFragment));
  }
  
  public void unbind()
  {
    InstalledVoicesSettingsFragment localInstalledVoicesSettingsFragment = target;
    if (localInstalledVoicesSettingsFragment != null)
    {
      target = null;
      mAvailableMemory = null;
      mProgressBar = null;
      view7f080114.setOnClickListener(null);
      view7f080114 = null;
      view7f080154.setOnClickListener(null);
      view7f080154 = null;
      super.unbind();
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
