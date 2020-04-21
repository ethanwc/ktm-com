package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.ktm.navapp.ui.welcome.WelcomeScreenSelectBike;

public class WelcomeScreenGetReady
  extends NavAppBaseActivity
{
  public WelcomeScreenGetReady() {}
  
  public void onCloseClicked()
  {
    startActivity(new Intent(this, WelcomeScreenSelectBike.class));
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427370);
    ButterKnife.bind(this);
  }
  
  public void onOpenSettingsClicked()
  {
    Intent localIntent = new Intent("android.settings.BLUETOOTH_SETTINGS");
    localIntent.setFlags(603979776);
    startActivity(localIntent);
  }
}
