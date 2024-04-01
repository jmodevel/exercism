import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

class PrimeFactorsCalculator {

    List<Long> calculatePrimeFactorsOf( long number ) {
        List<Long> divisors = new ArrayList<>();
        long divisor = 2L;
        while( number > 1 ){
            if( number % divisor == 0 ){
                number /= divisor;
                divisors.add( divisor );
            } else {
                divisor = nextPrime( divisor, number );
            }
        }
        return divisors;
    }

    private long nextPrime( long seed, long max ){
        return LongStream.range( ++seed, max )
            .filter( this::isPrime )
            .findFirst()
            .orElse( max );
    }

    private boolean isPrime( long number ){
        for( int i = 2; i < Math.sqrt( number ); i++ ){
            if( number%i == 0 ){
                return false;
            }
        }
        return true;
    }

}