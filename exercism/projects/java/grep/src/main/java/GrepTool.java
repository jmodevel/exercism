import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GrepTool {

    private static final String LINE_NUMBER_APPEND     = "-n";
    private static final String CONTAINING_FILE_OUTPUT = "-l";
    private static final String CASE_INSENSITIVE       = "-i";
    private static final String NO_MATCHES             = "-v";
    private static final String ENTIRE_LINE_MATCH      = "-x";

    String grep( String pattern, List<String> flags, List<String> files ) {
        return files.stream()
            .map( f -> getMatchingLines( pattern, flags, f, files.size() > 1 ) )
            .flatMap( Collection::stream )
            .collect( Collectors.joining( "\n" ) );
    }

    private List<String> getMatchingLines( String pattern, List<String> flags, String f, boolean multiple ) {
        List<String> lines = readAllLines( f );
        if( flags.contains( CONTAINING_FILE_OUTPUT ) ){
            return lines.stream().anyMatch( matches( flags, pattern ) )?
                    List.of( f ) : List.of();
        }
        return IntStream.range( 0, lines.size() )
            .filter( i -> matches( flags, pattern ).test( lines.get( i ) ) )
            .mapToObj( i ->
                format(
                    multiple, f,
                    flags.contains( LINE_NUMBER_APPEND ), i+1,
                    lines.get( i )
                )
            )
            .toList();
    }

    private List<String> readAllLines( String f ){
        try {
            return Files.readAllLines( Path.of( f ) );
        } catch( IOException e ) {
            throw new IllegalArgumentException( "file not found: " + f );
        }
    }

    private Predicate<String> matches( List<String> flags, String p ){
        Predicate<String> result = null;
        if( flags.contains( ENTIRE_LINE_MATCH ) ){
            result = ( flags.contains( CASE_INSENSITIVE ))?
                ( s -> s.equalsIgnoreCase( p ) ) :
                ( s -> s.equals( p ) );
        } else {
            result = ( flags.contains( CASE_INSENSITIVE ) ) ?
                ( s -> s.toLowerCase().contains( p.toLowerCase() )) :
                ( s -> s.contains( p ) );
        }
        if( flags.contains( NO_MATCHES ) ){
            result = result.negate();
        }
        return result;
    }

    private String format( boolean multiple, String f,
                           boolean appendLine, int lineNumber,
                           String line ){
        return String.format( "%s%s%s",
            multiple ? f + ":" : "",
            appendLine? lineNumber + ":" : "",
            line );
    }

}