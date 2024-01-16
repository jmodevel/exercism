class Darts {

    enum Target{

        INNER  ( 1.0, 10 ),
        MIDDLE ( 5.0,5 ),
        OUTER  ( 10.0, 1 ),
        OUTSIDE( Double.MAX_VALUE,0 );

        private final double radius;
        private final int    points;

        Target( double radius, int points ){
            this.points = points;
            this.radius = radius;
        }

        public int getPoints() {
            return points;
        }

    }

    int score( double xOfDart, double yOfDart ) {
        for( Target target : Target.values() ){
            if( target.radius >= Math.hypot( xOfDart, yOfDart ) ){
                return target.getPoints();
            }
        }
        return Target.OUTSIDE.getPoints();
    }

}
