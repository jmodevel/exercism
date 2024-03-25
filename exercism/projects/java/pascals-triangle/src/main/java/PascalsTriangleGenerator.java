class PascalsTriangleGenerator {

    int[][] generateTriangle(int rows) {
        int[][] result = new int[rows][];
        if( rows > 0 ){
            result[0] = new int[]{1};
            for( int i = 1; i < rows; i++ ){
                int[] prev = result [ i-1 ];
                int[] row  = new int[ prev.length+1 ];
                for( int j = 0; j <= prev.length; j++ ){
                    row[j] = ( (j == 0           )? 0 : prev[ j-1 ] ) +
                        ( (j == prev.length )? 0 : prev[ j   ] );
                }
                result[i] = row;
            }
        }
        return result;
    }

}