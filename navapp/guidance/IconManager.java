package com.com.navapp.guidance;

import com.com.kmrc.services.navigation.TurnIcon;
import com.here.android.ui.routing.Maneuver;
import com.here.android.ui.routing.Maneuver.Action;
import java.util.HashMap;
import java.util.Map;

public class IconManager
{
  private static final Map<com.here.android.mpa.routing.Maneuver.Icon, com.ktm.navapp.guidance.KTMManeuverIcon> hereToKTMIconsMap;
  private static final Map<com.ktm.navapp.guidance.KTMManeuverIcon, Integer> ktmIconsResourcesMap;
  private static final Map<com.ktm.navapp.guidance.KTMManeuverIcon, Integer> roundaboutLHIconsMap;
  
  static
  {
    Object localObject1 = new HashMap();
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.UNDEFINED, KTMManeuverIcon.UNDEFINED);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.GO_STRAIGHT, KTMManeuverIcon.GO_STRAIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.UTURN_RIGHT, KTMManeuverIcon.UTURN_RIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.UTURN_LEFT, KTMManeuverIcon.UTURN_LEFT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.KEEP_RIGHT, KTMManeuverIcon.KEEP_RIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.LIGHT_RIGHT, KTMManeuverIcon.LIGHT_RIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.QUITE_RIGHT, KTMManeuverIcon.QUITE_RIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.HEAVY_RIGHT, KTMManeuverIcon.HEAVY_RIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.KEEP_MIDDLE, KTMManeuverIcon.KEEP_MIDDLE);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.KEEP_LEFT, KTMManeuverIcon.KEEP_LEFT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.LIGHT_LEFT, KTMManeuverIcon.LIGHT_LEFT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.QUITE_LEFT, KTMManeuverIcon.QUITE_LEFT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.HEAVY_LEFT, KTMManeuverIcon.HEAVY_LEFT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ENTER_HIGHWAY_RIGHT_LANE, KTMManeuverIcon.ENTER_HIGHWAY_RIGHT_LANE);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ENTER_HIGHWAY_LEFT_LANE, KTMManeuverIcon.ENTER_HIGHWAY_LEFT_LANE);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.LEAVE_HIGHWAY_RIGHT_LANE, KTMManeuverIcon.LEAVE_HIGHWAY_RIGHT_LANE);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.LEAVE_HIGHWAY_LEFT_LANE, KTMManeuverIcon.LEAVE_HIGHWAY_LEFT_LANE);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.HIGHWAY_KEEP_RIGHT, KTMManeuverIcon.HIGHWAY_KEEP_RIGHT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.HIGHWAY_KEEP_LEFT, KTMManeuverIcon.HIGHWAY_KEEP_LEFT);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_1, KTMManeuverIcon.ROUNDABOUT_1);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_2, KTMManeuverIcon.ROUNDABOUT_2);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_3, KTMManeuverIcon.ROUNDABOUT_3);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_4, KTMManeuverIcon.ROUNDABOUT_4);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_5, KTMManeuverIcon.ROUNDABOUT_5);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_6, KTMManeuverIcon.ROUNDABOUT_6);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_7, KTMManeuverIcon.ROUNDABOUT_7);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_8, KTMManeuverIcon.ROUNDABOUT_8);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_9, KTMManeuverIcon.ROUNDABOUT_9);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_10, KTMManeuverIcon.ROUNDABOUT_10);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_11, KTMManeuverIcon.ROUNDABOUT_11);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_12, KTMManeuverIcon.ROUNDABOUT_12);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_1_LH, KTMManeuverIcon.ROUNDABOUT_1_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_2_LH, KTMManeuverIcon.ROUNDABOUT_2_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_3_LH, KTMManeuverIcon.ROUNDABOUT_3_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_4_LH, KTMManeuverIcon.ROUNDABOUT_4_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_5_LH, KTMManeuverIcon.ROUNDABOUT_5_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_6_LH, KTMManeuverIcon.ROUNDABOUT_6_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_7_LH, KTMManeuverIcon.ROUNDABOUT_7_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_8_LH, KTMManeuverIcon.ROUNDABOUT_8_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_9_LH, KTMManeuverIcon.ROUNDABOUT_9_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_10_LH, KTMManeuverIcon.ROUNDABOUT_10_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_11_LH, KTMManeuverIcon.ROUNDABOUT_11_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.ROUNDABOUT_12_LH, KTMManeuverIcon.ROUNDABOUT_12_LH);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.START, KTMManeuverIcon.START);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.PAUSE, KTMManeuverIcon.BUS);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.FERRY, KTMManeuverIcon.FERRY);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.PASS_STATION, KTMManeuverIcon.PASS_STATION);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.HEAD_TO, KTMManeuverIcon.HEAD_TO);
    ((Map)localObject1).put(com.here.android.ui.routing.Maneuver.Icon.CHANGE_LINE, KTMManeuverIcon.CHANGE_LINE);
    hereToKTMIconsMap = (Map)localObject1;
    HashMap localHashMap = new HashMap();
    localHashMap.put(KTMManeuverIcon.RAB_SECT_1_RH, Integer.valueOf(2131165964));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_2_RH, Integer.valueOf(2131165966));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_3_RH, Integer.valueOf(2131165968));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_4_RH, Integer.valueOf(2131165970));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_5_RH, Integer.valueOf(2131165972));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_6_RH, Integer.valueOf(2131165974));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_7_RH, Integer.valueOf(2131165976));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_8_RH, Integer.valueOf(2131165978));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_9_RH, Integer.valueOf(2131165980));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_10_RH, Integer.valueOf(2131165950));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_11_RH, Integer.valueOf(2131165952));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_12_RH, Integer.valueOf(2131165954));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_13_RH, Integer.valueOf(2131165956));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_14_RH, Integer.valueOf(2131165958));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_15_RH, Integer.valueOf(2131165960));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_16_RH, Integer.valueOf(2131165962));
    Object localObject2 = KTMManeuverIcon.RAB_SECT_1_LH;
    localObject1 = Integer.valueOf(2131165963);
    localHashMap.put(localObject2, localObject1);
    Object localObject3 = KTMManeuverIcon.RAB_SECT_2_LH;
    localObject2 = Integer.valueOf(2131165965);
    localHashMap.put(localObject3, localObject2);
    Object localObject4 = KTMManeuverIcon.RAB_SECT_3_LH;
    localObject3 = Integer.valueOf(2131165967);
    localHashMap.put(localObject4, localObject3);
    Object localObject5 = KTMManeuverIcon.RAB_SECT_4_LH;
    localObject4 = Integer.valueOf(2131165969);
    localHashMap.put(localObject5, localObject4);
    Object localObject6 = KTMManeuverIcon.RAB_SECT_5_LH;
    localObject5 = Integer.valueOf(2131165971);
    localHashMap.put(localObject6, localObject5);
    Object localObject7 = KTMManeuverIcon.RAB_SECT_6_LH;
    localObject6 = Integer.valueOf(2131165973);
    localHashMap.put(localObject7, localObject6);
    KTMManeuverIcon localKTMManeuverIcon = KTMManeuverIcon.RAB_SECT_7_LH;
    localObject7 = Integer.valueOf(2131165975);
    localHashMap.put(localKTMManeuverIcon, localObject7);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_8_LH, Integer.valueOf(2131165977));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_9_LH, Integer.valueOf(2131165979));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_10_LH, Integer.valueOf(2131165949));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_11_LH, Integer.valueOf(2131165951));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_12_LH, Integer.valueOf(2131165953));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_13_LH, Integer.valueOf(2131165955));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_14_LH, Integer.valueOf(2131165957));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_15_LH, Integer.valueOf(2131165959));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_16_LH, Integer.valueOf(2131165961));
    localHashMap.put(KTMManeuverIcon.UNDEFINED, Integer.valueOf(2131166019));
    localHashMap.put(KTMManeuverIcon.GO_STRAIGHT, Integer.valueOf(2131165372));
    localHashMap.put(KTMManeuverIcon.UTURN_RIGHT, Integer.valueOf(2131166021));
    localHashMap.put(KTMManeuverIcon.UTURN_LEFT, Integer.valueOf(2131166020));
    localHashMap.put(KTMManeuverIcon.KEEP_RIGHT, Integer.valueOf(2131165888));
    localHashMap.put(KTMManeuverIcon.LIGHT_RIGHT, Integer.valueOf(2131165896));
    localHashMap.put(KTMManeuverIcon.QUITE_RIGHT, Integer.valueOf(2131165948));
    localHashMap.put(KTMManeuverIcon.HEAVY_RIGHT, Integer.valueOf(2131165378));
    localHashMap.put(KTMManeuverIcon.KEEP_MIDDLE, Integer.valueOf(2131165887));
    localHashMap.put(KTMManeuverIcon.KEEP_LEFT, Integer.valueOf(2131165886));
    localHashMap.put(KTMManeuverIcon.LIGHT_LEFT, Integer.valueOf(2131165895));
    localHashMap.put(KTMManeuverIcon.QUITE_LEFT, Integer.valueOf(2131165947));
    localHashMap.put(KTMManeuverIcon.HEAVY_LEFT, Integer.valueOf(2131165377));
    localHashMap.put(KTMManeuverIcon.ENTER_HIGHWAY_RIGHT_LANE, Integer.valueOf(2131165365));
    localHashMap.put(KTMManeuverIcon.ENTER_HIGHWAY_LEFT_LANE, Integer.valueOf(2131165364));
    localHashMap.put(KTMManeuverIcon.LEAVE_HIGHWAY_RIGHT_LANE, Integer.valueOf(2131165891));
    localHashMap.put(KTMManeuverIcon.LEAVE_HIGHWAY_LEFT_LANE, Integer.valueOf(2131165890));
    localHashMap.put(KTMManeuverIcon.HIGHWAY_KEEP_RIGHT, Integer.valueOf(2131165380));
    localHashMap.put(KTMManeuverIcon.HIGHWAY_KEEP_LEFT, Integer.valueOf(2131165379));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_1, Integer.valueOf(2131165987));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_2, Integer.valueOf(2131165995));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_3, Integer.valueOf(2131165997));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_4, Integer.valueOf(2131165999));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_5, Integer.valueOf(2131166001));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_6, Integer.valueOf(2131166003));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_7, Integer.valueOf(2131166005));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_8, Integer.valueOf(2131166007));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_9, Integer.valueOf(2131166009));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_10, Integer.valueOf(2131165988));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_11, Integer.valueOf(2131165990));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_12, Integer.valueOf(2131165992));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_1_LH, Integer.valueOf(2131165994));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_2_LH, Integer.valueOf(2131165996));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_3_LH, Integer.valueOf(2131165998));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_4_LH, Integer.valueOf(2131166000));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_5_LH, Integer.valueOf(2131166002));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_6_LH, Integer.valueOf(2131166004));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_7_LH, Integer.valueOf(2131166006));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_8_LH, Integer.valueOf(2131166008));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_9_LH, Integer.valueOf(2131166010));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_10_LH, Integer.valueOf(2131165989));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_11_LH, Integer.valueOf(2131165991));
    localHashMap.put(KTMManeuverIcon.ROUNDABOUT_12_LH, Integer.valueOf(2131165993));
    localHashMap.put(KTMManeuverIcon.START, Integer.valueOf(2131166015));
    localHashMap.put(KTMManeuverIcon.BUS, Integer.valueOf(2131165363));
    localHashMap.put(KTMManeuverIcon.FERRY, Integer.valueOf(2131165366));
    localHashMap.put(KTMManeuverIcon.PASS_STATION, Integer.valueOf(2131165939));
    localHashMap.put(KTMManeuverIcon.HEAD_TO, Integer.valueOf(2131165376));
    localHashMap.put(KTMManeuverIcon.CHANGE_LINE, Integer.valueOf(2131165309));
    ktmIconsResourcesMap = localHashMap;
    localHashMap = new HashMap();
    localHashMap.put(KTMManeuverIcon.RAB_SECT_1_LH, localObject1);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_2_LH, localObject2);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_3_LH, localObject3);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_4_LH, localObject4);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_5_LH, localObject5);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_6_LH, localObject6);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_7_LH, localObject7);
    localHashMap.put(KTMManeuverIcon.RAB_SECT_8_LH, Integer.valueOf(2131165977));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_9_LH, Integer.valueOf(2131165979));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_10_LH, Integer.valueOf(2131165949));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_11_LH, Integer.valueOf(2131165951));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_12_LH, Integer.valueOf(2131165953));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_13_LH, Integer.valueOf(2131165955));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_14_LH, Integer.valueOf(2131165957));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_15_LH, Integer.valueOf(2131165959));
    localHashMap.put(KTMManeuverIcon.RAB_SECT_16_LH, Integer.valueOf(2131165961));
    roundaboutLHIconsMap = localHashMap;
  }
  
  public IconManager() {}
  
  public static KTMManeuverIcon getIcon(Maneuver paramManeuver)
  {
    if (paramManeuver == null) {
      return (KTMManeuverIcon)hereToKTMIconsMap.get(com.here.android.ui.routing.Maneuver.Icon.UNDEFINED);
    }
    if (paramManeuver.getAction() == Maneuver.Action.STOPOVER) {
      return (KTMManeuverIcon)hereToKTMIconsMap.get(com.here.android.ui.routing.Maneuver.Icon.START);
    }
    KTMManeuverIcon localKTMManeuverIcon = (KTMManeuverIcon)hereToKTMIconsMap.get(paramManeuver.getIcon());
    Object localObject = paramManeuver.getRoadName();
    String str = paramManeuver.getNextRoadName();
    boolean bool;
    if ((localObject != null) && (((String)localObject).equals(str))) {
      bool = true;
    } else {
      bool = false;
    }
    if (localKTMManeuverIcon.isRhRoundaboutIcon()) {
      return mapAngleToRHIcon(bool, paramManeuver.getAngle());
    }
    localObject = localKTMManeuverIcon;
    if (localKTMManeuverIcon.isLhRoundaboutIcon()) {
      localObject = mapAngleToLHIcon(bool, paramManeuver.getAngle());
    }
    return localObject;
  }
  
  public static String getIconExit(Maneuver paramManeuver)
  {
    if (paramManeuver == null) {
      paramManeuver = null;
    } else {
      paramManeuver = paramManeuver.getIcon();
    }
    if (paramManeuver == null) {
      return "";
    }
    switch (1.$SwitchMap$com$here$android$mpa$routing$Maneuver$Icon[paramManeuver.ordinal()])
    {
    default: 
      return "";
    case 23: 
    case 24: 
      return "12";
    case 21: 
    case 22: 
      return "11";
    case 19: 
    case 20: 
      return "10";
    case 17: 
    case 18: 
      return "9";
    case 15: 
    case 16: 
      return "8";
    case 13: 
    case 14: 
      return "7";
    case 11: 
    case 12: 
      return "6";
    case 9: 
    case 10: 
      return "5";
    case 7: 
    case 8: 
      return "4";
    case 5: 
    case 6: 
      return "3";
    case 3: 
    case 4: 
      return "2";
    }
    return "1";
  }
  
  public static int getIconResource(Maneuver paramManeuver)
  {
    return ((Integer)ktmIconsResourcesMap.get(getIcon(paramManeuver))).intValue();
  }
  
  public static KTMManeuverIcon getKTMIcon(com.here.android.ui.routing.Maneuver.Icon paramIcon)
  {
    return (KTMManeuverIcon)hereToKTMIconsMap.get(paramIcon);
  }
  
  public static TurnIcon getTurnIconValue(Maneuver paramManeuver)
  {
    paramManeuver = getIcon(paramManeuver);
    try
    {
      paramManeuver = TurnIcon.valueOf(paramManeuver.toString());
      return paramManeuver;
    }
    catch (IllegalArgumentException paramManeuver)
    {
      for (;;) {}
    }
    return TurnIcon.valueOf(KTMManeuverIcon.UNDEFINED.toString());
  }
  
  private static KTMManeuverIcon mapAngleToLHIcon(boolean paramBoolean, int paramInt)
  {
    KTMManeuverIcon localKTMManeuverIcon = KTMManeuverIcon.UNDEFINED;
    float f = paramInt;
    if (f > 33.0F) {
      f = (f - 11.25F) / 22.5F;
    } else {
      f = 0.0F;
    }
    int k = (int)f;
    int j = Math.min(k, 15) + KTMManeuverIcon.RAB_SECT_1_LH.value();
    int i = j;
    if (k >= 15) {
      if (paramBoolean)
      {
        i = j;
        if (360 - paramInt < 2.0F) {}
      }
      else
      {
        i = KTMManeuverIcon.RAB_SECT_15_LH.value();
      }
    }
    if (ktmIconsResourcesMap.containsKey(KTMManeuverIcon.FromInt(i))) {
      localKTMManeuverIcon = KTMManeuverIcon.FromInt(i);
    }
    return localKTMManeuverIcon;
  }
  
  private static KTMManeuverIcon mapAngleToRHIcon(boolean paramBoolean, int paramInt)
  {
    KTMManeuverIcon localKTMManeuverIcon = KTMManeuverIcon.UNDEFINED;
    float f = 360 - paramInt;
    if (f > 33.0F) {
      f = (f - 11.25F) / 22.5F;
    } else {
      f = 0.0F;
    }
    int k = (int)f;
    int j = Math.min(k, 15) + KTMManeuverIcon.RAB_SECT_1_RH.value();
    int i = j;
    if (k >= 15) {
      if (paramBoolean)
      {
        i = j;
        if (paramInt < 2.0F) {}
      }
      else
      {
        i = KTMManeuverIcon.RAB_SECT_15_RH.value();
      }
    }
    if (ktmIconsResourcesMap.containsKey(KTMManeuverIcon.FromInt(i))) {
      localKTMManeuverIcon = KTMManeuverIcon.FromInt(i);
    }
    return localKTMManeuverIcon;
  }
}
