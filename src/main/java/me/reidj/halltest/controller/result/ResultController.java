package me.reidj.halltest.controller.result;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import me.reidj.halltest.App;
import me.reidj.halltest.controller.AbstractScene;
import me.reidj.halltest.exception.Errors;
import me.reidj.halltest.factor.Factors;
import me.reidj.halltest.user.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ResultController extends AbstractScene {

    @FXML
    private Label resultLabel;

    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
    private final HashMap<Factors, Integer> sums = new HashMap<>();

    public ResultController() {
        super("result/resultScene.fxml");
    }

    @FXML
    private void initialize() {
        for (Factors factor : Factors.values()) {
            factor.getIndexes().forEach(index -> App.getApp().getUser().getQuestions().forEach((question, i) -> {
                if (index == question.index) {
                    sums.compute(factor, (key, value) -> (value == null) ? i : value + i);
                }
            }));
            if (sums.get(factor) < 0) {
                resultLabel.setText(factor.getTitle());
            }
        }
    }

    @FXML
    void saveResult() {
        String resultText = resultLabel.getText();
        User user = App.getApp().getUser();

        createWordDocument();

        onWrite(new StringBuilder("Результат прохождения теста\n" + getText(resultText, user)));

        App.getApp().getPrimaryStage().showAlert(
                Alert.AlertType.CONFIRMATION,
                "Файл успешно сохранён",
                "Успешно!"
        );
    }

    public void createWordDocument() {
        File selectedDirectory = directoryChooser.showDialog(App.getApp().getPrimaryStage().stage());
        User user = App.getApp().getUser();
        try {
            App.getApp().getFileManager().createFileByPath(
                    selectedDirectory.getAbsolutePath() + "\\"
                            + dateTimeFormatter.format(LocalDateTime.now()) + "_"
                            + user.getName() + "_" + user.getSurname() + ".doc"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onWrite(StringBuilder stringBuilder) {
        App.getApp().getFileManager().onWrite(stringBuilder.toString().getBytes());
    }

    private static String getText(String resultText, User user) {
        return Errors.FIELD_EMPTY.check(resultText) ? "Факторов выявлено не было." : "Выявлен фактор: " + resultText + "\n" +
                "Класс: " + user.getSurname() + "\n" +
                "Ученик: " + user.getName() + " " + user.getSurname() + " " + user.getPatronymic() + "\n" +
                "Время прохождения: " + App.getApp().getQuestionController().formatTime();
    }
}
