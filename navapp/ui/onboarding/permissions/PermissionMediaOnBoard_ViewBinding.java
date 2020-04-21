package com.com.navapp.ui.onboarding.permissions;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class PermissionMediaOnBoard_ViewBinding
  implements Unbinder
{
  private PermissionMediaOnBoard target;
  private View view7f0801f3;
  
  public PermissionMediaOnBoard_ViewBinding(PermissionMediaOnBoard paramPermissionMediaOnBoard)
  {
    this(paramPermissionMediaOnBoard, paramPermissionMediaOnBoard.getWindow().getDecorView());
  }
  
  public PermissionMediaOnBoard_ViewBinding(PermissionMediaOnBoard paramPermissionMediaOnBoard, View paramView)
  {
    target = paramPermissionMediaOnBoard;
    mAllowStorageDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230791, "field 'mAllowStorageDescription'", TextView.class));
    paramView = Utils.findRequiredView(paramView, 2131231219, "method 'onSkipClick'");
    view7f0801f3 = paramView;
    paramView.setOnClickListener(new PermissionMediaOnBoard_ViewBinding.1(this, paramPermissionMediaOnBoard));
  }
  
  public void unbind()
  {
    PermissionMediaOnBoard localPermissionMediaOnBoard = target;
    if (localPermissionMediaOnBoard != null)
    {
      target = null;
      mAllowStorageDescription = null;
      view7f0801f3.setOnClickListener(null);
      view7f0801f3 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
