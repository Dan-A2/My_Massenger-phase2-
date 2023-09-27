package org.openjfx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.openjfx.controller.saveLoad.SaveNLoad;
import org.openjfx.view.entrance.Login;

public class Main extends Application {

    public static void main(String[] args) {
        SaveNLoad.load();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        SceneManager.setMainStage(primaryStage);
        Login login = new Login();
        login.generate();
    }

}