package lab8;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiereExcelCuMedie {
    public static void main(String[] args) {
        String inputFilePath = "laborator8_students.xlsx";
        String outputFilePath = "laborator8_output2.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
             XSSFWorkbook inputWorkbook = new XSSFWorkbook(fis);
             XSSFWorkbook outputWorkbook = new XSSFWorkbook()) {

            XSSFSheet inputSheet = inputWorkbook.getSheetAt(0);
            XSSFSheet outputSheet = outputWorkbook.createSheet("Rezultate");

            for (int i = 0; i <= inputSheet.getLastRowNum(); i++) {
                Row inputRow = inputSheet.getRow(i);
                if (inputRow == null) continue;

                Row outputRow = outputSheet.createRow(i);
                int lastCellNum = inputRow.getLastCellNum();

                for (int j = 0; j < lastCellNum; j++) {
                    Cell inputCell = inputRow.getCell(j);
                    Cell outputCell = outputRow.createCell(j);

                    if (inputCell != null) {
                        switch (inputCell.getCellType()) {
                            case STRING:
                                outputCell.setCellValue(inputCell.getStringCellValue());
                                break;
                            case NUMERIC:
                                outputCell.setCellValue(inputCell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                outputCell.setCellValue(inputCell.getBooleanCellValue());
                                break;
                            default:
                                break;
                        }
                    }
                }

                Cell medieCell = outputRow.createCell(lastCellNum);

                if (i == 0) {
                    medieCell.setCellValue("Medie");
                } else {
                    double suma = 0;
                    int coloaneNumarate = 0;

                    for (int k = lastCellNum - 3; k < lastCellNum; k++) {
                        if (k >= 0) {
                            Cell c = inputRow.getCell(k);
                            if (c != null && c.getCellType() == CellType.NUMERIC) {
                                suma += c.getNumericCellValue();
                                coloaneNumarate++;
                            }
                        }
                    }

                    if (coloaneNumarate > 0) {
                        double medie = suma / coloaneNumarate;
                        medieCell.setCellValue(medie);
                    } else {
                        medieCell.setCellValue(0.0);
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(new File(outputFilePath))) {
                outputWorkbook.write(fos);
                System.out.println("Fișierul '" + outputFilePath + "' a fost generat!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}