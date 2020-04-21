package com.com.navapp.ui.settings.downloadmaps;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;

public class DownloadMapsSettingsFragment_ViewBinding
  extends MapsSettingsFragment_ViewBinding
{
  private DownloadMapsSettingsFragment target;
  
  public DownloadMapsSettingsFragment_ViewBinding(DownloadMapsSettingsFragment paramDownloadMapsSettingsFragment, View paramView)
  {
    super(paramDownloadMapsSettingsFragment, paramView);
    target = paramDownloadMapsSettingsFragment;
    mHeader = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230860, "field 'mHeader'", TextView.class));
  }
  
  public void unbind()
  {
    DownloadMapsSettingsFragment localDownloadMapsSettingsFragment = target;
    if (localDownloadMapsSettingsFragment != null)
    {
      target = null;
      mHeader = null;
      super.unbind();
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
