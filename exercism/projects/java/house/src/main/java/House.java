import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class House {

    private static final List<String> ELEMENTS = Arrays.asList(
        "house that Jack built",
        "malt",
        "rat",
        "cat",
        "dog",
        "cow with the crumpled horn",
        "maiden all forlorn",
        "man all tattered and torn",
        "priest all shaven and shorn",
        "rooster that crowed in the morn",
        "farmer sowing his corn",
        "horse and the hound and the horn"
    );
    
    private static final List<String> VERBS = Arrays.asList(
        "lay in", "ate", "killed", "worried", "tossed", "milked", "kissed", "married", "woke", "kept", "belonged to"
    );
    
    private static final String FIRST_VERSE = "This is the %s";
    private static final String VERSE       = "that %s the %s";

    String verse(int verse) {
        return IntStream.iterate( verse, i -> i-1 )
            .limit( verse )
            .mapToObj( i -> i == verse?
                String.format( FIRST_VERSE, ELEMENTS.get( verse-1 ) ) :
                String.format( VERSE, VERBS.get( i-1 ), ELEMENTS.get( i-1 ) )
            )
            .collect( Collectors.joining( " " ) )
            .concat( "." );
    }
    
    String verses( int startVerse, int endVerse ) {
        return IntStream.rangeClosed( startVerse, endVerse )
            .mapToObj( this::verse )
            .collect( Collectors.joining( "\n" ) );
    }
    
    String sing() {
        return verses( 1, ELEMENTS.size() );
    }
    
}
