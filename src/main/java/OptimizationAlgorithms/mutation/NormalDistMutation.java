package OptimizationAlgorithms.mutation;

import OptimizationAlgorithms.individual.DoubleArrayIndividual;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Random;

public class NormalDistMutation implements DoubleArrayMutation{
    Random rand;
    NormalDistribution normalDistribution;

    public NormalDistMutation(Random rand, double mi, double sigma){
        this.rand = rand;
        normalDistribution = new NormalDistribution(mi, sigma);
    }

    public void mutate(DoubleArrayIndividual individual) {
        for (int i = 0; i < individual.getGenotype().length; ++i){
            if(rand.nextDouble() < 0.1)
                individual.getGenotype()[i] += normalDistribution.sample();
        }
    }
}
