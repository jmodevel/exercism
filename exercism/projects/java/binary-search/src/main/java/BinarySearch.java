import java.util.List;

class BinarySearch {
    
    private List<Integer> items;
    
    BinarySearch(List<Integer> items) {
        this.items = items;
    }
    
    int indexOf( int item ) throws ValueNotFoundException {
        if( this.items == null || this.items.isEmpty() ){
            throw new ValueNotFoundException( "Value not in array" );
        }
        return indexOf( item, 0, this.items.size()-1 );
    }
    
    private int indexOf( int item, int from, int to ) throws ValueNotFoundException {
        if( from == to ){
            if( item != this.items.get( from ) ){
                throw new ValueNotFoundException( "Value not in array" );
            }
            return from;
        }
        int middle = from + (this.items.subList( from, to+1 ).size()/2);
        int value  = this.items.get( middle );
        if( item == value ) {
            return middle;
        } else if ( item > value  ) {
            return indexOf( item, middle+1, to );
        } else {
            return indexOf( item, from, middle-1 );
        }
    }
    
}