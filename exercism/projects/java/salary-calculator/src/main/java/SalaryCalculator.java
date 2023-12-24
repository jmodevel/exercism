public class SalaryCalculator {

    private static final int DAYS_SKIPPED_THRESHOLD = 5;
    private static final double PENALTY = 0.15;

    private static final int PRODUCTS_SOLD_THRESHOLD = 20;
    private static final int MONETARY_UNITS_LOWER = 10;
    private static final int MONETARY_UNITS_UPPER = 13;

    private static final double BASE_SALARY = 1000;
    private static final double TOP_SALARY = 2000;

    public double salaryMultiplier(int daysSkipped) {
        return 1 - (( daysSkipped >= DAYS_SKIPPED_THRESHOLD )? PENALTY : 0 );
    }

    public int bonusMultiplier(int productsSold) {
        return ( productsSold >= PRODUCTS_SOLD_THRESHOLD )?
                MONETARY_UNITS_UPPER : MONETARY_UNITS_LOWER;
    }

    public double bonusForProductsSold(int productsSold) {
        return productsSold * (double) bonusMultiplier( productsSold );
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        double salary = this.salaryMultiplier( daysSkipped ) * BASE_SALARY
                + bonusForProductsSold( productsSold );
        return Math.min( salary, TOP_SALARY );
    }

}
