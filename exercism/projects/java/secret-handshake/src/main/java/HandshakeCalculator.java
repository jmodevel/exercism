import java.util.*;

class HandshakeCalculator {

    private static final int REVERSE_BIT = 4;

    List<Signal> calculateHandshake(int number) {
        BitSet bitset = BitSet.valueOf(new long[]{number});
        List<Signal> signals = new ArrayList<>( Arrays.stream( Signal.values() )
                .filter( s ->
                            s.ordinal() < bitset.length() && bitset.get( s.ordinal() )
                ).toList());
        if ( bitset.length() >= REVERSE_BIT && bitset.get( REVERSE_BIT ) ){
            Collections.reverse( signals );
        }
        return signals;
    }

}
