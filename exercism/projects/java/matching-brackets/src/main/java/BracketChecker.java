import java.util.*;

class BracketChecker {

    private final Deque<Character> stack;

    private static final List<Character> OPENERS = Arrays.asList('[','(','{');
    private static final List<Character> CLOSERS = Arrays.asList(']',')','}');

    BracketChecker( String expression ) {
        this.stack = new ArrayDeque<>();
        expression.chars().forEach(
            c -> {
                char ch = (char) c;
                if( OPENERS.contains( ch ) ){
                    this.stack.push( ch );
                } else if( CLOSERS.contains( ch ) ){
                    int index = CLOSERS.indexOf( ch );
                    if( !this.stack.isEmpty() && this.stack.peek() == OPENERS.get( index ) ){
                        this.stack.pop();
                    } else {
                        this.stack.push( ch );
                    }
                }
            }
        );
    }

    boolean areBracketsMatchedAndNestedCorrectly() {
        return this.stack.isEmpty();
    }



}