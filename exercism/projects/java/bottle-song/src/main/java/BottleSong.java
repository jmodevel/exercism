import java.util.stream.Collectors;
import java.util.stream.IntStream;

class BottleSong {

    private static final String[] NUMBER_NAMES = {
        "no", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
    };

    private static final String VERSE = """
            %s green bottle%s hanging on the wall,
            %s green bottle%s hanging on the wall,
            And if one green bottle should accidentally fall,
            There'll be %s green bottle%s hanging on the wall.
            """;

    String recite( int startBottles, int takeDown ) {

        if( startBottles < 0 || takeDown < 0 ) throw new IllegalArgumentException( "Bottles must be positive" );
        if( startBottles < takeDown )          throw new IllegalArgumentException( "Start bottles must be greater than take down" );
        if( startBottles > 10  )               throw new IllegalArgumentException( "Bottles must be under 10" );

        return IntStream.iterate( startBottles, i -> i - 1 )
            .limit( takeDown )
            .mapToObj( this::composeVerse )
            .collect( Collectors.joining( "\n" ) );

    }

    private String composeVerse( int i ){
        String number = NUMBER_NAMES[ i ].substring( 0, 1 ).toUpperCase() + NUMBER_NAMES[ i ].substring( 1 );
        String plural = i == 1 ? "" : "s";
        return String.format( VERSE, number, plural, number, plural, NUMBER_NAMES[i-1], i-1 == 1 ? "" : "s" );
    }

}