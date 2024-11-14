import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Dominoes {
    
    List<Domino> formChain( List<Domino> inputDominoes ) throws ChainNotFoundException {
        if( inputDominoes == null || inputDominoes.isEmpty() ){
            return Collections.emptyList();
        }
        List<Domino> ret = null;
        for( int i = 0; i < inputDominoes.size(); i++ ){
            Domino current = inputDominoes.get( i );
            if( ret == null ) {
                ret = formChain( current, false, new ArrayList<>(), inputDominoes );
            }
            if( ret == null && current.getRight() != current.getLeft() ) {
                ret = formChain( current, true, new ArrayList<>(), inputDominoes );
            }
        }
        if( ret == null ){
            throw new ChainNotFoundException( "No domino chain found." );
        }
        return ret;
    }
    
    private List<Domino> formChain( Domino current, boolean reverse, List<Domino> chain, List<Domino> remaining ){
        List<Domino> copy = new ArrayList<>( chain );
        copy.add( ( reverse )? new Domino( current.getRight(), current.getLeft() ) : current );
        List<Domino> rest = new ArrayList<>( remaining );
        rest.remove( current );
        return formChain( copy, rest );
    }
    
    private List<Domino> formChain( List<Domino> chain, List<Domino> remaining ){
        Domino current = chain.get( chain.size() - 1 );
        if( remaining.isEmpty() ){
            return ( chain.get( 0 ).getLeft() == current.getRight() )? chain : null;
        } else {
            List<Domino> ret = null;
            List<Domino> nextCandidates = remaining.stream()
                .filter( d -> d.getLeft() == current.getRight() || d.getRight() == current.getRight() )
                .toList();
            if( nextCandidates.isEmpty() ) {
                return null;
            }
            for( Domino d : nextCandidates ){
                if( ret == null ) {
                    ret = formChain( d, d.getLeft() != current.getRight(), chain, remaining );
                }
            }
            return ret;
        }
    }
    
}