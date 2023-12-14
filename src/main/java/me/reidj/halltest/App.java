package me.reidj.halltest;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import me.reidj.halltest.controller.PrimaryStage;
import me.reidj.halltest.controller.authorization.AuthorizationController;
import me.reidj.halltest.user.User;

@Getter
public class App extends Application {
    @Getter
    private static App app;

    @Setter
    private User user;

    private PrimaryStage primaryStage;
    private AuthorizationController authorizationController;

    @Override
    public void start(Stage stage) {
        app = this;

        primaryStage = new PrimaryStage(stage);
        authorizationController = new AuthorizationController();

        primaryStage.setScene(authorizationController.getScene());
    }

    public static void main(String[] args) {
        launch();
    }
}