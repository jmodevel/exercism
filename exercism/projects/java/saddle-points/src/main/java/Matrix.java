import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Matrix {

    private final int   rows;
    private final int   cols;
    private final int[] rowsMax;
    private final int[] colsMin;

    Matrix( List<List<Integer>> values ) {
        this.rows = values.size();
        this.cols = !values.isEmpty() ? values.get(0).size() : 0;
        this.rowsMax = values.stream()
                .mapToInt( r ->
                        r.stream()
                                .mapToInt(Integer::intValue)
                                .max().orElse(-1)
                )
                .toArray();
        this.colsMin = IntStream.range( 0, cols )
                .map( i -> IntStream.range( 0, rows )
                        .map( j -> values.get(j).get(i) )
                        .min().orElse(-1)
                )
                .toArray();
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        return IntStream.range( 0, rows )
                .boxed()
                .flatMap( i ->
                        IntStream.range( 0, cols )
                                .filter( j -> rowsMax[i] == colsMin[j] )
                                .mapToObj( j-> new MatrixCoordinate( i+1, j+1 ) )
                )
                .collect( Collectors.toSet() );
    }

}
