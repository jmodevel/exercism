import java.time.DayOfWeek;
import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.*;

class Meetup {
    
    private static final int FIRST_TEENTH_DAY = 13;
    
    private final int monthOfYear;
    private final int year;
    
    Meetup( int monthOfYear, int year ) {
        this.monthOfYear = monthOfYear;
        this.year        = year;
    }
    
    LocalDate day( DayOfWeek dayOfWeek, MeetupSchedule schedule ) {
    
        final LocalDate meetingDate = LocalDate.of(year, this.monthOfYear, FIRST_TEENTH_DAY );
    
        return switch (schedule) {
            case FIRST, SECOND, THIRD, FOURTH -> meetingDate.with( dayOfWeekInMonth( schedule.ordinal()+1, dayOfWeek) );
            case LAST -> meetingDate.with( lastInMonth( dayOfWeek ) );
            case TEENTH -> meetingDate.with( nextOrSame( dayOfWeek ) );
        };
    
    }
    
}