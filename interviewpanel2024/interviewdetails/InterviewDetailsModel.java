package in.interviewpanel2024.interviewdetails;

import in.interviewpanel2024.datalayer.InterviewDatabase;
import in.interviewpanel2024.model.Candidate;
import in.interviewpanel2024.model.Interviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterviewDetailsModel {
    private InterviewDetailsView interviewDetailsView;
    InterviewDetailsModel(InterviewDetailsView interviewDetailsView){
        this.interviewDetailsView=interviewDetailsView;
    }

    public void getInterviewer() {
        String username=InterviewDatabase.getInstance().getUserName();
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        for(Interviewer interviewer:listOfInterviewer){
            if(interviewer.getName().equals(username)){
                interviewDetailsView.showCurrentCandidate(interviewer);
                return;
            }
        }
    }

    public void showScheduledCandidate() {
        String username=InterviewDatabase.getInstance().getUserName();
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        List<Candidate> interviewerCandidate=new ArrayList<>();
        for(Interviewer interviewer:listOfInterviewer){
            if(interviewer.getName().equals(username)){
                for(Candidate candidate:interviewer.getCandidateDetail()){
                    if(candidate.getCandidateStatus().equals("Scheduled")){
                        interviewerCandidate.add(candidate);
                    }
                }
                interviewDetailsView.showSelectedCandidate(interviewerCandidate);
                interviewDetailsView.showScheduledCandidate();
                return;
            }
        }
    }

    public void checkEmail(String candidateEmail) {
        String username=InterviewDatabase.getInstance().getUserName();
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        for(Interviewer interviewer:listOfInterviewer){
            if(interviewer.getName().equals(username)) {
                for(Candidate candidate:interviewer.getCandidateDetail()){
                    if(candidate.getCandidateStatus().equals("Scheduled") && candidate.getCandidateEmail().equals(candidateEmail)){
                        rateMark(candidate);
                        return;
                    }
                }
                interviewDetailsView.showAlart("Candidate Not exist");
            }
        }
    }

    private void rateMark(Candidate candidate) {
        Scanner sc=new Scanner(System.in);
        interviewDetailsView.showAlart("Interview start........");
        System.out.println("Candidate selected or not Enter (Yes/No): ");
        String candidateRes=sc.nextLine().trim().toLowerCase();
        if(candidateRes.equals("yes")){
            candidate.setViewResult(true);
            candidate.setCandidateStatus("Completed");
        }
        else {
            candidate.setViewResult(false);
            candidate.setCandidateStatus("Completed");
        }
        interviewDetailsView.showAlart("Interview Complete........");
    }

    public void getSelectedCandidate() {
        String username=InterviewDatabase.getInstance().getUserName();
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        List<Candidate> selectedCandidate=new ArrayList<>();
        for(Interviewer interviewer:listOfInterviewer){
            if(interviewer.getName().equals(username)){
                for(Candidate candidate:interviewer.getCandidateDetail()){
                    if(candidate.getCandidateStatus().equals("Completed")){
                        selectedCandidate.add(candidate);
                    }
                }
                interviewDetailsView.showSelectedCandidate(selectedCandidate);
                return;
            }
        }
    }

    public void storeFile() {
        InterviewDatabase.getInstance().dataCandidateStored();
        InterviewDatabase.getInstance().dataInterviewerStored();
    }
}
