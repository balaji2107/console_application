package atmprocess;

import model.Customers;

import java.util.List;
import java.util.Scanner;

public class ProcessView {
    private ProcessModel processModel;
    private Scanner sc=new Scanner(System.in);
    public ProcessView(){
        processModel=new ProcessModel(this);
    }

    public void init() {
        while (true){
            System.out.println("Choose: ");
            System.out.println("1. Load Cash to ATM\n2. show Customer Details\n3. show ATM operation");
            String choose=sc.nextLine();
            switch (choose){
                case "1":
                    loadCash();
                    break;
                case "2":
                    processModel.showCustomerDetails();
                    break;
                case "3":
                    getCustomerDetail();
                    break;
                default:
                    System.out.println("Enter Valid Choose");
            }
        }
    }

    private void getCustomerDetail() {
        Scanner sc1=new Scanner(System.in);
        System.out.println("Enter the Account Number: ");
        int accNo=sc1.nextInt();
        System.out.println("Enter Pin Number: ");
        int pin=sc1.nextInt();
        processModel.checkValidUser(accNo,pin);
    }

    public void showCustomer(List<Customers> customers) {
        System.out.println("Account Number\t Account Holder\t Pin number\t balance");
        for(Customers customer:customers){
            System.out.println(customer.getAccNo()+"\t"+customer.getName()+"\t"+customer.getPin()+"\t"+customer.getBalance());
        }
    }

    private void loadCash() {
        System.out.println("Enter the Amount to load: ");
        double total=sc.nextDouble();
        System.out.println("Enter the How mush thousand rupees: ");
        int thousand=sc.nextInt();
        System.out.println("Enter the How mush five Hundred rupees: ");
        int fiveHundered=sc.nextInt();
        System.out.println("Enter the How mush Hundred rupees: ");
        int hundered=sc.nextInt();
        sc.nextLine();
        processModel.checkValid(total,thousand,fiveHundered,hundered);
    }

    public void onError(String error) {
        System.out.println(error);
    }

    public void onSuccess(String success) {
        System.out.println(success);
    }
}
