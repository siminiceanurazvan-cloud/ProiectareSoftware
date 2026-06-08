import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AplicatieCuStrategy {
    private IExportStrategy exportStrategy;
    private IImportStrategy importStrategy;

    public void setExportStrategy(IExportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy;
    }

    public void setImportStrategy(IImportStrategy importStrategy) {
        this.importStrategy = importStrategy;
    }

    public void executaExport(Collection<Student> studenti, String destinatie) {
        if (exportStrategy != null) {
            exportStrategy.exporta(studenti, destinatie);
        } else {
            System.out.println("Nicio strategie de export nu a fost setata!");
        }
    }

    public List<Student> executaImport(String sursa) {
        if (importStrategy != null) {
            return importStrategy.importa(sursa);
        } else {
            System.out.println("Nicio strategie de import nu a fost setata!");
            return null;
        }
    }

    public static void main(String[] args) {
        List<Student> studenti = Arrays.asList(
                new Student("1025", "Popa", "Andrei", "ISM141/2", 8.70),
                new Student("1024", "Mihalcea", "Ioan", "ISM141/1", 10.0),
                new Student("1026", "Prodan", "Anamaria", "TI131/1", 8.90),
                new Student("1029", "Popescu", "Bianca", "TI131/1", 10.0),
                new Student("1030", "Pana", "Maria", "TI131/2", 4.10),
                new Student("1031", "Mohanu", "Gabriela", "TI131/2", 7.33),
                new Student("1032", "Nasta", "Marius", "TI131/2", 3.20),
                new Student("1033", "Nasta", "Marius", "TI131/1", 5.12),
                new Student("1034", "Dobrescu", "Andrei", "TI131/2", 2.22)
        );

        AplicatieCuStrategy app = new AplicatieCuStrategy();

        app.setExportStrategy(new StudentiInConsola());
        app.executaExport(studenti, null);

        app.setExportStrategy(new StudentiInFisierText());
        app.executaExport(studenti, "strategy_studenti.txt");

        app.setExportStrategy(new StudentiInFisierXlsx());
        app.executaExport(studenti, "strategy_studenti.xlsx");

        System.out.println("\n--- Testare Import ---");
        app.setImportStrategy(new StudentiDinFisierText());
        List<Student> dinText = app.executaImport("strategy_studenti.txt");
        System.out.println("Au fost importati din TXT: " + (dinText != null ? dinText.size() : 0) + " studenti.");

        app.setImportStrategy(new StudentiDinFisierXlsx());
        List<Student> dinXlsx = app.executaImport("strategy_studenti.xlsx");
        System.out.println("Au fost importati din XLSX: " + (dinXlsx != null ? dinXlsx.size() : 0) + " studenti.");
    }
}