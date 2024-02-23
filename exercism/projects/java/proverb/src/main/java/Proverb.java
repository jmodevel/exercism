import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Proverb {

    public static final String RECITE_LINE = "For want of a %s the %s was lost.\n";
    public static final String RECITE_END  = "And all for the want of a %s.";

    private final String[] words;

    Proverb( String[] words ) {
        this.words = words;
    }

    String recite() {
        if( words.length == 0 ){
            return "";
        }
        if( words.length == 1 ){
            return String.format( RECITE_END, words[0] );
        }
        return IntStream.range( 0, words.length-1 )
                .mapToObj( i -> String.format( RECITE_LINE, words[i], words[i+1] ) )
                .collect(
                        Collectors.joining(
                                "", "", String.format( RECITE_END, words[0] )
                        )
                );
    }

}
