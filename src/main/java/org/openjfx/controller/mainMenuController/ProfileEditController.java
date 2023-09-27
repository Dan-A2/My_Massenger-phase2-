package org.openjfx.controller.mainMenuController;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class ProfileEditController {

    static private final Logger logger = (Logger) LogManager.getLogger(ProfileEditController.class);

    public void change(User user, String firstname, String lastname, String username, String password, String email, String bio, String phoneNumber, LocalDate birthday){

        UserController controller = new UserController();
        if(!user.getFirstName().equals(firstname)) {
            controller.changeFirstName(user, firstname);
        }
        if(!user.getLastName().equals(lastname)) {
            controller.changeLastName(user, lastname);
        }
        controller.changeUsername(user, username);
        if(!user.getPassword().equals(password)) {
            controller.changePassword(user, password);
        }
        if(!user.getEmail().equals(email)){
            controller.changeEmail(user, email);
        }
        if(!phoneNumber.equals("") && !user.getPhoneNumber().equals(phoneNumber)){
            controller.changePhoneNumber(user, phoneNumber);
        }
        if(bio != null && !bio.equals("")){
            controller.changeBio(user, bio);
        }
        if(birthday != null){
            controller.changeBirthday(user, birthday);
        }
        logger.info("User " + user.getUsername() + " has edited his/her profile");
        SaveNLoad.saveUsers();

    }

}