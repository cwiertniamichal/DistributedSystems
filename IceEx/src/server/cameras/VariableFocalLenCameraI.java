package server.cameras;

import Ice.Current;
import Laboratory.*;

import java.util.List;

public class VariableFocalLenCameraI extends _VariableFocalLenCameraDisp {
    private VariableFocalLenCameraServiceProvider serviceProvider;
    private VariableFocalLenCameraStateProvider stateProvider;

    public VariableFocalLenCameraI(){
        this.serviceProvider = new VariableFocalLenCameraServiceProvider();
        this.stateProvider = new VariableFocalLenCameraStateProvider();
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

    public void increaseFocalLength(float focalLength, Current current) throws FocalLengthOutOfRangeException{
        serviceProvider.increaseFocalLength(focalLength, stateProvider);
    }
    public void decreaseFocalLength(float focalLength, Current current) throws FocalLengthOutOfRangeException{
        serviceProvider.decreaseFocalLength(focalLength, stateProvider);

    }
    public void setFocalLength(float focalLength, Current current) throws FocalLengthOutOfRangeException{
        serviceProvider.setFocalLength(focalLength, stateProvider);
    }

    public List<String> getDeviceFunctions(Current current){
        return serviceProvider.getDeviceFunctions();
    }

    public String getState(Current current){
        return stateProvider.getState();
    }
}
