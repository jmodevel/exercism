import java.util.ArrayList;
import java.util.List;

class ProteinTranslator {

    private static final String METHIONINE    = "Methionine";
    private static final String PHENYLALANINE = "Phenylalanine";
    private static final String LEUCINE       = "Leucine";
    private static final String SERINE        = "Serine";
    private static final String TYROSINE      = "Tyrosine";
    private static final String CYSTEINE      = "Cysteine";
    private static final String TRYPTOPHAN    = "Tryptophan";

    private static final String STOP          = "STOP";
    private static final int    CODON_LENGTH  = 3;
    private static final String INVALID_CODON = "Invalid codon";

    enum Codon {

        AUG( METHIONINE ),
        UUU( PHENYLALANINE ),
        UUC( PHENYLALANINE ),
        UUA( LEUCINE ),
        UUG( LEUCINE ),
        UCU( SERINE ),
        UCC( SERINE ),
        UCA( SERINE ),
        UCG( SERINE ),
        UAU( TYROSINE ),
        UAC( TYROSINE ),
        UGU( CYSTEINE ),
        UGC( CYSTEINE ),
        UGG( TRYPTOPHAN ),
        UAA( STOP ),
        UAG( STOP ),
        UGA( STOP );

        private final String protein;

        Codon( String protein ){
            this.protein = protein;
        }

        public String getProtein() {
            return protein;
        }

        static Codon fromValue( String value ){
            Codon codon;
            try {
                codon = Codon.valueOf( value );
            } catch( IllegalArgumentException iae ){
                throw new IllegalArgumentException( INVALID_CODON );
            }
            return codon;
        }

    }

    List<String> translate(String rnaSequence) {
        List<String> result = new ArrayList<>();
        for( int i = 0; i < rnaSequence.length(); i+=CODON_LENGTH ){
            Codon codon = next( rnaSequence, i );
            if ( STOP.equals( codon.getProtein() ) ) {
                break;
            }
            result.add( codon.getProtein() );
        }
        return result;
    }

    private Codon next( String rnaSequence, int i ){
        if( i + CODON_LENGTH > rnaSequence.length() ){
            throw new IllegalArgumentException( INVALID_CODON );
        }
        return Codon.fromValue( rnaSequence.substring( i, i+CODON_LENGTH ) );
    }

}
