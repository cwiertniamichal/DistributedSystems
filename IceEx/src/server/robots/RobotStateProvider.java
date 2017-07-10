package server.robots;

import Laboratory.Point;
import server.device.DeviceStateProvider;

public class RobotStateProvider extends DeviceStateProvider{
    private boolean turnedOn = false;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    private Point position = new Point(0, 0);

    public boolean getTurnedOn() {
        return turnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        this.turnedOn = turnedOn;
    }

    @Override
    public String getState(){
        return super.getState() +
                "turnedOn: " + turnedOn + "\n" +
                "position: x = " + position.x + " y = " + position.y + "\n";
    }
}
