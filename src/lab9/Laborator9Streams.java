package lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Laborator9Streams {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> numere = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numere.add(random.nextInt(21) + 5);
        }
        System.out.println("Lista initiala: " + numere);

        int suma = numere.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("a) Suma elementelor: " + suma);

        int max = numere.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow();
        int min = numere.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow();
        System.out.println("b) Maxim: " + max + ", Minim: " + min);

        List<Integer> numereFiltrate = numere.stream()
                .filter(n -> n >= 10 && n <= 20)
                .collect(Collectors.toList());
        System.out.println("c) Elemente in intervalul [10..20]: " + numereFiltrate);

        List<Double> numereDouble = numere.stream()
                .map(Integer::doubleValue)
                .collect(Collectors.toList());
        System.out.println("d) Lista transformata in Double: " + numereDouble);

        boolean contine12 = numere.stream()
                .anyMatch(n -> n == 12);
        System.out.println("e) Se gaseste valoarea 12 in lista? " + (contine12 ? "Da" : "Nu"));
    }
}