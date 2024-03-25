package com.librarymanagement.changepassword;

import com.librarymanagement.Model.Credentials;
import com.librarymanagement.login.LoginView;

import java.util.Scanner;

public class PasswordChangeView {
    private PasswordChangeModel passwordChangeModel;

    public PasswordChangeView(){
        passwordChangeModel=new PasswordChangeModel(this);
    }


    public void onSuccess(String passwordUpdateSuccessful) {
        System.out.println(passwordUpdateSuccessful);

    }

    public void init() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter your Old password");
        String oldPassword = sc.nextLine();
        passwordChangeModel.isPasswordExist(oldPassword);
    }

    public void goNewPassword() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the New Password");
        String newPassword=sc.nextLine();
        System.out.println("Enter the confirm Password: ");
        String confirmPassword=sc.nextLine();
        passwordChangeModel.createPassword(newPassword,confirmPassword);
    }

    public void showAlert(String res) {
        System.out.println(res);
    }

    public void checkForOldPassword() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Do you try again? \nType Yes/No");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            init();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\n ---- Thanks You ----");
        } else {
            System.out.println("\nInvalid choice, Please enter valid choice.\n");
            checkForOldPassword();
        }
    }
}


