import java.util.ArrayList;
import java.util.List;

class VariableLengthQuantity {

    private static final int PACK_BYTES  = 7;
    private static final int TOTAL_BYTES = 8;

    List<String> encode(List<Long> numbers) {
        List<String> encoded = new ArrayList<>();
        for( Long l : numbers ){
            if( l == 0 ) {
                encoded.add( "0x0" );
            } else {
                encoded.addAll( extractEncoded ( vlqEncode( l ) ) );
            }
        }
        return encoded;
    }

    List<String> decode(List<Long> bytes) {
        List<String> decoded = new ArrayList<>();
        StringBuilder vlqEncoded = new StringBuilder();
        for( Long l : bytes ){
            StringBuilder binaryString = new StringBuilder( Long.toBinaryString(l) );
            setPadding( binaryString, TOTAL_BYTES, true );
            vlqEncoded.append( binaryString );
            if( binaryString.toString().startsWith( "0" ) ){
                decoded.add( vlqDecode( vlqEncoded ) );
                vlqEncoded = new StringBuilder();
            }
        }
        if( vlqEncoded.length() > 0 ){
            throw new IllegalArgumentException( "Invalid variable-length quantity encoding" );
        }
        return decoded;
    }

    private String vlqEncode( Long l ){
        String binary = Long.toBinaryString( l );
        StringBuilder reversed = new StringBuilder( binary ).reverse();
        setPadding( reversed, PACK_BYTES, false );
        StringBuilder vlqString = new StringBuilder();
        for( int i = 0; vlqString.length() < reversed.length(); i = i+PACK_BYTES){
            vlqString.append( reversed.substring( i, i+PACK_BYTES ) );
            vlqString.append( i==0? 0 : 1 );
        }
        return vlqString.reverse().toString();
    }

    private String vlqDecode( StringBuilder vlqEncoded ) {
        StringBuilder vlqString = new StringBuilder();
        for( int i = 0; i+PACK_BYTES < vlqEncoded.length(); i += TOTAL_BYTES ){
            vlqString.append( vlqEncoded.substring( i+1, i+TOTAL_BYTES ) );
        }
        return toHex( Long.parseLong( vlqString.toString(),2 ) );
    }

    private List<String> extractEncoded( String vlqEncoded ){
        List<String> encoded = new ArrayList<>();
        boolean padding = true;
        for( int i = 0; i < vlqEncoded.length(); i += TOTAL_BYTES ){
            long num = Long.parseLong( vlqEncoded.substring( i, i+TOTAL_BYTES ),2 );
            if( num != 0 ){
                padding = false;
            }
            if( !padding ) {
                encoded.add( toHex( num ) );
            }
        }
        return encoded;
    }

    private void setPadding( StringBuilder binary, int maxLength, boolean atStart ) {
        int bytesMod      = ( binary.length() % maxLength );
        int zerosToAppend = bytesMod == 0? 0 : maxLength - bytesMod;
        for( int i = 0; i < zerosToAppend; i++ ){
            binary.insert( (atStart)? 0 : binary.length(), 0 );
        }
    }

    private String toHex( long num ){
        return num != 0 ? String.format("0x%02x", num) : "0x0";
    }

}
