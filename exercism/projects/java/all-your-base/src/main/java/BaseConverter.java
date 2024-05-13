import java.util.stream.IntStream;

class BaseConverter {

    private int base10;

    BaseConverter( int originalBase, int[] originalDigits ) {
        if( originalBase < 2 ){
            throw new IllegalArgumentException( "Bases must be at least 2." );
        }
        this.base10 = IntStream.range( 0, originalDigits.length )
            .map( i-> {
                if( originalDigits[i] >= originalBase ){
                    throw new IllegalArgumentException( "All digits must be strictly less than the base." );
                }
                if( originalDigits[i] < 0 ){
                    throw new IllegalArgumentException( "Digits may not be negative." );
                }
                return (int) Math.pow( originalBase, (double) originalDigits.length-1-i ) * originalDigits[i];
            } )
            .sum();
    }

    int[] convertToBase( int newBase ) {
        if( newBase < 2 ){
            throw new IllegalArgumentException( "Bases must be at least 2." );
        }
        int   pow    = maxPow( newBase );
        int[] result = new int[pow+1];
        int   left   = this.base10;
        while( pow >= 0 ){
            int currentPow = (int) Math.pow( newBase, pow );
            result[ result.length-1-pow ] = left / currentPow;
            left = left % currentPow;
            pow--;
        }
        return result;
    }

    private int maxPow( int base ){
        int pow = 0;
        while( this.base10 > Math.pow( base, (double) pow+1 ) ){
            pow++;
        }
        return pow;
    }

}
