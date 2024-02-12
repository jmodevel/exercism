import java.util.List;
import java.util.stream.IntStream;

class KindergartenGarden {

    private static final int ROWS_IN_GARDEN = 2;
    private static final int PLANTS_BY_CHILD_PER_ROW = 2;

    private final Plant[][] plants;

    private final List<String> students = List.of(
            "Alice", "Bob", "Charlie", "David", "Eve", "Fred",
            "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"
    );

    KindergartenGarden(String garden) {
        String[] split = garden.split( "\n" );
        plants = new Plant[ ROWS_IN_GARDEN ][ split.length/ PLANTS_BY_CHILD_PER_ROW ];
        IntStream.range( 0, ROWS_IN_GARDEN ).forEach( i ->
            plants[i] = split[i].chars()
                    .mapToObj( c -> Plant.getPlant( (char) c ) )
                    .toArray( Plant[]::new )
        );
    }

    List<Plant> getPlantsOfStudent(String student) {
        int position = students.indexOf( student );
        return List.of(
                plants[ 0 ][ position * PLANTS_BY_CHILD_PER_ROW   ],
                plants[ 0 ][ position * PLANTS_BY_CHILD_PER_ROW+1 ],
                plants[ 1 ][ position * PLANTS_BY_CHILD_PER_ROW   ],
                plants[ 1 ][ position * PLANTS_BY_CHILD_PER_ROW+1 ]
        );
    }

}
