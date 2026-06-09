package main.java.ro.ulbs.proiectaresoftware.lab6.calculator;

public class IntCalculator {
    protected int state = 0;

    public IntCalculator() {
    }

    public IntCalculator(int value) {
        this.state = value;
    }

    public IntCalculator add(int a) {
        this.state = this.state + a;
        return this;
    }

    public IntCalculator subtract(int a) {
        this.state = this.state - a;
        return this;
    }
    public IntCalculator multiply(int a) {
        this.state = this.state * a;
        return this;
    }

    public int result(){
        return this.state;
    }
}
