package com.com.navapp.ui.settings.downloadmaps;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MapsStorageFragment_ViewBinding
  implements Unbinder
{
  private MapsStorageFragment target;
  private View view7f080155;
  private View view7f080156;
  private View view7f080157;
  private View view7f080158;
  private View view7f08015b;
  private View view7f08015e;
  private View view7f08015f;
  
  public MapsStorageFragment_ViewBinding(MapsStorageFragment paramMapsStorageFragment, View paramView)
  {
    target = paramMapsStorageFragment;
    mDeviceMemText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231066, "field 'mDeviceMemText'", TextView.class));
    mDeviceMemBar = ((ProgressBar)Utils.findRequiredViewAsType(paramView, 2131231065, "field 'mDeviceMemBar'", ProgressBar.class));
    View localView = Utils.findRequiredView(paramView, 2131231063, "field 'mDeviceButton' and method 'onSelectDevice'");
    mDeviceButton = ((RadioButton)Utils.castView(localView, 2131231063, "field 'mDeviceButton'", RadioButton.class));
    view7f080157 = localView;
    localView.setOnClickListener(new MapsStorageFragment_ViewBinding.1(this, paramMapsStorageFragment));
    localView = Utils.findRequiredView(paramView, 2131231071, "field 'mSdCardLayout' and method 'onSelectSdCard'");
    mSdCardLayout = localView;
    view7f08015f = localView;
    localView.setOnClickListener(new MapsStorageFragment_ViewBinding.2(this, paramMapsStorageFragment));
    mSdCardMemText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231073, "field 'mSdCardMemText'", TextView.class));
    mSdCardMemBar = ((ProgressBar)Utils.findRequiredViewAsType(paramView, 2131231072, "field 'mSdCardMemBar'", ProgressBar.class));
    localView = Utils.findRequiredView(paramView, 2131231070, "field 'mSdCardButton' and method 'onSelectSdCard'");
    mSdCardButton = ((RadioButton)Utils.castView(localView, 2131231070, "field 'mSdCardButton'", RadioButton.class));
    view7f08015e = localView;
    localView.setOnClickListener(new MapsStorageFragment_ViewBinding.3(this, paramMapsStorageFragment));
    mPopupLayout = Utils.findRequiredView(paramView, 2131231068, "field 'mPopupLayout'");
    mPopupText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231069, "field 'mPopupText'", TextView.class));
    localView = Utils.findRequiredView(paramView, 2131231061, "method 'onButtonBack'");
    view7f080155 = localView;
    localView.setOnClickListener(new MapsStorageFragment_ViewBinding.4(this, paramMapsStorageFragment));
    localView = Utils.findRequiredView(paramView, 2131231064, "method 'onSelectDevice'");
    view7f080158 = localView;
    localView.setOnClickListener(new MapsStorageFragment_ViewBinding.5(this, paramMapsStorageFragment));
    localView = Utils.findRequiredView(paramView, 2131231067, "method 'onButtonOk'");
    view7f08015b = localView;
    localView.setOnClickListener(new MapsStorageFragment_ViewBinding.6(this, paramMapsStorageFragment));
    paramView = Utils.findRequiredView(paramView, 2131231062, "method 'onButtonCancel'");
    view7f080156 = paramView;
    paramView.setOnClickListener(new MapsStorageFragment_ViewBinding.7(this, paramMapsStorageFragment));
  }
  
  public void unbind()
  {
    MapsStorageFragment localMapsStorageFragment = target;
    if (localMapsStorageFragment != null)
    {
      target = null;
      mDeviceMemText = null;
      mDeviceMemBar = null;
      mDeviceButton = null;
      mSdCardLayout = null;
      mSdCardMemText = null;
      mSdCardMemBar = null;
      mSdCardButton = null;
      mPopupLayout = null;
      mPopupText = null;
      view7f080157.setOnClickListener(null);
      view7f080157 = null;
      view7f08015f.setOnClickListener(null);
      view7f08015f = null;
      view7f08015e.setOnClickListener(null);
      view7f08015e = null;
      view7f080155.setOnClickListener(null);
      view7f080155 = null;
      view7f080158.setOnClickListener(null);
      view7f080158 = null;
      view7f08015b.setOnClickListener(null);
      view7f08015b = null;
      view7f080156.setOnClickListener(null);
      view7f080156 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
