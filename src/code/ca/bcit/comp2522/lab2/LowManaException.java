package ca.bcit.comp2522.lab2;

/**
 * Represents an exception thrown when an {@link Elf} attempts
 * to cast a spell without sufficient mana.
 *
 * <p>
 * Extends {@link Exception}.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class LowManaException extends Exception
{
    /**
     * Constructs a new {@code LowManaException} with the specified detail message.
     *
     * @param message the detail message explaining the cause of the exception
     */
    public LowManaException(final String message)
    {
        super(message);
    }
}