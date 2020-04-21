package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.ktm.navapp.ui.mainscreen.MainScreen;

public class FinishPairingOnBoard
  extends NavAppBaseActivity
{
  public FinishPairingOnBoard() {}
  
  private void goToMainScreen()
  {
    startActivity(new Intent(this, MainScreen.class));
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427361);
    ButterKnife.bind(this);
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.FINISH_PAIRING.name());
  }
  
  protected void onStartTripClick()
  {
    goToMainScreen();
  }
}
