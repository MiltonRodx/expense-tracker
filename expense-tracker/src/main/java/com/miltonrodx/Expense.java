package com.miltonrodx;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class Expense {
    // Attributes
    public static final String FILEPATH = "src/main/java/com/miltonrodx/expenses.csv"; // set filepath

    private int id;
    private String description;
    private double amount;
    private String date;

    // Constructor
    public Expense (int id, String description, double amount, String date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    // Methods
    // Getters and setters
    // Getters
    public int getId () {
        return id;
    }
    public String getDescription () {
        return description;
    }
    public double getAmount () {
        return amount;
    }
    public String getDate () {
        return date;
    }

    // Setters
    public void setId (int id) {
        this.id = id;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setDate(String date) {
        this.date = date;
    }



    // ADD  method  (1 case)
    public static void add (String pDescription, double pAmount) throws Exception {        
        // Reader and headers:
        try {
            // Create reader
            Reader reader = new FileReader(FILEPATH);
            
            // Create writer
            FileWriter writer = new FileWriter(FILEPATH, true);
           
            // Create CSVFormat instance
            CSVFormat format = CSVFormat.DEFAULT.builder() // create csvformat
                .setHeader()    // lee encabezados automaticamente leyendo
                .setSkipHeaderRecord(true) // ignore first line
                .get();
            
            // create CSVParser instance
            CSVParser parser = CSVParser.parse(reader, format);

            // create the iterable record
            Iterable<CSVRecord> records = parser; // assign parser to the interface

            // create the printer
            CSVPrinter printer = new CSVPrinter(writer, format);

            
            int biggestId = 0; // declare variables when opening the csv
            int newId;

            // open the file
            for (CSVRecord record : records) {
                int actualId = NumberUtils.toInt(record.get("id"));
                
                if (actualId > biggestId) { // read from the file to see what is the biggest id
                    biggestId = actualId;
                }
            }

            // Get some camps values
            newId = biggestId + 1; // set id
            String actualDate = Utils.returnDate("yyyy-dd-MM"); // get date
           
            // Write to csv the expense record (id, description, amount, date)
            printer.printRecord(newId, pDescription, pAmount, actualDate);
           
            printer.flush(); // force writing

            printer.close(); // close printer

        } catch (IOException e) {
            System.err.println("Error when writing to CSV file expenses.csv: " + e.getMessage());
            //e.printStackTrace();
        } 

    }




    // UPDATE      (3 cases)
    // UPDATE in case there is only description
    public static void update (int pId, String pDescription) {
        try {
        // (1) intialize needed objects
            // Create readers (temp and original)
            String tempPath = "src/main/java/com/miltonrodx/expenses.tmp";
            Reader originalReader = new FileReader(FILEPATH);

            // Create writers (temp and original)
            FileWriter originalWriter = new FileWriter(FILEPATH, true); // make it to the iru
            FileWriter tempWriter = new FileWriter(tempPath, true);

            // Create CSVFormat instance
            CSVFormat format = CSVFormat.DEFAULT.builder() // create csvformat
                .setHeader()    // lee encabezados automaticamente leyendo
                .setSkipHeaderRecord(true) // ignore first line
                .get();
            
            // create CSVParser instance (original)
            CSVParser originalParser = CSVParser.parse(originalReader, format);

            // create iterable record (looping over original file)
            Iterable<CSVRecord> originalRecords = originalParser; // assign parser to the interface

            // create the printer
            CSVPrinter originalPrinter = new CSVPrinter(originalWriter, format);
            CSVPrinter tempPrinter = new CSVPrinter(tempWriter, format);


        // (2) Go to the record
            // Loop over records and get record with same id
            String strId = String.valueOf(pId);

            boolean wasFound = false;


            tempPrinter.printRecord("id", "description", "amount", "date"); //write header

            for (CSVRecord record : originalRecords) {
                if (!wasFound && record.get("id").equals(strId)) {

                    Double prevAmount = NumberUtils.toDouble(record.get("amount")); // save info shorcut
                    String actualDate = Utils.returnDate("yyyy-dd-MM");
                    tempPrinter.printRecord(pId, pDescription, prevAmount, actualDate); // write to new file this record.
                    wasFound = true;

                } else {
                    tempPrinter.printRecord(record);  // it will be normal
                }
            }

            if (!wasFound) {
                System.out.println("There is no record with the id of " + pId + ".");
            }
            
            // Atomic writing, replacing everything existing.
            // some redundancy to fix types
            Path origFile = Paths.get(FILEPATH); 
            Path tempFile = Paths.get(tempPath);

            Files.move(tempFile, origFile, StandardCopyOption.REPLACE_EXISTING);  //writing to file

            tempPrinter.close();
            originalPrinter.close();

            if (wasFound) {
                System.out.println("Updated id: " + pId + " succesfully!");
            }
            
            

        } catch (Exception e) {
            System.err.println("error when updating file: " + e.getMessage());
        }
        
        
        
    }




    // UPDATE in case there is only amount
    public static void update (int pId, double pAmount) {
        try {
        // (1) intialize needed objects
            // Create readers (temp and original)
            String tempPath = "src/main/java/com/miltonrodx/expenses.tmp";
            Reader originalReader = new FileReader(FILEPATH);

            // Create writers (temp and original)
            FileWriter originalWriter = new FileWriter(FILEPATH, true); // make it to the iru
            FileWriter tempWriter = new FileWriter(tempPath, true);

            // Create CSVFormat instance
            CSVFormat format = CSVFormat.DEFAULT.builder() // create csvformat
                .setHeader()    // lee encabezados automaticamente leyendo
                .setSkipHeaderRecord(true) // ignore first line
                .get();
            
            // create CSVParser instance (original)
            CSVParser originalParser = CSVParser.parse(originalReader, format);

            // create iterable record (looping over original file)
            Iterable<CSVRecord> originalRecords = originalParser; // assign parser to the interface

            // create the printer
            CSVPrinter originalPrinter = new CSVPrinter(originalWriter, format);
            CSVPrinter tempPrinter = new CSVPrinter(tempWriter, format);


        // (2) Go to the record
            // Loop over records and get record with same id
            String strId = String.valueOf(pId);

            boolean wasFound = false;


            tempPrinter.printRecord("id", "description", "amount", "date"); //write header

            for (CSVRecord record : originalRecords) {
                if (!wasFound && record.get("id").equals(strId)) {
                    String actualDate = Utils.returnDate("yyyy-dd-MM");
                    String description = record.get("description");

                    tempPrinter.printRecord(pId, description, pAmount, actualDate); // write to new file this record.
                    wasFound = true;

                } else {
                    tempPrinter.printRecord(record);  // it will be normal
                }
            }

            if (!wasFound) {
                System.out.println("There is no record with the id of " + pId + ".");
            }
            
            // Atomic writing, replacing everything existing.
            // some redundancy to fix types
            Path origFile = Paths.get(FILEPATH); 
            Path tempFile = Paths.get(tempPath);

            Files.move(tempFile, origFile, StandardCopyOption.REPLACE_EXISTING);  //writing to file

            tempPrinter.close();
            originalPrinter.close();

            if (wasFound) {
                System.out.println("Updated id: " + pId + " succesfully!");
            }
            
            

        } catch (Exception e) {
            System.err.println("error when updating file: " + e.getMessage());
        }

    }




    // UPDATE if both amount and description are present:
    public static void update (int pId, String pDescription, double pAmount) {
        try {
        // (1) intialize needed objects
            // Create readers (temp and original)
            String tempPath = "src/main/java/com/miltonrodx/expenses.tmp";
            Reader originalReader = new FileReader(FILEPATH);

            // Create writers (temp and original)
            FileWriter originalWriter = new FileWriter(FILEPATH, true); // make it to the iru
            FileWriter tempWriter = new FileWriter(tempPath, true);

            // Create CSVFormat instance
            CSVFormat format = CSVFormat.DEFAULT.builder() // create csvformat
                .setHeader()    // lee encabezados automaticamente leyendo
                .setSkipHeaderRecord(true) // ignore first line
                .get();
            
            // create CSVParser instance (original)
            CSVParser originalParser = CSVParser.parse(originalReader, format);

            // create iterable record (looping over original file)
            Iterable<CSVRecord> originalRecords = originalParser; // assign parser to the interface

            // create the printer
            CSVPrinter originalPrinter = new CSVPrinter(originalWriter, format);
            CSVPrinter tempPrinter = new CSVPrinter(tempWriter, format);


        // (2) Go to the record
            // Loop over records and get record with same id
            String strId = String.valueOf(pId);

            boolean wasFound = false;


            tempPrinter.printRecord("id", "description", "amount", "date"); //write header

            for (CSVRecord record : originalRecords) {
                if (!wasFound && record.get("id").equals(strId)) {
                    String actualDate = Utils.returnDate("yyyy-dd-MM");
                    tempPrinter.printRecord(pId, pDescription, pAmount, actualDate); // write to new file this record.
                    wasFound = true;

                } else {
                    tempPrinter.printRecord(record);  // it will be normal
                }
            }

            if (!wasFound) {
                System.out.println("There is no record with the id of " + pId + ".");
            }
            
            // Atomic writing, replacing everything existing.
            // some redundancy to fix types
            Path origFile = Paths.get(FILEPATH); 
            Path tempFile = Paths.get(tempPath);

            Files.move(tempFile, origFile, StandardCopyOption.REPLACE_EXISTING);  //writing to file

            tempPrinter.close();
            originalPrinter.close();

            if (wasFound) {
                System.out.println("Updated id: " + pId + " succesfully!");
            }
            
            

        } catch (Exception e) {
            System.err.println("error when updating file: " + e.getMessage());
        }
        
    }



    
    // DELETE:     (2 cases)
    // DELETE in case there is only int id
    public static void delete (int pId) {
        try {
            // (1) intialize needed objects
            // Create readers (temp and original)
            String tempPath = "src/main/java/com/miltonrodx/expenses.tmp";
            Reader originalReader = new FileReader(FILEPATH);

            // Create writers (temp and original)
            FileWriter originalWriter = new FileWriter(FILEPATH, true);
            FileWriter tempWriter = new FileWriter(tempPath, true);

            // Create CSVFormat instance
            CSVFormat format = CSVFormat.DEFAULT.builder() // create csvformat
                .setHeader()    // lee encabezados automaticamente leyendo
                .setSkipHeaderRecord(true) // ignore first line
                .get();
            
            // create CSVParser instance (original)
            CSVParser originalParser = CSVParser.parse(originalReader, format);

            // create iterable record (looping over original file)
            Iterable<CSVRecord> originalRecords = originalParser; // assign parser to the interface

            // create the printer
            CSVPrinter originalPrinter = new CSVPrinter(originalWriter, format);
            CSVPrinter tempPrinter = new CSVPrinter(tempWriter, format);

            boolean wasFound = false;
            String strId = String.valueOf(pId);

            // print header!!!!!!
            tempPrinter.printRecord("id", "description", "amount", "date");

            // (2) Loop and do stuff
            for (CSVRecord record : originalRecords) {
                if (!wasFound && record.get("id").equals(strId)) {
                    wasFound = true;
                    continue;
                } else {
                    tempPrinter.printRecord(record);
                }
            }

            if (!wasFound) {
                System.out.println("Record with id " + strId + " was not found.");
            }

            // write to file
            Path origFile = Paths.get(FILEPATH); 
            Path tempFile = Paths.get(tempPath);

            Files.move(tempFile, origFile, StandardCopyOption.REPLACE_EXISTING);  //writing to file

            if (wasFound){
                System.out.println("Record with id " + strId + " was deleted succesfully.");
            }
                

            // close printers
            tempPrinter.close();
            originalPrinter.close();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }




    // VIEWALL
    public static void viewAll () {
        
    }




    // SUMMARY      (2 cases)
    // summary in case no args:    summary of actual month
    public static void summary () {
        // basically is show in screen amount of money current month

    }




    // in case there is a specified month (of current year if exists, else previous year if exists).
    public static void summary (int pId) {
        // check if pId is integer and not cero and if it is between 1 and 12 both included.

    }
}
