package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class VoiceOnBoard_ViewBinding
  implements Unbinder
{
  private VoiceOnBoard target;
  private View view7f0801f3;
  
  public VoiceOnBoard_ViewBinding(VoiceOnBoard paramVoiceOnBoard)
  {
    this(paramVoiceOnBoard, paramVoiceOnBoard.getWindow().getDecorView());
  }
  
  public VoiceOnBoard_ViewBinding(VoiceOnBoard paramVoiceOnBoard, View paramView)
  {
    target = paramVoiceOnBoard;
    mDownloadingItem = ((RelativeLayout)Utils.findRequiredViewAsType(paramView, 2131230861, "field 'mDownloadingItem'", RelativeLayout.class));
    mDownloadingItemProgressBar = ((ProgressBar)Utils.findRequiredViewAsType(paramView, 2131231002, "field 'mDownloadingItemProgressBar'", ProgressBar.class));
    mCannotDownloadLabel = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230817, "field 'mCannotDownloadLabel'", TextView.class));
    mBottomLine = Utils.findRequiredView(paramView, 2131230807, "field 'mBottomLine'");
    paramView = Utils.findRequiredView(paramView, 2131231219, "method 'onSkipClick'");
    view7f0801f3 = paramView;
    paramView.setOnClickListener(new VoiceOnBoard_ViewBinding.1(this, paramVoiceOnBoard));
  }
  
  public void unbind()
  {
    VoiceOnBoard localVoiceOnBoard = target;
    if (localVoiceOnBoard != null)
    {
      target = null;
      mDownloadingItem = null;
      mDownloadingItemProgressBar = null;
      mCannotDownloadLabel = null;
      mBottomLine = null;
      view7f0801f3.setOnClickListener(null);
      view7f0801f3 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
