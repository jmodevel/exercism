import java.util.List;
import java.util.stream.IntStream;

class GameOfLife {

    private static final int ALIVE = 1;
    private static final int DEAD  = 0;
    private static final List<Integer> DEAD_TO_ALIVE = List.of( 3 );
    private static final List<Integer> STAY_ALIVE    = List.of( 2, 3 );

    public int[][] tick(int[][] matrix){
        int[][] result = new int[ matrix.length ][ matrix.length > 0? matrix[0].length : 0 ];
        IntStream.range( 0, matrix.length ).forEach(
            i -> IntStream.range( 0, matrix[i].length ).forEach(
                j -> result[i][j] = determineState( matrix, i, j, getAliveNeighbours( matrix, i, j ) )
            )
        );
        return result;
    }

    private static int determineState( int[][] matrix, int i, int j, int count ) {
        if( matrix[ i ][ j ] == DEAD ) {
            return DEAD_TO_ALIVE.contains( count ) ? ALIVE : DEAD;
        } else {
            return STAY_ALIVE.contains( count ) ? ALIVE : DEAD;
        }
    }

    private static int getAliveNeighbours( int[][] matrix, int i, int j ) {
        return IntStream.rangeClosed( Math.max( i-1, 0 ), Math.min( i+1, matrix.length-1 ) )
            .map( k ->
                (int) IntStream.rangeClosed( Math.max( j-1, 0 ), Math.min( j+1, matrix[k].length-1 ) )
                    .filter( l -> ( i != k || j != l ) && matrix[k][l] == ALIVE )
                    .count()
            ).sum();
    }

}
