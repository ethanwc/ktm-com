package com.com.navapp.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.search.PlacesApi;
import com.com.navapp.search.SearchHistory;
import com.com.navapp.utilities.Events;
import com.com.navapp.utilities.Utilities;
import java.util.regex.Pattern;

public class SearchAdapter
  extends ArrayAdapter<com.ktm.navapp.search.Waypoint>
{
  private static final String name = com.ktm.navapp.ui.search.SearchAdapter.class.getSimpleName();
  private Pattern mHighlight;
  private final LayoutInflater mInflater;
  private final double mLatitude;
  private final double mLongitude;
  private String mText;
  
  public SearchAdapter(Context paramContext, double paramDouble1, double paramDouble2)
  {
    super(paramContext, -1);
    mLatitude = paramDouble1;
    mLongitude = paramDouble2;
    mInflater = LayoutInflater.from(paramContext);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = mInflater.inflate(2131427458, paramViewGroup, false);
    }
    paramViewGroup = (TextView)paramView.findViewById(2131231277);
    TextView localTextView = (TextView)paramView.findViewById(2131231278);
    com.com.navapp.search.Waypoint localWaypoint = (com.com.navapp.search.Waypoint)getItem(paramInt);
    if (localWaypoint != null)
    {
      paramViewGroup.setText(Utilities.replaceStyleWithFont(localWaypoint.getTitleHighlight(mHighlight), 1, "fonts/TradeGothicLTStd-BdCn20.otf"));
      localTextView.setText(localWaypoint.getAddressSingleLine());
      return paramView;
    }
    paramViewGroup.setText("");
    localTextView.setText("");
    return paramView;
  }
  
  public void invalidateHistory()
  {
    RealmLog.i(name, "invalidateHistory()");
    String str = mText;
    if ((str == null) || (str.isEmpty()))
    {
      clear();
      addAll(SearchHistory.getHistory());
      notifyDataSetChanged();
    }
  }
  
  public void setSearchText(String paramString)
  {
    RealmLog.append(name, "setSearchText(%s)", new Object[] { paramString });
    mText = paramString;
    mHighlight = com.com.navapp.search.Waypoint.compileHighlight(paramString);
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      PlacesApi.search(paramString, mLatitude, mLongitude, new SearchAdapter.1(this));
      return;
    }
    PlacesApi.cancelSearch();
    Events.postOnMainThread(new SearchAdapter.2(this));
  }
}
