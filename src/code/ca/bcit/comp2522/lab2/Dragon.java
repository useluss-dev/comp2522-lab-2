package ca.bcit.comp2522.lab2;

/**
 * A Dragon is a specialized {@link Creature} that can also use and restore fire power.
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

    int firePower;

    /**
     * Create a new Dragon.
     *
     * @param name      the dragon's name; must satisfy {@link Creature} name constraints
     * @param birthDate the dragon's date of birth; must satisfy {@link Creature} date constraints
     * @param age       initial age value passed to the {@link Creature} constructor
     * @param firePower initial fire power; must be between {@value #MIN_FIRE_POWER} and {@value #MAX_FIRE_POWER}
     * @throws IllegalArgumentException if {@code firePower} is outside allowed range or if super constructor arguments are invalid
     */
    public Dragon(String name, Date birthDate, int age, int firePower)
    {
        super(name, birthDate, age);
        validateFirePower(firePower);
        this.firePower = firePower;
    }


    /**
     * Validate that the provided fire power is within allowable bounds.
     *
     * @param firePower the value to validate
     * @throws IllegalArgumentException if {@code firePower} is less than {@value #MIN_FIRE_POWER} or greater than {@value #MAX_FIRE_POWER}
     */
    private static void validateFirePower(final int firePower)
    {
        if (firePower < MIN_FIRE_POWER || firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power must be between " + MIN_FIRE_POWER + " and " + MAX_FIRE_POWER);
        }
    }


    /**
     * Print dragon details, including inherited creature details and current fire power.
     * <p>
     * Calls {@link Creature#getDetails()} then prints "Fire power: &lt;value&gt;".
     * </p>
     */
    @Override
    public void getDetails()
    {
        super.getDetails();
        System.out.println("Fire power: " + firePower);
    }

    /**
     * Return the dragon's current fire power.
     *
     * @return current fire power in range [{@value #MIN_FIRE_POWER}, {@value #MAX_FIRE_POWER}]
     */
    public int getFirePower()
    {
        return firePower;
    }

    /**
     * Breathe fire on a target creature.
     *
     * <p>
     * The method checks that the dragon has at least {@value #FIRE_POWER_COST} fire power,
     * reduces the dragon's fire power by that cost, and applies {@value #FIRE_POWER_DAMAGE}
     * damage to the provided target by calling {@link Creature#takeDamage(int)}.
     * </p>
     *
     * @param target the creature receiving the attack; must be non-null
     * @throws LowFirePowerException if the dragon does not have enough fire power to perform the attack
     */
    public void breathFire(final Creature target) throws LowFirePowerException
    {
        if (firePower < FIRE_POWER_COST)
        {
            throw new LowFirePowerException("Insufficient fire power to breathe fire");
        }
        firePower -= FIRE_POWER_COST;
        target.takeDamage(FIRE_POWER_DAMAGE);
        System.out.println(getName() + " breathes fire on " + target.getName() + " for " + FIRE_POWER_DAMAGE + " damage");
    }

    /**
     * Restore fire power by the specified amount.
     *
     * <p>
     * The amount must be non-negative. After restoration, fire power is clamped
     * to {@value #MAX_FIRE_POWER} if it would exceed that value.
     * </p>
     *
     * @param amount the amount of fire power to restore; must be >= {@value #MIN_FIRE_POWER}
     * @throws IllegalArgumentException if {@code amount} is negative
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
