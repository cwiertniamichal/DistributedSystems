package server.robots;

public class RobotWithArmStateProvider extends RobotStateProvider{
    private boolean isArmReleased = false;

    public boolean isItemHeld() {
        return isItemHeld;
    }

    public void setItemHeld(boolean itemHeld) {
        isItemHeld = itemHeld;
    }

    private boolean isItemHeld = false;

    public int getArmLength() {
        return armLength;
    }

    public void setArmLength(int armLength) {
        this.armLength = armLength;
    }

    private int armLength = 0;
    public static final int MAX_ARM_LENGTH = 500;

    public boolean isArmReleased() {
        return isArmReleased;
    }

    public void setArmReleased(boolean armReleased) {
        isArmReleased = armReleased;
    }

    @Override
    public String getState(){
        return super.getState() +
                "isArmReleased: " + isArmReleased + "\n" +
                "isItemHeld: " + isItemHeld + "\n" +
                "armLength: " + armLength + "\n";
    }

}
