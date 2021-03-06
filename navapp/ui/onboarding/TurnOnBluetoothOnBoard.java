package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.connectivity.bluetooth.BtControl;
import com.com.navapp.connectivity.bluetooth.BtControl.State;
import com.com.navapp.connectivity.endpoints.Dashboard;
import com.com.navapp.connectivity.endpoints.DashboardManager;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.ktm.navapp.ui.mainscreen.MainScreen;
import java.util.ArrayList;

public class TurnOnBluetoothOnBoard
  extends NavAppBaseActivity
{
  @BindView(2131231085)
  protected FrameLayout mNextButton;
  private boolean mTurnOnClicked = false;
  
  public TurnOnBluetoothOnBoard() {}
  
  private void checkPairedDevices()
  {
    ArrayList localArrayList = new ArrayList(DashboardManager.getInstance().listDashboards());
    Dashboard localDashboard = DashboardManager.getInstance().favorite();
    BtControl.State localState = BtControl.getInstance().getState();
    if ((!localArrayList.isEmpty()) && (localState == BtControl.State.ENABLED) && (mTurnOnClicked) && (localDashboard != null))
    {
      mNextButton.setVisibility(0);
      return;
    }
    mNextButton.setVisibility(8);
  }
  
  private void goToMainScreen()
  {
    startActivity(new Intent(this, MainScreen.class));
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427367);
    ButterKnife.bind(this);
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.TURN_ON_BLUETOOTH.name());
  }
  
  protected void onNextButtonClick()
  {
    Dashboard localDashboard = DashboardManager.getInstance().favorite();
    if ((localDashboard != null) && (localDashboard.macAddress() != null) && (!localDashboard.macAddress().isEmpty()) && (localDashboard.btDeviceName() != null))
    {
      if (localDashboard.btDeviceName().isEmpty()) {
        return;
      }
      Intent localIntent = new Intent(this, com.ktm.navapp.ui.onboarding.EnterBikeNameOnBoard.class);
      localIntent.putExtra(EnterBikeNameOnBoard.BIKE_MAC_ADDRESS, localDashboard.macAddress());
      localIntent.putExtra(EnterBikeNameOnBoard.BIKE_NAME, localDashboard.btDeviceName());
      startActivity(localIntent);
      finish();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    checkPairedDevices();
  }
  
  protected void onSkipPairingTextClick()
  {
    goToMainScreen();
  }
  
  protected void onTurnOnButtonClick()
  {
    mTurnOnClicked = true;
    startActivity(new Intent("android.settings.BLUETOOTH_SETTINGS"));
  }
}
