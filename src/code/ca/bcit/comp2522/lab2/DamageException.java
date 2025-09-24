package ca.bcit.comp2522.lab2;

/**
 * Represents an exception thrown when an invalid damage value is applied
 * to a {@link Creature}. This occurs when the damage is less than the
 * minimum allowed value.
 *
 * <p>
 * Extends {@link RuntimeException}.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class DamageException extends RuntimeException
{
    /**
     * Constructs a new {@code DamageException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public DamageException(final String message)
    {
        super(message);
    }
}
