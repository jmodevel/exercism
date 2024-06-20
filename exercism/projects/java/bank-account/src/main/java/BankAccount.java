class BankAccount {

    public static final String OPEN_ACCOUNT          = "Account already open";
    public static final String NOT_OPEN_ACCOUNT      = "Account not open";
    public static final String CLOSED_ACCOUNT        = "Account closed";
    public static final String NEGATIVE_AMOUNT_ERROR = "Cannot deposit or withdraw negative amount";
    public static final String INSUFFICIENT_AMOUNT   = "Cannot withdraw more money than is currently in the account";

    private int balance;
    private boolean opened;
    
    public BankAccount(){
        this.balance = 0;
        this.opened  = false;
    }
    
    void open() throws BankAccountActionInvalidException {
        if( this.opened ){
            throw new BankAccountActionInvalidException( OPEN_ACCOUNT );
        }
        this.balance = 0;
        this.opened  = true;
    }
    
    void close() throws BankAccountActionInvalidException {
        if( !this.opened ){
            throw new BankAccountActionInvalidException( NOT_OPEN_ACCOUNT );
        }
        this.opened = false;
    }
    
    synchronized int getBalance() throws BankAccountActionInvalidException {
        if( !this.opened ){
            throw new BankAccountActionInvalidException( CLOSED_ACCOUNT );
        }
        return this.balance;
    }
    
    synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        if( !this.opened ){
            throw new BankAccountActionInvalidException( CLOSED_ACCOUNT );
        }
        if( amount < 0 ){
            throw new BankAccountActionInvalidException( NEGATIVE_AMOUNT_ERROR );
        }
        this.balance += amount;
    }
    
    synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        if( !this.opened ){
            throw new BankAccountActionInvalidException( CLOSED_ACCOUNT );
        }
        if( this.balance < amount ) {
            throw new BankAccountActionInvalidException( INSUFFICIENT_AMOUNT );
        }
        if( amount < 0 ){
            throw new BankAccountActionInvalidException( NEGATIVE_AMOUNT_ERROR );
        }
        this.balance -= amount;
    }
    
}
