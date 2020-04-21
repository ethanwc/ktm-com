package com.com.navapp.ui.routing;

import android.os.BaseBundle;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import androidx.fragment.package_9.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.search.Trip;
import com.com.navapp.search.TripManager;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.ExtendedLogoPosition;
import com.com.navapp.ui.mainscreen.MainScreen;

public class RouteOptionsFragment
  extends NavAppBaseFragment
{
  static final String ARG_SINGLE_BUTTON = "SINGLE_BUTTON";
  static final String TAG = com.ktm.navapp.ui.routing.RouteOptionsFragment.class.getSimpleName();
  @BindView(2131231139)
  protected CheckBox mAvoidDirtRoadsCheckbox;
  @BindView(2131231140)
  protected CheckBox mAvoidHighwaysCheckbox;
  @BindView(2131231141)
  protected CheckBox mAvoidTollsCheckbox;
  @BindView(2131231143)
  protected RadioButton mFastestRadioButton;
  @BindView(2131230907)
  protected TextView mMainHeaderTextView;
  @BindView(2131231145)
  protected Button mRideItButton;
  @BindView(2131231147)
  protected View mSaveOrPreview;
  @BindView(2131231148)
  protected RadioButton mShortestRadioButton;
  
  public RouteOptionsFragment() {}
  
  private void applyOptions()
  {
    TripManager.setOptions(mFastestRadioButton.isChecked(), mAvoidTollsCheckbox.isChecked() ^ true, mAvoidHighwaysCheckbox.isChecked() ^ true, mAvoidDirtRoadsCheckbox.isChecked() ^ true);
  }
  
  public static RouteOptionsFragment newInstance(boolean paramBoolean)
  {
    RealmLog.append(TAG, "newInstance( singleButtonRideIt = %b )", new Object[] { Boolean.valueOf(paramBoolean) });
    RouteOptionsFragment localRouteOptionsFragment = new RouteOptionsFragment();
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("SINGLE_BUTTON", paramBoolean);
    localRouteOptionsFragment.setArguments(localBundle);
    return localRouteOptionsFragment;
  }
  
  private void refreshUi()
  {
    Trip localTrip = TripManager.getTrip();
    mMainHeaderTextView.setText(localTrip.getTitle());
    mAvoidTollsCheckbox.setChecked(allowTolls ^ true);
    mAvoidHighwaysCheckbox.setChecked(allowHighways ^ true);
    mAvoidDirtRoadsCheckbox.setChecked(allowDirtRoads ^ true);
    if (fastestRoute)
    {
      mFastestRadioButton.setChecked(true);
      return;
    }
    mShortestRadioButton.setChecked(true);
  }
  
  public void onAddWaypoints()
  {
    applyOptions();
    FragmentTransaction localFragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
    Bundle localBundle = getArguments();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (localBundle != null)
    {
      bool1 = bool2;
      if (getArguments().getBoolean("SINGLE_BUTTON", false)) {
        bool1 = true;
      }
    }
    localFragmentTransaction.replace(2131231033, RouteWaypointsFragment.newInstance(bool1)).addToBackStack(null).commit();
  }
  
  public void onButtonBack()
  {
    TripManager.restoreTrip(false);
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onButtonPreview()
  {
    applyOptions();
    if (getActivity().getSupportFragmentManager().popBackStackImmediate("ROUTE_PREVIEW_FRAGMENT_TAG", 0))
    {
      RealmLog.i(TAG, "Preview from backstack");
      return;
    }
    RealmLog.i(TAG, "New Preview");
    ((MainScreen)getActivity()).setRoutePreviewView(false);
  }
  
  public void onButtonRideIt()
  {
    applyOptions();
    getActivity().getSupportFragmentManager().popBackStack();
    getActivity().getSupportFragmentManager().beginTransaction().replace(2131231033, new NoPreviewFragment()).addToBackStack(null).commit();
  }
  
  public void onButtonSave()
  {
    applyOptions();
    ((MainScreen)getActivity()).setSaveTripView();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(2131427418, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    if ((getArguments() != null) && (getArguments().getBoolean("SINGLE_BUTTON", false)))
    {
      mSaveOrPreview.setVisibility(8);
      mRideItButton.setVisibility(0);
    }
    else
    {
      mSaveOrPreview.setVisibility(0);
      mRideItButton.setVisibility(8);
    }
    ((MainScreen)getActivity()).configCopyrightIconPosition(ExtendedLogoPosition.BOTTOM_RIGHT);
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    refreshUi();
  }
}
