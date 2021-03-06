package com.com.navapp.ui.routing;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class NoPreviewFragment_ViewBinding
  implements Unbinder
{
  private NoPreviewFragment target;
  private View view7f0800d5;
  private View view7f0800d6;
  private View view7f0800e3;
  private View view7f0800e4;
  private View view7f0800e7;
  
  public NoPreviewFragment_ViewBinding(NoPreviewFragment paramNoPreviewFragment, View paramView)
  {
    target = paramNoPreviewFragment;
    mScreenSaveChanges = Utils.findRequiredView(paramView, 2131230954, "field 'mScreenSaveChanges'");
    View localView = Utils.findRequiredView(paramView, 2131230947, "field 'mScreenChangesSaved' and method 'onSavedOk'");
    mScreenChangesSaved = localView;
    view7f0800e3 = localView;
    localView.setOnClickListener(new NoPreviewFragment_ViewBinding.1(this, paramNoPreviewFragment));
    mScreenFindingRoute = Utils.findRequiredView(paramView, 2131230950, "field 'mScreenFindingRoute'");
    mScreenFailed = Utils.findRequiredView(paramView, 2131230949, "field 'mScreenFailed'");
    mTvSaveText = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230955, "field 'mTvSaveText'", TextView.class));
    mTvSaveTripName = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230956, "field 'mTvSaveTripName'", TextView.class));
    localView = Utils.findRequiredView(paramView, 2131230934, "method 'onSaveTripYes'");
    view7f0800d6 = localView;
    localView.setOnClickListener(new NoPreviewFragment_ViewBinding.2(this, paramNoPreviewFragment));
    localView = Utils.findRequiredView(paramView, 2131230933, "method 'onSaveTripNo'");
    view7f0800d5 = localView;
    localView.setOnClickListener(new NoPreviewFragment_ViewBinding.3(this, paramNoPreviewFragment));
    localView = Utils.findRequiredView(paramView, 2131230951, "method 'onCancelOrFailed'");
    view7f0800e7 = localView;
    localView.setOnClickListener(new NoPreviewFragment_ViewBinding.4(this, paramNoPreviewFragment));
    paramView = Utils.findRequiredView(paramView, 2131230948, "method 'onCancelOrFailed'");
    view7f0800e4 = paramView;
    paramView.setOnClickListener(new NoPreviewFragment_ViewBinding.5(this, paramNoPreviewFragment));
  }
  
  public void unbind()
  {
    NoPreviewFragment localNoPreviewFragment = target;
    if (localNoPreviewFragment != null)
    {
      target = null;
      mScreenSaveChanges = null;
      mScreenChangesSaved = null;
      mScreenFindingRoute = null;
      mScreenFailed = null;
      mTvSaveText = null;
      mTvSaveTripName = null;
      view7f0800e3.setOnClickListener(null);
      view7f0800e3 = null;
      view7f0800d6.setOnClickListener(null);
      view7f0800d6 = null;
      view7f0800d5.setOnClickListener(null);
      view7f0800d5 = null;
      view7f0800e7.setOnClickListener(null);
      view7f0800e7 = null;
      view7f0800e4.setOnClickListener(null);
      view7f0800e4 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
