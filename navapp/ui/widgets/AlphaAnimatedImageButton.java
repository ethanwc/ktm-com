package com.com.navapp.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.appcompat.widget.AppCompatImageButton;

public class AlphaAnimatedImageButton
  extends AppCompatImageButton
{
  protected static final int ANIMATION_DURATION = 300;
  protected static final float MAX_ALPHA = 1.0F;
  protected static final float MIN_ALPHA = 0.0F;
  protected float mStartAlpha = 1.0F;
  
  public AlphaAnimatedImageButton(Context paramContext)
  {
    super(paramContext);
  }
  
  public AlphaAnimatedImageButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public AlphaAnimatedImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public float getTransformationAlpha()
  {
    Animation localAnimation = getAnimation();
    if (localAnimation == null) {
      return mStartAlpha;
    }
    Transformation localTransformation = new Transformation();
    localAnimation.getTransformation(AnimationUtils.currentAnimationTimeMillis(), localTransformation);
    return localTransformation.getAlpha();
  }
  
  public void hide()
  {
    hide(null);
  }
  
  public void hide(Runnable paramRunnable)
  {
    float f1 = getTransformationAlpha();
    float f2 = Math.abs(f1 - 0.0F);
    if (f2 < 0.01F) {
      return;
    }
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(f1, 0.0F);
    localAlphaAnimation.setDuration((f2 * 300.0F));
    localAlphaAnimation.setFillAfter(true);
    localAlphaAnimation.setAnimationListener(new AlphaAnimatedImageButton.2(this, paramRunnable));
    startAnimation(localAlphaAnimation);
  }
  
  public void setStartAlpha(float paramFloat, boolean paramBoolean)
  {
    mStartAlpha = paramFloat;
    setAlpha(mStartAlpha);
    setClickable(paramBoolean);
  }
  
  public void show()
  {
    show(null);
  }
  
  public void show(Runnable paramRunnable)
  {
    float f1 = getTransformationAlpha();
    float f2 = Math.abs(1.0F - f1);
    if (f2 < 0.01F) {
      return;
    }
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(f1, 1.0F);
    localAlphaAnimation.setDuration((f2 * 300.0F));
    localAlphaAnimation.setFillAfter(true);
    localAlphaAnimation.setAnimationListener(new AlphaAnimatedImageButton.1(this, paramRunnable));
    startAnimation(localAlphaAnimation);
  }
}
