package OptimizationAlgorithms.nn.aFunction;

public class SigmoidFunction implements AFunction {

    @Override
    public double getValue(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
