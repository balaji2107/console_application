package in.interviewpanel2024.companysetup;

//import in.interviewpanel2024.candidatesetup.candidatesetup.CandidateSetupView;
import in.interviewpanel2024.candidatesetup.CandidateSetupView;
import in.interviewpanel2024.interviewersetup.InterviewerSetupView;
import in.interviewpanel2024.login.LoginView;
import in.interviewpanel2024.InterviewPanel2024;
import in.interviewpanel2024.manageinterview.InterviewConductView;
import in.interviewpanel2024.model.Company;

import java.util.Scanner;

public class InterviewSetupView {
    private InterviewSetupModel interviewSetupModel;

    public InterviewSetupView(){
        interviewSetupModel=new InterviewSetupModel(this);
    }

    public void init() {
        interviewSetupModel.startSetup();
    }

    public void onSetupComplete() {
        System.out.println("\nCompany setup already completed");
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1. Manage interview \n2. Manage Interviewer \n3. Manage Candidate \n4. logout \n5.Exit");
            System.out.println("Enter the choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    new InterviewConductView().init();
                    break;
                case 2:
                    new InterviewerSetupView().init();
                    break;
                case 3:
                    new CandidateSetupView().init();
                    break;
                case 4:
                    System.out.println("\n-- You are logged out successfully -- \n\n");
                    new LoginView().init();
                    return;
                case 5:
                    System.out.println("\n-- Thanks for using " + InterviewPanel2024.getInstance().getAppName() + " --");
                    interviewSetupModel.storeFile();
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
        Company company=new Company();
        try {
            System.out.println("Enter the Company Name: ");
            String companyName = sc.nextLine();
            System.out.println("Enter the Company EmailId: ");
            String companyEmail = sc.nextLine();
            System.out.println("Enter the Company Address: ");
            String companyAddress = sc.nextLine();
            System.out.println("Enter the Company Contact No: ");
            long companyContact = sc.nextLong();

            company.setCompanyName(companyName);
            company.setCompanyEmailId(companyEmail);
            company.setCompanyAddress(companyAddress);
            company.setContactNo(companyContact);

            interviewSetupModel.createLibrary(company);
        }
        catch (Exception e){
            System.out.println("Invalid input format");
        }
    }
}
