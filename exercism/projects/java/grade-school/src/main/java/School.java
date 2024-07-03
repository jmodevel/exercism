import java.util.*;

class School {
    
    private final Map<Integer, Set<String>> students = new TreeMap<>();
    
    boolean add( String student, int grade ) {
        students.computeIfAbsent( grade, k -> new TreeSet<>() );
        return !roster().contains( student ) && students.get( grade ).add( student );
    }
    
    List<String> roster() {
        return students.values().stream().flatMap( Set::stream ).toList();
    }
    
    List<String> grade(int grade) {
        return students.getOrDefault( grade, new TreeSet<>() ).stream().toList();
    }
    
}
