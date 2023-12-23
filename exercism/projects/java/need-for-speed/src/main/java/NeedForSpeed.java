class NeedForSpeed {

    private static final int TOTAL_BATTERY = 100;

    private static final int NITRO_SPEED = 50;
    private static final int NITRO_BATTERY_DRAIN = 4;

    private int speed;
    private int batteryDrain;
    private int distance;
    private int batteryLeft;


    NeedForSpeed(int speed, int batteryDrain) {
        this.speed        = speed;
        this.batteryDrain = batteryDrain;
        this.distance     = 0;
        this.batteryLeft  = TOTAL_BATTERY;
    }

    public boolean batteryDrained() {
        return this.batteryLeft <= 0;
    }

    public int distanceDriven() {
        return this.distance;
    }

    public void drive() {
        if( !this.batteryDrained() ) {
            this.distance    += this.speed;
            this.batteryLeft -= this.batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed( NITRO_SPEED, NITRO_BATTERY_DRAIN );
    }
}

class RaceTrack {

    private int distance;

    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean tryFinishTrack(NeedForSpeed car) {
        do {
            car.drive();
        } while( this.distance >= car.distanceDriven() && !car.batteryDrained() );
        return this.distance <= car.distanceDriven();
    }

}
