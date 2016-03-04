import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates a genome and contains function to 
 * mutate the genome and calculate its fitness in relation to a target.
 * 
 * @author Brandon Semba
 * @version 1.25.15
 */
public class Genome
{
    /** The population's set of characters. */
    private final static char POPULATION[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
        'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '\'', '-'};
    
    /** The random number generator. */
    private final static Random RANDOM_GEN = new Random();

    private static final double FIFTY_PERCENT = .5;
    
    /** The mutation rate of the genome. */
    private final double myMutationRate;
    
    /** The value inside the genome. */
    private List<Character> myValue;
    
    /**
	 * Constructs a genome with a default value and
	 * assigns it a mutation rate.
	 * 
	 * @param mutationRate - the probability of the genome mutating.
	 */
    public Genome(final double mutationRate)
    {
        myMutationRate = mutationRate;
        myValue = new ArrayList<Character>();
        myValue.add('A');
    }
    
    /**
     * A copy constructor for copying a genome.
     * 
     * @param gene - the genome to be copied.
     */
    public Genome(final Genome gene)
    {
        this(gene.myMutationRate);
        myValue.clear();                
        myValue.addAll(gene.myValue);
    }
    
    /**
     * The method causes genomes to mutate in three different ways and mutation 
     * occurs when a generated random double is lower than the mutation rate of
     * .05. <br>
     * 
     * Mutates a genome by adding a character to a random index. <br>
     * Mutates a genome by removing a character from a random index. <br>
     * Mutates individual characters within the genome. <br>
     */
    public void mutate()
    {
        int popChar = RANDOM_GEN.nextInt(POPULATION.length);
        int charIndex;
        
        if(RANDOM_GEN.nextDouble() < myMutationRate)
        {
            charIndex = RANDOM_GEN.nextInt(myValue.size() + 1);
            if(charIndex == myValue.size())
            {
                myValue.add(POPULATION[popChar]);
            }
            else
            {
                myValue.add(charIndex, POPULATION[popChar]);
            }
        }
        
        if(RANDOM_GEN.nextDouble() < myMutationRate)
        {
            if(myValue.size() >= 2)
            {
                charIndex = RANDOM_GEN.nextInt(myValue.size());
                myValue.remove(charIndex);
            }
        }
        
        for(int i = 0; i < myValue.size(); i++)
        {
            if(RANDOM_GEN.nextDouble() < myMutationRate)
            {
                popChar = RANDOM_GEN.nextInt(POPULATION.length);
                myValue.set(i, POPULATION[popChar]);
            }
        }
    }
    
    /**
     * Creates a new genome from two parent genomes.
     * 
     * @param other - the other genome being crossed. 
     */
    public void crossover(final Genome other)
    {
        final List<Character> crossedGenome = new ArrayList<Character>();

        int counter = 0;
        while(counter < myValue.size() || counter < other.myValue.size())
        {
            if(RANDOM_GEN.nextDouble() > FIFTY_PERCENT)
            {
                if(counter > myValue.size() - 1)
                {
                    break;
                }
                else
                {
                    crossedGenome.add(myValue.get(counter++));
                }
            }
            else
            {
                if(counter > other.myValue.size() - 1)
                {
                    break;
                }
                else
                {
                    crossedGenome.add(other.myValue.get(counter++));
                }
            }
        }
        this.myValue = crossedGenome;
    }
    
    /**
     * Calculates the zero-based genetic fitness
     * between the genome and the target.
     * 
     * @return a value indicating the genome's fitness.
     */
    public Integer fitness()
    {
        final String target = Population.TARGET;
        
        int n = myValue.size();
        int m = target.length();
        int l = Math.max(n, m);
        int f = Math.abs(m - n);
        
        for(int i = 0; i < l; i++)
        {
            if(i >= target.length() && i <= myValue.size() - 1)
            {
                f++;
            }
            else if(i <= target.length() - 1 && i >= myValue.size())
            {
                f++;
            }       
            else
            {
                if(myValue.get(i) != target.charAt(i))
                {
                    f++;
                }
            }
        }               
        return f;
    }
    
    /**
     * Creates a string representation of the genome's value.
     * 
     * @return the string representation of the genome.
     */
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder(myValue.size());
        for(Character ch: myValue)
        {
            sb.append(ch);
        }
        sb.append(" ");
        sb.append(this.fitness());
        return sb.toString();
    }
}
