import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentiDinFisierText implements IImportStrategy {
    @Override
    public List<Student> importa(String sursa) {
        List<Student> listaStudenti = new ArrayList<>();
        File fisierIntrare = new File(sursa);

        try (Scanner scanner = new Scanner(fisierIntrare)) {
            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine();
                if (linie.trim().isEmpty()) continue;

                String[] date = linie.split(",");
                String nrMatricol = date[0].trim();
                String prenume = date[1].trim();
                String nume = date[2].trim();
                String formatie = date[3].trim();
                double nota = date.length > 4 ? Double.parseDouble(date[4].trim()) : 0.0;

                listaStudenti.add(new Student(nrMatricol, nume, prenume, formatie, nota));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Eroare: Nu s-a gasit fisierul " + sursa);
        }
        return listaStudenti;
    }
}