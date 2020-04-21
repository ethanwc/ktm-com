package com.com.navapp.ui.mainscreen;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class MapUpdateFragment_ViewBinding
  implements Unbinder
{
  private MapUpdateFragment target;
  private View view7f08014a;
  private View view7f08014b;
  
  public MapUpdateFragment_ViewBinding(MapUpdateFragment paramMapUpdateFragment, View paramView)
  {
    target = paramMapUpdateFragment;
    View localView = Utils.findRequiredView(paramView, 2131231050, "field 'mBtUpdate' and method 'onUpdateButton'");
    mBtUpdate = ((Button)Utils.castView(localView, 2131231050, "field 'mBtUpdate'", Button.class));
    view7f08014a = localView;
    localView.setOnClickListener(new MapUpdateFragment_ViewBinding.1(this, paramMapUpdateFragment));
    paramView = Utils.findRequiredView(paramView, 2131231051, "method 'onSnoozeButton'");
    view7f08014b = paramView;
    paramView.setOnClickListener(new MapUpdateFragment_ViewBinding.2(this, paramMapUpdateFragment));
  }
  
  public void unbind()
  {
    MapUpdateFragment localMapUpdateFragment = target;
    if (localMapUpdateFragment != null)
    {
      target = null;
      mBtUpdate = null;
      view7f08014a.setOnClickListener(null);
      view7f08014a = null;
      view7f08014b.setOnClickListener(null);
      view7f08014b = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
