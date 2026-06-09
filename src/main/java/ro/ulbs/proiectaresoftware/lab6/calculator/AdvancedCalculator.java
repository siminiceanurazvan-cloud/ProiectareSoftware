package main.java.ro.ulbs.proiectaresoftware.lab6.calculator;

public class AdvancedCalculator extends IntCalculator {

    public  AdvancedCalculator(){
        super();
    }

    public  AdvancedCalculator(int a) {
        super(a);
    }

    @Override
    public AdvancedCalculator add(int a) {
        super.add(a);
        return this;
    }

    @Override
    public AdvancedCalculator subtract(int a) {
        super.subtract(a);
        return this;
    }

    @Override
    public AdvancedCalculator multiply(int a) {
        super.multiply(a);
        return this;
    }

    public AdvancedCalculator divide(int a){
        if (a != 0) {
            super.state /= a;
        }
        return this;
    }

    public AdvancedCalculator root(int n){
        if (n != 0) {
            state = (int) Math.pow(state, 1.0 / n);
        }
        return this;
    }
}
