package OptimizationAlgorithms.selection;

import OptimizationAlgorithms.individual.Population;
import OptimizationAlgorithms.individual.Individual;

import java.util.HashSet;
import java.util.Random;

public class TournamentSelection implements Selection {
    int k;
    Random rand;

    public TournamentSelection(int k, Random rand) {
        this.k = k;
        this.rand = rand;
    }


    @Override
    public Individual select(Population population) {
        HashSet<Individual> set = new HashSet<>();
        Individual best = null;
        while(set.size() < k){
            Individual ind = population.get(rand.nextInt(population.size()));
            set.add(ind);
            best = ind;
        }
        for(Individual ind : set){
            if(best.getFitness() < ind.getFitness()){
                best = ind;
            }
        }
        return best;
    }
}
