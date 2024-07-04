class QueenAttackCalculator {
    
    private final Queen queen1;
    private final Queen queen2;
    
    QueenAttackCalculator(Queen queen1, Queen queen2) {
        if( queen1 == null || queen2 == null ){
            throw new IllegalArgumentException( "You must supply valid positions for both Queens." );
        }
        if( queen1.row == queen2.row && queen1.column == queen2.column ){
            throw new IllegalArgumentException( "Queens cannot occupy the same position." );
        }
        this.queen1 = queen1;
        this.queen2 = queen2;
    }
    
    boolean canQueensAttackOneAnother() {
        return queen1.row    == queen2.row
            || queen1.column == queen2.column
            || Math.abs( queen1.row - queen2.row ) == Math.abs( queen1.column - queen2.column );
    }
    
}