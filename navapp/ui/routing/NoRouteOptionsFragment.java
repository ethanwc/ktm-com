package com.com.navapp.ui.routing;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;
import com.com.navapp.ui.mainscreen.OnBackPressedHandler;

public class NoRouteOptionsFragment
  extends NavAppBaseFragment
  implements OnBackPressedHandler
{
  @BindView(2131231088)
  protected CheckBox mAvoidDirtRoadsCheckbox;
  @BindView(2131231089)
  protected CheckBox mAvoidHighwaysCheckbox;
  @BindView(2131231090)
  protected CheckBox mAvoidTollsCheckbox;
  @BindView(2131230904)
  protected TextView mDescriptionText;
  private boolean mPopOnPause;
  
  public NoRouteOptionsFragment() {}
  
  private void applyOptions()
  {
    TripManager.setOptions(getTripfastestRoute, mAvoidTollsCheckbox.isChecked() ^ true, mAvoidHighwaysCheckbox.isChecked() ^ true, mAvoidDirtRoadsCheckbox.isChecked() ^ true);
  }
  
  private void backToRoutePreview(boolean paramBoolean)
  {
    mPopOnPause = false;
    getActivity().getSupportFragmentManager().popBackStack();
    if (paramBoolean) {
      getActivity().getSupportFragmentManager().popBackStack();
    }
  }
  
  private void configOptions()
  {
    Trip localTrip = TripManager.getTrip();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append(mDescriptionText.getText().toString().replace("...", localTrip.getTitle()));
    ((StringBuilder)localObject).append("\n\n");
    ((StringBuilder)localObject).append(getResources().getString(2131558452));
    localObject = ((StringBuilder)localObject).toString();
    mDescriptionText.setText((CharSequence)localObject);
    mAvoidTollsCheckbox.setChecked(allowTolls ^ true);
    mAvoidHighwaysCheckbox.setChecked(allowHighways ^ true);
    mAvoidDirtRoadsCheckbox.setChecked(allowDirtRoads ^ true);
  }
  
  public void onAvoidDirtroads()
  {
    applyOptions();
  }
  
  public void onAvoidHighways()
  {
    applyOptions();
  }
  
  public void onAvoidTolls()
  {
    applyOptions();
  }
  
  public boolean onBackPressed()
  {
    onGoBack();
    return true;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427413, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    return paramLayoutInflater;
  }
  
  public void onDone()
  {
    backToRoutePreview(false);
  }
  
  public void onGoBack()
  {
    backToRoutePreview(true);
  }
  
  public void onPause()
  {
    super.onPause();
    if (mPopOnPause) {
      getActivity().getSupportFragmentManager().popBackStack();
    }
  }
  
  public void onResume()
  {
    super.onResume();
    mPopOnPause = true;
    configOptions();
  }
}
