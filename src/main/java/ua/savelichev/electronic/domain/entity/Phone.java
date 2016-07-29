package ua.savelichev.electronic.domain.entity;

/**
 * Class contains unique phone parameters
 */
public class Phone extends Product {

    /**
     * Phone's display diagonal size.
     */
    private String displayDiagonal;

    /**
     * Phone's operation system.
     */
    private String operationSystem;

    /**
     * Phone's main camera parameter.
     */
    private String mainCamera;

    /**
     * Phone's batteryCapacity.
     */
    private String batteryCapacity;


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

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
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
