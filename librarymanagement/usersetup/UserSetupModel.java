package com.librarymanagement.usersetup;

import com.librarymanagement.Model.Book;
import com.librarymanagement.Model.User;
import com.librarymanagement.datalayer.LibraryDatabase;

import java.util.List;

public class UserSetupModel {
    private UserSetupView userSetupView;
    private LibraryDatabase libraryDatabase;

    UserSetupModel(UserSetupView userSetupView){
        libraryDatabase=LibraryDatabase.getInstance();
        this.userSetupView=userSetupView;
    }
    public void addNewUser(User user) {
        if (LibraryDatabase.getInstance().insertUser(user)) {
            userSetupView.onUserAdded(user);
        } else {
            userSetupView.onUserExist(user);
        }
    }

    public void modifyUser(String userId, String userEmail, long userPhoneNo) {
        List<User> listOfUser=libraryDatabase.getALlUser();
        for(User user:listOfUser){
            if(user.getRollno().equals(userId)){
                user.setEmailId(userEmail);
                user.setPhoneNo(userPhoneNo);
                userSetupView.onSuccess(user);
            }
        }
        userSetupView.showSearchResult(listOfUser);
    }

    public void showAllUser() {
        List<User> listOfUser=libraryDatabase.getALlUser();
        if(listOfUser.size()==0){
            userSetupView.showAlert("No User Found");
        }
        else {
            userSetupView.showSearchResult(listOfUser);
        }
    }

    public boolean isExistUser(String userId) {
        List<User> listOfUser=libraryDatabase.getALlUser();
        boolean flag=false;
        for(User user:listOfUser){
            if(user.getRollno().equals(userId)){
                flag=true;
            }
        }
        if(flag)
            return true;
        else {
            userSetupView.showAlert(userId);
            return false;
        }

    }
}
