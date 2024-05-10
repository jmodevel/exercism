import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
class GottaSnatchEmAll {

    private GottaSnatchEmAll() {
        throw new IllegalStateException("Utility class");
    }

    static Set<String> newCollection(List<String> cards) {
        return cards.stream().collect( Collectors.toSet() );
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add( card );
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        if(myCollection.isEmpty() || theirCollection.isEmpty()) return false;
        return !myCollection.containsAll(theirCollection);
    }

    static Set<String> commonCards( List<Set<String>> collections ) {
        Set<String> result = null;
        for( Set<String> s : collections ){
            if( result == null ){
                result = s.stream().collect( Collectors.toSet() );
            } else {
                Set<String> copy = result.stream().collect( Collectors.toUnmodifiableSet() );
                copy.stream().filter( card -> !s.contains( card ) ).forEach( result::remove );
            }
        }
        return result;
    }

    static Set<String> allCards( List<Set<String>> collections ) {
        return collections.stream()
            .flatMap( Set::stream )
            .collect( Collectors.toSet() );
    }
}