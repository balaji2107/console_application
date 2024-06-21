import atmprocess.ProcessView;
import datalayer.ATMdatalayer;

public class ATMapplication {
    public static void main(String[] args) {
        ATMdatalayer.getInstance().saveAllCustomer();
        ProcessView processView=new ProcessView();
        processView.init();
    }
}
