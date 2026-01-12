package bankingapp.exception;

public class AccountValidationException extends BankException {
    public AccountValidationException(String message) {
        super(message);
    }
}
