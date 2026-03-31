import java.util.Objects;

public class Student {
    private String numarMatricol;
    private String nume;
    private String prenume;
    private String formatieDeStudiu;
    private double nota;

    public Student(String numarMatricol, String nume, String prenume, String formatieDeStudiu) {
        this.numarMatricol = numarMatricol;
        this.nume = nume;
        this.prenume = prenume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = 0.0;
    }

    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }
    public String getFormatieDeStudiu() { return formatieDeStudiu; }
    public String getNumarMatricol() { return numarMatricol; }
    public double getNota() { return nota; }

    public void setNota(double nota) {
        this.nota = nota;
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