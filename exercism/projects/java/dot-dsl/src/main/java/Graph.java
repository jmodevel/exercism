import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class Graph {
    
    private Map<String, String> attributes;
    private final Collection<Node> nodes;
    private final Collection<Edge> edges;
    
    public Graph() {
        this.nodes      = new ArrayList<>();
        this.edges      = new ArrayList<>();
        this.attributes = Collections.emptyMap();
    }
    
    public Graph(Map<String, String> attributes) {
        this();
        this.attributes = attributes;
    }
    
    public Collection<Node> getNodes() {
        return this.nodes;
    }
    
    public Collection<Edge> getEdges() {
        return this.edges;
    }
    
    public Graph node(String name) {
        return this.node( name, Collections.emptyMap() );
    }
    
    public Graph node(String name, Map<String, String> attributes) {
        this.nodes.add( new Node( name, attributes ) );
        return this;
    }
    
    public Graph edge(String start, String end) {
        return this.edge( start, end, Collections.emptyMap() );
    }
    
    public Graph edge(String start, String end, Map<String, String> attributes) {
        this.edges.add( new Edge( start, end, attributes ) );
        return this;
    }
    
    public Map<String, String> getAttributes() {
        return this.attributes;
    }
    
}