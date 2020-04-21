package com.com.navapp.connectivity.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.com.navapp.Globals;
import com.com.navapp.logging.RealmLog;
import com.com.navapp.utilities.Storage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class BtConnectedDevices
  extends BroadcastReceiver
{
  static final String KEY_DEVICES;
  static final String connecting = com.ktm.navapp.connectivity.bluetooth.BtConnectedDevices.class.getSimpleName();
  private static Devices devices = null;
  
  static
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(connecting);
    localStringBuilder.append(".devices");
    KEY_DEVICES = localStringBuilder.toString();
  }
  
  public BtConnectedDevices() {}
  
  public static List getConnectedDevices(boolean paramBoolean)
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = BluetoothAdapter.getDefaultAdapter().getBondedDevices().iterator();
    while (localIterator.hasNext())
    {
      BluetoothDevice localBluetoothDevice = (BluetoothDevice)localIterator.next();
      if (isConnected(localBluetoothDevice, paramBoolean)) {
        localLinkedList.add(localBluetoothDevice);
      }
    }
    return localLinkedList;
  }
  
  static Devices getDevices()
  {
    for (;;)
    {
      try
      {
        localObject = devices;
        if (localObject == null) {
          localObject = KEY_DEVICES;
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject;
        throw localThrowable;
      }
      try
      {
        localObject = Storage.load((String)localObject, com.ktm.navapp.connectivity.bluetooth.BtConnectedDevices.Devices.class);
        devices = (Devices)localObject;
      }
      catch (Exception localException) {}
    }
    devices = new Devices();
    Storage.save(KEY_DEVICES, devices);
    localObject = devices;
    return localObject;
  }
  
  public static boolean isConnected(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    Devices localDevices = getDevices();
    paramBluetoothDevice = paramBluetoothDevice.getAddress();
    boolean bool = connected.contains(paramBluetoothDevice);
    if (paramBoolean)
    {
      int i;
      if ((bool) && (connectedHFP.contains(paramBluetoothDevice))) {
        i = 1;
      } else {
        i = 0;
      }
      return (i != 0) && (connectedA2DP.contains(paramBluetoothDevice));
    }
    return bool;
  }
  
  static void on_A2DP_CHANGED(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    if (paramBluetoothDevice == null) {
      return;
    }
    try
    {
      RealmLog.append(connecting, "on_A2DP_CHANGED( device=%s, connected=%b )", new Object[] { paramBluetoothDevice, Boolean.valueOf(paramBoolean) });
      if (paramBoolean)
      {
        if (getDevicesconnectedA2DP.add(paramBluetoothDevice.getAddress())) {
          Storage.save(KEY_DEVICES, devices);
        }
      }
      else if (getDevicesconnectedA2DP.remove(paramBluetoothDevice.getAddress())) {
        Storage.save(KEY_DEVICES, devices);
      }
      return;
    }
    catch (Throwable paramBluetoothDevice)
    {
      throw paramBluetoothDevice;
    }
  }
  
  static void on_ACL_CHANGED(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    if (paramBluetoothDevice == null) {
      return;
    }
    try
    {
      RealmLog.append(connecting, "on_ACL_CHANGED( device=%s, connected=%b )", new Object[] { paramBluetoothDevice, Boolean.valueOf(paramBoolean) });
      if (paramBoolean)
      {
        if (getDevicesconnected.add(paramBluetoothDevice.getAddress())) {
          Storage.save(KEY_DEVICES, devices);
        }
      }
      else if (getDevicesconnected.remove(paramBluetoothDevice.getAddress())) {
        Storage.save(KEY_DEVICES, devices);
      }
      return;
    }
    catch (Throwable paramBluetoothDevice)
    {
      throw paramBluetoothDevice;
    }
  }
  
  static void on_HFP_CHANGED(BluetoothDevice paramBluetoothDevice, boolean paramBoolean)
  {
    if (paramBluetoothDevice == null) {
      return;
    }
    try
    {
      RealmLog.append(connecting, "on_HFP_CHANGED( device=%s, connected=%b )", new Object[] { paramBluetoothDevice, Boolean.valueOf(paramBoolean) });
      if (paramBoolean)
      {
        if (getDevicesconnectedHFP.add(paramBluetoothDevice.getAddress())) {
          Storage.save(KEY_DEVICES, devices);
        }
      }
      else if (getDevicesconnectedHFP.remove(paramBluetoothDevice.getAddress())) {
        Storage.save(KEY_DEVICES, devices);
      }
      return;
    }
    catch (Throwable paramBluetoothDevice)
    {
      throw paramBluetoothDevice;
    }
  }
  
  public static void overrideConnected(BluetoothDevice paramBluetoothDevice, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      RealmLog.append(connecting, "setConnected( device=%s, connected=%b )", new Object[] { paramBluetoothDevice, Boolean.valueOf(paramBoolean1) });
      on_ACL_CHANGED(paramBluetoothDevice, paramBoolean1);
      if (paramBoolean2)
      {
        on_HFP_CHANGED(paramBluetoothDevice, paramBoolean1);
        on_A2DP_CHANGED(paramBluetoothDevice, paramBoolean1);
      }
      return;
    }
    catch (Throwable paramBluetoothDevice)
    {
      throw paramBluetoothDevice;
    }
  }
  
  void initialize()
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter != null) {
      localBluetoothAdapter.getProfileProxy(Globals.getContext(), new BtConnectedDevices.1(this), 1);
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.bluetooth.device.action.ACL_CONNECTED".equals(paramIntent.getAction()))
    {
      on_ACL_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), true);
      return;
    }
    if ("android.bluetooth.device.action.ACL_DISCONNECTED".equals(paramIntent.getAction()))
    {
      on_ACL_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), false);
      return;
    }
    if ("android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED".equals(paramIntent.getAction()))
    {
      on_ACL_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), false);
      return;
    }
    int i;
    if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(paramIntent.getAction()))
    {
      i = paramIntent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
      if (i == 2)
      {
        on_HFP_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), true);
        return;
      }
      if ((i == 0) || (i == 3)) {
        on_HFP_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), false);
      }
    }
    else if ("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED".equals(paramIntent.getAction()))
    {
      i = paramIntent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
      if (i == 2)
      {
        on_A2DP_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), true);
        return;
      }
      if ((i == 0) || (i == 3)) {
        on_A2DP_CHANGED((BluetoothDevice)paramIntent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), false);
      }
    }
  }
  
  public class Devices
  {
    public final Set<String> connected = new HashSet();
    public final Set<String> connectedA2DP = new HashSet();
    public final Set<String> connectedHFP = new HashSet();
    
    public Devices() {}
  }
}
