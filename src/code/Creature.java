/**
 * Represents a creature with a name, date of birth and health state.
 * <p>
 * Instances validate inputs on construction and expose simple operations:
 * <ul>
 *      <li>get age in years,</li>
 *      <li>print details,</li>
 *      <li>check alive status,</li>
 *      <li>take damage,</li>
 *      <li>receive healing.</li>
 * </ul>
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Creature
{
    private final static int MIN_HEALTH = 0;
    private final static int MAX_HEALTH = 100;
    private final static int MIN_DAMAGE = 0;
    private final static int MIN_HEAL_AMOUNT = 0;
    private final static int MIN_AGE = 0;

    private final String name;
    private final Date dateOfBirth;
    private int health;

    /**
     * Construct a new Creature.
     *
     * @param name        the creature's name; must not be null or blank
     * @param dateOfBirth the creature's date of birth; must not be null or in the future
     * @param health      initial health; must be between {@value #MIN_HEALTH} and {@value #MAX_HEALTH}
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Creature(final String name, final Date dateOfBirth, final int health)
    {
        validateName(name);
        validateDate(dateOfBirth);
        validateHealth(health);

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
    }

    /**
     * Validate that the provided name is not null or blank.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if name is null or blank
     */
    private static void validateName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    /**
     * Validate that the provided date is not null and not in the future.
     *
     * <p>Uses a fixed "now" date in the current implementation.
     *
     * @param date the date to validate
     * @throws IllegalArgumentException if date is null or in the future
     */
    private static void validateDate(final Date date)
    {
        Date now;
        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null");
        }

        now = new Date(2025, 9, 22);
        if (date.getYear() > now.getYear())
        {
            throw new IllegalArgumentException("The provided date must not be in the future.");
        }
        else if (date.getYear() != now.getYear())
        {
            return;
        }

        //  Everything below we know is `date.getYear() == now.getYear()`
        if (date.getMonth() > now.getMonth())
        {
            throw new IllegalArgumentException("The provided date must not be in the future.");
        }
        else if (date.getMonth() != now.getMonth())
        {
            return;
        }

        // Everything below we know is `date.getMonth() == now.getMonth()`
        if (date.getDay() > now.getDay())
        {
            throw new IllegalArgumentException("The provided date must not be in the future.");
        }
    }

    /**
     * Validate that health is within allowed bounds.
     *
     * @param health the health value to validate
     * @throws IllegalArgumentException if health is outside [{@value #MIN_HEALTH}, {@value #MAX_HEALTH}]
     */
    private static void validateHealth(final int health)
    {
        if (health < MIN_HEALTH || health > MAX_HEALTH)
        {
            throw new IllegalArgumentException("Health must be between " + MIN_HEALTH + " and " + MAX_HEALTH);
        }
    }

    /**
     * Return the creature's age in whole years as of the fixed "now" date used in this class.
     *
     * @return age in years
     */
    public int getAgeYears()
    {
        Date now;
        int diff;

        now = new Date(2025, 9, 22);
        diff = now.getYear() - dateOfBirth.getYear();

        return diff;
    }

    /**
     * Print creature details to standard output.
     * <p>
     * Printed fields: Name, Date of birth (YYYYMMDD), Age, Health.
     * </p>
     */
    public void getDetails()
    {
        System.out.println("Name: " + name);
        System.out.println("Date of birth: " + dateOfBirth.getYYYYMMDD());
        System.out.println("Age: " + getAgeYears());
        System.out.println("Health: " + health);
    }

    /**
     * Return whether the creature is alive.
     *
     * @return true if health is greater than {@value #MIN_HEALTH}, otherwise false
     */
    public boolean isAlive()
    {
        return health > MIN_HEALTH;
    }

    /**
     * Apply damage to the creature, reducing health.
     *
     * <p>Health is clamped to {@value #MIN_HEALTH} if damage reduces it below that.</p>
     *
     * @param damage non-negative damage value
     * @throws DamageException if damage is negative
     */
    public void takeDamage(final int damage)
    {
        if (damage < MIN_DAMAGE)
        {
            throw new DamageException("Damage must not be negative");
        }

        health -= damage;

        if (health <= MIN_HEALTH)
        {
            health = MIN_HEALTH;
        }
    }

    /**
     * Heal the creature, increasing health.
     *
     * <p>Health is clamped to {@value #MAX_HEALTH} if healing raises it above that.</p>
     *
     * @param healAmount non-negative healing amount
     * @throws HealingException if healAmount is negative
     */
    public void heal(final int healAmount)
    {
        if (healAmount < MIN_HEAL_AMOUNT)
        {
            throw new HealingException("Healing amount must not be negative");
        }

        health += healAmount;

        if (health > MAX_HEALTH)
        {
            health = MAX_HEALTH;
        }
    }
}