package com.librarymanagement.issuebook;

import com.librarymanagement.Model.IssueBook;
import com.librarymanagement.Model.User;
import com.librarymanagement.datalayer.LibraryDatabase;
import com.librarymanagement.Model.Book;
import java.util.List;

public class IssueBookModel {
    private IssueBookView issueBookView;
    private LibraryDatabase libraryDatabase;

    IssueBookModel(IssueBookView issueBookView) {
        libraryDatabase = LibraryDatabase.getInstance();
        this.issueBookView = issueBookView;
    }

    public void isUserExist(String userId) {
        List<User> listOfUser = LibraryDatabase.getInstance().getALlUser();
        for (User user : listOfUser) {
            if (user.getRollno().equals(userId)) {
                if(user.getBookCount()<3) {
                    issueBookView.getBookDetail(user);
                    return;
                }else {
                    issueBookView.showAlert("limit exceed...");
                    issueBookView.checkForReturnBook();
                    return;
                }
            }
        }
        issueBookView.showAlert("User Doesn't Exist");
    }
    public void getAllIssue() {
        List<IssueBook> listOfAllIssue = libraryDatabase.getALlIssueBook();
        if(listOfAllIssue.size()==0){
            issueBookView.showAlert("No Record Found");
        }
        else {
            issueBookView.showAllIssueBook(listOfAllIssue);
        }
    }

    public void showAllBook() {
        List<Book> lisOfAllBook = LibraryDatabase.getInstance().getAllBook();
        if(lisOfAllBook.size()==0){
            issueBookView.showAlert("No Record Found");
        }
        else {
            issueBookView.showSearchResult(lisOfAllBook);
        }
    }

    public void isUserIdExist(String userId) {
        List<IssueBook> listOfIssueBook = LibraryDatabase.getInstance().getALlIssueBook();
        boolean flag = false;
        for (IssueBook issueBook : listOfIssueBook) {
            if (issueBook.getRollNo().equals(userId)) {
                flag = true;
            }
        }
        if (flag) {
            issueBookView.getReturnBookDetail(userId);
        } else {
            issueBookView.showAlert("User Doesn't Exist");
            issueBookView.returnBookUserId();
        }
    }

    public boolean checkBookName(String userId,String bookName) {
        List<IssueBook> listOfIssueBook = LibraryDatabase.getInstance().getALlIssueBook();
        List<Book> listOfBook=LibraryDatabase.getInstance().getAllBook();
        Book book=null;
        for(Book book1:listOfBook){
            if(book1.getName().equalsIgnoreCase(bookName)) {
                book = book1;
            }
        }
        if(book==null){
            issueBookView.showAlert("Book not exist");
            return false;
        }
        for (IssueBook issueBook : listOfIssueBook) {
            if(issueBook.getRollNo().equals(userId) && LibraryDatabase.getInstance().checkUserId(issueBook.getBookName(),book.getId())) {
                List<Book> userListOfBook = issueBook.getBookName();
                if (userListOfBook.size() == 1) {
                    LibraryDatabase.getInstance().removeIssuedBook(issueBook);
                } else{
                    for (Book book1 : userListOfBook) {
                        if (book1.getName().equalsIgnoreCase(bookName)) {
                            issueBook.getBookName().remove(book1);
                        }
                    }
                }
               book.setBookCount(book.getBookCount()+1);
               User user=LibraryDatabase.getInstance().getUser(userId);
               LibraryDatabase.getInstance().getUser(userId).setBookCount(user.getBookCount()-1);
               return true;
            }
        }
        return false;
    }
    public void isBookExist(User user,String bookName) {

        List<Book> listOfBook=LibraryDatabase.getInstance().getAllBook();
        for(Book book:listOfBook){
            if(book.getName().equals(bookName) && book.getBookCount()>0){
                book.setBookCount(book.getBookCount()-1);
                user.setBookCount(user.getBookCount()+1);
                assignBook(user.getRollno(),book);
                issueBookView.onSuccess("Book Lending successfull....");
                return;
            }
        }
        issueBookView.showAlert("Book doesn't exist");
    }

    private void assignBook(String RollNo, Book book) {
        List<IssueBook> listOfIssueBook = LibraryDatabase.getInstance().getALlIssueBook();
        boolean flag = false;
        for (IssueBook issueBook : listOfIssueBook) {
            if (RollNo.equals(issueBook.getRollNo())) {
                flag=true;
                issueBook.setBookName(book);
            }
        }
        if(!flag){
            IssueBook issueBook = new IssueBook();
            issueBook.setUserId(issueBook.getUserId()+1);
            issueBook.setRollNo(RollNo);
            issueBook.setBookName(book);
            libraryDatabase.insertIssueBook(issueBook);
        }
    }
}
