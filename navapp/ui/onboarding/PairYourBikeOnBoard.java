package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.ktm.navapp.ui.mainscreen.MainScreen;
import com.ktm.navapp.ui.onboarding.EnablePairingOnBoard;

public class PairYourBikeOnBoard
  extends NavAppBaseActivity
{
  public PairYourBikeOnBoard() {}
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427363);
    ButterKnife.bind(this);
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.PAIR_YOUR_BIKE.name());
  }
  
  protected void onSkipButtonClick()
  {
    startActivity(new Intent(this, EnablePairingOnBoard.class));
    finish();
  }
  
  protected void onSkipPairingTextClick()
  {
    startActivity(new Intent(this, MainScreen.class));
    finish();
  }
}
