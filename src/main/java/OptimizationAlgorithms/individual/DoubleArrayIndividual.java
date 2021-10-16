package OptimizationAlgorithms.individual;

import OptimizationAlgorithms.ErrorFunction.IFunction;

import java.util.Arrays;
import java.util.Objects;

public class DoubleArrayIndividual extends Individual<double[]> {
    IFunction function;

    public DoubleArrayIndividual(double[] genotype, IFunction function){
        this.genotype = Objects.requireNonNull(genotype);
        this.function = function;
    }
    @Override
    public double getFitness() {
        return function.getValue(genotype);
    }

    public IFunction getFunction() {
        return function;
    }

    public int genomeSize(){
        return genotype.length;
    }

    public double getGene(int index){
        outOfBoundsCheck(index);
        return genotype[index];
    }

    private void outOfBoundsCheck(int index){
        if(index < 0 && index > genomeSize())
            throw new IndexOutOfBoundsException();
    }
    public Individual copied(){
        double[] genotypeCopy = Arrays.copyOf(this.genotype, this.genotype.length);
        DoubleArrayIndividual copy = new DoubleArrayIndividual(genotypeCopy, this.function);
        return copy;
    }

    @Override
    public String toString() {
        return Double.toString(this.getFitness());
//        StringBuilder sb = new StringBuilder();
//        for (double genome : genotype) {
//            sb.append(genome).append(" ");
//        }
//       return sb.toString();
    }

}
