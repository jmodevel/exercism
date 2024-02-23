import java.util.Arrays;

class Matrix {

    private int[][] result;

    Matrix(String matrixAsString) {
        String[] rows = matrixAsString.split( "\n" );
        for( int i = 0; i < rows.length; i++ ){
            String[] columns = rows[i].split( " " );
            if( result == null ){
                result = new int[ rows.length ][ columns.length ];
            }
            result[i] = Arrays.stream( columns ).mapToInt( Integer::valueOf ).toArray();
        }
    }

    int[] getRow(int rowNumber) {
        return result[ rowNumber-1 ];
    }

    int[] getColumn(int columnNumber) {
        return Arrays.stream( result )
                .mapToInt( row -> row[ columnNumber-1 ] )
                .toArray();
    }

}
