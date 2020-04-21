package com.com.kmrc.upgrade.socket.btbluecove;

import com.com.kmrc.upgrade.Connection.LinkingListener;
import com.com.kmrc.upgrade.KSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

abstract class BtKSocket
  extends KSocket
{
  private final String deviceName;
  private StreamConnection transportConnection = null;
  private final String uuidString;
  private StreamConnectionNotifier welcomeConnection = null;
  
  public BtKSocket(String paramString1, String paramString2)
  {
    deviceName = paramString1;
    uuidString = paramString2;
  }
  
  public void accept(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    if (welcomeConnection == null)
    {
      LocalDevice.getLocalDevice().setDiscoverable(10390323);
      Object localObject = new UUID(uuidString.replace("-", ""), false);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("btspp://");
      localStringBuilder.append(deviceName);
      localStringBuilder.append(":");
      localStringBuilder.append(((UUID)localObject).toString());
      localStringBuilder.append(";name=RemoteBluetooth");
      localObject = localStringBuilder.toString();
      try
      {
        localObject = Connector.open((String)localObject);
        welcomeConnection = ((StreamConnectionNotifier)localObject);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    paramLinkingListener.linking();
    transportConnection = welcomeConnection.acceptAndOpen();
    a = transportConnection.openDataInputStream();
    b = transportConnection.openDataOutputStream();
  }
  
  public void close()
    throws IOException
  {
    if (a != null) {
      a.close();
    }
    if (b != null) {
      b.close();
    }
    Object localObject = welcomeConnection;
    if (localObject != null) {
      ((StreamConnectionNotifier)localObject).close();
    }
    localObject = transportConnection;
    if (localObject != null) {
      ((StreamConnection)localObject).close();
    }
  }
  
  public void connect(Connection.LinkingListener paramLinkingListener)
    throws IOException
  {
    Object localObject = new UUID(uuidString.replace("-", ""), false);
    localObject = new DeviceSearcher(deviceName, (UUID)localObject).findDevice();
    paramLinkingListener.linking();
    if (localObject != null)
    {
      transportConnection = ((StreamConnection)Connector.open((String)localObject));
      a = transportConnection.openDataInputStream();
      b = transportConnection.openDataOutputStream();
      return;
    }
    throw new IOException("Couldn't find a matching device");
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof BtKSocket)) {
      return false;
    }
    paramObject = (BtKSocket)paramObject;
    return (deviceName.equals(deviceName)) && (uuidString.equals(uuidString));
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("bt://");
    localStringBuilder.append(deviceName);
    localStringBuilder.append(":");
    localStringBuilder.append(uuidString);
    return localStringBuilder.toString();
  }
  
  class DeviceSearcher
    implements DiscoveryListener
  {
    private String connectionUrl;
    private final DiscoveryAgent discoveryAgent;
    private Set<RemoteDevice> scannedDevices;
    private boolean searchComplete = false;
    private final Object searchLock;
    private Set<Integer> searchTransactions;
    private final UUID[] serviceSet;
    
    DeviceSearcher(UUID paramUUID)
      throws BluetoothStateException
    {
      serviceSet = new UUID[] { paramUUID };
      searchLock = new Object();
      connectionUrl = null;
      discoveryAgent = LocalDevice.getLocalDevice().getDiscoveryAgent();
      scannedDevices = new HashSet();
      searchTransactions = new HashSet();
    }
    
    private boolean queryKnownDevices(RemoteDevice[] paramArrayOfRemoteDevice)
    {
      if (paramArrayOfRemoteDevice != null)
      {
        int j = paramArrayOfRemoteDevice.length;
        int i = 0;
        while (i < j)
        {
          RemoteDevice localRemoteDevice = paramArrayOfRemoteDevice[i];
          try
          {
            scanDevice(localRemoteDevice);
          }
          catch (IOException localIOException)
          {
            localIOException.printStackTrace();
          }
          i += 1;
        }
      }
      return false;
    }
    
    private void scanDevice(RemoteDevice paramRemoteDevice)
      throws IOException
    {
      Object localObject = searchLock;
      try
      {
        if ((equals(paramRemoteDevice.getFriendlyName(false))) && (!scannedDevices.contains(paramRemoteDevice)))
        {
          scannedDevices.add(paramRemoteDevice);
          searchTransactions.add(Integer.valueOf(discoveryAgent.searchServices(null, serviceSet, paramRemoteDevice, this)));
        }
        return;
      }
      catch (Throwable paramRemoteDevice)
      {
        throw paramRemoteDevice;
      }
    }
    
    public void deviceDiscovered(RemoteDevice paramRemoteDevice, DeviceClass paramDeviceClass)
    {
      try
      {
        scanDevice(paramRemoteDevice);
        return;
      }
      catch (IOException paramRemoteDevice)
      {
        paramRemoteDevice.printStackTrace();
      }
    }
    
    String findDevice()
    {
      queryKnownDevices(discoveryAgent.retrieveDevices(0));
      queryKnownDevices(discoveryAgent.retrieveDevices(1));
      label26:
      Object localObject2;
      while (!searchTransactions.isEmpty())
      {
        localObject1 = searchLock;
        localObject2 = searchLock;
      }
      try
      {
        try
        {
          localObject2.wait();
        }
        catch (Throwable localThrowable1)
        {
          break label66;
        }
      }
      catch (InterruptedException localInterruptedException1)
      {
        try
        {
          ((DiscoveryAgent)localObject1).startInquiry(10390323, this);
          for (;;)
          {
            if ((searchComplete) && (searchTransactions.isEmpty())) {
              return connectionUrl;
            }
            localObject1 = searchLock;
            Object localObject3 = searchLock;
            try
            {
              try
              {
                localObject3.wait();
              }
              catch (Throwable localThrowable2)
              {
                break;
              }
            }
            catch (InterruptedException localInterruptedException2)
            {
              for (;;) {}
            }
          }
          throw localThrowable2;
          localInterruptedException1 = localInterruptedException1;
        }
        catch (BluetoothStateException localBluetoothStateException)
        {
          for (;;) {}
        }
      }
      break label26;
      label66:
      throw localThrowable1;
      if (!searchComplete) {
        localObject1 = discoveryAgent;
      }
    }
    
    public void inquiryCompleted(int paramInt)
    {
      Object localObject = searchLock;
      try
      {
        searchComplete = true;
        searchLock.notify();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void serviceSearchCompleted(int paramInt1, int paramInt2)
    {
      Object localObject = searchLock;
      try
      {
        searchTransactions.remove(Integer.valueOf(paramInt1));
        searchLock.notify();
        return;
      }
      catch (Throwable localThrowable)
      {
        throw localThrowable;
      }
    }
    
    public void servicesDiscovered(int paramInt, ServiceRecord[] paramArrayOfServiceRecord)
    {
      if (paramArrayOfServiceRecord.length > 0)
      {
        connectionUrl = paramArrayOfServiceRecord[0].getConnectionURL(0, false);
        searchComplete = true;
        discoveryAgent.cancelServiceSearch(paramInt);
      }
    }
  }
}
