public class ElonsToyCar {

    private static final int METERS_BY_DRIVE = 20;
    private static final int BATTERY_BY_DRIVE = 1;

    protected ElonsToyCar(){
        this.batteryLevel  = 100;
        this.drivenMetters = 0;
    }

    private int batteryLevel;
    private int drivenMetters;

    public static ElonsToyCar buy() {
        return new ElonsToyCar();
    }

    public String distanceDisplay() {
        return String.format( "Driven %d meters", this.drivenMetters );
    }

    public String batteryDisplay() {
        return String.format( "Battery %s",
                ( this.batteryLevel == 0 )?
                        "empty" :
                        String.format( "at %d%%", this.batteryLevel ) );
    }

    public void drive() {
        if( this.batteryLevel > 0 ) {
            this.drivenMetters += METERS_BY_DRIVE;
            this.batteryLevel -= BATTERY_BY_DRIVE;
        }
    }
}
