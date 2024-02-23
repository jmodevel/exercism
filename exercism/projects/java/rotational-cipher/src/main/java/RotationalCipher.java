import java.util.stream.Collectors;

class RotationalCipher {

    private static final String PLAIN = "abcdefghijklmnopqrstuvwxyz";

    private final String cipher;

    RotationalCipher( int shiftKey ) {
        shiftKey = shiftKey % PLAIN.length();
        this.cipher = PLAIN.substring( shiftKey ) + PLAIN.substring( 0, shiftKey );
    }

    String rotate( String data ) {
        return data.chars()
                .mapToObj( c -> String.valueOf( getCipheredChar( (char) c ) ) )
                .collect( Collectors.joining() );
    }

    private char getCipheredChar( char c ){
        if( !Character.isLetter( c ) ) {
            return c;
        }
        char ci = cipher.charAt( PLAIN.indexOf( Character.toLowerCase(c) ));
        return ( Character.isUpperCase( c ) )? Character.toUpperCase(ci) : ci;
    }

}
