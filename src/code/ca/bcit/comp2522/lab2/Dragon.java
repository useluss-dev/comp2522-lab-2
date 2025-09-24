package ca.bcit.comp2522.lab2;


/**
 * Represents a dragon, a type of {@link Creature} that has fire power
 * in addition to standard creature attributes such as name, date of birth,
 * and health.
 * <p>
 * A {@code Dragon} can breathe fire on other creatures, causing them damage,
 * and can restore its fire power. Fire power is consumed when
 * breathing fire and is bounded by defined minimum and maximum values.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Dragon extends Creature
{
    private static final int MIN_FIRE_POWER = 0;
    private static final int MAX_FIRE_POWER = 100;
    private static final int FIRE_POWER_COST = 10;
    private static final int FIRE_POWER_DAMAGE = 20;

    private int firePower;

    /**
     * Constructs a new {@code Dragon} with the given attributes.
     *
     * @param name      the name of the dragon, must not be null or blank
     * @param birthDate the date of birth of the dragon, must not be null or in the future
     * @param age       the initial health of the dragon, must be between {@link Creature#MIN_HEALTH}
     *                  and {@link Creature#MAX_HEALTH}
     * @param firePower the initial fire power of the dragon, must be between {@link #MIN_FIRE_POWER}
     *                  and {@link #MAX_FIRE_POWER}
     * @throws IllegalArgumentException if firePower is outside the valid range, or if any
     *                                  arguments to the {@code Creature} constructor are invalid
     */
    public Dragon(String name, Date birthDate, int age, int firePower)
    {
        super(name, birthDate, age);
        validateFirePower(firePower);
        this.firePower = firePower;
    }

    /**
     * Validates that the provided fire power is within the allowed range.
     *
     * @param firePower the fire power value to validate
     * @throws IllegalArgumentException if fire power is less than {@link #MIN_FIRE_POWER}
     *                                  or greater than {@link #MAX_FIRE_POWER}
     */
    private static void validateFirePower(final int firePower)
    {
        if (firePower < MIN_FIRE_POWER ||
            firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power must be between " + MIN_FIRE_POWER + " and " + MAX_FIRE_POWER);
        }
    }

    /**
     * Prints the details of the dragon to standard output.
     * <p>
     * Details include all creature information plus fire power.
     * </p>
     */
    @Override
    public void getDetails()
    {
        super.getDetails();
        System.out.println("Fire power: " + firePower);
    }

    /**
     * Gets the current fire power of the dragon.
     *
     * @return the dragon's fire power
     */
    public int getFirePower()
    {
        return firePower;
    }

    /**
     * Breathes fire on a target creature, reducing the dragon's fire power
     * and dealing damage to the target.
     *
     * @param target the creature to breathe fire on, must not be null
     * @throws LowFirePowerException if the dragon does not have enough fire power
     *                               to perform the attack
     */
    public void breatheFire(final Creature target) throws LowFirePowerException
    {
        if (firePower < FIRE_POWER_COST)
        {
            throw new LowFirePowerException("Insufficient fire power to breathe fire");
        }
        firePower -= FIRE_POWER_COST;
        target.takeDamage(FIRE_POWER_DAMAGE);
        System.out.println(getName() + " breathes fire on " + target.getName() + " for " + FIRE_POWER_DAMAGE + " " +
            "damage");
    }

    /**
     * Restores fire power to the dragon by the specified amount.
     * <p>
     * Fire power cannot exceed {@link #MAX_FIRE_POWER}.
     * </p>
     *
     * @param amount the amount of fire power to restore, must be non-negative
     * @throws IllegalArgumentException if the amount is negative
     */
    public void restoreFirePower(final int amount)
    {
        if (amount < MIN_FIRE_POWER)
        {
            throw new IllegalArgumentException("Restore fire power cannot be negative");
        }

        firePower += amount;
        if (firePower > MAX_FIRE_POWER)
        {
            firePower = MAX_FIRE_POWER;
        }
    }
}