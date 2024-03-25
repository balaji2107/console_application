package in.interviewpanel2024.manageinterview;

import in.interviewpanel2024.model.Candidate;
import in.interviewpanel2024.model.Interviewer;

import java.util.List;
import java.util.Scanner;

public class InterviewConductView {
    private InterviewConductModel interviewConductModel;

    public InterviewConductView(){
        interviewConductModel=new InterviewConductModel(this);
    }

    public void init() {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("Enter the choice: ");
            System.out.println("1. Interview Schedule\n2. view Available Interviewer\n3. Move to HomeScreen");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    proceedSchedule();
                    break;
                case 2:
                    proceedGetAvailable();
                    break;
                case 3:
                    System.out.println("------Thank You-------");
                    return;
                default:
                    System.out.println("Please Enter Valid Choice");
            }
        }

    }


    public void proceedGetAvailable() {
        interviewConductModel.viewAllAvailableInterviewer();
    }

    private void proceedSchedule() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Candidate email: ");
        String candidateEmail=sc.nextLine();
        interviewConductModel.isEmailExist(candidateEmail);
    }

    public void checkAvailbleInterviewer(Candidate candidate) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the Interviewer Id to assign the candidate: ");
            int interviewerId = sc.nextInt();
            interviewConductModel.assignSchedule(candidate, interviewerId);
    }


    public void showAll(List<Interviewer> availableInterviewer) {
        if(availableInterviewer.size()==0){
            showAlert("No Interviewer Found");
        }
        else {
            System.out.printf(" %10s %20s %20s", "Interviewer ID", "Interviewer Name", "Interviewer Status");
            System.out.println();
            for (Interviewer interviewer : availableInterviewer) {
                System.out.format("%10s %20s %20s", interviewer.getId(), interviewer.getName(), interviewer.getAvailability());
                System.out.println();
            }
        }
    }

    public void showAlert(String candidateNotExist) {
        System.out.println(candidateNotExist);
    }

    public void onsuccess(String s) {
        System.out.println(s);
    }


}
