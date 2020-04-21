package com.com.navapp.ui.welcome;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class AcknowledgementFragment_ViewBinding
  implements Unbinder
{
  private AcknowledgementFragment target;
  private View view7f0800c5;
  
  public AcknowledgementFragment_ViewBinding(AcknowledgementFragment paramAcknowledgementFragment, View paramView)
  {
    target = paramAcknowledgementFragment;
    mDescription = ((TextView)Utils.findRequiredViewAsType(paramView, 2131230846, "field 'mDescription'", TextView.class));
    paramView = Utils.findRequiredView(paramView, 2131230917, "method 'onGoClick'");
    view7f0800c5 = paramView;
    paramView.setOnClickListener(new AcknowledgementFragment_ViewBinding.1(this, paramAcknowledgementFragment));
  }
  
  public void unbind()
  {
    AcknowledgementFragment localAcknowledgementFragment = target;
    if (localAcknowledgementFragment != null)
    {
      target = null;
      mDescription = null;
      view7f0800c5.setOnClickListener(null);
      view7f0800c5 = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
