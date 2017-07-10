package server.robots;

import Laboratory.BadRobotStateException;
import Laboratory.Point;
import Laboratory._RobotDisp;
import Ice.Current;

import java.util.List;

public class RobotI extends _RobotDisp {
    private RobotStateProvider stateProvider;
    private RobotServiceProvider serviceProvider;

    public RobotI(){
        stateProvider = new RobotStateProvider();
        serviceProvider = new RobotServiceProvider();
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

    public String getState(Current current){
        return stateProvider.getState();
    }

    public List<String> getDeviceFunctions(Current current){
        return serviceProvider.getDeviceFunctions();
    }

}
