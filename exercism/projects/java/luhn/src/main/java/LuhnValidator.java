import java.util.stream.IntStream;

class LuhnValidator {

    private static final int DOUBLE_FACTOR    = 2;
    private static final int DOUBLE_THRESHOLD = 9;
    private static final int LUHN_MODULUS     = 10;

    boolean isValid( String candidate ) {
        final String stripped = candidate.replace( " ", "" );
        if( stripped.length() <= 1 ){
            return false;
        }
        if( !stripped.chars().allMatch( c -> Character.isDigit( (char) c) ) ){
            return false;
        }
        int parity = stripped.length() % 2;
        return IntStream.range( 0, stripped.length() )
                .map( i -> doubleTransform( i, parity, stripped ) )
                .sum() % LUHN_MODULUS == 0;
    }

    private int doubleTransform( int index, int parity, String candidate ){
        int digit = Character.getNumericValue( candidate.charAt( index ) );
        digit = ( index % 2 == parity )? digit * DOUBLE_FACTOR : digit;
        digit = ( digit > DOUBLE_THRESHOLD )? digit - DOUBLE_THRESHOLD : digit;
        return digit;
    }

}
