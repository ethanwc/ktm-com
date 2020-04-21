package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FinishPairingOnBoard_ViewBinding
  implements Unbinder
{
  private FinishPairingOnBoard target;
  private View view7f080212;
  
  public FinishPairingOnBoard_ViewBinding(FinishPairingOnBoard paramFinishPairingOnBoard)
  {
    this(paramFinishPairingOnBoard, paramFinishPairingOnBoard.getWindow().getDecorView());
  }
  
  public FinishPairingOnBoard_ViewBinding(FinishPairingOnBoard paramFinishPairingOnBoard, View paramView)
  {
    target = paramFinishPairingOnBoard;
    paramView = Utils.findRequiredView(paramView, 2131231250, "method 'onStartTripClick'");
    view7f080212 = paramView;
    paramView.setOnClickListener(new FinishPairingOnBoard_ViewBinding.1(this, paramFinishPairingOnBoard));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f080212.setOnClickListener(null);
      view7f080212 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
