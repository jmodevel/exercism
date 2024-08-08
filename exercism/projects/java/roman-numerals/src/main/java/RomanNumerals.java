class RomanNumerals {
    
    enum DICTIONARY {
        
        M (1000),
        CM(900),
        D (500),
        CD(400),
        C (100),
        XC(90),
        L (50),
        XL(40),
        X (10),
        IX(9),
        V (5),
        IV(4),
        I (1);
        
        int arabic;
        
        DICTIONARY(int arabic){
            this.arabic = arabic;
        }
        
    }
    
    private int number;
    
    RomanNumerals(int number) {
        if( number <= 0 ) throw new IllegalArgumentException( "numbers under 1 are not translatable" );
        this.number = number;
    }
    
    String getRomanNumeral() {
        StringBuilder sb = new StringBuilder();
        int dictPos = 0;
        while( this.number > 0 && dictPos < DICTIONARY.values().length ){
            DICTIONARY entry = DICTIONARY.values()[dictPos];
            if( entry.arabic <= this.number ){
                sb.append( entry.name() );
                this.number -= entry.arabic;
            } else {
                dictPos++;
            }
        }
        return sb.toString();
    }
    
}
