package in.interviewpanel2024.login;

import com.librarymanagement.datalayer.LibraryDatabase;
import in.interviewpanel2024.datalayer.InterviewDatabase;
import in.interviewpanel2024.model.Credentials;

import java.util.List;
import java.util.stream.Collectors;

public class LoginModel {
    private LoginView loginView;
    private Credentials credentials=new Credentials();
    LoginModel(LoginView loginView)
    {
        this.loginView=loginView;
    }

    public void validateUser(String userName,String password){
        boolean usernameExist=false;
        boolean passwordExist=false;

            if (InterviewDatabase.getInstance().isUsernameCheck(userName)) {
                if (InterviewDatabase.getInstance().isPasswordCheck(password)) {
                    if(InterviewDatabase.getInstance().isAdmin()) {
                        loginView.onSuccess(1);
                    }
                    else if(InterviewDatabase.getInstance().isInterviewer()){
                        loginView.onSuccess(2);
                    }

                } else {
                    passwordExist=true;
                }
            } else {
                usernameExist=true;
            }

        if(usernameExist && passwordExist){
            loginView.showAlert("Invalid Username and password");
        }
        else if(passwordExist){
            loginView.showAlert("Invalid Password");
        }else {
            loginView.showAlert("Invalid Username");
        }
    }

    public void createAdmin(Credentials credentials) {
        if(InterviewDatabase.getInstance().insertCredential(credentials)){
            loginView.proceedLogin();
        }
    }

    public void startup() {
        if(InterviewDatabase.getInstance().checkExist()){
            loginView.initiateSetup();
        }
        else {
            loginView.proceedLogin();
        }
    }

    public void getCredential() {
        InterviewDatabase.getInstance().getCredentialFromFile();
    }

    public void getAllFromDB() {
        InterviewDatabase.getInstance().getCandidateFromFile();
        InterviewDatabase.getInstance().getInteviewerFromFile();
    }
}
