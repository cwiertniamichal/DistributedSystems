package server.cameras;

public class NightCameraStateProvider extends CameraStateProvider {
    public boolean isNightModeOn() {
        return nightModeOn;
    }

    public void setNightModeOn(boolean nightModeOn) {
        this.nightModeOn = nightModeOn;
    }

    private boolean nightModeOn = false;

    @Override
    public String getState(){
        return super.getState() +
                "nightModeOn: " + nightModeOn + "\n";
    }
}
