import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ParallelLetterFrequency {
    
    private final List<String> texts;
    
    ParallelLetterFrequency( String[] texts ) {
        this.texts = Arrays.asList( texts );
    }
    
    Map<Character, Integer> countLetters() {
        return this.texts.parallelStream()
            .map( String::toLowerCase )
            .flatMap( s -> s.codePoints().boxed().parallel() )
            .filter( Character::isLetter )
            .collect( Collectors.groupingBy(
                i -> (char) i.intValue(),
                Collectors.collectingAndThen( Collectors.counting(), Long::intValue )
            ));
    }
    
}
