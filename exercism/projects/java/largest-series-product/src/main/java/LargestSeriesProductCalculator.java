import java.util.stream.IntStream;

class LargestSeriesProductCalculator {

    private final String inputNumber;

    LargestSeriesProductCalculator( String inputNumber ) {
        if( inputNumber.chars().anyMatch( c-> !Character.isDigit( c ) ) ){
            throw new IllegalArgumentException( "String to search may only contain digits." );
        }
        this.inputNumber = inputNumber;
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if( numberOfDigits < 0 ){
            throw new IllegalArgumentException( "Series length must be non-negative." );
        }
        if( numberOfDigits > this.inputNumber.length() ){
            throw new IllegalArgumentException( "Series length must be less than or equal to the length of the string to search." );
        }
        return IntStream.rangeClosed( 0, (this.inputNumber.length()-numberOfDigits) )
                .mapToObj( i -> this.inputNumber.substring( i, i+numberOfDigits ) )
                .mapToLong( s -> s.chars()
                        .mapToLong( i-> (long) Character.getNumericValue( i ) )
                        .reduce( 1L, (p,m) -> p * m )
                ).max().orElse(0);
    }
}
