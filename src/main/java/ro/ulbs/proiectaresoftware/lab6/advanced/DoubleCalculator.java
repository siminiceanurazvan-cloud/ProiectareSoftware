package main.java.ro.ulbs.proiectaresoftware.lab6.advanced;

public class DoubleCalculator  extends ACalculator<Double> {
    @Override
    DoubleCalculator init() {
        state = 0.0;
        return this;
    }

    public  DoubleCalculator add(Double a) {
        state = (Double)state + a;
        return this;
    }

    public  DoubleCalculator subtract(Double a) {
        state = (Double)state - a;
        return this;
    }
    public  DoubleCalculator multiply(Double a) {
        state = (Double)state * a;
        return this;
    }
    public  DoubleCalculator divide(Double a) {
        state = (Double)state / a;
        return this;
    }
}
