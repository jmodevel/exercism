import java.util.*;

class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    
    void insert(T value) {
        if( root == null ){
            root = new Node<>( value );
        } else {
            insert( value, root );
        }
    }
    
    private void insert( T value, Node<T> current ){
        if( value.compareTo( current.data ) <= 0 ){
            if( current.getLeft() == null ){
                current.left = new Node<>( value );
            } else {
                insert( value, current.left );
            }
        } else {
            if( current.right == null ){
                current.right = new Node<>( value );
            } else {
                insert( value, current.right );
            }
        }
    }
    
    List<T> getAsSortedList() {
        Deque<Node<T>> stack = new ArrayDeque<>();
        if( this.root == null ){
            return Collections.emptyList();
        }
        List<Node<T>> visited = new ArrayList<>();
        stack.push( this.root );
        Node<T> current = this.root;
        do {
            if( current.left != null && !visited.contains( current.left ) ){
                stack.push( current.left );
                current = current.left;
            } else {
                visited.add( stack.pop() );
                if( current.right != null ){
                    stack.push( current.right );
                }
                if( !stack.isEmpty() ) {
                    current = stack.peek();
                }
            }
        } while ( !stack.isEmpty() );
        return visited.stream().map( Node::getData ).toList();
    }
    
    List<T> getAsLevelOrderList() {
        List<Node<T>> remaining = new ArrayList<>();
        if( this.root == null ){
            return Collections.emptyList();
        }
        List<Node<T>> visited = new ArrayList<>();
        remaining.add( this.root );
        Node<T> current = this.root;
        do {
            visited.add( remaining.remove( 0 ) );
            if ( current.left != null ) {
                remaining.add( current.left );
            }
            if ( current.right != null ) {
                remaining.add( current.right );
            }
            if( !remaining.isEmpty() ) {
                current = remaining.get( 0 );
            }
        } while ( !remaining.isEmpty() );
        return visited.stream().map( Node::getData ).toList();
    }
    
    Node<T> getRoot() {
        return this.root;
    }
    
    static class Node<T> {
        
        private Node<T> left;
        private Node<T> right;
        private T       data;
        
        public Node( T data ){
            this.data = data;
        }
        
        Node<T> getLeft() {
            return this.left;
        }
        
        Node<T> getRight() {
            return this.right;
        }
        
        T getData() {
            return this.data;
        }
        
    }
}
