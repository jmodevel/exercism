import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

class RestApi {
    
    private static final String NO_RESPONSE = "N/A";
    
    private static final String USERS_MAPPING = "/users";
    private static final String ADD_MAPPING   = "/add";
    private static final String IOU_MAPPING   = "/iou";
    
    private static final String USERS    = "users";
    private static final String USER     = "user";
    private static final String LENDER   = "lender";
    private static final String BORROWER = "borrower";
    private static final String AMOUNT   = "amount";
    private static final String NAME     = "name";
    private static final String OWES     = "owes";
    private static final String OWED_BY  = "owedBy";
    private static final String BALANCE  = "balance";
    
    private final TreeMap<String, User> elements;
    
    RestApi( User... elements ){
        this.elements = Arrays.stream(elements).collect( Collectors.toMap( User::name, Function.identity(), (s, s2) -> s, TreeMap::new ) );
    }
    
    String get( String url ) {
        return get( url, new JSONObject().put( USERS, new JSONArray() ) );
    }
    
    String get( String url, JSONObject payload ){
        if( !url.equals( USERS_MAPPING ) ){
            throw new IllegalArgumentException( url + " not found" );
        }
        if( !payload.has( USERS ) ){
            throw new IllegalArgumentException( "payload has no mandatory users key" );
        }
        return new JSONObject().put( USERS, filter( payload.optJSONArray( USERS ) ) ).toString();
    }
    
    String post(String url, JSONObject payload) {
        if( payload == null ){
            return NO_RESPONSE;
        }
        if( !Arrays.asList( ADD_MAPPING, IOU_MAPPING ).contains( url ) ){
            throw new IllegalArgumentException( url + " not found" );
        }
        if( url.equals( ADD_MAPPING ) ){
            return add( payload );
        } else {
            return iou( payload );
        }
    }
    
    private JSONArray filter( JSONArray filter ) {
        JSONArray filtered = new JSONArray();
        this.elements.keySet().stream()
            .filter( n -> filter.isEmpty() || filter.toList().contains( n ) )
            .map( k -> this.toJSONObject( this.elements.get( k ) ) )
            .forEach( filtered::put );
        return filtered;
    }
    
    private String add( JSONObject payload ){
        if( !payload.has( USER ) ){
            throw new IllegalArgumentException( "payload has no mandatory user key" );
        }
        String name = payload.getString( USER );
        if( elements.containsKey( name ) ){
            throw new IllegalArgumentException( USER + " " + name + " already exists" );
        }
        elements.put( name, User.builder().setName( name ).build() );
        return toJSONObject( elements.get( name ) ).toString();
    }
    
    private String iou( JSONObject payload ){
        
        if( !payload.has( LENDER ) || !payload.has( BORROWER ) || !payload.has( AMOUNT ) ){
            throw new IllegalArgumentException( "payload must have lender, borrower and amount keys" );
        }
        String lender   = payload.getString( LENDER   );
        if( !elements.containsKey( lender ) ){
            throw new IllegalArgumentException( USER + " " + lender + " does not exist" );
        }
        String borrower = payload.getString( BORROWER );
        if( !elements.containsKey( borrower ) ){
            throw new IllegalArgumentException( USER + " " + borrower + " does not exist" );
        }
        double balance = getBalance( elements.get( lender ), elements.get( borrower ), payload.getDouble( AMOUNT ) );
        
        elements.put( lender,   buildUser( lender,   borrower, elements.get( lender   ),  balance ) );
        elements.put( borrower, buildUser( borrower, lender,   elements.get( borrower ), -balance ) );

        return new JSONObject().put( USERS, filter( new JSONArray().put( lender ).put( borrower ) ) ).toString();

    }
    
    private double getBalance( User lender, User borrower, double amount ){
        double owedByBalance = lender.owedBy().stream().filter( b -> b.name.equals( borrower.name() ) ).mapToDouble( iou -> iou.amount ).sum();
        double owesBalance   = lender.owes()  .stream().filter( b -> b.name.equals( borrower.name() ) ).mapToDouble( iou -> iou.amount ).sum();
        return owedByBalance - owesBalance + amount;
    }
    
    private User buildUser( String l, String b, User lu, double balance ) {
        User.Builder builder = User.builder().setName( l );
        lu.owedBy().stream().filter( iou -> !iou.name.equals( b ) ).forEach( iou -> builder.owedBy( iou.name, iou.amount ) );
        lu.owes()  .stream().filter( iou -> !iou.name.equals( b ) ).forEach( iou -> builder.owes  ( iou.name, iou.amount ) );
        if( balance > 0 ){
            builder.setName( l ).owedBy( b, balance );
        } else if( balance < 0 ){
            builder.setName( l ).owes( b, -balance );
        }
        return builder.build();
    }
    
    private JSONObject toJSONObject( User user ){
        double balance = 0.0;
        JSONObject owes   = new JSONObject();
        for( Iou iou : user.owes() ){
            owes.put( iou.name, iou.amount );
            balance -= iou.amount;
        }
        JSONObject owedBy = new JSONObject();
        for( Iou iou : user.owedBy() ){
            owedBy.put( iou.name, iou.amount );
            balance += iou.amount;
        }
        return new JSONObject()
            .put( NAME,    user.name() )
            .put( OWES,    owes        )
            .put( OWED_BY, owedBy      )
            .put( BALANCE, balance     );
    }
    
}
