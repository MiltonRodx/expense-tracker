package com.miltonrodx;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: expense-tracker add [action] [arguments]");
            /* put some examples */
        }
        else {
            String filepath = "expenses.csv";

            try (Reader reader = new FileReader(filepath)) {

                CSVFormat format = CSVFormat.DEFAULT.builder()
                .setHeader("id", "description", "amount", "date")
                .setSkipHeaderRecord(true)
                .get();


            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}