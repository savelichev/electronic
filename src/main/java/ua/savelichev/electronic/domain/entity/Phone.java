package ua.savelichev.electronic.domain.entity;


public class Phone extends Product {

    private String displayDiagonal;

    private String operationSystem;

    private String mainCamera;

    private int batteryCapacity;

    public String getDisplayDiagonal() {
        return displayDiagonal;
    }

    public void setDisplayDiagonal(String displayDiagonal) {
        this.displayDiagonal = displayDiagonal;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(String mainCamera) {
        this.mainCamera = mainCamera;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "displayDiagonal='" + displayDiagonal + '\'' +
                ", operationSystem='" + operationSystem + '\'' +
                ", mainCamera='" + mainCamera + '\'' +
                ", batteryCapacity=" + batteryCapacity +
                "} " + super.toString();
    }
}
