import java.util.ArrayList;
import java.util.List;

class Flattener {

    <T> List<T> flatten(List<T> list) {
        List<T> result = new ArrayList<>();
        list.forEach( e ->{
            if( (e instanceof List) ){
                result.addAll( flatten( (List<T>) e ) );
            } else if( e != null ) {
                result.add( e );
            }
        });
        return result;
    }

}