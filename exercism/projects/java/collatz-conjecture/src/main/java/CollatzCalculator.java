class CollatzCalculator {

    int computeStepCount( int start ) {
        if( start <= 0 ){
            throw new IllegalArgumentException( "Only positive integers are allowed" );
        }
        return computeStepCount( start, 0 );
    }

    int computeStepCount( int start, int count ){
        if( start == 1 ){
            return count;
        }
        count++;
        if( start % 2 != 0 ){
            return computeStepCount( start*3 + 1, count );
        }
        return computeStepCount( start/2, count );
    }

}
