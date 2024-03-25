package in.interviewpanel2024.interviewersetup;


import in.interviewpanel2024.model.Candidate;
import in.interviewpanel2024.model.Credentials;
import in.interviewpanel2024.model.Interviewer;

import java.util.List;
import java.util.Scanner;

public class InterviewerSetupView {
    private InterviewerSetupModel interviewerSetupModel;
    public InterviewerSetupView(){
        interviewerSetupModel=new InterviewerSetupModel(this);
    }


    public void init() {
        while (true){
            Scanner sc=new Scanner(System.in);
            System.out.println("\nEnter your choice: ");
            System.out.println("1. Add Interviewer\n2. View ALl Interviewer\n3. Move TO Home Page");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    getDetailFromInterviewer();
                    break;
                case 2:
                    getAllDetails();
                    break;
                case 3:
                    System.out.println("-----Thank You-----");
                    return;
            }
        }
    }

    private void getAllDetails() {
        interviewerSetupModel.getAllInterviewer();
    }

    private void getDetailFromInterviewer() {
        try {
            Interviewer interviewer=new Interviewer();
            Credentials credentials=new Credentials();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the Interviewer ID: ");
            int interviewerId = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the Interviewer name:");
            String interviewerName = sc.nextLine();
            System.out.println("Enter the Interviewer Password");
            String interviewerPass=sc.nextLine();
            System.out.println("Enter the Interviewer Availability: ");
            boolean interviewerAvailable = sc.nextBoolean();

            interviewer.setId(interviewerId);
            interviewer.setName(interviewerName);
            interviewer.setAvailability(interviewerAvailable);

            credentials.setUserName(interviewerName);
            credentials.setPassword(interviewerPass);
            credentials.setRole(2);

            interviewerSetupModel.createInterviewerDetails(interviewer,credentials);
        }
        catch (Exception e){
            System.out.println("Invalid input format");
            getDetailFromInterviewer();
        }
    }

    public void onSuccess(String s) {
        System.out.println(s);
    }

    public void checkForAddInterviewer() {
        System.out.println("Do you want to add more Interviewer? \nType Yes/No");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("yes")) {
            getDetailFromInterviewer();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\n Thanks for adding users");
        } else {
            System.out.println("\nInvalid choice, Please enter valid choice.\n");
            checkForAddInterviewer();
        }
    }

    public void showAlert(String s) {
        System.out.println(s);
    }

    public void showALl(List<Interviewer> listOfInterviewer) {
        if(listOfInterviewer.size()==0){
            showAlert("No Inteviewer Found");
        }
        else {
            System.out.printf(" %10s %20s %20s", "Interviewer ID", "Interviewer Name", "Candidate Status");
            System.out.println();
            for (Interviewer interviewer : listOfInterviewer) {
                System.out.format("%10s %20s %20s", interviewer.getId(), interviewer.getName(), interviewer.getAvailability());
                System.out.println();
            }
        }
    }
}
