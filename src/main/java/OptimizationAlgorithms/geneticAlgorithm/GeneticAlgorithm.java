package OptimizationAlgorithms.geneticAlgorithm;

import OptimizationAlgorithms.individual.Population;
import OptimizationAlgorithms.crossover.Crossover;
import OptimizationAlgorithms.mutation.Mutation;
import OptimizationAlgorithms.selection.Selection;

import java.util.Random;

    public abstract class GeneticAlgorithm {
    Population population;
    Mutation mutation;
    Selection selection;
    Crossover crossover;
    Random rand;

    GeneticAlgorithm(Population population,
                     Mutation mutation,
                     Selection selection,
                     Crossover crossover,
                     Random rand) {
        this.population = population;
        this.mutation = mutation;
        this.selection = selection;
        this.crossover = crossover;
        this.rand = rand;
    }

    public Random getRand() {
        return rand;
    }

    public Population getPopulation() {
        return population;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public Selection getSelection() {
        return selection;
    }

    public Crossover getCrossover() {
        return crossover;
    }

    public abstract void step();

    public abstract void step(int stepAmount);

}
