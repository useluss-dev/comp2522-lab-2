package ca.bcit.comp2522.lab2;

/**
 * Represents an exception thrown when a {@link Dragon} attempts
 * to breathe fire without sufficient fire power.
 *
 * <p>
 * Extends {@link Exception}.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class LowFirePowerException extends Exception
{
    /**
     * Constructs a new {@code LowFirePowerException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public LowFirePowerException(final String message)
    {
        super(message);
    }

}
