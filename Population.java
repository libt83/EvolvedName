import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class creates a population of Genomes.
 * 
 * @author Brandon Semba
 * @version 1.26.15
 */
public class Population
{
    public static final String TARGET = "BRANDON MICHAEL SEMBA";
    
    private static final Double FIFTY_PERCENT = .5;
    
    private static final Random RANDOM_GEN = new Random();

    /** The List containing all the genomes of the population. */
    private List<Genome> geneList;

    /** The most fit genome within the population. */
    public Genome mostFit;

    /**
     * Constructs a population containing a fixed number of default genomes.
     */
    Population(final Integer numGenomes, final Double mutationRate)
    {
        geneList = new ArrayList<Genome>();
        while (geneList.size() < numGenomes)
        {   
            geneList.add(new Genome(mutationRate));
        }
        mostFit = geneList.get(0);
    }

    /**
     * Handles the breeding cycles of the population.
     */
    public void day()
    {
        geneList.subList(50, 100).clear();
        
        while(geneList.size() < 100)
        {
            Genome randomGene = geneList.get(RANDOM_GEN.nextInt(50));
            Genome rdmGenome =  new Genome(randomGene);
            
            if(RANDOM_GEN.nextDouble() < FIFTY_PERCENT)
            {
                rdmGenome.mutate();
                geneList.add(rdmGenome);
            }
            else
            {
                Genome toCross = geneList.get(RANDOM_GEN.nextInt(50));
                rdmGenome.crossover(toCross);
                rdmGenome.mutate();
                geneList.add(rdmGenome);
            }
        }
        geneList = selectionSort(geneList);
        mostFit = geneList.get(0);
        System.out.println(mostFit);
    }
    
    /**
     * Used to sort the genome's in the population by their fitness level.
     * @param array - the list of genomes to be sorted
     * @return the sorted list of genomes.
     */
    public List<Genome> selectionSort(final List<Genome> array) 
    {
        for (int i = 0; i < array.size() - 1; i++)
        {
            for (int j = i + 1; j < array.size(); j++)
            {
                if (array.get(i).fitness() > array.get(j).fitness()) 
                {
                    Genome temp = array.get(j);
                    array.set(j, array.get(i));
                    array.set(i, temp);
                }
            }
        }
        return array;
    }
    
    /**
     * Displays all of the genomes that are currently contained within
     * the population.
     */
    public void displayPop()
    {
        System.out.print(geneList.toString());
    }
}
