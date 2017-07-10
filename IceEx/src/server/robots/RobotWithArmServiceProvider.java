package server.robots;

import Laboratory.ArmLengthOutOfRangeException;
import Laboratory.BadArmStateException;
import Laboratory.BadRobotStateException;

import java.util.ArrayList;
import java.util.List;

public class RobotWithArmServiceProvider extends RobotServiceProvider {

    public void releaseArm(int distance, RobotWithArmStateProvider stateProvider)
    throws ArmLengthOutOfRangeException, BadRobotStateException{
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Cannot perform action cause robot is turned off!");
        if(distance < 0 || distance > RobotWithArmStateProvider.MAX_ARM_LENGTH)
            throw new ArmLengthOutOfRangeException(0, RobotWithArmStateProvider.MAX_ARM_LENGTH,
                    "Arm length: " + distance + "is out of range. See above values.");
        stateProvider.setArmLength(distance);
        stateProvider.setArmReleased(true);
        stateProvider.setOperationName("releaseArm");
    }

    public void pocketArm(RobotWithArmStateProvider stateProvider) throws
            BadArmStateException, BadRobotStateException{
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Cannot perform action cause robot is turned off!");
        if(!stateProvider.isArmReleased())
            throw new BadArmStateException("Arm is already tucked");
        stateProvider.setArmLength(0);
        stateProvider.setArmReleased(false);
        stateProvider.setOperationName("pocketArm");
    }

    public void grabItem(int power, RobotWithArmStateProvider stateProvider) throws
            BadArmStateException, BadRobotStateException{
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Cannot perform action cause robot is turned off!");
        if(stateProvider.isItemHeld())
            throw new BadArmStateException("Already holding some item!");
        if(!stateProvider.isArmReleased())
            throw new BadArmStateException("Arm is tucked. Cannot grab any item!");
        stateProvider.setItemHeld(true);
        stateProvider.setOperationName("grabItem");
    }

    public void dropItem(RobotWithArmStateProvider stateProvider) throws BadArmStateException,
    BadRobotStateException{
        if(!stateProvider.getTurnedOn())
            throw new BadRobotStateException("Cannot perform action cause robot is turned off!");
        if(!stateProvider.isItemHeld())
            throw new BadArmStateException("Not holding any item now!");
        stateProvider.setItemHeld(false);
        stateProvider.setOperationName("dropItem");
    }

    @Override
    public List<String> getDeviceFunctions(){
        List<String> robotFunctions = super.getDeviceFunctions();
        List<String> robotWithArmFunctions = new ArrayList<>(robotFunctions);
        robotWithArmFunctions.add("releaseArm(int distance)");
        robotWithArmFunctions.add("pocketArm()");
        robotWithArmFunctions.add("grabItem(int power)");
        robotWithArmFunctions.add("dropItem()");
        return robotWithArmFunctions;
    }
}
