class CalculatorConundrum {

    private static final String ADDITION       = "+";
    private static final String MULTIPLICATION = "*";
    private static final String DIVISION       = "/";

    public String calculate(int operand1, int operand2, String operation) {
        if( operation == null ){
            throw new IllegalArgumentException( "Operation cannot be null" );
        }
        if( operation.isEmpty() ){
            throw new IllegalArgumentException( "Operation cannot be empty" );
        }
        int result = 0;
        switch( operation ){
            case ADDITION       -> result = ( operand1 + operand2 );
            case MULTIPLICATION -> result = ( operand1 * operand2 );
            case DIVISION       -> {
                try {
                    result = (operand1 / operand2);
                } catch( ArithmeticException ae ){
                    throw new IllegalOperationException( "Division by zero is not allowed", ae );
                }
            }
            default -> throw new IllegalOperationException(
                    String.format( "Operation '%s' does not exist", operation ) );
        }
        return String.format( "%d %s %d = %d", operand1, operation, operand2, result );
    }
}
