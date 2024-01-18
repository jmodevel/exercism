import java.util.Arrays;
import java.util.stream.Collectors;

class ResistorColorDuo {

    enum Color {
        BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GREY, WHITE
    }

    private static final int COLORS_IN_DUO = 2;

    int value( String[] colors ) {
        return Integer.parseInt(
            Arrays.stream( colors )
                    .limit( COLORS_IN_DUO )
                    .map( c -> String.valueOf( Color.valueOf( c.toUpperCase() ).ordinal() ) )
                    .collect( Collectors.joining() )
        );
    }

}
