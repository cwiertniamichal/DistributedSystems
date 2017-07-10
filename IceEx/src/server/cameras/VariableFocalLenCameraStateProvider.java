package server.cameras;

public class VariableFocalLenCameraStateProvider extends CameraStateProvider {
    public float getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(float focalLength) {
        this.focalLength = focalLength;
    }

    private float focalLength = 5;
    public static final float MAX_FOCAL_LENGTH = 8.0f;
    public static final float MIN_FOCAL_LENGTH = 2.0f;

    @Override
    public String getState(){
        return super.getState() +
                "focalLength: " + focalLength + "\n";
    }
}
