package com.miltonrodx;

import org.apache.commons.lang3.math.NumberUtils;

public class App {
    public static void main(String[] args) throws Exception{
        if (args.length == 0) {
            System.out.println("Invoke the tool using arguments. for example add <description> <amount>, etc");
        }
        else {
            String command = args[0];

            switch (command) {
                case "add":
                    // get values to readable
                    if (args[1] != "--description" || args[3] != "--amount") {
                        System.out.println("Usage: add --description <description> --amount <amount>");
                    }

                    if (NumberUtils.isCreatable(args[4])) {
                        String description = args[2];
                        Double amount = NumberUtils.toDouble(args[4]);

                        Expense.add(description, amount);
                    } else {
                        System.out.println("Amount provided must be numeric.");
                    }
                    break;
            

                case "update":

                    if (args[1].equals("--id") &&
                        NumberUtils.isCreatable(args[2]) &&
                        args[3].equals("--description") &&
                        args[4] != null &&
                        args[5].equals("--amount") &&
                        NumberUtils.isCreatable(args[6])) {

                            int id = NumberUtils.toInt(args[2]);
                            double amount = NumberUtils.toDouble(args[6]);
                            Expense.update(id, args[4], amount);
                    
                    }
                    else if (args[1].equals("--id") &&
                            NumberUtils.isCreatable(args[2]) &&
                            args[3].equals("--description") &&
                            args[4] != null) {
                            
                            int id = NumberUtils.toInt(args[2]);
                            Expense.update(id, args[4]);
                    }
                    else if (args[1].equals("--id") &&
                            NumberUtils.isCreatable(args[2]) &&
                            args[3].equals("--amount") &&
                            NumberUtils.isCreatable(args[4])) {
                            
                            int id = NumberUtils.toInt(args[2]);
                            double amount = NumberUtils.toDouble(args[6]);
                            Expense.update(id, amount);
                    } else {
                        System.out.println("Usage: update --id <id> --description <description>    or");
                        System.out.println("update --id <id> --amount <amount>");
                        System.out.println("Or both at the same time.");
                    }
                        
                    break;

                case "delete":
                    if (args[1].equals("--id") &&
                        NumberUtils.isCreatable(args[2])) {
                            int id = NumberUtils.toInt(args[2]);
                            Expense.delete(id);
                    } else {
                        System.out.println("Usage: delete --id <id>");
                    }
                    break;

                case "list":
                    if (args.length == 1) {
                        Expense.list();
                    } else {
                        System.out.println("Usage: list");
                    }
                    break;
                
                case "summary":
                    if (args[1] == null) {
                        Expense.summary();
                    }
                    else if (args[2].equals("--month") &&
                            NumberUtils.isCreatable(args[3])) {
                            int id = NumberUtils.toInt(args[3]);
                            Expense.summary(id);
                    } else {
                        System.out.println("Usage: summary");
                        System.out.println("Usage: summary --month <number between 1 and 12>");
                    }
                     break;

                default:
                    System.out.println("Commands available are: add, update, delete, list, summary");
                    break;
            }

        }
        
    }
}
