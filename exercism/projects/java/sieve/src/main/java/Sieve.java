import java.util.*;
import java.util.stream.IntStream;

class Sieve {

    public static final int FIRST_PRIME = 2;

    private final int maxPrime;
    private final Set<Integer> marked;

    Sieve(int maxPrime) {
        this.maxPrime = maxPrime;
        this.marked   = new TreeSet<>();
    }

    List<Integer> getPrimes() {
        if( this.maxPrime < FIRST_PRIME ){
            return Collections.emptyList();
        }
        List<Integer> primes = new ArrayList<>();
        int n = FIRST_PRIME;
        while( n <= this.maxPrime ){
            primes.add( n );
            markAllMultiples( n );
            n = firstUnmarked( n );
        }
        return primes;
    }

    private int firstUnmarked( int current ){
        return IntStream.iterate( current, n -> n+1 )
                .limit( this.maxPrime )
                .filter( n -> !this.marked.contains(n) )
                .findFirst()
                .orElse(this.maxPrime + 1 );
    }

    private void markAllMultiples( int current ){
        int index = 1;
        int value = current;
        while( value <= maxPrime ){
            this.marked.add( value );
            value = current * index++;
        }
    }

}
