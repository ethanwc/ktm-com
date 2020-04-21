package com.com.navapp.logging;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.com.navapp.Globals;
import com.com.navapp.utilities.Utilities;
import io.flic.lib.FlicAppNotInstalledException;
import io.flic.lib.FlicBroadcastReceiver;
import io.flic.lib.FlicButton;
import io.flic.lib.FlicManager;

public class FlicHandler
  extends FlicBroadcastReceiver
{
  static final String FLOAT = com.ktm.navapp.logging.FlicHandler.class.getSimpleName();
  
  public FlicHandler() {}
  
  public static void grabButton(Activity paramActivity)
  {
    
    try
    {
      Context localContext = Globals.getContext();
      FlicManager.getInstance(localContext, new FlicHandler.1(paramActivity));
      return;
    }
    catch (FlicAppNotInstalledException paramActivity)
    {
      for (;;) {}
    }
    Utilities.toastShort("Flic App is not installed");
  }
  
  public static boolean handleGrabButtonResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 52875) {
      setAppCredentials();
    }
    try
    {
      Context localContext = Globals.getContext();
      FlicManager.getInstance(localContext, new FlicHandler.2(paramInt1, paramInt2, paramIntent));
    }
    catch (FlicAppNotInstalledException paramIntent)
    {
      for (;;) {}
    }
    Utilities.toastShort("Flic App is not installed");
    return true;
    return false;
  }
  
  static void setAppCredentials()
  {
    FlicManager.setAppCredentials("fc7287c2-264b-477b-a2d5-0ead288af916", "c82db5c8-e212-4923-bcce-b95873492cc5", "MyRide");
  }
  
  public void onButtonUpOrDown(Context paramContext, FlicButton paramFlicButton, boolean paramBoolean1, int paramInt, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean3)
    {
      LogExt.writeButtonTagEvent(paramFlicButton.getButtonId(), paramInt);
      Utilities.toastShort("BUTTON TAG");
    }
  }
  
  protected void onRequestAppCredentials(Context paramContext) {}
}
