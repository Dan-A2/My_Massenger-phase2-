package org.openjfx.controller.registrationController;

import org.openjfx.models.User;
import org.openjfx.validation.CheckValidity;
import org.openjfx.view.mainMenu.Mainmenu;

import java.time.LocalDate;

public class RegisterController {

    public void register(String firstname, String lastname,String username, String password, String email, String bio, String phoneNumber, LocalDate birthday){
        User newUser = new User(firstname, lastname, username , password, email);
        newUser.setPhoneNumber(phoneNumber);
        if(!bio.equals("")){
            newUser.setBio(bio);
        }
        if(birthday != null){
            newUser.setBirthday(birthday);
        }
        new Mainmenu(newUser);
    }

}
