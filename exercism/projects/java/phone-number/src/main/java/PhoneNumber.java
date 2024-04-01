import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PhoneNumber {

    private static final String NANP_REGEX = "^(\\d?)(\\d{3})(\\d{3})(\\d{4})$";

    private final String number;

    PhoneNumber( String numberString ){
        String clean = clean( numberString );
        checkLength    ( clean );
        checkCharacters( clean );
        Pattern pattern = Pattern.compile( NANP_REGEX );
        Matcher matcher = pattern.matcher( clean );
        if( !matcher.matches() ) {
            this.number = clean;
        } else {
            checkCountry( matcher.group( 1 ) );
            checkCode   ( matcher.group( 2 ), "area code"     );
            checkCode   ( matcher.group( 3 ), "exchange code" );
            this.number = String.join( "", matcher.group( 2 ), matcher.group( 3 ), matcher.group( 4 ) );
        }
    }

    String getNumber() {
        return this.number;
    }

    private String clean( String numberString ){
        String clean = numberString.replaceAll( "[\\s()-\\.]", "" );
        return ( clean.startsWith( "+" ) )? clean.substring( 1 ) : clean;
    }

    private void checkLength( String number ){
        if( number.length() < 10 ){
            throw new IllegalArgumentException( "must not be fewer than 10 digits" );
        }
        if( number.length() > 11 ){
            throw new IllegalArgumentException( "must not be greater than 11 digits" );
        }
    }

    private void checkCharacters( String number ){
        if( number.chars().anyMatch( Character::isLetter ) ){
            throw new IllegalArgumentException( "letters not permitted" );
        }
        if( number.chars().anyMatch( c -> !Character.isDigit( c ) ) ){
            throw new IllegalArgumentException( "punctuations not permitted" );
        }
    }

    private void checkCountry( String countryCode ){
        if ( !countryCode.isEmpty() && !countryCode.equals( "1" ) ) {
            throw new IllegalArgumentException( "11 digits must start with 1" );
        }
    }

    private void checkCode( String code, String literal ){
        if ( code.matches( "[01]\\d{2}" ) ) {
            throw new IllegalArgumentException(
                String.format( "%s cannot start with %s", literal, code.charAt( 0 ) == '0'? "zero" : "one" )
            );
        }
    }

}