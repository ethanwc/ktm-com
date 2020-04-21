package com.com.kmrc.services.navigation;

import com.com.kmrc.services.navigation.dump.GpsIconDump;
import com.com.kmrc.services.navigation.dump.LabelDump;
import com.com.kmrc.services.navigation.dump.NavRepoDump;
import com.com.kmrc.services.navigation.dump.NotificationIconDump;
import com.com.kmrc.services.navigation.dump.TurnIconDump;
import com.com.kmrc.services.navigation.dump.UpdateUIDump;
import com.com.kmrc.upgrade.Connection;
import com.com.kmrc.upgrade.KSocket;

public class NavServerConnection
  extends Connection
{
  private final NavServiceListener serviceListener;
  
  public NavServerConnection(NavServerListener paramNavServerListener)
  {
    super(new NavServerConnectionListener(paramNavServerListener));
    serviceListener = paramNavServerListener;
  }
  
  void handleNavCommand(String paramString)
  {
    Object localObject2 = serviceListener;
    Object localObject1 = this;
    Object localObject3 = ksocket();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("received ");
    localStringBuilder.append(paramString);
    ((NavServiceListener)localObject2).onServiceMsg((KSocket)localObject3, localStringBuilder.toString());
    localObject3 = NavRepoDump.fromJsonString(paramString);
    if (uiContext != null)
    {
      int i = 1.$SwitchMap$com$ktm$kmrc$services$navigation$UiContext[uiContext.ordinal()];
      if (i != 1) {
        if (i != 2)
        {
          if (i == 3) {
            ((NavServerListener)serviceListener).onGuidanceUiContext(((Connection)localObject1).ksocket());
          }
        }
        else {
          ((NavServerListener)serviceListener).onDefaultUiContext(((Connection)localObject1).ksocket());
        }
      }
    }
    localObject1 = this;
    if (updateUiDump != null)
    {
      paramString = (String)localObject1;
      if (updateUiDump.turnIcon != null)
      {
        localObject2 = localObject1;
        if (updateUiDump.turnIcon.Image != null)
        {
          paramString = serviceListener;
          ((NavServerListener)paramString).onChangeWidgetContent(((Connection)localObject1).ksocket(), "TurnIcon", updateUiDump.turnIcon.Image.toString());
          localObject2 = localObject1;
        }
        paramString = (String)localObject2;
        if (updateUiDump.turnIcon.Visibility != null)
        {
          localObject1 = serviceListener;
          paramString = (String)localObject2;
          ((NavServerListener)localObject1).onChangeWidgetVisibility(paramString.ksocket(), "TurnIcon", updateUiDump.turnIcon.Visibility);
        }
      }
      localObject1 = paramString;
      if (updateUiDump.turnDist != null)
      {
        localObject2 = paramString;
        if (updateUiDump.turnDist.Text != null)
        {
          localObject1 = serviceListener;
          ((NavServerListener)localObject1).onChangeWidgetContent(paramString.ksocket(), "TurnDist", updateUiDump.turnDist.Text);
          localObject2 = paramString;
        }
        localObject1 = localObject2;
        if (updateUiDump.turnDist.Visibility != null)
        {
          paramString = serviceListener;
          localObject1 = localObject2;
          ((NavServerListener)paramString).onChangeWidgetVisibility(((Connection)localObject1).ksocket(), "TurnDist", updateUiDump.turnDist.Visibility);
        }
      }
      paramString = (String)localObject1;
      if (updateUiDump.turnDistUnit != null)
      {
        localObject2 = localObject1;
        if (updateUiDump.turnDistUnit.Text != null)
        {
          paramString = serviceListener;
          ((NavServerListener)paramString).onChangeWidgetContent(((Connection)localObject1).ksocket(), "TurnDistUnit", updateUiDump.turnDistUnit.Text);
          localObject2 = localObject1;
        }
        paramString = (String)localObject2;
        if (updateUiDump.turnDistUnit.Visibility != null)
        {
          localObject1 = serviceListener;
          paramString = (String)localObject2;
          ((NavServerListener)localObject1).onChangeWidgetVisibility(paramString.ksocket(), "TurnDistUnit", updateUiDump.turnDistUnit.Visibility);
        }
      }
      localObject1 = paramString;
      if (updateUiDump.turnInfo != null)
      {
        localObject2 = paramString;
        if (updateUiDump.turnInfo.Text != null)
        {
          localObject1 = serviceListener;
          ((NavServerListener)localObject1).onChangeWidgetContent(paramString.ksocket(), "TurnInfo", updateUiDump.turnInfo.Text);
          localObject2 = paramString;
        }
        localObject1 = localObject2;
        if (updateUiDump.turnInfo.Visibility != null)
        {
          paramString = serviceListener;
          localObject1 = localObject2;
          ((NavServerListener)paramString).onChangeWidgetVisibility(((Connection)localObject1).ksocket(), "TurnInfo", updateUiDump.turnInfo.Visibility);
        }
      }
      paramString = (String)localObject1;
      if (updateUiDump.turnRoad != null)
      {
        localObject2 = localObject1;
        if (updateUiDump.turnRoad.Text != null)
        {
          paramString = serviceListener;
          ((NavServerListener)paramString).onChangeWidgetContent(((Connection)localObject1).ksocket(), "TurnRoad", updateUiDump.turnRoad.Text);
          localObject2 = localObject1;
        }
        paramString = (String)localObject2;
        if (updateUiDump.turnRoad.Visibility != null)
        {
          localObject1 = serviceListener;
          paramString = (String)localObject2;
          ((NavServerListener)localObject1).onChangeWidgetVisibility(paramString.ksocket(), "TurnRoad", updateUiDump.turnRoad.Visibility);
        }
      }
      localObject1 = paramString;
      if (updateUiDump.messageId != null)
      {
        localObject2 = paramString;
        if (updateUiDump.messageId.Text != null)
        {
          localObject1 = serviceListener;
          ((NavServerListener)localObject1).onChangeWidgetContent(paramString.ksocket(), "ETA", updateUiDump.messageId.Text);
          localObject2 = paramString;
        }
        localObject1 = localObject2;
        if (updateUiDump.messageId.Visibility != null)
        {
          paramString = serviceListener;
          localObject1 = localObject2;
          ((NavServerListener)paramString).onChangeWidgetVisibility(((Connection)localObject1).ksocket(), "ETA", updateUiDump.messageId.Visibility);
        }
      }
      paramString = (String)localObject1;
      if (updateUiDump.dist2Target != null)
      {
        localObject2 = localObject1;
        if (updateUiDump.dist2Target.Text != null)
        {
          paramString = serviceListener;
          ((NavServerListener)paramString).onChangeWidgetContent(((Connection)localObject1).ksocket(), "Dist2Target", updateUiDump.dist2Target.Text);
          localObject2 = localObject1;
        }
        paramString = (String)localObject2;
        if (updateUiDump.dist2Target.Visibility != null)
        {
          localObject1 = serviceListener;
          paramString = (String)localObject2;
          ((NavServerListener)localObject1).onChangeWidgetVisibility(paramString.ksocket(), "Dist2Target", updateUiDump.dist2Target.Visibility);
        }
      }
      localObject1 = paramString;
      if (updateUiDump.notificationIcon != null)
      {
        localObject2 = paramString;
        if (updateUiDump.notificationIcon.Image != null)
        {
          localObject1 = serviceListener;
          ((NavServerListener)localObject1).onChangeWidgetContent(paramString.ksocket(), "NotificationIcon", updateUiDump.notificationIcon.Image.toString());
          localObject2 = paramString;
        }
        localObject1 = localObject2;
        if (updateUiDump.notificationIcon.Visibility != null)
        {
          paramString = serviceListener;
          localObject1 = localObject2;
          ((NavServerListener)paramString).onChangeWidgetVisibility(((Connection)localObject1).ksocket(), "NotificationIcon", updateUiDump.notificationIcon.Visibility);
        }
      }
      paramString = (String)localObject1;
      if (updateUiDump.notificationText != null)
      {
        localObject2 = localObject1;
        if (updateUiDump.notificationText.Text != null)
        {
          paramString = serviceListener;
          ((NavServerListener)paramString).onChangeWidgetContent(((Connection)localObject1).ksocket(), "NotificationText", updateUiDump.notificationText.Text);
          localObject2 = localObject1;
        }
        paramString = (String)localObject2;
        if (updateUiDump.notificationText.Visibility != null)
        {
          localObject1 = serviceListener;
          paramString = (String)localObject2;
          ((NavServerListener)localObject1).onChangeWidgetVisibility(paramString.ksocket(), "NotificationText", updateUiDump.notificationText.Visibility);
        }
      }
      if (updateUiDump.gpsIcon != null)
      {
        localObject1 = paramString;
        if (updateUiDump.gpsIcon.Image != null)
        {
          localObject1 = serviceListener;
          ((NavServerListener)localObject1).onChangeWidgetContent(paramString.ksocket(), "GpsIcon", updateUiDump.gpsIcon.Image.toString());
          localObject1 = paramString;
        }
        if (updateUiDump.gpsIcon.Visibility != null) {
          ((NavServerListener)serviceListener).onChangeWidgetVisibility(((Connection)localObject1).ksocket(), "GpsIcon", updateUiDump.gpsIcon.Visibility);
        }
      }
    }
  }
}
