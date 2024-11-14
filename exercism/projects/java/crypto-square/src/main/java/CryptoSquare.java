import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CryptoSquare {
    
    private final String text;
    
    CryptoSquare( String plaintext ) {
        this.text = plaintext.toLowerCase().replaceAll( "\\W", "" );
    }
    
    String getCiphertext() {
        if( this.text.isBlank() ){
            return this.text;
        }
        double sqrt = Math.sqrt( this.text.length() );
        int r = (int) Math.round( sqrt );
        int c = (int) Math.ceil ( sqrt );
        List<StringBuilder> columns = IntStream.range( 0, c ).mapToObj( StringBuilder::new ).toList();
        for( int i = 0; i < r*c; i++ ){
            columns.get( i % c ).append( (i < this.text.length() )? this.text.charAt( i ) : " " );
        }
        return columns.stream().map( StringBuilder::toString ).collect( Collectors.joining( " " ) );
    }
    
}