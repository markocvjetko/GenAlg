package OptimizationAlgorithms.errorFunction;

import OptimizationAlgorithms.nn.NeuralNetwork;

import java.util.Map;
import java.util.Objects;

public class GenAlgSinLossFunction implements IFunction<double[]> {
    NeuralNetwork net;
    Map<Double, Double> sinValues;

    public GenAlgSinLossFunction(NeuralNetwork net, Map<Double, Double> sinValues) {
        this.net = Objects.requireNonNull(net);
        this.sinValues = Objects.requireNonNull(sinValues);
    }

    @Override
    public double getValue(double[] argument) {
        double result = 0;
        for(Map.Entry<Double, Double> entry : sinValues.entrySet()){
            double[] input = {entry.getKey()};
            double[] output = net.step(input, argument);
            result -= Math.pow(entry.getValue() - output[0], 2);
        }
        return result/sinValues.size();
    }


}
