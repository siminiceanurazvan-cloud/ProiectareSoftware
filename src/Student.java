import java.util.Objects;

public class Student {
    private final String numarMatricol;
    private final String nume;
    private final String prenume;
    private final String formatieDeStudiu;
    private final double nota;

    public Student(String numarMatricol, String nume, String prenume, String formatieDeStudiu) {
        this(numarMatricol, nume, prenume, formatieDeStudiu, 0.0);
    }

    public Student(String numarMatricol, String nume, String prenume, String formatieDeStudiu, double nota) {
        this.numarMatricol = numarMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = nota;
    }

    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public String getFormatieDeStudiu() { return formatieDeStudiu; }
    public String getNumarMatricol() { return numarMatricol; }
    public double getNota() { return nota; }

    // Inlocuieste setter-ul: returneaza o instanta noua cu nota modificata
    public Student withNota(double nouaNota) {
        return new Student(this.numarMatricol, this.nume, this.prenume, this.formatieDeStudiu, nouaNota);
    }

    @Override
    public String toString() {
        return numarMatricol + "," + prenume + "," + nume + "," + formatieDeStudiu + "," + nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(numarMatricol, student.numarMatricol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numarMatricol);
    }
}