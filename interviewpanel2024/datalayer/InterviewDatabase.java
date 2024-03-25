package in.interviewpanel2024.datalayer;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.librarymanagement.Model.User;
import in.interviewpanel2024.model.Candidate;
import in.interviewpanel2024.model.Company;
import in.interviewpanel2024.model.Credentials;
import in.interviewpanel2024.model.Interviewer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InterviewDatabase {
    private static InterviewDatabase interviewDatabase;
    private Company company;
    List<Candidate> candidateList=new ArrayList<>();
    List<Interviewer> interviewerList=new ArrayList<>();
    List<Credentials> listOfCredential=new ArrayList<>();

    public static InterviewDatabase getInstance() {
        if (interviewDatabase == null) {
            interviewDatabase = new InterviewDatabase();
        }
        return interviewDatabase;
    }

    public  List<Candidate> searchCandidate(String candidateName) {
        List<Candidate> resultSearch=new ArrayList<>();
        for(Candidate candidate:candidateList) {
            if (candidate.getCandidateName().startsWith(candidateName) || candidate.getCandidateName().contains(candidateName)) {
                resultSearch.add(candidate);
            }
        }
        return resultSearch;
    }

    public Company getCompany() {
        return company;
    }
    public void insertCompany(Company company) {
        this.company = company;
    }

    public List<Candidate> getALlCandidate(){
        return candidateList;
    }

    public List<Interviewer> getAllInterviewer(){ return  interviewerList;}

    public List<Credentials> getAllCredential(){ return  listOfCredential;}
    public boolean insertCandidate(Candidate candidate){
        for(Candidate cadidateList:candidateList){
            if(cadidateList.getCandidateEmail().equals(candidate.getCandidateEmail())) {
               return false;
            }
        }
        candidateList.add(candidate);
        return true;
    }

    public void removeCandidate(Candidate candidate) {
        candidateList.remove(candidate);
    }

    public boolean insertInterviewer(Interviewer interviewer) {
        for(Interviewer interviewerlist:interviewerList){
            if(interviewerlist.getId()==interviewer.getId()) {
                return false;
            }
        }
        interviewerList.add(interviewer);
        return true;
    }

    public boolean insertCredential(Credentials credentials) {
        listOfCredential.add(credentials);
        return true;
    }
    private Credentials passwordCheck;
    public boolean isUsernameCheck(String userName) {
        for(Credentials credential:listOfCredential){
            if(credential.getUserName().equals(userName)) {
                passwordCheck = credential;
                return true;
            }
        }
        return false;
    }

    public boolean isPasswordCheck(String password) {
        if(passwordCheck==null){
            return false;
        }
        else if(passwordCheck.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean isAdmin() {
        if(passwordCheck.getRole()==1){
            return true;
        }
        return false;
    }

    public boolean isInterviewer() {
        if(passwordCheck.getRole()==2){
            return true;
        }
        return false;
    }

    public String getUserName() {
        return passwordCheck.getUserName();
    }

    public void dataCandidateStored() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(candidateList);
        String filePath = "c:\\java\\DB\\Interview\\candidate.json";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
            writer.close();
            System.out.println("Successfully wrote JSON to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dataInterviewerStored() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(interviewerList);
        String filePath = "c:\\java\\DB\\Interview\\interviewer.json";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
            writer.close();
            System.out.println("Successfully wrote JSON to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dataCredentialStored() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(listOfCredential);
        String filePath = "c:\\java\\DB\\Interview\\credential.json";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(jsonString);
            writer.close();
            System.out.println("Successfully wrote JSON to file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCandidateFromFile() {
        String filePath = "c:\\java\\DB\\Interview\\candidate.json";
        Path path = Paths.get(filePath);
        if(Files.exists(path)) {
            try {
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder jsonData = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonData.append(line);
                }
                fileReader.close();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Candidate>>() {
                }.getType();
                List<Candidate> getCandidateFile = gson.fromJson(jsonData.toString(), listType);
                candidateList=getCandidateFile;
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }
    public void getInteviewerFromFile() {
        String filePath = "c:\\java\\DB\\Interview\\interviewer.json";
        Path path = Paths.get(filePath);
        if(Files.exists(path)) {
            try {
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder jsonData = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonData.append(line);
                }
                fileReader.close();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Interviewer>>() {
                }.getType();
                List<Interviewer> getIntervewerFile = gson.fromJson(jsonData.toString(), listType);
                interviewerList=getIntervewerFile;
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }

    public void getCredentialFromFile() {
        String filePath = "c:\\java\\DB\\Interview\\credential.json";
        Path path = Paths.get(filePath);
        if(Files.exists(path)) {
            try {
                FileReader fileReader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder jsonData = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    jsonData.append(line);
                }
                fileReader.close();
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Credentials>>() {
                }.getType();
                List<Credentials> getCredentialFile = gson.fromJson(jsonData.toString(), listType);
                listOfCredential=getCredentialFile;
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }

    public boolean checkExist() {
        if(listOfCredential.size()==0){
            return true;
        }
        return false;
    }
}
