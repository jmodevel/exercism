import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class IsbnVerifier {

    private static final int X_VALUE      = 10;
    private static final int ISBN_MODULUS = 11;

    boolean isValid( String stringToVerify ) {
        String clean = stringToVerify.replace("-", "" );
        Pattern pattern = Pattern.compile( "^\\d{9}[\\dX]$" );
        Matcher matcher = pattern.matcher( clean );
        if( !matcher.matches() ){
            return false;
        }
        return IntStream.range(0, clean.length())
                .map(i -> {
                    int factor = clean.charAt(i) == 'X' ?
                            X_VALUE :
                            Character.getNumericValue(clean.charAt(i));
                    return factor * (clean.length()-i);
                })
                .sum() % ISBN_MODULUS == 0;
    }

}
