import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class RailFenceCipher {
    
    private final int rows;
    
    RailFenceCipher(int rows) {
        this.rows = rows;
    }
    
    String getEncryptedData(String message) {
        List<StringBuilder> rails = IntStream.range( 0, this.rows ).mapToObj( StringBuilder::new ).toList();
        int j = 0;
        boolean desc = true;
        for( int i = 0; i < message.length(); i++ ){
            rails.get(j).append( message.charAt( i ) );
            if ( j + 1 == this.rows ) {
                desc = false;
            } else if( j-1 < 0 ){
                desc = true;
            }
            j = (desc)? j+1 : j-1;
        }
        return rails.stream().collect( Collectors.joining() );
    }
    
    String getDecryptedData(String message) {
        Character[][] rails = new Character[this.rows][];
        IntStream.range( 0, this.rows ).forEach( i -> rails[i] = new Character[ message.length() ] );
        int j = 0;
        boolean desc = true;
        for( int i = 0; i < message.length(); i++ ){
            rails[j][i] = '?';
            if ( j + 1 == this.rows ) {
                desc = false;
            } else if( j-1 < 0 ){
                desc = true;
            }
            j = (desc)? j+1 : j-1;
        }
        int index = 0;
        for( int i = 0; i < rails.length; i++ ){
            for( int k = 0; k < rails[i].length; k++ ) {
                rails[ i ][ k ] = ( rails[ i ][ k ] != null )? message.charAt( index++ ) : ' ';
            }
        }
        return IntStream.range( 0, message.length() )
            .mapToObj(
                c -> IntStream.range( 0, this.rows )
                    .mapToObj( r -> Character.toString( rails[r][c] ).trim() )
                    .collect( Collectors.joining() )
            )
            .collect( Collectors.joining() );
    }
    
}
