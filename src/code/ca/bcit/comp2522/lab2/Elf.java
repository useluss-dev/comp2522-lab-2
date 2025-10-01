package ca.bcit.comp2522.lab2;

/**
 * Represents an elf, a type of {@link Creature} that has mana
 * in addition to standard creature attributes such as name,
 * date of birth, and health.
 * <p>
 * An {@code Elf} can cast spells on other creatures, consuming mana
 * in the process. Mana can be restored, but is bounded by defined
 * minimum and maximum values.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int MIN_MANA     = 0;
    private static final int MAX_MANA     = 50;
    private static final int MANA_COST    = 5;
    private static final int SPELL_DAMAGE = 10;

    private int mana;

    /**
     * Constructs a new {@code Elf} with the given attributes.
     *
     * @param name        the name of the elf, must not be null or blank
     * @param dateOfBirth the date of birth of the elf, must not be null or in the future
     * @param health      the initial health of the elf, must be between
     *                    {@link Creature#MIN_HEALTH} and {@link Creature#MAX_HEALTH}
     * @param mana        the initial mana of the elf, must be between {@link #MIN_MANA}
     *                    and {@link #MAX_MANA}
     * @throws IllegalArgumentException if mana is outside the valid range,
     *                                  or if any arguments to the {@code Creature}
     *                                  constructor are invalid
     */
    public Elf(final String name,
               final Date dateOfBirth,
               final int health,
               final int mana)
    {
        super(name, dateOfBirth, health);

        validateMana(mana);
        this.mana = mana;
    }

    /**
     * Validates that the provided mana is within the allowed range.
     *
     * @param mana the mana value to validate
     * @throws IllegalArgumentException if mana is less than {@link #MIN_MANA}
     *                                  or greater than {@link #MAX_MANA}
     */
    private static void validateMana(final int mana)
    {
        if (mana < MIN_MANA ||
            mana > MAX_MANA)
        {
            throw new IllegalArgumentException("Mana must be between " + MIN_MANA + " and " + MAX_MANA);
        }
    }

    /**
     * Prints the details of the elf to standard output.
     * <p>
     * Details include all creature information plus mana.
     * </p>
     */
    @Override
    public void getDetails()
    {
        super.getDetails();
        System.out.println("Mana: " + mana);
    }

    /**
     * Gets the current mana of the elf.
     *
     * @return the elf's mana
     */
    public int getMana()
    {
        return mana;
    }


    /**
     * Casts a spell on a target creature, reducing the elf's mana
     * and dealing damage to the target.
     *
     * @param target the creature to cast the spell on, must not be null
     * @throws LowManaException if the elf does not have enough mana
     *                          to cast the spell
     */
    public void castSpell(final Creature target) throws LowManaException
    {
        if (mana < MANA_COST)
        {
            throw new LowManaException("Insufficient mana to cast");
        }

        mana -= MANA_COST;
        target.takeDamage(SPELL_DAMAGE);
        System.out.println(getName() + " casts a spell on " + target.getName() + " for " + SPELL_DAMAGE + " damage!");
    }

    /**
     * Restores mana to the elf by the specified amount.
     * <p>
     * Mana cannot exceed {@link #MAX_MANA}.
     * </p>
     *
     * @param amount the amount of mana to restore, must be non-negative
     * @throws IllegalArgumentException if the amount is negative
     */
    public void restoreMana(final int amount)
    {
        if (amount < MIN_MANA)
        {
            throw new IllegalArgumentException("Restore amount cannot be negative");
        }

        mana += amount;
        if (mana > MAX_MANA)
        {
            mana = MAX_MANA;
        }
    }
}