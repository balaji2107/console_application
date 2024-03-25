package in.interviewpanel2024.interviewersetup;

import in.interviewpanel2024.datalayer.InterviewDatabase;
import in.interviewpanel2024.model.Credentials;
import in.interviewpanel2024.model.Interviewer;

import java.util.List;

public class InterviewerSetupModel {
    private InterviewerSetupView interviewerSetupView;
    InterviewerSetupModel(InterviewerSetupView interviewerSetupView){
        this.interviewerSetupView=interviewerSetupView;
    }
    public void createInterviewerDetails(Interviewer interviewer,Credentials credentials) {
        if(InterviewDatabase.getInstance().insertInterviewer(interviewer)){
            if(InterviewDatabase.getInstance().insertCredential(credentials)) {
                interviewerSetupView.onSuccess("Interviewer " + interviewer.getName() + " Successfully added");
                interviewerSetupView.checkForAddInterviewer();
            }
        }
        else {
            interviewerSetupView.showAlert("Interviewer "+interviewer.getName()+" Already Exist");
            interviewerSetupView.checkForAddInterviewer();
        }
    }
    public void getAllInterviewer() {
        List<Interviewer> listOfInterviewer=InterviewDatabase.getInstance().getAllInterviewer();
        interviewerSetupView.showALl(listOfInterviewer);
    }
}
