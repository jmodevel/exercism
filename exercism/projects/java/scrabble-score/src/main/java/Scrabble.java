import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class Scrabble {

    private static final Map<String, Integer> VALUES = new HashMap<>();
    static{
        VALUES.put( "A", 1 );
        VALUES.put( "E", 1 );
        VALUES.put( "I", 1 );
        VALUES.put( "O", 1 );
        VALUES.put( "U", 1 );
        VALUES.put( "L", 1 );
        VALUES.put( "N", 1 );
        VALUES.put( "R", 1 );
        VALUES.put( "S", 1 );
        VALUES.put( "T", 1 );
        VALUES.put( "D", 2 );
        VALUES.put( "G", 2 );
        VALUES.put( "B", 3 );
        VALUES.put( "C", 3 );
        VALUES.put( "M", 3 );
        VALUES.put( "P", 3 );
        VALUES.put( "F", 4 );
        VALUES.put( "H", 4 );
        VALUES.put( "V", 4 );
        VALUES.put( "W", 4 );
        VALUES.put( "Y", 4 );
        VALUES.put( "K", 5 );
        VALUES.put( "J", 5 );
        VALUES.put( "X", 8 );
        VALUES.put( "Q", 10 );
        VALUES.put( "Z", 10 );

    }

    private final String word;

    Scrabble(String word) {
        this.word = word;
    }

    int getScore() {
        if( this.word.isEmpty() ){
            return 0;
        }
        Map<String, Long> frequencies = Arrays.stream( this.word.split("") )
                .collect( Collectors.groupingBy( e -> e, Collectors.counting() ) );
        return frequencies.entrySet().stream()
                .mapToInt( e ->
                        Integer.parseInt( e.getValue().toString() ) *
                                VALUES.get( e.getKey().toUpperCase() )
                )
                .sum();
    }

}
