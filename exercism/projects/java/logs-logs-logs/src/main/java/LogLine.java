import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogLine {

    private static final String LOG_LINE_REGEX = "\\[([^]]*)]: (.*)";
    private String line;

    public LogLine( String line ) {
        this.line = line;
    }

    public LogLevel getLogLevel() {
        Pattern pattern = Pattern.compile( LOG_LINE_REGEX );
        Matcher matcher = pattern.matcher( this.line );
        return ( matcher.matches() )?
                LogLevel.fromLevel( matcher.group(1) ) : LogLevel.UNKNOWN;
    }

    public String getOutputForShortLog() {
        Pattern pattern = Pattern.compile( LOG_LINE_REGEX );
        Matcher matcher = pattern.matcher( this.line );
        return ( matcher.matches() )?
                String.format( "%d:%s",
                        LogLevel.fromLevel( matcher.group(1) ).getCode(),
                        matcher.group(2) ) :
                this.line;
    }

}
