import java.util.Map;

class RaindropConverter {

    private static final Map<Integer, String> SOUNDS = Map.of(
        3, "Pling",
        5, "Plang",
        7, "Plong"
    );

    String convert(int number) {
        return SOUNDS.entrySet().stream()
                .sorted( Map.Entry.comparingByKey() )
                .filter( e -> number % e.getKey() == 0 )
                .map( Map.Entry::getValue )
                .reduce( (v1, v2) -> v1+v2 )
                .orElse( String.valueOf( number ) );
    }

}
