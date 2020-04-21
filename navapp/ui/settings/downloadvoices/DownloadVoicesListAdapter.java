package com.com.navapp.ui.settings.downloadvoices;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.com.navapp.downloading.DownloadManager;
import com.com.navapp.downloading.voice.VoiceManager;
import java.util.List;

public class DownloadVoicesListAdapter
  extends VoicesListAdapter
{
  public DownloadVoicesListAdapter(Context paramContext, List paramList)
  {
    super(paramContext, 2131427389, paramList);
  }
  
  private void setToInstalled(ProgressBar paramProgressBar, ImageButton paramImageButton, TextView paramTextView, View... paramVarArgs)
  {
    paramProgressBar.setIndeterminate(false);
    showViews(new View[] { paramImageButton, paramTextView });
    hideViews(paramVarArgs);
  }
  
  private void setToNotInstalled(ProgressBar paramProgressBar, ImageButton paramImageButton, TextView paramTextView, View... paramVarArgs)
  {
    paramProgressBar.setIndeterminate(false);
    showViews(new View[] { paramImageButton, paramTextView });
    hideViews(paramVarArgs);
  }
  
  private void setToWaiting(DownloadVoice paramDownloadVoice, TextView paramTextView, ProgressBar paramProgressBar, ImageButton paramImageButton, View... paramVarArgs)
  {
    paramTextView.setText(2131558701);
    paramProgressBar.setIndeterminate(true);
    showViews(new View[] { paramTextView, paramProgressBar, paramImageButton });
    hideViews(paramVarArgs);
    if (VoiceManager.getInstance().isActualPackageId(Long.valueOf(packageId))) {
      hideViews(new View[] { paramImageButton });
    }
  }
  
  protected void configButton(DownloadVoice paramDownloadVoice, View paramView)
  {
    ImageButton localImageButton1 = (ImageButton)paramView.findViewById(2131230872);
    ImageButton localImageButton2 = (ImageButton)paramView.findViewById(2131230870);
    TextView localTextView1 = (TextView)paramView.findViewById(2131230874);
    TextView localTextView2 = (TextView)paramView.findViewById(2131230871);
    paramView = (ProgressBar)paramView.findViewById(2131230873);
    localTextView2.setText(paramDownloadVoice.getDownloadSize());
    int i = 3.$SwitchMap$com$ktm$navapp$ui$settings$DownloadingList$DataStatus[status.ordinal()];
    if (i != 1)
    {
      if (i != 2)
      {
        if (i != 3)
        {
          if (i == 4)
          {
            localTextView2.setText(paramDownloadVoice.getContentSize());
            setToInstalled(paramView, localImageButton2, localTextView2, new View[] { localImageButton1, localTextView1, paramView });
          }
        }
        else {
          setToWaiting(paramDownloadVoice, localTextView1, paramView, localImageButton2, new View[] { localImageButton1, localTextView2 });
        }
      }
      else
      {
        paramView.setIndeterminate(false);
        paramView.setProgress(progress);
        if (progress != 100)
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramDownloadVoice.getDownloadedSize());
          localStringBuilder.append("/");
          localTextView1.setText(localStringBuilder.toString());
          showViews(new View[] { localTextView1, localTextView2, paramView, localImageButton2 });
          hideViews(new View[] { localImageButton1 });
        }
        else
        {
          localTextView1.setText(2131558524);
          showViews(new View[] { localTextView1, paramView, localImageButton2 });
          hideViews(new View[] { localImageButton1, localTextView2 });
        }
      }
    }
    else {
      setToNotInstalled(paramView, localImageButton1, localTextView2, new View[] { localImageButton2, localTextView1, paramView });
    }
    localImageButton1.setOnClickListener(new DownloadVoicesListAdapter.1(this, paramDownloadVoice, localTextView1, paramView, localImageButton2, localImageButton1, localTextView2));
    localImageButton2.setOnClickListener(new DownloadVoicesListAdapter.2(this, paramDownloadVoice, paramView, localImageButton1, localTextView2, localImageButton2, localTextView1));
  }
  
  protected int getLayoutResource()
  {
    return 2131427389;
  }
}
