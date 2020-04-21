package com.com.navapp.ui.onboarding.permissions;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class PermissionLocationOnBoard_ViewBinding
  implements Unbinder
{
  private PermissionLocationOnBoard target;
  private View view7f0801f3;
  
  public PermissionLocationOnBoard_ViewBinding(PermissionLocationOnBoard paramPermissionLocationOnBoard)
  {
    this(paramPermissionLocationOnBoard, paramPermissionLocationOnBoard.getWindow().getDecorView());
  }
  
  public PermissionLocationOnBoard_ViewBinding(PermissionLocationOnBoard paramPermissionLocationOnBoard, View paramView)
  {
    target = paramPermissionLocationOnBoard;
    mAllowLocationDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230790, "field 'mAllowLocationDescription'", TextView.class));
    paramView = Utils.findRequiredView(paramView, 2131231219, "method 'onSkipClick'");
    view7f0801f3 = paramView;
    paramView.setOnClickListener(new PermissionLocationOnBoard_ViewBinding.1(this, paramPermissionLocationOnBoard));
  }
  
  public void unbind()
  {
    PermissionLocationOnBoard localPermissionLocationOnBoard = target;
    if (localPermissionLocationOnBoard != null)
    {
      target = null;
      mAllowLocationDescription = null;
      view7f0801f3.setOnClickListener(null);
      view7f0801f3 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
