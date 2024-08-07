class Robot {
    
    private static final char LEFT    = 'L';
    private static final char RIGHT   = 'R';
    private static final char ADVANCE = 'A';
    
    private GridPosition gridPosition;
    private Orientation  orientation;
    
    Robot(GridPosition initialPosition, Orientation initialOrientation) {
        this.gridPosition = initialPosition;
        this.orientation  = initialOrientation;
    }
    
    GridPosition getGridPosition() {
        return this.gridPosition;
    }
    
    Orientation getOrientation() {
        return this.orientation;
    }
    
    void advance() {
        this.gridPosition = switch ( this.orientation ){
            case NORTH -> new GridPosition( this.gridPosition.x,   this.gridPosition.y+1 );
            case EAST  -> new GridPosition( this.gridPosition.x+1, this.gridPosition.y   );
            case SOUTH -> new GridPosition( this.gridPosition.x,   this.gridPosition.y-1 );
            case WEST  -> new GridPosition( this.gridPosition.x-1, this.gridPosition.y   );
        };
    }
    
    void turnLeft() {
        int pos = this.orientation.ordinal() - 1 + Orientation.values().length;
        this.orientation = Orientation.values()[ pos % Orientation.values().length ];
    }
    
    void turnRight() {
        this.orientation = Orientation.values()[ (this.orientation.ordinal()+1) % Orientation.values().length ];
    }
    
    void simulate( String instructions ) {
        instructions.chars().forEach( i-> this.action( (char) i ) );
    }
    
    private void action( char c ){
        switch ( c ){
            case RIGHT -> turnRight();
            case LEFT -> turnLeft();
            case ADVANCE -> advance();
            default -> throw new IllegalArgumentException( "unknown instruction: " + c );
        }
    }
    
}