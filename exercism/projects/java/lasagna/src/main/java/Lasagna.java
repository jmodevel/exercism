public class Lasagna {

    private static final int DEFAULT_MINUTES_IN_OVEN = 40;
    private static final int DEFAULT_TIME_BY_LAYER   = 2;

    private int minutesInOven;
    private int timeByLayer;


    public Lasagna(){
        this.minutesInOven = DEFAULT_MINUTES_IN_OVEN;
        this.timeByLayer   = DEFAULT_TIME_BY_LAYER;
    }

    public int expectedMinutesInOven() {
        return this.minutesInOven;
    }

    public int remainingMinutesInOven( int actualMinutesInOven ) {
        return ( actualMinutesInOven > this.minutesInOven )?
                0 :
                this.minutesInOven - actualMinutesInOven;
    }

    public int preparationTimeInMinutes( int layers ) {
        return ( layers > 0 )? this.timeByLayer * layers : 0;
    }

    public int totalTimeInMinutes( int layers, int actualMinutesInOven ) {
        return preparationTimeInMinutes( layers ) + actualMinutesInOven;
    }
}
