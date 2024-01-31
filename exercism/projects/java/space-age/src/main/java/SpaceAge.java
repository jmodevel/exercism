import java.util.Arrays;

class SpaceAge {

    private static final double EARTH_YEAR_SECONDS = 31557600;

    enum Planet {

        MERCURY( 0.2408467 ),
        VENUS  ( 0.61519726 ),
        EARTH  ( 1.0 ),
        MARS   ( 1.8808158 ),
        JUPITER( 11.862615 ),
        SATURN ( 29.447498 ),
        URANUS ( 84.016846 ),
        NEPTUNE( 164.79132 );

        final double factor;

        Planet( double factor ){
            this.factor = factor;
        }

        public double getFactor() {
            return factor;
        }

    }

    private final double   seconds;
    private final double[] ages = new double[ Planet.values().length ];

    SpaceAge( double seconds ) {
        this.seconds = seconds;
        Arrays.stream( Planet.values() ).forEach(
            p -> ages[ p.ordinal() ] = seconds / ( EARTH_YEAR_SECONDS * p.getFactor() )
        );
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return ages[ Planet.EARTH.ordinal() ];
    }

    double onMercury() {
        return ages[ Planet.MERCURY.ordinal() ];
    }

    double onVenus() {
        return ages[ Planet.VENUS.ordinal() ];
    }

    double onMars() {
        return ages[ Planet.MARS.ordinal() ];
    }

    double onJupiter() {
        return ages[ Planet.JUPITER.ordinal() ];
    }

    double onSaturn() {
        return ages[ Planet.SATURN.ordinal() ];
    }

    double onUranus() {
        return ages[ Planet.URANUS.ordinal() ];
    }

    double onNeptune() {
        return ages[ Planet.NEPTUNE.ordinal() ];
    }

}
