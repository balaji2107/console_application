package com.librarymanagement.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryValidate {
    public boolean validEmail(String email){
        if(!Character.isLetter(email.charAt(0)) || !Character.isLetter(email.charAt(email.length()-1))){
            return false;
        }
        String[] arr=email.split("@");
        if(arr.length != 2){
            return false;
        }
        String first=arr[0];
        if(first.charAt(first.length()-1)=='.'){
            return false;
        }
        for(int j=0;j<first.length();j++){
            char k=first.charAt(j);

            if((Character.isLetterOrDigit(k) && Character.isLowerCase(k)) || k=='.'){

                if(j<arr[0].length()-1 && k=='.' && arr[0].charAt(j+1) =='.'){
                    return false;
                }
            }
        }
        String second=arr[1];
        int count=0;
        if(second==null){return false;}
        if(!Character.isLetter(second.charAt(0))){
            return false;
        }
        for(int i=0;i<second.length();i++){
            char k=second.charAt(i);
            if(k=='.'){
                count++;
            }
            if(Character.isLowerCase(k) || k=='.'){
                if(i<second.length()-1 && k=='.' && second.charAt(i+1) =='.'){
                    return false;
                }
            }
            else{
                return false;
            }
        }
        if(count<1 || count>2){
            return false;
        }
        return true;
    }
    public boolean validPhoneNo(long phoneNo){
        String phoneNumber=String.valueOf(phoneNo);
        if(phoneNumber.length()==10)
            return true;
        return false;
    }
    private String regexPhoneNumber = "^(?:(?:\\+|0{0,2})91(\\s*|[\\-])?|[0]?)?[789]\\d{9}$";

    public  boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(regexPhoneNumber);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

}
