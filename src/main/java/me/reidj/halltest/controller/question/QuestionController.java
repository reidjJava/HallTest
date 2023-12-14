package me.reidj.halltest.controller.question;

import com.google.gson.Gson;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import me.reidj.halltest.App;
import me.reidj.halltest.controller.AbstractScene;
import me.reidj.halltest.question.Question;
import me.reidj.halltest.question.QuestionList;
import me.reidj.halltest.user.User;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class QuestionController extends AbstractScene {

    private static final String JSON_FILE_PATH = "/questions.json";

    @FXML
    private Label questionLabel;
    @FXML
    private Label timeLabel;
    private Iterator<Question> questionIterator;
    private final Gson gson = new Gson();
    private Timeline timeline;
    private Question currentQuestion;

    public QuestionController() {
        super("questions/questionsScene.fxml");
    }

    @FXML
    private void initialize() {
        QuestionList questions = loadQuestionsFromJson();

        if (questions != null)
            questionIterator = questions.questions.iterator();

        startTimer();

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (questionIterator.hasNext()) {
            currentQuestion = questionIterator.next();

            questionLabel.setText("#" + currentQuestion.index + ". " + currentQuestion.text);

        } else {
            App.getApp().getPrimaryStage().showScene(App.getApp().getResultController().getScene());
            stopTimer();
        }
    }

    // Полностью не согласен
    @FXML
    void completelyDisagree() {
        extracted(-3);
    }

    // В основном согласен
    @FXML
    void mostlyAgree() {
        extracted(2);
    }

    // В основном не согласен
    @FXML
    void mostlyDisagree() {
        extracted(-2);
    }

    // Отчасти согласен
    @FXML
    void partiallyAgree() {
        extracted(1);
    }

    // Отчасти не согласен
    @FXML
    void partlyDisagree() {
        extracted(-1);
    }

    // Полностью согласен
    @FXML
    void completelyAgree() {
        extracted(3);
    }

    private void extracted(int point) {
        questionLabel.setText("");
        App.getApp()
                .getUser()
                .getQuestions()
                .put(currentQuestion, point);
        displayNextQuestion();
    }

    private QuestionList loadQuestionsFromJson() {
        try (InputStream inputStream = getClass().getResourceAsStream(JSON_FILE_PATH);
             InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, QuestionList.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startTimer() {
        if (timeline == null || timeline.getStatus() == Animation.Status.STOPPED) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> Platform.runLater(this::updateTimer)));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

    private void updateTimer() {
        User user = App.getApp().getUser();
        user.setSeconds(user.getSeconds() + 1);
        if (user.getSeconds() == 60) {
            user.setSeconds(0);
            user.setMinutes(user.getMinutes() + 1);
            if (user.getMinutes() == 60) {
                user.setMinutes(0);
                user.setHour(user.getHour() + 1);
            }
        }
        timeLabel.setText(formatTime());
    }

    private void stopTimer() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }
    }

    public String formatTime() {
        User user = App.getApp().getUser();
        return String.format("%02d:%02d:%02d", user.getHour(), user.getMinutes(), user.getSeconds());
    }
}
