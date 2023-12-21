import java.util.ArrayList;
import java.util.List;

public class LanguageList {

    private static final String JAVA = "Java";
    private static final String KOTLIN = "Kotlin";

    private final List<String> languages = new ArrayList<>();

    public boolean isEmpty() {
        return languages.isEmpty();
    }

    public void addLanguage(String language) {
        if( !containsLanguage( language ) ){
            languages.add( language );
        }
    }

    public void removeLanguage(String language) {
        if( containsLanguage( language ) ){
            languages.remove( language );
        }
    }

    public String firstLanguage() {
        return ( !isEmpty() )? languages.get(0) : "";
    }

    public int count() {
        return languages.size();
    }

    public boolean containsLanguage(String language) {
        return languages.contains( language );
    }

    public boolean isExciting() {
        return languages.contains( JAVA ) ||  languages.contains( KOTLIN );
    }

}
