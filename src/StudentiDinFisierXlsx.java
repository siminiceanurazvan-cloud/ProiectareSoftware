import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierXlsx implements IImportStrategy {
    @Override
    public List<Student> importa(String sursa) {
        List<Student> studentsList = new ArrayList<>();

        try (FileInputStream fileIn = new FileInputStream(sursa);
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

                    studentsList.add(new Student(nrMatricol, nume, prenume, formatie, nota));
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la citirea din fisierul Excel: " + e.getMessage());
        }
        return studentsList;
    }
}