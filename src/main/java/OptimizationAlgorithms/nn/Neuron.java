package OptimizationAlgorithms.nn;

import OptimizationAlgorithms.nn.aFunction.AFunction;

import java.util.Objects;

/**
 * Representation of a single neural network neuron. Each neuron holds
 */
public class Neuron {

    private int freeWeightIndex;
    private int[] weightIndexes;
    private int[] inputIndexes;
    private int outputIndex;
    private AFunction activationFunction;

    Neuron(int freeWeightIndex, int[] weightIndexes, int inputIndexes[], int outputIndex, AFunction activationFunction) {
        weightAndInputLengthEqualsCheck(inputIndexes.length, weightIndexes.length);
        this.freeWeightIndex = freeWeightIndex;
        this.weightIndexes = Objects.requireNonNull(weightIndexes);
        this.inputIndexes = Objects.requireNonNull(inputIndexes);
        this.outputIndex = outputIndex;
        this.activationFunction = Objects.requireNonNull(activationFunction);
    }

    private void weightAndInputLengthEqualsCheck(int inputIndexesLength, int weightIndexesLength) {
        if (inputIndexesLength != weightIndexesLength) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Calculates neuron output value. The neuron accesses relevant weights and neuron values from given arrays to
     * perform the calculation.
     * @param weights
     * @param neuronValues
     */
    public void calculateOutput(double[] weights, double[] neuronValues) {
        double weightInputSum = weights[freeWeightIndex];
        for (int i = 0; i < inputIndexes.length; ++i) {
            weightInputSum += weights[weightIndexes[i]] * neuronValues[inputIndexes[i]];
        }

        double output = activationFunction.getValue(weightInputSum);
        neuronValues[outputIndex] = output;
    }
}
