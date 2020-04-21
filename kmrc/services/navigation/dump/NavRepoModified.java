package com.com.kmrc.services.navigation.dump;

import com.com.kmrc.services.navigation.GpsIcon;
import com.com.kmrc.services.navigation.NavRepo;
import com.com.kmrc.services.navigation.NavRepo.UpdateUi;
import com.com.kmrc.services.navigation.NotificationIcon;
import com.com.kmrc.services.navigation.StagedValue;
import com.com.kmrc.services.navigation.TurnIcon;
import com.com.kmrc.services.navigation.UiContext;
import com.com.kmrc.services.navigation.Visibility;
import com.com.kmrc.services.navigation.Widget;

public class NavRepoModified
  extends NavRepoDump
{
  public NavRepoModified(NavRepo paramNavRepo)
  {
    uiContext = ((UiContext)uiContext.modified());
    updateUiDump.turnIcon.Image = ((TurnIcon)updateUi.turnIcon.content().modified());
    updateUiDump.turnIcon.Visibility = ((Visibility)updateUi.turnIcon.visibility().modified());
    updateUiDump.turnDist.Text = ((String)updateUi.turnDist.content().modified());
    updateUiDump.turnDist.Visibility = ((Visibility)updateUi.turnDist.visibility().modified());
    updateUiDump.turnDistUnit.Text = ((String)updateUi.turnDistUnit.content().modified());
    updateUiDump.turnDistUnit.Visibility = ((Visibility)updateUi.turnDistUnit.visibility().modified());
    updateUiDump.turnInfo.Text = ((String)updateUi.turnInfo.content().modified());
    updateUiDump.turnInfo.Visibility = ((Visibility)updateUi.turnInfo.visibility().modified());
    updateUiDump.turnRoad.Text = ((String)updateUi.turnRoad.content().modified());
    updateUiDump.turnRoad.Visibility = ((Visibility)updateUi.turnRoad.visibility().modified());
    updateUiDump.messageId.Text = ((String)updateUi._transaction.content().modified());
    updateUiDump.messageId.Visibility = ((Visibility)updateUi._transaction.visibility().modified());
    updateUiDump.dist2Target.Text = ((String)updateUi.dist2Target.content().modified());
    updateUiDump.dist2Target.Visibility = ((Visibility)updateUi.dist2Target.visibility().modified());
    updateUiDump.gpsIcon.Image = ((GpsIcon)updateUi.gpsIcon.content().modified());
    updateUiDump.gpsIcon.Visibility = ((Visibility)updateUi.gpsIcon.visibility().modified());
    updateUiDump.notificationIcon.Image = ((NotificationIcon)updateUi.notificationIcon.content().modified());
    updateUiDump.notificationIcon.Visibility = ((Visibility)updateUi.notificationIcon.visibility().modified());
    updateUiDump.notificationText.Text = ((String)updateUi.notificationText.content().modified());
    updateUiDump.notificationText.Visibility = ((Visibility)updateUi.notificationText.visibility().modified());
  }
}
