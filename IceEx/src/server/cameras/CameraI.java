package server.cameras;

import Laboratory.*;
import Ice.Current;

import java.util.List;

public class CameraI extends _CameraDisp {
    private CameraStateProvider stateProvider;
    private CameraServiceProvider serviceProvider;

    public CameraI(){
        serviceProvider = new CameraServiceProvider();
        stateProvider = new CameraStateProvider();
    }

    public void setZoom(int zoom, Current current) throws ZoomOutOfRangeException{
        serviceProvider.setZoom(zoom, stateProvider);
    }

    public void rotateLeft(float angle, Current current) throws RotationOutOfRangeException{
        serviceProvider.rotateLeft(angle, stateProvider);
    }

    public void rotateRight(float angle, Current current) throws RotationOutOfRangeException{
        serviceProvider.rotateRight(angle, stateProvider);
    }

    public void rotateTop(float angle, Current current) throws RotationOutOfRangeException{
        serviceProvider.rotateTop(angle, stateProvider);
    }

    public void rotateDown(float angle, Current current) throws RotationOutOfRangeException{
        serviceProvider.rotateDown(angle, stateProvider);
    }

    public List<String> getDeviceFunctions(Current current){
        return serviceProvider.getDeviceFunctions();
    }

    public String getState(Current current){
        return stateProvider.getState();
    }
}
