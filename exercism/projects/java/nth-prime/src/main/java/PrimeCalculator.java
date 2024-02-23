class PrimeCalculator {

    int nth( int nth ){
        if( nth <= 0 ){
            throw new IllegalArgumentException();
        }
        int current      = 2;
        int currentPrime = current;
        int primesCount  = 0;
        while( primesCount < nth ){
            if( isPrime( current ) ) {
                currentPrime = current;
                primesCount++;
            }
            current++;
        }
        return currentPrime;
    }

    private boolean isPrime( int n ){
        if( n < 2 ){
            return false;
        }
        for( int div = 2; div <= (n/2); div++ ) {
            if( n % div == 0 ) {
                return false;
            }
        }
        return true;
    }

}
