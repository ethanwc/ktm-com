package com.com.navapp.ui.welcome;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class BikesListAdapter
  extends ArrayAdapter<com.ktm.navapp.connectivity.endpoints.Dashboard>
{
  BikesListAdapter(Context paramContext)
  {
    super(paramContext, 2131427439);
  }
  
  BikesListAdapter(Context paramContext, List paramList)
  {
    super(paramContext, 2131427439, paramList);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null) {
      localView = LayoutInflater.from(getContext()).inflate(2131427439, null);
    }
    paramView = (com.com.navapp.connectivity.endpoints.Dashboard)getItem(paramInt);
    if (paramView != null)
    {
      LinearLayout localLinearLayout = (LinearLayout)localView.findViewById(2131231202);
      TextView localTextView = (TextView)localView.findViewById(2131231124);
      ImageView localImageView = (ImageView)localView.findViewById(2131230802);
      Button localButton = (Button)localView.findViewById(2131230876);
      if (!paramView.aliasName().isEmpty()) {
        localTextView.setText(paramView.aliasName());
      } else {
        localTextView.setText(paramView.btDeviceName());
      }
      Resources localResources = getContext().getResources();
      if (paramView.isFavorite())
      {
        localTextView.setTextColor(localResources.getColor(2131034267, null));
        localImageView.setImageResource(2131165345);
        localButton.setTextColor(localResources.getColor(2131034267, null));
      }
      else
      {
        localTextView.setTextColor(localResources.getColor(2131034269, null));
        localImageView.setImageResource(2131165344);
        localButton.setTextColor(localResources.getColor(2131034269, null));
      }
      localLinearLayout.setOnClickListener(new BikesListAdapter.1(this, paramViewGroup, paramView));
      localButton.setOnClickListener(new BikesListAdapter.2(this, paramView));
    }
    return localView;
  }
}
