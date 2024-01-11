import java.util.Random;

class CaptainsLog {

    private static final String REGISTRY_NUMBER_PREFIX = "NCC";

    private static final int REGISTRY_NUMBER_START = 1000;
    private static final int REGISTRY_NUMBER_LIMIT = 9000;

    private static final double DATES_START = 41000.0;
    private static final double DATES_LIMIT = 1000;

    private static final char[] PLANET_CLASSES = new char[]{'D', 'H', 'J', 'K', 'L', 'M', 'N', 'R', 'T', 'Y'};

    private Random random;

    CaptainsLog(Random random) {
        this.random = random;
    }

    char randomPlanetClass() {
        return PLANET_CLASSES[ this.random.nextInt( PLANET_CLASSES.length ) ];
    }

    String randomShipRegistryNumber() {
        return String.format( "%s-%d", REGISTRY_NUMBER_PREFIX,
                REGISTRY_NUMBER_START + this.random.nextInt( REGISTRY_NUMBER_LIMIT ) );
    }

    double randomStardate() {
        return DATES_START + this.random.nextDouble( DATES_LIMIT );
    }
}
