package com.com.navapp.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import androidx.fragment.package_9.FragmentActivity;
import androidx.fragment.package_9.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.search.TripManager;
import com.com.navapp.search.Waypoint;
import com.com.navapp.ui.base.NavAppBaseFragment;
import com.com.navapp.ui.mainscreen.MainScreen;

public class DealerDetailFragment
  extends NavAppBaseFragment
{
  static final String DEBUG_TAG = com.ktm.navapp.ui.search.DealerDetailFragment.class.getSimpleName();
  private static Waypoint sDestination;
  @BindView(2131231046)
  TextView mMapPointDetail1;
  @BindView(2131231047)
  TextView mMapPointDetail2;
  
  public DealerDetailFragment() {}
  
  public static DealerDetailFragment newInstance(Waypoint paramWaypoint)
  {
    sDestination = paramWaypoint;
    return new DealerDetailFragment();
  }
  
  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    paramBundle = sDestination;
    if (paramBundle != null)
    {
      mMapPointDetail1.setText(paramBundle.getTitle().toUpperCase());
      mMapPointDetail2.setText(paramBundle.getAddressMultiline().toUpperCase());
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    RealmLog.i(DEBUG_TAG, "onCreateView()");
    paramLayoutInflater = paramLayoutInflater.inflate(2131427393, paramViewGroup, false);
    ButterKnife.bind(this, paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onDealerCancelButton()
  {
    RealmLog.i(DEBUG_TAG, "onDealerCancelButton()");
    getActivity().getSupportFragmentManager().popBackStack();
  }
  
  public void onDealerSetButton()
  {
    RealmLog.i(DEBUG_TAG, "onDealerSetButton()");
    TripManager.initTrip(sDestination);
    ((MainScreen)getActivity()).setRoutePreviewView(false);
  }
}
