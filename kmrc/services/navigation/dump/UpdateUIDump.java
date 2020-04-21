package com.com.kmrc.services.navigation.dump;

import com.google.gson.annotations.SerializedName;

public class UpdateUIDump
{
  @SerializedName("Dist2Target")
  public LabelDump dist2Target = new LabelDump();
  @SerializedName("GpsIcon")
  public GpsIconDump gpsIcon = new GpsIconDump();
  @SerializedName("ETA")
  public LabelDump messageId = new LabelDump();
  @SerializedName("NotificationIcon")
  public NotificationIconDump notificationIcon = new NotificationIconDump();
  @SerializedName("NotificationText")
  public LabelDump notificationText = new LabelDump();
  @SerializedName("TurnDist")
  public LabelDump turnDist = new LabelDump();
  @SerializedName("TurnDistUnit")
  public LabelDump turnDistUnit = new LabelDump();
  @SerializedName("TurnIcon")
  public TurnIconDump turnIcon = new TurnIconDump();
  @SerializedName("TurnInfo")
  public LabelDump turnInfo = new LabelDump();
  @SerializedName("TurnRoad")
  public LabelDump turnRoad = new LabelDump();
  
  public UpdateUIDump() {}
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof UpdateUIDump)) {
      return false;
    }
    paramObject = (UpdateUIDump)paramObject;
    return (turnIcon.equals(turnIcon)) && (turnDist.equals(turnDist)) && (turnDistUnit.equals(turnDistUnit)) && (turnInfo.equals(turnInfo)) && (turnRoad.equals(turnRoad)) && (messageId.equals(messageId)) && (dist2Target.equals(dist2Target)) && (notificationIcon.equals(notificationIcon)) && (notificationText.equals(notificationText)) && (gpsIcon.equals(gpsIcon));
  }
  
  public boolean hasValue()
  {
    return (turnIcon.hasValue()) || (turnDist.hasValue()) || (turnDistUnit.hasValue()) || (turnInfo.hasValue()) || (turnRoad.hasValue()) || (messageId.hasValue()) || (dist2Target.hasValue()) || (notificationIcon.hasValue()) || (notificationText.hasValue()) || (gpsIcon.hasValue());
  }
}
