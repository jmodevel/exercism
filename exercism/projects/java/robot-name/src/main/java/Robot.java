import java.security.SecureRandom;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Robot {

    private static final Set<String>  registered = new TreeSet<>();
    private static final SecureRandom random     = new SecureRandom();

    private String name;

    public Robot(){
        reset();
    }

    String getName() {
        return this.name;
    }

    void reset() {
        if( this.name != null ) {
            registered.remove( this.name );
        }
        do {
            this.name = generateName();
        } while( !registered.add( this.name ) );
    }

    private String generateName(){
        return String.format( "%s%s",
            getRandomFragment( 'A', 'Z', 2 ),
            getRandomFragment( '0', '9', 3 ) );
    }

    private String getRandomFragment( int origin, int bound, int length ){
        return random.ints( length, origin, bound+1 )
            .mapToObj( i -> String.valueOf( (char) i ) )
            .collect( Collectors.joining() );
    }

}