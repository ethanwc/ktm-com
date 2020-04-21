package com.com.kmrc.services.navigation.dump;

import com.com.kmrc.services.navigation.UiContext;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class NavRepoDump
{
  @SerializedName("UiContext")
  public UiContext uiContext = null;
  @SerializedName("UpdateUI")
  public UpdateUIDump updateUiDump = new UpdateUIDump();
  
  public NavRepoDump() {}
  
  public static NavRepoDump fromJsonString(String paramString)
  {
    GsonBuilder localGsonBuilder = new GsonBuilder();
    localGsonBuilder.registerTypeAdapter(com.ktm.kmrc.services.navigation.dump.UpdateUIDump.class, new NavRepoDumpUpdateUiDeserializer());
    return (NavRepoDump)localGsonBuilder.create().fromJson(paramString, com.ktm.kmrc.services.navigation.dump.NavRepoDump.class);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof NavRepoDump)) {
      return false;
    }
    paramObject = (NavRepoDump)paramObject;
    return (uiContext == uiContext) && (updateUiDump.equals(updateUiDump));
  }
  
  public boolean hasValue()
  {
    return (uiContext != null) || (updateUiDump.hasValue());
  }
  
  public String toJson()
  {
    GsonBuilder localGsonBuilder = new GsonBuilder();
    localGsonBuilder.setPrettyPrinting();
    localGsonBuilder.registerTypeAdapter(com.ktm.kmrc.services.navigation.dump.UpdateUIDump.class, new NavRepoDumpUpdateUISerializer());
    return localGsonBuilder.create().toJson(this);
  }
  
  public JsonObject toJsonTree()
  {
    GsonBuilder localGsonBuilder = new GsonBuilder();
    localGsonBuilder.registerTypeAdapter(com.ktm.kmrc.services.navigation.dump.UpdateUIDump.class, new NavRepoDumpUpdateUISerializer());
    return localGsonBuilder.create().toJsonTree(this).getAsJsonObject();
  }
}
