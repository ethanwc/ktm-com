package com.com.navapp.ui.settings;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class InfoSettingsFragment_ViewBinding
  implements Unbinder
{
  private InfoSettingsFragment target;
  private View view7f08010b;
  
  public InfoSettingsFragment_ViewBinding(InfoSettingsFragment paramInfoSettingsFragment, View paramView)
  {
    target = paramInfoSettingsFragment;
    mTxtAppVersion = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230794, "field 'mTxtAppVersion'", TextView.class));
    mVwMoreInfo = Utils.findRequiredView(paramView, 2131230986, "field 'mVwMoreInfo'");
    mTxtAppBuildNumber = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230793, "field 'mTxtAppBuildNumber'", TextView.class));
    mTxtKmrcVersion = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231006, "field 'mTxtKmrcVersion'", TextView.class));
    mTxtHereSdkVersion = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230973, "field 'mTxtHereSdkVersion'", TextView.class));
    paramView = Utils.findRequiredView(paramView, 2131230987, "method 'closeInfoSettings'");
    view7f08010b = paramView;
    paramView.setOnClickListener(new InfoSettingsFragment_ViewBinding.1(this, paramInfoSettingsFragment));
  }
  
  public void unbind()
  {
    InfoSettingsFragment localInfoSettingsFragment = target;
    if (localInfoSettingsFragment != null)
    {
      target = null;
      mTxtAppVersion = null;
      mVwMoreInfo = null;
      mTxtAppBuildNumber = null;
      mTxtKmrcVersion = null;
      mTxtHereSdkVersion = null;
      view7f08010b.setOnClickListener(null);
      view7f08010b = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
