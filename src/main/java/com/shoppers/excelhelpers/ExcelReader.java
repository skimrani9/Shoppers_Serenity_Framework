package com.shoppers.excelhelpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads Excel (.xlsx) test data from the classpath.
 */
public final class ExcelReader {

    private ExcelReader() {
    }

    public static List<Map<String, String>> readSheet(String classpathResource, String sheetName) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (InputStream input = ExcelReader.class.getClassLoader().getResourceAsStream(classpathResource)) {
            if (input == null) {
                throw new IOException("Resource not found: " + classpathResource);
            }
            try (Workbook workbook = new XSSFWorkbook(input)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IOException("Sheet not found: " + sheetName);
                }
                Row headerRow = sheet.getRow(0);
                if (headerRow == null) {
                    return rows;
                }
                List<String> headers = new ArrayList<>();
                for (Cell cell : headerRow) {
                    headers.add(cell.getStringCellValue());
                }
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) {
                        continue;
                    }
                    Map<String, String> data = new HashMap<>();
                    for (int col = 0; col < headers.size(); col++) {
                        Cell cell = row.getCell(col);
                        data.put(headers.get(col), cell == null ? "" : cell.toString());
                    }
                    rows.add(data);
                }
            }
        }
        return rows;
    }
}
