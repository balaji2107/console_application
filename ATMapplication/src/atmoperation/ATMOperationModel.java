package atmoperation;

import datalayer.ATMdatalayer;
import model.ATM;
import model.Customers;
import model.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ATMOperationModel {
    private ATMOperationView atmOperationView;

    private ATM atm;
    private ATMdatalayer atMdatalayer;
    public ATMOperationModel(ATMOperationView atmOperationView) {
        this.atmOperationView=atmOperationView;
        atMdatalayer=ATMdatalayer.getInstance();
        atm=atMdatalayer.getATMamount();
    }

    public void showBalance() {
        Customers customer=atMdatalayer.getCurrentUser();
        atmOperationView.showBalanceAmount(customer.getBalance());
    }

    public void checkValidPin(int pin) {
        Customers customer=atMdatalayer.getCurrentUser();
        if(customer.getPin()==pin){
            atmOperationView.onValid();
        }
        else
        System.out.println("Invalid Pin");
    }

    public void checkIfAmountvalid(double amount) {
        double wamount=amount;
        Customers customer=atMdatalayer.getCurrentUser();
        boolean flag=true;
        if(amount>10000 || amount<100){
            flag=false;
        }
        else  if(atm.getTotalAmount()==0 || atm.getTotalAmount()<amount){
            atmOperationView.onError("ATM does not have enough money");
            flag=false;
        }
        else if(customer.getBalance()<amount){
            atmOperationView.onError(
                    "Account balance is lower then entered withdrawal amount");
            flag=false;
        }
        if(flag){
            if(amount<=5000){
                if(amount<1000){
                    atm.setHunderedCount(atm.getHunderedCount()-((int)amount/100));
                }
                else  if(amount>=1000){
                    boolean thous=true;
                    boolean h=true;
                    while (amount!=0) {
                        if (atm.getThousandCount() > 0 && amount>=1000 && thous) {
                                atm.setThousandCount(atm.getThousandCount() - 1);
                                amount -= 1000;
                                thous=false;
                        }
                        else if(atm.getHunderedCount()>0 && amount>=100 && h){
                            atm.setHunderedCount(atm.getHunderedCount()-10);
                            amount-=(10*100);
                            h=false;
                        }
                        else if(atm.getFiveHunderedCount()>0 && amount>=500){
                            if((int)amount/500<=6) {
                                int getCount=(int)amount/500;
                                atm.setFiveHunderedCount(atm.getFiveHunderedCount()-getCount);
                                amount-=(getCount*500);
                            }
                        }

                    }
                }
            }
            else{
                boolean thous=true;
                boolean h=true;
                while (amount!=0) {
                    if (atm.getThousandCount() > 0 && amount>=1000 && thous) {
                        atm.setThousandCount(atm.getThousandCount() - 3);
                        amount -= 3000;
                        thous=false;
                    }
                    else if(atm.getHunderedCount()>0 && h){
                        atm.setHunderedCount(atm.getHunderedCount()-10);
                        amount-=(10*100);
                        h=false;
                    }
                    else if(atm.getFiveHunderedCount()>0) {
                        int getCount=(int)amount/500;
                        atm.setFiveHunderedCount(atm.getFiveHunderedCount()-getCount);
                        amount-=(getCount*500);
                    }
                }
            }
            atm.setTotalAmount(atm.getTotalAmount()-wamount);
            customer.setBalance(customer.getBalance()-wamount);
            atmOperationView.onSuccess("Amount Withdrawn successfully");
            System.out.println(atm.getTotalAmount()+" "+atm.getThousandCount()+" "+atm.getFiveHunderedCount()+" "+atm.getHunderedCount());
            System.out.println("customer balance: "+customer.getBalance());
            atMdatalayer.transfer(new Transfer(customer.getAccNo(),"Debited"+wamount+"₹from ATM","Debit",wamount));
        }
    }

    public void isValid(int accNo, String name) {
        Customers transferCus=null;
        List<Customers> customersList=atMdatalayer.getAllCustomer();
        for(Customers customer:customersList){
            if(customer.getAccNo()==accNo && customer.getName().equals(name)){
                transferCus=customer;
                break;
            }
        }
        if(transferCus!=null){
            atmOperationView.getAmount(transferCus);
        }else
            atmOperationView.onError("Invalid customer");
    }


    public void validCustomer(Customers transferCus,double amount) {
        Customers currCustomer=atMdatalayer.getCurrentUser();
        boolean flag=true;
        if(amount>10000 || amount<1000){
            atmOperationView.onError("Transfer amount must be between minimum thousand rupees and maximum Ten thousand");
            flag=false;
        }

        else if(currCustomer.getBalance()<amount){
            atmOperationView.onError("Account balance is lower then entered withdrawal amount");
            flag=false;
        }
        if(flag){
            currCustomer.setBalance(currCustomer.getBalance()-amount);
            transferCus.setBalance(transferCus.getBalance()+amount);
            atMdatalayer.transfer(new Transfer(currCustomer.getAccNo(),"Funds Transfer to Acc"+transferCus.getAccNo(),"Debit",amount));
            atMdatalayer.transfer(new Transfer(transferCus.getAccNo(),"Funds Transfer from Acc "+currCustomer.getAccNo(),"Credit",amount));
            atmOperationView.onSuccess("Successfully Transfer");
        }
    }

    public void getTransferList() {
        Customers currCustomer=atMdatalayer.getCurrentUser();
        List<Transfer> transferList=atMdatalayer.getAllTransfer();
        List<Transfer> transferCus=new ArrayList<>();
        for(Transfer transfer:transferList){
            if(transfer.getAccNo()==currCustomer.getAccNo()){
                transferCus.add(transfer);
            }
        }
        atmOperationView.showTransfer(currCustomer,transferCus);
    }
}
