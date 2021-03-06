package com.com.navapp.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.com.navapp.R.styleable;

public class PageProgressBar
  extends View
{
  private static final int DEFAULT_SELECTED = 1;
  private static final int MINIMUM_PAGES = 2;
  private int mCircleColor;
  private RectF mDrawingBounds;
  private float mLineWidth;
  private int mPageCount;
  private int mPageSelected;
  private Paint mPaint;
  
  public PageProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    configAttributes(paramContext, paramAttributeSet);
    init();
  }
  
  private void configAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PageProgressBar, 0, 0);
    try
    {
      mPageCount = paramContext.getInt(1, 2);
      mPageSelected = paramContext.getInt(3, 1);
      mCircleColor = paramContext.getColor(0, -39424);
      mLineWidth = paramContext.getDimension(2, 1.0F);
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
    mPaint.setStrokeWidth(mLineWidth);
    mPaint.setColor(mCircleColor);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    float f2 = mDrawingBounds.right;
    float f3 = mDrawingBounds.left;
    float f1 = mDrawingBounds.bottom - mDrawingBounds.top;
    int i = mPageCount;
    f2 = (f2 - f3 - i * f1) / (i - 1);
    i = 0;
    while (i < mPageCount)
    {
      if (i == mPageSelected - 1) {
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
      } else {
        mPaint.setStyle(Paint.Style.STROKE);
      }
      f3 = mDrawingBounds.left;
      float f4 = i;
      float f5 = f1 / 2.0F;
      paramCanvas.drawCircle(f3 + f4 * f2 + f4 * f1 + f5, mDrawingBounds.top + f5, f5, mPaint);
      i += 1;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(View.resolveSizeAndState(getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth(), paramInt1, 1), View.resolveSizeAndState(getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight(), paramInt2, 1));
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f1 = getPaddingLeft() + getPaddingRight();
    float f2 = getPaddingTop() + getPaddingBottom();
    RectF localRectF = new RectF(0.0F, 0.0F, paramInt1 - f1, paramInt2 - f2);
    localRectF.offsetTo(getPaddingLeft(), getPaddingTop());
    f1 = mLineWidth;
    mDrawingBounds = new RectF(left + f1, top + f1, right - f1, bottom - f1);
    paramInt1 = (int)((mDrawingBounds.right - mDrawingBounds.left) / (mDrawingBounds.bottom - mDrawingBounds.top));
    mPageCount = Math.max(Math.min(mPageCount, paramInt1), 2);
    mPageSelected = Math.max(Math.min(mPageSelected, mPageCount), 1);
  }
}
