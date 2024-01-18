import java.util.Arrays;

class ResistorColorTrio {

    enum Color {
        BLACK, BROWN, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET, GREY, WHITE
    }

    enum MetricPrefix {

        GIGA  (9),
        MEGA  (6),
        KILO  (3),
        NONE  (0);

        private int factor;

        MetricPrefix( int factor ){
            this.factor = factor;
        }

    }

    private static final String SUFFIX        = "ohms";
    private static final int    COLORS_IN_DUO = 3;

    String label(String[] colors) {

        int first = fromColor( colors[0] );
        int last  = fromColor( colors[1] );
        int exp   = fromColor( colors[2] );

        int exponent = ( last == 0 )? exp+1 : exp;

        MetricPrefix closest = Arrays.stream( MetricPrefix.values() )
                .filter( p -> p.factor <= exponent )
                .findFirst().orElse( MetricPrefix.NONE );

        String prefix = ( closest == MetricPrefix.NONE )? "" : closest.name().toLowerCase();

        int pow    = exponent % (( closest == MetricPrefix.NONE )? COLORS_IN_DUO : closest.factor );
        int factor = ( last == 0 )? 1 : 10;
        int value  = ( first * factor + last ) * (int) Math.pow( 10, pow );

        return String.format( "%d %s%s", value, prefix, SUFFIX );

    }

    private int fromColor( String color ){
        return Color.valueOf( color.toUpperCase() ).ordinal();
    }

}
