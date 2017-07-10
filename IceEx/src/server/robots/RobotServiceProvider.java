package server.robots;

import Laboratory.BadRobotStateException;
import Laboratory.Point;
import server.device.DeviceServiceProvider;
import server.device.DeviceStateProvider;

import java.util.ArrayList;
import java.util.List;

public class RobotServiceProvider extends DeviceServiceProvider {
    public void turnOn(RobotStateProvider stateProvider) throws BadRobotStateException{
        if(stateProvider.getTurnedOn())
            throw new BadRobotStateException("Robot is already turned on!");
        stateProvider.setTurnedOn(true);
        stateProvider.setOperationName("turnOn");
    }

    public void turnOff(RobotStateProvider stateProvider) throws BadRobotStateException{
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Robot is already turned off!");
        stateProvider.setTurnedOn(false);
        stateProvider.setOperationName("turnOff");
    }

    public void moveTo(Point point, RobotStateProvider stateProvider) throws BadRobotStateException{
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Cannot perform action cause robot is turned off!");
        stateProvider.setPosition(point);
        stateProvider.setOperationName("moveTo");
    }

    @Override
    public List<String> getDeviceFunctions(){
        List<String> deviceFunctions = super.getDeviceFunctions();
        List<String> robotFunctions = new ArrayList<>(deviceFunctions);
        robotFunctions.add("turnOn()");
        robotFunctions.add("turnOff()");
        robotFunctions.add("moveTo(Point point)");
        return robotFunctions;
    }

}
