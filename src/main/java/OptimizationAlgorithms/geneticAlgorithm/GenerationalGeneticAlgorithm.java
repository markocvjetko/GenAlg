package OptimizationAlgorithms.geneticAlgorithm;

import OptimizationAlgorithms.individual.Population;
import OptimizationAlgorithms.crossover.Crossover;
import OptimizationAlgorithms.individual.Individual;
import OptimizationAlgorithms.mutation.Mutation;
import OptimizationAlgorithms.selection.Selection;

import java.util.Random;

public class GenerationalGeneticAlgorithm extends GeneticAlgorithm{

    public GenerationalGeneticAlgorithm(Population population, Mutation mutation, Selection selection, Crossover crossover, Random rand) {
        super(population, mutation, selection, crossover, rand);
    }

    public void step(){
        Population nextPopulation = new Population();
        Individual bestIndividual = population.bestIndividual();
        nextPopulation.add(bestIndividual);
        for(int i = 0; i < population.size() - 1; i++) {
            int randIndex = rand.nextInt(population.size());
            Individual parent1 = selection.select(population);
            Individual parent2 = selection.select(population);
            Individual child = crossover.crossover(parent1, parent2);
            mutation.mutate(child);
            nextPopulation.add(child);
        }
        population = nextPopulation;
    }

    public void step(int stepAmount){
        checkStepAmount(stepAmount);
        for(int i = 1; i < stepAmount; ++i){
            step();
        }
    }

    public void checkStepAmount(int stepAmount){
        if(stepAmount <= 0){
            throw new IllegalArgumentException();
        }
    }
}
