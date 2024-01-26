import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Say {

    private static final long MAX_NUMBER = 999999999999L;

    private static final long BILLION    = 1000000000L;
    private static final long MILLION    = 1000000L;
    private static final long THOUSAND   = 1000L;
    private static final long HUNDRED    = 100L;
    private static final long TWENTY     = 20L;
    private static final long TEN        = 10L;
    private static final long ZERO       = 0L;

    private static final Map<Long,String> DICTIONARY = new HashMap<>();
    private static final List<Long>       CHUNKS = List.of( BILLION, MILLION, THOUSAND );

    static {

        DICTIONARY.put( 0L, "zero"  );
        DICTIONARY.put( 1L, "one"   );
        DICTIONARY.put( 2L, "two"   );
        DICTIONARY.put( 3L, "three" );
        DICTIONARY.put( 4L, "four"  );
        DICTIONARY.put( 5L, "five"  );
        DICTIONARY.put( 6L, "six"   );
        DICTIONARY.put( 7L, "seven" );
        DICTIONARY.put( 8L, "eight" );
        DICTIONARY.put( 9L, "nine"  );

        DICTIONARY.put( 10L, "ten"       );
        DICTIONARY.put( 11L, "eleven"    );
        DICTIONARY.put( 12L, "twelve"    );
        DICTIONARY.put( 13L, "thirteen"  );
        DICTIONARY.put( 14L, "fourteen"  );
        DICTIONARY.put( 15L, "fifteen"   );
        DICTIONARY.put( 16L, "sixteen"   );
        DICTIONARY.put( 17L, "seventeen" );
        DICTIONARY.put( 18L, "eighteen"  );
        DICTIONARY.put( 19L, "nineteen"  );

        DICTIONARY.put( 20L, "twenty"  );
        DICTIONARY.put( 30L, "thirty"  );
        DICTIONARY.put( 40L, "forty"   );
        DICTIONARY.put( 50L, "fifty"   );
        DICTIONARY.put( 60L, "sixty"   );
        DICTIONARY.put( 70L, "seventy" );
        DICTIONARY.put( 80L, "eighty"  );
        DICTIONARY.put( 90L, "ninety"  );

        DICTIONARY.put( BILLION  , "billion"  );
        DICTIONARY.put( MILLION  , "million"  );
        DICTIONARY.put( THOUSAND , "thousand" );
        DICTIONARY.put( HUNDRED  , "hundred"  );

    }

    public String say( long number ) {
        if( number < 0 || number > MAX_NUMBER ){
            throw new IllegalArgumentException();
        }
        if( number == 0 ){
            return DICTIONARY.get( ZERO );
        }
        List<String> parts = new ArrayList<>();
        for( long chunk : CHUNKS ){
            if( number >= chunk ){
                String part = String.join(
                        " ",
                        sayHundreds( (number/chunk) ),
                        DICTIONARY.get( chunk )
                );
                parts.add( part );
                number = number % chunk;
            }
        }
        if( number >= HUNDRED ){
            parts.add( sayHundreds( number ) );
        } else if( number > ZERO ) {
            parts.add( sayTens( number ) );
        }
        return String.join(" ", parts );
    }

    private String sayTens( long number ){
        if( number < TWENTY || number%TEN == ZERO ){
            return DICTIONARY.get( number );
        }
        if( number < HUNDRED ){
            long modulus = number % TEN;
            return String.join(
                    "-",
                    DICTIONARY.get( number - modulus ),
                   DICTIONARY.get( modulus )
            );
        }
        return "";
    }

    private String sayHundreds( long number ){
        if( number < HUNDRED ){
            return sayTens( number );
        }
        long   quotient = ( number / HUNDRED );
        long   tens     = ( number % HUNDRED );
        String hundreds = String.join(
                " ",
                DICTIONARY.get( quotient ),
                DICTIONARY.get( HUNDRED )
        );
        if( tens == 0 ){
            return hundreds;
        }
        return String.join( " ", hundreds, sayTens( tens ) );
    }


}
