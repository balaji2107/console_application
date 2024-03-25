package com.librarymanagement.managebooksetup;

import com.librarymanagement.Model.Book;
import com.librarymanagement.datalayer.LibraryDatabase;

import java.util.List;

class BookSetupModel {
    private BookSetupView bookSetupView;

    private LibraryDatabase libraryDatabase;

    BookSetupModel(BookSetupView bookSetupView) {
        this.libraryDatabase=LibraryDatabase.getInstance();
        this.bookSetupView = bookSetupView;
    }

    public void createBook(long bookId, String bookName, String authorName, String bookPublication, String bookEdition, int bookCount, String bookJournal, int bookVolumn) {
        if(libraryDatabase.getBook(bookId)==null){
            Book book=new Book();
            book.setId(bookId);
            book.setName(bookName);
            book.setAuthor(authorName);
            book.setPublication(bookPublication);
            book.setEdition(bookEdition);
            book.setBookCount(bookCount);
            book.setJournel(bookJournal);
            book.setVolume(bookVolumn);
            libraryDatabase.addBook(book);
            bookSetupView.onSuccess("Book added successfully");
        }
        else {
            bookSetupView.showAlert("Book Id Already exist");
            bookSetupView.addBook();
        }
    }

    public void findBook(String bookName) {
        if(bookName.length()!=0) {
            List<Book> listOfBook = libraryDatabase.searchBook(bookName);
            if(listOfBook.size()==0){
                bookSetupView.showAlert("No Book Found...");
            }else {
                bookSetupView.showSearchResult(listOfBook);
            }
        }
        else {
            bookSetupView.showAlert("No Book Found...");
        }
    }

    public void modifiyBook(long bookId, String bookEdition, int bookCount, String bookJournal, int bookVolumn) {
        List<Book> listOfBook=libraryDatabase.getAllBook();
        for(Book book:listOfBook){
            if(book.getId()==bookId){
                book.setEdition(bookEdition);
                book.setBookCount(bookCount);
                book.setJournel(bookJournal);
                book.setVolume(bookVolumn);
                bookSetupView.onSuccess("Book Update Successfully");
                bookSetupView.showSearchResult(listOfBook);
            }
        }
    }

    public void showAllBook() {
        List<Book> listOfBook=libraryDatabase.getAllBook();
        if(listOfBook.size()==0){
            bookSetupView.showAlert("No Book Found");
        }
        else {
            bookSetupView.showSearchResult(listOfBook);
        }
    }

    public boolean isExist(long bookId) {
        List<Book> listOfBook=libraryDatabase.getAllBook();
        for(Book book:listOfBook){
            if(book.getId()==bookId){
                return true;
            }
        }
        bookSetupView.showAlert("Book Id Does not exist");
        return false;

    }

    public void removeBookName(String bookName) {
        List<Book> listOfBook=libraryDatabase.getAllBook();
        for(Book book:listOfBook){
            if(book.getName().equals(bookName)){
                if(book.getBookCount()>0){
                    book.setBookCount(book.getBookCount()-1);
                    bookSetupView.onSuccess("Book Removed Successfully...");
                    return;
                }
            }
        }
    }
}
