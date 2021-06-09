/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.adtassigment3;

/**
 *
 * @author Isaac kissimba
 */
public class RunProgram {
    public static void main(String[] args)
    {
        Finder read = new Finder();
        read.openFile();
        read.readFile();
        read.closeFile();
       
        //read.openFileToWriteTheCustomer();
        //read.writeToCustomerFile();
        //read.closeWriter();
        
        read.openFileToWriteToTheSupplier();
        read.writeToSupplierFile();
        read.closeWriterAgain();
        
    }
}
