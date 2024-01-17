import java.math.BigInteger;
import java.util.stream.IntStream;

class Grains {

    private static final int BOARD_CELLS = 64;
    private static final int FACTOR      = 2;

    BigInteger grainsOnSquare(final int square) {
        if ( square < 1 || square > BOARD_CELLS ) {
            throw new IllegalArgumentException( "square must be between 1 and 64" );
        }
        return BigInteger.valueOf( FACTOR ).pow( square-1 );
    }

    BigInteger grainsOnBoard() {
        return IntStream.range( 1, BOARD_CELLS+1 )
                .mapToObj( this::grainsOnSquare )
                .reduce( BigInteger.ZERO, BigInteger::add );
    }

}
