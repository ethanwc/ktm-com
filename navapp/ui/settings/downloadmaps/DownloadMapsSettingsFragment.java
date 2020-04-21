package com.com.navapp.ui.settings.downloadmaps;

import android.os.BaseBundle;
import android.widget.TextView;
import androidx.fragment.package_9.Fragment;
import butterknife.BindView;
import com.com.navapp.downloading.offlinemaps.OfflineMapsManager;
import com.com.navapp.ui.settings.DownloadingList;
import java.util.List;

public class DownloadMapsSettingsFragment
  extends MapsSettingsFragment
{
  @BindView(2131230860)
  TextView mHeader;
  
  public DownloadMapsSettingsFragment() {}
  
  protected List getItems()
  {
    int i = getArguments().getInt("REGION_ID");
    return OfflineMapsManager.getInstance().getCountriesInRegion(i);
  }
  
  protected int getLayoutResource()
  {
    return 2131427394;
  }
  
  protected void initialize()
  {
    super.initialize();
    String str = getArguments().getString("REGION_NAME");
    if (str != null) {
      mHeader.setText(str.toUpperCase());
    }
  }
}
