package org.openjfx.controller.registrationController;

import org.openjfx.controller.modelController.UserController;
import org.openjfx.models.User;
import org.openjfx.view.mainMenu.Mainmenu;

public class LoginController {

    public boolean isInputValid(String username, String password){
        boolean flag = false;
        for (User user : User.getActiveUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                flag = true;
                break;
            }
        }
        for (User user : User.getInactiveUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void login(String username, String password){
        if(isInputValid(username, password)) {
            UserController controller = new UserController();
            User currentUser = controller.find(username);
            new Mainmenu(currentUser);
        }
    }

}