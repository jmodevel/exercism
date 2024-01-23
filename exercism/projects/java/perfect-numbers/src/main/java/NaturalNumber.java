import java.util.stream.IntStream;

class NaturalNumber {

    private final int number;

    NaturalNumber(int number) {
        if( number <= 0 ){
            throw new IllegalArgumentException( "You must supply a natural number (positive integer)" );
        }
        this.number = number;
    }

    Classification getClassification() {
        int aliquotSum = IntStream
                .rangeClosed( 1, number/2 )
                .parallel()
                .filter( i -> number%i == 0 )
                .sum();
        if( aliquotSum == number ){
            return Classification.PERFECT;
        }
        return aliquotSum < number? Classification.DEFICIENT : Classification.ABUNDANT;
    }

}
