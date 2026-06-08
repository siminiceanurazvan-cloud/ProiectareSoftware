package lab8;

import org.apache.poi.ss.usermodel.Cell;
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
        String outputFilePath3 = "laborator8_output3.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(inputFilePath));
             XSSFWorkbook inputWorkbook = new XSSFWorkbook(fis);
             XSSFWorkbook outputWorkbook3 = new XSSFWorkbook()) {

            XSSFSheet inputSheet = inputWorkbook.getSheetAt(0);
            XSSFSheet outputSheet3 = outputWorkbook3.createSheet("Rezultate Formula");

            for (int i = 0; i <= inputSheet.getLastRowNum(); i++) {
                Row inputRow = inputSheet.getRow(i);
                if (inputRow == null) continue;

                Row outputRow3 = outputSheet3.createRow(i);
                int lastCellNum = inputRow.getLastCellNum();

                for (int j = 0; j < lastCellNum; j++) {
                    Cell inputCell = inputRow.getCell(j);
                    Cell outputCell = outputRow3.createCell(j);

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

                Cell medieCell3 = outputRow3.createCell(lastCellNum);

                if (i == 0) {
                    medieCell3.setCellValue("Medie");
                } else {
                    int excelRowNum = i + 1;
                    String formula = "AVERAGE(D" + excelRowNum + ":F" + excelRowNum + ")";
                    medieCell3.setCellFormula(formula);
                }
            }

            try (FileOutputStream fos3 = new FileOutputStream(new File(outputFilePath3))) {
                outputWorkbook3.write(fos3);
                System.out.println("Fisierul '" + outputFilePath3 + "' a fost generat cu succes folosind formule!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}