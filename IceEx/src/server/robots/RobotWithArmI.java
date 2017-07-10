package server.robots;

import Laboratory.*;
import Ice.Current;

import java.util.List;

public class RobotWithArmI extends _RobotWithArmDisp {
    private RobotWithArmStateProvider stateProvider;
    private RobotWithArmServiceProvider serviceProvider;

    public RobotWithArmI(){
        this.stateProvider = new RobotWithArmStateProvider();
        this.serviceProvider = new RobotWithArmServiceProvider();
    }

    public String getState(Current current){
        return stateProvider.getState();
    }

    public List<String> getDeviceFunctions(Current current){
        return serviceProvider.getDeviceFunctions();
    }

    public void releaseArm(int distance, Current current) throws ArmLengthOutOfRangeException, BadRobotStateException{
        serviceProvider.releaseArm(distance, stateProvider);
    }

    public void pocketArm(Current current) throws BadArmStateException, BadRobotStateException{
        serviceProvider.pocketArm(stateProvider);
    }

    public void grabItem(int power, Current current) throws BadArmStateException, BadRobotStateException{
        serviceProvider.grabItem(power, stateProvider);
    }

    public void dropItem(Current current) throws BadArmStateException, BadRobotStateException {
        serviceProvider.dropItem(stateProvider);
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
