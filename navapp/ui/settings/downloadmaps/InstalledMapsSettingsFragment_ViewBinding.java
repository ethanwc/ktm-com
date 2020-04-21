package com.com.navapp.ui.settings.downloadmaps;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.internal.Utils;

public class InstalledMapsSettingsFragment_ViewBinding
  extends MapsSettingsFragment_ViewBinding
{
  private InstalledMapsSettingsFragment target;
  private View view7f08010e;
  private View view7f080149;
  private View view7f08014a;
  
  public InstalledMapsSettingsFragment_ViewBinding(InstalledMapsSettingsFragment paramInstalledMapsSettingsFragment, View paramView)
  {
    super(paramInstalledMapsSettingsFragment, paramView);
    target = paramInstalledMapsSettingsFragment;
    mAvailableMemory = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230992, "field 'mAvailableMemory'", TextView.class));
    mProgressBar = ((ProgressBar)Utils.findRequiredViewAsType(paramView, 2131230991, "field 'mProgressBar'", ProgressBar.class));
    mUpdateViewUptodate = Utils.findRequiredView(paramView, 2131231058, "field 'mUpdateViewUptodate'");
    mUpdateViewOutdated = Utils.findRequiredView(paramView, 2131231054, "field 'mUpdateViewOutdated'");
    mUpdateViewUnkown = Utils.findRequiredView(paramView, 2131231056, "field 'mUpdateViewUnkown'");
    mUpdateViewTextSearch = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231053, "field 'mUpdateViewTextSearch'", TextView.class));
    View localView = Utils.findRequiredView(paramView, 2131231049, "field 'mUpdateViewButtonSearch' and method 'onSearchUpdate'");
    mUpdateViewButtonSearch = ((TextView)Utils.castView(localView, 2131231049, "field 'mUpdateViewButtonSearch'", TextView.class));
    view7f080149 = localView;
    localView.setOnClickListener(new InstalledMapsSettingsFragment_ViewBinding.1(this, paramInstalledMapsSettingsFragment));
    mUpdateViewSeparator = Utils.findRequiredView(paramView, 2131231055, "field 'mUpdateViewSeparator'");
    mUpdateViewUpdating = Utils.findRequiredView(paramView, 2131231057, "field 'mUpdateViewUpdating'");
    localView = Utils.findRequiredView(paramView, 2131230990, "method 'onDownloadMapsSettings'");
    view7f08010e = localView;
    localView.setOnClickListener(new InstalledMapsSettingsFragment_ViewBinding.2(this, paramInstalledMapsSettingsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231050, "method 'onUpdateMaps'");
    view7f08014a = paramView;
    paramView.setOnClickListener(new InstalledMapsSettingsFragment_ViewBinding.3(this, paramInstalledMapsSettingsFragment));
  }
  
  public void unbind()
  {
    InstalledMapsSettingsFragment localInstalledMapsSettingsFragment = target;
    if (localInstalledMapsSettingsFragment != null)
    {
      target = null;
      mAvailableMemory = null;
      mProgressBar = null;
      mUpdateViewUptodate = null;
      mUpdateViewOutdated = null;
      mUpdateViewUnkown = null;
      mUpdateViewTextSearch = null;
      mUpdateViewButtonSearch = null;
      mUpdateViewSeparator = null;
      mUpdateViewUpdating = null;
      view7f080149.setOnClickListener(null);
      view7f080149 = null;
      view7f08010e.setOnClickListener(null);
      view7f08010e = null;
      view7f08014a.setOnClickListener(null);
      view7f08014a = null;
      super.unbind();
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
