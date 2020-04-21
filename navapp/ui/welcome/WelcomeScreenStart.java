package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.ktm.navapp.ui.mainscreen.MainScreen;
import com.ktm.navapp.ui.welcome.WelcomeScreenGetReady;

public class WelcomeScreenStart
  extends NavAppBaseActivity
{
  public WelcomeScreenStart() {}
  
  public void onCloseClicked()
  {
    startActivity(new Intent(this, MainScreen.class));
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427372);
    ButterKnife.bind(this);
  }
  
  public void onStartClicked()
  {
    startActivity(new Intent(this, WelcomeScreenGetReady.class));
    finish();
  }
}
