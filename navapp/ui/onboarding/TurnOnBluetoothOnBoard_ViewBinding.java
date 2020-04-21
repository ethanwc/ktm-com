package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class TurnOnBluetoothOnBoard_ViewBinding
  implements Unbinder
{
  private TurnOnBluetoothOnBoard target;
  private View view7f08016d;
  private View view7f0801f4;
  private View view7f080254;
  
  public TurnOnBluetoothOnBoard_ViewBinding(TurnOnBluetoothOnBoard paramTurnOnBluetoothOnBoard)
  {
    this(paramTurnOnBluetoothOnBoard, paramTurnOnBluetoothOnBoard.getWindow().getDecorView());
  }
  
  public TurnOnBluetoothOnBoard_ViewBinding(TurnOnBluetoothOnBoard paramTurnOnBluetoothOnBoard, View paramView)
  {
    target = paramTurnOnBluetoothOnBoard;
    View localView = Utils.findRequiredView(paramView, 2131231085, "field 'mNextButton' and method 'onNextButtonClick'");
    mNextButton = ((FrameLayout)Utils.castView(localView, 2131231085, "field 'mNextButton'", FrameLayout.class));
    view7f08016d = localView;
    localView.setOnClickListener(new TurnOnBluetoothOnBoard_ViewBinding.1(this, paramTurnOnBluetoothOnBoard));
    localView = Utils.findRequiredView(paramView, 2131231316, "method 'onTurnOnButtonClick'");
    view7f080254 = localView;
    localView.setOnClickListener(new TurnOnBluetoothOnBoard_ViewBinding.2(this, paramTurnOnBluetoothOnBoard));
    paramView = Utils.findRequiredView(paramView, 2131231220, "method 'onSkipPairingTextClick'");
    view7f0801f4 = paramView;
    paramView.setOnClickListener(new TurnOnBluetoothOnBoard_ViewBinding.3(this, paramTurnOnBluetoothOnBoard));
  }
  
  public void unbind()
  {
    TurnOnBluetoothOnBoard localTurnOnBluetoothOnBoard = target;
    if (localTurnOnBluetoothOnBoard != null)
    {
      target = null;
      mNextButton = null;
      view7f08016d.setOnClickListener(null);
      view7f08016d = null;
      view7f080254.setOnClickListener(null);
      view7f080254 = null;
      view7f0801f4.setOnClickListener(null);
      view7f0801f4 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
