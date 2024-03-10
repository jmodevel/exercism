import java.util.Arrays;
import java.util.function.Predicate;

class Bob {

    enum Answer {

        SILENCE       ( "Fine. Be that way!",                String::isEmpty ),
        YELL_QUESTION ( "Calm down, I know what I'm doing!", isYell().and( isQuestion() ) ),
        QUESTION      ( "Sure.",                             isQuestion() ),
        YELL          ( "Whoa, chill out!",                  isYell() ),
        DEFAULT       ( "Whatever.",                         ( s -> true ) );

        private final String say;
        private final Predicate<String> predicate;

        Answer( String say, Predicate<String> predicate ){
            this.say       = say;
            this.predicate = predicate;
        }

    }

    String hey( String input ) {
        return Arrays.stream( Answer.values() )
            .filter( a -> a.predicate.test( input.trim() ) )
            .findFirst().orElse( Answer.DEFAULT )
            .say;
    }

    private static Predicate<String> isYell(){
        return s -> s.equals( s.toUpperCase() ) && s.chars().anyMatch( Character::isLetter );
    }

    private static Predicate<String> isQuestion(){
        return s -> s.endsWith( "?" );
    }

}