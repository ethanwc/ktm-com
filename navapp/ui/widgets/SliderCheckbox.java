package com.com.navapp.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;

public class SliderCheckbox
  extends AppCompatCheckBox
{
  private boolean mClicked = true;
  private float mDeltaX;
  private float mLastX;
  private boolean mSlided = false;
  
  public SliderCheckbox(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SliderCheckbox(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public SliderCheckbox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if (i != 0)
    {
      if (i != 1)
      {
        if (i == 2)
        {
          float f = paramMotionEvent.getX();
          if (Math.round(mLastX) != 0) {
            mDeltaX += f - mLastX;
          }
          mLastX = f;
          if (Math.abs(mDeltaX) >= getMeasuredWidth() / 8) {
            mClicked = false;
          }
        }
        if (mDeltaX >= getMeasuredWidth() / 3)
        {
          mDeltaX = 0.0F;
          if ((!isChecked()) && (!mSlided))
          {
            mSlided = true;
            performClick();
            return true;
          }
        }
        else if (mDeltaX <= -getMeasuredWidth() / 3)
        {
          mDeltaX = 0.0F;
          if ((isChecked()) && (!mSlided))
          {
            mSlided = true;
            performClick();
            return true;
          }
        }
      }
      else
      {
        mSlided = false;
        if (mClicked) {
          super.onTouchEvent(paramMotionEvent);
        }
        mDeltaX = 0.0F;
        mLastX = 0.0F;
        return true;
      }
    }
    else
    {
      mClicked = true;
      mSlided = false;
      return super.onTouchEvent(paramMotionEvent);
    }
    return true;
  }
}
