package com.com.navapp.ui.settings.downloadmaps.region;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class RegionMapsListAdapter
  extends ArrayAdapter<com.ktm.navapp.ui.settings.downloadmaps.region.RegionMap>
{
  public RegionMapsListAdapter(Context paramContext, List paramList)
  {
    super(paramContext, 2131427457, paramList);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null) {
      paramView = LayoutInflater.from(getContext()).inflate(2131427457, null);
    }
    paramViewGroup = (TextView)paramView.findViewById(2131231134);
    RegionMap localRegionMap = (RegionMap)getItem(paramInt);
    if (localRegionMap != null) {
      paramViewGroup.setText(header.toUpperCase());
    }
    return paramView;
  }
}
