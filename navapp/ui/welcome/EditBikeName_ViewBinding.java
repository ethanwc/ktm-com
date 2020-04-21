package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class EditBikeName_ViewBinding
  implements Unbinder
{
  private EditBikeName target;
  private View view7f0801ba;
  
  public EditBikeName_ViewBinding(EditBikeName paramEditBikeName)
  {
    this(paramEditBikeName, paramEditBikeName.getWindow().getDecorView());
  }
  
  public EditBikeName_ViewBinding(EditBikeName paramEditBikeName, View paramView)
  {
    target = paramEditBikeName;
    mBikeNameEdit = ((EditText)Utils.findRequiredViewAsType(paramView, 2131230803, "field 'mBikeNameEdit'", EditText.class));
    paramView = Utils.findRequiredView(paramView, 2131231162, "method 'onSaveClicked'");
    view7f0801ba = paramView;
    paramView.setOnClickListener(new EditBikeName_ViewBinding.1(this, paramEditBikeName));
  }
  
  public void unbind()
  {
    EditBikeName localEditBikeName = target;
    if (localEditBikeName != null)
    {
      target = null;
      mBikeNameEdit = null;
      view7f0801ba.setOnClickListener(null);
      view7f0801ba = null;
      return;
    }
    throw new IllegalStateException("Bindings already cleared.");
  }
}
