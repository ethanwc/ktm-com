package com.com.navapp.offroad;

public class Tracker1D
{
  private double data;
  private final double end;
  private final double interval;
  private double length;
  private boolean mPredicted = false;
  private final double max;
  private final double min;
  private final double mt2d2;
  private final double mt3d2;
  private final double mt4d4;
  private double next;
  private double position;
  private final double running;
  private double start;
  private final double this$0;
  private double value;
  
  public Tracker1D(double paramDouble1, double paramDouble2)
  {
    max = paramDouble1;
    double d = max;
    min = (d * d);
    paramDouble1 = min;
    mt2d2 = (paramDouble1 / 2.0D);
    mt3d2 = (d * paramDouble1 / 2.0D);
    mt4d4 = (paramDouble1 * paramDouble1 / 4.0D);
    paramDouble2 *= paramDouble2;
    this$0 = (mt4d4 * paramDouble2);
    running = (mt3d2 * paramDouble2);
    d = running;
    interval = d;
    end = (paramDouble2 * paramDouble1);
    value = this$0;
    data = d;
    next = interval;
    start = end;
  }
  
  public double getAccuracy()
  {
    return Math.sqrt(start / min);
  }
  
  public double getPosition()
  {
    return position;
  }
  
  public double getVelocity()
  {
    return length;
  }
  
  public double predict(double paramDouble)
  {
    mPredicted = true;
    double d2 = position;
    double d3 = length;
    double d1 = max;
    position = (d2 + d3 * d1 + mt2d2 * paramDouble);
    length = (d3 + paramDouble * d1);
    paramDouble = start;
    d2 = paramDouble * d1;
    d3 = data + d2;
    double d4 = value;
    double d5 = next;
    value = (d4 + d1 * (d5 + d3) + this$0);
    data = (d3 + running);
    next = (d5 + d2 + interval);
    start = (paramDouble + end);
    return position;
  }
  
  public void setState(double paramDouble1, double paramDouble2, double paramDouble3)
  {
    position = paramDouble1;
    length = paramDouble2;
    paramDouble1 = paramDouble3 * paramDouble3;
    value = (mt4d4 * paramDouble1);
    data = (mt3d2 * paramDouble1);
    next = data;
    start = (paramDouble1 * min);
  }
  
  public void update(double paramDouble1, double paramDouble2)
  {
    if (!mPredicted) {
      predict(0.0D);
    }
    mPredicted = false;
    double d1 = position;
    double d3 = paramDouble1 - d1;
    paramDouble1 = value;
    double d4 = 1.0D / (paramDouble2 * paramDouble2 + paramDouble1);
    paramDouble2 = paramDouble1 * d4;
    double d2 = next;
    d4 *= d2;
    position = (d1 + paramDouble2 * d3);
    length += d3 * d4;
    d1 = data;
    d3 = start;
    value = (paramDouble1 - paramDouble2 * paramDouble1);
    data = (d1 - paramDouble2 * d1);
    next = (d2 - paramDouble1 * d4);
    start = (d3 - d4 * d1);
  }
}
