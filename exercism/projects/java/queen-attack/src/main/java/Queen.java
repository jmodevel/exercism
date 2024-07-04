class Queen {
    
    public static final int CHESS_TILES = 8;
    
    protected final int row;
    protected final int column;
    
    Queen( int row, int column ) {
        if( row    < 0 ) throw new IllegalArgumentException( "Queen position must have positive row." );
        if( column < 0 ) throw new IllegalArgumentException( "Queen position must have positive column." );
        if( row    >= CHESS_TILES ) throw new IllegalArgumentException( "Queen position must have row <= 7." );
        if( column >= CHESS_TILES ) throw new IllegalArgumentException( "Queen position must have column <= 7." );
        this.row    = row;
        this.column = column;
    }
    
}
