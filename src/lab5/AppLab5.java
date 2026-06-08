package lab5;

public class AppLab5 {
    public static void main(String[] args) {
        IntCalculator calculator = new IntCalculator(10);
        int resultA = calculator.add(5).subtract(3).multiply(2).result();
        System.out.println("a) " + resultA);

        AdvancedCalculator advanced = new AdvancedCalculator(16);
        int resultB = advanced.add(9).root(2).power(3).divide(5).result();
        System.out.println("b) " + resultB);
    }
}