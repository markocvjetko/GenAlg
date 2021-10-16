package OptimizationAlgorithms.individual;

public abstract class Individual<T> {
    T genotype;

    public abstract double getFitness();

    public T getGenotype() {
        return genotype;
    }

    public abstract Individual copied();

    public abstract String toString();
}
