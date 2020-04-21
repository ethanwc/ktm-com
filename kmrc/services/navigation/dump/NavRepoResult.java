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

public class NavRepoResult
  extends NavRepoDump
{
  public NavRepoResult(NavRepo paramNavRepo)
  {
    uiContext = ((UiContext)uiContext.result());
    updateUiDump.turnIcon.Image = ((TurnIcon)updateUi.turnIcon.content().result());
    updateUiDump.turnIcon.Visibility = ((Visibility)updateUi.turnIcon.visibility().result());
    updateUiDump.turnDist.Text = ((String)updateUi.turnDist.content().result());
    updateUiDump.turnDist.Visibility = ((Visibility)updateUi.turnDist.visibility().result());
    updateUiDump.turnDistUnit.Text = ((String)updateUi.turnDistUnit.content().result());
    updateUiDump.turnDistUnit.Visibility = ((Visibility)updateUi.turnDistUnit.visibility().result());
    updateUiDump.turnInfo.Text = ((String)updateUi.turnInfo.content().result());
    updateUiDump.turnInfo.Visibility = ((Visibility)updateUi.turnInfo.visibility().result());
    updateUiDump.turnRoad.Text = ((String)updateUi.turnRoad.content().result());
    updateUiDump.turnRoad.Visibility = ((Visibility)updateUi.turnRoad.visibility().result());
    updateUiDump.messageId.Text = ((String)updateUi._transaction.content().result());
    updateUiDump.messageId.Visibility = ((Visibility)updateUi._transaction.visibility().result());
    updateUiDump.dist2Target.Text = ((String)updateUi.dist2Target.content().result());
    updateUiDump.dist2Target.Visibility = ((Visibility)updateUi.dist2Target.visibility().result());
    updateUiDump.gpsIcon.Image = ((GpsIcon)updateUi.gpsIcon.content().result());
    updateUiDump.gpsIcon.Visibility = ((Visibility)updateUi.gpsIcon.visibility().result());
    updateUiDump.notificationIcon.Image = ((NotificationIcon)updateUi.notificationIcon.content().result());
    updateUiDump.notificationIcon.Visibility = ((Visibility)updateUi.notificationIcon.visibility().result());
    updateUiDump.notificationText.Text = ((String)updateUi.notificationText.content().result());
    updateUiDump.notificationText.Visibility = ((Visibility)updateUi.notificationText.visibility().result());
  }
}
