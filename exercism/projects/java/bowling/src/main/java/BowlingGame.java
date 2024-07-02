import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BowlingGame {

    private static final String NEGATIVE_ROLL     = "Negative roll is invalid";
    private static final String PIN_COUNT_EXCEEDS = "Pin count exceeds pins on the lane";
    private static final String GAME_OVER         = "Cannot roll after game is over";

    private static final int FRAMES     = 10;
    private static final int FRAME_PINS = 10;

    private final List<Integer> scores  = new ArrayList<>();
    private final List<Integer> indexes = new ArrayList<>();

    private int     frame  = 1;
    private int     thrown = 0;
    private boolean bonus  = false;

    void roll( int pins ) {

        if( pins < 0          ) throw new IllegalStateException( NEGATIVE_ROLL     );
        if( pins > FRAME_PINS ) throw new IllegalStateException( PIN_COUNT_EXCEEDS );
        if( frame > FRAMES    ) throw new IllegalStateException( GAME_OVER         );

        indexes.add( scores.size() );
        if( frame < FRAMES ){
            handleFrame( pins );
        } else {
            handleTenthFrame( pins );
        }
        scores.add( pins );
    }

    private void handleFrame( int pins ){
        if( thrown == 0 ){
            if( pins == FRAME_PINS ){
                indexes.addAll( Arrays.asList( scores.size()+1, scores.size()+2 ) );
                thrown = 0;
                frame++;
            } else {
                thrown++;
            }
        } else if( thrown == 1 ){
            int prev  = scores.get( scores.size()-1 );
            if( prev + pins >  FRAME_PINS ) throw new IllegalStateException( PIN_COUNT_EXCEEDS );
            if( prev + pins == FRAME_PINS ){
                indexes.add( scores.size() + 1 );
            }
            thrown = 0;
            frame++;
        }
    }

    private void handleTenthFrame( int pins ){
        if ( thrown == 0 ){
            if( pins == FRAME_PINS ) {
                bonus = true;
            }
        } else if( thrown == 1 ){
            int prev  = scores.get( scores.size()-1 );
            if( !bonus && prev + pins > FRAME_PINS ) throw new IllegalStateException( PIN_COUNT_EXCEEDS );
            if( prev + pins == FRAME_PINS ){
                bonus = true;
            }
        } else {
            handleTenthFrameBonusThrown( pins );
        }
        if( ( thrown == 1 && !bonus ) || ( thrown == 2 && bonus ) ){
            frame++;
        } else {
            thrown++;
        }
    }

    private void handleTenthFrameBonusThrown(int pins) {
        int prev = scores.get( scores.size()-1 );
        int ant  = scores.get( scores.size()-2 );
        if( prev + ant != FRAME_PINS && prev != FRAME_PINS && prev + pins > FRAME_PINS ){
            throw new IllegalStateException( PIN_COUNT_EXCEEDS );
        }
    }

    int score() {
        if( frame <= FRAMES ) throw new IllegalStateException( "Score cannot be taken until the end of the game" );
        return indexes.stream().filter( i -> i < scores.size() ).mapToInt( scores::get ).sum();
    }

}
