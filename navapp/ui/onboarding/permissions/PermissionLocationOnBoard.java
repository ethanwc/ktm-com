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
import com.ktm.navapp.ui.onboarding.permissions.DeniedLocationOnBoard;
import com.ktm.navapp.ui.onboarding.permissions.PermissionMediaOnBoard;

public class PermissionLocationOnBoard
  extends PermissionActivity
{
  @BindView(2131230790)
  protected TextView mAllowLocationDescription;
  
  public PermissionLocationOnBoard() {}
  
  public String[] getRequiredPermissions()
  {
    return getLocationPermissions();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427364);
    ButterKnife.bind(this);
    mAllowLocationDescription.setText(Html.fromHtml(getString(2131558434)));
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.PERMISSION_LOCATION.name());
  }
  
  protected void onDenyPermission()
  {
    startActivity(new Intent(this, DeniedLocationOnBoard.class));
    finish();
  }
  
  protected void onSkipClick()
  {
    requestPermissions(getRequiredPermissions());
  }
  
  protected void permissionsGranted()
  {
    startActivity(new Intent(this, PermissionMediaOnBoard.class));
    finish();
  }
}
