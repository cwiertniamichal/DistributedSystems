package server.device;

import Laboratory._DeviceDisp;
import Ice.Current;

import java.util.List;

public class DeviceI extends _DeviceDisp {
    private DeviceServiceProvider serviceProvider;
    private DeviceStateProvider stateProvider;

    public DeviceI(){
        this.stateProvider = new DeviceStateProvider();
        this.serviceProvider = new DeviceServiceProvider();
    }

    public String getState(Current current){
        return stateProvider.getState();
    }

    public List<String> getDeviceFunctions(Current current){
        return serviceProvider.getDeviceFunctions();
    }
}
