package com.shoppers.excelhelpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reads CSV test data from the classpath (e.g. src/test/resources/TestData).
 */
public final class CsvUtil {

    private CsvUtil() {
    }

    public static List<Map<String, String>> read(String classpathResource) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (InputStream input = CsvUtil.class.getClassLoader().getResourceAsStream(classpathResource)) {
            if (input == null) {
                throw new IOException("Resource not found: " + classpathResource);
            }
            try (CSVParser parser = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build()
                    .parse(new InputStreamReader(input, StandardCharsets.UTF_8))) {
                for (CSVRecord record : parser) {
                    Map<String, String> row = new HashMap<>();
                    for (String header : parser.getHeaderNames()) {
                        row.put(header, record.get(header));
                    }
                    rows.add(row);
                }
            }
        }
        return rows;
    }
}
