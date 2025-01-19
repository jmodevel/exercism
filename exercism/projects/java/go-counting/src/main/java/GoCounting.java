import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GoCounting {
    
    private static final String INVALID_COORDINATE = "Invalid coordinate";
    private static final char   BLACK_STONE = 'B';
    private static final char   WHITE_STONE = 'W';
    
    private enum Orientation {
        NORTH(  0, -1 ),
        EAST (  1,  0 ),
        SOUTH(  0,  1 ),
        WEST ( -1,  0 );
        protected int x;
        protected int y;
        Orientation( int x, int y ){
            this.x = x;
            this.y = y;
        }
    }
    
    private final char[][] board;
    
    private record OwnedTerritory( Player owner, Set<Point> territory ) {}
    
    GoCounting( String board ) {
        String[] rows = board.split( "\n" );
        this.board = new char[ rows.length ][];
        for( int i = 0; i < rows.length; i++ ){
            this.board[ i ] = rows[i].toCharArray();
        }
    }
    
    Player getTerritoryOwner(int x, int y) {
        if( isOutOfBoard( x, y ) ) throw new IllegalArgumentException( INVALID_COORDINATE );
        if( isStone     ( x, y ) ) return Player.NONE;
        Set<Point> territory = new HashSet<>( Set.of( new Point( x, y ) ) );
        OwnedTerritory t = getTerritory(new Point(x, y), territory, getOwner(new Point(x, y), null));
        return t != null? t.owner : null;
    }
    
    Set<Point> getTerritory( int x, int y ) {
        if( isOutOfBoard( x, y ) ) throw new IllegalArgumentException( INVALID_COORDINATE );
        if( isStone     ( x, y ) ) return new HashSet<>();
        Set<Point> territory = new HashSet<>( Set.of( new Point( x, y ) ) );
        OwnedTerritory t = getTerritory( new Point( x, y ), territory, getOwner( new Point( x, y ), null ) );
        return t != null? t.territory : null;
    }
    
    Map<Player, Set<Point>> getTerritories() {
        EnumMap<Player, Set<Point>> result = new EnumMap<>(Player.class);
        result.put( Player.BLACK, new HashSet<>() );
        result.put( Player.WHITE, new HashSet<>() );
        result.put( Player.NONE,  new HashSet<>() );
        List<Point> visited = new ArrayList<>();
        IntStream.range( 0, this.board.length ).forEach( i ->
            IntStream.range( 0, this.board[i].length ).forEach( j ->{
                Point current = new Point( j, i );
                if( this.board[ i ][ j ] == ' ' && !visited.contains( current ) ){
                    OwnedTerritory t = getTerritory(
                        current,
                        new HashSet<>( Set.of( current ) ),
                        getOwner( current, null )
                    );
                    result.get( t.owner ).addAll( t.territory );
                    visited.addAll( t.territory );
                }
            } )
        );
        return result;
    }
    
    private OwnedTerritory getTerritory( Point current, Set<Point> territory, Player owner ){
        Set<Point> neighbours = uncheckedNeighbours( getNeighbours( current ), territory );
        if( neighbours.isEmpty() ){
            return new OwnedTerritory( owner == null? Player.NONE : owner, territory );
        } else {
            OwnedTerritory t = null;
            for( Point neighbour : neighbours ){
                Set<Point> territoryCopy = new HashSet<>( Set.of( neighbour ) );
                territoryCopy.addAll( territory );
                t = getTerritory(neighbour, territoryCopy, getOwner(neighbour, owner));
            }
            return t;
        }
    }
    
    private Set<Point> getNeighbours( Point current ){
        return Arrays.stream( Orientation.values() )
            .map( o -> new Point( current.x + o.x, current.y + o.y ) )
            .filter( p -> !isOutOfBoard( p.x, p.y ) )
            .collect( Collectors.toSet() );
    }
    
    private Player getOwner( Point current, Player owner ){
        Player newOwner = owner;
        Set<Point> neighbours = getNeighbours( current );
        for( Point neighbour : neighbours ){
            if( isStone( neighbour.x, neighbour.y ) ) {
                newOwner = getStoneOwner( neighbour, newOwner );
            }
        }
        return newOwner;
    }

    private Player getStoneOwner(Point neighbour, Player newOwner) {
        if( newOwner == null ){
            newOwner = ( this.board[ neighbour.y ][ neighbour.x ] == BLACK_STONE )? Player.BLACK : Player.WHITE;
        } else {
            if( newOwner == Player.BLACK ) {
                newOwner = ( this.board[ neighbour.y ][ neighbour.x ] == BLACK_STONE )? Player.BLACK : Player.NONE;
            } else if( newOwner == Player.WHITE ) {
                newOwner = ( this.board[ neighbour.y ][ neighbour.x ] == WHITE_STONE )? Player.WHITE : Player.NONE;
            }
        }
        return newOwner;
    }

    private Set<Point> uncheckedNeighbours( Set<Point> neighbours, Set<Point> territory ){
        return neighbours.stream()
            .filter( n ->
                !isOutOfBoard( n.x, n.y )
                && !isStone  ( n.x, n.y )
                && territory.stream().noneMatch( p -> p.x == n.x && p.y == n.y  )
            )
            .collect( Collectors.toSet() );
    }
    
    private boolean isStone( int x, int y ){
        return this.board[ y ][ x ] == BLACK_STONE || this.board[ y ][ x ] == WHITE_STONE;
    }
    
    private boolean isOutOfBoard( int x, int y ){
        return y < 0 || y >= this.board.length || x < 0 || x >= this.board[y].length;
    }
    
}