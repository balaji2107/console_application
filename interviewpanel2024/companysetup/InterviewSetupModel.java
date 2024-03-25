package in.interviewpanel2024.companysetup;

import com.librarymanagement.datalayer.LibraryDatabase;
import com.librarymanagement.validate.LibraryValidate;
import in.interviewpanel2024.datalayer.InterviewDatabase;
import in.interviewpanel2024.model.Company;

public class InterviewSetupModel {
    private InterviewSetupView interviewSetupView;

    private Company company;

    InterviewSetupModel(InterviewSetupView interviewSetupView){
        this.interviewSetupView=interviewSetupView;
        company= InterviewDatabase.getInstance().getCompany();
    }

    public void startSetup() {
        if ( company== null) {
            interviewSetupView.initiateSetup();
        } else {
            interviewSetupView.onSetupComplete();
        }
    }

    public void createLibrary(Company company) {
            LibraryValidate libraryValidate = new LibraryValidate();
            if (libraryValidate.validEmail(company.getCompanyEmailId()) && libraryValidate.validPhoneNo(company.getContactNo())) {
                InterviewDatabase.getInstance().insertCompany(company);
                interviewSetupView.onSetupComplete();
            } else {
                interviewSetupView.showAlert("Please Enter valid input");
                interviewSetupView.initiateSetup();

            }

    }

    public void storeFile() {
        InterviewDatabase.getInstance().dataCandidateStored();
        InterviewDatabase.getInstance().dataInterviewerStored();
        InterviewDatabase.getInstance().dataCredentialStored();
    }
}
