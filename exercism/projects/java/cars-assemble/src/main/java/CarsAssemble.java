public class CarsAssemble {

    private static final int CARS_PER_HOUR = 221;
    private static final int SPEED_1_THRESHOLD = 1;
    private static final int SPEED_2_THRESHOLD = 5;
    private static final int SPEED_3_THRESHOLD = 9;
    private static final int SPEED_4_THRESHOLD = 10;

    private static final double SPEED_1_RATE = 1.0;
    private static final double SPEED_2_RATE = 0.9;
    private static final double SPEED_3_RATE = 0.8;
    private static final double SPEED_4_RATE = 0.77;
    private static final int MINUTES_PER_HOUR = 60;

    public double productionRatePerHour(int speed) {
        double rate = 0;
        if( speed >= SPEED_1_THRESHOLD && speed < SPEED_2_THRESHOLD ){
            rate = SPEED_1_RATE;
        } else if( speed >= SPEED_2_THRESHOLD && speed < SPEED_3_THRESHOLD ){
            rate = SPEED_2_RATE;
        } else if( speed >= SPEED_3_THRESHOLD && speed < SPEED_4_THRESHOLD ){
            rate = SPEED_3_RATE;
        } else if( speed >= SPEED_4_THRESHOLD ){
            rate = SPEED_4_RATE;
        }
        return rate * speed * CARS_PER_HOUR;
    }

    public int workingItemsPerMinute(int speed) {
        return (int) ( productionRatePerHour( speed ) / MINUTES_PER_HOUR );
    }

}
