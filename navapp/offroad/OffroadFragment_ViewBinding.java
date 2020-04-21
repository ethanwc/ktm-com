package com.com.navapp.offroad;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class OffroadFragment_ViewBinding
  implements Unbinder
{
  private OffroadFragment target;
  private View view7f08013a;
  private View view7f08013d;
  private View view7f08013e;
  private View view7f08017e;
  private View view7f080183;
  private View view7f080184;
  private View view7f080187;
  
  public OffroadFragment_ViewBinding(OffroadFragment paramOffroadFragment, View paramView)
  {
    target = paramOffroadFragment;
    mGuidanceOverlay = ((LinearLayout)Utils.findRequiredViewAsType(paramView, 2131231113, "field 'mGuidanceOverlay'", LinearLayout.class));
    View localView = Utils.findRequiredView(paramView, 2131231102, "field 'mCancelButton' and method 'onCancelButton'");
    mCancelButton = ((ImageButton)Utils.castView(localView, 2131231102, "field 'mCancelButton'", ImageButton.class));
    view7f08017e = localView;
    localView.setOnClickListener(new OffroadFragment_ViewBinding.1(this, paramOffroadFragment));
    localView = Utils.findRequiredView(paramView, 2131231107, "field 'mDestReached' and method 'onDestinationReachedClicked'");
    mDestReached = ((Button)Utils.castView(localView, 2131231107, "field 'mDestReached'", Button.class));
    view7f080183 = localView;
    localView.setOnClickListener(new OffroadFragment_ViewBinding.2(this, paramOffroadFragment));
    mCompassDisk = ((ImageView)Utils.findRequiredViewAsType(paramView, 2131231103, "field 'mCompassDisk'", ImageView.class));
    mCompassNeedle = ((ImageView)Utils.findRequiredViewAsType(paramView, 2131231106, "field 'mCompassNeedle'", ImageView.class));
    mCompassDistance = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231104, "field 'mCompassDistance'", TextView.class));
    mCompassHeading = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231105, "field 'mCompassHeading'", TextView.class));
    mMapControls = ((LinearLayout)Utils.findRequiredViewAsType(paramView, 2131231114, "field 'mMapControls'", LinearLayout.class));
    mFinishScreen = Utils.findRequiredView(paramView, 2131231112, "field 'mFinishScreen'");
    mFinishHeader = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231110, "field 'mFinishHeader'", TextView.class));
    mFinishDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131231109, "field 'mFinishDescription'", TextView.class));
    localView = Utils.findRequiredView(paramView, 2131231108, "method 'onFinishBackClicked'");
    view7f080184 = localView;
    localView.setOnClickListener(new OffroadFragment_ViewBinding.3(this, paramOffroadFragment));
    localView = Utils.findRequiredView(paramView, 2131231111, "method 'onFinishOkClicked'");
    view7f080187 = localView;
    localView.setOnClickListener(new OffroadFragment_ViewBinding.4(this, paramOffroadFragment));
    localView = Utils.findRequiredView(paramView, 2131231034, "method 'onCenterButton'");
    view7f08013a = localView;
    localView.setOnClickListener(new OffroadFragment_ViewBinding.5(this, paramOffroadFragment));
    localView = Utils.findRequiredView(paramView, 2131231038, "method 'onZoomIn'");
    view7f08013e = localView;
    localView.setOnClickListener(new OffroadFragment_ViewBinding.6(this, paramOffroadFragment));
    paramView = Utils.findRequiredView(paramView, 2131231037, "method 'onZoomOut'");
    view7f08013d = paramView;
    paramView.setOnClickListener(new OffroadFragment_ViewBinding.7(this, paramOffroadFragment));
  }
  
  public void unbind()
  {
    OffroadFragment localOffroadFragment = target;
    if (localOffroadFragment != null)
    {
      target = null;
      mGuidanceOverlay = null;
      mCancelButton = null;
      mDestReached = null;
      mCompassDisk = null;
      mCompassNeedle = null;
      mCompassDistance = null;
      mCompassHeading = null;
      mMapControls = null;
      mFinishScreen = null;
      mFinishHeader = null;
      mFinishDescription = null;
      view7f08017e.setOnClickListener(null);
      view7f08017e = null;
      view7f080183.setOnClickListener(null);
      view7f080183 = null;
      view7f080184.setOnClickListener(null);
      view7f080184 = null;
      view7f080187.setOnClickListener(null);
      view7f080187 = null;
      view7f08013a.setOnClickListener(null);
      view7f08013a = null;
      view7f08013e.setOnClickListener(null);
      view7f08013e = null;
      view7f08013d.setOnClickListener(null);
      view7f08013d = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
