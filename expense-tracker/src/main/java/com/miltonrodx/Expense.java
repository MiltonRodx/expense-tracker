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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Expense {
    // Attributes
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


    // ADD   (1 case)
    public static void add (String pDescription, double pAmount) throws Exception {
        String filepath = "expenses.csv"; // set filepath

        // Reader and headers:
        try {
            // Create reader
            Reader reader = new FileReader(filepath);
            
            // Create writer
            FileWriter writer = new FileWriter(filepath, true);
           
            // Create CSVFormat instance
            CSVFormat format = CSVFormat.DEFAULT.builder() // create csvformat
                .setHeader()    // lee encabezados automaticamente leyendo
                .setSkipHeaderRecord(true) // ignore first line
                .get();
            
            // create CSVParser instance
            CSVParser parser = CSVParser.parse(reader, format);

            // create the iterable record
            Iterable<CSVRecord> records = parser; // asign parser to the interface

            // create the printer
            CSVPrinter printer = new CSVPrinter(writer, format);



            // declare variables when opening the csv
            int biggestId = 0;
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
           
            // Write to file:
            printer.printRecord(newId, pDescription, pAmount, actualDate);

            printer.flush(); // force writing

        } catch (IOException e) {
            System.err.println("Error when writing to CSV file expenses.csv: " + e.getMessage());
            //e.printStackTrace();
        }
        


        


        // set new_id = last_id + 1    (opening csv and looking last one and be id = last + 1)
        
        // close reader thing

        // actual_date = invocar-funcion_obtener-fecha

        // create Expense object.
        
        // set Expense attributes

        // write to csv the Expense object (id, description, amount, date)
    
        // 

    }

    // UPDATE      (3 cases)
    // UPDATE in case there is only description
    public static void update (int pId, String pDescription) {
    
    }

    // UPDATE in case there is only amount
    public static void update (int pId, double pAmount) {

    }

    // UPDATE if both amount and description are present:
    public static void update (int pId, String pDescription, double pAmount) {

    }

    
    // DELETE:     (2 cases)
    // DELETE in case there is only int id
    public static void delete (int pId) {

    }

    // DELETE in case there is only String description
    public static void delete (String pDescription) {

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
