class SpiralMatrixBuilder {

    enum Orientation {

        EAST (  0,  1 ),
        SOUTH(  1,  0 ),
        WEST (  0, -1 ),
        NORTH( -1,  0 );

        private final int iShift;
        private final int jShift;

        Orientation( int iShift, int jShift ){
            this.iShift = iShift;
            this.jShift = jShift;
        }
    }

    private Orientation orientation = Orientation.EAST;

    int[][] buildMatrixOfSize( int size ) {
        int[][] ret = new int[size][size];
        int i = 0;
        int j = 0;
        for( int e = 1; e <= size*size; e++ ){
            ret[i][j] = e;
            if( orientationChange( ret, i, j ) ){
                orientation = Orientation.values()[ (orientation.ordinal()+1) % Orientation.values().length ];
            }
            i += orientation.iShift;
            j += orientation.jShift;
        }
        return ret;
    }

    private boolean orientationChange( int[][] ret, int i, int j ){
        return ( i + this.orientation.iShift ) < 0 || ( i + this.orientation.iShift ) >= ret.length ||
               ( j + this.orientation.jShift ) < 0 || ( j + this.orientation.jShift ) >= ret.length ||
               ret[ i + this.orientation.iShift ][ j + this.orientation.jShift ] != 0;
    }

}