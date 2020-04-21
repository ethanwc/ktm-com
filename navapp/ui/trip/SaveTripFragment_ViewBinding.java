package com.com.navapp.ui.trip;

import android.view.View;
import android.widget.EditText;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class SaveTripFragment_ViewBinding
  implements Unbinder
{
  private SaveTripFragment target;
  private View view7f080248;
  private View view7f080249;
  private View view7f080251;
  
  public SaveTripFragment_ViewBinding(SaveTripFragment paramSaveTripFragment, View paramView)
  {
    target = paramSaveTripFragment;
    mScreenSave = Utils.findRequiredView(paramView, 2131231175, "field 'mScreenSave'");
    mScreenOverwrite = Utils.findRequiredView(paramView, 2131231171, "field 'mScreenOverwrite'");
    mEditText = ((EditText)Utils.findRequiredViewAsType(paramView, 2131231314, "field 'mEditText'", EditText.class));
    mRvTrips = ((RecyclerView)Utils.findRequiredViewAsType(paramView, 2131231315, "field 'mRvTrips'", RecyclerView.class));
    View localView = Utils.findRequiredView(paramView, 2131231313, "method 'onBackButton'");
    view7f080251 = localView;
    localView.setOnClickListener(new SaveTripFragment_ViewBinding.1(this, paramSaveTripFragment));
    localView = Utils.findRequiredView(paramView, 2131231304, "method 'onOverwriteNo'");
    view7f080248 = localView;
    localView.setOnClickListener(new SaveTripFragment_ViewBinding.2(this, paramSaveTripFragment));
    paramView = Utils.findRequiredView(paramView, 2131231305, "method 'onOverwriteYes'");
    view7f080249 = paramView;
    paramView.setOnClickListener(new SaveTripFragment_ViewBinding.3(this, paramSaveTripFragment));
  }
  
  public void unbind()
  {
    SaveTripFragment localSaveTripFragment = target;
    if (localSaveTripFragment != null)
    {
      target = null;
      mScreenSave = null;
      mScreenOverwrite = null;
      mEditText = null;
      mRvTrips = null;
      view7f080251.setOnClickListener(null);
      view7f080251 = null;
      view7f080248.setOnClickListener(null);
      view7f080248 = null;
      view7f080249.setOnClickListener(null);
      view7f080249 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
