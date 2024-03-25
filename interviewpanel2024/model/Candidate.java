package in.interviewpanel2024.model;

public class Candidate {
    private String candidateName;
    private String candidateEmail;
    private String candidateStatus;
    private long candidateMobile;
    private boolean viewResult;

    public boolean isViewResult() {
        return viewResult;
    }

    public void setViewResult(boolean viewResult) {
        this.viewResult = viewResult;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateStatus() {
        return candidateStatus;
    }

    public void setCandidateStatus(String candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    public long getCandidateMobile() {
        return candidateMobile;
    }

    public void setCandidateMobile(long candidateMobile) {
        this.candidateMobile = candidateMobile;
    }
}
