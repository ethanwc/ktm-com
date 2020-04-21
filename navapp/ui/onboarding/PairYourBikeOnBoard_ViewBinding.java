package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class PairYourBikeOnBoard_ViewBinding
  implements Unbinder
{
  private PairYourBikeOnBoard target;
  private View view7f0801f3;
  private View view7f0801f4;
  
  public PairYourBikeOnBoard_ViewBinding(PairYourBikeOnBoard paramPairYourBikeOnBoard)
  {
    this(paramPairYourBikeOnBoard, paramPairYourBikeOnBoard.getWindow().getDecorView());
  }
  
  public PairYourBikeOnBoard_ViewBinding(PairYourBikeOnBoard paramPairYourBikeOnBoard, View paramView)
  {
    target = paramPairYourBikeOnBoard;
    View localView = Utils.findRequiredView(paramView, 2131231219, "method 'onSkipButtonClick'");
    view7f0801f3 = localView;
    localView.setOnClickListener(new PairYourBikeOnBoard_ViewBinding.1(this, paramPairYourBikeOnBoard));
    paramView = Utils.findRequiredView(paramView, 2131231220, "method 'onSkipPairingTextClick'");
    view7f0801f4 = paramView;
    paramView.setOnClickListener(new PairYourBikeOnBoard_ViewBinding.2(this, paramPairYourBikeOnBoard));
  }
  
  public void unbind()
  {
    if (target != null)
    {
      target = null;
      view7f0801f3.setOnClickListener(null);
      view7f0801f3 = null;
      view7f0801f4.setOnClickListener(null);
      view7f0801f4 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
