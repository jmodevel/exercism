import java.util.stream.Collectors;

class Atbash {

    private static final int ALPHABET_CHARACTERS = 26;
    private static final int GROUP_SIZE          = 5;

    String encode( String input ) {
        return input.toLowerCase().codePoints()
                .filter( Character::isLetterOrDigit )
                .map( this::cipher )
                .mapToObj( Character::toString )
                .collect( Collectors.joining() )
                .replaceAll( String.format ("(.{%d})", GROUP_SIZE ), "$1 ")
                .trim();
    }

    String decode(String input) {
        return input.replaceAll( "\\s", "" ).codePoints()
                .map( this::cipher )
                .mapToObj( Character::toString )
                .collect( Collectors.joining() );
    }

    private int cipher( int codepoint ){
        return ( Character.isLetter( codepoint ) )? 'a' + (ALPHABET_CHARACTERS-1) - codepoint + 'a' : codepoint;
    }

}
