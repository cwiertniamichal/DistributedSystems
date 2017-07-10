module Laboratory {
    ["java:type:java.util.ArrayList<String>"]
    sequence<string> StringList;

    interface Device{
        StringList getDeviceFunctions();
        string getState();
    };

    interface Reporter{
        void report(string message);
    };

    exception DeviceNotExistException{
        string info;
    };

    exception DeviceAlreadyControlledException{
        string info;
    };

    exception DeviceNotControlledException{
        string info;
    };

    interface Lab{
        StringList getDevices();
        Device* takeControlOverDevice(string device, string username)
            throws DeviceNotExistException, DeviceAlreadyControlledException;
        void releaseDevice(string device, string username)
            throws DeviceNotExistException, DeviceNotControlledException;
        StringList getAvailableFunctionsForDevice(string device) throws DeviceNotExistException;
    };

    exception RotationOutOfRangeException{
        float minAngle;
        float maxAngle;
        float currentAngle;
        string message;
    };

    exception ZoomOutOfRangeException{
        int minZoom;
        int maxZoom;
        string message;
    };

    exception FocalLengthOutOfRangeException{
        float minFocalLength;
        float maxFocalLength;
        float current;
        string message;
    };

	interface Camera extends Device{
		void setZoom(int zoom) throws ZoomOutOfRangeException;
		void rotateLeft(float angle) throws RotationOutOfRangeException;
		void rotateRight(float angle) throws RotationOutOfRangeException;
		void rotateTop(float angle) throws RotationOutOfRangeException;
		void rotateDown(float angle) throws RotationOutOfRangeException;
	};

	interface NightCamera extends Camera{
	    void switchNightModeOption();
	};

    interface VariableFocalLenCamera extends Camera{
        void increaseFocalLength(float focalLength) throws FocalLengthOutOfRangeException;
        void decreaseFocalLength(float focalLength) throws FocalLengthOutOfRangeException;
        void setFocalLength(float focalLength) throws FocalLengthOutOfRangeException;
    };

    exception BadRobotStateException{
        string reason;
    };

    struct Point{
        int x;
        int y;
    };

    interface Robot extends Device{
        void turnOn() throws BadRobotStateException;
        void turnOff() throws BadRobotStateException;
        void moveTo(Point point) throws BadRobotStateException;
    };

    exception ArmLengthOutOfRangeException{
        int minLength;
        int maxLength;
        string message;
    };

    exception BadArmStateException{
        string message;
    };

    interface RobotWithArm extends Robot{
        void releaseArm(int distance) throws ArmLengthOutOfRangeException, BadRobotStateException;
        void pocketArm() throws BadArmStateException, BadRobotStateException;
        void grabItem(int power) throws BadArmStateException, BadRobotStateException;
        void dropItem() throws BadArmStateException, BadRobotStateException;
    };

    exception ShovelHeightOutOfRangeException{
        int minShovelHeight;
        int maxShovelHeight;
        string message;
    };

    interface RobotWithShovel extends Robot{
        void setShovelHeight(int height) throws ShovelHeightOutOfRangeException, BadRobotStateException;
    };

};