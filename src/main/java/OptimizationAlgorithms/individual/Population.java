package OptimizationAlgorithms.individual;

import OptimizationAlgorithms.individual.Individual;

import java.util.*;

public class Population<T extends Individual> implements Iterable<T> {
    private LinkedList<T> population;
    private int modcount = 0;

    public Population(LinkedList<T> population) {
        modcount = 0;
        this.population = population;
    }

    public Population() {
        this(new LinkedList<>());
    }

    public double fitnessOf(int index) {
        return this.get(index).getFitness();
    }

    public double getFitnessSum() {
        double fitnessSum = 0;
        for (Individual individual : population) {
            fitnessSum += individual.getFitness();
        }
        return fitnessSum;
    }

    public Individual bestIndividual(){
        Individual best = population.getFirst();
        for(Individual individual : population){
            if(best.getFitness() < individual.getFitness()){
                best = individual;
            }
        }
        return best;
    }
    public int size() {
        return population.size();
    }

    public boolean add(T individual) {
        modcount++;
        return population.add(individual);
    }

    public boolean remove(T individual) {
        if (population.remove(individual)) {
            modcount++;
            return true;
        }
        return false;
    }

    public T get(int index) {
        return population.get(index);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int index;
        int creationModCount = modcount;

        @Override
        public boolean hasNext() {
            checkModCount();
            return index != population.size();
        }

        @Override
        public T next() {
            if (hasNext()) {
                return population.get(index++);
            }
            throw new NoSuchElementException();
        }

        private void checkModCount() {
            if (creationModCount != modcount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
