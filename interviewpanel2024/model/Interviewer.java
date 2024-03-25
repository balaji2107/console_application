package in.interviewpanel2024.model;

import java.util.ArrayList;
import java.util.List;

public class Interviewer {
    private int id;
    private String name;
    private boolean availability;
    private int getAvailableCount;
    private List<Candidate> candidateDetail=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public List<Candidate> getCandidateDetail() {
        return candidateDetail;
    }
    public void setCandidateDetail(Candidate candidateDetail1) {
        candidateDetail.add(candidateDetail1);
    }

    public int getGetAvailableCount() {
        return getAvailableCount;
    }

    public void setGetAvailableCount(int getAvailableCount) {
        this.getAvailableCount = getAvailableCount;
    }

}
