import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class PigLatinTranslator {

    private static final String SUFFIX          = "ay";
    private static final String VOWEL_REGEX     = "^(xr|yt|[aeiou])(\\w*)$";
    private static final String CONSONANT_REGEX = "^([^aeiouy]*qu|[^aeiouy]+|y[^aeiouy]*)(\\w*)$";

    public String translate( String word ){
        return Arrays.stream( word.split( " " ) )
            .map( w ->{
                if( w.matches( VOWEL_REGEX ) ){
                    return String.format( "%s%s", w, SUFFIX );
                }
                Matcher matcher = Pattern.compile( CONSONANT_REGEX ).matcher( w );
                if( matcher.matches() ){
                    return String.format( "%s%s%s", matcher.group(2), matcher.group(1), SUFFIX );
                }
                return w;
            })
            .collect( Collectors.joining( " " ) );
    }

}