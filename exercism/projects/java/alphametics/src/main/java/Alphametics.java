import java.util.*;
import java.util.stream.IntStream;

public class Alphametics {

    private final char[][] expression;
    private final Map<Character, Integer> letterMap = new HashMap<>();

    public Alphametics(String text) {
        this.expression = makeExpression( text );
        for (char[] chars : expression) {
            for (char ch : chars) {
                if (Character.isLetter(ch)) {
                    letterMap.put(ch, 0);
                }
            }
        }
    }

    private static char[][] makeExpression(String text) {
        String[] allParts = text.split("==");
        String[] leftParts = allParts[0].split("\\+");
        String rightPart = allParts[1].trim();
        int len = leftParts.length + 1;
        char[][] chars = new char[len][];
        chars[len - 1] = rightPart.toCharArray();
        for (int i = 0; i < leftParts.length; i++) {
            chars[i] = leftParts[i].trim().toCharArray();
        }
        return chars;
    }

    public Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        List<Character> keys = getUnsolvedKeys();
        // biggest pow of keys length
        long limit = (long) Math.pow(10, keys.size());
        // one of the characters has been assigned to 1
        boolean solvedForOne = keys.size() < letterMap.size();
        boolean[] blockedForZero = getBlockedForZero(keys);
        // 3 letters: iterate from 1 to 99
        // 4 letters: iterate from 10 to 999
        for (long i = (long) Math.pow(10, keys.size() - 2); i < limit; i++) {
            int[] digits = toDigits(i, keys.size());
            long skip = skip(digits, solvedForOne, blockedForZero);
            if (skip > 0) {
                // skip all not possible 
                i += skip - 1;
                continue;
            }
            for (int j = 0; j < digits.length; j++) {
                letterMap.put(keys.get(j), digits[j]);
            }
            if (isSolved()) {
                return letterMap;
            }
        }
        throw new UnsolvablePuzzleException();
    }

    // get characters that cannot be zero as they starts any of the words in expression
    private boolean[] getBlockedForZero(List<Character> keys) {
        boolean[] blocked = new boolean[keys.size()];
        for (char[] chars : expression) {
            int index = keys.indexOf(chars[0]);
            if (index >= 0) {
                blocked[index] = true;
            }
        }
        return blocked;
    }

    // loop all expressions to check if any of them has same length than the last one
    // if it is not the case, first character of last expression is '1' automatically
    // A + BB = MOM -> M = 1
    private List<Character> getUnsolvedKeys() {
        boolean firstChar = IntStream.range(0, expression.length - 1)
            .map(i -> expression[i].length)
            .anyMatch(len -> len == expression[expression.length - 1].length);
        Set<Character> keys = new HashSet<>(letterMap.keySet());
        if (!firstChar) {
            Character ch = expression[expression.length - 1][0];
            keys.remove(ch);
            letterMap.put(ch, 1);
        }
        return new ArrayList<>(keys);
    }

    private boolean isSolved() {
        int sum = sumRange(0, expression.length - 1);
        int result = sumRow(expression.length - 1);
        return sum == result;
    }

    private int sumRange(int startRow, int endRow) {
        int total = 0;
        for (int row = startRow; row < endRow; row++) {
            total += sumRow(row);
        }
        return total;
    }

    private int sumRow( int row ) {
        int total = 0;
        int multi = 1;
        for (int i = expression[row].length - 1; i >= 0; i--) {
            int digit = letterMap.get(expression[row][i]);
            total += digit * multi;
            multi *= 10;
        }
        return total;
    }

    // convert long to digits
    private static int[] toDigits( long value, int size ) {
        int[] digits = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            digits[i] = (int) (value % 10);
            value /= 10;
        }
        return digits;
    }
    private static long skip( int[] digits, boolean solvedForOne, boolean[] blockedForZero ){
        // one boolean for each digit indicating if it has been already assigned
        boolean[] set = new boolean[10];
        set[1] = solvedForOne;
        for (int i = 0; i < digits.length; i++) {
            int digit = digits[i];
            if (set[digit] || (digit == 0 && blockedForZero[i])) {
                return (long) Math.pow(10, digits.length - 1 - i);
            }
            set[digit] = true;
        }
        return 0;
    }
}


/*import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Alphametics {
    
    private static final List<String> DIGITS = "0123456789".chars().mapToObj( i-> String.valueOf( (char) i ) ).toList();
    
    private final List<String> addends;
    private final String       result;
    
    private final List<Character> characters;
    
    Alphametics( String userInput ) {
        String[] inputParts = userInput.split( " == " );
        if( inputParts.length != 2 ){
            throw new IllegalArgumentException( "Input must have addends and result" );
        }
        String addendsString = inputParts[0];
        long plusCount = addendsString.codePoints().filter( c -> c == '+' ).count();
        String[] addendsSplit = addendsString.split( " [+] " );
        if( addendsSplit.length != plusCount+1 ){
            throw new IllegalArgumentException( "Addends must be joined by '+'" );
        }
        this.addends    = Arrays.stream( addendsSplit ).toList();
        this.result     = inputParts[1];
        this.characters = userInput
            .codePoints()
            .filter( c -> c != ' ' && c != '+' && c != '=' )
            .distinct()
            .mapToObj( c -> (char) c )
            .toList();
        if( this.characters.size() > 10 ){
            throw new IllegalArgumentException( "Only ten characters are allowed" );
        }
    }
    
    Map<Character, Integer> solve() throws UnsolvablePuzzleException {
        if( this.addends.size() == 1 ){
            throw new UnsolvablePuzzleException();
        }
        List<Integer[]> candidates = computeCandidates();
        return candidates.stream()
            .map( this::mapConfig )
            .filter( this::checkSolved )
            .findFirst()
            .orElseThrow( UnsolvablePuzzleException::new );
    }
    
    private List<Integer[]> computeCandidates() {
        List<Integer[]> combinations = new ArrayList<>();
        combinationsWithoutRepetition( this.characters.size(), 0, new Integer[this.characters.size()], combinations );
        return combinations;
    }
    
    private void combinationsWithoutRepetition( int len, int startPosition, Integer[] result, List<Integer[]> combinations ){
        if (len == 0){
            List<Integer[]> permutations = new ArrayList<>();
            permutations( this.characters.size(), Arrays.copyOf( result, result.length ), permutations );
            combinations.addAll( permutations );
            return;
        }
        for (int i = startPosition; i <= DIGITS.size()-len; i++){
            result[result.length - len] = Integer.valueOf( DIGITS.get( i ) );
            combinationsWithoutRepetition( len-1, i+1, result, combinations );
        }
    }
    
    public static void permutations( int n, Integer[] elements, List<Integer[]> permutations ) {
        if( n == 1 ) {
            permutations.add( Arrays.copyOf( elements, elements.length ) );
        } else {
            for(int i = 0; i < n-1; i++) {
                permutations( n - 1, elements, permutations );
                if(n % 2 == 0) {
                    swap( elements, i, n-1 );
                } else {
                    swap( elements, 0, n-1 );
                }
            }
            permutations( n - 1, elements, permutations );
        }
    }
    
    private static void swap(Integer[] elements, int a, int b) {
        Integer tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }
    
    private boolean checkSolved( Map<Character, Integer> values ){
        List<Long> replacedAddends = this.addends.stream().map( a -> replace( a, values ) ).toList();
        Long       replacedResult  = replace( this.result, values );
        return replacedAddends.stream().mapToLong( Long::longValue ).sum() == replacedResult;
    }
    
    private long replace( String target, Map<Character, Integer> values ){
        String replaced = target.codePoints().mapToObj( c -> values.get( (char) c ).toString() ).collect( Collectors.joining() );
        return ( replaced.startsWith( "0" ) )? -1 : Long.parseLong( replaced );
    }
    
    private Map<Character, Integer> mapConfig( Integer[] config ){
        Map<Character, Integer> values = new HashMap<>();
        IntStream.range( 0, this.characters.size() ).forEach(
            i -> values.put( this.characters.get( i ), config[i] )
        );
        return values;
    }
    
}*/
