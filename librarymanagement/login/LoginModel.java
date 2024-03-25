package com.librarymanagement.login;

import com.librarymanagement.Model.Credentials;
import com.librarymanagement.Model.User;
import com.librarymanagement.datalayer.LibraryDatabase;

import java.util.List;

class LoginModel {
    private LoginView loginView;


    LoginModel(LoginView loginView) {

        this.loginView = loginView;
    }

    public void validateUser(String userName, String password) {
        if (LibraryDatabase.getInstance().isUsernameCheck(userName)) {
            if (LibraryDatabase.getInstance().isPasswordCheck(password)) {
                loginView.onSuccess();
            } else {

                loginView.showAlert("Invalid Password");
                loginView.proceedLogin();
            }
        } else {
            loginView.showAlert("Invalid Username");
            loginView.proceedLogin();
        }
    }


    public void createCredential(Credentials credentials) {
        if(LibraryDatabase.getInstance().insertCredentials(credentials))
        {
            loginView.proceedLogin();
        }
    }


    public void getCredential() {
        LibraryDatabase.getInstance().getCredentialFromFile();
    }

    public void startup() {
        if(LibraryDatabase.getInstance().checkExist()){
            loginView.initiateSetup();
        }
        else {
            loginView.proceedLogin();
        }

    }

    public void getLibraryDetail() {
        LibraryDatabase.getInstance().getLibraryFromFile();
    }
}
