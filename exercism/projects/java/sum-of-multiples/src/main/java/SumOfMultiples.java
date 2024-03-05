import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

class SumOfMultiples {

    private final int   limit;
    private final int[] set;

    SumOfMultiples( int number, int[] set ) {
        this.limit = number;
        this.set   = set;
    }

    int getSum() {
        return Arrays.stream( this.set )
                .mapToObj( this::getAllMultiples )
                .flatMap( Set::stream )
                .distinct()
                .reduce( 0, Integer::sum );
    }

    private Set<Integer> getAllMultiples( int base ){
        if( base == 0 ){
            return Collections.emptySet();
        }
        Set<Integer> multiples = new TreeSet<>();
        int n = 1;
        int multiple = base;
        while( multiple < this.limit ){
            multiples.add( multiple );
            multiple = base * ++n;
        }
        return multiples;
    }

}
