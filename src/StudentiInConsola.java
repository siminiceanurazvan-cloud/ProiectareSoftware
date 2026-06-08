import java.util.Collection;

public class StudentiInConsola implements IExportStrategy {
    @Override
    public void exporta(Collection<Student> studenti, String destinatie) {
        System.out.println("--- Afisare Studenti in Consola ---");
        for (Student s : studenti) {
            System.out.println(s);
        }
    }
}