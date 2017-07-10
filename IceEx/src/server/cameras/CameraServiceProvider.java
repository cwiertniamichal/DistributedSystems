package server.cameras;

import Laboratory.*;
import server.device.DeviceServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class CameraServiceProvider extends DeviceServiceProvider {
    @Override
    public List<String> getDeviceFunctions(){
        List<String> deviceFunctions = super.getDeviceFunctions();
        List<String> cameraFunctions = new ArrayList<>(deviceFunctions);
        cameraFunctions.add("setZoom(int zoom)");
        cameraFunctions.add("rotateLeft(float angle)");
        cameraFunctions.add("rotateRight(float angle)");
        cameraFunctions.add("rotateTop(float angle)");
        cameraFunctions.add("rotateDown(float angle)");
        return cameraFunctions;
    }

    public void setZoom(int zoom, CameraStateProvider stateProvider) throws ZoomOutOfRangeException{
        if(zoom > CameraStateProvider.MAX_ZOOM || zoom < 0)
            throw new ZoomOutOfRangeException(0, CameraStateProvider.MAX_ZOOM,
                    "Zoom value: " + zoom + " is out of range. See above values.");
        stateProvider.setZoom(zoom);
        stateProvider.setOperationName("setZoom");
    }

    public void rotateLeft(float angle, CameraStateProvider stateProvider) throws  RotationOutOfRangeException{
        float horizontalAngle = stateProvider.getHorizontalAngle();
        if (Math.abs(horizontalAngle - angle) > CameraStateProvider.MAX_HORIZONTAL_ANGLE)
            throw new RotationOutOfRangeException(-CameraStateProvider.MAX_HORIZONTAL_ANGLE,
                    CameraStateProvider.MAX_HORIZONTAL_ANGLE,
                    horizontalAngle, "Rotation would" +
                    "set camera's angle to: " + (horizontalAngle - angle) + " which is out of range. See above values.");
        stateProvider.setHorizontalAngle(horizontalAngle - angle);
        stateProvider.setOperationName("rotateLeft");
    }

    public void rotateRight(float angle, CameraStateProvider stateProvider) throws RotationOutOfRangeException {
        float horizontalAngle = stateProvider.getHorizontalAngle();
        if (Math.abs(horizontalAngle + angle) > CameraStateProvider.MAX_HORIZONTAL_ANGLE)
            throw new RotationOutOfRangeException(-CameraStateProvider.MAX_HORIZONTAL_ANGLE,
                    CameraStateProvider.MAX_HORIZONTAL_ANGLE,
                    horizontalAngle, "Rotation would" +
                    "set camera's angle to: " + (horizontalAngle + angle) + " which is out of range. See above values.");
        stateProvider.setHorizontalAngle(horizontalAngle + angle);
        stateProvider.setOperationName("rotateRight");
    }

    public void rotateTop(float angle, CameraStateProvider stateProvider) throws RotationOutOfRangeException {
        float verticalAngle = stateProvider.getVerticalAngle();
        if (Math.abs(verticalAngle + angle) > CameraStateProvider.MAX_VERTICAL_ANGLE)
            throw new RotationOutOfRangeException(-CameraStateProvider.MAX_VERTICAL_ANGLE,
                    CameraStateProvider.MAX_VERTICAL_ANGLE,
                    verticalAngle, "Rotation would" +
                    "set camera's angle to: " + (verticalAngle + angle) + " which is out of range. See above values.");
        stateProvider.setVerticalAngle(verticalAngle + angle);
        stateProvider.setOperationName("rotateTop");
    }

    public void rotateDown(float angle, CameraStateProvider stateProvider) throws RotationOutOfRangeException {
        float verticalAngle = stateProvider.getVerticalAngle();
        if (Math.abs(verticalAngle - angle) > CameraStateProvider.MAX_VERTICAL_ANGLE)
            throw new RotationOutOfRangeException(-CameraStateProvider.MAX_VERTICAL_ANGLE,
                    CameraStateProvider.MAX_VERTICAL_ANGLE,
                    verticalAngle, "Rotation would" +
                    "set camera's angle to: " + (verticalAngle - angle) + " which is out of range. See above values.");
        stateProvider.setVerticalAngle(verticalAngle - angle);
        stateProvider.setOperationName("rotateDown");
    }
}
