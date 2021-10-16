package OptimizationAlgorithms.nn.aFunction;

public class LinearFunction implements AFunction {

    private double coefficient;

    public LinearFunction() {
        this(1);
    }

    public LinearFunction(double constant) {
        this.coefficient = constant;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double constant) {
        this.coefficient = constant;
    }

    @Override
    public double getValue(double x) {
        return coefficient * x;
    }
}
