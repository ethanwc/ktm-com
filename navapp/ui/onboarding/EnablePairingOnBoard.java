package com.com.navapp.ui.onboarding;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife;
import com.com.navapp.ui.base.NavAppBaseActivity;
import com.com.navapp.ui.startup.StartupActivity.OnboardingLevel;
import com.com.navapp.utilities.Storage;
import com.ktm.navapp.ui.mainscreen.MainScreen;
import com.ktm.navapp.ui.onboarding.TurnOnBluetoothOnBoard;
import java.util.Locale;

public class EnablePairingOnBoard
  extends NavAppBaseActivity
{
  private static final int ANIMATION_MAX_FRAMES = 500;
  private static final int ANIMATION_MIN_FRAMES = 1;
  private static final String ANIMATION_START_NAME = "img_s10_";
  private static final int FRAME_DURATION = 30;
  
  public EnablePairingOnBoard() {}
  
  private void configAnimation()
  {
    ImageView localImageView = (ImageView)findViewById(2131230999);
    try
    {
      localAnimationDrawable = createAnimation(1);
      localImageView.setBackground(localAnimationDrawable);
      localAnimationDrawable.start();
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError2)
    {
      for (;;)
      {
        try
        {
          localAnimationDrawable = createAnimation(2);
          localImageView.setBackground(localAnimationDrawable);
          localAnimationDrawable.start();
          return;
        }
        catch (OutOfMemoryError localOutOfMemoryError3)
        {
          AnimationDrawable localAnimationDrawable;
          continue;
        }
        try
        {
          localAnimationDrawable = createAnimation(6);
          localImageView.setBackground(localAnimationDrawable);
          localAnimationDrawable.start();
          return;
        }
        catch (OutOfMemoryError localOutOfMemoryError1) {}
        localOutOfMemoryError2 = localOutOfMemoryError2;
      }
    }
  }
  
  private AnimationDrawable createAnimation(int paramInt)
    throws OutOfMemoryError
  {
    AnimationDrawable localAnimationDrawable = new AnimationDrawable();
    localAnimationDrawable.setOneShot(false);
    Resources localResources = getResources();
    int i = 1;
    while (i <= 500)
    {
      int j = localResources.getIdentifier(String.format(Locale.US, "%s%04d", new Object[] { "img_s10_", Integer.valueOf(i) }), "drawable", getPackageName());
      if (j != 0)
      {
        Drawable localDrawable = localResources.getDrawable(j, null);
        if (localDrawable != null) {
          localAnimationDrawable.addFrame(localDrawable, paramInt * 30);
        }
      }
      i += paramInt;
    }
    return localAnimationDrawable;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427359);
    ButterKnife.bind(this);
    Storage.put("ONBOARDING_LEVEL", StartupActivity.OnboardingLevel.ENABLE_PAIRING_ON_YOUR_BIKE.name());
    configAnimation();
  }
  
  protected void onSkipButtonClick()
  {
    startActivity(new Intent(this, TurnOnBluetoothOnBoard.class));
    finish();
  }
  
  protected void onSkipPairingTextClick()
  {
    startActivity(new Intent(this, MainScreen.class));
    finish();
  }
}
