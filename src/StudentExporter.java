import java.util.Collection;

public interface StudentExporter {
    void export(Collection<Student> studenti, String fileName);
}

class ExcelExporter implements StudentExporter {
    @Override
    public void export(Collection<Student> studenti, String fileName) {
        // Apelăm metoda veche exact așa cum era
        Main.writeToXls(studenti, fileName);
    }
}

class TimeMeasuringExporter implements StudentExporter {
    private StudentExporter exporterDeBaza;

    public TimeMeasuringExporter(StudentExporter exporterDeBaza) {
        this.exporterDeBaza = exporterDeBaza;
    }

    @Override
    public void export(Collection<Student> studenti, String fileName) {
        long startTime = System.currentTimeMillis();

        exporterDeBaza.export(studenti, fileName);

        long endTime = System.currentTimeMillis();
        System.out.println("Timpul de executie pentru export: " + (endTime - startTime) + " milisecunde.");
    }
}