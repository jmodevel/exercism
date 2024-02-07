import java.util.*;
import java.util.stream.IntStream;

public class KillerSudokuHelper {

    List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> excludes) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> excludeSet = new TreeSet<>( excludes );
        Set<Integer> includeSet = new TreeSet<>();
        combinationsInCage( cageSum, cageSize, excludeSet, includeSet, result );
        return result;
    }

    List<List<Integer>> combinationsInCage( Integer cageSum, Integer cageSize ) {
        return combinationsInCage( cageSum, cageSize, new ArrayList<>() );
    }

    private void combinationsInCage(
            Integer cageSum, Integer cageSize, Set<Integer> excludes, Set<Integer> includes, List<List<Integer>> hits
    ){
        if( cageSize == 0 && cageSum == 0 ){
            List<Integer> hit = includes.stream().toList();
            if( !hits.contains( hit ) ){
                hits.add( hit );
            }
        } else {
            List<Integer> left = IntStream.rangeClosed( 1, 9 )
                    .boxed()
                    .filter( i -> !excludes.contains(i) )
                    .toList();
            left.forEach( i ->{
                Set<Integer> excludesCopy = new TreeSet<>( excludes );
                excludesCopy.add( i );
                Set<Integer> includesCopy = new TreeSet<>( includes );
                includesCopy.add( i );
                if( cageSum - i >=0 && cageSize >= 1 ) {
                    combinationsInCage( cageSum - i, cageSize - 1, excludesCopy, includesCopy, hits );
                }
            });
        }
    }


}
