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

    private static final String SUFFIX         = "ohms";
    private static final int    DECIMAL_BASE   = 10;

    String label(String[] colors) {

        int first    = fromColor( colors[0] );
        int last     = fromColor( colors[1] );
        int exponent = fromColor( colors[2] );

        int value  = first * DECIMAL_BASE + last;

        if( value == 0 ){
            return String.join( " ", "0", SUFFIX );
        }
        int trailingZeros = exponent;
        if( last == 0 ){
            trailingZeros = exponent+1;
            value         = first;
        }
        MetricPrefix closest = getClosestMetricPrefix( trailingZeros );

        if( closest == MetricPrefix.NONE ){
            value *= (int) Math.pow( DECIMAL_BASE, trailingZeros );
            return String.join( " ", String.valueOf( value ), SUFFIX );
        }

        int pow = trailingZeros % closest.factor;
        value *= (int) Math.pow( DECIMAL_BASE, pow );

        return String.format( "%d %s%s", value, closest.name().toLowerCase(), SUFFIX );

    }

    private static MetricPrefix getClosestMetricPrefix(int trailingZeros) {
        return Arrays.stream( MetricPrefix.values() )
                .filter( p -> p.factor <= trailingZeros )
                .findFirst().orElse(MetricPrefix.NONE);
    }

    private int fromColor( String color ){
        return Color.valueOf( color.toUpperCase() ).ordinal();
    }

}
