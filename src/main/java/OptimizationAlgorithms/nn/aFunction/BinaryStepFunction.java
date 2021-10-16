package OptimizationAlgorithms.nn.aFunction;

public class BinaryStepFunction implements AFunction {

    private double treshold;

    public BinaryStepFunction() {
        this(0);
    }

    public BinaryStepFunction(double treshold) {
        this.treshold = treshold;
    }

    public void setTreshold(double treshold) {
        this.treshold = treshold;
    }

    public double getTreshold() {
        return treshold;
    }

    @Override
    public double getValue(double x) {
        if(x < treshold){
            return 0;
        }
        return 1;
    }
}