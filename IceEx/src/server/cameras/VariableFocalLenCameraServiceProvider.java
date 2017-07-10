package server.cameras;

import Laboratory.FocalLengthOutOfRangeException;

import java.util.ArrayList;
import java.util.List;

class VariableFocalLenCameraServiceProvider extends CameraServiceProvider {

    public void increaseFocalLength(float focalLength, VariableFocalLenCameraStateProvider stateProvider)
    throws FocalLengthOutOfRangeException{
        float actualFocalLength = stateProvider.getFocalLength();
        if(actualFocalLength + focalLength > VariableFocalLenCameraStateProvider.MAX_FOCAL_LENGTH)
            throw new FocalLengthOutOfRangeException(VariableFocalLenCameraStateProvider.MIN_FOCAL_LENGTH,
                    VariableFocalLenCameraStateProvider.MAX_FOCAL_LENGTH, actualFocalLength,
                    "New focal length: " + (actualFocalLength + focalLength) + " would be out of range. See above values.");
        stateProvider.setFocalLength(actualFocalLength + focalLength);
        stateProvider.setOperationName("increaseFocalLength");
    }

    public void decreaseFocalLength(float focalLength, VariableFocalLenCameraStateProvider stateProvider)
    throws FocalLengthOutOfRangeException{
        float actualFocalLength = stateProvider.getFocalLength();
        if(actualFocalLength - focalLength < VariableFocalLenCameraStateProvider.MIN_FOCAL_LENGTH)
            throw new FocalLengthOutOfRangeException(VariableFocalLenCameraStateProvider.MIN_FOCAL_LENGTH,
                    VariableFocalLenCameraStateProvider.MAX_FOCAL_LENGTH, actualFocalLength,
                    "New focal length: " + (actualFocalLength - focalLength) + " would be " +
                    "out of range. See above values.");
        stateProvider.setFocalLength(actualFocalLength - focalLength);
        stateProvider.setOperationName("decreaseFocalLength");
    }

    public void setFocalLength(float focalLength, VariableFocalLenCameraStateProvider stateProvider)
    throws FocalLengthOutOfRangeException{
        if(focalLength < VariableFocalLenCameraStateProvider.MIN_FOCAL_LENGTH ||
                focalLength > VariableFocalLenCameraStateProvider.MAX_FOCAL_LENGTH)
            throw new FocalLengthOutOfRangeException(VariableFocalLenCameraStateProvider.MIN_FOCAL_LENGTH,
                    VariableFocalLenCameraStateProvider.MAX_FOCAL_LENGTH, stateProvider.getFocalLength(),
                    "New focal length: " + focalLength + " would be out of range. See above values.");
        stateProvider.setFocalLength(focalLength);
        stateProvider.setOperationName("setFocalLength");
    }

    @Override
    public List<String> getDeviceFunctions(){
        List<String> cameraFunctions = super.getDeviceFunctions();
        List<String> variableFocalLenCameraFunctions = new ArrayList<>(cameraFunctions);
        variableFocalLenCameraFunctions.add("increaseFocalLength(float focalLength)");
        variableFocalLenCameraFunctions.add("decreaseFocalLength(float focalLength)");
        variableFocalLenCameraFunctions.add("setFocalLength(float focalLength)");
        return variableFocalLenCameraFunctions;
    }

}
