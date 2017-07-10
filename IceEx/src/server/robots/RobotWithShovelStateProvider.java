package server.robots;

public class RobotWithShovelStateProvider extends RobotStateProvider {
    public int getShovelHeight() {
        return shovelHeight;
    }

    public void setShovelHeight(int shovelHeight) {
        this.shovelHeight = shovelHeight;
    }

    private int shovelHeight = 0;
    public static final int MAX_SHOVEL_HEIGHT = 500;

    @Override
    public String getState(){
        return super.getState() +
                "shovelHeight: " + shovelHeight + "\n";
    }
}
