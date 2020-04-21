package com.com.navapp.ui.routing;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class NoRouteOptionsFragment_ViewBinding
  implements Unbinder
{
  private NoRouteOptionsFragment target;
  private View view7f080170;
  private View view7f080171;
  private View view7f080172;
  private View view7f080173;
  private View view7f080174;
  
  public NoRouteOptionsFragment_ViewBinding(NoRouteOptionsFragment paramNoRouteOptionsFragment, View paramView)
  {
    target = paramNoRouteOptionsFragment;
    View localView = Utils.findRequiredView(paramView, 2131231090, "field 'mAvoidTollsCheckbox' and method 'onAvoidTolls'");
    mAvoidTollsCheckbox = ((CheckBox)Utils.castView(localView, 2131231090, "field 'mAvoidTollsCheckbox'", CheckBox.class));
    view7f080172 = localView;
    localView.setOnClickListener(new NoRouteOptionsFragment_ViewBinding.1(this, paramNoRouteOptionsFragment));
    localView = Utils.findRequiredView(paramView, 2131231089, "field 'mAvoidHighwaysCheckbox' and method 'onAvoidHighways'");
    mAvoidHighwaysCheckbox = ((CheckBox)Utils.castView(localView, 2131231089, "field 'mAvoidHighwaysCheckbox'", CheckBox.class));
    view7f080171 = localView;
    localView.setOnClickListener(new NoRouteOptionsFragment_ViewBinding.2(this, paramNoRouteOptionsFragment));
    localView = Utils.findRequiredView(paramView, 2131231088, "field 'mAvoidDirtRoadsCheckbox' and method 'onAvoidDirtroads'");
    mAvoidDirtRoadsCheckbox = ((CheckBox)Utils.castView(localView, 2131231088, "field 'mAvoidDirtRoadsCheckbox'", CheckBox.class));
    view7f080170 = localView;
    localView.setOnClickListener(new NoRouteOptionsFragment_ViewBinding.3(this, paramNoRouteOptionsFragment));
    mDescriptionText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230904, "field 'mDescriptionText'", TextView.class));
    localView = Utils.findRequiredView(paramView, 2131231092, "method 'onDone'");
    view7f080174 = localView;
    localView.setOnClickListener(new NoRouteOptionsFragment_ViewBinding.4(this, paramNoRouteOptionsFragment));
    paramView = Utils.findRequiredView(paramView, 2131231091, "method 'onGoBack'");
    view7f080173 = paramView;
    paramView.setOnClickListener(new NoRouteOptionsFragment_ViewBinding.5(this, paramNoRouteOptionsFragment));
  }
  
  public void unbind()
  {
    NoRouteOptionsFragment localNoRouteOptionsFragment = target;
    if (localNoRouteOptionsFragment != null)
    {
      target = null;
      mAvoidTollsCheckbox = null;
      mAvoidHighwaysCheckbox = null;
      mAvoidDirtRoadsCheckbox = null;
      mDescriptionText = null;
      view7f080172.setOnClickListener(null);
      view7f080172 = null;
      view7f080171.setOnClickListener(null);
      view7f080171 = null;
      view7f080170.setOnClickListener(null);
      view7f080170 = null;
      view7f080174.setOnClickListener(null);
      view7f080174 = null;
      view7f080173.setOnClickListener(null);
      view7f080173 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
