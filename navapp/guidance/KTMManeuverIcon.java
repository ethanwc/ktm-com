package com.com.navapp.guidance;

import java.util.HashMap;
import java.util.Map;

public enum KTMManeuverIcon
{
  private static final Map<Integer, com.ktm.navapp.guidance.KTMManeuverIcon> mValuesMap;
  private int iconId;
  
  static
  {
    int i = 0;
    UNDEFINED = new KTMManeuverIcon("UNDEFINED", 0, 0);
    GO_STRAIGHT = new KTMManeuverIcon("GO_STRAIGHT", 1, 1);
    UTURN_RIGHT = new KTMManeuverIcon("UTURN_RIGHT", 2, 2);
    UTURN_LEFT = new KTMManeuverIcon("UTURN_LEFT", 3, 3);
    KEEP_RIGHT = new KTMManeuverIcon("KEEP_RIGHT", 4, 4);
    LIGHT_RIGHT = new KTMManeuverIcon("LIGHT_RIGHT", 5, 5);
    QUITE_RIGHT = new KTMManeuverIcon("QUITE_RIGHT", 6, 6);
    HEAVY_RIGHT = new KTMManeuverIcon("HEAVY_RIGHT", 7, 7);
    KEEP_MIDDLE = new KTMManeuverIcon("KEEP_MIDDLE", 8, 8);
    KEEP_LEFT = new KTMManeuverIcon("KEEP_LEFT", 9, 9);
    LIGHT_LEFT = new KTMManeuverIcon("LIGHT_LEFT", 10, 10);
    QUITE_LEFT = new KTMManeuverIcon("QUITE_LEFT", 11, 11);
    HEAVY_LEFT = new KTMManeuverIcon("HEAVY_LEFT", 12, 12);
    ENTER_HIGHWAY_RIGHT_LANE = new KTMManeuverIcon("ENTER_HIGHWAY_RIGHT_LANE", 13, 13);
    ENTER_HIGHWAY_LEFT_LANE = new KTMManeuverIcon("ENTER_HIGHWAY_LEFT_LANE", 14, 14);
    LEAVE_HIGHWAY_RIGHT_LANE = new KTMManeuverIcon("LEAVE_HIGHWAY_RIGHT_LANE", 15, 15);
    LEAVE_HIGHWAY_LEFT_LANE = new KTMManeuverIcon("LEAVE_HIGHWAY_LEFT_LANE", 16, 16);
    HIGHWAY_KEEP_RIGHT = new KTMManeuverIcon("HIGHWAY_KEEP_RIGHT", 17, 17);
    HIGHWAY_KEEP_LEFT = new KTMManeuverIcon("HIGHWAY_KEEP_LEFT", 18, 18);
    ROUNDABOUT_1 = new KTMManeuverIcon("ROUNDABOUT_1", 19, 19);
    ROUNDABOUT_2 = new KTMManeuverIcon("ROUNDABOUT_2", 20, 20);
    ROUNDABOUT_3 = new KTMManeuverIcon("ROUNDABOUT_3", 21, 21);
    ROUNDABOUT_4 = new KTMManeuverIcon("ROUNDABOUT_4", 22, 22);
    ROUNDABOUT_5 = new KTMManeuverIcon("ROUNDABOUT_5", 23, 23);
    ROUNDABOUT_6 = new KTMManeuverIcon("ROUNDABOUT_6", 24, 24);
    ROUNDABOUT_7 = new KTMManeuverIcon("ROUNDABOUT_7", 25, 25);
    ROUNDABOUT_8 = new KTMManeuverIcon("ROUNDABOUT_8", 26, 26);
    ROUNDABOUT_9 = new KTMManeuverIcon("ROUNDABOUT_9", 27, 27);
    ROUNDABOUT_10 = new KTMManeuverIcon("ROUNDABOUT_10", 28, 28);
    ROUNDABOUT_11 = new KTMManeuverIcon("ROUNDABOUT_11", 29, 29);
    ROUNDABOUT_12 = new KTMManeuverIcon("ROUNDABOUT_12", 30, 30);
    ROUNDABOUT_1_LH = new KTMManeuverIcon("ROUNDABOUT_1_LH", 31, 31);
    ROUNDABOUT_2_LH = new KTMManeuverIcon("ROUNDABOUT_2_LH", 32, 32);
    ROUNDABOUT_3_LH = new KTMManeuverIcon("ROUNDABOUT_3_LH", 33, 33);
    ROUNDABOUT_4_LH = new KTMManeuverIcon("ROUNDABOUT_4_LH", 34, 34);
    ROUNDABOUT_5_LH = new KTMManeuverIcon("ROUNDABOUT_5_LH", 35, 35);
    ROUNDABOUT_6_LH = new KTMManeuverIcon("ROUNDABOUT_6_LH", 36, 36);
    ROUNDABOUT_7_LH = new KTMManeuverIcon("ROUNDABOUT_7_LH", 37, 37);
    ROUNDABOUT_8_LH = new KTMManeuverIcon("ROUNDABOUT_8_LH", 38, 38);
    ROUNDABOUT_9_LH = new KTMManeuverIcon("ROUNDABOUT_9_LH", 39, 39);
    ROUNDABOUT_10_LH = new KTMManeuverIcon("ROUNDABOUT_10_LH", 40, 40);
    ROUNDABOUT_11_LH = new KTMManeuverIcon("ROUNDABOUT_11_LH", 41, 41);
    ROUNDABOUT_12_LH = new KTMManeuverIcon("ROUNDABOUT_12_LH", 42, 42);
    START = new KTMManeuverIcon("START", 43, 43);
    BUS = new KTMManeuverIcon("END", 44, 44);
    FERRY = new KTMManeuverIcon("FERRY", 45, 45);
    PASS_STATION = new KTMManeuverIcon("PASS_STATION", 46, 46);
    HEAD_TO = new KTMManeuverIcon("HEAD_TO", 47, 47);
    CHANGE_LINE = new KTMManeuverIcon("CHANGE_LINE", 48, 48);
    RAB_SECT_1_RH = new KTMManeuverIcon("RAB_SECT_1_RH", 49, 100);
    RAB_SECT_2_RH = new KTMManeuverIcon("RAB_SECT_2_RH", 50, 101);
    RAB_SECT_3_RH = new KTMManeuverIcon("RAB_SECT_3_RH", 51, 102);
    RAB_SECT_4_RH = new KTMManeuverIcon("RAB_SECT_4_RH", 52, 103);
    RAB_SECT_5_RH = new KTMManeuverIcon("RAB_SECT_5_RH", 53, 104);
    RAB_SECT_6_RH = new KTMManeuverIcon("RAB_SECT_6_RH", 54, 105);
    RAB_SECT_7_RH = new KTMManeuverIcon("RAB_SECT_7_RH", 55, 106);
    RAB_SECT_8_RH = new KTMManeuverIcon("RAB_SECT_8_RH", 56, 107);
    RAB_SECT_9_RH = new KTMManeuverIcon("RAB_SECT_9_RH", 57, 108);
    RAB_SECT_10_RH = new KTMManeuverIcon("RAB_SECT_10_RH", 58, 109);
    RAB_SECT_11_RH = new KTMManeuverIcon("RAB_SECT_11_RH", 59, 110);
    RAB_SECT_12_RH = new KTMManeuverIcon("RAB_SECT_12_RH", 60, 111);
    RAB_SECT_13_RH = new KTMManeuverIcon("RAB_SECT_13_RH", 61, 112);
    RAB_SECT_14_RH = new KTMManeuverIcon("RAB_SECT_14_RH", 62, 113);
    RAB_SECT_15_RH = new KTMManeuverIcon("RAB_SECT_15_RH", 63, 114);
    RAB_SECT_16_RH = new KTMManeuverIcon("RAB_SECT_16_RH", 64, 115);
    RAB_SECT_1_LH = new KTMManeuverIcon("RAB_SECT_1_LH", 65, 116);
    RAB_SECT_2_LH = new KTMManeuverIcon("RAB_SECT_2_LH", 66, 117);
    RAB_SECT_3_LH = new KTMManeuverIcon("RAB_SECT_3_LH", 67, 118);
    RAB_SECT_4_LH = new KTMManeuverIcon("RAB_SECT_4_LH", 68, 119);
    RAB_SECT_5_LH = new KTMManeuverIcon("RAB_SECT_5_LH", 69, 120);
    RAB_SECT_6_LH = new KTMManeuverIcon("RAB_SECT_6_LH", 70, 121);
    RAB_SECT_7_LH = new KTMManeuverIcon("RAB_SECT_7_LH", 71, 122);
    RAB_SECT_8_LH = new KTMManeuverIcon("RAB_SECT_8_LH", 72, 123);
    RAB_SECT_9_LH = new KTMManeuverIcon("RAB_SECT_9_LH", 73, 124);
    RAB_SECT_10_LH = new KTMManeuverIcon("RAB_SECT_10_LH", 74, 125);
    RAB_SECT_11_LH = new KTMManeuverIcon("RAB_SECT_11_LH", 75, 126);
    RAB_SECT_12_LH = new KTMManeuverIcon("RAB_SECT_12_LH", 76, 127);
    RAB_SECT_13_LH = new KTMManeuverIcon("RAB_SECT_13_LH", 77, 128);
    RAB_SECT_14_LH = new KTMManeuverIcon("RAB_SECT_14_LH", 78, 129);
    RAB_SECT_15_LH = new KTMManeuverIcon("RAB_SECT_15_LH", 79, 130);
    RAB_SECT_16_LH = new KTMManeuverIcon("RAB_SECT_16_LH", 80, 131);
    $VALUES = new KTMManeuverIcon[] { UNDEFINED, GO_STRAIGHT, UTURN_RIGHT, UTURN_LEFT, KEEP_RIGHT, LIGHT_RIGHT, QUITE_RIGHT, HEAVY_RIGHT, KEEP_MIDDLE, KEEP_LEFT, LIGHT_LEFT, QUITE_LEFT, HEAVY_LEFT, ENTER_HIGHWAY_RIGHT_LANE, ENTER_HIGHWAY_LEFT_LANE, LEAVE_HIGHWAY_RIGHT_LANE, LEAVE_HIGHWAY_LEFT_LANE, HIGHWAY_KEEP_RIGHT, HIGHWAY_KEEP_LEFT, ROUNDABOUT_1, ROUNDABOUT_2, ROUNDABOUT_3, ROUNDABOUT_4, ROUNDABOUT_5, ROUNDABOUT_6, ROUNDABOUT_7, ROUNDABOUT_8, ROUNDABOUT_9, ROUNDABOUT_10, ROUNDABOUT_11, ROUNDABOUT_12, ROUNDABOUT_1_LH, ROUNDABOUT_2_LH, ROUNDABOUT_3_LH, ROUNDABOUT_4_LH, ROUNDABOUT_5_LH, ROUNDABOUT_6_LH, ROUNDABOUT_7_LH, ROUNDABOUT_8_LH, ROUNDABOUT_9_LH, ROUNDABOUT_10_LH, ROUNDABOUT_11_LH, ROUNDABOUT_12_LH, START, BUS, FERRY, PASS_STATION, HEAD_TO, CHANGE_LINE, RAB_SECT_1_RH, RAB_SECT_2_RH, RAB_SECT_3_RH, RAB_SECT_4_RH, RAB_SECT_5_RH, RAB_SECT_6_RH, RAB_SECT_7_RH, RAB_SECT_8_RH, RAB_SECT_9_RH, RAB_SECT_10_RH, RAB_SECT_11_RH, RAB_SECT_12_RH, RAB_SECT_13_RH, RAB_SECT_14_RH, RAB_SECT_15_RH, RAB_SECT_16_RH, RAB_SECT_1_LH, RAB_SECT_2_LH, RAB_SECT_3_LH, RAB_SECT_4_LH, RAB_SECT_5_LH, RAB_SECT_6_LH, RAB_SECT_7_LH, RAB_SECT_8_LH, RAB_SECT_9_LH, RAB_SECT_10_LH, RAB_SECT_11_LH, RAB_SECT_12_LH, RAB_SECT_13_LH, RAB_SECT_14_LH, RAB_SECT_15_LH, RAB_SECT_16_LH };
    mValuesMap = new HashMap();
    KTMManeuverIcon[] arrayOfKTMManeuverIcon = values();
    int j = arrayOfKTMManeuverIcon.length;
    while (i < j)
    {
      KTMManeuverIcon localKTMManeuverIcon = arrayOfKTMManeuverIcon[i];
      mValuesMap.put(Integer.valueOf(localKTMManeuverIcon.value()), localKTMManeuverIcon);
      i += 1;
    }
  }
  
  private KTMManeuverIcon(int paramInt)
  {
    iconId = paramInt;
  }
  
  public static KTMManeuverIcon FromInt(int paramInt)
  {
    KTMManeuverIcon localKTMManeuverIcon2 = (KTMManeuverIcon)mValuesMap.get(Integer.valueOf(paramInt));
    KTMManeuverIcon localKTMManeuverIcon1 = localKTMManeuverIcon2;
    if (localKTMManeuverIcon2 == null) {
      localKTMManeuverIcon1 = UNDEFINED;
    }
    return localKTMManeuverIcon1;
  }
  
  public boolean isLhRoundaboutIcon()
  {
    switch (1.$SwitchMap$com$ktm$navapp$guidance$KTMManeuverIcon[ordinal()])
    {
    default: 
      return false;
    }
    return true;
  }
  
  public boolean isRhRoundaboutIcon()
  {
    switch (1.$SwitchMap$com$ktm$navapp$guidance$KTMManeuverIcon[ordinal()])
    {
    default: 
      return false;
    }
    return true;
  }
  
  public int value()
  {
    return iconId;
  }
}
