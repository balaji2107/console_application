package com.librarymanagement.librarysetup;

import com.librarymanagement.Model.Library;
import com.librarymanagement.Model.User;
import com.librarymanagement.datalayer.LibraryDatabase;
import com.librarymanagement.validate.LibraryValidate;

import java.util.List;

public class LibrarySetupModel {
    private LibrarySetupView librarySetupView;
    LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.librarySetupView = librarySetupView;
    }

    public void storeFile() {
        LibraryDatabase.getInstance().dataUserStored();
        LibraryDatabase.getInstance().dataBookStored();
        LibraryDatabase.getInstance().dataIssuedBookStore();
        LibraryDatabase.getInstance().dataCredentialsStore();
        LibraryDatabase.getInstance().dataLibraryStored();
    }


    public void startSetup() {
        if (LibraryDatabase.getInstance().isExistLibrary()) {
            librarySetupView.initiateSetup();
        } else {
            librarySetupView.onSetupComplete();
        }
    }

    public void createLibrary(Library library) {
        LibraryValidate libraryValidate = new LibraryValidate();
        if (libraryValidate.validEmail(library.getEmailId()) && libraryValidate.isValidPhoneNumber(library.getPhoneNo())) {
            LibraryDatabase.getInstance().insertLibrary(library);
            librarySetupView.onSetupComplete();
        } else {
            librarySetupView.showAlert("Please Enter valid input");
            librarySetupView.initiateSetup();

        }
    }
    public void getAllFromDB() {
        LibraryDatabase.getInstance().getUserFromFile();
        LibraryDatabase.getInstance().getBookFromFile();
        LibraryDatabase.getInstance().getIssueBookFromFile();
    }


}
