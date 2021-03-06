package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class EnterBikeNameOnBoard_ViewBinding
  implements Unbinder
{
  private EnterBikeNameOnBoard target;
  
  public EnterBikeNameOnBoard_ViewBinding(EnterBikeNameOnBoard paramEnterBikeNameOnBoard)
  {
    this(paramEnterBikeNameOnBoard, paramEnterBikeNameOnBoard.getWindow().getDecorView());
  }
  
  public EnterBikeNameOnBoard_ViewBinding(EnterBikeNameOnBoard paramEnterBikeNameOnBoard, View paramView)
  {
    target = paramEnterBikeNameOnBoard;
    mBikeName = ((EditText)Utils.findRequiredViewAsType(paramView, 2131230803, "field 'mBikeName'", EditText.class));
  }
  
  public void unbind()
  {
    EnterBikeNameOnBoard localEnterBikeNameOnBoard = target;
    if (localEnterBikeNameOnBoard != null)
    {
      target = null;
      mBikeName = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
