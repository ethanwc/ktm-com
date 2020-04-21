package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.ktm.navapp.ui.welcome.AcknowledgementScreen;

public class WelcomeOnBoard
  extends NavAppBaseActivity
{
  @BindView(2131231348)
  AppCompatTextView mWelcomeText;
  
  public WelcomeOnBoard() {}
  
  private void configHeaderLines()
  {
    if (mWelcomeText.getText() == null) {
      return;
    }
    if (mWelcomeText.getText().toString().split(" ").length >= 2)
    {
      mWelcomeText.setMaxLines(2);
      return;
    }
    mWelcomeText.setMaxLines(1);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427369);
    ButterKnife.bind(this);
    configHeaderLines();
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.WELCOME.name());
  }
  
  public void onStartClicked()
  {
    startActivity(new Intent(this, AcknowledgementScreen.class));
    finish();
  }
}
