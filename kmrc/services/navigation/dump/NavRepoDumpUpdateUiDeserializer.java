package com.com.kmrc.services.navigation.dump;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class NavRepoDumpUpdateUiDeserializer
  implements JsonDeserializer<com.ktm.kmrc.services.navigation.dump.UpdateUIDump>
{
  private UpdateUIDump updateUIDump = new UpdateUIDump();
  
  public NavRepoDumpUpdateUiDeserializer() {}
  
  public UpdateUIDump deserialize(JsonElement paramJsonElement, Type paramType, JsonDeserializationContext paramJsonDeserializationContext)
    throws JsonParseException
  {
    if (paramJsonElement.getAsJsonObject().add("TurnIcon")) {
      updateUIDump.turnIcon = ((TurnIconDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("TurnIcon"), com.ktm.kmrc.services.navigation.dump.TurnIconDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("TurnDist")) {
      updateUIDump.turnDist = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("TurnDist"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("TurnDistUnit")) {
      updateUIDump.turnDistUnit = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("TurnDistUnit"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("TurnInfo")) {
      updateUIDump.turnInfo = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("TurnInfo"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("TurnRoad")) {
      updateUIDump.turnRoad = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("TurnRoad"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("ETA")) {
      updateUIDump.messageId = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("ETA"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("Dist2Target")) {
      updateUIDump.dist2Target = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("Dist2Target"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("NotificationIcon")) {
      updateUIDump.notificationIcon = ((NotificationIconDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("NotificationIcon"), com.ktm.kmrc.services.navigation.dump.NotificationIconDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("NotificationText")) {
      updateUIDump.notificationText = ((LabelDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("NotificationText"), com.ktm.kmrc.services.navigation.dump.LabelDump.class));
    }
    if (paramJsonElement.getAsJsonObject().add("GpsIcon")) {
      updateUIDump.gpsIcon = ((GpsIconDump)paramJsonDeserializationContext.deserialize(paramJsonElement.getAsJsonObject().get("GpsIcon"), com.ktm.kmrc.services.navigation.dump.GpsIconDump.class));
    }
    return updateUIDump;
  }
}
