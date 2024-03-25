package com.librarymanagement.datalayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.librarymanagement.Model.*;
import in.interviewpanel2024.login.LoginModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {

    private static LibraryDatabase libraryDatabase;
    private Credentials passwordCheck=null;
    private List<Book> bookList=new ArrayList<>();
    private List<User> userList=new ArrayList<>();
    private List<IssueBook> issueBookList=new ArrayList<>();
    private List<Credentials> credentials=new ArrayList<>();
    private List<Library> libraryDetails=new ArrayList<>();

    public static LibraryDatabase getInstance() {
        if (libraryDatabase == null) {
            libraryDatabase = new LibraryDatabase();
        }
        return libraryDatabase;
    }

    public void insertLibrary(Library library1) {
        libraryDetails.add(library1);
    }

    public boolean insertCredentials(Credentials credentials) {
        this.credentials.add(credentials);
        return true;
    }

    public void addBook(Book book){
        bookList.add(book);
    }
    public List<Book> getAllBook(){
        return bookList;
    }
    public List<User> getALlUser(){
        return userList;
    }

    public List<IssueBook> getALlIssueBook(){
        return issueBookList;
    }
    public List<Book> searchBook(String bookName){
        List<Book> resultSearch=new ArrayList<>();
        for(Book book:bookList) {
            if (book.getName().startsWith(bookName) || book.getName().contains(bookName)) {
                resultSearch.add(book);
            }
        }
        return resultSearch;

    }
    public Book getBook(long bookId){
        for(Book book:bookList){
            if(bookId==book.getId()){
                return book;
            }
        }
        return null;
    }

    public boolean insertUser(User user) {
            boolean hasUser = false;
            for (User addedUser: userList) {
                if (addedUser.getRollno().equals(user.getRollno())) {
                    hasUser = true;
                    break;
                }
            }
            if (hasUser) {
                return false;
            } else {
                userList.add(user);
                return true;
            }
    }

    public void insertIssueBook(IssueBook issueBook){
        issueBookList.add(issueBook);
    }

    public void removeIssuedBook(IssueBook issueBook) {
        issueBookList.remove(issueBook);

    }

    public User getUser(String userId) {
        for(User user:userList){
            if(user.getRollno().equals(userId)){
                return user;
            }
        }return null;
    }
    public boolean checkUserId(List<Book> books,long bookId){
        for (Book book:books){
            if(book.getId()==bookId){
                return true;
            }
        }
        return false;
    }

    public boolean isUsernameCheck(String userName) {
           for(Credentials credential:credentials){
               if(credential.getUsername().equals(userName)) {
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

    public void dataUserStored() {
            Gson gson = new Gson();
            String jsonString = gson.toJson(userList);
            String filePath = "c:\\java\\DB\\user.json";
                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write(jsonString);
                    writer.close();
                    System.out.println("Successfully wrote JSON to file: " + filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }

    }
    public void dataLibraryStored() {
            Gson gson = new Gson();
            String jsonString = gson.toJson(libraryDetails);
            String filePath = "c:\\java\\DB\\library.json";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonString);
                writer.close();
                System.out.println("Successfully wrote JSON to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void dataBookStored() {
            Gson gson = new Gson();
            String jsonString = gson.toJson(bookList);
            String filePath = "c:\\java\\DB\\book.json";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonString);
                writer.close();
                System.out.println("Successfully wrote JSON to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    public void dataCredentialsStore() {
            Gson gson = new Gson();
            String jsonString = gson.toJson(credentials);
            String filePath = "c:\\java\\DB\\credential.json";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonString);
                writer.close();
                System.out.println("Successfully wrote JSON to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void dataIssuedBookStore() {
            Gson gson = new Gson();
            String jsonString = gson.toJson(issueBookList);
            String filePath = "c:\\java\\DB\\issuedbook.json";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonString);
                writer.close();
                System.out.println("Successfully wrote JSON to file: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void getUserFromFile() {
        String filePath = "c:\\java\\DB\\user.json";
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
                Type listType = new TypeToken<List<User>>() {
                }.getType();
                List<User> getUserFile = gson.fromJson(jsonData.toString(), listType);
                userList=getUserFile;
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }
    public void getLibraryFromFile() {
        String filePath = "c:\\java\\DB\\library.json";
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
                Type listType = new TypeToken<List<Library>>() {
                }.getType();
                List<Library> getLibraryFile = gson.fromJson(jsonData.toString(), listType);
                libraryDetails=getLibraryFile;

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }
    public void getBookFromFile() {
        String filePath = "c:\\java\\DB\\book.json";
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
                Type listType = new TypeToken<List<Book>>() {
                }.getType();
                List<Book> getBookFile = gson.fromJson(jsonData.toString(), listType);
                bookList=getBookFile;

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }
    public void getCredentialFromFile() {
        String filePath = "c:\\java\\DB\\credential.json";
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
                List<Credentials> getCredential = gson.fromJson(jsonData.toString(), listType);
                credentials=getCredential;

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }
    public void getIssueBookFromFile() {
        String filePath = "c:\\java\\DB\\issuedbook.json";
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
                Type listType = new TypeToken<List<IssueBook>>() {
                }.getType();
                List<IssueBook> getUserIssueBook = gson.fromJson(jsonData.toString(), listType);
                issueBookList=getUserIssueBook;

            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
    }

    public boolean changePass(String newPassword, String confirm) {
        if(newPassword.equals(confirm)) {
            passwordCheck.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public boolean isExistLibrary() {
        System.out.println(libraryDetails.size()+"library total size()");
        if(libraryDetails.size()==0){
            return true;
        }
        return false;
    }

    public boolean checkExist() {
        if(credentials.size()==0){
            return true;
        }
        return false;
    }
}
