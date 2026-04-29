/**
 * A custom checked exception used to represent a "super cool" failure scenario.
 */
public class SuperCoolException extends Exception {

    /**
     * Creates a new SuperCoolException with the given message.
     *
     * @param message a description of what went wrong
     */
    public SuperCoolException(String message) {
        super(message);
    }
}
