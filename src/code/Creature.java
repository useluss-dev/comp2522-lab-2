public class Creature
{
    private final static int MIN_HEALTH = 0;
    private final static int MAX_HEALTH = 100;
    private final static int MIN_DAMAGE = 0;
    private final static int MIN_HEAL_AMOUNT = 0;

    private final String name;
    private final Date dateOfBirth;
    private int health;

    public Creature(final String name, final Date dateOfBirth, final int health)
    {
        validateName(name);
        validateDate(dateOfBirth);
        validateHealth(health);

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
    }

    private static void validateName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
    }

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
    }

    private static void validateHealth(final int health)
    {
        if (health < MIN_HEALTH || health > MAX_HEALTH)
        {
            throw new IllegalArgumentException("Health must be between " + MIN_HEALTH + " and " + MAX_HEALTH);
        }
    }

    public int getAgeYears()
    {
        Date now;
        int diff;

        now = new Date(2025, 9, 22);
        diff = now.getYear() - dateOfBirth.getYear();
        if (dateOfBirth.getMonth() < now.getMonth())
        {
            diff--;
        }
        else if (dateOfBirth.getMonth() == now.getMonth())
        {
            if (dateOfBirth.getDay() < now.getDay())
            {
                diff--;
            }
        }

        return diff;
    }

    public void getDetails()
    {
        System.out.println("Name: " + name);
        System.out.println("Date of birth: " + dateOfBirth.getYYYYMMDD());
        System.out.println("Age: " + getAgeYears());
        System.out.println("Health: " + health);
    }

    public boolean isAlive()
    {
        return health > MIN_HEALTH;
    }

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