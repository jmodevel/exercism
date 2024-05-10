import java.util.List;
import java.util.stream.IntStream;

class Series {

    private final String source;

    Series( String source ) {
        if( source == null || source.trim().isEmpty() ){
            throw new IllegalArgumentException( "series cannot be empty" );
        }
        this.source = source;
    }

    List<String> slices( int num ) {
        if( num <= 0 ){
            throw new IllegalArgumentException( "slice length cannot be negative or zero" );
        }
        if( num > this.source.length() ){
            throw new IllegalArgumentException( "slice length cannot be greater than series length" );
        }
        return IntStream.rangeClosed( 0, source.length()-num )
            .mapToObj( i -> this.source.substring( i, i+num ) )
            .toList();
    }

}