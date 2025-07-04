package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    // Utility method to get all data from Excel as List of Maps
    public static List<Map<String, String>> getDataAsList(String excelPath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(excelPath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in sheet: " + sheetName);
            }

            int totalRows = sheet.getLastRowNum();

            for (int i = 1; i <= totalRows; i++) {
                Row dataRow = sheet.getRow(i);
                if (dataRow == null) continue;

                Map<String, String> dataMap = new HashMap<>();
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    Cell headerCell = headerRow.getCell(j);
                    Cell dataCell = dataRow.getCell(j);
                    String key = getCellValue(headerCell);
                    String value = getCellValue(dataCell);
                    dataMap.put(key, value);
                }
                dataList.add(dataMap);
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }

        return dataList;
    }

    // Safely converts any Excel cell to String
    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue()); // Avoid decimal points
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK, _NONE, ERROR -> "";
        };
    }
}
