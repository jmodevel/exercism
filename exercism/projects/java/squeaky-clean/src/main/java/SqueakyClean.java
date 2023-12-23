class SqueakyClean {
    private static final int ALPHA = 0x000003B1;
    private static final int OMEGA = 0x000003C9;

    private SqueakyClean() {
        throw new IllegalStateException("Utility class");
    }

    static String clean(String identifier) {
        StringBuilder sb = new StringBuilder();
        boolean camel = false;
        for( char c : identifier.toCharArray() ){
            if( c == '-' ){
                camel = true;
            } else if( Character.isISOControl( c ) ){
                sb.append( "CTRL" );
            } else if( Character.isWhitespace( c ) ){
                sb.append( "_" );
            } else if( Character.isLetter( c ) && !isLowerCaseGreek(c) ){
                if( camel ){
                    c = Character.toUpperCase(c);
                    camel = false;
                }
                sb.append( Character.toString(c) );
            }
        }
        return sb.toString();
    }
    private static boolean isLowerCaseGreek( char c ){
        return c >= ALPHA && c <= OMEGA;
    }

}