package server.robots;

import Laboratory.BadRobotStateException;
import Laboratory.Point;
import Laboratory.ShovelHeightOutOfRangeException;
import Laboratory._RobotWithShovelDisp;
import Ice.Current;

import java.util.List;

public class RobotWithShovelI extends _RobotWithShovelDisp {
    private RobotWithShovelStateProvider stateProvider;
    private RobotWithShovelServiceProvider serviceProvider;

    public RobotWithShovelI(){
        stateProvider = new RobotWithShovelStateProvider();
        serviceProvider = new RobotWithShovelServiceProvider();
    }

    public List<String> getDeviceFunctions(Current current){
        return serviceProvider.getDeviceFunctions();
    }

    public String getState(Current current){
        return  stateProvider.getState();
    }

    public void setShovelHeight(int height, Current current) throws
            ShovelHeightOutOfRangeException, BadRobotStateException {
        serviceProvider.setShovelHeight(height, stateProvider);
    }

    public void turnOn(Current current) throws BadRobotStateException{
        serviceProvider.turnOn(stateProvider);
    }

    public void turnOff(Current current) throws BadRobotStateException{
        serviceProvider.turnOff(stateProvider);
    }

    public void moveTo(Point point, Current current) throws BadRobotStateException{
        serviceProvider.moveTo(point, stateProvider);
    }

}
