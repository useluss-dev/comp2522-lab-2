package ca.bcit.comp2522.lab2;

/**
 * Represents an exception thrown when an {@link Orc} attempts
 * to use a berserk attack without sufficient rage.
 *
 * <p>
 * Extends {@link RuntimeException}.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class LowRageException extends RuntimeException
{
    /**
     * Constructs a new {@code LowRageException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public LowRageException(final String message)
    {
        super(message);
    }
}
