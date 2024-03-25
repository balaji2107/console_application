package com.librarymanagement.managebooksetup;

import com.librarymanagement.Model.Book;

import java.util.List;
import java.util.Scanner;

public class BookSetupView {
    Scanner sc=new Scanner(System.in);
    private BookSetupModel bookSetupModel;

    public BookSetupView() {
        bookSetupModel = new BookSetupModel(this);
    }

    public void init() {

        while (true) {
            System.out.println("1. Add Book \n2. Search Book \n3. Update Book \n4. view Book\n5. Remove Book\n6. Exit");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    viewBook();
                    break;
                case 5:
                    removeBook();
                    break;
                case 6:
                    System.out.println("Thank You for Adding Book...");
                    return;
                default:
                    System.out.println("Please Enter Valid Choice");
            }

        }

    }

    private void removeBook() {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Remove Book Name: ");
        String bookName=sc.nextLine();
        bookSetupModel.removeBookName(bookName);
    }

    public void showSearchResult(List<Book> searchListBook){
        System.out.println("-----Search result-----");
        System.out.println("Book ID \t Book Name \t Book Author Name \t Book Edition \t Book Availability \t Book Journal \t Book Volume");
        System.out.println();
        for(Book book:searchListBook){
            System.out.println("\t" +book.getId()+"\t\t"+book.getName()+"\t\t\t"+book.getAuthor()+"\t\t\t\t"+book.getEdition()+"\t\t\t\t"+book.getBookCount()+"\t\t\t"+book.getJournel()+"\t\t\t"+book.getVolume());

        }
    }
    private void viewBook() {
        bookSetupModel.showAllBook();
    }
    private void updateBook() {
        try {
            System.out.println("Please Enter Book Id to update the record: ");
            long bookId = sc.nextLong();
            sc.nextLine();
            if (bookSetupModel.isExist(bookId)) {
                System.out.println("Enter the book edition: ");
                String bookEdition = sc.nextLine();
                System.out.println("Enter the book Count: ");
                int bookCount = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the book Journal: ");
                String bookJournal = sc.nextLine();
                System.out.println("Enter the book Volumn: ");
                int bookVolumn = sc.nextInt();
                bookSetupModel.modifiyBook(bookId, bookEdition, bookCount, bookJournal, bookVolumn);
            }
        }
        catch (Exception e){
            System.out.println("Please Enter Valid Input");
            updateBook();
        }
    }

    private void searchBook() {
        sc.nextLine();
        System.out.println("Enter the book name to search the book: ");
        String bookName=sc.nextLine().trim().toLowerCase();
        bookSetupModel.findBook(bookName);
    }

    public void addBook(){
        try {
            System.out.println("Enter the Book Id: ");
            long bookId = sc.nextLong();
            sc.nextLine();
            System.out.println("Enter the Book name: ");
            String bookName = sc.nextLine().toLowerCase();
            System.out.println("Enter the author name: ");
            String authorName = sc.nextLine();
            System.out.println("Enter the publication: ");
            String bookPublication = sc.nextLine();
            System.out.println("Enter the book edition: ");
            String bookEdition = sc.nextLine();
            System.out.println("Enter the total book count: ");
            int bookCount=sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the book Journal: ");
            String bookJournal = sc.nextLine();
            System.out.println("Enter the book Volume: ");
            int bookVolumn = sc.nextInt();
            bookSetupModel.createBook(bookId, bookName, authorName, bookPublication, bookEdition, bookCount, bookJournal, bookVolumn);
        }
        catch (Exception e){
            System.out.println("Please Enter Valid Input");
            addBook();
        }
    }

    public void showAlert(String bookIdAlreadyExist) {
        System.out.println(bookIdAlreadyExist);
    }

    public void onSuccess(String success) {
        System.out.println(success);
    }
}
