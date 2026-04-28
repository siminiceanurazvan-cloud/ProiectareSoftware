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
            System.out.println("Eroare: Nu s-a gasit fisierul 'studenti_in.txt'.");
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
                    Student studentActualizat = student.withNota(nota);
                    tineri.put(nrMatricol, studentActualizat);

                    for (int i = 0; i < listaStudenti.size(); i++) {
                        if (listaStudenti.get(i).getNumarMatricol().equals(nrMatricol)) {
                            listaStudenti.set(i, studentActualizat);
                            break;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Eroare: Nu s-a gasit fisierul 'note_anon.txt'.");
        }

        Set<Student> setStudenti = new HashSet<>(listaStudenti);
        setStudenti = imparteInDouaFormatii(setStudenti, "TI 211_1", "TI 211_2");

        System.out.println("--- Lista noua dupa impartirea in formatii ---");
        for (Student s : setStudenti) {
            System.out.println(s);
        }
        System.out.println("----------------------------------------------\n");

        float notaM = gasesteNota("Bianca", "Popescu", tineri);
        float notaN = gasesteNota("Ioan", "Popa", tineri);

        System.out.println("Nota pentru Bianca Popescu: " + notaM);
        System.out.println("Nota pentru Ioan Popa: " + notaN);

        listaStudenti.sort(Comparator.comparing(Student::getNume));
        salveazaInFisier(listaStudenti, "studenti_out.txt");

        listaStudenti.sort(Comparator.comparing(Student::getFormatieDeStudiu).thenComparing(Student::getNume));
        salveazaInFisier(listaStudenti, "studenti_out_sorted.txt");

        List<Student> bursieri = new ArrayList<>();
        bursieri.add(new StudentBursier("1025", "Andrei", "Popa", "ISM141/2", 8.70, 725.50));
        bursieri.add(new StudentBursier("1024", "Ioan", "Mihalcea", "ISM141/1", 9.80, 801.10));
        bursieri.add(new StudentBursier("1026", "Anamaria", "Prodan", "TI131/1", 8.90, 745.50));
        bursieri.add(new StudentBursier("1029", "Bianca", "Popescu", "TI131/1", 9.10, 780.80));

        salveazaInFisier(bursieri, "bursieri_out.txt");
    }

    static Student schimbaFormatia(Student st, String nouaFormatieDeStudiu) {
        return new Student(st.getNumarMatricol(), st.getNume(), st.getPrenume(), nouaFormatieDeStudiu, st.getNota());
    }

    static Set<Student> imparteInDouaFormatii(Set<Student> studenti, String formatia1, String formatia2) {
        Set<Student> rezultat = new HashSet<>();
        List<Student> listaTemporara = new ArrayList<>(studenti);

        int n = listaTemporara.size();
        int jumatate = (n % 2 == 0) ? (n / 2) : (n / 2 + 1);

        for (int i = 0; i < n; i++) {
            String formatiaAtribuita = (i < jumatate) ? formatia1 : formatia2;
            rezultat.add(schimbaFormatia(listaTemporara.get(i), formatiaAtribuita));
        }

        return rezultat;
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

    public static void salveazaInFisier(List<? extends Student> lista, String numeFisier) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numeFisier))) {
            for (Student s : lista) {
                writer.println(s.toString());
            }
        } catch (IOException e) {
            System.out.println("Eroare critica la scrierea fisierului: " + e.getMessage());
        }
    }
}