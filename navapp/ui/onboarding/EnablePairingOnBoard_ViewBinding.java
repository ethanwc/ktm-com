package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class EnablePairingOnBoard_ViewBinding
  implements Unbinder
{
  private EnablePairingOnBoard target;
  private View view7f0801f3;
  private View view7f0801f4;
  
  public EnablePairingOnBoard_ViewBinding(EnablePairingOnBoard paramEnablePairingOnBoard)
  {
    this(paramEnablePairingOnBoard, paramEnablePairingOnBoard.getWindow().getDecorView());
  }
  
  public EnablePairingOnBoard_ViewBinding(EnablePairingOnBoard paramEnablePairingOnBoard, View paramView)
  {
    target = paramEnablePairingOnBoard;
    View localView = Utils.findRequiredView(paramView, 2131231219, "method 'onSkipButtonClick'");
    view7f0801f3 = localView;
    localView.setOnClickListener(new EnablePairingOnBoard_ViewBinding.1(this, paramEnablePairingOnBoard));
    paramView = Utils.findRequiredView(paramView, 2131231220, "method 'onSkipPairingTextClick'");
    view7f0801f4 = paramView;
    paramView.setOnClickListener(new EnablePairingOnBoard_ViewBinding.2(this, paramEnablePairingOnBoard));
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
