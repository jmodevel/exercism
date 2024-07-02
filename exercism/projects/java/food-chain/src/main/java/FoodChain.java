import java.util.stream.Collectors;
import java.util.stream.IntStream;

class FoodChain {

    private static final String[] ANIMALS = {
        "fly", "spider", "bird", "cat", "dog", "goat", "cow", "horse" };
    private static final String[] VERSES  = {
        "",
        "It wriggled and jiggled and tickled inside her.\n",
        "How absurd to swallow a bird!\n",
        "Imagine that, to swallow a cat!\n",
        "What a hog, to swallow a dog!\n",
        "Just opened her throat and swallowed a goat!\n",
        "I don't know how she swallowed a cow!\n",
        "She's dead, of course!"
    };

    private static final String FIRST_VERSE = "I know an old lady who swallowed a %s.\n";
    private static final String LAST_VERSE  = "I don't know why she swallowed the fly. Perhaps she'll die.";
    private static final String CATCH_VERSE = "She swallowed the %s to catch the %s%s.\n";

    String verse( int verse ) {
        StringBuilder sb = new StringBuilder();
        sb.append( String.format( FIRST_VERSE, ANIMALS[ verse - 1 ] ) ).append( verse < 1 ? "" : VERSES[ verse - 1 ] );
        if( verse < ANIMALS.length ) {
            if ( verse > 1 ) {
                for ( int i = verse; i > 1; i-- ) {
                    sb.append(
                        String.format( CATCH_VERSE,
                            ANIMALS[ i - 1 ],
                            ANIMALS[ i - 2 ],
                            i == 3 ? " that" + VERSES[ 1 ].substring( "It".length(), VERSES[ 1 ].length() - 2 ) : ""
                        )
                    );
                }
            }
            sb.append( LAST_VERSE );
        }
        return sb.toString();
    }

    String verses( int startVerse, int endVerse ) {
        return IntStream.rangeClosed( startVerse, endVerse )
            .mapToObj( this::verse )
            .collect( Collectors.joining( "\n\n" ) );
    }

}