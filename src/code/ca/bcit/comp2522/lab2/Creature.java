package ca.bcit.comp2522.lab2;

/**
 * Represents a fantasy creature with a name, date of birth, and health status.
 * <p>
 * A {@code Creature} can take damage, be healed, and report details such as its age,
 * current health, and whether it is alive. The class enforces validation rules for
 * name, date of birth (cannot be in the future), and health (must be within valid limits).
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

    private final String name;
    private final Date dateOfBirth;
    private int health;

    /**
     * Constructs a new {@code Creature} with the specified name, date of birth, and health.
     *
     * @param name        the name of the creature, must not be null or blank
     * @param dateOfBirth the date of birth of the creature, must not be null or in the future
     * @param health      the initial health of the creature, must be between {@link #MIN_HEALTH} and
     *                    {@link #MAX_HEALTH}
     * @throws IllegalArgumentException if validation of name, dateOfBirth, or health fails
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
     * Validates that the provided name is not null or blank.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is null or blank
     */
    private static void validateName(final String name)
    {
        if (name == null ||
            name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

    /**
     * Validates that the provided date is not null and not in the future.
     *
     * @param date the date to validate
     * @throws IllegalArgumentException if the date is null or in the future
     */
    private static void validateDate(final Date date)
    {
        Date now;
        if (date == null)
        {
            throw new IllegalArgumentException("ca.bcit.comp2522.lab2.Date cannot be null");
        }

        now = new Date(2025, 9, 22);
        if (date.getYear() > now.getYear())
        {
            throw new IllegalArgumentException("The provided date must not be in the future.");
        }

        if (date.getYear() == now.getYear() &&
            date.getMonth() > now.getMonth())
        {
            throw new IllegalArgumentException("The provided date must not be in the future.");
        }

        if (date.getMonth() == now.getMonth() &&
            date.getDay() > now.getDay())
        {
            throw new IllegalArgumentException("The provided date must not be in the future.");
        }
    }

    /**
     * Validates that the health is within the allowed range.
     *
     * @param health the health value to validate
     * @throws IllegalArgumentException if health is less than {@link #MIN_HEALTH} or greater than {@link #MAX_HEALTH}
     */
    private static void validateHealth(final int health)
    {
        if (health < MIN_HEALTH ||
            health > MAX_HEALTH)
        {
            throw new IllegalArgumentException("Health must be between " + MIN_HEALTH + " and " + MAX_HEALTH);
        }
    }

    /**
     * Gets the name of the creature.
     *
     * @return the creature's name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets the date of birth of the creature.
     *
     * @return the creature's date of birth
     */
    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    /**
     * Gets the current health of the creature.
     *
     * @return the creature's current health
     */
    public int getHealth()
    {
        return health;
    }


    /**
     * Calculates the age of the creature in years based on the current date.
     *
     * @return the age of the creature in years
     */
    public int getAgeYears()
    {
        Date now;
        int diff;

        now = new Date(2025, 9, 22);
        diff = now.getYear() - dateOfBirth.getYear();
        if (dateOfBirth.getMonth() > now.getMonth())
        {
            diff--;
        }

        if (dateOfBirth.getMonth() == now.getMonth() &&
            dateOfBirth.getDay() > now.getDay())
        {
            diff--;
        }

        return diff;
    }

    /**
     * Prints the details of the creature to standard output.
     * <p>
     * Details include name, date of birth, age, and health.
     * </p>
     */
    public void getDetails()
    {
        System.out.println("Name: " + name);
        System.out.println("ca.bcit.comp2522.lab2.Date of birth: " + dateOfBirth.getYYYYMMDD());
        System.out.println("Age: " + getAgeYears());
        System.out.println("Health: " + health);
    }

    /**
     * Checks if the creature is alive.
     *
     * @return {@code true} if the creature's health is greater than {@link #MIN_HEALTH}, {@code false} otherwise
     */
    public boolean isAlive()
    {
        return health > MIN_HEALTH;
    }


    /**
     * Applies damage to the creature, reducing its health.
     * <p>
     * Health cannot drop below {@link #MIN_HEALTH}.
     * </p>
     *
     * @param damage the amount of damage to apply, must be non-negative
     * @throws DamageException if the damage amount is negative
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
     * Heals the creature by increasing its health.
     * <p>
     * Health cannot exceed {@link #MAX_HEALTH}.
     * </p>
     *
     * @param healAmount the amount to heal, must be non-negative
     * @throws HealingException if the healing amount is negative
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