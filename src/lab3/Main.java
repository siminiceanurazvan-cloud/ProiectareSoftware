package lab3;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("src/lab3/in.txt"));
        String continut = "";
        while (scanner.hasNextLine()) {
            continut += scanner.nextLine() + "\n";
        }
        scanner.close();

        String[] linii = continut.split("\n");

        for (String linie : linii) {
            System.out.println(linie + "\n");
        }

        for (String linie : linii) {
            System.out.println(linie.replace(".", ".\n"));
        }

        try (PrintWriter writer = new PrintWriter("src/lab3/out.txt")) {
            for (String linie : linii) {
                writer.println(linie + "\n");
            }

            for (String linie : linii) {
                writer.println(linie.replace(".", ".\n"));
            }
        }
    }
}