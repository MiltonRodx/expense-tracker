package com.miltonrodx;

import org.apache.commons.lang3.math.NumberUtils;

public class App {
    public static void main(String[] args) throws Exception {
        
        switch (args.length) {
            case 1:
                if (args[0].equals("view-all")) {
                    Expense.viewAll();
                } else if (args[0].equals("summary")) {
                    Expense.summary();
                } else if (args[0].equals("add")) {// if he did it wrong
                    System.out.println("Usage: add <description> <amount>");
                }
                break;
        
            case 2:
                if (args[0].equals("summary") && NumberUtils.isCreatable(args[1])) { // if second argument can be numeric
                    Expense.summary(NumberUtils.toInt(args[1])); // summary of that month
                }
                else if (args[0].equals("add")) {
                    System.out.println("Usage: add <description> <amount>");
                } else if (args[0].equals("update")){
                    System.out.println("Usage: update <id> <new-description> <new-amount>");
                } else if (args[0].equals("delete") &&
                        NumberUtils.isCreatable(args[1])) {
                    int id = NumberUtils.toInt(args[1]);
                    Expense.delete(id);
                }

                break;

            case 3:
                if (args[0].equals("add")) { // ADD
                    try {
                        if ((args[1] instanceof String) &&
                            (args[2] != null) &&
                            (NumberUtils.isCreatable(args[2]))) {    // boolean isConvertible = NumberUtils.isCreatable(str);
                            double amount = Double.parseDouble(args[2]);
                            Expense.add(args[1], amount);
                        }
                        else if ((NumberUtils.isCreatable(args[1])) &&
                            (args[2] instanceof String)) {
                            Expense.add(args[2], Double.parseDouble(args[1]));
                        } else {
                            System.out.println("You must provide a description(text) and amount(numeric) as arguments to add an expense");
                        }

                    } catch (Exception e) { // exception
                        e.printStackTrace();
                    }

                } else if (args[0].equals("update")) { // update
                    try {
                        int pId;

                        if (NumberUtils.isCreatable(args[1])) {
                            pId = NumberUtils.toInt(args[1]);
                        } else break;

                        if (NumberUtils.isCreatable(args[2])) { // if there is  description and ID
                            Expense.update(NumberUtils.toInt(args[2]), pId);
                        }
                        else if (args[2] instanceof String) { // if there is  ID and description   // there is a BUG: TODO Fix bug 
                            Expense.update(NumberUtils.toInt(args[1]), args[2]);
                        } 
                        else if (args[2] instanceof String) { // if there is only description
                            Expense.update(pId, args[2]);
                        }
                        else if (NumberUtils.isCreatable(args[2])) { // if there is double amount
                            Expense.update(pId, NumberUtils.toDouble(args[2]));
                        }
                    } catch (Exception e) { // exception
                        e.printStackTrace();
                    }
                }

                break;

            case 4:
                if (args[0].equals("update") &&
                    NumberUtils.isCreatable(args[1]) &&
                    (args[2] instanceof String) &&
                    (NumberUtils.isCreatable(args[3]))) {

                        int id = NumberUtils.toInt(args[1]);
                        double amount = NumberUtils.toDouble(args[3]);

                        Expense.update(id, args[2], amount);
                }
                
                break;


            default:
                if (args.length == 0) {
                    System.out.println("Invoke the tool using arguments. for example add <description> <amount>, etc");
                }
                else if (args.length > 4) {
                    System.out.println("Too many arguments!");
                }

                break;
        }
        
    }
}