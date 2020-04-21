package com.com.navapp.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.com.navapp.R.styleable;

public class ArcProgressBar
  extends View
{
  private RectF mBounds;
  private RectF mDrawingBounds;
  private Paint mPaint;
  private Path mPath;
  private int mProgressColor;
  private int mRingColor;
  private float mRingWidth;
  private long mTimeMs;
  
  public ArcProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    configAttributes(paramContext, paramAttributeSet);
    init();
  }
  
  private void configAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ArcProgressBar, 0, 0);
    try
    {
      mTimeMs = paramContext.getInt(3, 1000);
      mRingColor = paramContext.getColor(1, -1);
      mProgressColor = paramContext.getColor(0, -16777216);
      mRingWidth = paramContext.getDimension(2, 10.0F);
      paramContext.recycle();
      return;
    }
    catch (Throwable paramAttributeSet)
    {
      paramContext.recycle();
      throw paramAttributeSet;
    }
  }
  
  private void init()
  {
    mPaint = new Paint(1);
    mPath = new Path();
    mPath.setFillType(Path.FillType.EVEN_ODD);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    long l1 = System.currentTimeMillis();
    long l2 = mTimeMs;
    paramCanvas.rotate((float)(l1 % l2) * 360.0F / (float)l2, (mBounds.right - mBounds.left) / 2.0F, (mBounds.bottom - mBounds.top) / 2.0F);
    mPaint.setStrokeWidth(mRingWidth);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setColor(mRingColor);
    mPath.reset();
    mPath.arcTo(mDrawingBounds, 0.0F, 180.0F, true);
    mPath.arcTo(mDrawingBounds, 180.0F, 359.0F, true);
    mPath.close();
    paramCanvas.drawPath(mPath, mPaint);
    mPaint.setColor(mProgressColor);
    mPath.reset();
    mPath.arcTo(mDrawingBounds, 0.0F, 90.0F, true);
    mPath.arcTo(mDrawingBounds, 90.0F, 0.0F, true);
    mPath.close();
    paramCanvas.drawPath(mPath, mPaint);
    postDelayed(new ArcProgressBar.1(this), 50L);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.resolveSizeAndState(getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth(), paramInt1, 1), View.resolveSizeAndState(getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight(), paramInt2, 1));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f1 = getPaddingLeft() + getPaddingRight();
    float f2 = getPaddingTop() + getPaddingBottom();
    mBounds = new RectF(0.0F, 0.0F, paramInt1 - f1, paramInt2 - f2);
    mBounds.offsetTo(getPaddingLeft(), getPaddingTop());
    mDrawingBounds = new RectF(mBounds.left + mRingWidth, mBounds.top + mRingWidth, mBounds.right - mRingWidth, mBounds.bottom - mRingWidth);
  }
  
  public void setTime(long paramLong)
  {
    mTimeMs = paramLong;
  }
}
