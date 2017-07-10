package server.cameras;

import server.cameras.CameraServiceProvider;

import java.util.ArrayList;
import java.util.List;

public class NightCameraServiceProvider extends CameraServiceProvider {

    public void switchNightModeOption(NightCameraStateProvider stateProvider){
        if(stateProvider.isNightModeOn())
            stateProvider.setNightModeOn(false);
        else
            stateProvider.setNightModeOn(true);
        stateProvider.setOperationName("switchNightModeOption");
    }

    @Override
    public List<String> getDeviceFunctions(){
        List<String> cameraFunctions = super.getDeviceFunctions();
        List<String> nightCameraFunctions = new ArrayList<>(cameraFunctions);
        nightCameraFunctions.add("switchNightModeOption()");
        return nightCameraFunctions;
    }
}
