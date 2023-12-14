package me.reidj.halltest;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import me.reidj.halltest.controller.PrimaryStage;
import me.reidj.halltest.controller.authorization.AuthorizationController;
import me.reidj.halltest.controller.question.QuestionController;
import me.reidj.halltest.controller.result.ResultController;
import me.reidj.halltest.manager.FileManager;
import me.reidj.halltest.user.User;

@Getter
public class App extends Application {
    @Getter
    private static App app;

    @Setter
    private User user;

    private PrimaryStage primaryStage;
    private AuthorizationController authorizationController;
    private QuestionController questionController;
    private ResultController resultController;
    private FileManager fileManager;

    @Override
    public void start(Stage stage) {
        app = this;

        primaryStage = new PrimaryStage(stage);
        authorizationController = new AuthorizationController();
        questionController = new QuestionController();
        resultController = new ResultController();
        fileManager = new FileManager();

        primaryStage.setScene(authorizationController.getScene());
    }

    public static void main(String[] args) {
        launch();
    }
}