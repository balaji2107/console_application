package in.interviewpanel2024;


import in.interviewpanel2024.login.LoginView;

public class InterviewPanel2024 {
    private static InterviewPanel2024 interviewPanel2024;
    private String appName = "Interview panel System";
    private String appVersion = "0.0.1";
    private InterviewPanel2024() {
    }
    public static InterviewPanel2024 getInstance() {
        if(interviewPanel2024 == null) {
            interviewPanel2024 = new InterviewPanel2024();
        }
        return interviewPanel2024;
    }

    private void create() {
        LoginView loginView=new LoginView();
        loginView.init();
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public static void main(String[] args) {
        InterviewPanel2024.getInstance().create();
    }
}
