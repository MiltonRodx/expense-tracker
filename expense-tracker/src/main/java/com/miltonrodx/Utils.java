package com.miltonrodx;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Utils {
    public static String returnDate(String pPattern) {
        LocalDate actualDate = LocalDate.now(); // system date 

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pPattern);

        // format system date   to  string using pattern
        String stringDate = actualDate.format(formatter);  

        return stringDate;
    }
}
