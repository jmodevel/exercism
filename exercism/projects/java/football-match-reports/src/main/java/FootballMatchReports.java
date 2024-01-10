public class FootballMatchReports {    

    private static final String GOALKEEPER  = "goalie";
    private static final String LEFT_BACK   = "left back";
    private static final String CENTER_BACK = "center back";
    private static final String RIGHT_BACK  = "right back";
    private static final String MIDFIELDER  = "midfielder";
    private static final String LEFT_WING   = "left wing";
    private static final String STRIKER     =  "striker";
    private static final String RIGHT_WING  = "right wing";

    private FootballMatchReports() {
        throw new IllegalStateException("Utility class");
    }

    public static String onField(int shirtNum) {
        return switch (shirtNum) {
            case 1       -> GOALKEEPER;
            case 2       -> LEFT_BACK;
            case 3, 4    -> CENTER_BACK;
            case 5       -> RIGHT_BACK;
            case 6, 7, 8 -> MIDFIELDER;
            case 9       -> LEFT_WING;
            case 10      -> STRIKER;
            case 11      -> RIGHT_WING;
            default      -> throw new IllegalArgumentException();
        };
    }

}
