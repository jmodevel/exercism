class Clock {
    
    private static final String FORMAT = "%02d:%02d";
    private static final int MINUTES_HOUR = 60;
    private static final int HOURS_DAY    = 24;
    private static final int MINUTES_DAY  = HOURS_DAY * MINUTES_HOUR;
    
    private int hours;
    private int minutes;
    
    Clock( int hours, int minutes ) {
        int total = getTotalMinutes( hours, minutes, 0 );
        this.hours   = total / MINUTES_HOUR;
        this.minutes = total % MINUTES_HOUR;
    }
    
    void add( int minutes ) {
        int total = getTotalMinutes( this.hours, this.minutes, minutes );
        this.hours   = total / MINUTES_HOUR;
        this.minutes = total % MINUTES_HOUR;
    }
    
    private int getTotalMinutes( int hours, int minutes, int additional ) {
        int total = (( hours * MINUTES_HOUR + minutes ) + additional ) % MINUTES_DAY ;
        total += (total < 0)? MINUTES_DAY : 0;
        return total;
    }
    
    @Override
    public String toString() {
        return String.format( FORMAT, this.hours, this.minutes );
    }
    
    @Override
    public boolean equals(Object obj) {
        if( !Clock.class.equals( obj.getClass() ) ){
            return false;
        }
        return ((Clock) obj).hours == this.hours && ((Clock) obj).minutes == this.minutes;
    }
    
}