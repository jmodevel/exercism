import java.util.stream.Collectors;
import java.util.stream.IntStream;

class TwelveDays {

    enum Gift {

        FIRST   ( "a Partridge in a Pear Tree."),
        SECOND  ( "two Turtle Doves" ),
        THIRD   ( "three French Hens" ),
        FOURTH  ( "four Calling Birds" ),
        FIFTH   ( "five Gold Rings" ),
        SIXTH   ( "six Geese-a-Laying" ),
        SEVENTH ( "seven Swans-a-Swimming" ),
        EIGHTH  ( "eight Maids-a-Milking" ),
        NINTH   ( "nine Ladies Dancing" ),
        TENTH   ( "ten Lords-a-Leaping" ),
        ELEVENTH( "eleven Pipers Piping" ),
        TWELFTH ( "twelve Drummers Drumming" );

        private final String content;

        Gift( String content ){
            this.content = content;
        }

    }

    private static final String VERSE = "On the %s day of Christmas my true love gave to me: ";

    String verse( int verseNumber ) {
        return IntStream.iterate( verseNumber, n -> n-1 )
                .limit( verseNumber )
                .mapToObj(
                        i ->
                                new StringBuilder()
                                        .append( ( i == 1 && verseNumber != 1 )? "and " : "" )
                                        .append( Gift.values()[i-1].content )
                )
                .collect( Collectors.joining(
                        ", ",
                        String.format( VERSE, Gift.values()[ verseNumber-1 ].name().toLowerCase() ),
                        "\n" ) );
    }

    String verses(int startVerse, int endVerse) {
        return IntStream.rangeClosed( startVerse, endVerse )
                .mapToObj( this::verse )
                .collect( Collectors.joining( "\n" ) );
    }

    String sing() {
        return this.verses( 1, 12 );
    }

}
