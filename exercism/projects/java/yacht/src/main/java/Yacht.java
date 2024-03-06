import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Yacht {

    private final Map<Integer, Long> frequencies;
    private final YachtCategory category;

    private static final int YACHT_SCORE    = 50;
    private static final int STRAIGHT_SCORE = 30;

    Yacht(int[] dice, YachtCategory yachtCategory) {
        this.frequencies = Arrays.stream( dice )
                .boxed()
                .collect( Collectors.groupingBy( Function.identity(), Collectors.counting() ) );
        this.category = yachtCategory;
    }

    int score() {
        return switch ( this.category ){
            case ONES            -> scoreByNumber( 1 );
            case TWOS            -> scoreByNumber( 2 );
            case THREES          -> scoreByNumber( 3 );
            case FOURS           -> scoreByNumber( 4 );
            case FIVES           -> scoreByNumber( 5 );
            case SIXES           -> scoreByNumber( 6 );
            case FULL_HOUSE      -> fullHouse();
            case FOUR_OF_A_KIND  -> fourOfAKind();
            case LITTLE_STRAIGHT -> straight( 6 );
            case BIG_STRAIGHT    -> straight( 1 );
            case CHOICE          -> diceTotal();
            case YACHT           -> yacht();
        };
    }

    private int scoreByNumber( int num ){
        return this.frequencies.getOrDefault( num, 0L ).intValue() * num;
    }

    private int fullHouse(){
        return this.frequencies.values().stream().anyMatch( v -> v == 3 ) &&
                this.frequencies.values().stream().anyMatch( v -> v == 2 ) ? diceTotal() : 0;
    }

    private int fourOfAKind(){
        return this.frequencies.entrySet().stream()
                .filter( e -> e.getValue() >= 4 )
                .mapToInt( e -> e.getKey()*4 )
                .findFirst()
                .orElse( 0 );
    }

    private int straight( int excluded ){
        return this.frequencies.keySet().stream().distinct().count() == 5 &&
                !this.frequencies.containsKey( excluded )? STRAIGHT_SCORE : 0;
    }

    private int yacht(){
        return this.frequencies.values().stream().anyMatch( v -> v == 5 )? YACHT_SCORE : 0;
    }

    private int diceTotal(){
        return this.frequencies.entrySet().stream()
                .mapToInt( e -> e.getKey() * e.getValue().intValue() )
                .sum();
    }

}