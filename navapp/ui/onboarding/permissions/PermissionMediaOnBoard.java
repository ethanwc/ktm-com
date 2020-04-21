package com.com.navapp.ui.onboarding.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.ktm.navapp.ui.onboarding.VoiceOnBoard;

public class PermissionMediaOnBoard
  extends PermissionActivity
{
  @BindView(2131230791)
  protected TextView mAllowStorageDescription;
  
  public PermissionMediaOnBoard() {}
  
  public String[] getRequiredPermissions()
  {
    return new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" };
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427365);
    ButterKnife.bind(this);
    mAllowStorageDescription.setText(Html.fromHtml(getString(2131558436)));
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.PERMISSION_MEDIA.name());
  }
  
  protected void onDenyPermission() {}
  
  protected void onSkipClick()
  {
    requestPermissions(getRequiredPermissions());
  }
  
  protected void permissionsGranted()
  {
    startActivity(new Intent(this, VoiceOnBoard.class));
    finish();
  }
}
