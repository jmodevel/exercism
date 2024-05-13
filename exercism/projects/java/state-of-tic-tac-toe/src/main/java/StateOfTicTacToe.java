import java.util.Arrays;
import java.util.stream.IntStream;

class StateOfTicTacToe {

    private static final String X = "X";
    private static final String O = "O";

    public GameState determineState( String[] board ) {
        String[][] cells = Arrays.stream( board )
            .map( s -> s.chars().mapToObj( c -> Character.toString( (char) c ) ).toArray( String[]::new ) )
            .toArray( String[][]::new );
        int xs = count( X, cells );
        int os = count( O, cells );
        if( xs < os ){
            throw new IllegalArgumentException( "Wrong turn order: O started" );
        }
        if( xs > os+1 ){
            throw new IllegalArgumentException( "Wrong turn order: X went twice" );
        }
        boolean xWon = hasWon( X, cells );
        boolean oWon = hasWon( O, cells );
        if( xWon ){
            if( oWon ){
                throw new IllegalArgumentException( "Impossible board: game should have ended after the game was won" );
            }
            return GameState.WIN;
        } else if( oWon ){
            return GameState.WIN;
        } else if( xs + os == ( cells.length * cells.length ) ){
            return GameState.DRAW;
        }
        return GameState.ONGOING;
    }

    public int count( String element, String[][] cells ){
        return (int) Arrays.stream( cells ).flatMap( Arrays::stream ).filter( s->s.equals( element ) ).count();
    }

    public boolean hasWon( String element, String[][] cells ){
        return horizontalWin( element, cells ) ||
            verticalWin  ( element, cells ) ||
            diagonalWin  ( element, cells ) ;
    }

    public boolean horizontalWin( String element, String[][] cells ){
        return Arrays.stream( cells ).anyMatch( r -> Arrays.stream( r ).allMatch( e -> e.equals( element ) ) );
    }

    public boolean verticalWin( String element, String[][] cells ){
        return IntStream.range( 0, cells.length )
            .mapToObj( i -> IntStream.range( 0, cells[i].length ).mapToObj( j -> cells[j][i] ) )
            .anyMatch( c -> c.allMatch( e -> e.equals( element ) ) );
    }

    public boolean diagonalWin( String element, String[][] cells ){
        return IntStream.range( 0, cells.length ).mapToObj( i -> cells[i][i]                ).allMatch( e -> e.equals( element ) ) ||
            IntStream.range( 0, cells.length ).mapToObj( i -> cells[cells.length-1-i][i] ).allMatch( e -> e.equals( element ) );
    }

}
