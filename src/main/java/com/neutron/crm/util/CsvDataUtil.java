package com.neutron.crm.util;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@UtilityClass
public class CsvDataUtil {

    public static List<String[]> getCsvDataFromInputStream(InputStream inputStream, Character delimiter) throws IOException {

        List<String[]> records;

        final CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(delimiter).build();

        try (CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream)).withCSVParser(csvParser).build()) {
            records = csvReader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
