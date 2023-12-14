package me.reidj.halltest.controller;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public record PrimaryStage(Stage stage) {

    private static final String TITLE = "Тест эмоционального интеллекта Холла";

    public void setScene(Scene scene) {
        stage.setOnHidden(event -> Platform.exit());
        stage.sizeToScene();
        stage.setTitle(TITLE);
        stage.setResizable(false);
        showScene(scene);
    }

    public void showScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public void showAlert(Alert.AlertType alertType, String contextText, String headerText) {
        Alert alert = new Alert(alertType, contextText);
        alert.setTitle(alertType == Alert.AlertType.ERROR ? "Ошибка!" : "Успешно!");
        alert.setHeaderText(headerText);
        alert.show();
    }
}
