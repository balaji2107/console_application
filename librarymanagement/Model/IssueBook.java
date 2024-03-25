package com.librarymanagement.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class IssueBook {
    private int userId;
    private LocalDate returnDate;
    private String RollNo;
    private List<Book> bookName=new ArrayList<>();

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public List<Book> getBookName() {
        return bookName;
    }
    public void setBookName(Book bookName1) {
        bookName.add(bookName1);
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
