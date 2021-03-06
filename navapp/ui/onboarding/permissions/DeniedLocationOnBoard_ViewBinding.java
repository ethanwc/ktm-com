package com.com.navapp.ui.onboarding.permissions;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class DeniedLocationOnBoard_ViewBinding
  implements Unbinder
{
  private DeniedLocationOnBoard target;
  private View view7f080254;
  
  public DeniedLocationOnBoard_ViewBinding(DeniedLocationOnBoard paramDeniedLocationOnBoard)
  {
    this(paramDeniedLocationOnBoard, paramDeniedLocationOnBoard.getWindow().getDecorView());
  }
  
  public DeniedLocationOnBoard_ViewBinding(DeniedLocationOnBoard paramDeniedLocationOnBoard, View paramView)
  {
    target = paramDeniedLocationOnBoard;
    mDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230846, "field 'mDescription'", TextView.class));
    paramView = Utils.findRequiredView(paramView, 2131231316, "method 'onTurnOnClick'");
    view7f080254 = paramView;
    paramView.setOnClickListener(new DeniedLocationOnBoard_ViewBinding.1(this, paramDeniedLocationOnBoard));
  }
  
  public void unbind()
  {
    DeniedLocationOnBoard localDeniedLocationOnBoard = target;
    if (localDeniedLocationOnBoard != null)
    {
      target = null;
      mDescription = null;
      view7f080254.setOnClickListener(null);
      view7f080254 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
