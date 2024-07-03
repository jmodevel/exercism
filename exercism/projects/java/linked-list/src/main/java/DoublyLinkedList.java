class DoublyLinkedList<T> {
    private Element<T> head;
    
    void push(T value) {
        if( this.head == null ){
            this.head = new Element<>( value, null, null );
        } else {
            Element<T> current = this.head;
            while( current.next != null ){
                current = current.next;
            }
            current.next = new Element<>( value, current, null );
        }
    }
    
    T pop() {
        if( this.head == null ){
            throw new IllegalStateException();
        }
        Element<T> current = this.head;
        while( current.next != null ){
            current = current.next;
        }
        T ret = current.value;
        if( current.prev != null ){
            current.prev.next = null;
        }
        return ret;
    }
    
    void unshift(T value) {
        this.head = new Element<>( value, null, this.head );
    }
    
    T shift() {
        if( this.head == null ) {
            throw new IllegalStateException();
        }
        T ret = this.head.value;
        this.head = this.head.next;
        return ret;
    }
    
    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;
        
        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev  = prev;
            this.next  = next;
        }
    }
}