package com.com.kmrc.services.navigation;

import com.com.kmrc.services.navigation.dump.NavRepoModified;
import com.com.kmrc.services.navigation.dump.NavRepoResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class NavRepo
{
  public static final String DIST2TARGETWIDGET = "Dist2Target";
  public static final String ETAWIDGET = "ETA";
  public static final String GPSICONWIDGET = "GpsIcon";
  public static final String IMAGE = "Image";
  public static final String NOTIFICATIONICONWIDGET = "NotificationIcon";
  public static final String NOTIFICATIONTEXTWIDGET = "NotificationText";
  public static final String TEXT = "Text";
  public static final String TURNDISTUNITWIDGET = "TurnDistUnit";
  public static final String TURNDISTWIDGET = "TurnDist";
  public static final String TURNICONWIDGET = "TurnIcon";
  public static final String TURNINFOWIDGET = "TurnInfo";
  public static final String TURNROADWIDGET = "TurnRoad";
  public static final String UICONTEXT = "UiContext";
  public static final String UPDATEUI = "UpdateUI";
  public static final String VISIBILITY = "Visibility";
  public static final List<String> allGiudanceWidgetNames = new ArrayList() {};
  public static final List<String> allNavWidgetNames = new ArrayList() {};
  static final List<String> gpsWidgetNames;
  static final List<String> notificationWidgetNames;
  static final List<String> turnByTurnWidgetNames = Arrays.asList(new String[] { "TurnIcon", "TurnDist", "TurnDistUnit", "TurnInfo", "TurnRoad", "ETA", "Dist2Target" });
  @Expose(deserialize=false, serialize=false)
  private final List<com.ktm.kmrc.services.navigation.Widget<com.ktm.kmrc.services.navigation.TurnIcon>> turnByTurnIconlWidgets = new ArrayList();
  private final List<com.ktm.kmrc.services.navigation.Widget<String>> turnByTurnLabelWidgets = new ArrayList();
  @Expose
  @SerializedName("UiContext")
  public com.ktm.kmrc.services.navigation.StagedValue<com.ktm.kmrc.services.navigation.UiContext> uiContext = new StagedValue();
  @Expose
  @SerializedName("UpdateUI")
  public UpdateUi updateUi = new UpdateUi();
  
  static
  {
    notificationWidgetNames = Arrays.asList(new String[] { "NotificationIcon", "NotificationText" });
    gpsWidgetNames = Arrays.asList(new String[] { "GpsIcon" });
  }
  
  public NavRepo()
  {
    turnByTurnIconlWidgets.add(updateUi.turnIcon);
    turnByTurnLabelWidgets.add(updateUi.turnDist);
    turnByTurnLabelWidgets.add(updateUi.turnDistUnit);
    turnByTurnLabelWidgets.add(updateUi.turnInfo);
    turnByTurnLabelWidgets.add(updateUi.turnRoad);
    turnByTurnLabelWidgets.add(updateUi._transaction);
    turnByTurnLabelWidgets.add(updateUi.dist2Target);
  }
  
  private void disableGuidanceAndNotificationWidgets()
  {
    try
    {
      Iterator localIterator = turnByTurnLabelWidgets.iterator();
      Widget localWidget;
      while (localIterator.hasNext())
      {
        localWidget = (Widget)localIterator.next();
        localWidget.visibility().diff(Visibility.PRIVATE);
        localWidget.content().diff("");
      }
      localIterator = turnByTurnIconlWidgets.iterator();
      while (localIterator.hasNext())
      {
        localWidget = (Widget)localIterator.next();
        localWidget.visibility().diff(Visibility.PRIVATE);
        localWidget.content().diff(TurnIcon.UNDEFINED);
      }
      updateUi.notificationIcon.visibility().diff(Visibility.PRIVATE);
      updateUi.notificationText.visibility().diff(Visibility.PRIVATE);
      updateUi.notificationText.content().diff("");
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private NavRepoModified getNavRepoModifiedCommitted()
  {
    try
    {
      NavRepoModified localNavRepoModified = getNavRepoModified();
      commit();
      return localNavRepoModified;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  private boolean shallBeGrey()
  {
    if ((updateUi.gpsIcon.visibility().result() == null) && (updateUi.notificationIcon.content().result() == null)) {
      return false;
    }
    if ((updateUi.gpsIcon.visibility().result() != null) && (updateUi.notificationIcon.content().result() == null)) {
      return ((Visibility)updateUi.gpsIcon.visibility().result()).equals(Visibility.HALF);
    }
    if ((updateUi.gpsIcon.visibility().result() != null) && (updateUi.notificationIcon.content().result() != null))
    {
      if ((((Visibility)updateUi.gpsIcon.visibility().result()).equals(Visibility.HALF)) || ((updateUi.notificationIcon.content().result() == NotificationIcon.NOTIFICATION_REROUTING) && (updateUi.notificationIcon.visibility().result() != Visibility.PRIVATE))) {
        return true;
      }
    }
    else if ((updateUi.gpsIcon.visibility().result() == null) && (updateUi.notificationIcon.content().result() != null) && (updateUi.notificationIcon.content().result() == NotificationIcon.NOTIFICATION_REROUTING) && (updateUi.notificationIcon.visibility().result() != Visibility.PRIVATE)) {
      return true;
    }
    return false;
  }
  
  private void updateLabelWidget(Widget paramWidget, String paramString)
  {
    paramWidget.content().diff(paramString);
    paramString = paramWidget.visibility();
    if (shallBeGrey()) {
      paramWidget = Visibility.HALF;
    } else {
      paramWidget = Visibility.FULL;
    }
    paramString.diff(paramWidget);
  }
  
  public void commit()
  {
    try
    {
      uiContext.commit();
      updateUi.turnIcon.commit();
      updateUi.turnDist.commit();
      updateUi.turnDistUnit.commit();
      updateUi.turnInfo.commit();
      updateUi.turnRoad.commit();
      updateUi.dist2Target.commit();
      updateUi._transaction.commit();
      updateUi.gpsIcon.commit();
      updateUi.notificationIcon.commit();
      updateUi.notificationText.commit();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  void disableNotification(NotificationIcon paramNotificationIcon)
  {
    if (paramNotificationIcon != null) {
      try
      {
        if ((updateUi.notificationIcon.content().result() != null) && (((NotificationIcon)updateUi.notificationIcon.content().result()).equals(paramNotificationIcon)))
        {
          updateUi.notificationIcon.visibility().diff(Visibility.PRIVATE);
          updateUi.notificationText.visibility().diff(Visibility.PRIVATE);
        }
        return;
      }
      catch (Throwable paramNotificationIcon) {}
    } else {
      throw new IllegalArgumentException("disableNotification() passed null argument");
    }
    throw paramNotificationIcon;
  }
  
  void enableNotification(NotificationIcon paramNotificationIcon, String paramString)
  {
    if ((paramNotificationIcon != null) && (paramString != null)) {}
    try
    {
      updateUi.notificationIcon.content().diff(paramNotificationIcon);
      updateUi.notificationIcon.visibility().diff(Visibility.FULL);
      updateUi.notificationText.content().diff(paramString);
      updateUi.notificationText.visibility().diff(Visibility.FULL);
      return;
    }
    catch (Throwable paramNotificationIcon)
    {
      throw paramNotificationIcon;
    }
    throw new IllegalArgumentException("enableNotification() passed null argument");
  }
  
  public NavRepoModified getNavRepoModified()
  {
    try
    {
      NavRepoModified localNavRepoModified = new NavRepoModified(this);
      return localNavRepoModified;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  boolean isReroutingAvtive()
  {
    try
    {
      if ((updateUi.notificationIcon.content().result() != null) && (((NotificationIcon)updateUi.notificationIcon.content().result()).equals(NotificationIcon.NOTIFICATION_REROUTING)))
      {
        bool = ((Visibility)updateUi.notificationIcon.visibility().result()).equals(Visibility.FULL);
        if (bool)
        {
          bool = true;
          break label77;
        }
      }
      boolean bool = false;
      label77:
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  boolean isUiContextGuidance()
  {
    try
    {
      if (uiContext.result() != null)
      {
        bool = ((UiContext)uiContext.result()).equals(UiContext.GUIDANCE);
        if (bool)
        {
          bool = true;
          break label40;
        }
      }
      boolean bool = false;
      label40:
      return bool;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  void modifyDist2TargetWidget(String paramString)
  {
    try
    {
      updateLabelWidget(updateUi.dist2Target, paramString);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  void modifyEtaWidget(String paramString)
  {
    try
    {
      updateLabelWidget(updateUi._transaction, paramString);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  void modifyGpsIconWidget(GpsIcon paramGpsIcon, Visibility paramVisibility)
  {
    try
    {
      updateUi.gpsIcon.content().diff(paramGpsIcon);
      updateUi.gpsIcon.visibility().diff(paramVisibility);
      return;
    }
    catch (Throwable paramGpsIcon)
    {
      throw paramGpsIcon;
    }
  }
  
  void modifyTurnDistUnitWidget(String paramString)
  {
    try
    {
      updateLabelWidget(updateUi.turnDistUnit, paramString);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  void modifyTurnDistWidget(String paramString)
  {
    try
    {
      updateLabelWidget(updateUi.turnDist, paramString);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  void modifyTurnIconWidget(TurnIcon paramTurnIcon)
  {
    try
    {
      updateUi.turnIcon.content().diff(paramTurnIcon);
      StagedValue localStagedValue = updateUi.turnIcon.visibility();
      if (shallBeGrey()) {
        paramTurnIcon = Visibility.HALF;
      } else {
        paramTurnIcon = Visibility.FULL;
      }
      localStagedValue.diff(paramTurnIcon);
      return;
    }
    catch (Throwable paramTurnIcon)
    {
      throw paramTurnIcon;
    }
  }
  
  void modifyTurnInfoWidget(String paramString)
  {
    try
    {
      updateLabelWidget(updateUi.turnInfo, paramString);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  void modifyTurnRoadWidget(String paramString)
  {
    try
    {
      updateLabelWidget(updateUi.turnRoad, paramString);
      return;
    }
    catch (Throwable paramString)
    {
      throw paramString;
    }
  }
  
  void modifyUiContext(UiContext paramUiContext)
  {
    try
    {
      uiContext.diff(paramUiContext);
      return;
    }
    catch (Throwable paramUiContext)
    {
      throw paramUiContext;
    }
  }
  
  void resetNavigationWidgets()
  {
    try
    {
      updateUi.turnIcon.reset();
      updateUi.turnDist.reset();
      updateUi.turnDistUnit.reset();
      updateUi.turnInfo.reset();
      updateUi.turnRoad.reset();
      updateUi._transaction.reset();
      updateUi.dist2Target.reset();
      updateUi.notificationIcon.reset();
      updateUi.notificationText.reset();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  void restore(NavClientConnection paramNavClientConnection)
  {
    try
    {
      NavRepoResult localNavRepoResult = new NavRepoResult(this);
      commit();
      if (paramNavClientConnection != null) {
        paramNavClientConnection.pushIfNeeded(localNavRepoResult, "Restore");
      }
      return;
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public void setGpsSignalAvailable(NavClientConnection paramNavClientConnection, String paramString)
  {
    try
    {
      modifyGpsIconWidget(GpsIcon.ORIGINAL, Visibility.FULL);
      setGuidanceWidgetsVisibility();
      if (paramNavClientConnection != null) {
        paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString);
      }
      return;
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public void setGpsSignalLost(NavClientConnection paramNavClientConnection, String paramString)
  {
    try
    {
      modifyGpsIconWidget(GpsIcon.ORIGINAL, Visibility.HALF);
      setGuidanceWidgetsVisibility();
      if (paramNavClientConnection != null) {
        paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString);
      }
      return;
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public void setGuidanceFinished(NavClientConnection paramNavClientConnection, String paramString)
  {
    try
    {
      if (isUiContextGuidance())
      {
        modifyUiContext(UiContext.DEFAULT);
        disableGuidanceAndNotificationWidgets();
        if (paramNavClientConnection != null) {
          paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString);
        }
        resetNavigationWidgets();
        return;
      }
      throw new RuntimeException("can't finish guidance: guidance UI-mode is not active");
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public void setGuidanceStarted(NavClientConnection paramNavClientConnection, String paramString)
  {
    try
    {
      if (!isUiContextGuidance())
      {
        modifyUiContext(UiContext.GUIDANCE);
        disableGuidanceAndNotificationWidgets();
        if (paramNavClientConnection != null) {
          paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString);
        }
        return;
      }
      throw new RuntimeException("can't start guidance: guidance UI-mode is already active");
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  void setGuidanceWidgetsVisibility()
  {
    try
    {
      if (isUiContextGuidance())
      {
        Visibility localVisibility;
        if (shallBeGrey()) {
          localVisibility = Visibility.HALF;
        } else {
          localVisibility = Visibility.FULL;
        }
        Iterator localIterator = turnByTurnLabelWidgets.iterator();
        while (localIterator.hasNext()) {
          ((Widget)localIterator.next()).visibility().diff(localVisibility);
        }
        localIterator = turnByTurnIconlWidgets.iterator();
        while (localIterator.hasNext()) {
          ((Widget)localIterator.next()).visibility().diff(localVisibility);
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      throw localThrowable;
    }
  }
  
  public void setNewGuidanceManeuver(NavClientConnection paramNavClientConnection, TurnIcon paramTurnIcon, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    try
    {
      if (isUiContextGuidance())
      {
        if (isReroutingAvtive())
        {
          disableNotification(NotificationIcon.NOTIFICATION_REROUTING);
          setGuidanceWidgetsVisibility();
        }
        modifyTurnIconWidget(paramTurnIcon);
        modifyTurnRoadWidget(paramString1);
        modifyTurnInfoWidget(paramString2);
        modifyTurnDistWidget(paramString3);
        modifyTurnDistUnitWidget(paramString4);
        if (paramNavClientConnection != null) {
          paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString5);
        }
        return;
      }
      throw new RuntimeException("can't update maneuver: guidance UI-mode is not active");
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public void setNewLocation(NavClientConnection paramNavClientConnection, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    try
    {
      if (isUiContextGuidance())
      {
        modifyTurnDistWidget(paramString1);
        modifyTurnDistUnitWidget(paramString2);
        modifyDist2TargetWidget(paramString3);
        modifyEtaWidget(paramString4);
        if (paramNavClientConnection != null) {
          paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString5);
        }
        return;
      }
      throw new RuntimeException("can't update new location: guidance UI-mode is not active");
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public void setRerouting(NavClientConnection paramNavClientConnection, String paramString1, String paramString2)
  {
    try
    {
      if (isUiContextGuidance())
      {
        enableNotification(NotificationIcon.NOTIFICATION_REROUTING, paramString1);
        setGuidanceWidgetsVisibility();
        if (paramNavClientConnection != null) {
          paramNavClientConnection.pushIfNeeded(getNavRepoModifiedCommitted(), paramString2);
        }
        return;
      }
      throw new RuntimeException("can't set rerouting: guidance UI-mode is not active");
    }
    catch (Throwable paramNavClientConnection)
    {
      throw paramNavClientConnection;
    }
  }
  
  public String toJson()
  {
    GsonBuilder localGsonBuilder = new GsonBuilder();
    localGsonBuilder.serializeNulls();
    localGsonBuilder.excludeFieldsWithoutExposeAnnotation();
    localGsonBuilder.setPrettyPrinting();
    return localGsonBuilder.create().toJson(this);
  }
  
  public class UpdateUi
  {
    @Expose
    @SerializedName("ETA")
    public com.ktm.kmrc.services.navigation.Widget<String> _transaction = new Widget();
    @Expose
    @SerializedName("Dist2Target")
    public com.ktm.kmrc.services.navigation.Widget<String> dist2Target = new Widget();
    @Expose
    @SerializedName("GpsIcon")
    public com.ktm.kmrc.services.navigation.Widget<com.ktm.kmrc.services.navigation.GpsIcon> gpsIcon = new Widget();
    @Expose
    @SerializedName("NotificationIcon")
    public com.ktm.kmrc.services.navigation.Widget<com.ktm.kmrc.services.navigation.NotificationIcon> notificationIcon = new Widget();
    @Expose
    @SerializedName("NotificationText")
    public com.ktm.kmrc.services.navigation.Widget<String> notificationText = new Widget();
    @Expose
    @SerializedName("TurnDist")
    public com.ktm.kmrc.services.navigation.Widget<String> turnDist = new Widget();
    @Expose
    @SerializedName("TurnDistUnit")
    public com.ktm.kmrc.services.navigation.Widget<String> turnDistUnit = new Widget();
    @Expose
    @SerializedName("TurnIcon")
    public com.ktm.kmrc.services.navigation.Widget<com.ktm.kmrc.services.navigation.TurnIcon> turnIcon = new Widget();
    @Expose
    @SerializedName("TurnInfo")
    public com.ktm.kmrc.services.navigation.Widget<String> turnInfo = new Widget();
    @Expose
    @SerializedName("TurnRoad")
    public com.ktm.kmrc.services.navigation.Widget<String> turnRoad = new Widget();
    
    public UpdateUi() {}
  }
}
