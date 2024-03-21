import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class NucleotideCounter {

    private static final List<Character> NUCLEOTIDES = Arrays.asList( 'A', 'C', 'G', 'T' );

    private final Map<Character, Integer> frequencies;

    NucleotideCounter( String sequence ) {
        if( sequence.chars().anyMatch( c -> !NUCLEOTIDES.contains( (char) c ) ) ){
            throw new IllegalArgumentException();
        }
        this.frequencies = sequence.chars()
            .mapToObj( c -> (char) c )
            .collect( Collectors.groupingBy(
                Function.identity(),
                Collectors.collectingAndThen( Collectors.counting(), Long::intValue )
            ));
        NUCLEOTIDES.forEach( n -> this.frequencies.putIfAbsent( n, 0 ) );
    }

    Map<Character, Integer> nucleotideCounts() {
        return this.frequencies;
    }

}