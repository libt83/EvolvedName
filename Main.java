/**
 * The controller for the EvolvedNames simulation.
 * @author Brandon Semba
 * @version 1.26.15
 */
public class Main
{
    /**
     * The main driver for the string evolving simulation.
     * @param args is ignored.
     */
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        Population pop = new Population(100, .05);
        int genCount = 0;
        while(pop.mostFit.fitness() > 0)
        {
            pop.day();
            ++genCount;
        }
        
        System.out.println("Generation Count: " + genCount);
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " milliseconds");
    }
}
