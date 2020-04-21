package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class WelcomeOnBoard_ViewBinding
  implements Unbinder
{
  private WelcomeOnBoard target;
  private View view7f080211;
  
  public WelcomeOnBoard_ViewBinding(WelcomeOnBoard paramWelcomeOnBoard)
  {
    this(paramWelcomeOnBoard, paramWelcomeOnBoard.getWindow().getDecorView());
  }
  
  public WelcomeOnBoard_ViewBinding(WelcomeOnBoard paramWelcomeOnBoard, View paramView)
  {
    target = paramWelcomeOnBoard;
    mWelcomeText = ((AppCompatTextView)Utils.findRequiredViewAsType(paramView, 2131231348, "field 'mWelcomeText'", AppCompatTextView.class));
    paramView = Utils.findRequiredView(paramView, 2131231249, "method 'onStartClicked'");
    view7f080211 = paramView;
    paramView.setOnClickListener(new WelcomeOnBoard_ViewBinding.1(this, paramWelcomeOnBoard));
  }
  
  public void unbind()
  {
    WelcomeOnBoard localWelcomeOnBoard = target;
    if (localWelcomeOnBoard != null)
    {
      target = null;
      mWelcomeText = null;
      view7f080211.setOnClickListener(null);
      view7f080211 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
