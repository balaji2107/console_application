package com.librarymanagement.usersetup;

import com.librarymanagement.Model.User;
import java.util.List;
import java.util.Scanner;

public class UserSetupView {

    private UserSetupModel userSetupModel;
    public UserSetupView(){
        userSetupModel=new UserSetupModel(this);
    }
    public void init() {
        Scanner sc=new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add User\n2. Update User\n3. view User\n4. Exit");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    initAdd();
                    break;
                case 2:
                    initUpdate();
                    break;
                case 3:
                    viewUser();
                    break;
                case 4:
                    System.out.println("Thank You!");
                    return;
                default:
                    System.out.println("\nPlease Enter valid choice\n");
            }

        }

    }

    private void initUpdate() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please Enter Rollno to update the record: ");
            String userRoll =sc.nextLine();
            if (userSetupModel.isExistUser(userRoll)) {
                System.out.println("Enter the User Email Id: ");
                String userEmail = sc.nextLine();
                System.out.println("Enter the User Mobile No: ");
                long userPhoneNo = sc.nextLong();
                userSetupModel.modifyUser(userRoll,userEmail, userPhoneNo);
            }
        }
        catch (Exception e){
            System.out.println("Input Invalid Format");
            initUpdate();
        }
    }

    public void initAdd() {
        try {
            System.out.println("Enter the following user Details: ");
            Scanner scanner = new Scanner(System.in);
            User user = new User();
            user.setId(user.getId()+1);
            System.out.println("\nEnter user Roll no:");
            user.setRollno(scanner.nextLine());
            System.out.println("\nEnter user name:");
            user.setName(scanner.nextLine());
            System.out.println("\nEnter user emailId:");
            user.setEmailId(scanner.nextLine());
            System.out.println("\nEnter user phoneNo:");
            user.setPhoneNo(scanner.nextLong());
            userSetupModel.addNewUser(user);
        }
        catch (Exception e){
            System.out.println("Please Enter Valid input");
            initAdd();
        }
    }

    public void showLibraryName(String libraryName) {
        System.out.println("Current Library Name - " + libraryName);
    }

    public void onUserAdded(User user) {
        System.out.println("\n------- User '" + user.getName() + "' added successfully ------- \n");
        checkForAddNewUser();
    }

    public void onUserExist(User user) {
        System.out.println("\n------- User '" + user.getName() + "' already exist -------\n");
        checkForAddNewUser();
    }

    private void checkForAddNewUser() {
        System.out.println("Do you want to add more users? \nType Yes/No");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            initAdd();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\n Thanks for adding users");
        } else {
            System.out.println("\nInvalid choice, Please enter valid choice.\n");
            checkForAddNewUser();
        }
    }

    public void onSuccess(User user) {
        System.out.println("\n------- User '" + user.getName() + "' update successfully ------- \n");
        checkForAnotherUpdate();
    }

    private void checkForAnotherUpdate() {
        System.out.println("Do you want more users update? \nType Yes/No");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            initUpdate();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\n Thanks for update users");
        } else {
            System.out.println("\nInvalid choice, Please enter valid choice.\n");
            checkForAnotherUpdate();
        }
    }

    public void showAlert(String userId) {
        System.out.println("\n------- User '" + userId+ "' doesn't exist -------\n");
        checkForAnotherUpdate();
    }

    public void showSearchResult(List<User> searchListBook){
        System.out.println("-----Search result-----");
        System.out.println("User ID \t User Name \t User Email \t User Phone");
        System.out.println();
        for(User user:searchListBook){
            System.out.println("\t" +user.getRollno()+"\t\t"+user.getName()+"\t\t\t"+user.getEmailId()+"\t\t\t\t"+user.getPhoneNo());
        }
    }
    private void viewUser() {
        userSetupModel.showAllUser();
    }
}
