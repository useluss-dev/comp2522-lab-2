public class Dragon extends Creature
{
    private static final int MIN_FIRE_POWER = 0;
    private static final int MAX_FIRE_POWER = 100;
    private static final int FIRE_POWER_COST = 10;
    private static final int FIRE_POWER_DAMAGE = 20;

    int firePower;

    public Dragon(String name, Date birthDate, int age, int firePower)
    {
        super(name, birthDate, age);
        validateFirePower(firePower);
        this.firePower = firePower;
    }

    private static void validateFirePower(final int firePower)
    {
        if (firePower < MIN_FIRE_POWER || firePower > MAX_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power must be between " + MIN_FIRE_POWER + " and " + MAX_FIRE_POWER);
        }
    }

    @Override
    public void getDetails()
    {
        super.getDetails();
        System.out.println("Fire power: " + firePower);
    }

    public int getFirePower()
    {
        return firePower;
    }

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
