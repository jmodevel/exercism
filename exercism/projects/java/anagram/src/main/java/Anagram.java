import java.util.Arrays;
import java.util.List;

class Anagram {
    
    private final String word;
    
    public Anagram( String word ) {
        this.word = word;
    }
    
    public List<String> match( List<String> candidates ) {
        int[] sortedWord = word.toLowerCase().chars().sorted().toArray();
        return candidates.stream()
            .filter( w -> !this.word.equalsIgnoreCase( w.toLowerCase() )
                          && Arrays.equals( sortedWord, w.toLowerCase().chars().sorted().toArray() ) )
            .toList();
    }
    
}