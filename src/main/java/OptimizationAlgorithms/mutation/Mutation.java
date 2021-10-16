package OptimizationAlgorithms.mutation;


import OptimizationAlgorithms.individual.Individual;

public interface Mutation<T extends Individual> {
    void mutate(T individual);
}
