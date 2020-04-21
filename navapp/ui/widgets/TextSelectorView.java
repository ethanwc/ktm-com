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
import android.widget.FrameLayout;
import com.com.navapp.R.styleable;

public class TextSelectorView
  extends FrameLayout
{
  private RectF mBounds;
  private RectF mDrawingBounds;
  private float mLineWidth;
  private Paint mPaint;
  private Path mPath;
  private int mRingColor;
  
  public TextSelectorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    configAttributes(paramContext, paramAttributeSet);
    init();
  }
  
  private void configAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.TextSelectorView, 0, 0);
    try
    {
      mLineWidth = paramContext.getDimension(0, 2.0F);
      mRingColor = paramContext.getColor(2, -16777216);
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
    mPaint.setColor(mRingColor);
    mPaint.setStrokeWidth(mLineWidth);
    mPaint.setStyle(Paint.Style.STROKE);
    mPath = new Path();
    mPath.setFillType(Path.FillType.EVEN_ODD);
    setWillNotDraw(false);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mDrawingBounds == null) {
      return;
    }
    mPaint.setStrokeWidth(mLineWidth);
    mPath.reset();
    mPath.addArc(mDrawingBounds, 0.0F, 360.0F);
    mPath.close();
    paramCanvas.drawPath(mPath, mPaint);
    mPath.reset();
    float f1 = (mBounds.right - mBounds.left) / 2.0F;
    float f2 = (mDrawingBounds.bottom - mDrawingBounds.top) / 2.0F;
    float f3 = mDrawingBounds.top + f2;
    mPath.moveTo(mDrawingBounds.left, f3);
    mPath.quadTo(mBounds.left, f3 - 1.2F * f2, mBounds.left + f1 * 0.99F, f3 - 0.99F * f2);
    mPath.moveTo(mDrawingBounds.left, f3);
    mPath.close();
    paramCanvas.drawPath(mPath, mPaint);
    mPaint.setStrokeWidth(mLineWidth / 2.0F);
    paramCanvas.drawLine(mDrawingBounds.left, f3, mDrawingBounds.left + f1 * 0.2F, f3 + f2 * 0.9F, mPaint);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    float f1 = getPaddingLeft() + getPaddingRight();
    float f2 = getPaddingTop() + getPaddingBottom();
    mBounds = new RectF(0.0F, 0.0F, paramInt1 - f1, paramInt2 - f2);
    mBounds.offsetTo(getPaddingLeft(), getPaddingTop());
    f1 = mLineWidth * 2.0F;
    mDrawingBounds = new RectF(mBounds.left + f1, mBounds.top + f1, mBounds.right - f1, mBounds.bottom - f1);
  }
}
