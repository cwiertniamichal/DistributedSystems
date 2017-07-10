package server;

import Ice.Current;
import Laboratory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LabI extends _LabDisp {
    private List<String> devicesNames;
    private Map<String, DevicePrx> proxies;
    private Map<String, String> devicesInUse;
    private Map<String, List<String>> devicesFunctions;
    private Lock lock;

    public List<String> getDevices(Current current){
        return this.devicesNames;
    }

    LabI(Map<String, DevicePrx> proxies, List<String> devicesNames,
         Map<String, List<String>> devicesFunctions){
        this.proxies = proxies;
        this.devicesNames = devicesNames;
        this.devicesInUse = new HashMap<>();
        this.devicesFunctions = devicesFunctions;
        for(String deviceName : devicesNames){
            devicesInUse.put(deviceName, "");
        }
        lock = new ReentrantLock();
    }

    public DevicePrx takeControlOverDevice(String device, String username, Current current)
    throws DeviceNotExistException, DeviceAlreadyControlledException{
        lock.lock();
        if(!devicesInUse.containsKey(device)) {
            lock.unlock();
            throw new DeviceNotExistException("Device: " + device + " does not exist");
        }
        if(!devicesInUse.get(device).equals("")){
            lock.unlock();
            throw new DeviceAlreadyControlledException("Device: " + device + " is " +
                    "being used by " + devicesInUse.get(device));
        }
        devicesInUse.put(device, username);
        lock.unlock();
        return proxies.get(device);
    }

    public void releaseDevice(String device, String username, Current current)
    throws DeviceNotExistException, DeviceNotControlledException{
        if(!devicesInUse.containsKey(device))
            throw new DeviceNotExistException("Device: " + device + " does not exist");
        if(!devicesInUse.get(device).equals(username))
            throw new DeviceNotControlledException("Device: " + device + " is not " +
                    "controlled by user: " + username);
        devicesInUse.put(device, "");
    }

    public List<String> getAvailableFunctionsForDevice(String device, Current current)
        throws DeviceNotExistException{
        if(!devicesFunctions.containsKey(device))
            throw new DeviceNotExistException("Device: " + device + " does not exist");
        return devicesFunctions.get(device);
    }

}
