import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Etl {

    Map<String, Integer> transform(Map<Integer, List<String>> old) {
        Map<String, Integer> result = new HashMap<>();
        old.entrySet().stream().forEach(
            e -> e.getValue().stream().forEach(
                l -> result.put( l.toLowerCase(), e.getKey() )
            )
        );
        return result;
    }

}