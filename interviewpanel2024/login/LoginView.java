package in.interviewpanel2024.login;

import in.interviewpanel2024.InterviewPanel2024;
import in.interviewpanel2024.companysetup.InterviewSetupView;
import in.interviewpanel2024.interviewdetails.InterviewDetailsView;
import in.interviewpanel2024.model.Credentials;

import java.util.Scanner;

public class LoginView {

    private LoginModel loginModel;
    public LoginView(){
        loginModel=new LoginModel(this);
    }

    Scanner scanner=new Scanner(System.in);
    public void init() {
        System.out.println("--- " + InterviewPanel2024.getInstance().getAppName() + " --- \nversion "
                + InterviewPanel2024.getInstance().getAppVersion());
        loginModel.getCredential();
        loginModel.startup();
    }

    public void proceedLogin() {
        System.out.println("\n\nPlease login to proceed.");
        System.out.println("Enter the Username: ");
        String userName=scanner.nextLine();
        System.out.println("Enter the Password: ");
        String password=scanner.nextLine();
        loginModel.validateUser(userName,password);
    }

    public void showAlert(String invalidPassword) {
        System.out.println(invalidPassword);
        checkForLogin();
    }

    private void checkForLogin() {
        System.out.println("Do you try again? \nType Yes/No");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            proceedLogin();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\n ---- Thanks You ----");
        } else {
            System.out.println("\nInvalid choice, Please enter valid choice.\n");
            checkForLogin();
        }
    }

    public void onSuccess(int role) {
        System.out.println(
                "\n\nLogin successful...\n\n ---- welcome to " + InterviewPanel2024.getInstance().getAppName()
                        + " - v" + InterviewPanel2024.getInstance().getAppVersion() + "----");

        if (role == 1){
            loginModel.getAllFromDB();
            InterviewSetupView interviewSetupView = new InterviewSetupView();
            interviewSetupView.init();
        }
        else if(role ==2){
            loginModel.getAllFromDB();
            InterviewDetailsView interviewDetailsView=new InterviewDetailsView();
            interviewDetailsView.init();
        }

    }
    public void initiateSetup() {
        Credentials credentials=new Credentials();
        credentials.setUserName("zsgs");
        credentials.setPassword("admin");
        credentials.setRole(1);
        loginModel.createAdmin(credentials);
    }
}
