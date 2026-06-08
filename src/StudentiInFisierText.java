import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class StudentiInFisierText implements IExportStrategy {
    @Override
    public void exporta(Collection<Student> studenti, String destinatie) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(destinatie))) {
            for (Student s : studenti) {
                writer.println(s.toString());
            }
            System.out.println("Export text finalizat cu succes in: " + destinatie);
        } catch (IOException e) {
            System.out.println("Eroare la exportul in fisier text: " + e.getMessage());
        }
    }
}