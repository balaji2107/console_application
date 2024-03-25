package in.interviewpanel2024.manageinterview;

import in.interviewpanel2024.datalayer.InterviewDatabase;
import in.interviewpanel2024.model.Candidate;
import in.interviewpanel2024.model.Interviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterviewConductModel {
    private InterviewConductView interviewConductView;

    InterviewConductModel(InterviewConductView interviewConductView){
        this.interviewConductView=interviewConductView;
    }

    public void isEmailExist(String candidateEmail) {
        List<Candidate> listOfCandidate= InterviewDatabase.getInstance().getALlCandidate();
        for(Candidate candidate:listOfCandidate) {
            if(candidate.getCandidateEmail().equals(candidateEmail) && candidate.getCandidateStatus().equals("Pending")){
                interviewConductView.checkAvailbleInterviewer(candidate);
                return;
            }
        }
        interviewConductView.showAlert("Candidate not Exist");
    }

    public void viewAllAvailableInterviewer() {
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        List<Interviewer> availableInterviewer=new ArrayList<>();
        for(Interviewer interviewer:listOfInterviewer){
            if(interviewer.getAvailability()){
                availableInterviewer.add(interviewer);
            }
        }
        interviewConductView.showAll(availableInterviewer);
    }

    public void assignSchedule(Candidate candidate, int interviewerId) {
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        boolean interviewerExist=false;
        for(Interviewer interviewer:listOfInterviewer){
            if(interviewer.getId()==interviewerId){
                if(interviewer.getAvailability() && interviewer.getGetAvailableCount()<3) {
                    interviewer.setCandidateDetail(candidate);
                    interviewer.setGetAvailableCount(interviewer.getGetAvailableCount()+1);
                    System.out.println(interviewer.getGetAvailableCount());
                    candidate.setCandidateStatus("Scheduled");
                    interviewConductView.onsuccess("Interview Scheduled Successfully!...");
                    return;
                }
                else {
                    if(interviewer.getGetAvailableCount()>2){
                        interviewer.setAvailability(false);
                        interviewConductView.showAlert("Interviewer unavailable!...");
                        return;
                    }
                    interviewConductView.showAlert("Interviewer unavailable!...");
                    return;
                }
            }
            else{
                interviewerExist=true;
            }
        }
        if(interviewerExist) {
            interviewConductView.showAlert("Interviewer not Exist");
        }
        else {
            interviewConductView.showAlert("Interview Schduled unsuccessful!...");
        }
    }
}
