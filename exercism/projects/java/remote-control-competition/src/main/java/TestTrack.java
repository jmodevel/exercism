import java.util.List;

public class TestTrack {

    private TestTrack() {
        throw new IllegalStateException("Utility class");
    }

    public static void race(RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> cars) {
        return cars.stream()
                .sorted()
                .toList();
    }

}
