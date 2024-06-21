package atmprocess;

import atmoperation.ATMOperationView;
import datalayer.ATMdatalayer;
import model.ATM;
import model.Customers;

import java.util.ArrayList;
import java.util.List;

public class ProcessModel {
    private ProcessView processView;
    private ATMdatalayer atMdatalayer;
    public ProcessModel(ProcessView processView) {
        this.processView=processView;
        this.atMdatalayer=ATMdatalayer.getInstance();
    }

    public void checkValid(double total, int thousand, int fiveHundered, int hundered) {
        int sum=(1000*thousand)+(500*fiveHundered)+(100*hundered);
        if(total!=sum){
            processView.onError("Invalid amount");
        }
        else {
            atMdatalayer.loadAmount(total,thousand,fiveHundered,hundered);
            ATM atm=atMdatalayer.getATMamount();
            processView.onSuccess("Amount load successfully");
        }
    }

    public void checkValidUser(int accNo, int pin) {
        Customers customers=atMdatalayer.checkValidcustomer(accNo,pin);
        if(customers!=null){
            new ATMOperationView().init();
        }
        else {
            processView.onError("Invalid User");
        }
    }

    public void showCustomerDetails() {
        List<Customers> customers=new ArrayList<>();
        processView.showCustomer(customers);
    }
}
