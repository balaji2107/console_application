package com.librarymanagement.librarysetup;

import com.librarymanagement.LibraryManagement;
import com.librarymanagement.Model.Library;
import com.librarymanagement.changepassword.PasswordChangeView;
import com.librarymanagement.issuebook.IssueBookView;
import com.librarymanagement.login.LoginView;
import com.librarymanagement.managebooksetup.BookSetupView;
import com.librarymanagement.usersetup.UserSetupView;

import java.util.Scanner;

public class LibrarySetupView {
    private LibrarySetupModel librarySetupModel;

    public LibrarySetupView() {
        librarySetupModel = new LibrarySetupModel(this);
    }

    public void init() {
        librarySetupModel.startSetup();
    }

    public void onSetupComplete() {
        librarySetupModel.getAllFromDB();
        System.out.println("\nLibrary setup already completed");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1. Manage Book\n2. Manage User\n3. logout\n4. Change Password\n5. Book lending\n0. Exit");
            System.out.println("Enter the choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    new BookSetupView().init();
                    break;
                case 2:
                    new UserSetupView().init();
                    break;
                case 3:
                    System.out.println("\n-- You are logged out successfully -- \n\n");
                    new LoginView().init();
                    return;
                case 4:
                    new PasswordChangeView().init();
                    break;
                case 5:
                    new IssueBookView().init();
                    break;
                case 0:
                    System.out.println("\n-- Thanks for using " + LibraryManagement.getInstance().getAppName() + " --");
                    librarySetupModel.storeFile();
                    return;
                default:
                    System.out.println("\nPlease Enter valid choice\n");
            }
        }
    }

    public void showAlert(String alert) {
        System.out.println(alert);
    }

    public void initiateSetup() {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
            System.out.println("Enter the Library Name: ");
            String libraryName = sc.nextLine();
            System.out.println("Enter the Library EmailId: ");
            String libraryEmail = sc.nextLine();
            System.out.println("Enter the Library Address: ");
            String libraryAddress = sc.nextLine();
            System.out.println("Enter the Library Phone No: ");
            String phoneNo = sc.nextLine();

            library.setLibraryName(libraryName);
            library.setPhoneNo(phoneNo);
            library.setEmailId(libraryEmail);
            library.setAddress(libraryAddress);
            library.setLibraryId(library.getLibraryId()+1);
            librarySetupModel.createLibrary(library);

    }

}
