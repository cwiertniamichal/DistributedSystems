package server.robots;

import Laboratory.BadRobotStateException;
import Laboratory.ShovelHeightOutOfRangeException;

import java.util.ArrayList;
import java.util.List;

public class RobotWithShovelServiceProvider extends RobotServiceProvider{

    public void setShovelHeight(int height, RobotWithShovelStateProvider stateProvider) throws
    ShovelHeightOutOfRangeException, BadRobotStateException {
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Cannot perform action cause robot is turned off!");
        if(RobotWithShovelStateProvider.MAX_SHOVEL_HEIGHT < height ||
                height < 0)
            throw new ShovelHeightOutOfRangeException(0, RobotWithShovelStateProvider.MAX_SHOVEL_HEIGHT,
                     "Shovel height: " + height + "is out of range. See above values.");
        stateProvider.setShovelHeight(height);
        stateProvider.setOperationName("setShovelHeight");
    }

    @Override
    public List<String> getDeviceFunctions(){
        List<String> robotFunctions = super.getDeviceFunctions();
        List<String> robotWithShovelFunctions = new ArrayList<>(robotFunctions);
        robotWithShovelFunctions.add("setShovelHeight(int height)");
        return  robotWithShovelFunctions;
    }



}
