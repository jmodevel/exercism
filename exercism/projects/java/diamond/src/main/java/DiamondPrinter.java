import java.util.ArrayList;
import java.util.List;

class DiamondPrinter {

    List<String> printToList(char a) {
        List<String> result = new ArrayList<>();
        int length = a-'A';
        for( int i = 0; i <= length; i++ ){
            int lineLength = 2*length + 1;
            String format = "%" + lineLength + "s";
            String spaces = String.format( format, "" );
            StringBuilder line = new StringBuilder( spaces );
            line.setCharAt( i, a );
            if( i != length ){
                line.setCharAt( lineLength-i-1, a );
            }
            result.add( line.toString() );
            if( i != 0 ){
                result.add( 0, line.toString() );
            }
            a--;
        }
        return result;
    }



}
