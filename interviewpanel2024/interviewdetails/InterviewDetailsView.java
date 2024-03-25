package in.interviewpanel2024.interviewdetails;

import in.interviewpanel2024.login.LoginView;
import in.interviewpanel2024.model.Candidate;
import in.interviewpanel2024.model.Interviewer;

import java.util.List;
import java.util.Scanner;

public class InterviewDetailsView {
    private InterviewDetailsModel interviewDetailsModel;

    public InterviewDetailsView(){
        interviewDetailsModel=new InterviewDetailsModel(this);
    }

    public void init() {
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("Enter the Choice: ");
            System.out.println("1. View Candidate detail\n2. interview start\n3. Selected Candidate\n4. logout");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    viewCandidate();
                    break;
                case 2:
                    startInterview();
                    break;
                case 3:
                    selectedCandidate();
                    break;
                case 4:
                    System.out.println("\n-- You are logged out successfully -- \n\n");
                    interviewDetailsModel.storeFile();
                    new LoginView().init();
                    return;
            }
        }
    }

    private void selectedCandidate() {
        interviewDetailsModel.getSelectedCandidate();
    }

    private void startInterview() {
        interviewDetailsModel.showScheduledCandidate();
    }

    private void viewCandidate() {
        interviewDetailsModel.getInterviewer();
    }

    public void showCurrentCandidate(Interviewer interviewer) {
        if(interviewer.getCandidateDetail().size()==0){
            showAlart("No Candidate Found");
        }
        else {
            System.out.printf(" %10s %20s %20s %25s", "Candidate Name", "Candidate Email", "Candidate Status", "Candidate MobileNo");
            System.out.println();
            for (Candidate candidate : interviewer.getCandidateDetail()) {
                System.out.format("%10s %20s %20s %25d",
                        candidate.getCandidateName(), candidate.getCandidateEmail(), candidate.getCandidateStatus(), candidate.getCandidateMobile());
                System.out.println();
            }
        }
    }

    public void showScheduledCandidate() {
        System.out.println("-----------------\n\n");
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Candidate Email to Select the candidate for start an interview");
        String candidateEmail=sc.nextLine();
        interviewDetailsModel.checkEmail(candidateEmail);
    }

    public void showAlart(String candidateNotExist) {
        System.out.println(candidateNotExist);
    }

    public void showSelectedCandidate(List<Candidate> selectedCandidate) {
        if(selectedCandidate.size()==0){
            showAlart("No Record Found");
        }
        else {
            System.out.printf(" %10s %20s %20s %25s %30s", "Candidate Name", "Candidate Email", "Candidate Status", "Candidate MobileNo", "Candidate Result");
            System.out.println();
            for (Candidate candidate : selectedCandidate) {
                System.out.format("%10s %20s %20s %25d %30s",
                        candidate.getCandidateName(), candidate.getCandidateEmail(), candidate.getCandidateStatus(), candidate.getCandidateMobile(), candidate.isViewResult());
                System.out.println();
            }
        }
    }
}
