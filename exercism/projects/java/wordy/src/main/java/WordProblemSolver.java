import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordProblemSolver {

    private static final String QUESTION_EXTRACTOR = "What is (.*)\\?$";
    private static final String ERROR              = "I'm sorry, I don't understand the question!";

    private static final String ADDITION       = "plus";
    private static final String SUBTRACTION    = "minus";
    private static final String MULTIPLICATION = "multiplied";
    private static final String DIVISION       = "divided";

    enum TOKEN { NUMBER, OPERATOR }

    int solve( final String wordProblem ) {
        Matcher questionMatcher = Pattern.compile( QUESTION_EXTRACTOR ).matcher( wordProblem );
        if( !questionMatcher.matches() ) throw new IllegalArgumentException( ERROR );
        String[] words = questionMatcher.group(1).split( "\\sby\\s|\\s" );
        TOKEN expected = TOKEN.NUMBER;
        Deque<Object> stack = new ArrayDeque<>();
        for( String word : words ){
            switch( expected ){
                case NUMBER   -> manageNumber  ( word, stack );
                case OPERATOR -> manageOperator( word, stack );
                default -> throw new IllegalArgumentException( "unknown operator: " + expected );
            }
            expected = expected == TOKEN.NUMBER ? TOKEN.OPERATOR : TOKEN.NUMBER;
        }
        if( stack.size() != 1 ) throw new IllegalArgumentException( ERROR );
        return (Integer) stack.pop();
    }

    private void manageNumber( String word, Deque<Object> stack ) {
        int number;
        try {
            number = Integer.parseInt( word );
        } catch( Exception e ){
            throw new IllegalArgumentException( ERROR );
        }
        if( !stack.isEmpty() ) {
            switch ( (String) stack.pop() ){
                case ADDITION       -> stack.push( (Integer) stack.pop() + number );
                case SUBTRACTION    -> stack.push( (Integer) stack.pop() - number );
                case MULTIPLICATION -> stack.push( (Integer) stack.pop() * number );
                case DIVISION       -> stack.push( (Integer) stack.pop() / number );
                default -> throw new IllegalArgumentException( "unknown operation: " + stack.pop() );
            }
        } else {
            stack.push( number );
        }
    }

    private void manageOperator( String word, Deque<Object> stack ){
        if( !List.of( ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION ).contains( word ) ){
            throw new IllegalArgumentException( ERROR );
        }
        stack.push( word );
    }

}