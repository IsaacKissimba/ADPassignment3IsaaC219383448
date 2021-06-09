/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.adtassigment3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Isaac kissimba
 */
public class Finder {

    ObjectInputStream fileLocation;
    ArrayList<Customer> customerList = new ArrayList<Customer>();
    ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    PrintWriter writerObject;

    public void openFile() {
        try {
            fileLocation = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
        } catch (IOException Exception) {
            System.out.println("Failed to open the ser file: " + Exception.getMessage());

        }

    }

    public void readFile() {
        for (int i = 0; i < 11; ++i) {
            try {
                Object object = fileLocation.readObject();
                if (object.getClass().equals(Customer.class)) {
                    customerList.add((Customer) object);
                } else {
                    supplierList.add((Supplier) object);
                }

            } catch (IOException e) {
                System.out.println("Failed to read from file: " + e.getMessage());
            } catch (ClassNotFoundException h) {
                System.out.println("Error found: " + h.getMessage());
            }
        }
        sortCust();
        System.out.println("Age of customer: " + customerList.get(0).getStHolderId() + "'s age is: " + getCustomerAge(customerList.get(0)));
        sortSupplier();
        //System.out.println("The Description of the supplier: " + supplierList.get(0).getName() + "'s description is: " + getDescription(supplierList.get(0)));
    }

    public void sortCust() {
        Customer timingCustomer;
        Customer[] customers = new Customer[customerList.size()];
        for (int d = 0; d < customerList.size(); ++d) {
            customers[d] = customerList.get(d);
        }
        for (int i = 0; i < customers.length - 1; i++) {
            for (int j = i + 1; j < customers.length; j++) {
                if (customers[i].getStHolderId().
                        compareTo(customers[j].getStHolderId()) > 0) {
                    timingCustomer = customers[i];
                    customers[i] = customers[j];
                    customers[j] = timingCustomer;
                }
            }
        }
        customerList.clear();
        customerList.addAll(Arrays.asList(customers));
    }

    public void sortSupplier() {
        Supplier timingSupplier;
        Supplier[] suppliers = new Supplier[supplierList.size()];
        for (int d = 0; d < supplierList.size(); ++d) {
            suppliers[d] = supplierList.get(d);
        }
        for (int i = 0; i < suppliers.length - 1; i++) {
            for (int j = i + 1; j < suppliers.length; j++) {
                if (suppliers[i].getName().
                        compareTo(suppliers[j].getName()) > 0) {
                    timingSupplier = suppliers[i];
                    suppliers[i] = suppliers[j];
                    suppliers[j] = timingSupplier;
                }
            }
        }
        supplierList.clear();
        supplierList.addAll(Arrays.asList(suppliers));
    }

    public int getCustomerAge(Customer customer) {
        LocalDate dateOfBirth = LocalDate.parse(customer.getDateOfBirth());
        LocalDate currDate = LocalDate.now();
        int age = Period.between(dateOfBirth, currDate).getYears();
        return age;
    }

    public String formattDOB(Customer customer) {
        LocalDate localDate = LocalDate.parse(customer.getDateOfBirth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String stringDate = localDate.format(formatter);
        return stringDate;
    }

    public void closeFile() {
        try {
            fileLocation.close();
        } catch (IOException ioException) {
            System.out.println("Failed to close the file: " + ioException.getMessage());
        }
    }

    public void openFileToWriteTheCustomer() {
        try {
            writerObject = new PrintWriter(new FileOutputStream("customerOutFile.txt"));
            System.out.println("****The file is opened successfully****");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write to the file: " + e.getMessage());
        }
    }

    public void writeToCustomerFile() {
        int token = 0;
        int tokentcannotbefund = 0;

        writerObject.println("....................CUSTOMERS....................");
        writerObject.println("ID  \t Name  \t Surname  \tDate of Birth \tAge");
        writerObject.println(".................................................");
        for (int i = 0; i < customerList.size(); ++i) {
            writerObject.print(
                    customerList.get(i).getStHolderId() + '\t'
                    + customerList.get(i).getFirstName() + '\t'
                    + customerList.get(i).getSurName() + '\t'
                    + formattDOB(customerList.get(i)) + '\t'
                    + getCustomerAge(customerList.get(i)) + '\n' + '\n'
            );
            if (customerList.get(i).getCanRent()) {
                token = token + 1;
            } else {
                tokentcannotbefund = tokentcannotbefund + 1;
            }

        }

        writerObject.print("Number of customers who can rent:" + token + '\n');
        writerObject.print("Number of customers who cannot rent:" + tokentcannotbefund + '\n');
    }

    public void closeWriter() {

        writerObject.close();
    }

    public void openFileToWriteToTheSupplier() {
        try {
            writerObject = new PrintWriter(new FileOutputStream("supplierOutFile.txt"));
            System.out.println("****The Opening of the file has successeded****");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write to the file: " + e.getMessage());
        }
    }

    public void writeToSupplierFile() {

        writerObject.println("....................SUPPLIERS................................");
        writerObject.println("ID  \t Name \tProd Type \tDescription");
        writerObject.println(".............................................................");
        for (int i = 0; i < supplierList.size(); ++i) {
            writerObject.print(
                    supplierList.get(i).getStHolderId() + '\t'
                    + supplierList.get(i).getName() + '\t'
                    + supplierList.get(i).getProductType() + '\t' 
                    + supplierList.get(i).getProductDescription() + '\n'
            );
        }
    
    }

    public void closeWriterAgain() {

        writerObject.close();
    }

}
