import java.util.stream.IntStream;

public class Hamming {

    private final String leftStrand;
    private final String rightStrand;

    public Hamming(String leftStrand, String rightStrand) {
        if( leftStrand.length() != rightStrand.length() ){
            throw new IllegalArgumentException( "strands must be of equal length" );
        }
        this.leftStrand  = leftStrand;
        this.rightStrand = rightStrand;
    }

    public int getHammingDistance() {
        return (int) IntStream.range( 0, leftStrand.length() )
                .filter( i -> leftStrand.charAt(i) != rightStrand.charAt(i) )
                .count();
    }

}
