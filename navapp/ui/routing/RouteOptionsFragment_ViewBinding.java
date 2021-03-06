package com.com.navapp.ui.routing;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class RouteOptionsFragment_ViewBinding
  implements Unbinder
{
  private RouteOptionsFragment target;
  private View view7f0800ba;
  private View view7f0801a6;
  private View view7f0801a8;
  private View view7f0801a9;
  private View view7f0801aa;
  
  public RouteOptionsFragment_ViewBinding(RouteOptionsFragment paramRouteOptionsFragment, View paramView)
  {
    target = paramRouteOptionsFragment;
    mMainHeaderTextView = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230907, "field 'mMainHeaderTextView'", TextView.class));
    mAvoidTollsCheckbox = ((CheckBox)Utils.findRequiredViewAsType(paramView, 2131231141, "field 'mAvoidTollsCheckbox'", CheckBox.class));
    mAvoidHighwaysCheckbox = ((CheckBox)Utils.findRequiredViewAsType(paramView, 2131231140, "field 'mAvoidHighwaysCheckbox'", CheckBox.class));
    mAvoidDirtRoadsCheckbox = ((CheckBox)Utils.findRequiredViewAsType(paramView, 2131231139, "field 'mAvoidDirtRoadsCheckbox'", CheckBox.class));
    mFastestRadioButton = ((RadioButton)Utils.findRequiredViewAsType(paramView, 2131231143, "field 'mFastestRadioButton'", RadioButton.class));
    mShortestRadioButton = ((RadioButton)Utils.findRequiredViewAsType(paramView, 2131231148, "field 'mShortestRadioButton'", RadioButton.class));
    mSaveOrPreview = Utils.findRequiredView(paramView, 2131231147, "field 'mSaveOrPreview'");
    View localView = Utils.findRequiredView(paramView, 2131231145, "field 'mRideItButton' and method 'onButtonRideIt'");
    mRideItButton = ((Button)Utils.castView(localView, 2131231145, "field 'mRideItButton'", Button.class));
    view7f0801a9 = localView;
    localView.setOnClickListener(new RouteOptionsFragment_ViewBinding.1(this, paramRouteOptionsFragment));
    localView = Utils.findRequiredView(paramView, 2131230906, "method 'onAddWaypoints'");
    view7f0800ba = localView;
    localView.setOnClickListener(new RouteOptionsFragment_ViewBinding.2(this, paramRouteOptionsFragment));
    localView = Utils.findRequiredView(paramView, 2131231146, "method 'onButtonSave'");
    view7f0801aa = localView;
    localView.setOnClickListener(new RouteOptionsFragment_ViewBinding.3(this, paramRouteOptionsFragment));
    localView = Utils.findRequiredView(paramView, 2131231144, "method 'onButtonPreview'");
    view7f0801a8 = localView;
    localView.setOnClickListener(new RouteOptionsFragment_ViewBinding.4(this, paramRouteOptionsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231142, "method 'onButtonBack'");
    view7f0801a6 = paramView;
    paramView.setOnClickListener(new RouteOptionsFragment_ViewBinding.5(this, paramRouteOptionsFragment));
  }
  
  public void unbind()
  {
    RouteOptionsFragment localRouteOptionsFragment = target;
    if (localRouteOptionsFragment != null)
    {
      target = null;
      mMainHeaderTextView = null;
      mAvoidTollsCheckbox = null;
      mAvoidHighwaysCheckbox = null;
      mAvoidDirtRoadsCheckbox = null;
      mFastestRadioButton = null;
      mShortestRadioButton = null;
      mSaveOrPreview = null;
      mRideItButton = null;
      view7f0801a9.setOnClickListener(null);
      view7f0801a9 = null;
      view7f0800ba.setOnClickListener(null);
      view7f0800ba = null;
      view7f0801aa.setOnClickListener(null);
      view7f0801aa = null;
      view7f0801a8.setOnClickListener(null);
      view7f0801a8 = null;
      view7f0801a6.setOnClickListener(null);
      view7f0801a6 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
