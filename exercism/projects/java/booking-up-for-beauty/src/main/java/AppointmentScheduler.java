import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class AppointmentScheduler {

    private static final String DATE_TIME_PATTERN   = "MM/dd/yyyy HH:mm:ss";
    private static final String DESCRIPTION_DATE_PATTERN = "EEEE, MMMM d, yyyy,";
    private static final String DESCRIPTION_TIME_PATTERN = "h:mm a";

    private static final LocalTime AFTERNOON_START = LocalTime.of( 12, 00 );
    private static final LocalTime AFTERNOON_END   = LocalTime.of( 18, 00 );
    private static final LocalDate ANNIVERSARY_DATE = LocalDate.of( 1970, Month.SEPTEMBER, 15 );

    public LocalDateTime schedule(String appointmentDateDescription) {
        return LocalDateTime.parse( appointmentDateDescription, DateTimeFormatter.ofPattern( DATE_TIME_PATTERN ) );
    }

    public boolean hasPassed(LocalDateTime appointmentDate) {
        return LocalDateTime.now().isAfter( appointmentDate );
    }

    public boolean isAfternoonAppointment(LocalDateTime appointmentDate) {
        LocalTime time = appointmentDate.toLocalTime();
        return ( time.equals( AFTERNOON_START ) || time.isAfter( AFTERNOON_START ) )
                && time.isBefore( AFTERNOON_END );
    }

    public String getDescription(LocalDateTime appointmentDate) {
        return String.format( "You have an appointment on %s at %s.",
                appointmentDate.format(
                        DateTimeFormatter.ofPattern( DESCRIPTION_DATE_PATTERN, Locale.ENGLISH ) ),
                appointmentDate.format(
                        DateTimeFormatter.ofPattern( DESCRIPTION_TIME_PATTERN, Locale.ENGLISH ) )
        );
    }

    public LocalDate getAnniversaryDate() {
        return LocalDate.of(
                LocalDate.now().getYear(),
                ANNIVERSARY_DATE.getMonth(),
                ANNIVERSARY_DATE.getDayOfMonth()
        );
    }

}
