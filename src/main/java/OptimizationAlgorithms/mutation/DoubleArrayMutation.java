package OptimizationAlgorithms.mutation;

import OptimizationAlgorithms.individual.DoubleArrayIndividual;

public interface DoubleArrayMutation extends Mutation<DoubleArrayIndividual>{

    void mutate(DoubleArrayIndividual individual);
}
