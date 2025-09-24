package ca.bcit.comp2522.lab2;

/**
 * Demonstrates and tests interactions between different {@link Creature}
 * subclasses: {@link Dragon}, {@link Elf}, and {@link Orc}.
 *
 * <p>
 * Includes battle simulations, exception handling tests, and
 * validation of negative values for damage and healing.
 * </p>
 *
 * @author Ryan Fiset, Larry Lin
 * @version 1.0
 */
public class CreatureTest
{
    private static final int DRAGON_BIRTH_YEAR = 1974;
    private static final int DRAGON_BIRTH_MONTH = 1;
    private static final int DRAGON_BIRTH_DAY = 15;
    private static final int DRAGON_HEALTH = 95;
    private static final int DRAGON_FIRE_POWER = 80;

    private static final int ELF_BIRTH_YEAR = 1924;
    private static final int ELF_BIRTH_MONTH = 3;
    private static final int ELF_BIRTH_DAY = 22;
    private static final int ELF_HEALTH = 85;
    private static final int ELF_MANA = 45;

    private static final int ORC_BIRTH_YEAR = 1999;
    private static final int ORC_BIRTH_MONTH = 7;
    private static final int ORC_BIRTH_DAY = 10;
    private static final int ORC_HEALTH = 90;
    private static final int ORC_RAGE = 15;

    private static final int CREATURES_ARRAY_SIZE = 3;
    private static final int DRAGON_INDEX = 0;
    private static final int ELF_INDEX = 1;
    private static final int ORC_INDEX = 2;

    private static final int NEGATIVE_DAMAGE = -10;
    private static final int NEGATIVE_HEALING = -5;
    private static final int WEAK_ORC_HEALTH = 50;
    private static final int WEAK_ORC_RAGE = 3;
    private static final int MIN_FIRE_POWER_FOR_ATTACK = 10;
    private static final int MIN_MANA_FOR_SPELL = 5;

    /**
     * Entry point of the program. Runs creature creation, prints details,
     * simulates a battle, and tests exception handling.
     *
     * @param args unused command-line arguments
     */
    public static void main(final String[] args)
    {
        try
        {
            // Dragon born 50 years ago
            final Date dragonBirth = new Date(DRAGON_BIRTH_YEAR, DRAGON_BIRTH_MONTH, DRAGON_BIRTH_DAY);

            // Elf born 100 years ago
            final Date elfBirth = new Date(ELF_BIRTH_YEAR, ELF_BIRTH_MONTH, ELF_BIRTH_DAY);

            // Orc born 25 years ago
            final Date orcBirth = new Date(ORC_BIRTH_YEAR, ORC_BIRTH_MONTH, ORC_BIRTH_DAY);

            final Creature[] creatures =
                {
                    new Dragon("Smaug", dragonBirth, DRAGON_HEALTH, DRAGON_FIRE_POWER),
                    new Elf("Legolas", elfBirth, ELF_HEALTH, ELF_MANA),
                    new Orc("Grommash", orcBirth, ORC_HEALTH, ORC_RAGE)
                };

            System.out.println("=== CREATURE DETAILS ===");
            for (final Creature creature : creatures)
            {
                System.out.println("\n--- " + creature.getName() + " ---");
                creature.getDetails();

                // Use instanceof and getClass() to determine exact type
                System.out.println("Class: " + creature.getClass().getSimpleName());
                if (creature instanceof Dragon)
                {
                    System.out.println("Type: Dragon (instanceof check)");
                }
                else if (creature instanceof Elf)
                {
                    System.out.println("Type: Elf (instanceof check)");
                }
                else if (creature instanceof Orc)
                {
                    System.out.println("Type: Orc (instanceof check)");
                }
            }

            System.out.println("\n\n=== BATTLE BEGINS! ===");

            final Dragon dragon = (Dragon) creatures[DRAGON_INDEX];
            final Elf elf = (Elf) creatures[ELF_INDEX];
            final Orc orc = (Orc) creatures[ORC_INDEX];

            // Dragon attacks Elf
            try
            {
                System.out.println("\n--- Dragon's Turn ---");
                dragon.breatheFire(elf);
                System.out.println("Elf's health after fire attack: " + elf.getHealth());
            }
            catch (final LowFirePowerException e)
            {
                System.out.println("Dragon failed to breathe fire: " + e.getMessage());
            }

            // Elf attacks Orc
            try
            {
                System.out.println("\n--- Elf's Turn ---");
                elf.castSpell(orc);
                System.out.println("Orc's health after spell: " + orc.getHealth());
            }
            catch (final LowManaException e)
            {
                System.out.println("Elf failed to cast spell: " + e.getMessage());
            }

            // Orc attacks Dragon
            try
            {
                System.out.println("\n--- Orc's Turn ---");
                orc.berserk(dragon);
                System.out.println("Dragon's health after berserk: " + dragon.getHealth());
            }
            catch (final LowRageException e)
            {
                System.out.println("Orc failed to go berserk: " + e.getMessage());
            }

            // Test exception handling with invalid operations
            System.out.println("\n\n=== TESTING EXCEPTION HANDLING ===");

            // Test negative damage (unchecked exception)
            try
            {
                System.out.println("\nTesting negative damage...");
                dragon.takeDamage(NEGATIVE_DAMAGE);
            }
            catch (final DamageException e)
            {
                System.out.println("Caught DamageException: " + e.getMessage());
            }

            // Test negative healing (unchecked exception)
            try
            {
                System.out.println("\nTesting negative healing...");
                elf.heal(NEGATIVE_HEALING);
            }
            catch (final HealingException e)
            {
                System.out.println("Caught HealingException: " + e.getMessage());
            }

            // Drain dragon's fire power and try to breathe fire
            System.out.println("\nDraining dragon's fire power...");
            while (dragon.getFirePower() >= MIN_FIRE_POWER_FOR_ATTACK)
            {
                try
                {
                    dragon.breatheFire(elf);
                }
                catch (final LowFirePowerException e)
                {
                    System.out.println("Caught LowFirePowerException: " + e.getMessage());
                    break;
                }
            }

            // Try to breathe fire with insufficient power
            try
            {
                System.out.println("\nTrying to breathe fire with low power...");
                dragon.breatheFire(elf);
            }
            catch (final LowFirePowerException e)
            {
                System.out.println("Caught LowFirePowerException: " + e.getMessage());
            }

            // Drain elf's mana and try to cast spell
            System.out.println("\nDraining elf's mana...");
            while (elf.getMana() >= MIN_MANA_FOR_SPELL)
            {
                try
                {
                    elf.castSpell(orc);
                }
                catch (final LowManaException e)
                {
                    System.out.println("Caught LowManaException: " + e.getMessage());
                    break;
                }
            }

            // Create an orc with low rage and try berserk
            try
            {
                System.out.println("\nCreating low-rage orc and testing berserk...");
                final Orc weakOrc = new Orc("WeakOrc", orcBirth, WEAK_ORC_HEALTH, WEAK_ORC_RAGE);
                weakOrc.berserk(dragon);
            }
            catch (final LowRageException e)
            {
                System.out.println("Caught LowRageException: " + e.getMessage());
            }

            System.out.println("\n\n=== FINAL STATUS ===");
            for (final Creature creature : creatures)
            {
                System.out.println("\n" + creature.getName() + " - Health: " + creature.getHealth() +
                    " - Status: " + (creature.isAlive() ? "Alive" : "Dead"));
            }

        }
        catch (final IllegalArgumentException e)
        {
            System.out.println("Invalid creature creation: " + e.getMessage());
        }
        catch (final Exception e)
        {
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
