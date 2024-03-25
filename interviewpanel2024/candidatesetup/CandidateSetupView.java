package in.interviewpanel2024.candidatesetup;

import in.interviewpanel2024.model.Candidate;

import java.util.List;
import java.util.Scanner;

public class CandidateSetupView {
    private CandidateSetupModel candidateSetupModel;

    public CandidateSetupView(){
        candidateSetupModel=new CandidateSetupModel(this);
    }

    public void init() {
        while (true){
            Scanner sc=new Scanner(System.in);
            System.out.println("\nEnter your choice: ");
            System.out.println("1. Add Candidate\n2. View ALl Candidate\n3. Search Candidate\n4. Remove Candidate\n5. Move TO Home Page");
            int choice=sc.nextInt();
            switch (choice){
                case 1:
                    getDetailFromCandidate();
                    break;
                case 2:
                    getAllDetails();
                    break;
                case 3:
                    searchCandidate();
                    break;
                case 4:
                    getUserEmail();
                    break;
                case 5:
                    System.out.println("-----Thank You-----");
                    return;
            }
        }
    }


private void getUserEmail() {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the Candidate Email: ");
    String candidateEmail=sc.nextLine();
    candidateSetupModel.checkEmail(candidateEmail);

}

public void searchCandidate() {
    Scanner sc=new Scanner(System.in);
    System.out.println("Enter the Candidate name to search the Candidate: ");
    String candidateName=sc.nextLine();
    candidateSetupModel.findCandidate(candidateName);
}

private void getAllDetails() {
    candidateSetupModel.showAllCandidate();
}

public void getDetailFromCandidate() {
    try {
        Candidate candidate = new Candidate();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Candidate Name: ");
        String candidateName = sc.nextLine();
        System.out.println("Enter the candidate Email ID:");
        String candidateEmail = sc.nextLine();
        System.out.println("Enter the Candidate Phone no: ");
        long candidateMobileNo = sc.nextLong();

        candidate.setCandidateName(candidateName);
        candidate.setCandidateEmail(candidateEmail);
        candidate.setCandidateStatus("Pending");
        candidate.setCandidateMobile(candidateMobileNo);

        candidateSetupModel.createCandidateDetails(candidate);
    }
    catch (Exception e){
        System.out.println("Invalid input format");
        getDetailFromCandidate();
    }
}

public void checkForAddNewUser() {
    System.out.println("Do you want to add more Candidate? \nType Yes/No");
    Scanner scanner = new Scanner(System.in);
    String choice = scanner.next();
    if (choice.equalsIgnoreCase("yes")) {
        getDetailFromCandidate();
    } else if (choice.equalsIgnoreCase("no")) {
        System.out.println("\n Thanks for adding users");
    } else {
        System.out.println("\nInvalid choice, Please enter valid choice.\n");
        checkForAddNewUser();
    }
}


public void onSuccess(String candidateSuccessfullyAdded) {
    System.out.println(candidateSuccessfullyAdded);
}

public void showAlert(String showError) {
    System.out.println(showError);
}

public void showAll(List<Candidate> listOfCandidate) {
    if(listOfCandidate.size()==0){
        showAlert("No Candidate Found");
    }
    else {
        System.out.printf(" %10s %20s %20s %25s", "Candidate Name", "Candidate Email", "Candidate Status", "Candidate MobileNo");
        System.out.println();
        for (Candidate candidate : listOfCandidate) {
            System.out.format("%10s %20s %20s %25d",
                    candidate.getCandidateName(), candidate.getCandidateEmail(), candidate.getCandidateStatus(), candidate.getCandidateMobile());
            System.out.println();
        }
    }
}
}

