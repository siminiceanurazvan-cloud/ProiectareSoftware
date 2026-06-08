package lab5.advanced;

public abstract class ACalculator {
    protected Object state;

    public Object result() {
        return this.state;
    }

    public void clear() {
        this.state = null;
    }

    public abstract void init(Object value);
}
