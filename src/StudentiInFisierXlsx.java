import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

public class StudentiInFisierXlsx implements IExportStrategy {
    @Override
    public void exporta(Collection<Student> studenti, String destinatie) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(destinatie)) {

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
            System.out.println("Export Excel finalizat cu succes in: " + destinatie);
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisierul Excel: " + e.getMessage());
        }
    }
}