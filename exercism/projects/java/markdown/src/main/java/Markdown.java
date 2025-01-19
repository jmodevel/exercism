import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Markdown {
    
    private static final String LINE_FORMAT = "<%s>%s</%s>" ;
    private static final int    MAX_HEADER  = 6;
    
    private final Map<String, String> replacements;

    public Markdown(){
        this.replacements = new LinkedHashMap<>();
        this.replacements.put( "__(.+)__", "<strong>$1</strong>" );
        this.replacements.put( "_(.+)_"  , "<em>$1</em>"         );
    }
    
    enum LineKind {
       
        HEADER   ( '#', "h"  ),
        LIST_ITEM( '*', "li" ),
        PARAGRAPH( ' ', "p"  );
        
        private char   startsWith;
        private String tag;
        
        LineKind( char startsWith, String tag ){
            this.startsWith = startsWith;
            this.tag        = tag;
        }
        
        static LineKind of(char c){
            return Arrays.stream( LineKind.values() ).filter( k -> k.startsWith == c ).findFirst().orElse( PARAGRAPH );
        }
        
    }
    
    String parse( String markdown ) {
        String[]      lines  = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        LineKind      prev   = null;
        for( String line : lines ) {
            if( !line.isEmpty() ) {
                LineKind kind = LineKind.of( line.charAt( 0 ) );
                result.append( switch ( kind ){
                    case HEADER, PARAGRAPH -> prev == LineKind.LIST_ITEM ? "</ul>" : "";
                    case LIST_ITEM         -> prev == LineKind.LIST_ITEM ? "" : "<ul>";
                } );
                int count = countLeadingHashes( line );
                result.append( buildTag( getTag( kind, count ), getText( kind, count, line ) ));
                prev = kind;
            }
        }
        result.append( ( prev == LineKind.LIST_ITEM )? "</ul>" : "" );
        return result.toString();
    }

    private String getTag( LineKind kind, int leadingSlashes ) {
        return switch( kind ) {
            case HEADER ->
                (leadingSlashes > MAX_HEADER) ? LineKind.PARAGRAPH.tag : LineKind.HEADER.tag + leadingSlashes;
            case LIST_ITEM, PARAGRAPH -> kind.tag;
        };
    }

    private String getText( LineKind kind, int leadingSlashes, String line ){
        return switch( kind ){
            case HEADER    -> line.substring( leadingSlashes > MAX_HEADER ? 0 : leadingSlashes + 1 );
            case LIST_ITEM -> parseSomeSymbols( line.substring( 2 ) );
            case PARAGRAPH -> parseSomeSymbols( line );
        };
    }

    private static int countLeadingHashes(String line) {
        return IntStream.range( 0, line.length() ).filter( i -> line.charAt( i ) != '#' ).findFirst().orElse( 0 );
    }
    
    private String buildTag( String tag, String markdown ){
        return String.format( LINE_FORMAT, tag, markdown, tag );
    }
    
    private String parseSomeSymbols( String markdown ) {
        for( Map.Entry<String, String> entry : this.replacements.entrySet() ){
            markdown = markdown.replaceAll( entry.getKey(), entry.getValue() );
        }
        return markdown;
    }
    
}
