import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class WordCount {

    public Map<String, Integer> phrase( String input ) {
        input = input.toLowerCase().replaceAll( "[^\\w'\\s]", " " );
        input = input.toLowerCase().replaceAll( "^'|'$|\\s'|'\\s", " " );
        return Arrays.stream( input.split( "\\s+" ) )
            .filter( s->!s.isEmpty() )
            .collect(
                Collectors.groupingBy( Function.identity(),
                    Collectors.collectingAndThen(
                        Collectors.counting(),
                        Long::intValue
                    )
                )
            );
    }

}