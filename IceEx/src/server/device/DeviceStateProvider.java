package server.device;

public class DeviceStateProvider {
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    private String operationName;

    public String getState(){
        return "Operation: " + operationName + " performed.\n";
    }
}
