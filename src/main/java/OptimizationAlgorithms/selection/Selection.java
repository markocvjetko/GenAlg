package OptimizationAlgorithms.selection;

import OptimizationAlgorithms.individual.Population;
import OptimizationAlgorithms.individual.Individual;


public interface Selection {
    public Individual select(Population population);
}
