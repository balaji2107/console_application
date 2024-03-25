package com.librarymanagement.changepassword;

import com.librarymanagement.Model.Credentials;
import com.librarymanagement.datalayer.LibraryDatabase;
import com.librarymanagement.login.LoginView;

public class PasswordChangeModel {
    private PasswordChangeView passwordChangeView;


    PasswordChangeModel(PasswordChangeView passwordChangeView){
        this.passwordChangeView=passwordChangeView;
    }

    public void isPasswordExist(String oldPassword) {
        if(LibraryDatabase.getInstance().isPasswordCheck(oldPassword)){
            passwordChangeView.goNewPassword();
        }
        passwordChangeView.showAlert("Password Incorrect");
        passwordChangeView.checkForOldPassword();
    }

    public void createPassword(String newPassword,String confirm) {
        if(LibraryDatabase.getInstance().changePass(newPassword,confirm)){
            passwordChangeView.onSuccess("Password Update Successful");
            new LoginView().init();
        }
        else {
            passwordChangeView.showAlert("Password and confirm password Mismatch");
            passwordChangeView.goNewPassword();
        }
    }


}
