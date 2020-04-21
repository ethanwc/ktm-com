package com.com.navapp.ui.settings.downloadvoices;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.com.navapp.downloading.voice.VoiceManager;
import java.util.List;

public class InstalledVoicesListAdapter
  extends VoicesListAdapter
{
  public InstalledVoicesListAdapter(Context paramContext, List paramList)
  {
    super(paramContext, 2131427438, paramList);
  }
  
  private void setSelectedElement(DownloadVoice paramDownloadVoice, CompoundButton paramCompoundButton, TextView paramTextView)
  {
    if (packageId == -1L) {
      hideViews(new View[] { paramTextView });
    } else {
      showViews(new View[] { paramTextView });
    }
    paramCompoundButton.setChecked(true);
  }
  
  private void setUnSelectedElement(DownloadVoice paramDownloadVoice, CompoundButton paramCompoundButton, TextView paramTextView)
  {
    if (packageId == -1L) {
      hideViews(new View[] { paramTextView });
    } else {
      showViews(new View[] { paramTextView });
    }
    paramCompoundButton.setChecked(false);
  }
  
  protected void configButton(DownloadVoice paramDownloadVoice, View paramView)
  {
    CompoundButton localCompoundButton = (CompoundButton)paramView.findViewById(2131230993);
    TextView localTextView = (TextView)paramView.findViewById(2131230994);
    localTextView.setText(paramDownloadVoice.getContentSize());
    if (VoiceManager.getInstance().isSelectedLanguage(packageId)) {
      setSelectedElement(paramDownloadVoice, localCompoundButton, localTextView);
    } else {
      setUnSelectedElement(paramDownloadVoice, localCompoundButton, localTextView);
    }
    paramView.setOnClickListener(new InstalledVoicesListAdapter.1(this, paramDownloadVoice));
    localCompoundButton.setOnClickListener(new InstalledVoicesListAdapter.2(this, paramDownloadVoice));
  }
  
  protected int getLayoutResource()
  {
    return 2131427438;
  }
}
