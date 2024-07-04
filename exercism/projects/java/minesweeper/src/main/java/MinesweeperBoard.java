import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MinesweeperBoard {
    
    private static final char MINE = '*';
    
    private final char[][] board;
    
    MinesweeperBoard( List<String> boardRows ) {
        this.board = new char[ boardRows.size() ][];
        IntStream.range( 0, boardRows.size() ).forEach( i -> this.board[i] = boardRows.get( i ).toCharArray() );
    }
    
    List<String> withNumbers() {
        return IntStream.range( 0, this.board.length )
            .mapToObj( i ->
                IntStream.range( 0, this.board[i].length )
                    .mapToObj( j -> String.valueOf( countMines( i, j ) ) )
                    .collect( Collectors.joining() )
            ).toList();
    }
    
    private char countMines( int i, int j ){
        if( this.board[i][j] == MINE ){
            return MINE;
        }
        int count = IntStream.rangeClosed( Math.max( 0, i-1 ), Math.min( i+1, this.board.length-1 ) )
            .map( row -> (int)
                IntStream.rangeClosed( Math.max( 0, j-1 ), Math.min( j+1, this.board[ row ].length-1 ) )
                    .filter( column -> ( row != i || column != j ) && this.board[ row ][ column ] == MINE )
                    .count()
            ).sum();
        return count > 0 ? Character.forDigit( count, 10 ) : ' ';
    }
    
}