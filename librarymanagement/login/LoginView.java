package com.librarymanagement.login;

import com.librarymanagement.LibraryManagement;
import com.librarymanagement.Model.Credentials;
import com.librarymanagement.Model.User;
import com.librarymanagement.datalayer.LibraryDatabase;
import com.librarymanagement.librarysetup.LibrarySetupView;

import java.util.List;
import java.util.Scanner;

public class LoginView{
    private LoginModel loginModel;
    Scanner scanner=new Scanner(System.in);

    public LoginView(){
        loginModel=new LoginModel(this);
    }

    public void init() {
        System.out.println("--- " + LibraryManagement.getInstance().getAppName() + " --- \nversion "
                + LibraryManagement.getInstance().getAppVersion());
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

    public void onSuccess() {
        System.out.println(
                "\n\nLogin successful...\n\n ---- welcome to " + LibraryManagement.getInstance().getAppName()
                        + " - v" + LibraryManagement.getInstance().getAppVersion() + "----");
        loginModel.getLibraryDetail();
        LibrarySetupView librarySetupView = new LibrarySetupView();
        librarySetupView.init();

    }
    public void initiateSetup() {
        Credentials credentials=new Credentials();
        credentials.setUsername("zsgs");
        credentials.setPassword("admin");
        loginModel.createCredential(credentials);
    }
}
