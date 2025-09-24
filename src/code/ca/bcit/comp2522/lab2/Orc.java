package ca.bcit.comp2522.lab2;

/**
 * Represents an orc, a type of {@link Creature} that builds and consumes rage
 * in combat. Rage influences the orc's ability to deal damage, including a
 * berserk state that allows double damage once the rage threshold is exceeded.
 * <p>
 * An {@code Orc} can accumulate rage through combat, unleash a berserk attack
 * on other creatures, and display its details including rage level.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class Orc extends Creature
{
    private static final int MIN_RAGE = 0;
    private static final int MAX_RAGE = 30;
    private static final int RAGE_INCREASE = 5;
    private static final int RAGE_THRESHOLD = 20;
    private static final int MIN_BERSERK_RAGE = 5;
    private static final int NORMAL_DAMAGE = 15;
    private static final int DOUBLE_DAMAGE = 30;

    private int rage;

    /**
     * Constructs a new {@code Orc} with the given attributes.
     *
     * @param name        the name of the orc, must not be null or blank
     * @param dateOfBirth the date of birth of the orc, must not be null or in the future
     * @param health      the initial health of the orc, must be between
     *                    {@link Creature#MIN_HEALTH} and {@link Creature#MAX_HEALTH}
     * @param rage        the initial rage of the orc, must be between {@link #MIN_RAGE}
     *                    and {@link #MAX_RAGE}
     * @throws IllegalArgumentException if rage is outside the valid range,
     *                                  or if any arguments to the {@code Creature}
     *                                  constructor are invalid
     */
    public Orc(final String name, final Date dateOfBirth, final int health, final int rage)
    {
        super(name, dateOfBirth, health);
        if (rage < MIN_RAGE ||
            rage > MAX_RAGE)
        {
            throw new IllegalArgumentException("Rage must be between " + MIN_RAGE + " and " + MAX_RAGE);
        }

        this.rage = rage;
    }


    /**
     * Prints the details of the orc to standard output.
     * <p>
     * Details include all creature information plus rage.
     * </p>
     */
    @Override
    public void getDetails()
    {
        super.getDetails();
        System.out.println("Rage: " + rage);
    }

    /**
     * Gets the current rage of the orc.
     *
     * @return the orc's rage
     */
    public int getRage()
    {
        return rage;
    }

    /**
     * Performs a berserk attack on a target creature.
     * <p>
     * The orc must have at least {@link #MIN_BERSERK_RAGE} rage to use this ability.
     * Each use increases rage by {@link #RAGE_INCREASE}, capped at {@link #MAX_RAGE}.
     * If rage exceeds {@link #RAGE_THRESHOLD}, the orc goes berserk and deals
     * double damage.
     * </p>
     *
     * @param target the creature to attack, must not be null
     * @throws LowRageException if the orc does not have enough rage
     *                          to perform a berserk attack
     */
    public void berserk(final Creature target)
    {
        if (rage < MIN_BERSERK_RAGE)
        {
            throw new LowRageException("Insufficient rage for berserk");
        }

        rage += RAGE_INCREASE;
        System.out.println(getName() + " add rage");

        if (rage > MAX_RAGE)
        {
            rage = MAX_RAGE;
        }

        int damage = NORMAL_DAMAGE;
        if (rage > RAGE_THRESHOLD)
        {
            damage = DOUBLE_DAMAGE;
            System.out.println(getName() + " goes berserk");
        }

        target.takeDamage(damage);
        System.out.println(getName() + " deals " + damage + " damage to " + target.getName());
    }

}
