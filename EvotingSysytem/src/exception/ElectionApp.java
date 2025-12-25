package exception;

public class ElectionApp extends RuntimeException {
    public ElectionApp(String message) {
        super(message);
    }
}
