import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ledger {

    private enum Currency {

        USD("$"), EUR("€");

        private final String symbol;

        Currency( String symbol ){
            this.symbol = symbol;
        }

    }

    private static final String EN_US = "en-US";
    private static final String NL_NL = "nl-NL";

    private static final int DESCRIPTION_MAX_LENGTH = 25;

    private static final Map<String, LocaleFormat> FORMATS = Map.of(
        EN_US, new LocaleFormat( "MM/dd/yyyy", ".", ",",
            "{currency}", "({currency}", " ", ")"),
        NL_NL, new LocaleFormat( "dd/MM/yyyy", ",", ".",
            " {currency} ", " {currency} -", " ", " " )
    );

    private static final Map<String, LocaleHeader> HEADERS = Map.of(
        EN_US, new LocaleHeader( "Date", "Description", "Change" ),
        NL_NL, new LocaleHeader( "Datum", "Omschrijving", "Verandering" )
    );

    public LedgerEntry createLedgerEntry(String d, String desc, int c) {
        LedgerEntry le = new LedgerEntry();
        le.setChange( c );
        le.setDescription( desc );
        le.setLocalDate( LocalDate.parse( d ) );
        return le;
    }

    public String format( String cur, String loc, LedgerEntry[] entries) {

        if( Arrays.stream( Currency.values() ).noneMatch(c-> c.name().equals( cur ) ) ) {
            throw new IllegalArgumentException("Invalid currency");
        }
        if (!loc.equals( EN_US ) && !loc.equals( NL_NL )) {
            throw new IllegalArgumentException("Invalid locale");
        }
        LocaleHeader localeHeader = HEADERS.get( loc );

        String headers = String.format( "%-10s | %-25s | %-13s",
            localeHeader.date, localeHeader.description, localeHeader.change
        );

        List<LedgerEntry> all = sortLedgerEntries( entries );
        return all.stream()
            .map( e -> formatLedgerEntry( e, FORMATS.get( loc ), Currency.valueOf( cur ).symbol ) )
            .collect( Collectors.joining( "", headers, "" ) );
    }

    private static String formatLedgerEntry( LedgerEntry e, LocaleFormat localeFormat, String currency ){
        return String.format( "\n%s | %-25s | %13s",
            e.getLocalDate().format( DateTimeFormatter.ofPattern( localeFormat.datePattern ) ),
            e.getDescription().length() < DESCRIPTION_MAX_LENGTH ?
                e.getDescription() :
                String.format( "%s...", e.getDescription().substring( 0, DESCRIPTION_MAX_LENGTH-3 ) ),
            localeFormat.format( e.getChange(), currency )
        );
    }

    private static List<LedgerEntry> sortLedgerEntries(LedgerEntry[] entries) {
        return Stream.concat(
            Arrays.stream( entries ).filter( e -> e.getChange() < 0  ).sorted( Comparator.comparing( LedgerEntry::getLocalDate ) ),
            Arrays.stream( entries ).filter( e -> e.getChange() >= 0 ).sorted( Comparator.comparing( LedgerEntry::getLocalDate ) )
        ).collect( Collectors.toList() );
    }

    public static class LocaleFormat {

        private static final String CURRENCY_TOKEN = "{currency}";

        String datePattern;
        String decimalSeparator;
        String thousandSeparator;
        String positivePrefix;
        String negativePrefix;
        String positiveSuffix;
        String negativeSuffix;

        public LocaleFormat(
            String datePattern,    String decimalSeparator, String thousandSeparator,
            String positivePrefix, String negativePrefix,
            String positiveSuffix, String negativeSuffix
        ){
            this.datePattern       = datePattern;
            this.decimalSeparator  = decimalSeparator;
            this.thousandSeparator = thousandSeparator;
            this.positivePrefix    = positivePrefix;
            this.negativePrefix    = negativePrefix;
            this.positiveSuffix    = positiveSuffix;
            this.negativeSuffix    = negativeSuffix;
        }

        public String format( double change, String currency ){
            String converted = String.format("%.02f", Math.abs( change/100 ) );
            String[] parts = converted.split("\\.");
            String amount = String.format( "%,d", Integer.parseInt( parts[0] ) );
            amount = amount.replace( ",", this.thousandSeparator );
            amount = String.format( "%s%s%s",
                change >= 0 ? positivePrefix : negativePrefix,
                amount + decimalSeparator + parts[1],
                change >= 0 ? positiveSuffix : negativeSuffix );
            return amount.replace( CURRENCY_TOKEN, currency );
        }

    }

    public static class LocaleHeader {

        String date;
        String description;
        String change;

        public LocaleHeader( String date,  String description, String change ){
            this.date        = date;
            this.description = description;
            this.change      = change;
        }

    }

    public static class LedgerEntry {

        LocalDate localDate;
        String description;
        double change;

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }

    }

}
