package atmoperation;

import model.Customers;
import model.Transfer;

import java.util.List;
import java.util.Scanner;

public class ATMOperationView {
    private ATMOperationModel atmOperationModel;
    public ATMOperationView(){
        atmOperationModel=new ATMOperationModel(this);
    }


    public void init() {
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("choose: ");
            System.out.println("1. Check Balance\n2. Withdraw Money\n 3.Transfer Money\n4. Mini Statement\5. logout");
            String choose=sc.nextLine();
            switch (choose){
                case "1":
                    atmOperationModel.showBalance();
                    break;
                case "2":
                    getPin();
                    break;
                case "3":
                    getCustomerDetail();
                    break;
                case "4":
                    atmOperationModel.getTransferList();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choose");
            }
        }
    }

    private void getCustomerDetail() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Transfer account Number: ");
        int accNo=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Account Holder name: ");
        String name=sc.nextLine();
        atmOperationModel.isValid(accNo,name);
    }

    private void getPin() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Pin: ");
        int pin=sc.nextInt();
         atmOperationModel.checkValidPin(pin);
    }

    public void showBalanceAmount(double balance) {
        System.out.println("Your Balance Amount is: "+balance);
    }

    public void onValid() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Withdraw amount: ");
        double amount=sc.nextDouble();
        atmOperationModel.checkIfAmountvalid(amount);
    }

    public void onSuccess(String amountWithdrawnSuccessfully) {
        System.out.println(amountWithdrawnSuccessfully);
    }

    public void onError(String error) {
        System.out.println(error);
    }

    public void getAmount(Customers transferCus) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Transfer amount: ");
        double amount=sc.nextDouble();
        atmOperationModel.validCustomer(transferCus,amount);
    }

    public void showTransfer(Customers currCustomer, List<Transfer> transferCus) {
        System.out.println("Account Number: "+currCustomer.getAccNo());
        System.out.println("Account Holder: "+currCustomer.getName());
        System.out.println("Account Balance: "+currCustomer.getBalance());

        System.out.println("Transaction Id\t Transaction Remarks\t Transaction Type\t amount");
        for (Transfer transfer:transferCus){
            System.out.println(transfer.getTid()+"\t"+transfer.getTRemarks()+"\t"+transfer.getTtype()+"\t"+transfer.getTamount());
        }
    }
}
