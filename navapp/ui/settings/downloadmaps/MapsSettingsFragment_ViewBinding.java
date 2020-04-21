package com.com.navapp.ui.settings.downloadmaps;

import android.view.View;
import butterknife.internal.Utils;
import com.com.navapp.ui.settings.DownloadingList_ViewBinding;

public class MapsSettingsFragment_ViewBinding
  extends DownloadingList_ViewBinding
{
  private MapsSettingsFragment target;
  private View view7f080154;
  
  public MapsSettingsFragment_ViewBinding(MapsSettingsFragment paramMapsSettingsFragment, View paramView)
  {
    super(paramMapsSettingsFragment, paramView);
    target = paramMapsSettingsFragment;
    paramView = Utils.findRequiredView(paramView, 2131231060, "method 'closeOfflineMapsSettings'");
    view7f080154 = paramView;
    paramView.setOnClickListener(new MapsSettingsFragment_ViewBinding.1(this, paramMapsSettingsFragment));
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
