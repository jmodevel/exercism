public class PangramChecker {

    private static final int ALPHABET_LETTERS = 26;

    public boolean isPangram( String input ) {
        return input
                .toLowerCase()
                .replaceAll("[^a-z]", "")
                .chars()
                .distinct()
                .count() == ALPHABET_LETTERS;
    }

}
