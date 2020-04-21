package com.com.kmrc.services.navigation;

public abstract interface NavigationRequest
{
  public abstract void onGpsSignalAvailable();
  
  public abstract void onGpsSignalAvailable(String paramString);
  
  public abstract void onGpsSignalLost();
  
  public abstract void onGpsSignalLost(String paramString);
  
  public abstract void onGuidanceFinished();
  
  public abstract void onGuidanceFinished(String paramString);
  
  public abstract void onGuidanceStarted();
  
  public abstract void onGuidanceStarted(String paramString);
  
  public abstract void onNewGuidanceManeuver(TurnIcon paramTurnIcon, String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract void onNewGuidanceManeuver(TurnIcon paramTurnIcon, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
  
  public abstract void onNewLocation(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public abstract void onNewLocation(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
  
  public abstract void onRerouting(String paramString);
  
  public abstract void onRerouting(String paramString1, String paramString2);
  
  public abstract void wait4RequestsSubmitted();
}
