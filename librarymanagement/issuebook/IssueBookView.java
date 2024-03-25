package com.librarymanagement.issuebook;

import com.librarymanagement.Model.Book;
import com.librarymanagement.Model.IssueBook;
import com.librarymanagement.Model.User;
import in.interviewpanel2024.model.Candidate;

import java.util.List;
import java.util.Scanner;

public class IssueBookView {

    private IssueBookModel issueBookModel;
    Scanner sc = new Scanner(System.in);

    public IssueBookView() {
        issueBookModel = new IssueBookModel(this);
    }

    public void init() {
        while (true) {
            System.out.println("1. View ALl Books\n2. Add book lending\n3. view lending Book\n4. return book\n5. Exit");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    viewAllBook();
                    break;
                case 2:
                    initStart();
                    break;
                case 3:
                    viewIssueBook();
                    break;
                case 4:
                    returnBookUserId();
                    break;
                case 5:
                    System.out.println("--Thank You!--");
                    return;
                default:
                    System.out.println("Please enter valid Choice");
            }

        }
    }

    private void viewAllBook() {
        issueBookModel.showAllBook();
    }

    private void viewIssueBook() {
        issueBookModel.getAllIssue();
    }


    private void initStart() {
            System.out.println("Enter the User RollNO: ");
            sc.nextLine();
            String userRoll = sc.nextLine();
            issueBookModel.isUserExist(userRoll);
    }

    public void showAlert(String s) {
        System.out.println(s);

    }

    public void getBookDetail(User user) {

        System.out.println("Enter the Book Name:");
        String bookName = sc.nextLine();
        issueBookModel.isBookExist(user,bookName);
    }
     public void checkForReturnBook() {
        System.out.println("Do you want return book? \nType Yes/No");
        sc.nextLine();
        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            returnBookUserId();
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("\n ---- Thanks You ----");
        } else {
            System.out.println("\nInvalid choice, Please enter valid choice.\n");
            checkForReturnBook();
        }
    }

    public void returnBookUserId() {
        System.out.println("Enter the User Roll No: ");
        sc.nextLine();
        String userId = sc.nextLine();
        issueBookModel.isUserIdExist(userId);

    }
    public void getReturnBookDetail(String userId){

        System.out.println("Enter the return Book name: ");
        String bookName=sc.nextLine();
        if(issueBookModel.checkBookName(userId,bookName)){
            System.out.println("Book return Completed...");
        }
        else {
            System.out.println("Book return uncomplete..");
        }
    }

    public void bookAlert(String s) {
        System.out.println(s);
    }


    public void showAllIssueBook(List<IssueBook> listOfAllIssue) {
        System.out.println("Issue list");
        System.out.printf(" %10s %20s", "User RollNO", "Book Name");
        System.out.println();
        for (IssueBook issueBook : listOfAllIssue) {
            System.out.format("%10s", issueBook.getRollNo());
            for(Book book:issueBook.getBookName()){
                System.out.format("%20s", book.getName());
                System.out.println();
                System.out.format("%10s"," ");
            }
            System.out.println();
        }
    }

    public void showSearchResult(List<Book> searchListBook) {
        System.out.println("-----Search result-----");
        System.out.println("Book ID \t Book Name \t Book Author Name \t Book Edition \t Book Availability \t Book Journal \t Book Volume");
        System.out.println();
        for (Book book : searchListBook) {
            System.out.println("\t" + book.getId() + "\t\t" + book.getName() + "\t\t\t" + book.getAuthor() + "\t\t\t\t" + book.getEdition() + "\t\t\t\t" + book.getBookCount() + "\t\t\t" + book.getJournel() + "\t\t\t" + book.getVolume());
        }
    }

    public void onSuccess(String s) {
        System.out.println(s);
    }
}
