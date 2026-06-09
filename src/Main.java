import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

        System.out.println("Lista noua dupa impartirea in formatii");
        for (Student s : setStudenti) {
            System.out.println(s);
        }
        System.out.println("\n");

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

        String xlsFileName = "laborator8_students.xlsx";
        //writeToXls(setStudenti, xlsFileName);

        StudentExporter simpluExporter = new ExcelExporter();

        StudentExporter exporterCuCronometru = new TimeMeasuringExporter(simpluExporter);

        exporterCuCronometru.export(setStudenti, xlsFileName);

        System.out.println("Studentii au fost salvati in " + xlsFileName);

        List<Student> studentsFromXls = readFromXls(xlsFileName);
        System.out.println("\nStudenti cititi din xlsx");
        for(Student st: studentsFromXls) {
            System.out.println(st);
        }
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

    public static void writeToXls(Collection<Student> studenti, String fileName) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(fileName)) {

            Sheet sheet = workbook.createSheet("Studenti");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("NrMatricol");
            headerRow.createCell(1).setCellValue("Nume");
            headerRow.createCell(2).setCellValue("Prenume");
            headerRow.createCell(3).setCellValue("Formatie");
            headerRow.createCell(4).setCellValue("Nota");

            int rowNum = 1;
            for (Student s : studenti) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getNume());
                row.createCell(2).setCellValue(s.getPrenume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisierul Excel: " + e.getMessage());
        }
    }

    public static List<Student> readFromXls(String fileName) {
        List<Student> studentsList = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(fileName);
             Workbook workbook = new XSSFWorkbook(fileIn)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String nrMatricol = formatter.formatCellValue(row.getCell(0));
                    String nume = formatter.formatCellValue(row.getCell(1));
                    String prenume = formatter.formatCellValue(row.getCell(2));
                    String formatie = formatter.formatCellValue(row.getCell(3));
                    double nota = 0.0;
                    if (row.getCell(4) != null && row.getCell(4).getCellType() == CellType.NUMERIC) {
                        nota = row.getCell(4).getNumericCellValue();
                    }

                    Student student = new Student(nrMatricol, nume, prenume, formatie, nota);
                    studentsList.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea din fisierul Excel: " + e.getMessage());
        }

        List<Student> studentiCuNote = Arrays.asList(
                new Student("1025", "Andrei", "Popa", "ISM141/2", 8.70),
                new Student("1024", "Ioan", "Mihalcea", "ISM141/1", 10.0),
                new Student("1026", "Anamaria", "Prodan", "TI131/1", 8.90),
                new Student("1029", "Bianca", "Popescu", "TI131/1", 10.0),
                new Student("1030", "Maria", "Pana", "TI131/2", 4.10),
                new Student("1031", "Gabriela", "Mohanu", "TI131/2", 7.33),
                new Student("1032", "Marius", "Nasta", "TI131/2", 3.20),
                new Student("1033", "Marius", "Nasta", "TI131/1", 5.12),
                new Student("1034", "Andrei", "Dobrescu", "TI131/2", 2.22)
        );
        System.out.println("\nStudenti cu nota 10");
        studentiCuNote.stream()
                .filter(s -> s.getNota() == 10.0)
                .forEach(s -> System.out.println(s));

        System.out.println("\nStudenti cu nota sub 5");
        studentiCuNote.stream()
                .filter(s -> s.getNota() < 5.0)
                .forEach(System.out::println);

        System.out.println("\nLista transformata (notele < 4 devin 4)");
        List<Student> studentiTransformati = studentiCuNote.stream()
                .map(s -> s.getNota() < 4.0 ? s.withNota(4.0) : s)
                .collect(Collectors.toList());
        studentiTransformati.forEach(System.out::println);

        System.out.println("\nSuma notelor tuturor studentilor");
        double sumaNotelor = studentiCuNote.stream()
                .map(Student::getNota)
                .reduce(0.0, Double::sum);
        System.out.println("Suma notelor este: " + sumaNotelor);

        System.out.println("\nMedia notelor");
        double mediaNotelor = sumaNotelor / studentiCuNote.size();


        System.out.printf("Media notelor este: %.2f\n", mediaNotelor);
        return studentsList;
    }
}