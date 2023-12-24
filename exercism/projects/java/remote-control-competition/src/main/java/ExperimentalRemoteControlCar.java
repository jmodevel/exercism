public class ExperimentalRemoteControlCar implements RemoteControlCar {

    private static final int UNITS_BY_DRIVE = 20;

    private int distance = 0;

    public void drive() {
        this.distance += UNITS_BY_DRIVE;
    }

    public int getDistanceTravelled() {
        return this.distance;
    }

}
