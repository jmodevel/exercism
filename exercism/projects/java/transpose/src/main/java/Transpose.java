
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Transpose {

    public String transpose(String toTranspose) {
        String[] sources = toTranspose.split( "\n" );
        if ( sources.length == 0 ) {
            return "";
        }
        int maxLengthLeft = maxLengthLeft( sources, 0 );
        int maxLength     = maxLengthLeft;
        for( int i = 0; i < sources.length; i++ ){
            int rightPad = maxLengthLeft - sources[i].length();
            String format = "%s%" + rightPad + "s";
            if     ( rightPad  > 0 ) sources[i]    = String.format( format, sources[i], " " );
            else if( rightPad == 0 ) maxLengthLeft = maxLengthLeft( sources, i+1 );
        }
        List<StringBuilder> targets = IntStream.range( 0, maxLength ).mapToObj( StringBuilder::new ).toList();
        IntStream.range( 0, sources.length ).forEach(
            i -> IntStream.range( 0, targets.size() ).forEach(
                j -> targets.get(j).append( ( j < sources[i].length() )? sources[i].charAt( j ) : "" )
            )
        );
        return targets.stream().collect( Collectors.joining( "\n" ) );
    }

    private int maxLengthLeft( String[] sources, int from ){
        return IntStream.range( from, sources.length )
            .mapToObj( i -> sources[i] )
            .map( String::length )
            .max( Integer::compareTo )
            .orElse( 0 );
    }

}