package ca.bcit.comp2522.lab2;

public class Main
{
    public static void main(String[] args)
    {
        Creature monkey = new Creature("Monkey", new Date(2024, 9, 23), 100);
        monkey.getDetails();

        System.out.println("\n");

        Dragon dragon = new Dragon("ca.bcit.comp2522.lab2.Dragon", new Date(2012, 8, 10), 100, 4);
        dragon.getDetails();

        try
        {
            dragon.breathFire(monkey);
        }
        catch (LowFirePowerException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
