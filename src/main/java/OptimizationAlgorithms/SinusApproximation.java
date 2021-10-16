package OptimizationAlgorithms;

import OptimizationAlgorithms.errorFunction.GenAlgSinLossFunction;
import OptimizationAlgorithms.errorFunction.IFunction;
import OptimizationAlgorithms.crossover.Crossover;
import OptimizationAlgorithms.crossover.SinglePointDoubleArrayCrossover;
import OptimizationAlgorithms.geneticAlgorithm.GenerationalGeneticAlgorithm;
import OptimizationAlgorithms.geneticAlgorithm.GeneticAlgorithm;
import OptimizationAlgorithms.individual.DoubleArrayIndividual;
import OptimizationAlgorithms.individual.Individual;
import OptimizationAlgorithms.individual.Population;
import OptimizationAlgorithms.mutation.Mutation;
import OptimizationAlgorithms.mutation.NormalDistMutation;
import OptimizationAlgorithms.nn.NeuralNetwork;
import OptimizationAlgorithms.nn.aFunction.AFunction;
import OptimizationAlgorithms.nn.aFunction.SigmoidFunction;
import OptimizationAlgorithms.selection.Selection;
import OptimizationAlgorithms.selection.TournamentSelection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SinusApproximation {
    public static void main(String[] args) throws IOException {

        Random random = new Random();
        GeneticAlgorithm genAlg = createGenAlg(random);
        for(int i = 0; i < 10000; ++i){
            genAlg.step();
            Population population = genAlg.getPopulation();
            Individual best = population.bestIndividual();
            System.out.println("best of " + i + ". generation = " + best.getFitness());
        }
    }

    private static Population createPopulation(int popSize) throws IOException {
        Population population = new Population();
        NeuralNetwork net = createNet();
        BufferedReader br = new BufferedReader(new FileReader("sinus.txt"));
        String line = br.readLine();
        Map<Double, Double> map = new HashMap<>();
        while(line != null){
            String[] keyVal = line.split(", ");
            double key = Double.parseDouble(keyVal[0]);
            double val = Double.parseDouble(keyVal[1]);
            map.put(key, val);
            line = br.readLine();
        }
        br.close();

        IFunction function = new GenAlgSinLossFunction(net, map);
        Random rand = new Random();
        for (int i = 0; i < popSize; ++i) {
            population.add(randChild(rand, function, net.getWeightDimension()));
        }
        return population;
    }

    private static Individual randChild(Random rand, IFunction function, int weightDimension) {
        double[] weights = new double[weightDimension];
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = rand.nextDouble() * 2 - 1;
        }
        return new DoubleArrayIndividual(weights, function);
    }

    private static GeneticAlgorithm createGenAlg(Random random) throws IOException {

        Population population = createPopulation(400);
        Mutation mutation = new NormalDistMutation(random, 0, 1);
        Selection selection = new TournamentSelection(3, random);
        Crossover crossover = new SinglePointDoubleArrayCrossover(random);
        return new GenerationalGeneticAlgorithm(population, mutation, selection, crossover, random);


    }

    private static NeuralNetwork createNet() {
        int[] netDimensions = {1, 5, 3, 1};
        AFunction[] aFunctions = {new SigmoidFunction(),
                new SigmoidFunction(),
                new SigmoidFunction()
        };
        return new NeuralNetwork(netDimensions, aFunctions);
    }
}
