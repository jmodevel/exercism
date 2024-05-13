import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AffineCipher {

    private static final int  ALPHABET_SIZE  = 26;
    private static final char ALPHA          = 'a';

    private final List<Integer> romanAlphabetFactors;

    public AffineCipher(){
        romanAlphabetFactors = getFactors( ALPHABET_SIZE );
    }

    public String encode( String text, int a, int b ){
        if( getFactors( a ).stream().anyMatch( romanAlphabetFactors::contains ) ){
            throw new IllegalArgumentException( "Error: keyA and alphabet size must be coprime." );
        }
        text = text.toLowerCase().replaceAll( "[^a-z0-9]", "" );
        return text.chars()
            .mapToObj( c -> encode( c, a, b ) )
            .collect( Collectors.joining() )
            .replaceAll( "(\\w{5})", "$1 " )
            .trim();
    }

    private String encode( int c, int a, int b ){
        return Character.toString( ( Character.isDigit( c ) )? c :
            (( (c-ALPHA) * a + b ) % ALPHABET_SIZE ) + ALPHA
        );
    }

    public String decode(String text, int a, int b){
        if( getFactors( a ).stream().anyMatch( romanAlphabetFactors::contains ) ){
            throw new IllegalArgumentException( "Error: keyA and alphabet size must be coprime." );
        }
        int mmi = mmi(a);
        text = text.toLowerCase().replaceAll( "\\s", "" );
        return text.chars()
            .mapToObj( c -> decode( c, mmi, b ) )
            .collect( Collectors.joining() );
    }

    private String decode( int c, int mmi, int b ){
        if( Character.isDigit( c ) ){
            return Character.toString( c );
        }
        int decoded = ( ( mmi * ( (c-ALPHA)-b )) % ALPHABET_SIZE );
        return Character.toString( decoded + ALPHA + ( ( decoded < 0 )? ALPHABET_SIZE : 0 ) );
    }

    private int mmi( int a ){
        int x = 1;
        while( a*x % ALPHABET_SIZE != 1 ){
            x++;
        }
        return x;
    }

    private List<Integer> getFactors( int limit ){
        List<Integer> factors = new ArrayList<>();
        int factor  = 2;
        int current = limit;
        while( current > 1 && factor <= limit ){
            if( current % factor == 0 ){
                current /= factor;
                factors.add( factor );
            } else {
                factor++;
            }
        }
        return factors;
    }

}