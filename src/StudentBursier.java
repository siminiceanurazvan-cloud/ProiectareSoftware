import java.util.Objects;

public class StudentBursier extends Student {
    private final double cuantumBursa;

    public StudentBursier(String numarMatricol, String nume, String prenume, String formatieDeStudiu, double nota, double cuantumBursa) {
        super(numarMatricol, nume, prenume, formatieDeStudiu, nota);
        this.cuantumBursa = cuantumBursa;
    }

    public double getCuantumBursa() {
        return cuantumBursa;
    }

    public StudentBursier withCuantumBursa(double nouCuantum) {
        return new StudentBursier(getNumarMatricol(), getNume(), getPrenume(), getFormatieDeStudiu(), getNota(), nouCuantum);
    }

    @Override
    public String toString() {
        return super.toString() + "," + cuantumBursa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentBursier that = (StudentBursier) o;
        return Double.compare(that.cuantumBursa, cuantumBursa) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cuantumBursa);
    }
}