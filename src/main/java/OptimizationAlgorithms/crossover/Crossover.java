package OptimizationAlgorithms.crossover;

import OptimizationAlgorithms.individual.Individual;

public interface Crossover<T extends Individual> {

    public T crossover(T Parent1, T Parent2);
}
