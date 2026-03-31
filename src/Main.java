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

        Map<String, Student> tineri = new HashMap<>();
        for (Student s : listaStudenti) {
            tineri.put(s.getNumarMatricol(), s);
        }

        File fisierNote = new File("note_anon.txt");
        try (Scanner scannerNote = new Scanner(fisierNote)) {
            while (scannerNote.hasNextLine()) {
                String linie = scannerNote.nextLine();
                if (linie.trim().isEmpty()) continue;

                String[] date = linie.split(",");
                String nrMatricol = date[0].trim();
                double nota = Double.parseDouble(date[1].trim());

                Student student = tineri.get(nrMatricol);
                if (student != null) {
                    student.setNota(nota);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Eroare: Nu s-a gasit fișierul 'note_anon.txt'.");
        }

        System.out.println("Cautare note studenti");
        float notaM = gasesteNota("Bianca", "Popescu", tineri);
        float notaN = gasesteNota("Ioan", "Popa", tineri);

        System.out.println("Nota pentru Bianca Popescu: " + notaM);
        System.out.println("Nota pentru Ioan Popa: " + notaN);

        listaStudenti.sort(Comparator.comparing(Student::getNume));
        salveazaInFisier(listaStudenti, "studenti_out.txt");

        listaStudenti.sort(Comparator.comparing(Student::getFormatieDeStudiu).thenComparing(Student::getNume));
        salveazaInFisier(listaStudenti, "studenti_out_sorted.txt");
    }

    public static float gasesteNota(String prenume, String nume, Map<String, Student> tineri) {
        Map<String, Student> mapNumePrenume = new HashMap<>();

        for (Student s : tineri.values()) {
            String cheie = s.getPrenume() + "-" + s.getNume();
            mapNumePrenume.put(cheie, s);
        }

        String cheieCautare = prenume + "-" + nume;
        Student studentGasit = mapNumePrenume.get(cheieCautare);

        if (studentGasit != null) {
            return (float) studentGasit.getNota();
        }
        return 0.0f;
    }

    public static void salveazaInFisier(List<Student> lista, String numeFisier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            for (Student s : lista) {
                writer.println(s.toString());
            }
        } catch (IOException e) {
            System.out.println("Eroare critica la scrierea fisierului: " + e.getMessage());
        }
    }
}