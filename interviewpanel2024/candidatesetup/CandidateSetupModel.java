package in.interviewpanel2024.candidatesetup;

import in.interviewpanel2024.datalayer.InterviewDatabase;
import in.interviewpanel2024.model.Candidate;

import java.util.List;

class CandidateSetupModel {
    private CandidateSetupView candidateSetupView;
    CandidateSetupModel(CandidateSetupView candidateSetupView){
        this.candidateSetupView=candidateSetupView;
    }

    public void createCandidateDetails(Candidate candidate) {
        if(InterviewDatabase.getInstance().insertCandidate(candidate)){
            candidateSetupView.onSuccess("Candidate " +candidate.getCandidateName()+ " Successfully added");
            candidateSetupView.checkForAddNewUser();
        }
        else {
            candidateSetupView.showAlert("Candidate "+candidate.getCandidateName()+" Already Exist");
            candidateSetupView.checkForAddNewUser();
        }
    }

    public void showAllCandidate() {
        List<Candidate> listOfCandidate=InterviewDatabase.getInstance().getALlCandidate();
        candidateSetupView.showAll(listOfCandidate);
    }

    public void findCandidate(String candidateName) {

        List<Candidate> listOfCandidate=InterviewDatabase.getInstance().searchCandidate(candidateName);
        if(listOfCandidate.size()==0) {
            candidateSetupView.showAlert("No Result Found!");
        }
        else {
            candidateSetupView.showAll(listOfCandidate);
        }
    }

    public void checkEmail(String candidateEmail) {
        List<Candidate> listOfCandidate=InterviewDatabase.getInstance().getALlCandidate();
        for(Candidate candidate:listOfCandidate){
            if(candidate.getCandidateEmail().equals(candidateEmail)){
                InterviewDatabase.getInstance().removeCandidate(candidate);
                candidateSetupView.onSuccess("Candidate Removed Successful");
                return;
            }
        }
        candidateSetupView.showAlert("Candidate not Exist");

    }
}
