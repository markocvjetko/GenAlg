package OptimizationAlgorithms.nn;

import OptimizationAlgorithms.nn.aFunction.AFunction;

import java.util.LinkedList;

/**
 * A multi-layer fully connected neural network. The network holds two arrays. A neuron value array and an weight array.
 * Such weight representation is created to simplify usage with some state space search optimization algoritms
 * (eg. a genetic algorithm).
 */
public class NeuralNetwork {

    private static final int MIN_NETWORK_ROW_COUNT = 2;
    private static final int MIN_NEURONS_PER_ROW_COUNT = 1;
    LinkedList<LinkedList<Neuron>> neuralNetwork;
    private int inputDimension;
    private int outputDimension;
    private double[] neuronValues;
    private AFunction[] aFunctions;
    private IndexProvider indexProvider;

    public NeuralNetwork(int[] netDimensions, AFunction[] activationFunctions) {
        dimensionCheck(netDimensions, activationFunctions.length);
        this.indexProvider = new IndexProvider();
        this.aFunctions = activationFunctions;
        this.inputDimension = netDimensions[0];
        this.outputDimension = netDimensions[netDimensions.length - 1];
        this.neuralNetwork = createNeuralNetwork(netDimensions);
        this.neuronValues = new double[getNumOfNeurons()];
    }

    private void dimensionCheck(int[] netDimensions, int activationFunctionCount) {
        netRowCountCheck(netDimensions.length);
        for (int neuronsPerRow : netDimensions) {
            neuronsPerRowCountCheck(neuronsPerRow);
        }
        aFunctionCountCheck(activationFunctionCount, netDimensions.length);
    }

    private void netRowCountCheck(int netRowCount) {
        if (netRowCount < MIN_NETWORK_ROW_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void neuronsPerRowCountCheck(int neuronsPerRow) {
        if (neuronsPerRow < MIN_NEURONS_PER_ROW_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void aFunctionCountCheck(int aFunctionCount, int netRowCount) {
        if (aFunctionCount != netRowCount - 1) {
            throw new IllegalArgumentException();

        }
    }

    /**
     * @return Dimension of input layer
     */
    public int getInputDimension() {
        return inputDimension;
    }

    /**
     * @return Dimension of output layer
     */
    public int getOutputDimension() {
        return outputDimension;
    }

    /**
     * @return Number of all neural network weights
     */
    public int getWeightDimension() {
        return indexProvider.lastWeightIndex;
    }

    private int getNumOfNeurons() {
        return indexProvider.outputIndex;
    }

    private LinkedList<LinkedList<Neuron>> createNeuralNetwork(int[] netDimensions) {
        neuralNetwork = new LinkedList<>();
        createInputRow(netDimensions[0]);
        createNeuralRows(netDimensions);
        return neuralNetwork;
    }

    private void createInputRow(int rowDimension) {
        for (int i = 0; i < rowDimension; ++i) {
            indexProvider.nextOutputIndex();
        }
    }

    private void createNeuralRows(int[] netDimensions) {
        for (int i = 0; i < netDimensions.length - 1; ++i) {
            LinkedList<Neuron> neuralRow = createRow(netDimensions[i], netDimensions[i + 1], aFunctions[i]);
            neuralNetwork.add(neuralRow);
        }
    }

    private LinkedList<Neuron> createRow(int previousRowDimension, int rowDimension, AFunction aFunction) {
        LinkedList<Neuron> neuralRow = new LinkedList<>();
        indexProvider.nextInputIndexes(previousRowDimension);
        for (int i = 0; i < rowDimension; ++i) {
            Neuron neuron = createNeuron(previousRowDimension, aFunction);
            neuralRow.add(neuron);
        }
        return neuralRow;
    }

    private Neuron createNeuron(int previousRowDimension, AFunction aFunction) {
        int freeWeightIndex = indexProvider.nextFreeWeightIndex();
        int[] weightIndexes = indexProvider.nextWeightIndexes(previousRowDimension);
        int[] inputIndexes = indexProvider.getInputIndexes();
        int outputIndex = indexProvider.nextOutputIndex();
        return new Neuron(freeWeightIndex, weightIndexes, inputIndexes, outputIndex, aFunction);
    }

    /**
     * Calculates output of the neural network from given input and weight arrays
     *
     * @param input
     * @param weights
     * @return
     */
    public double[] step(double[] input, double[] weights) {
        weightsLengthCheck(weights.length);
        inputLengthCheck(input.length);
        setInput(input);
        calculateNeuronValues(weights);
        return outputToArray();
    }

    private void weightsLengthCheck(int weightsLength) {
        if (weightsLength != indexProvider.lastWeightIndex) {
            throw new IllegalArgumentException();
        }
    }

    private void inputLengthCheck(int inputLength) {
        if (inputLength != inputDimension) {
            throw new IllegalArgumentException();
        }
    }

    private void setInput(double[] input) {
        for (int i = 0; i < input.length; ++i) {
            neuronValues[i] = input[i];
        }
    }

    private void calculateNeuronValues(double[] weights) {
        for (LinkedList<Neuron> row : neuralNetwork) {
            for (Neuron neuron : row) {
                neuron.calculateOutput(weights, neuronValues);
            }
        }
    }

    private double[] outputToArray() {
        double[] output = new double[outputDimension];
        for (int i = 0; i < outputDimension; ++i) {
            output[i] = neuronValues[neuronValues.length - outputDimension + i];
        }
        return output;
    }

    /**
     * Helper class, assigns each neuron its input, weight and output indexes.
     */
    private class IndexProvider {

        int lastInputIndex;
        int[] inputIndexes;

        int lastWeightIndex;
        int[] weightIndexes;

        int outputIndex;

        private int[] nextInputIndexes(int neuralRowDimension) {
            inputIndexes = new int[neuralRowDimension];
            for (int i = 0; i < neuralRowDimension; ++i) {
                inputIndexes[i] = lastInputIndex++;
            }
            return inputIndexes;
        }

        private int[] getInputIndexes() {
            return inputIndexes;
        }

        private int nextOutputIndex() {
            return outputIndex++;
        }

        private int nextFreeWeightIndex() {
            return lastWeightIndex++;
        }

        private int[] nextWeightIndexes(int neuralRowDimension) {

            weightIndexes = new int[neuralRowDimension];
            for (int i = 0; i < neuralRowDimension; ++i) {
                weightIndexes[i] = lastWeightIndex++;
            }
            return weightIndexes;
        }
    }
}
