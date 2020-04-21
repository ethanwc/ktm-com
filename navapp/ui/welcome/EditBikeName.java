package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.connectivity.endpoints.DashboardManager;
import com.com.navapp.ui.base.NavAppBaseActivity;

public class EditBikeName
  extends NavAppBaseActivity
{
  public static final String BIKE_MAC_ADDRESS;
  public static final String BIKE_NAME;
  @BindView(2131230803)
  EditText mBikeNameEdit;
  private String mBikesMac = "";
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(com.ktm.navapp.ui.welcome.EditBikeName.class.getSimpleName());
    localStringBuilder.append(".BikeMacAddress");
    BIKE_MAC_ADDRESS = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(com.ktm.navapp.ui.welcome.EditBikeName.class.getSimpleName());
    localStringBuilder.append(".BikeName");
    BIKE_NAME = localStringBuilder.toString();
  }
  
  public EditBikeName() {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427358);
    ButterKnife.bind(this);
    paramBundle = getIntent();
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getExtras();
      if ((paramBundle != null) && (paramBundle.containsKey(BIKE_MAC_ADDRESS)) && (paramBundle.containsKey(BIKE_NAME)))
      {
        mBikesMac = paramBundle.getString(BIKE_MAC_ADDRESS);
        paramBundle = paramBundle.getString(BIKE_NAME);
        if (paramBundle != null)
        {
          mBikeNameEdit.setText(paramBundle);
          mBikeNameEdit.setSelection(paramBundle.length());
        }
      }
      else
      {
        throw new RuntimeException("Missing essential bike info!");
      }
    }
    mBikeNameEdit.requestFocus();
  }
  
  public void onSaveClicked(View paramView)
  {
    InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
    if (localInputMethodManager != null) {
      localInputMethodManager.hideSoftInputFromWindow(paramView.getWindowToken(), 0);
    }
    DashboardManager.getInstance().setDashboardAliasName(mBikesMac, mBikeNameEdit.getText().toString());
    finish();
  }
}
