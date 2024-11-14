import java.util.*;
import java.util.stream.Collectors;
class Tournament {

    private static final String HEADER = "%-31s| MP |  W |  D |  L |  P\n";
    private static final String ROW    = "%-31s|%3d |%3d |%3d |%3d |%3d\n";

    enum Result {

        WIN ( 3 ),
        DRAW( 1 ),
        LOSS( 0 );

        protected int points;

        Result( int points ){
            this.points = points;
        }

    }

    private Map<String, Team> classification;

    public Tournament(){
        classification = new HashMap<>();
    }

    String printTable() {
        return classification.values().stream()
            .sorted( Comparator.comparing( Team::points ).reversed().thenComparing( Team::name ) )
            .map( t -> String.format( ROW, t.name, t.plays(), t.win, t.draw, t.loss, t.points() ) )
            .collect( Collectors.joining( "", String.format( HEADER, "Team" ), "" ) );
    }

    void applyResults( String resultString ) {
        Arrays.stream( resultString.split( "\n" ) ).forEach( this::parseResult );
    }

    private void parseResult(String r) {
        String[] res = r.split( ";" );
        classification.putIfAbsent( res[0], new Team( res[0] ) );
        classification.putIfAbsent( res[1], new Team( res[1] ) );
        Result result = Result.valueOf( res[ 2 ].toUpperCase() );
        switch( result ){
            case WIN -> {
                classification.get( res[0] ).getWin();
                classification.get( res[1] ).getLoss();
            }
            case DRAW -> {
                classification.get( res[0] ).getDraw();
                classification.get( res[1] ).getDraw();
            }
            case LOSS -> {
                classification.get( res[0] ).getLoss();
                classification.get( res[1] ).getWin();
            }
        }
    }

    class Team {

        String name;
        int    win;
        int    draw;
        int    loss;

        public Team( String name ){
            this.name = name;
        }

        String name() {
            return name;
        }

        int points(){
            return win * Result.WIN.points + draw * Result.DRAW.points;
        }

        int plays(){
            return win + draw + loss;
        }

        void getWin(){
            win++;
        }

        void getDraw(){
            draw++;
        }

        void getLoss(){
            loss++;
        }
    }

}