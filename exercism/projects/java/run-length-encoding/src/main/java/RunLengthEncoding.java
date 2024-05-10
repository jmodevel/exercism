import java.util.Arrays;
import java.util.stream.Collectors;

class RunLengthEncoding {

    private static final String REPEATED_CHARS_REGEX        = "(?<=(.))(?!\\1)";
    private static final String CHARS_WITH_LENGTH_REGEX     = "(?<=[a-zA-Z ])";
    private static final String SPLIT_CHAR_AND_LENGTH_REGEX = "(?=[a-zA-Z ])";

    String encode( String data ) {
        if( data.isEmpty() ){
            return "";
        }
        return Arrays.stream( data.split( REPEATED_CHARS_REGEX ) )
            .map( s -> (( s.length() > 1 )? s.length() : "") + Character.toString( s.charAt( 0 ) ) )
            .collect( Collectors.joining() );
    }

    String decode( String data ) {
        if( data.isEmpty() ){
            return "";
        }
        return Arrays.stream( data.split( CHARS_WITH_LENGTH_REGEX ) )
            .map( s -> {
                String[] parts = (s.length() == 1)? new String[]{ "", s } : s.split( SPLIT_CHAR_AND_LENGTH_REGEX );
                return parts[1].repeat( ( parts[0].isEmpty() )? 1 : Integer.valueOf( parts[0] ) );
            })
            .collect( Collectors.joining() );
    }

}