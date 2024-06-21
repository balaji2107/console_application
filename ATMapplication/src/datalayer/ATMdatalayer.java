package datalayer;

import model.ATM;
import model.Customers;
import model.Transfer;

import java.util.ArrayList;
import java.util.List;

public class ATMdatalayer {

    private static ATMdatalayer atMdatalayer;
    private static ATM atm;
    private Customers currentCus;
    static List<Customers> listCustomer=new ArrayList<>();

    static List<Transfer> transferList=new ArrayList<>();
    public static ATMdatalayer getInstance(){
        if(atMdatalayer==null)
            atMdatalayer=new ATMdatalayer();
        return atMdatalayer;
    }

    public List<Customers> getAllCustomer(){
        return listCustomer;
    }
    public List<Transfer> getAllTransfer(){
        return transferList;
    }

    public void saveAllCustomer(){
        listCustomer.add(new Customers(101,"Suresh",2343,23234));
        listCustomer.add(new Customers(102,"Ganesh",5432,34123));
        listCustomer.add(new Customers(103,"Magesh",7854,26100));
        listCustomer.add(new Customers(104,"Naresh",2345,80000));
        listCustomer.add(new Customers(105,"Harish",1907,103400));

    }

    public void loadAmount(double total, int thousand, int fiveHundered, int hundered) {
        atm=new ATM(total,thousand,fiveHundered,hundered);
    }
    public ATM getATMamount(){
        return atm;
    }

    public Customers getCurrentUser(){
        return currentCus;
    }

    public Customers checkValidcustomer(int accNo, int pin) {
        for(Customers customers:listCustomer){
            if(customers.getAccNo()==accNo && customers.getPin()==pin){
                currentCus=customers;
                return customers;
            }
        }
        return null;
    }

    public void transfer(Transfer transfer) {
        transferList.add(transfer);
    }
}
