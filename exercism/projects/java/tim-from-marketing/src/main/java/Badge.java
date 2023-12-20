class Badge {

    private static final String OWNER = "OWNER";

    public String print(Integer id, String name, String department) {
        StringBuilder result = new StringBuilder();
        result.append( (id == null)? "" : String.format("[%s] - ", id) )
                .append( name )
                .append( " - " )
                .append( (department == null)?
                        OWNER : String.format("%S", department) );
        return result.toString();
    }

}
