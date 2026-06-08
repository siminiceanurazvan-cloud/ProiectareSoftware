package lab9;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Laborator9Cuvinte {
    public static void main(String[] args) {
        String text = "Acesta este un program scris in java pentru expresii lambda";
        List<String> cuvinte = Arrays.asList(text.split(" "));

        List<String> cuvinteFiltrate = cuvinte.stream()
                .filter(c -> c.length() >= 5)
                .collect(Collectors.toList());
        System.out.println("a) Numarul de cuvinte: " + cuvinteFiltrate.size());
        System.out.println("   Lista filtrata: " + cuvinteFiltrate);

        List<String> cuvinteOrdonate = cuvinteFiltrate.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("b) Lista ordonata: " + cuvinteOrdonate);

        Optional<String> cuvantCuP = cuvinte.stream()
                .filter(c -> c.startsWith("p"))
                .findFirst();
        System.out.print("c) Element care incepe cu 'p': ");
        if (cuvantCuP.isPresent()) {
            System.out.println(cuvantCuP.get());
        } else {
            System.out.println("Nu s-a gasit niciun cuvant.");
        }
    }
}