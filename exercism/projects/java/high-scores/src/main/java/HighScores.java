import java.util.Collections;
import java.util.List;

class HighScores {

    private final List<Integer> scores;

    public HighScores(List<Integer> highScores) {
        this.scores = highScores;
    }

    List<Integer> scores() {
        return this.scores;
    }

    Integer latest() {
        return this.scores.get( this.scores.size()-1 );
    }

    Integer personalBest() {
        return this.scores.stream()
                .sorted( Collections.reverseOrder() )
                .reduce( 0, Integer::max );
    }

    List<Integer> personalTopThree() {
        return this.scores.stream()
                .sorted( Collections.reverseOrder() )
                .limit( 3 )
                .toList();
    }

}
