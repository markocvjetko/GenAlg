package OptimizationAlgorithms.crossover;

import OptimizationAlgorithms.individual.DoubleArrayIndividual;

import java.util.Random;

public class SinglePointDoubleArrayCrossover implements Crossover<DoubleArrayIndividual>{
    private Random rand;

    public SinglePointDoubleArrayCrossover(Random rand){
        this.rand = rand;
    }

    public DoubleArrayIndividual crossover(DoubleArrayIndividual parent1,
                                          DoubleArrayIndividual parent2){
        genomeSizeCheck(parent1, parent2);
        double[] childGenotype = createGenotype(parent1, parent2);
        return new DoubleArrayIndividual(childGenotype, parent1.getFunction());
    }

    public double[] createGenotype(DoubleArrayIndividual parent1,
                                   DoubleArrayIndividual parent2){

        double[] childGenotype = new double[parent1.genomeSize()];
        int cutoffPoint = rand.nextInt(parent1.genomeSize());

        //copy first parent genome
        for(int i = 0; i < cutoffPoint; ++i){
            childGenotype[i] = parent1.getGenotype()[i];
        }

        //second parent genome
        for(int i = cutoffPoint; i < parent1.genomeSize(); ++i){
            childGenotype[i] = parent2.getGenotype()[i];
        }
        return childGenotype;
    }

    public void genomeSizeCheck(DoubleArrayIndividual parent1,
                                DoubleArrayIndividual parent2){
        if(parent1.genomeSize() != parent2.genomeSize()){
            throw new IllegalArgumentException();
        }
    }
}
