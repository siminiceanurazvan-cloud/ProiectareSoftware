import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Student> listaStudenti = new ArrayList<>();

        File fisierIntrare = new File("studenti_in.txt");

        try (Scanner scanner = new Scanner(fisierIntrare)) {
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                if (linie.trim().isEmpty()) continue;

                String[] date = linie.split(",");

                String nrMatricol = date[0].trim();
                String prenume = date[1].trim();
                String nume = date[2].trim();
                String formatie = date[3].trim();

                listaStudenti.add(new Student(nrMatricol, nume, prenume, formatie));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Eroare: Nu s-a gasit fișierul 'studenti_in.txt'.");
            return;
        }

        System.out.println("Studenți cititi");
        for (Student s : listaStudenti) {
            System.out.println(s);
        }

        listaStudenti.sort(Comparator.comparing(Student::getNume));
        salveazaInFisier(listaStudenti, "studenti_out.txt");

        listaStudenti.sort(Comparator
                .comparing(Student::getFormatieDeStudiu)
                .thenComparing(Student::getNume));

        salveazaInFisier(listaStudenti, "studenti_out_sorted.txt");
    }

    public static void salveazaInFisier(List<Student> lista, String numeFisier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            for (Student s : lista) {
                writer.println(s.toString());
            }
        } catch (IOException e) {
            System.out.println("Eroare critică la scrierea fișierului: " + e.getMessage());
        }
    }
}