package com.com.navapp.ui.settings.downloadmaps.region;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class RegionMapsSettingsFragment_ViewBinding
  implements Unbinder
{
  private RegionMapsSettingsFragment target;
  private View view7f080154;
  
  public RegionMapsSettingsFragment_ViewBinding(RegionMapsSettingsFragment paramRegionMapsSettingsFragment, View paramView)
  {
    target = paramRegionMapsSettingsFragment;
    mHeader = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230860, "field 'mHeader'", TextView.class));
    mList = ((ListView)Utils.findRequiredViewAsType(paramView, 2131230857, "field 'mList'", ListView.class));
    mNoItemsText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231087, "field 'mNoItemsText'", TextView.class));
    paramView = Utils.findRequiredView(paramView, 2131231060, "method 'closeOfflineMapsSettings'");
    view7f080154 = paramView;
    paramView.setOnClickListener(new RegionMapsSettingsFragment_ViewBinding.1(this, paramRegionMapsSettingsFragment));
  }
  
  public void unbind()
  {
    RegionMapsSettingsFragment localRegionMapsSettingsFragment = target;
    if (localRegionMapsSettingsFragment != null)
    {
      target = null;
      mHeader = null;
      mList = null;
      mNoItemsText = null;
      view7f080154.setOnClickListener(null);
      view7f080154 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
