import java.util.stream.IntStream;

public class SquareRoot {

    public int squareRoot( int radicand ) {
        return IntStream.rangeClosed( 1, radicand )
            .filter( i -> i*i==radicand )
            .findFirst()
            .orElse( 0 );
    }

}