class ProductionRemoteControlCar implements RemoteControlCar, Comparable<ProductionRemoteControlCar>{

    private static final int UNITS_BY_DRIVE = 10;

    private int distance = 0;
    private int numberOfVictories;

    public void drive() {
        this.distance += UNITS_BY_DRIVE;
    }

    public int getDistanceTravelled() {
        return this.distance;
    }

    public int getNumberOfVictories() {
        return this.numberOfVictories;
    }

    public void setNumberOfVictories(int numberOfVictories) {
        this.numberOfVictories = numberOfVictories;
    }

    @Override
    public int compareTo(ProductionRemoteControlCar c) {
        return c.getNumberOfVictories() - this.getNumberOfVictories();
    }

    @Override
    public boolean equals( Object obj ){
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return this.compareTo( (ProductionRemoteControlCar) obj ) == 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
