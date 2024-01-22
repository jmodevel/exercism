import java.util.stream.Collectors;

class RnaTranscription {

    enum Nucleotide {

        G ("C"),
        C ("G"),
        T ("A"),
        A ("U");

        private final String complement;

        Nucleotide( String complement ){
            this.complement = complement;
        }

        public String getComplement() {
            return complement;
        }

    }

    String transcribe( String dnaStrand ) {
        return dnaStrand.chars()
                .mapToObj( n -> Nucleotide.valueOf( Character.toString(n) ).getComplement() )
                .collect(Collectors.joining() );
    }

}
