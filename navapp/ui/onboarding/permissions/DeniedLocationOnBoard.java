package com.com.navapp.ui.onboarding.permissions;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.ktm.navapp.ui.onboarding.permissions.PermissionMediaOnBoard;

public class DeniedLocationOnBoard
  extends PermissionActivity
{
  @BindView(2131230846)
  protected TextView mDescription;
  
  public DeniedLocationOnBoard() {}
  
  private void configDescription()
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(getResources().getString(2131558564));
    ((StringBuilder)localObject).append("\n\n");
    ((StringBuilder)localObject).append(getResources().getString(2131558603));
    localObject = ((StringBuilder)localObject).toString();
    mDescription.setText((CharSequence)localObject);
  }
  
  public String[] getRequiredPermissions()
  {
    return getLocationPermissions();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427357);
    ButterKnife.bind(this);
    configDescription();
  }
  
  protected void onDenyPermission() {}
  
  protected void onTurnOnClick()
  {
    requestPermissions(getRequiredPermissions());
  }
  
  protected void permissionsGranted()
  {
    startActivity(new Intent(this, PermissionMediaOnBoard.class));
    finish();
  }
}
