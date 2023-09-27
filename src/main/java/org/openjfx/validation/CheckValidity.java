package org.openjfx.validation;

import org.openjfx.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckValidity {

    public static boolean isNameValid(String name){
        for (int i = 0; i < name.length(); i++) {
            if(!Character.isAlphabetic(name.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isUsernameValid(String username){ // spaces

        for (int i = 0; i < username.length(); i++) {
            if(username.charAt(i) == ' '){
                return false;
            }
        }
        return true;

    }

    public static boolean isUsernameRepeated(String username){

        for (int i = 0; i < User.getActiveUsers().size(); i++) {
            if (User.getActiveUsers().get(i).getUsername().equals(username)) {
                return true;
            }
        }
        for (int i = 0; i < User.getInactiveUsers().size(); i++) {
            if(User.getInactiveUsers().get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;

    }

    public static boolean isPhoneNumberRepeated(String phoneNumber){

        for (int i = 0; i < User.getActiveUsers().size(); i++) {
            try {
                if (User.getActiveUsers().get(i).getPhoneNumber().equals(phoneNumber)) {
                    return true;
                }
            } catch (Exception e) {}
        }
        for (int i = 0; i < User.getInactiveUsers().size(); i++) {
            try {
                if (User.getInactiveUsers().get(i).getPhoneNumber().equals(phoneNumber)) {
                    return true;
                }
            } catch (Exception e) {}
        }
        return false;

    }

    public static boolean isEmailRepeated(String email){

        for (int i = 0; i < User.getActiveUsers().size(); i++) {
            if (User.getActiveUsers().get(i).getEmail().equals(email)) {
                return true;
            }
        }
        for (int i = 0; i < User.getInactiveUsers().size(); i++) {
            if(User.getInactiveUsers().get(i).getEmail().equals(email)){
                return true;
            }
        }
        return false;

    }

    public static boolean isNumberValid(String phoneNumber){
        for (int i = 0; i < phoneNumber.length(); i++) {
            if(!Character.isDigit(phoneNumber.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean isValidEmail(String email) {
        final String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}