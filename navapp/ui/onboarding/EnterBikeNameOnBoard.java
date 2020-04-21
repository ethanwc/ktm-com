package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.BaseBundle;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.connectivity.endpoints.DashboardManager;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.utilities.Events;

public class EnterBikeNameOnBoard
  extends NavAppBaseActivity
{
  public static final String BIKE_MAC_ADDRESS;
  public static final String BIKE_NAME;
  static final String INTEGER = com.ktm.navapp.ui.onboarding.EnterBikeNameOnBoard.class.getSimpleName();
  @BindView(2131230803)
  EditText mBikeName;
  private String mBikesMac = "";
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(INTEGER);
    localStringBuilder.append(".BikeMacAddress");
    BIKE_MAC_ADDRESS = localStringBuilder.toString();
    localStringBuilder = new StringBuilder();
    localStringBuilder.append(INTEGER);
    localStringBuilder.append(".BikeName");
    BIKE_NAME = localStringBuilder.toString();
  }
  
  public EnterBikeNameOnBoard() {}
  
  private void configActions()
  {
    mBikeName.setOnEditorActionListener(new EnterBikeNameOnBoard.1(this));
  }
  
  private void configExtras()
  {
    Object localObject = getIntent();
    if (localObject != null)
    {
      localObject = ((Intent)localObject).getExtras();
      if ((localObject != null) && (((BaseBundle)localObject).containsKey(BIKE_MAC_ADDRESS)) && (((BaseBundle)localObject).containsKey(BIKE_NAME)))
      {
        mBikesMac = ((BaseBundle)localObject).getString(BIKE_MAC_ADDRESS, null);
        localObject = ((BaseBundle)localObject).getString(BIKE_NAME, "");
        mBikeName.setText((CharSequence)localObject);
        mBikeName.setSelection(((String)localObject).length());
        return;
      }
      throw new RuntimeException("Missing essential bike info!");
    }
  }
  
  private boolean saveNewBikeName()
  {
    String str1 = mBikeName.getText().toString();
    if (!str1.isEmpty())
    {
      String str2 = mBikesMac;
      if ((str2 != null) && (!str2.isEmpty()))
      {
        DashboardManager.getInstance().setDashboardAliasName(mBikesMac, str1);
        return true;
      }
    }
    return false;
  }
  
  private void showKeyboard()
  {
    Events.postDelayed(new EnterBikeNameOnBoard.2(this), 200L);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427360);
    ButterKnife.bind(this);
    configActions();
    configExtras();
    showKeyboard();
  }
}
