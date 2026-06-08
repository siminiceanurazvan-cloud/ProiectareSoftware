package lab5.advanced;

public class Lab5Adv {
    public static void main(String[] args) {
        NewIntCalculator intCalc = new NewIntCalculator();
        intCalc.init(10);
        Object intResult = intCalc.add(5).subtract(3).multiply(2).result();
        System.out.println("Rezultat Integer: " + intResult);

        DoubleCalculator doubleCalc = new DoubleCalculator();
        doubleCalc.init(10.0);
        Object doubleResult = doubleCalc.add(5.0).subtract(3.3).multiply(2.2).result();
        System.out.println("Rezultat Double: " + doubleResult);
    }
}
