import java.util.Arrays;

public enum LogLevel {

    UNKNOWN ( 0, "" ),
    TRACE   ( 1, "TRC" ),
    DEBUG   ( 2, "DBG" ),
    INFO    ( 4, "INF" ),
    WARNING ( 5, "WRN" ),
    ERROR   ( 6, "ERR" ),
    FATAL   ( 42, "FTL" );

    private int code;
    private String level;

    LogLevel( int code, String level ){
        this.code  = code;
        this.level = level;
    }

    public int getCode() {
        return code;
    }

    public String getLevel() {
        return level;
    }

    public static LogLevel fromLevel( String level ){
        return Arrays.stream( LogLevel.values() )
                .filter( l -> l.getLevel().equals( level ) )
                .findFirst()
                .orElse( LogLevel.UNKNOWN );
    }

}
