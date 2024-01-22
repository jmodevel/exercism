import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {

    private String phrase;

    Acronym(String phrase) {
        this.phrase = phrase;
    }

    String get() {
        String clean = this.phrase
                .replace("-", " " )
                .replaceAll( "\\p{Punct}", "" );
        return Arrays.stream( clean.split(" ") )
                .filter( s -> !s.isEmpty() )
                .map( s -> s.substring(0,1).toUpperCase() )
                .collect(Collectors.joining() );
    }

}
