package OptimizationAlgorithms.selection;

import OptimizationAlgorithms.individual.Population;
import OptimizationAlgorithms.individual.Individual;
import java.util.Random;

public class FitnessProportionateSelection implements Selection {
    Random rand;

    public FitnessProportionateSelection(Random rand) {
        this.rand = rand;
    }

    public Individual select(Population population) {

        double[] rouletteWheel = createWheel(population);
        int rouletteIndex = spinTheWheel(rouletteWheel);
        return population.get(rouletteIndex);
    }

    private double[] createWheel(Population population) {

        double[] rouletteWheel = new double[population.size()];
        double fitSum = population.getFitnessSum();

        //calculate first element
        rouletteWheel[0] = population.fitnessOf(0) / fitSum;

        //calculate other elements
        for (int i = 1; i < population.size(); ++i) {
            rouletteWheel[i] = population.fitnessOf(i) / fitSum;
            rouletteWheel[i] += rouletteWheel[i - 1];
        }
        return rouletteWheel;
    }

    private int spinTheWheel(double[] rouletteWheel) {
        double rouletteBallValue = rand.nextDouble();
        int index = 0;
        while (rouletteBallValue >= rouletteWheel[index]) {
            ++index;
        }
        return index;
    }
}
