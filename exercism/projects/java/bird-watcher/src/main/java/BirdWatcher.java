import java.util.Arrays;

class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        int length = birdsPerDay.length;
        return length <= 7 ?
                birdsPerDay : Arrays.copyOfRange( birdsPerDay, length -7, length );
    }

    public int getToday() {
        int length = birdsPerDay.length;
        return length > 0? birdsPerDay[ length-1 ] : 0;
    }

    public void incrementTodaysCount() {
        int length = birdsPerDay.length;
        if( length > 0 ){
            birdsPerDay[ length-1 ]++;
        }
    }

    public boolean hasDayWithoutBirds() {
        return Arrays.stream( birdsPerDay ).anyMatch( i -> i == 0 );
    }

    public int getCountForFirstDays(int numberOfDays) {
        int limit = Math.min( numberOfDays, birdsPerDay.length );
        return Arrays.stream( birdsPerDay ).limit( limit ).sum();
    }

    public int getBusyDays() {
        return (int) Arrays.stream( birdsPerDay ).filter( i -> i>=5 ).count();
    }
}
