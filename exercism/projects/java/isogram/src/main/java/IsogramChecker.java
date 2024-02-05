import java.util.regex.Pattern;

class IsogramChecker {

    private static final String REPEATED_CHAR_PATTERN = "([a-z]).*\\1.*";

    boolean isIsogram(String phrase) {
        String clean = phrase.toLowerCase().replaceAll( "[ -]", "" );
        Pattern pattern = Pattern.compile( REPEATED_CHAR_PATTERN );
        return !pattern.matcher( clean ).find();
    }

}
