package com.com.navapp.ui.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.connectivity.endpoints.DashboardManager;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.ktm.navapp.ui.welcome.WelcomeScreenGetReady;
import java.util.ArrayList;

public class WelcomeScreenSelectBike
  extends NavAppBaseActivity
{
  BikesListAdapter mBikesListAdapter;
  @BindView(2131231343)
  ListView mBikesListView;
  
  public WelcomeScreenSelectBike() {}
  
  public void onAddBikeClicked()
  {
    Intent localIntent = new Intent(this, WelcomeScreenGetReady.class);
    localIntent.setFlags(603979776);
    startActivity(localIntent);
    finish();
  }
  
  public void onCloseClicked()
  {
    finish();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427371);
    ButterKnife.bind(this);
  }
  
  public void onResume()
  {
    super.onResume();
    ArrayList localArrayList = new ArrayList(DashboardManager.getInstance().listDashboards());
    mBikesListAdapter = new BikesListAdapter(this, localArrayList);
    mBikesListView.setAdapter(mBikesListAdapter);
    if (localArrayList.isEmpty()) {
      findViewById(2131231024).setVisibility(4);
    }
  }
}
