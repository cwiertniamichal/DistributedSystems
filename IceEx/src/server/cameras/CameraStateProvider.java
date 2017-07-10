package server.cameras;

import server.device.DeviceStateProvider;

public class CameraStateProvider extends DeviceStateProvider{
    private int zoom = 0;
    private float verticalAngle = 0;
    private float horizontalAngle = 0;
    public static final int MAX_ZOOM = 16;
    public static final int MAX_HORIZONTAL_ANGLE = 60;
    public static final int MAX_VERTICAL_ANGLE = 30;

    public float getHorizontalAngle() {
        return horizontalAngle;
    }

    public void setHorizontalAngle(float horizontalAngle) {
        this.horizontalAngle = horizontalAngle;
    }

    public float getVerticalAngle() {
        return verticalAngle;
    }

    public void setVerticalAngle(float verticalAngle) {
        this.verticalAngle = verticalAngle;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    @Override
    public String getState(){
        return super.getState() +
                "zoom: " + zoom + "\n" +
                "verticalAngle: " + verticalAngle + "\n" +
                "horizontalAngle: " + horizontalAngle + "\n";
    }
}
