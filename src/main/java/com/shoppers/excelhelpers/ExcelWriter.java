package com.shoppers.excelhelpers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Writes test execution results to an Excel file.
 */
public final class ExcelWriter {

    private ExcelWriter() {
    }

    public static void writeResults(Path outputPath, String sheetName,
                                    List<String> headers, List<Map<String, String>> rows) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet(sheetName);
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                headerRow.createCell(i).setCellValue(headers.get(i));
            }
            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                Map<String, String> data = rows.get(rowIndex);
                for (int col = 0; col < headers.size(); col++) {
                    row.createCell(col).setCellValue(data.getOrDefault(headers.get(col), ""));
                }
            }
            Files.createDirectories(outputPath.getParent());
            try (OutputStream out = Files.newOutputStream(outputPath)) {
                workbook.write(out);
            }
        }
    }
}
